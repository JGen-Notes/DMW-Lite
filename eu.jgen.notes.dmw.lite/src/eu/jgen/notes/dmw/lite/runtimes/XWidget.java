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

	/*
	 *  This function allows to set global exit state value .
	 */
	 public void setExitState(DMWExitState exitState )  {
	 
	}
	
	/*
	 *  This function allows to set global command value .
	 */
	 public void setCommand(DMWCommand command ) {

	}
	
	/*
	 *  This function allows to check if global exist state is set to the
	 *  concrete value .
	 */
	 public boolean isExitState(DMWExitState exitState) {
		return false;
	}
	
	/*
	 *  This function allows to check if global command is set to the
	 *  concrete value .
	 */
	 public boolean isCommand(DMWCommand command ) {
	 	return false;
	}
	
	 public void moveStruct(XStructure fromview  , XStructure toview) {

	}
}
