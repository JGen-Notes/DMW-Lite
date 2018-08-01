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
package eu.jgen.notes.dmw.lite.validation;

import com.google.common.base.Objects;
import com.google.common.collect.HashMultimap;
import com.google.inject.Inject;
import eu.jgen.notes.dmw.lite.lang.LangPackage;
import eu.jgen.notes.dmw.lite.lang.YAccessLevel;
import eu.jgen.notes.dmw.lite.lang.YAnnotAbstractColumn;
import eu.jgen.notes.dmw.lite.lang.YAnnotAttr;
import eu.jgen.notes.dmw.lite.lang.YAnnotColumn;
import eu.jgen.notes.dmw.lite.lang.YAnnotColumnLike;
import eu.jgen.notes.dmw.lite.lang.YAnnotDatabase;
import eu.jgen.notes.dmw.lite.lang.YAnnotEntity;
import eu.jgen.notes.dmw.lite.lang.YAnnotEntityInner;
import eu.jgen.notes.dmw.lite.lang.YAnnotForeignKey;
import eu.jgen.notes.dmw.lite.lang.YAnnotId;
import eu.jgen.notes.dmw.lite.lang.YAnnotPrimaryKey;
import eu.jgen.notes.dmw.lite.lang.YAnnotRel;
import eu.jgen.notes.dmw.lite.lang.YAnnotTable;
import eu.jgen.notes.dmw.lite.lang.YAnnotTop;
import eu.jgen.notes.dmw.lite.lang.YBlock;
import eu.jgen.notes.dmw.lite.lang.YClass;
import eu.jgen.notes.dmw.lite.lang.YExpression;
import eu.jgen.notes.dmw.lite.lang.YFunction;
import eu.jgen.notes.dmw.lite.lang.YMember;
import eu.jgen.notes.dmw.lite.lang.YMemberSelection;
import eu.jgen.notes.dmw.lite.lang.YNamedElement;
import eu.jgen.notes.dmw.lite.lang.YParameter;
import eu.jgen.notes.dmw.lite.lang.YProperty;
import eu.jgen.notes.dmw.lite.lang.YReturn;
import eu.jgen.notes.dmw.lite.lang.YStatement;
import eu.jgen.notes.dmw.lite.lang.YSuper;
import eu.jgen.notes.dmw.lite.lang.YVariableDeclaration;
import eu.jgen.notes.dmw.lite.lang.YWidget;
import eu.jgen.notes.dmw.lite.scoping.LangIndex;
import eu.jgen.notes.dmw.lite.typing.LangTypeComputer;
import eu.jgen.notes.dmw.lite.typing.LangTypeConformance;
import eu.jgen.notes.dmw.lite.utility.LangUtil;
import eu.jgen.notes.dmw.lite.validation.AbstractLangValidator;
import eu.jgen.notes.dmw.lite.validation.LangAccessibility;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.naming.IQualifiedNameProvider;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.validation.Check;
import org.eclipse.xtext.validation.CheckType;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.InputOutput;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ListExtensions;

/**
 * This class contains custom validation rules.
 * 
 * See https://www.eclipse.org/Xtext/documentation/303_runtime_concepts.html#validation
 */
@SuppressWarnings("all")
public class LangValidator extends AbstractLangValidator {
  protected final static String ISSUE_CODE_PREFIX = " eu.jgen.notes.dmw.lite.";
  
  public final static String HIERARCHY_CYCLE = (LangValidator.ISSUE_CODE_PREFIX + "HierarchyCycle");
  
  public final static String PROPERTY_SELECTION_ON_FUNCTION = (LangValidator.ISSUE_CODE_PREFIX + "FieldSelectionOnMethod");
  
  public final static String FUNCTION_INVOCATION_ON_PROPERTY = (LangValidator.ISSUE_CODE_PREFIX + "FunctionInvocationOnProperty");
  
  public final static String UNREACHABLE_CODE = (LangValidator.ISSUE_CODE_PREFIX + "UnreachableCode");
  
  public final static String FUNCTION_FINAL_RETURN = (LangValidator.ISSUE_CODE_PREFIX + "MissingFinalReturn");
  
  public final static String DUPLICATE_ELEMENT = (LangValidator.ISSUE_CODE_PREFIX + "DuplicateElement");
  
  public final static String INCOMPATIBLE_TYPES = (LangValidator.ISSUE_CODE_PREFIX + "IncompatibleTypes");
  
  public final static String INVALID_ARGS = (LangValidator.ISSUE_CODE_PREFIX + "InvalidArgs");
  
  public final static String WRONG_FUNCTION_OVERRIDE = (LangValidator.ISSUE_CODE_PREFIX + "WrongFunctionOverride");
  
  public final static String MEMBER_NOT_ACCESSIBLE = (LangValidator.ISSUE_CODE_PREFIX + "MemberNotAccessible");
  
  public final static String DUPLICATE_CLASS = (LangValidator.ISSUE_CODE_PREFIX + "DuplicateClass");
  
  public final static String DUPLICATE_ENTITY = (LangValidator.ISSUE_CODE_PREFIX + "DuplicateEntity");
  
  public final static String WRONG_SUPER_USAGE = (LangValidator.ISSUE_CODE_PREFIX + "WrongSuperUsage");
  
