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
package eu.jgen.notes.dmw.lite.tests.inwork;

import eu.jgen.notes.dmw.lite.tests.LangInjectorProvider;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.XtextRunner;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Functions.Function0;
import org.eclipse.xtext.xbase.lib.InputOutput;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(XtextRunner.class)
@InjectWith(LangInjectorProvider.class)
@SuppressWarnings("all")
public class DerbyTest {
  private String driver = "org.apache.derby.jdbc.EmbeddedDriver";
  
  private String dbName = "jdbcDemoDB";
  
  private String connectionURL = (("jdbc:derby:" + this.dbName) + ";create=true");
  
  private final String createString = new Function0<String>() {
    public String apply() {
      StringConcatenation _builder = new StringConcatenation();
      _builder.newLine();
      _builder.append("CREATE TABLE \"SERVER\" (");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("\"NAME\" CHAR(5) ,");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("\"DESC\" CHAR(20) ");
      _builder.newLine();
      _builder.append(",\tCONSTRAINT \"pk_SERVER\"");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("PRIMARY KEY (\"NAME\")");
      _builder.newLine();
      _builder.append(") ");
      _builder.newLine();
      _builder.newLine();
      _builder.newLine();
      _builder.newLine();
      _builder.newLine();
      _builder.newLine();
      return _builder.toString();
    }
  }.apply();
  
  @Test
  public void testWidgetNameTooLong() {
    try {
      Connection conn = DriverManager.getConnection(this.connectionURL);
      InputOutput.<Connection>println(conn);
      final Statement s = conn.createStatement();
      InputOutput.<Boolean>println(Boolean.valueOf(s.execute(this.createString)));
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
}
