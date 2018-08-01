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
 * 
 */
package eu.jgen.notes.dmw.lite.utility

import com.google.inject.Inject
import eu.jgen.notes.dmw.lite.lang.LangPackage
import eu.jgen.notes.dmw.lite.lang.YAnnotAttr
import eu.jgen.notes.dmw.lite.lang.YAnnotColumn
import eu.jgen.notes.dmw.lite.lang.YAnnotEntity
import eu.jgen.notes.dmw.lite.lang.YAnnotTable
import eu.jgen.notes.dmw.lite.lang.YAnnotTechnicalDesign
import eu.jgen.notes.dmw.lite.lang.YBlock
import eu.jgen.notes.dmw.lite.lang.YClass
import eu.jgen.notes.dmw.lite.lang.YFunction
import eu.jgen.notes.dmw.lite.lang.YMember
import eu.jgen.notes.dmw.lite.lang.YProperty
import eu.jgen.notes.dmw.lite.lang.YReturn
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EObject
import org.eclipse.xtext.EcoreUtil2
import org.eclipse.xtext.resource.IContainer
import org.eclipse.xtext.resource.IEObjectDescription
import org.eclipse.xtext.resource.impl.ResourceDescriptionsProvider
import eu.jgen.notes.dmw.lite.lang.YAnnotRel

class LangUtil {

	@Inject extension LangLib
	@Inject ResourceDescriptionsProvider resourceDescriptionsProvider;
	@Inject IContainer.Manager containerManager;

	def properties(YClass c) {
		c.members.filter(YProperty)
	}

	def functions(YClass c) {
		c.members.filter(YFunction)
	}

	def returnStatement(YFunction m) {
		m.body.returnStatement
	}

	def returnStatement(YBlock block) {
		block.statements.filter(YReturn).head
	}

	def classHierarchy(YClass c) {
		val visited = newLinkedHashSet()

		var current = c.superclass
		while (current !== null && !visited.contains(current)) {
			visited.add(current)
			current = current.superclass
		}

		val object = c.getLangObjectClass
		if (object !== null)
			visited.add(object)

		visited
	}

	def classHierarchyMethods(YClass c) {
		// reverse the list so that methods in subclasses
		// will be added later to the map, thus overriding
		// the one already present in the superclasses
		// if the methods have the same name
		// do something
		c.classHierarchy.toList.reverseView.map[functions].flatten.toMap[name]
	}

	def classHierarchyMembers(YClass c) {
		c.classHierarchy.map[members].flatten
	}

	def memberAsString(YMember m) {
		val a = m.eContainer as YClass
		a.name + if (m instanceof YFunction)
			"(" + m.params.map[type.name].join(", ") + ")"
		else
			""
	}

	def memberAsStringWithType(YMember m) {

		// println(m)
		m.memberAsString + " : " // + m.type.name
	}

	def getMemberName(YMember member) {
		member
	}

	/*
	 *  for Entities
	 */
	def entityHierarchy(YAnnotEntity c) {
		val visited = newLinkedHashSet()
		var current = c.superannot
		while (current !== null && !visited.contains(current)) {
			visited.add(current)
			current = current.superannot
		}
		val object = c.getLangObjectClass
		if (object !== null)
			visited.add(object)
		visited
	}

	def YAnnotTable getImplementingTable(YAnnotEntity entity) {
		val entityName = entity.name
		var resourceDescriptions = resourceDescriptionsProvider.getResourceDescriptions(entity.eResource());
		var resourceDescription = resourceDescriptions.getResourceDescription(entity.eResource().getURI());
		for (IContainer c : containerManager.getVisibleContainers(resourceDescription, resourceDescriptions)) {
			for (IEObjectDescription objectDescription : c.getExportedObjectsByType(
				LangPackage.Literals.YANNOT_TABLE)) {
				if (objectDescription.getEObjectOrProxy instanceof YAnnotTable) {
					var table = objectDescription.getEObjectOrProxy as YAnnotTable
					if (table.eIsProxy) {
						table = entity.eResource.resourceSet.getEObject(objectDescription.getEObjectURI,
							true) as YAnnotTable
					}
					if (table.entityref !== null && table.entityref.name == entityName) {
						return table
					}
				}
			}
		}
		return null
	}

	def boolean isTechnicalDesign(EObject context) { 
		for (element : EcoreUtil2.getResourceSet(EcoreUtil2.getRoot(context, true)).allContents.toList) {
			if (element instanceof YAnnotTechnicalDesign) {
				return true
			}
		}
		return false
	}

	def boolean isAttributeImplemented(YAnnotTable table, YAnnotAttr attribute) {
		for (abstractColumn : table.columns) {
			if (abstractColumn.type instanceof YAnnotColumn) {
				val column = abstractColumn.type as YAnnotColumn
				if (column.attrref.name == attribute.name) {
					return true
				}
			}
		}
		return false
	}

	def YAnnotTechnicalDesign getTechnicalDesign(EObject context, EClass clazz) {
		for (element : EcoreUtil2.getResourceSet(EcoreUtil2.getRoot(context, true)).allContents.toList) {
			if (element instanceof YAnnotTechnicalDesign) {
				return element
			}
		}
		return null
	}
	
	def String getFileSystemPath(String packname) {
		packname.replace(".","/")
	}
	
	def boolean isInverseRelationshipDefinedInTarget(YAnnotRel annotRel) {
		for (element : annotRel.target.annots) {
			if (element instanceof YAnnotRel) {
				if ((element as YAnnotRel).name == annotRel.inverse.name) {
					return true
				}
			}
		}
		return false
	}

}