  public final static String REDUCED_ACCESSIBILITY = (LangValidator.ISSUE_CODE_PREFIX + "ReducedAccessibility");
  
  public final static String MISSING_ENTITY_REFERENCE = (LangValidator.ISSUE_CODE_PREFIX + "MissingEntityReference");
  
  public final static String WRONG_TYPE = (LangValidator.ISSUE_CODE_PREFIX + "WrongType");
  
  public final static String WRONG_CROSS_REFERENCE = (LangValidator.ISSUE_CODE_PREFIX + "WrongCrossReference");
  
  public final static String WRONG_INVERT_REFERENCE = (LangValidator.ISSUE_CODE_PREFIX + "WrongInvertReference");
  
  public final static String ATTRIBUTE_NO_TECH_DESIGN = (LangValidator.ISSUE_CODE_PREFIX + "AttributeNoTechDesign");
  
  public final static String ENTITY_NO_TECH_DESIGN = (LangValidator.ISSUE_CODE_PREFIX + "EntityNoTechDesign");
  
  public final static String TABLE_DOES_NOT_HAVE_COLUMNS = (LangValidator.ISSUE_CODE_PREFIX + "TableDoesNotHaveColumns");
  
  public final static String TABLE_NAME_NOT_UNIQUE = (LangValidator.ISSUE_CODE_PREFIX + "TableNameNotUnique");
  
  public final static String COLUMN_NAME_NOT_UNIQUE = (LangValidator.ISSUE_CODE_PREFIX + "ColumnNameNotUnique");
  
  public final static String IDENTIFIER_NO_TECH_DESIGN = (LangValidator.ISSUE_CODE_PREFIX + "IdentifgierNoTechDesign");
  
  public final static String RELATIONSSHIP_NOT_IMPLEMENTED = (LangValidator.ISSUE_CODE_PREFIX + "RelationshipNotImplemented");
  
  public final static String UNSUPPORTED_DATABASE = (LangValidator.ISSUE_CODE_PREFIX + "UnsupportedDatabase");
  
  public final static String MISSING_INVERSE_REALTIONSHIP = (LangValidator.ISSUE_CODE_PREFIX + "MissingInverseRelationship");
  
  public final static String MANY_TO_MANY_NOT_SUPPORTED = (LangValidator.ISSUE_CODE_PREFIX + "ManyToManyNotSuported");
  
  public final static String ONE_TO_ONE_MANDATORY_NOT_SUPPORTED = (LangValidator.ISSUE_CODE_PREFIX + "OneToOneMandatoryNotSuported");
  
  public final static String INVERSE_RELATIONSHIP_CANNOT_BE_ITSELF = (LangValidator.ISSUE_CODE_PREFIX + "MatchingInverseCannotMatchItself");
  
  public final static String INVERSE_RELATIONSHIP_DOES_NOT_EXIST_IN_TAGET = (LangValidator.ISSUE_CODE_PREFIX + "InverseDoesNotExistInTarget");
  
  public final static String NO_DESGNATED_PARENT = (LangValidator.ISSUE_CODE_PREFIX + "NoDesignatedParent");
  
  public final static String ONLY_ONE_DESGNATED_PARENT = (LangValidator.ISSUE_CODE_PREFIX + "OnlyOneDesignatedParent");
  
  @Inject
  @Extension
  private LangUtil _langUtil;
  
  @Inject
  @Extension
  private LangTypeComputer _langTypeComputer;
  
  @Inject
  @Extension
  private LangTypeConformance _langTypeConformance;
  
  @Inject
  @Extension
  private LangAccessibility _langAccessibility;
  
  @Inject
  @Extension
  private LangIndex _langIndex;
  
  @Inject
  @Extension
  private IQualifiedNameProvider _iQualifiedNameProvider;
  
  /**
   * Supported Database
   */
  @Check
  public void checkIfSupportedDatabase(final YAnnotDatabase annotDatabase) {
    String _name = annotDatabase.getName();
    boolean _tripleNotEquals = (_name != null);
    if (_tripleNotEquals) {
      if ((((Objects.equal(annotDatabase.getName(), "MySQL") || Objects.equal(annotDatabase.getName(), "SQLite")) || Objects.equal(annotDatabase.getName(), "PostgreSQL")) || 
        Objects.equal(annotDatabase.getName(), "MongoDB"))) {
        return;
      } else {
        this.error("This database is not supported yet.", LangPackage.eINSTANCE.getYAnnotDatabase_Name(), 
          LangValidator.UNSUPPORTED_DATABASE, annotDatabase.getName());
      }
    }
  }
  
  /**
   * Entities
   */
  @Check(CheckType.FAST)
  public void checkEntityHierarchy(final YAnnotEntity entity) {
    boolean _contains = this._langUtil.entityHierarchy(entity).contains(entity);
    if (_contains) {
      String _name = entity.getName();
      String _plus = ("cycle in hierarchy of entities \'" + _name);
      String _plus_1 = (_plus + "\'");
      this.error(_plus_1, LangPackage.eINSTANCE.getYAnnotEntity_Superannot(), 
        LangValidator.HIERARCHY_CYCLE, entity.getSuperannot().getName());
    }
  }
  
