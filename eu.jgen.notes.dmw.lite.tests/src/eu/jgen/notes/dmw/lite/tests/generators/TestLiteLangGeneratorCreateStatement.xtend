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
class TestLiteLangGeneratorCreateStatement{

	//@Inject extension ParseHelper<YWidget>
	//@Inject extension ValidationTestHelper
	@Inject extension CompilationTestHelper
	@Inject extension LangLib
	//@Inject Provider<ResourceSet> resourceSetProvider;

	@Test
	def void testGenerateExpressionPlus() {
		val body = '''
			package dmw.test.derby;
			
			@database Derby    
			
			@java                
			  
			@td database Derby {     
				@table DataTypes -> AllDataTypes {
					@column NUMBER -> AllDataTypes.number as INTEGER @length ( 9 )
					@column INT_TYPE -> AllDataTypes.intType as INTEGER @length ( 9 )
					@column SHORT_TYPE -> AllDataTypes.shortType as SMALLINT @length ( 2 )
					@column DATE_TYPE -> AllDataTypes.dateType as DATE
					@column TIME_TYPE -> AllDataTypes.timeType as TIME
					@column STRING_TYPE -> AllDataTypes.stringType as CHAR @length ( 128 )
					@column TIMESTAMP_TYPE -> AllDataTypes.timestampType as TIMESTAMP
					@column DOUBLE_TYPE -> AllDataTypes.doubleType as DECIMAL @length ( 2 )
					@column LONG_TYPE -> AllDataTypes.longType as BIGINT     @length ( 2 )
					@column BOOL_TYPE -> AllDataTypes.boolType as BOOLEAN
					@primary (NUMBER)
				} 
			}                     
			     
			@entity AllDataTypes {
				@attr number : Int @length(9) @default(20) ;
				@attr intType : Int @length(9) @default(100000) ;
				@attr shortType : Short @length(2) @default(20) ;
				@attr longType : Long @length(2) @default(20) ;
				@attr doubleType : Double @length(2) @default(20) ;
				@attr dateType : Date;
				@attr timeType : Time;
				@attr timestampType : Timestamp;
				@attr stringType : String @length(128) @default("Some message") ;
				@attr boolType : Bool;
				@id logid(number);       
			}
			class CreateAllDataTypes : Widget {   
				class EntityAllTypes : Structure -> AllDataTypes {  
						public var number : Int -> AllDataTypes.number;
						public var intType : Int -> AllDataTypes.intType;
						public var shortType : Short -> AllDataTypes.shortType;
						public var longType : Long -> AllDataTypes.longType;
						public var doubleType : Double -> AllDataTypes.doubleType;
						public var dateType : Date -> AllDataTypes.dateType;
						public var timeType : Time -> AllDataTypes.timeType;
						public var timestampType : Timestamp -> AllDataTypes.timestampType;  
						public var stringType : String -> AllDataTypes.stringType;
						public var boolType : Bool -> AllDataTypes.boolType;  
				}
				public var entityAllTypes : EntityAllTypes;
				public func start() {
					db-create entityAllTypes -> AllDataTypes {
					}  
					success {
					}
					already exist {
					}
				}                  
			}  			
			
		'''
	   
		newArrayList(loadLibSource, body).compile() [	
			println(it.getGeneratedCode("dmw.test.derby.CreateAllDataTypes"))		
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
