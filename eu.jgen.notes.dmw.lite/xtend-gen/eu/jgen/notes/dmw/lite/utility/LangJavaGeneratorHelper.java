package eu.jgen.notes.dmw.lite.utility;

import com.google.common.base.Objects;
import com.google.inject.Inject;
import eu.jgen.notes.dmw.lite.lang.YAnnot;
import eu.jgen.notes.dmw.lite.lang.YAnnotAttr;
import eu.jgen.notes.dmw.lite.lang.YAnnotDefault;
import eu.jgen.notes.dmw.lite.lang.YAnnotDefaultNumber;
import eu.jgen.notes.dmw.lite.lang.YAnnotDefaultText;
import eu.jgen.notes.dmw.lite.lang.YAnnotMax;
import eu.jgen.notes.dmw.lite.lang.YAnnotTable;
import eu.jgen.notes.dmw.lite.lang.YClass;
import eu.jgen.notes.dmw.lite.lang.YMember;
import eu.jgen.notes.dmw.lite.lang.YProperty;
import eu.jgen.notes.dmw.lite.lang.YReadEachStatement;
import eu.jgen.notes.dmw.lite.lang.YReadStatement;
import eu.jgen.notes.dmw.lite.lang.YStructRefPair;
import eu.jgen.notes.dmw.lite.lang.YWidget;
import eu.jgen.notes.dmw.lite.utility.LangUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.documentation.IEObjectDocumentationProvider;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Extension;

@SuppressWarnings("all")
public class LangJavaGeneratorHelper {
  private final String SYSTEM_DEFAULT_STRING = "\"\"";
  
  private final String SYSTEM_DEFAULT_INT = "0";
  
  private final String SYSTEM_DEFAULT_SHORT = "0";
  
  private final String SYSTEM_DEFAULT_DOUBLE = "0.0";
  
  private final String SYSTEM_DEFAULT_LONG = "0";
  
  private final String SYSTEM_DEFAULT_BOOLEAN = "true";
  
  private final String SYSTEM_DEFAULT_DATE = "new Date(System.currentTimeMillis())";
  
  private final String SYSTEM_DEFAULT_TIME = "new Time(System.currentTimeMillis())";
  
  private final String SYSTEM_DEFAULT_TIMESTAMP = "new Timestamp(System.currentTimeMillis())";
  
  private Map<String, Integer> usedNames = new HashMap<String, Integer>();
  
  @Inject
  @Extension
  private LangUtil _langUtil;
  
  @Inject
  private IEObjectDocumentationProvider documentationProvider;
  
