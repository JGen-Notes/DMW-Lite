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
package eu.jgen.notes.dmw.lite.validation

import com.google.common.collect.HashMultimap
import com.google.inject.Inject
import eu.jgen.notes.dmw.lite.lang.LangPackage
import eu.jgen.notes.dmw.lite.lang.YAnnotAttr
import eu.jgen.notes.dmw.lite.lang.YAnnotColumn
import eu.jgen.notes.dmw.lite.lang.YAnnotEntity
import eu.jgen.notes.dmw.lite.lang.YAnnotId
import eu.jgen.notes.dmw.lite.lang.YAnnotRel
import eu.jgen.notes.dmw.lite.lang.YBlock
import eu.jgen.notes.dmw.lite.lang.YClass
import eu.jgen.notes.dmw.lite.lang.YExpression
import eu.jgen.notes.dmw.lite.lang.YFunction
import eu.jgen.notes.dmw.lite.lang.YMemberSelection
import eu.jgen.notes.dmw.lite.lang.YNamedElement
import eu.jgen.notes.dmw.lite.lang.YProperty
import eu.jgen.notes.dmw.lite.lang.YReturn
import eu.jgen.notes.dmw.lite.lang.YSuper
import eu.jgen.notes.dmw.lite.lang.YVariableDeclaration
import eu.jgen.notes.dmw.lite.lang.YWidget
import eu.jgen.notes.dmw.lite.scoping.LangIndex
import eu.jgen.notes.dmw.lite.typing.LangTypeComputer
import eu.jgen.notes.dmw.lite.typing.LangTypeConformance
import eu.jgen.notes.dmw.lite.utility.LangUtil
import java.util.Map
import org.eclipse.xtext.naming.IQualifiedNameProvider
import org.eclipse.xtext.naming.QualifiedName
import org.eclipse.xtext.resource.IEObjectDescription
import org.eclipse.xtext.validation.Check

import static extension org.eclipse.xtext.EcoreUtil2.*
import eu.jgen.notes.dmw.lite.lang.YAnnotAbstractColumn
import eu.jgen.notes.dmw.lite.lang.YAnnotTable
import eu.jgen.notes.dmw.lite.lang.YAnnotColumnLike
import eu.jgen.notes.dmw.lite.lang.YAnnotDatabase
import org.eclipse.xtext.validation.CheckType
import eu.jgen.notes.dmw.lite.services.LangGrammarAccess.YTypedDeclarationElements

/**
 * This class contains custom validation rules. 
 * 
 * See https://www.eclipse.org/Xtext/documentation/303_runtime_concepts.html#validation
 */
class LangValidator extends AbstractLangValidator {

