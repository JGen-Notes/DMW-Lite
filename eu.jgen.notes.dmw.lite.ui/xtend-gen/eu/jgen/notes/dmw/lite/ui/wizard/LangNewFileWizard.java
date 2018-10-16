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
 */
package eu.jgen.notes.dmw.lite.ui.wizard;

import com.google.inject.Inject;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWizard;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.ui.PluginImageHelper;

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
@SuppressWarnings("all")
public class LangNewFileWizard extends Wizard implements INewWizard {
  private /* LangNewFileWizardPage */Object page;
  
  private ISelection selection;
  
  @Inject
  private PluginImageHelper imageHelper;
  
  /**
   * Constructor for JGModelFragmentNewWizard.
   */
  public LangNewFileWizard() {
    super();
    this.setNeedsProgressMonitor(true);
  }
  
  /**
   * Adding the page to the wizard.
   */
  @Override
  public void addPages() {
    throw new Error("Unresolved compilation problems:"
      + "\nLangNewFileWizardPage cannot be resolved."
      + "\nThe field LangNewFileWizard.page refers to the missing type LangNewFileWizardPage"
      + "\nThe field LangNewFileWizard.page refers to the missing type LangNewFileWizardPage"
      + "\nThe field LangNewFileWizard.page refers to the missing type LangNewFileWizardPage"
      + "\ninit cannot be resolved");
  }
  
