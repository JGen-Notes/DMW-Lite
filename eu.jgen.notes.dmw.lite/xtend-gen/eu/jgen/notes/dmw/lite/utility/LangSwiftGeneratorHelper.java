package eu.jgen.notes.dmw.lite.utility;

import com.google.common.base.Objects;
import com.google.inject.Inject;
import eu.jgen.notes.dmw.lite.lang.YAnnotAbstractColumn;
import eu.jgen.notes.dmw.lite.lang.YAnnotColumn;
import eu.jgen.notes.dmw.lite.lang.YAnnotColumnLike;
import eu.jgen.notes.dmw.lite.lang.YAnnotPrimaryKey;
import eu.jgen.notes.dmw.lite.lang.YAnnotTable;
import eu.jgen.notes.dmw.lite.lang.YClass;
import eu.jgen.notes.dmw.lite.lang.YProperty;
import eu.jgen.notes.dmw.lite.lang.YWidget;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.common.types.JvmIdentifiableElement;
import org.eclipse.xtext.documentation.IEObjectDocumentationProvider;
import org.eclipse.xtext.xbase.compiler.DocumentationAdapter;

@SuppressWarnings("all")
public class LangSwiftGeneratorHelper {
  @Inject
  private IEObjectDocumentationProvider documentationProvider;
  
  public String getDocumentation(final EObject source) {
    if ((source == null)) {
      return null;
    }
    if ((source instanceof JvmIdentifiableElement)) {
      Adapter _adapter = EcoreUtil.getAdapter(((JvmIdentifiableElement)source).eAdapters(), DocumentationAdapter.class);
      final DocumentationAdapter adapter = ((DocumentationAdapter) _adapter);
      if ((adapter != null)) {
        return this.wrapAsSwiftComment(adapter.getDocumentation());
      }
    }
    final String documentation = this.documentationProvider.getDocumentation(source);
    return this.wrapAsSwiftComment(documentation);
  }
  
  private String wrapAsSwiftComment(final String documentation) {
    if ((documentation == null)) {
      return "";
    } else {
      final String[] array = documentation.split("\n");
      StringBuffer buf = new StringBuffer();
      buf.append("/*");
      for (final String line : array) {
        buf.append(("\n * " + line));
      }
      buf.append("\n */");
      return buf.toString();
    }
  }
  
  public String findPackageName(final YProperty property) {
    Object _xblockexpression = null;
    {
      final EObject a = property.getType().eContainer();
      Object _xifexpression = null;
      if ((a instanceof YWidget)) {
        _xifexpression = null;
      } else {
        Object _xifexpression_1 = null;
        if ((a instanceof YClass)) {
          _xifexpression_1 = null;
        } else {
          return "<do not know what to do yet>";
        }
        _xifexpression = _xifexpression_1;
      }
      _xblockexpression = _xifexpression;
    }
    return ((String)_xblockexpression);
  }
  
  public String getSwiftColumnName(final YAnnotAbstractColumn abstractColumn) {
    EObject _type = abstractColumn.getType();
    if ((_type instanceof YAnnotColumn)) {
      EObject _type_1 = abstractColumn.getType();
      final YAnnotColumn annotColumn = ((YAnnotColumn) _type_1);
      return annotColumn.getAttrref().getName();
    }
    return "";
  }
  
  public String getSwiftColumnType(final YAnnotAbstractColumn abstractColumn) {
    String prkeytext = "";
    boolean _isSwiftPrimarykey = this.isSwiftPrimarykey(abstractColumn);
    if (_isSwiftPrimarykey) {
      prkeytext = ", primaryKey: true";
    }
    EObject _type = abstractColumn.getType();
    if ((_type instanceof YAnnotColumn)) {
      EObject _type_1 = abstractColumn.getType();
      String _swiftColumnType = this.getSwiftColumnType(((YAnnotColumn) _type_1));
      return (_swiftColumnType + prkeytext);
    } else {
      EObject _type_2 = abstractColumn.getType();
      if ((_type_2 instanceof YAnnotColumnLike)) {
        EObject _type_3 = abstractColumn.getType();
        String _swiftColumnType_1 = this.getSwiftColumnType(((YAnnotColumnLike) _type_3).getColumnref());
        return (_swiftColumnType_1 + prkeytext);
      }
    }
    return "";
  }
  
  public String getSwiftColumnType(final YAnnotColumn annotColumn) {
    String _type = annotColumn.getType();
    if (_type != null) {
      switch (_type) {
        case "VARCHAR":
          return "String.self";
        case "CHAR":
          return "String.self";
        case "BIGINT":
          return "Int64.self";
        case "INTEGER":
          return "Int32.self";
        case "SMALLINT":
          return "Int16.self";
        case "DECIMAL":
          return "Double.self";
        case "TIME":
          return "Date.self";
        case "TIMESTAMP":
          return "Date.self";
        case "DATE":
          return "Date.self";
        case "BOOLEAN":
          return "Bool.self";
        default:
          return "unknown";
      }
    } else {
      return "unknown";
    }
  }
  
  public boolean isSwiftPrimarykey(final YAnnotAbstractColumn abstractColumn) {
    EObject _eContainer = abstractColumn.eContainer();
    final YAnnotTable table = ((YAnnotTable) _eContainer);
    final YAnnotPrimaryKey pr = table.getPrimarykey();
    if ((pr == null)) {
      return false;
    }
    EList<YAnnotAbstractColumn> _columns = pr.getColumns();
    for (final YAnnotAbstractColumn column : _columns) {
      String _name = column.getName();
      String _name_1 = abstractColumn.getName();
      boolean _equals = Objects.equal(_name, _name_1);
      if (_equals) {
        return true;
      }
    }
    return false;
  }
}
