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
import eu.jgen.notes.dmw.lite.lang.YAnnotAttr
import eu.jgen.notes.dmw.lite.lang.YWidget
import eu.jgen.notes.dmw.lite.utility.LangLib
import org.eclipse.emf.ecore.resource.ResourceSet
import org.eclipse.xtext.testing.InjectWith
import org.eclipse.xtext.testing.XtextRunner
import org.eclipse.xtext.testing.util.ParseHelper
import org.eclipse.xtext.testing.validation.ValidationTestHelper
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import eu.jgen.notes.dmw.lite.utility.LangDBUtil

@RunWith(XtextRunner)
@InjectWith(LangInjectorProvider)
class CreateColumnUsingAttributeAsTemplateTests {

	@Inject extension ParseHelper<YWidget>
	@Inject extension LangDBUtil
	@Inject extension ValidationTestHelper
	@Inject extension LangLib
	@Inject Provider<ResourceSet> resourceSetProvider;

	@Test
	def void testIntegerMandatory() {
		val model = '''
			package eu.jgen.notes.dmw.sample;		
			@entity LogRecord {
				@attr number : Int @length(9);
			}
		'''.loadLibAndParse
		val attr = model.eAllContents.findFirst [ element |
			element instanceof YAnnotAttr
		] as YAnnotAttr

		model.validate.forEach[println(it)]

		val abstractColumn = attr.converAttributeIntoAbstractColumn
		Assert.assertEquals("NUMBER", abstractColumn.name)
		Assert.assertEquals("INTEGER", abstractColumn.getColumnTypeName)
		Assert.assertEquals(9, abstractColumn.getColumnLength)
		Assert.assertEquals(0, abstractColumn.getColumnDecimal)
		Assert.assertFalse(abstractColumn.isColumnOptional)
	}

	@Test
	def void testIntegerOptional() {
		val model = '''
			package eu.jgen.notes.dmw.sample;		
			@entity LogRecord {
				@attr number : Int? @length(9);
			}
		'''.loadLibAndParse
		val attr = model.eAllContents.findFirst [ element |
			element instanceof YAnnotAttr
		] as YAnnotAttr

		model.validate.forEach[println(it)]

		val abstractColumn = attr.converAttributeIntoAbstractColumn
		Assert.assertEquals("NUMBER", abstractColumn.name)
		Assert.assertEquals("INTEGER", abstractColumn.getColumnTypeName)
		Assert.assertEquals(9, abstractColumn.getColumnLength)
		Assert.assertEquals(0, abstractColumn.getColumnDecimal)
		Assert.assertTrue(abstractColumn.isColumnOptional)
	}

	@Test
	def void testShortMandatory() {
		val model = '''
			package eu.jgen.notes.dmw.sample;		
			@entity LogRecord {
				@attr number : Short @length(4);
			}
		'''.loadLibAndParse
		val attr = model.eAllContents.findFirst [ element |
			element instanceof YAnnotAttr
		] as YAnnotAttr

		model.validate.forEach[println(it)]

		val abstractColumn = attr.converAttributeIntoAbstractColumn
		Assert.assertEquals("NUMBER", abstractColumn.name)
		Assert.assertEquals("SMALLINT", abstractColumn.getColumnTypeName)
		Assert.assertEquals(4, abstractColumn.getColumnLength)
		Assert.assertEquals(0, abstractColumn.getColumnDecimal)
		Assert.assertFalse(abstractColumn.isColumnOptional)
	}

	@Test
	def void testShortOptional() {
		val model = '''
			package eu.jgen.notes.dmw.sample;		
			@entity LogRecord {
				@attr number : Short? @length(4);
			}
		'''.loadLibAndParse
		val attr = model.eAllContents.findFirst [ element |
			element instanceof YAnnotAttr
		] as YAnnotAttr

		model.validate.forEach[println(it)]

		val abstractColumn = attr.converAttributeIntoAbstractColumn
		Assert.assertEquals("NUMBER", abstractColumn.name)
		Assert.assertEquals("SMALLINT", abstractColumn.getColumnTypeName)
		Assert.assertEquals(4, abstractColumn.getColumnLength)
		Assert.assertEquals(0, abstractColumn.getColumnDecimal)
		Assert.assertTrue(abstractColumn.isColumnOptional)
	}

