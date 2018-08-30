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
import eu.jgen.notes.dmw.lite.lang.YStatement
import eu.jgen.notes.dmw.lite.lang.YIfStatement
import eu.jgen.notes.dmw.lite.lang.YAndExpression
import eu.jgen.notes.dmw.lite.lang.YComparisonExpression
import eu.jgen.notes.dmw.lite.lang.YEqualityExpression
import eu.jgen.notes.dmw.lite.lang.YOrExpression
import eu.jgen.notes.dmw.lite.lang.YBoolConstant
import eu.jgen.notes.dmw.lite.lang.YNot
import eu.jgen.notes.dmw.lite.lang.YForInStatement
import eu.jgen.notes.dmw.lite.lang.YWhileStatement
import eu.jgen.notes.dmw.lite.lang.YRepeatWhileStatement
import eu.jgen.notes.dmw.lite.utility.LocalNameGenerator
import eu.jgen.notes.dmw.lite.lang.YSwitchStatement
import eu.jgen.notes.dmw.lite.lang.YStringConstant

class LangJavaWidgetGenerator implements IGenerator {

	@Inject extension LangUtil

	@Inject extension LangJavaGeneratorHelper

	@Inject extension LocalNameGenerator
	
	@Inject extension LangTypeComputer

	private List<String> imports = newArrayList()

	override doGenerate(Resource input, IFileSystemAccess fsa) {
		if (input.allContents.findFirst[it instanceof YAnnotJava] !== null) {
			val list = input.allContents.filter[it instanceof YWidget]
			list.forEach [
				generateWidget(fsa, it as YWidget)
			]
		}
	}

