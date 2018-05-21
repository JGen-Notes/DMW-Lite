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
import eu.jgen.notes.dmw.lite.utility.LangLib
import org.eclipse.emf.ecore.resource.ResourceSet
import org.eclipse.xtext.testing.InjectWith
import org.eclipse.xtext.testing.XtextRunner
import org.eclipse.xtext.testing.util.ParseHelper
import org.eclipse.xtext.testing.validation.ValidationTestHelper
import org.eclipse.xtext.xbase.testing.CompilationTestHelper
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert

@RunWith(XtextRunner)
@InjectWith(LangInjectorProvider)
class GenerateWidget {

	@Inject extension ParseHelper<YWidget>
	@Inject extension ValidationTestHelper
	@Inject  extension CompilationTestHelper //compilationTestHelper
	@Inject extension LangLib
	@Inject Provider<ResourceSet> resourceSetProvider;

	@Test
	def void testGenerateWidgetEmptyClass() {
			val model = '''
			package log.sample.project;
			@database MySQL
			@swift SampleWidgetModule  uses ^MySQL
			class LogWidget : Widget {
				
			}
		''' 
      	model.loadLibAndParse.validate.forEach[println(it)]
      	
		newArrayList(loadLibSource, model).compile() [
			it.errorsAndWarnings.forEach[println(it)]
			
			it.generatedCode.forEach[p1, p2|
				println(p1)
				println(p2)
				
			]
 
		]	 
	}
	
		@Test
	def void testGenerateWidgetSimpleEntity() {
			val model = '''
			package log.sample.project;
			
			@entity Log {
				@attr entryType : Short @length (2);
				@id logid (entryType);
			}
			 @database MySQL
			 @swift SampleWidgetModule  uses ^MySQL
			 
			@td database ^MySQL {
				@table LOG -> Log {
					@column ENTRY_TYPE -> Log.entryType as CHAR @length(2);
					@primary (ENTRY_TYPE);
					
				}
			}
			
			class LogWidget : Widget {
				class ImpLog : Structure -> Log {
					var entryType : Short -> Log.entryType;
				}
					
			var impLog : ImpLog;
			
			public func start() {
				}
				
			}
			
		''' 
         	model.loadLibAndParse.validate.forEach[println(it)]
		newArrayList(loadLibSource, model).compile() [
			it.errorsAndWarnings.forEach[println(it)]
			println(it.getGeneratedCode("log.sample.project.LogWidget"))
		]	 
	}
	
		def private loadLibAndParse(CharSequence p) {
		val resourceSet = resourceSetProvider.get
		loadLib(resourceSet)
		p.parse(resourceSet)
	}
}