	@Test
	def void testDoubleMandatory() {
		val model = '''
			package eu.jgen.notes.dmw.sample;		
			@entity LogRecord {
				@attr number : Double @decimal(9,2);
			}
		'''.loadLibAndParse
		val attr = model.eAllContents.findFirst [ element |
			element instanceof YAnnotAttr
		] as YAnnotAttr

		model.validate.forEach[println(it)]

		val abstractColumn = attr.converAttributeIntoAbstractColumn
		Assert.assertEquals("NUMBER", abstractColumn.name)
		Assert.assertEquals("DECIMAL", abstractColumn.getColumnTypeName)
		Assert.assertEquals(9, abstractColumn.getColumnLength)
		Assert.assertEquals(2, abstractColumn.getColumnDecimal)
		Assert.assertFalse(abstractColumn.isColumnOptional)
	}

	@Test
	def void testDoubleOptional() {
		val model = '''
			package eu.jgen.notes.dmw.sample;		
			@entity LogRecord {
				@attr number : Double? @decimal(9,2);
			}
		'''.loadLibAndParse
		val attr = model.eAllContents.findFirst [ element |
			element instanceof YAnnotAttr
		] as YAnnotAttr

		model.validate.forEach[println(it)]

		val abstractColumn = attr.converAttributeIntoAbstractColumn
		Assert.assertEquals("NUMBER", abstractColumn.name)
		Assert.assertEquals("DECIMAL", abstractColumn.getColumnTypeName)
		Assert.assertEquals(9, abstractColumn.getColumnLength)
		Assert.assertEquals(2, abstractColumn.getColumnDecimal)
		Assert.assertTrue(abstractColumn.isColumnOptional)
	}

	@Test
	def void testStringMandatory() {
		val model = '''
			package eu.jgen.notes.dmw.sample;		
			@entity LogRecord {
				@attr shortDesc : String @length(20);
			}
		'''.loadLibAndParse
		val attr = model.eAllContents.findFirst [ element |
			element instanceof YAnnotAttr
		] as YAnnotAttr

		model.validate.forEach[println(it)]

		val abstractColumn = attr.converAttributeIntoAbstractColumn
		Assert.assertEquals("SHORT_DESC", abstractColumn.name)
		Assert.assertEquals("CHAR", abstractColumn.getColumnTypeName)
		Assert.assertEquals(20, abstractColumn.getColumnLength)
		Assert.assertFalse(abstractColumn.isColumnOptional)
	}

	@Test
	def void testStringOptional() {
		val model = '''
			package eu.jgen.notes.dmw.sample;		
			@entity LogRecord {
					@attr shortDesc : String? @length(20);
			}
		'''.loadLibAndParse
		val attr = model.eAllContents.findFirst [ element |
			element instanceof YAnnotAttr
		] as YAnnotAttr

		model.validate.forEach[println(it)]

		val abstractColumn = attr.converAttributeIntoAbstractColumn
		Assert.assertEquals("SHORT_DESC", abstractColumn.name)
		Assert.assertEquals("CHAR", abstractColumn.getColumnTypeName)
		Assert.assertEquals(20, abstractColumn.getColumnLength)
		Assert.assertTrue(abstractColumn.isColumnOptional)
	}
	
		@Test
	def void testBlobMandatory() {
		val model = '''
			package eu.jgen.notes.dmw.sample;		
			@entity LogRecord {
				@attr picture : Blob @length(20000);
			}
		'''.loadLibAndParse
		val attr = model.eAllContents.findFirst [ element |
			element instanceof YAnnotAttr
		] as YAnnotAttr

		model.validate.forEach[println(it)]

		val abstractColumn = attr.converAttributeIntoAbstractColumn
		Assert.assertEquals("PICTURE", abstractColumn.name)
		Assert.assertEquals("BLOB", abstractColumn.getColumnTypeName)
		Assert.assertEquals(20000, abstractColumn.getColumnLength)
		Assert.assertFalse(abstractColumn.isColumnOptional)
	}

	@Test
	def void testBlobOptional() {
		val model = '''
			package eu.jgen.notes.dmw.sample;		
			@entity LogRecord {
					@attr picture : Blob? @length(20000);
								}
			}
		'''.loadLibAndParse
		val attr = model.eAllContents.findFirst [ element |
			element instanceof YAnnotAttr
		] as YAnnotAttr

		model.validate.forEach[println(it)]

		val abstractColumn = attr.converAttributeIntoAbstractColumn
		Assert.assertEquals("PICTURE", abstractColumn.name)
		Assert.assertEquals("BLOB", abstractColumn.getColumnTypeName)
		Assert.assertEquals(20000, abstractColumn.getColumnLength)
		Assert.assertTrue(abstractColumn.isColumnOptional)
	}