  @Check
  public void checkNoDuplicateEntities(final YWidget widget) {
    final HashMultimap<String, EObject> multiMap = HashMultimap.<String, EObject>create();
    final Function1<YAnnotTop, Boolean> _function = (YAnnotTop it) -> {
      EObject _type = it.getType();
      return Boolean.valueOf((_type instanceof YAnnotEntity));
    };
    Iterable<YAnnotTop> _filter = IterableExtensions.<YAnnotTop>filter(widget.getAnnotations(), _function);
    for (final YAnnotTop annotEntity : _filter) {
      EObject _type = annotEntity.getType();
      multiMap.put(((YAnnotEntity) _type).getName(), annotEntity.getType());
    }
    Set<Map.Entry<String, Collection<EObject>>> _entrySet = multiMap.asMap().entrySet();
    for (final Map.Entry<String, Collection<EObject>> entry : _entrySet) {
      {
        final Collection<EObject> duplicates = entry.getValue();
        int _size = duplicates.size();
        boolean _greaterThan = (_size > 1);
        if (_greaterThan) {
          for (final EObject d : duplicates) {
            String _name = ((YAnnotEntity) d).getName();
            String _plus = ((("Duplicate " + "entity") + " \'") + _name);
            String _plus_1 = (_plus + "\'");
            this.error(_plus_1, d, 
              LangPackage.eINSTANCE.getYAnnotEntity_Name(), LangValidator.DUPLICATE_ELEMENT);
          }
        }
      }
    }
  }
  
  @Check
  public void checkDuplicateEntitiesAcrossFiles(final YWidget widget) {
    final Map<QualifiedName, IEObjectDescription> externalEntities = this._langIndex.getVisibleExternalEntitiesDescriptions(widget);
    EList<YAnnotTop> _annotations = widget.getAnnotations();
    for (final YAnnotTop annot : _annotations) {
      EObject _type = annot.getType();
      if ((_type instanceof YAnnotEntity)) {
        final QualifiedName annotName = this._iQualifiedNameProvider.getFullyQualifiedName(annot.getType());
        boolean _containsKey = externalEntities.containsKey(annotName);
        if (_containsKey) {
          EObject _type_1 = annot.getType();
          String _name = ((YAnnotEntity) _type_1).getName();
          String _plus = ("The entity " + _name);
          String _plus_1 = (_plus + " is already defined");
          this.error(_plus_1, annot.getType(), 
            LangPackage.eINSTANCE.getYAnnotEntity_Name(), LangValidator.DUPLICATE_ENTITY);
        }
      }
    }
  }
  
  @Check
  public void checkNoDuplicateAttributes(final YAnnotEntity annotEntity) {
    final HashMultimap<String, YAnnotAttr> multiMap = HashMultimap.<String, YAnnotAttr>create();
    EList<YAnnotEntityInner> _annots = annotEntity.getAnnots();
    for (final YAnnotEntityInner annot : _annots) {
      if ((annot instanceof YAnnotAttr)) {
        multiMap.put(((YAnnotAttr) annot).getName(), ((YAnnotAttr)annot));
      }
    }
    Set<Map.Entry<String, Collection<YAnnotAttr>>> _entrySet = multiMap.asMap().entrySet();
    for (final Map.Entry<String, Collection<YAnnotAttr>> entry : _entrySet) {
      {
        final Collection<YAnnotAttr> duplicates = entry.getValue();
        int _size = duplicates.size();
        boolean _greaterThan = (_size > 1);
        if (_greaterThan) {
          for (final YAnnotAttr duplicate : duplicates) {
            String _name = ((YAnnotAttr) duplicate).getName();
            String _plus = ((("Duplicate " + "attribute") + " \'") + _name);
            String _plus_1 = (_plus + "\'");
            this.error(_plus_1, duplicate, 
              LangPackage.eINSTANCE.getYAnnotAttr_Name(), LangValidator.DUPLICATE_ELEMENT);
          }
        }
      }
    }
  }
  
  @Check
  public void checkNoDuplicateIdentifiers(final YAnnotEntity annotEntity) {
    final HashMultimap<String, YAnnotId> multiMap = HashMultimap.<String, YAnnotId>create();
    EList<YAnnotEntityInner> _annots = annotEntity.getAnnots();
    for (final YAnnotEntityInner annot : _annots) {
      if ((annot instanceof YAnnotId)) {
        multiMap.put(((YAnnotId) annot).getName(), ((YAnnotId)annot));
      }
    }
    Set<Map.Entry<String, Collection<YAnnotId>>> _entrySet = multiMap.asMap().entrySet();
    for (final Map.Entry<String, Collection<YAnnotId>> entry : _entrySet) {
      {
        final Collection<YAnnotId> duplicates = entry.getValue();
        int _size = duplicates.size();
        boolean _greaterThan = (_size > 1);
        if (_greaterThan) {
          for (final YAnnotId duplicate : duplicates) {
            String _name = ((YAnnotId) duplicate).getName();
            String _plus = ((("Duplicate " + "identifier") + " \'") + _name);
            String _plus_1 = (_plus + "\'");
            this.error(_plus_1, duplicate, 
              LangPackage.eINSTANCE.getYAnnotId_Name(), LangValidator.DUPLICATE_ELEMENT);
          }
        }
      }
    }
  }
  
