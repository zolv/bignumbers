package net.turtle.math.core;

public class BigRationalMatrixValues {
	
	public static final BigRationalMatrix EMPTY = new BigRationalMatrix();
	
	public static final BigRationalMatrix ZERO_1x1 = new BigRationalMatrix( "[[0]]" );
	
	public static final BigRationalMatrix ZERO_2x2 = new BigRationalMatrix( "[[0,0],[0,0]]" );
	
	public static final BigRationalMatrix ZERO_3x3 = new BigRationalMatrix( "[[0,0,0],[0,0,0],[0,0,0]]" );
	
	public static final BigRationalMatrix ZERO_4x4 = new BigRationalMatrix( "[[0,0,0,0],[0,0,0,0],[0,0,0,0],[0,0,0,0]]" );

	public static final BigRationalMatrix ZERO_5x5 = new BigRationalMatrix( "[[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0]]" );
	
	public static final BigRationalMatrix IDENTITY_1x1 = new BigRationalMatrix( "[[1]]" );
	
	public static final BigRationalMatrix IDENTITY_2x2 = new BigRationalMatrix( "[[1,0],[0,1]]" );
	
	public static final BigRationalMatrix IDENTITY_3x3 = new BigRationalMatrix( "[[1,0,0],[0,1,0],[0,0,1]]" );

	public static final BigRationalMatrix IDENTITY_4x4 = new BigRationalMatrix( "[[1,0,0,0],[0,1,0,0],[0,0,1,0],[0,0,0,1]]" );

	public static final BigRationalMatrix IDENTITY_5x5 = new BigRationalMatrix( "[[1,0,0,0,0],[0,1,0,0,0],[0,0,1,0,0],[0,0,0,1,0],[0,0,0,0,1]]" );
	
	private BigRationalMatrixValues() {
		super();
	}
}
