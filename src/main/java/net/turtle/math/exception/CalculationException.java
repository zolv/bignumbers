package net.turtle.math.exception;

public class CalculationException extends RuntimeException {

	private static final long serialVersionUID = 8730069633079206973L;

	public CalculationException() {
	}

	public CalculationException( String message ) {
		super( message );
	}

	public CalculationException( Throwable cause ) {
		super( cause );
	}

	public CalculationException( String message , Throwable cause ) {
		super( message , cause );
	}

	public CalculationException( String message , Throwable cause , boolean enableSuppression , boolean writableStackTrace ) {
		super( message , cause , enableSuppression , writableStackTrace );
	}

}
