package eu.jgen.notes.dmw.lite.utility

import com.google.inject.Inject
import eu.jgen.notes.dmw.lite.lang.YAndExpression
import eu.jgen.notes.dmw.lite.lang.YAnnotDefault
import eu.jgen.notes.dmw.lite.lang.YAnnotDefaultNumber
import eu.jgen.notes.dmw.lite.lang.YAnnotDefaultText
import eu.jgen.notes.dmw.lite.lang.YAnnotMax
import eu.jgen.notes.dmw.lite.lang.YBoolConstant
import eu.jgen.notes.dmw.lite.lang.YClass
import eu.jgen.notes.dmw.lite.lang.YComparisonExpression
import eu.jgen.notes.dmw.lite.lang.YCreateStatement
import eu.jgen.notes.dmw.lite.lang.YEqualityExpression
import eu.jgen.notes.dmw.lite.lang.YExpression
import eu.jgen.notes.dmw.lite.lang.YFunction
import eu.jgen.notes.dmw.lite.lang.YIntConstant
import eu.jgen.notes.dmw.lite.lang.YMember
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
import eu.jgen.notes.dmw.lite.lang.YSelf
import eu.jgen.notes.dmw.lite.lang.YStringConstant
import eu.jgen.notes.dmw.lite.lang.YSymbolRef
import eu.jgen.notes.dmw.lite.lang.YUpdateStatement
import eu.jgen.notes.dmw.lite.lang.YWidget
import java.util.ArrayList
import java.util.HashMap
import java.util.Map
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.util.EcoreUtil
import org.eclipse.xtext.common.types.JvmIdentifiableElement
import org.eclipse.xtext.documentation.IEObjectDocumentationProvider
import org.eclipse.xtext.xbase.compiler.DocumentationAdapter

class LangGeneratorHelperForJava {

	val String SYSTEM_DEFAULT_STRING = "\"\""
	val String SYSTEM_DEFAULT_INT = "0"
	val String SYSTEM_DEFAULT_SHORT = "0"
	val String SYSTEM_DEFAULT_DOUBLE = "0.0"
	val String SYSTEM_DEFAULT_LONG = "0"
	val String SYSTEM_DEFAULT_BOOLEAN = "true"
	val String SYSTEM_DEFAULT_DATE = "new Date(System.currentTimeMillis())"
	val String SYSTEM_DEFAULT_TIME = "new Time(System.currentTimeMillis())"
	val String SYSTEM_DEFAULT_TIMESTAMP = "new Timestamp(System.currentTimeMillis())"

	var Map<String, Integer> usedNames = new HashMap<String, Integer>()

	@Inject extension LangUtil

	@Inject IEObjectDocumentationProvider documentationProvider;

	def String getDocumentation( /* @Nullable */ EObject source) {
		if (source === null)
			return null;
		if (source instanceof JvmIdentifiableElement) {
			val adapter = EcoreUtil.getAdapter(source.eAdapters(), DocumentationAdapter) as DocumentationAdapter
			if (adapter !== null)
				return adapter.getDocumentation().wrapAsJavaComment;
		}
		val documentation = documentationProvider.getDocumentation(source);
		return documentation.wrapAsJavaComment

	}

	def private String wrapAsJavaComment(String documentation) {
		if (documentation === null) {
			return ""
		} else {
			val array = documentation.split("\n")
			var buf = new StringBuffer()
			buf.append("/*")
			for (String line : array) {
				buf.append("\n * " + line)
			}
			buf.append("\n */")
			return buf.toString
		}
	}

	def String findPackageName(YProperty property) {
		val a = property.type.eContainer
		if (a instanceof YWidget) {
			return (a as YWidget).name
		} else if (a instanceof YClass) {
			return ((a as YClass).eContainer as YWidget).name + "." + (a as YClass).name
		} else {
			return "<do not know what to do yet>"
		}

	}

	def String translateTypeName(String typeName) {
		switch (typeName) {
			case "Int": {
				return "int"
			}
			case "Short": {
				return "short"
			}
			case "Long": {
				return "long"
			}
			case "Double": {
				return "double"
			}
			case "String": {
				return "String"
			}
			case "Bool": {
				return "boolean"
			}
			case "Date": {
				return "Date"
			}
			case "Time": {
				return "Time"
			}
			case "Timestamp": {
				return "Timestamp"
			}
			default: {
				return typeName
			}
		}
	}

	def YClass whatFuntionType(EObject eobject) {
		if (eobject.eContainer instanceof YFunction) {
			return (eobject.eContainer as YFunction).type
		} else {
			whatFuntionType(eobject.eContainer)
		}
	}

