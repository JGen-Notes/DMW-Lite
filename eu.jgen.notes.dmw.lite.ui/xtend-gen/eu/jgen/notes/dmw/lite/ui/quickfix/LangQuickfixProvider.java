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
 */
package eu.jgen.notes.dmw.lite.ui.quickfix;

import com.google.inject.Inject;
import eu.jgen.notes.dmw.lite.lang.LangPackage;
import eu.jgen.notes.dmw.lite.lang.YAnnotAbstractColumn;
import eu.jgen.notes.dmw.lite.lang.YAnnotAttr;
import eu.jgen.notes.dmw.lite.lang.YAnnotEntity;
import eu.jgen.notes.dmw.lite.lang.YAnnotEntityInner;
import eu.jgen.notes.dmw.lite.lang.YAnnotForeignKey;
import eu.jgen.notes.dmw.lite.lang.YAnnotId;
import eu.jgen.notes.dmw.lite.lang.YAnnotPrimaryKey;
import eu.jgen.notes.dmw.lite.lang.YAnnotRel;
import eu.jgen.notes.dmw.lite.lang.YAnnotTable;
import eu.jgen.notes.dmw.lite.lang.YAnnotTechnicalDesign;
import eu.jgen.notes.dmw.lite.lang.YClass;
import eu.jgen.notes.dmw.lite.utility.LangDBUtil;
import eu.jgen.notes.dmw.lite.utility.LangUtil;
import eu.jgen.notes.dmw.lite.validation.LangValidator;
import java.util.function.Consumer;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.ui.editor.model.edit.IModificationContext;
import org.eclipse.xtext.ui.editor.model.edit.ISemanticModification;
import org.eclipse.xtext.ui.editor.quickfix.DefaultQuickfixProvider;
import org.eclipse.xtext.ui.editor.quickfix.Fix;
import org.eclipse.xtext.ui.editor.quickfix.IssueResolutionAcceptor;
import org.eclipse.xtext.validation.Issue;
import org.eclipse.xtext.xbase.lib.Extension;

@SuppressWarnings("all")
public class LangQuickfixProvider extends DefaultQuickfixProvider {
  @Inject
  @Extension
  private LangDBUtil _langDBUtil;
  
  @Inject
  @Extension
  private LangUtil _langUtil;
  
  @Fix(LangValidator.ENTITY_NO_TECH_DESIGN)
  public void createTableForEntityType(final Issue issue, final IssueResolutionAcceptor acceptor) {
    final ISemanticModification _function = (EObject element, IModificationContext context) -> {
      final YAnnotTechnicalDesign technicalDesign = this._langUtil.getTechnicalDesign(element, LangPackage.Literals.YANNOT_TECHNICAL_DESIGN);
      final YAnnotEntity entity = ((YAnnotEntity) element);
      technicalDesign.getFeatures().add(
        this._langDBUtil.converEntityIntoTable(entity));
    };
    acceptor.accept(issue, "Create missing table", "Creates table implementing entity type.", "table.gif", _function);
  }
  
  @Fix(LangValidator.ATTRIBUTE_NO_TECH_DESIGN)
  public void createColumnForAttributeType(final Issue issue, final IssueResolutionAcceptor acceptor) {
    final ISemanticModification _function = (EObject element, IModificationContext context) -> {
      if ((element instanceof YAnnotAttr)) {
        final YAnnotAttr attribute = ((YAnnotAttr) element);
        EObject _eContainer = attribute.eContainer();
        final YAnnotTable table = this._langUtil.getImplementingTable(((YAnnotEntity) _eContainer));
        final YAnnotAbstractColumn abstractColumn = this._langDBUtil.converAttributeIntoAbstractColumn(attribute);
        table.getColumns().add(abstractColumn);
      }
    };
    acceptor.accept(issue, "Create missing column", "Creates column implementing attribute type.", "column.gif", _function);
  }
  
  @Fix(LangValidator.RELATIONSSHIP_NOT_IMPLEMENTED)
  public void createFKColumnForAttributeType(final Issue issue, final IssueResolutionAcceptor acceptor) {
    final ISemanticModification _function = (EObject element, IModificationContext context) -> {
      if ((element instanceof YAnnotRel)) {
        final YAnnotRel relationship = ((YAnnotRel) element);
        EObject _eContainer = relationship.eContainer();
        final YAnnotTable table = this._langUtil.getImplementingTable(((YAnnotEntity) _eContainer));
        final YAnnotForeignKey foreignKey = this._langDBUtil.converRelationshipIntoForeignKeys(relationship);
        table.getForeignkeys().add(foreignKey);
      }
    };
    acceptor.accept(issue, "Create missing foreign key", "Adds foreign key column implementing relationship.", 
      "relationship.png", _function);
  }
  
  @Fix(LangValidator.IDENTIFIER_NO_TECH_DESIGN)
  public void createPrimaryKeyForIdentifier(final Issue issue, final IssueResolutionAcceptor acceptor) {
    final ISemanticModification _function = (EObject element, IModificationContext context) -> {
      if ((element instanceof YAnnotId)) {
        final YAnnotId identifier = ((YAnnotId) element);
        EObject _eContainer = identifier.eContainer();
        final YAnnotTable table = this._langUtil.getImplementingTable(((YAnnotEntity) _eContainer));
        final YAnnotPrimaryKey primaryKey = this._langDBUtil.converIdentifierIntoPrimaryKey(identifier);
        table.setPrimarykey(primaryKey);
      }
    };
    acceptor.accept(issue, "Create missing primary key", "Creates primary key implementing identifier.", 
      "identifier.gif", _function);
  }
  
  @Fix(LangValidator.TABLE_DOES_NOT_HAVE_COLUMNS)
  public void addingColumnToTable(final Issue issue, final IssueResolutionAcceptor acceptor) {
    final ISemanticModification _function = (EObject element, IModificationContext context) -> {
      if (((element instanceof YAnnotTable) && (((YAnnotTable) element).getEntityref() != null))) {
        final YAnnotTable table = ((YAnnotTable) element);
        final EList<YAnnotEntityInner> list = ((YAnnotTable) element).getEntityref().getAnnots();
        final Consumer<YAnnotEntityInner> _function_1 = (YAnnotEntityInner entityInner) -> {
          if ((entityInner instanceof YAnnotAttr)) {
            final YAnnotAttr attribute = ((YAnnotAttr) entityInner);
            final YAnnotAbstractColumn abstractColumn = this._langDBUtil.converAttributeIntoAbstractColumn(attribute);
            table.getColumns().add(abstractColumn);
          }
        };
        list.forEach(_function_1);
      }
    };
    acceptor.accept(issue, "Add all missing columns using list of attributes", 
      "Add all missing columns using related list of attributes", "column.gif", _function);
  }
  
  @Fix(LangValidator.CLASS_NEED_TO_HAVE_PROPERTIES)
  public void addingPropertiesToStructure(final Issue issue, final IssueResolutionAcceptor acceptor) {
    final ISemanticModification _function = (EObject element, IModificationContext context) -> {
      if (((element instanceof YClass) && (((YClass) element).getEntityRef() != null))) {
        final YClass clazz = ((YClass) element);
        final EList<YAnnotEntityInner> list = ((YClass) element).getEntityRef().getAnnots();
        final Consumer<YAnnotEntityInner> _function_1 = (YAnnotEntityInner entityInner) -> {
        };
        list.forEach(_function_1);
      }
    };
    acceptor.accept(issue, "Add all missing properties using list of attributes as public", 
      "Add all missing properties using list of attributes", "property.gif", _function);
  }
}
