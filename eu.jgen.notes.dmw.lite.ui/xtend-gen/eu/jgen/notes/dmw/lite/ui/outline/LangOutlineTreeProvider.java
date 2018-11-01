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
import com.google.inject.Inject;
import eu.jgen.notes.dmw.lite.lang.YAnnotAbstractColumn;
import eu.jgen.notes.dmw.lite.lang.YAnnotAttr;
import eu.jgen.notes.dmw.lite.lang.YAnnotColumn;
import eu.jgen.notes.dmw.lite.lang.YAnnotColumnLike;
import eu.jgen.notes.dmw.lite.lang.YAnnotDatabase;
import eu.jgen.notes.dmw.lite.lang.YAnnotEntity;
import eu.jgen.notes.dmw.lite.lang.YAnnotEntityInner;
import eu.jgen.notes.dmw.lite.lang.YAnnotForeignKey;
import eu.jgen.notes.dmw.lite.lang.YAnnotId;
import eu.jgen.notes.dmw.lite.lang.YAnnotJava;
import eu.jgen.notes.dmw.lite.lang.YAnnotPrimaryKey;
import eu.jgen.notes.dmw.lite.lang.YAnnotRel;
import eu.jgen.notes.dmw.lite.lang.YAnnotSwift;
import eu.jgen.notes.dmw.lite.lang.YAnnotTable;
import eu.jgen.notes.dmw.lite.lang.YAnnotTechnicalDesign;
import eu.jgen.notes.dmw.lite.lang.YAnnotTop;
import eu.jgen.notes.dmw.lite.lang.YClass;
import eu.jgen.notes.dmw.lite.lang.YFunction;
import eu.jgen.notes.dmw.lite.lang.YImport;
import eu.jgen.notes.dmw.lite.lang.YProperty;
import eu.jgen.notes.dmw.lite.lang.YTuples;
import eu.jgen.notes.dmw.lite.lang.YWidget;
import java.util.function.Consumer;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.xtext.ui.PluginImageHelper;
import org.eclipse.xtext.ui.editor.outline.impl.DefaultOutlineTreeProvider;
import org.eclipse.xtext.ui.editor.outline.impl.DocumentRootNode;
import org.eclipse.xtext.ui.editor.utils.TextStyle;
import org.eclipse.xtext.ui.label.StylerFactory;

/**
 * Customization of the default outline structure.
 * 
 * See https://www.eclipse.org/Xtext/documentation/310_eclipse_support.html#outline
 */
@SuppressWarnings("all")
public class LangOutlineTreeProvider extends DefaultOutlineTreeProvider {
  @Inject
  private PluginImageHelper imageHelper;
  
  @Inject
  private StylerFactory stylerFactory;
  
  public void _createChildren(final DocumentRootNode outlineNode, final YWidget widget) {
    this.createNode(outlineNode, widget);
    final Consumer<YImport> _function = (YImport element) -> {
      this.createNode(outlineNode, element);
    };
    widget.getImports().forEach(_function);
    final Consumer<YAnnotTop> _function_1 = (YAnnotTop element) -> {
      this.createNode(outlineNode, element.getType());
    };
    widget.getAnnotations().forEach(_function_1);
    final Consumer<YClass> _function_2 = (YClass element) -> {
      this.createNode(outlineNode, element);
    };
    widget.getClasses().forEach(_function_2);
  }
  
  public void _createChildren(final DocumentRootNode outlineNode, final YAnnotTop annotTop) {
    this.createNode(outlineNode, annotTop.getType());
  }
  
  public void _createChildren(final DocumentRootNode outlineNode, final YAnnotEntity annotEntity) {
    final Consumer<YAnnotEntityInner> _function = (YAnnotEntityInner annot) -> {
      this.createNode(outlineNode, annot);
    };
    annotEntity.getAnnots().forEach(_function);
  }
  
  public boolean _isLeaf(final YWidget element) {
    return true;
  }
  
  public Object _text(final YWidget widget) {
    String _name = widget.getName();
    boolean _tripleNotEquals = (_name != null);
    if (_tripleNotEquals) {
      return widget.getName();
    }
    return null;
  }
  
  /**
   * Widget
   */
  public Object _image(final YWidget widget) {
    String _name = widget.getName();
    boolean _tripleNotEquals = (_name != null);
    if (_tripleNotEquals) {
      return this.imageHelper.getImage("package.gif");
    }
    return null;
  }
  
  /**
   * Database
   */
  public Object _text(final YAnnotDatabase annotDatabase) {
    String _name = annotDatabase.getName();
    boolean _tripleNotEquals = (_name != null);
    if (_tripleNotEquals) {
      return annotDatabase.getName();
    }
    return null;
  }
  
  public Object _image(final YAnnotDatabase annotDatabase) {
    String _name = annotDatabase.getName();
    boolean _tripleNotEquals = (_name != null);
    if (_tripleNotEquals) {
      return this.imageHelper.getImage("database.gif");
    }
    return null;
  }
  
