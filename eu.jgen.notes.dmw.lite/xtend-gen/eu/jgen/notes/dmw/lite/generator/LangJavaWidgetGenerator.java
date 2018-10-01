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
 */
package eu.jgen.notes.dmw.lite.generator;

import com.google.common.base.Objects;
import com.google.inject.Inject;
import eu.jgen.notes.dmw.lite.generator.LangOutputProvider;
import eu.jgen.notes.dmw.lite.lang.YAccessLevel;
import eu.jgen.notes.dmw.lite.lang.YAndExpression;
import eu.jgen.notes.dmw.lite.lang.YAnnotAbstractColumn;
import eu.jgen.notes.dmw.lite.lang.YAnnotColumn;
import eu.jgen.notes.dmw.lite.lang.YAnnotColumnLike;
import eu.jgen.notes.dmw.lite.lang.YAnnotForeignKey;
import eu.jgen.notes.dmw.lite.lang.YAnnotJava;
import eu.jgen.notes.dmw.lite.lang.YAnnotTable;
import eu.jgen.notes.dmw.lite.lang.YAssignment;
import eu.jgen.notes.dmw.lite.lang.YBlock;
import eu.jgen.notes.dmw.lite.lang.YBoolConstant;
import eu.jgen.notes.dmw.lite.lang.YClass;
import eu.jgen.notes.dmw.lite.lang.YComparisonExpression;
import eu.jgen.notes.dmw.lite.lang.YCreateStatement;
import eu.jgen.notes.dmw.lite.lang.YDeleteStatement;
import eu.jgen.notes.dmw.lite.lang.YEqualityExpression;
import eu.jgen.notes.dmw.lite.lang.YExpression;
import eu.jgen.notes.dmw.lite.lang.YForInStatement;
import eu.jgen.notes.dmw.lite.lang.YFunction;
import eu.jgen.notes.dmw.lite.lang.YIfStatement;
import eu.jgen.notes.dmw.lite.lang.YIntConstant;
import eu.jgen.notes.dmw.lite.lang.YJoin;
import eu.jgen.notes.dmw.lite.lang.YJoinDef;
import eu.jgen.notes.dmw.lite.lang.YMember;
import eu.jgen.notes.dmw.lite.lang.YMemberSelection;
import eu.jgen.notes.dmw.lite.lang.YMinus;
import eu.jgen.notes.dmw.lite.lang.YMulOrDiv;
import eu.jgen.notes.dmw.lite.lang.YNot;
import eu.jgen.notes.dmw.lite.lang.YOrExpression;
import eu.jgen.notes.dmw.lite.lang.YParameter;
import eu.jgen.notes.dmw.lite.lang.YParenties;
import eu.jgen.notes.dmw.lite.lang.YPlus;
import eu.jgen.notes.dmw.lite.lang.YProperty;
import eu.jgen.notes.dmw.lite.lang.YReadEachStatement;
import eu.jgen.notes.dmw.lite.lang.YReadStatement;
import eu.jgen.notes.dmw.lite.lang.YRepeatWhileStatement;
import eu.jgen.notes.dmw.lite.lang.YReturn;
import eu.jgen.notes.dmw.lite.lang.YSelf;
import eu.jgen.notes.dmw.lite.lang.YStatement;
import eu.jgen.notes.dmw.lite.lang.YStringConstant;
import eu.jgen.notes.dmw.lite.lang.YStructRefPair;
import eu.jgen.notes.dmw.lite.lang.YSwitchCase;
import eu.jgen.notes.dmw.lite.lang.YSwitchStatement;
import eu.jgen.notes.dmw.lite.lang.YSymbolRef;
import eu.jgen.notes.dmw.lite.lang.YTuples;
import eu.jgen.notes.dmw.lite.lang.YUpdateStatement;
import eu.jgen.notes.dmw.lite.lang.YVariableDeclaration;
import eu.jgen.notes.dmw.lite.lang.YWhileStatement;
import eu.jgen.notes.dmw.lite.lang.YWidget;
import eu.jgen.notes.dmw.lite.typing.LangTypeComputer;
import eu.jgen.notes.dmw.lite.utility.LangJavaGeneratorHelper;
import eu.jgen.notes.dmw.lite.utility.LangUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.generator.IFileSystemAccess;
import org.eclipse.xtext.generator.IGenerator;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IteratorExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.eclipse.xtext.xbase.lib.StringExtensions;

@SuppressWarnings("all")
public class LangJavaWidgetGenerator implements IGenerator {
  @Inject
  @Extension
  private LangUtil _langUtil;
  
  @Inject
  @Extension
  private LangJavaGeneratorHelper _langJavaGeneratorHelper;
  
  @Inject
  @Extension
  private LangTypeComputer _langTypeComputer;
  
  private List<String> imports = CollectionLiterals.<String>newArrayList();
  
  private List<String> innerFunctions = CollectionLiterals.<String>newArrayList();
  
  @Override
  public void doGenerate(final Resource input, final IFileSystemAccess fsa) {
    final Function1<EObject, Boolean> _function = (EObject it) -> {
      return Boolean.valueOf((it instanceof YAnnotJava));
    };
    EObject _findFirst = IteratorExtensions.<EObject>findFirst(input.getAllContents(), _function);
    boolean _tripleNotEquals = (_findFirst != null);
    if (_tripleNotEquals) {
      final Function1<EObject, Boolean> _function_1 = (EObject it) -> {
        return Boolean.valueOf((it instanceof YWidget));
      };
      final Iterator<EObject> list = IteratorExtensions.<EObject>filter(input.getAllContents(), _function_1);
      final Procedure1<EObject> _function_2 = (EObject it) -> {
        this.generateWidget(fsa, ((YWidget) it));
      };
      IteratorExtensions.<EObject>forEach(list, _function_2);
    }
  }
  
  private void generateWidget(final IFileSystemAccess fsa, final YWidget widget) {
    final Consumer<YClass> _function = (YClass clazz) -> {
      if (((clazz.getSuperclass() != null) && Objects.equal(clazz.getSuperclass().getName(), "Widget"))) {
        this._langJavaGeneratorHelper.resetLocalNames();
        this.innerFunctions.clear();
        this.imports.clear();
        this.imports.add("eu.jgen.notes.dmw.lite.runtimes.XWidget");
        StringConcatenation _builder = new StringConcatenation();
        String _documentation = this._langJavaGeneratorHelper.getDocumentation(clazz);
        _builder.append(_documentation);
        _builder.newLineIfNotEmpty();
        _builder.append("@SuppressWarnings(\"all\")");
        _builder.newLine();
        _builder.append("public class ");
        String _name = clazz.getName();
        _builder.append(_name);
        _builder.append(" extends XWidget {");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.newLine();
        _builder.append("\t  ");
        _builder.append("public ");
        String _name_1 = clazz.getName();
        _builder.append(_name_1, "\t  ");
        _builder.append("() {");
        _builder.newLineIfNotEmpty();
        _builder.append("\t  ");
        _builder.append("}");
        _builder.newLine();
        _builder.append("\t  ");
        String _generateInnerClasses = this.generateInnerClasses(clazz, clazz.getName());
        _builder.append(_generateInnerClasses, "\t  ");
        _builder.newLineIfNotEmpty();
        _builder.append("\t  ");
        String _generateProperties = this.generateProperties(clazz);
        _builder.append(_generateProperties, "\t  ");
        _builder.append("\t\t\t\t\t   ");
        _builder.newLineIfNotEmpty();
        _builder.append("\t  ");
        String _generateArrays = this.generateArrays(clazz, clazz.getName());
        _builder.append(_generateArrays, "\t  ");
        _builder.newLineIfNotEmpty();
        _builder.append("\t  ");
        String _generateGetInstance = this.generateGetInstance(clazz, clazz.getName());
        _builder.append(_generateGetInstance, "\t  ");
        _builder.append("\t");
        _builder.newLineIfNotEmpty();
        _builder.append("\t  ");
        String _generateConstructor = this.generateConstructor(clazz, clazz.getName());
        _builder.append(_generateConstructor, "\t  ");
        _builder.append("\t\t\t   ");
        _builder.newLineIfNotEmpty();
        _builder.append("\t  ");
        String _generateFunctions = this.generateFunctions(clazz);
        _builder.append(_generateFunctions, "\t  ");
        _builder.newLineIfNotEmpty();
        {
          for(final String innerFunction : this.innerFunctions) {
            _builder.append("\t  ");
            _builder.newLine();
            _builder.append("\t  ");
            _builder.append(innerFunction, "\t  ");
            _builder.newLineIfNotEmpty();
          }
        }
        _builder.append("}");
        _builder.newLine();
        final String body = _builder.toString();
        String _fileSystemPath = this._langUtil.getFileSystemPath(widget.getName());
        String _plus = (_fileSystemPath + "/");
        String _name_2 = clazz.getName();
        String _plus_1 = (_plus + _name_2);
        String _plus_2 = (_plus_1 + ".java");
        StringConcatenation _builder_1 = new StringConcatenation();
        _builder_1.append("package ");
        String _name_3 = widget.getName();
        _builder_1.append(_name_3);
        _builder_1.append(";");
        _builder_1.newLineIfNotEmpty();
        _builder_1.newLine();
        {
          for(final String imp : this.imports) {
            _builder_1.append("import ");
            _builder_1.append(imp);
            _builder_1.append(";");
            _builder_1.newLineIfNotEmpty();
          }
        }
        _builder_1.newLine();
        _builder_1.append(body);
        _builder_1.newLineIfNotEmpty();
        fsa.generateFile(_plus_2, 
          LangOutputProvider.DEFAULT, _builder_1);
      } else {
        this.imports.clear();
        StringConcatenation _builder_2 = new StringConcatenation();
        String _documentation_1 = this._langJavaGeneratorHelper.getDocumentation(clazz);
        _builder_2.append(_documentation_1);
        _builder_2.newLineIfNotEmpty();
        _builder_2.append("@SuppressWarnings(\"unused\")");
        _builder_2.newLine();
        _builder_2.append("public class ");
        String _name_4 = clazz.getName();
        _builder_2.append(_name_4);
        _builder_2.append(" {");
        _builder_2.newLineIfNotEmpty();
        _builder_2.newLine();
        _builder_2.append("}");
        _builder_2.newLine();
        final String body_1 = _builder_2.toString();
        String _fileSystemPath_1 = this._langUtil.getFileSystemPath(widget.getName());
        String _plus_3 = (_fileSystemPath_1 + "/");
        String _name_5 = clazz.getName();
        String _plus_4 = (_plus_3 + _name_5);
        String _plus_5 = (_plus_4 + ".java");
        StringConcatenation _builder_3 = new StringConcatenation();
        _builder_3.append("package ");
        String _name_6 = widget.getName();
        _builder_3.append(_name_6);
        _builder_3.append(";");
        _builder_3.newLineIfNotEmpty();
        _builder_3.newLine();
        {
          for(final String imp_1 : this.imports) {
            _builder_3.append("import ");
            _builder_3.append(imp_1);
            _builder_3.append(";");
            _builder_3.newLineIfNotEmpty();
          }
        }
        _builder_3.newLine();
        _builder_3.append(body_1);
        _builder_3.newLineIfNotEmpty();
        fsa.generateFile(_plus_5, 
          LangOutputProvider.DEFAULT, _builder_3);
      }
    };
    widget.getClasses().forEach(_function);
  }
  
