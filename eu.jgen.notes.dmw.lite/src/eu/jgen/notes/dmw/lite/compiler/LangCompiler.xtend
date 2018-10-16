package eu.jgen.notes.dmw.lite.compiler

import com.google.inject.Inject
import eu.jgen.notes.dmw.lite.lang.YCreateStatement
import eu.jgen.notes.dmw.lite.lang.YProperty
import eu.jgen.notes.dmw.lite.runtimes.DMWRuntimeException
import eu.jgen.notes.dmw.lite.utility.LangUtil
import java.sql.PreparedStatement
import java.sql.SQLException
import org.eclipse.xtext.xbase.XExpression
import org.eclipse.xtext.xbase.compiler.XbaseCompiler
import org.eclipse.xtext.xbase.compiler.output.ITreeAppendable
import eu.jgen.notes.dmw.lite.lang.YReadStatement
import eu.jgen.notes.dmw.lite.utility.LangJavaGeneratorHelper
import org.eclipse.xtext.xbase.XBinaryOperation

class LangCompiler extends XbaseCompiler {

	@Inject extension LangUtil
	@Inject extension LangJavaGeneratorHelper

	override protected doInternalToJavaStatement(XExpression expr, ITreeAppendable b, boolean isReferenced) {
		switch expr {
			YCreateStatement: {
				_toJavaStatement(expr, b, isReferenced)
			}
			YReadStatement: {
				_toJavaStatement(expr, b, isReferenced)
			}
			default:
				super.doInternalToJavaStatement(expr, b, isReferenced)
		}
	}

	protected def void _toJavaStatement(YReadStatement readStatement, ITreeAppendable b, boolean isReferenced) {
		var String bufferName = b.declareSyntheticVariable(readStatement, "_buffer");

		b.newLine.newLine
		b.append("//************** Begin of Read Statement **************")
		b.newLine
		b.append("//************** End of Read Statement **************")
		b.newLine.append("StringBuffer ").append(bufferName).append(" = new StringBuffer();")
		b.newLine.append(bufferName).append(".append(\"SELECT \");").newLine.append(bufferName).append(".append(\"")
		val elements = readStatement.createQualifiedColumnNamesListForRead.iterator;
		while (elements.hasNext()) {
			val element = elements.next
			b.append(element)
			if (elements.hasNext()) {
				b.append(", ");
			}
		}
		b.append("\");").newLine
		b.newLine.append(bufferName).append(".append(\"FROM ")
		val elements2 = readStatement.generateFROMClause.iterator;
		while (elements2.hasNext()) {
			val element = elements2.next
			b.append(element)
			if (elements.hasNext()) {
				b.append(", ");
			}
		}    
		b.append("\");").newLine
		if (readStatement.whereclause !== null) {
			val whereclause = readStatement.whereclause
			b.newLine.append(bufferName).append(".append(\"WHERE \");")
			b.newLine
			b.append("// begin of where exception")
			println((whereclause as XBinaryOperation).leftOperand)
			
			readStatement.whereclause.internalToJavaStatement(b, false)
			
			println((whereclause as XBinaryOperation).rightOperand)
			
			b.newLine
			b.append("// end of where exception")
		}
	}

	protected def void _toJavaStatement(YCreateStatement createStatement, ITreeAppendable b, boolean isReferenced) {
		var String bufferName = b.declareSyntheticVariable(createStatement, "_buffer");
		val implementingTable = createStatement.struct.structclass.implementingTable
		b.newLine.newLine
		b.append("//************** Begin of Create Statement **************")
		b.newLine
		b.append("// begin of set section")
		for (expr : createStatement.setExpressions) {
			expr.internalToJavaStatement(b, false)
		}
		b.newLine
		b.append("// end of set section")
		b.newLine.newLine
		b.append("StringBuffer ").append(bufferName).append(" = new StringBuffer();")
		b.newLine
		b.append(bufferName).append(".append(\"INSERT INTO \\\"" + implementingTable.name + "\\\" ( \");").newLine.
			append(bufferName).append(".append(\"")
		val elements = createStatement.struct.structproperty.findStructureDeclaration.members.iterator;
		while (elements.hasNext()) {
			val element = elements.next
			b.append("\\\"" + getImplementingColumnName(implementingTable, element) + "\\\"")
			if (elements.hasNext()) {
				b.append(", ");
			}
		}
		b.append("\");").newLine
		b.append(bufferName).append(".append(\") VALUES (\");")
		b.newLine
		b.append(bufferName).append(".append(\"")
		val elements2 = createStatement.struct.structproperty.findStructureDeclaration.members.iterator;
		while (elements2.hasNext()) {
			elements2.next
			b.append("?")
			if (elements2.hasNext()) {
				b.append(", ");
			}
		}
		b.append(")\");")
		b.newLine
		b.append("try {").increaseIndentation();
		b.newLine.append(PreparedStatement).append(
			" _statement = getContext().getConnection().prepareStatement(_buffer.toString());")
		b.newLine.append(generateSetMethodsForCreateStatement(createStatement))
		b.newLine.append(" _statement.execute();")
		b.newLine.newLine
		b.append("// begin of success section")
		for (expr : createStatement.successExpressions) {
			expr.internalToJavaStatement(b, false)
		}
		b.newLine
		b.append("// end of success section")
		b.decreaseIndentation().newLine().newLine()
		b.newLine.append("_statement.close();")
		b.newLine.append("} catch (")
		b.append('org.apache.derby.shared.common.error.DerbySQLIntegrityConstraintViolationException')
		b.append(" e) {")
		b.newLine.newLine
		b.append("// begin of already exists section")
		for (expr : createStatement.alreadyExistExpressions) {
			expr.internalToJavaStatement(b, false)
		}
		b.newLine
		b.append("// end of already exists section")
		b.newLine.newLine.append("} catch (")
		b.append(SQLException).append(" e) {")
		b.newLine.append("   throw new ")
		b.append(DMWRuntimeException).append("(e);")
		b.newLine.append("}")
		b.newLine.append("//************** End of Create Statement **************")
	}

	private def String generateSetMethodsForCreateStatement(YCreateStatement createStatement) {
		val buffer = new StringBuffer()
		var index = 1;
		for (member : createStatement.struct.structproperty.findStructureDeclaration.members) {
			if (member instanceof YProperty) {
				val property = member as YProperty
				buffer.append("_statement." + generaterSetMethodName(property))
				buffer.append("(")
				buffer.append(index)
				buffer.append(", this.")
				buffer.append(createStatement.struct.structproperty.name + "." + property.name + ".value")
				buffer.append(");\n")
				index++
			}
		}
		return buffer.toString
	}

	private def String generaterSetMethodName(YProperty property) {
		return switch (property.type.simpleName) {
			case "XInt": {
				"setInt"
			}
			case "XShort": {
				"setShort"
			}
			case "XLong": {
				"setLong"
			}
			case "XBool": {
				"setBoolean"
			}
			case "XString": {
				"setString"
			}
			case "XDouble": {
				"setDouble"
			}
			case "XTime": {
				"setTime"
			}
			case "XDate": {
				"setDate"
			}
			case "XTimestamp": {
				"setTimestamp"
			}
			default: {
				"not handled property"
			}
		}
	}

}
