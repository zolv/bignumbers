package net.turtle.math.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import net.turtle.math.exception.CalculationException;
import net.turtle.math.exception.DifferentDimensionsException;

public class BigVector implements FieldElement< BigVector > {

	private final List< BigRational > coordinates;

	public BigVector() {
		this.coordinates = Collections.emptyList();
	}

	public BigVector( BigRational... coordinatesToUse ) {
		this( Arrays.asList( coordinatesToUse ) , true );
	}

	public BigVector( List< BigRational > input ) {
		this( input , false );
	}

	private BigVector( List< BigRational > input , boolean trusted ) {
		if ( trusted ) {
			this.coordinates = input;
		} else {
			this.coordinates = new ArrayList< >(input);
		}
	}

	public List< BigRational > getCoordinates() {
		return Collections.unmodifiableList( this.coordinates );
	}

	@Override
	public BigVector add( BigVector augend ) throws CalculationException {
		this.checkDimensions( augend );
		final ArrayList< BigRational > coordinatesSum = new ArrayList< >( this.getDimension() );
		final Iterator< BigRational > thisCoordinatesIt = this.coordinates.iterator();
		final Iterator< BigRational > augendCoordinatesIt = augend.coordinates.iterator();
		while ( thisCoordinatesIt.hasNext() && augendCoordinatesIt.hasNext() ) {
			coordinatesSum.add( thisCoordinatesIt.next().add( augendCoordinatesIt.next() ) );
		}
		return new BigVector( coordinatesSum );
	}

	public int getDimension() {
		return this.coordinates.size();
	}

	@Override
	public BigVector subtract( BigVector subtrahend ) throws CalculationException {
		this.checkDimensions( subtrahend );
		final ArrayList< BigRational > coordinatesSum = new ArrayList< >( this.getDimension() );
		final Iterator< BigRational > thisCoordinatesIt = this.coordinates.iterator();
		final Iterator< BigRational > augendCoordinatesIt = subtrahend.coordinates.iterator();
		while ( thisCoordinatesIt.hasNext() && augendCoordinatesIt.hasNext() ) {
			coordinatesSum.add( thisCoordinatesIt.next().subtract( augendCoordinatesIt.next() ) );
		}
		return new BigVector( coordinatesSum );
	}

	@Override
	public BigVector multiply( BigVector multiplicand ) throws CalculationException {
		return null;
	}

	public BigVector multiply( BigRational multiplicand ) {
		final ArrayList< BigRational > coordinatesSum = new ArrayList< >( this.getDimension() );
		final Iterator< BigRational > thisCoordinatesIt = this.coordinates.iterator();
		while ( thisCoordinatesIt.hasNext() ) {
			coordinatesSum.add( thisCoordinatesIt.next().multiply( multiplicand ) );
		}
		return new BigVector( coordinatesSum );
	}

	@Override
	public BigVector divide( BigVector divisor ) {
		return this.multiply( divisor.inverse() );
	}

	public BigVector divide( BigRational divisor ) {
		return this.multiply( divisor.inverse() );
	}

	@Override
	public BigVector inverse() {
		final ArrayList< BigRational > resultCoordinates = new ArrayList< >( this.getDimension() );
		final Iterator< BigRational > thisCoordinatesIt = this.coordinates.iterator();
		while ( thisCoordinatesIt.hasNext() ) {
			resultCoordinates.add( thisCoordinatesIt.next().inverse() );
		}
		return new BigVector( resultCoordinates );
	}

	@Override
	public BigVector negate() {
		final ArrayList< BigRational > resultCoordinates = new ArrayList< >( this.getDimension() );
		final Iterator< BigRational > thisCoordinatesIt = this.coordinates.iterator();
		while ( thisCoordinatesIt.hasNext() ) {
			resultCoordinates.add( thisCoordinatesIt.next().negate() );
		}
		return new BigVector( resultCoordinates );
	}

	public boolean isSameDimensionAs( BigVector other ) {
		return this.coordinates.size() == other.coordinates.size();
	}

	private void checkDimensions( BigVector other ) {
		if ( !this.isSameDimensionAs( other ) ) {
			throw new DifferentDimensionsException();
		}
	}

}
