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

import com.google.common.base.Objects;
import com.google.inject.Inject;
import com.google.inject.Provider;
import eu.jgen.notes.dmw.lite.lang.YClass;
import eu.jgen.notes.dmw.lite.lang.YWidget;
import eu.jgen.notes.dmw.lite.tests.LangInjectorProvider;
import eu.jgen.notes.dmw.lite.typing.LangTypeComputer;
import eu.jgen.notes.dmw.lite.typing.LangTypeConformance;
import eu.jgen.notes.dmw.lite.utility.LangLib;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.naming.IQualifiedNameProvider;
import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.XtextRunner;
import org.eclipse.xtext.testing.util.ParseHelper;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(XtextRunner.class)
@InjectWith(LangInjectorProvider.class)
@SuppressWarnings("all")
public class LiteLangTypeConformanceTest {
  @Inject
  @Extension
  private ParseHelper<YWidget> _parseHelper;
  
  @Inject
  @Extension
  private LangTypeConformance _langTypeConformance;
  
  @Inject
  @Extension
  private LangLib _langLib;
  
  @Inject
  @Extension
  private IQualifiedNameProvider _iQualifiedNameProvider;
  
  @Inject
  private Provider<ResourceSet> rsp;
  
  @Test
  public void testClassConformance() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("class A {}");
      _builder.newLine();
      _builder.append("class B : A {}");
      _builder.newLine();
      _builder.append("class C {}");
      _builder.newLine();
      _builder.append("class D : B {}");
      _builder.newLine();
      EList<YClass> _classes = this._parseHelper.parse(_builder).getClasses();
      final Procedure1<EList<YClass>> _function = (EList<YClass> it) -> {
        Assert.assertTrue(this._langTypeConformance.isConformant(it.get(0), it.get(0)));
        Assert.assertTrue(this._langTypeConformance.isConformant(it.get(1), it.get(0)));
        Assert.assertTrue(this._langTypeConformance.isConformant(it.get(2), it.get(0)));
        Assert.assertTrue(this._langTypeConformance.isConformant(it.get(3), it.get(0)));
        Assert.assertTrue(this._langTypeConformance.isConformant(LangTypeComputer.NULL_TYPE, it.get(0)));
      };
      ObjectExtensions.<EList<YClass>>operator_doubleArrow(_classes, _function);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testStringConformance() {
    Assert.assertTrue(this._langTypeConformance.isConformant(LangTypeComputer.STRING_TYPE, this.libClass(LangLib.LIB_STRING)));
  }
  
  @Test
  public void testIntConformance() {
    Assert.assertTrue(this._langTypeConformance.isConformant(LangTypeComputer.INT_TYPE, this.libClass(LangLib.LIB_INTEGER)));
  }
  
  @Test
  public void testBoolConformance() {
    Assert.assertTrue(this._langTypeConformance.isConformant(LangTypeComputer.BOOLEAN_TYPE, this.libClass(LangLib.LIB_BOOL)));
  }
  
  private YClass libClass(final String fqn) {
    YClass _xblockexpression = null;
    {
      final ResourceSet rs = this.rsp.get();
      this._langLib.loadLib(rs);
      EObject _head = IterableExtensions.<EObject>head(IterableExtensions.<Resource>head(rs.getResources()).getContents());
      final Function1<YClass, Boolean> _function = (YClass it) -> {
        String _string = this._iQualifiedNameProvider.getFullyQualifiedName(it).toString();
        return Boolean.valueOf(Objects.equal(_string, fqn));
      };
      _xblockexpression = IterableExtensions.<YClass>findFirst(((YWidget) _head).getClasses(), _function);
    }
    return _xblockexpression;
  }
}
