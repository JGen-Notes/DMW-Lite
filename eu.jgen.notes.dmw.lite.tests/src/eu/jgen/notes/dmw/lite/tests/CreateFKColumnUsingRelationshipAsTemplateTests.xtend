package eu.jgen.notes.dmw.lite.tests

import com.google.inject.Inject
import com.google.inject.Provider
import org.eclipse.xtext.testing.InjectWith
import org.eclipse.xtext.testing.XtextRunner
import org.eclipse.xtext.testing.util.ParseHelper
import org.eclipse.xtext.testing.validation.ValidationTestHelper
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import eu.jgen.notes.dmw.lite.lang.YWidget
import eu.jgen.notes.dmw.lite.lang.YAnnotRel
import eu.jgen.notes.dmw.lite.lang.YAnnotAbstractColumn
import eu.jgen.notes.dmw.lite.lang.YAnnotColumnLike
import eu.jgen.notes.dmw.lite.utility.LangLib
import org.eclipse.emf.ecore.resource.ResourceSet
import eu.jgen.notes.dmw.lite.utility.LangDBUtil

@RunWith(XtextRunner)
@InjectWith(LangInjectorProvider)
class CreateFKColumnUsingRelationshipAsTemplateTests {

	@Inject extension ParseHelper<YWidget>
	@Inject extension LangDBUtil
	@Inject extension ValidationTestHelper
	@Inject extension LangLib
	@Inject Provider<ResourceSet> resourceSetProvider;

	@Test
	def void testEntityDefault() {
		val model = '''
			@database MySQL
			
			@entity Log {
				@attr entryType : Short @length(2);
				@rel @parent has -> Description * <- Description.isFor;
				@id logid(entryType);
			}
			
			@entity Description {
				@attr number : Short @length(2);
				@attr message : String @length(128);
				@rel isFor -> Log <- Log.has;
				@id logid(number);
			}
			
			@td database MySQL {
				@table LOG -> Log {
					@column ENTRY_TYPE -> Log.entryType as CHAR @length(2);
					@primary (ENTRY_TYPE);
				}
				@table DESCRIPTION -> Description {
					@column NUMBER -> Description.number as SMALLINT @length (2)
					@column MESSAGE -> Description.message as CHAR @length (128)
					@primary (NUMBER)
					   @foreign Description.isFor { 
					   	@column FK_LOG__ENTRY_TYPE -> LOG.ENTRY_TYPE
					   }
			}
			
			}
		'''.loadLibAndParse

		model.validate.forEach[println(it)]

		model.eAllContents.filter[it instanceof YAnnotRel].forEach [
			val relationship = it as YAnnotRel;
			if (!relationship.parent) {
				val foreignKeys = relationship.converRelationshipIntoForeignKeys
				if (foreignKeys !== null) {
					val foreignKey = foreignKeys.columns.findFirst[true]
					println(foreignKey.name)
					Assert.assertEquals("FK_LOG__ENTRY_TYPE", foreignKey.name)
				}
			}
		]
	}

	def private loadLibAndParse(CharSequence p) {
		val resourceSet = resourceSetProvider.get
		loadLib(resourceSet)
		p.parse(resourceSet)
	}
}
