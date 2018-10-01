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

import com.google.common.base.Objects;
import com.google.inject.Inject;
import eu.jgen.notes.dmw.lite.ui.wizard.LangNewFileWizardPage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWizard;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.ui.PluginImageHelper;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.StringExtensions;

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
  private LangNewFileWizardPage page;
  
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
    LangNewFileWizardPage _langNewFileWizardPage = new LangNewFileWizardPage(this.imageHelper);
    this.page = _langNewFileWizardPage;
    this.page.init(((IStructuredSelection) this.selection));
    this.addPage(this.page);
  }
  
  /**
   * This method is called when 'Finish' button is pressed in
   * the wizard. We will create an operation and run it
   * using wizard as execution context.
   */
  @Override
  public boolean performFinish() {
    try {
      final IJavaProject javaProject = this.page.getJavaProject();
      final Function1<IPackageFragment, Boolean> _function = (IPackageFragment element) -> {
        String _elementName = element.getElementName();
        String _packageText = this.page.getPackageText();
        return Boolean.valueOf(Objects.equal(_elementName, _packageText));
      };
      final Function1<IPackageFragment, Boolean> _function_1 = (IPackageFragment it) -> {
        return Boolean.valueOf(true);
      };
      final IPath path = IterableExtensions.<IPackageFragment>findFirst(IterableExtensions.<IPackageFragment>filter(((Iterable<IPackageFragment>)Conversions.doWrapArray(javaProject.getPackageFragments())), _function), _function_1).getPath();
      final IRunnableWithProgress _function_2 = (IProgressMonitor monitor) -> {
        try {
          this.doFinish(path, this.page.getTypeName(), this.page.getPackageText(), this.page.getModifiers(), monitor);
        } catch (final Throwable _t) {
          if (_t instanceof CoreException) {
            final CoreException e = (CoreException)_t;
            throw new InvocationTargetException(e);
          } else {
            throw Exceptions.sneakyThrow(_t);
          }
        } finally {
          monitor.done();
        }
      };
      IRunnableWithProgress op = _function_2;
      try {
        this.getContainer().run(true, false, op);
      } catch (final Throwable _t) {
        if (_t instanceof InterruptedException) {
          final InterruptedException e = (InterruptedException)_t;
          return false;
        } else if (_t instanceof InvocationTargetException) {
          final InvocationTargetException e_1 = (InvocationTargetException)_t;
          Throwable realException = e_1.getTargetException();
          MessageDialog.openError(this.getShell(), "Error", realException.getMessage());
          return false;
        } else {
          throw Exceptions.sneakyThrow(_t);
        }
      }
      return true;
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  /**
   * The worker method. It will find the container, create the
   * file if missing or just replace its contents, and open
   * the editor on the newly created file.
   */
  private void doFinish(final IPath path, final String typeName, final String packageName, final int modifier, final IProgressMonitor monitor) throws CoreException {
    String _firstUpper = StringExtensions.toFirstUpper(typeName);
    final String fileName = (_firstUpper + ".dmw");
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("Creating ");
    _builder.append(fileName);
    monitor.beginTask(_builder.toString(), 2);
    IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
    IResource resource = root.findMember(path);
    if (((!resource.exists()) || (!(resource instanceof IContainer)))) {
      StringConcatenation _builder_1 = new StringConcatenation();
      _builder_1.append("Container \"");
      _builder_1.append(path);
      _builder_1.append("\" does not exist.");
      this.throwCoreException(_builder_1.toString());
    }
    IContainer container = ((IContainer) resource);
    Path _path = new Path(fileName);
    final IFile file = container.getFile(_path);
    try {
      InputStream stream = this.openContentStreamAction(packageName, typeName);
      boolean _matched = false;
      if (Objects.equal(modifier, LangNewFileWizardPage.TEMPLATE_WIDGET)) {
        _matched=true;
        stream = this.openContentStreamAction(packageName, typeName);
      }
      if (!_matched) {
        if (Objects.equal(modifier, LangNewFileWizardPage.TEMPLATE_ENTITY)) {
          _matched=true;
          stream = this.openContentStreamEntity(packageName, typeName);
        }
      }
      if (!_matched) {
        if (Objects.equal(modifier, LangNewFileWizardPage.TEMPLATE_USER_EXITS)) {
          _matched=true;
          stream = this.openContentStreamExits(packageName, typeName);
        }
      }
      if (!_matched) {
        if (Objects.equal(modifier, LangNewFileWizardPage.TEMPLATE_COMMANDS)) {
          _matched=true;
          stream = this.openContentStreamCommands(packageName, typeName);
        }
      }
      if (!_matched) {
        if (Objects.equal(modifier, LangNewFileWizardPage.TEMPLATE_NONE)) {
          _matched=true;
          stream = this.openContentStreamNone(packageName, typeName);
        }
      }
      boolean _exists = file.exists();
      if (_exists) {
        file.setContents(stream, true, true, monitor);
      } else {
        file.create(stream, true, monitor);
      }
      stream.close();
    } catch (final Throwable _t) {
      if (_t instanceof IOException) {
        final IOException e = (IOException)_t;
      } else {
        throw Exceptions.sneakyThrow(_t);
      }
    }
    monitor.worked(1);
    monitor.setTaskName("Opening file for editing...");
    final Runnable _function = () -> {
      IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
      try {
        IDE.openEditor(page, file, true);
      } catch (final Throwable _t_1) {
        if (_t_1 instanceof PartInitException) {
          final PartInitException e_1 = (PartInitException)_t_1;
        } else {
          throw Exceptions.sneakyThrow(_t_1);
        }
      }
    };
    this.getShell().getDisplay().asyncExec(_function);
    monitor.worked(
      1);
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
