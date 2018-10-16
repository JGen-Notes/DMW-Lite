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
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.XtextRunner;
import org.eclipse.xtext.testing.util.ParseHelper;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(XtextRunner.class)
@InjectWith(LangInjectorProvider.class)
@SuppressWarnings("all")
public class LiteLangIndexTest {
  @Inject
  @Extension
  private ParseHelper<YWidget> _parseHelper;
  
  @Inject
  @Extension
  private /* LangIndex */Object _langIndex;
  
  @Test
  public void testExportedEObjectDescriptions() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("class C {");
      _builder.newLine();
      _builder.append("  ");
      _builder.append("var f : A;");
      _builder.newLine();
      _builder.append("  ");
      _builder.append("func m(p : A) -> A{");
      _builder.newLine();
      _builder.append("    ");
      _builder.append("v : A = null;");
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
      this.assertExportedEObjectDescriptions(this._parseHelper.parse(_builder), "C, C.f, C.m, C.m.p, A");
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testExportedEObjectDescriptionsWithPackage() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("package my.pack;");
      _builder.newLine();
      _builder.append("class C {}");
      _builder.newLine();
      this.assertExportedEObjectDescriptions(this._parseHelper.parse(_builder), "my.pack, my.pack.C");
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testClassesInIndex() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("package my.first.pack;");
      _builder.newLine();
      _builder.append("class C { }");
      final YWidget first = this._parseHelper.parse(_builder);
      StringConcatenation _builder_1 = new StringConcatenation();
      _builder_1.append("package my.second.pack;");
      _builder_1.newLine();
      _builder_1.append("class D { }");
      _builder_1.newLine();
      this.assertClassesInIndex(IterableExtensions.<YClass>head(this._parseHelper.parse(_builder_1, first.eResource().getResourceSet()).getClasses()), "my.first.pack.C, my.second.pack.D");
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testVisibleExternalClassesInIndex() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("package my.first.pack;");
      _builder.newLine();
      _builder.append("class C { }");
      _builder.newLine();
      final YWidget first = this._parseHelper.parse(_builder);
      StringConcatenation _builder_1 = new StringConcatenation();
      _builder_1.append("package my.second.pack;");
      _builder_1.newLine();
      _builder_1.append("class D { }");
      _builder_1.newLine();
      final YWidget second = this._parseHelper.parse(_builder_1, first.eResource().getResourceSet());
      this.assertExternalClassesInIndex(IterableExtensions.<YClass>head(first.getClasses()), "my.second.pack.D");
      this.assertExternalClassesInIndex(IterableExtensions.<YClass>head(second.getClasses()), "my.first.pack.C");
      this.assertClassesInIndex(IterableExtensions.<YClass>head(second.getClasses()), "my.first.pack.C, my.second.pack.D");
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testVisibleExternalClassesContainDuplicatesFromExternalFiles() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("package my.first.pack;");
      _builder.newLine();
      _builder.append("class C { }");
      _builder.newLine();
      final YWidget first = this._parseHelper.parse(_builder);
      StringConcatenation _builder_1 = new StringConcatenation();
      _builder_1.append("package my.first.pack;");
      _builder_1.newLine();
      _builder_1.append("class C { }");
      _builder_1.newLine();
      _builder_1.append("class D { }");
      _builder_1.newLine();
      final YWidget second = this._parseHelper.parse(_builder_1, first.eResource().getResourceSet());
      this.assertExternalClassesInIndex(IterableExtensions.<YClass>head(first.getClasses()), "my.first.pack.C, my.first.pack.D");
      this.assertExternalClassesInIndex(IterableExtensions.<YClass>head(second.getClasses()), "my.first.pack.C");
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testVisibleExternalClassesAppearOnlyOnce() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("package my.first.pack;");
      _builder.newLine();
      _builder.append("class C { }");
      _builder.newLine();
      _builder.append("class C { }");
      _builder.newLine();
      final YWidget first = this._parseHelper.parse(_builder);
      StringConcatenation _builder_1 = new StringConcatenation();
      _builder_1.append("package my.second.pack;");
      _builder_1.newLine();
      _builder_1.append("class D { }");
      _builder_1.newLine();
      _builder_1.append("class D { }");
      _builder_1.newLine();
      final YWidget second = this._parseHelper.parse(_builder_1, first.eResource().getResourceSet());
      this.assertExternalClassesInIndex(IterableExtensions.<YClass>head(first.getClasses()), "my.second.pack.D");
      this.assertExternalClassesInIndex(IterableExtensions.<YClass>head(second.getClasses()), "my.first.pack.C");
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  private void assertExportedEObjectDescriptions(final EObject o, final CharSequence expected) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method or field getExportedEObjectDescriptions is undefined for the type EObject"
      + "\nThe method or field qualifiedName is undefined"
      + "\nmap cannot be resolved"
      + "\njoin cannot be resolved");
  }
  
  private void assertClassesInIndex(final EObject o, final String expected) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method or field getVisibleClassesDescriptions is undefined for the type EObject"
      + "\nThe method or field qualifiedName is undefined for the type Object"
      + "\nmap cannot be resolved"
      + "\njoin cannot be resolved");
  }
  
  private void assertExternalClassesInIndex(final EObject o, final String expected) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method or field getVisibleExternalClassesDescriptions is undefined for the type EObject"
      + "\nThe method or field qualifiedName is undefined for the type Object"
      + "\nvalues cannot be resolved"
      + "\nmap cannot be resolved"
      + "\njoin cannot be resolved");
  }
}
