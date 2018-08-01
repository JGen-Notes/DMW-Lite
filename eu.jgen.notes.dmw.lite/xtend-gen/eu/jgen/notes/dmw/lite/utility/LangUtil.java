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
import eu.jgen.notes.dmw.lite.lang.YAnnotAbstractColumn;
import eu.jgen.notes.dmw.lite.lang.YAnnotAttr;
import eu.jgen.notes.dmw.lite.lang.YAnnotColumn;
import eu.jgen.notes.dmw.lite.lang.YAnnotEntity;
import eu.jgen.notes.dmw.lite.lang.YAnnotEntityInner;
import eu.jgen.notes.dmw.lite.lang.YAnnotRel;
import eu.jgen.notes.dmw.lite.lang.YAnnotTable;
import eu.jgen.notes.dmw.lite.lang.YAnnotTechnicalDesign;
import eu.jgen.notes.dmw.lite.lang.YBlock;
import eu.jgen.notes.dmw.lite.lang.YClass;
import eu.jgen.notes.dmw.lite.lang.YFunction;
import eu.jgen.notes.dmw.lite.lang.YMember;
import eu.jgen.notes.dmw.lite.lang.YParameter;
import eu.jgen.notes.dmw.lite.lang.YProperty;
import eu.jgen.notes.dmw.lite.lang.YReturn;
import eu.jgen.notes.dmw.lite.utility.LangLib;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
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
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.IteratorExtensions;
import org.eclipse.xtext.xbase.lib.ListExtensions;

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
  
  public YReturn returnStatement(final YFunction m) {
    return this.returnStatement(m.getBody());
  }
  
  public YReturn returnStatement(final YBlock block) {
    return IterableExtensions.<YReturn>head(Iterables.<YReturn>filter(block.getStatements(), YReturn.class));
  }
  
  public LinkedHashSet<YClass> classHierarchy(final YClass c) {
    LinkedHashSet<YClass> _xblockexpression = null;
    {
      final LinkedHashSet<YClass> visited = CollectionLiterals.<YClass>newLinkedHashSet();
      YClass current = c.getSuperclass();
      while (((current != null) && (!visited.contains(current)))) {
        {
          visited.add(current);
          current = current.getSuperclass();
        }
      }
      final YClass object = this._langLib.getLangObjectClass(c);
      if ((object != null)) {
        visited.add(object);
      }
      _xblockexpression = visited;
    }
    return _xblockexpression;
  }
  
  public Map<String, YFunction> classHierarchyMethods(final YClass c) {
    final Function1<YClass, Iterable<YFunction>> _function = (YClass it) -> {
      return this.functions(it);
    };
    final Function1<YFunction, String> _function_1 = (YFunction it) -> {
      return it.getName();
    };
    return IterableExtensions.<String, YFunction>toMap(Iterables.<YFunction>concat(ListExtensions.<YClass, Iterable<YFunction>>map(ListExtensions.<YClass>reverseView(IterableExtensions.<YClass>toList(this.classHierarchy(c))), _function)), _function_1);
  }
  
  public Iterable<YMember> classHierarchyMembers(final YClass c) {
    final Function1<YClass, EList<YMember>> _function = (YClass it) -> {
      return it.getMembers();
    };
    return Iterables.<YMember>concat(IterableExtensions.<YClass, EList<YMember>>map(this.classHierarchy(c), _function));
  }
  
  public String memberAsString(final YMember m) {
    String _xblockexpression = null;
    {
      EObject _eContainer = m.eContainer();
      final YClass a = ((YClass) _eContainer);
      String _name = a.getName();
      String _xifexpression = null;
      if ((m instanceof YFunction)) {
        final Function1<YParameter, String> _function = (YParameter it) -> {
          return it.getType().getName();
        };
        String _join = IterableExtensions.join(ListExtensions.<YParameter, String>map(((YFunction)m).getParams(), _function), ", ");
        String _plus = ("(" + _join);
        _xifexpression = (_plus + ")");
      } else {
        _xifexpression = "";
      }
      _xblockexpression = (_name + _xifexpression);
    }
    return _xblockexpression;
  }
  
  public String memberAsStringWithType(final YMember m) {
    String _memberAsString = this.memberAsString(m);
    return (_memberAsString + " : ");
  }
  
  public YMember getMemberName(final YMember member) {
    return member;
  }
  
  /**
   * for Entities
   */
  public LinkedHashSet<EObject> entityHierarchy(final YAnnotEntity c) {
    LinkedHashSet<EObject> _xblockexpression = null;
    {
      final LinkedHashSet<EObject> visited = CollectionLiterals.<EObject>newLinkedHashSet();
      YAnnotEntity current = c.getSuperannot();
      while (((current != null) && (!visited.contains(current)))) {
        {
          visited.add(current);
          current = current.getSuperannot();
        }
      }
      final YClass object = this._langLib.getLangObjectClass(c);
      if ((object != null)) {
        visited.add(object);
      }
      _xblockexpression = visited;
    }
    return _xblockexpression;
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
}
