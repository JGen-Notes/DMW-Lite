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
import eu.jgen.notes.dmw.lite.lang.LangFactory
import eu.jgen.notes.dmw.lite.lang.YAndExpression
import eu.jgen.notes.dmw.lite.lang.YAnnotAbstractColumn
import eu.jgen.notes.dmw.lite.lang.YAnnotColumn
import eu.jgen.notes.dmw.lite.lang.YAnnotColumnLike
import eu.jgen.notes.dmw.lite.lang.YAnnotJava
import eu.jgen.notes.dmw.lite.lang.YAssignment
import eu.jgen.notes.dmw.lite.lang.YBlock
import eu.jgen.notes.dmw.lite.lang.YBoolConstant
import eu.jgen.notes.dmw.lite.lang.YClass
import eu.jgen.notes.dmw.lite.lang.YComparisonExpression
import eu.jgen.notes.dmw.lite.lang.YCreateStatement
import eu.jgen.notes.dmw.lite.lang.YDeleteStatement
import eu.jgen.notes.dmw.lite.lang.YEqualityExpression
import eu.jgen.notes.dmw.lite.lang.YExpression
import eu.jgen.notes.dmw.lite.lang.YForInStatement
import eu.jgen.notes.dmw.lite.lang.YFunction
import eu.jgen.notes.dmw.lite.lang.YIfStatement
import eu.jgen.notes.dmw.lite.lang.YIntConstant
import eu.jgen.notes.dmw.lite.lang.YJoinDef
import eu.jgen.notes.dmw.lite.lang.YMemberSelection
import eu.jgen.notes.dmw.lite.lang.YMinus
import eu.jgen.notes.dmw.lite.lang.YMulOrDiv
import eu.jgen.notes.dmw.lite.lang.YNot
import eu.jgen.notes.dmw.lite.lang.YOrExpression
import eu.jgen.notes.dmw.lite.lang.YParenties
import eu.jgen.notes.dmw.lite.lang.YPlus
import eu.jgen.notes.dmw.lite.lang.YProperty
import eu.jgen.notes.dmw.lite.lang.YReadEachStatement
import eu.jgen.notes.dmw.lite.lang.YReadStatement
import eu.jgen.notes.dmw.lite.lang.YRepeatWhileStatement
import eu.jgen.notes.dmw.lite.lang.YReturn
import eu.jgen.notes.dmw.lite.lang.YSelf
import eu.jgen.notes.dmw.lite.lang.YStatement
import eu.jgen.notes.dmw.lite.lang.YStringConstant
import eu.jgen.notes.dmw.lite.lang.YStructRefPair
import eu.jgen.notes.dmw.lite.lang.YSwitchStatement
import eu.jgen.notes.dmw.lite.lang.YSymbolRef
import eu.jgen.notes.dmw.lite.lang.YUpdateStatement
import eu.jgen.notes.dmw.lite.lang.YVariableDeclaration
import eu.jgen.notes.dmw.lite.lang.YWhileStatement
import eu.jgen.notes.dmw.lite.lang.YWidget
import eu.jgen.notes.dmw.lite.typing.LangTypeComputer
import eu.jgen.notes.dmw.lite.utility.LangGeneratorHelperForJava
import eu.jgen.notes.dmw.lite.utility.LangUtil
import java.util.ArrayList
import java.util.List
import org.eclipse.emf.common.util.EList
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.xtext.generator.AbstractGenerator
import org.eclipse.xtext.generator.IFileSystemAccess2
import org.eclipse.xtext.generator.IGeneratorContext
import org.eclipse.xtext.generator.trace.node.IGeneratorNode
import org.eclipse.xtext.generator.trace.node.Traced
import org.eclipse.xtext.generator.trace.node.TracedAccessors
import eu.jgen.notes.dmw.lite.lang.YNull
import eu.jgen.notes.dmw.lite.lang.YAssociateStatement

class LangWidgetGeneratorForJava extends AbstractGenerator {

	@TracedAccessors(LangFactory)
	static class LangTraceExtensions {
	}

	@Inject extension LangTraceExtensions

