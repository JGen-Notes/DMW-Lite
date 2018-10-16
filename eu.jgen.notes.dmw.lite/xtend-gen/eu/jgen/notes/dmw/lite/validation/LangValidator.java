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
import eu.jgen.notes.dmw.lite.lang.YWidget;
import eu.jgen.notes.dmw.lite.utility.LangUtil;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.naming.IQualifiedNameProvider;
import org.eclipse.xtext.validation.Check;
import org.eclipse.xtext.validation.CheckType;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.StringExtensions;
import org.eclipse.xtext.xbase.validation.XbaseValidator;

/**
 * This class contains custom validation rules.
 * 
 * See https://www.eclipse.org/Xtext/documentation/303_runtime_concepts.html#validation
 */
@SuppressWarnings("all")
public class LangValidator extends XbaseValidator {
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
  
  public final static String CLASS_NEED_TO_BE_EXTENDED = (LangValidator.ISSUE_CODE_PREFIX + "ClassNeedToBeExtended");
  
  public final static String CLASS_NEED_TO_HAVE_PROPERTIES = (LangValidator.ISSUE_CODE_PREFIX + "ClassNeedToHaveProperties");
  
  public final static String CLASS_NAME_FIRST_CHARACTER_NOT_CAPITAL = (LangValidator.ISSUE_CODE_PREFIX + "ClassNameFirstCharacterNotCapital");
  
  public final static String ENTITY_NAME_FIRST_CHARACTER_NOT_CAPITAL = (LangValidator.ISSUE_CODE_PREFIX + "EntityNameFirstCharacterNotCapital");
  
  public final static String ATTRIBUTE_NAME_FIRST_CHARACTER_NOT_LOWERCASE = (LangValidator.ISSUE_CODE_PREFIX + 
    "AttributeNameFirstCharacterNotLowercase");
  
  public final static String FUNCTION_NAME_FIRST_CHARACTER_NOT_LOWERCASE = (LangValidator.ISSUE_CODE_PREFIX + 
    "FunctionNameFirstCharacterNotLowercase");
  
  public final static String PROPERTY_NAME_FIRST_CHARACTER_NOT_LOWERCASE = (LangValidator.ISSUE_CODE_PREFIX + 
    "PropertyNameFirstCharacterNotLowercase");
  
  public final static String VARIABLE_NAME_FIRST_CHARACTER_NOT_LOWERCASE = (LangValidator.ISSUE_CODE_PREFIX + 
    "VariableNameFirstCharacterNotLowercase");
  
  public final static String ATTRIBUTE_TYPE_NOT_COMP_WITH_DEFAULT = (LangValidator.ISSUE_CODE_PREFIX + "AttributeTypeNotCompatibleWithDefault");
  
  @Inject
  @Extension
  private LangUtil _langUtil;
  
  @Inject
  @Extension
  private IQualifiedNameProvider _iQualifiedNameProvider;
  
  /**
   * Supported Database
   */
  @Check
  public void checkSupportedDatabase(final YAnnotDatabase annotDatabase) {
    String _name = annotDatabase.getName();
    boolean _tripleNotEquals = (_name != null);
    if (_tripleNotEquals) {
      if (((((Objects.equal(annotDatabase.getName(), "Derby") || Objects.equal(annotDatabase.getName(), "MySQL")) || Objects.equal(annotDatabase.getName(), "SQLite")) || 
        Objects.equal(annotDatabase.getName(), "PostgreSQL")) || Objects.equal(annotDatabase.getName(), "MongoDB"))) {
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
  public Object checkEntityHierarchy(final YAnnotEntity entity) {
    return null;
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
  
  @Check
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
      this.error(_plus_3, relationship, LangPackage.Literals.YANNOT_REL__NAME, 
        LangValidator.ONLY_ONE_DESGNATED_PARENT);
    }
  }
  
  @Check
  public void checkEntityNameStartsWithCapital(final YAnnotEntity annotEntity) {
    String _firstUpper = StringExtensions.toFirstUpper(annotEntity.getName());
    String _name = annotEntity.getName();
    boolean _notEquals = (!Objects.equal(_firstUpper, _name));
    if (_notEquals) {
      this.error("Entity name should start with a capital letter", annotEntity, 
        LangPackage.eINSTANCE.getYAnnotEntity_Name(), LangValidator.ENTITY_NAME_FIRST_CHARACTER_NOT_CAPITAL);
    }
  }
  
  @Check
  public void checkAttributeNameStartsWithLowecase(final YAnnotAttr annotAttr) {
    String _firstLower = StringExtensions.toFirstLower(annotAttr.getName());
    String _name = annotAttr.getName();
    boolean _notEquals = (!Objects.equal(_firstLower, _name));
    if (_notEquals) {
      this.error("Attribute name should start with a lower case letter", annotAttr, 
        LangPackage.eINSTANCE.getYAnnotAttr_Name(), LangValidator.ATTRIBUTE_NAME_FIRST_CHARACTER_NOT_LOWERCASE);
    }
  }
  
  @Check
  public void checkAttributeTypeMatchesDefaultValueIfAny(final YAnnotAttr annotAttr) {
    boolean _isTypeCompatibleWithDefault = this._langUtil.isTypeCompatibleWithDefault(annotAttr);
    boolean _not = (!_isTypeCompatibleWithDefault);
    if (_not) {
      this.error("Attribute type not compatible with default value", annotAttr, 
        LangPackage.eINSTANCE.getYAnnotAttr_Yclass(), LangValidator.ATTRIBUTE_TYPE_NOT_COMP_WITH_DEFAULT);
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
}
