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
package eu.jgen.notes.dmw.lite.scoping

import com.google.inject.Inject
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EObject
import org.eclipse.xtext.resource.IContainer
import org.eclipse.xtext.resource.impl.ResourceDescriptionsProvider
import eu.jgen.notes.dmw.lite.lang.LangPackage

class LangIndex {
	@Inject ResourceDescriptionsProvider resourceDescriptionsProvider
	@Inject IContainer.Manager container

	/*******************************************************
	 * Entities
	 *******************************************************/
	def getVisibleExternalEntitiesDescriptions(EObject object) {
		val allVisibleEntities = object.getVisibleEntitiesDescriptions
		val allExportedEntities = object.getExportedEntitiesEObjectDescriptions
		val difference = allVisibleEntities.toSet
		difference.removeAll(allExportedEntities.toSet)
		return difference.toMap[qualifiedName]
	}

	def getVisibleEntitiesDescriptions(EObject object) {
		object.getVisibleEObjectDescriptions(LangPackage.eINSTANCE.YAnnotEntity)
	}

	def getExportedEntitiesEObjectDescriptions(EObject object) {
		object.getResourceDescription.getExportedObjectsByType(LangPackage.eINSTANCE.YAnnotEntity)
	}

	def getVisibleEntityDescriptions(EObject object) {
		object.getVisibleEObjectDescriptions(LangPackage.eINSTANCE.YAnnotEntity)
	}

	/*******************************************************
	 * Classes
	 *******************************************************/
	def getVisibleExternalClassesDescriptions(EObject object) {
		val allVisibleClasses = object.getVisibleClassesDescriptions
		val allExportedClasses = object.getExportedClassesEObjectDescriptions
		val difference = allVisibleClasses.toSet
		difference.removeAll(allExportedClasses.toSet)
		return difference.toMap[qualifiedName]
	}

	def getVisibleClassesDescriptions(EObject object) {
		object.getVisibleEObjectDescriptions(LangPackage.eINSTANCE.YClass)
	}

	def getExportedClassesEObjectDescriptions(EObject object) {
		object.getResourceDescription.getExportedObjectsByType(LangPackage.eINSTANCE.YClass)
	}

	/*******************************************************
	 * General
	 *******************************************************/
	def getVisibleEObjectDescriptions(EObject object, EClass type) {
		object.getVisibleContainers.map [ container |
			container.getExportedObjectsByType(type)
		].flatten
	}

	def getVisibleContainers(EObject object) {
		val index = resourceDescriptionsProvider.getResourceDescriptions(object.eResource)
		val rd = index.getResourceDescription(object.eResource.getURI)
		container.getVisibleContainers(rd, index)
	}

	def getResourceDescription(EObject object) {
		val index = resourceDescriptionsProvider.getResourceDescriptions(object.eResource)
		index.getResourceDescription(object.eResource.getURI)
	}

	def getExportedEObjectDescriptions(EObject object) {
		object.getResourceDescription.getExportedObjects
	}
	
 
}