	def String getPropertyDefaultValue(YProperty property) {
		if (property.attrRef === null) {
			getSystemDefault(property.type.name.translateTypeName)
		} else {
			findDefaultFromAnnot(property)
		}
	}

	def String getSystemDefault(String type) {
		switch (type) {
			case "int": {
				return SYSTEM_DEFAULT_INT
			}
			case "short": {
				return SYSTEM_DEFAULT_SHORT
			}
			case "long": {
				return SYSTEM_DEFAULT_LONG
			}
			case "double": {
				return SYSTEM_DEFAULT_DOUBLE
			}
			case "String": {
				return SYSTEM_DEFAULT_STRING
			}
			case "boolean": {
				return SYSTEM_DEFAULT_BOOLEAN
			}
			case "Date": {
				return SYSTEM_DEFAULT_DATE
			}
			case "Time": {
				return SYSTEM_DEFAULT_TIME
			}
			case "Timestamp": {
				return SYSTEM_DEFAULT_TIMESTAMP
			}
			default: {
				return "?"
			}
		}
	}

	def String findDefaultFromAnnot(YProperty property) {
		for (annotation : property.attrRef.annots) {
			if (annotation.type instanceof YAnnotDefault) {
				val annotDefault = annotation.type as YAnnotDefault
				// TODO - need more work to handle other types of data
				if (annotDefault.type instanceof YAnnotDefaultText) {
					val value = (annotDefault.type as YAnnotDefaultText).value
					return "\"" + value + "\""
				} else if (annotDefault.type instanceof YAnnotDefaultNumber) {
					val value = (annotDefault.type as YAnnotDefaultNumber).value
					return String.valueOf(value)
				} else {
					return "?"
				}
			}
		}
		return getSystemDefault(property.type.name.translateTypeName)
	}

	/*
	 * Find all properties of type Array
	 */
	def ArrayList<YProperty> findPropertiesOfTypeArray(YClass eClass) {
		val ArrayList<YProperty> array = newArrayList()
		for (member : eClass.members) {
			if (member instanceof YProperty) {
				val property = member as YProperty
				if (property.type.name == "Array") {
					array.add(property)
				}
			}
		}
		return array;
	}

	/*
	 * Get size of the array.
	 */
	def int getArraySize(YProperty property) {
		for (annotation : property.annotations) {
			if (annotation.type instanceof YAnnotMax) {
				val annotMax = annotation.type as YAnnotMax
				return annotMax.length
			}
		}
		return 0;
	}

	def ArrayList<String> createQualifiedColumnNamesListForRead(YReadStatement readStatement) {
		val list = newArrayList()
		var index = 1;
		for (struct : readStatement.structs) {
			val implementingTable = struct.structclass.implementingTable
			for (member : struct.structproperty.type.members) {
				list.add('''T«index».\"«getImplementingColumnName(implementingTable,member)»\"''')
			}
			index++;
		}
		return list
	}

	def ArrayList<String> createQualifiedColumnNamesListForReadEach(YReadEachStatement readEachStatement) {
		val list = newArrayList()
		var index = 1;
		for (struct : readEachStatement.structs) {
			val implementingTable = struct.structclass.implementingTable
			for (member : struct.structproperty.type.members) {
				list.add('''T«index».\"«getImplementingColumnName(implementingTable,member)»\"''')
			}
			index++;
		}
		return list
	}

