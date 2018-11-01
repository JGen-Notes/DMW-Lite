package eu.jgen.notes.dmw.lite.generator;

import com.google.inject.Inject;
import eu.jgen.notes.dmw.lite.typing.LangTypeComputer;
import eu.jgen.notes.dmw.lite.utility.LangGeneratorHelperForJava;
import eu.jgen.notes.dmw.lite.utility.LangUtil;
import org.eclipse.xtext.xbase.lib.Extension;

@SuppressWarnings("all")
public class LasngStatemntGeneratorForJava {
  @Inject
  @Extension
  private LangUtil _langUtil;
  
  @Inject
  @Extension
  private LangGeneratorHelperForJava _langGeneratorHelperForJava;
  
  @Inject
  @Extension
  private LangTypeComputer _langTypeComputer;
}
