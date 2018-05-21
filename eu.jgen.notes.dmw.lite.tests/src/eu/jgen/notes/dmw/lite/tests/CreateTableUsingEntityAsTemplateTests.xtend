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
import eu.jgen.notes.dmw.lite.lang.YWidget
import eu.jgen.notes.dmw.lite.lang.YAnnotEntity
import eu.jgen.notes.dmw.lite.utility.LangLib
import com.google.inject.Provider
import org.eclipse.emf.ecore.resource.ResourceSet
import eu.jgen.notes.dmw.lite.utility.LangDBUtil

@RunWith(XtextRunner)
@InjectWith(LangInjectorProvider)
class CreateTableUsingEntityAsTemplateTests {

	@Inject extension ParseHelper<YWidget>
	@Inject extension LangDBUtil
	@Inject extension ValidationTestHelper
	@Inject extension LangLib
	@Inject Provider<ResourceSet> resourceSetProvider;

	@Test
	def void testEntityDefault() {
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

		val entity = model.eAllContents.findFirst [ element |
			element instanceof YAnnotEntity
		]
		val table = (entity as YAnnotEntity).converEntityIntoTable
		Assert.assertEquals("LOG_RECORD", table.name)
	}

	@Test
	def void testEntityAnnotated() {
		val model = '''
			package eu.jgen.notes.dmw.sample;
			
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
		'''.loadLibAndParse

		model.validate.forEach[println(it)]

		val entity = model.eAllContents.findFirst [ element |
			element instanceof YAnnotEntity
		]
		val table = (entity as YAnnotEntity).converEntityIntoTable
		Assert.assertEquals("LOG", table.name)
	}

	def private loadLibAndParse(CharSequence p) {
		val resourceSet = resourceSetProvider.get
		loadLib(resourceSet)
		p.parse(resourceSet)
	}

}
