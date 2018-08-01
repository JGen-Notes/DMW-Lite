package eu.jgen.notes.dmw.lite.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	LangTechDesignTest.class,
	
	CreateColumnUsingAttributeAsTemplateTests.class,
	CreateFKColumnUsingRelationshipAsTemplateTests.class,
	CreatePrimaryKeyUsingIdentifierAsTemplateTests.class,
	CreateTableUsingEntityAsTemplateTests.class,
	
	LiteLangScopeProviderTest1.class,
	LiteLangTypeComputerTest.class,
	LiteLangTypeConformanceTest.class,
	LiteLangUtilTest.class, 
	LiteLangValidatorTest1.class,
	LiteLangValidatorDMTest.class,
	LiteLangAccessibilityTest.class,
	LiteLangGeneratorTest.class,
	LiteLangIndexTest.class,
	LiteLangLibTest1.class,
	LiteLangLibTest2.class,
	LiteLangParsingTest1.class,
	LiteLangParsingTest2.class
	
})
public class AllTests {

}
