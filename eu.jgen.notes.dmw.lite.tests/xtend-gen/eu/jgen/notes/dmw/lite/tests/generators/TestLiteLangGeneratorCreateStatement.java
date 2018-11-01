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

import com.google.inject.Inject;
import eu.jgen.notes.dmw.lite.tests.LangInjectorProvider;
import eu.jgen.notes.dmw.lite.utility.LangLib;
import java.util.function.Consumer;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.XtextRunner;
import org.eclipse.xtext.util.IAcceptor;
import org.eclipse.xtext.validation.Issue;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.InputOutput;
import org.eclipse.xtext.xbase.testing.CompilationTestHelper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(XtextRunner.class)
@InjectWith(LangInjectorProvider.class)
@SuppressWarnings("all")
public class TestLiteLangGeneratorCreateStatement {
  @Inject
  @Extension
  private CompilationTestHelper _compilationTestHelper;
  
  @Inject
  @Extension
  private LangLib _langLib;
  
  @Test
  public void testGenerateExpressionPlus() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("package dmw.test.derby;");
      _builder.newLine();
      _builder.newLine();
      _builder.append("@database Derby    ");
      _builder.newLine();
      _builder.newLine();
      _builder.append("@java                ");
      _builder.newLine();
      _builder.append("  ");
      _builder.newLine();
      _builder.append("@td database Derby {     ");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("@table DataTypes -> AllDataTypes {");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("@column NUMBER -> AllDataTypes.number as INTEGER @length ( 9 )");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("@column INT_TYPE -> AllDataTypes.intType as INTEGER @length ( 9 )");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("@column SHORT_TYPE -> AllDataTypes.shortType as SMALLINT @length ( 2 )");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("@column DATE_TYPE -> AllDataTypes.dateType as DATE");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("@column TIME_TYPE -> AllDataTypes.timeType as TIME");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("@column STRING_TYPE -> AllDataTypes.stringType as CHAR @length ( 128 )");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("@column TIMESTAMP_TYPE -> AllDataTypes.timestampType as TIMESTAMP");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("@column DOUBLE_TYPE -> AllDataTypes.doubleType as DECIMAL @length ( 2 )");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("@column LONG_TYPE -> AllDataTypes.longType as BIGINT     @length ( 2 )");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("@column BOOL_TYPE -> AllDataTypes.boolType as BOOLEAN");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("@primary (NUMBER)");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("} ");
      _builder.newLine();
      _builder.append("}                     ");
      _builder.newLine();
      _builder.append("     ");
      _builder.newLine();
      _builder.append("@entity AllDataTypes {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("@attr number : Int @length(9) @default(20) ;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("@attr intType : Int @length(9) @default(100000) ;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("@attr shortType : Short @length(2) @default(20) ;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("@attr longType : Long @length(2) @default(20) ;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("@attr doubleType : Double @length(2) @default(20) ;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("@attr dateType : Date;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("@attr timeType : Time;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("@attr timestampType : Timestamp;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("@attr stringType : String @length(128) @default(\"Some message\") ;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("@attr boolType : Bool;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("@id logid(number);       ");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _builder.append("class CreateAllDataTypes : Widget {   ");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("class EntityAllTypes : Structure -> AllDataTypes {  ");
      _builder.newLine();
      _builder.append("\t\t\t");
      _builder.append("public var number : Int -> AllDataTypes.number;");
      _builder.newLine();
      _builder.append("\t\t\t");
      _builder.append("public var intType : Int -> AllDataTypes.intType;");
      _builder.newLine();
      _builder.append("\t\t\t");
      _builder.append("public var shortType : Short -> AllDataTypes.shortType;");
      _builder.newLine();
      _builder.append("\t\t\t");
      _builder.append("public var longType : Long -> AllDataTypes.longType;");
      _builder.newLine();
      _builder.append("\t\t\t");
      _builder.append("public var doubleType : Double -> AllDataTypes.doubleType;");
      _builder.newLine();
      _builder.append("\t\t\t");
      _builder.append("public var dateType : Date -> AllDataTypes.dateType;");
      _builder.newLine();
      _builder.append("\t\t\t");
      _builder.append("public var timeType : Time -> AllDataTypes.timeType;");
      _builder.newLine();
      _builder.append("\t\t\t");
      _builder.append("public var timestampType : Timestamp -> AllDataTypes.timestampType;  ");
      _builder.newLine();
      _builder.append("\t\t\t");
      _builder.append("public var stringType : String -> AllDataTypes.stringType;");
      _builder.newLine();
      _builder.append("\t\t\t");
      _builder.append("public var boolType : Bool -> AllDataTypes.boolType;  ");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("public var entityAllTypes : EntityAllTypes;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("public func start() {");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("db-create entityAllTypes -> AllDataTypes {");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("}  ");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("success {");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("already exist {");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}                  ");
      _builder.newLine();
      _builder.append("}  \t\t\t");
      _builder.newLine();
      _builder.newLine();
      final String body = _builder.toString();
      final IAcceptor<CompilationTestHelper.Result> _function = (CompilationTestHelper.Result it) -> {
        InputOutput.<String>println(it.getGeneratedCode("dmw.test.derby.CreateAllDataTypes"));
        Assert.assertFalse(this.checkIfIssues(it));
      };
      this._compilationTestHelper.compile(CollectionLiterals.<String>newArrayList(this._langLib.loadLibSource(), body), _function);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  public boolean checkIfIssues(final CompilationTestHelper.Result result) {
    int _size = result.getErrorsAndWarnings().size();
    boolean _greaterThan = (_size > 0);
    if (_greaterThan) {
      final Consumer<Issue> _function = (Issue it) -> {
        InputOutput.<Issue>println(it);
      };
      result.getErrorsAndWarnings().forEach(_function);
      return true;
    }
    result.getCompiledClass();
    return false;
  }
}
