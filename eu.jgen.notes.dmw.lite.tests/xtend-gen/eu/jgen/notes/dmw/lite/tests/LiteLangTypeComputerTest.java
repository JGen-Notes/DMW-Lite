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
  private /* LangTypeComputer */Object _langTypeComputer;
  
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
    throw new Error("Unresolved compilation problems:"
      + "\nThe method or field statements is undefined for the type XExpression"
      + "\nType mismatch: cannot convert implicit first argument from Object to Object[]"
      + "\nType mismatch: cannot convert implicit first argument from Object to Object[]"
      + "\nType mismatch: cannot convert implicit first argument from Object to Object[]"
      + "\nThe method statementExpressionType(YStatement) from the type LiteLangTypeComputerTest refers to the missing type Object"
      + "\nThe method statementExpressionType(YStatement) from the type LiteLangTypeComputerTest refers to the missing type Object"
      + "\nThe method statementExpressionType(YStatement) from the type LiteLangTypeComputerTest refers to the missing type Object"
      + "\nThe method statementExpressionType(YStatement) from the type LiteLangTypeComputerTest refers to the missing type Object"
      + "\nThe method statementExpressionType(YStatement) from the type LiteLangTypeComputerTest refers to the missing type Object"
      + "\nThe method statementExpressionType(YStatement) from the type LiteLangTypeComputerTest refers to the missing type Object"
      + "\nThe method statementExpressionType(YStatement) from the type LiteLangTypeComputerTest refers to the missing type Object"
      + "\nThe method statementExpressionType(YStatement) from the type LiteLangTypeComputerTest refers to the missing type Object"
      + "\nThe method statementExpressionType(YStatement) from the type LiteLangTypeComputerTest refers to the missing type Object"
      + "\nThe method statementExpressionType(YStatement) from the type LiteLangTypeComputerTest refers to the missing type Object"
      + "\nThe method statementExpressionType(YStatement) from the type LiteLangTypeComputerTest refers to the missing type Object"
      + "\nThe method statementExpressionType(YStatement) from the type LiteLangTypeComputerTest refers to the missing type Object"
      + "\n=> cannot be resolved"
      + "\nassertNull cannot be resolved"
      + "\nassertNull cannot be resolved"
      + "\nassertNull cannot be resolved");
  }
  
  @Test
  public void testIsPrimitiveType() {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method or field isPrimitive is undefined for the type YClass"
      + "\nThe method or field returnStatement is undefined for the type YFunction"
      + "\nassertFalse cannot be resolved"
      + "\nexpression cannot be resolved"
      + "\ntypeFor cannot be resolved"
      + "\nisPrimitive cannot be resolved"
      + "\nassertTrue cannot be resolved");
  }
  
  @Test
  public void testVarDeclExpectedType() {
    throw new Error("Unresolved compilation problems:"
      + "\nYVariableDeclaration cannot be resolved to a type."
      + "\nThe method testStatements(CharSequence) from the type LiteLangTypeComputerTest refers to the missing type Object"
      + "\nhead cannot be resolved"
      + "\nexpression cannot be resolved"
      + "\nassertExpectedType cannot be resolved");
  }
  
  @Test
  public void testAssignmentRightExpectedType() {
    throw new Error("Unresolved compilation problems:"
      + "\nYAssignment cannot be resolved to a type."
      + "\nThe method testStatements(CharSequence) from the type LiteLangTypeComputerTest refers to the missing type Object"
      + "\nhead cannot be resolved"
      + "\nright cannot be resolved"
      + "\nassertExpectedType cannot be resolved");
  }
  
  @Test
  public void testAssignmentLeftExpectedType() {
    throw new Error("Unresolved compilation problems:"
      + "\nYAssignment cannot be resolved to a type."
      + "\nThe method testStatements(CharSequence) from the type LiteLangTypeComputerTest refers to the missing type Object"
      + "\nhead cannot be resolved"
      + "\nleft cannot be resolved"
      + "\nexpectedType cannot be resolved"
      + "\nassertNull cannot be resolved");
  }
  
  @Test
  public void testReturnExpectedType() {
    throw new Error("Unresolved compilation problems:"
      + "\nYReturn cannot be resolved to a type."
      + "\nThe method testStatements(CharSequence) from the type LiteLangTypeComputerTest refers to the missing type Object"
      + "\nlast cannot be resolved"
      + "\nexpression cannot be resolved"
      + "\nassertExpectedType cannot be resolved");
  }
  
  @Test
  public void testIfExpressionExpectedType() {
    throw new Error("Unresolved compilation problems:"
      + "\nYIfStatement cannot be resolved to a type."
      + "\nThe method testStatements(CharSequence) from the type LiteLangTypeComputerTest refers to the missing type Object"
      + "\nhead cannot be resolved"
      + "\nexpression cannot be resolved"
      + "\nassertExpectedType cannot be resolved");
  }
  
  @Test
  public void testMethodInvocationArgsExpectedType() {
    throw new Error("Unresolved compilation problems:"
      + "\nYMemberSelection cannot be resolved to a type."
      + "\nType mismatch: cannot convert implicit first argument from Object to Object[]"
      + "\nType mismatch: cannot convert implicit first argument from Object to Object[]"
      + "\nThe method testStatements(CharSequence) from the type LiteLangTypeComputerTest refers to the missing type Object"
      + "\nThe method assertExpectedType(YExpression, String) from the type LiteLangTypeComputerTest refers to the missing type YExpression"
      + "\nThe method assertExpectedType(YExpression, String) from the type LiteLangTypeComputerTest refers to the missing type YExpression"
      + "\nThe method assertExpectedType(YExpression, String) from the type LiteLangTypeComputerTest refers to the missing type YExpression"
      + "\nThe method assertExpectedType(YExpression, String) from the type LiteLangTypeComputerTest refers to the missing type YExpression"
      + "\nhead cannot be resolved"
      + "\nargs cannot be resolved"
      + "\n=> cannot be resolved");
  }
  
  @Test
  public void testMethodInvocationReceiverExpectedType() {
    throw new Error("Unresolved compilation problems:"
      + "\nYMemberSelection cannot be resolved to a type."
      + "\nThe method testStatements(CharSequence) from the type LiteLangTypeComputerTest refers to the missing type Object"
      + "\nhead cannot be resolved"
      + "\nreceiver cannot be resolved"
      + "\nexpectedType cannot be resolved"
      + "\nassertNull cannot be resolved");
  }
  
  @Test
  public void testStandaloneMemberSelectionExpectedType() {
    throw new Error("Unresolved compilation problems:"
      + "\nYExpression cannot be resolved to a type."
      + "\nYExpression cannot be resolved to a type."
      + "\nThe method or field statements is undefined for the type XExpression"
      + "\nType mismatch: cannot convert implicit first argument from Object to Object[]"
      + "\nType mismatch: cannot convert implicit first argument from Object to Object[]"
      + "\n=> cannot be resolved"
      + "\nexpectedType cannot be resolved"
      + "\nassertNull cannot be resolved"
      + "\nexpectedType cannot be resolved"
      + "\nassertNull cannot be resolved");
  }
  
  @Test
  public void testWrongMethodInvocationArgsExpectedType() {
    throw new Error("Unresolved compilation problems:"
      + "\nYMemberSelection cannot be resolved to a type."
      + "\nYMemberSelection cannot be resolved to a type."
      + "\nThe method or field expectedType is undefined for the type Object"
      + "\nThe method or field expectedType is undefined for the type Object"
      + "\nType mismatch: cannot convert implicit first argument from Object to Object[]"
      + "\nType mismatch: cannot convert implicit first argument from Object to Object[]"
      + "\nThe method testStatements(CharSequence) from the type LiteLangTypeComputerTest refers to the missing type Object"
      + "\nThe method testStatements(CharSequence) from the type LiteLangTypeComputerTest refers to the missing type Object"
      + "\nhead cannot be resolved"
      + "\nargs cannot be resolved"
      + "\n=> cannot be resolved"
      + "\nassertNull cannot be resolved"
      + "\nassertNull cannot be resolved"
      + "\nhead cannot be resolved"
      + "\nargs cannot be resolved"
      + "\nget cannot be resolved"
      + "\nexpectedType cannot be resolved"
      + "\nassertNull cannot be resolved");
  }
  
  private YWidget assertType(final CharSequence testExp, final String expectedClassName) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method or field statements is undefined for the type XExpression"
      + "\nget cannot be resolved"
      + "\nstatementExpressionType cannot be resolved"
      + "\nname cannot be resolved");
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
  
  private Object statementExpressionType(final /* YStatement */Object s) {
    throw new Error("Unresolved compilation problems:"
      + "\nYExpression cannot be resolved to a type."
      + "\ntypeFor cannot be resolved");
  }
  
  private Object testStatements(final CharSequence statement) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method or field statements is undefined for the type XExpression");
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
  
  private void assertExpectedType(final /* YExpression */Object exp, final String expectedClassName) {
    throw new Error("Unresolved compilation problems:"
      + "\nexpectedType cannot be resolved"
      + "\nname cannot be resolved");
  }
}
