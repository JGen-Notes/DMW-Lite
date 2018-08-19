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
package eu.jgen.notes.dmw.lite.scoping;

import com.google.common.collect.Iterables;
import com.google.inject.Inject;
import eu.jgen.notes.dmw.lite.lang.LangPackage;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.resource.IContainer;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.resource.IResourceDescription;
import org.eclipse.xtext.resource.IResourceDescriptions;
import org.eclipse.xtext.resource.impl.ResourceDescriptionsProvider;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ListExtensions;

@SuppressWarnings("all")
public class LangIndex {
  @Inject
  private ResourceDescriptionsProvider resourceDescriptionsProvider;
  
  @Inject
  private IContainer.Manager container;
  
  /**
   * Entities
   */
  public Map<QualifiedName, IEObjectDescription> getVisibleExternalEntitiesDescriptions(final EObject object) {
    final Iterable<IEObjectDescription> allVisibleEntities = this.getVisibleEntitiesDescriptions(object);
    final Iterable<IEObjectDescription> allExportedEntities = this.getExportedEntitiesEObjectDescriptions(object);
    final Set<IEObjectDescription> difference = IterableExtensions.<IEObjectDescription>toSet(allVisibleEntities);
    difference.removeAll(IterableExtensions.<IEObjectDescription>toSet(allExportedEntities));
    final Function1<IEObjectDescription, QualifiedName> _function = (IEObjectDescription it) -> {
      return it.getQualifiedName();
    };
    return IterableExtensions.<QualifiedName, IEObjectDescription>toMap(difference, _function);
  }
  
  public Iterable<IEObjectDescription> getVisibleEntitiesDescriptions(final EObject object) {
    return this.getVisibleEObjectDescriptions(object, LangPackage.eINSTANCE.getYAnnotEntity());
  }
  
  public Iterable<IEObjectDescription> getExportedEntitiesEObjectDescriptions(final EObject object) {
    return this.getResourceDescription(object).getExportedObjectsByType(LangPackage.eINSTANCE.getYAnnotEntity());
  }
  
  public Iterable<IEObjectDescription> getVisibleEntityDescriptions(final EObject object) {
    return this.getVisibleEObjectDescriptions(object, LangPackage.eINSTANCE.getYAnnotEntity());
  }
  
  /**
   * Classes
   */
  public Map<QualifiedName, IEObjectDescription> getVisibleExternalClassesDescriptions(final EObject object) {
    final Iterable<IEObjectDescription> allVisibleClasses = this.getVisibleClassesDescriptions(object);
    final Iterable<IEObjectDescription> allExportedClasses = this.getExportedClassesEObjectDescriptions(object);
    final Set<IEObjectDescription> difference = IterableExtensions.<IEObjectDescription>toSet(allVisibleClasses);
    difference.removeAll(IterableExtensions.<IEObjectDescription>toSet(allExportedClasses));
    final Function1<IEObjectDescription, QualifiedName> _function = (IEObjectDescription it) -> {
      return it.getQualifiedName();
    };
    return IterableExtensions.<QualifiedName, IEObjectDescription>toMap(difference, _function);
  }
  
  public Iterable<IEObjectDescription> getVisibleClassesDescriptions(final EObject object) {
    return this.getVisibleEObjectDescriptions(object, LangPackage.eINSTANCE.getYClass());
  }
  
  public Iterable<IEObjectDescription> getExportedClassesEObjectDescriptions(final EObject object) {
    return this.getResourceDescription(object).getExportedObjectsByType(LangPackage.eINSTANCE.getYClass());
  }
  
  /**
   * General
   */
  public Iterable<IEObjectDescription> getVisibleEObjectDescriptions(final EObject object, final EClass type) {
    final Function1<IContainer, Iterable<IEObjectDescription>> _function = (IContainer container) -> {
      return container.getExportedObjectsByType(type);
    };
    return Iterables.<IEObjectDescription>concat(ListExtensions.<IContainer, Iterable<IEObjectDescription>>map(this.getVisibleContainers(object), _function));
  }
  
  public List<IContainer> getVisibleContainers(final EObject object) {
    List<IContainer> _xblockexpression = null;
    {
      final IResourceDescriptions index = this.resourceDescriptionsProvider.getResourceDescriptions(object.eResource());
      final IResourceDescription rd = index.getResourceDescription(object.eResource().getURI());
      _xblockexpression = this.container.getVisibleContainers(rd, index);
    }
    return _xblockexpression;
  }
  
  public IResourceDescription getResourceDescription(final EObject object) {
    IResourceDescription _xblockexpression = null;
    {
      final IResourceDescriptions index = this.resourceDescriptionsProvider.getResourceDescriptions(object.eResource());
      _xblockexpression = index.getResourceDescription(object.eResource().getURI());
    }
    return _xblockexpression;
  }
  
  public Iterable<IEObjectDescription> getExportedEObjectDescriptions(final EObject object) {
    return this.getResourceDescription(object).getExportedObjects();
  }
}
