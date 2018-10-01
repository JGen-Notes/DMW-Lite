package eu.jgen.notes.dmw.lite.utility

import com.google.inject.Inject
import eu.jgen.notes.dmw.lite.lang.YAnnotDefault
import eu.jgen.notes.dmw.lite.lang.YAnnotDefaultNumber
import eu.jgen.notes.dmw.lite.lang.YAnnotDefaultText
import eu.jgen.notes.dmw.lite.lang.YAnnotMax
import eu.jgen.notes.dmw.lite.lang.YClass
import eu.jgen.notes.dmw.lite.lang.YFunction
import eu.jgen.notes.dmw.lite.lang.YProperty
import eu.jgen.notes.dmw.lite.lang.YReadStatement
import eu.jgen.notes.dmw.lite.lang.YStatement
import eu.jgen.notes.dmw.lite.lang.YWidget
import java.util.ArrayList
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.util.EcoreUtil
import org.eclipse.xtext.common.types.JvmIdentifiableElement
import org.eclipse.xtext.documentation.IEObjectDocumentationProvider
import org.eclipse.xtext.xbase.compiler.DocumentationAdapter
import java.util.Map
import java.util.HashMap
import eu.jgen.notes.dmw.lite.lang.YExpression
import eu.jgen.notes.dmw.lite.lang.YMemberSelection
import eu.jgen.notes.dmw.lite.lang.YPlus
import eu.jgen.notes.dmw.lite.lang.YMinus
import eu.jgen.notes.dmw.lite.lang.YAndExpression
import eu.jgen.notes.dmw.lite.lang.YOrExpression
import eu.jgen.notes.dmw.lite.lang.YComparisonExpression
import eu.jgen.notes.dmw.lite.lang.YEqualityExpression
import eu.jgen.notes.dmw.lite.lang.YNot
import eu.jgen.notes.dmw.lite.lang.YSelf
import eu.jgen.notes.dmw.lite.lang.YBoolConstant
import eu.jgen.notes.dmw.lite.lang.YParenties
import eu.jgen.notes.dmw.lite.lang.YSymbolRef
import eu.jgen.notes.dmw.lite.lang.YIntConstant
import eu.jgen.notes.dmw.lite.lang.YStringConstant
import eu.jgen.notes.dmw.lite.lang.YMulOrDiv
import eu.jgen.notes.dmw.lite.lang.YReadEachStatement

class LangJavaGeneratorHelper {

	val String SYSTEM_DEFAULT_STRING = "\"\""
	val String SYSTEM_DEFAULT_INT = "0"
	val String SYSTEM_DEFAULT_SHORT = "0"
	val String SYSTEM_DEFAULT_DOUBLE = "0.0"
	val String SYSTEM_DEFAULT_LONG = "0"

	var Map<String, Integer> usedNames = new HashMap<String, Integer>()

	@Inject extension LangUtil

	@Inject
	private IEObjectDocumentationProvider documentationProvider;

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
			case "Decimal": {
				return "double"
			}
			case "String": {
				return "String"
			}
			case "Boolean": {
				return "boolean"
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
		if (property.attr === null) {
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
			default: {
				return "?"
			}
		}
	}

	def String findDefaultFromAnnot(YProperty property) {
		for (annotation : property.attr.annots) {
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

}
