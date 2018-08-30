package eu.jgen.notes.dmw.lite.utility;

import com.google.common.base.CaseFormat;
import com.google.common.base.Objects;
import com.google.common.collect.Iterables;
import eu.jgen.notes.dmw.lite.lang.LangFactory;
import eu.jgen.notes.dmw.lite.lang.YAnnot;
import eu.jgen.notes.dmw.lite.lang.YAnnotAbstractColumn;
import eu.jgen.notes.dmw.lite.lang.YAnnotAttr;
import eu.jgen.notes.dmw.lite.lang.YAnnotColumn;
import eu.jgen.notes.dmw.lite.lang.YAnnotColumnLike;
import eu.jgen.notes.dmw.lite.lang.YAnnotDecimal;
import eu.jgen.notes.dmw.lite.lang.YAnnotEntity;
import eu.jgen.notes.dmw.lite.lang.YAnnotEntityInner;
import eu.jgen.notes.dmw.lite.lang.YAnnotForeignKey;
import eu.jgen.notes.dmw.lite.lang.YAnnotId;
import eu.jgen.notes.dmw.lite.lang.YAnnotLength;
import eu.jgen.notes.dmw.lite.lang.YAnnotPrimaryKey;
import eu.jgen.notes.dmw.lite.lang.YAnnotRel;
import eu.jgen.notes.dmw.lite.lang.YAnnotTable;
import eu.jgen.notes.dmw.lite.utility.LangUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import javax.inject.Inject;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;

@SuppressWarnings("all")
public class LangDBUtil {
  @Inject
  @Extension
  private LangUtil _langUtil;
  
  public String getColumnName(final YAnnotColumn column) {
    EObject _eContainer = column.eContainer();
    return ((YAnnotAbstractColumn) _eContainer).getName();
  }
  
  public String getColumnTypeName(final YAnnotAbstractColumn abstractColumn) {
    String _xblockexpression = null;
    {
      EObject _type = abstractColumn.getType();
      if ((_type instanceof YAnnotColumn)) {
        EObject _type_1 = abstractColumn.getType();
        return ((YAnnotColumn) _type_1).getType();
      }
      _xblockexpression = "yet unknown";
    }
    return _xblockexpression;
  }
  
  public int getColumnLength(final YAnnotAbstractColumn abstractColumn) {
    int _xblockexpression = (int) 0;
    {
      EObject _type = abstractColumn.getType();
      if ((_type instanceof YAnnotColumn)) {
        EObject _type_1 = abstractColumn.getType();
        return this.extractLength(((YAnnotColumn) _type_1));
      }
      _xblockexpression = 0;
    }
    return _xblockexpression;
  }
  
  public boolean isColumnOptional(final YAnnotAbstractColumn abstractColumn) {
    boolean _xblockexpression = false;
    {
      EObject _type = abstractColumn.getType();
      if ((_type instanceof YAnnotColumn)) {
        EObject _type_1 = abstractColumn.getType();
        final YAnnotColumn column = ((YAnnotColumn) _type_1);
        String _optional = column.getOptional();
        boolean _tripleEquals = (_optional == null);
        if (_tripleEquals) {
          return false;
        } else {
          String _optional_1 = column.getOptional();
          boolean _equals = Objects.equal(_optional_1, "?");
          if (_equals) {
            return true;
          }
        }
      }
      _xblockexpression = false;
    }
    return _xblockexpression;
  }
  
  public int getColumnDecimal(final YAnnotAbstractColumn abstractColumn) {
    int _xblockexpression = (int) 0;
    {
      EObject _type = abstractColumn.getType();
      if ((_type instanceof YAnnotColumn)) {
        EObject _type_1 = abstractColumn.getType();
        return this.extractDecimal(((YAnnotColumn) _type_1));
      }
      _xblockexpression = 0;
    }
    return _xblockexpression;
  }
  
