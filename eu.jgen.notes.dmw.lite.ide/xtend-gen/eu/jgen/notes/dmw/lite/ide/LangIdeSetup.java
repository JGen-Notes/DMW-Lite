/**
 * generated by Xtext 2.12.0
 */
package eu.jgen.notes.dmw.lite.ide;

import com.google.inject.Guice;
import com.google.inject.Injector;
import eu.jgen.notes.dmw.lite.LangRuntimeModule;
import eu.jgen.notes.dmw.lite.LangStandaloneSetup;
import eu.jgen.notes.dmw.lite.ide.LangIdeModule;
import org.eclipse.xtext.util.Modules2;

/**
 * Initialization support for running Xtext languages as language servers.
 */
@SuppressWarnings("all")
public class LangIdeSetup extends LangStandaloneSetup {
  @Override
  public Injector createInjector() {
    LangRuntimeModule _langRuntimeModule = new LangRuntimeModule();
    LangIdeModule _langIdeModule = new LangIdeModule();
    return Guice.createInjector(Modules2.mixin(_langRuntimeModule, _langIdeModule));
  }
}
