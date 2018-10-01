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
import eu.jgen.notes.dmw.lite.lang.YAnnotAbstractColumn;
import eu.jgen.notes.dmw.lite.lang.YAnnotAttr;
import eu.jgen.notes.dmw.lite.lang.YWidget;
import eu.jgen.notes.dmw.lite.tests.LangInjectorProvider;
import eu.jgen.notes.dmw.lite.utility.LangDBUtil;
import eu.jgen.notes.dmw.lite.utility.LangLib;
import java.util.function.Consumer;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.XtextRunner;
import org.eclipse.xtext.testing.util.ParseHelper;
import org.eclipse.xtext.testing.validation.ValidationTestHelper;
import org.eclipse.xtext.validation.Issue;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.InputOutput;
import org.eclipse.xtext.xbase.lib.IteratorExtensions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(XtextRunner.class)
@InjectWith(LangInjectorProvider.class)
@SuppressWarnings("all")
public class CreateColumnUsingAttributeAsTemplateTests {
  @Inject
  @Extension
  private ParseHelper<YWidget> _parseHelper;
  
  @Inject
  @Extension
  private LangDBUtil _langDBUtil;
  
  @Inject
  @Extension
  private ValidationTestHelper _validationTestHelper;
  
  @Inject
  @Extension
  private LangLib _langLib;
  
  @Inject
  private Provider<ResourceSet> resourceSetProvider;
  
