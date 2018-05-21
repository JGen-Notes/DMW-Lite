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
import eu.jgen.notes.dmw.lite.lang.LangPackage
import eu.jgen.notes.dmw.lite.lang.YMemberSelection
import eu.jgen.notes.dmw.lite.lang.YFunction
import eu.jgen.notes.dmw.lite.lang.YSymbolRef
import eu.jgen.notes.dmw.lite.lang.YVariableDeclaration
import eu.jgen.notes.dmw.lite.lang.YWidget
import eu.jgen.notes.dmw.lite.utility.LangUtil
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.EReference
import org.eclipse.xtext.scoping.IScopeProvider
import org.eclipse.xtext.testing.InjectWith
import org.eclipse.xtext.testing.XtextRunner
import org.eclipse.xtext.testing.util.ParseHelper
import org.junit.Test
import org.junit.runner.RunWith

import static extension org.junit.Assert.*

@RunWith(XtextRunner)
@InjectWith(LangInjectorProvider)
class LiteLangScopeProviderTest1 {
	@Inject extension ParseHelper<YWidget>
	@Inject extension IScopeProvider
	@Inject extension LangUtil

	@Test def void testScopeProvider() {
		'''
			class C {
			  var f : A;
			  func m(p : A) -> A {
			    v : A = null;
			    return null; // return's expression is the context
			  }
			}
			class A {}
		'''.parse.classes.head.functions.last.returnStatement.expression => [
			assertScope(LangPackage.eINSTANCE.YMemberSelection_Member, "f, m, C.f, C.m")
			assertScope(LangPackage.eINSTANCE.YSymbolRef_Symbol, "v, p")
		]
	}

	@Test def void testScopeProviderForSymbols() {
		'''
			class C {
			  func m(p : A) -> A {
			    v1 : A = null;
			    if (true) {
			      v2 : A = null;
			      v3 : A = null;
			    }
			    v4 : A = null;
			    return null;
			  }
			}
			class A {}
		'''.parse.classes.head.functions.last.body.eAllContents.filter(YVariableDeclaration) => [
			findFirst[name == 'v3'].expression.assertScope(LangPackage.eINSTANCE.YSymbolRef_Symbol, "v2, v1, p")
			findFirst[name == 'v4'].expression.assertScope(LangPackage.eINSTANCE.YSymbolRef_Symbol, "v1, p")
		]
	}

	@Test def void testVariableShadowsParamScoping() {
		'''
			class C {
				func m(a : A) -> A{
					a : A = null;
					return a;
				}
			}
			class A {}
		'''.parse.classes.head.functions.head => [
			returnStatement.expression.assertScope
				(LangPackage.eINSTANCE.YSymbolRef_Symbol, 
				"a")
		]
	}

	@Test def void testVariableShadowsParamLinking() {
		'''
			class A {
				func m(a : A) -> A {
					  a : A = null;
					return a;
				}
			}
		'''.parse.classes.head.functions.head => [
			// the local variable should shadow the param
			body.statements.head.assertSame(
				(returnStatement.expression as YSymbolRef).symbol)
		]
	}

	@Test def void testFieldsScoping() {
		'''
		class C { 
			var a : C;
		}
		
		class D : C {
			var b : C;
			func m(p1 : C, p2 : D) -> C { return self.b; }
		}'''.parse.classes.last.functions.get(0).returnStatement.expression.assertScope(
			LangPackage.eINSTANCE.YMemberSelection_Member, "b, m, a")
		// before custom scoping was: "b, m, C.a, D.b, D.m"
	}

	@Test def void testMethodsScoping() {
		'''
		class C { 
			func n() -> C { return null; }
		}
		
		class D : C {
			var f : C;
			func m() -> C { return self.m(); }
		}'''.parse.classes.last.functions.get(0).returnStatement.expression.assertScope(
			LangPackage.eINSTANCE.YMemberSelection_Member, "m, f, n")
		// before custom scoping was: "m, C.n, D.m"
	}