	protected static val ISSUE_CODE_PREFIX = " eu.jgen.notes.dmw.lite."
	public static val HIERARCHY_CYCLE = ISSUE_CODE_PREFIX + "HierarchyCycle"
	public static val PROPERTY_SELECTION_ON_FUNCTION = ISSUE_CODE_PREFIX + "FieldSelectionOnMethod"
	public static val FUNCTION_INVOCATION_ON_PROPERTY = ISSUE_CODE_PREFIX + "FunctionInvocationOnProperty"
	public static val UNREACHABLE_CODE = ISSUE_CODE_PREFIX + "UnreachableCode"
	public static val FUNCTION_FINAL_RETURN = ISSUE_CODE_PREFIX + "MissingFinalReturn"
	public static val DUPLICATE_ELEMENT = ISSUE_CODE_PREFIX + "DuplicateElement"
	public static val INCOMPATIBLE_TYPES = ISSUE_CODE_PREFIX + "IncompatibleTypes"
	public static val INVALID_ARGS = ISSUE_CODE_PREFIX + "InvalidArgs"
	public static val WRONG_FUNCTION_OVERRIDE = ISSUE_CODE_PREFIX + "WrongFunctionOverride"
	public static val MEMBER_NOT_ACCESSIBLE = ISSUE_CODE_PREFIX + "MemberNotAccessible"
	public static val DUPLICATE_CLASS = ISSUE_CODE_PREFIX + "DuplicateClass"
	public static val DUPLICATE_ENTITY = ISSUE_CODE_PREFIX + "DuplicateEntity"
	public static val WRONG_SUPER_USAGE = ISSUE_CODE_PREFIX + "WrongSuperUsage"
	public static val REDUCED_ACCESSIBILITY = ISSUE_CODE_PREFIX + "ReducedAccessibility"
	public static val MISSING_ENTITY_REFERENCE = ISSUE_CODE_PREFIX + "MissingEntityReference"
	public static val WRONG_TYPE = ISSUE_CODE_PREFIX + "WrongType"
	public static val WRONG_CROSS_REFERENCE = ISSUE_CODE_PREFIX + "WrongCrossReference"
	public static val WRONG_INVERT_REFERENCE = ISSUE_CODE_PREFIX + "WrongInvertReference"
	public static val ATTRIBUTE_NO_TECH_DESIGN = ISSUE_CODE_PREFIX + "AttributeNoTechDesign"
	public static val ENTITY_NO_TECH_DESIGN = ISSUE_CODE_PREFIX + "EntityNoTechDesign"
	public static val TABLE_DOES_NOT_HAVE_COLUMNS = ISSUE_CODE_PREFIX + "TableDoesNotHaveColumns"
	public static val TABLE_NAME_NOT_UNIQUE = ISSUE_CODE_PREFIX + "TableNameNotUnique"
	public static val COLUMN_NAME_NOT_UNIQUE = ISSUE_CODE_PREFIX + "ColumnNameNotUnique"
	public static val IDENTIFIER_NO_TECH_DESIGN = ISSUE_CODE_PREFIX + "IdentifgierNoTechDesign"
	public static val RELATIONSSHIP_NOT_IMPLEMENTED = ISSUE_CODE_PREFIX + "RelationshipNotImplemented"
	public static val UNSUPPORTED_DATABASE = ISSUE_CODE_PREFIX + "UnsupportedDatabase"
	public static val MISSING_INVERSE_REALTIONSHIP = ISSUE_CODE_PREFIX + "MissingInverseRelationship"
	public static val MANY_TO_MANY_NOT_SUPPORTED = ISSUE_CODE_PREFIX + "ManyToManyNotSuported"
	public static val ONE_TO_ONE_MANDATORY_NOT_SUPPORTED = ISSUE_CODE_PREFIX + "OneToOneMandatoryNotSuported"
	public static val INVERSE_RELATIONSHIP_CANNOT_BE_ITSELF = ISSUE_CODE_PREFIX + "MatchingInverseCannotMatchItself"
	public static val INVERSE_RELATIONSHIP_DOES_NOT_EXIST_IN_TAGET = ISSUE_CODE_PREFIX + "InverseDoesNotExistInTarget"
	public static val NO_DESGNATED_PARENT = ISSUE_CODE_PREFIX + "NoDesignatedParent"
	public static val ONLY_ONE_DESGNATED_PARENT = ISSUE_CODE_PREFIX + "OnlyOneDesignatedParent"
//	public static val CLASS_NEED_TO_BE_EXTENDED = ISSUE_CODE_PREFIX + "ClassNeedToBeExtended"
	public static val CLASS_NEED_TO_HAVE_PROPERTIES = ISSUE_CODE_PREFIX + "ClassNeedToHaveProperties"
	public static val CLASS_NAME_FIRST_CHARACTER_NOT_CAPITAL = ISSUE_CODE_PREFIX + "ClassNameFirstCharacterNotCapital"
	public static val ENTITY_NAME_FIRST_CHARACTER_NOT_CAPITAL = ISSUE_CODE_PREFIX + "EntityNameFirstCharacterNotCapital"
	public static val ATTRIBUTE_NAME_FIRST_CHARACTER_NOT_LOWERCASE = ISSUE_CODE_PREFIX +
		"AttributeNameFirstCharacterNotLowercase"
	public static val FUNCTION_NAME_FIRST_CHARACTER_NOT_LOWERCASE = ISSUE_CODE_PREFIX +
		"FunctionNameFirstCharacterNotLowercase"
	public static val PROPERTY_NAME_FIRST_CHARACTER_NOT_LOWERCASE = ISSUE_CODE_PREFIX +
		"PropertyNameFirstCharacterNotLowercase"
	public static val VARIABLE_NAME_FIRST_CHARACTER_NOT_LOWERCASE = ISSUE_CODE_PREFIX +
		"VariableNameFirstCharacterNotLowercase"
	public static val ATTRIBUTE_TYPE_NOT_COMP_WITH_DEFAULT = ISSUE_CODE_PREFIX + "AttributeTypeNotCompatibleWithDefault"

