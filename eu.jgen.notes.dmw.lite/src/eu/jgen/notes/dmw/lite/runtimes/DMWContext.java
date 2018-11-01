package eu.jgen.notes.dmw.lite.runtimes;

import java.sql.Connection;

public class DMWContext {
	
	public DMWCommand command;
	public DMWExitState exitState;
	private Connection connection;
	
	public static DMWContext getInstance() {
		return new DMWContext();
	}
	
	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
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

}
