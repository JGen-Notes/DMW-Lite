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
package  eu.jgen.notes.dmw.lite.tests.inwork

import com.google.inject.Inject
import com.google.inject.Provider
 
import eu.jgen.notes.dmw.lite.lang.YAssignment
import eu.jgen.notes.dmw.lite.lang.YMemberSelection

import eu.jgen.notes.dmw.lite.lang.YReturn
import eu.jgen.notes.dmw.lite.lang.YStatement
import eu.jgen.notes.dmw.lite.lang.YSymbolRef

import eu.jgen.notes.dmw.lite.lang.YWidget
import eu.jgen.notes.dmw.lite.utility.LangLib
import eu.jgen.notes.dmw.lite.utility.LangUtil
import org.eclipse.emf.ecore.resource.ResourceSet
import org.eclipse.xtext.testing.InjectWith
import org.eclipse.xtext.testing.XtextRunner
import org.eclipse.xtext.testing.util.ParseHelper
import org.eclipse.xtext.testing.validation.ValidationTestHelper
import org.junit.Test
import org.junit.runner.RunWith

import static extension org.junit.Assert.*
import eu.jgen.notes.dmw.lite.scoping.LangIndex
import eu.jgen.notes.dmw.lite.tests.LangInjectorProvider

@RunWith(XtextRunner)
@InjectWith(LangInjectorProvider)
class LangParsingTest3 {

	@Inject extension ParseHelper<YWidget> parseHelper
	@Inject extension ValidationTestHelper
	@Inject extension LangUtil
	@Inject extension LangLib
	@Inject extension LangIndex
		@Inject Provider<ResourceSet> rsp

	@Test 
	def void loadModel() {
		val result = loadLibAndParse('''
			class Exits  {  
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
						 
			public	hello() -> String { 
				this.a.number = 1;			 		 
				return ""; 
				}
			}
			
		''')
		//  result.assertNoErrors
		 
		 result.validate.forEach[println(it)]
		 
//		 result.exportedEObjectDescriptions.forEach[println(it)]
//		 
//	 	 result.visibleExternalClassesDescriptions.forEach[p1, p2| println(p1 + " === " + p2)]
	}


//	def private assertAssociativity(YStatement s, CharSequence expected) {
//		expected.toString.assertEquals(s.stringRepr)
//	}

//	def private String stringRepr(YStatement s) {
//		switch (s) {
//			YAssignment: '''(«s.left.stringRepr» = «s.right.stringRepr»)'''
//			YMemberSelection: '''(«s.receiver.stringRepr».«s.member.name»)'''
////			YThis: "this"
////			YNew: '''new «s.type.name»()'''
////			YNull: "null"
//			YSymbolRef: s.symbol.name
//			YReturn: s.expression.stringRepr
//		}
//	}

//	def private assertAccessLevel(YWidget p, int memberIndex, YAccessLevel access) {
//		access.assertEquals(
//			p.classes.last.members.get(memberIndex).access)
//	}
	
		def private loadLibAndParse(CharSequence p) {
		val resourceSet = rsp.get
		loadLib(resourceSet)
		p.parse(resourceSet)
	}

}
