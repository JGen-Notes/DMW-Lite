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
import eu.jgen.notes.dmw.lite.lang.YAnnotJava;
import eu.jgen.notes.dmw.lite.lang.YAssignment;
import eu.jgen.notes.dmw.lite.lang.YBlock;
import eu.jgen.notes.dmw.lite.lang.YBoolConstant;
import eu.jgen.notes.dmw.lite.lang.YClass;
import eu.jgen.notes.dmw.lite.lang.YComparisonExpression;
import eu.jgen.notes.dmw.lite.lang.YEqualityExpression;
import eu.jgen.notes.dmw.lite.lang.YExpression;
import eu.jgen.notes.dmw.lite.lang.YFunction;
import eu.jgen.notes.dmw.lite.lang.YIfStatement;
import eu.jgen.notes.dmw.lite.lang.YIntConstant;
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
import eu.jgen.notes.dmw.lite.lang.YRepeatWhileStatement;
import eu.jgen.notes.dmw.lite.lang.YReturn;
import eu.jgen.notes.dmw.lite.lang.YSelf;
import eu.jgen.notes.dmw.lite.lang.YStatement;
import eu.jgen.notes.dmw.lite.lang.YStringConstant;
import eu.jgen.notes.dmw.lite.lang.YSwitchCase;
import eu.jgen.notes.dmw.lite.lang.YSwitchStatement;
import eu.jgen.notes.dmw.lite.lang.YSymbolRef;
import eu.jgen.notes.dmw.lite.lang.YVariableDeclaration;
import eu.jgen.notes.dmw.lite.lang.YWhileStatement;
import eu.jgen.notes.dmw.lite.lang.YWidget;
import eu.jgen.notes.dmw.lite.typing.LangTypeComputer;
import eu.jgen.notes.dmw.lite.utility.LangJavaGeneratorHelper;
import eu.jgen.notes.dmw.lite.utility.LangUtil;
import eu.jgen.notes.dmw.lite.utility.LocalNameGenerator;
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
  private LocalNameGenerator _localNameGenerator;
  
  @Inject
  @Extension
  private LangTypeComputer _langTypeComputer;
  
  private List<String> imports = CollectionLiterals.<String>newArrayList();
  
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
  
  protected void generateWidget(final IFileSystemAccess fsa, final YWidget widget) {
    this._localNameGenerator.reset();
    final Consumer<YClass> _function = (YClass clazz) -> {
      if (((clazz.getSuperclass() != null) && Objects.equal(clazz.getSuperclass().getName(), "Widget"))) {
        this.imports.clear();
        this.imports.add("eu.jgen.notes.dmw.lite.runtimes.XWidget");
        StringConcatenation _builder = new StringConcatenation();
        String _documentation = this._langJavaGeneratorHelper.getDocumentation(clazz);
        _builder.append(_documentation);
        _builder.newLineIfNotEmpty();
        _builder.append("@SuppressWarnings(\"unused\")");
        _builder.newLine();
        _builder.append("public class ");
        String _name = clazz.getName();
        _builder.append(_name);
        _builder.append(" extends XWidget {");
        _builder.newLineIfNotEmpty();
        _builder.append("   ");
        String _generateInnerClasses = this.generateInnerClasses(clazz, clazz.getName());
        _builder.append(_generateInnerClasses, "   ");
        _builder.newLineIfNotEmpty();
        _builder.append("   ");
        String _generateProperties = this.generateProperties(clazz);
        _builder.append(_generateProperties, "   ");
        _builder.append("\t\t\t\t\t   ");
        _builder.newLineIfNotEmpty();
        _builder.append("   ");
        String _generateArrays = this.generateArrays(clazz, clazz.getName());
        _builder.append(_generateArrays, "   ");
        _builder.append("\t\t\t\t\t   ");
        _builder.newLineIfNotEmpty();
        _builder.append("   ");
        String _generateFunctions = this.generateFunctions(clazz);
        _builder.append(_generateFunctions, "   ");
        _builder.newLineIfNotEmpty();
        _builder.append("}");
        _builder.newLine();
        final String body = _builder.toString();
        String _fileSystemPath = this._langUtil.getFileSystemPath(widget.getName());
        String _plus = (_fileSystemPath + "/");
        String _name_1 = clazz.getName();
        String _plus_1 = (_plus + _name_1);
        String _plus_2 = (_plus_1 + ".java");
        StringConcatenation _builder_1 = new StringConcatenation();
        _builder_1.append("package ");
        String _name_2 = widget.getName();
        _builder_1.append(_name_2);
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
        String _name_3 = clazz.getName();
        _builder_2.append(_name_3);
        _builder_2.append(" {");
        _builder_2.newLineIfNotEmpty();
        _builder_2.newLine();
        _builder_2.append("}");
        _builder_2.newLine();
        final String body_1 = _builder_2.toString();
        String _fileSystemPath_1 = this._langUtil.getFileSystemPath(widget.getName());
        String _plus_3 = (_fileSystemPath_1 + "/");
        String _name_4 = clazz.getName();
        String _plus_4 = (_plus_3 + _name_4);
        String _plus_5 = (_plus_4 + ".java");
        StringConcatenation _builder_3 = new StringConcatenation();
        _builder_3.append("package ");
        String _name_5 = widget.getName();
        _builder_3.append(_name_5);
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
  
  protected String generateArrays(final YClass clazz, final String widgetName) {
    StringConcatenation _builder = new StringConcatenation();
    {
      ArrayList<YProperty> _listArrayProperties = this._langJavaGeneratorHelper.listArrayProperties(clazz);
      for(final YProperty property : _listArrayProperties) {
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
            _builder.append(" = ");
            _builder.append(widgetName, "\t\t\t");
            _builder.append(".instance");
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
        _builder.append("(20);");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.newLine();
    return _builder.toString();
  }
  
  protected String generateFunctions(final YClass clazz) {
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
  
  protected String generateFunctionForWidget(final YFunction function) {
    StringConcatenation _builder = new StringConcatenation();
    String _documentation = this._langJavaGeneratorHelper.getDocumentation(function);
    _builder.append(_documentation);
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
  
  protected String generateFunctionParameters(final YFunction function) {
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
  
  protected String generateFunctionBody(final YFunction function) {
    String _xifexpression = null;
    YBlock _body = function.getBody();
    boolean _tripleNotEquals = (_body != null);
    if (_tripleNotEquals) {
      String _xblockexpression = null;
      {
        final YBlock block = function.getBody();
        StringConcatenation _builder = new StringConcatenation();
        _builder.append("   ");
        String _generateBlock = this.generateBlock(block);
        _builder.append(_generateBlock, "   ");
        _builder.append(" ");
        _xblockexpression = _builder.toString();
      }
      _xifexpression = _xblockexpression;
    }
    return _xifexpression;
  }
  
  protected String generateBlock(final YBlock block) {
    StringConcatenation _builder = new StringConcatenation();
    {
      EList<YStatement> _statements = block.getStatements();
      for(final YStatement statement : _statements) {
        String _generateStatement = this.generateStatement(statement);
        _builder.append(_generateStatement);
        _builder.newLineIfNotEmpty();
      }
    }
    final String body = _builder.toString();
    return body;
  }
  
  protected String generateStatement(final YStatement statement) {
    String _switchResult = null;
    boolean _matched = false;
    if ((statement instanceof YRepeatWhileStatement)) {
      _matched=true;
      final YRepeatWhileStatement repeatWhileStatement = ((YRepeatWhileStatement) statement);
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
    if (!_matched) {
      if ((statement instanceof YSwitchStatement)) {
        _matched=true;
        final YSwitchStatement switchStatement = ((YSwitchStatement) statement);
        StringConcatenation _builder_1 = new StringConcatenation();
        String _documentation_1 = this._langJavaGeneratorHelper.getDocumentation(switchStatement);
        _builder_1.append(_documentation_1);
        _builder_1.append("  ");
        _builder_1.newLineIfNotEmpty();
        String _doSwitchStatement = this.doSwitchStatement(switchStatement);
        _builder_1.append(_doSwitchStatement);
        _builder_1.append("\t\t");
        _builder_1.newLineIfNotEmpty();
        return _builder_1.toString();
      }
    }
    if (!_matched) {
      if ((statement instanceof YWhileStatement)) {
        _matched=true;
        final YWhileStatement whileStatement = ((YWhileStatement) statement);
        StringConcatenation _builder_2 = new StringConcatenation();
        String _documentation_2 = this._langJavaGeneratorHelper.getDocumentation(whileStatement);
        _builder_2.append(_documentation_2);
        _builder_2.append("  ");
        _builder_2.newLineIfNotEmpty();
        _builder_2.append("while (");
        String _generateExpression_1 = this.generateExpression(whileStatement.getExpression());
        _builder_2.append(_generateExpression_1);
        _builder_2.append(") {");
        _builder_2.newLineIfNotEmpty();
        _builder_2.append("\t   ");
        String _generateBlock_1 = this.generateBlock(whileStatement.getBody());
        _builder_2.append(_generateBlock_1, "\t   ");
        _builder_2.newLineIfNotEmpty();
        _builder_2.append("}\t\t\t");
        _builder_2.newLine();
        return _builder_2.toString();
      }
    }
    if (!_matched) {
      if ((statement instanceof YReturn)) {
        _matched=true;
        final YReturn returnStatement = ((YReturn) statement);
        YExpression _expression = returnStatement.getExpression();
        boolean _tripleEquals = (_expression == null);
        if (_tripleEquals) {
          StringConcatenation _builder_3 = new StringConcatenation();
          String _documentation_3 = this._langJavaGeneratorHelper.getDocumentation(returnStatement);
          _builder_3.append(_documentation_3);
          _builder_3.newLineIfNotEmpty();
          _builder_3.append("return;");
          _builder_3.newLine();
          return _builder_3.toString();
        } else {
          StringConcatenation _builder_4 = new StringConcatenation();
          String _documentation_4 = this._langJavaGeneratorHelper.getDocumentation(returnStatement);
          _builder_4.append(_documentation_4);
          _builder_4.newLineIfNotEmpty();
          _builder_4.append("return ");
          String _generateExpression_2 = this.generateExpression(returnStatement.getExpression());
          _builder_4.append(_generateExpression_2);
          _builder_4.append(";");
          _builder_4.newLineIfNotEmpty();
          return _builder_4.toString();
        }
      }
    }
    if (!_matched) {
      if ((statement instanceof YVariableDeclaration)) {
        _matched=true;
        final YVariableDeclaration variableDeclaration = ((YVariableDeclaration) statement);
        StringConcatenation _builder_5 = new StringConcatenation();
        String _documentation_5 = this._langJavaGeneratorHelper.getDocumentation(variableDeclaration);
        _builder_5.append(_documentation_5);
        _builder_5.newLineIfNotEmpty();
        String _generateVariableDeclaration = this.generateVariableDeclaration(variableDeclaration);
        _builder_5.append(_generateVariableDeclaration);
        _builder_5.newLineIfNotEmpty();
        return _builder_5.toString();
      }
    }
    if (!_matched) {
      if ((statement instanceof YAssignment)) {
        _matched=true;
        final YAssignment assignment = ((YAssignment) statement);
        StringConcatenation _builder_6 = new StringConcatenation();
        String _documentation_6 = this._langJavaGeneratorHelper.getDocumentation(assignment);
        _builder_6.append(_documentation_6);
        _builder_6.newLineIfNotEmpty();
        String _generateAssigment = this.generateAssigment(assignment);
        _builder_6.append(_generateAssigment);
        _builder_6.newLineIfNotEmpty();
        return _builder_6.toString();
      }
    }
    if (!_matched) {
      if ((statement instanceof YIfStatement)) {
        _matched=true;
        String _xblockexpression = null;
        {
          final YIfStatement ifStatement = ((YIfStatement) statement);
          StringConcatenation _builder_7 = new StringConcatenation();
          String _documentation_7 = this._langJavaGeneratorHelper.getDocumentation(ifStatement);
          _builder_7.append(_documentation_7);
          _builder_7.append("  ");
          _builder_7.newLineIfNotEmpty();
          _builder_7.append("if (");
          String _generateExpression_3 = this.generateExpression(ifStatement.getExpression());
          _builder_7.append(_generateExpression_3);
          _builder_7.append(") {");
          _builder_7.newLineIfNotEmpty();
          _builder_7.append("\t");
          String _generateBlock_2 = this.generateBlock(ifStatement.getThenBlock());
          _builder_7.append(_generateBlock_2, "\t");
          _builder_7.append(" ");
          _builder_7.newLineIfNotEmpty();
          _builder_7.append("} ");
          {
            YBlock _elseBlock = ifStatement.getElseBlock();
            boolean _tripleNotEquals = (_elseBlock != null);
            if (_tripleNotEquals) {
              _builder_7.append(" else {");
              _builder_7.newLineIfNotEmpty();
              _builder_7.append("\t\t\t\t\t\t");
              String _generateBlock_3 = this.generateBlock(ifStatement.getElseBlock());
              _builder_7.append(_generateBlock_3, "\t\t\t\t\t\t");
              _builder_7.newLineIfNotEmpty();
              _builder_7.append("}");
            }
          }
          _builder_7.newLineIfNotEmpty();
          _builder_7.newLine();
          _xblockexpression = _builder_7.toString();
        }
        _switchResult = _xblockexpression;
      }
    }
    if (!_matched) {
      return "//TODO - not implemented yet";
    }
    return _switchResult;
  }
  
  protected String generateAssigment(final YAssignment assignment) {
    YExpression _left = assignment.getLeft();
    if ((_left instanceof YMemberSelection)) {
      YExpression _left_1 = assignment.getLeft();
      String _generatMemberSelection = this.generatMemberSelection(((YMemberSelection) _left_1));
      String _plus = (_generatMemberSelection + " = ");
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
  
  protected String generateProperties(final YClass clazz) {
    StringConcatenation _builder = new StringConcatenation();
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
  
  protected String generateProperty(final YProperty property) {
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
  
  protected String generateInnerClasses(final YClass clazz, final String widgetName) {
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
  
  protected String generateClass(final YClass innerclazz, final String widgetName) {
    String _xifexpression = null;
    if (((innerclazz.getSuperclass() != null) && Objects.equal(innerclazz.getSuperclass().getName(), "Structure"))) {
      String _xblockexpression = null;
      {
        this.registerImport("eu.jgen.notes.dmw.lite.runtimes.XStructure");
        StringConcatenation _builder = new StringConcatenation();
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
                _builder.append("\t");
                String _generatePropertyForStructure = this.generatePropertyForStructure(((YProperty) member));
                _builder.append(_generatePropertyForStructure, "\t");
                _builder.newLineIfNotEmpty();
              }
            }
          }
        }
        _builder.append("}");
        _builder.newLine();
        _builder.newLine();
        _builder.append("public static ");
        String _name_1 = innerclazz.getName();
        _builder.append(_name_1);
        _builder.append(" instance");
        String _name_2 = innerclazz.getName();
        _builder.append(_name_2);
        _builder.append("() {");
        _builder.newLineIfNotEmpty();
        _builder.append("   ");
        String _name_3 = innerclazz.getName();
        _builder.append(_name_3, "   ");
        _builder.append(" ");
        String _firstLower = StringExtensions.toFirstLower(innerclazz.getName());
        _builder.append(_firstLower, "   ");
        _builder.append(" = new ");
        _builder.append(widgetName, "   ");
        _builder.append("().new ");
        String _name_4 = innerclazz.getName();
        _builder.append(_name_4, "   ");
        _builder.append("();");
        _builder.newLineIfNotEmpty();
        {
          EList<YMember> _members_1 = innerclazz.getMembers();
          for(final YMember member_1 : _members_1) {
            {
              if ((member_1 instanceof YProperty)) {
                _builder.append("   ");
                String _initializeProperty = this.initializeProperty(((YProperty) member_1), StringExtensions.toFirstLower(innerclazz.getName()));
                _builder.append(_initializeProperty, "   ");
                _builder.newLineIfNotEmpty();
              }
            }
          }
        }
        _builder.append("   ");
        _builder.append("return ");
        String _firstLower_1 = StringExtensions.toFirstLower(innerclazz.getName());
        _builder.append(_firstLower_1, "   ");
        _builder.append(";");
        _builder.newLineIfNotEmpty();
        _builder.append("}");
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
              String _initializeProperty = this.initializeProperty(((YProperty) member_1), StringExtensions.toFirstLower(innerclazz.getName()));
              _builder.append(_initializeProperty, "\t   ");
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
  
  protected String initializeProperty(final YProperty property, final String structureName) {
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
  
  protected String generatePropertyForStructure(final YProperty property) {
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
  
  protected String registerImport(final String name) {
    boolean _contains = this.imports.contains(name);
    if (_contains) {
      return "";
    }
    this.imports.add(name);
    return "";
  }
  
  protected String generateVariableDeclaration(final YVariableDeclaration variableDeclaration) {
    String _translateTypeName = this._langJavaGeneratorHelper.translateTypeName(variableDeclaration.getType().getName());
    String _plus = (_translateTypeName + " ");
    String _name = variableDeclaration.getName();
    String _plus_1 = (_plus + _name);
    String _plus_2 = (_plus_1 + " = ");
    String _generateExpression = this.generateExpression(variableDeclaration.getExpression());
    String _plus_3 = (_plus_2 + _generateExpression);
    return (_plus_3 + ";");
  }
  
  protected String generateExpression(final YExpression expression) {
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
        return this.generatMemberSelection(memberSelection);
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
  
  protected String generatMemberSelection(final YMemberSelection memberSelection) {
    boolean _isFunctioninvocation = memberSelection.isFunctioninvocation();
    if (_isFunctioninvocation) {
      final String terminalExpression = this.generateTermination(memberSelection.getReceiver());
      YMember _member = memberSelection.getMember();
      String _name = ((YFunction) _member).getName();
      String _plus = ((terminalExpression + ".") + _name);
      String _generateFunctionArguments = this.generateFunctionArguments(memberSelection);
      return (_plus + _generateFunctionArguments);
    } else {
      YExpression _receiver = memberSelection.getReceiver();
      if ((_receiver instanceof YMemberSelection)) {
        YExpression _receiver_1 = memberSelection.getReceiver();
        final String terminalExpression_1 = this.generateTermination(((YMemberSelection) _receiver_1).getReceiver());
        YExpression _receiver_2 = memberSelection.getReceiver();
        String _name_1 = ((YMemberSelection) _receiver_2).getMember().getName();
        final String text = ((terminalExpression_1 + ".") + _name_1);
        String _name_2 = memberSelection.getMember().getName();
        return ((text + ".") + _name_2);
      } else {
        final String terminalExpression_2 = this.generateTermination(memberSelection.getReceiver());
        String _name_3 = memberSelection.getMember().getName();
        final String text_1 = ((terminalExpression_2 + ".") + _name_3);
        return text_1;
      }
    }
  }
  
  protected String generateFunctionArguments(final YMemberSelection memberSelection) {
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
  
  protected String generateTermination(final YExpression expression) {
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
  
  public String doSwitchStatement(final YSwitchStatement switchStatement) {
    this.imports.add("com.google.common.base.Objects");
    String key = this._localNameGenerator.generateLocalName("key");
    String _matched = this._localNameGenerator.generateLocalName("_matched");
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
        String _generateBlock = this.generateBlock(switchStatement.getDefault());
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
}
