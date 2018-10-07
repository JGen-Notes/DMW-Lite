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
package eu.jgen.notes.dmw.lite.utility

import com.google.inject.Inject
import org.apache.log4j.Logger
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.resource.ResourceSet
import eu.jgen.notes.dmw.lite.scoping.LangIndex
import eu.jgen.notes.dmw.lite.lang.YClass
import eu.jgen.notes.dmw.lite.lang.YAnnotEntity
import com.google.common.io.CharStreams
import java.io.InputStreamReader
import com.google.common.base.Charsets

class LangLib {
	@Inject extension LangIndex

	public val static MAIN_LIB = "eu/jgen/notes/lib/dmw/lang.dmw"

	public val static LIB_PACKAGE = "eu.jgen.notes.lib.dmw"
	public val static LIB_OBJECT = LIB_PACKAGE + ".Object"
	public val static LIB_STRING = LIB_PACKAGE + ".String"
	public val static LIB_INTEGER = LIB_PACKAGE + ".Int"
	public val static LIB_SHORT = LIB_PACKAGE + ".Short"
	public val static LIB_DATE = LIB_PACKAGE + ".Date"
	public val static LIB_TIME = LIB_PACKAGE + ".Time"
	public val static LIB_TIMESTAMP = LIB_PACKAGE + ".Timestamp"
	public val static LIB_BOOL = LIB_PACKAGE + ".Bool"
	public val static LIB_WIDGET = LIB_PACKAGE + ".Widget"
	public val static LIB_EXIT = LIB_PACKAGE + ".Exit"
	private static final Logger LOG = Logger.getLogger(LangLib);

	def String loadLibSource() {
		val url = getClass().getClassLoader().getResource(LangLib.MAIN_LIB)
		val stream = url.openStream
		CharStreams.toString(new InputStreamReader(stream, Charsets.UTF_8));
	}

	def loadLib(ResourceSet resourceSet) {
		val url = getClass().getClassLoader().getResource(MAIN_LIB)
		val stream = url.openStream
		val urlPath = url.path
		val resource = resourceSet.createResource(URI.createFileURI(urlPath))
		LOG.info("loading library " + urlPath)
		resource.load(stream, resourceSet.getLoadOptions())
	}

	def getLangObjectClass(EObject context) {
		val desc = context.getVisibleClassesDescriptions.findFirst[qualifiedName.toString == LIB_OBJECT]
		if (desc === null)
			return null
		var o = desc.getEObjectOrProxy
		if (o.eIsProxy)
			o = context.eResource.resourceSet.getEObject(desc.getEObjectURI, true)
		o as YClass
	}

	/*
	 * for Entities
	 */
	def getLangObjectEntity(EObject context) {
		val desc = context.getVisibleEntityDescriptions.findFirst[qualifiedName.toString == LIB_OBJECT]
		if (desc === null)
			return null
		var o = desc.getEObjectOrProxy
		if (o.eIsProxy)
			o = context.eResource.resourceSet.getEObject(desc.getEObjectURI, true)
		o as YAnnotEntity
	}
}
