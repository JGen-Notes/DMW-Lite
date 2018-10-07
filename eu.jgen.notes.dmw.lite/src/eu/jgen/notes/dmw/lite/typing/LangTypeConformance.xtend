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
import org.eclipse.xtext.naming.IQualifiedNameProvider



import eu.jgen.notes.dmw.lite.utility.LangUtil
import eu.jgen.notes.dmw.lite.lang.YClass
import eu.jgen.notes.dmw.lite.utility.LangLib

import static eu.jgen.notes.dmw.lite.typing.LangTypeComputer.*
class LangTypeConformance {

	@Inject extension LangUtil
	@Inject extension IQualifiedNameProvider

	def isConformant(YClass yclass1, YClass yclass2) {
		yclass1 === NULL_TYPE || // null can be assigned to everything
		yclass1 === yclass2 ||
		yclass2.fullyQualifiedName.toString == LangLib.LIB_OBJECT ||
		conformToLibraryTypes(yclass1, yclass2) ||
		yclass1.isSubclassOf(yclass2)
	}

	def conformToLibraryTypes(YClass yclass1, YClass yclass2) {
		val a = (yclass1.conformsToString && yclass2.conformsToString)||
		(yclass1.conformsToInt && yclass2.conformsToInt) ||
		(yclass1.conformsToBoolean && yclass2.conformsToBoolean)
		return a
	}

	def conformsToString(YClass yclass) {
		yclass == STRING_TYPE || yclass.fullyQualifiedName.toString == LangLib.LIB_STRING
	}

	def conformsToInt(YClass yclass) {
		yclass == INT_TYPE || yclass.fullyQualifiedName.toString == LangLib.LIB_INTEGER
	}

	def conformsToBoolean(YClass yclass) {
		yclass == BOOLEAN_TYPE || yclass.fullyQualifiedName.toString == LangLib.LIB_BOOL
	}

	def isSubclassOf(YClass yclass1, YClass yclass2) {
		yclass1.classHierarchy.contains(yclass2)
	}
}
