package eu.jgen.notes.dmw.lite.generator

import org.eclipse.xtext.generator.IGenerator
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.xtext.generator.IFileSystemAccess
import eu.jgen.notes.dmw.lite.lang.YAnnotTechnicalDesign
import eu.jgen.notes.dmw.lite.lang.YAnnotTable
import eu.jgen.notes.dmw.lite.lang.YAnnotAbstractColumn
import eu.jgen.notes.dmw.lite.lang.YAnnotForeignKey
import eu.jgen.notes.dmw.lite.lang.YAnnotColumn
import eu.jgen.notes.dmw.lite.lang.YAnnotColumnLike
import eu.jgen.notes.dmw.lite.lang.YAnnotSwift
import com.google.inject.Inject
import eu.jgen.notes.dmw.lite.utility.LangJavaGeneratorHelper
import eu.jgen.notes.dmw.lite.lang.YWidget
import eu.jgen.notes.dmw.lite.utility.LangUtil
import eu.jgen.notes.dmw.lite.utility.LangSwiftGeneratorHelper
import eu.jgen.notes.dmw.lite.lang.YClass
import eu.jgen.notes.dmw.lite.lang.YProperty

class LangSwiftWidgetGenerator implements IGenerator {

	@Inject extension LangUtil
	@Inject extension LangSwiftGeneratorHelper

	override doGenerate(Resource input, IFileSystemAccess fsa) {
		input.allContents.filter[element|element instanceof YAnnotSwift].forEach [ element |
			val annotSwift = element as YAnnotSwift
			generatePackageModule(fsa, annotSwift)
			input.allContents.filter[element2|element2 instanceof YWidget].forEach [ element2 |
				val widget = element2 as YWidget
				generateWidget(fsa, widget, annotSwift)
			]
		]
	}

	def generateWidget(IFileSystemAccess fsa, YWidget widget, YAnnotSwift annotSwift) {
		widget.classes.forEach [ clazz |
			if (clazz.superclass !== null && clazz.superclass.name == "Widget") {
				val body = '''
					// dmw-generator-version: 0.2
															
					//  Created by Marek Stankiewicz on 17.04.2018.
					//  Copyright © 2018 JGen. All rights reserved.
															
					import Foundation
					
					«clazz.documentation»
					class «clazz.name»  {
						   «generateInnerClasses(clazz)»
						   
						   «generateTableClasses(clazz)»
					
					}
				'''
				fsa.generateFile(
					annotSwift.name + "/Sources/" + annotSwift.name + "/" + clazz.name + ".swift",
					LangOutputProvider.SWIFT,
					'''		
						
						«body»
					'''
				)

			}
		]
	}
	
	protected def String generateTableClasses(YClass clazz) {
		
		for ( inner : clazz.inners) {
			if(inner.entity !== null) {
				println(inner.entity.name)
			}
		}
		 return ""
	}

	protected def String generateInnerClasses(YClass clazz) {
		'''
			«FOR innerclazz : clazz.inners»
			«generateClass(innerclazz)»
			
			«ENDFOR»
		'''
	}

	protected def String generateClass(YClass innerclazz) {
		if (innerclazz.superclass !== null && innerclazz.superclass.name == "Structure") {
			'''
				«innerclazz.documentation»  
				public class «innerclazz.name»  {
					«FOR member : innerclazz.members»
						«IF member instanceof YProperty»
							«generatePropertyForStructure(member as YProperty)»
						«ENDIF»
					«ENDFOR»
					«generateFunctionInit(innerclazz)» 
				}
			'''
		}
	}
	
	def generateFunctionInit(YClass clazz) {
		'''
		init() {
			«FOR member : clazz.members»
			«IF member instanceof YProperty»
			«generatePropertyInit(member as YProperty)»
			«ENDIF»
			«ENDFOR»
		}
		'''	 
	}
	
	def generatePropertyInit(YProperty property) {
		 		switch (property.type.name) {
			case "Double": {
				return property.name +" = " + "0;"
			}
			case "Int": {
				return property.name +" = " + "0;"
			}
			case "Short": {
				return property.name +" = " + "0;"
			}
			case "String": {
				return property.name +" = " + "\"\";"
			}
			case "Date": {
				return property.name +" = " + "Date();"
			}
			case "Time": {
				return property.name +" = " + "Date();"
			}
			default: {
				return "unknown"
			}
		}
	}

	protected def String generatePropertyForStructure(YProperty property) {
		'''
		«property.documentation»  
		var «property.name» : «property.generatePropertyType»;'''
	}

	protected def String generatePropertyType(YProperty property) {
		switch (property.type.name) {
			case "Double": {
				return "Double"
			}
			case "Int": {
				return "Int"
			}
			case "Short": {
				return "Int16"
			}
			case "String": {
				return "String"
			}
			case "Date": {
				return "Date"
			}
			case "Time": {
				return "Date"
			}
			default: {
				return "unknown"
			}
		}
	}

	protected def void generatePackageModule(IFileSystemAccess fsa, YAnnotSwift annotSwift) {
		fsa.generateFile(
			annotSwift.name + "/" + "Package" + ".swift",
			LangOutputProvider.SWIFT,
			'''				
				// swift-tools-version:4.0
				// The swift-tools-version declares the minimum version of Swift required to build this package.				
				
				import PackageDescription
				
				let package = Package(
				    name: "«annotSwift.name»",
				    products: [
				        // Products define the executables and libraries produced by a package, and make them visible to other packages.
				        .library(
				            name: "«annotSwift.name»",
				            targets: ["«annotSwift.name»"]),
				    ],
				    dependencies: [
				        // Dependencies declare other packages that this package depends on.
				        // .package(url: /* package url */, from: "1.0.0"),
				    ],
				    targets: [
				        // Targets are the basic building blocks of a package. A target can define a module or a test suite.
				        // Targets can depend on other targets in this package, and on products in packages which this package depends on.
				        .target(
				            name: "«annotSwift.name»",
				            dependencies: []),
				        .testTarget(
				            name: "«annotSwift.name»Tests",
				            dependencies: ["«annotSwift.name»"]),
				    ]
				)
			'''
		)

		// generate .gitignore
		fsa.generateFile(
			annotSwift.name + "/" + ".gitignore",
			LangOutputProvider.SWIFT,
			'''				
				.DS_Store
				/.build
				/Packages
				/*.xcodeproj
			'''
		)

		// generate .gitignore
		fsa.generateFile(
			annotSwift.name + "/" + "README.md",
			LangOutputProvider.SWIFT,
			'''				
				# «annotSwift.name»
				
				«annotSwift.documentation»
			'''
		)

	}

}
