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
package eu.jgen.notes.dmw.lite.tests;

import com.google.inject.Inject;
import eu.jgen.notes.dmw.lite.lang.YAssignment;
import eu.jgen.notes.dmw.lite.lang.YClass;
import eu.jgen.notes.dmw.lite.lang.YFunction;
import eu.jgen.notes.dmw.lite.lang.YIfStatement;
import eu.jgen.notes.dmw.lite.lang.YMemberSelection;
import eu.jgen.notes.dmw.lite.lang.YNew;
import eu.jgen.notes.dmw.lite.lang.YNull;
import eu.jgen.notes.dmw.lite.lang.YParameter;
import eu.jgen.notes.dmw.lite.lang.YReturn;
import eu.jgen.notes.dmw.lite.lang.YSelf;
import eu.jgen.notes.dmw.lite.lang.YStatement;
import eu.jgen.notes.dmw.lite.lang.YSymbolRef;
import eu.jgen.notes.dmw.lite.lang.YVariableDeclaration;
import eu.jgen.notes.dmw.lite.lang.YWidget;
import eu.jgen.notes.dmw.lite.tests.LangInjectorProvider;
import eu.jgen.notes.dmw.lite.utility.LangUtil;
import java.util.function.Consumer;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.XtextRunner;
import org.eclipse.xtext.testing.util.ParseHelper;
import org.eclipse.xtext.testing.validation.ValidationTestHelper;
import org.eclipse.xtext.validation.Issue;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.InputOutput;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(XtextRunner.class)
@InjectWith(LangInjectorProvider.class)
@SuppressWarnings("all")
public class LiteLangParsingTest1 {
  @Inject
  @Extension
  private ParseHelper<YWidget> parseHelper;
  
  @Inject
  @Extension
  private LangUtil _langUtil;
  
  @Inject
  @Extension
  private ValidationTestHelper _validationTestHelper;
  