	@Inject extension LangUtil

	@Inject extension LangGeneratorHelperForJava

	@Inject extension LangTypeComputer

	List<String> imports = newArrayList()

	// List<String> innerFunctions = newArrayList()
	List<IGeneratorNode> innerFunctions2 = newArrayList()

	override doGenerate(Resource input, IFileSystemAccess2 fsa, IGeneratorContext context) {
		val model = input.contents.head as YWidget
		if (input.allContents.exists[it instanceof YAnnotJava]) {
			val list = input.allContents.filter[it instanceof YWidget]
			list.forEach [
				val widget = it as YWidget
				widget.classes.forEach [ clazz |
					if (clazz.superclass !== null && clazz.superclass.name == "Widget") {
						resetLocalNames
						// innerFunctions.clear
						innerFunctions2.clear
						// println("--> " + widget.name.getFileSystemPath + "/" + clazz.name + ".java")
						fsa.generateTracedFile(
							widget.name.getFileSystemPath + "/" + clazz.name + ".java",
							model,
							'''	
								/*
								 * Generated by Lang Widget Generator for Java, Version 0.4
								 */	
								 package «widget.name»;
								 
								 import eu.jgen.notes.dmw.lite.runtimes.XWidget;
								 import eu.jgen.notes.dmw.lite.runtimes.DMWContext;
								 import eu.jgen.notes.dmw.lite.runtimes.XStructure;
								 import java.sql.Connection;
								 import java.sql.SQLException;
								 import java.sql.PreparedStatement;
								 import java.sql.ResultSet;
								 import java.sql.Date;
								 import java.sql.Time;
								 import java.sql.Timestamp;	
								 import org.apache.derby.shared.common.error.DerbySQLIntegrityConstraintViolationException;
								 import eu.jgen.notes.dmw.lite.runtimes.DMWRuntimeException;
								 import eu.jgen.notes.dmw.lite.runtimes.XArray;
								 import java.util.SortedMap;
								 import java.util.concurrent.ConcurrentSkipListMap;
								 import com.google.common.base.Objects;
								 
								 «clazz.documentation»
								 @SuppressWarnings("unused")					
								 public class «clazz.name» extends XWidget {	
								 	
								 «generateConstructor(clazz)»
								 
								 «generateGetInstance(clazz)»
								 
								 «generateInnerClasses(clazz)»
								 
								 «generateProperties(clazz)»
								 
								 «generateArrays(clazz)»
								 
								 «generateFunctions(clazz)»
								 
								 «FOR function : innerFunctions2»
								 	«function»
								 «ENDFOR»					
								 
								 }
								 
							'''
						)
					}
				]
			]
		}
	}

	/*
	 * Generate get instance static method
	 */
	@Traced protected def generateGetInstance(YClass it) '''
		public static «_name» getInstance(DMWContext _context) {
		   return new «_name»(_context);
		}
	'''

	/*
	 * Generate constructor for the widget class.
	 */
	@Traced protected def generateConstructor(YClass it) '''
		private «_name»(DMWContext _context) {
		  super(_context);
		  «FOR member : members»
		  	«IF member instanceof YProperty»
		  		«generateInitializeStructure(it, member as YProperty)»
		  	«ENDIF»	
		  «ENDFOR»
		}
	'''

	@Traced protected def generateInnerClasses(YClass clazz) {
		'''
			«FOR innerclazz : clazz.inners»«generateClass(innerclazz, clazz.name)»«ENDFOR»
		'''
	}

	@Traced protected def generateClass(YClass it, String widgetName) {
		if (superclass !== null && superclass.name == "Structure") {
			registerImport("eu.jgen.notes.dmw.lite.runtimes.XStructure")
			'''				
				«documentation»  
				public class «_name» extends XStructure {
				   «FOR member : members»
				   	«IF member instanceof YProperty»
				   		«generatePropertyForStructure(member as YProperty)»
				   	«ENDIF»
				   «ENDFOR»
				}
				
			'''
		} else {
			''''''
		}
	}

