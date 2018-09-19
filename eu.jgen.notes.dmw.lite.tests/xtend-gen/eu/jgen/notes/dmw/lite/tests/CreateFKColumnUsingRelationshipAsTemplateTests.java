package eu.jgen.notes.dmw.lite.tests;

import com.google.inject.Inject;
import com.google.inject.Provider;
import eu.jgen.notes.dmw.lite.lang.YAnnotAbstractColumn;
import eu.jgen.notes.dmw.lite.lang.YAnnotForeignKey;
import eu.jgen.notes.dmw.lite.lang.YAnnotRel;
import eu.jgen.notes.dmw.lite.lang.YWidget;
import eu.jgen.notes.dmw.lite.tests.LangInjectorProvider;
import eu.jgen.notes.dmw.lite.utility.LangDBUtil;
import eu.jgen.notes.dmw.lite.utility.LangLib;
import java.util.function.Consumer;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.XtextRunner;
import org.eclipse.xtext.testing.util.ParseHelper;
import org.eclipse.xtext.testing.validation.ValidationTestHelper;
import org.eclipse.xtext.validation.Issue;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.InputOutput;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.IteratorExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(XtextRunner.class)
@InjectWith(LangInjectorProvider.class)
@SuppressWarnings("all")
public class CreateFKColumnUsingRelationshipAsTemplateTests {
  @Inject
  @Extension
  private ParseHelper<YWidget> _parseHelper;
  
  @Inject
  @Extension
  private LangDBUtil _langDBUtil;
  
  @Inject
  @Extension
  private ValidationTestHelper _validationTestHelper;
  
  @Inject
  @Extension
  private LangLib _langLib;
  
  @Inject
  private Provider<ResourceSet> resourceSetProvider;
  
  @Test
  public void testEntityDefault() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("@database MySQL");
    _builder.newLine();
    _builder.newLine();
    _builder.append("@entity Log {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@attr entryType : Short @length(2);");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@rel @parent has -> Description * <- Description.isFor;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@id logid(entryType);");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("@entity Description {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@attr number : Short @length(2);");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@attr message : String @length(128);");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@rel isFor -> Log <- Log.has;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@id logid(number);");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("@td database MySQL {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@table LOG -> Log {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("@column ENTRY_TYPE -> Log.entryType as CHAR @length(2);");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("@primary (ENTRY_TYPE);");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@table DESCRIPTION -> Description {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("@column NUMBER -> Description.number as SMALLINT @length (2)");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("@column MESSAGE -> Description.message as CHAR @length (128)");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("@primary (NUMBER)");
    _builder.newLine();
    _builder.append("\t\t   ");
    _builder.append("@foreign Description.isFor { ");
    _builder.newLine();
    _builder.append("\t\t   \t");
    _builder.append("@column FK_LOG__ENTRY_TYPE -> LOG.ENTRY_TYPE");
    _builder.newLine();
    _builder.append("\t\t   ");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    final YWidget model = this.loadLibAndParse(_builder);
    final Consumer<Issue> _function = (Issue it) -> {
      InputOutput.<Issue>println(it);
    };
    this._validationTestHelper.validate(model).forEach(_function);
    final Function1<EObject, Boolean> _function_1 = (EObject it) -> {
      return Boolean.valueOf((it instanceof YAnnotRel));
    };
    final Procedure1<EObject> _function_2 = (EObject it) -> {
      final YAnnotRel relationship = ((YAnnotRel) it);
      boolean _isParent = relationship.isParent();
      boolean _not = (!_isParent);
      if (_not) {
        final YAnnotForeignKey foreignKeys = this._langDBUtil.converRelationshipIntoForeignKeys(relationship);
        if ((foreignKeys != null)) {
          final Function1<YAnnotAbstractColumn, Boolean> _function_3 = (YAnnotAbstractColumn it_1) -> {
            return Boolean.valueOf(true);
          };
          final YAnnotAbstractColumn foreignKey = IterableExtensions.<YAnnotAbstractColumn>findFirst(foreignKeys.getColumns(), _function_3);
          InputOutput.<String>println(foreignKey.getName());
          Assert.assertEquals("FK_LOG__ENTRY_TYPE", foreignKey.getName());
        }
      }
    };
    IteratorExtensions.<EObject>forEach(IteratorExtensions.<EObject>filter(model.eAllContents(), _function_1), _function_2);
  }
  
  private YWidget loadLibAndParse(final CharSequence p) {
    try {
      YWidget _xblockexpression = null;
      {
        final ResourceSet resourceSet = this.resourceSetProvider.get();
        this._langLib.loadLib(resourceSet);
        _xblockexpression = this._parseHelper.parse(p, resourceSet);
      }
      return _xblockexpression;
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
}
