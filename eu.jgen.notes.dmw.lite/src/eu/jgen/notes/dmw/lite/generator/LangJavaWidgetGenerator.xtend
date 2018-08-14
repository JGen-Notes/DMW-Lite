/**
 * [The "BSD license"]
 * Copyright (c) 2016, JGen Notes
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, 
 * are permitted provided that the following conditions are met:
 * 
 * 1. Redistributions of source code must retain the above copyright notice, this list of conditions 
 *    and the following disclaimer.
 * 
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions 
 *    and the following disclaimer in the documentation and/or other materials provided with the distribution.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, 
 * INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE 
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, 
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS 
 * OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, 
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE 
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * 
 */
package eu.jgen.notes.dmw.lite.generator

import com.google.inject.Inject
import eu.jgen.notes.dmw.lite.lang.YAnnotJava
import eu.jgen.notes.dmw.lite.lang.YBlock
import eu.jgen.notes.dmw.lite.lang.YClass
import eu.jgen.notes.dmw.lite.lang.YExpression
import eu.jgen.notes.dmw.lite.lang.YFunction
import eu.jgen.notes.dmw.lite.lang.YIntConstant
import eu.jgen.notes.dmw.lite.lang.YMemberSelection
import eu.jgen.notes.dmw.lite.lang.YMinus
import eu.jgen.notes.dmw.lite.lang.YMulOrDiv
import eu.jgen.notes.dmw.lite.lang.YPlus
import eu.jgen.notes.dmw.lite.lang.YProperty
import eu.jgen.notes.dmw.lite.lang.YReturn
import eu.jgen.notes.dmw.lite.lang.YSelf
import eu.jgen.notes.dmw.lite.lang.YSymbolRef
import eu.jgen.notes.dmw.lite.lang.YVariableDeclaration
import eu.jgen.notes.dmw.lite.lang.YWidget
import eu.jgen.notes.dmw.lite.typing.LangTypeComputer
import eu.jgen.notes.dmw.lite.utility.LangJavaGeneratorHelper
import eu.jgen.notes.dmw.lite.utility.LangUtil
import java.util.List
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.xtext.generator.IFileSystemAccess
import org.eclipse.xtext.generator.IGenerator
import eu.jgen.notes.dmw.lite.lang.YParenties
import eu.jgen.notes.dmw.lite.lang.YAssignment

class LangJavaWidgetGenerator implements IGenerator {

	@Inject extension LangUtil

	@Inject extension LangJavaGeneratorHelper
	
	@Inject extension LangTypeComputer

	private List<String> imports = newArrayList()

	override doGenerate(Resource input, IFileSystemAccess fsa) {
		input.allContents.filter[element|element instanceof YAnnotJava].forEach [ element |
			val annotJava = element as YAnnotJava
			if (annotJava.database.name == "MySQL") {
				val a = input.contents.findFirst[e|e instanceof YWidget]
				generateWidget(fsa, a as YWidget)
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
			public «IF function.type !== null»«function.type.name.translateTypeName»«ENDIF» «IF function.type === null»void «ENDIF»«function.name»«generateFunctionParameters(function)»
			   «generateFunctionBody(function)»
			}
		'''
	}
	
	protected def String generateFunctionParameters(YFunction function) {
		
		'''
		(«FOR param : function.params SEPARATOR ', '»«param.type.name.translateTypeName» «param.name»«ENDFOR») {
		'''
		
	}

	protected def String generateFunctionBody(YFunction function) {
		if (function.body !== null) {
			val block = function.body
			generateBlock(block)
		}
	}

	protected def String generateBlock(YBlock block) {
		for (statement : block.statements) {
			switch (statement) {
				case statement instanceof YReturn: {
					val returnStatement = statement as YReturn
					if (returnStatement.expression === null) {
						return "return;"
					} else {				
						return "return new " +  returnStatement.whatFuntionType.name.translateTypeName + "("  + generateExpression(returnStatement.expression) + ");"
					}

				}
				case statement instanceof YVariableDeclaration: {
					val variableDeclaration = statement as YVariableDeclaration
					return generateVariableDeclaration(variableDeclaration)
				}
				case statement instanceof YAssignment: {
					val assignment = statement as YAssignment
					return generateAssigment(assignment)
				}
				default: {
					return ""
				}
			}
		}
		return ""
	}
	
	protected def String generateAssigment(YAssignment assignment) {
		 return  generatMemberSelection(assignment.left as YMemberSelection) + " = " + generateExpression(assignment.right)
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
		imports.add("eu.jgen.notes.dmw.lite.runtimes." + property.type.name.translateTypeName)
		'''
		«property.documentation»  
		public «property.type.name.translateTypeName» «property.name»;'''
	}

	protected def registerImport(String name) {
		if (imports.contains(name)) {
			return ""
		}
		imports.add(name)
		return ""
	}

	protected def String generateVariableDeclaration(YVariableDeclaration variableDeclaration) {
		return variableDeclaration.type.name.translateTypeName + " " + variableDeclaration.name + " = new " +
			variableDeclaration.type.name.translateTypeName + "(" + generateExpression(variableDeclaration.expression) + ");"
	}

	protected def String generateExpression(YExpression expression) {
		switch (expression) {
			case expression instanceof YPlus: {
				val plus = expression as YPlus
				return generateExpression(plus.left) + " + " + generateExpression(plus.right);
			}
			case expression instanceof YMinus: {
				val minus = expression as YMinus
				return generateExpression(minus.left) + " - " + generateExpression(minus.right);
			}
			case expression instanceof YMulOrDiv: {
				val mulOrDiv = expression as YMulOrDiv
				
				return generateExpression(mulOrDiv.left) + " " + mulOrDiv.op + " " +  generateExpression(mulOrDiv.right)
			}
			case expression instanceof YMemberSelection: {
				val memberSelection = expression as YMemberSelection
				return generatMemberSelection(memberSelection)
			}
			case expression instanceof YSelf: {
				return "this"
			}
			case expression instanceof YParenties: {
				return "(" + generateExpression((expression as YParenties).a) + ")"
			}
			
			case expression instanceof YSymbolRef: {
				val symbolRef = expression as YSymbolRef
				
				return symbolRef.symbol.name + ".value"
			}			
			case expression instanceof YIntConstant: {
				val intConstant = expression as YIntConstant
				intConstant.value.toString
			}
			default: {
			}
		}

	}

	protected def String generatMemberSelection(YMemberSelection memberSelection) {
		if (memberSelection.functioninvocation) {
			val terminalExpression = generateTermination(memberSelection.receiver)
			return terminalExpression +  "." + (memberSelection.member as YFunction).name + generateFunctionArguments(memberSelection) + ".value"
		} else {
			val terminalExpression = generateTermination((memberSelection.receiver as YMemberSelection).receiver)
			val text = terminalExpression + "." + (memberSelection.receiver as YMemberSelection).member.name
			return text + "." + memberSelection.member.name + ".value"
		}
	}
	
	protected def String generateFunctionArguments(YMemberSelection memberSelection) {
		 return '''(«FOR arg : memberSelection.args SEPARATOR ", "»new XInt(«generateExpression(arg)»)«ENDFOR»)'''
	}

	protected def String generateTermination(YExpression expression) {
		switch (expression) {
			case expression instanceof YSelf: {
				return "this"
			}
			case expression instanceof YIntConstant: {
				val intConstant = expression as YIntConstant
				intConstant.value.toString
			}
			default: {
			}
		}
	}
}
