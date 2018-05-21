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
import eu.jgen.notes.dmw.lite.scoping.LangIndex
import eu.jgen.notes.dmw.lite.utility.LangLib
import eu.jgen.notes.dmw.lite.utility.LangUtil
import org.eclipse.emf.ecore.resource.ResourceSet
import org.eclipse.xtext.testing.InjectWith
import org.eclipse.xtext.testing.XtextRunner
import org.eclipse.xtext.testing.util.ParseHelper
import org.eclipse.xtext.testing.validation.ValidationTestHelper
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(XtextRunner)
@InjectWith(LangInjectorProvider)
class LiteLangParsingTest2 {

	@Inject extension ParseHelper<YWidget> parseHelper
	@Inject extension ValidationTestHelper
	@Inject extension LangUtil
	@Inject extension LangLib
	@Inject extension LangIndex
	@Inject Provider<ResourceSet> rsp

	@Test
	def void loadModel() {
		val text = '''
			class Person {
				
			}
		'''
		val result = text.loadLibAndParse()
		result.assertNoErrors
	}

	@Test
	def void testDefineExitStates() {
		val text = '''
			class Exits {
				public var display : ExitState @prop(action : "normal", msgtype : "error", msg : "Display required");
			}
						
			class CheckExits : Widget {
				var exits : Exits;
				public func hello() { 
					super.setExitState(self.exits.display);
				}
			}		'''
		val result = text.loadLibAndParse()
		result.assertNoErrors
	}

	@Test
	def void testDefineCommands() {
		val text = '''
			class Commands {
				public var DISPLAY : Command;
				public var SELECT : Command;
			}
		'''
		val result = text.loadLibAndParse()
		result.assertNoErrors
	}
	
	@Test
	def void testDefineEntity() {
		val text = '''
			@entity Person  {

			}
		'''
		val result = text.loadLibAndParse()
		result.assertNoErrors
	}
	
	@Test
	def void testDefineEntityWithAttributes() {
		val text = '''
			@entity Person : Entity {
			@attr number : Int  @size(length : 9);
			@attr firstName : String @size(length : 25);
			@attr lastName : String @size(length : 25);
			@attr description : String @size(length : 200, variable : "yes");
			@attr dateOfBirth : Date;
			@attr lastEntry : Time;
			@attr lastUpdate : Timestamp;
			@attr age: Short;
			@attr rate: Double @size(length : 9, decimal : 2);
			}
		'''
		val result = text.loadLibAndParse()
		result.assertNoErrors
	}

	@Test
	def void testDefineStructure() {
		val text = '''
			@entity Person  {

			}
			
			class PersonView : Structure -> Person {
				
			}
		'''
		val result = text.loadLibAndParse()
		result.assertNoErrors
	}

	@Test
	def void testDefineWidget() {
		val text = '''
			class CreatePerson : Widget {

			}
		'''
		val result = text.loadLibAndParse()
		result.assertNoErrors
	}
	
	@Test
	def void testReadStatement() {
		val text = '''
		class ExitStates {	
				public var processStarted : ExitState @action (normal)  @msgtype(none) @message("Process started.");
				public var processCompleted : ExitState  @action (normal)  @msgtype(none) @message("Process completed.");
				public var personNF : ExitState  @action (normal)  @msgtype(none) @message("Person not found.");
				public var personAE : ExitState  @action (normal)  @msgtype(none) @message("Person already exists.");
		}
		
		@entity Person {     
			@attr nationalid : Int  @length(9);
			@attr firstName : String  @length(25);
			@attr lastName : String  @length(25);
			@attr description : String? @length (200);
			@attr dateOfBirth : Date? ;	
			@rel livesIn -> Address <- Address.isPlaceFor ;
			@id personid (nationalid);    
		}
		
		@entity Employee : Person {
			@attr number : Short @length(4);
			@attr jobTittle : String @length(10);
			@rel employedBy -> Department <- Department.employs;
			@id myid (number);          
		}
		
		@entity Department{     
			@attr number : Int  @length(9);
			@attr name : String  @length(50);
			@attr description : String? @length (200);
			@attr location : String? ;	
			@rel employs -> Employee <- Employee.employedBy;
			@id myid (number);          
		}	
		
		@entity Address {      
		
			@attr town : String  @length(50);
			@attr streetName : String? @length (30);
			@attr number : String  @length(6);
			@attr zipcode : String? ;	
			@rel isPlaceFor -> Person <- Person.livesIn;
			@id addrid (number);          
		}
		
		class ReadPerson : Widget { 
			
			class ExpPerson : Structure -> Person {
				public var number : Int;
				public var firstName : String;
				public var lastName : String;
				public var description : String?;
				public var dateOfBirth : Date?;	
			}
			
			class ImpPerson : Structure -> Person {
				public var number : Int;
			}
			
			class CurrentPerson : Structure -> Person {
				public var number : Int;
				public var firstName : String;
				public var lastName : String;
				public var description : String?;
				public var dateOfBirth : Date?;	
			}
			
			var exits : ExitStates;
			var impPerson : ImpPerson;
			var expPerson : ExpPerson;
			var currentPerson : CurrentPerson;
			
			func start() { 
				if(super.isExitState(self.exits.processStarted)) {
				   read currentPerson -> Person
				   where self.currentPerson.number == self.impPerson.number
				    success {
				      super.moveView(self.currentPerson, self.expPerson); 
				   	  super.setExitState(self.exits.processCompleted);
				   } not found {
				   	 super.setExitState(self.exits.personNF);
				   }
				}	
			}	
		}	
		'''
		val result = text.loadLibAndParse()
		result.assertNoErrors
	}
	
