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
import com.google.inject.Provider;
import eu.jgen.notes.dmw.lite.lang.LangPackage;
import eu.jgen.notes.dmw.lite.lang.YClass;
import eu.jgen.notes.dmw.lite.lang.YWidget;
import eu.jgen.notes.dmw.lite.tests.LangInjectorProvider;
import eu.jgen.notes.dmw.lite.utility.LangLib;
import eu.jgen.notes.dmw.lite.validation.LangValidator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.XtextRunner;
import org.eclipse.xtext.testing.util.ParseHelper;
import org.eclipse.xtext.testing.validation.ValidationTestHelper;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(XtextRunner.class)
@InjectWith(LangInjectorProvider.class)
@SuppressWarnings("all")
public class LiteLangValidatorTest1 {
  @Inject
  @Extension
  private ParseHelper<YWidget> _parseHelper;
  
  @Inject
  @Extension
  private ValidationTestHelper _validationTestHelper;
  
  @Inject
  @Extension
  private LangLib _langLib;
  
  @Inject
  private Provider<ResourceSet> resourceSetProvider;
  
  @Test
  public void testClassHierarchyCycle() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("class A : C {}");
      _builder.newLine();
      _builder.append("class C : B {}");
      _builder.newLine();
      _builder.append("class B : A {}");
      _builder.newLine();
      YWidget _parse = this._parseHelper.parse(_builder);
      final Procedure1<YWidget> _function = (YWidget it) -> {
        this.assertClassHierarchyCycle(it, "A");
        this.assertClassHierarchyCycle(it, "B");
        this.assertClassHierarchyCycle(it, "C");
      };
      ObjectExtensions.<YWidget>operator_doubleArrow(_parse, _function);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testEntityHierarchyCycle() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("@entity A : C {}");
      _builder.newLine();
      _builder.append("@entity C : B {}");
      _builder.newLine();
      _builder.append("@entity B : A {}");
      _builder.newLine();
      YWidget _parse = this._parseHelper.parse(_builder);
      final Procedure1<YWidget> _function = (YWidget it) -> {
        this.assertEntityHierarchyCycle(it, "A");
        this.assertEntityHierarchyCycle(it, "B");
        this.assertEntityHierarchyCycle(it, "C");
      };
      ObjectExtensions.<YWidget>operator_doubleArrow(_parse, _function);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testNoHierarchyCycle() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("class A : C {}");
      _builder.newLine();
      _builder.append("class C : B {}");
      _builder.newLine();
      _builder.append("class B {}");
      _builder.newLine();
      this._validationTestHelper.assertNoErrors(this._parseHelper.parse(_builder));
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testInvocationOnField() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class A {");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("var f : A;");
    _builder.newLine();
    _builder.append("   ");
    _builder.append("func m() -> A {");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("return self.f();");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    final Procedure1<String> _function = (String it) -> {
      try {
        this._validationTestHelper.assertError(this._parseHelper.parse(it), 
          LangPackage.eINSTANCE.getYMemberSelection(), 
          LangValidator.FUNCTION_INVOCATION_ON_PROPERTY, 
          it.lastIndexOf("("), 1, 
          "Function invocation on a property");
      } catch (Throwable _e) {
        throw Exceptions.sneakyThrow(_e);
      }
    };
    ObjectExtensions.<String>operator_doubleArrow(_builder.toString(), _function);
  }
  
  @Test
  public void testFieldSelectionOnMethod() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class A {");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("func  m() -> A{");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("return self.m;");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    final Procedure1<String> _function = (String it) -> {
      try {
        this._validationTestHelper.assertError(this._parseHelper.parse(it), 
          LangPackage.eINSTANCE.getYMemberSelection(), 
          LangValidator.PROPERTY_SELECTION_ON_FUNCTION, 
          it.lastIndexOf("m"), 1, 
          "Property selection on a function");
      } catch (Throwable _e) {
        throw Exceptions.sneakyThrow(_e);
      }
    };
    ObjectExtensions.<String>operator_doubleArrow(_builder.toString(), _function);
  }
  
