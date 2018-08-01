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
 * 
 */
package eu.jgen.notes.dmw.lite.tests

import com.google.inject.Inject
import com.google.inject.Provider
import eu.jgen.notes.dmw.lite.lang.LangPackage
import eu.jgen.notes.dmw.lite.lang.YWidget
import eu.jgen.notes.dmw.lite.utility.LangLib
import eu.jgen.notes.dmw.lite.validation.LangValidator
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.resource.ResourceSet
import org.eclipse.xtext.testing.InjectWith
import org.eclipse.xtext.testing.XtextRunner
import org.eclipse.xtext.testing.util.ParseHelper
import org.eclipse.xtext.testing.validation.ValidationTestHelper
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(XtextRunner)
@InjectWith(LangInjectorProvider)
class LiteLangValidatorDMTest {

	@Inject extension ParseHelper<YWidget>
	@Inject extension ValidationTestHelper
	@Inject extension LangLib
	@Inject Provider<ResourceSet> resourceSetProvider;

	@Test def void testEntityHierarchyCycle() {
		'''
			@entity A : C {}
			@entity C : B {}
			@entity B : A {}
		'''.parse => [
			assertEntityHierarchyCycle("A")
			assertEntityHierarchyCycle("B")
			assertEntityHierarchyCycle("C")
		]
	}

	@Test def void testNoHierarchyCycle() {
		'''
			@entity A : C {}
			@entity C : B {}
			@entity B {}
		'''.parse.assertNoErrors
	}

	@Test def void testDuplicateEntities() {
		'''
			@entity C {}
			@entity C {}
		'''.toString.assertDuplicate(LangPackage.eINSTANCE.YAnnotEntity, "entity", "C")
	}

	@Test def void testDuplicateEntityAcrossFiles() {
		val first = '''
		package my.first.pack;
		
		@entity C {}'''.parse

		'''
			package my.first.pack;
			@entity D {}
			@entity C {}
		'''.parse(first.eResource.resourceSet).assertError(
			LangPackage.eINSTANCE.YAnnotEntity,
			LangValidator.DUPLICATE_ENTITY,
			"The entity C is already defined"
		)

		first.assertError(
			LangPackage.eINSTANCE.YAnnotEntity,
			LangValidator.DUPLICATE_ENTITY,
			"The entity C is already defined"
		)
	}

	@Test def void testDuplicateAttribute() {
		'''
			@entity C {
				@attr f : String;
				@attr f : String;
			}
		'''.toString.assertDuplicate(LangPackage.eINSTANCE.YAnnotAttr, "attribute", "f")
	}

	@Test def void testDuplicateIdentifier() {
		'''
			@entity C {
				@attr f : String;
				@id myid(f);
				@id myid(f);
			}
		'''.toString.assertDuplicate(LangPackage.eINSTANCE.YAnnotId, "identifier", "myid")
	}

	@Test def void testPropertyAttributeRef1() {
		'''
			@entity C {
				@attr f : String;
				@rel isFor -> D * <- D.has;
				@id i (f);
			}
			
			@entity D {
				@attr f : String @length (30);
				@rel has -> C  <- C.isFor;
				@id i (f);
			}
			
			@entity E : D {
				@attr name : Date ?;
			}
			
			class TestX : Widget {
				
				class InnerX : Structure -> D {
					public var f : String -> D.f;
				}
				
			}		 
		'''.loadLibAndParse.assertNoErrors
	}

	@Test def void testPropertyAttributeRef2() {
		val result = '''
			@entity C {
				@attr f : String;
				@rel isFor -> C * <- D.has;
				@id i (f);
			}
			
			@entity D {
				@attr f : String @length (30);
				@rel has -> C * <- C.isFor;
				@id i (f);
			}
			
			@entity E : D {
				@attr name : Date ?;
			}
			
			class TestX : Widget {
				
				class InnerX : Structure  {
					public var f : String -> C.f;
				}
				
			}		 
		'''.loadLibAndParse

		result.assertError(LangPackage.eINSTANCE.YProperty, LangValidator.MISSING_ENTITY_REFERENCE,
			"Entity has to implement entity type before pointing to attribute")
	}

