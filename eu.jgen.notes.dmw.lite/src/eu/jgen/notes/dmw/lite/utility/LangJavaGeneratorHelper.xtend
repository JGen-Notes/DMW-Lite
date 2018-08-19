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

class LangJavaGeneratorHelper {

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
				return "XInt"
			}
			case "Short": {
				return "XShort"
			}
			case "Long": {
				return "XLong"
			}
			case "Decimal": {
				return "XDouble"
			}
			case "String": {
				return "XString"
			}

			default: {
				return "//TODO - not translkated yet"
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
	
	def String getPropertyDefault(YProperty property) {
		
		switch (property.type.name.translateTypeName) {
			case "XInt": {
				return "0"
			}
			case "XString": {
				return "\"\""
			}
			default: {
				
			}
		}
		
	}
	
	def ArrayList<YProperty> listArrayProperties(YClass eClass) {
		val ArrayList<YProperty> array = newArrayList()
		for (member : eClass.members) {
			if(member instanceof YProperty) {
				val property = member as YProperty
				if(property.type.name == "Array") {
					array.add(property)
				}
			}
		}		
		return array;		
	}

}
