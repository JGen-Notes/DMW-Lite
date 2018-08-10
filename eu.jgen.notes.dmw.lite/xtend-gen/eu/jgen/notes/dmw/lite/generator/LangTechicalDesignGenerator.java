package eu.jgen.notes.dmw.lite.generator;

import com.google.common.base.Objects;
import eu.jgen.notes.dmw.lite.generator.LangOutputProvider;
import eu.jgen.notes.dmw.lite.lang.YAnnot;
import eu.jgen.notes.dmw.lite.lang.YAnnotAbstractColumn;
import eu.jgen.notes.dmw.lite.lang.YAnnotColumn;
import eu.jgen.notes.dmw.lite.lang.YAnnotColumnLike;
import eu.jgen.notes.dmw.lite.lang.YAnnotForeignKey;
import eu.jgen.notes.dmw.lite.lang.YAnnotLength;
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
    };
    IteratorExtensions.<EObject>forEach(IteratorExtensions.<EObject>filter(input.getAllContents(), _function), _function_1);
  }
  
  protected void generateDDLForDerby(final IFileSystemAccess fsa, final YAnnotTechnicalDesign technicalDesign) {
    EList<YAnnotTable> _features = technicalDesign.getFeatures();
    for (final YAnnotTable table : _features) {
      this.generateTable(fsa, table);
    }
  }
  
  private void generateTable(final IFileSystemAccess fsa, final YAnnotTable table) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("CREATE TABLE \"");
    String _upperCase = table.getName().toUpperCase();
    _builder.append(_upperCase);
    _builder.append("\" (");
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
    _builder.append(")");
    _builder.newLine();
    final String text = _builder.toString();
    String _upperCase_1 = table.getName().toUpperCase();
    String _plus = ("derby/" + _upperCase_1);
    String _plus_1 = (_plus + ".ddl");
    fsa.generateFile(_plus_1, 
      LangOutputProvider.DDL, text);
  }
  
  private String generateColumns(final YAnnotAbstractColumn abstractColumn) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("\"");
    String _upperCase = abstractColumn.getName().toUpperCase();
    _builder.append(_upperCase);
    _builder.append("\" ");
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
        _builder.append("\"");
        String _upperCase = abstractColumn.getName().toUpperCase();
        _builder.append(_upperCase);
        _builder.append("\" ");
        String _generateColumnType = this.generateColumnType(abstractColumn);
        _builder.append(_generateColumnType);
        _builder.newLineIfNotEmpty();
      }
    }
    final String text = _builder.toString();
    return text;
  }
  
  private String generateColumnType(final YAnnotAbstractColumn abstractColumn) {
    String _xifexpression = null;
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
      _xifexpression = this.generateColumnType(((YAnnotColumnLike) _type_3).getColumnref());
    }
    return _xifexpression;
  }
  
  private String generateLength(final YAnnotColumn column) {
    EList<YAnnot> _annots = column.getAnnots();
    for (final YAnnot annot : _annots) {
      if ((annot instanceof YAnnotLength)) {
        final YAnnotLength a = ((YAnnotLength) annot);
        return Integer.valueOf(a.getLength()).toString();
      }
    }
    return "";
  }
  
  private String generateNullFlag(final YAnnotColumn column) {
    String _optional = column.getOptional();
    boolean _notEquals = (!Objects.equal(_optional, "?"));
    if (_notEquals) {
      return "NOT NULL";
    }
    return null;
  }
  
  private String generatePrimaryKey(final YAnnotTable table) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("CONSTRAINT \"");
    String _name = table.getName();
    String _plus = ("PK_" + _name);
    _builder.append(_plus);
    _builder.append("\"");
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
        _builder.append("\"");
        String _upperCase = abstractColumn.getName().toUpperCase();
        _builder.append(_upperCase);
        _builder.append("\"");
      }
    }
    _builder.append(")");
    _builder.newLineIfNotEmpty();
    final String text = _builder.toString();
    return text;
  }
}
