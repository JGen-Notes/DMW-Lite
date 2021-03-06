package eu.jgen.notes.dmw.lite.ui;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.ui.IEditorInput;
import org.eclipse.xtext.ui.editor.XtextEditor;
import org.eclipse.xtext.xbase.ui.editor.XbaseEditorInputRedirector;
import com.google.inject.Inject;

@SuppressWarnings("restriction")
public class LangEditor extends XtextEditor {
	
	@Inject
    private XbaseEditorInputRedirector editorInputRedirector;

	
	@Override
    protected void doSetInput(IEditorInput input) throws CoreException {
        try {
            IEditorInput inputToUse = editorInputRedirector.findOriginalSource(input);
            super.doSetInput(inputToUse);
            return;
        } catch (CoreException e) {
            // ignore
        }
        super.doSetInput(input);
    }
}
