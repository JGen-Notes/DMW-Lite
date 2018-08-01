package eu.jgen.notes.dmw.lite.generator;

import com.google.common.base.Objects;
import eu.jgen.notes.dmw.lite.generator.LangOutputProvider;
import eu.jgen.notes.dmw.lite.lang.YAnnotAbstractColumn;
import eu.jgen.notes.dmw.lite.lang.YAnnotColumn;
import eu.jgen.notes.dmw.lite.lang.YAnnotColumnLike;
import eu.jgen.notes.dmw.lite.lang.YAnnotForeignKey;
import eu.jgen.notes.dmw.lite.lang.YAnnotPrimaryKey;
import eu.jgen.notes.dmw.lite.lang.YAnnotTable;
import eu.jgen.notes.dmw.lite.lang.YAnnotTechnicalDesign;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.generator.IFileSystemAccess;
import org.eclipse.xtext.generator.IGenerator;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IteratorExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;

@SuppressWarnings("all")
public class LangTechicalDesignGenerator implements IGenerator {
  @Override
  public void doGenerate(final Resource input, final IFileSystemAccess fsa) {
    final Function1<EObject, Boolean> _function = (EObject element) -> {
      return Boolean.valueOf((element instanceof YAnnotTechnicalDesign));
    };
    final Procedure1<EObject> _function_1 = (EObject element) -> {
      final YAnnotTechnicalDesign technicalDesign = ((YAnnotTechnicalDesign) element);
      this.generateDDLForDerby(fsa, technicalDesign);
      String _name = technicalDesign.getDatabase().getName();
      boolean _equals = Objects.equal(_name, "MySQL");
      if (_equals) {
        this.generateDDLForMySQL(fsa, technicalDesign);
      }
    };
    IteratorExtensions.<EObject>forEach(IteratorExtensions.<EObject>filter(input.getAllContents(), _function), _function_1);
  }
  
  protected String generateDDLString(final IFileSystemAccess fsa, final YAnnotTechnicalDesign technicalDesign) {
    StringConcatenation _builder = new StringConcatenation();
    {
      EList<YAnnotTable> _features = technicalDesign.getFeatures();
      for(final YAnnotTable table : _features) {
        String _generateTables = this.generateTables(table);
        _builder.append(_generateTables);
        _builder.newLineIfNotEmpty();
      }
    }
    return _builder.toString();
  }
  
  protected void generateDDLForDerby(final IFileSystemAccess fsa, final YAnnotTechnicalDesign technicalDesign) {
    StringConcatenation _builder = new StringConcatenation();
    {
      EList<YAnnotTable> _features = technicalDesign.getFeatures();
      for(final YAnnotTable table : _features) {
        String _generateTables = this.generateTables(table);
        _builder.append(_generateTables);
        _builder.append("\t\t");
        _builder.newLineIfNotEmpty();
      }
    }
    fsa.generateFile(
      (("derby/" + "createdatabase") + ".ddl"), 
      LangOutputProvider.DDL, _builder);
  }
  
  protected void generateDDLForMySQL(final IFileSystemAccess fsa, final YAnnotTechnicalDesign technicalDesign) {
    StringConcatenation _builder = new StringConcatenation();
    {
      EList<YAnnotTable> _features = technicalDesign.getFeatures();
      for(final YAnnotTable table : _features) {
        String _generateTables = this.generateTables(table);
        _builder.append(_generateTables);
        _builder.append("\t\t");
        _builder.newLineIfNotEmpty();
      }
    }
    fsa.generateFile(
      (("mysql/" + "createdatabase") + ".ddl"), 
      LangOutputProvider.DDL, _builder);
  }
  
