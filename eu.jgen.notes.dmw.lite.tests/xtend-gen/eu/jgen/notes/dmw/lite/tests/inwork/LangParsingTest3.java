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
package eu.jgen.notes.dmw.lite.tests.inwork;

import com.google.inject.Inject;
import com.google.inject.Provider;
import eu.jgen.notes.dmw.lite.lang.YWidget;
import eu.jgen.notes.dmw.lite.tests.LangInjectorProvider;
import eu.jgen.notes.dmw.lite.utility.LangLib;
import eu.jgen.notes.dmw.lite.utility.LangUtil;
import java.util.function.Consumer;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.XtextRunner;
import org.eclipse.xtext.testing.util.ParseHelper;
import org.eclipse.xtext.testing.validation.ValidationTestHelper;
import org.eclipse.xtext.validation.Issue;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.InputOutput;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(XtextRunner.class)
@InjectWith(LangInjectorProvider.class)
@SuppressWarnings("all")
public class LangParsingTest3 {
  @Inject
  @Extension
  private ParseHelper<YWidget> parseHelper;
  
  @Inject
  @Extension
  private ValidationTestHelper _validationTestHelper;
  
  @Inject
  @Extension
  private LangUtil _langUtil;
  
  @Inject
  @Extension
  private LangLib _langLib;
  
  @Inject
  @Extension
  private /* LangIndex */Object _langIndex;
  
  @Inject
  private Provider<ResourceSet> rsp;
  
  @Test
  public void loadModel() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class Exits  {  ");
    _builder.newLine();
    _builder.append(" \t");
    _builder.append("public var  ExistState person_nf;");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("}");
    _builder.newLine();
    _builder.append(" ");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("class Person : Entity {  ");
    _builder.newLine();
    _builder.append(" \t");
    _builder.append("attr Int number #(9);");
    _builder.newLine();
    _builder.append(" \t");
    _builder.append("attr String firstName #(25);");
    _builder.newLine();
    _builder.append(" \t");
    _builder.append("attr String lastName #(25);");
    _builder.newLine();
    _builder.append(" \t");
    _builder.append("attr Date dateOfBirth;\t");
    _builder.newLine();
    _builder.append(" \t");
    _builder.append("id Identifier myid (number);          ");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.newLine();
    _builder.append("class CreatePerson : Widget {");
    _builder.newLine();
    _builder.append("   ");
    _builder.append("class PersonView : View -> Person {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("public var  Int number;");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("public var  String firstName ;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t ");
    _builder.append("var Exits exits;");
    _builder.newLine();
    _builder.append("\t ");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("var PersonView a ; ");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("var PersonView b ; ");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("var Group impGrp ; ");
    _builder.newLine();
    _builder.append("\t\t\t ");
    _builder.newLine();
    _builder.append("public\thello() -> String { ");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("this.a.number = 1;\t\t\t \t\t ");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("return \"\"; ");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    final YWidget result = this.loadLibAndParse(_builder);
    final Consumer<Issue> _function = (Issue it) -> {
      InputOutput.<Issue>println(it);
    };
    this._validationTestHelper.validate(result).forEach(_function);
  }
  
  private YWidget loadLibAndParse(final CharSequence p) {
    try {
      YWidget _xblockexpression = null;
      {
        final ResourceSet resourceSet = this.rsp.get();
        this._langLib.loadLib(resourceSet);
        _xblockexpression = this.parseHelper.parse(p, resourceSet);
      }
      return _xblockexpression;
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
}
