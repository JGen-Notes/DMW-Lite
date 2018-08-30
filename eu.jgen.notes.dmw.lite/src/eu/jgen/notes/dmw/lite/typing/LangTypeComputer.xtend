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
package eu.jgen.notes.dmw.lite.typing

import com.google.inject.Inject


import static extension org.eclipse.xtext.EcoreUtil2.*
import eu.jgen.notes.dmw.lite.lang.LangFactory
import eu.jgen.notes.dmw.lite.lang.YMemberSelection
import eu.jgen.notes.dmw.lite.lang.YReturn
import eu.jgen.notes.dmw.lite.lang.YAssignment
import eu.jgen.notes.dmw.lite.lang.YExpression
import eu.jgen.notes.dmw.lite.lang.YVariableDeclaration
import eu.jgen.notes.dmw.lite.lang.YClass
import eu.jgen.notes.dmw.lite.lang.YIntConstant
import eu.jgen.notes.dmw.lite.lang.LangPackage
import eu.jgen.notes.dmw.lite.utility.LangLib

import eu.jgen.notes.dmw.lite.lang.YStringConstant
import eu.jgen.notes.dmw.lite.lang.YFunction
import eu.jgen.notes.dmw.lite.lang.YSymbolRef
import eu.jgen.notes.dmw.lite.lang.YSuper
import eu.jgen.notes.dmw.lite.lang.YNull
import eu.jgen.notes.dmw.lite.lang.YBoolConstant
import eu.jgen.notes.dmw.lite.lang.YProperty
import eu.jgen.notes.dmw.lite.lang.YSelf
import eu.jgen.notes.dmw.lite.lang.YNot
import eu.jgen.notes.dmw.lite.lang.YMulOrDiv
import eu.jgen.notes.dmw.lite.lang.YMinus
import eu.jgen.notes.dmw.lite.lang.YComparisonExpression
import eu.jgen.notes.dmw.lite.lang.YEqualityExpression
import eu.jgen.notes.dmw.lite.lang.YAndExpression
import eu.jgen.notes.dmw.lite.lang.YOrExpression

class LangTypeComputer {
	
	private static val factory = LangFactory.eINSTANCE
	public static val STRING_TYPE = factory.createYClass => [name = 'stringType']
	public static val INT_TYPE = factory.createYClass => [name = 'intType']
	public static val BOOLEAN_TYPE = factory.createYClass => [name = 'booleanType']
	public static val NULL_TYPE = factory.createYClass => [name = 'nullType']

	static val ep = LangPackage.eINSTANCE

	@Inject extension LangLib

	def YClass typeFor(YExpression epression) {
				
		switch (epression) {
	
			YSymbolRef:
				epression.symbol.type
			YMemberSelection: {
				if(epression.member instanceof YFunction) {
					val method = epression.member as YFunction
					method.type
				} else if(epression.member instanceof YProperty) {
					val property = epression.member as YProperty
					property.type
				} else {
					NULL_TYPE
				}			
			}
				 
			YAssignment:
				epression.left.typeFor
			YSelf:
				epression.getContainerOfType(YClass)
			YSuper:
				epression.getContainerOfType(YClass).getSuperclassOrObject
			YNull:
				NULL_TYPE
			YStringConstant:
				STRING_TYPE
			YIntConstant:
				INT_TYPE
 			YBoolConstant:
 				BOOLEAN_TYPE
 			YNot:
 				BOOLEAN_TYPE
 			YMulOrDiv:
				INT_TYPE
			YMinus:
				INT_TYPE
			YComparisonExpression:
				BOOLEAN_TYPE
			YEqualityExpression:
				BOOLEAN_TYPE
			YAndExpression:
			    BOOLEAN_TYPE
			YOrExpression:
			    BOOLEAN_TYPE

		}
	}

	def getSuperclassOrObject(YClass yclass) {
		yclass.superclass ?: getLangObjectClass(yclass)
	}

	def isPrimitive(YClass yclass) {
		yclass.eResource === null
	}
	def expectedType(YExpression expression) {

		val container = expression.eContainer
		val feature = expression.eContainingFeature
		switch (container) {
			YVariableDeclaration:
				container.type
			YAssignment case feature == ep.YAssignment_Right:
				typeFor(container.left)
			YReturn:
				container.getContainerOfType(YFunction).type
			case feature == ep.YIfStatement_Expression:
				BOOLEAN_TYPE
			YMemberSelection case feature == ep.YMemberSelection_Args: {
				// assume that it refers to a method and that there
				// is a parameter corresponding to the argument
				try {
					(container.member as YFunction).params.get(container.args.indexOf(expression)).type
				} catch (Throwable t) {
					null // otherwise there is no specific expected type
				}
			}
		}
	}
}