	// VARIABLE_NAME_FIRST_CHARACTER_NOT_LOWERCASE
	@Inject extension LangUtil
	@Inject extension LangTypeComputer
	@Inject extension LangTypeConformance
	@Inject extension LangAccessibility
	@Inject extension LangIndex
	@Inject extension IQualifiedNameProvider

	/*********************************************************
	 * Supported Database
	 ********************************************************/
	@Check
	def checkSupportedDatabase(YAnnotDatabase annotDatabase) {
		if (annotDatabase.name !== null) {
			if (annotDatabase.name == "Derby" || annotDatabase.name == "MySQL" || annotDatabase.name == "SQLite" ||
				annotDatabase.name == "PostgreSQL" || annotDatabase.name == "MongoDB") {
				return
			} else {
				error("This database is not supported yet.", LangPackage.eINSTANCE.YAnnotDatabase_Name,
					UNSUPPORTED_DATABASE, annotDatabase.name)
			}
		}
	}

	/*********************************************************
	 * Entities
	 ********************************************************/
	@Check(CheckType.FAST)
	def checkEntityHierarchy(YAnnotEntity entity) {
		if (entity.entityHierarchy.contains(entity)) {
			error("cycle in hierarchy of entities '" + entity.name + "'", LangPackage.eINSTANCE.YAnnotEntity_Superannot,
				HIERARCHY_CYCLE, entity.superannot.name)
		}
	}

	@Check def void checkNoDuplicateEntities(YWidget widget) {
		val multiMap = HashMultimap.create()
		for (annotEntity : widget.annotations.filter[it.type instanceof YAnnotEntity]) {
			multiMap.put((annotEntity.type as YAnnotEntity).name, annotEntity.type)
		}
		for (entry : multiMap.asMap.entrySet) {
			val duplicates = entry.value
			if (duplicates.size > 1) {
				for (d : duplicates)
					error("Duplicate " + "entity" + " '" + (d as YAnnotEntity).name + "'", d,
						LangPackage.eINSTANCE.YAnnotEntity_Name, DUPLICATE_ELEMENT)
			}
		}
	}

	// perform this check only on file save
	@Check // (CheckType.NORMAL)
	def checkDuplicateEntitiesAcrossFiles(YWidget widget) {
		val externalEntities = widget.getVisibleExternalEntitiesDescriptions
		for (annot : widget.annotations) {
			if (annot.type instanceof YAnnotEntity) {
				val annotName = annot.type.fullyQualifiedName
				if (externalEntities.containsKey(annotName)) {
					error("The entity " + (annot.type as YAnnotEntity).name + " is already defined", annot.type,
						LangPackage.eINSTANCE.YAnnotEntity_Name, DUPLICATE_ENTITY)
				}
			}
		}
	}

	@Check
	def void checkNoDuplicateAttributes(YAnnotEntity annotEntity) {
		val multiMap = HashMultimap.create()
		for (annot : annotEntity.annots) {
			if (annot instanceof YAnnotAttr) {
				multiMap.put((annot as YAnnotAttr).name, annot)
			}
		}
		for (entry : multiMap.asMap.entrySet) {
			val duplicates = entry.value
			if (duplicates.size > 1) {
				for (duplicate : duplicates)
					error("Duplicate " + "attribute" + " '" + (duplicate as YAnnotAttr).name + "'", duplicate,
						LangPackage.eINSTANCE.YAnnotAttr_Name, DUPLICATE_ELEMENT)
			}
		}
	}