	@Test def void testFieldScoping() {
		'''
			class A {
			  var a : D;
			  var b  : D;
			  var c : D;
			}
			
			class B : A {
			  var b : D;
			}
			
			class C : B {
			  var a : D;
			  func m() -> D { return self.a; }
			  func n() -> D { return self.b; }
			  func p() -> D { return self.c; }
			}
			class D {}
		'''.parse.classes => [
			// a in this.a must refer to C.a
			get(2).properties.get(0).assertSame(get(2).functions.get(0).returnExpSel.member)
			// b in this.b must refer to B.b
			get(1).properties.get(0).assertSame(get(2).functions.get(1).returnExpSel.member)
			// c in this.c must refer to A.c
			get(0).properties.get(2).assertSame(get(2).functions.get(2).returnExpSel.member)
		]
	}

	@Test def void testMethodScoping() {
		'''
			class A { 
				func n() -> D { return null; }
				func m() -> D { return null; }
				func o() -> D { return null; }
			}
			
			class B : A { 
				func n() -> D { return null; }
			}
			
			class C : B {
				func m() -> D { return self.m(); }
				func p() -> D { return self.n(); }
				func q() -> D { return self.o(); }
			}
			class D {}
		'''.parse.classes => [
			// m in this.m() must refer to C.m
			get(2).functions.get(0).assertSame(get(2).functions.get(0).returnExpSel.member)
			// n in this.n() must refer to B.n
			get(1).functions.get(0).assertSame(get(2).functions.get(1).returnExpSel.member)
			// o in this.o() must refer to B.n
			get(0).functions.get(2).assertSame(get(2).functions.get(2).returnExpSel.member)
		]
	}

	@Test def void testMemberScopeWithUnresolvedReceiver() {
		'''
			class C {
			  func m() -> A {
			    return foo.m(); // return's expression is the context
			  }
			}
			class A {}
		'''.parse.classes.head.functions.last.returnStatement.expression => [
			assertScope(LangPackage.eINSTANCE.YMemberSelection_Member, "")
		]
	}

	@Test def void testFieldsAndMethodsWithTheSameName() {
		'''
			class C {
			  var f : A;
			  func f() -> A {
			    return self.f(); // must refer to method f
			  }
			  func m()-> A {
			    return self.m; // must refer to field m
			  }
			  var m : A;
			}
			class A {}
		'''.parse.classes.head => [
			// must refer to method f()
			functions.head.assertSame(functions.head.returnExpSel.member)
			// must refer to field m
			properties.last.assertSame(functions.last.returnExpSel.member)
		]
	}

	@Test def void testClassesInTheSamePackageInDifferentFiles() {
		val first = '''
			package apackage;
			
			class B {}
			class C {}
		'''.parse
		val second = '''
			package apackage;
			
			class D {
			  var  c : C;
			}
		'''.parse(first.eResource.resourceSet)
		'''
			package anotherpackage;
			
			class E {}
		'''.parse(first.eResource.resourceSet)
		second.classes.head => [
			assertScope(LangPackage.eINSTANCE.YMember_Type, "D, B, C, apackage.D, apackage.B, apackage.C, anotherpackage.E")
		]
	}

	@Test def void testLocalClassHasThePrecedenceOverTheSameClassInTheSamePackageInDifferentFiles() {
		val first = '''
			package apackage;
			
			class B {}
			class C {}
		'''.parse
		val second = '''
			package apackage;
			
			class C {
			  var  c : C;
			}
		'''.parse(first.eResource.resourceSet)
		second.classes.head => [
			assertScope(LangPackage.eINSTANCE.YMember_Type, "C, B, apackage.C, apackage.B")
			assertSame(properties.head.type)
		]
	}

	def private returnExpSel(YFunction function) {
		function.returnStatement.expression as YMemberSelection
	}

	def private assertScope(EObject context, EReference reference, CharSequence expected) {
		expected.toString.assertEquals(context.getScope(reference).allElements.map[name].join(", "))
	}
}
