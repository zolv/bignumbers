package net.turtle.math.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import net.turtle.math.exception.CalculationException;
import net.turtle.math.exception.DifferentDimensionsException;
import net.turtle.math.exception.NotImplementedException;

public abstract class BigVector< T extends FieldElement< T > > implements FieldElement< BigVector< T > > {

	private final List< T > coordinates;

	public BigVector() {
		this.coordinates = Collections.emptyList();
	}

	public BigVector( List< T > input ) {
		this( input , false );
	}

	protected BigVector( List< T > input , boolean trusted ) {
		if ( trusted ) {
			this.coordinates = input;
		} else {
			this.coordinates = new ArrayList< >( input );
		}
	}

	protected abstract BigVector< T > createInstance( final ArrayList< T > coordinatesSum );

	public List< T > getCoordinates() {
		return Collections.unmodifiableList( this.coordinates );
	}

	@Override
	public BigVector< T > add( BigVector< T > augend ) throws CalculationException {
		this.checkDimensions( augend );
		final ArrayList< T > coordinatesSum = new ArrayList< >( this.getDimension() );
		final Iterator< T > thisCoordinatesIt = this.coordinates.iterator();
		final Iterator< T > augendCoordinatesIt = augend.coordinates.iterator();
		while ( thisCoordinatesIt.hasNext() && augendCoordinatesIt.hasNext() ) {
			coordinatesSum.add( thisCoordinatesIt.next().add( augendCoordinatesIt.next() ) );
		}
		assert ( !( thisCoordinatesIt.hasNext() || augendCoordinatesIt.hasNext() ) );
		return this.createInstance( coordinatesSum );
	}

	public int getDimension() {
		return this.coordinates.size();
	}

	@Override
	public BigVector< T > subtract( BigVector< T > subtrahend ) throws CalculationException {
		this.checkDimensions( subtrahend );
		final ArrayList< T > coordinatesSum = new ArrayList< >( this.getDimension() );
		final Iterator< T > thisCoordinatesIt = this.coordinates.iterator();
		final Iterator< T > subtrahendCoordinatesIt = subtrahend.coordinates.iterator();
		while ( thisCoordinatesIt.hasNext() && subtrahendCoordinatesIt.hasNext() ) {
			coordinatesSum.add( thisCoordinatesIt.next().subtract( subtrahendCoordinatesIt.next() ) );
		}
		assert ( !( thisCoordinatesIt.hasNext() || subtrahendCoordinatesIt.hasNext() ) );
		return this.createInstance( coordinatesSum );
	}

	@Override
	public BigVector< T > multiply( BigVector< T > multiplicand ) throws CalculationException {
		throw new NotImplementedException();
	}

	public BigVector< T > multiply( T multiplicand ) {
		final ArrayList< T > coordinatesSum = new ArrayList< >( this.getDimension() );
		final Iterator< T > thisCoordinatesIt = this.coordinates.iterator();
		while ( thisCoordinatesIt.hasNext() ) {
			coordinatesSum.add( thisCoordinatesIt.next().multiply( multiplicand ) );
		}
		return this.createInstance( coordinatesSum );
	}

	@Override
	public BigVector< T > divide( BigVector< T > divisor ) {
		return this.multiply( divisor.inverse() );
	}

	public BigVector< T > divide( T divisor ) {
		return this.multiply( divisor.inverse() );
	}

	/**
	 * There is no official way to inversing vector. This method just uses
	 * inversion of every coordinate. Not so usefull but...
	 */
	@Override
	public BigVector< T > inverse() {
		final ArrayList< T > resultCoordinates = new ArrayList< >( this.getDimension() );
		final Iterator< T > thisCoordinatesIt = this.coordinates.iterator();
		while ( thisCoordinatesIt.hasNext() ) {
			resultCoordinates.add( thisCoordinatesIt.next().inverse() );
		}
		return this.createInstance( resultCoordinates );
	}

	@Override
	public BigVector< T > negate() {
		final ArrayList< T > resultCoordinates = new ArrayList< >( this.getDimension() );
		final Iterator< T > thisCoordinatesIt = this.coordinates.iterator();
		while ( thisCoordinatesIt.hasNext() ) {
			resultCoordinates.add( thisCoordinatesIt.next().negate() );
		}
		return this.createInstance( resultCoordinates );
	}

	public boolean isSameDimensionAs( BigVector< T > other ) {
		return this.coordinates.size() == other.coordinates.size();
	}

	private void checkDimensions( BigVector< T > other ) {
		if ( !this.isSameDimensionAs( other ) ) {
			throw new DifferentDimensionsException();
		}
	}

}
