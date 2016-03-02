package net.turtle.math.core;

import java.util.List;

import net.turtle.math.exception.CalculationException;

public interface BigVector< T extends FieldElement< T > , V extends BigVector<T,V> > {
	
	
	V add(V augend ) throws CalculationException;

	V negate();

	V inverse();

	V divide( T divisor );

	V divide( V divisor );

	V multiply( T multiplicand );

	V multiply( V multiplicand ) throws CalculationException;

	V subtract( V subtrahend ) throws CalculationException;

	int getDimension();

	List< T > getCoordinates();
	
}