  /**
   * This method is called when 'Finish' button is pressed in
   * the wizard. We will create an operation and run it
   * using wizard as execution context.
   */
  @Override
  public boolean performFinish() {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method or field elementName is undefined for the type Object"
      + "\nThe field LangNewFileWizard.page refers to the missing type LangNewFileWizardPage"
      + "\nThere is no context to infer the closure\'s argument types from. Consider typing the arguments or put the closures into a typed context."
      + "\nThe field LangNewFileWizard.page refers to the missing type LangNewFileWizardPage"
      + "\nThe field LangNewFileWizard.page refers to the missing type LangNewFileWizardPage"
      + "\nThe field LangNewFileWizard.page refers to the missing type LangNewFileWizardPage"
      + "\nThe field LangNewFileWizard.page refers to the missing type LangNewFileWizardPage"
      + "\nThe field LangNewFileWizard.page refers to the missing type LangNewFileWizardPage"
      + "\nThe field LangNewFileWizard.page refers to the missing type LangNewFileWizardPage"
      + "\nThe field LangNewFileWizard.page refers to the missing type LangNewFileWizardPage"
      + "\njavaProject cannot be resolved"
      + "\ngetPackageFragments cannot be resolved"
      + "\nfilter cannot be resolved"
      + "\n== cannot be resolved"
      + "\npackageText cannot be resolved"
      + "\nfindFirst cannot be resolved"
      + "\npath cannot be resolved"
      + "\ntypeName cannot be resolved"
      + "\npackageText cannot be resolved"
      + "\nmodifiers cannot be resolved");
  }
  
  /**
   * The worker method. It will find the container, create the
   * file if missing or just replace its contents, and open
   * the editor on the newly created file.
   */
  private void doFinish(final IPath path, final String typeName, final String packageName, final int modifier, final IProgressMonitor monitor) throws CoreException {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method or field LangNewFileWizardPage is undefined"
      + "\nThe method or field LangNewFileWizardPage is undefined"
      + "\nThe method or field LangNewFileWizardPage is undefined"
      + "\nThe method or field LangNewFileWizardPage is undefined"
      + "\nThe method or field LangNewFileWizardPage is undefined"
      + "\nTEMPLATE_WIDGET cannot be resolved"
      + "\nTEMPLATE_ENTITY cannot be resolved"
      + "\nTEMPLATE_USER_EXITS cannot be resolved"
      + "\nTEMPLATE_COMMANDS cannot be resolved"
      + "\nTEMPLATE_NONE cannot be resolved");
  }
  
  public ByteArrayInputStream openContentStreamNone(final String packageName, final String name) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package ");
    _builder.append(packageName);
    _builder.append(";\t");
    _builder.newLineIfNotEmpty();
    String contents = _builder.toString();
    byte[] _bytes = contents.getBytes();
    return new ByteArrayInputStream(_bytes);
  }
  
  public ByteArrayInputStream openContentStreamCommands(final String packageName, final String name) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package ");
    _builder.append(packageName);
    _builder.append(";\t");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("/**");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* command definitions here ....");
    _builder.newLine();
    _builder.append("*/");
    _builder.newLine();
    _builder.append("class Commands {\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("var display : Command;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("var exit : Command;");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    String contents = _builder.toString();
    byte[] _bytes = contents.getBytes();
    return new ByteArrayInputStream(_bytes);
  }
  
  public ByteArrayInputStream openContentStreamExits(final String packageName, final String name) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package ");
    _builder.append(packageName);
    _builder.append(";\t");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("/**");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*  exit definitions here ...");
    _builder.newLine();
    _builder.append("*/");
    _builder.newLine();
    _builder.append("class ExitStates {\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public var processStarted : ExitState ");
    _builder.newLine();
    _builder.append("\t   ");
    _builder.append("@action (normal)  @msgtype(none) @message(\"Process started.\");");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public var processCompleted : ExitState  ");
    _builder.newLine();
    _builder.append("\t   ");
    _builder.append("@action (normal)  @msgtype(none) @message(\"Process completed.\");");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public var entityNF : ExitState  ");
    _builder.newLine();
    _builder.append("\t   ");
    _builder.append("@action (normal)  @msgtype(none) @message(\"Entity type not found.\");");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public var entityAE : ExitState  ");
    _builder.newLine();
    _builder.append("\t   ");
    _builder.append("@action (normal)  @msgtype(none) @message(\"Entity type already exists.\");");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    String contents = _builder.toString();
    byte[] _bytes = contents.getBytes();
    return new ByteArrayInputStream(_bytes);
  }
  
  public ByteArrayInputStream openContentStreamEntity(final String packageName, final String name) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package ");
    _builder.append(packageName);
    _builder.append("\t;");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.newLine();
    _builder.append("/**");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*  description here ...");
    _builder.newLine();
    _builder.append("*/");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("@entity ");
    _builder.append(name, " ");
    _builder.append("  {");
    _builder.newLineIfNotEmpty();
    _builder.append("    \t");
    _builder.append("@attr number : Int  @length(9);");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("}\t\t\t\t");
    _builder.newLine();
    String contents = _builder.toString();
    byte[] _bytes = contents.getBytes();
    return new ByteArrayInputStream(_bytes);
  }
  
  /**
   * We will initialize file contents with a sample text.
   */
  private InputStream openContentStreamAction(final String packageName, final String name) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package ");
    _builder.append(packageName);
    _builder.append("\t;");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("/**");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* description here ...");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("class ");
    _builder.append(name);
    _builder.append(" : Widget {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("// define inner classes for structures here ...");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("// define properties here ...");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public func start() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("// your logic insert here....");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}\t\t\t\t");
    _builder.newLine();
    String contents = _builder.toString();
    byte[] _bytes = contents.getBytes();
    return new ByteArrayInputStream(_bytes);
  }
  
  private void throwCoreException(final String message) throws CoreException {
    IStatus status = new Status(IStatus.ERROR, "eu.jgen.jgmodel.exchange", IStatus.OK, message, null);
    throw new CoreException(status);
  }
  
  /**
   * We will accept the selection in the workbench to see if
   * we can initialize from it.
   * @see IWorkbenchWizard#init(IWorkbench, IStructuredSelection)
   */
  @Override
  public void init(final IWorkbench workbench, final IStructuredSelection selection) {
    this.selection = selection;
  }
}
