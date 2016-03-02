package net.turtle.math.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import net.turtle.math.exception.CalculationException;
import net.turtle.math.exception.DifferentDimensionsException;
import net.turtle.math.exception.NotImplementedException;

public abstract class BigFieldElementVector< T extends FieldElement< T >, V extends BigFieldElementVector< T, V > > implements FieldElement< V >, BigVector< T, V > {
	
	
	private final List< T > coordinates;
	
	public BigFieldElementVector() {
		this.coordinates = Collections.emptyList();
	}
	
	public BigFieldElementVector( List< T > input ) {
		this( input, false );
	}
	
	protected BigFieldElementVector( List< T > input, boolean trusted ) {
		if ( trusted ) {
			this.coordinates = input;
		} else {
			this.coordinates = new ArrayList<>( input );
		}
	}
	
	protected abstract V createInstance( final List< T > coordinatesSum );
	
	@Override
	public List< T > getCoordinates() {
		return Collections.unmodifiableList( this.coordinates );
	}
	
	@Override
	public V add( V augend ) throws CalculationException {
		this.checkDimensions( augend );
		final ArrayList< T > coordinatesSum = new ArrayList<>( this.getDimension() );
		final Iterator< T > thisCoordinatesIt = this.coordinates.iterator();
		final Iterator< T > augendCoordinatesIt = augend.getCoordinates().iterator();
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
		final ArrayList< T > coordinatesSum = new ArrayList<>( this.getDimension() );
		final Iterator< T > thisCoordinatesIt = this.coordinates.iterator();
		final Iterator< T > subtrahendCoordinatesIt = subtrahend.getCoordinates().iterator();
		while ( thisCoordinatesIt.hasNext() && subtrahendCoordinatesIt.hasNext() ) {
			coordinatesSum.add( thisCoordinatesIt.next().subtract( subtrahendCoordinatesIt.next() ) );
		}
		assert ( !( thisCoordinatesIt.hasNext() || subtrahendCoordinatesIt.hasNext() ) );
		return this.createInstance( coordinatesSum );
	}
	
	@Override
	public V multiply( V multiplicand ) throws CalculationException {
		throw new NotImplementedException();
	}
	
	@Override
	public V multiply( T multiplicand ) {
		final ArrayList< T > coordinatesSum = new ArrayList<>( this.getDimension() );
		final Iterator< T > thisCoordinatesIt = this.coordinates.iterator();
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
	public V divide( T divisor ) {
		return this.multiply( divisor.inverse() );
	}
	
	/**
	 * There is no official way to inversing vector. This method just uses
	 * inversion of every coordinate. Not so usefull but...
	 */
	@Override
	public V inverse() {
		final ArrayList< T > resultCoordinates = new ArrayList<>( this.getDimension() );
		final Iterator< T > thisCoordinatesIt = this.coordinates.iterator();
		while ( thisCoordinatesIt.hasNext() ) {
			resultCoordinates.add( thisCoordinatesIt.next().inverse() );
		}
		return this.createInstance( resultCoordinates );
	}
	
	@Override
	public V negate() {
		final ArrayList< T > resultCoordinates = new ArrayList<>( this.getDimension() );
		final Iterator< T > thisCoordinatesIt = this.coordinates.iterator();
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
	
}
