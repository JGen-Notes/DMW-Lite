package eu.jgen.notes.dmw.lite.utility;

import com.google.inject.Inject;
import eu.jgen.notes.dmw.lite.lang.YClass;
import eu.jgen.notes.dmw.lite.lang.YProperty;
import eu.jgen.notes.dmw.lite.lang.YWidget;
import org.eclipse.emf.common.notify.Adapter;
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
}