  public YAnnotAbstractColumn converAttributeIntoAbstractColumn(final YAnnotAttr attribute) {
    YAnnotAbstractColumn _createYAnnotAbstractColumn = LangFactory.eINSTANCE.createYAnnotAbstractColumn();
    final Procedure1<YAnnotAbstractColumn> _function = (YAnnotAbstractColumn it) -> {
      it.setName(this.convertToTechnicalDesignName(attribute.getName()));
      YAnnotColumn _createYAnnotColumn = LangFactory.eINSTANCE.createYAnnotColumn();
      final Procedure1<YAnnotColumn> _function_1 = (YAnnotColumn it_1) -> {
        it_1.setAttrref(attribute);
      };
      final YAnnotColumn column = ObjectExtensions.<YAnnotColumn>operator_doubleArrow(_createYAnnotColumn, _function_1);
      it.setType(column);
      this.selectColumnProperties(column, attribute);
    };
    final YAnnotAbstractColumn abstractColumn = ObjectExtensions.<YAnnotAbstractColumn>operator_doubleArrow(_createYAnnotAbstractColumn, _function);
    return abstractColumn;
  }
  
  private void selectColumnProperties(final YAnnotColumn column, final YAnnotAttr attribute) {
    String _name = attribute.getYclass().getName();
    boolean _equals = Objects.equal(_name, "String");
    if (_equals) {
      column.setType("CHAR");
    } else {
      String _name_1 = attribute.getYclass().getName();
      boolean _equals_1 = Objects.equal(_name_1, "String");
      if (_equals_1) {
        column.setType("CHAR");
      } else {
        String _name_2 = attribute.getYclass().getName();
        boolean _equals_2 = Objects.equal(_name_2, "Short");
        if (_equals_2) {
          column.setType("SMALLINT");
        } else {
          String _name_3 = attribute.getYclass().getName();
          boolean _equals_3 = Objects.equal(_name_3, "Int");
          if (_equals_3) {
            column.setType("INTEGER");
          } else {
            String _name_4 = attribute.getYclass().getName();
            boolean _equals_4 = Objects.equal(_name_4, "Double");
            if (_equals_4) {
              column.setType("DECIMAL");
            } else {
              String _name_5 = attribute.getYclass().getName();
              boolean _equals_5 = Objects.equal(_name_5, "Date");
              if (_equals_5) {
                column.setType("DATE");
              } else {
                String _name_6 = attribute.getYclass().getName();
                boolean _equals_6 = Objects.equal(_name_6, "Time");
                if (_equals_6) {
                  column.setType("TIME");
                } else {
                  String _name_7 = attribute.getYclass().getName();
                  boolean _equals_7 = Objects.equal(_name_7, "Timestamp");
                  if (_equals_7) {
                    column.setType("TIMESTAMP");
                  } else {
                    String _name_8 = attribute.getYclass().getName();
                    boolean _equals_8 = Objects.equal(_name_8, "Blob");
                    if (_equals_8) {
                      column.setType("BLOB");
                    }
                  }
                }
              }
            }
          }
        }
      }
    }
    final Consumer<YAnnot> _function = (YAnnot annot) -> {
    };
    attribute.getAnnots().forEach(_function);
    if (((attribute.getOptional() != null) && Objects.equal(attribute.getOptional(), "?"))) {
      column.setOptional("?");
    }
  }
  
  public YAnnot cloneYAnnot(final YAnnot annot) {
    Object _switchResult = null;
    boolean _matched = false;
    if ((annot instanceof YAnnotLength)) {
      _matched=true;
      YAnnotLength _createYAnnotLength = LangFactory.eINSTANCE.createYAnnotLength();
      final Procedure1<YAnnotLength> _function = (YAnnotLength it) -> {
        it.setLength(((YAnnotLength) annot).getLength());
      };
      final YAnnotLength clone = ObjectExtensions.<YAnnotLength>operator_doubleArrow(_createYAnnotLength, _function);
      return clone;
    }
    if (!_matched) {
      if ((annot instanceof YAnnotDecimal)) {
        _matched=true;
        YAnnotDecimal _createYAnnotDecimal = LangFactory.eINSTANCE.createYAnnotDecimal();
        final Procedure1<YAnnotDecimal> _function_1 = (YAnnotDecimal it) -> {
          it.setLength(((YAnnotDecimal) annot).getLength());
          it.setDecimal(((YAnnotDecimal) annot).getDecimal());
        };
        final YAnnotDecimal clone_1 = ObjectExtensions.<YAnnotDecimal>operator_doubleArrow(_createYAnnotDecimal, _function_1);
        return clone_1;
      }
    }
    if (!_matched) {
      _switchResult = null;
    }
    return ((YAnnot)_switchResult);
  }
  
