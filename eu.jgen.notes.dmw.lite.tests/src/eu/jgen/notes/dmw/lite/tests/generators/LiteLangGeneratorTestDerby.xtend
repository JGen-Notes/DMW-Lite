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
package eu.jgen.notes.dmw.lite.tests.generators

import com.google.inject.Inject
import eu.jgen.notes.dmw.lite.tests.LangInjectorProvider
import eu.jgen.notes.dmw.lite.utility.LangLib
import java.sql.Connection
import java.sql.DriverManager
import java.sql.Statement
import org.eclipse.xtext.diagnostics.Severity
import org.eclipse.xtext.testing.InjectWith
import org.eclipse.xtext.testing.XtextRunner
import org.eclipse.xtext.xbase.testing.CompilationTestHelper
import org.eclipse.xtext.xbase.testing.CompilationTestHelper.Result
import org.eclipse.xtext.xbase.testing.TemporaryFolder
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(XtextRunner)
@InjectWith(LangInjectorProvider)
class LiteLangGeneratorTestDerby {

	@Rule
	@Inject public TemporaryFolder temporaryFolder
	@Inject extension CompilationTestHelper
  
	@Inject extension LangLib

	var driver = "org.apache.derby.jdbc.EmbeddedDriver";
	var dbName = "DBUnitTest";
	var connectionURL = "jdbc:derby:" + dbName + ";create=true";
	var Connection  connection;
	var Statement  statement;
		/**
	 * @throws java.lang.Exception
	 */
	@Before
	def void setUp() throws Exception {		
		val properties = System.getProperties();
        properties.setProperty("derby.system.home", "D:\\temp");
        Class.forName(driver);
	   connection = DriverManager.getConnection(connectionURL,properties);
	   statement = connection.createStatement();		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	def void tearDown() throws Exception {
		//DriverManager.getConnection("jdbc:derby:;shutdown=true");
	}
	

	@Test
	def void testGeneratedDataDefinitionsForDerby() {
		val body = '''
		package log.sample.project
		
		@entity server {
			@attr name : String @length(8)
			@attr desc : String @length(40)
			@id myid(name)
			@rel @parent produces -> Log * <- Log.isFor         
		}
		
		@entity Log {
			@attr entryType : Short @length(2);
			@attr message : String @length(128);
			@attr timeCreated : Time;
			@id logid(timeCreated);
			@rel isFor -> server <- server.produces  
		}
		
		@database MySQL;
		
		@td database MySQL {
			@table SERVER -> server {
				@column NAME -> server.name as CHAR @length ( 8 )
				@column DESC -> server.desc as CHAR @length ( 40 )
				@primary (NAME)
			}
			@table LOG -> Log {
				@column ENTRY_TYPE -> Log.entryType as SMALLINT @length ( 2 )
				@column MESSAGE -> Log.message as CHAR @length ( 128 )
				@column TIME_CREATED -> Log.timeCreated as TIME
				@primary (TIME_CREATED)@foreign Log.isFor { @column FK_SERVER__NAME -> SERVER.NAME }
			}
		}
		'''
		body.assertCompilesTo(
			'''
			MULTIPLE FILES WERE GENERATED
			
			File 1 : /myProject/./src-gen-ddl/derby/LOG.ddl
			
			CREATE TABLE "LOG" (
				"ENTRY_TYPE" SMALLINT NOT NULL,
				"MESSAGE" CHAR(128) NOT NULL,
				"TIME_CREATED" TIME NOT NULL
			,	"FK_SERVER__NAME" CHAR(8) NOT NULL
			,	CONSTRAINT "PK_LOG"
				PRIMARY KEY ("TIME_CREATED")
			)
			
			File 2 : /myProject/./src-gen-ddl/derby/SERVER.ddl
			
			CREATE TABLE "SERVER" (
				"NAME" CHAR(8) NOT NULL,
				"DESC" CHAR(40) NOT NULL
			,	CONSTRAINT "PK_SERVER"
				PRIMARY KEY ("NAME")
			)
			
		''')

		cleanUpDatabase()
		newArrayList(loadLibSource, body).compile()[
			checkNoValidationErrors(it)
			println(extractDataDefinitionStatements(it, "LOG.ddl"))
		 	statement.execute(extractDataDefinitionStatements(it, "LOG.ddl"))
			println(extractDataDefinitionStatements(it, "SERVER.ddl"))
		 	statement.execute(extractDataDefinitionStatements(it, "SERVER.ddl"))
		]
	}

	protected def void checkNoValidationErrors(Result it) {
		val allErrors = getErrorsAndWarnings.filter[severity == Severity.ERROR]
		if (!allErrors.empty) {
			throw new IllegalStateException(
				"One or more resources contained errors : " + allErrors.map[toString].join(", ")
			);
		}
	}

	protected def String extractDataDefinitionStatements(Result result, String name) {
		for (a : result.allGeneratedResources.entrySet) {
			if (a.key.endsWith(name)) {
				return a.value.toString
			}
		}
		return ""
	}
	
	protected def void cleanUpDatabase() { 
		val metaData = connection.getMetaData();		 
		val rs = metaData.getTables(null, "APP", "%" , newArrayList("TABLE") );		
		 while (rs.next()) {
		 	println(rs.getString(3))
		 	statement.execute('DROP TABLE "' +rs.getString(3) + '"')
		 }
	}

}
