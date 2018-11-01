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

import com.google.common.base.Objects;
import com.google.common.collect.Iterators;
import com.google.inject.Inject;
import com.google.inject.Provider;
import eu.jgen.notes.dmw.lite.lang.LangPackage;
import eu.jgen.notes.dmw.lite.lang.YClass;
import eu.jgen.notes.dmw.lite.lang.YExpression;
import eu.jgen.notes.dmw.lite.lang.YFunction;
import eu.jgen.notes.dmw.lite.lang.YMemberSelection;
import eu.jgen.notes.dmw.lite.lang.YProperty;
import eu.jgen.notes.dmw.lite.lang.YStatement;
import eu.jgen.notes.dmw.lite.lang.YSymbolRef;
import eu.jgen.notes.dmw.lite.lang.YVariableDeclaration;
import eu.jgen.notes.dmw.lite.lang.YWidget;
import eu.jgen.notes.dmw.lite.tests.LangInjectorProvider;
import eu.jgen.notes.dmw.lite.utility.LangLib;
import eu.jgen.notes.dmw.lite.utility.LangUtil;
import java.util.Iterator;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.scoping.IScopeProvider;
import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.XtextRunner;
import org.eclipse.xtext.testing.util.ParseHelper;
import org.eclipse.xtext.testing.validation.ValidationTestHelper;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.IteratorExtensions;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(XtextRunner.class)
@InjectWith(LangInjectorProvider.class)
@SuppressWarnings("all")
public class LiteLangScopeProviderTest1 {
  @Inject
  @Extension
  private ParseHelper<YWidget> _parseHelper;
  
  @Inject
  @Extension
  private ValidationTestHelper _validationTestHelper;
  
  @Inject
  @Extension
  private IScopeProvider _iScopeProvider;
  
  @Inject
  @Extension
  private LangUtil _langUtil;
  
  @Inject
  @Extension
  private LangLib _langLib;
  
  @Inject
  private Provider<ResourceSet> rsp;
  
