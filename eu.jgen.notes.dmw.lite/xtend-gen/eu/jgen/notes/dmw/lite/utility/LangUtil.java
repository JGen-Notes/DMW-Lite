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
package eu.jgen.notes.dmw.lite.utility;

import com.google.common.base.Objects;
import com.google.common.collect.Iterables;
import com.google.inject.Inject;
import eu.jgen.notes.dmw.lite.lang.LangPackage;
import eu.jgen.notes.dmw.lite.lang.YAnnot;
import eu.jgen.notes.dmw.lite.lang.YAnnotAbstractColumn;
import eu.jgen.notes.dmw.lite.lang.YAnnotAttr;
import eu.jgen.notes.dmw.lite.lang.YAnnotColumn;
import eu.jgen.notes.dmw.lite.lang.YAnnotColumnLike;
import eu.jgen.notes.dmw.lite.lang.YAnnotDefault;
import eu.jgen.notes.dmw.lite.lang.YAnnotDefaultNumber;
import eu.jgen.notes.dmw.lite.lang.YAnnotDefaultText;
import eu.jgen.notes.dmw.lite.lang.YAnnotEntity;
import eu.jgen.notes.dmw.lite.lang.YAnnotEntityInner;
import eu.jgen.notes.dmw.lite.lang.YAnnotRel;
import eu.jgen.notes.dmw.lite.lang.YAnnotTable;
import eu.jgen.notes.dmw.lite.lang.YAnnotTechnicalDesign;
import eu.jgen.notes.dmw.lite.lang.YClass;
import eu.jgen.notes.dmw.lite.lang.YFunction;
import eu.jgen.notes.dmw.lite.lang.YMember;
import eu.jgen.notes.dmw.lite.lang.YProperty;
import eu.jgen.notes.dmw.lite.runtimes.DMWRuntimeException;
import eu.jgen.notes.dmw.lite.utility.LangLib;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.resource.IContainer;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.resource.IResourceDescription;
import org.eclipse.xtext.resource.IResourceDescriptions;
import org.eclipse.xtext.resource.impl.ResourceDescriptionsProvider;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.IteratorExtensions;

@SuppressWarnings("all")
public class LangUtil {
  @Inject
  @Extension
  private LangLib _langLib;
  
  @Inject
  private ResourceDescriptionsProvider resourceDescriptionsProvider;
  
  @Inject
  private IContainer.Manager containerManager;
  
  public Iterable<YProperty> properties(final YClass c) {
    return Iterables.<YProperty>filter(c.getMembers(), YProperty.class);
  }
  
  public Iterable<YFunction> functions(final YClass c) {
    return Iterables.<YFunction>filter(c.getMembers(), YFunction.class);
  }
  
  public YMember getMemberName(final YMember member) {
    return member;
  }
  
  /**
   * for Entities
   */
  public String getImplementingColumnName(final YAnnotTable table, final YMember member) {
    EList<YAnnotAbstractColumn> _columns = table.getColumns();
    for (final YAnnotAbstractColumn annotAbstractColumn : _columns) {
      EObject _type = annotAbstractColumn.getType();
      if ((_type instanceof YAnnotColumn)) {
        EObject _type_1 = annotAbstractColumn.getType();
        String _name = ((YAnnotColumn) _type_1).getAttrref().getName();
        String _name_1 = member.getName();
        boolean _equals = Objects.equal(_name, _name_1);
        if (_equals) {
          return annotAbstractColumn.getName();
        }
      } else {
        EObject _type_2 = annotAbstractColumn.getType();
        EObject _type_3 = ((YAnnotColumnLike) _type_2).getColumnref().getType();
        String _name_2 = ((YAnnotAbstractColumn) _type_3).getName();
        String _name_3 = member.getName();
        boolean _equals_1 = Objects.equal(_name_2, _name_3);
        if (_equals_1) {
          EObject _type_4 = annotAbstractColumn.getType();
          EObject _type_5 = ((YAnnotColumnLike) _type_4).getColumnref().getType();
          return ((YAnnotAbstractColumn) _type_5).getName();
        }
        EObject _type_6 = annotAbstractColumn.getType();
        EObject _type_7 = ((YAnnotColumnLike) _type_6).getColumnref().getType();
        return ((YAnnotAbstractColumn) _type_7).getName();
      }
    }
    return "";
  }
  
  public YAnnotTable getImplementingTable(final YAnnotEntity entity) {
    final String entityName = entity.getName();
    IResourceDescriptions resourceDescriptions = this.resourceDescriptionsProvider.getResourceDescriptions(entity.eResource());
    IResourceDescription resourceDescription = resourceDescriptions.getResourceDescription(entity.eResource().getURI());
    List<IContainer> _visibleContainers = this.containerManager.getVisibleContainers(resourceDescription, resourceDescriptions);
    for (final IContainer c : _visibleContainers) {
      Iterable<IEObjectDescription> _exportedObjectsByType = c.getExportedObjectsByType(
        LangPackage.Literals.YANNOT_TABLE);
      for (final IEObjectDescription objectDescription : _exportedObjectsByType) {
        EObject _eObjectOrProxy = objectDescription.getEObjectOrProxy();
        if ((_eObjectOrProxy instanceof YAnnotTable)) {
          EObject _eObjectOrProxy_1 = objectDescription.getEObjectOrProxy();
          YAnnotTable table = ((YAnnotTable) _eObjectOrProxy_1);
          boolean _eIsProxy = table.eIsProxy();
          if (_eIsProxy) {
            EObject _eObject = entity.eResource().getResourceSet().getEObject(objectDescription.getEObjectURI(), 
              true);
            table = ((YAnnotTable) _eObject);
          }
          if (((table.getEntityref() != null) && Objects.equal(table.getEntityref().getName(), entityName))) {
            return table;
          }
        }
      }
    }
    return null;
  }
  
