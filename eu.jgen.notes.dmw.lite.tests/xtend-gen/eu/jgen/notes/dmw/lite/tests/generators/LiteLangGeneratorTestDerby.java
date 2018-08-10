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
package eu.jgen.notes.dmw.lite.tests.generators;

import com.google.common.base.Objects;
import com.google.inject.Inject;
import eu.jgen.notes.dmw.lite.tests.LangInjectorProvider;
import eu.jgen.notes.dmw.lite.utility.LangLib;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.diagnostics.Severity;
import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.XtextRunner;
import org.eclipse.xtext.util.IAcceptor;
import org.eclipse.xtext.validation.Issue;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.InputOutput;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.testing.CompilationTestHelper;
import org.eclipse.xtext.xbase.testing.TemporaryFolder;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(XtextRunner.class)
@InjectWith(LangInjectorProvider.class)
@SuppressWarnings("all")
public class LiteLangGeneratorTestDerby {
  @Rule
  @Inject
  public TemporaryFolder temporaryFolder;
  
  @Inject
  @Extension
  private CompilationTestHelper _compilationTestHelper;
  
  @Inject
  @Extension
  private LangLib _langLib;
  
  private String driver = "org.apache.derby.jdbc.EmbeddedDriver";
  
  private String dbName = "DBUnitTest";
  
  private String connectionURL = (("jdbc:derby:" + this.dbName) + ";create=true");
  
  private Connection connection;
  
  private Statement statement;
  
  /**
   * @throws java.lang.Exception
   */
  @Before
  public void setUp() throws Exception {
    final Properties properties = System.getProperties();
    properties.setProperty("derby.system.home", "D:\\temp");
    Class.forName(this.driver);
    this.connection = DriverManager.getConnection(this.connectionURL, properties);
    this.statement = this.connection.createStatement();
  }
  
  /**
   * @throws java.lang.Exception
   */
  @After
  public void tearDown() throws Exception {
  }
  