  /**
   * Swift
   */
  public Object _text(final YAnnotSwift annotSwift) {
    String _name = annotSwift.getName();
    boolean _tripleNotEquals = (_name != null);
    if (_tripleNotEquals) {
      YAnnotDatabase _database = annotSwift.getDatabase();
      boolean _tripleNotEquals_1 = (_database != null);
      if (_tripleNotEquals_1) {
        String _name_1 = annotSwift.getName();
        String _plus = ("Swift " + _name_1);
        String _plus_1 = (_plus + " + ");
        String _name_2 = annotSwift.getDatabase().getName();
        return (_plus_1 + _name_2);
      }
    }
    return null;
  }
  
  public Object _image(final YAnnotSwift annotSwift) {
    return this.imageHelper.getImage("swift.png");
  }
  
  /**
   * Java
   */
  public Object _text(final YAnnotJava annotJava) {
    YAnnotDatabase _database = annotJava.getDatabase();
    boolean _tripleNotEquals = (_database != null);
    if (_tripleNotEquals) {
      String _name = annotJava.getDatabase().getName();
      return ("Java + " + _name);
    } else {
      return "Java";
    }
  }
  
  public Object _image(final YAnnotJava annotJava) {
    return this.imageHelper.getImage("java.png");
  }
  
  /**
   * Technical Design
   */
  public Object _text(final YAnnotTechnicalDesign element) {
    YAnnotDatabase _database = element.getDatabase();
    boolean _tripleNotEquals = (_database != null);
    if (_tripleNotEquals) {
      return element.getDatabase().getName();
    }
    return null;
  }
  
  public Object _image(final YAnnotTechnicalDesign element) {
    return this.imageHelper.getImage("td.gif");
  }
  
  /**
   * Table
   */
  public Object _text(final YAnnotTable element) {
    String _name = element.getName();
    boolean _tripleNotEquals = (_name != null);
    if (_tripleNotEquals) {
      YAnnotEntity _entityref = element.getEntityref();
      boolean _tripleNotEquals_1 = (_entityref != null);
      if (_tripleNotEquals_1) {
        String _name_1 = element.getName();
        String _plus = (_name_1 + " -> ");
        String _name_2 = element.getEntityref().getName();
        return (_plus + _name_2);
      } else {
        return element.getName();
      }
    }
    return "";
  }
  
  public Object _image(final YAnnotTable element) {
    return this.imageHelper.getImage("table.gif");
  }
  
  /**
   * Table Column
   */
  public Object _text(final YAnnotAbstractColumn element) {
    String _name = element.getName();
    boolean _tripleNotEquals = (_name != null);
    if (_tripleNotEquals) {
      if (((element.getType() != null) && (element.getType() instanceof YAnnotColumn))) {
        EObject _type = element.getType();
        final YAnnotColumn annotColumn = ((YAnnotColumn) _type);
        String _name_1 = element.getName();
        String _plus = (_name_1 + " -> ");
        String _name_2 = annotColumn.getAttrref().getName();
        return (_plus + _name_2);
      } else {
        if (((element.getType() != null) && (element.getType() instanceof YAnnotColumn))) {
          EObject _type_1 = element.getType();
          final YAnnotColumnLike annotColumnLike = ((YAnnotColumnLike) _type_1);
          String _name_3 = element.getName();
          String _plus_1 = (_name_3 + " as -> ");
          String _name_4 = annotColumnLike.getColumnref().getName();
          return (_plus_1 + _name_4);
        } else {
          return element.getName();
        }
      }
    }
    return "";
  }
  
  public Object _image(final YAnnotAbstractColumn element) {
    EObject _eContainer = element.eContainer();
    if ((_eContainer instanceof YAnnotForeignKey)) {
      return this.imageHelper.getImage("foreignKey.gif");
    } else {
      EObject _eContainer_1 = element.eContainer();
      if ((_eContainer_1 instanceof YAnnotTable)) {
        EObject _eContainer_2 = element.eContainer();
        final YAnnotPrimaryKey pk = ((YAnnotTable) _eContainer_2).getPrimarykey();
        if ((pk != null)) {
          EList<YAnnotAbstractColumn> _columns = pk.getColumns();
          for (final YAnnotAbstractColumn column : _columns) {
            String _name = column.getName();
            String _name_1 = element.getName();
            boolean _equals = Objects.equal(_name, _name_1);
            if (_equals) {
              return this.imageHelper.getImage("column_pkey.gif");
            }
          }
        }
      }
    }
    return this.imageHelper.getImage("column.gif");
  }
  
  public boolean _isLeaf(final YAnnotAbstractColumn element) {
    return true;
  }
  
  /**
   * Foreign Key
   */
  public void _createChildren(final DocumentRootNode outlineNode, final YAnnotForeignKey element) {
    final Consumer<YAnnotAbstractColumn> _function = (YAnnotAbstractColumn column) -> {
      this.createNode(outlineNode, column);
    };
    element.getColumns().forEach(_function);
  }
  
