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
import eu.jgen.notes.dmw.lite.utility.LangUtil
import eu.jgen.notes.dmw.lite.validation.LangAccessibility
import org.eclipse.xtext.testing.InjectWith
import org.eclipse.xtext.testing.XtextRunner
import org.eclipse.xtext.testing.util.ParseHelper
import org.junit.Test
import org.junit.runner.RunWith

import static extension org.junit.Assert.*
import eu.jgen.notes.dmw.lite.lang.YStatement
import eu.jgen.notes.dmw.lite.lang.YMemberSelection

@RunWith(XtextRunner)
@InjectWith(LangInjectorProvider)
class LiteLangAccessibilityTest {
	@Inject extension ParseHelper<YWidget>
	@Inject extension LangAccessibility
	@Inject extension LangUtil

	@Test def void testPropertyAccessibility() {
		'''
			class A {
				private var  priv : D;
				protected var  prot : D;
				public var pub : D;
				func D m() -> D {
					self.priv; // private field
					self.prot; // protected field
					self.pub; // public field
					return null;
				}
			}
			
			class B : A {
				func m() -> D {
					self.priv; // private field
					self.prot; // protected field
					self.pub; // public field
					return null;
				}
			}
			
			class C {
				func m() -> D {
					(new A()).priv; // private field
					(new A()).prot; // protected field
					(new A()).pub; // public field
					return null;
				}
			}
			
			class D {}
		'''.parse.classes => [
			// method in A
			get(0).functions.get(0).body.statements => [
				get(0).assertMemberAccessible(true)
				get(1).assertMemberAccessible(true)
				get(2).assertMemberAccessible(true)
			]
			// method in B
			get(1).functions.get(0).body.statements => [
				get(0).assertMemberAccessible(false)
				get(1).assertMemberAccessible(true)
				get(2).assertMemberAccessible(true)
			]
			// method in C
			get(2).functions.get(0).body.statements => [
				get(0).assertMemberAccessible(false)
				get(1).assertMemberAccessible(false)
				get(2).assertMemberAccessible(true)
			]
		]
	}

	@Test def void testFunctionAccessibility() {
		'''
			class A {
				privatefunc   priv() -> D{ return null; }
				protected func   prot() -> D { return null; }
				public func  D pub() -> D { return null; }
				func m() -> D {
					self.priv(); // private method
					self.prot(); // protected method
					self.pub(); // public method
					return null;
				}
			} 
			
			class B : A {
				func  m() -> D {
					self.priv(); // private method
					self.prot(); // protected method
					self.pub(); // public method
					return null;
				}
			}
			
			class C {
				func m() -> D {
					(new A()).priv(); // private method
					(new A()).prot(); // protected method
					(new A()).pub(); // public method
					return null;
				}
			}
			
			class D {}
		'''.parse.classes => [
			// method in A
			get(0).functions.last.body.statements => [
				get(0).assertMemberAccessible(true)
				get(1).assertMemberAccessible(true)
				get(2).assertMemberAccessible(true)
			]
			// method in B
			get(1).functions.last.body.statements => [
				get(0).assertMemberAccessible(false)
				get(1).assertMemberAccessible(true)
				get(2).assertMemberAccessible(true)
			]
			// method in C
			get(2).functions.last.body.statements => [
				get(0).assertMemberAccessible(false)
				get(1).assertMemberAccessible(false)
				get(2).assertMemberAccessible(true)
			]
		]
	}
	
	def private assertMemberAccessible(YStatement s, boolean expected) {
		val sel = s as YMemberSelection
		expected.assertEquals(
			sel.member.isAccessibleFrom(sel))
	}
}
