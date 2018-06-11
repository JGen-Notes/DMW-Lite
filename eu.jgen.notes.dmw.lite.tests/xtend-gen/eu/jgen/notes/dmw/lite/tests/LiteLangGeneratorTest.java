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
import com.google.inject.Inject;
import eu.jgen.notes.dmw.lite.tests.LangInjectorProvider;
import eu.jgen.notes.dmw.lite.utility.LangLib;
import java.util.Collections;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.diagnostics.Severity;
import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.XtextRunner;
import org.eclipse.xtext.util.IAcceptor;
import org.eclipse.xtext.validation.Issue;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Pair;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.eclipse.xtext.xbase.lib.util.ReflectExtensions;
import org.eclipse.xtext.xbase.testing.CompilationTestHelper;
import org.eclipse.xtext.xbase.testing.TemporaryFolder;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(XtextRunner.class)
@InjectWith(LangInjectorProvider.class)
@SuppressWarnings("all")
public class LiteLangGeneratorTest {
  @Rule
  @Inject
  public TemporaryFolder temporaryFolder;
  
  @Inject
  @Extension
  private CompilationTestHelper _compilationTestHelper;
  
  @Inject
  @Extension
  private ReflectExtensions _reflectExtensions;
  
  @Inject
  private LangLib langLib;
  
