package eu.jgen.notes.dmw.lite.generator;

import com.google.common.base.Objects;
import com.google.inject.Inject;
import eu.jgen.notes.dmw.lite.generator.LangOutputProvider;
import eu.jgen.notes.dmw.lite.lang.YAnnotJava;
import eu.jgen.notes.dmw.lite.lang.YClass;
import eu.jgen.notes.dmw.lite.lang.YFunction;
import eu.jgen.notes.dmw.lite.lang.YMember;
import eu.jgen.notes.dmw.lite.lang.YProperty;
import eu.jgen.notes.dmw.lite.lang.YWidget;
import eu.jgen.notes.dmw.lite.utility.LangJavaGeneratorHelper;
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
public class LangJavaWidgetGenerator implements IGenerator {
  @Inject
  @Extension
  private LangUtil _langUtil;
  
  @Inject
  @Extension
  private LangJavaGeneratorHelper _langJavaGeneratorHelper;
  
  private List<String> imports = CollectionLiterals.<String>newArrayList();
  
  @Override
  public void doGenerate(final Resource input, final IFileSystemAccess fsa) {
    final Function1<EObject, Boolean> _function = (EObject element) -> {
      return Boolean.valueOf((element instanceof YAnnotJava));
    };
    final Procedure1<EObject> _function_1 = (EObject element) -> {
      final YAnnotJava annotSwift = ((YAnnotJava) element);
      String _name = annotSwift.getDatabase().getName();
      boolean _equals = Objects.equal(_name, "MySQL");
      if (_equals) {
        final Function1<EObject, Boolean> _function_2 = (EObject element2) -> {
          return Boolean.valueOf((element instanceof YWidget));
        };
        final Procedure1<EObject> _function_3 = (EObject element2) -> {
          final YWidget widget = ((YWidget) element2);
          this.generateWidget(fsa, widget);
        };
        IteratorExtensions.<EObject>forEach(IteratorExtensions.<EObject>filter(input.getAllContents(), _function_2), _function_3);
      }
    };
    IteratorExtensions.<EObject>forEach(IteratorExtensions.<EObject>filter(input.getAllContents(), _function), _function_1);
  }
  
  protected void generateWidget(final IFileSystemAccess fsa, final YWidget widget) {
    this.imports.clear();
    final Consumer<YClass> _function = (YClass clazz) -> {
      if (((clazz.getSuperclass() != null) && Objects.equal(clazz.getSuperclass().getName(), "Widget"))) {
        this.imports.add("eu.jgen.notes.dmw.lite.runtimes.XWidget");
        StringConcatenation _builder = new StringConcatenation();
        String _documentation = this._langJavaGeneratorHelper.getDocumentation(clazz);
        _builder.append(_documentation);
        _builder.newLineIfNotEmpty();
        _builder.append("@SuppressWarnings(\"unused\")");
        _builder.newLine();
        _builder.append("public class ");
        String _name = clazz.getName();
        _builder.append(_name);
        _builder.append(" extends XWidget {");
        _builder.newLineIfNotEmpty();
        _builder.append("   ");
        String _generateInnerClasses = this.generateInnerClasses(clazz);
        _builder.append(_generateInnerClasses, "   ");
        _builder.newLineIfNotEmpty();
        _builder.append("   ");
        _builder.newLine();
        _builder.append("   ");
        String _generateProperties = this.generateProperties(clazz);
        _builder.append(_generateProperties, "   ");
        _builder.newLineIfNotEmpty();
        _builder.append("   ");
        _builder.newLine();
        _builder.append("   ");
        String _generateFunctions = this.generateFunctions(clazz);
        _builder.append(_generateFunctions, "   ");
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
        _builder_1.newLineIfNotEmpty();
        fsa.generateFile(_plus_2, 
          LangOutputProvider.DEFAULT, _builder_1);
      } else {
        StringConcatenation _builder_2 = new StringConcatenation();
        String _documentation_1 = this._langJavaGeneratorHelper.getDocumentation(clazz);
        _builder_2.append(_documentation_1);
        _builder_2.newLineIfNotEmpty();
        _builder_2.append("@SuppressWarnings(\"unused\")");
        _builder_2.newLine();
        _builder_2.append("public class ");
        String _name_3 = clazz.getName();
        _builder_2.append(_name_3);
        _builder_2.append(" {");
        _builder_2.newLineIfNotEmpty();
        _builder_2.newLine();
        _builder_2.append("}");
        _builder_2.newLine();
        final String body_1 = _builder_2.toString();
        String _fileSystemPath_1 = this._langUtil.getFileSystemPath(widget.getName());
        String _plus_3 = (_fileSystemPath_1 + "/");
        String _name_4 = clazz.getName();
        String _plus_4 = (_plus_3 + _name_4);
        String _plus_5 = (_plus_4 + ".java");
        StringConcatenation _builder_3 = new StringConcatenation();
        _builder_3.append("package ");
        String _name_5 = widget.getName();
        _builder_3.append(_name_5);
        _builder_3.append(";");
        _builder_3.newLineIfNotEmpty();
        _builder_3.newLine();
        {
          for(final String imp_1 : this.imports) {
            _builder_3.append("import ");
            _builder_3.append(imp_1);
            _builder_3.append(";");
            _builder_3.newLineIfNotEmpty();
          }
        }
        _builder_3.newLine();
        _builder_3.append(body_1);
        _builder_3.newLineIfNotEmpty();
        fsa.generateFile(_plus_5, 
          LangOutputProvider.DEFAULT, _builder_3);
      }
    };
    widget.getClasses().forEach(_function);
  }
  
