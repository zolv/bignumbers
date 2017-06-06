package net.turtle.math.exception;

public class NotImplementedException extends RuntimeException {
	
	private static final long serialVersionUID = 6170333883901763614L;
	
	public NotImplementedException() {
	}
	
	public NotImplementedException( String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace ) {
		super( message, cause, enableSuppression, writableStackTrace );
	}
	
	public NotImplementedException( String message, Throwable cause ) {
		super( message, cause );
	}
	
	public NotImplementedException( String message ) {
		super( message );
	}
	
	public NotImplementedException( Throwable cause ) {
		super( cause );
	}
	
}