  @Test
  public void testIntegerMandatory() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package eu.jgen.notes.dmw.sample;\t\t");
    _builder.newLine();
    _builder.append("@entity LogRecord {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@attr number : Int @length(9);");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    final YWidget model = this.loadLibAndParse(_builder);
    final Function1<EObject, Boolean> _function = (EObject element) -> {
      return Boolean.valueOf((element instanceof YAnnotAttr));
    };
    EObject _findFirst = IteratorExtensions.<EObject>findFirst(model.eAllContents(), _function);
    final YAnnotAttr attr = ((YAnnotAttr) _findFirst);
    final Consumer<Issue> _function_1 = (Issue it) -> {
      InputOutput.<Issue>println(it);
    };
    this._validationTestHelper.validate(model).forEach(_function_1);
    final YAnnotAbstractColumn abstractColumn = this._langDBUtil.converAttributeIntoAbstractColumn(attr);
    Assert.assertEquals("NUMBER", abstractColumn.getName());
    Assert.assertEquals("INTEGER", this._langDBUtil.getColumnTypeName(abstractColumn));
    Assert.assertEquals(9, this._langDBUtil.getColumnLength(abstractColumn));
    Assert.assertEquals(0, this._langDBUtil.getColumnDecimal(abstractColumn));
    Assert.assertFalse(this._langDBUtil.isColumnOptional(abstractColumn));
  }
  
  @Test
  public void testIntegerOptional() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package eu.jgen.notes.dmw.sample;\t\t");
    _builder.newLine();
    _builder.append("@entity LogRecord {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@attr number : Int? @length(9);");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    final YWidget model = this.loadLibAndParse(_builder);
    final Function1<EObject, Boolean> _function = (EObject element) -> {
      return Boolean.valueOf((element instanceof YAnnotAttr));
    };
    EObject _findFirst = IteratorExtensions.<EObject>findFirst(model.eAllContents(), _function);
    final YAnnotAttr attr = ((YAnnotAttr) _findFirst);
    final Consumer<Issue> _function_1 = (Issue it) -> {
      InputOutput.<Issue>println(it);
    };
    this._validationTestHelper.validate(model).forEach(_function_1);
    final YAnnotAbstractColumn abstractColumn = this._langDBUtil.converAttributeIntoAbstractColumn(attr);
    Assert.assertEquals("NUMBER", abstractColumn.getName());
    Assert.assertEquals("INTEGER", this._langDBUtil.getColumnTypeName(abstractColumn));
    Assert.assertEquals(9, this._langDBUtil.getColumnLength(abstractColumn));
    Assert.assertEquals(0, this._langDBUtil.getColumnDecimal(abstractColumn));
    Assert.assertTrue(this._langDBUtil.isColumnOptional(abstractColumn));
  }
  
  @Test
  public void testShortMandatory() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package eu.jgen.notes.dmw.sample;\t\t");
    _builder.newLine();
    _builder.append("@entity LogRecord {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@attr number : Short @length(4);");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    final YWidget model = this.loadLibAndParse(_builder);
    final Function1<EObject, Boolean> _function = (EObject element) -> {
      return Boolean.valueOf((element instanceof YAnnotAttr));
    };
    EObject _findFirst = IteratorExtensions.<EObject>findFirst(model.eAllContents(), _function);
    final YAnnotAttr attr = ((YAnnotAttr) _findFirst);
    final Consumer<Issue> _function_1 = (Issue it) -> {
      InputOutput.<Issue>println(it);
    };
    this._validationTestHelper.validate(model).forEach(_function_1);
    final YAnnotAbstractColumn abstractColumn = this._langDBUtil.converAttributeIntoAbstractColumn(attr);
    Assert.assertEquals("NUMBER", abstractColumn.getName());
    Assert.assertEquals("SMALLINT", this._langDBUtil.getColumnTypeName(abstractColumn));
    Assert.assertEquals(4, this._langDBUtil.getColumnLength(abstractColumn));
    Assert.assertEquals(0, this._langDBUtil.getColumnDecimal(abstractColumn));
    Assert.assertFalse(this._langDBUtil.isColumnOptional(abstractColumn));
  }
  
  @Test
  public void testShortOptional() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package eu.jgen.notes.dmw.sample;\t\t");
    _builder.newLine();
    _builder.append("@entity LogRecord {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@attr number : Short? @length(4);");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    final YWidget model = this.loadLibAndParse(_builder);
    final Function1<EObject, Boolean> _function = (EObject element) -> {
      return Boolean.valueOf((element instanceof YAnnotAttr));
    };
    EObject _findFirst = IteratorExtensions.<EObject>findFirst(model.eAllContents(), _function);
    final YAnnotAttr attr = ((YAnnotAttr) _findFirst);
    final Consumer<Issue> _function_1 = (Issue it) -> {
      InputOutput.<Issue>println(it);
    };
    this._validationTestHelper.validate(model).forEach(_function_1);
    final YAnnotAbstractColumn abstractColumn = this._langDBUtil.converAttributeIntoAbstractColumn(attr);
    Assert.assertEquals("NUMBER", abstractColumn.getName());
    Assert.assertEquals("SMALLINT", this._langDBUtil.getColumnTypeName(abstractColumn));
    Assert.assertEquals(4, this._langDBUtil.getColumnLength(abstractColumn));
    Assert.assertEquals(0, this._langDBUtil.getColumnDecimal(abstractColumn));
    Assert.assertTrue(this._langDBUtil.isColumnOptional(abstractColumn));
  }
  
  @Test
  public void testDoubleMandatory() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package eu.jgen.notes.dmw.sample;\t\t");
    _builder.newLine();
    _builder.append("@entity LogRecord {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@attr number : Double @decimal(9,2);");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    final YWidget model = this.loadLibAndParse(_builder);
    final Function1<EObject, Boolean> _function = (EObject element) -> {
      return Boolean.valueOf((element instanceof YAnnotAttr));
    };
    EObject _findFirst = IteratorExtensions.<EObject>findFirst(model.eAllContents(), _function);
    final YAnnotAttr attr = ((YAnnotAttr) _findFirst);
    final Consumer<Issue> _function_1 = (Issue it) -> {
      InputOutput.<Issue>println(it);
    };
    this._validationTestHelper.validate(model).forEach(_function_1);
    final YAnnotAbstractColumn abstractColumn = this._langDBUtil.converAttributeIntoAbstractColumn(attr);
    Assert.assertEquals("NUMBER", abstractColumn.getName());
    Assert.assertEquals("DECIMAL", this._langDBUtil.getColumnTypeName(abstractColumn));
    Assert.assertEquals(9, this._langDBUtil.getColumnLength(abstractColumn));
    Assert.assertEquals(2, this._langDBUtil.getColumnDecimal(abstractColumn));
    Assert.assertFalse(this._langDBUtil.isColumnOptional(abstractColumn));
  }
  
  @Test
  public void testDoubleOptional() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package eu.jgen.notes.dmw.sample;\t\t");
    _builder.newLine();
    _builder.append("@entity LogRecord {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@attr number : Double? @decimal(9,2);");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    final YWidget model = this.loadLibAndParse(_builder);
    final Function1<EObject, Boolean> _function = (EObject element) -> {
      return Boolean.valueOf((element instanceof YAnnotAttr));
    };
    EObject _findFirst = IteratorExtensions.<EObject>findFirst(model.eAllContents(), _function);
    final YAnnotAttr attr = ((YAnnotAttr) _findFirst);
    final Consumer<Issue> _function_1 = (Issue it) -> {
      InputOutput.<Issue>println(it);
    };
    this._validationTestHelper.validate(model).forEach(_function_1);
    final YAnnotAbstractColumn abstractColumn = this._langDBUtil.converAttributeIntoAbstractColumn(attr);
    Assert.assertEquals("NUMBER", abstractColumn.getName());
    Assert.assertEquals("DECIMAL", this._langDBUtil.getColumnTypeName(abstractColumn));
    Assert.assertEquals(9, this._langDBUtil.getColumnLength(abstractColumn));
    Assert.assertEquals(2, this._langDBUtil.getColumnDecimal(abstractColumn));
    Assert.assertTrue(this._langDBUtil.isColumnOptional(abstractColumn));
  }
  
  @Test
  public void testStringMandatory() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package eu.jgen.notes.dmw.sample;\t\t");
    _builder.newLine();
    _builder.append("@entity LogRecord {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@attr shortDesc : String @length(20);");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    final YWidget model = this.loadLibAndParse(_builder);
    final Function1<EObject, Boolean> _function = (EObject element) -> {
      return Boolean.valueOf((element instanceof YAnnotAttr));
    };
    EObject _findFirst = IteratorExtensions.<EObject>findFirst(model.eAllContents(), _function);
    final YAnnotAttr attr = ((YAnnotAttr) _findFirst);
    final Consumer<Issue> _function_1 = (Issue it) -> {
      InputOutput.<Issue>println(it);
    };
    this._validationTestHelper.validate(model).forEach(_function_1);
    final YAnnotAbstractColumn abstractColumn = this._langDBUtil.converAttributeIntoAbstractColumn(attr);
    Assert.assertEquals("SHORT_DESC", abstractColumn.getName());
    Assert.assertEquals("CHAR", this._langDBUtil.getColumnTypeName(abstractColumn));
    Assert.assertEquals(20, this._langDBUtil.getColumnLength(abstractColumn));
    Assert.assertFalse(this._langDBUtil.isColumnOptional(abstractColumn));
  }
  
  @Test
  public void testStringOptional() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package eu.jgen.notes.dmw.sample;\t\t");
    _builder.newLine();
    _builder.append("@entity LogRecord {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("@attr shortDesc : String? @length(20);");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    final YWidget model = this.loadLibAndParse(_builder);
    final Function1<EObject, Boolean> _function = (EObject element) -> {
      return Boolean.valueOf((element instanceof YAnnotAttr));
    };
    EObject _findFirst = IteratorExtensions.<EObject>findFirst(model.eAllContents(), _function);
    final YAnnotAttr attr = ((YAnnotAttr) _findFirst);
    final Consumer<Issue> _function_1 = (Issue it) -> {
      InputOutput.<Issue>println(it);
    };
    this._validationTestHelper.validate(model).forEach(_function_1);
    final YAnnotAbstractColumn abstractColumn = this._langDBUtil.converAttributeIntoAbstractColumn(attr);
    Assert.assertEquals("SHORT_DESC", abstractColumn.getName());
    Assert.assertEquals("CHAR", this._langDBUtil.getColumnTypeName(abstractColumn));
    Assert.assertEquals(20, this._langDBUtil.getColumnLength(abstractColumn));
    Assert.assertTrue(this._langDBUtil.isColumnOptional(abstractColumn));
  }
  
  @Test
  public void testBlobMandatory() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package eu.jgen.notes.dmw.sample;\t\t");
    _builder.newLine();
    _builder.append("@entity LogRecord {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@attr picture : Blob @length(20000);");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    final YWidget model = this.loadLibAndParse(_builder);
    final Function1<EObject, Boolean> _function = (EObject element) -> {
      return Boolean.valueOf((element instanceof YAnnotAttr));
    };
    EObject _findFirst = IteratorExtensions.<EObject>findFirst(model.eAllContents(), _function);
    final YAnnotAttr attr = ((YAnnotAttr) _findFirst);
    final Consumer<Issue> _function_1 = (Issue it) -> {
      InputOutput.<Issue>println(it);
    };
    this._validationTestHelper.validate(model).forEach(_function_1);
    final YAnnotAbstractColumn abstractColumn = this._langDBUtil.converAttributeIntoAbstractColumn(attr);
    Assert.assertEquals("PICTURE", abstractColumn.getName());
    Assert.assertEquals("BLOB", this._langDBUtil.getColumnTypeName(abstractColumn));
    Assert.assertEquals(20000, this._langDBUtil.getColumnLength(abstractColumn));
    Assert.assertFalse(this._langDBUtil.isColumnOptional(abstractColumn));
  }
  
  @Test
  public void testBlobOptional() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package eu.jgen.notes.dmw.sample;\t\t");
    _builder.newLine();
    _builder.append("@entity LogRecord {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("@attr picture : Blob? @length(20000);");
    _builder.newLine();
    _builder.append("\t\t\t\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    final YWidget model = this.loadLibAndParse(_builder);
    final Function1<EObject, Boolean> _function = (EObject element) -> {
      return Boolean.valueOf((element instanceof YAnnotAttr));
    };
    EObject _findFirst = IteratorExtensions.<EObject>findFirst(model.eAllContents(), _function);
    final YAnnotAttr attr = ((YAnnotAttr) _findFirst);
    final Consumer<Issue> _function_1 = (Issue it) -> {
      InputOutput.<Issue>println(it);
    };
    this._validationTestHelper.validate(model).forEach(_function_1);
    final YAnnotAbstractColumn abstractColumn = this._langDBUtil.converAttributeIntoAbstractColumn(attr);
    Assert.assertEquals("PICTURE", abstractColumn.getName());
    Assert.assertEquals("BLOB", this._langDBUtil.getColumnTypeName(abstractColumn));
    Assert.assertEquals(20000, this._langDBUtil.getColumnLength(abstractColumn));
    Assert.assertTrue(this._langDBUtil.isColumnOptional(abstractColumn));
  }
  
  @Test
  public void testDateMandatory() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package eu.jgen.notes.dmw.sample;\t\t");
    _builder.newLine();
    _builder.append("@entity LogRecord {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@attr updateDate : Date;");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    final YWidget model = this.loadLibAndParse(_builder);
    final Function1<EObject, Boolean> _function = (EObject element) -> {
      return Boolean.valueOf((element instanceof YAnnotAttr));
    };
    EObject _findFirst = IteratorExtensions.<EObject>findFirst(model.eAllContents(), _function);
    final YAnnotAttr attr = ((YAnnotAttr) _findFirst);
    final Consumer<Issue> _function_1 = (Issue it) -> {
      InputOutput.<Issue>println(it);
    };
    this._validationTestHelper.validate(model).forEach(_function_1);
    final YAnnotAbstractColumn abstractColumn = this._langDBUtil.converAttributeIntoAbstractColumn(attr);
    Assert.assertEquals("UPDATE_DATE", abstractColumn.getName());
    Assert.assertEquals("DATE", this._langDBUtil.getColumnTypeName(abstractColumn));
    Assert.assertFalse(this._langDBUtil.isColumnOptional(abstractColumn));
  }
  
  @Test
  public void testDateOptional() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package eu.jgen.notes.dmw.sample;\t\t");
    _builder.newLine();
    _builder.append("@entity LogRecord {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@attr updateDate : Date?;");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    final YWidget model = this.loadLibAndParse(_builder);
    final Function1<EObject, Boolean> _function = (EObject element) -> {
      return Boolean.valueOf((element instanceof YAnnotAttr));
    };
    EObject _findFirst = IteratorExtensions.<EObject>findFirst(model.eAllContents(), _function);
    final YAnnotAttr attr = ((YAnnotAttr) _findFirst);
    final Consumer<Issue> _function_1 = (Issue it) -> {
      InputOutput.<Issue>println(it);
    };
    this._validationTestHelper.validate(model).forEach(_function_1);
    final YAnnotAbstractColumn abstractColumn = this._langDBUtil.converAttributeIntoAbstractColumn(attr);
    Assert.assertEquals("UPDATE_DATE", abstractColumn.getName());
    Assert.assertEquals("DATE", this._langDBUtil.getColumnTypeName(abstractColumn));
    Assert.assertTrue(this._langDBUtil.isColumnOptional(abstractColumn));
  }
  
  @Test
  public void testTimeMandatory() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package eu.jgen.notes.dmw.sample;\t\t");
    _builder.newLine();
    _builder.append("@entity LogRecord {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@attr updateTime : Time;");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    final YWidget model = this.loadLibAndParse(_builder);
    final Function1<EObject, Boolean> _function = (EObject element) -> {
      return Boolean.valueOf((element instanceof YAnnotAttr));
    };
    EObject _findFirst = IteratorExtensions.<EObject>findFirst(model.eAllContents(), _function);
    final YAnnotAttr attr = ((YAnnotAttr) _findFirst);
    final Consumer<Issue> _function_1 = (Issue it) -> {
      InputOutput.<Issue>println(it);
    };
    this._validationTestHelper.validate(model).forEach(_function_1);
    final YAnnotAbstractColumn abstractColumn = this._langDBUtil.converAttributeIntoAbstractColumn(attr);
    Assert.assertEquals("UPDATE_TIME", abstractColumn.getName());
    Assert.assertEquals("TIME", this._langDBUtil.getColumnTypeName(abstractColumn));
    Assert.assertFalse(this._langDBUtil.isColumnOptional(abstractColumn));
  }
  
  @Test
  public void testTimeOptional() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package eu.jgen.notes.dmw.sample;\t\t");
    _builder.newLine();
    _builder.append("@entity LogRecord {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@attr updateTime : Time?;");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    final YWidget model = this.loadLibAndParse(_builder);
    final Function1<EObject, Boolean> _function = (EObject element) -> {
      return Boolean.valueOf((element instanceof YAnnotAttr));
    };
    EObject _findFirst = IteratorExtensions.<EObject>findFirst(model.eAllContents(), _function);
    final YAnnotAttr attr = ((YAnnotAttr) _findFirst);
    final Consumer<Issue> _function_1 = (Issue it) -> {
      InputOutput.<Issue>println(it);
    };
    this._validationTestHelper.validate(model).forEach(_function_1);
    final YAnnotAbstractColumn abstractColumn = this._langDBUtil.converAttributeIntoAbstractColumn(attr);
    Assert.assertEquals("UPDATE_TIME", abstractColumn.getName());
    Assert.assertEquals("TIME", this._langDBUtil.getColumnTypeName(abstractColumn));
    Assert.assertTrue(this._langDBUtil.isColumnOptional(abstractColumn));
  }
  
  @Test
  public void testTimestampMandatory() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package eu.jgen.notes.dmw.sample;\t\t");
    _builder.newLine();
    _builder.append("@entity LogRecord {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@attr updateTimestamp : Timestamp;");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    final YWidget model = this.loadLibAndParse(_builder);
    final Function1<EObject, Boolean> _function = (EObject element) -> {
      return Boolean.valueOf((element instanceof YAnnotAttr));
    };
    EObject _findFirst = IteratorExtensions.<EObject>findFirst(model.eAllContents(), _function);
    final YAnnotAttr attr = ((YAnnotAttr) _findFirst);
    final Consumer<Issue> _function_1 = (Issue it) -> {
      InputOutput.<Issue>println(it);
    };
    this._validationTestHelper.validate(model).forEach(_function_1);
    final YAnnotAbstractColumn abstractColumn = this._langDBUtil.converAttributeIntoAbstractColumn(attr);
    Assert.assertEquals("UPDATE_TIMESTAMP", abstractColumn.getName());
    Assert.assertEquals("TIMESTAMP", this._langDBUtil.getColumnTypeName(abstractColumn));
    Assert.assertFalse(this._langDBUtil.isColumnOptional(abstractColumn));
  }
  
  @Test
  public void testTimestampOptional() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package eu.jgen.notes.dmw.sample;\t\t");
    _builder.newLine();
    _builder.append("@entity LogRecord {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@attr updateTimestamp : Timestamp?;");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    final YWidget model = this.loadLibAndParse(_builder);
    final Function1<EObject, Boolean> _function = (EObject element) -> {
      return Boolean.valueOf((element instanceof YAnnotAttr));
    };
    EObject _findFirst = IteratorExtensions.<EObject>findFirst(model.eAllContents(), _function);
    final YAnnotAttr attr = ((YAnnotAttr) _findFirst);
    final Consumer<Issue> _function_1 = (Issue it) -> {
      InputOutput.<Issue>println(it);
    };
    this._validationTestHelper.validate(model).forEach(_function_1);
    final YAnnotAbstractColumn abstractColumn = this._langDBUtil.converAttributeIntoAbstractColumn(attr);
    Assert.assertEquals("UPDATE_TIMESTAMP", abstractColumn.getName());
    Assert.assertEquals("TIMESTAMP", this._langDBUtil.getColumnTypeName(abstractColumn));
    Assert.assertTrue(this._langDBUtil.isColumnOptional(abstractColumn));
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