  private String wrapAsJavaComment(final String documentation) {
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
  
  public String translateTypeName(final String typeName) {
    if (typeName != null) {
      switch (typeName) {
        case "Int":
          return "int";
        case "Short":
          return "short";
        case "Long":
          return "long";
        case "Double":
          return "double";
        case "String":
          return "String";
        case "Bool":
          return "boolean";
        case "Date":
          return "Date";
        case "Time":
          return "Time";
        case "Timestamp":
          return "Timestamp";
        default:
          return typeName;
      }
    } else {
      return typeName;
    }
  }
  
  public String getPropertyDefaultValue(final YProperty property) {
    String _xifexpression = null;
    YAnnotAttr _attrRef = property.getAttrRef();
    boolean _tripleEquals = (_attrRef == null);
    if (_tripleEquals) {
      _xifexpression = this.getSystemDefault(this.translateTypeName(property.getType().getSimpleName()));
    } else {
      _xifexpression = this.findDefaultFromAnnot(property);
    }
    return _xifexpression;
  }
  
  public String getSystemDefault(final String type) {
    if (type != null) {
      switch (type) {
        case "int":
          return this.SYSTEM_DEFAULT_INT;
        case "short":
          return this.SYSTEM_DEFAULT_SHORT;
        case "long":
          return this.SYSTEM_DEFAULT_LONG;
        case "double":
          return this.SYSTEM_DEFAULT_DOUBLE;
        case "String":
          return this.SYSTEM_DEFAULT_STRING;
        case "boolean":
          return this.SYSTEM_DEFAULT_BOOLEAN;
        case "Date":
          return this.SYSTEM_DEFAULT_DATE;
        case "Time":
          return this.SYSTEM_DEFAULT_TIME;
        case "Timestamp":
          return this.SYSTEM_DEFAULT_TIMESTAMP;
        default:
          return "?";
      }
    } else {
      return "?";
    }
  }
  
  public String findDefaultFromAnnot(final YProperty property) {
    EList<YAnnot> _annots = property.getAttrRef().getAnnots();
    for (final YAnnot annotation : _annots) {
      EObject _type = annotation.getType();
      if ((_type instanceof YAnnotDefault)) {
        EObject _type_1 = annotation.getType();
        final YAnnotDefault annotDefault = ((YAnnotDefault) _type_1);
        EObject _type_2 = annotDefault.getType();
        if ((_type_2 instanceof YAnnotDefaultText)) {
          EObject _type_3 = annotDefault.getType();
          final String value = ((YAnnotDefaultText) _type_3).getValue();
          return (("\"" + value) + "\"");
        } else {
          EObject _type_4 = annotDefault.getType();
          if ((_type_4 instanceof YAnnotDefaultNumber)) {
            EObject _type_5 = annotDefault.getType();
            final int value_1 = ((YAnnotDefaultNumber) _type_5).getValue();
            return String.valueOf(value_1);
          } else {
            return "?";
          }
        }
      }
    }
    return this.getSystemDefault(this.translateTypeName(property.getName()));
  }
  
  /**
   * Find all properties of type Array
   */
  public ArrayList<YProperty> findPropertiesOfTypeArray(final YClass eClass) {
    final ArrayList<YProperty> array = CollectionLiterals.<YProperty>newArrayList();
    EList<YMember> _members = eClass.getMembers();
    for (final YMember member : _members) {
      if ((member instanceof YProperty)) {
        final YProperty property = ((YProperty) member);
        String _name = property.getName();
        boolean _equals = Objects.equal(_name, "Array");
        if (_equals) {
          array.add(property);
        }
      }
    }
    return array;
  }
  
  /**
   * Get size of the array.
   */
  public int getArraySize(final YProperty property) {
    EList<YAnnot> _annotations = property.getAnnotations();
    for (final YAnnot annotation : _annotations) {
      EObject _type = annotation.getType();
      if ((_type instanceof YAnnotMax)) {
        EObject _type_1 = annotation.getType();
        final YAnnotMax annotMax = ((YAnnotMax) _type_1);
        return annotMax.getLength();
      }
    }
    return 0;
  }
  
  public ArrayList<String> createQualifiedColumnNamesListForRead(final YReadStatement readStatement) {
    final ArrayList<String> list = CollectionLiterals.<String>newArrayList();
    int index = 1;
    EList<YStructRefPair> _structs = readStatement.getStructs();
    for (final YStructRefPair struct : _structs) {
      {
        final YAnnotTable implementingTable = this._langUtil.getImplementingTable(struct.getStructclass());
        EList<YMember> _members = this._langUtil.findStructureDeclaration(struct.getStructproperty()).getMembers();
        for (final YMember member : _members) {
          StringConcatenation _builder = new StringConcatenation();
          _builder.append("T");
          _builder.append(index);
          _builder.append(".\\\"");
          String _implementingColumnName = this._langUtil.getImplementingColumnName(implementingTable, member);
          _builder.append(_implementingColumnName);
          _builder.append("\\\"");
          list.add(_builder.toString());
        }
        index++;
      }
    }
    return list;
  }
  
  public ArrayList<String> createQualifiedColumnNamesListForReadEach(final YReadEachStatement readEachStatement) {
    final ArrayList<String> list = CollectionLiterals.<String>newArrayList();
    int index = 1;
    EList<YStructRefPair> _structs = readEachStatement.getStructs();
    for (final YStructRefPair struct : _structs) {
      {
        final YAnnotTable implementingTable = this._langUtil.getImplementingTable(struct.getStructclass());
        EList<YMember> _members = this._langUtil.findStructureDeclaration(struct.getStructproperty()).getMembers();
        for (final YMember member : _members) {
          StringConcatenation _builder = new StringConcatenation();
          _builder.append("T");
          _builder.append(index);
          _builder.append(".\\\"");
          String _implementingColumnName = this._langUtil.getImplementingColumnName(implementingTable, member);
          _builder.append(_implementingColumnName);
          _builder.append("\\\"");
          list.add(_builder.toString());
        }
        index++;
      }
    }
    return list;
  }
  
  public ArrayList<String> generateFROMClause(final YReadStatement readStatement) {
    final ArrayList<String> list = CollectionLiterals.<String>newArrayList();
    int index = 1;
    if ((readStatement instanceof YReadStatement)) {
      EList<YStructRefPair> _structs = readStatement.getStructs();
      for (final YStructRefPair struct : _structs) {
        {
          final YAnnotTable implementingTable = this._langUtil.getImplementingTable(readStatement.getStructs().get(0).getStructclass());
          StringConcatenation _builder = new StringConcatenation();
          _builder.append("\\\"");
          String _name = implementingTable.getName();
          _builder.append(_name);
          _builder.append("\\\" T");
          _builder.append(index);
          list.add(_builder.toString());
          index++;
        }
      }
    }
    return list;
  }
  
  public boolean isVaraibleProperty(final ArrayList<String> readProperties, final String name) {
    for (final String property : readProperties) {
      boolean _equals = Objects.equal(property, name);
      if (_equals) {
        return true;
      }
    }
    return false;
  }
  
  public void resetLocalNames() {
    this.usedNames.clear();
  }
  
  public String generateLocalName(final String corename) {
    boolean _containsKey = this.usedNames.containsKey(corename);
    if (_containsKey) {
      final int number = this.usedNames.get(corename).intValue();
      Integer _integer = new Integer((number + 1));
      this.usedNames.put(corename, _integer);
      return ((corename + "_") + Integer.valueOf(number));
    } else {
      Integer _integer_1 = new Integer(1);
      this.usedNames.put(corename, _integer_1);
      return corename;
    }
  }
  
  public void findListOfProperties(final YProperty property) {
    property.getType();
  }
}
