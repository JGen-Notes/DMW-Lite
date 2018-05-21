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
import eu.jgen.notes.dmw.lite.lang.YExpression
import eu.jgen.notes.dmw.lite.lang.YIfStatement
import eu.jgen.notes.dmw.lite.lang.YMemberSelection
import eu.jgen.notes.dmw.lite.lang.YReturn
import eu.jgen.notes.dmw.lite.lang.YStatement
import eu.jgen.notes.dmw.lite.lang.YVariableDeclaration
import eu.jgen.notes.dmw.lite.lang.YWidget
import eu.jgen.notes.dmw.lite.typing.LangTypeComputer
import eu.jgen.notes.dmw.lite.utility.LangUtil
import org.eclipse.xtext.testing.InjectWith
import org.eclipse.xtext.testing.XtextRunner
import org.eclipse.xtext.testing.util.ParseHelper
import org.junit.Test
import org.junit.runner.RunWith

import static extension org.junit.Assert.*

@RunWith(XtextRunner)
@InjectWith(LangInjectorProvider)
class LiteLangTypeComputerTest {
	@Inject extension ParseHelper<YWidget>
	@Inject extension LangTypeComputer
	@Inject extension LangUtil

	@Test def void thisType() {
		"this".assertType("C")
	}

	@Test def void paramRefType() {
		"p".assertType("P")
	}

	@Test def void varRefType() {
		"v".assertType("V")
	}

	@Test def void newType() {
		"new N()".assertType("N")
	}

	@Test def void fieldSelectionType() {
		"this.f".assertType("F")
	}

	@Test def void methodInvocationType() {
		"this.m(new P())".assertType("R")
	}

	@Test def void assignmentType() {
		"v = new P()".assertType("V")
	}

	@Test def void stringConstantType() {
		'"foo"'.assertType("stringType")
	}

	@Test def void intConstantType() {
		'10'.assertType("intType")
	}

	@Test def void boolConstantType() {
		'true'.assertType("booleanType")
	}

	@Test def void nullType() {
		'null'.assertType("nullType")
	}

	@Test def void testTypeForUnresolvedReferences() {
		'''
			class C {
				U m() {
					f ; // unresolved symbol
					this.n(); // unresolved method 
					this.f; // unresolved field
					return null;
				}
			}
		'''.parse => [
			classes.head.functions.head.body.statements => [
				get(0).statementExpressionType.assertNull
				get(1).statementExpressionType.assertNull
				get(2).statementExpressionType.assertNull
			]
		]
	}

	@Test def void testIsPrimitiveType() {
		'''
			class C {
				C m() {
					return true;
				}
			}
		'''.parse.classes.head => [
			it.isPrimitive.assertFalse
			functions.head.returnStatement.expression.typeFor.isPrimitive.assertTrue
		]
	}

	@Test def void testVarDeclExpectedType() {
		('''V v = null;'''.testStatements.head as YVariableDeclaration).
			expression.assertExpectedType("V")
	}

	@Test def void testAssignmentRightExpectedType() {
		('''this.f = null;'''.testStatements.head as YAssignment).
			right.assertExpectedType("F")
	}

	@Test def void testAssignmentLeftExpectedType() {
		('''this.f = null;'''.testStatements.head as YAssignment).
			left.expectedType.assertNull
	}

	@Test def void testReturnExpectedType() {
		("".testStatements.last as YReturn).
			expression.assertExpectedType("R")
	}

	@Test def void testIfExpressionExpectedType() {
		("if (e) {}".testStatements.head as YIfStatement).
			expression.assertExpectedType("booleanType")
	}

	@Test def void testMethodInvocationArgsExpectedType() {
		("this.m(new P1(), new P2());".testStatements.head as YMemberSelection).
			args => [
				get(0).assertExpectedType("P1")
				get(1).assertExpectedType("P2")
			]
	}

	@Test def void testMethodInvocationReceiverExpectedType() {
		("this.m();".testStatements.head as YMemberSelection).
			receiver.expectedType.assertNull
	}

	@Test def void testStandaloneMemberSelectionExpectedType() {
		// a standalone method invocation has no expected type
		'''
			class A {
				A a;
				A m() { this.a; this.m(); return null; }
			}
		'''.parse => [
			classes.head.functions.head.body.statements => [
				(get(0) as YExpression).expectedType.assertNull;
				(get(1) as YExpression).expectedType.assertNull
			]
		]
	}

	@Test def void testWrongMethodInvocationArgsExpectedType() {
		// method n does not exist
		("this.n(new P1(), new P2());".testStatements.head as YMemberSelection).
			args => [
				get(0).expectedType.assertNull
				get(1).expectedType.assertNull
			]
		
		// too many arguments
		("this.m(new P1(), new P2(), new P1());".testStatements.head as YMemberSelection).
			args.get(2).expectedType.assertNull
	}

	def private assertType(CharSequence testExp, String expectedClassName) {
		'''
			class R { }
			class P { }
			class V { }
			class N { }
			class F { }
			
			class C {
			  F f;
			  
			  R m(P p) {
			    V v = null;
			    «testExp»;
			    return null;
			  }
			}
		'''.parse => [
			expectedClassName.assertEquals(
				classes.last.functions.last.body.statements.get(1).statementExpressionType.name
			)
		]
	}

	def private statementExpressionType(YStatement s) {
		(s as YExpression).typeFor
	}

	def private testStatements(CharSequence statement) {
		'''
			class R {  }
			class P1 {  }
			class P2 {  }
			class V {  }
			class F {  }
			
			class C {
				F f;
				R m(P1 p1, P2 p2) {
					«statement»
					return null;
				}
			}
		'''.parse.classes.last.functions.last.body.statements
	}

	def private assertExpectedType(YExpression exp, String expectedClassName) {
		expectedClassName.assertEquals(exp.expectedType.name)
	}


}
