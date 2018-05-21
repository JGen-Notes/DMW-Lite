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
import eu.jgen.notes.dmw.lite.lang.YWidget;
import eu.jgen.notes.dmw.lite.tests.LangInjectorProvider;
import eu.jgen.notes.dmw.lite.utility.LangLib;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.XtextRunner;
import org.eclipse.xtext.testing.util.ParseHelper;
import org.eclipse.xtext.testing.validation.ValidationTestHelper;
import org.eclipse.xtext.util.IAcceptor;
import org.eclipse.xtext.validation.Issue;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.InputOutput;
import org.eclipse.xtext.xbase.testing.CompilationTestHelper;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(XtextRunner.class)
@InjectWith(LangInjectorProvider.class)
@SuppressWarnings("all")
public class GenerateWidget {
  @Inject
  @Extension
  private ParseHelper<YWidget> _parseHelper;
  
  @Inject
  @Extension
  private ValidationTestHelper _validationTestHelper;
  
  @Inject
  @Extension
  private CompilationTestHelper _compilationTestHelper;
  
  @Inject
  @Extension
  private LangLib _langLib;
  
  @Inject
  private Provider<ResourceSet> resourceSetProvider;
  
  @Test
  public void testGenerateWidgetEmptyClass() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("package log.sample.project;");
      _builder.newLine();
      _builder.append("@database MySQL");
      _builder.newLine();
      _builder.append("@swift SampleWidgetModule  uses ^MySQL");
      _builder.newLine();
      _builder.append("class LogWidget : Widget {");
      _builder.newLine();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      final String model = _builder.toString();
      final Consumer<Issue> _function = (Issue it) -> {
        InputOutput.<Issue>println(it);
      };
      this._validationTestHelper.validate(this.loadLibAndParse(model)).forEach(_function);
      final IAcceptor<CompilationTestHelper.Result> _function_1 = (CompilationTestHelper.Result it) -> {
        final Consumer<Issue> _function_2 = (Issue it_1) -> {
          InputOutput.<Issue>println(it_1);
        };
        it.getErrorsAndWarnings().forEach(_function_2);
        final BiConsumer<String, String> _function_3 = (String p1, String p2) -> {
          InputOutput.<String>println(p1);
          InputOutput.<String>println(p2);
        };
        it.getGeneratedCode().forEach(_function_3);
      };
      this._compilationTestHelper.compile(CollectionLiterals.<String>newArrayList(this._langLib.loadLibSource(), model), _function_1);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testGenerateWidgetSimpleEntity() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("package log.sample.project;");
      _builder.newLine();
      _builder.newLine();
      _builder.append("@entity Log {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("@attr entryType : Short @length (2);");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("@id logid (entryType);");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _builder.append(" ");
      _builder.append("@database MySQL");
      _builder.newLine();
      _builder.append(" ");
      _builder.append("@swift SampleWidgetModule  uses ^MySQL");
      _builder.newLine();
      _builder.append(" ");
      _builder.newLine();
      _builder.append("@td database ^MySQL {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("@table LOG -> Log {");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("@column ENTRY_TYPE -> Log.entryType as CHAR @length(2);");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("@primary (ENTRY_TYPE);");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _builder.newLine();
      _builder.append("class LogWidget : Widget {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("class ImpLog : Structure -> Log {");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("var entryType : Short -> Log.entryType;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.newLine();
      _builder.append("var impLog : ImpLog;");
      _builder.newLine();
      _builder.newLine();
      _builder.append("public func start() {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _builder.newLine();
      final String model = _builder.toString();
      final Consumer<Issue> _function = (Issue it) -> {
        InputOutput.<Issue>println(it);
      };
      this._validationTestHelper.validate(this.loadLibAndParse(model)).forEach(_function);
      final IAcceptor<CompilationTestHelper.Result> _function_1 = (CompilationTestHelper.Result it) -> {
        final Consumer<Issue> _function_2 = (Issue it_1) -> {
          InputOutput.<Issue>println(it_1);
        };
        it.getErrorsAndWarnings().forEach(_function_2);
        InputOutput.<String>println(it.getGeneratedCode("log.sample.project.LogWidget"));
      };
      this._compilationTestHelper.compile(CollectionLiterals.<String>newArrayList(this._langLib.loadLibSource(), model), _function_1);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  private YWidget loadLibAndParse(final CharSequence p) {
    try {
      YWidget _xblockexpression = null;
      {
        final ResourceSet resourceSet = this.resourceSetProvider.get();
        this._langLib.loadLib(resourceSet);
        _xblockexpression = this._parseHelper.parse(p, resourceSet);
      }
      return _xblockexpression;
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
}
