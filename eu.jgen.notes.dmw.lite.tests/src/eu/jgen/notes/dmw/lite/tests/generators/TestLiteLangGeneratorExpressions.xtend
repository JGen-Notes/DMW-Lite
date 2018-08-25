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
package eu.jgen.notes.dmw.lite.tests.generators

import com.google.inject.Inject

import org.junit.Test
import org.junit.runner.RunWith

import org.eclipse.xtext.testing.XtextRunner
import org.eclipse.xtext.testing.InjectWith
import org.eclipse.xtext.testing.util.ParseHelper

import org.eclipse.xtext.testing.validation.ValidationTestHelper

import org.eclipse.xtext.xbase.testing.CompilationTestHelper
import eu.jgen.notes.dmw.lite.lang.YWidget
import eu.jgen.notes.dmw.lite.tests.LangInjectorProvider
import eu.jgen.notes.dmw.lite.utility.LangLib
import com.google.inject.Provider
import org.eclipse.emf.ecore.resource.ResourceSet
import org.eclipse.emf.common.util.URI
import java.io.ByteArrayInputStream
import org.eclipse.xtext.validation.Issue
import org.eclipse.xtext.diagnostics.Severity

import org.eclipse.xtext.xbase.testing.CompilationTestHelper.Result

@RunWith(XtextRunner)
@InjectWith(LangInjectorProvider)
class TestLiteLangGeneratorExpressions {

	@Inject extension ParseHelper<YWidget>
	@Inject extension ValidationTestHelper
	@Inject extension CompilationTestHelper
	@Inject extension LangLib
	@Inject Provider<ResourceSet> resourceSetProvider;

	@Test
	def void testGenerateExpressionPlus() {
		val body = '''
			package sample.project;						
			@java 
			class A : Widget {	
				var x : Int;			
				public func start() {
				   a : Int = 2;
				   b : Int = 3;
				   c : Int = a + b;
				}
			}			
		'''
		newArrayList(loadLibSource, body).compile() [			
			org.junit.Assert.assertFalse(checkIfIssues(it))
		]
	}

	@Test
	def void testGenerateExpressionMinus() {
		val body = '''
			package sample.project;						
			@java 
			class A : Widget {	
				var x : Int;			
				public func start() {
				   a : Int = 2;
				   b : Int = 3;
				   c : Int = b - a;
				}
			}
			
		'''
		newArrayList(loadLibSource, body).compile() [
			org.junit.Assert.assertFalse(checkIfIssues(it))
		]
	}

	@Test
	def void testGenerateExpressionMultiply() {
		val body = '''
			package sample.project;						
			@java 
			class A : Widget {	
				var x : Int;			
				public func start() {
				   a : Int = 2;
				   b : Int = 3;
				   c : Int = b * a;
				}
			}
			
		'''
		newArrayList(loadLibSource, body).compile() [
			org.junit.Assert.assertFalse(checkIfIssues(it))
		]
	}

	@Test
	def void testGenerateExpressionDivide() {
		val body = '''
			package sample.project;						
			@java 
			class A : Widget {	
				var x : Int;			
				public func start() {
				   a : Int = 2;
				   b : Int = 4;
				   c : Int = b / a;
				}
			}
			
		'''
		newArrayList(loadLibSource, body).compile() [
			org.junit.Assert.assertFalse(checkIfIssues(it))
		]
	}

	@Test
	def void testGenerateExpressionBrackets() {
		val body = '''
			package sample.project;						
			@java 
			class A : Widget {	
				var x : Int;			
				public func start() {
				   a : Int = 2;
				   b : Int = 4;
				   c : Int = ((b - a) * 1) / a;
				}
			}
			
		'''
		newArrayList(loadLibSource, body).compile() [
			org.junit.Assert.assertFalse(checkIfIssues(it))
		]
	}
	
	@Test
	def void testGenerateExpressionEqual() {
		val body = '''
			package sample.project;						
			@java 
			class A : Widget {	
				var x : Int;			
				public func start() {
				   a : Int = 2;
				   b : Int = 4;
				   c : Boolean = a == b;
				}
			}
			
		'''
		newArrayList(loadLibSource, body).compile() [
			org.junit.Assert.assertFalse(checkIfIssues(it))
			// println(it.getGeneratedCode("sample.project.A"))
		]
	}

	@Test
	def void testGenerateExpressionNotEqual() {
		val body = '''
			package sample.project;						
			@java 
			class A : Widget {	
				var x : Int;			
				public func start() {
				   a : Int = 2;
				   b : Int = 4;
				   c : Boolean = a != b;
				}
			}
			
		'''
		newArrayList(loadLibSource, body).compile() [
			org.junit.Assert.assertFalse(checkIfIssues(it))
			//println(it.getGeneratedCode("sample.project.A"))
		]
	}
	