	@Test def void testPropertyAttributeRef3() {
		val result = '''
			@entity C {
				@attr f : String;
				@rel isFor -> C * <- D.has;
				@id i (f);
			}
			
			@entity D {
				@attr f : String @length (30);
				@rel has -> C * <- C.isFor;
				@id i (f);
			}
			
			@entity E : D {
				@attr name : Date ?;
			}
			
			class TestX : Widget {
				
				class InnerX : Structure -> C {
					public var f1 : String -> C.f;
				}
				
			}		 
		'''.loadLibAndParse

		result.assertError(LangPackage.eINSTANCE.YProperty, LangValidator.MISSING_ENTITY_REFERENCE,
			"Cannot find matching attribute for selected entity type")
	}

	@Test def void testPropertyAttributeRef4() {
		val result = '''
			@entity C {
				@attr f : String;
				@rel isFor -> C * <- D.has;
				@id i (f);
			}
			
			@entity D {
				@attr f : String @length (30);
				@rel has -> C * <- C.isFor;
				@id i (f);
			}
			
			@entity E : D {
				@attr name : Date ?;
			}
			
			class TestX : Widget {
				
				class InnerX : Structure -> C {
					public var f : Int -> C.f;
				}
				
			}		 
		'''.loadLibAndParse

		result.assertError(LangPackage.eINSTANCE.YProperty, LangValidator.WRONG_TYPE,
			"Attribute type does not match property type")
	}

	@Test def void testPropertyAttributeRef5() {
		val result = '''
				@entity C {
				@attr f : String;
				@rel isFor -> C * <- D.has;
				@id i (f);
				}
				
			@entity D {
				@attr f : String @length (30);
				@rel has -> C * <- C.has;
				@id i (f);
			}
				
				@entity E : D {
					@attr name : Date ?;
				}
				
				class TestX : Widget {
					
					class InnerX : Structure -> C {
						public var f : String -> D.f;
					}
					
				}		 
		'''.loadLibAndParse

		result.assertError(LangPackage.eINSTANCE.YProperty, LangValidator.WRONG_CROSS_REFERENCE,
			"Attribute does not belong to the chosen entity")
	}

	/*
	 *  Testing relationships
	 */
	@Test
	def void testRelationshipTest1() {
		val result = '''
			package log.sample.project;
			
			@entity A {
				@attr id : Int;
				@id myid(id);
				@rel has? -> A*  
			}
		'''.loadLibAndParse

		result.assertError(LangPackage.eINSTANCE.YAnnotRel, LangValidator.MISSING_INVERSE_REALTIONSHIP,
			"Inverse relationship for this relationship not yet defined.")
	}

	@Test
	def void testRelationshipTest2() {
		val result = '''
			package log.sample.project;
			
			@entity A {
				@attr id : Int;
				@id myid(id);
				@rel has? -> B* <- B.isFor1
			}
			
			
			@entity B {
				@attr seqno : Int;
				@id myid(seqno);  
				@rel isFor -> A  <- A.has;
			}
			
			@entityC {
				@attr seqno : Int;
				@id myid(seqno);  
				@rel isFor -> A  <- A.has;  
			} 
		'''.loadLibAndParse

		result.assertError(LangPackage.eINSTANCE.YAnnotRel, LangValidator.INVERSE_RELATIONSHIP_DOES_NOT_EXIST_IN_TAGET,
			"Inverse relationship does not exists in target entity.")
	}

	@Test
	def void testRelationshipTest3() {
		val result = '''
			package log.sample.project;
			
			@entity A {
				@attr id : Int;
				@id myid(id);
				@rel has? -> A* <- A.has
			}
		'''.loadLibAndParse

		result.assertError(LangPackage.eINSTANCE.YAnnotRel, LangValidator.INVERSE_RELATIONSHIP_CANNOT_BE_ITSELF,
			"Matching Inverse relationship cannot be the same relationship.")
	}

