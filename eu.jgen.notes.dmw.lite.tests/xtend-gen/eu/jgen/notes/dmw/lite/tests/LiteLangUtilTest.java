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
import eu.jgen.notes.dmw.lite.lang.YWidget;
import eu.jgen.notes.dmw.lite.tests.LangInjectorProvider;
import eu.jgen.notes.dmw.lite.utility.LangLib;
import eu.jgen.notes.dmw.lite.utility.LangUtil;
import org.eclipse.emf.common.util.EList;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.XtextRunner;
import org.eclipse.xtext.testing.util.ParseHelper;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
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
    throw new Error("Unresolved compilation problems:"
      + "\nYMemberSelection cannot be resolved to a type."
      + "\nThe method or field returnStatement is undefined for the type YFunction"
      + "\nexpression cannot be resolved");
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
    throw new Error("Unresolved compilation problems:"
      + "\nThe method or field classHierarchyMethods is undefined"
      + "\nThe method or field key is undefined"
      + "\nThe method or field value is undefined"
      + "\nentrySet cannot be resolved"
      + "\nmap cannot be resolved"
      + "\ntoString cannot be resolved"
      + "\n+ cannot be resolved"
      + "\n+ cannot be resolved"
      + "\neContainer cannot be resolved"
      + "\njoin cannot be resolved");
  }
  
  @Test
  public void testHierarchyMembers() {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method or field classHierarchyMembers is undefined"
      + "\nThe method or field eClass is undefined"
      + "\nThe method or field name is undefined"
      + "\nThe method or field eContainer is undefined"
      + "\nmap cannot be resolved"
      + "\nname cannot be resolved"
      + "\n+ cannot be resolved"
      + "\n+ cannot be resolved"
      + "\n+ cannot be resolved"
      + "\n+ cannot be resolved"
      + "\njoin cannot be resolved");
  }
  
  @Test
  public void testMemberAsStringWithType() {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method or field memberAsStringWithType is undefined for the type YFunction"
      + "\nThe method or field memberAsStringWithType is undefined for the type YFunction"
      + "\nThe method or field memberAsStringWithType is undefined for the type YFunction"
      + "\nThe method or field memberAsStringWithType is undefined for the type YProperty");
  }
  
  @Test
  public void testHierarchyMethodsWithLibraryObject() {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method or field classHierarchyMethods is undefined"
      + "\nThe method or field key is undefined"
      + "\nThe method or field value is undefined"
      + "\nentrySet cannot be resolved"
      + "\nmap cannot be resolved"
      + "\ntoString cannot be resolved"
      + "\n+ cannot be resolved"
      + "\n+ cannot be resolved"
      + "\neContainer cannot be resolved"
      + "\njoin cannot be resolved");
  }
  
  private void assertHierarchy(final YClass c, final CharSequence expected) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method or field classHierarchy is undefined for the type YClass"
      + "\nThe method or field name is undefined"
      + "\nmap cannot be resolved"
      + "\njoin cannot be resolved");
  }
}