	@Test
	def void testDateMandatory() {
		val model = '''
			package eu.jgen.notes.dmw.sample;		
			@entity LogRecord {
				@attr updateDate : Date;
			}
		'''.loadLibAndParse
		val attr = model.eAllContents.findFirst [ element |
			element instanceof YAnnotAttr
		] as YAnnotAttr

		model.validate.forEach[println(it)]

		val abstractColumn = attr.converAttributeIntoAbstractColumn
		Assert.assertEquals("UPDATE_DATE", abstractColumn.name)
		Assert.assertEquals("DATE", abstractColumn.getColumnTypeName)
		Assert.assertFalse(abstractColumn.isColumnOptional)
	}

	@Test
	def void testDateOptional() {
		val model = '''
			package eu.jgen.notes.dmw.sample;		
			@entity LogRecord {
				@attr updateDate : Date?;
			}
		'''.loadLibAndParse
		val attr = model.eAllContents.findFirst [ element |
			element instanceof YAnnotAttr
		] as YAnnotAttr

		model.validate.forEach[println(it)]

		val abstractColumn = attr.converAttributeIntoAbstractColumn
		Assert.assertEquals("UPDATE_DATE", abstractColumn.name)
		Assert.assertEquals("DATE", abstractColumn.getColumnTypeName)
		Assert.assertTrue(abstractColumn.isColumnOptional)
	}

	@Test
	def void testTimeMandatory() {
		val model = '''
			package eu.jgen.notes.dmw.sample;		
			@entity LogRecord {
				@attr updateTime : Time;
			}
		'''.loadLibAndParse
		val attr = model.eAllContents.findFirst [ element |
			element instanceof YAnnotAttr
		] as YAnnotAttr

		model.validate.forEach[println(it)]

		val abstractColumn = attr.converAttributeIntoAbstractColumn
		Assert.assertEquals("UPDATE_TIME", abstractColumn.name)
		Assert.assertEquals("TIME", abstractColumn.getColumnTypeName)
		Assert.assertFalse(abstractColumn.isColumnOptional)
	}

	@Test
	def void testTimeOptional() {
		val model = '''
			package eu.jgen.notes.dmw.sample;		
			@entity LogRecord {
				@attr updateTime : Time?;
			}
		'''.loadLibAndParse
		val attr = model.eAllContents.findFirst [ element |
			element instanceof YAnnotAttr
		] as YAnnotAttr

		model.validate.forEach[println(it)]

		val abstractColumn = attr.converAttributeIntoAbstractColumn
		Assert.assertEquals("UPDATE_TIME", abstractColumn.name)
		Assert.assertEquals("TIME", abstractColumn.getColumnTypeName)
		Assert.assertTrue(abstractColumn.isColumnOptional)
	}
	
		@Test
	def void testTimestampMandatory() {
		val model = '''
			package eu.jgen.notes.dmw.sample;		
			@entity LogRecord {
				@attr updateTimestamp : Timestamp;
			}
		'''.loadLibAndParse
		val attr = model.eAllContents.findFirst [ element |
			element instanceof YAnnotAttr
		] as YAnnotAttr

		model.validate.forEach[println(it)]

		val abstractColumn = attr.converAttributeIntoAbstractColumn
		Assert.assertEquals("UPDATE_TIMESTAMP", abstractColumn.name)
		Assert.assertEquals("TIMESTAMP", abstractColumn.getColumnTypeName)
		Assert.assertFalse(abstractColumn.isColumnOptional)
	}

	@Test
	def void testTimestampOptional() {
		val model = '''
			package eu.jgen.notes.dmw.sample;		
			@entity LogRecord {
				@attr updateTimestamp : Timestamp?;
			}
		'''.loadLibAndParse
		val attr = model.eAllContents.findFirst [ element |
			element instanceof YAnnotAttr
		] as YAnnotAttr

		model.validate.forEach[println(it)]

		val abstractColumn = attr.converAttributeIntoAbstractColumn
		Assert.assertEquals("UPDATE_TIMESTAMP", abstractColumn.name)
		Assert.assertEquals("TIMESTAMP", abstractColumn.getColumnTypeName)
		Assert.assertTrue(abstractColumn.isColumnOptional)
	}
	
	def private loadLibAndParse(CharSequence p) {
		val resourceSet = resourceSetProvider.get
		loadLib(resourceSet)
		p.parse(resourceSet)
	}

}
