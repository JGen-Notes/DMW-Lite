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
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.XtextRunner;
import org.eclipse.xtext.testing.util.ParseHelper;
import org.eclipse.xtext.testing.validation.ValidationTestHelper;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(XtextRunner.class)
@InjectWith(LangInjectorProvider.class)
@SuppressWarnings("all")
public class LiteLangValidatorTest4 {
  @Inject
  @Extension
  private ParseHelper<YWidget> _parseHelper;
  
  @Inject
  @Extension
  private ValidationTestHelper _validationTestHelper;
  
  @Inject
  @Extension
  private LangLib _langLib;
  
  @Inject
  private Provider<ResourceSet> resourceSetProvider;
  
  @Test
  public void testCheckShortCompatibleWithDefaultValue() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("package sample.project           ");
      _builder.newLine();
      _builder.newLine();
      _builder.append("import eu.jgen.notes.dmw.lite.runtimes.XStructure  ");
      _builder.newLine();
      _builder.append("import eu.jgen.notes.dmw.lite.runtimes.XWidget");
      _builder.newLine();
      _builder.append("import eu.jgen.notes.dmw.lite.runtimes.XInt");
      _builder.newLine();
      _builder.append("import eu.jgen.notes.dmw.lite.runtimes.XString");
      _builder.newLine();
      _builder.append("import eu.jgen.notes.dmw.lite.runtimes.XShort");
      _builder.newLine();
      _builder.newLine();
      _builder.newLine();
      _builder.newLine();
      _builder.append("@entity Ee {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("@attr number : XInt @length(9) @default(20) ;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("@attr type : XShort @length(2) @default(20) ;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("@attr message : XString @length(128) @default(\"Some message\") ;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("@attr description : XString ? @length(500) @default(\"Some description\") ;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("@id logid(number);");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _builder.newLine();
      _builder.append("class Cos   {    ");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("class CurrentEe : XStructure -> Ee {");
      _builder.newLine();
      _builder.append("\t\t\t");
      _builder.append("var number : XInt -> Ee.number;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("} ");
      _builder.newLine();
      _builder.append("}  ");
      _builder.newLine();
      final String result = _builder.toString();
      this._validationTestHelper.validate(this._parseHelper.parse(result));
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  private ResourceSet loadLibInResourceSet() {
    ResourceSet _get = this.resourceSetProvider.get();
    final Procedure1<ResourceSet> _function = (ResourceSet it) -> {
      this._langLib.loadLib(it);
    };
    return ObjectExtensions.<ResourceSet>operator_doubleArrow(_get, _function);
  }
}
