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
import eu.jgen.notes.dmw.lite.lang.YExpression;
import eu.jgen.notes.dmw.lite.lang.YFunction;
import eu.jgen.notes.dmw.lite.lang.YIfStatement;
import eu.jgen.notes.dmw.lite.lang.YMemberSelection;
import eu.jgen.notes.dmw.lite.lang.YReturn;
import eu.jgen.notes.dmw.lite.lang.YStatement;
import eu.jgen.notes.dmw.lite.lang.YVariableDeclaration;
import eu.jgen.notes.dmw.lite.lang.YWidget;
import eu.jgen.notes.dmw.lite.tests.LangInjectorProvider;
import eu.jgen.notes.dmw.lite.typing.LangTypeComputer;
import eu.jgen.notes.dmw.lite.utility.LangUtil;
import java.util.function.Consumer;
import org.eclipse.emf.common.util.EList;
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
public class LiteLangTypeComputerTest {
  @Inject
  @Extension
  private ParseHelper<YWidget> _parseHelper;
  
  @Inject
  @Extension
  private LangTypeComputer _langTypeComputer;
  
  @Inject
  @Extension
  private LangUtil _langUtil;
  
  @Inject
  @Extension
  private ValidationTestHelper _validationTestHelper;
  
  @Test
  public void thisSelf() {
    this.assertType("self", "C");
  }
  
  @Test
  public void paramRefType() {
    this.assertType("p", "P");
  }
  
  @Test
  public void varRefType() {
    this.assertType("v", "V");
  }
  
  @Test
  public void newType() {
    this.assertType("new N()", "N");
  }
  
  @Test
  public void fieldSelectionType() {
    this.assertType("self.f", "F");
  }
  
  @Test
  public void methodInvocationType() {
    this.assertType("self.m(new P())", "R");
  }
  
  @Test
  public void assignmentType() {
    this.assertType("v = new P()", "V");
  }
  
  @Test
  public void stringConstantType() {
    this.assertType("\"foo\"", "stringType");
  }
  
  @Test
  public void intConstantType() {
    this.assertType("10", "intType");
  }
  
  @Test
  public void boolConstantType() {
    this.assertType("true", "booleanType");
  }
  
  @Test
  public void nullType() {
    this.assertType("null", "nullType");
  }
  