	protected def void generateWidget(IFileSystemAccess fsa, YWidget widget) {
		reset
		widget.classes.forEach [ clazz |
			if (clazz.superclass !== null && clazz.superclass.name == "Widget") {
				imports.clear
				imports.add("eu.jgen.notes.dmw.lite.runtimes.XWidget")
				val body = '''
					«clazz.documentation»
					@SuppressWarnings("unused")
					public class «clazz.name» extends XWidget {
					   «generateInnerClasses(clazz, clazz.name)»
					   «generateProperties(clazz)»					   
					   «generateArrays(clazz, clazz.name)»					   
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
				imports.clear
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

	protected def String generateArrays(YClass clazz, String widgetName) {
		'''
			«FOR property : clazz.listArrayProperties»
				«registerImport("eu.jgen.notes.dmw.lite.runtimes.XArray")»
				«registerImport("java.util.SortedMap")»
				«registerImport("java.util.concurrent.ConcurrentSkipListMap")»
				class «property.name.toFirstUpper» extends XArray {
					
					public «property.name.toFirstUpper»(int max) {
						super(max);
					}
					
					«FOR ref : property.tuples.includes» 
						
						SortedMap<Integer, «ref.type.name.toFirstUpper»> map«ref.type.name.toFirstUpper» = new ConcurrentSkipListMap<Integer, «ref.type.name.toFirstUpper»>();
						
						public «ref.type.name.toFirstUpper» get«ref.type.name.toFirstUpper»() {
							if(super.getSubscript() == 0) {
								return null;
							}
							if (map«ref.type.name.toFirstUpper».containsKey(super.getSubscript())) {
								return map«ref.type.name.toFirstUpper».get(super.getSubscript());
							} else {
								«ref.type.name.toFirstUpper» «ref.name» = «widgetName».instance«ref.type.name.toFirstUpper»();
								map«ref.type.name.toFirstUpper».put(super.getSubscript(), «ref.name»);
								return «ref.name»;
							}
						}
						
					«ENDFOR»
				}
				
				«property.name.toFirstUpper» «property.name» = new «property.name.toFirstUpper»(20);
			«ENDFOR»
			
		'''
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
			«function.access» «IF function.type !== null»«function.type.name.translateTypeName»«ENDIF» «IF function.type === null»void «ENDIF»«function.name»«generateFunctionParameters(function)»
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
			'''   «generateBlock(block)» '''
		}
	}

	protected def String generateBlock(YBlock block) {
		val body = '''
			«FOR statement : block.statements»
				«generateStatement(statement)»
			«ENDFOR»
		'''
		return body
	}

	protected def String generateStatement(YStatement statement) {
		switch (statement) {
			case statement instanceof YRepeatWhileStatement: {
				val repeatWhileStatement = statement as YRepeatWhileStatement
				return '''
					«repeatWhileStatement.documentation»  
					do {
						   «generateBlock(repeatWhileStatement.body)»
					} while («generateExpression(repeatWhileStatement.expression)»);		
				'''
			}
			case statement instanceof YSwitchStatement: {
				val switchStatement = statement as YSwitchStatement
				return '''
					«switchStatement.documentation»  
					«doSwitchStatement(switchStatement)»		
				'''
			}
			case statement instanceof YWhileStatement: {
				val whileStatement = statement as YWhileStatement
				return '''
					«whileStatement.documentation»  
					while («generateExpression(whileStatement.expression)») {
						   «generateBlock(whileStatement.body)»
					}			
				'''
			}
			case statement instanceof YReturn: {
				val returnStatement = statement as YReturn
				if (returnStatement.expression === null) {
					return '''
						«returnStatement.documentation»
						return;
					'''
				} else {
					return '''
						«returnStatement.documentation»
						return «generateExpression(returnStatement.expression)»;
					'''
				}
			}
			case statement instanceof YVariableDeclaration: {
				val variableDeclaration = statement as YVariableDeclaration
				return '''
					«variableDeclaration.documentation»
					«generateVariableDeclaration(variableDeclaration)»
				'''
			}
			case statement instanceof YAssignment: {
				val assignment = statement as YAssignment
				return '''
					«assignment.documentation»
					«generateAssigment(assignment)»
				'''
			}
			case statement instanceof YIfStatement: {
				val ifStatement = statement as YIfStatement
				'''
					«ifStatement.documentation»  
					if («generateExpression(ifStatement.expression)») {
						«generateBlock(ifStatement.thenBlock)» 
					} «IF ifStatement.elseBlock !== null» else {
											«generateBlock(ifStatement.elseBlock)»
					}«ENDIF»
					
				'''
			}
			default: {
				return "//TODO - not implemented yet"
			}
		}
	}

	protected def String generateAssigment(YAssignment assignment) {
		if (assignment.left instanceof YMemberSelection) {
			return generatMemberSelection(assignment.left as YMemberSelection) + " = " +
				generateExpression(assignment.right) + ";"
		} else {
			if (assignment.left instanceof YSymbolRef) {
				val symbolRef = assignment.left as YSymbolRef
				return symbolRef.symbol.name + " = " + generateExpression(assignment.right) + ";"
			}
			return "?"
		}
	}

	protected def String generateProperties(YClass clazz) {
		'''
			«FOR member : clazz.members»
				«IF member instanceof YProperty»
					«generateProperty(member as YProperty)»
				«ENDIF»	
			«ENDFOR»
		'''
	}

	protected def String generateProperty(YProperty property) {
		if (property.type.name == "Array") {
			return ""
		}
		'''
		«property.documentation»  
		«IF property.access!== null»«property.access» «ENDIF»«property.type.name.translateTypeName» «property.name»;'''
	}

	protected def String generateInnerClasses(YClass clazz, String widgetName) {
		'''
			«FOR innerclazz : clazz.inners»«generateClass(innerclazz, widgetName)»«ENDFOR»
		'''
	}

	protected def String generateClass(YClass innerclazz, String widgetName) {
		if (innerclazz.superclass !== null && innerclazz.superclass.name == "Structure") {
			registerImport("eu.jgen.notes.dmw.lite.runtimes.XStructure")
			'''
				«innerclazz.documentation»  
				public class «innerclazz.name» extends XStructure {
					«FOR member : innerclazz.members»
						«IF member instanceof YProperty»
							«generatePropertyForStructure(member as YProperty)»
						«ENDIF»
					«ENDFOR»
				}
				
				public static «innerclazz.name» instance«innerclazz.name»() {
				   «innerclazz.name» «innerclazz.name.toFirstLower» = new «widgetName»().new «innerclazz.name»();
				   «FOR member : innerclazz.members»
				   	«IF member instanceof YProperty»
				   		«initializeProperty(member as YProperty, innerclazz.name.toFirstLower)»
				   	«ENDIF»
				   «ENDFOR»
				   return «innerclazz.name.toFirstLower»;
				}
			'''
		} else {
			'''
				«innerclazz.documentation» 
					public class «innerclazz.name» «IF innerclazz.superclass !== null»extends «innerclazz.superclass.name»«ENDIF» {
						«FOR member : innerclazz.members»
							«IF member instanceof YProperty»
								«generatePropertyForStructure(member as YProperty)»
							«ENDIF»
						«ENDFOR»
					}
					
					public static «innerclazz.name» instance«innerclazz.name»() {
					   «innerclazz.name» «innerclazz.name.toFirstLower» = new «widgetName»().new «innerclazz.name»();
					   «FOR member : innerclazz.members»
					   	«IF member instanceof YProperty»
					   		«initializeProperty(member as YProperty, innerclazz.name.toFirstLower)»
					   	«ENDIF»
					   «ENDFOR»
					   return «innerclazz.name.toFirstLower»;
					}
			'''
		}
	}

	protected def String initializeProperty(YProperty property, String structureName) {
		'''
		«structureName».«property.name» = «property.getPropertyDefaultValue»;'''
	}

	protected def String generatePropertyForStructure(YProperty property) {
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
		return variableDeclaration.type.name.translateTypeName + " " + variableDeclaration.name + " = " +
			generateExpression(variableDeclaration.expression) + ";"
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
				return generateExpression(mulOrDiv.left) + " " + mulOrDiv.op + " " + generateExpression(mulOrDiv.right)
			}
			case expression instanceof YAndExpression: {
				val andExpression = expression as YAndExpression
				return generateExpression(andExpression.left) + " " + " && " + " " +
					generateExpression(andExpression.right)
			}
			case expression instanceof YOrExpression: {
				val orExpression = expression as YOrExpression
				return generateExpression(orExpression.left) + " " + " || " + " " +
					generateExpression(orExpression.right)
			}
			case expression instanceof YComparisonExpression: {
				val comparisonExpression = expression as YComparisonExpression
				return generateExpression(comparisonExpression.left) + " " + comparisonExpression.op + " " +
					generateExpression(comparisonExpression.right)
			}
			case expression instanceof YEqualityExpression: {
				val equalityExpression = expression as YEqualityExpression
				return generateExpression(equalityExpression.left) + " " + equalityExpression.op + " " +
					generateExpression(equalityExpression.right)
			}
			case expression instanceof YMemberSelection: {
				val memberSelection = expression as YMemberSelection
				return generatMemberSelection(memberSelection)
			}
			case expression instanceof YSelf: {
				return "this"
			}
			case expression instanceof YNot: {
				val not = expression as YNot
				return "!" + generateExpression(not.expression)

			}
			case expression instanceof YBoolConstant: {
				val boolConstant = expression as YBoolConstant
				return boolConstant.value
			}
			case expression instanceof YParenties: {
				return "(" + generateExpression((expression as YParenties).a) + ")"
			}
			case expression instanceof YSymbolRef: {
				val symbolRef = expression as YSymbolRef
				return symbolRef.symbol.name
			}
			case expression instanceof YIntConstant: {
				val intConstant = expression as YIntConstant
				intConstant.value.toString
			}
			case expression instanceof YStringConstant: {
				val stringConstant = expression as YStringConstant
				"\"" + stringConstant.value.toString + "\""
			}
			default: {
			}
		}

	}

	protected def String generatMemberSelection(YMemberSelection memberSelection) {
		if (memberSelection.functioninvocation) {
			val terminalExpression = generateTermination(memberSelection.receiver)
			return terminalExpression + "." + (memberSelection.member as YFunction).name +
				generateFunctionArguments(memberSelection)
		} else {
			if (memberSelection.receiver instanceof YMemberSelection) {
				val terminalExpression = generateTermination((memberSelection.receiver as YMemberSelection).receiver)
				val text = terminalExpression + "." + (memberSelection.receiver as YMemberSelection).member.name
				return text + "." + memberSelection.member.name
			} else {
				val terminalExpression = generateTermination(memberSelection.receiver)
				val text = terminalExpression + "." + memberSelection.member.name
				return text
			}

		}
	}

	protected def String generateFunctionArguments(YMemberSelection memberSelection) {
		return '''(«FOR arg : memberSelection.args SEPARATOR ", "»«generateExpression(arg)»«ENDFOR»)'''
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
			case expression instanceof YStringConstant: {
				val stringConstant = expression as YStringConstant
				"\"" + stringConstant.value.toString + "\""
			}
			default: {
			}
		}
	}

	def String doSwitchStatement(YSwitchStatement switchStatement) {
		imports.add("com.google.common.base.Objects")
			var key = "key".generateLocalName
			var _matched = "_matched".generateLocalName
			val buffer = new StringBuffer()
			buffer.append(
		 	'''
				final «switchStatement.switchExpression.typeFor.name.translateTypeName» «key» = «generateExpression(switchStatement.switchExpression)»;
				boolean «_matched» = false;
			''')
			if (switchStatement.cases.empty) {
				return buffer.toString
			}
			var firstItem = true
			for (caseFragment : switchStatement.cases) {
				if (firstItem) {
					buffer.append(
		 		'''
				if (Objects.equal(«key», «generateExpression(caseFragment.caseExpression)»)) {
				   «_matched»=true;
				   «generateBlock(switchStatement.^default)»
				}
				''')
				firstItem = false
				} else {
				buffer.append(
					'''
					if (!«_matched») {
					   if (Objects.equal(«key», «generateExpression(caseFragment.caseExpression)»)) {
					      «_matched»=true;
					      «generateBlock(caseFragment.then)»   
					   }
					}
					''')	
				}
			}
			buffer.append(
				'''
				«IF switchStatement.^default !== null»
				if (!«_matched») {
				   «generateBlock(switchStatement.^default)»
				}
				«ENDIF»
				'''
			)
			return buffer.toString
	}
}
