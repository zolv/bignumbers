package net.turtle.math.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.NotImplementedException;

import net.turtle.math.exception.CalculationException;
import net.turtle.math.exception.DifferentDimensionsException;

public abstract class BigFieldElementVector< F extends BigFieldElement< F >, V extends BigFieldElementVector< F, V > > implements BigFieldElement< V >, BigVector< F, V > {
	
	
	protected final List< F > coordinates;
	
	public BigFieldElementVector() {
		this.coordinates = Collections.emptyList();
	}
	
	public BigFieldElementVector( List< F > input ) {
		this( input, false );
	}
	
	protected BigFieldElementVector( List< F > input, boolean trusted ) {
		if ( trusted ) {
			this.coordinates = input;
		} else {
			this.coordinates = new ArrayList<>( input );
		}
	}
	
	protected abstract F createZeroFieldElement();
	
	protected abstract V createInstance( final List< F > coordinatesSum );
	
	@Override
	public List< F > getCoordinates() {
		return Collections.unmodifiableList( this.coordinates );
	}
	
	@Override
	public F getCoordinate( int index ) {
		return this.coordinates.get( index );
	}
	
	@Override
	public V add( V augend ) throws CalculationException {
		this.checkDimensions( augend );
		final ArrayList< F > coordinatesSum = new ArrayList<>( this.getDimension() );
		final Iterator< F > thisCoordinatesIt = this.coordinates.iterator();
		final Iterator< F > augendCoordinatesIt = augend.getCoordinates().iterator();
		while ( thisCoordinatesIt.hasNext() && augendCoordinatesIt.hasNext() ) {
			coordinatesSum.add( thisCoordinatesIt.next().add( augendCoordinatesIt.next() ) );
		}
		assert ( !( thisCoordinatesIt.hasNext() || augendCoordinatesIt.hasNext() ) );
		return this.createInstance( coordinatesSum );
	}
	
	@Override
	public int getDimension() {
		return this.coordinates.size();
	}
	
	@Override
	public V subtract( V subtrahend ) throws CalculationException {
		this.checkDimensions( subtrahend );
		final ArrayList< F > coordinatesSum = new ArrayList<>( this.getDimension() );
		final Iterator< F > thisCoordinatesIt = this.coordinates.iterator();
		final Iterator< F > subtrahendCoordinatesIt = subtrahend.getCoordinates().iterator();
		while ( thisCoordinatesIt.hasNext() && subtrahendCoordinatesIt.hasNext() ) {
			coordinatesSum.add( thisCoordinatesIt.next().subtract( subtrahendCoordinatesIt.next() ) );
		}
		assert ( !( thisCoordinatesIt.hasNext() || subtrahendCoordinatesIt.hasNext() ) );
		return this.createInstance( coordinatesSum );
	}
	
	@Override
	public V multiply( V multiplicand ) throws CalculationException {
		throw new NotImplementedException("Unknown value");
	}
	
	@Override
	public F dotProduct( V multiplicand ) throws CalculationException {
		this.checkDimensions( multiplicand );
		F coordinatesSum = this.createZeroFieldElement();
		final Iterator< F > thisCoordinatesIt = this.coordinates.iterator();
		final Iterator< F > subtrahendCoordinatesIt = multiplicand.getCoordinates().iterator();
		while ( thisCoordinatesIt.hasNext() && subtrahendCoordinatesIt.hasNext() ) {
			coordinatesSum = coordinatesSum.add( thisCoordinatesIt.next().multiply( subtrahendCoordinatesIt.next() ) );
		}
		assert ( !( thisCoordinatesIt.hasNext() || subtrahendCoordinatesIt.hasNext() ) );
		return coordinatesSum;
	}
	
	@Override
	public V multiply( F multiplicand ) {
		final ArrayList< F > coordinatesSum = new ArrayList<>( this.getDimension() );
		final Iterator< F > thisCoordinatesIt = this.coordinates.iterator();
		while ( thisCoordinatesIt.hasNext() ) {
			coordinatesSum.add( thisCoordinatesIt.next().multiply( multiplicand ) );
		}
		return this.createInstance( coordinatesSum );
	}
	
	@Override
	public V divide( V divisor ) {
		return this.multiply( divisor.inverse() );
	}
	
	@Override
	public V divide( F divisor ) {
		return this.multiply( divisor.inverse() );
	}
	
	/**
	 * There is no official way to inversing vector. This method just uses
	 * inversion of every coordinate. Not so usefull but...
	 */
	@Override
	public V inverse() {
		final ArrayList< F > resultCoordinates = new ArrayList<>( this.getDimension() );
		final Iterator< F > thisCoordinatesIt = this.coordinates.iterator();
		while ( thisCoordinatesIt.hasNext() ) {
			resultCoordinates.add( thisCoordinatesIt.next().inverse() );
		}
		return this.createInstance( resultCoordinates );
	}
	
	@Override
	public V negate() {
		final ArrayList< F > resultCoordinates = new ArrayList<>( this.getDimension() );
		final Iterator< F > thisCoordinatesIt = this.coordinates.iterator();
		while ( thisCoordinatesIt.hasNext() ) {
			resultCoordinates.add( thisCoordinatesIt.next().negate() );
		}
		return this.createInstance( resultCoordinates );
	}
	
	protected boolean isSameDimensionAs( V other ) {
		return this.coordinates.size() == other.getCoordinates().size();
	}
	
	protected void checkDimensions( V other ) {
		if ( !this.isSameDimensionAs( other ) ) {
			throw new DifferentDimensionsException();
		}
	}
	
	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append( this.coordinates );
		return builder.toString();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = ( prime * result ) + this.coordinates.hashCode();
		return result;
	}
	
	@Override
	public boolean equals( Object obj ) {
		final boolean result;
		if ( this == obj ) {
			result = true;
		} else {
			if ( obj == null ) {
				result = false;
			} else {
				if ( obj instanceof BigFieldElementVector ) {
					final BigFieldElementVector< F, V > other = (BigFieldElementVector< F, V >)obj;
					result = this.coordinates.equals( other.coordinates );
				} else {
					result = false;
				}
			}
		}
		return result;
	}
	
}
