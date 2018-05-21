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
import eu.jgen.notes.dmw.lite.lang.YAssignment
import eu.jgen.notes.dmw.lite.lang.YIfStatement
import eu.jgen.notes.dmw.lite.lang.YMemberSelection
import eu.jgen.notes.dmw.lite.lang.YNull
import eu.jgen.notes.dmw.lite.lang.YReturn
import eu.jgen.notes.dmw.lite.lang.YStatement
import eu.jgen.notes.dmw.lite.lang.YSymbolRef
import eu.jgen.notes.dmw.lite.lang.YSelf
import eu.jgen.notes.dmw.lite.lang.YVariableDeclaration
import eu.jgen.notes.dmw.lite.lang.YWidget
import eu.jgen.notes.dmw.lite.utility.LangUtil
import org.eclipse.xtext.testing.InjectWith
import org.eclipse.xtext.testing.XtextRunner
import org.eclipse.xtext.testing.util.ParseHelper
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

import static extension org.junit.Assert.*
import org.eclipse.xtext.testing.validation.ValidationTestHelper
import eu.jgen.notes.dmw.lite.lang.YNew

@RunWith(XtextRunner)
@InjectWith(LangInjectorProvider)
class LiteLangParsingTest1 {

	@Inject extension ParseHelper<YWidget> parseHelper
	@Inject extension LangUtil
	@Inject extension ValidationTestHelper

	@Test 
	def void loadModel() {
		val result = parseHelper.parse('''
			class C {
				
			}
		''')
		Assert.assertNotNull(result)
	}

	@Test def void testThenBlockWithoutStatements() {
		val text = '''
			class C {
				var c : C;
				func m() -> C {
					if (true) {
						
					}
					return self.c;
				}
			}
		'''
		text.parse.validate.forEach[println]
		
		text.parse => [
			val ifS = (classes.head.functions.head.
				body.statements.head as YIfStatement)
			ifS.thenBlock.statements.empty.assertTrue
		]
	}

	@Test def void testElse() {
		val text = '''
			class C {
				var c : C;
				func m() -> C  {
					if (true)
						if (false)
							self.c = null;
						else
							self.c = null;
					return self.c;
				}
			}
		'''
		text.parse.validate.forEach[println]
		
		text.parse => [
			val ifS = (classes.head.functions.head.
				body.statements.head as YIfStatement)
			ifS.elseBlock.assertNull
			// thus the else is associated to the inner if
		]
	}

	@Test def void testElseWithBlock() {
		val text = '''
			class C {
				var c : C;
				func m() -> C {
					if (true) {
						if (false)
							self.c = null;
					} else
							self.c = null;
					return self.c;
				}
			}
		'''
		text.parse.validate.forEach[println]
		text.parse => [
			val ifS = (classes.head.functions.head.
				body.statements.head as YIfStatement)
			ifS.elseBlock.assertNotNull
			// thus the else is associated to the outer if
		]
	}

	@Test def void testMemberSelectionLeftAssociativity() {
		val text = '''
			class A {
				func m() -> A { return self.m().m(); }
			}
		'''
		text.parse.validate.forEach[println]
		text.parse.classes.head.functions.head.
			body.statements.last.
			assertAssociativity("((this.m).m)")
	}

	@Test def void testAssignmentRightAssociativity() {
		val text = '''
			class A {
				func m() -> A {
					f : A = null;
					g : A = null;
					f = g = null;
				}
			}
		'''
		text.parse.validate.forEach[println]
		text.parse.classes.head.functions.head.
			body.statements.last.
			assertAssociativity("(f = (g = null))")
	}

	@Test def void testParameterAndVariable() {
		val text = '''
			class A {
				func m(A p) -> A {
					v : A = null;
					return null;
				}
			}
		'''
		text.parse.validate.forEach[println]
		text.parse.classes.head.functions.head => [
			params.head.assertNotNull ;
			(body.statements.head instanceof YVariableDeclaration).assertTrue
		]
	}

	def private assertAssociativity(YStatement s, CharSequence expected) {
		expected.toString.assertEquals(s.stringRepr)
	}

	def private String stringRepr(YStatement s) {
		switch (s) {
			YAssignment: '''(«s.left.stringRepr» = «s.right.stringRepr»)'''
			YMemberSelection: '''(«s.receiver.stringRepr».«s.member.name»)'''
			YSelf: "this"
  			YNew: '''new «s.type.name»()'''
			YNull: "null"
			YSymbolRef: s.symbol.name
			YReturn: s.expression.stringRepr
		}
	}

}