	@Traced protected def String generatePropertyForStructure(YProperty property) '''
	«property.documentation»  
	public «property.type.name.translateTypeName» «property.name»;'''

	@Traced protected def generateProperties(YClass it) '''
		«FOR member : members»
			«IF member instanceof YProperty»
				«generateProperty(member as YProperty)»
			«ENDIF»	
		«ENDFOR»
	'''

	@Traced protected def generateProperty(YProperty it) {
		if (it.type.name == "Array") {
			return ''''''
		}
		'''
		«documentation»  
		«IF access!== null»«access» «ENDIF»«type.name.translateTypeName» «_name»;'''
	}

	@Traced protected def generateFunctions(YClass it) '''
		«FOR member : members»
			«IF member instanceof YFunction»
				«generateFunctionForWidget(member as YFunction)»
			«ENDIF»
		«ENDFOR»
	'''

	@Traced protected def generateFunctionForWidget(YFunction it) '''
		
		«documentation»  
		«access» «IF type !== null»«type.name.translateTypeName»«ENDIF» «IF type === null»void «ENDIF»«name»«generateFunctionParameters(it)»
		   «generateBlock(it.body)»
		}
	'''

	@Traced protected def generateFunctionParameters(YFunction it) '''
		(«FOR param : params SEPARATOR ', '»«param.type.name.translateTypeName» «param.name»«ENDFOR») {
	'''

	@Traced protected def generateArrays(YClass it) '''
		«FOR property : it.findPropertiesOfTypeArray»
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
							«ref.type.name.toFirstUpper» «ref.name» = new «ref.type.name.toFirstUpper»();
							map«ref.type.name.toFirstUpper».put(super.getSubscript(), «ref._name»);
							return «ref.name»;
						}
					}
					
				«ENDFOR»
			}
			
			«property.name.toFirstUpper» «property._name» = new «property.name.toFirstUpper»(«property.arraySize»);
		«ENDFOR»
		
	'''

	@Traced protected def generateBlock(YBlock it) {
		'''
			«FOR statement : it.statements» 
				«selectStatementGeneration(statement)»
			«ENDFOR»
		'''
	}

	/*
	 * Select type of the statement and generate code.
	 */
	protected def selectStatementGeneration(YStatement statement) {
		switch (statement) { //
			case statement instanceof YCreateStatement: {
				return generateStatementCreate(statement as YCreateStatement);
			}
			case statement instanceof YDeleteStatement: {
				return generateStatementDelete(statement as YDeleteStatement);
			}
			case statement instanceof YUpdateStatement: {
				return generateStatementUpdate(statement as YUpdateStatement);
			}
			case statement instanceof YReadStatement: {
				return generateStatementRead(statement as YReadStatement);
			}
			case statement instanceof YAssociateStatement: {
				return generateStatementAssociate(statement as YAssociateStatement);
			}
			case statement instanceof YReadEachStatement: {
				return generateStatementReadEach(statement as YReadEachStatement);
			}
			case statement instanceof YForInStatement: {
				return generateStatementForIn(statement as YForInStatement)
			}
			case statement instanceof YIfStatement: {
				return generateStatementIf(statement as YIfStatement);
			}
			case statement instanceof YReturn: {
				return generateStatementReturn(statement as YReturn);
			}
			case statement instanceof YWhileStatement: {
				return generateStatementWhile(statement as YWhileStatement)
			}
			case statement instanceof YSwitchStatement: {
				return generateStatementSwitch(statement as YSwitchStatement);
			}
			case statement instanceof YRepeatWhileStatement: {
				return generateStatementRepeatWhile(statement as YRepeatWhileStatement)
			}
			case statement instanceof YVariableDeclaration: {
				return generateStatementVariableDeclaration(statement as YVariableDeclaration)
			}
			case statement instanceof YAssignment: {
				return generateStatementYAssignment(statement as YAssignment)
			}
			case statement instanceof YMemberSelection: {
				return generateStatementYAssignment(statement as YMemberSelection)
			}
			default: {
			}
		}
		return null
	}

