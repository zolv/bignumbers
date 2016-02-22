package net.turtle.math.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import net.turtle.math.exception.CalculationException;
import net.turtle.math.exception.DifferentDimensionsException;
import net.turtle.math.exception.NotImplementedException;
import net.turtle.math.exception.ParsingException;

public class BigVector implements FieldElement< BigVector > {

	private final List< BigRational > coordinates;

	public BigVector() {
		this.coordinates = Collections.emptyList();
	}

	public BigVector( String vector ) {
		if ( vector.startsWith( "[" ) && vector.endsWith( "]" ) ) {
			final String vectorValues = vector.substring( 1 , vector.length() - 1 ).trim();
			if(!vectorValues.isEmpty()) {
			final String[] values = vectorValues.split( "," );
			final List< BigRational > coordinatesTemp = new ArrayList< >( values.length );
				for ( final String value : values ) {
					final BigRational valueNumber = new BigRational( value );
					coordinatesTemp.add( valueNumber );
				}
				this.coordinates = coordinatesTemp;
			} else {
				this.coordinates = new ArrayList< >( 0 );
			}
		} else {
			throw new ParsingException( "Vector string does not start with '[' or does not ends with ']'" );
		}
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
			this.coordinates = new ArrayList< >( input );
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
		assert ( !( thisCoordinatesIt.hasNext() || augendCoordinatesIt.hasNext() ) );
		return new BigVector( coordinatesSum , true );
	}

	public int getDimension() {
		return this.coordinates.size();
	}

	@Override
	public BigVector subtract( BigVector subtrahend ) throws CalculationException {
		this.checkDimensions( subtrahend );
		final ArrayList< BigRational > coordinatesSum = new ArrayList< >( this.getDimension() );
		final Iterator< BigRational > thisCoordinatesIt = this.coordinates.iterator();
		final Iterator< BigRational > subtrahendCoordinatesIt = subtrahend.coordinates.iterator();
		while ( thisCoordinatesIt.hasNext() && subtrahendCoordinatesIt.hasNext() ) {
			coordinatesSum.add( thisCoordinatesIt.next().subtract( subtrahendCoordinatesIt.next() ) );
		}
		assert ( !( thisCoordinatesIt.hasNext() || subtrahendCoordinatesIt.hasNext() ) );
		return new BigVector( coordinatesSum , true );
	}

	@Override
	public BigVector multiply( BigVector multiplicand ) throws CalculationException {
		throw new NotImplementedException();
	}

	public BigVector multiply( BigRational multiplicand ) {
		final ArrayList< BigRational > coordinatesSum = new ArrayList< >( this.getDimension() );
		final Iterator< BigRational > thisCoordinatesIt = this.coordinates.iterator();
		while ( thisCoordinatesIt.hasNext() ) {
			coordinatesSum.add( thisCoordinatesIt.next().multiply( multiplicand ) );
		}
		return new BigVector( coordinatesSum , true );
	}

	@Override
	public BigVector divide( BigVector divisor ) {
		return this.multiply( divisor.inverse() );
	}

	public BigVector divide( BigRational divisor ) {
		return this.multiply( divisor.inverse() );
	}

	/**
	 * There is no official way to inversing vector. This method just uses
	 * inversion of every coordinate. Not so usefull but...
	 */
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
		return new BigVector( resultCoordinates , true );
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