	@Check
	def void checkNoDuplicateIdentifiers(YAnnotEntity annotEntity) {
		val multiMap = HashMultimap.create()
		for (annot : annotEntity.annots) {
			if (annot instanceof YAnnotId) {
				multiMap.put((annot as YAnnotId).name, annot)
			}
		}
		for (entry : multiMap.asMap.entrySet) {
			val duplicates = entry.value
			if (duplicates.size > 1) {
				for (duplicate : duplicates)
					error("Duplicate " + "identifier" + " '" + (duplicate as YAnnotId).name + "'", duplicate,
						LangPackage.eINSTANCE.YAnnotId_Name, DUPLICATE_ELEMENT)
			}
		}
	}

	@Check
	def void checkNoDuplicateRelationship(YAnnotEntity annotEntity) {
		val multiMap = HashMultimap.create()
		for (annot : annotEntity.annots) {
			if (annot instanceof YAnnotRel) {
				multiMap.put((annot as YAnnotRel).name + (annot as YAnnotRel).target.name, annot)
			}
		}
		for (entry : multiMap.asMap.entrySet) {
			val duplicates = entry.value
			if (duplicates.size > 1) {
				for (duplicate : duplicates)
					error("Duplicate " + "relationship" + " '" + (duplicate as YAnnotRel).name + "'", duplicate,
						LangPackage.eINSTANCE.YAnnotRel_Name, DUPLICATE_ELEMENT)
			}
		}
	}

	@Check
	def void checkInverseRelationship(YAnnotEntity annotEntity) {
		for (element : annotEntity.annots) {
			if (element instanceof YAnnotRel) {
				doCheckRelationshipCorrectness(element as YAnnotRel)
			}
		}
	}

	@Check
	def void checkFunctionInvocation(YMemberSelection memberSelection) {
		if (memberSelection.isFunctioninvocation) {
			if (memberSelection.member.name == "moveView") {
				doValidateMoveStructureFunction(memberSelection)
			}
		}
	}

	def private doValidateMoveStructureFunction(YMemberSelection memberSelection) {
		println(memberSelection.member.name)
		memberSelection.args.forEach [ element |
			println(element)
			val a = element as YMemberSelection
			val b = a.member as YProperty
			println(b.name + " : " + b.type.name + " -> " + b.type.entityRef.name)
		]
	}

	/*********************************************************
	 * Classes YMemberSelection
	 ********************************************************/
	@Check
	def checkPropertyReferenceToAtttribute(YProperty property) {
		if (property.attrRef === null) {
			return
		}
		val parent = property.eContainer as YClass
		if (parent.entityRef === null) {
			error("Entity has to implement entity type before pointing to attribute",
				LangPackage.eINSTANCE.YProperty_AttrRef, MISSING_ENTITY_REFERENCE, property.name)
			return
		}
		if (property.attrRef.name == property.name) {
			if (property.type.name != property.attrRef.yclass.name) {
				error("Attribute type does not match property type", LangPackage.eINSTANCE.YMember_Type, WRONG_TYPE,
					property.type.name)
			}
			val name = (property.attrRef.eContainer as YAnnotEntity).name
			if (name != parent.entityRef.name) {
				error("Attribute does not belong to the chosen entity", LangPackage.eINSTANCE.YProperty_AttrRef,
					WRONG_CROSS_REFERENCE, property.name)
			}
		} else {
			error("Cannot find matching attribute for selected entity type", LangPackage.eINSTANCE.YProperty_AttrRef,
				MISSING_ENTITY_REFERENCE, property.name)
		}
	}

	@Check
	def checkClassHierarchy(YClass yclass) {
		if (yclass.classHierarchy.contains(yclass)) {
			error("cycle in hierarchy of class '" + yclass.name + "'", LangPackage.eINSTANCE.YClass_Superclass,
				HIERARCHY_CYCLE, yclass.superclass.name)
		}
	}

