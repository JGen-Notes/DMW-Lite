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
import eu.jgen.notes.dmw.lite.lang.LangPackage;
import eu.jgen.notes.dmw.lite.lang.YWidget;
import eu.jgen.notes.dmw.lite.tests.LangInjectorProvider;
import eu.jgen.notes.dmw.lite.utility.LangLib;
import eu.jgen.notes.dmw.lite.validation.LangValidator;
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
public class LiteLangValidatorTest2 {
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
  public void testCheckClassFirstCharacterNotCapital() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("class c : Object {");
      _builder.newLine();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      this._validationTestHelper.assertError(this._parseHelper.parse(_builder, this.loadLibInResourceSet()), 
        LangPackage.eINSTANCE.getYClass(), 
        LangValidator.CLASS_NAME_FIRST_CHARACTER_NOT_CAPITAL, 
        "Class name should start with a capital letter");
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testCheckEntityFirstCharacterNotCapital() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("@entity person {");
      _builder.newLine();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      this._validationTestHelper.assertError(this._parseHelper.parse(_builder, this.loadLibInResourceSet()), 
        LangPackage.eINSTANCE.getYAnnotEntity(), 
        LangValidator.ENTITY_NAME_FIRST_CHARACTER_NOT_CAPITAL, 
        "Entity name should start with a capital letter");
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testCheckAttributeFirstCharacterNotLowercase() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("@entity person {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("@attr Number : Int;");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      this._validationTestHelper.assertError(this._parseHelper.parse(_builder, this.loadLibInResourceSet()), 
        LangPackage.eINSTANCE.getYAnnotAttr(), 
        LangValidator.ATTRIBUTE_NAME_FIRST_CHARACTER_NOT_LOWERCASE, 
        "Attribute name should start with a lower case letter");
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testCheckPropertyFirstCharacterNotLowercase() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("class C : Object {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("var D : Int;\t\t\t");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      this._validationTestHelper.assertError(this._parseHelper.parse(_builder, this.loadLibInResourceSet()), 
        LangPackage.eINSTANCE.getYProperty(), 
        LangValidator.PROPERTY_NAME_FIRST_CHARACTER_NOT_LOWERCASE, 
        "Property name should start with a lower case letter");
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testCheckFunctionFirstCharacterNotLowercase() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("class C : Object {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("var d : Int;");
      _builder.newLine();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("func Start() {");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("}\t\t\t");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      this._validationTestHelper.assertError(this._parseHelper.parse(_builder, this.loadLibInResourceSet()), 
        LangPackage.eINSTANCE.getYFunction(), 
        LangValidator.FUNCTION_NAME_FIRST_CHARACTER_NOT_LOWERCASE, 
        "Function name should start with a lower case letter");
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testCheckVariableFirstCharacterNotLowercase() {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method or field YVariableDeclaration is undefined for the type LangPackage");
  }
  
  private ResourceSet loadLibInResourceSet() {
    ResourceSet _get = this.resourceSetProvider.get();
    final Procedure1<ResourceSet> _function = (ResourceSet it) -> {
      this._langLib.loadLib(it);
    };
    return ObjectExtensions.<ResourceSet>operator_doubleArrow(_get, _function);
  }
}
