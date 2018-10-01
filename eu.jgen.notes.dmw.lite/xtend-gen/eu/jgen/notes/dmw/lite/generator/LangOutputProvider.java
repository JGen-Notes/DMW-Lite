package eu.jgen.notes.dmw.lite.generator;

import com.google.common.collect.Sets;
import java.util.Set;
import org.eclipse.xtext.generator.IOutputConfigurationProvider;
import org.eclipse.xtext.generator.OutputConfiguration;

@SuppressWarnings("all")
public class LangOutputProvider implements IOutputConfigurationProvider {
  public final static String DEFAULT = "DEFAULT_OUTPUT";
  
  public final static String DDL = "DDL";
  
  public final static String SWIFT = "SWIFT";
  
  @Override
  public Set<OutputConfiguration> getOutputConfigurations() {
    final Set<OutputConfiguration> configurations = Sets.<OutputConfiguration>newHashSet();
    OutputConfiguration defaultOutput = new OutputConfiguration(LangOutputProvider.DEFAULT);
    defaultOutput.setDescription("Default Folder");
    defaultOutput.setOutputDirectory("./src-gen");
    defaultOutput.setOverrideExistingResources(true);
    defaultOutput.setCreateOutputDirectory(true);
    defaultOutput.setCleanUpDerivedResources(true);
    defaultOutput.setSetDerivedProperty(true);
    configurations.add(defaultOutput);
    OutputConfiguration otherOutput = new OutputConfiguration(LangOutputProvider.DDL);
    otherOutput.setDescription("Data Definition Language");
    otherOutput.setOutputDirectory("./src-gen-ddl");
    otherOutput.setOverrideExistingResources(true);
    otherOutput.setCreateOutputDirectory(true);
    otherOutput.setCleanUpDerivedResources(true);
    otherOutput.setSetDerivedProperty(true);
    configurations.add(otherOutput);
    OutputConfiguration swiftOutput = new OutputConfiguration(LangOutputProvider.SWIFT);
    swiftOutput.setDescription("Swift Package Manager");
    swiftOutput.setOutputDirectory("./src-swift");
    swiftOutput.setOverrideExistingResources(true);
    swiftOutput.setCreateOutputDirectory(true);
    swiftOutput.setCleanUpDerivedResources(true);
    swiftOutput.setSetDerivedProperty(true);
    configurations.add(swiftOutput);
    return configurations;
  }
}
