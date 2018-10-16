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
import eu.jgen.notes.dmw.lite.lang.YAnnotRel;
import eu.jgen.notes.dmw.lite.lang.YClass;
import eu.jgen.notes.dmw.lite.lang.YMember;
import eu.jgen.notes.dmw.lite.ui.contentassist.AbstractLangProposalProvider;
import java.util.ArrayList;
import java.util.function.Consumer;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.Assignment;
import org.eclipse.xtext.RuleCall;
import org.eclipse.xtext.ui.PluginImageHelper;
import org.eclipse.xtext.ui.editor.contentassist.ContentAssistContext;
import org.eclipse.xtext.ui.editor.contentassist.ICompletionProposalAcceptor;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;

@SuppressWarnings("all")
public class LangProposalProvider extends AbstractLangProposalProvider {
  @Inject
  private PluginImageHelper imageHelper;
  
  @Override
  public void completeYAnnotDatabase_Name(final EObject model, final Assignment assignment, final ContentAssistContext context, final ICompletionProposalAcceptor acceptor) {
    acceptor.accept(this.createCompletionProposal("Derby", "Derby", this.imageHelper.getImage("database.gif"), context));
    acceptor.accept(this.createCompletionProposal("MySQL", "MySQL", this.imageHelper.getImage("database.gif"), context));
    acceptor.accept(this.createCompletionProposal("SQLite", "SQLite", this.imageHelper.getImage("database.gif"), context));
    acceptor.accept(this.createCompletionProposal("PostgreSQL", "PostgreSQL", this.imageHelper.getImage("database.gif"), context));
    acceptor.accept(this.createCompletionProposal("MongoDB", "MongoDB", this.imageHelper.getImage("database.gif"), context));
  }
  
  @Override
  public void completeYAnnotAttr_Yclass(final EObject model, final Assignment assignment, final ContentAssistContext context, final ICompletionProposalAcceptor acceptor) {
    acceptor.accept(this.createCompletionProposal("XString", "XString", this.imageHelper.getImage("class.gif"), context));
    acceptor.accept(this.createCompletionProposal("XShort", "XShort", this.imageHelper.getImage("class.gif"), context));
    acceptor.accept(this.createCompletionProposal("XInt", "XInt", this.imageHelper.getImage("class.gif"), context));
    acceptor.accept(this.createCompletionProposal("XLong", "XLong", this.imageHelper.getImage("class.gif"), context));
    acceptor.accept(this.createCompletionProposal("XDouble", "XDouble", this.imageHelper.getImage("class.gif"), context));
    acceptor.accept(this.createCompletionProposal("XBool", "XBool", this.imageHelper.getImage("class.gif"), context));
    acceptor.accept(this.createCompletionProposal("XDate", "XDate", this.imageHelper.getImage("class.gif"), context));
    acceptor.accept(this.createCompletionProposal("XTime", "XTime", this.imageHelper.getImage("class.gif"), context));
    acceptor.accept(this.createCompletionProposal("XTimestamp", "XTimestamp", this.imageHelper.getImage("class.gif"), context));
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
  public void completeYAnnotRel_Inverse(final EObject object, final Assignment assignment, final ContentAssistContext context, final ICompletionProposalAcceptor acceptor) {
    final YAnnotRel annotRel = ((YAnnotRel) object);
    final Consumer<YAnnotEntityInner> _function = (YAnnotEntityInner element) -> {
      if ((element instanceof YAnnotRel)) {
        final YAnnotRel targetAnnotRel = ((YAnnotRel) element);
        YAnnotRel _inverse = targetAnnotRel.getInverse();
        boolean _tripleNotEquals = (_inverse != null);
        if (_tripleNotEquals) {
          EObject _eContainer = targetAnnotRel.eContainer();
          final String targetEnntityName = ((YAnnotEntity) _eContainer).getName();
          String _name = annotRel.getTarget().getName();
          boolean _equals = Objects.equal(targetEnntityName, _name);
          if (_equals) {
            String _name_1 = annotRel.getTarget().getName();
            String _plus = (_name_1 + ".");
            String _name_2 = targetAnnotRel.getName();
            final String proposal = (_plus + _name_2);
            acceptor.accept(this.createCompletionProposal(proposal, proposal, this.imageHelper.getImage("relationship.gif"), context));
            return;
          }
        }
        if (((targetAnnotRel.getInverse() == null) && (targetAnnotRel != annotRel))) {
          String _name_3 = annotRel.getTarget().getName();
          String _plus_1 = (_name_3 + ".");
          String _name_4 = targetAnnotRel.getName();
          final String proposal_1 = (_plus_1 + _name_4);
          acceptor.accept(this.createCompletionProposal(proposal_1, proposal_1, this.imageHelper.getImage("relationship.gif"), context));
        }
      }
    };
    annotRel.getTarget().getAnnots().forEach(_function);
  }
  
  protected void createAttributeIncludeAll(final YClass clazz, final ICompletionProposalAcceptor acceptor, final ContentAssistContext context) {
    final ArrayList<String> list = CollectionLiterals.<String>newArrayList();
    EList<YAnnotEntityInner> _annots = clazz.getEntityRef().getAnnots();
    for (final YAnnotEntityInner annotEntityInner : _annots) {
      if ((annotEntityInner instanceof YAnnotAttr)) {
        final YAnnotAttr annotAttr = ((YAnnotAttr) annotEntityInner);
        boolean _isPropertyAlreadyIncluded = this.isPropertyAlreadyIncluded(clazz, annotAttr.getName());
        boolean _not = (!_isPropertyAlreadyIncluded);
        if (_not) {
          String _name = annotAttr.getName();
          String _plus = ("public var " + _name);
          String _plus_1 = (_plus + " : ");
          String _qualifiedName = annotAttr.getYclass().getQualifiedName();
          String _plus_2 = (_plus_1 + _qualifiedName);
          String _plus_3 = (_plus_2 + " -> ");
          String _name_1 = clazz.getEntityRef().getName();
          String _plus_4 = (_plus_3 + _name_1);
          String _plus_5 = (_plus_4 + ".");
          String _name_2 = annotAttr.getName();
          String _plus_6 = (_plus_5 + _name_2);
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
    EList<YAnnotEntityInner> _annots = clazz.getEntityRef().getAnnots();
    for (final YAnnotEntityInner annotEntityInner : _annots) {
      if ((annotEntityInner instanceof YAnnotAttr)) {
        final YAnnotAttr annotAttr = ((YAnnotAttr) annotEntityInner);
        boolean _isPropertyAlreadyIncluded = this.isPropertyAlreadyIncluded(clazz, annotAttr.getName());
        boolean _not = (!_isPropertyAlreadyIncluded);
        if (_not) {
          String _name = annotAttr.getName();
          String _plus = ("public var " + _name);
          String _plus_1 = (_plus + " : ");
          String _qualifiedName = annotAttr.getYclass().getQualifiedName();
          String _plus_2 = (_plus_1 + _qualifiedName);
          String _plus_3 = (_plus_2 + " -> ");
          String _name_1 = clazz.getEntityRef().getName();
          String _plus_4 = (_plus_3 + _name_1);
          String _plus_5 = (_plus_4 + ".");
          String _name_2 = annotAttr.getName();
          String _plus_6 = (_plus_5 + _name_2);
          final String line = (_plus_6 + ";");
          String _name_3 = annotAttr.getName();
          String _plus_7 = ("Include Only: " + _name_3);
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
