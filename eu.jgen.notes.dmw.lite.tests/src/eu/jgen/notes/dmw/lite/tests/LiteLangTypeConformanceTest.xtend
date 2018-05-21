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
import eu.jgen.notes.dmw.lite.lang.YWidget
import eu.jgen.notes.dmw.lite.typing.LangTypeConformance
import eu.jgen.notes.dmw.lite.utility.LangLib
import org.eclipse.emf.ecore.resource.ResourceSet
import org.eclipse.xtext.naming.IQualifiedNameProvider
import org.eclipse.xtext.testing.InjectWith
import org.eclipse.xtext.testing.XtextRunner
import org.eclipse.xtext.testing.util.ParseHelper
import org.junit.Test
import org.junit.runner.RunWith
import static org.junit.Assert.*;



import static eu.jgen.notes.dmw.lite.typing.LangTypeComputer.*

@RunWith(XtextRunner)
@InjectWith(LangInjectorProvider)
class LiteLangTypeConformanceTest {
	@Inject extension ParseHelper<YWidget>
	@Inject extension LangTypeConformance
	@Inject extension LangLib
	@Inject extension IQualifiedNameProvider
	@Inject Provider<ResourceSet> rsp

	@Test def void testClassConformance() {
		'''
			class A {}
			class B : A {}
			class C {}
			class D : B {}
		'''.parse.classes => [
			// A subclass of A
			assertTrue(get(0).isConformant(get(0)))
			// B subclass of A
			assertTrue(get(1).isConformant(get(0)))
			// C not subclass of A
			assertTrue(get(2).isConformant(get(0)))
			// D subclass of A
			assertTrue(get(3).isConformant(get(0)))
			// null's type is conformant to any type
			assertTrue(NULL_TYPE.isConformant(get(0)))
		]
	}

	@Test def void testStringConformance() {
		assertTrue(STRING_TYPE.isConformant(LangLib.LIB_STRING.libClass))
	}

	@Test def void testIntConformance() {
		assertTrue(INT_TYPE.isConformant(LangLib.LIB_INTEGER.libClass))
	}

	@Test def void testBoolConformance() {
		assertTrue(BOOLEAN_TYPE.isConformant(LangLib.LIB_BOOLEAN.libClass))
	}

	def private libClass(String fqn) {
		val rs = rsp.get
		loadLib(rs)
		(rs.resources.head.contents.head as YWidget).classes.
			findFirst[fullyQualifiedName.toString == fqn]
	}

}