  @Test
  public void loadModel() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("class C {");
      _builder.newLine();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      final YWidget result = this.parseHelper.parse(_builder);
      Assert.assertNotNull(result);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testThenBlockWithoutStatements() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("class C {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("var c : C;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("func m() -> C {");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("if (true) {");
      _builder.newLine();
      _builder.append("\t\t\t");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("return self.c;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      final String text = _builder.toString();
      final Consumer<Issue> _function = (Issue it) -> {
        InputOutput.println();
      };
      this._validationTestHelper.validate(this.parseHelper.parse(text)).forEach(_function);
      YWidget _parse = this.parseHelper.parse(text);
      final Procedure1<YWidget> _function_1 = (YWidget it) -> {
        YStatement _head = IterableExtensions.<YStatement>head(IterableExtensions.<YFunction>head(this._langUtil.functions(IterableExtensions.<YClass>head(it.getClasses()))).getBody().getStatements());
        final YIfStatement ifS = ((YIfStatement) _head);
        Assert.assertTrue(ifS.getThenBlock().getStatements().isEmpty());
      };
      ObjectExtensions.<YWidget>operator_doubleArrow(_parse, _function_1);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testElse() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("class C {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("var c : C;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("func m() -> C  {");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("if (true)");
      _builder.newLine();
      _builder.append("\t\t\t");
      _builder.append("if (false)");
      _builder.newLine();
      _builder.append("\t\t\t\t");
      _builder.append("self.c = null;");
      _builder.newLine();
      _builder.append("\t\t\t");
      _builder.append("else");
      _builder.newLine();
      _builder.append("\t\t\t\t");
      _builder.append("self.c = null;");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("return self.c;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      final String text = _builder.toString();
      final Consumer<Issue> _function = (Issue it) -> {
        InputOutput.println();
      };
      this._validationTestHelper.validate(this.parseHelper.parse(text)).forEach(_function);
      YWidget _parse = this.parseHelper.parse(text);
      final Procedure1<YWidget> _function_1 = (YWidget it) -> {
        YStatement _head = IterableExtensions.<YStatement>head(IterableExtensions.<YFunction>head(this._langUtil.functions(IterableExtensions.<YClass>head(it.getClasses()))).getBody().getStatements());
        final YIfStatement ifS = ((YIfStatement) _head);
        Assert.assertNull(ifS.getElseBlock());
      };
      ObjectExtensions.<YWidget>operator_doubleArrow(_parse, _function_1);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testElseWithBlock() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("class C {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("var c : C;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("func m() -> C {");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("if (true) {");
      _builder.newLine();
      _builder.append("\t\t\t");
      _builder.append("if (false)");
      _builder.newLine();
      _builder.append("\t\t\t\t");
      _builder.append("self.c = null;");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("} else");
      _builder.newLine();
      _builder.append("\t\t\t\t");
      _builder.append("self.c = null;");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("return self.c;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      final String text = _builder.toString();
      final Consumer<Issue> _function = (Issue it) -> {
        InputOutput.println();
      };
      this._validationTestHelper.validate(this.parseHelper.parse(text)).forEach(_function);
      YWidget _parse = this.parseHelper.parse(text);
      final Procedure1<YWidget> _function_1 = (YWidget it) -> {
        YStatement _head = IterableExtensions.<YStatement>head(IterableExtensions.<YFunction>head(this._langUtil.functions(IterableExtensions.<YClass>head(it.getClasses()))).getBody().getStatements());
        final YIfStatement ifS = ((YIfStatement) _head);
        Assert.assertNotNull(ifS.getElseBlock());
      };
      ObjectExtensions.<YWidget>operator_doubleArrow(_parse, _function_1);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testMemberSelectionLeftAssociativity() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("class A {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("func m() -> A { return self.m().m(); }");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      final String text = _builder.toString();
      final Consumer<Issue> _function = (Issue it) -> {
        InputOutput.println();
      };
      this._validationTestHelper.validate(this.parseHelper.parse(text)).forEach(_function);
      this.assertAssociativity(IterableExtensions.<YStatement>last(IterableExtensions.<YFunction>head(this._langUtil.functions(IterableExtensions.<YClass>head(this.parseHelper.parse(text).getClasses()))).getBody().getStatements()), "((this.m).m)");
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testAssignmentRightAssociativity() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("class A {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("func m() -> A {");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("f : A = null;");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("g : A = null;");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("f = g = null;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      final String text = _builder.toString();
      final Consumer<Issue> _function = (Issue it) -> {
        InputOutput.println();
      };
      this._validationTestHelper.validate(this.parseHelper.parse(text)).forEach(_function);
      this.assertAssociativity(IterableExtensions.<YStatement>last(IterableExtensions.<YFunction>head(this._langUtil.functions(IterableExtensions.<YClass>head(this.parseHelper.parse(text).getClasses()))).getBody().getStatements()), "(f = (g = null))");
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testParameterAndVariable() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("class A {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("func m(A p) -> A {");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("v : A = null;");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("return null;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      final String text = _builder.toString();
      final Consumer<Issue> _function = (Issue it) -> {
        InputOutput.println();
      };
      this._validationTestHelper.validate(this.parseHelper.parse(text)).forEach(_function);
      YFunction _head = IterableExtensions.<YFunction>head(this._langUtil.functions(IterableExtensions.<YClass>head(this.parseHelper.parse(text).getClasses())));
      final Procedure1<YFunction> _function_1 = (YFunction it) -> {
        Assert.assertNotNull(IterableExtensions.<YParameter>head(it.getParams()));
        YStatement _head_1 = IterableExtensions.<YStatement>head(it.getBody().getStatements());
        Assert.assertTrue((_head_1 instanceof YVariableDeclaration));
      };
      ObjectExtensions.<YFunction>operator_doubleArrow(_head, _function_1);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  private void assertAssociativity(final YStatement s, final CharSequence expected) {
    Assert.assertEquals(expected.toString(), this.stringRepr(s));
  }
  
  private String stringRepr(final YStatement s) {
    String _switchResult = null;
    boolean _matched = false;
    if (s instanceof YAssignment) {
      _matched=true;
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("(");
      String _stringRepr = this.stringRepr(((YAssignment)s).getLeft());
      _builder.append(_stringRepr);
      _builder.append(" = ");
      String _stringRepr_1 = this.stringRepr(((YAssignment)s).getRight());
      _builder.append(_stringRepr_1);
      _builder.append(")");
      _switchResult = _builder.toString();
    }
    if (!_matched) {
      if (s instanceof YMemberSelection) {
        _matched=true;
        StringConcatenation _builder = new StringConcatenation();
        _builder.append("(");
        String _stringRepr = this.stringRepr(((YMemberSelection)s).getReceiver());
        _builder.append(_stringRepr);
        _builder.append(".");
        String _name = ((YMemberSelection)s).getMember().getName();
        _builder.append(_name);
        _builder.append(")");
        _switchResult = _builder.toString();
      }
    }
    if (!_matched) {
      if (s instanceof YSelf) {
        _matched=true;
        _switchResult = "this";
      }
    }
    if (!_matched) {
      if (s instanceof YNew) {
        _matched=true;
        StringConcatenation _builder = new StringConcatenation();
        _builder.append("new ");
        String _name = ((YNew)s).getType().getName();
        _builder.append(_name);
        _builder.append("()");
        _switchResult = _builder.toString();
      }
    }
    if (!_matched) {
      if (s instanceof YNull) {
        _matched=true;
        _switchResult = "null";
      }
    }
    if (!_matched) {
      if (s instanceof YSymbolRef) {
        _matched=true;
        _switchResult = ((YSymbolRef)s).getSymbol().getName();
      }
    }
    if (!_matched) {
      if (s instanceof YReturn) {
        _matched=true;
        _switchResult = this.stringRepr(((YReturn)s).getExpression());
      }
    }
    return _switchResult;
  }
}
