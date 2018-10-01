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
import eu.jgen.notes.dmw.lite.lang.YFunction;
import eu.jgen.notes.dmw.lite.lang.YMemberSelection;
import eu.jgen.notes.dmw.lite.lang.YStatement;
import eu.jgen.notes.dmw.lite.lang.YWidget;
import eu.jgen.notes.dmw.lite.tests.LangInjectorProvider;
import eu.jgen.notes.dmw.lite.utility.LangUtil;
import eu.jgen.notes.dmw.lite.validation.LangAccessibility;
import org.eclipse.emf.common.util.EList;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.XtextRunner;
import org.eclipse.xtext.testing.util.ParseHelper;
import org.eclipse.xtext.xbase.lib.Conversions;
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
public class LiteLangAccessibilityTest {
  @Inject
  @Extension
  private ParseHelper<YWidget> _parseHelper;
  
  @Inject
  @Extension
  private LangAccessibility _langAccessibility;
  
  @Inject
  @Extension
  private LangUtil _langUtil;
  
  @Test
  public void testPropertyAccessibility() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("class A {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("private var  priv : D;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("protected var  prot : D;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("public var pub : D;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("func D m() -> D {");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("self.priv; // private field");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("self.prot; // protected field");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("self.pub; // public field");
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
      _builder.append("func m() -> D {");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("self.priv; // private field");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("self.prot; // protected field");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("self.pub; // public field");
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
      _builder.append("func m() -> D {");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("(new A()).priv; // private field");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("(new A()).prot; // protected field");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("(new A()).pub; // public field");
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
      _builder.append("class D {}");
      _builder.newLine();
      EList<YClass> _classes = this._parseHelper.parse(_builder).getClasses();
      final Procedure1<EList<YClass>> _function = (EList<YClass> it) -> {
        EList<YStatement> _statements = ((YFunction[])Conversions.unwrapArray(this._langUtil.functions(it.get(0)), YFunction.class))[0].getBody().getStatements();
        final Procedure1<EList<YStatement>> _function_1 = (EList<YStatement> it_1) -> {
          this.assertMemberAccessible(it_1.get(0), true);
          this.assertMemberAccessible(it_1.get(1), true);
          this.assertMemberAccessible(it_1.get(2), true);
        };
        ObjectExtensions.<EList<YStatement>>operator_doubleArrow(_statements, _function_1);
        EList<YStatement> _statements_1 = ((YFunction[])Conversions.unwrapArray(this._langUtil.functions(it.get(1)), YFunction.class))[0].getBody().getStatements();
        final Procedure1<EList<YStatement>> _function_2 = (EList<YStatement> it_1) -> {
          this.assertMemberAccessible(it_1.get(0), false);
          this.assertMemberAccessible(it_1.get(1), true);
          this.assertMemberAccessible(it_1.get(2), true);
        };
        ObjectExtensions.<EList<YStatement>>operator_doubleArrow(_statements_1, _function_2);
        EList<YStatement> _statements_2 = ((YFunction[])Conversions.unwrapArray(this._langUtil.functions(it.get(2)), YFunction.class))[0].getBody().getStatements();
        final Procedure1<EList<YStatement>> _function_3 = (EList<YStatement> it_1) -> {
          this.assertMemberAccessible(it_1.get(0), false);
          this.assertMemberAccessible(it_1.get(1), false);
          this.assertMemberAccessible(it_1.get(2), true);
        };
        ObjectExtensions.<EList<YStatement>>operator_doubleArrow(_statements_2, _function_3);
      };
      ObjectExtensions.<EList<YClass>>operator_doubleArrow(_classes, _function);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testFunctionAccessibility() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("class A {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("privatefunc   priv() -> D{ return null; }");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("protected func   prot() -> D { return null; }");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("public func  D pub() -> D { return null; }");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("func m() -> D {");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("self.priv(); // private method");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("self.prot(); // protected method");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("self.pub(); // public method");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("return null;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("} ");
      _builder.newLine();
      _builder.newLine();
      _builder.append("class B : A {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("func  m() -> D {");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("self.priv(); // private method");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("self.prot(); // protected method");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("self.pub(); // public method");
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
      _builder.append("func m() -> D {");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("(new A()).priv(); // private method");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("(new A()).prot(); // protected method");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("(new A()).pub(); // public method");
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
      _builder.append("class D {}");
      _builder.newLine();
      EList<YClass> _classes = this._parseHelper.parse(_builder).getClasses();
      final Procedure1<EList<YClass>> _function = (EList<YClass> it) -> {
        EList<YStatement> _statements = IterableExtensions.<YFunction>last(this._langUtil.functions(it.get(0))).getBody().getStatements();
        final Procedure1<EList<YStatement>> _function_1 = (EList<YStatement> it_1) -> {
          this.assertMemberAccessible(it_1.get(0), true);
          this.assertMemberAccessible(it_1.get(1), true);
          this.assertMemberAccessible(it_1.get(2), true);
        };
        ObjectExtensions.<EList<YStatement>>operator_doubleArrow(_statements, _function_1);
        EList<YStatement> _statements_1 = IterableExtensions.<YFunction>last(this._langUtil.functions(it.get(1))).getBody().getStatements();
        final Procedure1<EList<YStatement>> _function_2 = (EList<YStatement> it_1) -> {
          this.assertMemberAccessible(it_1.get(0), false);
          this.assertMemberAccessible(it_1.get(1), true);
          this.assertMemberAccessible(it_1.get(2), true);
        };
        ObjectExtensions.<EList<YStatement>>operator_doubleArrow(_statements_1, _function_2);
        EList<YStatement> _statements_2 = IterableExtensions.<YFunction>last(this._langUtil.functions(it.get(2))).getBody().getStatements();
        final Procedure1<EList<YStatement>> _function_3 = (EList<YStatement> it_1) -> {
          this.assertMemberAccessible(it_1.get(0), false);
          this.assertMemberAccessible(it_1.get(1), false);
          this.assertMemberAccessible(it_1.get(2), true);
        };
        ObjectExtensions.<EList<YStatement>>operator_doubleArrow(_statements_2, _function_3);
      };
      ObjectExtensions.<EList<YClass>>operator_doubleArrow(_classes, _function);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  private void assertMemberAccessible(final YStatement s, final boolean expected) {
    final YMemberSelection sel = ((YMemberSelection) s);
    Assert.assertEquals(Boolean.valueOf(expected), 
      Boolean.valueOf(this._langAccessibility.isAccessibleFrom(sel.getMember(), sel)));
  }
}
