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
import eu.jgen.notes.dmw.lite.lang.YAnnotEntity;
import eu.jgen.notes.dmw.lite.lang.YAnnotTable;
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
public class CreateTableUsingEntityAsTemplateTests {
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
  public void testEntityDefault() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package eu.jgen.notes.dmw.sample;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("@entity LogRecord {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("/*");
    _builder.newLine();
    _builder.append("\t ");
    _builder.append("*  How to read below: Log has attribute <code>entryType</code> of type <code>Short</code> having");
    _builder.newLine();
    _builder.append("\t ");
    _builder.append("*  maximum length of 2 digits.");
    _builder.newLine();
    _builder.append("\t ");
    _builder.append("*/ ");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@attr entryType : Short @length (2);");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@attr message : String @length (128);");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@attr timeCreated : Time;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@id logid (timeCreated);");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    final YWidget model = this.loadLibAndParse(_builder);
    final Consumer<Issue> _function = (Issue it) -> {
      InputOutput.<Issue>println(it);
    };
    this._validationTestHelper.validate(model).forEach(_function);
    final Function1<EObject, Boolean> _function_1 = (EObject element) -> {
      return Boolean.valueOf((element instanceof YAnnotEntity));
    };
    final EObject entity = IteratorExtensions.<EObject>findFirst(model.eAllContents(), _function_1);
    final YAnnotTable table = this._langDBUtil.converEntityIntoTable(((YAnnotEntity) entity));
    Assert.assertEquals("LOG_RECORD", table.getName());
  }
  
  @Test
  public void testEntityAnnotated() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package eu.jgen.notes.dmw.sample;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("@entity Log {");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("/*");
    _builder.newLine();
    _builder.append("\t\t\t\t ");
    _builder.append("*  How to read below: Log has attribute <code>entryType</code> of type <code>Short</code> having");
    _builder.newLine();
    _builder.append("\t\t\t\t ");
    _builder.append("*  maximum length of 2 digits.");
    _builder.newLine();
    _builder.append("\t\t\t\t ");
    _builder.append("*/ ");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("@attr entryType : Short @length (2);");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("@attr message : String @length (128);");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("@attr timeCreated : Time;");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("@id logid (timeCreated);");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("}");
    _builder.newLine();
    final YWidget model = this.loadLibAndParse(_builder);
    final Consumer<Issue> _function = (Issue it) -> {
      InputOutput.<Issue>println(it);
    };
    this._validationTestHelper.validate(model).forEach(_function);
    final Function1<EObject, Boolean> _function_1 = (EObject element) -> {
      return Boolean.valueOf((element instanceof YAnnotEntity));
    };
    final EObject entity = IteratorExtensions.<EObject>findFirst(model.eAllContents(), _function_1);
    final YAnnotTable table = this._langDBUtil.converEntityIntoTable(((YAnnotEntity) entity));
    Assert.assertEquals("LOG", table.getName());
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
