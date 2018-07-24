package eu.jgen.notes.dmw.lite.generator

import eu.jgen.notes.dmw.lite.lang.YWidget
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.xtext.generator.IFileSystemAccess
import org.eclipse.xtext.generator.IGenerator
import com.google.inject.Inject
import eu.jgen.notes.dmw.lite.utility.LangUtil
import eu.jgen.notes.dmw.lite.lang.YClass
import eu.jgen.notes.dmw.lite.lang.YProperty
import java.util.List
import eu.jgen.notes.dmw.lite.utility.LangJavaGeneratorHelper
import eu.jgen.notes.dmw.lite.lang.YFunction
import eu.jgen.notes.dmw.lite.lang.YAnnotJava

class LangJavaWidgetGenerator implements IGenerator {

	@Inject extension LangUtil

	@Inject extension LangJavaGeneratorHelper

	private List<String> imports = newArrayList()

	override doGenerate(Resource input, IFileSystemAccess fsa) {
		input.allContents.filter[element|element instanceof YAnnotJava].forEach [ element |
			val annotSwift = element as YAnnotJava
			if (annotSwift.database.name == "MySQL") {
				input.allContents.filter[element2|element instanceof YWidget].forEach [ element2 |
					val widget = element2 as YWidget
					generateWidget(fsa, widget)
				]
			}
		]
	}

	protected def void generateWidget(IFileSystemAccess fsa, YWidget widget) {
		imports.clear
		widget.classes.forEach [ clazz |
			if (clazz.superclass !== null && clazz.superclass.name == "Widget") {
				imports.add("eu.jgen.notes.dmw.lite.runtimes.XWidget")
				val body = '''
					«clazz.documentation»
					@SuppressWarnings("unused")
					public class «clazz.name» extends XWidget {
					   «generateInnerClasses(clazz)»
					   
					   «generateProperties(clazz)»
					   
					   «generateFunctions(clazz)»
					}
				'''
				fsa.generateFile(
					widget.name.getFileSystemPath + "/" + clazz.name + ".java",
					LangOutputProvider.DEFAULT,
					'''		
						package «widget.name»;
						
						«FOR imp : imports»
							import «imp»;
						«ENDFOR»
						
						«body»
					'''
				)
			} else {
				val body = '''
					«clazz.documentation»
					@SuppressWarnings("unused")
					public class «clazz.name» {
					
					}
				'''
				fsa.generateFile(
					widget.name.getFileSystemPath + "/" + clazz.name + ".java",
					LangOutputProvider.DEFAULT,
					'''		
						package «widget.name»;
						
						«FOR imp : imports»
							import «imp»;
						«ENDFOR»
						
						«body»
					'''
				)
			}
		]
	}

	protected def String generateFunctions(YClass clazz) {
		'''
			«FOR member : clazz.members»
				«IF member instanceof YFunction»
					«generateFunctionForWidget(member as YFunction)»
				«ENDIF»	
			«ENDFOR»
		'''
	}

	protected def String generateFunctionForWidget(YFunction function) {
		'''
			«function.documentation»  
			public «IF function.type !== null»«function.type.name»«ENDIF»«IF function.type === null»void«ENDIF» «function.name»() {
			
			}
		'''
	}

	protected def String generateProperties(YClass clazz) {
		'''
			«FOR member : clazz.members»
				«IF member instanceof YProperty»
					«generatePropertyForWidget(member as YProperty)»
				«ENDIF»	
			«ENDFOR»
		'''
	}

	protected def String generatePropertyForWidget(YProperty property) {
		registerImport(findPackageName(property) + "." + property.type.name)
		'''
		«property.documentation»  
		public «property.type.name» «property.name»;'''
	}

	protected def String generateInnerClasses(YClass clazz) {
		'''
			«FOR innerclazz : clazz.inners»«generateClass(innerclazz)»«ENDFOR»
		'''
	}

	protected def String generateClass(YClass innerclazz) {
		if (innerclazz.superclass !== null && innerclazz.superclass.name == "Structure") {
			imports.add("eu.jgen.notes.dmw.lite.runtimes.XStructure")
			'''
				«innerclazz.documentation»  
				public class «innerclazz.name» extends XStructure {
					«FOR member : innerclazz.members»
						«IF member instanceof YProperty»
							«generatePropertyForStructure(member as YProperty)»
						«ENDIF»
					«ENDFOR»
				}
			'''
		}
	}

	protected def String generatePropertyForStructure(YProperty property) {
		imports.add("eu.jgen.notes.dmw.lite.runtimes." + "X" + property.type.name)
		'''
		«property.documentation»  
		public X«property.type.name» «property.name»;'''
	}

	protected def registerImport(String name) {
		if (imports.contains(name)) {
			return ""
		}
		imports.add(name)
		return ""
	}

}