  @Test
  public void testCorrectMemberSelection() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("class A {");
      _builder.newLine();
      _builder.append("  ");
      _builder.append("var f : A ;");
      _builder.newLine();
      _builder.append("  ");
      _builder.append("func  m() -> A{");
      _builder.newLine();
      _builder.append("  \t ");
      _builder.append("v : A = self.f;");
      _builder.newLine();
      _builder.append("  \t ");
      _builder.append("return self.m();");
      _builder.newLine();
      _builder.append("  ");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      this._validationTestHelper.assertNoErrors(this._parseHelper.parse(_builder));
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testUnreachableCode() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("class C {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("func m() -> C{");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("return null;");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("self.m();");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      this._validationTestHelper.assertError(this._parseHelper.parse(_builder), 
        LangPackage.eINSTANCE.getYMemberSelection(), 
        LangValidator.UNREACHABLE_CODE, 
        "Unreachable code");
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testUnreachableCode2() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("class C {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("func m() -> C{");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("return null;");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("i : C = null;");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("self.m();");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      this._validationTestHelper.assertError(this._parseHelper.parse(_builder), 
        LangPackage.eINSTANCE.getYVariableDeclaration(), 
        LangValidator.UNREACHABLE_CODE, 
        "Unreachable code");
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testUnreachableCodeOnlyOnce() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("class C {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("func m() -> C{");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("return null;");
      _builder.newLine();
      _builder.append("\t\t ");
      _builder.append("i : C = null; // error only here");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("return null;");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("return null; // no error here");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      YWidget _parse = this._parseHelper.parse(_builder);
      final Procedure1<YWidget> _function = (YWidget it) -> {
        this._validationTestHelper.assertError(it, 
          LangPackage.eINSTANCE.getYVariableDeclaration(), 
          LangValidator.UNREACHABLE_CODE, 
          "Unreachable code");
        Assert.assertEquals(1, this._validationTestHelper.validate(it).size());
      };
      ObjectExtensions.<YWidget>operator_doubleArrow(_parse, _function);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testUnreachableCodeInsideIf() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("class C {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("func m() -> C {");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("if (true) {");
      _builder.newLine();
      _builder.append("\t\t\t");
      _builder.append("return null;");
      _builder.newLine();
      _builder.append("\t\t\t");
      _builder.append("i : C  = null;");
      _builder.newLine();
      _builder.append("\t\t\t");
      _builder.append("self.m();");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      this._validationTestHelper.assertError(this._parseHelper.parse(_builder), 
        LangPackage.eINSTANCE.getYVariableDeclaration(), 
        LangValidator.UNREACHABLE_CODE, 
        "Unreachable code");
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testNoUnreachableCode() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("class C {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("func m() -> C{");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("self.m();");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("return null;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      this._validationTestHelper.assertNoErrors(this._parseHelper.parse(_builder));
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testMissingFinalReturn() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("class C {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("func m() -> C {");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("self.m();");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      this._validationTestHelper.assertError(this._parseHelper.parse(_builder), 
        LangPackage.eINSTANCE.getYFunction(), 
        LangValidator.FUNCTION_FINAL_RETURN, 
        "Function must end with a return statement");
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testDuplicateClasses() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class C {}");
    _builder.newLine();
    _builder.append("class C {}");
    _builder.newLine();
    this.assertDuplicate(_builder.toString(), LangPackage.eINSTANCE.getYClass(), "class", "C");
  }
  
  @Test
  public void testDuplicateFields() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class C {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("var f : C;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("var f : C;");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    this.assertDuplicate(_builder.toString(), LangPackage.eINSTANCE.getYProperty(), "property", "f");
  }
  
  @Test
  public void testDuplicateMethods() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class C {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("func m() -> C { return null; }");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("func m() -> C { return null; }");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    this.assertDuplicate(_builder.toString(), LangPackage.eINSTANCE.getYFunction(), "function", "m");
  }
  
  @Test
  public void testDuplicateParams() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class C {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("func m(p : C, p : C) -> C { return null; }");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    this.assertDuplicate(_builder.toString(), LangPackage.eINSTANCE.getYParameter(), "parameter", "p");
  }
  
  @Test
  public void testDuplicateVariables() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class C {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("func m() -> C {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("v : C = null;");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("if (true)");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("v : C = null;");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return null;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    this.assertDuplicate(_builder.toString(), LangPackage.eINSTANCE.getYVariableDeclaration(), "variable", "v");
  }
  
  @Test
  public void testFieldAndMethodWithTheSameNameAreOK() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("class C {");
      _builder.newLine();
      _builder.append("  ");
      _builder.append("var f: C;");
      _builder.newLine();
      _builder.append("  ");
      _builder.append("func f() -> C { return null; }");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      this._validationTestHelper.assertNoErrors(this._parseHelper.parse(_builder));
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testVariableDeclExpIncompatibleTypes() {
    this.assertIncompatibleTypes("v : A = new C(); return null;", LangPackage.eINSTANCE.getYNew(), "A", "C");
  }
  
  @Test
  public void testReturnExpIncompatibleTypes() {
    this.assertIncompatibleTypes("return new C();", LangPackage.eINSTANCE.getYNew(), "A", "C");
  }
  
  @Test
  public void testArgExpIncompatibleTypes() {
    this.assertIncompatibleTypes("this.m(new C());", LangPackage.eINSTANCE.getYNew(), "A", "C");
  }
  
  @Test
  public void testIfExpressionIncompatibleTypes() {
    this.assertIncompatibleTypes("if (new C()) { return null; } ", 
      LangPackage.eINSTANCE.getYNew(), 
      "booleanType", "C");
  }
  
  @Test
  public void testAssignmentIncompatibleTypes() {
    this.assertIncompatibleTypes("v : A = null; v = new C();", 
      LangPackage.eINSTANCE.getYNew(), 
      "A", "C");
  }
  
  @Test
  public void testInvalidNumberOfArgs() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("class A {}");
      _builder.newLine();
      _builder.append("class B {}");
      _builder.newLine();
      _builder.append("class C {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("func m(a : A, b : B) -> C { return self.m(new B()); }");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      YWidget _parse = this._parseHelper.parse(_builder);
      EClass _yMemberSelection = LangPackage.eINSTANCE.getYMemberSelection();
      StringConcatenation _builder_1 = new StringConcatenation();
      _builder_1.append("Invalid number of arguments: expected 2 but was 1");
      this._validationTestHelper.assertError(_parse, _yMemberSelection, 
        LangValidator.INVALID_ARGS, _builder_1.toString());
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testWrongMethodOverride() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("class A {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("func m(a : A) -> A{ return null; }");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("func n(a : A) -> B{ return null; }");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _builder.newLine();
      _builder.append("class B : A {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("// parameters must have the same type");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("func m(a : B) -> A{ return null; }");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("// return type cannot be a supertype");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("func n(a : A) -> A{ return null; }");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _builder.newLine();
      _builder.append("class C : A {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("// return type can be a subtype");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("func m(a : A) -> B { return null; }");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      YWidget _parse = this._parseHelper.parse(_builder);
      final Procedure1<YWidget> _function = (YWidget it) -> {
        this._validationTestHelper.assertError(it, LangPackage.eINSTANCE.getYFunction(), 
          LangValidator.WRONG_FUNCTION_OVERRIDE, 
          "The function \'m\' must override a superclass function");
        this._validationTestHelper.assertError(it, LangPackage.eINSTANCE.getYFunction(), 
          LangValidator.WRONG_FUNCTION_OVERRIDE, 
          "The function \'n\' must override a superclass function");
        Assert.assertEquals(2, this._validationTestHelper.validate(it).size());
      };
      ObjectExtensions.<YWidget>operator_doubleArrow(_parse, _function);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testCorrectMethodOverride() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("class A {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("func m(a : A) -> A { return null; }");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _builder.newLine();
      _builder.append("class B : A {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("func m(a : A) -> A { return null; }");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _builder.newLine();
      _builder.append("class C : A {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("// return type can be a subtype");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("func m(a : A) -> B { return null; }");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      this._validationTestHelper.assertNoErrors(this._parseHelper.parse(_builder));
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testFieldAccessibility() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("class A {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("var private priv : A;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("var protected  prot : A;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("var public pub : A ;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("func m() -> A{");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("self.priv = null; // private field");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("self.prot = null; // protected field");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("self.pub = null; // public field");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("return null;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _builder.newLine();
      _builder.append("class B : A {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("func m() -> A{");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("self.priv = null; // private field ERROR");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("self.prot = null; // protected field");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("self.pub = null; // public field");
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
        Assert.assertEquals(1, this._validationTestHelper.validate(it).size());
        this._validationTestHelper.assertError(it, 
          LangPackage.eINSTANCE.getYMemberSelection(), 
          LangValidator.MEMBER_NOT_ACCESSIBLE, 
          "The private member priv is not accessible here");
      };
      ObjectExtensions.<YWidget>operator_doubleArrow(_parse, _function);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testFieldAccessibilityInSubclass() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("class A {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("var private A priv;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("var protected A prot;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("public A pub;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("A m() {");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("this.priv = null; // private field");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("this.prot = null; // protected field");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("this.pub = null; // public field");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("return null;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _builder.newLine();
      _builder.append("class C {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("A m() {");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("(new A()).priv = null; // private field ERROR");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("(new A()).prot = null; // protected field ERROR");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("(new A()).pub = null; // public field");
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
        Assert.assertEquals(2, this._validationTestHelper.validate(it).size());
        this._validationTestHelper.assertError(it, 
          LangPackage.eINSTANCE.getYMemberSelection(), 
          LangValidator.MEMBER_NOT_ACCESSIBLE, 
          "The private member priv is not accessible here");
        this._validationTestHelper.assertError(it, 
          LangPackage.eINSTANCE.getYMemberSelection(), 
          LangValidator.MEMBER_NOT_ACCESSIBLE, 
          "The protected member prot is not accessible here");
      };
      ObjectExtensions.<YWidget>operator_doubleArrow(_parse, _function);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testMethodAccessibility() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("class A {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("private func priv() -> A{ return null; }");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("protected func prot() -> A { return null; }");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("public func pub() -> A { return null; }");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("func m() -> A{");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("a : A = null;");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("a = self.priv(); // private method");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("a = self.prot(); // protected method");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("a = self.pub(); // public method");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("return null;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _builder.newLine();
      _builder.append("class B : A {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("func m() -> A{");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("a : A = null;");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("a = self.priv(); // private method ERROR");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("a = self.prot(); // protected method");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("a = self.pub(); // public method");
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
        Assert.assertEquals(1, this._validationTestHelper.validate(it).size());
        this._validationTestHelper.assertError(it, 
          LangPackage.eINSTANCE.getYMemberSelection(), 
          LangValidator.MEMBER_NOT_ACCESSIBLE, 
          "The private member priv is not accessible here");
      };
      ObjectExtensions.<YWidget>operator_doubleArrow(_parse, _function);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testMethodAccessibilityInSubclass() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("class A {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("private func priv() -> A { return null; }");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("protected func prot() -> A { return null; }");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("public func pub() -> A { return null; }");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("func A m() -> A{");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("a : A = null;");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("a = self.priv(); // private method");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("a = self.prot(); // protected method");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("a = self.pub(); // public method");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("return null;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _builder.newLine();
      _builder.append("class C {");
      _builder.newLine();
      _builder.append("func  m() -> A {");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("a : A = null;");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("a = (new A()).priv(); // private method ERROR");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("a = (new A()).prot(); // protected method ERROR");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("a = (new A()).pub(); // public method");
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
        Assert.assertEquals(4, this._validationTestHelper.validate(it).size());
        this._validationTestHelper.assertError(it, 
          LangPackage.eINSTANCE.getYMemberSelection(), 
          LangValidator.MEMBER_NOT_ACCESSIBLE, 
          "The private member priv is not accessible here");
        this._validationTestHelper.assertError(it, 
          LangPackage.eINSTANCE.getYMemberSelection(), 
          LangValidator.MEMBER_NOT_ACCESSIBLE, 
          "The protected member prot is not accessible here");
      };
      ObjectExtensions.<YWidget>operator_doubleArrow(_parse, _function);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testUnresolvedMethodAccessibility() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("class A {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("func m() -> A{");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("a : A = self.foo();");
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
        Assert.assertEquals(1, this._validationTestHelper.validate(it).size());
      };
      ObjectExtensions.<YWidget>operator_doubleArrow(_parse, _function);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testTwoFiles() {
    try {
      final ResourceSet resourceSet = this.resourceSetProvider.get();
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("class B : A {}");
      final YWidget first = this._parseHelper.parse(_builder, resourceSet);
      StringConcatenation _builder_1 = new StringConcatenation();
      _builder_1.append("class A { var b : B; }");
      final YWidget second = this._parseHelper.parse(_builder_1, resourceSet);
      this._validationTestHelper.assertNoErrors(first);
      this._validationTestHelper.assertNoErrors(second);
      Assert.assertSame(IterableExtensions.<YClass>head(second.getClasses()), IterableExtensions.<YClass>head(first.getClasses()).getSuperclass());
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testTwoFilesAlternative() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("class B : A {}");
      final YWidget first = this._parseHelper.parse(_builder);
      StringConcatenation _builder_1 = new StringConcatenation();
      _builder_1.append("class A { var b : B; } ");
      final YWidget second = this._parseHelper.parse(_builder_1, first.eResource().getResourceSet());
      this._validationTestHelper.assertNoErrors(first);
      this._validationTestHelper.assertNoErrors(second);
      Assert.assertSame(IterableExtensions.<YClass>head(second.getClasses()), IterableExtensions.<YClass>head(first.getClasses()).getSuperclass());
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testPackagesAndClassQualifiedNames() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("package my.first.pack;");
      _builder.newLine();
      _builder.append("class B : my.second.pack.A {}");
      _builder.newLine();
      final YWidget first = this._parseHelper.parse(_builder);
      StringConcatenation _builder_1 = new StringConcatenation();
      _builder_1.append("package my.second.pack;");
      _builder_1.newLine();
      _builder_1.append("class A {");
      _builder_1.newLine();
      _builder_1.append("  ");
      _builder_1.append("my.first.pack.B b;");
      _builder_1.newLine();
      _builder_1.append("}");
      _builder_1.newLine();
      final YWidget second = this._parseHelper.parse(_builder_1, first.eResource().getResourceSet());
      this._validationTestHelper.assertNoErrors(first);
      this._validationTestHelper.assertNoErrors(second);
      Assert.assertSame(IterableExtensions.<YClass>head(second.getClasses()), IterableExtensions.<YClass>head(first.getClasses()).getSuperclass());
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testImports() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("package my.first.pack;");
      _builder.newLine();
      _builder.append("class C1 { }");
      _builder.newLine();
      _builder.append("class C2 { }");
      _builder.newLine();
      final YWidget first = this._parseHelper.parse(_builder);
      StringConcatenation _builder_1 = new StringConcatenation();
      _builder_1.append("package my.second.pack;");
      _builder_1.newLine();
      _builder_1.append("class D1 { }");
      _builder_1.newLine();
      _builder_1.append("class D2 { }");
      _builder_1.newLine();
      this._parseHelper.parse(_builder_1, first.eResource().getResourceSet());
      StringConcatenation _builder_2 = new StringConcatenation();
      _builder_2.append("package my.third.pack;");
      _builder_2.newLine();
      _builder_2.append("import my.first.pack.C1;");
      _builder_2.newLine();
      _builder_2.append("import my.second.pack.*;");
      _builder_2.newLine();
      _builder_2.newLine();
      _builder_2.append("class E : C1 { // C1 is imported");
      _builder_2.newLine();
      _builder_2.append("  ");
      _builder_2.append("my.first.pack.C2 c; // C2 not imported, but fully qualified");
      _builder_2.newLine();
      _builder_2.append("  ");
      _builder_2.append("var  d1 : D1; // D1 imported by wildcard");
      _builder_2.newLine();
      _builder_2.append("  ");
      _builder_2.append("var  d2 : D2; // D2 imported by wildcard");
      _builder_2.newLine();
      _builder_2.append("}");
      _builder_2.newLine();
      this._validationTestHelper.assertNoErrors(this._parseHelper.parse(_builder_2, first.eResource().getResourceSet()));
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testDuplicateClassesAcrossFiles() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("package my.first.pack;");
      _builder.newLine();
      _builder.newLine();
      _builder.append("class C {}");
      final YWidget first = this._parseHelper.parse(_builder);
      StringConcatenation _builder_1 = new StringConcatenation();
      _builder_1.append("package my.first.pack;");
      _builder_1.newLine();
      _builder_1.append("class D {}");
      _builder_1.newLine();
      _builder_1.append("class C {}");
      _builder_1.newLine();
      this._validationTestHelper.assertError(this._parseHelper.parse(_builder_1, first.eResource().getResourceSet()), 
        LangPackage.eINSTANCE.getYClass(), 
        LangValidator.DUPLICATE_CLASS, 
        "The type C is already defined");
      this._validationTestHelper.assertError(first, 
        LangPackage.eINSTANCE.getYClass(), 
        LangValidator.DUPLICATE_CLASS, 
        "The type C is already defined");
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testClassesWithSameNameButWithDifferentQNAreOK() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("package my.first.pack;");
      _builder.newLine();
      _builder.newLine();
      _builder.append("class C {}");
      final YWidget first = this._parseHelper.parse(_builder);
      StringConcatenation _builder_1 = new StringConcatenation();
      _builder_1.append("package my.second.pack;");
      _builder_1.newLine();
      _builder_1.append("class C {}");
      _builder_1.newLine();
      this._validationTestHelper.assertNoErrors(this._parseHelper.parse(_builder_1, first.eResource().getResourceSet()));
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testStringConformance() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("class A {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("func m() -> String { return \"foo\"; }");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      this._validationTestHelper.assertNoErrors(this._parseHelper.parse(_builder, this.loadLibInResourceSet()));
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testIntConformance() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("class A {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("func m() -> Int { return 10; }");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      this._validationTestHelper.assertNoErrors(this._parseHelper.parse(_builder, this.loadLibInResourceSet()));
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testBooleanConformance() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("class A {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("func m() -> Boolean { return true; }");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      this._validationTestHelper.assertNoErrors(this._parseHelper.parse(_builder, this.loadLibInResourceSet()));
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testBooleanAcceptedByIfStatement() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("class A {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("func m() -> A {");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("if (new Boolean()) {");
      _builder.newLine();
      _builder.append("\t\t\t");
      _builder.append("return null;");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("return null;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      this._validationTestHelper.assertNoErrors(this._parseHelper.parse(_builder, this.loadLibInResourceSet()));
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testEveryClassExtendsObject() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("class A {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("func m() -> Object{");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("return new A();");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      this._validationTestHelper.assertNoErrors(this._parseHelper.parse(_builder, this.loadLibInResourceSet()));
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testStringConformantToObject() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("class A {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("func m() -> Object {");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("return \"a\";");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      this._validationTestHelper.assertNoErrors(this._parseHelper.parse(_builder, this.loadLibInResourceSet()));
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testAccessToExplicitlyInheritedObject() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("class A : Object {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("Object m(Object o) {");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("Object c = this.clone();");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("return this.toString();");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      this._validationTestHelper.assertNoErrors(this._parseHelper.parse(_builder, this.loadLibInResourceSet()));
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testAccessToImplictlyInheritedObject() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("class A {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("Object m(Object o) {");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("Object c = this.clone();");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("return this.toString();");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      this._validationTestHelper.assertNoErrors(this._parseHelper.parse(_builder, this.loadLibInResourceSet()));
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testWrongMethodOverrideWithLibrary() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("class A {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("func toString() -> Object {");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("return null;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      YWidget _parse = this._parseHelper.parse(_builder, this.loadLibInResourceSet());
      final Procedure1<YWidget> _function = (YWidget it) -> {
        this._validationTestHelper.assertError(it, LangPackage.eINSTANCE.getYFunction(), 
          LangValidator.WRONG_FUNCTION_OVERRIDE, 
          "The function \'toString\' must override a superclass function");
        Assert.assertEquals(1, this._validationTestHelper.validate(it).size());
      };
      ObjectExtensions.<YWidget>operator_doubleArrow(_parse, _function);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testCorrectMethodOverrideWithLibrary() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("class A {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("public String toString() {");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("return null;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      this._validationTestHelper.assertNoErrors(this._parseHelper.parse(_builder, this.loadLibInResourceSet()));
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testWrongSuperUsage() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("class C {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("C m() {");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("return super;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      this._validationTestHelper.assertError(this._parseHelper.parse(_builder), LangPackage.eINSTANCE.getYSuper(), 
        LangValidator.WRONG_SUPER_USAGE, 
        "\'super\' can be used only as member selection receiver");
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testCanAccessToSuper() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("class A {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("func m() -> String{");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("return null;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _builder.newLine();
      _builder.append("class B : A {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("func m() -> String {");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("return super.toString();");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      this._validationTestHelper.assertNoErrors(this._parseHelper.parse(_builder, this.loadLibInResourceSet()));
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testCanAccessToSuperWithLibrary() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("class A {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("public func toString() -> String{");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("return super.toString();");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      this._validationTestHelper.assertNoErrors(this._parseHelper.parse(_builder, this.loadLibInResourceSet()));
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testReducedAccessibility() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("class A {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("public func m() -> A {");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("return null;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _builder.newLine();
      _builder.append("class B : A {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("func m() -> A{");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("return null;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      this._validationTestHelper.assertError(this._parseHelper.parse(_builder), LangPackage.eINSTANCE.getYFunction(), 
        LangValidator.REDUCED_ACCESSIBILITY, 
        "Cannot reduce access from public to private");
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testReducedAccessibility2() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("class A {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("protected func m() -> A{");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("return null;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _builder.newLine();
      _builder.append("class B : A {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("func m() -> A{");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("return null;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      this._validationTestHelper.assertError(this._parseHelper.parse(_builder), LangPackage.eINSTANCE.getYFunction(), 
        LangValidator.REDUCED_ACCESSIBILITY, 
        "Cannot reduce access from protected to private");
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testReducedAccessibility3() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("class A {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("public func m() -> A{");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("return null;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _builder.newLine();
      _builder.append("class B : A {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("protected func m() -> A{");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("return null;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      this._validationTestHelper.assertError(this._parseHelper.parse(_builder), LangPackage.eINSTANCE.getYFunction(), 
        LangValidator.REDUCED_ACCESSIBILITY, 
        "Cannot reduce access from public to protected");
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  private void assertClassHierarchyCycle(final YWidget p, final String expectedClassName) {
    this._validationTestHelper.assertError(p, 
      LangPackage.eINSTANCE.getYClass(), 
      LangValidator.HIERARCHY_CYCLE, 
      (("cycle in hierarchy of class \'" + expectedClassName) + "\'"));
  }
  
  private void assertEntityHierarchyCycle(final YWidget p, final String expectedEntityName) {
    this._validationTestHelper.assertError(p, 
      LangPackage.eINSTANCE.getYAnnotEntity(), 
      LangValidator.HIERARCHY_CYCLE, 
      (("cycle in hierarchy of entities \'" + expectedEntityName) + "\'"));
  }
  
  private void assertDuplicate(final String input, final EClass type, final String desc, final String name) {
    try {
      YWidget _parse = this._parseHelper.parse(input);
      final Procedure1<YWidget> _function = (YWidget it) -> {
        this._validationTestHelper.assertError(it, type, 
          LangValidator.DUPLICATE_ELEMENT, 
          input.indexOf(name), name.length(), 
          (((("Duplicate " + desc) + " \'") + name) + "\'"));
        this._validationTestHelper.assertError(it, type, 
          LangValidator.DUPLICATE_ELEMENT, 
          input.lastIndexOf(name), name.length(), 
          (((("Duplicate " + desc) + " \'") + name) + "\'"));
      };
      ObjectExtensions.<YWidget>operator_doubleArrow(_parse, _function);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  private void assertIncompatibleTypes(final CharSequence methodBody, final EClass c, final String expectedType, final String actualType) {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("class A {}");
      _builder.newLine();
      _builder.append("class B : A {}");
      _builder.newLine();
      _builder.append("class C {");
      _builder.newLine();
      _builder.append("  ");
      _builder.append("var f : A;");
      _builder.newLine();
      _builder.append("  ");
      _builder.append("func m(p : A) -> A {");
      _builder.newLine();
      _builder.append("    ");
      _builder.append(methodBody, "    ");
      _builder.newLineIfNotEmpty();
      _builder.append("  ");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      this._validationTestHelper.assertError(this._parseHelper.parse(_builder), c, 
        LangValidator.INCOMPATIBLE_TYPES, 
        (((("Incompatible types. Expected \'" + expectedType) + "\' but was \'") + actualType) + "\'"));
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  private ResourceSet loadLibInResourceSet() {
    ResourceSet _get = this.resourceSetProvider.get();
    final Procedure1<ResourceSet> _function = (ResourceSet it) -> {
      this._langLib.loadLib(it);
    };
    return ObjectExtensions.<ResourceSet>operator_doubleArrow(_get, _function);
  }
}
