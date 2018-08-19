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
package eu.jgen.notes.dmw.lite.validation;

import com.google.common.base.Objects;
import com.google.inject.Inject;
import eu.jgen.notes.dmw.lite.lang.YAccessLevel;
import eu.jgen.notes.dmw.lite.lang.YClass;
import eu.jgen.notes.dmw.lite.lang.YMember;
import eu.jgen.notes.dmw.lite.typing.LangTypeConformance;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.xbase.lib.Extension;

@SuppressWarnings("all")
public class LangAccessibility {
  @Inject
  @Extension
  private LangTypeConformance _langTypeConformance;
  
  public boolean isAccessibleFrom(final YMember member, final EObject context) {
    boolean _xblockexpression = false;
    {
      final YClass contextClass = EcoreUtil2.<YClass>getContainerOfType(context, YClass.class);
      final YClass memberClass = EcoreUtil2.<YClass>getContainerOfType(member, YClass.class);
      boolean _switchResult = false;
      boolean _matched = false;
      if ((contextClass == memberClass)) {
        _matched=true;
        _switchResult = true;
      }
      if (!_matched) {
        boolean _isSubclassOf = this._langTypeConformance.isSubclassOf(contextClass, memberClass);
        if (_isSubclassOf) {
          _matched=true;
          YAccessLevel _access = member.getAccess();
          _switchResult = (!Objects.equal(_access, YAccessLevel.PRIVATE));
        }
      }
      if (!_matched) {
        YAccessLevel _access_1 = member.getAccess();
        _switchResult = Objects.equal(_access_1, YAccessLevel.PUBLIC);
      }
      _xblockexpression = _switchResult;
    }
    return _xblockexpression;
  }
}