  @Test
  public void testScopeProvider() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("class C {");
      _builder.newLine();
      _builder.append("  ");
      _builder.append("var f : A;");
      _builder.newLine();
      _builder.append("  ");
      _builder.append("func m(p : A) -> A {");
      _builder.newLine();
      _builder.append("    ");
      _builder.append("v : A = null;");
      _builder.newLine();
      _builder.append("    ");
      _builder.append("return null; // return\'s expression is the context");
      _builder.newLine();
      _builder.append("  ");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _builder.append("class A {}");
      _builder.newLine();
      YExpression _expression = this._langUtil.returnStatement(IterableExtensions.<YFunction>last(this._langUtil.functions(IterableExtensions.<YClass>head(this._parseHelper.parse(_builder).getClasses())))).getExpression();
      final Procedure1<YExpression> _function = (YExpression it) -> {
        this.assertScope(it, LangPackage.eINSTANCE.getYMemberSelection_Member(), "f, m, C.f, C.m");
        this.assertScope(it, LangPackage.eINSTANCE.getYSymbolRef_Symbol(), "v, p");
      };
      ObjectExtensions.<YExpression>operator_doubleArrow(_expression, _function);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testScopeProviderForSymbols() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("class C {");
      _builder.newLine();
      _builder.append("  ");
      _builder.append("func m(p : A) -> A {");
      _builder.newLine();
      _builder.append("    ");
      _builder.append("v1 : A = null;");
      _builder.newLine();
      _builder.append("    ");
      _builder.append("if (true) {");
      _builder.newLine();
      _builder.append("      ");
      _builder.append("v2 : A = null;");
      _builder.newLine();
      _builder.append("      ");
      _builder.append("v3 : A = null;");
      _builder.newLine();
      _builder.append("    ");
      _builder.append("}");
      _builder.newLine();
      _builder.append("    ");
      _builder.append("v4 : A = null;");
      _builder.newLine();
      _builder.append("    ");
      _builder.append("return null;");
      _builder.newLine();
      _builder.append("  ");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _builder.append("class A {}");
      _builder.newLine();
      Iterator<YVariableDeclaration> _filter = Iterators.<YVariableDeclaration>filter(IterableExtensions.<YFunction>last(this._langUtil.functions(IterableExtensions.<YClass>head(this._parseHelper.parse(_builder).getClasses()))).getBody().eAllContents(), YVariableDeclaration.class);
      final Procedure1<Iterator<YVariableDeclaration>> _function = (Iterator<YVariableDeclaration> it) -> {
        final Function1<YVariableDeclaration, Boolean> _function_1 = (YVariableDeclaration it_1) -> {
          String _name = it_1.getName();
          return Boolean.valueOf(Objects.equal(_name, "v3"));
        };
        this.assertScope(IteratorExtensions.<YVariableDeclaration>findFirst(it, _function_1).getExpression(), LangPackage.eINSTANCE.getYSymbolRef_Symbol(), "v2, v1, p");
        final Function1<YVariableDeclaration, Boolean> _function_2 = (YVariableDeclaration it_1) -> {
          String _name = it_1.getName();
          return Boolean.valueOf(Objects.equal(_name, "v4"));
        };
        this.assertScope(IteratorExtensions.<YVariableDeclaration>findFirst(it, _function_2).getExpression(), LangPackage.eINSTANCE.getYSymbolRef_Symbol(), "v1, p");
      };
      ObjectExtensions.<Iterator<YVariableDeclaration>>operator_doubleArrow(_filter, _function);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testVariableShadowsParamScoping() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("class C {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("func m(a : A) -> A{");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("a : A = null;");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("return a;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _builder.append("class A {}");
      _builder.newLine();
      YFunction _head = IterableExtensions.<YFunction>head(this._langUtil.functions(IterableExtensions.<YClass>head(this._parseHelper.parse(_builder).getClasses())));
      final Procedure1<YFunction> _function = (YFunction it) -> {
        this.assertScope(this._langUtil.returnStatement(it).getExpression(), LangPackage.eINSTANCE.getYSymbolRef_Symbol(), 
          "a");
      };
      ObjectExtensions.<YFunction>operator_doubleArrow(_head, _function);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testVariableShadowsParamLinking() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("class A {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("func m(a : A) -> A {");
      _builder.newLine();
      _builder.append("\t\t  ");
      _builder.append("a : A = null;");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("return a;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      YFunction _head = IterableExtensions.<YFunction>head(this._langUtil.functions(IterableExtensions.<YClass>head(this._parseHelper.parse(_builder).getClasses())));
      final Procedure1<YFunction> _function = (YFunction it) -> {
        YExpression _expression = this._langUtil.returnStatement(it).getExpression();
        Assert.assertSame(IterableExtensions.<YStatement>head(it.getBody().getStatements()), 
          ((YSymbolRef) _expression).getSymbol());
      };
      ObjectExtensions.<YFunction>operator_doubleArrow(_head, _function);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testFieldsScoping() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("class C { ");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("var a : C;");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _builder.newLine();
      _builder.append("class D : C {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("var b : C;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("func m(p1 : C, p2 : D) -> C { return self.b; }");
      _builder.newLine();
      _builder.append("}");
      this.assertScope(this._langUtil.returnStatement(((YFunction[])Conversions.unwrapArray(this._langUtil.functions(IterableExtensions.<YClass>last(this._parseHelper.parse(_builder).getClasses())), YFunction.class))[0]).getExpression(), 
        LangPackage.eINSTANCE.getYMemberSelection_Member(), "b, m, a");
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testMethodsScoping() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("class C { ");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("func n() -> C { return null; }");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _builder.newLine();
      _builder.append("class D : C {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("var f : C;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("func m() -> C { return self.m(); }");
      _builder.newLine();
      _builder.append("}");
      this.assertScope(this._langUtil.returnStatement(((YFunction[])Conversions.unwrapArray(this._langUtil.functions(IterableExtensions.<YClass>last(this._parseHelper.parse(_builder).getClasses())), YFunction.class))[0]).getExpression(), 
        LangPackage.eINSTANCE.getYMemberSelection_Member(), "m, f, n");
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testFieldScoping() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("class A {");
      _builder.newLine();
      _builder.append("  ");
      _builder.append("var a : D;");
      _builder.newLine();
      _builder.append("  ");
      _builder.append("var b  : D;");
      _builder.newLine();
      _builder.append("  ");
      _builder.append("var c : D;");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _builder.newLine();
      _builder.append("class B : A {");
      _builder.newLine();
      _builder.append("  ");
      _builder.append("var b : D;");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _builder.newLine();
      _builder.append("class C : B {");
      _builder.newLine();
      _builder.append("  ");
      _builder.append("var a : D;");
      _builder.newLine();
      _builder.append("  ");
      _builder.append("func m() -> D { return self.a; }");
      _builder.newLine();
      _builder.append("  ");
      _builder.append("func n() -> D { return self.b; }");
      _builder.newLine();
      _builder.append("  ");
      _builder.append("func p() -> D { return self.c; }");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _builder.append("class D {}");
      _builder.newLine();
      EList<YClass> _classes = this._parseHelper.parse(_builder).getClasses();
      final Procedure1<EList<YClass>> _function = (EList<YClass> it) -> {
        Assert.assertSame(((YProperty[])Conversions.unwrapArray(this._langUtil.properties(it.get(2)), YProperty.class))[0], this.returnExpSel(((YFunction[])Conversions.unwrapArray(this._langUtil.functions(it.get(2)), YFunction.class))[0]).getMember());
        Assert.assertSame(((YProperty[])Conversions.unwrapArray(this._langUtil.properties(it.get(1)), YProperty.class))[0], this.returnExpSel(((YFunction[])Conversions.unwrapArray(this._langUtil.functions(it.get(2)), YFunction.class))[1]).getMember());
        Assert.assertSame(((YProperty[])Conversions.unwrapArray(this._langUtil.properties(it.get(0)), YProperty.class))[2], this.returnExpSel(((YFunction[])Conversions.unwrapArray(this._langUtil.functions(it.get(2)), YFunction.class))[2]).getMember());
      };
      ObjectExtensions.<EList<YClass>>operator_doubleArrow(_classes, _function);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testMethodScoping() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("class A { ");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("func n() -> D { return null; }");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("func m() -> D { return null; }");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("func o() -> D { return null; }");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _builder.newLine();
      _builder.append("class B : A { ");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("func n() -> D { return null; }");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _builder.newLine();
      _builder.append("class C : B {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("func m() -> D { return self.m(); }");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("func p() -> D { return self.n(); }");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("func q() -> D { return self.o(); }");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _builder.append("class D {}");
      _builder.newLine();
      EList<YClass> _classes = this._parseHelper.parse(_builder).getClasses();
      final Procedure1<EList<YClass>> _function = (EList<YClass> it) -> {
        Assert.assertSame(((YFunction[])Conversions.unwrapArray(this._langUtil.functions(it.get(2)), YFunction.class))[0], this.returnExpSel(((YFunction[])Conversions.unwrapArray(this._langUtil.functions(it.get(2)), YFunction.class))[0]).getMember());
        Assert.assertSame(((YFunction[])Conversions.unwrapArray(this._langUtil.functions(it.get(1)), YFunction.class))[0], this.returnExpSel(((YFunction[])Conversions.unwrapArray(this._langUtil.functions(it.get(2)), YFunction.class))[1]).getMember());
        Assert.assertSame(((YFunction[])Conversions.unwrapArray(this._langUtil.functions(it.get(0)), YFunction.class))[2], this.returnExpSel(((YFunction[])Conversions.unwrapArray(this._langUtil.functions(it.get(2)), YFunction.class))[2]).getMember());
      };
      ObjectExtensions.<EList<YClass>>operator_doubleArrow(_classes, _function);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testMemberScopeWithUnresolvedReceiver() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("class C {");
      _builder.newLine();
      _builder.append("  ");
      _builder.append("func m() -> A {");
      _builder.newLine();
      _builder.append("    ");
      _builder.append("return foo.m(); // return\'s expression is the context");
      _builder.newLine();
      _builder.append("  ");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _builder.append("class A {}");
      _builder.newLine();
      YExpression _expression = this._langUtil.returnStatement(IterableExtensions.<YFunction>last(this._langUtil.functions(IterableExtensions.<YClass>head(this._parseHelper.parse(_builder).getClasses())))).getExpression();
      final Procedure1<YExpression> _function = (YExpression it) -> {
        this.assertScope(it, LangPackage.eINSTANCE.getYMemberSelection_Member(), "");
      };
      ObjectExtensions.<YExpression>operator_doubleArrow(_expression, _function);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testFieldsAndMethodsWithTheSameName() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("class C {");
      _builder.newLine();
      _builder.append("  ");
      _builder.append("var f : A;");
      _builder.newLine();
      _builder.append("  ");
      _builder.append("func f() -> A {");
      _builder.newLine();
      _builder.append("    ");
      _builder.append("return self.f(); // must refer to method f");
      _builder.newLine();
      _builder.append("  ");
      _builder.append("}");
      _builder.newLine();
      _builder.append("  ");
      _builder.append("func m()-> A {");
      _builder.newLine();
      _builder.append("    ");
      _builder.append("return self.m; // must refer to field m");
      _builder.newLine();
      _builder.append("  ");
      _builder.append("}");
      _builder.newLine();
      _builder.append("  ");
      _builder.append("var m : A;");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _builder.append("class A {}");
      _builder.newLine();
      YClass _head = IterableExtensions.<YClass>head(this._parseHelper.parse(_builder).getClasses());
      final Procedure1<YClass> _function = (YClass it) -> {
        Assert.assertSame(IterableExtensions.<YFunction>head(this._langUtil.functions(it)), this.returnExpSel(IterableExtensions.<YFunction>head(this._langUtil.functions(it))).getMember());
        Assert.assertSame(IterableExtensions.<YProperty>last(this._langUtil.properties(it)), this.returnExpSel(IterableExtensions.<YFunction>last(this._langUtil.functions(it))).getMember());
      };
      ObjectExtensions.<YClass>operator_doubleArrow(_head, _function);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testClassesInTheSamePackageInDifferentFiles() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("package apackage;");
      _builder.newLine();
      _builder.newLine();
      _builder.append("class B {}");
      _builder.newLine();
      _builder.append("class C {}");
      _builder.newLine();
      final YWidget first = this._parseHelper.parse(_builder);
      StringConcatenation _builder_1 = new StringConcatenation();
      _builder_1.append("package apackage;");
      _builder_1.newLine();
      _builder_1.newLine();
      _builder_1.append("class D {");
      _builder_1.newLine();
      _builder_1.append("  ");
      _builder_1.append("var  c : C;");
      _builder_1.newLine();
      _builder_1.append("}");
      _builder_1.newLine();
      final YWidget second = this._parseHelper.parse(_builder_1, first.eResource().getResourceSet());
      StringConcatenation _builder_2 = new StringConcatenation();
      _builder_2.append("package anotherpackage;");
      _builder_2.newLine();
      _builder_2.newLine();
      _builder_2.append("class E {}");
      _builder_2.newLine();
      this._parseHelper.parse(_builder_2, first.eResource().getResourceSet());
      YClass _head = IterableExtensions.<YClass>head(second.getClasses());
      final Procedure1<YClass> _function = (YClass it) -> {
        this.assertScope(it, LangPackage.eINSTANCE.getYMember_Type(), "D, B, C, apackage.D, apackage.B, apackage.C, anotherpackage.E");
      };
      ObjectExtensions.<YClass>operator_doubleArrow(_head, _function);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testLocalClassHasThePrecedenceOverTheSameClassInTheSamePackageInDifferentFiles() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("package apackage;");
      _builder.newLine();
      _builder.newLine();
      _builder.append("class B {}");
      _builder.newLine();
      _builder.append("class C {}");
      _builder.newLine();
      final YWidget first = this._parseHelper.parse(_builder);
      StringConcatenation _builder_1 = new StringConcatenation();
      _builder_1.append("package apackage;");
      _builder_1.newLine();
      _builder_1.newLine();
      _builder_1.append("class C {");
      _builder_1.newLine();
      _builder_1.append("  ");
      _builder_1.append("var  c : C;");
      _builder_1.newLine();
      _builder_1.append("}");
      _builder_1.newLine();
      final YWidget second = this._parseHelper.parse(_builder_1, first.eResource().getResourceSet());
      YClass _head = IterableExtensions.<YClass>head(second.getClasses());
      final Procedure1<YClass> _function = (YClass it) -> {
        this.assertScope(it, LangPackage.eINSTANCE.getYMember_Type(), "C, B, apackage.C, apackage.B");
        Assert.assertSame(it, IterableExtensions.<YProperty>head(this._langUtil.properties(it)).getType());
      };
      ObjectExtensions.<YClass>operator_doubleArrow(_head, _function);
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
      _builder.newLine();
      _builder.append("class B : my.second.pack.A {}");
      _builder.newLine();
      _builder.newLine();
      final YWidget first = this._parseHelper.parse(_builder);
      StringConcatenation _builder_1 = new StringConcatenation();
      _builder_1.append("package my.second.pack;");
      _builder_1.newLine();
      _builder_1.newLine();
      _builder_1.append("class A  {");
      _builder_1.newLine();
      _builder_1.append("  ");
      _builder_1.append("var b : my.first.pack.B ;");
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
      _builder.newLine();
      _builder.append("class C1 {}");
      _builder.newLine();
      _builder.append("class C2 {}");
      _builder.newLine();
      _builder.newLine();
      final YWidget first = this._parseHelper.parse(_builder);
      StringConcatenation _builder_1 = new StringConcatenation();
      _builder_1.append("package my.second.pack;");
      _builder_1.newLine();
      _builder_1.newLine();
      _builder_1.append("class D1 {}");
      _builder_1.newLine();
      _builder_1.append("class D2 {}");
      _builder_1.newLine();
      final YWidget second = this._parseHelper.parse(_builder_1, first.eResource().getResourceSet());
      StringConcatenation _builder_2 = new StringConcatenation();
      _builder_2.append("package my.third.pack;");
      _builder_2.newLine();
      _builder_2.append("import my.first.pack.C1;");
      _builder_2.newLine();
      _builder_2.append("import my.second.pack.*;");
      _builder_2.newLine();
      _builder_2.newLine();
      _builder_2.append("class E : C1 {");
      _builder_2.newLine();
      _builder_2.append("\t");
      _builder_2.append("// C1 is imported");
      _builder_2.newLine();
      _builder_2.append("\t");
      _builder_2.newLine();
      _builder_2.append("\t");
      _builder_2.append("var c : my.first.pack.C2;");
      _builder_2.newLine();
      _builder_2.append("\t");
      _builder_2.append("// C2 not imported");
      _builder_2.newLine();
      _builder_2.append("\t");
      _builder_2.newLine();
      _builder_2.append("\t");
      _builder_2.append("var d1 : D1;");
      _builder_2.newLine();
      _builder_2.append("\t");
      _builder_2.append("// D1 imported by wildcard");
      _builder_2.newLine();
      _builder_2.append("\t");
      _builder_2.newLine();
      _builder_2.append("\t");
      _builder_2.append("var d2 : D2;");
      _builder_2.newLine();
      _builder_2.append("\t");
      _builder_2.append("// D2 imported by wildcard");
      _builder_2.newLine();
      _builder_2.append("\t");
      _builder_2.newLine();
      _builder_2.append("\t");
      _builder_2.newLine();
      _builder_2.append("}");
      _builder_2.newLine();
      _builder_2.newLine();
      final YWidget third = this._parseHelper.parse(_builder_2, first.eResource().getResourceSet());
      this._validationTestHelper.assertNoErrors(first);
      this._validationTestHelper.assertNoErrors(second);
      this._validationTestHelper.assertNoErrors(third);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  private YMemberSelection returnExpSel(final YFunction function) {
    YExpression _expression = this._langUtil.returnStatement(function).getExpression();
    return ((YMemberSelection) _expression);
  }
  
  private void assertScope(final EObject context, final EReference reference, final CharSequence expected) {
    final Function1<IEObjectDescription, QualifiedName> _function = (IEObjectDescription it) -> {
      return it.getName();
    };
    Assert.assertEquals(expected.toString(), IterableExtensions.join(IterableExtensions.<IEObjectDescription, QualifiedName>map(this._iScopeProvider.getScope(context, reference).getAllElements(), _function), ", "));
  }
  
  private YWidget loadLibAndParse(final CharSequence p) {
    try {
      YWidget _xblockexpression = null;
      {
        final ResourceSet resourceSet = this.rsp.get();
        this._langLib.loadLib(resourceSet);
        _xblockexpression = this._parseHelper.parse(p, resourceSet);
      }
      return _xblockexpression;
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
}
