package net.turtle.math.core;

import java.util.List;

import net.turtle.math.exception.CalculationException;

public interface BigVector< F extends FieldElement< F >, V extends BigVector< F, V > > {
	
	V add( V augend ) throws CalculationException;
	
	V negate();
	
	V inverse();
	
	V divide( F divisor );
	
	V divide( V divisor );
	
	V multiply( F multiplicand );
	
	V multiply( V multiplicand ) throws CalculationException;
	
	F dotProduct( V multiplicand );
	
	V subtract( V subtrahend ) throws CalculationException;
	
	int getDimension();
	
	List< F > getCoordinates();

	F getCoordinate( int index );
	
}