	@Test
	def void testGenerateExpressionLess() {
		val body = '''
			package sample.project;						
			@java 
			class A : Widget {	
				var x : Int;			
				public func start() {
				   a : Int = 2;
				   b : Int = 4;
				   c : Boolean = a < b;
				}
			}
			
		'''
		newArrayList(loadLibSource, body).compile() [
			org.junit.Assert.assertFalse(checkIfIssues(it))
			//println(it.getGeneratedCode("sample.project.A"))
		]
	}

	@Test
	def void testGenerateExpressionGreater() {
		val body = '''
			package sample.project;						
			@java 
			class A : Widget {	
				var x : Int;			
				public func start() {
				   a : Int = 2;
				   b : Int = 4;
				   c : Boolean = a > b;
				}
			}
			
		'''
		newArrayList(loadLibSource, body).compile() [
			org.junit.Assert.assertFalse(checkIfIssues(it))
			//println(it.getGeneratedCode("sample.project.A"))
		]
	}
	
	@Test
	def void testGenerateExpressionLessEqual() {
		val body = '''
			package sample.project;						
			@java 
			class A : Widget {	
				var x : Int;			
				public func start() {
				   a : Int = 2;
				   b : Int = 4;
				   c : Boolean = a <= b;
				}
			}
			
		'''
		newArrayList(loadLibSource, body).compile() [
			org.junit.Assert.assertFalse(checkIfIssues(it))
			//println(it.getGeneratedCode("sample.project.A"))
		]
	}
	
	@Test
	def void testGenerateExpressionGreaterEqual() {
		val body = '''
			package sample.project;						
			@java 
			class A : Widget {	
				var x : Int;			
				public func start() {
				   a : Int = 2;
				   b : Int = 4;
				   c : Boolean = a >= b;
				}
			}
			
		'''
		newArrayList(loadLibSource, body).compile() [
			org.junit.Assert.assertFalse(checkIfIssues(it))
			// println(it.getGeneratedCode("sample.project.A"))
		]
	}
	
	@Test
	def void testGenerateExpressionOr() {
		val body = '''
			package sample.project;						
			@java 
			class A : Widget {	
				var x : Int;			
				public func start() {
				   a : Int = 2;
				   b : Int = 4;
				   c : Boolean = (a >= b) || (a == b);
				}
			}
			
		'''
		newArrayList(loadLibSource, body).compile() [
			//println(it.getGeneratedCode("sample.project.A"))			
			org.junit.Assert.assertFalse(checkIfIssues(it))

		]
	}

	@Test
	def void testGenerateExpressionAnd() {
		val body = '''
			package sample.project;						
			@java 
			class A : Widget {	
				var x : Int;			
				public func start() {
				   a : Int = 2;
				   b : Int = 4;
				   c : Boolean = (a >= b) && (a == 2);
				}
			}
			
		'''
		newArrayList(loadLibSource, body).compile() [
			org.junit.Assert.assertFalse(checkIfIssues(it))
			//println(it.getGeneratedCode("sample.project.A"))
		]
	}
	
	@Test
	def void testGenerateExpressionTrue() {
		val body = '''
			package sample.project;						
			@java 
			class A : Widget {	
				var x : Int;			
				public func start() {
				   a : Int = 2;
				   b : Int = 4;
				   c : Boolean = true;
				}
			}
			
		'''
		newArrayList(loadLibSource, body).compile() [
			org.junit.Assert.assertFalse(checkIfIssues(it))
			//println(it.getGeneratedCode("sample.project.A"))
		]
	}
	
	@Test
	def void testGenerateExpressionFalse() {
		val body = '''
			package sample.project;						
			@java 
			class A : Widget {	
				var x : Int;			
				public func start() {
				   a : Int = 2;
				   b : Int = 4;
				   c : Boolean = true;
				}
			}
			
		'''
		newArrayList(loadLibSource, body).compile() [
			org.junit.Assert.assertFalse(checkIfIssues(it))
			//println(it.getGeneratedCode("sample.project.A"))
		]
	}
	
	@Test
	def void testGenerateExpressionAllLiterals() {
		val body = '''
			package sample.project;						
			@java 
			class A : Widget {			
				public func start() {

				   c : Int = (2 + 4) / (4 * 2);
				}
			}
			
		'''
		newArrayList(loadLibSource, body).compile() [
			org.junit.Assert.assertFalse(checkIfIssues(it))
			//println(it.getGeneratedCode("sample.project.A"))
		]
	}
	
	@Test
	def void testGenerateExpressionNot() {
		val body = '''
			package sample.project;						
			@java 
			class A : Widget {			
				public func start() {

				    c : Boolean = !(1 < 3);
				}
			}
			
		'''
		newArrayList(loadLibSource, body).compile() [
			org.junit.Assert.assertFalse(checkIfIssues(it))
			println(it.getGeneratedCode("sample.project.A"))
		]
	}

	
	def boolean checkIfIssues(Result result) {
		if (result.errorsAndWarnings.size > 0) {
			result.errorsAndWarnings.forEach[println(it)]			
			return true;
		}
 		result.compiledClass
		return false
	}

}
