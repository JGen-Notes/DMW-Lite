package eu.jgen.notes.dmw.lite.compiler;

import com.google.inject.Inject;
import eu.jgen.notes.dmw.lite.lang.YAnnotTable;
import eu.jgen.notes.dmw.lite.lang.YCreateStatement;
import eu.jgen.notes.dmw.lite.lang.YMember;
import eu.jgen.notes.dmw.lite.lang.YProperty;
import eu.jgen.notes.dmw.lite.lang.YReadStatement;
import eu.jgen.notes.dmw.lite.runtimes.DMWRuntimeException;
import eu.jgen.notes.dmw.lite.utility.LangJavaGeneratorHelper;
import eu.jgen.notes.dmw.lite.utility.LangUtil;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;
import org.eclipse.emf.common.util.EList;
import org.eclipse.xtext.xbase.XBinaryOperation;
import org.eclipse.xtext.xbase.XExpression;
import org.eclipse.xtext.xbase.compiler.XbaseCompiler;
import org.eclipse.xtext.xbase.compiler.output.ITreeAppendable;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.InputOutput;

@SuppressWarnings("all")
public class LangCompiler extends XbaseCompiler {
  @Inject
  @Extension
  private LangUtil _langUtil;
  
  @Inject
  @Extension
  private LangJavaGeneratorHelper _langJavaGeneratorHelper;
  
  @Override
  protected void doInternalToJavaStatement(final XExpression expr, final ITreeAppendable b, final boolean isReferenced) {
    boolean _matched = false;
    if (expr instanceof YCreateStatement) {
      _matched=true;
      this._toJavaStatement(((YCreateStatement)expr), b, isReferenced);
    }
    if (!_matched) {
      if (expr instanceof YReadStatement) {
        _matched=true;
        this._toJavaStatement(((YReadStatement)expr), b, isReferenced);
      }
    }
    if (!_matched) {
      super.doInternalToJavaStatement(expr, b, isReferenced);
    }
  }
  
  protected void _toJavaStatement(final YReadStatement readStatement, final ITreeAppendable b, final boolean isReferenced) {
    String bufferName = b.declareSyntheticVariable(readStatement, "_buffer");
    b.newLine().newLine();
    b.append("//************** Begin of Read Statement **************");
    b.newLine();
    b.append("//************** End of Read Statement **************");
    b.newLine().append("StringBuffer ").append(bufferName).append(" = new StringBuffer();");
    b.newLine().append(bufferName).append(".append(\"SELECT \");").newLine().append(bufferName).append(".append(\"");
    final Iterator<String> elements = this._langJavaGeneratorHelper.createQualifiedColumnNamesListForRead(readStatement).iterator();
    while (elements.hasNext()) {
      {
        final String element = elements.next();
        b.append(element);
        boolean _hasNext = elements.hasNext();
        if (_hasNext) {
          b.append(", ");
        }
      }
    }
    b.append("\");").newLine();
    b.newLine().append(bufferName).append(".append(\"FROM ");
    final Iterator<String> elements2 = this._langJavaGeneratorHelper.generateFROMClause(readStatement).iterator();
    while (elements2.hasNext()) {
      {
        final String element = elements2.next();
        b.append(element);
        boolean _hasNext = elements.hasNext();
        if (_hasNext) {
          b.append(", ");
        }
      }
    }
    b.append("\");").newLine();
    XExpression _whereclause = readStatement.getWhereclause();
    boolean _tripleNotEquals = (_whereclause != null);
    if (_tripleNotEquals) {
      final XExpression whereclause = readStatement.getWhereclause();
      b.newLine().append(bufferName).append(".append(\"WHERE \");");
      b.newLine();
      b.append("// begin of where exception");
      InputOutput.<XExpression>println(((XBinaryOperation) whereclause).getLeftOperand());
      this.internalToJavaStatement(readStatement.getWhereclause(), b, false);
      InputOutput.<XExpression>println(((XBinaryOperation) whereclause).getRightOperand());
      b.newLine();
      b.append("// end of where exception");
    }
  }
  
