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
package eu.jgen.notes.dmw.lite.ui.wizard

import com.google.inject.Inject
import java.io.ByteArrayInputStream
import java.io.IOException
import java.io.InputStream
import java.lang.reflect.InvocationTargetException
import org.eclipse.core.resources.IContainer
import org.eclipse.core.resources.IFile
import org.eclipse.core.resources.IResource
import org.eclipse.core.resources.IWorkspaceRoot
import org.eclipse.core.resources.ResourcesPlugin
import org.eclipse.core.runtime.CoreException
import org.eclipse.core.runtime.IPath
import org.eclipse.core.runtime.IProgressMonitor
import org.eclipse.core.runtime.IStatus
import org.eclipse.core.runtime.Path
import org.eclipse.core.runtime.Status
import org.eclipse.jface.dialogs.MessageDialog
import org.eclipse.jface.operation.IRunnableWithProgress
import org.eclipse.jface.viewers.ISelection
import org.eclipse.jface.viewers.IStructuredSelection
import org.eclipse.jface.wizard.Wizard
import org.eclipse.ui.INewWizard
import org.eclipse.ui.IWorkbench
import org.eclipse.ui.IWorkbenchPage
import org.eclipse.ui.IWorkbenchWizard
import org.eclipse.ui.PartInitException
import org.eclipse.ui.PlatformUI
import org.eclipse.ui.ide.IDE
import org.eclipse.xtext.ui.PluginImageHelper

/** 
 * This is a sample new wizard. Its role is to create a new file 
 * resource in the provided container. If the container resource
 * (a folder or a project) is selected in the workspace 
 * when the wizard is opened, it will accept it as the target
 * container. The wizard creates one file with the extension
 * "jg". If a sample multi-page editor (also available
 * as a template) is registered for the same extension, it will
 * be able to open it.
 */
class LangNewFileWizard extends Wizard implements INewWizard {
	LangNewFileWizardPage page
	ISelection selection
	
	@Inject
	private PluginImageHelper imageHelper;

	/** 
	 * Constructor for JGModelFragmentNewWizard.
	 */
	new() {
		super()
		setNeedsProgressMonitor(true)
	}

	/** 
	 * Adding the page to the wizard.
	 */
	override void addPages() {
		page = new LangNewFileWizardPage(imageHelper)
		page.init(selection as IStructuredSelection)
		addPage(page)
	}

	/** 
	 * This method is called when 'Finish' button is pressed in
	 * the wizard. We will create an operation and run it
	 * using wizard as execution context.
	 */
	override boolean performFinish() {
		val javaProject = page.javaProject
		val path = javaProject.getPackageFragments.filter [ element |
			element.elementName == page.packageText
		].findFirst[true].path
		
		var IRunnableWithProgress op = [IProgressMonitor monitor|
			try {
				doFinish(path, page.typeName, page.packageText, page.modifiers, monitor)
			} catch (CoreException e) {
				throw new InvocationTargetException(e)
			} finally {
				monitor.done()
			}
		]
		try {
			getContainer().run(true, false, op)
		} catch (InterruptedException e) {
			return false
		} catch (InvocationTargetException e) {
			var Throwable realException = e.getTargetException()
			MessageDialog.openError(getShell(), "Error", realException.getMessage())
			return false
		}

		return true
	}

	/** 
	 * The worker method. It will find the container, create the
	 * file if missing or just replace its contents, and open
	 * the editor on the newly created file.
	 */
	def private void doFinish(IPath path, String typeName, String packageName, int modifier, IProgressMonitor monitor) throws CoreException {
		val String fileName = typeName.toFirstUpper + ".dmw"
		monitor.beginTask('''Creating «fileName»''', 2)
		var IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot()
		var IResource resource = root.findMember(path)
		if (!resource.exists() || !(resource instanceof IContainer)) {
			throwCoreException('''Container "«path»" does not exist.''')
		}
		var IContainer container = (resource as IContainer)
		val IFile file = container.getFile(new Path(fileName))
		try {
			var InputStream stream = openContentStreamAction(packageName, typeName)			
			switch modifier {
				case LangNewFileWizardPage.TEMPLATE_WIDGET:
				   stream = openContentStreamAction(packageName, typeName)
				case LangNewFileWizardPage.TEMPLATE_ENTITY:
				   stream = openContentStreamEntity(packageName, typeName)
				case LangNewFileWizardPage.TEMPLATE_USER_EXITS:
				   stream = openContentStreamExits(packageName, typeName)
				 case LangNewFileWizardPage.TEMPLATE_COMMANDS:
				   stream = openContentStreamCommands(packageName, typeName)
				case LangNewFileWizardPage.TEMPLATE_NONE:
				   stream = openContentStreamNone(packageName, typeName)
			}
			if (file.exists()) {
				file.setContents(stream, true, true, monitor)
			} else {
				file.create(stream, true, monitor)
			}
			stream.close()
		} catch (IOException e) {
		}
		monitor.worked(1)
		monitor.setTaskName("Opening file for editing...")
		getShell().getDisplay().asyncExec([
			var IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
			try {
				IDE.openEditor(page, file, true)
			} catch (PartInitException e) {
			}
		])
		monitor.worked(
			1)
	}
	
	def openContentStreamNone(String packageName, String name) {
		var contents = '''
			package «packageName»;	
		'''
		return new ByteArrayInputStream(contents.getBytes())
	}
	
	def openContentStreamCommands(String packageName, String name) {
				var contents = '''
			package «packageName»;	
			
			/**
			 * command definitions here ....
			*/
			class Commands {	
				var display : Command;
				var exit : Command;
			}
		'''
		return new ByteArrayInputStream(contents.getBytes())

	}
	
		def openContentStreamExits(String packageName, String name) {
				var contents = '''
			package «packageName»;	
			
			/**
			 *  exit definitions here ...
			*/
			class ExitStates {	
				public var processStarted : ExitState 
				   @action (normal)  @msgtype(none) @message("Process started.");
				public var processCompleted : ExitState  
				   @action (normal)  @msgtype(none) @message("Process completed.");
				public var entityNF : ExitState  
				   @action (normal)  @msgtype(none) @message("Entity type not found.");
				public var entityAE : ExitState  
				   @action (normal)  @msgtype(none) @message("Entity type already exists.");
			}
		'''
		return new ByteArrayInputStream(contents.getBytes())

	}
	
	
	def openContentStreamEntity(String packageName, String name) {
		var contents = '''
			package «packageName»	;
					
			/**
			 *  description here ...
			*/
			 @entity «name»  {
			    	@attr number : Int  @length(9);
			  }				
		'''
		return new ByteArrayInputStream(contents.getBytes())
	}

	/** 
	 * We will initialize file contents with a sample text.
	 */
	def private InputStream openContentStreamAction(String packageName, String name) {
		var contents = '''
			package «packageName»	;
			
			/**
			 * description here ...
			 */
			class «name» : Widget {
				
				// define inner classes for structures here ...
				
				// define properties here ...
				
				public func start() {
					
					// your logic insert here....
					
					}
			}				
		'''
		return new ByteArrayInputStream(contents.getBytes())
	}

	def private void throwCoreException(String message) throws CoreException {
		var IStatus status = new Status(IStatus.ERROR, "eu.jgen.jgmodel.exchange", IStatus.OK, message, null)
		throw new CoreException(status)
	}

	/** 
	 * We will accept the selection in the workbench to see if
	 * we can initialize from it.
	 * @see IWorkbenchWizard#init(IWorkbench, IStructuredSelection)
	 */
	override void init(IWorkbench workbench, IStructuredSelection selection) {
		this.selection = selection
	}
}
