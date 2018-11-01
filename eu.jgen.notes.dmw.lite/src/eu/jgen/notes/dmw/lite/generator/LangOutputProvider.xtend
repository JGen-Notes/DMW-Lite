package eu.jgen.notes.dmw.lite.generator

import com.google.common.collect.Sets
import java.util.Set
import org.eclipse.xtext.generator.IOutputConfigurationProvider
import org.eclipse.xtext.generator.OutputConfiguration
import org.eclipse.xtext.generator.IFileSystemAccess

class LangOutputProvider implements IOutputConfigurationProvider {
	public static final String DEFAULT = "DEFAULT_OUTPUT"
	public static final String DDL = "DDL"
	public static final String SWIFT = "SWIFT"

	override Set<OutputConfiguration> getOutputConfigurations() {
		
		val Set<OutputConfiguration> configurations = Sets.newHashSet()
		
		var defaultOutput = new OutputConfiguration(IFileSystemAccess.DEFAULT_OUTPUT);
	    defaultOutput.setDescription("Default Folder");
	    defaultOutput.setOutputDirectory("./src-gen");
	    defaultOutput.setOverrideExistingResources(true);
	    defaultOutput.setCreateOutputDirectory(true);
	    defaultOutput.setCleanUpDerivedResources(true);
	    defaultOutput.setSetDerivedProperty(true);
		configurations.add(defaultOutput)
		
		var OutputConfiguration otherOutput = new OutputConfiguration(DDL)
		otherOutput.setDescription("Data Definition Language")
		otherOutput.setOutputDirectory("./src-gen-ddl")
		otherOutput.setOverrideExistingResources(true)
		otherOutput.setCreateOutputDirectory(true)
		otherOutput.setCleanUpDerivedResources(true)
		otherOutput.setSetDerivedProperty(true)
		configurations.add(otherOutput)
		
		var OutputConfiguration swiftOutput = new OutputConfiguration(SWIFT)
		swiftOutput.setDescription("Swift Package Manager")
		swiftOutput.setOutputDirectory("./src-swift")
		swiftOutput.setOverrideExistingResources(true)
		swiftOutput.setCreateOutputDirectory(true)
		swiftOutput.setCleanUpDerivedResources(true)
		swiftOutput.setSetDerivedProperty(true)
		configurations.add(swiftOutput)
		
		return configurations

	}  
}
