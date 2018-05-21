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
package eu.jgen.notes.dmw.lite.ui.contentassist;

import com.google.common.base.Objects;
import com.google.inject.Inject;
import eu.jgen.notes.dmw.lite.lang.YAnnotAttr;
import eu.jgen.notes.dmw.lite.lang.YAnnotEntity;
import eu.jgen.notes.dmw.lite.lang.YAnnotEntityInner;
import eu.jgen.notes.dmw.lite.lang.YClass;
import eu.jgen.notes.dmw.lite.lang.YMember;
import eu.jgen.notes.dmw.lite.scoping.LangIndex;
import eu.jgen.notes.dmw.lite.ui.contentassist.AbstractLangProposalProvider;
import java.util.ArrayList;
import java.util.Set;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.Assignment;
import org.eclipse.xtext.RuleCall;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.ui.PluginImageHelper;
import org.eclipse.xtext.ui.editor.contentassist.ContentAssistContext;
import org.eclipse.xtext.ui.editor.contentassist.ICompletionProposalAcceptor;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.IterableExtensions;

/**
 * See https://www.eclipse.org/Xtext/documentation/304_ide_concepts.html#content-assist
 * on how to customize the content assistant.
 */
@SuppressWarnings("all")
public class LangProposalProvider extends AbstractLangProposalProvider {
  @Inject
  private PluginImageHelper imageHelper;
  
  @Inject
  @Extension
  private LangIndex _langIndex;
  
  @Override
  public void completeYAnnotEntity_Superannot(final EObject model, final Assignment assignment, final ContentAssistContext context, final ICompletionProposalAcceptor acceptor) {
    final Set<IEObjectDescription> allVisibleEntities = IterableExtensions.<IEObjectDescription>toSet(this._langIndex.getVisibleEntitiesDescriptions(((YAnnotEntity) model)));
    for (final IEObjectDescription element : allVisibleEntities) {
      String _lastSegment = element.getName().getLastSegment();
      String _name = ((YAnnotEntity) model).getName();
      boolean _notEquals = (!Objects.equal(_lastSegment, _name));
      if (_notEquals) {
        acceptor.accept(
          this.createCompletionProposal(element.getName().toString(), element.getName().toString(), 
            this.imageHelper.getImage("entity.gif"), context));
      }
    }
  }
  
  @Override
  public void completeYAnnotAttr_Yclass(final EObject model, final Assignment assignment, final ContentAssistContext context, final ICompletionProposalAcceptor acceptor) {
    acceptor.accept(this.createCompletionProposal("String", "String", this.imageHelper.getImage("class.gif"), context));
    acceptor.accept(this.createCompletionProposal("Int", "Int", this.imageHelper.getImage("class.gif"), context));
    acceptor.accept(this.createCompletionProposal("Short", "Short", this.imageHelper.getImage("class.gif"), context));
    acceptor.accept(this.createCompletionProposal("Double", "Double", this.imageHelper.getImage("class.gif"), context));
    acceptor.accept(this.createCompletionProposal("Boolean", "Boolean", this.imageHelper.getImage("class.gif"), context));
    acceptor.accept(this.createCompletionProposal("Date", "Date", this.imageHelper.getImage("class.gif"), context));
    acceptor.accept(this.createCompletionProposal("Time", "Time", this.imageHelper.getImage("class.gif"), context));
    acceptor.accept(this.createCompletionProposal("Timestamp", "Timestamp", this.imageHelper.getImage("class.gif"), context));
  }
  
  @Override
  public void complete_YProperty(final EObject model, final RuleCall ruleCall, final ContentAssistContext context, final ICompletionProposalAcceptor acceptor) {
    super.complete_YProperty(model, ruleCall, context, acceptor);
  }
  
  @Override
  public void complete_YClass(final EObject model, final RuleCall ruleCall, final ContentAssistContext context, final ICompletionProposalAcceptor acceptor) {
    super.complete_YClass(model, ruleCall, context, acceptor);
  }
  