  public YAnnotTable converEntityIntoTable(final YAnnotEntity entity) {
    YAnnotTable _createYAnnotTable = LangFactory.eINSTANCE.createYAnnotTable();
    final Procedure1<YAnnotTable> _function = (YAnnotTable it) -> {
      it.setName(this.convertToTechnicalDesignName(entity.getName()));
      it.setEntityref(entity);
    };
    final YAnnotTable table = ObjectExtensions.<YAnnotTable>operator_doubleArrow(_createYAnnotTable, _function);
    return table;
  }
  
  public YAnnotForeignKey converRelationshipIntoForeignKeys(final YAnnotRel relationship) {
    return this.createForeignKeyForRelationship(relationship, relationship.getInverse());
  }
  
  private YAnnotForeignKey createForeignKeyForRelationship(final YAnnotRel thisrelationship, final YAnnotRel targetRelationship) {
    YAnnotForeignKey _createYAnnotForeignKey = LangFactory.eINSTANCE.createYAnnotForeignKey();
    final Procedure1<YAnnotForeignKey> _function = (YAnnotForeignKey it) -> {
      final List<YAnnotAbstractColumn> list = this.convertRetaionshipIntoFKColumns(targetRelationship);
      for (final YAnnotAbstractColumn abstractColumn : list) {
        it.getColumns().add(abstractColumn);
      }
      it.setRelationship(thisrelationship);
    };
    final YAnnotForeignKey foreignKey = ObjectExtensions.<YAnnotForeignKey>operator_doubleArrow(_createYAnnotForeignKey, _function);
    return foreignKey;
  }
  
  private List<YAnnotAbstractColumn> convertRetaionshipIntoFKColumns(final YAnnotRel relationship) {
    final ArrayList<YAnnotAbstractColumn> list = CollectionLiterals.<YAnnotAbstractColumn>newArrayList();
    EObject _eContainer = relationship.eContainer();
    final YAnnotEntity parentEntity = ((YAnnotEntity) _eContainer);
    final YAnnotTable parentTable = this._langUtil.getImplementingTable(parentEntity);
    if (((parentTable == null) || (parentTable.getPrimarykey() == null))) {
      return list;
    }
    EList<YAnnotAbstractColumn> _columns = parentTable.getPrimarykey().getColumns();
    for (final YAnnotAbstractColumn abstractColumn : _columns) {
      {
        YAnnotAbstractColumn _createYAnnotAbstractColumn = LangFactory.eINSTANCE.createYAnnotAbstractColumn();
        final Procedure1<YAnnotAbstractColumn> _function = (YAnnotAbstractColumn it) -> {
          String _name = parentTable.getName();
          String _plus = ("FK_" + _name);
          String _plus_1 = (_plus + "__");
          String _name_1 = abstractColumn.getName();
          String _plus_2 = (_plus_1 + _name_1);
          it.setName(_plus_2);
          YAnnotColumnLike _createYAnnotColumnLike = LangFactory.eINSTANCE.createYAnnotColumnLike();
          final Procedure1<YAnnotColumnLike> _function_1 = (YAnnotColumnLike it_1) -> {
            it_1.setColumnref(abstractColumn);
          };
          final YAnnotColumnLike annotColumnLike = ObjectExtensions.<YAnnotColumnLike>operator_doubleArrow(_createYAnnotColumnLike, _function_1);
          it.setType(annotColumnLike);
        };
        final YAnnotAbstractColumn newAbstractColumn = ObjectExtensions.<YAnnotAbstractColumn>operator_doubleArrow(_createYAnnotAbstractColumn, _function);
        list.add(newAbstractColumn);
      }
    }
    return list;
  }
  
