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
			@entity Log {
				@attr entryType : Short @length (2);
				@rel has -> Description* <- Description.isFor;
				@id logid (timeCreated);
			}
			
			@entity Description {
				@attr number : Short @length (2);
				@attr message : String @length (128);
				@rel isFor -> Log <- Log.has;
				@id logid (number);
			}
			
			@td {
				@table LOG -> Log {
					@column ENTRY_TYPE -> Log.entryType as CHAR @length(2);
					@primary (ENTRY_TYPE)
					
				}
			}
		'''.loadLibAndParse

		model.validate.forEach[println(it)]

		model.eAllContents.filter[it instanceof YAnnotRel].forEach [
			val fk = (it as YAnnotRel).converRelationshipIntoForeignKeys
			if (fk !== null) {
				fk.columns.forEach [
					Assert.assertEquals("FK_LOG__ENTRY_TYPE", it.name)
					Assert.assertEquals("ENTRY_TYPE",
						((it.type as YAnnotColumnLike).columnref.eContainer as YAnnotAbstractColumn).name)
				]
			}
		]
	}

	def private loadLibAndParse(CharSequence p) {
		val resourceSet = resourceSetProvider.get
		loadLib(resourceSet)
		p.parse(resourceSet)
	}
}
