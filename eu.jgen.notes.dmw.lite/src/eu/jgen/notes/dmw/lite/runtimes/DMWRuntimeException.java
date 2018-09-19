package eu.jgen.notes.dmw.lite.runtimes;

@SuppressWarnings("serial")
public class DMWRuntimeException extends RuntimeException {

	public DMWRuntimeException() {
		super();
	}

	public DMWRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public DMWRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}

	public DMWRuntimeException(String message) {
		super(message);
	}

	public DMWRuntimeException(Throwable cause) {
		super(cause);
	}

	

}
