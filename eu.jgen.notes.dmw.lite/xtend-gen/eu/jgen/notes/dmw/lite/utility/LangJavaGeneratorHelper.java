package eu.jgen.notes.dmw.lite.utility;

import com.google.inject.Inject;
import eu.jgen.notes.dmw.lite.lang.YClass;
import eu.jgen.notes.dmw.lite.lang.YFunction;
import eu.jgen.notes.dmw.lite.lang.YProperty;
import eu.jgen.notes.dmw.lite.lang.YWidget;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.common.types.JvmIdentifiableElement;
import org.eclipse.xtext.documentation.IEObjectDocumentationProvider;
import org.eclipse.xtext.xbase.compiler.DocumentationAdapter;

@SuppressWarnings("all")
public class LangJavaGeneratorHelper {
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
          return "XInt";
        case "Short":
          return "XShort";
        case "Long":
          return "XLong";
        case "Decimal":
          return "XDouble";
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
}
