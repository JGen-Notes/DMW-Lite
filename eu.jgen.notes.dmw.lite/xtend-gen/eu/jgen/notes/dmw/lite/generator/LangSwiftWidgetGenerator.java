package eu.jgen.notes.dmw.lite.generator;

import com.google.common.base.Objects;
import com.google.inject.Inject;
import eu.jgen.notes.dmw.lite.generator.LangOutputProvider;
import eu.jgen.notes.dmw.lite.lang.YAnnotEntity;
import eu.jgen.notes.dmw.lite.lang.YAnnotSwift;
import eu.jgen.notes.dmw.lite.lang.YClass;
import eu.jgen.notes.dmw.lite.lang.YMember;
import eu.jgen.notes.dmw.lite.lang.YProperty;
import eu.jgen.notes.dmw.lite.lang.YWidget;
import eu.jgen.notes.dmw.lite.utility.LangSwiftGeneratorHelper;
import eu.jgen.notes.dmw.lite.utility.LangUtil;
import java.util.function.Consumer;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.generator.IFileSystemAccess;
import org.eclipse.xtext.generator.IGenerator;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.InputOutput;
import org.eclipse.xtext.xbase.lib.IteratorExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;

@SuppressWarnings("all")
public class LangSwiftWidgetGenerator implements IGenerator {
  @Inject
  @Extension
  private LangUtil _langUtil;
  
  @Inject
  @Extension
  private LangSwiftGeneratorHelper _langSwiftGeneratorHelper;
  
  @Override
  public void doGenerate(final Resource input, final IFileSystemAccess fsa) {
    final Function1<EObject, Boolean> _function = (EObject element) -> {
      return Boolean.valueOf((element instanceof YAnnotSwift));
    };
    final Procedure1<EObject> _function_1 = (EObject element) -> {
      final YAnnotSwift annotSwift = ((YAnnotSwift) element);
      this.generatePackageModule(fsa, annotSwift);
      final Function1<EObject, Boolean> _function_2 = (EObject element2) -> {
        return Boolean.valueOf((element2 instanceof YWidget));
      };
      final Procedure1<EObject> _function_3 = (EObject element2) -> {
        final YWidget widget = ((YWidget) element2);
        this.generateWidget(fsa, widget, annotSwift);
      };
      IteratorExtensions.<EObject>forEach(IteratorExtensions.<EObject>filter(input.getAllContents(), _function_2), _function_3);
    };
    IteratorExtensions.<EObject>forEach(IteratorExtensions.<EObject>filter(input.getAllContents(), _function), _function_1);
  }
  
  public void generateWidget(final IFileSystemAccess fsa, final YWidget widget, final YAnnotSwift annotSwift) {
    final Consumer<YClass> _function = (YClass clazz) -> {
      if (((clazz.getSuperclass() != null) && Objects.equal(clazz.getSuperclass().getName(), "Widget"))) {
        StringConcatenation _builder = new StringConcatenation();
        _builder.append("// dmw-generator-version: 0.2");
        _builder.newLine();
        _builder.append("\t\t\t\t\t\t\t\t\t\t");
        _builder.newLine();
        _builder.append("//  Created by Marek Stankiewicz on 17.04.2018.");
        _builder.newLine();
        _builder.append("//  Copyright © 2018 JGen. All rights reserved.");
        _builder.newLine();
        _builder.append("\t\t\t\t\t\t\t\t\t\t");
        _builder.newLine();
        _builder.append("import Foundation");
        _builder.newLine();
        _builder.newLine();
        String _documentation = this._langSwiftGeneratorHelper.getDocumentation(clazz);
        _builder.append(_documentation);
        _builder.newLineIfNotEmpty();
        _builder.append("class ");
        String _name = clazz.getName();
        _builder.append(_name);
        _builder.append("  {");
        _builder.newLineIfNotEmpty();
        _builder.append("\t   ");
        String _generateInnerClasses = this.generateInnerClasses(clazz);
        _builder.append(_generateInnerClasses, "\t   ");
        _builder.newLineIfNotEmpty();
        _builder.append("\t   ");
        _builder.newLine();
        _builder.append("\t   ");
        String _generateTableClasses = this.generateTableClasses(clazz);
        _builder.append(_generateTableClasses, "\t   ");
        _builder.newLineIfNotEmpty();
        _builder.newLine();
        _builder.append("}");
        _builder.newLine();
        final String body = _builder.toString();
        String _name_1 = annotSwift.getName();
        String _plus = (_name_1 + "/Sources/");
        String _name_2 = annotSwift.getName();
        String _plus_1 = (_plus + _name_2);
        String _plus_2 = (_plus_1 + "/");
        String _name_3 = clazz.getName();
        String _plus_3 = (_plus_2 + _name_3);
        String _plus_4 = (_plus_3 + ".swift");
        StringConcatenation _builder_1 = new StringConcatenation();
        _builder_1.newLine();
        _builder_1.append(body);
        _builder_1.newLineIfNotEmpty();
        fsa.generateFile(_plus_4, 
          LangOutputProvider.SWIFT, _builder_1);
      }
    };
    widget.getClasses().forEach(_function);
  }
  
