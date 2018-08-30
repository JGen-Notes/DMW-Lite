package eu.jgen.notes.dmw.lite.utility

import com.google.inject.Inject
import eu.jgen.notes.dmw.lite.lang.YClass
import eu.jgen.notes.dmw.lite.lang.YFunction
import eu.jgen.notes.dmw.lite.lang.YProperty
import eu.jgen.notes.dmw.lite.lang.YWidget
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.util.EcoreUtil
import org.eclipse.xtext.common.types.JvmIdentifiableElement
import org.eclipse.xtext.documentation.IEObjectDocumentationProvider
import org.eclipse.xtext.xbase.compiler.DocumentationAdapter
import java.util.ArrayList
import eu.jgen.notes.dmw.lite.lang.YAnnotAttr
import eu.jgen.notes.dmw.lite.lang.YAnnotDefault
import eu.jgen.notes.dmw.lite.lang.YAnnotDefaultText
import eu.jgen.notes.dmw.lite.lang.YAnnotDefaultNumber
import eu.jgen.notes.dmw.lite.lang.YExpression

class LangJavaGeneratorHelper {

	val String SYSTEM_DEFAULT_STRING = "\"\""
	val String SYSTEM_DEFAULT_INT = "0"
	val String SYSTEM_DEFAULT_SHORT = "0"
	val String SYSTEM_DEFAULT_DOUBLE = "0.0"
	val String SYSTEM_DEFAULT_LONG = "0"

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

	def ArrayList<YProperty> listArrayProperties(YClass eClass) {
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
	
	def boolean isExpressionInt(YExpression expression) {
		
		return true;
	}

}