	@Check
	def void checkMemberSelection(YMemberSelection memberSelection) {
		val member = memberSelection.member

		if (member instanceof YProperty && memberSelection.functioninvocation)
			error(
				'''Function invocation on a property''', LangPackage.eINSTANCE.YMemberSelection_Functioninvocation,
				LangValidator.FUNCTION_INVOCATION_ON_PROPERTY)
		else if (member instanceof YFunction && !memberSelection.functioninvocation)
			error(
				'''Property selection on a function''',
				LangPackage.eINSTANCE.YMemberSelection_Member,
				LangValidator.PROPERTY_SELECTION_ON_FUNCTION
			)
	}

	@Check
	def void checkUnreachableCode(YBlock block) {
		val statements = block.statements
		for (var i = 0; i < statements.length - 1; i++) {
			if (statements.get(i) instanceof YReturn) {
				// put the error on the statement after the return
				error("Unreachable code", statements.get(i + 1), null, // EStructuralFeature
				UNREACHABLE_CODE)
				return // no need to report further errors
			}
		}
	}

	// @Check  - need to detect unreachable code
	//
	def void checkMethodEndsWithReturn(YFunction function) {
		if (function.returnvalue) {
			if (function.returnStatement === null) {
				error(
					"Function must end with a return statement",
					LangPackage.eINSTANCE.YFunction_Body,
					LangValidator.FUNCTION_FINAL_RETURN
				)
			}
		}
	}

	@Check def void checkNoDuplicateClasses(YWidget widget) {
		checkNoDuplicateElements(widget.classes, "class")
	}

	@Check def void checkNoDuplicateMembers(YClass yclass) {
		checkNoDuplicateElements(yclass.properties, "property")
		checkNoDuplicateElements(yclass.functions, "function")
	}

	@Check def void checkNoDuplicateSymbols(YFunction function) {
		checkNoDuplicateElements(function.params, "parameter")
		checkNoDuplicateElements(function.body.getAllContentsOfType(YVariableDeclaration), "variable")
	}

	@Check def void checkConformance(YExpression expression) {
		val actualType = expression.typeFor
		val expectedType = expression.expectedType
		if (expectedType === null || actualType === null)
			return; // nothing to check
		if (!actualType.isConformant(expectedType)) {
			error("Incompatible types. Expected '" + expectedType.name + "' but was '" + actualType.name + "'", null,
				INCOMPATIBLE_TYPES);
		}
	}

	@Check def void checkFunctionInvocationArguments(YMemberSelection selection) {
		val method = selection.member
		if (method instanceof YFunction) {
			if (method.params.size != selection.args.size) {
				error("Invalid number of arguments: expected " + method.params.size + " but was " + selection.args.size,
					LangPackage.eINSTANCE.YMemberSelection_Member, INVALID_ARGS)
			}
		}
	}

	@Check def void checkFunctionOverride(YClass yclass) {
		val hierarchyMethods = yclass.classHierarchyMethods

		for (function : yclass.functions) {
			val overridden = hierarchyMethods.get(function.name)
			if (overridden !== null && (!function.type.isConformant(overridden.type) ||
				!function.params.map[type].elementsEqual(overridden.params.map[type]))) {
				error("The function '" + function.name + "' must override a superclass function", function,
					LangPackage.eINSTANCE.YNamedElement_Name, LangValidator.WRONG_FUNCTION_OVERRIDE)
			} else if (function.access < overridden.access) {
				error("Cannot reduce access from " + overridden.access + " to " + function.access, function,
					LangPackage.eINSTANCE.YMember_Access, REDUCED_ACCESSIBILITY)
			}
		}
	}

	@Check def void checkAccessibility(YMemberSelection selection) {
		val member = selection.member
		if (member.name !== null && !member.isAccessibleFrom(selection))
			error(
				'''The «member.access» member «member.name» is not accessible here''',
				LangPackage.eINSTANCE.YMemberSelection_Member,
				MEMBER_NOT_ACCESSIBLE
			)
	}

	// perform this check only on file save
	@Check // (CheckType.NORMAL)
	def checkDuplicateClassesInFiles(YWidget widget) {
		val externalClasses = widget.getVisibleExternalClassesDescriptions
		for (clazz : widget.classes) {
			val className = clazz.fullyQualifiedName
			if (externalClasses.containsKey(className)) {
				error("The type " + clazz.name + " is already defined", clazz, LangPackage.eINSTANCE.YNamedElement_Name,
					DUPLICATE_CLASS)
			}
		}
	}