	@Test
	def void testReadEachStatement() {
		val text = '''
		class ExitStates {	
				public var processStarted : ExitState @action (normal)  @msgtype(none) @message("Process started.");
				public var processCompleted : ExitState  @action (normal)  @msgtype(none) @message("Process completed.");
				public var personNF : ExitState  @action (normal)  @msgtype(none) @message("Person not found.");
				public var personAE : ExitState  @action (normal)  @msgtype(none) @message("Person already exists.");
		}
		
		@entity Person {     
			@attr nationalid : Int  @length(9);
			@attr firstName : String  @length(25);
			@attr lastName : String  @length(25);
			@attr description : String? @length (200);
			@attr dateOfBirth : Date? ;	
			@rel livesIn -> Address <- Address.isPlaceFor ;
			@id personid (nationalid);    
		}	
		
		class ReadEachPerson : Widget {  
			
			class ExpPerson : Structure -> Person {
				public var number : Int;
				public var firstName : String;
				public var lastName : String;
				public var description : String ?;
				public var dateOfBirth : Date ?;
			}
		
			class ImpPerson : Structure -> Person {
				public var number : Int;
			}
		
			class CurrentPerson : Structure -> Person {
				public var number : Int;
				public var firstName : String;
				public var lastName : String;
				public var description : String ?;
				public var dateOfBirth : Date ?;
			}
		
			var exits : ExitStates;
			var impPerson : ImpPerson;
			var expPerson : ExpPerson;
			var selectedPersons : Array <expPerson>;
			var currentPerson : CurrentPerson;
		
			func start() {
				if (super.isExitState(self.exits.processStarted)) {
					read each currentPerson -> Person 
					where self.currentPerson.number > self.impPerson.number
				    target currentPerson;
				}
			}
		}
		'''
		val result = text.loadLibAndParse()
		result.assertNoErrors
	}
	
	@Test
	def void testCreateStatement() {
		val text = '''
		class ExitStates {	
				public var processStarted : ExitState @action (normal)  @msgtype(none) @message("Process started.");
				public var processCompleted : ExitState  @action (normal)  @msgtype(none) @message("Process completed.");
				public var personNF : ExitState  @action (normal)  @msgtype(none) @message("Person not found.");
				public var personAE : ExitState  @action (normal)  @msgtype(none) @message("Person already exists.");
		}
		
		@entity Person {     
			@attr nationalid : Int  @length(9);
			@attr firstName : String  @length(25);
			@attr lastName : String  @length(25);
			@attr description : String? @length (200);
			@attr dateOfBirth : Date? ;	
			@rel livesIn -> Address <- Address.isPlaceFor ;
			@id personid (nationalid);    
		}	
		
		class CreatePerson : Widget {
			
			class ImpPerson : Structure -> Person {
				public var number : Int;
				public var firstName : String;
				public var lastName : String;
				public var description : String?;
				public var dateOfBirth : Date?;	
			}
			
			class ExpPerson : Structure -> Person {
				public var number : Int;
		
			}
			
			var exits : ExitStates;
			var impPerson : ImpPerson;
			var expPerson : ExpPerson;
			var currentPerson : ImpPerson;
			
			func start() { 
			
				if(super.isExitState(self.exits.processStarted)) {
				   create currentPerson -> Person  {
				      self.currentPerson.number = 10;
				      self.currentPerson.firstName = self.impPerson.firstName;
				      self.currentPerson.lastName = self.impPerson.lastName;		      	
				   } success {
				   	  super.setExitState(self.exits.processCompleted);
				   	  self.expPerson.number = self.currentPerson.number;
				   } already  exist {
				   	 super.setExitState(self.exits.personAE);
				   }
				}	
			}	
		}	
		'''
		val result = text.loadLibAndParse()
		result.assertNoErrors
	}
	