  @Check
  public void checkNoDuplicateRelationship(final YAnnotEntity annotEntity) {
    final HashMultimap<String, YAnnotRel> multiMap = HashMultimap.<String, YAnnotRel>create();
    EList<YAnnotEntityInner> _annots = annotEntity.getAnnots();
    for (final YAnnotEntityInner annot : _annots) {
      if ((annot instanceof YAnnotRel)) {
        String _name = ((YAnnotRel) annot).getName();
        String _name_1 = ((YAnnotRel) annot).getTarget().getName();
        String _plus = (_name + _name_1);
        multiMap.put(_plus, ((YAnnotRel)annot));
      }
    }
    Set<Map.Entry<String, Collection<YAnnotRel>>> _entrySet = multiMap.asMap().entrySet();
    for (final Map.Entry<String, Collection<YAnnotRel>> entry : _entrySet) {
      {
        final Collection<YAnnotRel> duplicates = entry.getValue();
        int _size = duplicates.size();
        boolean _greaterThan = (_size > 1);
        if (_greaterThan) {
          for (final YAnnotRel duplicate : duplicates) {
            String _name_2 = ((YAnnotRel) duplicate).getName();
            String _plus_1 = ((("Duplicate " + "relationship") + " \'") + _name_2);
            String _plus_2 = (_plus_1 + "\'");
            this.error(_plus_2, duplicate, 
              LangPackage.eINSTANCE.getYAnnotRel_Name(), LangValidator.DUPLICATE_ELEMENT);
          }
        }
      }
    }
  }
  
  @Check
  public void checkInverseRelationship(final YAnnotEntity annotEntity) {
    EList<YAnnotEntityInner> _annots = annotEntity.getAnnots();
    for (final YAnnotEntityInner element : _annots) {
      if ((element instanceof YAnnotRel)) {
        this.doCheckRelationshipCorrectness(((YAnnotRel) element));
      }
    }
  }
  
  public void doCheckRelationshipCorrectness(final YAnnotRel forwardRel) {
    final YAnnotRel backwardRel = forwardRel.getInverse();
    YAnnotRel _inverse = forwardRel.getInverse();
    boolean _tripleEquals = (_inverse == null);
    if (_tripleEquals) {
      this.error("Inverse relationship for this relationship not yet defined.", forwardRel, 
        LangPackage.eINSTANCE.getYAnnotRel_Name(), LangValidator.MISSING_INVERSE_REALTIONSHIP);
      return;
    }
    boolean _isInverseRelationshipDefinedInTarget = this._langUtil.isInverseRelationshipDefinedInTarget(backwardRel);
    boolean _not = (!_isInverseRelationshipDefinedInTarget);
    if (_not) {
      this.error("Inverse relationship does not exists in target entity.", backwardRel, 
        LangPackage.eINSTANCE.getYAnnotRel_Name(), LangValidator.INVERSE_RELATIONSHIP_DOES_NOT_EXIST_IN_TAGET);
      return;
    }
    boolean _equals = Objects.equal(forwardRel, backwardRel);
    if (_equals) {
      this.error("Matching Inverse relationship cannot be the same relationship.", forwardRel, 
        LangPackage.eINSTANCE.getYAnnotRel_Name(), 
        LangValidator.INVERSE_RELATIONSHIP_CANNOT_BE_ITSELF);
      return;
    }
    if ((forwardRel.isMany() && backwardRel.isMany())) {
      this.error("Many-to-many relationship type not supported yet.", forwardRel, 
        LangPackage.eINSTANCE.getYAnnotRel_Name(), LangValidator.MANY_TO_MANY_NOT_SUPPORTED);
      return;
    }
    if (((((!forwardRel.isMany()) && (!backwardRel.isMany())) && (!forwardRel.isOptional())) && (!backwardRel.isOptional()))) {
      this.error("A fully mandatory 1-to-1 relationship is unusual and supported.", forwardRel, 
        LangPackage.eINSTANCE.getYAnnotRel_Name(), LangValidator.ONE_TO_ONE_MANDATORY_NOT_SUPPORTED);
      return;
    }
  }
  
  @Check
  public void checkFunctionInvocation(final YMemberSelection memberSelection) {
    boolean _isFunctioninvocation = memberSelection.isFunctioninvocation();
    if (_isFunctioninvocation) {
      String _name = memberSelection.getMember().getName();
      boolean _equals = Objects.equal(_name, "moveView");
      if (_equals) {
        this.doValidateMoveStructureFunction(memberSelection);
      }
    }
  }
  
  private void doValidateMoveStructureFunction(final YMemberSelection memberSelection) {
    InputOutput.<String>println(memberSelection.getMember().getName());
    final Consumer<YExpression> _function = (YExpression element) -> {
      InputOutput.<YExpression>println(element);
      final YMemberSelection a = ((YMemberSelection) element);
      YMember _member = a.getMember();
      final YProperty b = ((YProperty) _member);
      String _name = b.getName();
      String _plus = (_name + " : ");
      String _name_1 = b.getType().getName();
      String _plus_1 = (_plus + _name_1);
      String _plus_2 = (_plus_1 + " -> ");
      String _name_2 = b.getType().getEntity().getName();
      String _plus_3 = (_plus_2 + _name_2);
      InputOutput.<String>println(_plus_3);
    };
    memberSelection.getArgs().forEach(_function);
  }
  
