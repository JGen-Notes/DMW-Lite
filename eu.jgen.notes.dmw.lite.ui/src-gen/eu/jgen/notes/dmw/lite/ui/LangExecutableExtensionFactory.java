/*
 * generated by Xtext 2.12.0
 */
package eu.jgen.notes.dmw.lite.ui;

import com.google.inject.Injector;
import eu.jgen.notes.dmw.lite.ui.internal.LiteActivator;
import org.eclipse.xtext.ui.guice.AbstractGuiceAwareExecutableExtensionFactory;
import org.osgi.framework.Bundle;

/**
 * This class was generated. Customizations should only happen in a newly
 * introduced subclass. 
 */
public class LangExecutableExtensionFactory extends AbstractGuiceAwareExecutableExtensionFactory {

	@Override
	protected Bundle getBundle() {
		return LiteActivator.getInstance().getBundle();
	}
	
	@Override
	protected Injector getInjector() {
		return LiteActivator.getInstance().getInjector(LiteActivator.EU_JGEN_NOTES_DMW_LITE_LANG);
	}
	
}