	@Test def void testRelationshipTest4() {
		'''
			@entity C {
			@attr f : String;
			@rel isFor -> C * <- D.has;
			@id i(f);
			}  
			
			@entity D {
			@attr f : String;
			@rel has -> C * <- C.isFor;
			@rel has -> C * <- C.isFor;
			@id i(f);
			} 
		'''.toString.assertDuplicate(LangPackage.eINSTANCE.YAnnotRel, "relationship", "has")
	}
	
		@Test
	def void testRelationshipTest5() {
		val result = '''
			package log.sample.project;
			
			@entity A {
				@attr id : Int;
				@id myid(id);
				@rel has? -> B* <- B.isFor
			}
			
			
			@entity B {
				@attr seqno : Int;
				@id myid(seqno);  
				@rel isFor -> A*  <- A.has;
			}
		'''.loadLibAndParse

		result.assertError(LangPackage.eINSTANCE.YAnnotRel, LangValidator.MANY_TO_MANY_NOT_SUPPORTED,
			"Many-to-many relationship type not supported yet.")
	}
	
		@Test
	def void testRelationshipTest6() {
		val result = '''
			package log.sample.project;
			
			@entity A {
				@attr id : Int;
				@id myid(id);
				@rel has -> B <- B.isFor
			}
			
			
			@entity B {
				@attr seqno : Int;
				@id myid(seqno);  
				@rel isFor -> A  <- A.has;
			}

		'''.loadLibAndParse

		result.assertError(LangPackage.eINSTANCE.YAnnotRel, LangValidator.ONE_TO_ONE_MANDATORY_NOT_SUPPORTED,
			"A fully mandatory 1-to-1 relationship is unusual and supported.")
	}
	
	@Test
	def void testRelationshipTest7() {
		val result = '''
			package log.sample.project;
			
			@entity A {
				@attr id : Int;
				@id myid(id);
				@rel has -> B <- B.isFor
			}
			
			
			@entity B {
				@attr seqno : Int;
				@id myid(seqno);  
				@rel isFor? -> A  <- A.has;
			}

		'''.loadLibAndParse

		result.assertError(LangPackage.eINSTANCE.YAnnotRel, LangValidator.NO_DESGNATED_PARENT,
			"Relation pair (has,isFor) does not have designated parent.")
	}
	
	@Test
	def void testRelationshipTest8() {
		val result = '''
			package log.sample.project;
			
			@entity A {
				@attr id : Int;
				@id myid(id);
				@rel @parent has -> B <- B.isFor
			}
			
			
			@entity B {
				@attr seqno : Int;
				@id myid(seqno);  
				@rel @parent isFor? -> A  <- A.has;
			}

		'''.loadLibAndParse

		result.assertError(LangPackage.eINSTANCE.YAnnotRel, LangValidator.ONLY_ONE_DESGNATED_PARENT,
			"Relation pair (has,isFor) can have  only one designated parent.")
	}

	/*******************************************************
	 *  Validation helpers
	 *******************************************************/
	def private void assertEntityHierarchyCycle(YWidget p, String expectedEntityName) {
		p.assertError(
			LangPackage.eINSTANCE.YAnnotEntity,
			LangValidator.HIERARCHY_CYCLE,
			"cycle in hierarchy of entities '" + expectedEntityName + "'"
		)
	}

	def private void assertDuplicate(String input, EClass type, String desc, String name) {
		input.loadLibAndParse => [
			assertError(type, LangValidator.DUPLICATE_ELEMENT, input.lastIndexOf(name), name.length,
				"Duplicate " + desc + " '" + name + "'")
		]
	}

	def private loadLibAndParse(CharSequence p) {
		val resourceSet = resourceSetProvider.get
		loadLib(resourceSet)
		p.parse(resourceSet)
	}
}
