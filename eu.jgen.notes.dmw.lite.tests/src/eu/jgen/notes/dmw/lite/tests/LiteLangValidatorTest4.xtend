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
import eu.jgen.notes.dmw.lite.lang.LangPackage
import eu.jgen.notes.dmw.lite.lang.YWidget
import eu.jgen.notes.dmw.lite.utility.LangLib
import eu.jgen.notes.dmw.lite.validation.LangValidator
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.resource.ResourceSet
import org.eclipse.xtext.testing.InjectWith
import org.eclipse.xtext.testing.XtextRunner
import org.eclipse.xtext.testing.util.ParseHelper
import org.eclipse.xtext.testing.validation.ValidationTestHelper
import org.junit.Test
import org.junit.runner.RunWith

import static extension org.junit.Assert.*

@RunWith(XtextRunner)
@InjectWith(LangInjectorProvider)
class LiteLangValidatorTest4 {

	@Inject extension ParseHelper<YWidget>
	@Inject extension ValidationTestHelper
	@Inject extension LangLib
	@Inject Provider<ResourceSet> resourceSetProvider;


	@Test def void testCheckShortCompatibleWithDefaultValue() {
		val result = '''
			package sample.project           
			
			import eu.jgen.notes.dmw.lite.runtimes.XStructure  
			import eu.jgen.notes.dmw.lite.runtimes.XWidget
			import eu.jgen.notes.dmw.lite.runtimes.XInt
			import eu.jgen.notes.dmw.lite.runtimes.XString
			import eu.jgen.notes.dmw.lite.runtimes.XShort
			
			
			
			@entity Ee {
				@attr number : XInt @length(9) @default(20) ;
				@attr type : XShort @length(2) @default(20) ;
				@attr message : XString @length(128) @default("Some message") ;
				@attr description : XString ? @length(500) @default("Some description") ;
				@id logid(number);
			}
			
			class Cos   {    
				class CurrentEe : XStructure -> Ee {
						var number : XInt -> Ee.number;
				} 
			}  
		'''
		
		result.parse.validate
	}

	def private loadLibInResourceSet() {
		resourceSetProvider.get => [loadLib]
	}
}