  public YAnnotPrimaryKey converIdentifierIntoPrimaryKey(final YAnnotId identifier) {
    YAnnotPrimaryKey _createYAnnotPrimaryKey = LangFactory.eINSTANCE.createYAnnotPrimaryKey();
    final Procedure1<YAnnotPrimaryKey> _function = (YAnnotPrimaryKey it) -> {
      final List<YAnnotAbstractColumn> list = this.convertIdentifierIntoColumns(identifier);
      for (final YAnnotAbstractColumn abstarctColumn : list) {
        it.getColumns().add(abstarctColumn);
      }
    };
    final YAnnotPrimaryKey primaryKey = ObjectExtensions.<YAnnotPrimaryKey>operator_doubleArrow(_createYAnnotPrimaryKey, _function);
    return primaryKey;
  }
  
  private List<YAnnotAbstractColumn> convertIdentifierIntoColumns(final YAnnotId identifier) {
    final List<YAnnotAbstractColumn> list = CollectionLiterals.<YAnnotAbstractColumn>newArrayList();
    EList<YAnnotEntityInner> _annots = identifier.getAnnots();
    for (final YAnnotEntityInner identifierPart : _annots) {
      if ((identifierPart instanceof YAnnotAttr)) {
        final YAnnotAttr attribute = ((YAnnotAttr) identifierPart);
        EObject _eContainer = attribute.eContainer();
        final YAnnotTable parentTable = this._langUtil.getImplementingTable(((YAnnotEntity) _eContainer));
        EList<YAnnotAbstractColumn> _columns = parentTable.getColumns();
        for (final YAnnotAbstractColumn abstractColumn : _columns) {
          EObject _type = abstractColumn.getType();
          if ((_type instanceof YAnnotColumn)) {
            EObject _type_1 = abstractColumn.getType();
            final YAnnotColumn column = ((YAnnotColumn) _type_1);
            YAnnotAttr _attrref = column.getAttrref();
            boolean _equals = Objects.equal(_attrref, attribute);
            if (_equals) {
              list.add(abstractColumn);
            }
          }
        }
      } else {
        if ((identifierPart instanceof YAnnotRel)) {
          final YAnnotRel relationship = ((YAnnotRel) identifierPart);
          EObject _eContainer_1 = relationship.eContainer();
          final YAnnotTable parentTable_1 = this._langUtil.getImplementingTable(((YAnnotEntity) _eContainer_1));
          EList<YAnnotForeignKey> _foreignkeys = parentTable_1.getForeignkeys();
          for (final YAnnotForeignKey foreignKey : _foreignkeys) {
            YAnnotRel _relationship = foreignKey.getRelationship();
            boolean _equals_1 = Objects.equal(_relationship, relationship);
            if (_equals_1) {
              EList<YAnnotAbstractColumn> _columns_1 = foreignKey.getColumns();
              for (final YAnnotAbstractColumn abstractColumn_1 : _columns_1) {
                EObject _type_2 = abstractColumn_1.getType();
                if ((_type_2 instanceof YAnnotColumnLike)) {
                  list.add(abstractColumn_1);
                }
              }
            }
          }
        }
      }
    }
    return list;
  }
  
  public String convertToTechnicalDesignName(final String name) {
    return CaseFormat.UPPER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, name);
  }
  
  public int extractLength(final YAnnotColumn column) {
    final YAnnotLength annot = IterableExtensions.<YAnnotLength>head(Iterables.<YAnnotLength>filter(column.getAnnots(), YAnnotLength.class));
    if ((annot != null)) {
      return annot.getLength();
    }
    final YAnnotDecimal annot2 = IterableExtensions.<YAnnotDecimal>head(Iterables.<YAnnotDecimal>filter(column.getAnnots(), YAnnotDecimal.class));
    if ((annot2 != null)) {
      return annot2.getLength();
    }
    return 0;
  }
  
  public int extractDecimal(final YAnnotColumn column) {
    final YAnnotDecimal annot = IterableExtensions.<YAnnotDecimal>head(Iterables.<YAnnotDecimal>filter(column.getAnnots(), YAnnotDecimal.class));
    if ((annot != null)) {
      return annot.getDecimal();
    }
    return 0;
  }
}
