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
package eu.jgen.notes.dmw.lite.ui.outline;

import com.google.common.base.Objects;
import com.google.common.collect.Iterators;
import com.google.inject.Inject;
import eu.jgen.notes.dmw.lite.lang.YAnnotAttr;
import eu.jgen.notes.dmw.lite.lang.YAnnotEntity;
import eu.jgen.notes.dmw.lite.lang.YAnnotEntityInner;
import eu.jgen.notes.dmw.lite.lang.YAnnotId;
import eu.jgen.notes.dmw.lite.lang.YAnnotRel;
import eu.jgen.notes.dmw.lite.lang.YClass;
import eu.jgen.notes.dmw.lite.lang.YFunction;
import eu.jgen.notes.dmw.lite.lang.YImport;
import eu.jgen.notes.dmw.lite.lang.YProperty;
import eu.jgen.notes.dmw.lite.lang.YTuples;
import eu.jgen.notes.dmw.lite.lang.YWidget;
import java.util.function.Consumer;
import org.eclipse.xtext.ui.PluginImageHelper;
import org.eclipse.xtext.ui.editor.outline.impl.DefaultOutlineTreeProvider;
import org.eclipse.xtext.ui.editor.outline.impl.DocumentRootNode;
import org.eclipse.xtext.xbase.lib.IteratorExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;

/**
 * Customization of the default outline structure.
 * 
 * See https://www.eclipse.org/Xtext/documentation/310_eclipse_support.html#outline
 */
@SuppressWarnings("all")
public class LangOutlineTreeProvider extends DefaultOutlineTreeProvider {
  @Inject
  private PluginImageHelper imageHelper;
  
  public boolean _isLeaf(final YFunction function) {
    return true;
  }
  
  public void _createChildren(final DocumentRootNode outlineNode, final YWidget widget) {
    final Procedure1<YAnnotEntity> _function = (YAnnotEntity annotEntity) -> {
      this.createNode(outlineNode, annotEntity);
      final Consumer<YAnnotEntityInner> _function_1 = (YAnnotEntityInner annot) -> {
        this.createNode(outlineNode, annot);
      };
      annotEntity.getAnnots().forEach(_function_1);
    };
    IteratorExtensions.<YAnnotEntity>forEach(Iterators.<YAnnotEntity>filter(widget.eAllContents(), YAnnotEntity.class), _function);
    final Consumer<YClass> _function_1 = (YClass cl) -> {
      this.createNode(outlineNode, cl);
    };
    widget.getClasses().forEach(_function_1);
  }
  
  public Object _text(final YAnnotEntity element) {
    return element.getName();
  }
  
  public Object _text(final YAnnotAttr element) {
    String _name = element.getName();
    String _plus = (_name + " : ");
    String _name_1 = element.getYclass().getName();
    return (_plus + _name_1);
  }
  
  public Object _text(final YAnnotRel element) {
    boolean _isMany = element.isMany();
    if (_isMany) {
      String _name = element.getName();
      String _plus = (_name + " -> ");
      String _name_1 = element.getTarget().getName();
      String _plus_1 = (_plus + _name_1);
      return (_plus_1 + "*");
    } else {
      String _name_2 = element.getName();
      String _plus_2 = (_name_2 + " -> ");
      String _name_3 = element.getTarget().getName();
      return (_plus_2 + _name_3);
    }
  }
  
  public Object _text(final YProperty element) {
    String tuple = "";
    YTuples _tuples = element.getTuples();
    boolean _tripleNotEquals = (_tuples != null);
    if (_tripleNotEquals) {
      tuple = "<>";
    }
    String _name = element.getName();
    String _plus = (_name + " : ");
    String _name_1 = element.getType().getName();
    String _plus_1 = (_plus + _name_1);
    return (_plus_1 + tuple);
  }
  
  public Object _text(final YFunction element) {
    String _name = element.getName();
    return (_name + "()");
  }
  
  public Object _text(final YClass element) {
    String _xifexpression = null;
    if (((element.getSuperclass() != null) && Objects.equal(element.getSuperclass().getName(), "Widget"))) {
      _xifexpression = element.getName();
    } else {
      String _xifexpression_1 = null;
      if (((element.getSuperclass() != null) && Objects.equal(element.getSuperclass().getName(), "Structure"))) {
        String _xifexpression_2 = null;
        YAnnotEntity _entity = element.getEntity();
        boolean _tripleNotEquals = (_entity != null);
        if (_tripleNotEquals) {
          String _name = element.getName();
          String _plus = (_name + " -> ");
          String _name_1 = element.getEntity().getName();
          _xifexpression_2 = (_plus + _name_1);
        } else {
          _xifexpression_2 = element.getName();
        }
        _xifexpression_1 = _xifexpression_2;
      } else {
        return element.getName();
      }
      _xifexpression = _xifexpression_1;
    }
    return _xifexpression;
  }
  
  public Object _image(final YClass element) {
    if (((element.getSuperclass() != null) && Objects.equal(element.getSuperclass().getName(), "Widget"))) {
      return this.imageHelper.getImage("widget.gif");
    } else {
      if (((element.getSuperclass() != null) && Objects.equal(element.getSuperclass().getName(), "Structure"))) {
        return this.imageHelper.getImage("structure.gif");
      } else {
        return this.imageHelper.getImage("class.gif");
      }
    }
  }
  
  public Object _image(final YAnnotEntity element) {
    return this.imageHelper.getImage("entity.gif");
  }
  
  public Object _image(final YProperty element) {
    return this.imageHelper.getImage("property.gif");
  }
  
  public Object _image(final YFunction element) {
    return this.imageHelper.getImage("function.gif");
  }
  
  public Object _image(final YImport element) {
    return this.imageHelper.getImage("import.gif");
  }
  
  public Object _image(final YAnnotAttr element) {
    return this.imageHelper.getImage("attribute.gif");
  }
  
  public boolean _isLeaf(final YAnnotAttr element) {
    return true;
  }
  
  public Object _image(final YAnnotRel element) {
    boolean _isMany = element.isMany();
    if (_isMany) {
      return this.imageHelper.getImage("onetomany.gif");
    } else {
      return this.imageHelper.getImage("onetoone.gif");
    }
  }
  
  public boolean _isLeaf(final YAnnotRel element) {
    return true;
  }
  
  public Object _image(final YAnnotId element) {
    return this.imageHelper.getImage("identifier.gif");
  }
  
  public boolean _isLeaf(final YAnnotId element) {
    return true;
  }
}
