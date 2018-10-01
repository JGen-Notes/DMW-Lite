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
class TestLiteLangGeneratorReadStatementWithJoin {

	//@Inject extension ParseHelper<YWidget>
	//@Inject extension ValidationTestHelper
	@Inject extension CompilationTestHelper
	@Inject extension LangLib
	//@Inject Provider<ResourceSet> resourceSetProvider;

	@Test
	def void testGenerateExpressionPlus() {
		val body = '''
			package templates;
			
			@database MySQL
			@java 
			
			@entity Log {
				@attr entryType : Short @length(2);
				@rel @parent has -> Description * <- Description.isFor;
				@id logid(entryType);
			}
			
			@entity Description {  
				@attr number : Short @length(2);
				@attr message : String @length(128);
				@rel isFor -> Log <- Log.has;
				@id logid(number);  
			}
			
			@td database MySQL {     
				@table LOG -> Log {
					@column ENTRY_TYPE -> Log.entryType as CHAR @length(2);
					@primary (ENTRY_TYPE);
				}
				@table DESCRIPTION -> Description {
					@column NUMBER -> Description.number as SMALLINT @length (2)
					@column MESSAGE -> Description.message as CHAR @length (128)
					@primary (NUMBER)
				    @foreign Description.isFor { 
				    	@column FK_LOG__ENTRY_TYPE -> LOG.ENTRY_TYPE
				    }
			}
			
			}
			
			class LogWidget : Widget {
				
				class EntityLog : Structure -> Log {
					public var entryType : Short -> Log.entryType;
			
				}
				
				class EntityDescription : Structure -> Description {
					public var number : Short -> Description.number;
			        public var message : String -> Description.message;
				}
				
				var entityLog : EntityLog;
				var entityDescription : EntityDescription;
				var impEntityLog : EntityLog;
				
				func start() {
					
					read entityLog -> Log, entityDescription -> Description 
					   join entityLog -> Log.has -> entityDescription
					   where self.entityLog.entryType == self.impEntityLog.entryType
					      && self.entityDescription.number == 100
					      && self.entityDescription.message == "hello"
					   success {
					   	
					   } not found {
					   	
					   }
					
				}
				
			}
		'''
	   
		newArrayList(loadLibSource, body).compile() [	
			println(it.getGeneratedCode("templates.LogWidget"))		
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
