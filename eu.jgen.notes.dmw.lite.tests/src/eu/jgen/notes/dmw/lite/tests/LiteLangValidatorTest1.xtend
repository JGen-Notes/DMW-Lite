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

import static extension org.junit.Assert.*

@RunWith(XtextRunner)
@InjectWith(LangInjectorProvider)
class LiteLangValidatorTest1 {

	@Inject extension ParseHelper<YWidget>
	@Inject extension ValidationTestHelper
	@Inject extension LangLib
	@Inject Provider<ResourceSet> resourceSetProvider;

	@Test def void testClassHierarchyCycle() {
		'''
			class A : C {}
			class C : B {}
			class B : A {}
		'''.parse => [
			assertClassHierarchyCycle("A")
			assertClassHierarchyCycle("B")
			assertClassHierarchyCycle("C")

		]
	}
	
	/*
	 * *********
	 */
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
	
	/*
	 * *********
	 */

	@Test def void testNoHierarchyCycle() {
		'''
			class A : C {}
			class C : B {}
			class B {}
		'''.parse.assertNoErrors
	}

	@Test def void testInvocationOnField() {
		'''
			class A {
			  var f : A;
			   func m() -> A {
			    return self.f();
			  }
			}
		''' => [
			parse.assertError(
				LangPackage.eINSTANCE.YMemberSelection,
				LangValidator.FUNCTION_INVOCATION_ON_PROPERTY,
				lastIndexOf("("), 1, // check error position
				"Function invocation on a property"
			)
		]
	}

	@Test def void testFieldSelectionOnMethod() {
		'''
			class A {
			  func  m() -> A{
			    return self.m;
			  }
			}
		''' => [
			parse.assertError(
				LangPackage.eINSTANCE.YMemberSelection,
				LangValidator.PROPERTY_SELECTION_ON_FUNCTION,
				lastIndexOf("m"), 1, // check error position
				"Property selection on a function"
			)
		]
	}

	@Test def void testCorrectMemberSelection() {
		'''
			class A {
			  var f : A ;
			  func  m() -> A{
			  	 v : A = self.f;
			  	 return self.m();
			  }
			}
		'''.parse.assertNoErrors
	}

	@Test def void testUnreachableCode() {
		'''
			class C {
				func m() -> C{
					return null;
					self.m();
				}
			}
		'''.parse.assertError(
			LangPackage.eINSTANCE.YMemberSelection,
			LangValidator.UNREACHABLE_CODE,
			"Unreachable code"
		)
	}

	@Test def void testUnreachableCode2() {
		'''
			class C {
				func m() -> C{
					return null;
					i : C = null;
					self.m();
				}
			}
		'''.parse.assertError(
			LangPackage.eINSTANCE.YVariableDeclaration,
			LangValidator.UNREACHABLE_CODE,
			"Unreachable code"
		)
	}

	@Test def void testUnreachableCodeOnlyOnce() {
		'''
			class C {
				func m() -> C{
					return null;
					 i : C = null; // error only here
					return null;
					return null; // no error here
				}
			}
		'''.parse => [
			assertError(
				LangPackage.eINSTANCE.YVariableDeclaration,
				LangValidator.UNREACHABLE_CODE,
				"Unreachable code"
			)
			1.assertEquals(validate.size)
		]
	}

	@Test def void testUnreachableCodeInsideIf() {
		'''
			class C {
				func m() -> C {
					if (true) {
						return null;
						i : C  = null;
						self.m();
					}
				}
			}
		'''.parse.assertError(
			LangPackage.eINSTANCE.YVariableDeclaration,
			LangValidator.UNREACHABLE_CODE,
			"Unreachable code"
		)
	}

	@Test def void testNoUnreachableCode() {
		'''
			class C {
				func m() -> C{
					self.m();
					return null;
				}
			}
		'''.parse.assertNoErrors
	}

	@Test def void testMissingFinalReturn() {
		'''
			class C {
				func m() -> C {
					self.m();
				}
			}
		'''.parse.assertError(
			LangPackage.eINSTANCE.YFunction,
			LangValidator.FUNCTION_FINAL_RETURN,
			"Function must end with a return statement"
		)
	}

