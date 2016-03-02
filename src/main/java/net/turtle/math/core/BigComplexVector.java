package net.turtle.math.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import net.turtle.math.util.BigVectorUtil;

public class BigComplexVector extends BigFieldElementVector< BigComplex, BigComplexVector > {
	
	public BigComplexVector() {
		
	}
	
	public BigComplexVector( String vector ) {
		super( BigVectorUtil.parseBigComplexVector( vector ) );
	}
	
	public BigComplexVector( BigComplex... coordinatesToUse ) {
		super( Arrays.asList( coordinatesToUse ) );
	}
	
	public BigComplexVector( List< BigComplex > input ) {
		super( input );
	}
	
	protected BigComplexVector( List< BigComplex > input, boolean trusted ) {
		super( input, trusted );
	}
	
	@Override
	protected BigComplex createZeroFieldElement() {
		return BigComplex.ZERO;
	}
	
	@Override
	protected BigComplexVector createInstance( List< BigComplex > coordinatesSum ) {
		return new BigComplexVector( coordinatesSum, true );
	}

	public BigComplexVector conjugate() {
		final ArrayList< BigComplex > resultCoordinates = new ArrayList<>( this.getDimension() );
		final Iterator< BigComplex > thisCoordinatesIt = this.coordinates.iterator();
		while ( thisCoordinatesIt.hasNext() ) {
			resultCoordinates.add( thisCoordinatesIt.next().conjugate() );
		}
		return this.createInstance( resultCoordinates );
	}
	
}