  @Test
  public void testEmptyProgram() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      final IAcceptor<CompilationTestHelper.Result> _function = (CompilationTestHelper.Result it) -> {
      };
      this._compilationTestHelper.compile(_builder, _function);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testGeneratedCodeWithoutPackage() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("class C {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("var f : C;");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      StringConcatenation _builder_1 = new StringConcatenation();
      _builder_1.append("public class C {");
      _builder_1.newLine();
      _builder_1.append("\t");
      _builder_1.append("private C f;");
      _builder_1.newLine();
      _builder_1.append("}");
      _builder_1.newLine();
      this._compilationTestHelper.assertCompilesTo(_builder, _builder_1);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testGeneratedCodeWithPackage() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("package my.pack;");
      _builder.newLine();
      _builder.append("class C {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("C f;");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      StringConcatenation _builder_1 = new StringConcatenation();
      _builder_1.append("package my.pack;");
      _builder_1.newLine();
      _builder_1.newLine();
      _builder_1.append("public class C {");
      _builder_1.newLine();
      _builder_1.append("\t");
      _builder_1.append("private my.pack.C f;");
      _builder_1.newLine();
      _builder_1.append("}");
      _builder_1.newLine();
      this._compilationTestHelper.assertCompilesTo(_builder, _builder_1);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testGeneratedCodeWithMethods() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("package my.pack;");
      _builder.newLine();
      _builder.append("class C {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("C f;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("C m(C p1, C p2) {");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("p1 = p2 = p1;");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("C aVar = p1;");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("if (true)");
      _builder.newLine();
      _builder.append("\t\t\t");
      _builder.append("this.f = new C();");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("if (false)");
      _builder.newLine();
      _builder.append("\t\t\t");
      _builder.append("this.m(p2, p1);");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("else");
      _builder.newLine();
      _builder.append("\t\t\t");
      _builder.append("this.f = null;");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("return new C();");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      final IAcceptor<CompilationTestHelper.Result> _function = (CompilationTestHelper.Result it) -> {
        StringConcatenation _builder_1 = new StringConcatenation();
        _builder_1.append("package my.pack;");
        _builder_1.newLine();
        _builder_1.newLine();
        _builder_1.append("public class C {");
        _builder_1.newLine();
        _builder_1.append("\t");
        _builder_1.append("private my.pack.C f;");
        _builder_1.newLine();
        _builder_1.append("\t");
        _builder_1.append("private my.pack.C m(my.pack.C p1, my.pack.C p2) {");
        _builder_1.newLine();
        _builder_1.append("\t\t");
        _builder_1.append("p1 = p2 = p1;");
        _builder_1.newLine();
        _builder_1.append("\t\t");
        _builder_1.append("my.pack.C aVar = p1;");
        _builder_1.newLine();
        _builder_1.append("\t\t");
        _builder_1.append("if (true)");
        _builder_1.newLine();
        _builder_1.append("\t\t\t");
        _builder_1.append("{");
        _builder_1.newLine();
        _builder_1.append("\t\t\t\t");
        _builder_1.append("this.f = new my.pack.C();");
        _builder_1.newLine();
        _builder_1.append("\t\t\t");
        _builder_1.append("}");
        _builder_1.newLine();
        _builder_1.append("\t\t");
        _builder_1.append("if (false)");
        _builder_1.newLine();
        _builder_1.append("\t\t\t");
        _builder_1.append("{");
        _builder_1.newLine();
        _builder_1.append("\t\t\t\t");
        _builder_1.append("this.m(p2, p1);");
        _builder_1.newLine();
        _builder_1.append("\t\t\t");
        _builder_1.append("}");
        _builder_1.newLine();
        _builder_1.append("\t\t");
        _builder_1.append("else");
        _builder_1.newLine();
        _builder_1.append("\t\t\t");
        _builder_1.append("{");
        _builder_1.newLine();
        _builder_1.append("\t\t\t\t");
        _builder_1.append("this.f = null;");
        _builder_1.newLine();
        _builder_1.append("\t\t\t");
        _builder_1.append("}");
        _builder_1.newLine();
        _builder_1.append("\t\t");
        _builder_1.append("return new my.pack.C();");
        _builder_1.newLine();
        _builder_1.append("\t");
        _builder_1.append("}");
        _builder_1.newLine();
        _builder_1.append("}");
        _builder_1.newLine();
        Assert.assertEquals(_builder_1.toString(), 
          it.getGeneratedCode("my.pack.C"));
        it.getCompiledClass();
      };
      this._compilationTestHelper.compile(_builder, _function);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testGeneratedCodeWithLibrary() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("package my.pack;");
      _builder.newLine();
      _builder.append("class D {}");
      _builder.newLine();
      _builder.append("class C extends D {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("String aString;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("Integer aInt;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("Boolean aBool;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("Object anObj;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("Object m() {");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("this.aString = \"foo\";");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("this.aInt = 0;");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("this.aBool = true;");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("String s = null;");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("Integer i = null;");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("if (false)");
      _builder.newLine();
      _builder.append("\t\t\t");
      _builder.append("this.anObj = s;");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("else");
      _builder.newLine();
      _builder.append("\t\t\t");
      _builder.append("this.anObj = i;");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("return this.anObj;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      Pair<String, String> _mappedTo = Pair.<String, String>of("My.smalljava", _builder.toString());
      final ResourceSet rs = this._compilationTestHelper.resourceSet(_mappedTo);
      this.langLib.loadLib(rs);
      final IAcceptor<CompilationTestHelper.Result> _function = (CompilationTestHelper.Result it) -> {
        StringConcatenation _builder_1 = new StringConcatenation();
        _builder_1.append("package my.pack;");
        _builder_1.newLine();
        _builder_1.newLine();
        _builder_1.append("public class C extends my.pack.D {");
        _builder_1.newLine();
        _builder_1.append("\t");
        _builder_1.append("private String aString;");
        _builder_1.newLine();
        _builder_1.append("\t");
        _builder_1.append("private Integer aInt;");
        _builder_1.newLine();
        _builder_1.append("\t");
        _builder_1.append("private Boolean aBool;");
        _builder_1.newLine();
        _builder_1.append("\t");
        _builder_1.append("private Object anObj;");
        _builder_1.newLine();
        _builder_1.append("\t");
        _builder_1.append("private Object m() {");
        _builder_1.newLine();
        _builder_1.append("\t\t");
        _builder_1.append("this.aString = \"foo\";");
        _builder_1.newLine();
        _builder_1.append("\t\t");
        _builder_1.append("this.aInt = 0;");
        _builder_1.newLine();
        _builder_1.append("\t\t");
        _builder_1.append("this.aBool = true;");
        _builder_1.newLine();
        _builder_1.append("\t\t");
        _builder_1.append("String s = null;");
        _builder_1.newLine();
        _builder_1.append("\t\t");
        _builder_1.append("Integer i = null;");
        _builder_1.newLine();
        _builder_1.append("\t\t");
        _builder_1.append("if (false)");
        _builder_1.newLine();
        _builder_1.append("\t\t\t");
        _builder_1.append("{");
        _builder_1.newLine();
        _builder_1.append("\t\t\t\t");
        _builder_1.append("this.anObj = s;");
        _builder_1.newLine();
        _builder_1.append("\t\t\t");
        _builder_1.append("}");
        _builder_1.newLine();
        _builder_1.append("\t\t");
        _builder_1.append("else");
        _builder_1.newLine();
        _builder_1.append("\t\t\t");
        _builder_1.append("{");
        _builder_1.newLine();
        _builder_1.append("\t\t\t\t");
        _builder_1.append("this.anObj = i;");
        _builder_1.newLine();
        _builder_1.append("\t\t\t");
        _builder_1.append("}");
        _builder_1.newLine();
        _builder_1.append("\t\t");
        _builder_1.append("return this.anObj;");
        _builder_1.newLine();
        _builder_1.append("\t");
        _builder_1.append("}");
        _builder_1.newLine();
        _builder_1.append("}");
        _builder_1.newLine();
        Assert.assertEquals(_builder_1.toString(), 
          it.getGeneratedCode("my.pack.C"));
        it.getCompiledClass();
      };
      this._compilationTestHelper.compile(rs, _function);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testGeneratedCodeWithLibraryAndInheritedObjectMethods() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("package my.pack;");
      _builder.newLine();
      _builder.append("class C {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("Object m() {");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("return this.toString().toString();");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      Pair<String, String> _mappedTo = Pair.<String, String>of("My.smalljava", _builder.toString());
      final ResourceSet rs = this._compilationTestHelper.resourceSet(_mappedTo);
      this.langLib.loadLib(rs);
      final IAcceptor<CompilationTestHelper.Result> _function = (CompilationTestHelper.Result it) -> {
        this.checkNoValidationErrors(it);
        StringConcatenation _builder_1 = new StringConcatenation();
        _builder_1.append("package my.pack;");
        _builder_1.newLine();
        _builder_1.newLine();
        _builder_1.append("public class C {");
        _builder_1.newLine();
        _builder_1.append("\t");
        _builder_1.append("private Object m() {");
        _builder_1.newLine();
        _builder_1.append("\t\t");
        _builder_1.append("return this.toString().toString();");
        _builder_1.newLine();
        _builder_1.append("\t");
        _builder_1.append("}");
        _builder_1.newLine();
        _builder_1.append("}");
        _builder_1.newLine();
        Assert.assertEquals(_builder_1.toString(), 
          it.getGeneratedCode("my.pack.C"));
        it.getCompiledClass();
      };
      this._compilationTestHelper.compile(rs, _function);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testGeneratedCodeWithSuper() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("package my.pack;");
      _builder.newLine();
      _builder.append("class C {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("public String toString() {");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("return super.toString();");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      Pair<String, String> _mappedTo = Pair.<String, String>of("My.smalljava", _builder.toString());
      final ResourceSet rs = this._compilationTestHelper.resourceSet(_mappedTo);
      this.langLib.loadLib(rs);
      final IAcceptor<CompilationTestHelper.Result> _function = (CompilationTestHelper.Result it) -> {
        this.checkNoValidationErrors(it);
        StringConcatenation _builder_1 = new StringConcatenation();
        _builder_1.append("package my.pack;");
        _builder_1.newLine();
        _builder_1.newLine();
        _builder_1.append("public class C {");
        _builder_1.newLine();
        _builder_1.append("\t");
        _builder_1.append("public String toString() {");
        _builder_1.newLine();
        _builder_1.append("\t\t");
        _builder_1.append("return super.toString();");
        _builder_1.newLine();
        _builder_1.append("\t");
        _builder_1.append("}");
        _builder_1.newLine();
        _builder_1.append("}");
        _builder_1.newLine();
        Assert.assertEquals(_builder_1.toString(), 
          it.getGeneratedCode("my.pack.C"));
        it.getCompiledClass();
      };
      this._compilationTestHelper.compile(rs, _function);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testGeneratedJavaCodeIsValid() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("package my.pack;");
      _builder.newLine();
      _builder.newLine();
      _builder.append("class C {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("C f;");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      final IAcceptor<CompilationTestHelper.Result> _function = (CompilationTestHelper.Result it) -> {
        it.getCompiledClass();
      };
      this._compilationTestHelper.compile(_builder, _function);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testGeneratedJavaCode() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("class C {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("C m() {");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("return new C();");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      final IAcceptor<CompilationTestHelper.Result> _function = (CompilationTestHelper.Result it) -> {
        try {
          Object _newInstance = it.getCompiledClass().newInstance();
          final Procedure1<Object> _function_1 = (Object it_1) -> {
            try {
              Assert.assertNotNull(this._reflectExtensions.invoke(it_1, "m"));
              Assert.assertEquals("class C", this._reflectExtensions.invoke(this._reflectExtensions.invoke(it_1, "m"), "getClass").toString());
            } catch (Throwable _e) {
              throw Exceptions.sneakyThrow(_e);
            }
          };
          ObjectExtensions.<Object>operator_doubleArrow(_newInstance, _function_1);
        } catch (Throwable _e) {
          throw Exceptions.sneakyThrow(_e);
        }
      };
      this._compilationTestHelper.compile(_builder, _function);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testGeneratedCodeWithTwoClasses() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("package my.pack;");
      _builder.newLine();
      _builder.newLine();
      _builder.append("class C {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("D m() {");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("return new D();");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _builder.newLine();
      _builder.append("class D {");
      _builder.newLine();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      final IAcceptor<CompilationTestHelper.Result> _function = (CompilationTestHelper.Result it) -> {
        StringConcatenation _builder_1 = new StringConcatenation();
        _builder_1.append("package my.pack;");
        _builder_1.newLine();
        _builder_1.newLine();
        _builder_1.append("public class C {");
        _builder_1.newLine();
        _builder_1.append("\t");
        _builder_1.append("private my.pack.D m() {");
        _builder_1.newLine();
        _builder_1.append("\t\t");
        _builder_1.append("return new my.pack.D();");
        _builder_1.newLine();
        _builder_1.append("\t");
        _builder_1.append("}");
        _builder_1.newLine();
        _builder_1.append("}");
        _builder_1.newLine();
        Assert.assertEquals(_builder_1.toString(), it.getGeneratedCode("my.pack.C"));
        StringConcatenation _builder_2 = new StringConcatenation();
        _builder_2.append("package my.pack;");
        _builder_2.newLine();
        _builder_2.newLine();
        _builder_2.append("public class D {");
        _builder_2.newLine();
        _builder_2.append("}");
        _builder_2.newLine();
        Assert.assertEquals(_builder_2.toString(), it.getGeneratedCode("my.pack.D"));
      };
      this._compilationTestHelper.compile(_builder, _function);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testGeneratedJavaCodeWithTwoClasses() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("package my.pack;");
      _builder.newLine();
      _builder.newLine();
      _builder.append("class C {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("D m() {");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("return new D();");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _builder.newLine();
      _builder.append("class D {");
      _builder.newLine();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      final IAcceptor<CompilationTestHelper.Result> _function = (CompilationTestHelper.Result it) -> {
        try {
          final Object C = it.getCompiledClass("my.pack.C").newInstance();
          Assert.assertEquals("class my.pack.D", this._reflectExtensions.invoke(this._reflectExtensions.invoke(C, "m"), "getClass").toString());
        } catch (Throwable _e) {
          throw Exceptions.sneakyThrow(_e);
        }
      };
      this._compilationTestHelper.compile(_builder, _function);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testClassesInTheSamePackageInDifferentFiles() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("package my.pack;");
      _builder.newLine();
      _builder.newLine();
      _builder.append("class B {}");
      _builder.newLine();
      _builder.append("class C {}");
      _builder.newLine();
      StringConcatenation _builder_1 = new StringConcatenation();
      _builder_1.append("package my.pack;");
      _builder_1.newLine();
      _builder_1.newLine();
      _builder_1.append("class D {");
      _builder_1.newLine();
      _builder_1.append("  ");
      _builder_1.append("C c;");
      _builder_1.newLine();
      _builder_1.append("}");
      _builder_1.newLine();
      final IAcceptor<CompilationTestHelper.Result> _function = (CompilationTestHelper.Result it) -> {
        StringConcatenation _builder_2 = new StringConcatenation();
        _builder_2.append("package my.pack;");
        _builder_2.newLine();
        _builder_2.newLine();
        _builder_2.append("public class D {");
        _builder_2.newLine();
        _builder_2.append("\t");
        _builder_2.append("private my.pack.C c;");
        _builder_2.newLine();
        _builder_2.append("}");
        _builder_2.newLine();
        Assert.assertEquals(_builder_2.toString(), it.getGeneratedCode("my.pack.D"));
        it.getCompiledClass();
      };
      this._compilationTestHelper.compile(Collections.<CharSequence>unmodifiableList(CollectionLiterals.<CharSequence>newArrayList(_builder, _builder_1)), _function);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test(expected = IllegalStateException.class)
  public void testInputWithValidationError() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("class MyEntity {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("// missing ;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("string myAttribute");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      final IAcceptor<CompilationTestHelper.Result> _function = (CompilationTestHelper.Result it) -> {
        this.checkNoValidationErrors(it);
      };
      this._compilationTestHelper.compile(_builder, _function);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  protected void checkNoValidationErrors(final CompilationTestHelper.Result it) {
    final Function1<Issue, Boolean> _function = (Issue it_1) -> {
      Severity _severity = it_1.getSeverity();
      return Boolean.valueOf(Objects.equal(_severity, Severity.ERROR));
    };
    final Iterable<Issue> allErrors = IterableExtensions.<Issue>filter(it.getErrorsAndWarnings(), _function);
    boolean _isEmpty = IterableExtensions.isEmpty(allErrors);
    boolean _not = (!_isEmpty);
    if (_not) {
      final Function1<Issue, String> _function_1 = (Issue it_1) -> {
        return it_1.toString();
      };
      String _join = IterableExtensions.join(IterableExtensions.<Issue, String>map(allErrors, _function_1), ", ");
      String _plus = ("One or more resources contained errors : " + _join);
      throw new IllegalStateException(_plus);
    }
  }
}