	@Test def void testDuplicateClasses() {
		'''
			class C {}
			class C {}
		'''.toString.assertDuplicate(LangPackage.eINSTANCE.YClass, "class", "C")
	}

	@Test def void testDuplicateFields() {
		'''
			class C {
				var f : C;
				var f : C;
			}
		'''.toString.assertDuplicate(LangPackage.eINSTANCE.YProperty, "property", "f")
	}

	@Test def void testDuplicateMethods() {
		'''
			class C {
				func m() -> C { return null; }
				func m() -> C { return null; }
			}
		'''.toString.assertDuplicate(LangPackage.eINSTANCE.YFunction, "function", "m")
	}

	@Test def void testDuplicateParams() {
		'''
			class C {
				func m(p : C, p : C) -> C { return null; }
			}
		'''.toString.assertDuplicate(LangPackage.eINSTANCE.YParameter, "parameter", "p")
	}

	@Test def void testDuplicateVariables() {
		'''
			class C {
				func m() -> C {
					v : C = null;
					if (true)
						v : C = null;
					return null;
				}
			}
		'''.toString.assertDuplicate(LangPackage.eINSTANCE.YVariableDeclaration, "variable", "v")
	}

	@Test def void testFieldAndMethodWithTheSameNameAreOK() {
		'''
			class C {
			  var f: C;
			  func f() -> C { return null; }
			}
		'''.parse.assertNoErrors
	}

	@Test def void testVariableDeclExpIncompatibleTypes() {
		"v : A = new C(); return null;".assertIncompatibleTypes(LangPackage.eINSTANCE.YNew, "A", "C")
	}

	@Test def void testReturnExpIncompatibleTypes() {
		"return new C();".assertIncompatibleTypes(LangPackage.eINSTANCE.YNew, "A", "C")
	}

	@Test def void testArgExpIncompatibleTypes() {
		"this.m(new C());".assertIncompatibleTypes(LangPackage.eINSTANCE.YNew, "A", "C")
	}

	@Test def void testIfExpressionIncompatibleTypes() {
		"if (new C()) { return null; } ".
			assertIncompatibleTypes(
				LangPackage.eINSTANCE.YNew,
				"booleanType", "C"
			)
	}

	@Test def void testAssignmentIncompatibleTypes() {
		"v : A = null; v = new C();".
			assertIncompatibleTypes(
				LangPackage.eINSTANCE.YNew,
				"A", "C"
			)
	}

	@Test def void testInvalidNumberOfArgs() {
		'''
			class A {}
			class B {}
			class C {
				func m(a : A, b : B) -> C { return self.m(new B()); }
			}
		'''.parse.assertError(
			LangPackage.eINSTANCE.YMemberSelection,
			LangValidator.INVALID_ARGS,
			'''Invalid number of arguments: expected 2 but was 1'''
		)
	}

	@Test def void testWrongMethodOverride() {
		'''
			class A {
				func m(a : A) -> A{ return null; }
				func n(a : A) -> B{ return null; }
			}
			
			class B : A {
				// parameters must have the same type
				func m(a : B) -> A{ return null; }
				// return type cannot be a supertype
				func n(a : A) -> A{ return null; }
			}
			
			class C : A {
				// return type can be a subtype
				func m(a : A) -> B { return null; }
			}
		'''.parse => [
			assertError(LangPackage.eINSTANCE.YFunction,
				LangValidator.WRONG_FUNCTION_OVERRIDE,
				"The function 'm' must override a superclass function"
			)
			assertError(LangPackage.eINSTANCE.YFunction,
				LangValidator.WRONG_FUNCTION_OVERRIDE,
				"The function 'n' must override a superclass function"
			)
			2.assertEquals(validate.size)
		]
	}

	@Test def void testCorrectMethodOverride() {
		'''
			class A {
				func m(a : A) -> A { return null; }
			}
			
			class B : A {
				func m(a : A) -> A { return null; }
			}
			
			class C : A {
				// return type can be a subtype
				func m(a : A) -> B { return null; }
			}
		'''.parse.assertNoErrors
	}

