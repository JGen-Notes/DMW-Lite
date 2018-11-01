package eu.jgen.notes.dmw.lite.generator;

import com.google.common.base.Objects;
import com.google.inject.Inject;
import eu.jgen.notes.dmw.lite.generator.LangOutputProvider;
import eu.jgen.notes.dmw.lite.lang.YClass;
import eu.jgen.notes.dmw.lite.lang.YMember;
import eu.jgen.notes.dmw.lite.lang.YProperty;
import eu.jgen.notes.dmw.lite.lang.YWidget;
import eu.jgen.notes.dmw.lite.utility.LangGeneratorHelperForJava;
import eu.jgen.notes.dmw.lite.utility.LangUtil;
import java.util.List;
import java.util.function.Consumer;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.generator.IFileSystemAccess;
import org.eclipse.xtext.generator.IGenerator;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IteratorExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;

@SuppressWarnings("all")
public class LangGlobalCommandsGenerator implements IGenerator {
  @Inject
  @Extension
  private LangUtil _langUtil;
  
  @Inject
  @Extension
  private LangGeneratorHelperForJava _langGeneratorHelperForJava;
  
  private List<String> imports = CollectionLiterals.<String>newArrayList();
  
  @Override
  public void doGenerate(final Resource input, final IFileSystemAccess fsa) {
    final Function1<EObject, Boolean> _function = (EObject element) -> {
      return Boolean.valueOf((element instanceof YWidget));
    };
    final Procedure1<EObject> _function_1 = (EObject element) -> {
      final YWidget widget = ((YWidget) element);
      this.generateWidget(fsa, widget);
    };
    IteratorExtensions.<EObject>forEach(IteratorExtensions.<EObject>filter(input.getAllContents(), _function), _function_1);
  }
  
  protected void generateWidget(final IFileSystemAccess fsa, final YWidget widget) {
    this.imports.clear();
    final Consumer<YClass> _function = (YClass clazz) -> {
      if (((clazz.getSuperclass() != null) && Objects.equal(clazz.getSuperclass().getName(), "GlobalCommands"))) {
        this.imports.add("eu.jgen.notes.dmw.lite.runtimes.GlobalCommands");
        this.imports.add("eu.jgen.notes.dmw.lite.runtimes.Command");
        StringConcatenation _builder = new StringConcatenation();
        String _documentation = this._langGeneratorHelperForJava.getDocumentation(clazz);
        _builder.append(_documentation);
        _builder.append("  ");
        _builder.newLineIfNotEmpty();
        _builder.append("public class ");
        String _name = clazz.getName();
        _builder.append(_name);
        _builder.append(" extends GlobalCommands {");
        _builder.newLineIfNotEmpty();
        _builder.append("   ");
        String _generateCommands = this.generateCommands(clazz);
        _builder.append(_generateCommands, "   ");
        _builder.newLineIfNotEmpty();
        _builder.append("}");
        _builder.newLine();
        final String body = _builder.toString();
        String _fileSystemPath = this._langUtil.getFileSystemPath(widget.getName());
        String _plus = (_fileSystemPath + "/");
        String _name_1 = clazz.getName();
        String _plus_1 = (_plus + _name_1);
        String _plus_2 = (_plus_1 + ".java");
        StringConcatenation _builder_1 = new StringConcatenation();
        _builder_1.append("package ");
        String _name_2 = widget.getName();
        _builder_1.append(_name_2);
        _builder_1.append(";");
        _builder_1.newLineIfNotEmpty();
        _builder_1.newLine();
        {
          for(final String imp : this.imports) {
            _builder_1.append("import ");
            _builder_1.append(imp);
            _builder_1.append(";");
            _builder_1.newLineIfNotEmpty();
          }
        }
        _builder_1.newLine();
        _builder_1.append(body);
        _builder_1.append("\t\t\t\t\t\t");
        _builder_1.newLineIfNotEmpty();
        fsa.generateFile(_plus_2, 
          LangOutputProvider.DEFAULT, _builder_1);
      }
    };
    widget.getClasses().forEach(_function);
  }
  
  protected String generateCommands(final YClass clazz) {
    StringConcatenation _builder = new StringConcatenation();
    {
      EList<YMember> _members = clazz.getMembers();
      for(final YMember member : _members) {
        {
          if ((member instanceof YProperty)) {
            String _generatePropertyForCommand = this.generatePropertyForCommand(((YProperty) member));
            _builder.append(_generatePropertyForCommand);
            _builder.newLineIfNotEmpty();
          }
        }
      }
    }
    return _builder.toString();
  }
  
  protected String generatePropertyForCommand(final YProperty property) {
    StringConcatenation _builder = new StringConcatenation();
    String _documentation = this._langGeneratorHelperForJava.getDocumentation(property);
    _builder.append(_documentation);
    _builder.newLineIfNotEmpty();
    _builder.append("public Command ");
    String _name = property.getName();
    _builder.append(_name);
    _builder.append(";");
    _builder.newLineIfNotEmpty();
    return _builder.toString();
  }
}