  @Test
  public void testTypeForUnresolvedReferences() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("class C {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("U m() {");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("f ; // unresolved symbol");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("self.n(); // unresolved method ");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("self.f; // unresolved field");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("return null;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      YWidget _parse = this._parseHelper.parse(_builder);
      final Procedure1<YWidget> _function = (YWidget it) -> {
        EList<YStatement> _statements = IterableExtensions.<YFunction>head(this._langUtil.functions(IterableExtensions.<YClass>head(it.getClasses()))).getBody().getStatements();
        final Procedure1<EList<YStatement>> _function_1 = (EList<YStatement> it_1) -> {
          Assert.assertNull(this.statementExpressionType(it_1.get(0)));
          Assert.assertNull(this.statementExpressionType(it_1.get(1)));
          Assert.assertNull(this.statementExpressionType(it_1.get(2)));
        };
        ObjectExtensions.<EList<YStatement>>operator_doubleArrow(_statements, _function_1);
      };
      ObjectExtensions.<YWidget>operator_doubleArrow(_parse, _function);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testIsPrimitiveType() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("class C {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("func m() -> C {");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("return true;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      YClass _head = IterableExtensions.<YClass>head(this._parseHelper.parse(_builder).getClasses());
      final Procedure1<YClass> _function = (YClass it) -> {
        Assert.assertFalse(this._langTypeComputer.isPrimitive(it));
        Assert.assertTrue(this._langTypeComputer.isPrimitive(this._langTypeComputer.typeFor(this._langUtil.returnStatement(IterableExtensions.<YFunction>head(this._langUtil.functions(it))).getExpression())));
      };
      ObjectExtensions.<YClass>operator_doubleArrow(_head, _function);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testVarDeclExpectedType() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("v : V = null;");
    YStatement _head = IterableExtensions.<YStatement>head(this.testStatements(_builder));
    this.assertExpectedType(((YVariableDeclaration) _head).getExpression(), "V");
  }
  
  @Test
  public void testAssignmentRightExpectedType() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("self.f = null;");
    YStatement _head = IterableExtensions.<YStatement>head(this.testStatements(_builder));
    this.assertExpectedType(((YAssignment) _head).getRight(), "F");
  }
  
  @Test
  public void testAssignmentLeftExpectedType() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("self.f = null;");
    YStatement _head = IterableExtensions.<YStatement>head(this.testStatements(_builder));
    Assert.assertNull(this._langTypeComputer.expectedType(((YAssignment) _head).getLeft()));
  }
  
  @Test
  public void testReturnExpectedType() {
    YStatement _last = IterableExtensions.<YStatement>last(this.testStatements(""));
    this.assertExpectedType(((YReturn) _last).getExpression(), "R");
  }
  
  @Test
  public void testIfExpressionExpectedType() {
    YStatement _head = IterableExtensions.<YStatement>head(this.testStatements("if (e) {}"));
    this.assertExpectedType(((YIfStatement) _head).getExpression(), "booleanType");
  }
  
  @Test
  public void testMethodInvocationArgsExpectedType() {
    YStatement _head = IterableExtensions.<YStatement>head(this.testStatements("self.m(new P1(), new P2());"));
    EList<YExpression> _args = ((YMemberSelection) _head).getArgs();
    final Procedure1<EList<YExpression>> _function = (EList<YExpression> it) -> {
      this.assertExpectedType(it.get(0), "P1");
      this.assertExpectedType(it.get(1), "P2");
    };
    ObjectExtensions.<EList<YExpression>>operator_doubleArrow(_args, _function);
  }
  
  @Test
  public void testMethodInvocationReceiverExpectedType() {
    YStatement _head = IterableExtensions.<YStatement>head(this.testStatements("self.m();"));
    Assert.assertNull(this._langTypeComputer.expectedType(((YMemberSelection) _head).getReceiver()));
  }
  
  @Test
  public void testStandaloneMemberSelectionExpectedType() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("class A {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("var a : A;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("func m() -> { self.a; self.m(); return null; }");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      YWidget _parse = this._parseHelper.parse(_builder);
      final Procedure1<YWidget> _function = (YWidget it) -> {
        EList<YStatement> _statements = IterableExtensions.<YFunction>head(this._langUtil.functions(IterableExtensions.<YClass>head(it.getClasses()))).getBody().getStatements();
        final Procedure1<EList<YStatement>> _function_1 = (EList<YStatement> it_1) -> {
          YStatement _get = it_1.get(0);
          Assert.assertNull(this._langTypeComputer.expectedType(((YExpression) _get)));
          YStatement _get_1 = it_1.get(1);
          Assert.assertNull(this._langTypeComputer.expectedType(((YExpression) _get_1)));
        };
        ObjectExtensions.<EList<YStatement>>operator_doubleArrow(_statements, _function_1);
      };
      ObjectExtensions.<YWidget>operator_doubleArrow(_parse, _function);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testWrongMethodInvocationArgsExpectedType() {
    YStatement _head = IterableExtensions.<YStatement>head(this.testStatements("self.n(new P1(), new P2());"));
    EList<YExpression> _args = ((YMemberSelection) _head).getArgs();
    final Procedure1<EList<YExpression>> _function = (EList<YExpression> it) -> {
      Assert.assertNull(this._langTypeComputer.expectedType(it.get(0)));
      Assert.assertNull(this._langTypeComputer.expectedType(it.get(1)));
    };
    ObjectExtensions.<EList<YExpression>>operator_doubleArrow(_args, _function);
    YStatement _head_1 = IterableExtensions.<YStatement>head(this.testStatements("self.m(new P1(), new P2(), new P1());"));
    Assert.assertNull(this._langTypeComputer.expectedType(((YMemberSelection) _head_1).getArgs().get(2)));
  }
  
  private YWidget assertType(final CharSequence testExp, final String expectedClassName) {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("class R { }");
      _builder.newLine();
      _builder.append("class P { }");
      _builder.newLine();
      _builder.append("class V { }");
      _builder.newLine();
      _builder.append("class N { }");
      _builder.newLine();
      _builder.append("class F { }");
      _builder.newLine();
      _builder.newLine();
      _builder.append("class C {");
      _builder.newLine();
      _builder.append("  ");
      _builder.append("var f : F;");
      _builder.newLine();
      _builder.append("  ");
      _builder.newLine();
      _builder.append("  ");
      _builder.append("func m(p : P) -> R {");
      _builder.newLine();
      _builder.append("    ");
      _builder.append("v : V = null;");
      _builder.newLine();
      _builder.append("   ");
      _builder.append(testExp, "   ");
      _builder.append(";");
      _builder.newLineIfNotEmpty();
      _builder.append("    ");
      _builder.append("return null;");
      _builder.newLine();
      _builder.append("  ");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      YWidget _parse = this._parseHelper.parse(_builder);
      final Procedure1<YWidget> _function = (YWidget it) -> {
        Assert.assertEquals(expectedClassName, 
          this.statementExpressionType(IterableExtensions.<YFunction>last(this._langUtil.functions(IterableExtensions.<YClass>last(it.getClasses()))).getBody().getStatements().get(1)).getName());
      };
      return ObjectExtensions.<YWidget>operator_doubleArrow(_parse, _function);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void test1() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("class R { }");
      _builder.newLine();
      _builder.append("class P { }");
      _builder.newLine();
      _builder.append("class V { }");
      _builder.newLine();
      _builder.append("class N { }");
      _builder.newLine();
      _builder.append("class F { }");
      _builder.newLine();
      _builder.newLine();
      _builder.append("class C {");
      _builder.newLine();
      _builder.append("  ");
      _builder.append("var f : F;");
      _builder.newLine();
      _builder.append("  ");
      _builder.newLine();
      _builder.append("  ");
      _builder.append("func m(p : P) -> R {");
      _builder.newLine();
      _builder.append("    ");
      _builder.append("v : V = null;");
      _builder.newLine();
      _builder.append("    ");
      _builder.append("new N();");
      _builder.newLine();
      _builder.append("    ");
      _builder.append("return null;");
      _builder.newLine();
      _builder.append("  ");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      final YWidget model = this._parseHelper.parse(_builder);
      final Consumer<Issue> _function = (Issue it) -> {
        InputOutput.<Issue>print(it);
      };
      this._validationTestHelper.validate(model).forEach(_function);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  private YClass statementExpressionType(final YStatement s) {
    return this._langTypeComputer.typeFor(((YExpression) s));
  }
  
  private EList<YStatement> testStatements(final CharSequence statement) {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("class R {  }");
      _builder.newLine();
      _builder.append("class P1 {  }");
      _builder.newLine();
      _builder.append("class P2 {  }");
      _builder.newLine();
      _builder.append("class V {  }");
      _builder.newLine();
      _builder.append("class F {  }");
      _builder.newLine();
      _builder.newLine();
      _builder.append("class C {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("var f : F;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("func m(p1 : P1, p2 : P2) -> R {");
      _builder.newLine();
      _builder.append("\t ");
      _builder.append(statement, "\t ");
      _builder.append(";;");
      _builder.newLineIfNotEmpty();
      _builder.append("\t\t");
      _builder.append("return null;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      return IterableExtensions.<YFunction>last(this._langUtil.functions(IterableExtensions.<YClass>last(this._parseHelper.parse(_builder).getClasses()))).getBody().getStatements();
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void test2() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("class R {  }");
      _builder.newLine();
      _builder.append("class P1 {  }");
      _builder.newLine();
      _builder.append("class P2 {  }");
      _builder.newLine();
      _builder.append("class V {  }");
      _builder.newLine();
      _builder.append("class F {  }");
      _builder.newLine();
      _builder.newLine();
      _builder.append("class C {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("var f : F;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("func m(p1 : P1, p2 : P2) -> R {");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("self.m(new P1(), new P2());");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("return null;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      final YWidget model = this._parseHelper.parse(_builder);
      final Consumer<Issue> _function = (Issue it) -> {
        InputOutput.<Issue>print(it);
      };
      this._validationTestHelper.validate(model).forEach(_function);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  private void assertExpectedType(final YExpression exp, final String expectedClassName) {
    Assert.assertEquals(expectedClassName, this._langTypeComputer.expectedType(exp).getName());
  }
}
