/*
 * generated by Xtext 2.15.0
 */
package eu.jgen.notes.dmw.lite.ui.tests;

import com.google.inject.Injector;
import eu.jgen.notes.dmw.lite.ui.internal.LiteActivator;
import org.eclipse.xtext.testing.IInjectorProvider;

public class LangUiInjectorProvider implements IInjectorProvider {

	@Override
	public Injector getInjector() {
		return LiteActivator.getInstance().getInjector("eu.jgen.notes.dmw.lite.Lang");
	}

}