	@Traced protected def generateStatementIf(YIfStatement it) '''
		«documentation»  
		if («generateExpression(expression)») {
			«generateBlock(thenBlock)» 
		} «IF elseBlock !== null» else {
										«generateBlock(elseBlock)»
		}«ENDIF»
		
	'''

	@Traced protected def generateStatementForIn(YForInStatement it) {
		val _index = "_index".generateLocalName
		return '''
			«documentation»  
			int «_index» = 1;
			for («_index»=1; «_index» <= this.«collection.name».getLast(); «_index»++) {
			«collection.name».setSubscript(«_index»);
			«FOR include : collection.tuples.includes»
				this.«include.name» = «collection.name».get«include.type.name»();
			«ENDFOR» 	
			   «generateBlock(body)»
			}		
		'''
	}

	@Traced protected def generateStatementReturn(YReturn it) {
		if (expression === null) {
			return '''
				«documentation»
				return;
			'''
		} else {
			return '''
				«documentation»
				return «generateExpression(expression)»;
			'''
		}
	}

	@Traced protected def generateStatementWhile(YWhileStatement it) '''
		«documentation»  
		while («generateExpression(expression)») {
		   «generateBlock(body)»
		}			
	'''

	@Traced protected def generateStatementRepeatWhile(YRepeatWhileStatement it) '''
		«documentation»  
		do {
		      «generateBlock(body)»
		} while («generateExpression(expression)»);		
	'''

	@Traced protected def generateStatementVariableDeclaration(YVariableDeclaration it) '''
		«documentation»
		«generateVariableDeclaration(it)»
	'''

	private def String generateVariableDeclaration(YVariableDeclaration variableDeclaration) {
		return variableDeclaration.type.name.translateTypeName + " " + variableDeclaration.name + " = " +
			generateExpression(variableDeclaration.expression) + ";"
	}

	@Traced protected def generateStatementYAssignment(YMemberSelection it) '''
		«documentation»
		«generateSpecialFunctions(it)»
	'''

	protected def generateStatementSwitch(YSwitchStatement it) {

		val buffer = trace

		var key = "key".generateLocalName
		var _matched = "_matched".generateLocalName
		buffer.append("final ")
		buffer.append(switchExpression.typeFor.name.translateTypeName)
		buffer.append(" ")
		buffer.append(key)
		buffer.append(" = ")
		buffer.append(generateExpression(switchExpression))
		buffer.append(";")
		buffer.appendNewLine
		buffer.append("boolean ")
		buffer.append(_matched)
		buffer.append(" = false;")
		buffer.appendNewLine
		var firstItem = true
		for (caseFragment : cases) {
			if (firstItem) {
				buffer.append("if (Objects.equal(")
				buffer.append(key)
				buffer.append(", ")
				buffer.append(generateExpression(caseFragment.caseExpression))
				buffer.append(")) {")
				buffer.appendNewLine
				buffer.append(_matched)
				buffer.append(" = true;")
				buffer.appendNewLine
				buffer.append(generateBlock(caseFragment.then))
				buffer.append("}")
				buffer.appendNewLine
				firstItem = false
			} else {
				buffer.append("if (!")
				buffer.append(_matched)
				buffer.append(") {")
				buffer.appendNewLine
				buffer.append("if (Objects.equal(")
				buffer.append(key)
				buffer.append(", ")
				buffer.append(generateExpression(caseFragment.caseExpression))
				buffer.append(")) {")
				buffer.appendNewLine
				buffer.append(_matched)
				buffer.append(" = true;")
				buffer.appendNewLine
				buffer.append(generateBlock(caseFragment.then))
				buffer.append("}")
				buffer.appendNewLine
				buffer.append("}")
				buffer.appendNewLine
			}
		}
		buffer.append("if (!")
		buffer.append(_matched)
		buffer.append(") {")
		buffer.appendNewLine
		buffer.append(generateBlock(^default))
		buffer.appendNewLine
		buffer.append("}")
		buffer.appendNewLine
		return buffer
	}

