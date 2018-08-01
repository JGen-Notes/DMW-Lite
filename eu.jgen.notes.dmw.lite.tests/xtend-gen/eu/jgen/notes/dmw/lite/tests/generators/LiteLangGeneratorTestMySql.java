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
package eu.jgen.notes.dmw.lite.tests.generators;

import com.google.common.base.Objects;
import com.google.inject.Inject;
import eu.jgen.notes.dmw.lite.tests.LangInjectorProvider;
import eu.jgen.notes.dmw.lite.utility.LangLib;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.diagnostics.Severity;
import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.XtextRunner;
import org.eclipse.xtext.util.IAcceptor;
import org.eclipse.xtext.validation.Issue;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.util.ReflectExtensions;
import org.eclipse.xtext.xbase.testing.CompilationTestHelper;
import org.eclipse.xtext.xbase.testing.TemporaryFolder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(XtextRunner.class)
@InjectWith(LangInjectorProvider.class)
@SuppressWarnings("all")
public class LiteLangGeneratorTestMySql {
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
      _builder.append("@database MySQL");
      _builder.newLine();
      _builder.newLine();
      _builder.append("@entity Log {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("@attr entryType : Short @length(2);");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("@attr message : String @length(128);");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("@attr timeCreated : Time;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("@id logid(timeCreated);");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _builder.newLine();
      _builder.append("@td database MySQL {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("@table LOG -> Log {");
      _builder.newLine();
      _builder.append("\t   ");
      _builder.append("@column ENTRY_TYPE -> Log.entryType as SMALLINT ;");
      _builder.newLine();
      _builder.append("\t   ");
      _builder.append("@column MESSAGE -> Log.message as CHAR @length ( 128 ) \t");
      _builder.newLine();
      _builder.append("\t   ");
      _builder.append("@column TIME_CREATED -> Log.timeCreated as TIME");
      _builder.newLine();
      _builder.append("\t   ");
      _builder.append("@primary (TIME_CREATED);");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      StringConcatenation _builder_1 = new StringConcatenation();
      _builder_1.append("CREATE TABLE `LOG` (");
      _builder_1.newLine();
      _builder_1.append("\t");
      _builder_1.append("`ENTRY_TYPE` SMALLINT ,");
      _builder_1.newLine();
      _builder_1.append("\t");
      _builder_1.append("`MESSAGE` CHAR() ,");
      _builder_1.newLine();
      _builder_1.append("\t");
      _builder_1.append("`TIME_CREATED` TIME ");
      _builder_1.newLine();
      _builder_1.append(",\tCONSTRAINT `pk_LOG`");
      _builder_1.newLine();
      _builder_1.append("\t");
      _builder_1.append("PRIMARY KEY (`TIME_CREATED`)");
      _builder_1.newLine();
      _builder_1.append(");");
      _builder_1.newLine();
      this._compilationTestHelper.assertCompilesTo(_builder, _builder_1);
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