	def ArrayList<String> createReadStatementSetMethodList(ArrayList<String> list, YExpression expression,
		ArrayList<String> readProperties) {

		switch (expression) {
			case expression instanceof YPlus: {
				val plus = expression as YPlus
				(createReadStatementSetMethodList(list, plus.left, readProperties))
				(createReadStatementSetMethodList(list, plus.right, readProperties))
			}
			case expression instanceof YMinus: {
				val minus = expression as YMinus
				(createReadStatementSetMethodList(list, minus.left, readProperties))
				(createReadStatementSetMethodList(list, minus.right, readProperties) )
			}
			case expression instanceof YMulOrDiv: {
				val mulOrDiv = expression as YMulOrDiv
				(createReadStatementSetMethodList(list, mulOrDiv.left, readProperties))
				(createReadStatementSetMethodList(list, mulOrDiv.right, readProperties))
			}
			case expression instanceof YAndExpression: {
				val andExpression = expression as YAndExpression
				(createReadStatementSetMethodList(list, andExpression.left, readProperties))
				(createReadStatementSetMethodList(list, andExpression.right, readProperties))
			}
			case expression instanceof YOrExpression: {
				val orExpression = expression as YOrExpression
				(createReadStatementSetMethodList(list, orExpression.left, readProperties))
				(createReadStatementSetMethodList(list, orExpression.right, readProperties))
			}
			case expression instanceof YComparisonExpression: {
				val comparisonExpression = expression as YComparisonExpression
				(createReadStatementSetMethodList(list, comparisonExpression.left, readProperties))
				(createReadStatementSetMethodList(list, comparisonExpression.right, readProperties))
			}
			case expression instanceof YEqualityExpression: {
				val equalityExpression = expression as YEqualityExpression
				(createReadStatementSetMethodList(list, equalityExpression.left, readProperties))
				(createReadStatementSetMethodList(list, equalityExpression.right, readProperties))
			}
			case expression instanceof YMemberSelection: {
				val memberSelection = expression as YMemberSelection
				if (!isVaraibleProperty(readProperties, (memberSelection.receiver as YMemberSelection).member.name)) {
					val variableName = (memberSelection.receiver as YMemberSelection).member.name + "." +
						memberSelection.member.name
					var setMethodName = ""
					switch (memberSelection.member.type.name) {
						case "Int": {
							setMethodName = "setInt"
						}
						case "Short": {
							setMethodName = "setShort"
						}
						case "String": {
							setMethodName = "setString"
						}
						default: {
							setMethodName = "unknown"
						}
					}
					list.add(setMethodName + "(&index&," + variableName + ");")
				}
			}
			case expression instanceof YSelf: {
				println(expression)
			}
			case expression instanceof YNot: {
				val not = expression as YNot
				(createReadStatementSetMethodList(list, not.expression, readProperties))
			}
			case expression instanceof YBoolConstant: {
			}
			case expression instanceof YParenties: {
			}
			case expression instanceof YSymbolRef: {
			}
			case expression instanceof YIntConstant: {
			}
			case expression instanceof YStringConstant: {
			}
			default: {

				println(expression)
			}
		}
		return list;
	}

	def boolean isVaraibleProperty(ArrayList<String> readProperties, String name) {
		for (property : readProperties) {
			if (property == name) {
				return true
			}
		}

		return false
	}

	def void resetLocalNames() {
		usedNames.clear
	}

	def String generateLocalName(String corename) {
		if (usedNames.containsKey(corename)) {
			val number = usedNames.get(corename).intValue
			usedNames.put(corename, new Integer(number + 1))
			return corename + "_" + number
		} else {
			usedNames.put(corename, new Integer(1))
			return corename
		}
	}

	def String generateGetMethodsForRead(YReadStatement readStatement) {
		val buffer = new StringBuffer()
		var index = 1;
		for (struct : readStatement.structs) {
			for (member : struct.structproperty.type.members) {
				var setMethodName = ""
				switch (member.type.name) {
					case "Int": {
						setMethodName = "getInt"
					}
					case "Short": {
						setMethodName = "getShort"
					}
					case "Long": {
						setMethodName = "getLong"
					}
					case "Bool": {
						setMethodName = "getBoolean"
					}
					case "String": {
						setMethodName = "getString"
					}
					case "Double": {
						setMethodName = "getDouble"
					}
					case "Time": {
						setMethodName = "getTime"
					}
					case "Date": {
						setMethodName = "getDate"
					}
					case "Timestamp": {
						setMethodName = "getTimestamp"
					}
					default: {
						setMethodName = "unknown"
					}
				}
				buffer.append(
					struct.structproperty.name + "." + member.name + " = _rs." + setMethodName + "(" +
						Integer.toString(index) + ");\n")
				index++
			}
		}
		return buffer.toString
	}

	def String generaterUpdateMethodName(YMember member) {
		switch (member.type.name) {
			case "Int": {
				return "updateInt"
			}
			case "Short": {
				return "updateShort"
			}
			case "Long": {
				return "updateLong"
			}
			case "String": {
				return "updateString"
			}
			case "Double": {
				return "updateDouble"
			}
			case "Date": {
				return "updateDate"
			}
			case "Time": {
				return "updateTime"
			}
			case "Timestamp": {
				return "updateTimestamp"
			}
			case "Bool": {
				return "updateBoolean"
			}
			default: {
				return "not yet done"
			}
		}
	}