	@Traced protected def generateStatementCreate(YCreateStatement it) {
		val _create = "_create".generateLocalName
		innerFunctions2.add(generateStatementCreateFunction(it, _create))
		return '''
			«documentation»
			«generateBlock(setBlock)»
			«_create»();				  
		'''
	}

	@Traced protected def generateStatementCreateFunction(YCreateStatement it, String name) {
		val implementingTable = struct.structclass.implementingTable
		return '''
			private void «name»() {
			   // Begin of Create Statement
			   «documentation»
			   «generateBlock(setBlock)»
			   StringBuffer buffer = new StringBuffer();
			   buffer.append("INSERT INTO \"«implementingTable.name»\" (");
			   buffer.append("«FOR member : struct.structproperty.type.members SEPARATOR ","»\"«getImplementingColumnName(implementingTable,member)»\"«ENDFOR»");
			   buffer.append(") VALUES (");
			   buffer.append("«FOR member : struct.structproperty.type.members SEPARATOR ","»?«ENDFOR»");
			   buffer.append(")");	
			   System.out.println(buffer.toString());
			   try {
			      PreparedStatement _statement = getContext().getConnection().prepareStatement(buffer.toString());
			      «generateSetMethodsForCreateStatement(it)»
			      _statement.execute();
			      «generateBlock(success)»
			      _statement.close();
			      return;
			   } catch (DerbySQLIntegrityConstraintViolationException e) {
			      «generateBlock(alreadyExist)»
			   } catch (SQLException e) {
			      throw new DMWRuntimeException(e);
			   }
			   // End of Create Statement
			}
		'''
	}

	@Traced protected def generateStatementAssociate(YAssociateStatement it) {
		val _associate = "_associate".generateLocalName
		innerFunctions2.add(generateStatementAssociateFunction(it, _associate))
		return '''
			«documentation» 
			«_associate»();
		'''
	}

	@Traced protected def generateStatementAssociateFunction(YAssociateStatement statement, String name) {
		return '''
			private void «name»() {
			   // Begin of Associate Statement
			   
			   // End of Associate Statement
			}
		'''
	}

	@Traced protected def generateStatementDelete(YDeleteStatement it) {
		val _delete = "_delete".generateLocalName
		innerFunctions2.add(generateStatementDeleteFunction(it, _delete))
		return '''
			«documentation»	
			«_delete»(_rs);		  
		'''
	}

	@Traced protected def generateStatementDeleteFunction(YDeleteStatement it, String functionName) {
		return '''
			private void «functionName»(ResultSet _rs) {		
			   // begin of Delete Statement
			   «documentation»
			   try {
			      _rs.deleteRow();
			   } catch (SQLException e) {
			      throw new DMWRuntimeException(e);
			   // End of Delete Statement
			   }
			}
		'''
	}

	@Traced protected def generateStatementUpdate(YUpdateStatement it) {
		val _update = "_update".generateLocalName
		innerFunctions2.add(generateStatementUpdateFunction(it, _update))
		return '''
			«documentation»	
			«_update»(_rs);		  
		'''
	}

	@Traced protected def generateStatementUpdateFunction(YUpdateStatement it, String functionName) {
		return '''
			private void «functionName»(ResultSet _rs) {
			   // begin of Update Statement
			   «documentation»
			   «generateBlock(it.setBlock)»
			   try {
			      «generateSetMethodsForUpdateStatement(it)»
			      _rs.updateRow();
			   } catch (SQLException e) {
			      throw new DMWRuntimeException(e);
			   }
			   // end of Update Statement
			}
		'''
	}

	@Traced protected def generateStatementReadEach(YReadEachStatement it) {
		val _readEach = "_read".generateLocalName
		innerFunctions2.add(generateStatementReadEachFunction(it, _readEach))
		return '''
			«documentation»	
			«_readEach»();		  
		'''
	}

