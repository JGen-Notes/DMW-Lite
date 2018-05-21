package eu.jgen.notes.dmw.lite.utility

import com.google.inject.Inject
import org.eclipse.xtext.documentation.IEObjectDocumentationProvider
import org.eclipse.emf.ecore.EObject
import org.eclipse.xtext.common.types.JvmIdentifiableElement
import org.eclipse.emf.ecore.util.EcoreUtil
import org.eclipse.xtext.xbase.compiler.DocumentationAdapter
import eu.jgen.notes.dmw.lite.lang.YProperty
import eu.jgen.notes.dmw.lite.lang.YWidget
import eu.jgen.notes.dmw.lite.lang.YClass

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
		if(a instanceof YWidget) {
			return (a as YWidget).name
		} else 	if(a instanceof YClass) {
			return  ((a as YClass).eContainer as YWidget).name  + "." + (a as YClass).name
		} else {
			return "<do not know what to do yet>"
		}

		
	}
}