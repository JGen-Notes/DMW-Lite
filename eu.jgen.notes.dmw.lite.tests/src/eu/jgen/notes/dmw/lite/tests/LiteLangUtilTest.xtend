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
import eu.jgen.notes.dmw.lite.lang.YClass
import eu.jgen.notes.dmw.lite.lang.YMemberSelection
import eu.jgen.notes.dmw.lite.lang.YWidget
import eu.jgen.notes.dmw.lite.utility.LangLib
import eu.jgen.notes.dmw.lite.utility.LangUtil
import org.eclipse.xtext.testing.InjectWith
import org.eclipse.xtext.testing.XtextRunner
import org.eclipse.xtext.testing.util.ParseHelper
import org.junit.Test
import org.junit.runner.RunWith

import static extension org.junit.Assert.*

@RunWith(XtextRunner)
@InjectWith(LangInjectorProvider)
class LiteLangUtilTest {

	@Inject extension ParseHelper<YWidget>
	@Inject extension LangUtil
	@Inject extension LangLib

	@Test
	def void testModelUtilMethodsByType() {
		'''
			class C {
				C f;
				C m() {
					if (true) {
						
					}
					return this.c;
				}
			}
		'''.parse.classes.head => [
			"f".assertEquals(properties.head.name)
			"m".assertEquals(functions.head.name)
			(functions.head.returnStatement.expression instanceof YMemberSelection).assertTrue
		]
	}

	@Test def void testValidHierarchy() {
		'''
		class C {}
		
		class D extends C {}
		
		class E extends D {}'''.parse.classes => [
			get(0).assertHierarchy("")
			get(1).assertHierarchy("C")
			get(2).assertHierarchy("D, C")
		]
	}

	@Test def void testCyclicHierarchy() {
		'''
		class C extends E {}
		
		class D extends C {}
		
		class E extends D {}'''.parse.classes => [
			get(0).assertHierarchy("E, D, C")
			get(1).assertHierarchy("C, E, D")
			get(2).assertHierarchy("D, C, E")
		]
	}

	@Test def void testHierarchyMethods() {
		'''
			class C1 {
				C1 m() { return null; }
				C1 n() { return null; }
			}
			class C2 extends C1 {
				C1 m() { return null; } // this must override the one in C1
			}
			class C3 extends C2 {
			}
		'''.parse.classes.last => [
			"m -> C2, n -> C1".assertEquals(
				classHierarchyMethods.entrySet.
				map[key.toString + " -> " + (value.eContainer as YClass).name].join(", ")
			)
		]
	}

	@Test def void testHierarchyMembers() {
		'''
			class C1 {
				C1 m;
				C1 m() { return null; }
				C1 n() { return null; }
				C1 n;
			}
			class C2 extends C1 {
				C1 f;
				C1 m() { return null; } // this must come before the one in C1
			}
			class C3 extends C2 {
			}
		'''.parse.classes.last => [
			"YField f in C2, YMethod m in C2, YField m in C1, YMethod m in C1, YMethod n in C1, YField n in C1".assertEquals(
				classHierarchyMembers.
				map[
					eClass.name + " " + name + " in " + (eContainer as YClass).name
				].join(", ")
			)
		]
	}

	@Test def void testMemberAsStringWithType() {
		'''
			class A {}
			class B {}
			class C {
				A f;
				A m() { return null; }
				A n(B b, C c) { return null; }
				A p(Foo b, C c) { return null; }
			}
		'''.parse.classes.last => [
			functions => [
				"m() : A".assertEquals(get(0).memberAsStringWithType)
				"n(B, C) : A".assertEquals(get(1).memberAsStringWithType)
				"p(null, C) : A".assertEquals(get(2).memberAsStringWithType)
			]
			"f : A".assertEquals(properties.head.memberAsStringWithType)
		]
	}

	@Test def void testHierarchyMethodsWithLibraryObject() {
		val p = '''
			class C1 {
				C1 m() { return null; }
				// the following overrides the one from Object
				String toString() { return null; }
			}
			
			class C2 extends C1 {
				
			}
		'''.parse
		loadLib(p.eResource.resourceSet)
		p.classes.last => [
			"clone -> Object, toString -> C1, equals -> Object, m -> C1".assertEquals(
				classHierarchyMethods.entrySet.
				map[key.toString + " -> " + (value.eContainer as YClass).name].join(", ")
			)
		]
	}

	def private assertHierarchy(YClass c, CharSequence expected) {
		expected.toString.assertEquals(c.classHierarchy.map[name].join(", "))
	}
}