  public boolean isTechnicalDesign(final EObject context) {
    List<Notifier> _list = IteratorExtensions.<Notifier>toList(EcoreUtil2.getResourceSet(EcoreUtil2.getRoot(context, true)).getAllContents());
    for (final Notifier element : _list) {
      if ((element instanceof YAnnotTechnicalDesign)) {
        return true;
      }
    }
    return false;
  }
  
  public boolean isAttributeImplemented(final YAnnotTable table, final YAnnotAttr attribute) {
    EList<YAnnotAbstractColumn> _columns = table.getColumns();
    for (final YAnnotAbstractColumn abstractColumn : _columns) {
      EObject _type = abstractColumn.getType();
      if ((_type instanceof YAnnotColumn)) {
        EObject _type_1 = abstractColumn.getType();
        final YAnnotColumn column = ((YAnnotColumn) _type_1);
        String _name = column.getAttrref().getName();
        String _name_1 = attribute.getName();
        boolean _equals = Objects.equal(_name, _name_1);
        if (_equals) {
          return true;
        }
      }
    }
    return false;
  }
  
  public YAnnotTechnicalDesign getTechnicalDesign(final EObject context, final EClass clazz) {
    List<Notifier> _list = IteratorExtensions.<Notifier>toList(EcoreUtil2.getResourceSet(EcoreUtil2.getRoot(context, true)).getAllContents());
    for (final Notifier element : _list) {
      if ((element instanceof YAnnotTechnicalDesign)) {
        return ((YAnnotTechnicalDesign)element);
      }
    }
    return null;
  }
  
  public String getFileSystemPath(final String packname) {
    return packname.replace(".", "/");
  }
  
  public boolean isInverseRelationshipDefinedInTarget(final YAnnotRel annotRel) {
    EList<YAnnotEntityInner> _annots = annotRel.getTarget().getAnnots();
    for (final YAnnotEntityInner element : _annots) {
      if ((element instanceof YAnnotRel)) {
        String _name = ((YAnnotRel) element).getName();
        String _name_1 = annotRel.getInverse().getName();
        boolean _equals = Objects.equal(_name, _name_1);
        if (_equals) {
          return true;
        }
      }
    }
    return false;
  }
  
  public boolean isTypeCompatibleWithDefault(final YAnnotAttr annotAttr) {
    EList<YAnnot> _annots = annotAttr.getAnnots();
    for (final YAnnot annot : _annots) {
      EObject _type = annot.getType();
      if ((_type instanceof YAnnotDefault)) {
        EObject _type_1 = annot.getType();
        final YAnnotDefault annotDefault = ((YAnnotDefault) _type_1);
        EObject _type_2 = annotDefault.getType();
        if ((_type_2 instanceof YAnnotDefaultText)) {
          if ((((Objects.equal(annotAttr.getYclass().getSimpleName(), "XString") || Objects.equal(annotAttr.getYclass().getSimpleName(), "XDate")) || 
            Objects.equal(annotAttr.getYclass().getSimpleName(), "XTime")) || Objects.equal(annotAttr.getYclass().getSimpleName(), "XTimestamp"))) {
            return true;
          } else {
            return false;
          }
        } else {
          EObject _type_3 = annotDefault.getType();
          if ((_type_3 instanceof YAnnotDefaultNumber)) {
            if ((((Objects.equal(annotAttr.getYclass().getSimpleName(), "XInt") || Objects.equal(annotAttr.getYclass().getSimpleName(), "XShort")) || 
              Objects.equal(annotAttr.getYclass().getSimpleName(), "XDouble")) || Objects.equal(annotAttr.getYclass().getSimpleName(), "XLong"))) {
              return true;
            } else {
              return false;
            }
          }
        }
      }
    }
    return true;
  }
  
  public List<YProperty> findPropertiesTypeStructure(final YClass clazz) {
    final ArrayList<YProperty> list = CollectionLiterals.<YProperty>newArrayList();
    EList<YMember> _members = clazz.getMembers();
    for (final YMember member : _members) {
      if (((((member instanceof YProperty) && (this.findStructureDeclaration(((YProperty) member)) != null)) && 
        (this.findStructureDeclaration(((YProperty) member)).getSuperclass() != null)) && 
        Objects.equal(this.findStructureDeclaration(((YProperty) member)).getSuperclass().getSimpleName(), "XStructure"))) {
        list.add(((YProperty) member));
      }
    }
    return list;
  }
  
  public YClass findStructureDeclaration(final YProperty property) {
    final String className = property.getType().getSimpleName();
    final YClass widgetClass = this.findOwningWidgetClass(property);
    EList<YMember> _members = widgetClass.getMembers();
    for (final YMember member : _members) {
      if (((member instanceof YClass) && Objects.equal(((YClass) member).getName(), className))) {
        return ((YClass) member);
      }
    }
    return null;
  }
  
  public YClass findOwningWidgetClass(final EObject object) {
    YClass _xifexpression = null;
    if ((((object instanceof YClass) && (((YClass) object).getSuperclass() != null)) && 
      Objects.equal(((YClass) object).getSuperclass().getSimpleName(), "XWidget"))) {
      return ((YClass) object);
    } else {
      YClass _xifexpression_1 = null;
      EObject _eContainer = object.eContainer();
      boolean _tripleNotEquals = (_eContainer != null);
      if (_tripleNotEquals) {
        _xifexpression_1 = this.findOwningWidgetClass(object.eContainer());
      } else {
        throw new DMWRuntimeException(("Cannot find class of type XWidget owning object: " + object));
      }
      _xifexpression = _xifexpression_1;
    }
    return _xifexpression;
  }
}
