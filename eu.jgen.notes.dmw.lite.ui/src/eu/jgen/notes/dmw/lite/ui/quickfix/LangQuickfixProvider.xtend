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
package eu.jgen.notes.dmw.lite.ui.quickfix

import org.eclipse.xtext.ui.editor.quickfix.DefaultQuickfixProvider
import org.eclipse.xtext.ui.editor.quickfix.Fix
import eu.jgen.notes.dmw.lite.validation.LangValidator
import org.eclipse.xtext.ui.editor.quickfix.IssueResolutionAcceptor
import org.eclipse.xtext.validation.Issue
import eu.jgen.notes.dmw.lite.lang.LangPackage
import eu.jgen.notes.dmw.lite.lang.YAnnotEntity
import eu.jgen.notes.dmw.lite.lang.YAnnotAttr
import eu.jgen.notes.dmw.lite.lang.YAnnotRel
import eu.jgen.notes.dmw.lite.lang.YAnnotId
import com.google.inject.Inject
import eu.jgen.notes.dmw.lite.utility.LangUtil
import eu.jgen.notes.dmw.lite.utility.LangDBUtil

/**
 * Custom quickfixes.
 *
 * See https://www.eclipse.org/Xtext/documentation/310_eclipse_support.html#quick-fixes
 */
class LangQuickfixProvider extends DefaultQuickfixProvider {
	
		@Inject extension LangDBUtil
		@Inject extension LangUtil
		
	@Fix(LangValidator.ENTITY_NO_TECH_DESIGN)
	def createTableForEntityType(Issue issue, IssueResolutionAcceptor acceptor) {
		acceptor.accept(issue, 'Create missing table', 'Creates table implementing entity type.', 'table.gif') [ element, context |
			val technicalDesign = getTechnicalDesign(element, LangPackage.Literals.YANNOT_TECHNICAL_DESIGN)
			val entity =element as  YAnnotEntity
			technicalDesign.features.add(
				converEntityIntoTable(entity)
			)
			technicalDesign.eContainer.eResource.save(null)
		]
	}

	@Fix(LangValidator.ATTRIBUTE_NO_TECH_DESIGN)
	def createColumnForAttributeType(Issue issue, IssueResolutionAcceptor acceptor) {
		acceptor.accept(issue, 'Create missing column', 'Creates column implementing attribute type.', 'column.gif') [ element, context |
			if (element instanceof YAnnotAttr) {
				val attribute = element as YAnnotAttr
				val table = getImplementingTable(attribute.eContainer as YAnnotEntity)
				val abstractColumn = attribute.converAttributeIntoAbstractColumn
				table.columns.add(abstractColumn)
				table.eContainer.eResource.save(null)
			}
		]
	}

	@Fix(LangValidator.RELATIONSSHIP_NOT_IMPLEMENTED)
	def createFKColumnForAttributeType(Issue issue, IssueResolutionAcceptor acceptor) {
		acceptor.accept(issue, 'Create missing foreign key', 'Creates foreign key column implementing realtionship.',
			'relationship.png') [ element, context |
			if (element instanceof YAnnotRel) {
				val relationship = element as YAnnotRel
				val table = getImplementingTable(relationship.eContainer as YAnnotEntity)
				val foreignKey = relationship.converRelationshipIntoForeignKeys
				table.foreignkeys.add(foreignKey)
				table.eContainer.eResource.save(null)
			}
		]
	}

	@Fix(LangValidator.IDENTIFIER_NO_TECH_DESIGN)
	def createPrimaryKeyForIdentifier(Issue issue, IssueResolutionAcceptor acceptor) {
		acceptor.accept(issue, 'Create missing primary key', 'Creates primary key implementing identifier.',
			'identifier.gif') [ element, context |
			if (element instanceof YAnnotId) {
				val identifier = element as YAnnotId
				val table = getImplementingTable(identifier.eContainer as YAnnotEntity)
				val primaryKey = identifier.converIdentifierIntoPrimaryKey
				table.primarykey = primaryKey
				table.eContainer.eResource.save(null)
			}
		]
	}

}