  /**
   * Classes YMemberSelection
   */
  @Check
  public void checkPropertyReferenceToAtttribute(final YProperty property) {
    YAnnotAttr _attr = property.getAttr();
    boolean _tripleEquals = (_attr == null);
    if (_tripleEquals) {
      return;
    }
    EObject _eContainer = property.eContainer();
    final YClass parent = ((YClass) _eContainer);
    YAnnotEntity _entity = parent.getEntity();
    boolean _tripleEquals_1 = (_entity == null);
    if (_tripleEquals_1) {
      this.error("Entity has to implement entity type before pointing to attribute", 
        LangPackage.eINSTANCE.getYProperty_Attr(), LangValidator.MISSING_ENTITY_REFERENCE, property.getName());
      return;
    }
    String _name = property.getAttr().getName();
    String _name_1 = property.getName();
    boolean _equals = Objects.equal(_name, _name_1);
    if (_equals) {
      String _name_2 = property.getType().getName();
      String _name_3 = property.getAttr().getYclass().getName();
      boolean _notEquals = (!Objects.equal(_name_2, _name_3));
      if (_notEquals) {
        this.error("Attribute type does not match property type", LangPackage.eINSTANCE.getYMember_Type(), LangValidator.WRONG_TYPE, 
          property.getType().getName());
      }
      EObject _eContainer_1 = property.getAttr().eContainer();
      final String name = ((YAnnotEntity) _eContainer_1).getName();
      String _name_4 = parent.getEntity().getName();
      boolean _notEquals_1 = (!Objects.equal(name, _name_4));
      if (_notEquals_1) {
        this.error("Attribute does not belong to the chosen entity", LangPackage.eINSTANCE.getYProperty_Attr(), 
          LangValidator.WRONG_CROSS_REFERENCE, property.getName());
      }
    } else {
      this.error("Cannot find matching attribute for selected entity type", LangPackage.eINSTANCE.getYProperty_Attr(), 
        LangValidator.MISSING_ENTITY_REFERENCE, property.getName());
    }
  }
  
  @Check
  public void checkClassHierarchy(final YClass yclass) {
    boolean _contains = this._langUtil.classHierarchy(yclass).contains(yclass);
    if (_contains) {
      String _name = yclass.getName();
      String _plus = ("cycle in hierarchy of class \'" + _name);
      String _plus_1 = (_plus + "\'");
      this.error(_plus_1, LangPackage.eINSTANCE.getYClass_Superclass(), 
        LangValidator.HIERARCHY_CYCLE, yclass.getSuperclass().getName());
    }
  }
  
  @Check
  public void checkMemberSelection(final YMemberSelection memberSelection) {
    final YMember member = memberSelection.getMember();
    if (((member instanceof YProperty) && memberSelection.isFunctioninvocation())) {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("Function invocation on a property");
      this.error(_builder.toString(), LangPackage.eINSTANCE.getYMemberSelection_Functioninvocation(), 
        LangValidator.FUNCTION_INVOCATION_ON_PROPERTY);
    } else {
      if (((member instanceof YFunction) && (!memberSelection.isFunctioninvocation()))) {
        StringConcatenation _builder_1 = new StringConcatenation();
        _builder_1.append("Property selection on a function");
        this.error(_builder_1.toString(), 
          LangPackage.eINSTANCE.getYMemberSelection_Member(), 
          LangValidator.PROPERTY_SELECTION_ON_FUNCTION);
      }
    }
  }
  
  @Check
  public void checkUnreachableCode(final YBlock block) {
    final EList<YStatement> statements = block.getStatements();
    for (int i = 0; (i < (((Object[])Conversions.unwrapArray(statements, Object.class)).length - 1)); i++) {
      YStatement _get = statements.get(i);
      if ((_get instanceof YReturn)) {
        this.error("Unreachable code", statements.get((i + 1)), null, 
          LangValidator.UNREACHABLE_CODE);
        return;
      }
    }
  }
  
  @Check
  public void checkMethodEndsWithReturn(final YFunction function) {
    boolean _isReturnvalue = function.isReturnvalue();
    if (_isReturnvalue) {
      YReturn _returnStatement = this._langUtil.returnStatement(function);
      boolean _tripleEquals = (_returnStatement == null);
      if (_tripleEquals) {
        this.error(
          "Function must end with a return statement", 
          LangPackage.eINSTANCE.getYFunction_Body(), 
          LangValidator.FUNCTION_FINAL_RETURN);
      }
    }
  }
  
  @Check
  public void checkNoDuplicateClasses(final YWidget widget) {
    this.checkNoDuplicateElements(widget.getClasses(), "class");
  }
  
  @Check
  public void checkNoDuplicateMembers(final YClass yclass) {
    this.checkNoDuplicateElements(this._langUtil.properties(yclass), "property");
    this.checkNoDuplicateElements(this._langUtil.functions(yclass), "function");
  }
  
  @Check
  public void checkNoDuplicateSymbols(final YFunction function) {
    this.checkNoDuplicateElements(function.getParams(), "parameter");
    this.checkNoDuplicateElements(EcoreUtil2.<YVariableDeclaration>getAllContentsOfType(function.getBody(), YVariableDeclaration.class), "variable");
  }
  