  @Override
  public void completeYClass_Members(final EObject model, final Assignment assignment, final ContentAssistContext context, final ICompletionProposalAcceptor acceptor) {
    final YClass clazz = ((YClass) model);
    String _name = clazz.getSuperclass().getName();
    boolean _equals = Objects.equal(_name, "Structure");
    if (_equals) {
      YAnnotEntity _entity = clazz.getEntity();
      boolean _tripleNotEquals = (_entity != null);
      if (_tripleNotEquals) {
        this.createAttributeIncludeAll(clazz, acceptor, context);
        this.createAttributeIncludeOne(clazz, acceptor, context);
      }
    } else {
      super.completeYClass_Members(model, assignment, context, acceptor);
    }
  }
  
  protected void createAttributeIncludeAll(final YClass clazz, final ICompletionProposalAcceptor acceptor, final ContentAssistContext context) {
    final ArrayList<String> list = CollectionLiterals.<String>newArrayList();
    EList<YAnnotEntityInner> _annots = clazz.getEntity().getAnnots();
    for (final YAnnotEntityInner annotEntityInner : _annots) {
      if ((annotEntityInner instanceof YAnnotAttr)) {
        final YAnnotAttr annotAttr = ((YAnnotAttr) annotEntityInner);
        boolean _isPropertyAlreadyIncluded = this.isPropertyAlreadyIncluded(clazz, annotAttr.getName());
        boolean _not = (!_isPropertyAlreadyIncluded);
        if (_not) {
          String _name = annotAttr.getName();
          String _plus = ("public var " + _name);
          String _plus_1 = (_plus + " : ");
          String _name_1 = annotAttr.getYclass().getName();
          String _plus_2 = (_plus_1 + _name_1);
          String _plus_3 = (_plus_2 + " -> ");
          String _name_2 = clazz.getEntity().getName();
          String _plus_4 = (_plus_3 + _name_2);
          String _plus_5 = (_plus_4 + ".");
          String _name_3 = annotAttr.getName();
          String _plus_6 = (_plus_5 + _name_3);
          final String line = (_plus_6 + ";");
          list.add(line);
        }
      }
    }
    StringConcatenation _builder = new StringConcatenation();
    {
      for(final String line_1 : list) {
        _builder.append(line_1);
        _builder.newLineIfNotEmpty();
      }
    }
    final String text = _builder.toString();
    acceptor.accept(this.createCompletionProposal(text, "Include All", this.imageHelper.getImage("includeall.gif"), context));
  }
  
  protected void createAttributeIncludeOne(final YClass clazz, final ICompletionProposalAcceptor acceptor, final ContentAssistContext context) {
    EList<YAnnotEntityInner> _annots = clazz.getEntity().getAnnots();
    for (final YAnnotEntityInner annotEntityInner : _annots) {
      if ((annotEntityInner instanceof YAnnotAttr)) {
        final YAnnotAttr annotAttr = ((YAnnotAttr) annotEntityInner);
        boolean _isPropertyAlreadyIncluded = this.isPropertyAlreadyIncluded(clazz, annotAttr.getName());
        boolean _not = (!_isPropertyAlreadyIncluded);
        if (_not) {
          String _name = annotAttr.getName();
          String _plus = ("public var " + _name);
          String _plus_1 = (_plus + " : ");
          String _name_1 = annotAttr.getYclass().getName();
          String _plus_2 = (_plus_1 + _name_1);
          String _plus_3 = (_plus_2 + " -> ");
          String _name_2 = clazz.getEntity().getName();
          String _plus_4 = (_plus_3 + _name_2);
          String _plus_5 = (_plus_4 + ".");
          String _name_3 = annotAttr.getName();
          String _plus_6 = (_plus_5 + _name_3);
          final String line = (_plus_6 + ";");
          String _name_4 = annotAttr.getName();
          String _plus_7 = ("Include Only: " + _name_4);
          acceptor.accept(
            this.createCompletionProposal(line, _plus_7, 
              this.imageHelper.getImage("property.gif"), context));
        }
      }
    }
  }
  
  private boolean isPropertyAlreadyIncluded(final YClass clazz, final String name) {
    EList<YMember> _members = clazz.getMembers();
    for (final YMember member : _members) {
      String _name = member.getName();
      boolean _equals = Objects.equal(_name, name);
      if (_equals) {
        return true;
      }
    }
    return false;
  }
}
