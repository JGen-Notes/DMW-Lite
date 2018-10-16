package eu.jgen.notes.dmw.lite.runtimes;

/*
 * This class is a building foundation for every widget. Any widget class 
 * need to extent directly or indirectly <code>Widget</code> class.
 * <code>Widget</code> class provides a lot of useful functions allowing
 * development fully functional widgets.
 */
public class XWidget {

	private DMWContext context;
	
	 public DMWContext getContext() {
		return context;
	}

	public XWidget(DMWContext context) {
		super();
		this.context = context;
	}

	public void moveStruct(XStructure fromview  , XStructure toview) {

	}
}