	@Traced protected def generateStatementReadEachFunction(YReadEachStatement it, String functionName) {
		return '''
			private void «functionName»() {
			   // Begin of Create Statement
			   «documentation»
			   StringBuffer buffer = new StringBuffer();
			   buffer.append("SELECT ");
			   buffer.append("«FOR qualifiedName : it.createQualifiedColumnNamesListForReadEach SEPARATOR ","»«qualifiedName»«ENDFOR»");		   
			   buffer.append(" FROM ");
			   buffer.append("«FOR qualifiedName : generateFROMClause(it) SEPARATOR ","»«qualifiedName»«ENDFOR»");		   
			   buffer.append(" WHERE ");
			   buffer.append("«generateJoinExpressionForReadEach(it)»");
			   buffer.append("«generateJDBCExpression(it, it.whereclause.expression)»");
			   buffer.append(" FOR UPDATE ");
			   System.out.println(buffer.toString());		
			   try {
			      PreparedStatement _statement = getContext().getConnection().prepareStatement(buffer.toString(), ResultSet.CONCUR_UPDATABLE,
			      ResultSet.CLOSE_CURSORS_AT_COMMIT);
			      _statement.setCursorName("viewF");
			      «generateSetMethodsForReadEach(it)»
			      _statement.execute();
			      ResultSet _rs = _statement.getResultSet();
			      while (_rs.next()) {
			         «generateGetMethodsForReadEach(it)»
			      «generateBlock(success)» 
			      }
			      _rs.close();
			      _statement.close();
			      	  
			   } catch (SQLException e) {
			     if(e.getSQLState() == "23000") {
			           
			   }
			     throw new DMWRuntimeException(e);
			   }
			}
		'''
	}

	@Traced protected def generateStatementRead(YReadStatement readStatement) {
		val _read = "_read".generateLocalName
		innerFunctions2.add(generateStatementReadFunction(readStatement, _read))
		return '''
			«readStatement.documentation»	
			«_read»();		  
		'''
	}

	@Traced protected def generateStatementReadFunction(YReadStatement readStatement, String _read) {
		readStatement.structs
		return '''			
			private void «_read»() {
			   StringBuffer buffer = new StringBuffer();
			   buffer.append("SELECT ");
			   buffer.append("«FOR qualifiedName : readStatement.createQualifiedColumnNamesListForRead SEPARATOR ","»«qualifiedName»«ENDFOR»");		   
			   buffer.append(" FROM ");
			   buffer.append("«FOR qualifiedName : generateFROMClause(readStatement) SEPARATOR ","»«qualifiedName»«ENDFOR»");		   
			   buffer.append(" WHERE ");
			   buffer.append("«generateJoinExpressionForRead(readStatement)»");
			   buffer.append("«generateJDBCExpression(readStatement, readStatement.whereclause.expression)»");
			   buffer.append(" FOR UPDATE ");
			   System.out.println(buffer.toString());		
			   try {
			   	  PreparedStatement _statement = getContext().getConnection().prepareStatement(buffer.toString(), ResultSet.CONCUR_UPDATABLE,
			   	  					ResultSet.CLOSE_CURSORS_AT_COMMIT);
			   	  _statement.setCursorName("viewF");
			   	  «generateSetMethodsForRead(readStatement)»
			   	  _statement.execute();
			   	  ResultSet _rs = _statement.getResultSet();
			   	  if (_rs.next()) {
			   	  	 «generateGetMethodsForRead(readStatement)»
			   	  	  «generateBlock(readStatement.success)»
			   	  } else {
			   	  	 «generateBlock(readStatement.notfound)»
			   	  }
			   	  _rs.close();
			   	  _statement.close();			   	  
			   } catch (SQLException e) {
			     throw new DMWRuntimeException(e);
			   }
			}
		'''
	}

	/*
	 * Generate content of the constructor for the widget class. It is a sequence
	 * of methods initialising structures and setting default values for each property.
	 */
	@Traced protected def generateInitializeStructure(YClass clazz, YProperty property) {
		// TODO move to helper
		if (property.type.name == "Array" || property.type.name == "Int" || property.type.name == "Short" ||
			property.type.name == "Decimal" || property.type.name == "Long" || property.type.name == "Bool") {
			return ''''''
		}
		val _initStructure = "_initStructure".generateLocalName
		innerFunctions2.add(generateInitStructureFunction(property, _initStructure))
		'''
		«_initStructure»();'''
	}