	@Test def void testFieldAccessibility() {
		'''
			class A {
				var private priv : A;
				var protected  prot : A;
				var public pub : A ;
				func m() -> A{
					self.priv = null; // private field
					self.prot = null; // protected field
					self.pub = null; // public field
					return null;
				}
			}
			
			class B : A {
				func m() -> A{
					self.priv = null; // private field ERROR
					self.prot = null; // protected field
					self.pub = null; // public field
					return null;
				}
			}
		'''.parse => [
			1.assertEquals(validate.size)
			assertError(
				LangPackage.eINSTANCE.YMemberSelection,
				LangValidator.MEMBER_NOT_ACCESSIBLE,
				"The private member priv is not accessible here"
			)
		]
	}

	@Test def void testFieldAccessibilityInSubclass() {
		'''
			class A {
				var private A priv;
				var protected A prot;
				public A pub;
				A m() {
					this.priv = null; // private field
					this.prot = null; // protected field
					this.pub = null; // public field
					return null;
				}
			}
			
			class C {
				A m() {
					(new A()).priv = null; // private field ERROR
					(new A()).prot = null; // protected field ERROR
					(new A()).pub = null; // public field
					return null;
				}
			}
		'''.parse => [
			2.assertEquals(validate.size)
			assertError(
				LangPackage.eINSTANCE.YMemberSelection,
				LangValidator.MEMBER_NOT_ACCESSIBLE,
				"The private member priv is not accessible here"
			)
			assertError(
				LangPackage.eINSTANCE.YMemberSelection,
				LangValidator.MEMBER_NOT_ACCESSIBLE,
				"The protected member prot is not accessible here"
			)
		]
	}

	@Test def void testMethodAccessibility() {
		'''
			class A {
				private func priv() -> A{ return null; }
				protected func prot() -> A { return null; }
				public func pub() -> A { return null; }
				func m() -> A{
					a : A = null;
					a = self.priv(); // private method
					a = self.prot(); // protected method
					a = self.pub(); // public method
					return null;
				}
			}
			
			class B : A {
				func m() -> A{
					a : A = null;
					a = self.priv(); // private method ERROR
					a = self.prot(); // protected method
					a = self.pub(); // public method
					return null;
				}
			}
		'''.parse => [
			1.assertEquals(validate.size)
			assertError(
				LangPackage.eINSTANCE.YMemberSelection,
				LangValidator.MEMBER_NOT_ACCESSIBLE,
				"The private member priv is not accessible here"
			)
		]
	}

	@Test def void testMethodAccessibilityInSubclass() {
		'''
			class A {
				private func priv() -> A { return null; }
				protected func prot() -> A { return null; }
				public func pub() -> A { return null; }
				func A m() -> A{
					a : A = null;
					a = self.priv(); // private method
					a = self.prot(); // protected method
					a = self.pub(); // public method
					return null;
				}
			}
			
			class C {
			func  m() -> A {
					a : A = null;
					a = (new A()).priv(); // private method ERROR
					a = (new A()).prot(); // protected method ERROR
					a = (new A()).pub(); // public method
					return null;
				}
			}
		'''.parse => [
			4.assertEquals(validate.size)
			assertError(
				LangPackage.eINSTANCE.YMemberSelection,
				LangValidator.MEMBER_NOT_ACCESSIBLE,
				"The private member priv is not accessible here"
			)
			assertError(
				LangPackage.eINSTANCE.YMemberSelection,
				LangValidator.MEMBER_NOT_ACCESSIBLE,
				"The protected member prot is not accessible here"
			)
		]
	}

	@Test def void testUnresolvedMethodAccessibility() {
		'''
			class A {
				func m() -> A{
					a : A = self.foo();
					return null;
				}
			}
		'''.parse => [
			// expect only the "Couldn't resolve reference..." error
			// and no error about accessibility is expected
			1.assertEquals(validate.size)
		]
	}

	@Test def void testTwoFiles() {
		val resourceSet = resourceSetProvider.get
		val first = '''class B : A {}'''.parse(resourceSet)
		val second = '''class A { var b : B; }'''.parse(resourceSet)
		first.assertNoErrors
		second.assertNoErrors

		second.classes.head.assertSame(first.classes.head.superclass)
	}

