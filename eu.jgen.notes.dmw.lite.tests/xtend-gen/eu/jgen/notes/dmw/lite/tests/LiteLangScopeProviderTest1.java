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
package eu.jgen.notes.dmw.lite.tests;

import com.google.inject.Inject;
import eu.jgen.notes.dmw.lite.lang.YFunction;
import eu.jgen.notes.dmw.lite.lang.YWidget;
import eu.jgen.notes.dmw.lite.tests.LangInjectorProvider;
import eu.jgen.notes.dmw.lite.utility.LangUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.scoping.IScopeProvider;
import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.XtextRunner;
import org.eclipse.xtext.testing.util.ParseHelper;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(XtextRunner.class)
@InjectWith(LangInjectorProvider.class)
@SuppressWarnings("all")
public class LiteLangScopeProviderTest1 {
  @Inject
  @Extension
  private ParseHelper<YWidget> _parseHelper;
  
  @Inject
  @Extension
  private IScopeProvider _iScopeProvider;
  
  @Inject
  @Extension
  private LangUtil _langUtil;
  
  @Test
  public void testScopeProvider() {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method or field returnStatement is undefined for the type YFunction"
      + "\nThe method or field YMemberSelection_Member is undefined for the type LangPackage"
      + "\nThe method or field YSymbolRef_Symbol is undefined for the type LangPackage"
      + "\nexpression cannot be resolved"
      + "\n=> cannot be resolved");
  }
  
  @Test
  public void testScopeProviderForSymbols() {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method or field YVariableDeclaration is undefined"
      + "\nThe method or field expression is undefined for the type EObject"
      + "\nThe method or field name is undefined"
      + "\nThe method or field YSymbolRef_Symbol is undefined for the type LangPackage"
      + "\nThe method or field expression is undefined for the type EObject"
      + "\nThe method or field name is undefined"
      + "\nThe method or field YSymbolRef_Symbol is undefined for the type LangPackage"
      + "\n== cannot be resolved"
      + "\nassertScope cannot be resolved"
      + "\n== cannot be resolved"
      + "\nassertScope cannot be resolved");
  }
  
  @Test
  public void testVariableShadowsParamScoping() {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method or field returnStatement is undefined"
      + "\nThe method or field YSymbolRef_Symbol is undefined for the type LangPackage"
      + "\nexpression cannot be resolved"
      + "\nassertScope cannot be resolved");
  }
  
  @Test
  public void testVariableShadowsParamLinking() {
    throw new Error("Unresolved compilation problems:"
      + "\nYSymbolRef cannot be resolved to a type."
      + "\nThe method or field statements is undefined for the type XExpression"
      + "\nThe method or field returnStatement is undefined"
      + "\nhead cannot be resolved"
      + "\nassertSame cannot be resolved"
      + "\nexpression cannot be resolved"
      + "\nsymbol cannot be resolved");
  }
  
  @Test
  public void testFieldsScoping() {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method or field returnStatement is undefined for the type YFunction"
      + "\nThe method or field YMemberSelection_Member is undefined for the type LangPackage"
      + "\nexpression cannot be resolved"
      + "\nassertScope cannot be resolved");
  }
  
  @Test
  public void testMethodsScoping() {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method or field returnStatement is undefined for the type YFunction"
      + "\nThe method or field YMemberSelection_Member is undefined for the type LangPackage"
      + "\nexpression cannot be resolved"
      + "\nassertScope cannot be resolved");
  }
  
  @Test
  public void testFieldScoping() {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method returnExpSel(YFunction) from the type LiteLangScopeProviderTest1 refers to the missing type YMemberSelection"
      + "\nThe method returnExpSel(YFunction) from the type LiteLangScopeProviderTest1 refers to the missing type YMemberSelection"
      + "\nThe method returnExpSel(YFunction) from the type LiteLangScopeProviderTest1 refers to the missing type YMemberSelection"
      + "\nThe method returnExpSel(YFunction) from the type LiteLangScopeProviderTest1 refers to the missing type YMemberSelection"
      + "\nThe method returnExpSel(YFunction) from the type LiteLangScopeProviderTest1 refers to the missing type YMemberSelection"
      + "\nThe method returnExpSel(YFunction) from the type LiteLangScopeProviderTest1 refers to the missing type YMemberSelection"
      + "\nmember cannot be resolved"
      + "\nmember cannot be resolved"
      + "\nmember cannot be resolved");
  }
  
  @Test
  public void testMethodScoping() {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method returnExpSel(YFunction) from the type LiteLangScopeProviderTest1 refers to the missing type YMemberSelection"
      + "\nThe method returnExpSel(YFunction) from the type LiteLangScopeProviderTest1 refers to the missing type YMemberSelection"
      + "\nThe method returnExpSel(YFunction) from the type LiteLangScopeProviderTest1 refers to the missing type YMemberSelection"
      + "\nThe method returnExpSel(YFunction) from the type LiteLangScopeProviderTest1 refers to the missing type YMemberSelection"
      + "\nThe method returnExpSel(YFunction) from the type LiteLangScopeProviderTest1 refers to the missing type YMemberSelection"
      + "\nThe method returnExpSel(YFunction) from the type LiteLangScopeProviderTest1 refers to the missing type YMemberSelection"
      + "\nmember cannot be resolved"
      + "\nmember cannot be resolved"
      + "\nmember cannot be resolved");
  }
  
  @Test
  public void testMemberScopeWithUnresolvedReceiver() {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method or field returnStatement is undefined for the type YFunction"
      + "\nThe method or field YMemberSelection_Member is undefined for the type LangPackage"
      + "\nexpression cannot be resolved"
      + "\n=> cannot be resolved");
  }
  
  @Test
  public void testFieldsAndMethodsWithTheSameName() {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method returnExpSel(YFunction) from the type LiteLangScopeProviderTest1 refers to the missing type YMemberSelection"
      + "\nThe method returnExpSel(YFunction) from the type LiteLangScopeProviderTest1 refers to the missing type YMemberSelection"
      + "\nThe method returnExpSel(YFunction) from the type LiteLangScopeProviderTest1 refers to the missing type YMemberSelection"
      + "\nThe method returnExpSel(YFunction) from the type LiteLangScopeProviderTest1 refers to the missing type YMemberSelection"
      + "\nmember cannot be resolved"
      + "\nmember cannot be resolved");
  }
  
  @Test
  public void testClassesInTheSamePackageInDifferentFiles() {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method or field YMember_Type is undefined for the type LangPackage");
  }
  
  @Test
  public void testLocalClassHasThePrecedenceOverTheSameClassInTheSamePackageInDifferentFiles() {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method or field YMember_Type is undefined for the type LangPackage");
  }
  
  private YMemberSelection returnExpSel(final YFunction function) {
    throw new Error("Unresolved compilation problems:"
      + "\nYMemberSelection cannot be resolved to a type."
      + "\nThe method or field returnStatement is undefined for the type YFunction"
      + "\nexpression cannot be resolved");
  }
  
  private void assertScope(final EObject context, final EReference reference, final CharSequence expected) {
    final Function1<IEObjectDescription, QualifiedName> _function = (IEObjectDescription it) -> {
      return it.getName();
    };
    Assert.assertEquals(expected.toString(), IterableExtensions.join(IterableExtensions.<IEObjectDescription, QualifiedName>map(this._iScopeProvider.getScope(context, reference).getAllElements(), _function), ", "));
  }
}
