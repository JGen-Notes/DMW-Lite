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
import eu.jgen.notes.dmw.lite.tests.LangInjectorProvider
import eu.jgen.notes.dmw.lite.utility.LangLib
import org.eclipse.xtext.testing.InjectWith
import org.eclipse.xtext.testing.XtextRunner
import org.eclipse.xtext.xbase.testing.CompilationTestHelper
import org.eclipse.xtext.xbase.testing.CompilationTestHelper.Result
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(XtextRunner)
@InjectWith(LangInjectorProvider)
class TestLiteLangGeneratorStructures{

	//@Inject extension ParseHelper<YWidget>
	//@Inject extension ValidationTestHelper
	@Inject extension CompilationTestHelper
	@Inject extension LangLib
	//@Inject Provider<ResourceSet> resourceSetProvider;

	@Test
	def void testGenerateExpressionPlus() {
		val body = '''
			package sample.project;						
			@java 
			@entity E {
				@attr number : Int  @length (9) @default(20);
				@attr type : Short @length (2) @default(20); 
				@attr message : String @length (128) @default("Some message");
				@attr description : String? @length (500) @default("Some description");
				@id logid (number);
			}
			class A : Widget {	
				
				class R : Structure -> E {
					public var number : Int -> E.number;
					public var type : Short -> E.type;					
					public var message : String -> E.message;					
					public var description : String -> E.description;					
				}
				
				public var r : R;
						
				public func start() {
				}
			}
		'''
	   
		newArrayList(loadLibSource, body).compile() [	
			println(it.getGeneratedCode("sample.project.A"))		
			Assert.assertFalse(checkIfIssues(it))
			
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