	@Test
	def void testDeleteStatement() {
		val text = '''
		class ExitStates {	
			public var processStarted : ExitState @action (normal)  @msgtype(none) @message("Process started.");
			public var processCompleted : ExitState  @action (normal)  @msgtype(none) @message("Process completed.");
			public var personNF : ExitState  @action (normal)  @msgtype(none) @message("Person not found.");
			public var personAE : ExitState  @action (normal)  @msgtype(none) @message("Person already exists.");
		}
		
		@entity Person {     
			@attr nationalid : Int  @length(9);
			@attr firstName : String  @length(25);
			@attr lastName : String  @length(25);
			@attr description : String? @length (200);
			@attr dateOfBirth : Date? ;	
			@rel livesIn -> Address <- Address.isPlaceFor ;
			@id personid (nationalid);    
		}		
		
		class DeletePerson : Widget {   
			
			class ImpPerson : Structure -> Person {
				public var number : Int;
			}
				
			class CurrentPerson : Structure -> Person {
				public var number : Int;
				public var firstName : String;
				public var lastName : String;
				public var description : String?;
				public var dateOfBirth : Date?;	
			}
			
			var exits : ExitStates;
			var impPerson : ImpPerson;
			var currentPerson : CurrentPerson;
			
			func start() { 
				if(super.isExitState(self.exits.processStarted)) {
				   read currentPerson -> Person  
				   where self.currentPerson.number == self.impPerson.number
				    success {
				       delete  currentPerson -> Person;	       
				   } not found {
				   	 super.setExitState(self.exits.personNF);
				   }
				}	
			}	
		}	
		'''
		val result = text.loadLibAndParse()
		result.assertNoErrors
	}
	
	@Test
	def void testUpdateStatement() {
		val text = '''
		class ExitStates {	
			public var processStarted : ExitState @action (normal)  @msgtype(none) @message("Process started.");
			public var processCompleted : ExitState  @action (normal)  @msgtype(none) @message("Process completed.");
			public var personNF : ExitState  @action (normal)  @msgtype(none) @message("Person not found.");
			public var personAE : ExitState  @action (normal)  @msgtype(none) @message("Person already exists.");
		}
		
		@entity Person {     
			@attr nationalid : Int  @length(9);
			@attr firstName : String  @length(25);
			@attr lastName : String  @length(25);
			@attr description : String? @length (200);
			@attr dateOfBirth : Date? ;	
			@rel livesIn -> Address <- Address.isPlaceFor ;
			@id personid (nationalid);    
		}
		
		class UpdatePerson : Widget {  
			
			class ImpPerson : Structure -> Person {
				public var number : Int;
				public var description : String?;
				public var dateOfBirth : Date?;	
			}
				
			class CurrentPerson : Structure -> Person {
				public var number : Int;
				public var firstName : String;
				public var lastName : String;
				public var description : String?;
				public var dateOfBirth : Date?;	
			}
			
			var exits : ExitStates;
			var impPerson : ImpPerson;
			var currentPerson : CurrentPerson;
			
			func start() { 
				if(super.isExitState(self.exits.processStarted)) {
				   read currentPerson -> Person
				   where self.currentPerson.number == self.impPerson.number
				    success {
				       update  currentPerson -> Person {
				       	  self.currentPerson.description = self.impPerson.description;
				       	  self.currentPerson.dateOfBirth = self.impPerson.dateOfBirth;
				       } success {
				       	  super.setExitState(self.exits.processCompleted);
				       }		       
				   } not found {
				   	 super.setExitState(self.exits.personNF);
				   }
				}	
			}	
		}	
		'''
		val result = text.loadLibAndParse()
		result.assertNoErrors
	}
		
	def private loadLibAndParse(CharSequence p) {
		val resourceSet = rsp.get
		loadLib(resourceSet)
		p.parse(resourceSet)
	}

}
