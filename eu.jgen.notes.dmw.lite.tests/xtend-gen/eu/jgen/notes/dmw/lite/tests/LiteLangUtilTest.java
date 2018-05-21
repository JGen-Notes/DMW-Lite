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
import eu.jgen.notes.dmw.lite.lang.YClass;
import eu.jgen.notes.dmw.lite.lang.YExpression;
import eu.jgen.notes.dmw.lite.lang.YFunction;
import eu.jgen.notes.dmw.lite.lang.YMember;
import eu.jgen.notes.dmw.lite.lang.YMemberSelection;
import eu.jgen.notes.dmw.lite.lang.YProperty;
import eu.jgen.notes.dmw.lite.lang.YWidget;
import eu.jgen.notes.dmw.lite.tests.LangInjectorProvider;
import eu.jgen.notes.dmw.lite.utility.LangLib;
import eu.jgen.notes.dmw.lite.utility.LangUtil;
import java.util.Map;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.XtextRunner;
import org.eclipse.xtext.testing.util.ParseHelper;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(XtextRunner.class)
@InjectWith(LangInjectorProvider.class)
@SuppressWarnings("all")
public class LiteLangUtilTest {
  @Inject
  @Extension
  private ParseHelper<YWidget> _parseHelper;
  
  @Inject
  @Extension
  private LangUtil _langUtil;
  
  @Inject
  @Extension
  private LangLib _langLib;
  
