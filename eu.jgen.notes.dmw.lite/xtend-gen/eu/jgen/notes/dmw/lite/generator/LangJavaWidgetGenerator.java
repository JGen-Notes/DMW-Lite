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
import eu.jgen.notes.dmw.lite.lang.YAnnotJava;
import eu.jgen.notes.dmw.lite.lang.YAssignment;
import eu.jgen.notes.dmw.lite.lang.YBlock;
import eu.jgen.notes.dmw.lite.lang.YClass;
import eu.jgen.notes.dmw.lite.lang.YExpression;
import eu.jgen.notes.dmw.lite.lang.YFunction;
import eu.jgen.notes.dmw.lite.lang.YIntConstant;
import eu.jgen.notes.dmw.lite.lang.YMember;
import eu.jgen.notes.dmw.lite.lang.YMemberSelection;
import eu.jgen.notes.dmw.lite.lang.YMinus;
import eu.jgen.notes.dmw.lite.lang.YMulOrDiv;
import eu.jgen.notes.dmw.lite.lang.YParameter;
import eu.jgen.notes.dmw.lite.lang.YParenties;
import eu.jgen.notes.dmw.lite.lang.YPlus;
import eu.jgen.notes.dmw.lite.lang.YProperty;
import eu.jgen.notes.dmw.lite.lang.YReturn;
import eu.jgen.notes.dmw.lite.lang.YSelf;
import eu.jgen.notes.dmw.lite.lang.YStatement;
import eu.jgen.notes.dmw.lite.lang.YSymbolRef;
import eu.jgen.notes.dmw.lite.lang.YVariableDeclaration;
import eu.jgen.notes.dmw.lite.lang.YWidget;
import eu.jgen.notes.dmw.lite.typing.LangTypeComputer;
import eu.jgen.notes.dmw.lite.utility.LangJavaGeneratorHelper;
import eu.jgen.notes.dmw.lite.utility.LangUtil;
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
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.IteratorExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;

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
  
  @Override
  public void doGenerate(final Resource input, final IFileSystemAccess fsa) {
    final Function1<EObject, Boolean> _function = (EObject element) -> {
      return Boolean.valueOf((element instanceof YAnnotJava));
    };
    final Procedure1<EObject> _function_1 = (EObject element) -> {
      final YAnnotJava annotJava = ((YAnnotJava) element);
      String _name = annotJava.getDatabase().getName();
      boolean _equals = Objects.equal(_name, "MySQL");
      if (_equals) {
        final Function1<EObject, Boolean> _function_2 = (EObject e) -> {
          return Boolean.valueOf((e instanceof YWidget));
        };
        final EObject a = IterableExtensions.<EObject>findFirst(input.getContents(), _function_2);
        this.generateWidget(fsa, ((YWidget) a));
      }
    };
    IteratorExtensions.<EObject>forEach(IteratorExtensions.<EObject>filter(input.getAllContents(), _function), _function_1);
  }
  
  protected void generateWidget(final IFileSystemAccess fsa, final YWidget widget) {
    this.imports.clear();
    final Consumer<YClass> _function = (YClass clazz) -> {
      if (((clazz.getSuperclass() != null) && Objects.equal(clazz.getSuperclass().getName(), "Widget"))) {
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
        String _generateInnerClasses = this.generateInnerClasses(clazz);
        _builder.append(_generateInnerClasses, "   ");
        _builder.newLineIfNotEmpty();
        _builder.append("   ");
        _builder.newLine();
        _builder.append("   ");
        String _generateProperties = this.generateProperties(clazz);
        _builder.append(_generateProperties, "   ");
        _builder.newLineIfNotEmpty();
        _builder.append("   ");
        _builder.newLine();
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
    _builder.append("public ");
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
        _xblockexpression = this.generateBlock(block);
      }
      _xifexpression = _xblockexpression;
    }
    return _xifexpression;
  }
  
  protected String generateBlock(final YBlock block) {
    EList<YStatement> _statements = block.getStatements();
    for (final YStatement statement : _statements) {
      boolean _matched = false;
      if ((statement instanceof YReturn)) {
        _matched=true;
        final YReturn returnStatement = ((YReturn) statement);
        YExpression _expression = returnStatement.getExpression();
        boolean _tripleEquals = (_expression == null);
        if (_tripleEquals) {
          return "return;";
        } else {
          String _translateTypeName = this._langJavaGeneratorHelper.translateTypeName(this._langJavaGeneratorHelper.whatFuntionType(returnStatement).getName());
          String _plus = ("return new " + _translateTypeName);
          String _plus_1 = (_plus + "(");
          String _generateExpression = this.generateExpression(returnStatement.getExpression());
          String _plus_2 = (_plus_1 + _generateExpression);
          return (_plus_2 + ");");
        }
      }
      if (!_matched) {
        if ((statement instanceof YVariableDeclaration)) {
          _matched=true;
          final YVariableDeclaration variableDeclaration = ((YVariableDeclaration) statement);
          return this.generateVariableDeclaration(variableDeclaration);
        }
      }
      if (!_matched) {
        if ((statement instanceof YAssignment)) {
          _matched=true;
          final YAssignment assignment = ((YAssignment) statement);
          return this.generateAssigment(assignment);
        }
      }
      return "";
    }
    return "";
  }
  
  protected String generateAssigment(final YAssignment assignment) {
    YExpression _left = assignment.getLeft();
    String _generatMemberSelection = this.generatMemberSelection(((YMemberSelection) _left));
    String _plus = (_generatMemberSelection + " = ");
    String _generateExpression = this.generateExpression(assignment.getRight());
    return (_plus + _generateExpression);
  }
  
  protected String generateProperties(final YClass clazz) {
    StringConcatenation _builder = new StringConcatenation();
    {
      EList<YMember> _members = clazz.getMembers();
      for(final YMember member : _members) {
        {
          if ((member instanceof YProperty)) {
            String _generatePropertyForWidget = this.generatePropertyForWidget(((YProperty) member));
            _builder.append(_generatePropertyForWidget);
            _builder.newLineIfNotEmpty();
          }
        }
      }
    }
    return _builder.toString();
  }
  
  protected String generatePropertyForWidget(final YProperty property) {
    String _xblockexpression = null;
    {
      String _findPackageName = this._langJavaGeneratorHelper.findPackageName(property);
      String _plus = (_findPackageName + ".");
      String _name = property.getType().getName();
      String _plus_1 = (_plus + _name);
      this.registerImport(_plus_1);
      StringConcatenation _builder = new StringConcatenation();
      String _documentation = this._langJavaGeneratorHelper.getDocumentation(property);
      _builder.append(_documentation);
      _builder.append("  ");
      _builder.newLineIfNotEmpty();
      _builder.append("public ");
      String _name_1 = property.getType().getName();
      _builder.append(_name_1);
      _builder.append(" ");
      String _name_2 = property.getName();
      _builder.append(_name_2);
      _builder.append(";");
      _xblockexpression = _builder.toString();
    }
    return _xblockexpression;
  }
  
  protected String generateInnerClasses(final YClass clazz) {
    StringConcatenation _builder = new StringConcatenation();
    {
      EList<YClass> _inners = clazz.getInners();
      for(final YClass innerclazz : _inners) {
        String _generateClass = this.generateClass(innerclazz);
        _builder.append(_generateClass);
      }
    }
    _builder.newLineIfNotEmpty();
    return _builder.toString();
  }
  
  protected String generateClass(final YClass innerclazz) {
    String _xifexpression = null;
    if (((innerclazz.getSuperclass() != null) && Objects.equal(innerclazz.getSuperclass().getName(), "Structure"))) {
      String _xblockexpression = null;
      {
        this.imports.add("eu.jgen.notes.dmw.lite.runtimes.XStructure");
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
        _xblockexpression = _builder.toString();
      }
      _xifexpression = _xblockexpression;
    }
    return _xifexpression;
  }
  
  protected String generatePropertyForStructure(final YProperty property) {
    String _xblockexpression = null;
    {
      String _translateTypeName = this._langJavaGeneratorHelper.translateTypeName(property.getType().getName());
      String _plus = ("eu.jgen.notes.dmw.lite.runtimes." + _translateTypeName);
      this.imports.add(_plus);
      StringConcatenation _builder = new StringConcatenation();
      String _documentation = this._langJavaGeneratorHelper.getDocumentation(property);
      _builder.append(_documentation);
      _builder.append("  ");
      _builder.newLineIfNotEmpty();
      _builder.append("public ");
      String _translateTypeName_1 = this._langJavaGeneratorHelper.translateTypeName(property.getType().getName());
      _builder.append(_translateTypeName_1);
      _builder.append(" ");
      String _name = property.getName();
      _builder.append(_name);
      _builder.append(";");
      _xblockexpression = _builder.toString();
    }
    return _xblockexpression;
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
    String _plus_2 = (_plus_1 + " = new ");
    String _translateTypeName_1 = this._langJavaGeneratorHelper.translateTypeName(variableDeclaration.getType().getName());
    String _plus_3 = (_plus_2 + _translateTypeName_1);
    String _plus_4 = (_plus_3 + "(");
    String _generateExpression = this.generateExpression(variableDeclaration.getExpression());
    String _plus_5 = (_plus_4 + _generateExpression);
    return (_plus_5 + ");");
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
      if ((expression instanceof YParenties)) {
        _matched=true;
        String _generateExpression_6 = this.generateExpression(((YParenties) expression).getA());
        String _plus_5 = ("(" + _generateExpression_6);
        return (_plus_5 + ")");
      }
    }
    if (!_matched) {
      if ((expression instanceof YSymbolRef)) {
        _matched=true;
        final YSymbolRef symbolRef = ((YSymbolRef) expression);
        String _name = symbolRef.getSymbol().getName();
        return (_name + ".value");
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
      String _plus_1 = (_plus + _generateFunctionArguments);
      return (_plus_1 + ".value");
    } else {
      YExpression _receiver = memberSelection.getReceiver();
      final String terminalExpression_1 = this.generateTermination(((YMemberSelection) _receiver).getReceiver());
      YExpression _receiver_1 = memberSelection.getReceiver();
      String _name_1 = ((YMemberSelection) _receiver_1).getMember().getName();
      final String text = ((terminalExpression_1 + ".") + _name_1);
      String _name_2 = memberSelection.getMember().getName();
      String _plus_2 = ((text + ".") + _name_2);
      return (_plus_2 + ".value");
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
        _builder.append("new XInt(");
        String _generateExpression = this.generateExpression(arg);
        _builder.append(_generateExpression);
        _builder.append(")");
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
      _switchResult = null;
    }
    return _switchResult;
  }
}
