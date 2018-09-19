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
import eu.jgen.notes.dmw.lite.lang.YCreateStatement
import eu.jgen.notes.dmw.lite.lang.YMember
import eu.jgen.notes.dmw.lite.lang.YReadStatement
import eu.jgen.notes.dmw.lite.lang.YAnnotTable
import java.util.ArrayList
import eu.jgen.notes.dmw.lite.lang.YAnnotColumn

class LangJavaWidgetGenerator implements IGenerator {

	@Inject extension LangUtil

	@Inject extension LangJavaGeneratorHelper

	@Inject extension LocalNameGenerator

	@Inject extension LangTypeComputer

	private List<String> imports = newArrayList()

	private List<String> innerFunctions = newArrayList()

	override doGenerate(Resource input, IFileSystemAccess fsa) {
		if (input.allContents.findFirst[it instanceof YAnnotJava] !== null) {
			val list = input.allContents.filter[it instanceof YWidget]
			list.forEach [
				generateWidget(fsa, it as YWidget)
			]
		}
	}

	private
	 def void generateWidget(IFileSystemAccess fsa, YWidget widget) {

		widget.classes.forEach [ clazz |
			if (clazz.superclass !== null && clazz.superclass.name == "Widget") {
				reset
				imports.clear
				imports.add("eu.jgen.notes.dmw.lite.runtimes.XWidget")
				val body = '''
					«clazz.documentation»
					@SuppressWarnings("all")
					public class «clazz.name» extends XWidget {
					   «generateInnerClasses(clazz, clazz.name)»
					   «generateProperties(clazz)»					   
					   «generateArrays(clazz, clazz.name)»
					   «generateGetInstance(clazz, clazz.name)»	
					   «generateConstructor(clazz, clazz.name)»			   
					   «generateFunctions(clazz)»
					   «FOR innerFunction : innerFunctions»
					   	
					   	«innerFunction»
					   «ENDFOR»
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

	private def String generateGetInstance(YClass clazz, String name) {
		'''
			public static «clazz.name» getInstance(Connection connection) {
			   return new «clazz.name»(connection);
			}
		'''
	}

	private def String generateConstructor(YClass clazz, String name) {
		registerImport("java.sql.Connection")
		registerImport("java.sql.SQLException")
		registerImport("java.sql.PreparedStatement")
		registerImport("eu.jgen.notes.dmw.lite.runtimes.DMWRuntimeException")
		'''
			//
			public  «clazz.name»(Connection connection) {
			   this._connection = connection;
			   «FOR member : clazz.members»
			   	«IF member instanceof YProperty»
			   		«generateInitializeClassProperty(clazz, member as YProperty)»
			   	«ENDIF»	
			   «ENDFOR»
			}
		'''
	}

	private def String generateInitializeClassProperty(YClass clazz, YProperty property) {
		if (property.type.name == "Array") {
			return ""
		}
		var _initStructure = "_initStructure".generateLocalName
		generateInitStructureMethod(clazz, property, _initStructure)
		'''
		«_initStructure»();'''
	}

	private def void generateInitStructureMethod(YClass clazz, YProperty property, String methodName) {

		innerFunctions.add('''
			private void «methodName»() {
			   «FOR member : property.type.members»
			   	«IF member instanceof YProperty»
			   		«generateInitializeProperty(member as YProperty, property.type.name.toFirstLower)»
			   	«ENDIF»
			   «ENDFOR»
			}	
		''')

	}

	private def String generateCreateStatement(YCreateStatement createStatement, String _create) {
		createStatement.struct.structproperty.type.members
		val implementingTable = createStatement.struct.structclass.implementingTable
		return '''
			
			private boolean «_create»() {
			   StringBuffer buffer = new StringBuffer();
			   buffer.append("INSERT INTO \"«implementingTable.name»\" (");
			   buffer.append("«FOR member : createStatement.struct.structproperty.type.members SEPARATOR ","»\"«getImplementingColumnName(implementingTable,member)»\"«ENDFOR»");
			   buffer.append(") VALUES (");
			   buffer.append("«FOR member : createStatement.struct.structproperty.type.members SEPARATOR ","»?«ENDFOR»");
			   buffer.append(")");	
			   System.out.println(buffer.toString());		
			   try {
			   	  PreparedStatement _statement = _connection.prepareStatement(buffer.toString());
			   	  «generateSetMethods(createStatement)»
			   	  _statement.execute();
			   	  _statement.close();
			   } catch (SQLException e) {
			     if(e.getSQLState() == "23000") {
			        return false;
			     }
			     throw new DMWRuntimeException(e);
			   }
			   return true;
			}
		'''
	}

	private def String generateSetMethods(YCreateStatement createStatement) {
		val buffer = new StringBuffer()
		var index = 1;
		for (member : createStatement.struct.structproperty.type.members) {
			buffer.append("_statement." + generaterSetMethodName(member))
			buffer.append("(")
			buffer.append(index)
			buffer.append(", ")
			buffer.append(createStatement.struct.structproperty.name + "." + member.name)
			buffer.append(");\n")
			index++
		}
		return buffer.toString
	}

	private def String generaterSetMethodName(YMember member) {
		switch (member.type.name) {
			case "Int": {
				return "setInt"
			}
			case "Short": {
				return "setShort"
			}
			case "String": {
				return "setString"
			}
			case "Double": {
				return "setDouble"
			}
			default: {
				return "not yet done"
			}
		}
	}

	private def String generateArrays(YClass clazz, String widgetName) {
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
				
				«property.name.toFirstUpper» «property.name» = new «property.name.toFirstUpper»(«property.findArraySize»);
			«ENDFOR»
			
		'''
	}

	private def String generateFunctions(YClass clazz) {
		'''
			«FOR member : clazz.members»
				«IF member instanceof YFunction»
					«generateFunctionForWidget(member as YFunction)»
				«ENDIF»
			«ENDFOR»
		'''
	}

	private def String generateFunctionForWidget(YFunction function) {
		'''
			
			   «function.documentation»  
			«function.access» «IF function.type !== null»«function.type.name.translateTypeName»«ENDIF» «IF function.type === null»void «ENDIF»«function.name»«generateFunctionParameters(function)»
			   «generateFunctionBody(function)»
			}
		'''
	}

	private def String generateFunctionParameters(YFunction function) {

		'''
			(«FOR param : function.params SEPARATOR ', '»«param.type.name.translateTypeName» «param.name»«ENDFOR») {
		'''

	}

	private def String generateFunctionBody(YFunction function) {
		if (function.body !== null) {
			val block = function.body
			'''«generateBlock(block)» '''
		}
	}

	private def String generateBlock(YBlock block) {
		val body = '''
			«FOR statement : block.statements»
				«generateStatement(statement)»
			«ENDFOR»
		'''
		return body
	}

	private def String generateStatement(YStatement statement) {
		switch (statement) {
			case statement instanceof YForInStatement: {
				val forInStatement = statement as YForInStatement
				var _index = "_index".generateLocalName
				return '''
					«forInStatement.documentation»  
					int «_index» = 1;
					for («_index»=1; «_index» <= this.«forInStatement.collection.name».getLast(); «_index»++) {
					«forInStatement.collection.name».setSubscript(«_index»);
					«FOR include : forInStatement.collection.tuples.includes»
						this.«include.name» = «forInStatement.collection.name».get«include.type.name»();
					«ENDFOR» 	
					   «generateBlock(forInStatement.body)»
					}		
				'''
			}
			case statement instanceof YCreateStatement: {
				val createStatement = statement as YCreateStatement
				var _create = "_create".generateLocalName
				innerFunctions.add(generateCreateStatement(createStatement, _create))
				return '''
					«createStatement.documentation»
					«generateBlock(createStatement.setBlock)»
					if(_create()) {
						// execution of create statement completed successfully
						«generateBlock(createStatement.success)»
					} else {
						// duplicate entity type detected during when executing create statement
						«generateBlock(createStatement.alreadyExist)»
					}				  
				'''
			}
			case statement instanceof YReadStatement: {
				val readStatement = statement as YReadStatement
				var _read = "_read".generateLocalName
				innerFunctions.add(generateReadStatement(readStatement, _read))
				return '''
					«readStatement.documentation»
					if(_Read()) {
						// execution of read statement completed successfully
						«generateBlock(readStatement.success)»
					} else {
						// entity type not found when executing create statement
						«generateBlock(readStatement.notfound)»
					}				  
				'''
			}
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
					«generateSwitchStatement(switchStatement)»		
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
			case statement instanceof YMemberSelection: {
				val memberSelection = statement as YMemberSelection
				return '''
					«memberSelection.documentation»
					«generateSpecialFunctions(memberSelection)»
				'''
			}
			default: {
				return "//TODO - not implemented yet"
			}
		}
	}

	private def String generateReadStatement(YReadStatement readStatement, String _read) {
		readStatement.structs
		val implementingTable = readStatement.structs.get(0).structclass.implementingTable
		return '''			
			private boolean «_read»() {
			   StringBuffer buffer = new StringBuffer();
			   buffer.append("SELECT");
			   buffer.append("«FOR qualifiedName : readStatement.createQualifiedColumnNamesList SEPARATOR ","»«qualifiedName»«ENDFOR»");		   
			   buffer.append("FROM");
			   buffer.append("«FOR qualifiedName : generateFROMClause(readStatement) SEPARATOR ","»«qualifiedName»«ENDFOR»");		   
			   buffer.append("WHERE");
			   buffer.append("   «generateJDBCExpression(readStatement, readStatement.whereclause.expression)»");
			   System.out.println(buffer.toString());		
			   try {
			   	  PreparedStatement _statement = _connection.prepareStatement(buffer.toString());
			
			      _statement.execute();
			      _statement.close();
			   } catch (SQLException e) {
			     if(e.getSQLState() == "23000") {
			        return false;
			     }
			     throw new DMWRuntimeException(e);
			   }
			   return true;
			}
		'''
	}

	private def ArrayList<String> generateFROMClause(YStatement statement) {
		val list = newArrayList()
		var index = 1;
		if (statement instanceof YReadStatement) {
			val readStatement = statement as YReadStatement
			for (struct : readStatement.structs) {
				val implementingTable = readStatement.structs.get(0).structclass.implementingTable
				list.add('''T«index» \"«implementingTable.name»\"''')
				index++
			}
		}
		return list
	}

//	private def ArrayList<String> createQualifiedColumnNamesList(YStatement statement) {
//		val list = newArrayList()
//		var index = 1;
//		if (statement instanceof YReadStatement) {
//			val readStatement = statement as YReadStatement
//			for (struct : readStatement.structs) {
//				val implementingTable = readStatement.structs.get(0).structclass.implementingTable
//				for (member : struct.structproperty.type.members) {
//					list.add('''T«index».\"«getImplementingColumnName(implementingTable,member)»\"''')
//				}
//				index++;
//			}
//		}
//		return list
//	}

	private def String generateAssigment(YAssignment assignment) {
		if (assignment.left instanceof YMemberSelection) {
			return generateMemberSelection(assignment.left as YMemberSelection) + " = " +
				generateExpression(assignment.right) + ";"
		} else {
			if (assignment.left instanceof YSymbolRef) {
				val symbolRef = assignment.left as YSymbolRef
				return symbolRef.symbol.name + " = " + generateExpression(assignment.right) + ";"
			}
			return "?"
		}
	}

	private def String generateProperties(YClass clazz) {
		'''
			private Connection _connection;
			«FOR member : clazz.members»
				«IF member instanceof YProperty»
					«generateProperty(member as YProperty)»
				«ENDIF»	
			«ENDFOR»
		'''
	}

	private def String generateProperty(YProperty property) {
		if (property.type.name == "Array") {
			return ""
		}
		'''
		«property.documentation»  
		«IF property.access!== null»«property.access» «ENDIF»«property.type.name.translateTypeName» «property.name»;'''
	}

	private def String generateInnerClasses(YClass clazz, String widgetName) {
		'''
			«FOR innerclazz : clazz.inners»«generateClass(innerclazz, widgetName)»«ENDFOR»
		'''
	}

	private def String generateClass(YClass innerclazz, String widgetName) {
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
					   		«generateInitializeProperty(member as YProperty, innerclazz.name.toFirstLower)»
					   	«ENDIF»
					   «ENDFOR»
					   return «innerclazz.name.toFirstLower»;
					}
			'''
		}
	}

	private def String generateInitializeProperty(YProperty property, String structureName) {
		'''
		«structureName».«property.name» = «property.getPropertyDefaultValue»;'''
	}

	private def String generatePropertyForStructure(YProperty property) {
		'''
		«property.documentation»  
		public «property.type.name.translateTypeName» «property.name»;'''
	}

	private def registerImport(String name) {
		if (imports.contains(name)) {
			return ""
		}
		imports.add(name)
		return ""
	}

	private def String generateVariableDeclaration(YVariableDeclaration variableDeclaration) {
		return variableDeclaration.type.name.translateTypeName + " " + variableDeclaration.name + " = " +
			generateExpression(variableDeclaration.expression) + ";"
	}

	private def String generateExpression(YExpression expression) {
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
				return generateMemberSelection(memberSelection)
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

	private def String generateJDBCExpression(YStatement statement, YExpression expression) {
		switch (expression) {
			case expression instanceof YPlus: {
				val plus = expression as YPlus
				return generateJDBCExpression(statement, plus.left) + " + " +
					generateJDBCExpression(statement, plus.right);
			}
			case expression instanceof YMinus: {
				val minus = expression as YMinus
				return generateJDBCExpression(statement, minus.left) + " - " +
					generateJDBCExpression(statement, minus.right);
			}
			case expression instanceof YMulOrDiv: {
				val mulOrDiv = expression as YMulOrDiv
				return generateJDBCExpression(statement, mulOrDiv.left) + " " + mulOrDiv.op + " " +
					generateJDBCExpression(statement, mulOrDiv.right)
			}
			case expression instanceof YAndExpression: {
				val andExpression = expression as YAndExpression
				return generateJDBCExpression(statement, andExpression.left) + " " + " AND " + " " +
					generateJDBCExpression(statement, andExpression.right)
			}
			case expression instanceof YOrExpression: {
				val orExpression = expression as YOrExpression
				return generateJDBCExpression(statement, orExpression.left) + " " + " OR " + " " +
					generateJDBCExpression(statement, orExpression.right)
			}
			case expression instanceof YComparisonExpression: {
				val comparisonExpression = expression as YComparisonExpression
				return generateJDBCExpression(statement, comparisonExpression.left) + " " + comparisonExpression.op +
					" " + generateJDBCExpression(statement, comparisonExpression.right)
			}
			case expression instanceof YEqualityExpression: {
				val equalityExpression = expression as YEqualityExpression
				var operator = "?"
				if (equalityExpression.op == "==") {
					operator = "="
				} else if (equalityExpression.op == "!=") {
					operator = "<>"
				} 
				return generateJDBCExpression(statement, equalityExpression.left) + " " + operator +
					" " + generateJDBCExpression(statement, equalityExpression.right)
			}
			case expression instanceof YMemberSelection: {
				val memberSelection = expression as YMemberSelection
				return generateJDBCMemberSelection(statement, memberSelection)
			}
			case expression instanceof YSelf: {
				return "this"
			}
			case expression instanceof YNot: {
				val not = expression as YNot
				return "!" + generateJDBCExpression(statement, not.expression)

			}
			case expression instanceof YBoolConstant: {
				val boolConstant = expression as YBoolConstant
				return boolConstant.value
			}
			case expression instanceof YParenties: {
				return "(" + generateJDBCExpression(statement, (expression as YParenties).a) + ")"
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
				"not done yet"
			}
		}

	}

	private def String generateJDBCMemberSelection(YStatement statement, YMemberSelection memberSelection) {
		val propertyName = (memberSelection.receiver as YMemberSelection).member.name
		if (statement instanceof YReadStatement) {
			var index = 1;
			val readStatement = statement as YReadStatement
			for (structclass : readStatement.structs) {
				val name = structclass.structproperty.name
				val table = structclass.structclass.implementingTable
				for (annotAbstractColumn : table.columns) {
					if (propertyName == name &&
						(annotAbstractColumn.type as YAnnotColumn).attrref.name == memberSelection.member.name) {
						val qualName = "T" + index + "." + annotAbstractColumn.name
						println(qualName)
						return qualName
					}					
				}
				index++
			}
		}
		"?????"
	}


	private def String generateMemberSelection(YMemberSelection memberSelection) {
		if (memberSelection.functioninvocation) {
			val function = (memberSelection.member as YFunction)
			val terminalExpression = generateTermination((memberSelection.receiver as YMemberSelection).receiver)
			return terminalExpression + "." + (memberSelection.receiver as YMemberSelection).member.name + "." +
				function.name + generateFunctionArguments(memberSelection)
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

	private def String generateFunctionArguments(YMemberSelection memberSelection) {
		return '''(«FOR arg : memberSelection.args SEPARATOR ", "»«generateExpression(arg)»«ENDFOR»)'''
	}

	private def String generateTermination(YExpression expression) {
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

	private def String generateSwitchStatement(YSwitchStatement switchStatement) {
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
					   «generateBlock(caseFragment.then)»
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

	private def String generateSpecialFunctions(YMemberSelection memberSelection) {
		if (!memberSelection.functioninvocation) {
			return ""
		}
		if (memberSelection.member.name == "setSubscript") {
			return generateSetSubscript(memberSelection)
		}
		return ""
	}

	private def String generateSetSubscript(YMemberSelection memberSelection) {
		val property = ((memberSelection.receiver as YMemberSelection).member as YProperty)
		val arrayName = property.name
		val tuples = property.tuples
		val block = '''		
			«arrayName».setSubscript(«generateExpression(memberSelection.args.get(0))»);
			«FOR include : tuples.includes»
				this.«include.name» = «arrayName».get«include.type.name»();
			«ENDFOR»
		'''
		return block
	}

	private def String generateSetSubscript(YProperty property, int index) {
		val arrayName = property.name
		val tuples = property.tuples
		val block = '''		
			«arrayName».setSubscript(«index»);
			«FOR include : tuples.includes»
				this.«include.name» = «arrayName».get«include.type.name»();
			«ENDFOR»
		'''
		return block
	}
}
