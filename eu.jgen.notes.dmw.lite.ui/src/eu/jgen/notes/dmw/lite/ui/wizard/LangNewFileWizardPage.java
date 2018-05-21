
/**
 * [The "BSD license"]
 * Copyright (c) 2016, JGen Notes
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, 
 * are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this list of conditions 
 *    and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions 
 *    and the following disclaimer in the documentation and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, 
 * INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE 
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, 
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS 
 * OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, 
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE 
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * 
 */
package eu.jgen.notes.dmw.lite.ui.wizard;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.internal.ui.wizards.dialogfields.DialogField;
import org.eclipse.jdt.internal.ui.wizards.dialogfields.LayoutUtil;
import org.eclipse.jdt.internal.ui.wizards.dialogfields.SelectionButtonDialogFieldGroup;
import org.eclipse.jdt.ui.wizards.NewTypeWizardPage;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.xtext.ui.PluginImageHelper;

@SuppressWarnings("restriction")
public class LangNewFileWizardPage extends NewTypeWizardPage {
	
	static public int TEMPLATE_WIDGET = 0;	
	static public int TEMPLATE_ENTITY = 1;	
	static public int TEMPLATE_USER_EXITS = 2;	
	static public int TEMPLATE_COMMANDS = 3;	
	static public int TEMPLATE_NONE = 4;
    
    private SelectionButtonDialogFieldGroup fAccMdfButtons;
	private SelectionButtonDialogFieldGroup fOtherMdfButtons;
	private int fTypeKind;  

    public LangNewFileWizardPage(PluginImageHelper imageHelper) {
        super(true, "Add New File");
        setTitle("Add a New Widget Fragment");
		setDescription("Creates a file containing a fragment of the widget application");
		setImageDescriptor(imageHelper.getImageDescriptor("newfile_wiz.png"));		
    }
 
    public void init(IStructuredSelection selection) {    		
        IJavaElement jelem= getInitialJavaElement(selection);
        initContainerPage(jelem);
        initTypePage(jelem);
        doStatusUpdate();
    }

    private void doStatusUpdate() {
        // define the components for which a status is desired
        IStatus[] status= new IStatus[] {
            fContainerStatus,
            isEnclosingTypeSelected() ? fEnclosingTypeStatus : fPackageStatus,
            fTypeNameStatus,
        };
        updateStatus(status);
    }

    protected void handleFieldChanged(String fieldName) {
        super.handleFieldChanged(fieldName);
        doStatusUpdate();
    }
 
    public void createControl(Composite parent) {	
        initializeDialogUnits(parent);
        Composite composite= new Composite(parent, SWT.NONE);
        int nColumns= 7;
        GridLayout layout= new GridLayout();
        layout.numColumns= nColumns;
        composite.setLayout(layout);        
        String[] buttonModifiersNames= new String[] {
    			"Widget",
    			"Entity",
    			"Exit States",
    			"Commands",
    			"None"
    		};
    		fAccMdfButtons= new SelectionButtonDialogFieldGroup(SWT.RADIO, buttonModifiersNames, 5);
    		fAccMdfButtons.setLabelText("Modifier:");
    		fAccMdfButtons.setSelection(0, true);

        // Create the standard input fields
        createContainerControls(composite, nColumns);
        createPackageControls(composite, nColumns);
        createSeparator(composite, nColumns);
        createTypeNameControls(composite, nColumns);
        createSeparator(composite, nColumns);
        createTemplateControls(composite, 7);
        setControl(composite);
    }

    
	protected void createTemplateControls(Composite composite, int nColumns) {
		LayoutUtil.setHorizontalSpan(fAccMdfButtons.getLabelControl(composite), 1);
		Control control= fAccMdfButtons.getSelectionButtonsGroup(composite);
		GridData gd= new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gd.horizontalSpan= nColumns - 2;
		control.setLayoutData(gd);
		DialogField.createEmptySpace(composite);
		if (fTypeKind == CLASS_TYPE) {
			DialogField.createEmptySpace(composite);
			control= fOtherMdfButtons.getSelectionButtonsGroup(composite);
			gd= new GridData(GridData.HORIZONTAL_ALIGN_FILL);
			gd.horizontalSpan= nColumns - 2;
			control.setLayoutData(gd);
			DialogField.createEmptySpace(composite);
		}
	}
	
	@Override
	public int getModifiers() {		
		if(fAccMdfButtons.isSelected(TEMPLATE_WIDGET)) {
			return TEMPLATE_WIDGET;
		} else if(fAccMdfButtons.isSelected(TEMPLATE_ENTITY)) {
			return TEMPLATE_ENTITY;
		} else if(fAccMdfButtons.isSelected(TEMPLATE_USER_EXITS)) {
			return TEMPLATE_USER_EXITS;
		} else if(fAccMdfButtons.isSelected(TEMPLATE_COMMANDS)) {
			return TEMPLATE_COMMANDS;
		} else if(fAccMdfButtons.isSelected(TEMPLATE_NONE)) {
			return TEMPLATE_NONE;
		}
		return TEMPLATE_NONE;
	}

}
