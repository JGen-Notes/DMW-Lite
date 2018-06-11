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
package eu.jgen.notes.dmw.lite.scoping;

import com.google.common.base.Objects;
import com.google.common.collect.Iterables;
import com.google.inject.Inject;
import eu.jgen.notes.dmw.lite.lang.LangPackage;
import eu.jgen.notes.dmw.lite.lang.YBlock;
import eu.jgen.notes.dmw.lite.lang.YClass;
import eu.jgen.notes.dmw.lite.lang.YFunction;
import eu.jgen.notes.dmw.lite.lang.YMember;
import eu.jgen.notes.dmw.lite.lang.YMemberSelection;
import eu.jgen.notes.dmw.lite.lang.YProperty;
import eu.jgen.notes.dmw.lite.lang.YStatement;
import eu.jgen.notes.dmw.lite.lang.YVariableDeclaration;
import eu.jgen.notes.dmw.lite.scoping.AbstractLangScopeProvider;
import eu.jgen.notes.dmw.lite.typing.LangTypeComputer;
import eu.jgen.notes.dmw.lite.utility.LangUtil;
import java.util.List;
import java.util.Map;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.xtext.scoping.IScope;
import org.eclipse.xtext.scoping.Scopes;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;

@SuppressWarnings("all")
public class LangScopeProvider extends AbstractLangScopeProvider {
  private final LangPackage epackage = LangPackage.eINSTANCE;
  
  @Inject
  @Extension
  private LangUtil _langUtil;
  
  @Inject
  @Extension
  private LangTypeComputer _langTypeComputer;
  
  @Override
  public IScope getScope(final EObject context, final EReference reference) {
    EReference _ySymbolRef_Symbol = this.epackage.getYSymbolRef_Symbol();
    boolean _equals = Objects.equal(reference, _ySymbolRef_Symbol);
    if (_equals) {
      return this.scopeForSymbolRef(context);
    } else {
      if ((context instanceof YMemberSelection)) {
        return this.scopeForMemberSelection(((YMemberSelection)context), reference);
      }
    }
    return super.getScope(context, reference);
  }
  
  protected IScope scopeForSymbolRef(final EObject context) {
    final EObject container = context.eContainer();
    IScope _switchResult = null;
    boolean _matched = false;
    if (container instanceof YFunction) {
      _matched=true;
      _switchResult = Scopes.scopeFor(((YFunction)container).getParams());
    }
    if (!_matched) {
      if (container instanceof YBlock) {
        _matched=true;
        final Function1<YStatement, Boolean> _function = (YStatement it) -> {
          return Boolean.valueOf((!Objects.equal(it, context)));
        };
        _switchResult = Scopes.scopeFor(
          Iterables.<YVariableDeclaration>filter(IterableExtensions.<YStatement>takeWhile(((YBlock)container).getStatements(), _function), YVariableDeclaration.class), 
          this.scopeForSymbolRef(container));
      }
    }
    if (!_matched) {
      _switchResult = this.scopeForSymbolRef(container);
    }
    return _switchResult;
  }
  
  protected IScope scopeForMemberSelection(final YMemberSelection selection, final EReference reference) {
    final YClass type = this._langTypeComputer.typeFor(selection.getReceiver());
    if (((type == null) || this._langTypeComputer.isPrimitive(type))) {
      return IScope.NULLSCOPE;
    }
    final Function1<YMember, Boolean> _function = (YMember it) -> {
      return Boolean.valueOf((it instanceof YFunction));
    };
    final Map<Boolean, List<YMember>> grouped = IterableExtensions.<Boolean, YMember>groupBy(this._langUtil.classHierarchyMembers(type), _function);
    List<YMember> _elvis = null;
    List<YMember> _get = grouped.get(Boolean.valueOf(true));
    if (_get != null) {
      _elvis = _get;
    } else {
      List<YMember> _emptyList = CollectionLiterals.<YMember>emptyList();
      _elvis = _emptyList;
    }
    final List<YMember> inheritedMethods = _elvis;
    List<YMember> _elvis_1 = null;
    List<YMember> _get_1 = grouped.get(Boolean.valueOf(false));
    if (_get_1 != null) {
      _elvis_1 = _get_1;
    } else {
      List<YMember> _emptyList_1 = CollectionLiterals.<YMember>emptyList();
      _elvis_1 = _emptyList_1;
    }
    final List<YMember> inheritedFields = _elvis_1;
    boolean _isFunctioninvocation = selection.isFunctioninvocation();
    if (_isFunctioninvocation) {
      Iterable<YFunction> _functions = this._langUtil.functions(type);
      Iterable<YProperty> _properties = this._langUtil.properties(type);
      Iterable<YMember> _plus = Iterables.<YMember>concat(_functions, _properties);
      Iterable<YMember> _plus_1 = Iterables.<YMember>concat(inheritedMethods, inheritedFields);
      return Scopes.scopeFor(_plus, 
        Scopes.scopeFor(_plus_1));
    } else {
      Iterable<YProperty> _properties_1 = this._langUtil.properties(type);
      Iterable<YFunction> _functions_1 = this._langUtil.functions(type);
      Iterable<YMember> _plus_2 = Iterables.<YMember>concat(_properties_1, _functions_1);
      Iterable<YMember> _plus_3 = Iterables.<YMember>concat(inheritedFields, inheritedMethods);
      return Scopes.scopeFor(_plus_2, 
        Scopes.scopeFor(_plus_3));
    }
  }
}