  protected void _toJavaStatement(final YCreateStatement createStatement, final ITreeAppendable b, final boolean isReferenced) {
    String bufferName = b.declareSyntheticVariable(createStatement, "_buffer");
    final YAnnotTable implementingTable = this._langUtil.getImplementingTable(createStatement.getStruct().getStructclass());
    b.newLine().newLine();
    b.append("//************** Begin of Create Statement **************");
    b.newLine();
    b.append("// begin of set section");
    EList<XExpression> _setExpressions = createStatement.getSetExpressions();
    for (final XExpression expr : _setExpressions) {
      this.internalToJavaStatement(expr, b, false);
    }
    b.newLine();
    b.append("// end of set section");
    b.newLine().newLine();
    b.append("StringBuffer ").append(bufferName).append(" = new StringBuffer();");
    b.newLine();
    ITreeAppendable _append = b.append(bufferName);
    String _name = implementingTable.getName();
    String _plus = (".append(\"INSERT INTO \\\"" + _name);
    String _plus_1 = (_plus + "\\\" ( \");");
    _append.append(_plus_1).newLine().append(bufferName).append(".append(\"");
    final Iterator<YMember> elements = this._langUtil.findStructureDeclaration(createStatement.getStruct().getStructproperty()).getMembers().iterator();
    while (elements.hasNext()) {
      {
        final YMember element = elements.next();
        String _implementingColumnName = this._langUtil.getImplementingColumnName(implementingTable, element);
        String _plus_2 = ("\\\"" + _implementingColumnName);
        String _plus_3 = (_plus_2 + "\\\"");
        b.append(_plus_3);
        boolean _hasNext = elements.hasNext();
        if (_hasNext) {
          b.append(", ");
        }
      }
    }
    b.append("\");").newLine();
    b.append(bufferName).append(".append(\") VALUES (\");");
    b.newLine();
    b.append(bufferName).append(".append(\"");
    final Iterator<YMember> elements2 = this._langUtil.findStructureDeclaration(createStatement.getStruct().getStructproperty()).getMembers().iterator();
    while (elements2.hasNext()) {
      {
        elements2.next();
        b.append("?");
        boolean _hasNext = elements2.hasNext();
        if (_hasNext) {
          b.append(", ");
        }
      }
    }
    b.append(")\");");
    b.newLine();
    b.append("try {").increaseIndentation();
    b.newLine().append(PreparedStatement.class).append(
      " _statement = getContext().getConnection().prepareStatement(_buffer.toString());");
    b.newLine().append(this.generateSetMethodsForCreateStatement(createStatement));
    b.newLine().append(" _statement.execute();");
    b.newLine().newLine();
    b.append("// begin of success section");
    EList<XExpression> _successExpressions = createStatement.getSuccessExpressions();
    for (final XExpression expr_1 : _successExpressions) {
      this.internalToJavaStatement(expr_1, b, false);
    }
    b.newLine();
    b.append("// end of success section");
    b.decreaseIndentation().newLine().newLine();
    b.newLine().append("_statement.close();");
    b.newLine().append("} catch (");
    b.append("org.apache.derby.shared.common.error.DerbySQLIntegrityConstraintViolationException");
    b.append(" e) {");
    b.newLine().newLine();
    b.append("// begin of already exists section");
    EList<XExpression> _alreadyExistExpressions = createStatement.getAlreadyExistExpressions();
    for (final XExpression expr_2 : _alreadyExistExpressions) {
      this.internalToJavaStatement(expr_2, b, false);
    }
    b.newLine();
    b.append("// end of already exists section");
    b.newLine().newLine().append("} catch (");
    b.append(SQLException.class).append(" e) {");
    b.newLine().append("   throw new ");
    b.append(DMWRuntimeException.class).append("(e);");
    b.newLine().append("}");
    b.newLine().append("//************** End of Create Statement **************");
  }
  
  private String generateSetMethodsForCreateStatement(final YCreateStatement createStatement) {
    final StringBuffer buffer = new StringBuffer();
    int index = 1;
    EList<YMember> _members = this._langUtil.findStructureDeclaration(createStatement.getStruct().getStructproperty()).getMembers();
    for (final YMember member : _members) {
      if ((member instanceof YProperty)) {
        final YProperty property = ((YProperty) member);
        String _generaterSetMethodName = this.generaterSetMethodName(property);
        String _plus = ("_statement." + _generaterSetMethodName);
        buffer.append(_plus);
        buffer.append("(");
        buffer.append(index);
        buffer.append(", this.");
        String _name = createStatement.getStruct().getStructproperty().getName();
        String _plus_1 = (_name + ".");
        String _name_1 = property.getName();
        String _plus_2 = (_plus_1 + _name_1);
        String _plus_3 = (_plus_2 + ".value");
        buffer.append(_plus_3);
        buffer.append(");\n");
        index++;
      }
    }
    return buffer.toString();
  }
  
  private String generaterSetMethodName(final YProperty property) {
    String _switchResult = null;
    String _simpleName = property.getType().getSimpleName();
    if (_simpleName != null) {
      switch (_simpleName) {
        case "XInt":
          _switchResult = "setInt";
          break;
        case "XShort":
          _switchResult = "setShort";
          break;
        case "XLong":
          _switchResult = "setLong";
          break;
        case "XBool":
          _switchResult = "setBoolean";
          break;
        case "XString":
          _switchResult = "setString";
          break;
        case "XDouble":
          _switchResult = "setDouble";
          break;
        case "XTime":
          _switchResult = "setTime";
          break;
        case "XDate":
          _switchResult = "setDate";
          break;
        case "XTimestamp":
          _switchResult = "setTimestamp";
          break;
        default:
          _switchResult = "not handled property";
          break;
      }
    } else {
      _switchResult = "not handled property";
    }
    return _switchResult;
  }
}