	@Test def void testTwoFilesAlternative() {
		val first = '''class B : A {}'''.parse
		val second = '''class A { var b : B; } '''.parse(first.eResource.resourceSet)
		first.assertNoErrors
		second.assertNoErrors

		second.classes.head.assertSame(first.classes.head.superclass)
	}

	@Test def void testPackagesAndClassQualifiedNames() {
		val first = '''
			package my.first.pack;
			class B : my.second.pack.A {}
		'''.parse
		val second = '''
			package my.second.pack;
			class A {
			  my.first.pack.B b;
			}
		'''.parse(first.eResource.resourceSet)
		first.assertNoErrors
		second.assertNoErrors

		second.classes.head.assertSame(first.classes.head.superclass)
	}

	@Test def void testImports() {
		val first = '''
			package my.first.pack;
			class C1 { }
			class C2 { }
		'''.parse

		'''
			package my.second.pack;
			class D1 { }
			class D2 { }
		'''.parse(first.eResource.resourceSet)

		'''
			package my.third.pack;
			import my.first.pack.C1;
			import my.second.pack.*;
			
			class E : C1 { // C1 is imported
			  my.first.pack.C2 c; // C2 not imported, but fully qualified
			  var  d1 : D1; // D1 imported by wildcard
			  var  d2 : D2; // D2 imported by wildcard
			}
		'''.parse(first.eResource.resourceSet).assertNoErrors
	}

	@Test def void testDuplicateClassesAcrossFiles() {
		val first = '''
		package my.first.pack;
		
		class C {}'''.parse
		
		'''
			package my.first.pack;
			class D {}
			class C {}
		'''.parse(first.eResource.resourceSet).assertError(
				LangPackage.eINSTANCE.YClass,
				LangValidator.DUPLICATE_CLASS,
				"The type C is already defined"
		)
		
		first.assertError(
			LangPackage.eINSTANCE.YClass,
			LangValidator.DUPLICATE_CLASS,
			"The type C is already defined"
		)
	}

	@Test def void testClassesWithSameNameButWithDifferentQNAreOK() {
		val first = '''
		package my.first.pack;
		
		class C {}'''.parse
		
		'''
			package my.second.pack;
			class C {}
		'''.parse(first.eResource.resourceSet).assertNoErrors
	}

	@Test def void testStringConformance() {
		'''
			class A {
				func m() -> String { return "foo"; }
			}
		'''.parse(loadLibInResourceSet).assertNoErrors
	}

	@Test def void testIntConformance() {
		'''
			class A {
				func m() -> Int { return 10; }
			}
		'''.parse(loadLibInResourceSet).assertNoErrors
	}

	@Test def void testBooleanConformance() {
		'''
			class A {
				func m() -> Boolean { return true; }
			}
		'''.parse(loadLibInResourceSet).assertNoErrors
	}

	@Test def void testBooleanAcceptedByIfStatement() {
		'''
			class A {
				func m() -> A {
					if (new Boolean()) {
						return null;
					}
					return null;
				}
			}
		'''.parse(loadLibInResourceSet).assertNoErrors
	}

	@Test def void testEveryClassExtendsObject() {
		'''
			class A {
				func m() -> Object{
					return new A();
				}
			}
		'''.parse(loadLibInResourceSet).assertNoErrors
	}

	@Test def void testStringConformantToObject() {
		'''
			class A {
				func m() -> Object {
					return "a";
				}
			}
		'''.parse(loadLibInResourceSet).assertNoErrors
	}

	@Test def void testAccessToExplicitlyInheritedObject() {
		'''
			class A : Object {
				Object m(Object o) {
					Object c = this.clone();
					return this.toString();
				}
			}
		'''.parse(loadLibInResourceSet).assertNoErrors
	}

	@Test def void testAccessToImplictlyInheritedObject() {
		'''
			class A {
				Object m(Object o) {
					Object c = this.clone();
					return this.toString();
				}
			}
		'''.parse(loadLibInResourceSet).assertNoErrors
	}

