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
package eu.jgen.notes.dmw.lite.typing;

import com.google.common.base.Objects;
import com.google.inject.Inject;
import eu.jgen.notes.dmw.lite.lang.LangFactory;
import eu.jgen.notes.dmw.lite.lang.LangPackage;
import eu.jgen.notes.dmw.lite.lang.YAndExpression;
import eu.jgen.notes.dmw.lite.lang.YAssignment;
import eu.jgen.notes.dmw.lite.lang.YBoolConstant;
import eu.jgen.notes.dmw.lite.lang.YClass;
import eu.jgen.notes.dmw.lite.lang.YComparisonExpression;
import eu.jgen.notes.dmw.lite.lang.YEqualityExpression;
import eu.jgen.notes.dmw.lite.lang.YExpression;
import eu.jgen.notes.dmw.lite.lang.YFunction;
import eu.jgen.notes.dmw.lite.lang.YIntConstant;
import eu.jgen.notes.dmw.lite.lang.YMember;
import eu.jgen.notes.dmw.lite.lang.YMemberSelection;
import eu.jgen.notes.dmw.lite.lang.YMinus;
import eu.jgen.notes.dmw.lite.lang.YMulOrDiv;
import eu.jgen.notes.dmw.lite.lang.YNot;
import eu.jgen.notes.dmw.lite.lang.YNull;
import eu.jgen.notes.dmw.lite.lang.YOrExpression;
import eu.jgen.notes.dmw.lite.lang.YProperty;
import eu.jgen.notes.dmw.lite.lang.YReturn;
import eu.jgen.notes.dmw.lite.lang.YSelf;
import eu.jgen.notes.dmw.lite.lang.YStringConstant;
import eu.jgen.notes.dmw.lite.lang.YSuper;
import eu.jgen.notes.dmw.lite.lang.YSymbolRef;
import eu.jgen.notes.dmw.lite.lang.YVariableDeclaration;
import eu.jgen.notes.dmw.lite.utility.LangLib;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;

@SuppressWarnings("all")
public class LangTypeComputer {
  private final static LangFactory factory = LangFactory.eINSTANCE;
  
  public final static YClass STRING_TYPE = ObjectExtensions.<YClass>operator_doubleArrow(LangTypeComputer.factory.createYClass(), ((Procedure1<YClass>) (YClass it) -> {
    it.setName("stringType");
  }));
  
  public final static YClass INT_TYPE = ObjectExtensions.<YClass>operator_doubleArrow(LangTypeComputer.factory.createYClass(), ((Procedure1<YClass>) (YClass it) -> {
    it.setName("intType");
  }));
  
  public final static YClass BOOLEAN_TYPE = ObjectExtensions.<YClass>operator_doubleArrow(LangTypeComputer.factory.createYClass(), ((Procedure1<YClass>) (YClass it) -> {
    it.setName("booleanType");
  }));
  
  public final static YClass NULL_TYPE = ObjectExtensions.<YClass>operator_doubleArrow(LangTypeComputer.factory.createYClass(), ((Procedure1<YClass>) (YClass it) -> {
    it.setName("nullType");
  }));
  
  private final static LangPackage ep = LangPackage.eINSTANCE;
  
  @Inject
  @Extension
  private LangLib _langLib;
  
