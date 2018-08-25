package eu.jgen.notes.dmw.lite.utility;

import com.google.common.base.Objects;
import com.google.inject.Inject;
import eu.jgen.notes.dmw.lite.lang.YAnnot;
import eu.jgen.notes.dmw.lite.lang.YAnnotAttr;
import eu.jgen.notes.dmw.lite.lang.YAnnotDefault;
import eu.jgen.notes.dmw.lite.lang.YClass;
import eu.jgen.notes.dmw.lite.lang.YFunction;
import eu.jgen.notes.dmw.lite.lang.YMember;
import eu.jgen.notes.dmw.lite.lang.YProperty;
import eu.jgen.notes.dmw.lite.lang.YWidget;
import java.util.ArrayList;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.common.types.JvmIdentifiableElement;
import org.eclipse.xtext.documentation.IEObjectDocumentationProvider;
import org.eclipse.xtext.xbase.compiler.DocumentationAdapter;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;

@SuppressWarnings("all")
public class LangJavaGeneratorHelper {
  private final String SYSTEM_DEFAULT_STRING = "\"\"";
  
  private final String SYSTEM_DEFAULT_INT = "0";
  
  private final String SYSTEM_DEFAULT_SHORT = "0";
  
  private final String SYSTEM_DEFAULT_DOUBLE = "0.0";
  
  private final String SYSTEM_DEFAULT_LONG = "0";
  
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
        return this.wrapAsJavaComment(adapter.getDocumentation());
      }
    }
    final String documentation = this.documentationProvider.getDocumentation(source);
    return this.wrapAsJavaComment(documentation);
  }
  
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
    final EObject a = property.getType().eContainer();
    if ((a instanceof YWidget)) {
      return ((YWidget) a).getName();
    } else {
      if ((a instanceof YClass)) {
        EObject _eContainer = ((YClass) a).eContainer();
        String _name = ((YWidget) _eContainer).getName();
        String _plus = (_name + ".");
        String _name_1 = ((YClass) a).getName();
        return (_plus + _name_1);
      } else {
        return "<do not know what to do yet>";
      }
    }
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
        case "Decimal":
          return "double";
        case "String":
          return "String";
        case "Boolean":
          return "boolean";
        default:
          return typeName;
      }
    } else {
      return typeName;
    }
  }
  
  public YClass whatFuntionType(final EObject eobject) {
    YClass _xifexpression = null;
    EObject _eContainer = eobject.eContainer();
    if ((_eContainer instanceof YFunction)) {
      EObject _eContainer_1 = eobject.eContainer();
      return ((YFunction) _eContainer_1).getType();
    } else {
      _xifexpression = this.whatFuntionType(eobject.eContainer());
    }
    return _xifexpression;
  }
  
  public String getPropertyDefaultValue(final YProperty property) {
    String _xifexpression = null;
    YAnnotAttr _attr = property.getAttr();
    boolean _tripleEquals = (_attr == null);
    if (_tripleEquals) {
      _xifexpression = this.getSystemDefault(this.translateTypeName(property.getType().getName()));
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
        default:
          return "?";
      }
    } else {
      return "?";
    }
  }
  
  public String findDefaultFromAnnot(final YProperty property) {
    EList<YAnnot> _annots = property.getAttr().getAnnots();
    for (final YAnnot annotation : _annots) {
      YAnnot _type = annotation.getType();
      if ((_type instanceof YAnnotDefault)) {
        YAnnot _type_1 = annotation.getType();
        final YAnnotDefault annotDefault = ((YAnnotDefault) _type_1);
        String _text = annotDefault.getText();
        boolean _tripleNotEquals = (_text != null);
        if (_tripleNotEquals) {
          String _text_1 = annotDefault.getText();
          String _plus = ("\"" + _text_1);
          return (_plus + "\"");
        } else {
          return String.valueOf(annotDefault.getNumber());
        }
      }
    }
    return this.getSystemDefault(this.translateTypeName(property.getType().getName()));
  }
  
  public ArrayList<YProperty> listArrayProperties(final YClass eClass) {
    final ArrayList<YProperty> array = CollectionLiterals.<YProperty>newArrayList();
    EList<YMember> _members = eClass.getMembers();
    for (final YMember member : _members) {
      if ((member instanceof YProperty)) {
        final YProperty property = ((YProperty) member);
        String _name = property.getType().getName();
        boolean _equals = Objects.equal(_name, "Array");
        if (_equals) {
          array.add(property);
        }
      }
    }
    return array;
  }
}
