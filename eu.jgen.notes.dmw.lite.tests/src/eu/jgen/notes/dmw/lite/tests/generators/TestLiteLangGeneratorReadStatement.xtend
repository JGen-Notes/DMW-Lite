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
class TestLiteLangGeneratorReadStatement {

	//@Inject extension ParseHelper<YWidget>
	//@Inject extension ValidationTestHelper
	@Inject extension CompilationTestHelper
	@Inject extension LangLib
	//@Inject Provider<ResourceSet> resourceSetProvider;

	@Test
	def void testGenerateReadStatement() {
		val body = '''
			package sample.project             
			
			import eu.jgen.notes.dmw.lite.runtimes.XStructure  
			import eu.jgen.notes.dmw.lite.runtimes.XWidget
			import eu.jgen.notes.dmw.lite.runtimes.XInt
			import eu.jgen.notes.dmw.lite.runtimes.XString
			import eu.jgen.notes.dmw.lite.runtimes.XShort
			
			@database Derby 
			@entity Ee {
				@attr number : XInt @length(9) @default(20) ; 
				@attr type : XShort @length(2) @default(20) ;
				@attr message : XString @length(128) @default("Some message") ;
				@attr description : XString ? @length(500) @default("Some description") ;
				@id logid(number);
			} 
			     
			@td database Derby {
				@table F -> Ee {
					@column NUMBER -> Ee.number as INTEGER @length ( 9 )
					@column TYPE -> Ee.type as SMALLINT @length ( 2 )
					@column MESSAGE -> Ee.message as CHAR @length ( 128 )
					@column DESCRIPTION -> Ee.description as CHAR ? @length ( 200 )
					@primary (NUMBER)
				}
			}    
			class ReadF : XWidget {                
				      
			   class CurrentEe : XStructure -> Ee {
					var number : XInt -> Ee.number;
					var type : XShort -> Ee.type;
			   }
				                    
				
			   var a : ReadF$CurrentEe;   
			   var b : ReadF$CurrentEe;  
			       
			   func start() {
			   	     
			   	this.a.number.value = 1        
			   
					db-read a -> Ee 
				    	where a.number.value == 1 || a.number.value == 2
					success {
						
					} not found {
						
					}
			   	    
			   }
			 	
			}  			

		'''
	   
		newArrayList(loadLibSource, body).compile() [	
			println(it.getGeneratedCode("sample.project.ReadF"))		
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