  public YClass typeFor(final YExpression epression) {
    YClass _switchResult = null;
    boolean _matched = false;
    if (epression instanceof YSymbolRef) {
      _matched=true;
      _switchResult = ((YSymbolRef)epression).getSymbol().getType();
    }
    if (!_matched) {
      if (epression instanceof YMemberSelection) {
        _matched=true;
        YClass _xifexpression = null;
        YMember _member = ((YMemberSelection)epression).getMember();
        if ((_member instanceof YFunction)) {
          YClass _xblockexpression = null;
          {
            YMember _member_1 = ((YMemberSelection)epression).getMember();
            final YFunction method = ((YFunction) _member_1);
            _xblockexpression = method.getType();
          }
          _xifexpression = _xblockexpression;
        } else {
          YClass _xifexpression_1 = null;
          YMember _member_1 = ((YMemberSelection)epression).getMember();
          if ((_member_1 instanceof YProperty)) {
            YClass _xblockexpression_1 = null;
            {
              YMember _member_2 = ((YMemberSelection)epression).getMember();
              final YProperty property = ((YProperty) _member_2);
              _xblockexpression_1 = property.getType();
            }
            _xifexpression_1 = _xblockexpression_1;
          } else {
            _xifexpression_1 = LangTypeComputer.NULL_TYPE;
          }
          _xifexpression = _xifexpression_1;
        }
        _switchResult = _xifexpression;
      }
    }
    if (!_matched) {
      if (epression instanceof YAssignment) {
        _matched=true;
        _switchResult = this.typeFor(((YAssignment)epression).getLeft());
      }
    }
    if (!_matched) {
      if (epression instanceof YSelf) {
        _matched=true;
        _switchResult = EcoreUtil2.<YClass>getContainerOfType(epression, YClass.class);
      }
    }
    if (!_matched) {
      if (epression instanceof YSuper) {
        _matched=true;
        _switchResult = this.getSuperclassOrObject(EcoreUtil2.<YClass>getContainerOfType(epression, YClass.class));
      }
    }
    if (!_matched) {
      if (epression instanceof YNull) {
        _matched=true;
        _switchResult = LangTypeComputer.NULL_TYPE;
      }
    }
    if (!_matched) {
      if (epression instanceof YStringConstant) {
        _matched=true;
        _switchResult = LangTypeComputer.STRING_TYPE;
      }
    }
    if (!_matched) {
      if (epression instanceof YIntConstant) {
        _matched=true;
        _switchResult = LangTypeComputer.INT_TYPE;
      }
    }
    if (!_matched) {
      if (epression instanceof YBoolConstant) {
        _matched=true;
        _switchResult = LangTypeComputer.BOOLEAN_TYPE;
      }
    }
    if (!_matched) {
      if (epression instanceof YNot) {
        _matched=true;
        _switchResult = LangTypeComputer.BOOLEAN_TYPE;
      }
    }
    if (!_matched) {
      if (epression instanceof YMulOrDiv) {
        _matched=true;
        _switchResult = LangTypeComputer.INT_TYPE;
      }
    }
    if (!_matched) {
      if (epression instanceof YMinus) {
        _matched=true;
        _switchResult = LangTypeComputer.INT_TYPE;
      }
    }
    if (!_matched) {
      if (epression instanceof YComparisonExpression) {
        _matched=true;
        _switchResult = LangTypeComputer.BOOLEAN_TYPE;
      }
    }
    if (!_matched) {
      if (epression instanceof YEqualityExpression) {
        _matched=true;
        _switchResult = LangTypeComputer.BOOLEAN_TYPE;
      }
    }
    if (!_matched) {
      if (epression instanceof YAndExpression) {
        _matched=true;
        _switchResult = LangTypeComputer.BOOLEAN_TYPE;
      }
    }
    if (!_matched) {
      if (epression instanceof YOrExpression) {
        _matched=true;
        _switchResult = LangTypeComputer.BOOLEAN_TYPE;
      }
    }
    return _switchResult;
  }
  
  public YClass getSuperclassOrObject(final YClass yclass) {
    YClass _elvis = null;
    YClass _superclass = yclass.getSuperclass();
    if (_superclass != null) {
      _elvis = _superclass;
    } else {
      YClass _langObjectClass = this._langLib.getLangObjectClass(yclass);
      _elvis = _langObjectClass;
    }
    return _elvis;
  }
  
  public boolean isPrimitive(final YClass yclass) {
    Resource _eResource = yclass.eResource();
    return (_eResource == null);
  }
  
  public YClass expectedType(final YExpression expression) {
    YClass _xblockexpression = null;
    {
      final EObject container = expression.eContainer();
      final EStructuralFeature feature = expression.eContainingFeature();
      YClass _switchResult = null;
      boolean _matched = false;
      if (container instanceof YVariableDeclaration) {
        _matched=true;
        _switchResult = ((YVariableDeclaration)container).getType();
      }
      if (!_matched) {
        if (container instanceof YAssignment) {
          EReference _yAssignment_Right = LangTypeComputer.ep.getYAssignment_Right();
          boolean _equals = Objects.equal(feature, _yAssignment_Right);
          if (_equals) {
            _matched=true;
            _switchResult = this.typeFor(((YAssignment)container).getLeft());
          }
        }
      }
      if (!_matched) {
        if (container instanceof YReturn) {
          _matched=true;
          _switchResult = EcoreUtil2.<YFunction>getContainerOfType(container, YFunction.class).getType();
        }
      }
      if (!_matched) {
        EReference _yIfStatement_Expression = LangTypeComputer.ep.getYIfStatement_Expression();
        boolean _equals = Objects.equal(feature, _yIfStatement_Expression);
        if (_equals) {
          _matched=true;
          _switchResult = LangTypeComputer.BOOLEAN_TYPE;
        }
      }
      if (!_matched) {
        if (container instanceof YMemberSelection) {
          EReference _yMemberSelection_Args = LangTypeComputer.ep.getYMemberSelection_Args();
          boolean _equals_1 = Objects.equal(feature, _yMemberSelection_Args);
          if (_equals_1) {
            _matched=true;
            YClass _xtrycatchfinallyexpression = null;
            try {
              YMember _member = ((YMemberSelection)container).getMember();
              _xtrycatchfinallyexpression = ((YFunction) _member).getParams().get(((YMemberSelection)container).getArgs().indexOf(expression)).getType();
            } catch (final Throwable _t) {
              if (_t instanceof Throwable) {
                final Throwable t = (Throwable)_t;
                _xtrycatchfinallyexpression = null;
              } else {
                throw Exceptions.sneakyThrow(_t);
              }
            }
            _switchResult = _xtrycatchfinallyexpression;
          }
        }
      }
      _xblockexpression = _switchResult;
    }
    return _xblockexpression;
  }
}
