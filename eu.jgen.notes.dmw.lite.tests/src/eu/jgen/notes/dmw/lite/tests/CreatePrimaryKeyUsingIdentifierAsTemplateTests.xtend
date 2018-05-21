package eu.jgen.notes.dmw.lite.tests

import com.google.inject.Inject
import org.eclipse.xtext.testing.InjectWith
import org.eclipse.xtext.testing.XtextRunner
import org.eclipse.xtext.testing.util.ParseHelper
import org.eclipse.xtext.testing.validation.ValidationTestHelper
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import eu.jgen.notes.dmw.lite.lang.YWidget
import eu.jgen.notes.dmw.lite.lang.YAnnotId
import eu.jgen.notes.dmw.lite.utility.LangLib
import com.google.inject.Provider
import org.eclipse.emf.ecore.resource.ResourceSet
import eu.jgen.notes.dmw.lite.utility.LangDBUtil

@RunWith(XtextRunner)
@InjectWith(LangInjectorProvider)
class CreatePrimaryKeyUsingIdentifierAsTemplateTests {

	@Inject extension ParseHelper<YWidget>
	@Inject extension LangDBUtil
	@Inject extension ValidationTestHelper
	@Inject extension LangLib
	@Inject Provider<ResourceSet> resourceSetProvider;

	@Test
	def void testEntityDefault() {
		val model = '''
			package blue.dm
			
			@entity Log {
				@attr entryType : Short @length (2);
				@rel has -> Description* <- Description.isFor;
				@id logid (entryType);
			}
			
			@td {
				@table LOG -> Log {
					@column ENTRY_TYPE -> Log.entryType as CHAR @length(23);
					@primary (ENTRY_TYPE)
					
				}
			}
		'''.loadLibAndParse

		model.validate.forEach[println(it)]

		val id = model.eAllContents.filter[it instanceof YAnnotId].last as YAnnotId
		val pk = id.converIdentifierIntoPrimaryKey
		if (pk !== null) {
			Assert.assertEquals("ENTRY_TYPE", pk.columns.head.name)
		}

	}

	def private loadLibAndParse(CharSequence p) {
		val resourceSet = resourceSetProvider.get
		loadLib(resourceSet)
		p.parse(resourceSet)
	}

}