  protected String generateTableClasses(final YClass clazz) {
    EList<YClass> _inners = clazz.getInners();
    for (final YClass inner : _inners) {
      YAnnotEntity _entity = inner.getEntity();
      boolean _tripleNotEquals = (_entity != null);
      if (_tripleNotEquals) {
        InputOutput.<String>println(inner.getEntity().getName());
      }
    }
    return "";
  }
  
  protected String generateInnerClasses(final YClass clazz) {
    StringConcatenation _builder = new StringConcatenation();
    {
      EList<YClass> _inners = clazz.getInners();
      for(final YClass innerclazz : _inners) {
        String _generateClass = this.generateClass(innerclazz);
        _builder.append(_generateClass);
        _builder.newLineIfNotEmpty();
        _builder.newLine();
      }
    }
    return _builder.toString();
  }
  
  protected String generateClass(final YClass innerclazz) {
    String _xifexpression = null;
    if (((innerclazz.getSuperclass() != null) && Objects.equal(innerclazz.getSuperclass().getName(), "Structure"))) {
      StringConcatenation _builder = new StringConcatenation();
      String _documentation = this._langSwiftGeneratorHelper.getDocumentation(innerclazz);
      _builder.append(_documentation);
      _builder.append("  ");
      _builder.newLineIfNotEmpty();
      _builder.append("public class ");
      String _name = innerclazz.getName();
      _builder.append(_name);
      _builder.append("  {");
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
      _builder.append("\t");
      CharSequence _generateFunctionInit = this.generateFunctionInit(innerclazz);
      _builder.append(_generateFunctionInit, "\t");
      _builder.append(" ");
      _builder.newLineIfNotEmpty();
      _builder.append("}");
      _builder.newLine();
      _xifexpression = _builder.toString();
    }
    return _xifexpression;
  }
  
  public CharSequence generateFunctionInit(final YClass clazz) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("init() {");
    _builder.newLine();
    {
      EList<YMember> _members = clazz.getMembers();
      for(final YMember member : _members) {
        {
          if ((member instanceof YProperty)) {
            _builder.append("\t");
            String _generatePropertyInit = this.generatePropertyInit(((YProperty) member));
            _builder.append(_generatePropertyInit, "\t");
            _builder.newLineIfNotEmpty();
          }
        }
      }
    }
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public String generatePropertyInit(final YProperty property) {
    String _name = property.getType().getName();
    if (_name != null) {
      switch (_name) {
        case "Double":
          String _name_1 = property.getName();
          String _plus = (_name_1 + " = ");
          return (_plus + "0;");
        case "Int":
          String _name_2 = property.getName();
          String _plus_1 = (_name_2 + " = ");
          return (_plus_1 + "0;");
        case "Short":
          String _name_3 = property.getName();
          String _plus_2 = (_name_3 + " = ");
          return (_plus_2 + "0;");
        case "String":
          String _name_4 = property.getName();
          String _plus_3 = (_name_4 + " = ");
          return (_plus_3 + "\"\";");
        case "Date":
          String _name_5 = property.getName();
          String _plus_4 = (_name_5 + " = ");
          return (_plus_4 + "Date();");
        case "Time":
          String _name_6 = property.getName();
          String _plus_5 = (_name_6 + " = ");
          return (_plus_5 + "Date();");
        default:
          return "unknown";
      }
    } else {
      return "unknown";
    }
  }
  
  protected String generatePropertyForStructure(final YProperty property) {
    StringConcatenation _builder = new StringConcatenation();
    String _documentation = this._langSwiftGeneratorHelper.getDocumentation(property);
    _builder.append(_documentation);
    _builder.append("  ");
    _builder.newLineIfNotEmpty();
    _builder.append("var ");
    String _name = property.getName();
    _builder.append(_name);
    _builder.append(" : ");
    String _generatePropertyType = this.generatePropertyType(property);
    _builder.append(_generatePropertyType);
    _builder.append(";");
    return _builder.toString();
  }
  
