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
class LiteLangValidatorTest2 {

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

	@Test def void testDuplicateRelationship() {
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

	@Test def void testPropertyAttributeRef1() {
		'''
			@entity C {
				@attr f : String;
				@rel isFor -> D * <- D.has;
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
	
		@Test def void testPropertyAttributeRef6() {
		val result ='''
			@entity C {
				@attr f : String;
				@rel isFor -> D * <- D.has;
				@id i (f);
			}
			
			@entity D {
				@attr f : String @length (30);
				@rel has -> C * <- D.has;
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
		'''.loadLibAndParse
		
//		result.assertError(LangPackage.eINSTANCE.YAnnotRel, LangValidator.WRONG_INVERT_REFERENCE,
//			"Pointing to wrong invert relationship 'has'")
		 
	}
	
		@Test def void testMoveFunction() {
		val result ='''
			@entity C {
				@attr f : String;
				@id i (f);
			}
			
			class TestX : Widget {
				
				class InnerX : Structure -> C {
					public var f : String -> C.f;
				}
				
				var imp : InnerX;
				var exp : InnerX;
				
				func entry() {
					super.moveView(self.imp,self.exp);	
				}
				
			}		 
		'''.loadLibAndParse
		
		 result.validate.forEach[println(it)]
		 
	}
	
	/*******************************************************
	 * 
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