  @Check
  public void checkConformance(final YExpression expression) {
    final YClass actualType = this._langTypeComputer.typeFor(expression);
    final YClass expectedType = this._langTypeComputer.expectedType(expression);
    if (((expectedType == null) || (actualType == null))) {
      return;
    }
    boolean _isConformant = this._langTypeConformance.isConformant(actualType, expectedType);
    boolean _not = (!_isConformant);
    if (_not) {
      String _name = expectedType.getName();
      String _plus = ("Incompatible types. Expected \'" + _name);
      String _plus_1 = (_plus + "\' but was \'");
      String _name_1 = actualType.getName();
      String _plus_2 = (_plus_1 + _name_1);
      String _plus_3 = (_plus_2 + "\'");
      this.error(_plus_3, 
        null, LangValidator.INCOMPATIBLE_TYPES);
    }
  }
  
  @Check
  public void checkFunctionInvocationArguments(final YMemberSelection selection) {
    final YMember method = selection.getMember();
    if ((method instanceof YFunction)) {
      int _size = ((YFunction)method).getParams().size();
      int _size_1 = selection.getArgs().size();
      boolean _notEquals = (_size != _size_1);
      if (_notEquals) {
        int _size_2 = ((YFunction)method).getParams().size();
        String _plus = ("Invalid number of arguments: expected " + Integer.valueOf(_size_2));
        String _plus_1 = (_plus + " but was ");
        int _size_3 = selection.getArgs().size();
        String _plus_2 = (_plus_1 + Integer.valueOf(_size_3));
        this.error(_plus_2, LangPackage.eINSTANCE.getYMemberSelection_Member(), LangValidator.INVALID_ARGS);
      }
    }
  }
  
  @Check
  public void checkFunctionOverride(final YClass yclass) {
    final Map<String, YFunction> hierarchyMethods = this._langUtil.classHierarchyMethods(yclass);
    Iterable<YFunction> _functions = this._langUtil.functions(yclass);
    for (final YFunction function : _functions) {
      {
        final YFunction overridden = hierarchyMethods.get(function.getName());
        if (((overridden != null) && ((!this._langTypeConformance.isConformant(function.getType(), overridden.getType())) || 
          (!IterableExtensions.elementsEqual(ListExtensions.<YParameter, YClass>map(function.getParams(), ((Function1<YParameter, YClass>) (YParameter it) -> {
            return it.getType();
          })), ListExtensions.<YParameter, YClass>map(overridden.getParams(), ((Function1<YParameter, YClass>) (YParameter it) -> {
            return it.getType();
          }))))))) {
          String _name = function.getName();
          String _plus = ("The function \'" + _name);
          String _plus_1 = (_plus + "\' must override a superclass function");
          this.error(_plus_1, function, 
            LangPackage.eINSTANCE.getYNamedElement_Name(), LangValidator.WRONG_FUNCTION_OVERRIDE);
        } else {
          YAccessLevel _access = function.getAccess();
          YAccessLevel _access_1 = overridden.getAccess();
          boolean _lessThan = (_access.compareTo(_access_1) < 0);
          if (_lessThan) {
            YAccessLevel _access_2 = overridden.getAccess();
            String _plus_2 = ("Cannot reduce access from " + _access_2);
            String _plus_3 = (_plus_2 + " to ");
            YAccessLevel _access_3 = function.getAccess();
            String _plus_4 = (_plus_3 + _access_3);
            this.error(_plus_4, function, 
              LangPackage.eINSTANCE.getYMember_Access(), LangValidator.REDUCED_ACCESSIBILITY);
          }
        }
      }
    }
  }
  
  @Check
  public void checkAccessibility(final YMemberSelection selection) {
    final YMember member = selection.getMember();
    if (((member.getName() != null) && (!this._langAccessibility.isAccessibleFrom(member, selection)))) {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("The ");
      YAccessLevel _access = member.getAccess();
      _builder.append(_access);
      _builder.append(" member ");
      String _name = member.getName();
      _builder.append(_name);
      _builder.append(" is not accessible here");
      this.error(_builder.toString(), 
        LangPackage.eINSTANCE.getYMemberSelection_Member(), 
        LangValidator.MEMBER_NOT_ACCESSIBLE);
    }
  }
  
  @Check
  public void checkDuplicateClassesInFiles(final YWidget widget) {
    final Map<QualifiedName, IEObjectDescription> externalClasses = this._langIndex.getVisibleExternalClassesDescriptions(widget);
    EList<YClass> _classes = widget.getClasses();
    for (final YClass clazz : _classes) {
      {
        final QualifiedName className = this._iQualifiedNameProvider.getFullyQualifiedName(clazz);
        boolean _containsKey = externalClasses.containsKey(className);
        if (_containsKey) {
          String _name = clazz.getName();
          String _plus = ("The type " + _name);
          String _plus_1 = (_plus + " is already defined");
          this.error(_plus_1, clazz, 
            LangPackage.eINSTANCE.getYNamedElement_Name(), LangValidator.DUPLICATE_CLASS);
        }
      }
    }
  }
  
  @Check
  public void checkSuper(final YSuper ysuper) {
    EStructuralFeature _eContainingFeature = ysuper.eContainingFeature();
    EReference _yMemberSelection_Receiver = LangPackage.eINSTANCE.getYMemberSelection_Receiver();
    boolean _notEquals = (!Objects.equal(_eContainingFeature, _yMemberSelection_Receiver));
    if (_notEquals) {
      this.error("\'super\' can be used only as member selection receiver", null, LangValidator.WRONG_SUPER_USAGE);
    }
  }
  