  private String generateGetInstance(final YClass clazz, final String name) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("public static ");
    String _name = clazz.getName();
    _builder.append(_name);
    _builder.append(" getInstance(Connection connection) {");
    _builder.newLineIfNotEmpty();
    _builder.append("   ");
    _builder.append("return new ");
    String _name_1 = clazz.getName();
    _builder.append(_name_1, "   ");
    _builder.append("(connection);");
    _builder.newLineIfNotEmpty();
    _builder.append("}");
    _builder.newLine();
    return _builder.toString();
  }
  
  /**
   * Generate constructor for the widget class.
   */
  private String generateConstructor(final YClass clazz, final String name) {
    String _xblockexpression = null;
    {
      this.registerImport("java.sql.Connection");
      this.registerImport("java.sql.SQLException");
      this.registerImport("java.sql.PreparedStatement");
      this.registerImport("java.sql.ResultSet");
      this.registerImport("eu.jgen.notes.dmw.lite.runtimes.DMWRuntimeException");
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("//");
      _builder.newLine();
      _builder.append("public  ");
      String _name = clazz.getName();
      _builder.append(_name);
      _builder.append("(Connection connection) {");
      _builder.newLineIfNotEmpty();
      _builder.append("   ");
      _builder.append("this._connection = connection;");
      _builder.newLine();
      {
        EList<YMember> _members = clazz.getMembers();
        for(final YMember member : _members) {
          {
            if ((member instanceof YProperty)) {
              _builder.append("   ");
              String _generateInitializeStructure = this.generateInitializeStructure(clazz, ((YProperty) member));
              _builder.append(_generateInitializeStructure, "   ");
              _builder.newLineIfNotEmpty();
            }
          }
        }
      }
      _builder.append("}");
      _builder.newLine();
      _xblockexpression = _builder.toString();
    }
    return _xblockexpression;
  }
  
  /**
   * Generate content of the constructor for the widget class. It is a sequence
   * of methods initialising structures and setting default values for each property.
   */
  private String generateInitializeStructure(final YClass clazz, final YProperty property) {
    String _xblockexpression = null;
    {
      if ((((((Objects.equal(property.getType().getName(), "Array") || Objects.equal(property.getType().getName(), "Int")) || Objects.equal(property.getType().getName(), "Short")) || 
        Objects.equal(property.getType().getName(), "Decimal")) || Objects.equal(property.getType().getName(), "Long")) || Objects.equal(property.getType().getName(), "Boolean"))) {
        return "";
      }
      String _initStructure = this._langJavaGeneratorHelper.generateLocalName("_initStructure");
      this.generateInitStructureMethod(clazz, property, _initStructure);
      StringConcatenation _builder = new StringConcatenation();
      _builder.append(_initStructure);
      _builder.append("();");
      _xblockexpression = _builder.toString();
    }
    return _xblockexpression;
  }
  
  /**
   * Generate body of initialisation  method for for structure
   */
  private void generateInitStructureMethod(final YClass clazz, final YProperty property, final String methodName) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("private void ");
    _builder.append(methodName);
    _builder.append("() {");
    _builder.newLineIfNotEmpty();
    _builder.append("   ");
    String _name = property.getName();
    _builder.append(_name, "   ");
    _builder.append(" = new ");
    String _name_1 = property.getType().getName();
    _builder.append(_name_1, "   ");
    _builder.append("();");
    _builder.newLineIfNotEmpty();
    {
      EList<YMember> _members = property.getType().getMembers();
      for(final YMember member : _members) {
        {
          if ((member instanceof YProperty)) {
            _builder.append("   ");
            String _generateInitializeProperty = this.generateInitializeProperty(((YProperty) member), property.getName());
            _builder.append(_generateInitializeProperty, "   ");
            _builder.newLineIfNotEmpty();
          }
        }
      }
    }
    _builder.append("}\t");
    _builder.newLine();
    this.innerFunctions.add(_builder.toString());
  }
  
  private String generateStatementCreateInnerFunction(final YCreateStatement createStatement, final String _create) {
    createStatement.getStruct().getStructproperty().getType().getMembers();
    final YAnnotTable implementingTable = this._langUtil.getImplementingTable(createStatement.getStruct().getStructclass());
    StringConcatenation _builder = new StringConcatenation();
    _builder.newLine();
    _builder.append("private boolean ");
    _builder.append(_create);
    _builder.append("() {");
    _builder.newLineIfNotEmpty();
    _builder.append("   ");
    _builder.append("StringBuffer buffer = new StringBuffer();");
    _builder.newLine();
    _builder.append("   ");
    _builder.append("buffer.append(\"INSERT INTO \\\"");
    String _name = implementingTable.getName();
    _builder.append(_name, "   ");
    _builder.append("\\\" (\");");
    _builder.newLineIfNotEmpty();
    _builder.append("   ");
    _builder.append("buffer.append(\"");
    {
      EList<YMember> _members = createStatement.getStruct().getStructproperty().getType().getMembers();
      boolean _hasElements = false;
      for(final YMember member : _members) {
        if (!_hasElements) {
          _hasElements = true;
        } else {
          _builder.appendImmediate(",", "   ");
        }
        _builder.append("\\\"");
        String _implementingColumnName = this._langUtil.getImplementingColumnName(implementingTable, member);
        _builder.append(_implementingColumnName, "   ");
        _builder.append("\\\"");
      }
    }
    _builder.append("\");");
    _builder.newLineIfNotEmpty();
    _builder.append("   ");
    _builder.append("buffer.append(\") VALUES (\");");
    _builder.newLine();
    _builder.append("   ");
    _builder.append("buffer.append(\"");
    {
      EList<YMember> _members_1 = createStatement.getStruct().getStructproperty().getType().getMembers();
      boolean _hasElements_1 = false;
      for(final YMember member_1 : _members_1) {
        if (!_hasElements_1) {
          _hasElements_1 = true;
        } else {
          _builder.appendImmediate(",", "   ");
        }
        _builder.append("?");
      }
    }
    _builder.append("\");");
    _builder.newLineIfNotEmpty();
    _builder.append("   ");
    _builder.append("buffer.append(\")\");\t");
    _builder.newLine();
    _builder.append("   ");
    _builder.append("System.out.println(buffer.toString());\t\t");
    _builder.newLine();
    _builder.append("   ");
    _builder.append("try {");
    _builder.newLine();
    _builder.append("   \t  ");
    _builder.append("PreparedStatement _statement = _connection.prepareStatement(buffer.toString());");
    _builder.newLine();
    _builder.append("   \t  ");
    String _generateSetMethodsForCreateStatement = this.generateSetMethodsForCreateStatement(createStatement);
    _builder.append(_generateSetMethodsForCreateStatement, "   \t  ");
    _builder.newLineIfNotEmpty();
    _builder.append("   \t  ");
    _builder.append("_statement.execute();");
    _builder.newLine();
    _builder.append("   \t  ");
    _builder.append("_statement.close();");
    _builder.newLine();
    _builder.append("   ");
    _builder.append("} catch (SQLException e) {");
    _builder.newLine();
    _builder.append("     ");
    _builder.append("if(e.getSQLState() == \"23000\") {");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("return false;");
    _builder.newLine();
    _builder.append("     ");
    _builder.append("}");
    _builder.newLine();
    _builder.append("     ");
    _builder.append("throw new DMWRuntimeException(e);");
    _builder.newLine();
    _builder.append("   ");
    _builder.append("}");
    _builder.newLine();
    _builder.append("   ");
    _builder.append("return true;");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder.toString();
  }
  
  private String generateSetMethodsForCreateStatement(final YCreateStatement createStatement) {
    final StringBuffer buffer = new StringBuffer();
    int index = 1;
    EList<YMember> _members = createStatement.getStruct().getStructproperty().getType().getMembers();
    for (final YMember member : _members) {
      {
        String _generaterSetMethodName = this.generaterSetMethodName(member);
        String _plus = ("_statement." + _generaterSetMethodName);
        buffer.append(_plus);
        buffer.append("(");
        buffer.append(index);
        buffer.append(", ");
        String _name = createStatement.getStruct().getStructproperty().getName();
        String _plus_1 = (_name + ".");
        String _name_1 = member.getName();
        String _plus_2 = (_plus_1 + _name_1);
        buffer.append(_plus_2);
        buffer.append(");\n");
        index++;
      }
    }
    return buffer.toString();
  }
  
  private String generateSetMethodsForUpdateStatement(final YUpdateStatement updateStatement) {
    final StringBuffer buffer = new StringBuffer();
    int index = 1;
    EList<YMember> _members = updateStatement.getStruct().getStructproperty().getType().getMembers();
    for (final YMember member : _members) {
      {
        String _generaterUpdateMethodName = this.generaterUpdateMethodName(member);
        String _plus = ("_rs." + _generaterUpdateMethodName);
        buffer.append(_plus);
        buffer.append("(");
        buffer.append(index);
        buffer.append(", ");
        String _name = updateStatement.getStruct().getStructproperty().getName();
        String _plus_1 = (_name + ".");
        String _name_1 = member.getName();
        String _plus_2 = (_plus_1 + _name_1);
        buffer.append(_plus_2);
        buffer.append(");\n");
        index++;
      }
    }
    return buffer.toString();
  }
  
  private String generaterUpdateMethodName(final YMember member) {
    String _name = member.getType().getName();
    if (_name != null) {
      switch (_name) {
        case "Int":
          return "updateInt";
        case "Short":
          return "updateShort";
        case "String":
          return "updateString";
        case "Double":
          return "updateDouble";
        default:
          return "not yet done";
      }
    } else {
      return "not yet done";
    }
  }
  
  private String generaterSetMethodName(final YMember member) {
    String _name = member.getType().getName();
    if (_name != null) {
      switch (_name) {
        case "Int":
          return "setInt";
        case "Short":
          return "setShort";
        case "String":
          return "setString";
        case "Double":
          return "setDouble";
        default:
          return "not yet done";
      }
    } else {
      return "not yet done";
    }
  }
  
  private String generateArrays(final YClass clazz, final String widgetName) {
    StringConcatenation _builder = new StringConcatenation();
    {
      ArrayList<YProperty> _findPropertiesOfTypeArray = this._langJavaGeneratorHelper.findPropertiesOfTypeArray(clazz);
      for(final YProperty property : _findPropertiesOfTypeArray) {
        String _registerImport = this.registerImport("eu.jgen.notes.dmw.lite.runtimes.XArray");
        _builder.append(_registerImport);
        _builder.newLineIfNotEmpty();
        String _registerImport_1 = this.registerImport("java.util.SortedMap");
        _builder.append(_registerImport_1);
        _builder.newLineIfNotEmpty();
        String _registerImport_2 = this.registerImport("java.util.concurrent.ConcurrentSkipListMap");
        _builder.append(_registerImport_2);
        _builder.newLineIfNotEmpty();
        _builder.append("class ");
        String _firstUpper = StringExtensions.toFirstUpper(property.getName());
        _builder.append(_firstUpper);
        _builder.append(" extends XArray {");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("public ");
        String _firstUpper_1 = StringExtensions.toFirstUpper(property.getName());
        _builder.append(_firstUpper_1, "\t");
        _builder.append("(int max) {");
        _builder.newLineIfNotEmpty();
        _builder.append("\t\t");
        _builder.append("super(max);");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("}");
        _builder.newLine();
        _builder.append("\t");
        _builder.newLine();
        {
          EList<YProperty> _includes = property.getTuples().getIncludes();
          for(final YProperty ref : _includes) {
            _builder.append("\t");
            _builder.newLine();
            _builder.append("\t");
            _builder.append("SortedMap<Integer, ");
            String _firstUpper_2 = StringExtensions.toFirstUpper(ref.getType().getName());
            _builder.append(_firstUpper_2, "\t");
            _builder.append("> map");
            String _firstUpper_3 = StringExtensions.toFirstUpper(ref.getType().getName());
            _builder.append(_firstUpper_3, "\t");
            _builder.append(" = new ConcurrentSkipListMap<Integer, ");
            String _firstUpper_4 = StringExtensions.toFirstUpper(ref.getType().getName());
            _builder.append(_firstUpper_4, "\t");
            _builder.append(">();");
            _builder.newLineIfNotEmpty();
            _builder.append("\t");
            _builder.newLine();
            _builder.append("\t");
            _builder.append("public ");
            String _firstUpper_5 = StringExtensions.toFirstUpper(ref.getType().getName());
            _builder.append(_firstUpper_5, "\t");
            _builder.append(" get");
            String _firstUpper_6 = StringExtensions.toFirstUpper(ref.getType().getName());
            _builder.append(_firstUpper_6, "\t");
            _builder.append("() {");
            _builder.newLineIfNotEmpty();
            _builder.append("\t");
            _builder.append("\t");
            _builder.append("if(super.getSubscript() == 0) {");
            _builder.newLine();
            _builder.append("\t");
            _builder.append("\t\t");
            _builder.append("return null;");
            _builder.newLine();
            _builder.append("\t");
            _builder.append("\t");
            _builder.append("}");
            _builder.newLine();
            _builder.append("\t");
            _builder.append("\t");
            _builder.append("if (map");
            String _firstUpper_7 = StringExtensions.toFirstUpper(ref.getType().getName());
            _builder.append(_firstUpper_7, "\t\t");
            _builder.append(".containsKey(super.getSubscript())) {");
            _builder.newLineIfNotEmpty();
            _builder.append("\t");
            _builder.append("\t\t");
            _builder.append("return map");
            String _firstUpper_8 = StringExtensions.toFirstUpper(ref.getType().getName());
            _builder.append(_firstUpper_8, "\t\t\t");
            _builder.append(".get(super.getSubscript());");
            _builder.newLineIfNotEmpty();
            _builder.append("\t");
            _builder.append("\t");
            _builder.append("} else {");
            _builder.newLine();
            _builder.append("\t");
            _builder.append("\t\t");
            String _firstUpper_9 = StringExtensions.toFirstUpper(ref.getType().getName());
            _builder.append(_firstUpper_9, "\t\t\t");
            _builder.append(" ");
            String _name = ref.getName();
            _builder.append(_name, "\t\t\t");
            _builder.append(" = new ");
            String _firstUpper_10 = StringExtensions.toFirstUpper(ref.getType().getName());
            _builder.append(_firstUpper_10, "\t\t\t");
            _builder.append("();");
            _builder.newLineIfNotEmpty();
            _builder.append("\t");
            _builder.append("\t\t");
            _builder.append("map");
            String _firstUpper_11 = StringExtensions.toFirstUpper(ref.getType().getName());
            _builder.append(_firstUpper_11, "\t\t\t");
            _builder.append(".put(super.getSubscript(), ");
            String _name_1 = ref.getName();
            _builder.append(_name_1, "\t\t\t");
            _builder.append(");");
            _builder.newLineIfNotEmpty();
            _builder.append("\t");
            _builder.append("\t\t");
            _builder.append("return ");
            String _name_2 = ref.getName();
            _builder.append(_name_2, "\t\t\t");
            _builder.append(";");
            _builder.newLineIfNotEmpty();
            _builder.append("\t");
            _builder.append("\t");
            _builder.append("}");
            _builder.newLine();
            _builder.append("\t");
            _builder.append("}");
            _builder.newLine();
            _builder.append("\t");
            _builder.newLine();
          }
        }
        _builder.append("}");
        _builder.newLine();
        _builder.newLine();
        String _firstUpper_12 = StringExtensions.toFirstUpper(property.getName());
        _builder.append(_firstUpper_12);
        _builder.append(" ");
        String _name_3 = property.getName();
        _builder.append(_name_3);
        _builder.append(" = new ");
        String _firstUpper_13 = StringExtensions.toFirstUpper(property.getName());
        _builder.append(_firstUpper_13);
        _builder.append("(");
        int _arraySize = this._langJavaGeneratorHelper.getArraySize(property);
        _builder.append(_arraySize);
        _builder.append(");");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.newLine();
    return _builder.toString();
  }
  
  private String generateFunctions(final YClass clazz) {
    StringConcatenation _builder = new StringConcatenation();
    {
      EList<YMember> _members = clazz.getMembers();
      for(final YMember member : _members) {
        {
          if ((member instanceof YFunction)) {
            String _generateFunctionForWidget = this.generateFunctionForWidget(((YFunction) member));
            _builder.append(_generateFunctionForWidget);
            _builder.newLineIfNotEmpty();
          }
        }
      }
    }
    return _builder.toString();
  }
  
  private String generateFunctionForWidget(final YFunction function) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.newLine();
    _builder.append("   ");
    String _documentation = this._langJavaGeneratorHelper.getDocumentation(function);
    _builder.append(_documentation, "   ");
    _builder.append("  ");
    _builder.newLineIfNotEmpty();
    YAccessLevel _access = function.getAccess();
    _builder.append(_access);
    _builder.append(" ");
    {
      YClass _type = function.getType();
      boolean _tripleNotEquals = (_type != null);
      if (_tripleNotEquals) {
        String _translateTypeName = this._langJavaGeneratorHelper.translateTypeName(function.getType().getName());
        _builder.append(_translateTypeName);
      }
    }
    _builder.append(" ");
    {
      YClass _type_1 = function.getType();
      boolean _tripleEquals = (_type_1 == null);
      if (_tripleEquals) {
        _builder.append("void ");
      }
    }
    String _name = function.getName();
    _builder.append(_name);
    String _generateFunctionParameters = this.generateFunctionParameters(function);
    _builder.append(_generateFunctionParameters);
    _builder.newLineIfNotEmpty();
    _builder.append("   ");
    String _generateFunctionBody = this.generateFunctionBody(function);
    _builder.append(_generateFunctionBody, "   ");
    _builder.newLineIfNotEmpty();
    _builder.append("}");
    _builder.newLine();
    return _builder.toString();
  }
  
  private String generateFunctionParameters(final YFunction function) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("(");
    {
      EList<YParameter> _params = function.getParams();
      boolean _hasElements = false;
      for(final YParameter param : _params) {
        if (!_hasElements) {
          _hasElements = true;
        } else {
          _builder.appendImmediate(", ", "");
        }
        String _translateTypeName = this._langJavaGeneratorHelper.translateTypeName(param.getType().getName());
        _builder.append(_translateTypeName);
        _builder.append(" ");
        String _name = param.getName();
        _builder.append(_name);
      }
    }
    _builder.append(") {");
    _builder.newLineIfNotEmpty();
    return _builder.toString();
  }
  
  private String generateFunctionBody(final YFunction function) {
    String _xifexpression = null;
    YBlock _body = function.getBody();
    boolean _tripleNotEquals = (_body != null);
    if (_tripleNotEquals) {
      String _xblockexpression = null;
      {
        final YBlock block = function.getBody();
        StringConcatenation _builder = new StringConcatenation();
        String _generateBlock = this.generateBlock(block);
        _builder.append(_generateBlock);
        _builder.append(" ");
        _xblockexpression = _builder.toString();
      }
      _xifexpression = _xblockexpression;
    }
    return _xifexpression;
  }
  
  /**
   * Generate block of statements
   */
  private String generateBlock(final YBlock block) {
    StringConcatenation _builder = new StringConcatenation();
    {
      EList<YStatement> _statements = block.getStatements();
      for(final YStatement statement : _statements) {
        String _selectStatementGeneration = this.selectStatementGeneration(statement);
        _builder.append(_selectStatementGeneration);
        _builder.newLineIfNotEmpty();
      }
    }
    final String body = _builder.toString();
    return body;
  }
  
  /**
   * Select type of the statement and generate code.
   */
  private String selectStatementGeneration(final YStatement statement) {
    boolean _matched = false;
    if ((statement instanceof YDeleteStatement)) {
      _matched=true;
      return this.generateStatementDelete(((YDeleteStatement) statement));
    }
    if (!_matched) {
      if ((statement instanceof YUpdateStatement)) {
        _matched=true;
        return this.generateStatementUpdate(((YUpdateStatement) statement));
      }
    }
    if (!_matched) {
      if ((statement instanceof YReadEachStatement)) {
        _matched=true;
        return this.generateStatementReadEach(((YReadEachStatement) statement));
      }
    }
    if (!_matched) {
      if ((statement instanceof YForInStatement)) {
        _matched=true;
        return this.generateStatementForIn(((YForInStatement) statement));
      }
    }
    if (!_matched) {
      if ((statement instanceof YCreateStatement)) {
        _matched=true;
        return this.generateStatementCreate(((YCreateStatement) statement));
      }
    }
    if (!_matched) {
      if ((statement instanceof YIfStatement)) {
        _matched=true;
        return this.generateStatementIf(((YIfStatement) statement));
      }
    }
    if (!_matched) {
      if ((statement instanceof YReturn)) {
        _matched=true;
        return this.generateStatementReturn(((YReturn) statement));
      }
    }
    if (!_matched) {
      if ((statement instanceof YWhileStatement)) {
        _matched=true;
        return this.generateStatementWhile(((YWhileStatement) statement));
      }
    }
    if (!_matched) {
      if ((statement instanceof YSwitchStatement)) {
        _matched=true;
        return this.generateStatementSwitch(((YSwitchStatement) statement));
      }
    }
    if (!_matched) {
      if ((statement instanceof YReadStatement)) {
        _matched=true;
        return this.generateStatementRead(((YReadStatement) statement));
      }
    }
    if (!_matched) {
      if ((statement instanceof YRepeatWhileStatement)) {
        _matched=true;
        return this.generateStatementRead(((YRepeatWhileStatement) statement));
      }
    }
    if (!_matched) {
      if ((statement instanceof YVariableDeclaration)) {
        _matched=true;
        return this.generateStatementVariableDeclaration(((YVariableDeclaration) statement));
      }
    }
    if (!_matched) {
      if ((statement instanceof YAssignment)) {
        _matched=true;
        return this.generateStatementYAssignment(((YAssignment) statement));
      }
    }
    if (!_matched) {
      if ((statement instanceof YMemberSelection)) {
        _matched=true;
        return this.generateStatementYAssignment(((YMemberSelection) statement));
      }
    }
    return ("//TODO - This statement is not implemented yet: " + statement);
  }
  
  private String generateStatementYAssignment(final YMemberSelection memberSelection) {
    StringConcatenation _builder = new StringConcatenation();
    String _documentation = this._langJavaGeneratorHelper.getDocumentation(memberSelection);
    _builder.append(_documentation);
    _builder.newLineIfNotEmpty();
    String _generateSpecialFunctions = this.generateSpecialFunctions(memberSelection);
    _builder.append(_generateSpecialFunctions);
    _builder.newLineIfNotEmpty();
    return _builder.toString();
  }
  
  private String generateStatementYAssignment(final YAssignment assignment) {
    StringConcatenation _builder = new StringConcatenation();
    String _documentation = this._langJavaGeneratorHelper.getDocumentation(assignment);
    _builder.append(_documentation);
    _builder.newLineIfNotEmpty();
    String _generateAssigment = this.generateAssigment(assignment);
    _builder.append(_generateAssigment);
    _builder.newLineIfNotEmpty();
    return _builder.toString();
  }
  
  private String generateStatementVariableDeclaration(final YVariableDeclaration variableDeclaration) {
    StringConcatenation _builder = new StringConcatenation();
    String _documentation = this._langJavaGeneratorHelper.getDocumentation(variableDeclaration);
    _builder.append(_documentation);
    _builder.newLineIfNotEmpty();
    String _generateVariableDeclaration = this.generateVariableDeclaration(variableDeclaration);
    _builder.append(_generateVariableDeclaration);
    _builder.newLineIfNotEmpty();
    return _builder.toString();
  }
  
  private String generateStatementRead(final YRepeatWhileStatement repeatWhileStatement) {
    StringConcatenation _builder = new StringConcatenation();
    String _documentation = this._langJavaGeneratorHelper.getDocumentation(repeatWhileStatement);
    _builder.append(_documentation);
    _builder.append("  ");
    _builder.newLineIfNotEmpty();
    _builder.append("do {");
    _builder.newLine();
    _builder.append("\t   ");
    String _generateBlock = this.generateBlock(repeatWhileStatement.getBody());
    _builder.append(_generateBlock, "\t   ");
    _builder.newLineIfNotEmpty();
    _builder.append("} while (");
    String _generateExpression = this.generateExpression(repeatWhileStatement.getExpression());
    _builder.append(_generateExpression);
    _builder.append(");\t\t");
    _builder.newLineIfNotEmpty();
    return _builder.toString();
  }
  
  private String generateStatementRead(final YReadStatement readStatement) {
    String _read = this._langJavaGeneratorHelper.generateLocalName("_read");
    this.innerFunctions.add(this.generateStatementReadInnerFunction(readStatement, _read));
    StringConcatenation _builder = new StringConcatenation();
    String _documentation = this._langJavaGeneratorHelper.getDocumentation(readStatement);
    _builder.append(_documentation);
    _builder.newLineIfNotEmpty();
    _builder.append("if(!");
    _builder.append(_read);
    _builder.append("()) {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("// entity type not found when executing create statement");
    _builder.newLine();
    _builder.append("\t");
    String _generateBlock = this.generateBlock(readStatement.getNotfound());
    _builder.append(_generateBlock, "\t");
    _builder.newLineIfNotEmpty();
    _builder.append("}\t\t\t\t  ");
    _builder.newLine();
    return _builder.toString();
  }
  
  private String generateStatementWhile(final YWhileStatement whileStatement) {
    StringConcatenation _builder = new StringConcatenation();
    String _documentation = this._langJavaGeneratorHelper.getDocumentation(whileStatement);
    _builder.append(_documentation);
    _builder.append("  ");
    _builder.newLineIfNotEmpty();
    _builder.append("while (");
    String _generateExpression = this.generateExpression(whileStatement.getExpression());
    _builder.append(_generateExpression);
    _builder.append(") {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t   ");
    String _generateBlock = this.generateBlock(whileStatement.getBody());
    _builder.append(_generateBlock, "\t   ");
    _builder.newLineIfNotEmpty();
    _builder.append("}\t\t\t");
    _builder.newLine();
    return _builder.toString();
  }
  
  private String generateStatementReturn(final YReturn returnStatement) {
    YExpression _expression = returnStatement.getExpression();
    boolean _tripleEquals = (_expression == null);
    if (_tripleEquals) {
      StringConcatenation _builder = new StringConcatenation();
      String _documentation = this._langJavaGeneratorHelper.getDocumentation(returnStatement);
      _builder.append(_documentation);
      _builder.newLineIfNotEmpty();
      _builder.append("return;");
      _builder.newLine();
      return _builder.toString();
    } else {
      StringConcatenation _builder_1 = new StringConcatenation();
      String _documentation_1 = this._langJavaGeneratorHelper.getDocumentation(returnStatement);
      _builder_1.append(_documentation_1);
      _builder_1.newLineIfNotEmpty();
      _builder_1.append("return ");
      String _generateExpression = this.generateExpression(returnStatement.getExpression());
      _builder_1.append(_generateExpression);
      _builder_1.append(";");
      _builder_1.newLineIfNotEmpty();
      return _builder_1.toString();
    }
  }
  
  private String generateStatementIf(final YIfStatement ifStatement) {
    StringConcatenation _builder = new StringConcatenation();
    String _documentation = this._langJavaGeneratorHelper.getDocumentation(ifStatement);
    _builder.append(_documentation);
    _builder.append("  ");
    _builder.newLineIfNotEmpty();
    _builder.append("if (");
    String _generateExpression = this.generateExpression(ifStatement.getExpression());
    _builder.append(_generateExpression);
    _builder.append(") {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    String _generateBlock = this.generateBlock(ifStatement.getThenBlock());
    _builder.append(_generateBlock, "\t");
    _builder.append(" ");
    _builder.newLineIfNotEmpty();
    _builder.append("} ");
    {
      YBlock _elseBlock = ifStatement.getElseBlock();
      boolean _tripleNotEquals = (_elseBlock != null);
      if (_tripleNotEquals) {
        _builder.append(" else {");
        _builder.newLineIfNotEmpty();
        _builder.append("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
        String _generateBlock_1 = this.generateBlock(ifStatement.getElseBlock());
        _builder.append(_generateBlock_1, "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
        _builder.newLineIfNotEmpty();
        _builder.append("}");
      }
    }
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    return _builder.toString();
  }
  
  private String generateStatementDelete(final YDeleteStatement deleteStatement) {
    String _delete = this._langJavaGeneratorHelper.generateLocalName("_delete");
    this.innerFunctions.add(this.generateStatementDelete(deleteStatement, _delete));
    StringConcatenation _builder = new StringConcatenation();
    String _documentation = this._langJavaGeneratorHelper.getDocumentation(deleteStatement);
    _builder.append(_documentation);
    _builder.newLineIfNotEmpty();
    _builder.append("               ");
    _builder.append(_delete, "               ");
    _builder.append("(_rs);");
    _builder.newLineIfNotEmpty();
    return _builder.toString();
  }
  
  private String generateStatementUpdate(final YUpdateStatement updateStatement) {
    String _update = this._langJavaGeneratorHelper.generateLocalName("_update");
    this.innerFunctions.add(this.generateStatementUpdateInnerFunction(updateStatement, _update));
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("   ");
    String _generateBlock = this.generateBlock(updateStatement.getSetBlock());
    _builder.append(_generateBlock, "   ");
    _builder.newLineIfNotEmpty();
    String _documentation = this._langJavaGeneratorHelper.getDocumentation(updateStatement);
    _builder.append(_documentation);
    _builder.newLineIfNotEmpty();
    _builder.append("               ");
    _builder.append(_update, "               ");
    _builder.append("(_rs);");
    _builder.newLineIfNotEmpty();
    return _builder.toString();
  }
  
  private String generateStatementReadEach(final YReadEachStatement readEachStatement) {
    String _readEach = this._langJavaGeneratorHelper.generateLocalName("_read");
    this.innerFunctions.add(this.generateStatementReadEachInnerFunction(readEachStatement, _readEach));
    StringConcatenation _builder = new StringConcatenation();
    String _documentation = this._langJavaGeneratorHelper.getDocumentation(readEachStatement);
    _builder.append(_documentation);
    _builder.newLineIfNotEmpty();
    _builder.append(_readEach);
    _builder.append("();\t\t\t\t  ");
    _builder.newLineIfNotEmpty();
    return _builder.toString();
  }
  
  private String generateStatementCreate(final YCreateStatement createStatement) {
    String _create = this._langJavaGeneratorHelper.generateLocalName("_create");
    this.innerFunctions.add(this.generateStatementCreateInnerFunction(createStatement, _create));
    StringConcatenation _builder = new StringConcatenation();
    String _documentation = this._langJavaGeneratorHelper.getDocumentation(createStatement);
    _builder.append(_documentation);
    _builder.newLineIfNotEmpty();
    String _generateBlock = this.generateBlock(createStatement.getSetBlock());
    _builder.append(_generateBlock);
    _builder.newLineIfNotEmpty();
    _builder.append("if(_create()) {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("// execution of create statement completed successfully");
    _builder.newLine();
    _builder.append("\t");
    String _generateBlock_1 = this.generateBlock(createStatement.getSuccess());
    _builder.append(_generateBlock_1, "\t");
    _builder.newLineIfNotEmpty();
    _builder.append("} else {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("// duplicate entity type detected during when executing create statement");
    _builder.newLine();
    _builder.append("\t");
    String _generateBlock_2 = this.generateBlock(createStatement.getAlreadyExist());
    _builder.append(_generateBlock_2, "\t");
    _builder.newLineIfNotEmpty();
    _builder.append("}\t\t\t\t  ");
    _builder.newLine();
    return _builder.toString();
  }
  
  private String generateStatementForIn(final YForInStatement forInStatement) {
    String _index = this._langJavaGeneratorHelper.generateLocalName("_index");
    StringConcatenation _builder = new StringConcatenation();
    String _documentation = this._langJavaGeneratorHelper.getDocumentation(forInStatement);
    _builder.append(_documentation);
    _builder.append("  ");
    _builder.newLineIfNotEmpty();
    _builder.append("int ");
    _builder.append(_index);
    _builder.append(" = 1;");
    _builder.newLineIfNotEmpty();
    _builder.append("for (");
    _builder.append(_index);
    _builder.append("=1; ");
    _builder.append(_index);
    _builder.append(" <= this.");
    String _name = forInStatement.getCollection().getName();
    _builder.append(_name);
    _builder.append(".getLast(); ");
    _builder.append(_index);
    _builder.append("++) {");
    _builder.newLineIfNotEmpty();
    String _name_1 = forInStatement.getCollection().getName();
    _builder.append(_name_1);
    _builder.append(".setSubscript(");
    _builder.append(_index);
    _builder.append(");");
    _builder.newLineIfNotEmpty();
    {
      EList<YProperty> _includes = forInStatement.getCollection().getTuples().getIncludes();
      for(final YProperty include : _includes) {
        _builder.append("this.");
        String _name_2 = include.getName();
        _builder.append(_name_2);
        _builder.append(" = ");
        String _name_3 = forInStatement.getCollection().getName();
        _builder.append(_name_3);
        _builder.append(".get");
        String _name_4 = include.getType().getName();
        _builder.append(_name_4);
        _builder.append("();");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("   ");
    String _generateBlock = this.generateBlock(forInStatement.getBody());
    _builder.append(_generateBlock, "   ");
    _builder.newLineIfNotEmpty();
    _builder.append("}\t\t");
    _builder.newLine();
    return _builder.toString();
  }
  
  private String generateStatementUpdateInnerFunction(final YUpdateStatement updateStatement, final String _update) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("private void ");
    _builder.append(_update);
    _builder.append("(ResultSet _rs) {");
    _builder.newLineIfNotEmpty();
    _builder.append("   ");
    _builder.append("try {");
    _builder.newLine();
    _builder.append("   \t");
    String _generateSetMethodsForUpdateStatement = this.generateSetMethodsForUpdateStatement(updateStatement);
    _builder.append(_generateSetMethodsForUpdateStatement, "   \t");
    _builder.newLineIfNotEmpty();
    _builder.append("   ");
    _builder.append("_rs.updateRow();");
    _builder.newLine();
    _builder.append("   ");
    _builder.append("} catch (SQLException e) {");
    _builder.newLine();
    _builder.append("   ");
    _builder.append("throw new DMWRuntimeException(e);");
    _builder.newLine();
    _builder.append("   ");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder.toString();
  }
  
  private String generateStatementDelete(final YDeleteStatement deleteStatement, final String _delete) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("private void ");
    _builder.append(_delete);
    _builder.append("(ResultSet _rs) {");
    _builder.newLineIfNotEmpty();
    _builder.append("   ");
    _builder.append("try {");
    _builder.newLine();
    _builder.append("   ");
    _builder.append("_rs.deleteRow();");
    _builder.newLine();
    _builder.append("   ");
    _builder.append("} catch (SQLException e) {");
    _builder.newLine();
    _builder.append("   ");
    _builder.append("throw new DMWRuntimeException(e);");
    _builder.newLine();
    _builder.append("   ");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder.toString();
  }
  
  private String generateStatementReadInnerFunction(final YReadStatement readStatement, final String _read) {
    readStatement.getStructs();
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("private boolean ");
    _builder.append(_read);
    _builder.append("() {");
    _builder.newLineIfNotEmpty();
    _builder.append("   ");
    _builder.append("StringBuffer buffer = new StringBuffer();");
    _builder.newLine();
    _builder.append("   ");
    _builder.append("buffer.append(\"SELECT \");");
    _builder.newLine();
    _builder.append("   ");
    _builder.append("buffer.append(\"");
    {
      ArrayList<String> _createQualifiedColumnNamesListForRead = this._langJavaGeneratorHelper.createQualifiedColumnNamesListForRead(readStatement);
      boolean _hasElements = false;
      for(final String qualifiedName : _createQualifiedColumnNamesListForRead) {
        if (!_hasElements) {
          _hasElements = true;
        } else {
          _builder.appendImmediate(",", "   ");
        }
        _builder.append(qualifiedName, "   ");
      }
    }
    _builder.append("\");\t\t   ");
    _builder.newLineIfNotEmpty();
    _builder.append("   ");
    _builder.append("buffer.append(\" FROM \");");
    _builder.newLine();
    _builder.append("   ");
    _builder.append("buffer.append(\"");
    {
      ArrayList<String> _generateFROMClause = this.generateFROMClause(readStatement);
      boolean _hasElements_1 = false;
      for(final String qualifiedName_1 : _generateFROMClause) {
        if (!_hasElements_1) {
          _hasElements_1 = true;
        } else {
          _builder.appendImmediate(",", "   ");
        }
        _builder.append(qualifiedName_1, "   ");
      }
    }
    _builder.append("\");\t\t   ");
    _builder.newLineIfNotEmpty();
    _builder.append("   ");
    _builder.append("buffer.append(\" WHERE \");");
    _builder.newLine();
    _builder.append("   ");
    _builder.append("buffer.append(\"");
    String _generateJoinExpressionForRead = this.generateJoinExpressionForRead(readStatement);
    _builder.append(_generateJoinExpressionForRead, "   ");
    _builder.append("\");");
    _builder.newLineIfNotEmpty();
    _builder.append("   ");
    _builder.append("buffer.append(\"");
    String _generateJDBCExpression = this.generateJDBCExpression(readStatement, readStatement.getWhereclause().getExpression());
    _builder.append(_generateJDBCExpression, "   ");
    _builder.append("\");");
    _builder.newLineIfNotEmpty();
    _builder.append("   ");
    _builder.append("buffer.append(\" FOR UPDATE \");");
    _builder.newLine();
    _builder.append("   ");
    _builder.append("System.out.println(buffer.toString());\t\t");
    _builder.newLine();
    _builder.append("   ");
    _builder.append("try {");
    _builder.newLine();
    _builder.append("   \t  ");
    _builder.append("PreparedStatement _statement = _connection.prepareStatement(buffer.toString(), ResultSet.CONCUR_UPDATABLE,");
    _builder.newLine();
    _builder.append("   \t  \t\t\t\t\t");
    _builder.append("ResultSet.CLOSE_CURSORS_AT_COMMIT);");
    _builder.newLine();
    _builder.append("   \t  ");
    _builder.append("_statement.setCursorName(\"viewF\");");
    _builder.newLine();
    _builder.append("   \t  ");
    String _generateSetMethodsForRead = this.generateSetMethodsForRead(readStatement);
    _builder.append(_generateSetMethodsForRead, "   \t  ");
    _builder.newLineIfNotEmpty();
    _builder.append("   \t  ");
    _builder.append("_statement.execute();");
    _builder.newLine();
    _builder.append("   \t  ");
    _builder.append("ResultSet _rs = _statement.getResultSet();");
    _builder.newLine();
    _builder.append("   \t  ");
    _builder.append("_rs.next();");
    _builder.newLine();
    _builder.append("   \t  ");
    String _generateGetMethodsForRead = this.generateGetMethodsForRead(readStatement);
    _builder.append(_generateGetMethodsForRead, "   \t  ");
    _builder.newLineIfNotEmpty();
    _builder.append("   \t  ");
    _builder.append("// when sucessfull");
    _builder.newLine();
    _builder.append("   \t  ");
    String _generateBlock = this.generateBlock(readStatement.getSuccess());
    _builder.append(_generateBlock, "   \t  ");
    _builder.append(" ");
    _builder.newLineIfNotEmpty();
    _builder.append("   \t  ");
    _builder.append("_rs.close();");
    _builder.newLine();
    _builder.append("   \t  ");
    _builder.append("_statement.close();");
    _builder.newLine();
    _builder.append("   \t  ");
    _builder.newLine();
    _builder.append("   ");
    _builder.append("} catch (SQLException e) {");
    _builder.newLine();
    _builder.append("     ");
    _builder.append("if(e.getSQLState() == \"23000\") {");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("return false;");
    _builder.newLine();
    _builder.append("     ");
    _builder.append("}");
    _builder.newLine();
    _builder.append("     ");
    _builder.append("throw new DMWRuntimeException(e);");
    _builder.newLine();
    _builder.append("   ");
    _builder.append("}");
    _builder.newLine();
    _builder.append("   ");
    _builder.append("return true;");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder.toString();
  }
  
  private String generateStatementReadEachInnerFunction(final YReadEachStatement readEachStatement, final String readEach) {
    readEachStatement.getStructs();
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("private boolean ");
    _builder.append(readEach);
    _builder.append("() {");
    _builder.newLineIfNotEmpty();
    _builder.append("   ");
    _builder.append("StringBuffer buffer = new StringBuffer();");
    _builder.newLine();
    _builder.append("   ");
    _builder.append("buffer.append(\"SELECT \");");
    _builder.newLine();
    _builder.append("   ");
    _builder.append("buffer.append(\"");
    {
      ArrayList<String> _createQualifiedColumnNamesListForReadEach = this._langJavaGeneratorHelper.createQualifiedColumnNamesListForReadEach(readEachStatement);
      boolean _hasElements = false;
      for(final String qualifiedName : _createQualifiedColumnNamesListForReadEach) {
        if (!_hasElements) {
          _hasElements = true;
        } else {
          _builder.appendImmediate(",", "   ");
        }
        _builder.append(qualifiedName, "   ");
      }
    }
    _builder.append("\");\t\t   ");
    _builder.newLineIfNotEmpty();
    _builder.append("   ");
    _builder.append("buffer.append(\" FROM \");");
    _builder.newLine();
    _builder.append("   ");
    _builder.append("buffer.append(\"");
    {
      ArrayList<String> _generateFROMClause = this.generateFROMClause(readEachStatement);
      boolean _hasElements_1 = false;
      for(final String qualifiedName_1 : _generateFROMClause) {
        if (!_hasElements_1) {
          _hasElements_1 = true;
        } else {
          _builder.appendImmediate(",", "   ");
        }
        _builder.append(qualifiedName_1, "   ");
      }
    }
    _builder.append("\");\t\t   ");
    _builder.newLineIfNotEmpty();
    _builder.append("   ");
    _builder.append("buffer.append(\" WHERE \");");
    _builder.newLine();
    _builder.append("   ");
    _builder.append("buffer.append(\"");
    String _generateJoinExpressionForReadEach = this.generateJoinExpressionForReadEach(readEachStatement);
    _builder.append(_generateJoinExpressionForReadEach, "   ");
    _builder.append("\");");
    _builder.newLineIfNotEmpty();
    _builder.append("   ");
    _builder.append("buffer.append(\"");
    String _generateJDBCExpression = this.generateJDBCExpression(readEachStatement, readEachStatement.getWhereclause().getExpression());
    _builder.append(_generateJDBCExpression, "   ");
    _builder.append("\");");
    _builder.newLineIfNotEmpty();
    _builder.append("   ");
    _builder.append("buffer.append(\" FOR UPDATE \");");
    _builder.newLine();
    _builder.append("   ");
    _builder.append("System.out.println(buffer.toString());\t\t");
    _builder.newLine();
    _builder.append("   ");
    _builder.append("try {");
    _builder.newLine();
    _builder.append("   \t  ");
    _builder.append("PreparedStatement _statement = _connection.prepareStatement(buffer.toString(), ResultSet.CONCUR_UPDATABLE,");
    _builder.newLine();
    _builder.append("   \t  \t\t\t\t\t");
    _builder.append("ResultSet.CLOSE_CURSORS_AT_COMMIT);");
    _builder.newLine();
    _builder.append("   \t  ");
    _builder.append("_statement.setCursorName(\"viewF\");");
    _builder.newLine();
    _builder.append("   \t  ");
    String _generateSetMethodsForReadEach = this.generateSetMethodsForReadEach(readEachStatement);
    _builder.append(_generateSetMethodsForReadEach, "   \t  ");
    _builder.newLineIfNotEmpty();
    _builder.append("   \t  ");
    _builder.append("_statement.execute();");
    _builder.newLine();
    _builder.append("   \t  ");
    _builder.append("ResultSet _rs = _statement.getResultSet();");
    _builder.newLine();
    _builder.append("   \t  ");
    _builder.append("while (_rs.next()) {");
    _builder.newLine();
    _builder.append("   \t  \t  ");
    String _generateGetMethodsForReadEach = this.generateGetMethodsForReadEach(readEachStatement);
    _builder.append(_generateGetMethodsForReadEach, "   \t  \t  ");
    _builder.newLineIfNotEmpty();
    _builder.append("   \t  \t  ");
    String _generateBlock = this.generateBlock(readEachStatement.getSuccess());
    _builder.append(_generateBlock, "   \t  \t  ");
    _builder.append(" ");
    _builder.newLineIfNotEmpty();
    _builder.append("   \t  ");
    _builder.append("}");
    _builder.newLine();
    _builder.append("   \t  ");
    _builder.append("_rs.close();");
    _builder.newLine();
    _builder.append("   \t  ");
    _builder.append("_statement.close();");
    _builder.newLine();
    _builder.append("   \t  ");
    _builder.newLine();
    _builder.append("   ");
    _builder.append("} catch (SQLException e) {");
    _builder.newLine();
    _builder.append("     ");
    _builder.append("if(e.getSQLState() == \"23000\") {");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("return false;");
    _builder.newLine();
    _builder.append("     ");
    _builder.append("}");
    _builder.newLine();
    _builder.append("     ");
    _builder.append("throw new DMWRuntimeException(e);");
    _builder.newLine();
    _builder.append("   ");
    _builder.append("}");
    _builder.newLine();
    _builder.append("   ");
    _builder.append("return true;");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder.toString();
  }
  
  private String generateGetMethodsForRead(final YReadStatement readStatement) {
    final StringBuffer buffer = new StringBuffer();
    int index = 1;
    EList<YStructRefPair> _structs = readStatement.getStructs();
    for (final YStructRefPair struct : _structs) {
      EList<YMember> _members = struct.getStructproperty().getType().getMembers();
      for (final YMember member : _members) {
        {
          String setMethodName = "";
          String _name = member.getType().getName();
          if (_name != null) {
            switch (_name) {
              case "Int":
                setMethodName = "getInt";
                break;
              case "Short":
                setMethodName = "getShort";
                break;
              case "String":
                setMethodName = "getString";
                break;
              default:
                setMethodName = "unknown";
                break;
            }
          } else {
            setMethodName = "unknown";
          }
          String _name_1 = struct.getStructproperty().getName();
          String _plus = (_name_1 + ".");
          String _name_2 = member.getName();
          String _plus_1 = (_plus + _name_2);
          String _plus_2 = (_plus_1 + " = _rs.");
          String _plus_3 = (_plus_2 + setMethodName);
          String _plus_4 = (_plus_3 + "(");
          String _string = Integer.toString(index);
          String _plus_5 = (_plus_4 + _string);
          String _plus_6 = (_plus_5 + ");\n");
          buffer.append(_plus_6);
          index++;
        }
      }
    }
    return buffer.toString();
  }
  
  private String generateGetMethodsForReadEach(final YReadEachStatement readEachStatement) {
    final StringBuffer buffer = new StringBuffer();
    int index = 1;
    EList<YStructRefPair> _structs = readEachStatement.getStructs();
    for (final YStructRefPair struct : _structs) {
      EList<YMember> _members = struct.getStructproperty().getType().getMembers();
      for (final YMember member : _members) {
        {
          String setMethodName = "";
          String _name = member.getType().getName();
          if (_name != null) {
            switch (_name) {
              case "Int":
                setMethodName = "getInt";
                break;
              case "Short":
                setMethodName = "getShort";
                break;
              case "String":
                setMethodName = "getString";
                break;
              default:
                setMethodName = "unknown";
                break;
            }
          } else {
            setMethodName = "unknown";
          }
          String _name_1 = struct.getStructproperty().getName();
          String _plus = (_name_1 + ".");
          String _name_2 = member.getName();
          String _plus_1 = (_plus + _name_2);
          String _plus_2 = (_plus_1 + " = _rs.");
          String _plus_3 = (_plus_2 + setMethodName);
          String _plus_4 = (_plus_3 + "(");
          String _string = Integer.toString(index);
          String _plus_5 = (_plus_4 + _string);
          String _plus_6 = (_plus_5 + ");\n");
          buffer.append(_plus_6);
          index++;
        }
      }
    }
    return buffer.toString();
  }
  
  private String generateSetMethodsForRead(final YReadStatement readStatement) {
    final StringBuffer buffer = new StringBuffer();
    int index = 1;
    final ArrayList<String> list = CollectionLiterals.<String>newArrayList();
    final ArrayList<String> proplist = CollectionLiterals.<String>newArrayList();
    this.getListOfPropertiesForRead(readStatement, proplist);
    proplist.add("viewF");
    final ArrayList<String> newlist = this._langJavaGeneratorHelper.createReadStatementSetMethodList(list, readStatement.getWhereclause().getExpression(), proplist);
    for (final String setMethod : newlist) {
      {
        String _replace = setMethod.replace("&index&", Integer.toString(index));
        String _plus = ("_statement." + _replace);
        String _plus_1 = (_plus + "\n");
        buffer.append(_plus_1);
        index++;
      }
    }
    return buffer.toString();
  }
  
  private String generateSetMethodsForReadEach(final YReadEachStatement readEachStatement) {
    final StringBuffer buffer = new StringBuffer();
    int index = 1;
    final ArrayList<String> list = CollectionLiterals.<String>newArrayList();
    final ArrayList<String> proplist = CollectionLiterals.<String>newArrayList();
    this.getListOfPropertiesForReadEach(readEachStatement, proplist);
    proplist.add("viewF");
    final ArrayList<String> newlist = this._langJavaGeneratorHelper.createReadStatementSetMethodList(list, readEachStatement.getWhereclause().getExpression(), proplist);
    for (final String setMethod : newlist) {
      {
        String _replace = setMethod.replace("&index&", Integer.toString(index));
        String _plus = ("_statement." + _replace);
        String _plus_1 = (_plus + "\n");
        buffer.append(_plus_1);
        index++;
      }
    }
    return buffer.toString();
  }
  
  private void getListOfPropertiesForRead(final YReadStatement readStatement, final ArrayList<String> readProperties) {
    EList<YStructRefPair> _structs = readStatement.getStructs();
    for (final YStructRefPair struct : _structs) {
      readProperties.add(struct.getStructproperty().getName());
    }
  }
  
  private void getListOfPropertiesForReadEach(final YReadEachStatement readEachStatement, final ArrayList<String> readProperties) {
    EList<YStructRefPair> _structs = readEachStatement.getStructs();
    for (final YStructRefPair struct : _structs) {
      readProperties.add(struct.getStructproperty().getName());
    }
  }
  
  private String generateJoinExpressionForRead(final YReadStatement statement) {
    YJoin _joinclause = statement.getJoinclause();
    boolean _tripleEquals = (_joinclause == null);
    if (_tripleEquals) {
      return "";
    }
    EList<YJoinDef> _joindefs = statement.getJoinclause().getJoindefs();
    for (final YJoinDef joinDef : _joindefs) {
      return this.generateJoinExpressionFragment(statement, joinDef);
    }
    return "something wrong";
  }
  
  private String generateJoinExpressionForReadEach(final YReadEachStatement readEachStatement) {
    YJoin _joinclause = readEachStatement.getJoinclause();
    boolean _tripleEquals = (_joinclause == null);
    if (_tripleEquals) {
      return "";
    }
    EList<YJoinDef> _joindefs = readEachStatement.getJoinclause().getJoindefs();
    for (final YJoinDef joinDef : _joindefs) {
      return this.generateJoinExpressionFragmentForReadEach(readEachStatement, joinDef);
    }
    return "something wrong";
  }
  
  private String generateJoinExpressionFragment(final YReadStatement readStatement, final YJoinDef joinDef) {
    final YAnnotTable fromTable = this._langUtil.getImplementingTable(joinDef.getFromView().getType().getEntity());
    final String fromPrefix = this.findTablePrefix(readStatement.getStructs(), joinDef.getFromView());
    final YAnnotTable toTable = this._langUtil.getImplementingTable(joinDef.getToView().getType().getEntity());
    final String toPrefix = this.findTablePrefix(readStatement.getStructs(), joinDef.getToView());
    final boolean parent = joinDef.getRelRef().isParent();
    if (parent) {
      StringBuffer buffer = new StringBuffer();
      String columnName = "";
      String cadidateColumnName = null;
      EList<YAnnotAbstractColumn> _columns = fromTable.getPrimarykey().getColumns();
      for (final YAnnotAbstractColumn abstractElement : _columns) {
        {
          columnName = this.getAttributeNameForAbstractColumn(abstractElement);
          EList<YAnnotForeignKey> _foreignkeys = toTable.getForeignkeys();
          for (final YAnnotForeignKey foreignKey : _foreignkeys) {
            String _name = joinDef.getRelRef().getInverse().getName();
            String _name_1 = foreignKey.getRelationship().getName();
            boolean _equals = Objects.equal(_name, _name_1);
            if (_equals) {
              EList<YAnnotAbstractColumn> _columns_1 = foreignKey.getColumns();
              for (final YAnnotAbstractColumn cadidateElement : _columns_1) {
                {
                  cadidateColumnName = this.getAttributeNameForAbstractColumn(cadidateElement);
                  boolean _equals_1 = Objects.equal(columnName, cadidateColumnName);
                  if (_equals_1) {
                    String _name_2 = abstractElement.getName();
                    String _plus = ((fromPrefix + ".") + _name_2);
                    String _plus_1 = (_plus + " = ");
                    String _plus_2 = (_plus_1 + toPrefix);
                    String _plus_3 = (_plus_2 + ".");
                    String _name_3 = cadidateElement.getName();
                    String _plus_4 = (_plus_3 + _name_3);
                    String _plus_5 = (_plus_4 + " AND");
                    buffer.append(_plus_5);
                  }
                }
              }
            }
          }
        }
      }
      return buffer.toString();
    }
    return "";
  }
  
  private String generateJoinExpressionFragmentForReadEach(final YReadEachStatement readEachStatement, final YJoinDef joinDef) {
    final YAnnotTable fromTable = this._langUtil.getImplementingTable(joinDef.getFromView().getType().getEntity());
    final String fromPrefix = this.findTablePrefix(readEachStatement.getStructs(), joinDef.getFromView());
    final YAnnotTable toTable = this._langUtil.getImplementingTable(joinDef.getToView().getType().getEntity());
    final String toPrefix = this.findTablePrefix(readEachStatement.getStructs(), joinDef.getToView());
    final boolean parent = joinDef.getRelRef().isParent();
    if (parent) {
      StringBuffer buffer = new StringBuffer();
      String columnName = "";
      String cadidateColumnName = null;
      EList<YAnnotAbstractColumn> _columns = fromTable.getPrimarykey().getColumns();
      for (final YAnnotAbstractColumn abstractElement : _columns) {
        {
          columnName = this.getAttributeNameForAbstractColumn(abstractElement);
          EList<YAnnotForeignKey> _foreignkeys = toTable.getForeignkeys();
          for (final YAnnotForeignKey foreignKey : _foreignkeys) {
            String _name = joinDef.getRelRef().getInverse().getName();
            String _name_1 = foreignKey.getRelationship().getName();
            boolean _equals = Objects.equal(_name, _name_1);
            if (_equals) {
              EList<YAnnotAbstractColumn> _columns_1 = foreignKey.getColumns();
              for (final YAnnotAbstractColumn cadidateElement : _columns_1) {
                {
                  cadidateColumnName = this.getAttributeNameForAbstractColumn(cadidateElement);
                  boolean _equals_1 = Objects.equal(columnName, cadidateColumnName);
                  if (_equals_1) {
                    String _name_2 = abstractElement.getName();
                    String _plus = ((fromPrefix + ".") + _name_2);
                    String _plus_1 = (_plus + " = ");
                    String _plus_2 = (_plus_1 + toPrefix);
                    String _plus_3 = (_plus_2 + ".");
                    String _name_3 = cadidateElement.getName();
                    String _plus_4 = (_plus_3 + _name_3);
                    String _plus_5 = (_plus_4 + " AND");
                    buffer.append(_plus_5);
                  }
                }
              }
            }
          }
        }
      }
      return buffer.toString();
    }
    return "";
  }
  
  private String findTablePrefix(final EList<YStructRefPair> list, final YProperty property) {
    String _xblockexpression = null;
    {
      int index = 1;
      for (final YStructRefPair pair : list) {
        {
          YProperty _structproperty = pair.getStructproperty();
          boolean _equals = Objects.equal(_structproperty, property);
          if (_equals) {
            return ("T" + Integer.valueOf(index));
          }
          index++;
        }
      }
      _xblockexpression = "";
    }
    return _xblockexpression;
  }
  
  private String getAttributeNameForAbstractColumn(final YAnnotAbstractColumn annotAbstractColumn) {
    EObject _type = annotAbstractColumn.getType();
    if ((_type instanceof YAnnotColumnLike)) {
      EObject _type_1 = annotAbstractColumn.getType();
      EObject _type_2 = ((YAnnotColumnLike) _type_1).getColumnref().getType();
      return ((YAnnotColumn) _type_2).getAttrref().getName();
    } else {
      EObject _type_3 = annotAbstractColumn.getType();
      return ((YAnnotColumn) _type_3).getAttrref().getName();
    }
  }
  
  private ArrayList<String> generateFROMClause(final YStatement statement) {
    final ArrayList<String> list = CollectionLiterals.<String>newArrayList();
    int index = 1;
    if ((statement instanceof YReadStatement)) {
      final YReadStatement readStatement = ((YReadStatement) statement);
      EList<YStructRefPair> _structs = readStatement.getStructs();
      for (final YStructRefPair struct : _structs) {
        {
          final YAnnotTable implementingTable = this._langUtil.getImplementingTable(readStatement.getStructs().get(0).getStructclass());
          StringConcatenation _builder = new StringConcatenation();
          _builder.append("\\\"");
          String _name = implementingTable.getName();
          _builder.append(_name);
          _builder.append("\\\" T");
          _builder.append(index);
          list.add(_builder.toString());
          index++;
        }
      }
    }
    return list;
  }
  
  private String generateAssigment(final YAssignment assignment) {
    YExpression _left = assignment.getLeft();
    if ((_left instanceof YMemberSelection)) {
      YExpression _left_1 = assignment.getLeft();
      String _generateMemberSelection = this.generateMemberSelection(((YMemberSelection) _left_1));
      String _plus = (_generateMemberSelection + " = ");
      String _generateExpression = this.generateExpression(assignment.getRight());
      String _plus_1 = (_plus + _generateExpression);
      return (_plus_1 + ";");
    } else {
      YExpression _left_2 = assignment.getLeft();
      if ((_left_2 instanceof YSymbolRef)) {
        YExpression _left_3 = assignment.getLeft();
        final YSymbolRef symbolRef = ((YSymbolRef) _left_3);
        String _name = symbolRef.getSymbol().getName();
        String _plus_2 = (_name + " = ");
        String _generateExpression_1 = this.generateExpression(assignment.getRight());
        String _plus_3 = (_plus_2 + _generateExpression_1);
        return (_plus_3 + ";");
      }
      return "?";
    }
  }
  
  private String generateProperties(final YClass clazz) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("private Connection _connection;");
    _builder.newLine();
    {
      EList<YMember> _members = clazz.getMembers();
      for(final YMember member : _members) {
        {
          if ((member instanceof YProperty)) {
            String _generateProperty = this.generateProperty(((YProperty) member));
            _builder.append(_generateProperty);
            _builder.newLineIfNotEmpty();
          }
        }
      }
    }
    return _builder.toString();
  }
  
  private String generateProperty(final YProperty property) {
    String _xblockexpression = null;
    {
      String _name = property.getType().getName();
      boolean _equals = Objects.equal(_name, "Array");
      if (_equals) {
        return "";
      }
      StringConcatenation _builder = new StringConcatenation();
      String _documentation = this._langJavaGeneratorHelper.getDocumentation(property);
      _builder.append(_documentation);
      _builder.append("  ");
      _builder.newLineIfNotEmpty();
      {
        YAccessLevel _access = property.getAccess();
        boolean _tripleNotEquals = (_access != null);
        if (_tripleNotEquals) {
          YAccessLevel _access_1 = property.getAccess();
          _builder.append(_access_1);
          _builder.append(" ");
        }
      }
      String _translateTypeName = this._langJavaGeneratorHelper.translateTypeName(property.getType().getName());
      _builder.append(_translateTypeName);
      _builder.append(" ");
      String _name_1 = property.getName();
      _builder.append(_name_1);
      _builder.append(";");
      _xblockexpression = _builder.toString();
    }
    return _xblockexpression;
  }
  
  private String generateInnerClasses(final YClass clazz, final String widgetName) {
    StringConcatenation _builder = new StringConcatenation();
    {
      EList<YClass> _inners = clazz.getInners();
      for(final YClass innerclazz : _inners) {
        String _generateClass = this.generateClass(innerclazz, widgetName);
        _builder.append(_generateClass);
      }
    }
    _builder.newLineIfNotEmpty();
    return _builder.toString();
  }
  
  private String generateClass(final YClass innerclazz, final String widgetName) {
    String _xifexpression = null;
    if (((innerclazz.getSuperclass() != null) && Objects.equal(innerclazz.getSuperclass().getName(), "Structure"))) {
      String _xblockexpression = null;
      {
        this.registerImport("eu.jgen.notes.dmw.lite.runtimes.XStructure");
        StringConcatenation _builder = new StringConcatenation();
        _builder.newLine();
        String _documentation = this._langJavaGeneratorHelper.getDocumentation(innerclazz);
        _builder.append(_documentation);
        _builder.append("  ");
        _builder.newLineIfNotEmpty();
        _builder.append("public class ");
        String _name = innerclazz.getName();
        _builder.append(_name);
        _builder.append(" extends XStructure {");
        _builder.newLineIfNotEmpty();
        {
          EList<YMember> _members = innerclazz.getMembers();
          for(final YMember member : _members) {
            {
              if ((member instanceof YProperty)) {
                _builder.append("   ");
                String _generatePropertyForStructure = this.generatePropertyForStructure(((YProperty) member));
                _builder.append(_generatePropertyForStructure, "   ");
                _builder.newLineIfNotEmpty();
              }
            }
          }
        }
        _builder.append("}");
        _builder.newLine();
        _builder.newLine();
        _xblockexpression = _builder.toString();
      }
      _xifexpression = _xblockexpression;
    } else {
      StringConcatenation _builder = new StringConcatenation();
      String _documentation = this._langJavaGeneratorHelper.getDocumentation(innerclazz);
      _builder.append(_documentation);
      _builder.append(" ");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.append("public class ");
      String _name = innerclazz.getName();
      _builder.append(_name, "\t");
      _builder.append(" ");
      {
        YClass _superclass = innerclazz.getSuperclass();
        boolean _tripleNotEquals = (_superclass != null);
        if (_tripleNotEquals) {
          _builder.append("extends ");
          String _name_1 = innerclazz.getSuperclass().getName();
          _builder.append(_name_1, "\t");
        }
      }
      _builder.append(" {");
      _builder.newLineIfNotEmpty();
      {
        EList<YMember> _members = innerclazz.getMembers();
        for(final YMember member : _members) {
          {
            if ((member instanceof YProperty)) {
              _builder.append("\t\t");
              String _generatePropertyForStructure = this.generatePropertyForStructure(((YProperty) member));
              _builder.append(_generatePropertyForStructure, "\t\t");
              _builder.newLineIfNotEmpty();
            }
          }
        }
      }
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("public static ");
      String _name_2 = innerclazz.getName();
      _builder.append(_name_2, "\t");
      _builder.append(" instance");
      String _name_3 = innerclazz.getName();
      _builder.append(_name_3, "\t");
      _builder.append("() {");
      _builder.newLineIfNotEmpty();
      _builder.append("\t   ");
      String _name_4 = innerclazz.getName();
      _builder.append(_name_4, "\t   ");
      _builder.append(" ");
      String _firstLower = StringExtensions.toFirstLower(innerclazz.getName());
      _builder.append(_firstLower, "\t   ");
      _builder.append(" = new ");
      _builder.append(widgetName, "\t   ");
      _builder.append("().new ");
      String _name_5 = innerclazz.getName();
      _builder.append(_name_5, "\t   ");
      _builder.append("();");
      _builder.newLineIfNotEmpty();
      {
        EList<YMember> _members_1 = innerclazz.getMembers();
        for(final YMember member_1 : _members_1) {
          {
            if ((member_1 instanceof YProperty)) {
              _builder.append("\t   ");
              String _generateInitializeProperty = this.generateInitializeProperty(((YProperty) member_1), StringExtensions.toFirstLower(innerclazz.getName()));
              _builder.append(_generateInitializeProperty, "\t   ");
              _builder.newLineIfNotEmpty();
            }
          }
        }
      }
      _builder.append("\t   ");
      _builder.append("return ");
      String _firstLower_1 = StringExtensions.toFirstLower(innerclazz.getName());
      _builder.append(_firstLower_1, "\t   ");
      _builder.append(";");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _xifexpression = _builder.toString();
    }
    return _xifexpression;
  }
  
  private String generateInitializeProperty(final YProperty property, final String structureName) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append(structureName);
    _builder.append(".");
    String _name = property.getName();
    _builder.append(_name);
    _builder.append(" = ");
    String _propertyDefaultValue = this._langJavaGeneratorHelper.getPropertyDefaultValue(property);
    _builder.append(_propertyDefaultValue);
    _builder.append(";");
    return _builder.toString();
  }
  
  private String generatePropertyForStructure(final YProperty property) {
    StringConcatenation _builder = new StringConcatenation();
    String _documentation = this._langJavaGeneratorHelper.getDocumentation(property);
    _builder.append(_documentation);
    _builder.append("  ");
    _builder.newLineIfNotEmpty();
    _builder.append("public ");
    String _translateTypeName = this._langJavaGeneratorHelper.translateTypeName(property.getType().getName());
    _builder.append(_translateTypeName);
    _builder.append(" ");
    String _name = property.getName();
    _builder.append(_name);
    _builder.append(";");
    return _builder.toString();
  }
  
  private String registerImport(final String name) {
    boolean _contains = this.imports.contains(name);
    if (_contains) {
      return "";
    }
    this.imports.add(name);
    return "";
  }
  
  private String generateVariableDeclaration(final YVariableDeclaration variableDeclaration) {
    String _translateTypeName = this._langJavaGeneratorHelper.translateTypeName(variableDeclaration.getType().getName());
    String _plus = (_translateTypeName + " ");
    String _name = variableDeclaration.getName();
    String _plus_1 = (_plus + _name);
    String _plus_2 = (_plus_1 + " = ");
    String _generateExpression = this.generateExpression(variableDeclaration.getExpression());
    String _plus_3 = (_plus_2 + _generateExpression);
    return (_plus_3 + ";");
  }
  
  private String generateExpression(final YExpression expression) {
    String _switchResult = null;
    boolean _matched = false;
    if ((expression instanceof YPlus)) {
      _matched=true;
      final YPlus plus = ((YPlus) expression);
      String _generateExpression = this.generateExpression(plus.getLeft());
      String _plus = (_generateExpression + " + ");
      String _generateExpression_1 = this.generateExpression(plus.getRight());
      return (_plus + _generateExpression_1);
    }
    if (!_matched) {
      if ((expression instanceof YMinus)) {
        _matched=true;
        final YMinus minus = ((YMinus) expression);
        String _generateExpression_2 = this.generateExpression(minus.getLeft());
        String _plus_1 = (_generateExpression_2 + " - ");
        String _generateExpression_3 = this.generateExpression(minus.getRight());
        return (_plus_1 + _generateExpression_3);
      }
    }
    if (!_matched) {
      if ((expression instanceof YMulOrDiv)) {
        _matched=true;
        final YMulOrDiv mulOrDiv = ((YMulOrDiv) expression);
        String _generateExpression_4 = this.generateExpression(mulOrDiv.getLeft());
        String _plus_2 = (_generateExpression_4 + " ");
        String _op = mulOrDiv.getOp();
        String _plus_3 = (_plus_2 + _op);
        String _plus_4 = (_plus_3 + " ");
        String _generateExpression_5 = this.generateExpression(mulOrDiv.getRight());
        return (_plus_4 + _generateExpression_5);
      }
    }
    if (!_matched) {
      if ((expression instanceof YAndExpression)) {
        _matched=true;
        final YAndExpression andExpression = ((YAndExpression) expression);
        String _generateExpression_6 = this.generateExpression(andExpression.getLeft());
        String _plus_5 = (_generateExpression_6 + " ");
        String _plus_6 = (_plus_5 + " && ");
        String _plus_7 = (_plus_6 + " ");
        String _generateExpression_7 = this.generateExpression(andExpression.getRight());
        return (_plus_7 + _generateExpression_7);
      }
    }
    if (!_matched) {
      if ((expression instanceof YOrExpression)) {
        _matched=true;
        final YOrExpression orExpression = ((YOrExpression) expression);
        String _generateExpression_8 = this.generateExpression(orExpression.getLeft());
        String _plus_8 = (_generateExpression_8 + " ");
        String _plus_9 = (_plus_8 + " || ");
        String _plus_10 = (_plus_9 + " ");
        String _generateExpression_9 = this.generateExpression(orExpression.getRight());
        return (_plus_10 + _generateExpression_9);
      }
    }
    if (!_matched) {
      if ((expression instanceof YComparisonExpression)) {
        _matched=true;
        final YComparisonExpression comparisonExpression = ((YComparisonExpression) expression);
        String _generateExpression_10 = this.generateExpression(comparisonExpression.getLeft());
        String _plus_11 = (_generateExpression_10 + " ");
        String _op_1 = comparisonExpression.getOp();
        String _plus_12 = (_plus_11 + _op_1);
        String _plus_13 = (_plus_12 + " ");
        String _generateExpression_11 = this.generateExpression(comparisonExpression.getRight());
        return (_plus_13 + _generateExpression_11);
      }
    }
    if (!_matched) {
      if ((expression instanceof YEqualityExpression)) {
        _matched=true;
        final YEqualityExpression equalityExpression = ((YEqualityExpression) expression);
        String _generateExpression_12 = this.generateExpression(equalityExpression.getLeft());
        String _plus_14 = (_generateExpression_12 + " ");
        String _op_2 = equalityExpression.getOp();
        String _plus_15 = (_plus_14 + _op_2);
        String _plus_16 = (_plus_15 + " ");
        String _generateExpression_13 = this.generateExpression(equalityExpression.getRight());
        return (_plus_16 + _generateExpression_13);
      }
    }
    if (!_matched) {
      if ((expression instanceof YMemberSelection)) {
        _matched=true;
        final YMemberSelection memberSelection = ((YMemberSelection) expression);
        return this.generateMemberSelection(memberSelection);
      }
    }
    if (!_matched) {
      if ((expression instanceof YSelf)) {
        _matched=true;
        return "this";
      }
    }
    if (!_matched) {
      if ((expression instanceof YNot)) {
        _matched=true;
        final YNot not = ((YNot) expression);
        String _generateExpression_14 = this.generateExpression(not.getExpression());
        return ("!" + _generateExpression_14);
      }
    }
    if (!_matched) {
      if ((expression instanceof YBoolConstant)) {
        _matched=true;
        final YBoolConstant boolConstant = ((YBoolConstant) expression);
        return boolConstant.getValue();
      }
    }
    if (!_matched) {
      if ((expression instanceof YParenties)) {
        _matched=true;
        String _generateExpression_15 = this.generateExpression(((YParenties) expression).getA());
        String _plus_17 = ("(" + _generateExpression_15);
        return (_plus_17 + ")");
      }
    }
    if (!_matched) {
      if ((expression instanceof YSymbolRef)) {
        _matched=true;
        final YSymbolRef symbolRef = ((YSymbolRef) expression);
        return symbolRef.getSymbol().getName();
      }
    }
    if (!_matched) {
      if ((expression instanceof YIntConstant)) {
        _matched=true;
        String _xblockexpression = null;
        {
          final YIntConstant intConstant = ((YIntConstant) expression);
          _xblockexpression = Integer.valueOf(intConstant.getValue()).toString();
        }
        _switchResult = _xblockexpression;
      }
    }
    if (!_matched) {
      if ((expression instanceof YStringConstant)) {
        _matched=true;
        String _xblockexpression_1 = null;
        {
          final YStringConstant stringConstant = ((YStringConstant) expression);
          String _string = stringConstant.getValue().toString();
          String _plus_18 = ("\"" + _string);
          _xblockexpression_1 = (_plus_18 + "\"");
        }
        _switchResult = _xblockexpression_1;
      }
    }
    if (!_matched) {
      _switchResult = null;
    }
    return _switchResult;
  }
  
  private String generateJDBCExpression(final YStatement statement, final YExpression expression) {
    String _switchResult = null;
    boolean _matched = false;
    if ((expression instanceof YPlus)) {
      _matched=true;
      final YPlus plus = ((YPlus) expression);
      String _generateJDBCExpression = this.generateJDBCExpression(statement, plus.getLeft());
      String _plus = (_generateJDBCExpression + " + ");
      String _generateJDBCExpression_1 = this.generateJDBCExpression(statement, plus.getRight());
      return (_plus + _generateJDBCExpression_1);
    }
    if (!_matched) {
      if ((expression instanceof YMinus)) {
        _matched=true;
        final YMinus minus = ((YMinus) expression);
        String _generateJDBCExpression_2 = this.generateJDBCExpression(statement, minus.getLeft());
        String _plus_1 = (_generateJDBCExpression_2 + " - ");
        String _generateJDBCExpression_3 = this.generateJDBCExpression(statement, minus.getRight());
        return (_plus_1 + _generateJDBCExpression_3);
      }
    }
    if (!_matched) {
      if ((expression instanceof YMulOrDiv)) {
        _matched=true;
        final YMulOrDiv mulOrDiv = ((YMulOrDiv) expression);
        String _generateJDBCExpression_4 = this.generateJDBCExpression(statement, mulOrDiv.getLeft());
        String _plus_2 = (_generateJDBCExpression_4 + " ");
        String _op = mulOrDiv.getOp();
        String _plus_3 = (_plus_2 + _op);
        String _plus_4 = (_plus_3 + " ");
        String _generateJDBCExpression_5 = this.generateJDBCExpression(statement, mulOrDiv.getRight());
        return (_plus_4 + _generateJDBCExpression_5);
      }
    }
    if (!_matched) {
      if ((expression instanceof YAndExpression)) {
        _matched=true;
        final YAndExpression andExpression = ((YAndExpression) expression);
        String _generateJDBCExpression_6 = this.generateJDBCExpression(statement, andExpression.getLeft());
        String _plus_5 = (_generateJDBCExpression_6 + " ");
        String _plus_6 = (_plus_5 + " AND ");
        String _plus_7 = (_plus_6 + " ");
        String _generateJDBCExpression_7 = this.generateJDBCExpression(statement, andExpression.getRight());
        return (_plus_7 + _generateJDBCExpression_7);
      }
    }
    if (!_matched) {
      if ((expression instanceof YOrExpression)) {
        _matched=true;
        final YOrExpression orExpression = ((YOrExpression) expression);
        String _generateJDBCExpression_8 = this.generateJDBCExpression(statement, orExpression.getLeft());
        String _plus_8 = (_generateJDBCExpression_8 + " ");
        String _plus_9 = (_plus_8 + " OR ");
        String _plus_10 = (_plus_9 + " ");
        String _generateJDBCExpression_9 = this.generateJDBCExpression(statement, orExpression.getRight());
        return (_plus_10 + _generateJDBCExpression_9);
      }
    }
    if (!_matched) {
      if ((expression instanceof YComparisonExpression)) {
        _matched=true;
        final YComparisonExpression comparisonExpression = ((YComparisonExpression) expression);
        String operator = "?";
        String _op_1 = comparisonExpression.getOp();
        boolean _equals = Objects.equal(_op_1, ">=");
        if (_equals) {
          operator = ">=";
        } else {
          String _op_2 = comparisonExpression.getOp();
          boolean _equals_1 = Objects.equal(_op_2, "<=");
          if (_equals_1) {
            operator = "<=";
          } else {
            String _op_3 = comparisonExpression.getOp();
            boolean _equals_2 = Objects.equal(_op_3, ">");
            if (_equals_2) {
              operator = ">";
            } else {
              String _op_4 = comparisonExpression.getOp();
              boolean _equals_3 = Objects.equal(_op_4, "<");
              if (_equals_3) {
                operator = "<";
              }
            }
          }
        }
        String _generateJDBCExpression_10 = this.generateJDBCExpression(statement, comparisonExpression.getLeft());
        String _plus_11 = (_generateJDBCExpression_10 + " ");
        String _plus_12 = (_plus_11 + operator);
        String _plus_13 = (_plus_12 + " ");
        String _generateJDBCExpression_11 = this.generateJDBCExpression(statement, comparisonExpression.getRight());
        return (_plus_13 + _generateJDBCExpression_11);
      }
    }
    if (!_matched) {
      if ((expression instanceof YEqualityExpression)) {
        _matched=true;
        final YEqualityExpression equalityExpression = ((YEqualityExpression) expression);
        String operator_1 = "?";
        String _op_5 = equalityExpression.getOp();
        boolean _equals_4 = Objects.equal(_op_5, "==");
        if (_equals_4) {
          operator_1 = "=";
        } else {
          String _op_6 = equalityExpression.getOp();
          boolean _equals_5 = Objects.equal(_op_6, "!=");
          if (_equals_5) {
            operator_1 = "<>";
          }
        }
        String _generateJDBCExpression_12 = this.generateJDBCExpression(statement, equalityExpression.getLeft());
        String _plus_14 = (_generateJDBCExpression_12 + " ");
        String _plus_15 = (_plus_14 + operator_1);
        String _plus_16 = (_plus_15 + " ");
        String _generateJDBCExpression_13 = this.generateJDBCExpression(statement, equalityExpression.getRight());
        return (_plus_16 + _generateJDBCExpression_13);
      }
    }
    if (!_matched) {
      if ((expression instanceof YMemberSelection)) {
        _matched=true;
        final YMemberSelection memberSelection = ((YMemberSelection) expression);
        return this.generateJDBCMemberSelection(statement, memberSelection);
      }
    }
    if (!_matched) {
      if ((expression instanceof YSelf)) {
        _matched=true;
        return "this";
      }
    }
    if (!_matched) {
      if ((expression instanceof YNot)) {
        _matched=true;
        final YNot not = ((YNot) expression);
        String _generateJDBCExpression_14 = this.generateJDBCExpression(statement, not.getExpression());
        return ("!" + _generateJDBCExpression_14);
      }
    }
    if (!_matched) {
      if ((expression instanceof YBoolConstant)) {
        _matched=true;
        final YBoolConstant boolConstant = ((YBoolConstant) expression);
        return boolConstant.getValue();
      }
    }
    if (!_matched) {
      if ((expression instanceof YParenties)) {
        _matched=true;
        String _generateJDBCExpression_15 = this.generateJDBCExpression(statement, ((YParenties) expression).getA());
        String _plus_17 = ("(" + _generateJDBCExpression_15);
        return (_plus_17 + ")");
      }
    }
    if (!_matched) {
      if ((expression instanceof YSymbolRef)) {
        _matched=true;
        final YSymbolRef symbolRef = ((YSymbolRef) expression);
        return symbolRef.getSymbol().getName();
      }
    }
    if (!_matched) {
      if ((expression instanceof YIntConstant)) {
        _matched=true;
        String _xblockexpression = null;
        {
          final YIntConstant intConstant = ((YIntConstant) expression);
          _xblockexpression = Integer.valueOf(intConstant.getValue()).toString();
        }
        _switchResult = _xblockexpression;
      }
    }
    if (!_matched) {
      if ((expression instanceof YStringConstant)) {
        _matched=true;
        String _xblockexpression_1 = null;
        {
          final YStringConstant stringConstant = ((YStringConstant) expression);
          String _string = stringConstant.getValue().toString();
          String _plus_18 = ("\'" + _string);
          _xblockexpression_1 = (_plus_18 + "\'");
        }
        _switchResult = _xblockexpression_1;
      }
    }
    if (!_matched) {
      _switchResult = "not done yet";
    }
    return _switchResult;
  }
  
  private String generateJDBCMemberSelection(final YStatement statement, final YMemberSelection memberSelection) {
    String _xblockexpression = null;
    {
      YExpression _receiver = memberSelection.getReceiver();
      final String propertyName = ((YMemberSelection) _receiver).getMember().getName();
      if ((statement instanceof YReadStatement)) {
        int index = 1;
        final YReadStatement readStatement = ((YReadStatement) statement);
        EList<YStructRefPair> _structs = readStatement.getStructs();
        for (final YStructRefPair structclass : _structs) {
          {
            final String name = structclass.getStructproperty().getName();
            final YAnnotTable table = this._langUtil.getImplementingTable(structclass.getStructclass());
            EList<YAnnotAbstractColumn> _columns = table.getColumns();
            for (final YAnnotAbstractColumn annotAbstractColumn : _columns) {
              if ((Objects.equal(propertyName, name) && 
                Objects.equal(((YAnnotColumn) annotAbstractColumn.getType()).getAttrref().getName(), memberSelection.getMember().getName()))) {
                String _name = annotAbstractColumn.getName();
                final String qualName = ((("T" + Integer.valueOf(index)) + ".") + _name);
                return qualName;
              }
            }
            index++;
          }
        }
        return "?";
      }
      _xblockexpression = "something went wrong";
    }
    return _xblockexpression;
  }
  
  private String generateMemberSelection(final YMemberSelection memberSelection) {
    boolean _isFunctioninvocation = memberSelection.isFunctioninvocation();
    if (_isFunctioninvocation) {
      YMember _member = memberSelection.getMember();
      final YFunction function = ((YFunction) _member);
      YExpression _receiver = memberSelection.getReceiver();
      final String terminalExpression = this.generateTermination(((YMemberSelection) _receiver).getReceiver());
      YExpression _receiver_1 = memberSelection.getReceiver();
      String _name = ((YMemberSelection) _receiver_1).getMember().getName();
      String _plus = ((terminalExpression + ".") + _name);
      String _plus_1 = (_plus + ".");
      String _name_1 = function.getName();
      String _plus_2 = (_plus_1 + _name_1);
      String _generateFunctionArguments = this.generateFunctionArguments(memberSelection);
      return (_plus_2 + _generateFunctionArguments);
    } else {
      YExpression _receiver_2 = memberSelection.getReceiver();
      if ((_receiver_2 instanceof YMemberSelection)) {
        YExpression _receiver_3 = memberSelection.getReceiver();
        final String terminalExpression_1 = this.generateTermination(((YMemberSelection) _receiver_3).getReceiver());
        YExpression _receiver_4 = memberSelection.getReceiver();
        String _name_2 = ((YMemberSelection) _receiver_4).getMember().getName();
        final String text = ((terminalExpression_1 + ".") + _name_2);
        String _name_3 = memberSelection.getMember().getName();
        return ((text + ".") + _name_3);
      } else {
        final String terminalExpression_2 = this.generateTermination(memberSelection.getReceiver());
        String _name_4 = memberSelection.getMember().getName();
        final String text_1 = ((terminalExpression_2 + ".") + _name_4);
        return text_1;
      }
    }
  }
  
  private String generateFunctionArguments(final YMemberSelection memberSelection) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("(");
    {
      EList<YExpression> _args = memberSelection.getArgs();
      boolean _hasElements = false;
      for(final YExpression arg : _args) {
        if (!_hasElements) {
          _hasElements = true;
        } else {
          _builder.appendImmediate(", ", "");
        }
        String _generateExpression = this.generateExpression(arg);
        _builder.append(_generateExpression);
      }
    }
    _builder.append(")");
    return _builder.toString();
  }
  
  private String generateTermination(final YExpression expression) {
    String _switchResult = null;
    boolean _matched = false;
    if ((expression instanceof YSelf)) {
      _matched=true;
      return "this";
    }
    if (!_matched) {
      if ((expression instanceof YIntConstant)) {
        _matched=true;
        String _xblockexpression = null;
        {
          final YIntConstant intConstant = ((YIntConstant) expression);
          _xblockexpression = Integer.valueOf(intConstant.getValue()).toString();
        }
        _switchResult = _xblockexpression;
      }
    }
    if (!_matched) {
      if ((expression instanceof YStringConstant)) {
        _matched=true;
        String _xblockexpression_1 = null;
        {
          final YStringConstant stringConstant = ((YStringConstant) expression);
          String _string = stringConstant.getValue().toString();
          String _plus = ("\"" + _string);
          _xblockexpression_1 = (_plus + "\"");
        }
        _switchResult = _xblockexpression_1;
      }
    }
    if (!_matched) {
      _switchResult = null;
    }
    return _switchResult;
  }
  
  private String generateStatementSwitch(final YSwitchStatement switchStatement) {
    this.imports.add("com.google.common.base.Objects");
    String key = this._langJavaGeneratorHelper.generateLocalName("key");
    String _matched = this._langJavaGeneratorHelper.generateLocalName("_matched");
    final StringBuffer buffer = new StringBuffer();
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("final ");
    String _translateTypeName = this._langJavaGeneratorHelper.translateTypeName(this._langTypeComputer.typeFor(switchStatement.getSwitchExpression()).getName());
    _builder.append(_translateTypeName);
    _builder.append(" ");
    _builder.append(key);
    _builder.append(" = ");
    String _generateExpression = this.generateExpression(switchStatement.getSwitchExpression());
    _builder.append(_generateExpression);
    _builder.append(";");
    _builder.newLineIfNotEmpty();
    _builder.append("boolean ");
    _builder.append(_matched);
    _builder.append(" = false;");
    _builder.newLineIfNotEmpty();
    buffer.append(_builder);
    boolean _isEmpty = switchStatement.getCases().isEmpty();
    if (_isEmpty) {
      return buffer.toString();
    }
    boolean firstItem = true;
    EList<YSwitchCase> _cases = switchStatement.getCases();
    for (final YSwitchCase caseFragment : _cases) {
      if (firstItem) {
        StringConcatenation _builder_1 = new StringConcatenation();
        _builder_1.append("if (Objects.equal(");
        _builder_1.append(key);
        _builder_1.append(", ");
        String _generateExpression_1 = this.generateExpression(caseFragment.getCaseExpression());
        _builder_1.append(_generateExpression_1);
        _builder_1.append(")) {");
        _builder_1.newLineIfNotEmpty();
        _builder_1.append("   ");
        _builder_1.append(_matched, "   ");
        _builder_1.append("=true;");
        _builder_1.newLineIfNotEmpty();
        _builder_1.append("   ");
        String _generateBlock = this.generateBlock(caseFragment.getThen());
        _builder_1.append(_generateBlock, "   ");
        _builder_1.newLineIfNotEmpty();
        _builder_1.append("}");
        _builder_1.newLine();
        buffer.append(_builder_1);
        firstItem = false;
      } else {
        StringConcatenation _builder_2 = new StringConcatenation();
        _builder_2.append("if (!");
        _builder_2.append(_matched);
        _builder_2.append(") {");
        _builder_2.newLineIfNotEmpty();
        _builder_2.append("   ");
        _builder_2.append("if (Objects.equal(");
        _builder_2.append(key, "   ");
        _builder_2.append(", ");
        String _generateExpression_2 = this.generateExpression(caseFragment.getCaseExpression());
        _builder_2.append(_generateExpression_2, "   ");
        _builder_2.append(")) {");
        _builder_2.newLineIfNotEmpty();
        _builder_2.append("      ");
        _builder_2.append(_matched, "      ");
        _builder_2.append("=true;");
        _builder_2.newLineIfNotEmpty();
        _builder_2.append("      ");
        String _generateBlock_1 = this.generateBlock(caseFragment.getThen());
        _builder_2.append(_generateBlock_1, "      ");
        _builder_2.append("   ");
        _builder_2.newLineIfNotEmpty();
        _builder_2.append("   ");
        _builder_2.append("}");
        _builder_2.newLine();
        _builder_2.append("}");
        _builder_2.newLine();
        buffer.append(_builder_2);
      }
    }
    StringConcatenation _builder_3 = new StringConcatenation();
    {
      YBlock _default = switchStatement.getDefault();
      boolean _tripleNotEquals = (_default != null);
      if (_tripleNotEquals) {
        _builder_3.append("if (!");
        _builder_3.append(_matched);
        _builder_3.append(") {");
        _builder_3.newLineIfNotEmpty();
        _builder_3.append("   ");
        String _generateBlock_2 = this.generateBlock(switchStatement.getDefault());
        _builder_3.append(_generateBlock_2, "   ");
        _builder_3.newLineIfNotEmpty();
        _builder_3.append("}");
        _builder_3.newLine();
      }
    }
    buffer.append(_builder_3);
    return buffer.toString();
  }
  
  private String generateSpecialFunctions(final YMemberSelection memberSelection) {
    boolean _isFunctioninvocation = memberSelection.isFunctioninvocation();
    boolean _not = (!_isFunctioninvocation);
    if (_not) {
      return "";
    }
    String _name = memberSelection.getMember().getName();
    boolean _equals = Objects.equal(_name, "setSubscript");
    if (_equals) {
      return this.generateSetSubscript(memberSelection);
    }
    return "";
  }
  
  private String generateSetSubscript(final YMemberSelection memberSelection) {
    YExpression _receiver = memberSelection.getReceiver();
    YMember _member = ((YMemberSelection) _receiver).getMember();
    final YProperty property = ((YProperty) _member);
    final String arrayName = property.getName();
    final YTuples tuples = property.getTuples();
    StringConcatenation _builder = new StringConcatenation();
    _builder.append(arrayName);
    _builder.append(".setSubscript(");
    String _generateExpression = this.generateExpression(memberSelection.getArgs().get(0));
    _builder.append(_generateExpression);
    _builder.append(");");
    _builder.newLineIfNotEmpty();
    {
      EList<YProperty> _includes = tuples.getIncludes();
      for(final YProperty include : _includes) {
        _builder.append("this.");
        String _name = include.getName();
        _builder.append(_name);
        _builder.append(" = ");
        _builder.append(arrayName);
        _builder.append(".get");
        String _name_1 = include.getType().getName();
        _builder.append(_name_1);
        _builder.append("();");
        _builder.newLineIfNotEmpty();
      }
    }
    final String block = _builder.toString();
    return block;
  }
  
  private String generateSetSubscript(final YProperty property, final int index) {
    final String arrayName = property.getName();
    final YTuples tuples = property.getTuples();
    StringConcatenation _builder = new StringConcatenation();
    _builder.append(arrayName);
    _builder.append(".setSubscript(");
    _builder.append(index);
    _builder.append(");");
    _builder.newLineIfNotEmpty();
    {
      EList<YProperty> _includes = tuples.getIncludes();
      for(final YProperty include : _includes) {
        _builder.append("this.");
        String _name = include.getName();
        _builder.append(_name);
        _builder.append(" = ");
        _builder.append(arrayName);
        _builder.append(".get");
        String _name_1 = include.getType().getName();
        _builder.append(_name_1);
        _builder.append("();");
        _builder.newLineIfNotEmpty();
      }
    }
    final String block = _builder.toString();
    return block;
  }
}
