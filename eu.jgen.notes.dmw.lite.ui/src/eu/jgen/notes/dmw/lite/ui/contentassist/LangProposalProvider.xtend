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
package eu.jgen.notes.dmw.lite.ui.contentassist

import com.google.inject.Inject
import eu.jgen.notes.dmw.lite.lang.YAnnotAttr
import eu.jgen.notes.dmw.lite.lang.YClass
import org.eclipse.emf.ecore.EObject
import org.eclipse.xtext.Assignment
import org.eclipse.xtext.RuleCall
import org.eclipse.xtext.ui.PluginImageHelper
import org.eclipse.xtext.ui.editor.contentassist.ContentAssistContext
import org.eclipse.xtext.ui.editor.contentassist.ICompletionProposalAcceptor
import eu.jgen.notes.dmw.lite.lang.YAnnotEntity
import eu.jgen.notes.dmw.lite.scoping.LangIndex

/**
 * See https://www.eclipse.org/Xtext/documentation/304_ide_concepts.html#content-assist
 * on how to customize the content assistant.
 */
class LangProposalProvider extends AbstractLangProposalProvider {

	@Inject
	private PluginImageHelper imageHelper;
	@Inject extension LangIndex

	override completeYAnnotEntity_Superannot(EObject model, Assignment assignment, ContentAssistContext context,
		ICompletionProposalAcceptor acceptor) {
		val allVisibleEntities = (model as YAnnotEntity).getVisibleEntitiesDescriptions.toSet
		for (element : allVisibleEntities) {
			if (element.name.lastSegment != (model as YAnnotEntity).name) {
				acceptor.accept(
					createCompletionProposal(element.name.toString, element.name.toString,
						imageHelper.getImage("entity.gif"), context))
			}
		}
	}

	override completeYAnnotAttr_Yclass(EObject model, Assignment assignment, ContentAssistContext context,
		ICompletionProposalAcceptor acceptor) {
		acceptor.accept(createCompletionProposal("String", "String", imageHelper.getImage("class.gif"), context))
		acceptor.accept(createCompletionProposal("Int", "Int", imageHelper.getImage("class.gif"), context))
		acceptor.accept(createCompletionProposal("Short", "Short", imageHelper.getImage("class.gif"), context))
		acceptor.accept(createCompletionProposal("Double", "Double", imageHelper.getImage("class.gif"), context))
		acceptor.accept(createCompletionProposal("Boolean", "Boolean", imageHelper.getImage("class.gif"), context))
		acceptor.accept(createCompletionProposal("Date", "Date", imageHelper.getImage("class.gif"), context))
		acceptor.accept(createCompletionProposal("Time", "Time", imageHelper.getImage("class.gif"), context))
		acceptor.accept(createCompletionProposal("Timestamp", "Timestamp", imageHelper.getImage("class.gif"), context))
	}

	override complete_YProperty(EObject model, RuleCall ruleCall, ContentAssistContext context,
		ICompletionProposalAcceptor acceptor) {
		super.complete_YProperty(model, ruleCall, context, acceptor)
	}

	override complete_YClass(EObject model, RuleCall ruleCall, ContentAssistContext context,
		ICompletionProposalAcceptor acceptor) {
		super.complete_YClass(model, ruleCall, context, acceptor)
	}

	override completeYClass_Members(EObject model, Assignment assignment, ContentAssistContext context,
		ICompletionProposalAcceptor acceptor) {
		val clazz = model as YClass
		if (clazz.superclass.name == "Structure") {
			if (clazz.entity !== null) {
				createAttributeIncludeAll(clazz, acceptor, context)
				createAttributeIncludeOne(clazz, acceptor, context)
			}
		} else {
			super.completeYClass_Members(model, assignment, context, acceptor)
		}
	}

	protected def void createAttributeIncludeAll(YClass clazz, ICompletionProposalAcceptor acceptor,
		ContentAssistContext context) {
		val list = newArrayList()
		for (annotEntityInner : clazz.entity.annots) {
			if (annotEntityInner instanceof YAnnotAttr) {
				val annotAttr = annotEntityInner as YAnnotAttr
				if (!isPropertyAlreadyIncluded(clazz, annotAttr.name)) {
					val line = "public var " + annotAttr.name + " : " + annotAttr.yclass.name + " -> " +
						clazz.entity.name + "." + annotAttr.name + ";"
					list.add(line)
				}
			}
		}
		val text = '''
			«FOR line : list»
				«line»
			«ENDFOR»
		'''
		acceptor.accept(createCompletionProposal(text, "Include All", imageHelper.getImage("includeall.gif"), context))
	}

	protected def void createAttributeIncludeOne(YClass clazz, ICompletionProposalAcceptor acceptor,
		ContentAssistContext context) {
		for (annotEntityInner : clazz.entity.annots) {
			if (annotEntityInner instanceof YAnnotAttr) {
				val annotAttr = annotEntityInner as YAnnotAttr
				if (!isPropertyAlreadyIncluded(clazz, annotAttr.name)) {
					val line = "public var " + annotAttr.name + " : " + annotAttr.yclass.name + " -> " +
						clazz.entity.name + "." + annotAttr.name + ";"
					acceptor.accept(
						createCompletionProposal(line, "Include Only: " + annotAttr.name,
							imageHelper.getImage("property.gif"), context))
				}
			}
		}
	}

	def private boolean isPropertyAlreadyIncluded(YClass clazz, String name) {
		for (member : clazz.members) {
			if (member.name == name) {
				return true
			}
		}
		return false
	}

}