  private void checkNoDuplicateElements(final Iterable<? extends YNamedElement> elements, final String desc) {
    final HashMultimap<String, YNamedElement> multiMap = HashMultimap.<String, YNamedElement>create();
    for (final YNamedElement element : elements) {
      multiMap.put(element.getName(), element);
    }
    Set<Map.Entry<String, Collection<YNamedElement>>> _entrySet = multiMap.asMap().entrySet();
    for (final Map.Entry<String, Collection<YNamedElement>> entry : _entrySet) {
      {
        final Collection<YNamedElement> duplicates = entry.getValue();
        int _size = duplicates.size();
        boolean _greaterThan = (_size > 1);
        if (_greaterThan) {
          for (final YNamedElement duplicate : duplicates) {
            String _name = duplicate.getName();
            String _plus = ((("Duplicate " + desc) + " \'") + _name);
            String _plus_1 = (_plus + "\'");
            this.error(_plus_1, duplicate, 
              LangPackage.eINSTANCE.getYNamedElement_Name(), LangValidator.DUPLICATE_ELEMENT);
          }
        }
      }
    }
  }
  
  /**
   * Check if there is technical design and issue warning in case technical
   * design is defined and there is no table implementing entity type.
   */
  @Check
  public void checkEntityHasTechDesign(final YAnnotEntity entity) {
    boolean _isTechnicalDesign = this._langUtil.isTechnicalDesign(entity);
    if (_isTechnicalDesign) {
      final YAnnotTable table = this._langUtil.getImplementingTable(entity);
      if ((table != null)) {
        return;
      } else {
        this.warning("The declared entity is not yet implemented as table", entity, 
          LangPackage.Literals.YANNOT_ENTITY__NAME, LangValidator.ENTITY_NO_TECH_DESIGN);
      }
    }
  }
  