  @Test
  public void testGeneratedDataDefinitionsForDerby() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("package log.sample.project");
      _builder.newLine();
      _builder.newLine();
      _builder.append("@entity server {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("@attr name : String @length(8)");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("@attr desc : String @length(40)");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("@id myid(name)");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("@rel @parent produces -> Log * <- Log.isFor         ");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _builder.newLine();
      _builder.append("@entity Log {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("@attr entryType : Short @length(2);");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("@attr message : String @length(128);");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("@attr timeCreated : Time;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("@id logid(timeCreated);");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("@rel isFor -> server <- server.produces  ");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _builder.newLine();
      _builder.append("@database MySQL;");
      _builder.newLine();
      _builder.newLine();
      _builder.append("@td database MySQL {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("@table SERVER -> server {");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("@column NAME -> server.name as CHAR @length ( 8 )");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("@column DESC -> server.desc as CHAR @length ( 40 )");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("@primary (NAME)");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("@table LOG -> Log {");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("@column ENTRY_TYPE -> Log.entryType as SMALLINT @length ( 2 )");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("@column MESSAGE -> Log.message as CHAR @length ( 128 )");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("@column TIME_CREATED -> Log.timeCreated as TIME");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("@primary (TIME_CREATED)@foreign Log.isFor { @column FK_SERVER__NAME -> SERVER.NAME }");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      final String body = _builder.toString();
      StringConcatenation _builder_1 = new StringConcatenation();
      _builder_1.append("MULTIPLE FILES WERE GENERATED");
      _builder_1.newLine();
      _builder_1.newLine();
      _builder_1.append("File 1 : /myProject/./src-gen-ddl/derby/LOG.ddl");
      _builder_1.newLine();
      _builder_1.newLine();
      _builder_1.append("CREATE TABLE \"LOG\" (");
      _builder_1.newLine();
      _builder_1.append("\t");
      _builder_1.append("\"ENTRY_TYPE\" SMALLINT NOT NULL,");
      _builder_1.newLine();
      _builder_1.append("\t");
      _builder_1.append("\"MESSAGE\" CHAR(128) NOT NULL,");
      _builder_1.newLine();
      _builder_1.append("\t");
      _builder_1.append("\"TIME_CREATED\" TIME NOT NULL");
      _builder_1.newLine();
      _builder_1.append(",\t\"FK_SERVER__NAME\" CHAR(8) NOT NULL");
      _builder_1.newLine();
      _builder_1.append(",\tCONSTRAINT \"PK_LOG\"");
      _builder_1.newLine();
      _builder_1.append("\t");
      _builder_1.append("PRIMARY KEY (\"TIME_CREATED\")");
      _builder_1.newLine();
      _builder_1.append(")");
      _builder_1.newLine();
      _builder_1.newLine();
      _builder_1.append("File 2 : /myProject/./src-gen-ddl/derby/SERVER.ddl");
      _builder_1.newLine();
      _builder_1.newLine();
      _builder_1.append("CREATE TABLE \"SERVER\" (");
      _builder_1.newLine();
      _builder_1.append("\t");
      _builder_1.append("\"NAME\" CHAR(8) NOT NULL,");
      _builder_1.newLine();
      _builder_1.append("\t");
      _builder_1.append("\"DESC\" CHAR(40) NOT NULL");
      _builder_1.newLine();
      _builder_1.append(",\tCONSTRAINT \"PK_SERVER\"");
      _builder_1.newLine();
      _builder_1.append("\t");
      _builder_1.append("PRIMARY KEY (\"NAME\")");
      _builder_1.newLine();
      _builder_1.append(")");
      _builder_1.newLine();
      _builder_1.newLine();
      this._compilationTestHelper.assertCompilesTo(body, _builder_1);
      this.cleanUpDatabase();
      final IAcceptor<CompilationTestHelper.Result> _function = (CompilationTestHelper.Result it) -> {
        try {
          this.checkNoValidationErrors(it);
          InputOutput.<String>println(this.extractDataDefinitionStatements(it, "LOG.ddl"));
          this.statement.execute(this.extractDataDefinitionStatements(it, "LOG.ddl"));
          InputOutput.<String>println(this.extractDataDefinitionStatements(it, "SERVER.ddl"));
          this.statement.execute(this.extractDataDefinitionStatements(it, "SERVER.ddl"));
        } catch (Throwable _e) {
          throw Exceptions.sneakyThrow(_e);
        }
      };
      this._compilationTestHelper.compile(CollectionLiterals.<String>newArrayList(this._langLib.loadLibSource(), body), _function);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  protected void checkNoValidationErrors(final CompilationTestHelper.Result it) {
    final Function1<Issue, Boolean> _function = (Issue it_1) -> {
      Severity _severity = it_1.getSeverity();
      return Boolean.valueOf(Objects.equal(_severity, Severity.ERROR));
    };
    final Iterable<Issue> allErrors = IterableExtensions.<Issue>filter(it.getErrorsAndWarnings(), _function);
    boolean _isEmpty = IterableExtensions.isEmpty(allErrors);
    boolean _not = (!_isEmpty);
    if (_not) {
      final Function1<Issue, String> _function_1 = (Issue it_1) -> {
        return it_1.toString();
      };
      String _join = IterableExtensions.join(IterableExtensions.<Issue, String>map(allErrors, _function_1), ", ");
      String _plus = ("One or more resources contained errors : " + _join);
      throw new IllegalStateException(_plus);
    }
  }
  
  protected String extractDataDefinitionStatements(final CompilationTestHelper.Result result, final String name) {
    Set<Map.Entry<String, CharSequence>> _entrySet = result.getAllGeneratedResources().entrySet();
    for (final Map.Entry<String, CharSequence> a : _entrySet) {
      boolean _endsWith = a.getKey().endsWith(name);
      if (_endsWith) {
        return a.getValue().toString();
      }
    }
    return "";
  }
  
  protected void cleanUpDatabase() {
    try {
      final DatabaseMetaData metaData = this.connection.getMetaData();
      final ResultSet rs = metaData.getTables(null, "APP", "%", ((String[])Conversions.unwrapArray(CollectionLiterals.<String>newArrayList("TABLE"), String.class)));
      while (rs.next()) {
        {
          InputOutput.<String>println(rs.getString(3));
          String _string = rs.getString(3);
          String _plus = ("DROP TABLE \"" + _string);
          String _plus_1 = (_plus + "\"");
          this.statement.execute(_plus_1);
        }
      }
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
}