	@Traced protected def generateInitStructureFunction(YProperty property, String methodName) {
		'''
			private void «methodName»() {
			   «property.name» = new «property.type.name»();
			   «FOR member : property.type.members»
			   	«IF member instanceof YProperty»
			   		«generateInitializeProperty(member as YProperty, property.name)»
			   	«ENDIF»
			   «ENDFOR»
			}	
		'''
	}

	private def String generateStatementYAssignment(YAssignment assignment) {
		return '''
			«assignment.documentation»
			«generateAssigment(assignment)»
		'''
	}

	private def String generateJoinExpressionForRead(YReadStatement statement) {
		if (statement.joinclause === null) {
			return ""
		}
		for (joinDef : statement.joinclause.joindefs) {
			return generateJoinExpressionFragment(statement, joinDef)
		}
		return "something wrong"
	}

	private def String generateJoinExpressionForReadEach(YReadEachStatement readEachStatement) {
		if (readEachStatement.joinclause === null) {
			return ""
		}
		for (joinDef : readEachStatement.joinclause.joindefs) {
			return generateJoinExpressionFragmentForReadEach(readEachStatement, joinDef)
		}
		return "something wrong"
	}

	private def String generateJoinExpressionFragment(YReadStatement readStatement, YJoinDef joinDef) {
		val fromTable = joinDef.fromView.type.entityRef.implementingTable
		val fromPrefix = findTablePrefix(readStatement.structs, joinDef.fromView)
		val toTable = joinDef.toView.type.entityRef.implementingTable
		val toPrefix = findTablePrefix(readStatement.structs, joinDef.toView)
		val parent = joinDef.relRef.parent
		if (parent) {
			var buffer = new StringBuffer()
			var String columnName = ""
			var String cadidateColumnName = null
			for (abstractElement : fromTable.primarykey.columns) {
				columnName = getAttributeNameForAbstractColumn(abstractElement)
				for (foreignKey : toTable.foreignkeys) {
					if (joinDef.relRef.inverse.name == foreignKey.relationship.name) {
						for (cadidateElement : foreignKey.columns) {
							cadidateColumnName = getAttributeNameForAbstractColumn(cadidateElement)
							if (columnName == cadidateColumnName) {
								buffer.append(
									fromPrefix + "." + abstractElement.name + " = " + toPrefix + "." +
										cadidateElement.name + " AND")
							}
						}
					}
				}
			}
			return buffer.toString()
		}
		return ""
	}

	private def String generateJoinExpressionFragmentForReadEach(YReadEachStatement readEachStatement,
		YJoinDef joinDef) {
		val fromTable = joinDef.fromView.type.entityRef.implementingTable
		val fromPrefix = findTablePrefix(readEachStatement.structs, joinDef.fromView)
		val toTable = joinDef.toView.type.entityRef.implementingTable
		val toPrefix = findTablePrefix(readEachStatement.structs, joinDef.toView)
		val parent = joinDef.relRef.parent
		if (parent) {
			var buffer = new StringBuffer()
			var String columnName = ""
			var String cadidateColumnName = null
			for (abstractElement : fromTable.primarykey.columns) {
				columnName = getAttributeNameForAbstractColumn(abstractElement)
				for (foreignKey : toTable.foreignkeys) {
					if (joinDef.relRef.inverse.name == foreignKey.relationship.name) {
						for (cadidateElement : foreignKey.columns) {
							cadidateColumnName = getAttributeNameForAbstractColumn(cadidateElement)
							if (columnName == cadidateColumnName) {
								buffer.append(
									fromPrefix + "." + abstractElement.name + " = " + toPrefix + "." +
										cadidateElement.name + " AND")
							}
						}
					}
				}
			}
			return buffer.toString()
		}
		return ""
	}

	private def String findTablePrefix(EList<YStructRefPair> list, YProperty property) {
		var index = 1;
		for (pair : list) {
			if (pair.structproperty == property) {
				return "T" + index
			}
			index++;
		}
		""
	}

