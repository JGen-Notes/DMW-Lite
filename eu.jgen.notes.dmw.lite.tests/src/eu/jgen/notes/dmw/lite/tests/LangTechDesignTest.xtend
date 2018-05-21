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
import org.eclipse.xtext.testing.InjectWith
import org.eclipse.xtext.testing.XtextRunner
import org.eclipse.xtext.testing.util.ParseHelper
import org.eclipse.xtext.testing.validation.ValidationTestHelper
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import eu.jgen.notes.dmw.lite.utility.LangLib
import com.google.inject.Provider
import org.eclipse.emf.ecore.resource.ResourceSet
import eu.jgen.notes.dmw.lite.lang.YWidget
import eu.jgen.notes.dmw.lite.lang.LangPackage
import eu.jgen.notes.dmw.lite.validation.LangValidator
import eu.jgen.notes.dmw.lite.runtimes.XWidget

@RunWith(XtextRunner)
@InjectWith(LangInjectorProvider)
class LangTechDesignTest {

	@Inject extension ParseHelper<YWidget>
	@Inject extension ValidationTestHelper
	@Inject extension LangLib
	@Inject Provider<ResourceSet> rsp

	@Test
	def void testCheckIfEntityImplemented1() {
		val model = '''
			package eu.jgen.notes.dmw.sample;
			
			@entity LogRecord {
				/*
				 *  How to read below: Log has attribute <code>entryType</code> of type <code>Short</code> having
				 *  maximum length of 2 digits.
				 */ 
				@attr entryType : Short @length (2);
				@attr message : String @length (128);
				@attr timeCreated : Time;
				@id logid (timeCreated);
			}
		'''.loadLibAndParse
		model.validate.forEach[println(it)]
		model.assertNoIssues

	}

	@Test
	def void testCheckIfEntityImplemented2() {
		val model = '''
			package eu.jgen.notes.dmw.sample;
			
			@entity LogRecord {
			}
			
			@td {
				
			}
		'''.loadLibAndParse
		model.validate.forEach[println(it)]
		model.assertWarning(LangPackage::eINSTANCE.YAnnotEntity, LangValidator.ENTITY_NO_TECH_DESIGN,
			"The declared entity is not yet implemented as table")
	}

	@Test
	def void testCheckIfEntityImplemented3() {
		val model = '''
			package eu.jgen.notes.dmw.sample;
			
			@entity LogRecord {
			}
			
			@td {
				@table LOG_RECORD -> LogRecord {
					
			    }
			}
		'''.loadLibAndParse
		model.validate.forEach[println(it)]
		model.assertError(LangPackage::eINSTANCE.YAnnotTable, LangValidator.TABLE_DOES_NOT_HAVE_COLUMNS,
			"Table does not have any columns.")
	}

	@Test
	def void testCheckIfAttributeImplemented1() {
		val model = '''
			package eu.jgen.notes.dmw.sample;
			
			@entity LogRecord {
				@attr entryType : Short @length (2);

			}
			
			@td {
				@table LOG_RECORD -> LogRecord {
					
			    }
			}
		'''.loadLibAndParse
		model.validate.forEach[println(it)]
		model.assertWarning(LangPackage::eINSTANCE.YAnnotAttr, LangValidator.ATTRIBUTE_NO_TECH_DESIGN,
			"The declared attribute is not yet implemented as a column")

	}

	@Test
	def void testCheckIfAttributeImplemented2() {
		val model = '''
			package eu.jgen.notes.dmw.sample;
			
			@entity LogRecord {
				/*
				 *  How to read below: Log has attribute <code>entryType</code> of type <code>Short</code> having
				 *  maximum length of 2 digits.
				 */ 
				@attr entryType : Short @length (2);
				@attr message : String @length (128);
				@attr timeCreated : Time;
				@id logid (timeCreated);
			}
			
			@td {
				
			}
		'''.loadLibAndParse
		model.validate.forEach[println(it)]
		model.assertNoIssues
		model.assertWarning(LangPackage::eINSTANCE.YAnnotEntity, LangValidator.ENTITY_NO_TECH_DESIGN,
			"The declared entity is not yet implemented as table")
	}

	@Test
	def void testCheckIfTableComplete1() {
	val model = '''
			package eu.jgen.notes.dmw.sample;
			
			@entity LogRecord {
			}
			
			@td {
				@table LOG_RECORD -> LogRecord {
					
			    }
			}
		'''.loadLibAndParse
		model.validate.forEach[println(it)]
		model.assertError(LangPackage::eINSTANCE.YAnnotTable, LangValidator.TABLE_DOES_NOT_HAVE_COLUMNS,
			"Table does not have any columns.")
	}

	@Test
	def void testCheckIfColumnNameUnique() {
		val model = '''
			package eu.jgen.notes.dmw.sample;
			
			@entity LogRecord {

				@attr entryType : Short @length (2);

			}
			
			@td {
				@table LOG_RECORD -> LogRecord {
					 @column ENTRY_TYPE -> LogRecord.entryType as SMALLINT;
					  @column ENTRY_TYPE -> LogRecord.entryType as SMALLINT;
			    }
				
			}
		'''.loadLibAndParse
		model.validate.forEach[println(it)]
		model.assertError(LangPackage::eINSTANCE.YAnnotAbstractColumn, LangValidator.COLUMN_NAME_NOT_UNIQUE,
			"Table column name is not unique.")
	}

	@Test
	def void testCheckIfIdentifierImplemented() {
		val model = '''
			package eu.jgen.notes.dmw.sample;
			
			@entity LogRecord {
				/*
				 *  How to read below: Log has attribute <code>entryType</code> of type <code>Short</code> having
				 *  maximum length of 2 digits.
				 */ 
				@attr entryType : Short @length (2);
				@attr message : String @length (128);
				@attr timeCreated : Time;
				@id logid (timeCreated);
			}
			
			@td {
				
			}
		'''.loadLibAndParse
		model.validate.forEach[println(it)]
		model.assertNoIssues
		model.assertWarning(LangPackage::eINSTANCE.YAnnotEntity, LangValidator.ENTITY_NO_TECH_DESIGN,
			"The declared entity is not yet implemented as table")
	}

	def private loadLibAndParse(CharSequence p) {
		val resourceSet = rsp.get
		loadLib(resourceSet)
		p.parse(resourceSet)
	}
}
