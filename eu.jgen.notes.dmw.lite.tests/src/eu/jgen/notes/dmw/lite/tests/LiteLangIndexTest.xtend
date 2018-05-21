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
import eu.jgen.notes.dmw.lite.lang.YWidget
import eu.jgen.notes.dmw.lite.scoping.LangIndex
import org.eclipse.emf.ecore.EObject
import org.eclipse.xtext.testing.InjectWith
import org.eclipse.xtext.testing.XtextRunner
import org.eclipse.xtext.testing.util.ParseHelper
import org.junit.Test
import org.junit.runner.RunWith

import static extension org.junit.Assert.*

@RunWith(XtextRunner)
@InjectWith(LangInjectorProvider)
class LiteLangIndexTest {
	@Inject extension ParseHelper<YWidget>
	@Inject extension LangIndex

	@Test def void testExportedEObjectDescriptions() {
		'''
			class C {
			  var f : A;
			  func m(p : A) -> A{
			    v : A = null;
			    return null;
			  }
			}
			class A {}
		'''.parse.assertExportedEObjectDescriptions("C, C.f, C.m, C.m.p, A")
		// before SmallJavaResourceDescriptionsStrategy the output was
		// "C, C.f, C.m, C.m.p, C.m.v, A"
	}

	@Test def void testExportedEObjectDescriptionsWithPackage() {
		'''
			package my.pack;
			class C {}
		'''.parse.assertExportedEObjectDescriptions("my.pack, my.pack.C")
	}

	@Test def void testClassesInIndex() {
		val first = '''
		package my.first.pack;
		class C { }'''.parse

		'''
			package my.second.pack;
			class D { }
		'''.parse(first.eResource.resourceSet).
		classes.head.assertClassesInIndex("my.first.pack.C, my.second.pack.D")
	}

	@Test def void testVisibleExternalClassesInIndex() {
		val first = '''
			package my.first.pack;
			class C { }
		'''.parse

		val second = '''
			package my.second.pack;
			class D { }
		'''.parse(first.eResource.resourceSet)

		first.classes.head.
			assertExternalClassesInIndex("my.second.pack.D")
		second.classes.head.
			assertExternalClassesInIndex("my.first.pack.C")
		// also check that the original collections are not changed
		// after the difference
		second.classes.head.assertClassesInIndex("my.first.pack.C, my.second.pack.D")
	}

	@Test def void testVisibleExternalClassesContainDuplicatesFromExternalFiles() {
		val first = '''
			package my.first.pack;
			class C { }
		'''.parse

		val second = '''
			package my.first.pack;
			class C { }
			class D { }
		'''.parse(first.eResource.resourceSet)

		first.classes.head.
			assertExternalClassesInIndex("my.first.pack.C, my.first.pack.D")
		second.classes.head.
			assertExternalClassesInIndex("my.first.pack.C")
	}

	@Test def void testVisibleExternalClassesAppearOnlyOnce() {
		val first = '''
			package my.first.pack;
			class C { }
			class C { }
		'''.parse

		val second = '''
			package my.second.pack;
			class D { }
			class D { }
		'''.parse(first.eResource.resourceSet)

		first.classes.head.
			assertExternalClassesInIndex("my.second.pack.D")
		second.classes.head.
			assertExternalClassesInIndex("my.first.pack.C")
	}

	def private assertExportedEObjectDescriptions(EObject o, CharSequence expected) {
		expected.toString.assertEquals(
			o.getExportedEObjectDescriptions.map[qualifiedName].join(", ")
		)
	}

	def private assertClassesInIndex(EObject o, String expected) {
		val visibleClassesDescriptions = o.getVisibleClassesDescriptions
		expected.assertEquals(visibleClassesDescriptions.
			map[it.qualifiedName].join(", "))
	}

	def private assertExternalClassesInIndex(EObject o, String expected) {
		val visibleExternalClassesDescriptions = o.getVisibleExternalClassesDescriptions
		expected.assertEquals(visibleExternalClassesDescriptions.values.
			map[it.qualifiedName].join(", "))
	}

}