  protected String generateFunctions(final YClass clazz) {
    StringConcatenation _builder = new StringConcatenation();
    {
      EList<YMember> _members = clazz.getMembers();
      for(final YMember member : _members) {
        {
          if ((member instanceof YFunction)) {
            String _generateFunctionForWidget = this.generateFunctionForWidget(((YFunction) member));
            _builder.append(_generateFunctionForWidget);
            _builder.newLineIfNotEmpty();
          }
        }
      }
    }
    return _builder.toString();
  }
  
  protected String generateFunctionForWidget(final YFunction function) {
    StringConcatenation _builder = new StringConcatenation();
    String _documentation = this._langJavaGeneratorHelper.getDocumentation(function);
    _builder.append(_documentation);
    _builder.append("  ");
    _builder.newLineIfNotEmpty();
    _builder.append("public ");
    {
      YClass _type = function.getType();
      boolean _tripleNotEquals = (_type != null);
      if (_tripleNotEquals) {
        String _name = function.getType().getName();
        _builder.append(_name);
      }
    }
    {
      YClass _type_1 = function.getType();
      boolean _tripleEquals = (_type_1 == null);
      if (_tripleEquals) {
        _builder.append("void");
      }
    }
    _builder.append(" ");
    String _name_1 = function.getName();
    _builder.append(_name_1);
    _builder.append("() {");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder.toString();
  }
  
  protected String generateProperties(final YClass clazz) {
    StringConcatenation _builder = new StringConcatenation();
    {
      EList<YMember> _members = clazz.getMembers();
      for(final YMember member : _members) {
        {
          if ((member instanceof YProperty)) {
            String _generatePropertyForWidget = this.generatePropertyForWidget(((YProperty) member));
            _builder.append(_generatePropertyForWidget);
            _builder.newLineIfNotEmpty();
          }
        }
      }
    }
    return _builder.toString();
  }
  
  protected String generatePropertyForWidget(final YProperty property) {
    String _xblockexpression = null;
    {
      String _findPackageName = this._langJavaGeneratorHelper.findPackageName(property);
      String _plus = (_findPackageName + ".");
      String _name = property.getType().getName();
      String _plus_1 = (_plus + _name);
      this.registerImport(_plus_1);
      StringConcatenation _builder = new StringConcatenation();
      String _documentation = this._langJavaGeneratorHelper.getDocumentation(property);
      _builder.append(_documentation);
      _builder.append("  ");
      _builder.newLineIfNotEmpty();
      _builder.append("public ");
      String _name_1 = property.getType().getName();
      _builder.append(_name_1);
      _builder.append(" ");
      String _name_2 = property.getName();
      _builder.append(_name_2);
      _builder.append(";");
      _xblockexpression = _builder.toString();
    }
    return _xblockexpression;
  }
  
  protected String generateInnerClasses(final YClass clazz) {
    StringConcatenation _builder = new StringConcatenation();
    {
      EList<YClass> _inners = clazz.getInners();
      for(final YClass innerclazz : _inners) {
        String _generateClass = this.generateClass(innerclazz);
        _builder.append(_generateClass);
      }
    }
    _builder.newLineIfNotEmpty();
    return _builder.toString();
  }
  
  protected String generateClass(final YClass innerclazz) {
    String _xifexpression = null;
    if (((innerclazz.getSuperclass() != null) && Objects.equal(innerclazz.getSuperclass().getName(), "Structure"))) {
      String _xblockexpression = null;
      {
        this.imports.add("eu.jgen.notes.dmw.lite.runtimes.XStructure");
        StringConcatenation _builder = new StringConcatenation();
        String _documentation = this._langJavaGeneratorHelper.getDocumentation(innerclazz);
        _builder.append(_documentation);
        _builder.append("  ");
        _builder.newLineIfNotEmpty();
        _builder.append("public class ");
        String _name = innerclazz.getName();
        _builder.append(_name);
        _builder.append(" extends XStructure {");
        _builder.newLineIfNotEmpty();
        {
          EList<YMember> _members = innerclazz.getMembers();
          for(final YMember member : _members) {
            {
              if ((member instanceof YProperty)) {
                _builder.append("\t");
                String _generatePropertyForStructure = this.generatePropertyForStructure(((YProperty) member));
                _builder.append(_generatePropertyForStructure, "\t");
                _builder.newLineIfNotEmpty();
              }
            }
          }
        }
        _builder.append("}");
        _builder.newLine();
        _xblockexpression = _builder.toString();
      }
      _xifexpression = _xblockexpression;
    }
    return _xifexpression;
  }
  
  protected String generatePropertyForStructure(final YProperty property) {
    String _xblockexpression = null;
    {
      String _name = property.getType().getName();
      String _plus = (("eu.jgen.notes.dmw.lite.runtimes." + "X") + _name);
      this.imports.add(_plus);
      StringConcatenation _builder = new StringConcatenation();
      String _documentation = this._langJavaGeneratorHelper.getDocumentation(property);
      _builder.append(_documentation);
      _builder.append("  ");
      _builder.newLineIfNotEmpty();
      _builder.append("public X");
      String _name_1 = property.getType().getName();
      _builder.append(_name_1);
      _builder.append(" ");
      String _name_2 = property.getName();
      _builder.append(_name_2);
      _builder.append(";");
      _xblockexpression = _builder.toString();
    }
    return _xblockexpression;
  }
  
  protected String registerImport(final String name) {
    boolean _contains = this.imports.contains(name);
    if (_contains) {
      return "";
    }
    this.imports.add(name);
    return "";
  }
}