  private String generateTables(final YAnnotTable table) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("CREATE TABLE `");
    String _upperCase = table.getName().toUpperCase();
    _builder.append(_upperCase);
    _builder.append("` (");
    _builder.newLineIfNotEmpty();
    {
      EList<YAnnotAbstractColumn> _columns = table.getColumns();
      boolean _hasElements = false;
      for(final YAnnotAbstractColumn abstractColumn : _columns) {
        if (!_hasElements) {
          _hasElements = true;
        } else {
          _builder.appendImmediate(",", "\t");
        }
        _builder.append("\t");
        String _generateColumns = this.generateColumns(abstractColumn);
        _builder.append(_generateColumns, "\t");
        _builder.newLineIfNotEmpty();
      }
      if (_hasElements) {
        _builder.append(",", "\t");
      }
    }
    {
      EList<YAnnotForeignKey> _foreignkeys = table.getForeignkeys();
      boolean _hasElements_1 = false;
      for(final YAnnotForeignKey foreignKey : _foreignkeys) {
        if (!_hasElements_1) {
          _hasElements_1 = true;
        } else {
          _builder.appendImmediate(",", "\t");
        }
        _builder.append("\t");
        String _generateForeignKeyColumns = this.generateForeignKeyColumns(foreignKey);
        _builder.append(_generateForeignKeyColumns, "\t");
        _builder.newLineIfNotEmpty();
      }
      if (_hasElements_1) {
        _builder.append(",", "\t");
      }
    }
    _builder.append("\t");
    {
      YAnnotPrimaryKey _primarykey = table.getPrimarykey();
      boolean _tripleNotEquals = (_primarykey != null);
      if (_tripleNotEquals) {
        String _generatePrimaryKey = this.generatePrimaryKey(table);
        _builder.append(_generatePrimaryKey, "\t");
      }
    }
    _builder.newLineIfNotEmpty();
    _builder.append(");");
    _builder.newLine();
    final String text = _builder.toString();
    return text;
  }
  
  private String generateColumns(final YAnnotAbstractColumn abstractColumn) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("`");
    String _upperCase = abstractColumn.getName().toUpperCase();
    _builder.append(_upperCase);
    _builder.append("` ");
    String _generateColumnType = this.generateColumnType(abstractColumn);
    _builder.append(_generateColumnType);
    _builder.newLineIfNotEmpty();
    final String text = _builder.toString();
    return text;
  }
  
  private String generateForeignKeyColumns(final YAnnotForeignKey foreignKey) {
    StringConcatenation _builder = new StringConcatenation();
    {
      EList<YAnnotAbstractColumn> _columns = foreignKey.getColumns();
      for(final YAnnotAbstractColumn abstractColumn : _columns) {
        _builder.append("`");
        String _upperCase = abstractColumn.getName().toUpperCase();
        _builder.append(_upperCase);
        _builder.append("` ");
        String _generateColumnType = this.generateColumnType(abstractColumn);
        _builder.append(_generateColumnType);
        _builder.newLineIfNotEmpty();
      }
    }
    final String text = _builder.toString();
    return text;
  }
  
  private String generateColumnType(final YAnnotAbstractColumn abstractColumn) {
    EObject _type = abstractColumn.getType();
    if ((_type instanceof YAnnotColumn)) {
      EObject _type_1 = abstractColumn.getType();
      final YAnnotColumn column = ((YAnnotColumn) _type_1);
      StringConcatenation _builder = new StringConcatenation();
      String _type_2 = column.getType();
      _builder.append(_type_2);
      {
        if ((Objects.equal(column.getType(), "CHAR") || Objects.equal(column.getType(), "VARCHAR"))) {
          _builder.append("(");
          String _generateLength = this.generateLength(column);
          _builder.append(_generateLength);
          _builder.append(")");
        }
      }
      _builder.append(" ");
      String _generateNullFlag = this.generateNullFlag(column);
      _builder.append(_generateNullFlag);
      _builder.newLineIfNotEmpty();
      final String text = _builder.toString();
      return text;
    } else {
      EObject _type_3 = abstractColumn.getType();
      if ((_type_3 instanceof YAnnotColumnLike)) {
        EObject _type_4 = abstractColumn.getType();
        final YAnnotColumnLike columnLike = ((YAnnotColumnLike) _type_4);
        final YAnnotColumn column_1 = columnLike.getColumnref();
        StringConcatenation _builder_1 = new StringConcatenation();
        String _type_5 = column_1.getType();
        _builder_1.append(_type_5);
        {
          if ((Objects.equal(column_1.getType(), "CHAR") || Objects.equal(column_1.getType(), "VARCHAR"))) {
            _builder_1.append("(");
            String _generateLength_1 = this.generateLength(column_1);
            _builder_1.append(_generateLength_1);
            _builder_1.append(")");
          }
        }
        String _generateNullFlag_1 = this.generateNullFlag(column_1);
        _builder_1.append(_generateNullFlag_1);
        _builder_1.newLineIfNotEmpty();
        final String text_1 = _builder_1.toString();
        return text_1;
      }
    }
    return null;
  }
  
  private String generateLength(final YAnnotColumn column) {
    return null;
  }
  
  private String generateNullFlag(final YAnnotColumn column) {
    return null;
  }
  
  private String generatePrimaryKey(final YAnnotTable table) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("CONSTRAINT `");
    String _name = table.getName();
    String _plus = ("pk_" + _name);
    _builder.append(_plus);
    _builder.append("`");
    _builder.newLineIfNotEmpty();
    _builder.append("PRIMARY KEY (");
    {
      EList<YAnnotAbstractColumn> _columns = table.getPrimarykey().getColumns();
      boolean _hasElements = false;
      for(final YAnnotAbstractColumn abstractColumn : _columns) {
        if (!_hasElements) {
          _hasElements = true;
        } else {
          _builder.appendImmediate(",", "");
        }
        _builder.append("`");
        String _upperCase = abstractColumn.getName().toUpperCase();
        _builder.append(_upperCase);
        _builder.append("`");
      }
    }
    _builder.append(")");
    _builder.newLineIfNotEmpty();
    final String text = _builder.toString();
    return text;
  }
}