  @Test
  public void testModelUtilMethodsByType() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("class C {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("C f;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("C m() {");
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
      _builder.append("return this.c;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      YClass _head = IterableExtensions.<YClass>head(this._parseHelper.parse(_builder).getClasses());
      final Procedure1<YClass> _function = (YClass it) -> {
        Assert.assertEquals("f", IterableExtensions.<YProperty>head(this._langUtil.properties(it)).getName());
        Assert.assertEquals("m", IterableExtensions.<YFunction>head(this._langUtil.functions(it)).getName());
        YExpression _expression = this._langUtil.returnStatement(IterableExtensions.<YFunction>head(this._langUtil.functions(it))).getExpression();
        Assert.assertTrue((_expression instanceof YMemberSelection));
      };
      ObjectExtensions.<YClass>operator_doubleArrow(_head, _function);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testValidHierarchy() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("class C {}");
      _builder.newLine();
      _builder.newLine();
      _builder.append("class D extends C {}");
      _builder.newLine();
      _builder.newLine();
      _builder.append("class E extends D {}");
      EList<YClass> _classes = this._parseHelper.parse(_builder).getClasses();
      final Procedure1<EList<YClass>> _function = (EList<YClass> it) -> {
        this.assertHierarchy(it.get(0), "");
        this.assertHierarchy(it.get(1), "C");
        this.assertHierarchy(it.get(2), "D, C");
      };
      ObjectExtensions.<EList<YClass>>operator_doubleArrow(_classes, _function);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testCyclicHierarchy() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("class C extends E {}");
      _builder.newLine();
      _builder.newLine();
      _builder.append("class D extends C {}");
      _builder.newLine();
      _builder.newLine();
      _builder.append("class E extends D {}");
      EList<YClass> _classes = this._parseHelper.parse(_builder).getClasses();
      final Procedure1<EList<YClass>> _function = (EList<YClass> it) -> {
        this.assertHierarchy(it.get(0), "E, D, C");
        this.assertHierarchy(it.get(1), "C, E, D");
        this.assertHierarchy(it.get(2), "D, C, E");
      };
      ObjectExtensions.<EList<YClass>>operator_doubleArrow(_classes, _function);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testHierarchyMethods() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("class C1 {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("C1 m() { return null; }");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("C1 n() { return null; }");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _builder.append("class C2 extends C1 {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("C1 m() { return null; } // this must override the one in C1");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _builder.append("class C3 extends C2 {");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      YClass _last = IterableExtensions.<YClass>last(this._parseHelper.parse(_builder).getClasses());
      final Procedure1<YClass> _function = (YClass it) -> {
        final Function1<Map.Entry<String, YFunction>, String> _function_1 = (Map.Entry<String, YFunction> it_1) -> {
          String _string = it_1.getKey().toString();
          String _plus = (_string + " -> ");
          EObject _eContainer = it_1.getValue().eContainer();
          String _name = ((YClass) _eContainer).getName();
          return (_plus + _name);
        };
        Assert.assertEquals("m -> C2, n -> C1", 
          IterableExtensions.join(IterableExtensions.<Map.Entry<String, YFunction>, String>map(this._langUtil.classHierarchyMethods(it).entrySet(), _function_1), ", "));
      };
      ObjectExtensions.<YClass>operator_doubleArrow(_last, _function);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testHierarchyMembers() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("class C1 {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("C1 m;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("C1 m() { return null; }");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("C1 n() { return null; }");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("C1 n;");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _builder.append("class C2 extends C1 {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("C1 f;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("C1 m() { return null; } // this must come before the one in C1");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _builder.append("class C3 extends C2 {");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      YClass _last = IterableExtensions.<YClass>last(this._parseHelper.parse(_builder).getClasses());
      final Procedure1<YClass> _function = (YClass it) -> {
        final Function1<YMember, String> _function_1 = (YMember it_1) -> {
          String _name = it_1.eClass().getName();
          String _plus = (_name + " ");
          String _name_1 = it_1.getName();
          String _plus_1 = (_plus + _name_1);
          String _plus_2 = (_plus_1 + " in ");
          EObject _eContainer = it_1.eContainer();
          String _name_2 = ((YClass) _eContainer).getName();
          return (_plus_2 + _name_2);
        };
        Assert.assertEquals("YField f in C2, YMethod m in C2, YField m in C1, YMethod m in C1, YMethod n in C1, YField n in C1", 
          IterableExtensions.join(IterableExtensions.<YMember, String>map(this._langUtil.classHierarchyMembers(it), _function_1), ", "));
      };
      ObjectExtensions.<YClass>operator_doubleArrow(_last, _function);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testMemberAsStringWithType() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("class A {}");
      _builder.newLine();
      _builder.append("class B {}");
      _builder.newLine();
      _builder.append("class C {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("A f;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("A m() { return null; }");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("A n(B b, C c) { return null; }");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("A p(Foo b, C c) { return null; }");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      YClass _last = IterableExtensions.<YClass>last(this._parseHelper.parse(_builder).getClasses());
      final Procedure1<YClass> _function = (YClass it) -> {
        Iterable<YFunction> _functions = this._langUtil.functions(it);
        final Procedure1<Iterable<YFunction>> _function_1 = (Iterable<YFunction> it_1) -> {
          Assert.assertEquals("m() : A", this._langUtil.memberAsStringWithType(((YFunction[])Conversions.unwrapArray(it_1, YFunction.class))[0]));
          Assert.assertEquals("n(B, C) : A", this._langUtil.memberAsStringWithType(((YFunction[])Conversions.unwrapArray(it_1, YFunction.class))[1]));
          Assert.assertEquals("p(null, C) : A", this._langUtil.memberAsStringWithType(((YFunction[])Conversions.unwrapArray(it_1, YFunction.class))[2]));
        };
        ObjectExtensions.<Iterable<YFunction>>operator_doubleArrow(_functions, _function_1);
        Assert.assertEquals("f : A", this._langUtil.memberAsStringWithType(IterableExtensions.<YProperty>head(this._langUtil.properties(it))));
      };
      ObjectExtensions.<YClass>operator_doubleArrow(_last, _function);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testHierarchyMethodsWithLibraryObject() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("class C1 {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("C1 m() { return null; }");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("// the following overrides the one from Object");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("String toString() { return null; }");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _builder.newLine();
      _builder.append("class C2 extends C1 {");
      _builder.newLine();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      final YWidget p = this._parseHelper.parse(_builder);
      this._langLib.loadLib(p.eResource().getResourceSet());
      YClass _last = IterableExtensions.<YClass>last(p.getClasses());
      final Procedure1<YClass> _function = (YClass it) -> {
        final Function1<Map.Entry<String, YFunction>, String> _function_1 = (Map.Entry<String, YFunction> it_1) -> {
          String _string = it_1.getKey().toString();
          String _plus = (_string + " -> ");
          EObject _eContainer = it_1.getValue().eContainer();
          String _name = ((YClass) _eContainer).getName();
          return (_plus + _name);
        };
        Assert.assertEquals("clone -> Object, toString -> C1, equals -> Object, m -> C1", 
          IterableExtensions.join(IterableExtensions.<Map.Entry<String, YFunction>, String>map(this._langUtil.classHierarchyMethods(it).entrySet(), _function_1), ", "));
      };
      ObjectExtensions.<YClass>operator_doubleArrow(_last, _function);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  private void assertHierarchy(final YClass c, final CharSequence expected) {
    final Function1<YClass, String> _function = (YClass it) -> {
      return it.getName();
    };
    Assert.assertEquals(expected.toString(), IterableExtensions.join(IterableExtensions.<YClass, String>map(this._langUtil.classHierarchy(c), _function), ", "));
  }
}
