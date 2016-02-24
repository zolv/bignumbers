package net.turtle.math.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BigRationalVector extends BigVector< BigRational > {

	public BigRationalVector() {
	}

	public BigRationalVector( String vector ) {
		super( vector );
	}

	public BigRationalVector( BigRational... coordinatesToUse ) {
		super( Arrays.asList( coordinatesToUse ) );
	}

	public BigRationalVector( List< BigRational > input ) {
		super( input );
	}

	protected BigRationalVector( List< BigRational > input , boolean trusted ) {
		super( input , trusted );
	}

	@Override
	protected BigRational createCoordinateInstance( String text ) {
		return new BigRational( text );
	}

	@Override
	protected BigVector< BigRational > createInstance( ArrayList< BigRational > coordinatesSum ) {
		return new BigRationalVector( coordinatesSum , true );
	}

}