  @Check
  public void checkAttributeHasTechDesign(final YAnnotAttr attribute) {
    EObject _eContainer = attribute.eContainer();
    final YAnnotEntity entity = ((YAnnotEntity) _eContainer);
    final YAnnotTable table = this._langUtil.getImplementingTable(entity);
    if ((table == null)) {
      return;
    }
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
          return;
        }
      }
    }
    this.warning("The declared attribute is not yet implemented as a column", attribute, 
      LangPackage.Literals.YANNOT_ATTR__NAME, LangValidator.ATTRIBUTE_NO_TECH_DESIGN);
  }
  
  @Check
  public void checkTableIfComplete(final YAnnotTable table) {
    int count = table.getColumns().size();
    EList<YAnnotForeignKey> _foreignkeys = table.getForeignkeys();
    for (final YAnnotForeignKey foreignKey : _foreignkeys) {
      int _count = count;
      int _size = foreignKey.getColumns().size();
      count = (_count + _size);
    }
    if ((count == 0)) {
      this.error("Table does not have any columns.", table, LangPackage.Literals.YANNOT_TABLE__NAME, 
        LangValidator.TABLE_DOES_NOT_HAVE_COLUMNS);
      return;
    }
  }
  
  @Check
  public void checkDuplicateColumnName(final YAnnotColumn column) {
    int count = 0;
    EObject _eContainer = column.eContainer().eContainer();
    if ((_eContainer instanceof YAnnotTable)) {
      EObject _eContainer_1 = column.eContainer().eContainer();
      final YAnnotTable table = ((YAnnotTable) _eContainer_1);
      EList<YAnnotAbstractColumn> _columns = table.getColumns();
      for (final YAnnotAbstractColumn abstractColumn : _columns) {
        String _name = abstractColumn.getName();
        EObject _eContainer_2 = column.eContainer();
        String _name_1 = ((YAnnotAbstractColumn) _eContainer_2).getName();
        boolean _equals = Objects.equal(_name, _name_1);
        if (_equals) {
          count++;
        }
      }
    }
    EObject _eContainer_3 = column.eContainer().eContainer().eContainer();
    if ((_eContainer_3 instanceof YAnnotTable)) {
      EObject _eContainer_4 = column.eContainer().eContainer().eContainer();
      final YAnnotTable table_1 = ((YAnnotTable) _eContainer_4);
      EList<YAnnotForeignKey> _foreignkeys = table_1.getForeignkeys();
      for (final YAnnotForeignKey foreignKey : _foreignkeys) {
        EList<YAnnotAbstractColumn> _columns_1 = foreignKey.getColumns();
        for (final YAnnotAbstractColumn abstractColumn_1 : _columns_1) {
          String _name_2 = abstractColumn_1.getName();
          EObject _eContainer_5 = column.eContainer();
          String _name_3 = ((YAnnotAbstractColumn) _eContainer_5).getName();
          boolean _equals_1 = Objects.equal(_name_2, _name_3);
          if (_equals_1) {
            count++;
          }
        }
      }
    }
    if ((count > 1)) {
      this.error("Table column name is not unique.", column.eContainer(), 
        LangPackage.Literals.YANNOT_ABSTRACT_COLUMN__NAME, 
        LangValidator.COLUMN_NAME_NOT_UNIQUE);
      return;
    }
  }
  
  public void checkDuplicateFKColumnName(final YAnnotColumnLike column) {
    int count = 0;
    EObject _eContainer = column.eContainer().eContainer().eContainer();
    final YAnnotTable table = ((YAnnotTable) _eContainer);
    EList<YAnnotAbstractColumn> _columns = table.getColumns();
    for (final YAnnotAbstractColumn abstractColumn : _columns) {
      String _name = abstractColumn.getName();
      EObject _eContainer_1 = column.eContainer();
      String _name_1 = ((YAnnotAbstractColumn) _eContainer_1).getName();
      boolean _equals = Objects.equal(_name, _name_1);
      if (_equals) {
        count++;
      }
    }
    EList<YAnnotForeignKey> _foreignkeys = table.getForeignkeys();
    for (final YAnnotForeignKey foreignKey : _foreignkeys) {
      EList<YAnnotAbstractColumn> _columns_1 = foreignKey.getColumns();
      for (final YAnnotAbstractColumn abstractColumn_1 : _columns_1) {
        String _name_2 = abstractColumn_1.getName();
        EObject _eContainer_2 = column.eContainer();
        String _name_3 = ((YAnnotAbstractColumn) _eContainer_2).getName();
        boolean _equals_1 = Objects.equal(_name_2, _name_3);
        if (_equals_1) {
          count++;
        }
      }
    }
    if ((count > 1)) {
      this.error("Table foreign key column name is not unique.", column.eContainer(), 
        LangPackage.Literals.YANNOT_ABSTRACT_COLUMN__NAME, LangValidator.COLUMN_NAME_NOT_UNIQUE);
      return;
    }
  }
  
  @Check
  public void checkIdentifierHasTechDesign(final YAnnotId identifier) {
    EObject _eContainer = identifier.eContainer();
    boolean _isTechnicalDesign = this._langUtil.isTechnicalDesign(((YAnnotEntity) _eContainer));
    if (_isTechnicalDesign) {
      EObject _eContainer_1 = identifier.eContainer();
      final YAnnotTable table = this._langUtil.getImplementingTable(((YAnnotEntity) _eContainer_1));
      YAnnotPrimaryKey _primarykey = table.getPrimarykey();
      boolean _tripleEquals = (_primarykey == null);
      if (_tripleEquals) {
        this.warning("The declared identifier is not yet implemented as primary key", identifier, 
          LangPackage.Literals.YANNOT_ID__NAME, LangValidator.IDENTIFIER_NO_TECH_DESIGN);
      }
    }
  }
  
  @Check
  public void checkRelationshipHasTechDesign(final YAnnotRel relationship) {
    EObject _eContainer = relationship.eContainer();
    boolean _isTechnicalDesign = this._langUtil.isTechnicalDesign(((YAnnotEntity) _eContainer));
    if (_isTechnicalDesign) {
      boolean _isMany = relationship.isMany();
      if (_isMany) {
        return;
      }
      boolean _isMany_1 = relationship.getInverse().isMany();
      if (_isMany_1) {
        EObject _eContainer_1 = relationship.eContainer();
        final YAnnotTable table = this._langUtil.getImplementingTable(((YAnnotEntity) _eContainer_1));
        if ((table != null)) {
          EList<YAnnotForeignKey> _foreignkeys = table.getForeignkeys();
          for (final YAnnotForeignKey foreignKey : _foreignkeys) {
            YAnnotRel _relationship = foreignKey.getRelationship();
            boolean _equals = Objects.equal(_relationship, relationship);
            if (_equals) {
              return;
            }
          }
          this.warning("The declared relationship is not yet implemented as a foreign key", relationship, 
            LangPackage.Literals.YANNOT_REL__NAME, LangValidator.RELATIONSSHIP_NOT_IMPLEMENTED);
        }
      }
    }
  }
  
  @Check
  public void checkRelationshipHasParentDesignated(final YAnnotRel relationship) {
    boolean _isParent = relationship.isParent();
    if (_isParent) {
      return;
    } else {
      if (((relationship.getInverse() != null) && relationship.getInverse().isParent())) {
        return;
      }
      String _name = relationship.getName();
      String _plus = ("Relation pair (" + _name);
      String _plus_1 = (_plus + ",");
      String _name_1 = relationship.getInverse().getName();
      String _plus_2 = (_plus_1 + _name_1);
      String _plus_3 = (_plus_2 + 
        ") does not have designated parent.");
      this.error(_plus_3, relationship, LangPackage.Literals.YANNOT_REL__NAME, 
        LangValidator.NO_DESGNATED_PARENT);
    }
  }
  
  @Check
  public void checkRelationshipHasOnlySingleParentDesignated(final YAnnotRel relationship) {
    if ((((relationship.getInverse() != null) && relationship.isParent()) && relationship.getInverse().isParent())) {
      String _name = relationship.getName();
      String _plus = ("Relation pair (" + _name);
      String _plus_1 = (_plus + ",");
      String _name_1 = relationship.getInverse().getName();
      String _plus_2 = (_plus_1 + _name_1);
      String _plus_3 = (_plus_2 + 
        ") can have  only one designated parent.");
      this.error(_plus_3, relationship, 
        LangPackage.Literals.YANNOT_REL__NAME, LangValidator.ONLY_ONE_DESGNATED_PARENT);
    }
  }
}
