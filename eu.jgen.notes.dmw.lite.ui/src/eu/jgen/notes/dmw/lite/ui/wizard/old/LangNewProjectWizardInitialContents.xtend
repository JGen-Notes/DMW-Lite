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
package eu.jgen.notes.dmw.lite.ui.wizard


import com.google.inject.Inject
import org.eclipse.xtext.generator.IFileSystemAccess2
import org.eclipse.xtext.resource.FileExtensionProvider

class LangNewProjectWizardInitialContents {
	@Inject
	FileExtensionProvider fileExtensionProvider

	def generateInitialContents(IFileSystemAccess2 fsa) {
		 
		fsa.generateFile(
			"src/log/sample/project/LogWidget." + fileExtensionProvider.primaryFileExtension,
			'''
			/**
			 * This is sample application helping to explain how to widget application
			 * is built and how to start development of the widget application.
			 */
			
			/*
			 * We need to declare all entities used in the widget first.
			 * The <code>Log</code> is very simple entity type
			 * having three attributes. Entity type has one identifier.
			 * 
			 * As you can see, we are using annotations to define entity type and its properties.
			 * Annotation is a mechanism do deliver more specific meta data that will be
			 * used later during generation process.
			 */
			@entity Log {
				/*
				 *  How to read below: Log has attribute <code>entryType</code> of type <code>Short</code> having
				 *  maximum length of 2 digits.
				 */ 
				@attr entryType : Short @length (2);
				@attr message : String @length (128);
				@attr timeCreated : Time;
				@id logid (timeCreated);
			}
			
			/*
			 * We need to define so called exit states that are used to describe global
			 * status of the widget. We use <code>class</code> to define all exit states
			 * used in the widget application.
			 */
			
			class ExitStates {	
				/*
				 * We like to tell outside world that process creating log entry was completed successfully.
				 * We are going to use the following exit state. You need define some properties for each exit state.
				 * Again, we use annotations to provide more specific information detailing this particular
				 * exit state.
				 */
				public var processCompleted : ExitState  
				   @action (normal)  @msgtype(none) @message("Process completed.");
				/*
				 * In case log record already exist exit state will be set to the following state.
				 */
				public var logAlreadyExists : ExitState  
				   @action (normal)  @msgtype(none) @message("Log entry already exists.");
			}
			
			/*
			 * This is an example of the widget. This widget should create instance of log record
			 * in the database. Please notice that your class extending class <code>Widget</code>. 
			 * You can visit declaration of the class by clicking on the  name once <code>cntl</code>
			 * button pressed.
			 */
			class LogWidget : Widget {
			 	
			 	/*
			 	 * We need to declare inner class defining data structure. You can notice that
			 	 * class <code>Record</code> extends special class <code>Structure</code>.
			 	 * This class is referring to the annotation <code>Log</code>. You can
			 	 * automate creation of the properties by coping definition of the annotation.
			 	 */
			 	class Record : Structure -> Log {
			 		public var entryType : Short -> Log.entryType;
					public var message : String -> Log.message;
					public var timeCreated : Time -> Log.timeCreated;
				}
			
				/*
				 * We need to create property exits using class <code>ExitStates</code> defined 
				 * earlier. Property will be instantiated automatically before creating widget. 
				 */
				private var exits : ExitStates;
				
				/*
				 * This object will be instantiated automatically by the session manager.
				 * Properties will be set externally by the invoking process.
				 */
				public var imp : Record;
				
				/*
				 * We use this object to create row in the SQL database.
				 */
				private var current : Record;
				
				/*
				 * We need to define function which will be executed by the external
				 * process. We make it <code>public</code>.
				 */
				public func start() -> Record {
					
					/*
					 * Create log record using provided information.
					 */
					create current -> Log {
						// we use special word self when referring local properties.
					 	self.current.message = "Hello World";
					 	self.current.entryType = self.imp.entryType;
					 	self.current.timeCreated = self.imp.timeCreated;
					 	// or
					 	super.moveStruct(self.imp,self.current);
					 } success {
					 	// we use word super  when referring to properties or functions of the super class
						super.setExitState(self.exits.processCompleted);
						return self.imp;
					} already exist {
						super.setExitState(self.exits.logAlreadyExists);
					}
					return null;
				}
			}
			
			/*
			 * This file has everything what is required in a single file. However, in real world you
			 * will keep different information in different files. The DMW Lite supports concept of packages
			 * and import statement.
			 * 
			 */
			'''
			
			)
	}  
}