	@Test def void testWrongMethodOverrideWithLibrary() {
		'''
			class A {
				func toString() -> Object {
					return null;
				}
			}
		'''.parse(loadLibInResourceSet) => [
			assertError(LangPackage.eINSTANCE.YFunction,
				LangValidator.WRONG_FUNCTION_OVERRIDE,
				"The function 'toString' must override a superclass function"
			)
			1.assertEquals(validate.size)
		]
	}

	@Test def void testCorrectMethodOverrideWithLibrary() {
		'''
			class A {
				public String toString() {
					return null;
				}
			}
		'''.parse(loadLibInResourceSet).assertNoErrors
	}

	@Test def void testWrongSuperUsage() {
		'''
			class C {
				C m() {
					return super;
				}
			}
		'''.parse.assertError(LangPackage.eINSTANCE.YSuper,
			LangValidator.WRONG_SUPER_USAGE,
			"'super' can be used only as member selection receiver"
		)
	}

	@Test def void testCanAccessToSuper() {
		'''
			class A {
				func m() -> String{
					return null;
				}
			}
			
			class B : A {
				func m() -> String {
					return super.toString();
				}
			}
		'''.parse(loadLibInResourceSet).assertNoErrors
	}

	@Test def void testCanAccessToSuperWithLibrary() {
		'''
			class A {
				public func toString() -> String{
					return super.toString();
				}
			}
		'''.parse(loadLibInResourceSet).assertNoErrors
	}

	@Test def void testReducedAccessibility() {
		'''
			class A {
				public func m() -> A {
					return null;
				}
			}
			
			class B : A {
				func m() -> A{
					return null;
				}
			}
		'''.parse.assertError(LangPackage.eINSTANCE.YFunction,
			LangValidator.REDUCED_ACCESSIBILITY,
			"Cannot reduce access from public to private"
		)
	}

	@Test def void testReducedAccessibility2() {
		'''
			class A {
				protected func m() -> A{
					return null;
				}
			}
			
			class B : A {
				func m() -> A{
					return null;
				}
			}
		'''.parse.assertError(LangPackage.eINSTANCE.YFunction,
			LangValidator.REDUCED_ACCESSIBILITY,
			"Cannot reduce access from protected to private"
		)
	}

	@Test def void testReducedAccessibility3() {
		'''
			class A {
				public func m() -> A{
					return null;
				}
			}
			
			class B : A {
				protected func m() -> A{
					return null;
				}
			}
		'''.parse.assertError(LangPackage.eINSTANCE.YFunction,
			LangValidator.REDUCED_ACCESSIBILITY,
			"Cannot reduce access from public to protected"
		)
	}

	def private void assertClassHierarchyCycle(YWidget p, String expectedClassName) {
		p.assertError(
			LangPackage.eINSTANCE.YClass,
			LangValidator.HIERARCHY_CYCLE,
			"cycle in hierarchy of class '" + expectedClassName + "'"
		)
	}
	
	/*
	 * 
	 */	
	def private void assertEntityHierarchyCycle(YWidget p, String expectedEntityName) {
		p.assertError(
			LangPackage.eINSTANCE.YAnnotEntity,
			LangValidator.HIERARCHY_CYCLE,
			"cycle in hierarchy of entities '" + expectedEntityName + "'"
		)
	}

	def private void assertDuplicate(String input, EClass type, String desc, String name) {
		input.parse => [
			// check that the error is on both duplicates
			assertError(type,
				LangValidator.DUPLICATE_ELEMENT,
				input.indexOf(name), name.length,
				"Duplicate " + desc + " '" + name + "'")
			assertError(type,
				LangValidator.DUPLICATE_ELEMENT,
				input.lastIndexOf(name), name.length,
				"Duplicate " + desc + " '" + name + "'")
		]
	}

	def private void assertIncompatibleTypes(CharSequence methodBody, EClass c, String expectedType, String actualType) {
		'''
			class A {}
			class B : A {}
			class C {
			  var f : A;
			  func m(p : A) -> A {
			    «methodBody»
			  }
			}
		'''.parse.assertError(
			c,
			LangValidator.INCOMPATIBLE_TYPES,
			"Incompatible types. Expected '" + expectedType + "' but was '" + actualType + "'"
		)
	}

	def private loadLibInResourceSet() {
		resourceSetProvider.get => [loadLib]
	}
}
