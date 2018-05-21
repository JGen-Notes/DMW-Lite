package eu.jgen.notes.dmw.lite.runtimes;

/*
 * This class is a building foundation for every widget. Any widget class 
 * need to extent directly or indirectly <code>Widget</code> class.
 * <code>Widget</code> class provides a lot of useful functions allowing
 * development fully functional widgets.
 */
public class XWidget {

	/*
	 *  This function allows to set global exit state value .
	 */
	 public void setExitState(ExitState exitState )  {
	 
	}
	
	/*
	 *  This function allows to set global command value .
	 */
	 public void setCommand(Command command ) {

	}
	
	/*
	 *  This function allows to check if global exist state is set to the
	 *  concrete value .
	 */
	 public boolean isExitState(ExitState exitState) {
		return false;
	}
	
	/*
	 *  This function allows to check if global command is set to the
	 *  concrete value .
	 */
	 public boolean isCommand(Command command ) {
	 	return false;
	}
	
	 public void moveStruct(XStructure fromview  , XStructure toview) {

	}
}
