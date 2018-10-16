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
import eu.jgen.notes.dmw.lite.lang.YWidget;
import eu.jgen.notes.dmw.lite.tests.LangInjectorProvider;
import eu.jgen.notes.dmw.lite.utility.LangUtil;
import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.XtextRunner;
import org.eclipse.xtext.testing.util.ParseHelper;
import org.eclipse.xtext.xbase.lib.Extension;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(XtextRunner.class)
@InjectWith(LangInjectorProvider.class)
@SuppressWarnings("all")
public class LiteLangAccessibilityTest {
  @Inject
  @Extension
  private ParseHelper<YWidget> _parseHelper;
  
  @Inject
  @Extension
  private /* LangAccessibility */Object _langAccessibility;
  
  @Inject
  @Extension
  private LangUtil _langUtil;
  
  @Test
  public void testPropertyAccessibility() {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method or field statements is undefined for the type XExpression"
      + "\nThe method or field statements is undefined for the type XExpression"
      + "\nThe method or field statements is undefined for the type XExpression"
      + "\nType mismatch: cannot convert implicit first argument from Object to Object[]"
      + "\nType mismatch: cannot convert implicit first argument from Object to Object[]"
      + "\nType mismatch: cannot convert implicit first argument from Object to Object[]"
      + "\nType mismatch: cannot convert implicit first argument from Object to Object[]"
      + "\nType mismatch: cannot convert implicit first argument from Object to Object[]"
      + "\nType mismatch: cannot convert implicit first argument from Object to Object[]"
      + "\nType mismatch: cannot convert implicit first argument from Object to Object[]"
      + "\nType mismatch: cannot convert implicit first argument from Object to Object[]"
      + "\nType mismatch: cannot convert implicit first argument from Object to Object[]"
      + "\nThe method assertMemberAccessible(YStatement, boolean) from the type LiteLangAccessibilityTest refers to the missing type YStatement"
      + "\nThe method assertMemberAccessible(YStatement, boolean) from the type LiteLangAccessibilityTest refers to the missing type YStatement"
      + "\nThe method assertMemberAccessible(YStatement, boolean) from the type LiteLangAccessibilityTest refers to the missing type YStatement"
      + "\nThe method assertMemberAccessible(YStatement, boolean) from the type LiteLangAccessibilityTest refers to the missing type YStatement"
      + "\nThe method assertMemberAccessible(YStatement, boolean) from the type LiteLangAccessibilityTest refers to the missing type YStatement"
      + "\nThe method assertMemberAccessible(YStatement, boolean) from the type LiteLangAccessibilityTest refers to the missing type YStatement"
      + "\nThe method assertMemberAccessible(YStatement, boolean) from the type LiteLangAccessibilityTest refers to the missing type YStatement"
      + "\nThe method assertMemberAccessible(YStatement, boolean) from the type LiteLangAccessibilityTest refers to the missing type YStatement"
      + "\nThe method assertMemberAccessible(YStatement, boolean) from the type LiteLangAccessibilityTest refers to the missing type YStatement"
      + "\nThe method assertMemberAccessible(YStatement, boolean) from the type LiteLangAccessibilityTest refers to the missing type YStatement"
      + "\nThe method assertMemberAccessible(YStatement, boolean) from the type LiteLangAccessibilityTest refers to the missing type YStatement"
      + "\nThe method assertMemberAccessible(YStatement, boolean) from the type LiteLangAccessibilityTest refers to the missing type YStatement"
      + "\nThe method assertMemberAccessible(YStatement, boolean) from the type LiteLangAccessibilityTest refers to the missing type YStatement"
      + "\nThe method assertMemberAccessible(YStatement, boolean) from the type LiteLangAccessibilityTest refers to the missing type YStatement"
      + "\nThe method assertMemberAccessible(YStatement, boolean) from the type LiteLangAccessibilityTest refers to the missing type YStatement"
      + "\nThe method assertMemberAccessible(YStatement, boolean) from the type LiteLangAccessibilityTest refers to the missing type YStatement"
      + "\nThe method assertMemberAccessible(YStatement, boolean) from the type LiteLangAccessibilityTest refers to the missing type YStatement"
      + "\nThe method assertMemberAccessible(YStatement, boolean) from the type LiteLangAccessibilityTest refers to the missing type YStatement"
      + "\nThe method assertMemberAccessible(YStatement, boolean) from the type LiteLangAccessibilityTest refers to the missing type YStatement"
      + "\nThe method assertMemberAccessible(YStatement, boolean) from the type LiteLangAccessibilityTest refers to the missing type YStatement"
      + "\nThe method assertMemberAccessible(YStatement, boolean) from the type LiteLangAccessibilityTest refers to the missing type YStatement"
      + "\nThe method assertMemberAccessible(YStatement, boolean) from the type LiteLangAccessibilityTest refers to the missing type YStatement"
      + "\nThe method assertMemberAccessible(YStatement, boolean) from the type LiteLangAccessibilityTest refers to the missing type YStatement"
      + "\nThe method assertMemberAccessible(YStatement, boolean) from the type LiteLangAccessibilityTest refers to the missing type YStatement"
      + "\nThe method assertMemberAccessible(YStatement, boolean) from the type LiteLangAccessibilityTest refers to the missing type YStatement"
      + "\nThe method assertMemberAccessible(YStatement, boolean) from the type LiteLangAccessibilityTest refers to the missing type YStatement"
      + "\nThe method assertMemberAccessible(YStatement, boolean) from the type LiteLangAccessibilityTest refers to the missing type YStatement"
      + "\nThe method assertMemberAccessible(YStatement, boolean) from the type LiteLangAccessibilityTest refers to the missing type YStatement"
      + "\nThe method assertMemberAccessible(YStatement, boolean) from the type LiteLangAccessibilityTest refers to the missing type YStatement"
      + "\nThe method assertMemberAccessible(YStatement, boolean) from the type LiteLangAccessibilityTest refers to the missing type YStatement"
      + "\nThe method assertMemberAccessible(YStatement, boolean) from the type LiteLangAccessibilityTest refers to the missing type YStatement"
      + "\nThe method assertMemberAccessible(YStatement, boolean) from the type LiteLangAccessibilityTest refers to the missing type YStatement"
      + "\nThe method assertMemberAccessible(YStatement, boolean) from the type LiteLangAccessibilityTest refers to the missing type YStatement"
      + "\nThe method assertMemberAccessible(YStatement, boolean) from the type LiteLangAccessibilityTest refers to the missing type YStatement"
      + "\nThe method assertMemberAccessible(YStatement, boolean) from the type LiteLangAccessibilityTest refers to the missing type YStatement"
      + "\nThe method assertMemberAccessible(YStatement, boolean) from the type LiteLangAccessibilityTest refers to the missing type YStatement"
      + "\n=> cannot be resolved"
      + "\n=> cannot be resolved"
      + "\n=> cannot be resolved");
  }
  
  @Test
  public void testFunctionAccessibility() {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method or field statements is undefined for the type XExpression"
      + "\nThe method or field statements is undefined for the type XExpression"
      + "\nThe method or field statements is undefined for the type XExpression"
      + "\nType mismatch: cannot convert implicit first argument from Object to Object[]"
      + "\nType mismatch: cannot convert implicit first argument from Object to Object[]"
      + "\nType mismatch: cannot convert implicit first argument from Object to Object[]"
      + "\nType mismatch: cannot convert implicit first argument from Object to Object[]"
      + "\nType mismatch: cannot convert implicit first argument from Object to Object[]"
      + "\nType mismatch: cannot convert implicit first argument from Object to Object[]"
      + "\nType mismatch: cannot convert implicit first argument from Object to Object[]"
      + "\nType mismatch: cannot convert implicit first argument from Object to Object[]"
      + "\nType mismatch: cannot convert implicit first argument from Object to Object[]"
      + "\nThe method assertMemberAccessible(YStatement, boolean) from the type LiteLangAccessibilityTest refers to the missing type YStatement"
      + "\nThe method assertMemberAccessible(YStatement, boolean) from the type LiteLangAccessibilityTest refers to the missing type YStatement"
      + "\nThe method assertMemberAccessible(YStatement, boolean) from the type LiteLangAccessibilityTest refers to the missing type YStatement"
      + "\nThe method assertMemberAccessible(YStatement, boolean) from the type LiteLangAccessibilityTest refers to the missing type YStatement"
      + "\nThe method assertMemberAccessible(YStatement, boolean) from the type LiteLangAccessibilityTest refers to the missing type YStatement"
      + "\nThe method assertMemberAccessible(YStatement, boolean) from the type LiteLangAccessibilityTest refers to the missing type YStatement"
      + "\nThe method assertMemberAccessible(YStatement, boolean) from the type LiteLangAccessibilityTest refers to the missing type YStatement"
      + "\nThe method assertMemberAccessible(YStatement, boolean) from the type LiteLangAccessibilityTest refers to the missing type YStatement"
      + "\nThe method assertMemberAccessible(YStatement, boolean) from the type LiteLangAccessibilityTest refers to the missing type YStatement"
      + "\nThe method assertMemberAccessible(YStatement, boolean) from the type LiteLangAccessibilityTest refers to the missing type YStatement"
      + "\nThe method assertMemberAccessible(YStatement, boolean) from the type LiteLangAccessibilityTest refers to the missing type YStatement"
      + "\nThe method assertMemberAccessible(YStatement, boolean) from the type LiteLangAccessibilityTest refers to the missing type YStatement"
      + "\nThe method assertMemberAccessible(YStatement, boolean) from the type LiteLangAccessibilityTest refers to the missing type YStatement"
      + "\nThe method assertMemberAccessible(YStatement, boolean) from the type LiteLangAccessibilityTest refers to the missing type YStatement"
      + "\nThe method assertMemberAccessible(YStatement, boolean) from the type LiteLangAccessibilityTest refers to the missing type YStatement"
      + "\nThe method assertMemberAccessible(YStatement, boolean) from the type LiteLangAccessibilityTest refers to the missing type YStatement"
      + "\nThe method assertMemberAccessible(YStatement, boolean) from the type LiteLangAccessibilityTest refers to the missing type YStatement"
      + "\nThe method assertMemberAccessible(YStatement, boolean) from the type LiteLangAccessibilityTest refers to the missing type YStatement"
      + "\nThe method assertMemberAccessible(YStatement, boolean) from the type LiteLangAccessibilityTest refers to the missing type YStatement"
      + "\nThe method assertMemberAccessible(YStatement, boolean) from the type LiteLangAccessibilityTest refers to the missing type YStatement"
      + "\nThe method assertMemberAccessible(YStatement, boolean) from the type LiteLangAccessibilityTest refers to the missing type YStatement"
      + "\nThe method assertMemberAccessible(YStatement, boolean) from the type LiteLangAccessibilityTest refers to the missing type YStatement"
      + "\nThe method assertMemberAccessible(YStatement, boolean) from the type LiteLangAccessibilityTest refers to the missing type YStatement"
      + "\nThe method assertMemberAccessible(YStatement, boolean) from the type LiteLangAccessibilityTest refers to the missing type YStatement"
      + "\nThe method assertMemberAccessible(YStatement, boolean) from the type LiteLangAccessibilityTest refers to the missing type YStatement"
      + "\nThe method assertMemberAccessible(YStatement, boolean) from the type LiteLangAccessibilityTest refers to the missing type YStatement"
      + "\nThe method assertMemberAccessible(YStatement, boolean) from the type LiteLangAccessibilityTest refers to the missing type YStatement"
      + "\nThe method assertMemberAccessible(YStatement, boolean) from the type LiteLangAccessibilityTest refers to the missing type YStatement"
      + "\nThe method assertMemberAccessible(YStatement, boolean) from the type LiteLangAccessibilityTest refers to the missing type YStatement"
      + "\nThe method assertMemberAccessible(YStatement, boolean) from the type LiteLangAccessibilityTest refers to the missing type YStatement"
      + "\nThe method assertMemberAccessible(YStatement, boolean) from the type LiteLangAccessibilityTest refers to the missing type YStatement"
      + "\nThe method assertMemberAccessible(YStatement, boolean) from the type LiteLangAccessibilityTest refers to the missing type YStatement"
      + "\nThe method assertMemberAccessible(YStatement, boolean) from the type LiteLangAccessibilityTest refers to the missing type YStatement"
      + "\nThe method assertMemberAccessible(YStatement, boolean) from the type LiteLangAccessibilityTest refers to the missing type YStatement"
      + "\nThe method assertMemberAccessible(YStatement, boolean) from the type LiteLangAccessibilityTest refers to the missing type YStatement"
      + "\nThe method assertMemberAccessible(YStatement, boolean) from the type LiteLangAccessibilityTest refers to the missing type YStatement"
      + "\n=> cannot be resolved"
      + "\n=> cannot be resolved"
      + "\n=> cannot be resolved");
  }
  
  private void assertMemberAccessible(final /* YStatement */Object s, final boolean expected) {
    throw new Error("Unresolved compilation problems:"
      + "\nYMemberSelection cannot be resolved to a type."
      + "\nmember cannot be resolved"
      + "\nisAccessibleFrom cannot be resolved");
  }
}
