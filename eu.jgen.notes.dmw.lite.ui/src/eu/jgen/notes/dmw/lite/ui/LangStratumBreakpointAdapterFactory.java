package eu.jgen.notes.dmw.lite.ui;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.builder.smap.StratumBreakpointAdapterFactory;
import org.eclipse.xtext.resource.XtextResource;

import eu.jgen.notes.dmw.lite.lang.YClass;

@SuppressWarnings("restriction")
public class LangStratumBreakpointAdapterFactory extends StratumBreakpointAdapterFactory  {
	
	@Override
    protected String getClassNamePattern(XtextResource state) {
//		String name = super.getClassNamePattern(state);
   //     String name = state.getURI().trimFileExtension().lastSegment()+"*";
		String name = "CreateAllDataTypes*";
    	System.out.println("===> " + name);
        return name;
        
//        TreeIterator<Object> contents = EcoreUtil.getAllContents(state, false);
//		StringBuilder sb = new StringBuilder();
//		while (contents.hasNext()) {
//			Object next = contents.next();
//			if (next instanceof YClass) {
//				YClass type = (YClass) next;
//				sb.append(type.getName()).append("*");
//				sb.append(",");
//			}
//		}
//		if (sb.length() == 0)
//			return null;
//		else {
//			String name = sb.substring(0, sb.length() - 1);
//			System.out.println("===> " + name);
//			return name;
//		}

    }
    
    @SuppressWarnings("unchecked")
	public Object getAdapter(Object adaptableObject, @SuppressWarnings("rawtypes") Class adapterType) {
        if (adaptableObject instanceof LangEditor) {
            return this;
        }
        return null;
    }

}