	@Check
	def void checkSuper(YSuper ysuper) {
		if (ysuper.eContainingFeature != LangPackage.eINSTANCE.YMemberSelection_Receiver)
			error("'super' can be used only as member selection receiver", null, WRONG_SUPER_USAGE)
	}

	def private void checkNoDuplicateElements(Iterable<? extends YNamedElement> elements, String desc) {
		val multiMap = HashMultimap.create()

		for (element : elements)
			multiMap.put(element.name, element)

		for (entry : multiMap.asMap.entrySet) {
			val duplicates = entry.value
			if (duplicates.size > 1) {
				for (duplicate : duplicates)
					error("Duplicate " + desc + " '" + duplicate.name + "'", duplicate,
						LangPackage.eINSTANCE.YNamedElement_Name, DUPLICATE_ELEMENT)
			}
		}
	}

	/*
	 * Check if there is technical design and issue warning in case technical 
	 * design is defined and there is no table implementing entity type.
	 */
	@Check
	def void checkEntityHasTechDesign(YAnnotEntity entity) {
		if (isTechnicalDesign(entity)) {
			val table = getImplementingTable(entity)
			if (table !== null) {
				return
			} else {
				warning("The declared entity is not yet implemented as table", entity,
					LangPackage.Literals.YANNOT_ENTITY__NAME, ENTITY_NO_TECH_DESIGN)
			}
		}
	}

	@Check
	def void checkAttributeHasTechDesign(YAnnotAttr attribute) {
		val entity = attribute.eContainer as YAnnotEntity
		val table = getImplementingTable(entity)
		if (table === null) {
			return
		}
		for (YAnnotAbstractColumn abstractColumn : table.columns) {
			if (abstractColumn.type instanceof YAnnotColumn) {
				val column = abstractColumn.type as YAnnotColumn
				if (column.attrref.name == attribute.name) {
					return
				}
			}
		}
		warning("The declared attribute is not yet implemented as a column", attribute,
			LangPackage.Literals.YANNOT_ATTR__NAME, ATTRIBUTE_NO_TECH_DESIGN)
	}

	@Check
	def void checkTableIfComplete(YAnnotTable table) {
		var count = table.columns.size
		for (foreignKey : table.foreignkeys) {
			count += foreignKey.getColumns.size
		}
		if (count == 0) {
			error("Table does not have any columns.", table, LangPackage.Literals.YANNOT_TABLE__NAME,
				TABLE_DOES_NOT_HAVE_COLUMNS);
			return
		}
	}

	@Check
	def void checkDuplicateColumnName(YAnnotColumn column) {
		var count = 0
		if (column.eContainer.eContainer instanceof YAnnotTable) {
			val table = column.eContainer.eContainer as YAnnotTable
			for (abstractColumn : table.columns) {
				if (abstractColumn.name == (column.eContainer as YAnnotAbstractColumn).name) {
					count++
				}
			}
		}
		if (column.eContainer.eContainer.eContainer instanceof YAnnotTable) {
			val table = column.eContainer.eContainer.eContainer as YAnnotTable
			for (foreignKey : table.foreignkeys) {
				for (abstractColumn : foreignKey.columns) {
					if (abstractColumn.name == (column.eContainer as YAnnotAbstractColumn).name) {
						count++
					}
				}
			}
		}
		if (count > 1) {
			error("Table column name is not unique.", column.eContainer,
				LangPackage.Literals.YANNOT_ABSTRACT_COLUMN__NAME,
				eu.jgen.notes.dmw.lite.validation.LangValidator.COLUMN_NAME_NOT_UNIQUE);
				return
			}
		}

