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
package eu.jgen.notes.dmw.lite.tests.inwork

import com.google.inject.Inject
import eu.jgen.notes.dmw.lite.lang.YWidget
import org.eclipse.xtext.testing.InjectWith
import org.eclipse.xtext.testing.XtextRunner
import org.eclipse.xtext.testing.util.ParseHelper
import org.eclipse.xtext.testing.validation.ValidationTestHelper
import org.junit.Test
import org.junit.runner.RunWith
import eu.jgen.notes.dmw.lite.tests.LangInjectorProvider

@RunWith(XtextRunner)
@InjectWith(LangInjectorProvider)
class LangParsingTest2 {

	@Inject extension ParseHelper<YWidget> parseHelper
	@Inject extension ValidationTestHelper
//	@Inject extension LangUtil
//	@Inject extension LangLib
//	@Inject extension LangIndex
//		@Inject Provider<ResourceSet> rsp

	@Test 
	def void loadModel() {
		val text = '''
			class Object {
			  public  clone() -> Object {
			    return this;
			  }
			
			  public  toString() -> String {
			    // fake implementation
			    return "not implemented";
			  }
			
			  public  equals(Object o) -> Boolean {
			    // fake implementation
			    return false;
			  }
			}
			
			/**
			 * Text of unlimited length.
			 */
			 class String : Object {
			   
			} 
			
			 class Int : Object {
			 	 
			}
			
			 class Short : Object {
			   
			}
			
			 class Double : Object {
			}
			
			 class Boolean : Object {
			    
			}
			
			 class Date : Object {
			}  
			
			 class Time : Object {
			}
			
			 class Timestamp : Object {
			}
			
			 class Widget : Object {
				
				 public  setExitState(ExistState exitState) -> String {
				 	return null;
				}
				
				 public  setCommand(Command cmd) -> String {
				 	return null;
				}
				
				
				 public  isExitState(ExistState exitState) -> Boolean {
					return false;
				}
				
				 public  isCommand(Command cmd) -> Boolean {
				 	return false;
				}
				
				 public  moveView(View viewFrom, View viewTo) -> String {
				 	return null;
					
				}
				
			}
			
			 class ExistState : Object {
				
			}
			
			 class Command : Object {
				
			}
			
			class View : Object {
				
			}
			
			 class Group : Object {
				
				 public  setSubscript(Int value)  {
				  return null;
				}
				
				 public  getSubscript() -> Int {
					return null;
				}
				
				 public  getMax() -> Int {
					return null;
				}
				
				 public  getLast() -> Int {
					return null;
				}
				
			}
			
			 class Relationship : Object {
				
			}
			
			 class RelOneToMany : Relationship {
				
			}
			 
			 class RelOneToOne : Relationship {
				
			}
			
			 class Identifier : Object {
				
			}
			
			/**
			 *  Entity represents a fundamental thing of relevance to the
			 *  enterprise about which data may be kept.
			 */ 
			 class Entity : Object {
				
			}
			
			class Exits   {  
						 	public var  ExistState person_nf;
						 }
						
						class Person : Entity {     
							attr Int number #(9);
							attr String firstName #(25);
							attr String lastName #(25);
							attr Date dateOfBirth;	
							id Identifier myid (number);          
						}
						  
						class CreatePerson : Widget {
							class PersonView : View -> Person {
								public var  Int number;
								public var  String firstName ;
							}
							 var Exits exits;    
							 var PersonView a ; 
							 var PersonView b ; 
							 var Group impGrp ;
								 
							public  hello() -> String { 
							 	super.moveView(this.a, this.b);    
							 	 String name = "hello"; 
							 	 name = "another hello";
								 this.impGrp.setSubscript(1); 
						 		 this.a.number = 1 + 2  + this.a.number; 
						 		 if(this.a.number == 1) {
						 		 	this.moveView(this.a, this.b);
						 		 }
						 		 super.setExitState(this.exits.person_nf);
						 		 Boolean a = super.isExitState(this.exits.person_nf);  
						 		 if(super.isExitState(this.exits.person_nf)) {       
						 		 
						 		 	if(true) {   
						 		 	}
						 		 }
						 		 
								 return  this.a.firstName; 
							}
						}
		'''
	//	  result.parse.assertNoErrors
	
		val result =  text.parse
		 
		 result.validate.forEach[println(it)]
	}



}
