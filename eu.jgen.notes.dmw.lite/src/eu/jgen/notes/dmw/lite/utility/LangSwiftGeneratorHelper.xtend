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
import eu.jgen.notes.dmw.lite.lang.YAnnotAbstractColumn
import eu.jgen.notes.dmw.lite.lang.YAnnotColumn
import eu.jgen.notes.dmw.lite.lang.YAnnotColumnLike
import eu.jgen.notes.dmw.lite.lang.YAnnotTable

class LangSwiftGeneratorHelper {

	@Inject
	private IEObjectDocumentationProvider documentationProvider;

	def String getDocumentation( /* @Nullable */ EObject source) {
		if (source === null)
			return null;
		if (source instanceof JvmIdentifiableElement) {
			val adapter = EcoreUtil.getAdapter(source.eAdapters(), DocumentationAdapter) as DocumentationAdapter
			if (adapter !== null)
				return adapter.getDocumentation().wrapAsSwiftComment;
		}
		val documentation = documentationProvider.getDocumentation(source);
		return documentation.wrapAsSwiftComment

	}

	def private String wrapAsSwiftComment(String documentation) {
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

	def String getSwiftColumnName(YAnnotAbstractColumn abstractColumn) {
		if (abstractColumn.type instanceof YAnnotColumn) {
			val annotColumn = abstractColumn.type as YAnnotColumn
			return annotColumn.attrref.name
		}
		return ""
	}

	def String getSwiftColumnType(YAnnotAbstractColumn abstractColumn) {
		var prkeytext = ""
		if (isSwiftPrimarykey(abstractColumn) ) {
			prkeytext = ", primaryKey: true"
		}
		if (abstractColumn.type instanceof YAnnotColumn) {
			return getSwiftColumnType(abstractColumn.type as YAnnotColumn) + prkeytext
		} else if (abstractColumn.type instanceof YAnnotColumnLike) {
			return getSwiftColumnType((abstractColumn.type as YAnnotColumnLike).columnref) + prkeytext
		}
		return ""
	}

	def String getSwiftColumnType(YAnnotColumn annotColumn) {
		switch (annotColumn.type) {
			case "VARCHAR": {
				return "String.self"
			}
			case "CHAR": {
				return "String.self"
			}
			case "BIGINT": {
				return "Int64.self"
			}
			case "INTEGER": {
				return "Int32.self"
			}
			case "SMALLINT": {
				return "Int16.self"
			}
			case "DECIMAL": {
				return "Double.self"
			}
			case "TIME": {
				return "Date.self"
			}
			case "TIMESTAMP": {
				return "Date.self"
			}
			case "DATE": {
				return "Date.self"
			}
			case "BOOLEAN": {
				return "Bool.self"
			}
			default: {
				return "unknown"
			}
		}
	}
	
	def boolean isSwiftPrimarykey(YAnnotAbstractColumn abstractColumn ) {
		val table = abstractColumn.eContainer as YAnnotTable
		val pr = table.primarykey
		if(pr === null) {
			return false
		}
		for (column : pr.columns) {
			if(column.name == abstractColumn.name) {
				return true
			}
		}		
		return false
	}

}