		/*
		 * 
		 */
		@Check
		def void checkDuplicateFKColumnName(YAnnotColumnLike column) {
			var count = 0
			val table = column.eContainer.eContainer.eContainer as YAnnotTable
			for (abstractColumn : table.columns) {
				if (abstractColumn.name == (column.eContainer as YAnnotAbstractColumn).name) {
					count++
				}
			}
			for (foreignKey : table.foreignkeys) {
				for (abstractColumn : foreignKey.columns) {
					if (abstractColumn.name == (column.eContainer as YAnnotAbstractColumn).name) {
						count++
					}
				}
			}
			if (count > 1) {
				error("Table foreign key column name is not unique.", column.eContainer,
					LangPackage.Literals.YANNOT_ABSTRACT_COLUMN__NAME, COLUMN_NAME_NOT_UNIQUE);
				return
			}
		}

		@Check
		def void checkIdentifierHasTechDesign(YAnnotId identifier) {
			if (isTechnicalDesign(identifier.eContainer as YAnnotEntity)) {
				val table = getImplementingTable(identifier.eContainer as YAnnotEntity)
				if (table.primarykey === null) {
					warning("The declared identifier is not yet implemented as primary key", identifier,
						LangPackage.Literals.YANNOT_ID__NAME, IDENTIFIER_NO_TECH_DESIGN)
				}
			}
		}

		@Check
		def void checkRelationshipHasTechDesign(YAnnotRel relationship) {
			if (isTechnicalDesign(relationship.eContainer as YAnnotEntity)) {
				if (relationship.isMany) {
					return
				}
				if (relationship.inverse.isMany) {
					val table = getImplementingTable(relationship.eContainer as YAnnotEntity)
					if (table !== null) {
						for (foreignKey : table.foreignkeys) {
							if (foreignKey.relationship == relationship) {
								return
							}
						}
						warning("The declared relationship is not yet implemented as a foreign key", relationship,
							LangPackage.Literals.YANNOT_REL__NAME, RELATIONSSHIP_NOT_IMPLEMENTED)
					}
				}
			}
		}

		@Check
		def void checkRelationshipHasParentDesignated(YAnnotRel relationship) {
			if (relationship.parent) {
				return
			} else {
				if (relationship.inverse !== null && relationship.inverse.parent) {
					return
				}
				error("Relation pair (" + relationship.name + "," + relationship.inverse.name +
					") does not have designated parent.", relationship, LangPackage.Literals.YANNOT_REL__NAME,
					NO_DESGNATED_PARENT);
			}
		}

		@Check
		def void checkClassExtendingStructureHasProperties(YClass clazz) {
			if (clazz.name == "Object") {
				return;
			}
			if (clazz.superclass.name == "Structure" && clazz.members.size == 0) {
				error("Class " + clazz.name + " does not have any properties yet.", clazz,
					LangPackage.eINSTANCE.YNamedElement_Name, CLASS_NEED_TO_HAVE_PROPERTIES);
			}
		}

//		@Check
//		def void checkIfClassHasExtention(YClass clazz) {
//			if (clazz.name == "Object") {
//				return;
//			}
//			if (clazz.superclass === null) {
//				error("Class " + clazz.name + " does need to extend Object type.", clazz,
//					LangPackage.eINSTANCE.YNamedElement_Name, CLASS_NEED_TO_BE_EXTENDED);
//			}
//		}

		@Check
		def void checkRelationshipHasOnlySingleParentDesignated(YAnnotRel relationship) {
			if (relationship.inverse !== null && relationship.parent && relationship.inverse.parent) {
				error("Relation pair (" + relationship.name + "," + relationship.inverse.name +
					") can have  only one designated parent.", relationship, LangPackage.Literals.YANNOT_REL__NAME,
					ONLY_ONE_DESGNATED_PARENT);
			}
		}

		@Check
		def void checkClassNameStartsWithCapital(YClass clazz) {
			if (clazz.name.toFirstUpper != clazz.name) {
				error("Class name should start with a capital letter", clazz, LangPackage.eINSTANCE.YNamedElement_Name,
					CLASS_NAME_FIRST_CHARACTER_NOT_CAPITAL);
			}
		}

