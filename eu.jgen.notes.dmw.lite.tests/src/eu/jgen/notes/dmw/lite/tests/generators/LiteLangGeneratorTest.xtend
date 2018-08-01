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
import eu.jgen.notes.dmw.lite.utility.LangLib
import org.eclipse.xtext.diagnostics.Severity

import org.eclipse.xtext.testing.InjectWith
import org.eclipse.xtext.testing.XtextRunner
 
import org.eclipse.xtext.xbase.lib.util.ReflectExtensions
 
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

import static extension org.junit.Assert.*
import org.eclipse.xtext.xbase.testing.CompilationTestHelper
import org.eclipse.xtext.xbase.testing.CompilationTestHelper.Result
import org.eclipse.xtext.xbase.testing.TemporaryFolder
import eu.jgen.notes.dmw.lite.tests.LangInjectorProvider

@RunWith(XtextRunner)
@InjectWith(LangInjectorProvider)
class LiteLangGeneratorTestMySql {

	@Rule
	@Inject public TemporaryFolder temporaryFolder
	@Inject extension CompilationTestHelper
	@Inject extension ReflectExtensions
	@Inject LangLib langLib

	@Test
	def void testEmptyProgram() {
		'''
		'''.compile[]
	}

	@Test
	def void testGeneratedCodeWithoutPackage() {
		'''
			@database MySQL
			
			@entity Log {
				@attr entryType : Short @length(2);
				@attr message : String @length(128);
				@attr timeCreated : Time;
				@id logid(timeCreated);
			}
			
			@td database MySQL {
				@table LOG -> Log {
				   @column ENTRY_TYPE -> Log.entryType as SMALLINT ;
				   @column MESSAGE -> Log.message as CHAR @length ( 128 ) 	
				   @column TIME_CREATED -> Log.timeCreated as TIME
				   @primary (TIME_CREATED);
				}
			}
		'''.assertCompilesTo(
			'''
				CREATE TABLE `LOG` (
					`ENTRY_TYPE` SMALLINT ,
					`MESSAGE` CHAR() ,
					`TIME_CREATED` TIME 
				,	CONSTRAINT `pk_LOG`
					PRIMARY KEY (`TIME_CREATED`)
				);
			'''
		)
	}


	protected def void checkNoValidationErrors(Result it) {
		val allErrors = getErrorsAndWarnings.filter[severity == Severity.ERROR]
		if (!allErrors.empty) {
			throw new IllegalStateException(
				"One or more resources contained errors : " + allErrors.map[toString].join(", ")
			);
		}
	}

}
