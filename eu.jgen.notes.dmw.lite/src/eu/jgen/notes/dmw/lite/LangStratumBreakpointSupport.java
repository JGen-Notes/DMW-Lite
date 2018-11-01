package eu.jgen.notes.dmw.lite;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.debug.IStratumBreakpointSupport;
import org.eclipse.xtext.nodemodel.ICompositeNode;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.parser.IParseResult;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.util.ITextRegionWithLineInformation;

import eu.jgen.notes.dmw.lite.lang.YCreateStatement;
import eu.jgen.notes.dmw.lite.lang.YReadStatement;

@SuppressWarnings("restriction")
public class LangStratumBreakpointSupport implements IStratumBreakpointSupport  {

	@Override
	public boolean isValidLineForBreakPoint(XtextResource resource, int line) {
		 IParseResult parseResult = resource.getParseResult();
	        if (parseResult == null)
	            return false;
	        ICompositeNode node = parseResult.getRootNode();
	        return isValidLineForBreakpoint(node, line);
	}

	private boolean isValidLineForBreakpoint(ICompositeNode node, int line) {
		 
		for (INode n : node.getChildren()) {
            ITextRegionWithLineInformation textRegion = n.getTextRegionWithLineInformation();
            if (textRegion.getLineNumber()<= line && textRegion.getEndLineNumber() >= line) {
                EObject eObject = n.getSemanticElement();
                if (eObject instanceof YCreateStatement) {
                    return true;
                }
                if (n instanceof ICompositeNode && isValidLineForBreakpoint((ICompositeNode) n, line)) {
                    return true;
                }
            }
            if (textRegion.getLineNumber() > line) {
                return false;
            }
        }
        return false;
	}

}
