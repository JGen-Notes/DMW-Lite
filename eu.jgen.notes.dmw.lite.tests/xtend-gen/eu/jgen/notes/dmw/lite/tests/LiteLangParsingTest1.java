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
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.XtextRunner;
import org.eclipse.xtext.testing.util.ParseHelper;
import org.eclipse.xtext.testing.validation.ValidationTestHelper;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(XtextRunner.class)
@InjectWith(LangInjectorProvider.class)
@SuppressWarnings("all")
public class LiteLangParsingTest1 {
  @Inject
  @Extension
  private ParseHelper<YWidget> parseHelper;
  
  @Inject
  @Extension
  private LangUtil _langUtil;
  
  @Inject
  @Extension
  private ValidationTestHelper _validationTestHelper;
  
  @Test
  public void loadModel() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("class C {");
      _builder.newLine();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      final YWidget result = this.parseHelper.parse(_builder);
      Assert.assertNotNull(result);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testThenBlockWithoutStatements() {
    throw new Error("Unresolved compilation problems:"
      + "\nYIfStatement cannot be resolved to a type."
      + "\nThe method or field statements is undefined for the type XExpression"
      + "\nhead cannot be resolved"
      + "\nthenBlock cannot be resolved"
      + "\nstatements cannot be resolved"
      + "\nempty cannot be resolved"
      + "\nassertTrue cannot be resolved");
  }
  
  @Test
  public void testElse() {
    throw new Error("Unresolved compilation problems:"
      + "\nYIfStatement cannot be resolved to a type."
      + "\nThe method or field statements is undefined for the type XExpression"
      + "\nhead cannot be resolved"
      + "\nelseBlock cannot be resolved"
      + "\nassertNull cannot be resolved");
  }
  
  @Test
  public void testElseWithBlock() {
    throw new Error("Unresolved compilation problems:"
      + "\nYIfStatement cannot be resolved to a type."
      + "\nThe method or field statements is undefined for the type XExpression"
      + "\nhead cannot be resolved"
      + "\nelseBlock cannot be resolved"
      + "\nassertNotNull cannot be resolved");
  }
  
  @Test
  public void testMemberSelectionLeftAssociativity() {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method or field statements is undefined for the type XExpression"
      + "\nlast cannot be resolved"
      + "\nassertAssociativity cannot be resolved");
  }
  
  @Test
  public void testAssignmentRightAssociativity() {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method or field statements is undefined for the type XExpression"
      + "\nlast cannot be resolved"
      + "\nassertAssociativity cannot be resolved");
  }
  
  @Test
  public void testParameterAndVariable() {
    throw new Error("Unresolved compilation problems:"
      + "\nYVariableDeclaration cannot be resolved to a type."
      + "\nThe method or field statements is undefined for the type XExpression"
      + "\nhead cannot be resolved");
  }
  
  private void assertAssociativity(final /* YStatement */Object s, final CharSequence expected) {
    throw new Error("Unresolved compilation problems:"
      + "\nstringRepr cannot be resolved");
  }
  
  private String stringRepr(final /* YStatement */Object s) {
    throw new Error("Unresolved compilation problems:"
      + "\nYAssignment cannot be resolved to a type."
      + "\nYMemberSelection cannot be resolved to a type."
      + "\nYSelf cannot be resolved to a type."
      + "\nYNew cannot be resolved to a type."
      + "\nYNull cannot be resolved to a type."
      + "\nYSymbolRef cannot be resolved to a type."
      + "\nYReturn cannot be resolved to a type."
      + "\nUnreachable code: The case can never match. It is already handled by a previous condition."
      + "\nUnreachable code: The case can never match. It is already handled by a previous condition."
      + "\nUnreachable code: The case can never match. It is already handled by a previous condition."
      + "\nUnreachable code: The case can never match. It is already handled by a previous condition."
      + "\nUnreachable code: The case can never match. It is already handled by a previous condition."
      + "\nUnreachable code: The case can never match. It is already handled by a previous condition."
      + "\nleft cannot be resolved"
      + "\nstringRepr cannot be resolved"
      + "\nright cannot be resolved"
      + "\nstringRepr cannot be resolved"
      + "\nreceiver cannot be resolved"
      + "\nstringRepr cannot be resolved"
      + "\nmember cannot be resolved"
      + "\nname cannot be resolved"
      + "\ntype cannot be resolved"
      + "\nname cannot be resolved"
      + "\nsymbol cannot be resolved"
      + "\nname cannot be resolved"
      + "\nexpression cannot be resolved"
      + "\nstringRepr cannot be resolved");
  }
}