  protected String generatePropertyType(final YProperty property) {
    String _name = property.getType().getName();
    if (_name != null) {
      switch (_name) {
        case "Double":
          return "Double";
        case "Int":
          return "Int";
        case "Short":
          return "Int16";
        case "String":
          return "String";
        case "Date":
          return "Date";
        case "Time":
          return "Date";
        default:
          return "unknown";
      }
    } else {
      return "unknown";
    }
  }
  
  protected void generatePackageModule(final IFileSystemAccess fsa, final YAnnotSwift annotSwift) {
    String _name = annotSwift.getName();
    String _plus = (_name + "/");
    String _plus_1 = (_plus + "Package");
    String _plus_2 = (_plus_1 + ".swift");
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("// swift-tools-version:4.0");
    _builder.newLine();
    _builder.append("// The swift-tools-version declares the minimum version of Swift required to build this package.\t\t\t\t");
    _builder.newLine();
    _builder.newLine();
    _builder.append("import PackageDescription");
    _builder.newLine();
    _builder.newLine();
    _builder.append("let package = Package(");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("name: \"");
    String _name_1 = annotSwift.getName();
    _builder.append(_name_1, "    ");
    _builder.append("\",");
    _builder.newLineIfNotEmpty();
    _builder.append("    ");
    _builder.append("products: [");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("// Products define the executables and libraries produced by a package, and make them visible to other packages.");
    _builder.newLine();
    _builder.append("        ");
    _builder.append(".library(");
    _builder.newLine();
    _builder.append("            ");
    _builder.append("name: \"");
    String _name_2 = annotSwift.getName();
    _builder.append(_name_2, "            ");
    _builder.append("\",");
    _builder.newLineIfNotEmpty();
    _builder.append("            ");
    _builder.append("targets: [\"");
    String _name_3 = annotSwift.getName();
    _builder.append(_name_3, "            ");
    _builder.append("\"]),");
    _builder.newLineIfNotEmpty();
    _builder.append("    ");
    _builder.append("],");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("dependencies: [");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("// Dependencies declare other packages that this package depends on.");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("// .package(url: /* package url */, from: \"1.0.0\"),");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("],");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("targets: [");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("// Targets are the basic building blocks of a package. A target can define a module or a test suite.");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("// Targets can depend on other targets in this package, and on products in packages which this package depends on.");
    _builder.newLine();
    _builder.append("        ");
    _builder.append(".target(");
    _builder.newLine();
    _builder.append("            ");
    _builder.append("name: \"");
    String _name_4 = annotSwift.getName();
    _builder.append(_name_4, "            ");
    _builder.append("\",");
    _builder.newLineIfNotEmpty();
    _builder.append("            ");
    _builder.append("dependencies: []),");
    _builder.newLine();
    _builder.append("        ");
    _builder.append(".testTarget(");
    _builder.newLine();
    _builder.append("            ");
    _builder.append("name: \"");
    String _name_5 = annotSwift.getName();
    _builder.append(_name_5, "            ");
    _builder.append("Tests\",");
    _builder.newLineIfNotEmpty();
    _builder.append("            ");
    _builder.append("dependencies: [\"");
    String _name_6 = annotSwift.getName();
    _builder.append(_name_6, "            ");
    _builder.append("\"]),");
    _builder.newLineIfNotEmpty();
    _builder.append("    ");
    _builder.append("]");
    _builder.newLine();
    _builder.append(")");
    _builder.newLine();
    fsa.generateFile(_plus_2, 
      LangOutputProvider.SWIFT, _builder);
    String _name_7 = annotSwift.getName();
    String _plus_3 = (_name_7 + "/");
    String _plus_4 = (_plus_3 + ".gitignore");
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append(".DS_Store");
    _builder_1.newLine();
    _builder_1.append("/.build");
    _builder_1.newLine();
    _builder_1.append("/Packages");
    _builder_1.newLine();
    _builder_1.append("/*.xcodeproj");
    _builder_1.newLine();
    fsa.generateFile(_plus_4, 
      LangOutputProvider.SWIFT, _builder_1);
    String _name_8 = annotSwift.getName();
    String _plus_5 = (_name_8 + "/");
    String _plus_6 = (_plus_5 + "README.md");
    StringConcatenation _builder_2 = new StringConcatenation();
    _builder_2.append("# ");
    String _name_9 = annotSwift.getName();
    _builder_2.append(_name_9);
    _builder_2.newLineIfNotEmpty();
    _builder_2.newLine();
    String _documentation = this._langSwiftGeneratorHelper.getDocumentation(annotSwift);
    _builder_2.append(_documentation);
    _builder_2.newLineIfNotEmpty();
    fsa.generateFile(_plus_6, 
      LangOutputProvider.SWIFT, _builder_2);
  }
}
