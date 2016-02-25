package net.turtle.math.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.turtle.math.util.BigVectorUtil;

public class BigRationalVector extends BigVector< BigRational > {

	public BigRationalVector() {

	}

	public BigRationalVector( String vector ) {
		super(BigVectorUtil.parseBigRationalVector(vector));
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
	protected BigVector< BigRational > createInstance( ArrayList< BigRational > coordinatesSum ) {
		return new BigRationalVector( coordinatesSum , true );
	}

}