	def String generaterSetMethodName(YMember member) {
		switch (member.type.name) {
			case "Int": {
				return "setInt"
			}
			case "Short": {
				return "setShort"
			}
			case "Long": {
				return "setLong"
			}
			case "Bool": {
				return "setBoolean"
			}
			case "String": {
				return "setString"
			}
			case "Double": {
				return "setDouble"
			}
			case "Time": {
				return "setTime"
			}
			case "Date": {
				return "setDate"
			}
			case "Timestamp": {
				return "setTimestamp"
			}
			default: {
				return "not yet done"

			}
		}
	}

	def String generateGetMethodsForReadEach(YReadEachStatement readEachStatement) {
		val buffer = new StringBuffer()
		var index = 1;
		for (struct : readEachStatement.structs) {
			for (member : struct.structproperty.type.members) {
				var setMethodName = ""
				switch (member.type.name) {
					case "Int": {
						setMethodName = "getInt"
					}
					case "Short": {
						setMethodName = "getShort"
					}
					case "Long": {
						setMethodName = "getLong"
					}
					case "Bool": {
						setMethodName = "getBoolean"
					}
					case "String": {
						setMethodName = "getString"
					}
					case "Double": {
						setMethodName = "getDouble"
					}
					case "Time": {
						setMethodName = "getTime"
					}
					case "Date": {
						setMethodName = "getDate"
					}
					case "Timestamp": {
						setMethodName = "getTimestamp"
					}
					default: {
						setMethodName = "unknown"
					}
				}
				buffer.append(
					struct.structproperty.name + "." + member.name + " = _rs." + setMethodName + "(" +
						Integer.toString(index) + ");\n")
				index++
			}
		}
		return buffer.toString
	}

	def String generateSetMethodsForCreateStatement(YCreateStatement createStatement) {
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

	def String generateSetMethodsForUpdateStatement(YUpdateStatement updateStatement) {
		val buffer = new StringBuffer()
		var index = 1;
		for (member : updateStatement.struct.structproperty.type.members) {
			buffer.append("_rs." + generaterUpdateMethodName(member))
			buffer.append("(")
			buffer.append(index)
			buffer.append(", ")
			buffer.append(updateStatement.struct.structproperty.name + "." + member.name)
			buffer.append(");\n")
			index++
		}
		return buffer.toString
	}

	def String generateSetMethodsForRead(YReadStatement readStatement) {
		val buffer = new StringBuffer()
		var index = 1;
		val list = newArrayList()
		val proplist = newArrayList()
		getListOfPropertiesForRead(readStatement, proplist)
		proplist.add("viewF")
		val newlist = createReadStatementSetMethodList(list, readStatement.whereclause.expression, proplist)
		for (setMethod : newlist) {
			buffer.append("_statement." + setMethod.replace("&index&", Integer.toString(index)) + "\n")
			index++
		}
		return buffer.toString
	}

	def String generateSetMethodsForReadEach(YReadEachStatement readEachStatement) {
		val buffer = new StringBuffer()
		var index = 1;
		val list = newArrayList()
		val proplist = newArrayList()
		getListOfPropertiesForReadEach(readEachStatement, proplist)
		proplist.add("viewF")
		val newlist = createReadStatementSetMethodList(list, readEachStatement.whereclause.expression, proplist)
		for (setMethod : newlist) {
			buffer.append("_statement." + setMethod.replace("&index&", Integer.toString(index)) + "\n")
			index++
		}
		return buffer.toString
	}

	private def getListOfPropertiesForRead(YReadStatement readStatement, ArrayList<String> readProperties) {
		for (struct : readStatement.structs) {
			readProperties.add(struct.structproperty.name)
		}
	}

	private def getListOfPropertiesForReadEach(YReadEachStatement readEachStatement, ArrayList<String> readProperties) {
		for (struct : readEachStatement.structs) {
			readProperties.add(struct.structproperty.name)
		}
	}
	
    def String generateFunctionMove(YMemberSelection memberSelection) {
		val buffer = new StringBuffer()
		buffer.append("\n// start of moveStruct()\n")
		val fromProperty = memberSelection.args.get(0) as YMemberSelection
		val toProperty = memberSelection.args.get(1) as YMemberSelection
		for (from : fromProperty.member.type.members) {
			for (to : toProperty.member.type.members) {
				if ((from as YProperty).name == (to as YProperty).name &&
					(from as YProperty).type.name == (to as YProperty).type.name) {
					buffer.append(
						"this." + toProperty.member.name + "." + (to as YProperty).name + " = " + "this." +
							fromProperty.member.name + "." + (from as YProperty).name + ";\n")
				}
			}
		}
		buffer.append("// end of moveStruct()\n")
		return buffer.toString
	}
	
	

}
