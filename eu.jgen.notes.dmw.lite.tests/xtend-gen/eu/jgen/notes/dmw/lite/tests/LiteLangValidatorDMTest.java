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
import org.eclipse.emf.ecore.EClass;
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
public class LiteLangValidatorDMTest {
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
  public void testEntityHierarchyCycle() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("@entity A : C {}");
      _builder.newLine();
      _builder.append("@entity C : B {}");
      _builder.newLine();
      _builder.append("@entity B : A {}");
      _builder.newLine();
      YWidget _parse = this._parseHelper.parse(_builder);
      final Procedure1<YWidget> _function = (YWidget it) -> {
        this.assertEntityHierarchyCycle(it, "A");
        this.assertEntityHierarchyCycle(it, "B");
        this.assertEntityHierarchyCycle(it, "C");
      };
      ObjectExtensions.<YWidget>operator_doubleArrow(_parse, _function);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testNoHierarchyCycle() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("@entity A : C {}");
      _builder.newLine();
      _builder.append("@entity C : B {}");
      _builder.newLine();
      _builder.append("@entity B {}");
      _builder.newLine();
      this._validationTestHelper.assertNoErrors(this._parseHelper.parse(_builder));
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testDuplicateEntities() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("@entity C {}");
    _builder.newLine();
    _builder.append("@entity C {}");
    _builder.newLine();
    this.assertDuplicate(_builder.toString(), LangPackage.eINSTANCE.getYAnnotEntity(), "entity", "C");
  }
  
  @Test
  public void testDuplicateEntityAcrossFiles() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("package my.first.pack;");
      _builder.newLine();
      _builder.newLine();
      _builder.append("@entity C {}");
      final YWidget first = this._parseHelper.parse(_builder);
      StringConcatenation _builder_1 = new StringConcatenation();
      _builder_1.append("package my.first.pack;");
      _builder_1.newLine();
      _builder_1.append("@entity D {}");
      _builder_1.newLine();
      _builder_1.append("@entity C {}");
      _builder_1.newLine();
      this._validationTestHelper.assertError(this._parseHelper.parse(_builder_1, first.eResource().getResourceSet()), 
        LangPackage.eINSTANCE.getYAnnotEntity(), 
        LangValidator.DUPLICATE_ENTITY, 
        "The entity C is already defined");
      this._validationTestHelper.assertError(first, 
        LangPackage.eINSTANCE.getYAnnotEntity(), 
        LangValidator.DUPLICATE_ENTITY, 
        "The entity C is already defined");
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testDuplicateAttribute() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("@entity C {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@attr f : String;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@attr f : String;");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    this.assertDuplicate(_builder.toString(), LangPackage.eINSTANCE.getYAnnotAttr(), "attribute", "f");
  }
  
  @Test
  public void testDuplicateIdentifier() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("@entity C {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@attr f : String;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@id myid(f);");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@id myid(f);");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    this.assertDuplicate(_builder.toString(), LangPackage.eINSTANCE.getYAnnotId(), "identifier", "myid");
  }
  
  @Test
  public void testPropertyAttributeRef1() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("@entity C {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@attr f : String;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@rel isFor -> D * <- D.has;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@id i (f);");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("@entity D {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@attr f : String @length (30);");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@rel has -> C  <- C.isFor;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@id i (f);");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("@entity E : D {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@attr name : Date ?;");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("class TestX : Widget {");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("class InnerX : Structure -> D {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("public var f : String -> D.f;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("}\t\t ");
    _builder.newLine();
    this._validationTestHelper.assertNoErrors(this.loadLibAndParse(_builder));
  }
  
  @Test
  public void testPropertyAttributeRef2() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("@entity C {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@attr f : String;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@rel isFor -> C * <- D.has;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@id i (f);");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("@entity D {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@attr f : String @length (30);");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@rel has -> C * <- C.isFor;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@id i (f);");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("@entity E : D {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@attr name : Date ?;");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("class TestX : Widget {");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("class InnerX : Structure  {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("public var f : String -> C.f;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("}\t\t ");
    _builder.newLine();
    final YWidget result = this.loadLibAndParse(_builder);
    this._validationTestHelper.assertError(result, LangPackage.eINSTANCE.getYProperty(), LangValidator.MISSING_ENTITY_REFERENCE, 
      "Entity has to implement entity type before pointing to attribute");
  }
  
  @Test
  public void testPropertyAttributeRef3() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("@entity C {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@attr f : String;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@rel isFor -> C * <- D.has;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@id i (f);");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("@entity D {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@attr f : String @length (30);");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@rel has -> C * <- C.isFor;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@id i (f);");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("@entity E : D {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@attr name : Date ?;");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("class TestX : Widget {");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("class InnerX : Structure -> C {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("public var f1 : String -> C.f;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("}\t\t ");
    _builder.newLine();
    final YWidget result = this.loadLibAndParse(_builder);
    this._validationTestHelper.assertError(result, LangPackage.eINSTANCE.getYProperty(), LangValidator.MISSING_ENTITY_REFERENCE, 
      "Cannot find matching attribute for selected entity type");
  }
  
  @Test
  public void testPropertyAttributeRef4() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("@entity C {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@attr f : String;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@rel isFor -> C * <- D.has;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@id i (f);");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("@entity D {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@attr f : String @length (30);");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@rel has -> C * <- C.isFor;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@id i (f);");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("@entity E : D {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@attr name : Date ?;");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("class TestX : Widget {");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("class InnerX : Structure -> C {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("public var f : Int -> C.f;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("}\t\t ");
    _builder.newLine();
    final YWidget result = this.loadLibAndParse(_builder);
    this._validationTestHelper.assertError(result, LangPackage.eINSTANCE.getYProperty(), LangValidator.WRONG_TYPE, 
      "Attribute type does not match property type");
  }
  
  @Test
  public void testPropertyAttributeRef5() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("\t");
    _builder.append("@entity C {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@attr f : String;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@rel isFor -> C * <- D.has;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@id i (f);");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("@entity D {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@attr f : String @length (30);");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@rel has -> C * <- C.has;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@id i (f);");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@entity E : D {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("@attr name : Date ?;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("class TestX : Widget {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("class InnerX : Structure -> C {");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("public var f : String -> D.f;");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}\t\t ");
    _builder.newLine();
    final YWidget result = this.loadLibAndParse(_builder);
    this._validationTestHelper.assertError(result, LangPackage.eINSTANCE.getYProperty(), LangValidator.WRONG_CROSS_REFERENCE, 
      "Attribute does not belong to the chosen entity");
  }
  
  /**
   * Testing relationships
   */
  @Test
  public void testRelationshipTest1() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package log.sample.project;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("@entity A {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@attr id : Int;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@id myid(id);");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@rel has? -> A*  ");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    final YWidget result = this.loadLibAndParse(_builder);
    this._validationTestHelper.assertError(result, LangPackage.eINSTANCE.getYAnnotRel(), LangValidator.MISSING_INVERSE_REALTIONSHIP, 
      "Inverse relationship for this relationship not yet defined.");
  }
  
  @Test
  public void testRelationshipTest2() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package log.sample.project;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("@entity A {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@attr id : Int;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@id myid(id);");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@rel has? -> B* <- B.isFor1");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.newLine();
    _builder.append("@entity B {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@attr seqno : Int;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@id myid(seqno);  ");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@rel isFor -> A  <- A.has;");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("@entityC {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@attr seqno : Int;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@id myid(seqno);  ");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@rel isFor -> A  <- A.has;  ");
    _builder.newLine();
    _builder.append("} ");
    _builder.newLine();
    final YWidget result = this.loadLibAndParse(_builder);
    this._validationTestHelper.assertError(result, LangPackage.eINSTANCE.getYAnnotRel(), LangValidator.INVERSE_RELATIONSHIP_DOES_NOT_EXIST_IN_TAGET, 
      "Inverse relationship does not exists in target entity.");
  }
  
  @Test
  public void testRelationshipTest3() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package log.sample.project;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("@entity A {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@attr id : Int;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@id myid(id);");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@rel has? -> A* <- A.has");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    final YWidget result = this.loadLibAndParse(_builder);
    this._validationTestHelper.assertError(result, LangPackage.eINSTANCE.getYAnnotRel(), LangValidator.INVERSE_RELATIONSHIP_CANNOT_BE_ITSELF, 
      "Matching Inverse relationship cannot be the same relationship.");
  }
  
  @Test
  public void testRelationshipTest4() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("@entity C {");
    _builder.newLine();
    _builder.append("@attr f : String;");
    _builder.newLine();
    _builder.append("@rel isFor -> C * <- D.has;");
    _builder.newLine();
    _builder.append("@id i(f);");
    _builder.newLine();
    _builder.append("}  ");
    _builder.newLine();
    _builder.newLine();
    _builder.append("@entity D {");
    _builder.newLine();
    _builder.append("@attr f : String;");
    _builder.newLine();
    _builder.append("@rel has -> C * <- C.isFor;");
    _builder.newLine();
    _builder.append("@rel has -> C * <- C.isFor;");
    _builder.newLine();
    _builder.append("@id i(f);");
    _builder.newLine();
    _builder.append("} ");
    _builder.newLine();
    this.assertDuplicate(_builder.toString(), LangPackage.eINSTANCE.getYAnnotRel(), "relationship", "has");
  }
  
  @Test
  public void testRelationshipTest5() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package log.sample.project;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("@entity A {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@attr id : Int;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@id myid(id);");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@rel has? -> B* <- B.isFor");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.newLine();
    _builder.append("@entity B {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@attr seqno : Int;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@id myid(seqno);  ");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@rel isFor -> A*  <- A.has;");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    final YWidget result = this.loadLibAndParse(_builder);
    this._validationTestHelper.assertError(result, LangPackage.eINSTANCE.getYAnnotRel(), LangValidator.MANY_TO_MANY_NOT_SUPPORTED, 
      "Many-to-many relationship type not supported yet.");
  }
  
  @Test
  public void testRelationshipTest6() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package log.sample.project;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("@entity A {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@attr id : Int;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@id myid(id);");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@rel has -> B <- B.isFor");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.newLine();
    _builder.append("@entity B {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@attr seqno : Int;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@id myid(seqno);  ");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@rel isFor -> A  <- A.has;");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    final YWidget result = this.loadLibAndParse(_builder);
    this._validationTestHelper.assertError(result, LangPackage.eINSTANCE.getYAnnotRel(), LangValidator.ONE_TO_ONE_MANDATORY_NOT_SUPPORTED, 
      "A fully mandatory 1-to-1 relationship is unusual and supported.");
  }
  
  @Test
  public void testRelationshipTest7() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package log.sample.project;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("@entity A {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@attr id : Int;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@id myid(id);");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@rel has -> B <- B.isFor");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.newLine();
    _builder.append("@entity B {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@attr seqno : Int;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@id myid(seqno);  ");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@rel isFor? -> A  <- A.has;");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    final YWidget result = this.loadLibAndParse(_builder);
    this._validationTestHelper.assertError(result, LangPackage.eINSTANCE.getYAnnotRel(), LangValidator.NO_DESGNATED_PARENT, 
      "Relation pair (has,isFor) does not have designated parent.");
  }
  
  @Test
  public void testRelationshipTest8() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package log.sample.project;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("@entity A {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@attr id : Int;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@id myid(id);");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@rel @parent has -> B <- B.isFor");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.newLine();
    _builder.append("@entity B {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@attr seqno : Int;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@id myid(seqno);  ");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@rel @parent isFor? -> A  <- A.has;");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    final YWidget result = this.loadLibAndParse(_builder);
    this._validationTestHelper.assertError(result, LangPackage.eINSTANCE.getYAnnotRel(), LangValidator.ONLY_ONE_DESGNATED_PARENT, 
      "Relation pair (has,isFor) can have  only one designated parent.");
  }
  
  /**
   * Validation helpers
   */
  private void assertEntityHierarchyCycle(final YWidget p, final String expectedEntityName) {
    this._validationTestHelper.assertError(p, 
      LangPackage.eINSTANCE.getYAnnotEntity(), 
      LangValidator.HIERARCHY_CYCLE, 
      (("cycle in hierarchy of entities \'" + expectedEntityName) + "\'"));
  }
  
  private void assertDuplicate(final String input, final EClass type, final String desc, final String name) {
    YWidget _loadLibAndParse = this.loadLibAndParse(input);
    final Procedure1<YWidget> _function = (YWidget it) -> {
      this._validationTestHelper.assertError(it, type, LangValidator.DUPLICATE_ELEMENT, input.lastIndexOf(name), name.length(), 
        (((("Duplicate " + desc) + " \'") + name) + "\'"));
    };
    ObjectExtensions.<YWidget>operator_doubleArrow(_loadLibAndParse, _function);
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