  public Object _text(final YAnnotForeignKey element) {
    YAnnotRel _relationship = element.getRelationship();
    boolean _tripleNotEquals = (_relationship != null);
    if (_tripleNotEquals) {
      YAnnotEntity _target = element.getRelationship().getTarget();
      boolean _tripleNotEquals_1 = (_target != null);
      if (_tripleNotEquals_1) {
        String _name = element.getRelationship().getName();
        String _plus = (_name + " -> ");
        String _name_1 = element.getRelationship().getTarget().getName();
        return (_plus + _name_1);
      } else {
        return element.getRelationship().getName();
      }
    }
    return null;
  }
  
  public Object _image(final YAnnotForeignKey element) {
    return this.imageHelper.getImage("fk.gif");
  }
  
  /**
   * Entity
   */
  public Object _text(final YAnnotEntity element) {
    return element.getName();
  }
  
  public Object _text(final YAnnotAttr element) {
    YClass _yclass = element.getYclass();
    boolean _tripleNotEquals = (_yclass != null);
    if (_tripleNotEquals) {
      String _name = element.getName();
      String _plus = (_name + " : ");
      String _name_1 = element.getYclass().getName();
      return (_plus + _name_1);
    } else {
      return element.getName();
    }
  }
  
  public Object _text(final YAnnotRel element) {
    String opt = "";
    boolean _isOptional = element.isOptional();
    if (_isOptional) {
      opt = "?";
    }
    String desc = "";
    boolean _isMany = element.isMany();
    if (_isMany) {
      YAnnotEntity _target = element.getTarget();
      boolean _tripleNotEquals = (_target != null);
      if (_tripleNotEquals) {
        String _name = element.getName();
        String _plus = (_name + opt);
        String _plus_1 = (_plus + " -> ");
        String _name_1 = element.getTarget().getName();
        String _plus_2 = (_plus_1 + _name_1);
        String _plus_3 = (_plus_2 + "*");
        desc = _plus_3;
      } else {
        String _name_2 = element.getName();
        String _plus_4 = (_name_2 + opt);
        desc = _plus_4;
      }
    } else {
      YAnnotEntity _target_1 = element.getTarget();
      boolean _tripleNotEquals_1 = (_target_1 != null);
      if (_tripleNotEquals_1) {
        String _name_3 = element.getName();
        String _plus_5 = (_name_3 + opt);
        String _plus_6 = (_plus_5 + " -> ");
        String _name_4 = element.getTarget().getName();
        String _plus_7 = (_plus_6 + _name_4);
        desc = _plus_7;
      } else {
        String _name_5 = element.getName();
        String _plus_8 = (_name_5 + opt);
        desc = _plus_8;
      }
    }
    return this.prepareText(element, desc);
  }
  
  public Object _text(final YProperty element) {
    String tuple = "";
    YTuples _tuples = element.getTuples();
    boolean _tripleNotEquals = (_tuples != null);
    if (_tripleNotEquals) {
      tuple = "<>";
    }
    String _name = element.getName();
    boolean _tripleNotEquals_1 = (_name != null);
    if (_tripleNotEquals_1) {
      return element.getName();
    }
    YClass _type = element.getType();
    boolean _tripleNotEquals_2 = (_type != null);
    if (_tripleNotEquals_2) {
      String _name_1 = element.getName();
      String _plus = (_name_1 + " : ");
      String _name_2 = element.getType().getName();
      String _plus_1 = (_plus + _name_2);
      return (_plus_1 + tuple);
    }
    return "";
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
        YAnnotEntity _entityRef = element.getEntityRef();
        boolean _tripleNotEquals = (_entityRef != null);
        if (_tripleNotEquals) {
          String _name = element.getName();
          String _plus = (_name + " -> ");
          String _name_1 = element.getEntityRef().getName();
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
  
  public boolean _isLeaf(final YProperty element) {
    return true;
  }
  
  public boolean _isLeaf(final YFunction element) {
    return true;
  }
  
  public Object prepareText(final YAnnotRel relationship, final String description) {
    String parent = "";
    boolean _isParent = relationship.isParent();
    if (_isParent) {
      parent = "as parent ";
    }
    StyledString.Styler _createXtextStyleAdapterStyler = this.stylerFactory.createXtextStyleAdapterStyler(this.getTypeKeywordStyleText());
    StyledString _styledString = new StyledString(parent, _createXtextStyleAdapterStyler);
    StyledString.Styler _createXtextStyleAdapterStyler_1 = this.stylerFactory.createXtextStyleAdapterStyler(this.getTypeTextStyleParameter());
    StyledString _styledString_1 = new StyledString(description, _createXtextStyleAdapterStyler_1);
    return _styledString.append(_styledString_1);
  }
  
  public TextStyle getTypeKeywordStyleText() {
    final TextStyle textStyle = new TextStyle();
    textStyle.setColor(Display.getCurrent().getSystemColor(SWT.COLOR_GRAY).getRGB());
    textStyle.setStyle(SWT.ITALIC);
    return textStyle;
  }
  
  public TextStyle getTypeTextStyleParameter() {
    final TextStyle textStyle = new TextStyle();
    textStyle.setColor(Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_FOREGROUND).getRGB());
    textStyle.setStyle(SWT.NORMAL);
    return textStyle;
  }
}
