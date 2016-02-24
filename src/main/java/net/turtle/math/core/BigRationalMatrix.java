package net.turtle.math.core;

public class BigRationalMatrix extends BigMatrix< BigRational > {

	public BigRationalMatrix() {
		super();
	}

	public BigRationalMatrix( String matrix ) {
		super(matrix);
	}

	@Override
	protected BigVector< BigRational > createRowElement( String vectorString ) {
		return new BigRationalVector( vectorString );
	}

}