	private def String getAttributeNameForAbstractColumn(YAnnotAbstractColumn annotAbstractColumn) {
		if (annotAbstractColumn.type instanceof YAnnotColumnLike) {
			return ((annotAbstractColumn.type as YAnnotColumnLike).columnref.type as YAnnotColumn).attrref.name
		} else {
			return (annotAbstractColumn.type as YAnnotColumn).attrref.name
		}
	}

	private def ArrayList<String> generateFROMClause(YStatement statement) {
		val list = newArrayList()
		var index = 1;
		if (statement instanceof YReadStatement) {
			val readStatement = statement as YReadStatement
			for (struct : readStatement.structs) {
				val implementingTable = readStatement.structs.get(0).structclass.implementingTable
				list.add('''\"«implementingTable.name»\" T«index»''')
				index++
			}
		}
		return list
	}

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

	private def String generateInitializeProperty(YProperty property, String structureName) {
		'''
		«structureName».«property.name» = «property.getPropertyDefaultValue»;'''
	}

	private def registerImport(String name) {
		if (imports.contains(name)) {
			return ""
		}
		imports.add(name)
		return ""
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
			case expression instanceof YNull: {
				return "null"
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
				var operator = "?"
				if (comparisonExpression.op == ">=") {
					operator = ">="
				} else if (comparisonExpression.op == "<=") {
					operator = "<="
				} else if (comparisonExpression.op == ">") {
					operator = ">"
				} else if (comparisonExpression.op == "<") {
					operator = "<"
				}
				return generateJDBCExpression(statement, comparisonExpression.left) + " " + operator + " " +
					generateJDBCExpression(statement, comparisonExpression.right)
			}
			case expression instanceof YEqualityExpression: {
				val equalityExpression = expression as YEqualityExpression
				var operator = "?"
				if (equalityExpression.op == "==") {
					operator = "="
				} else if (equalityExpression.op == "!=") {
					operator = "<>"
				}
				return generateJDBCExpression(statement, equalityExpression.left) + " " + operator + " " +
					generateJDBCExpression(statement, equalityExpression.right)
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
				"\'" + stringConstant.value.toString + "\'"
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
						// println(qualName)
						return qualName
					}
				}
				index++
			}

			// This is not reference to column. It is rather reference to to some other. Use question mark.			
			return "?"
		}
		"something went wrong"
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

	private def String generateSpecialFunctions(YMemberSelection memberSelection) {
		if (!memberSelection.functioninvocation) {
			return ""
		}
		if (memberSelection.member.name == "setSubscript") {
			return generateFunctionSetSubscript(memberSelection)
		}
		if (memberSelection.member.name == "moveStruct") {
			return generateFunctionMove(memberSelection)
		}
		if (memberSelection.member.name == "println") {
			return generateFunctionPrintln(memberSelection)
		}
		return ""
	}

	private def String generateFunctionPrintln(YMemberSelection memberSelection) {
		val memberSelectionInner = memberSelection.args.get(0) as YMemberSelection
		val block = '''	
			// start of println()	
			System.out.println(«memberSelectionInner.generateExpression»);
			// end of println()	
		'''
		return block
	}

	private def String generateFunctionSetSubscript(YMemberSelection memberSelection) {
		val property = ((memberSelection.receiver as YMemberSelection).member as YProperty)
		val arrayName = property.name
		val tuples = property.tuples
		val block = '''	
			
			    // start of setSubscript()	
				«arrayName».setSubscript(«generateExpression(memberSelection.args.get(0))»);
				«FOR include : tuples.includes»
					this.«include.name» = «arrayName».get«include.type.name»();
				«ENDFOR»
				// end of setSubscript()	
		'''
		return block
	}

//	private def String generateSetSubscript(YProperty property, int index) {
//		val arrayName = property.name
//		val tuples = property.tuples
//		val block = '''		
//			«arrayName».setSubscript(«index»);
//			«FOR include : tuples.includes»
//				this.«include.name» = «arrayName».get«include.type.name»();
//			«ENDFOR»
//		'''
//		return block
//	}
}
