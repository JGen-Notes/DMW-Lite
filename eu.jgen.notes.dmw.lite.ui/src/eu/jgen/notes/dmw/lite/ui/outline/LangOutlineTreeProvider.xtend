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
package eu.jgen.notes.dmw.lite.ui.outline

import org.eclipse.xtext.ui.editor.outline.impl.DefaultOutlineTreeProvider
import eu.jgen.notes.dmw.lite.lang.YFunction
import org.eclipse.xtext.ui.editor.outline.impl.DocumentRootNode
import eu.jgen.notes.dmw.lite.lang.YWidget
import eu.jgen.notes.dmw.lite.lang.YClass
import com.google.inject.Inject
import org.eclipse.xtext.ui.PluginImageHelper
import eu.jgen.notes.dmw.lite.lang.YProperty
import eu.jgen.notes.dmw.lite.lang.YImport
import eu.jgen.notes.dmw.lite.lang.YAnnotAttr
import eu.jgen.notes.dmw.lite.lang.YAnnotRel
import eu.jgen.notes.dmw.lite.lang.YAnnotId
import eu.jgen.notes.dmw.lite.lang.YAnnotEntity

/**
 * Customization of the default outline structure.
 * 
 * See https://www.eclipse.org/Xtext/documentation/310_eclipse_support.html#outline
 */
class LangOutlineTreeProvider extends DefaultOutlineTreeProvider {

	@Inject
	private PluginImageHelper imageHelper;

	def _isLeaf(YFunction function) {
		true
	}

	def void _createChildren(DocumentRootNode outlineNode, YWidget widget) {
		widget.eAllContents.filter(YAnnotEntity).forEach [ annotEntity |
			createNode(outlineNode, annotEntity);
			annotEntity.annots.forEach [ annot |
				createNode(outlineNode, annot)
			]
		]
		widget.classes.forEach [ cl |
			createNode(outlineNode, cl);
		]
	}

	def Object _text(YAnnotEntity element) {
		return element.name
	}

	def Object _text(YAnnotAttr element) {
		return element.name + " : " + element.yclass.name
	}
	
	def Object _text(YAnnotRel element) {
		if(element.many) {
			return element.name + " -> " + element.target.name + "*"
		} else {
			return element.name + " -> " + element.target.name
		}		
	}

	def Object _text(YProperty element) {
		var tuple = ""
		if (element.tuples !== null) {
			tuple = "<>"
		}
		return element.name + " : " + element.type.name + tuple
	}
		def Object _text(YFunction element) {
		return element.name + "()"
	}

	def Object _text(YClass element) {
		if (element.superclass !== null && element.superclass.name == "Widget") {
			element.name
		} else if (element.superclass !== null && element.superclass.name == "Structure") {
			if (element.entity !== null) {
				element.name + " -> " + element.entity.name
			} else {
				element.name
			}
		} else {
			return element.name
		}
	}

	def Object _image(YClass element) {
		if (element.superclass !== null && element.superclass.name == "Widget") {
			return imageHelper.getImage("widget.gif")
		} else if (element.superclass !== null && element.superclass.name == "Structure") {
			return imageHelper.getImage("structure.gif")
		} else {
			return imageHelper.getImage("class.gif")
		}
	}

	def Object _image(YAnnotEntity element) {
		return imageHelper.getImage("entity.gif")
	}

	def Object _image(YProperty element) {
		return imageHelper.getImage("property.gif")
	}

	def Object _image(YFunction element) {
		return imageHelper.getImage("function.gif")
	}

	def Object _image(YImport element) {
		return imageHelper.getImage("import.gif")
	}

	def Object _image(YAnnotAttr element) {
		return imageHelper.getImage("attribute.gif")
	}

	def boolean _isLeaf(YAnnotAttr element) {
		return true
	}

	def Object _image(YAnnotRel element) {
		if(element.many) {
			return imageHelper.getImage("onetomany.gif")
		} else {
			return imageHelper.getImage("onetoone.gif")
		}		
	}

	def boolean _isLeaf(YAnnotRel element) {
		return true
	}

	def Object _image(YAnnotId element) {
		return imageHelper.getImage("identifier.gif")
	}

	def boolean _isLeaf(YAnnotId element) {
		return true
	}
}