		@Check
		def void checkEntityNameStartsWithCapital(YAnnotEntity annotEntity) {
			if (annotEntity.name.toFirstUpper != annotEntity.name) {
				error("Entity name should start with a capital letter", annotEntity,
					LangPackage.eINSTANCE.YAnnotEntity_Name, ENTITY_NAME_FIRST_CHARACTER_NOT_CAPITAL);
			}
		}

		@Check
		def void checkAttributeNameStartsWithLowecase(YAnnotAttr annotAttr) {
			if (annotAttr.name.toFirstLower != annotAttr.name) {
				error("Attribute name should start with a lower case letter", annotAttr,
					LangPackage.eINSTANCE.YAnnotAttr_Name, ATTRIBUTE_NAME_FIRST_CHARACTER_NOT_LOWERCASE);
			}
		}

		@Check
		def void checkAttributeTypeMatchesDefaultValueIfAny(YAnnotAttr annotAttr) {
			if (!annotAttr.isTypeCompatibleWithDefault) {
				error("Attribute type not compatible with default value", annotAttr,
					LangPackage.eINSTANCE.YAnnotAttr_Yclass, ATTRIBUTE_TYPE_NOT_COMP_WITH_DEFAULT);
			}
		}

		@Check
		def void checkFunctionNameStartsWithLowecase(YFunction function) {
			if (function.name.toFirstLower != function.name) {
				error("Function name should start with a lower case letter", function,
					LangPackage.eINSTANCE.YNamedElement_Name, FUNCTION_NAME_FIRST_CHARACTER_NOT_LOWERCASE);
			}
		}

		@Check
		def void checkPropertyNameStartsWithLowecase(YProperty property) {
			if (property.name.toFirstLower != property.name) {
				error("Property name should start with a lower case letter", property,
					LangPackage.eINSTANCE.YNamedElement_Name, PROPERTY_NAME_FIRST_CHARACTER_NOT_LOWERCASE);
			}
		}

		@Check
		def void checkVariableNameStartsWithLowecase(YVariableDeclaration variableDeclaration) {
			if (variableDeclaration.name.toFirstLower != variableDeclaration.name) {
				error("Variable name should start with a lower case letter", variableDeclaration,
					LangPackage.eINSTANCE.YNamedElement_Name, VARIABLE_NAME_FIRST_CHARACTER_NOT_LOWERCASE);
			}
		}

		def doCheckRelationshipCorrectness(YAnnotRel forwardRel) {
			val backwardRel = forwardRel.inverse

			// Check if inverse defined.
			if (forwardRel.inverse === null) {
				error("Inverse relationship for this relationship not yet defined.", forwardRel,
					LangPackage.eINSTANCE.YAnnotRel_Name, MISSING_INVERSE_REALTIONSHIP)
				return
			}

			// Check if inverse relationship exists in target
			if (!backwardRel.isInverseRelationshipDefinedInTarget) {
				error("Inverse relationship does not exists in target entity.", backwardRel,
					LangPackage.eINSTANCE.YAnnotRel_Name, INVERSE_RELATIONSHIP_DOES_NOT_EXIST_IN_TAGET)
				return
			}

			// Check if matching to itself.
			if (forwardRel == backwardRel) {
				error("Matching Inverse relationship cannot be the same relationship.", forwardRel,
					LangPackage.eINSTANCE.YAnnotRel_Name,
					eu.jgen.notes.dmw.lite.validation.LangValidator.INVERSE_RELATIONSHIP_CANNOT_BE_ITSELF)
					return
				}

				// Check if many-to-many
				if (forwardRel.many && backwardRel.many) {
					error("Many-to-many relationship type not supported yet.", forwardRel,
						LangPackage.eINSTANCE.YAnnotRel_Name, MANY_TO_MANY_NOT_SUPPORTED)
					return
				}

				// Check if both  one-to-one and mandatory
				if (!forwardRel.many && !backwardRel.many && !forwardRel.optional && !backwardRel.optional) {
					error("A fully mandatory 1-to-1 relationship is unusual and supported.", forwardRel,
						LangPackage.eINSTANCE.YAnnotRel_Name, ONE_TO_ONE_MANDATORY_NOT_SUPPORTED)
					return
				}
			}

		}
		