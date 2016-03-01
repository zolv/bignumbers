package net.turtle.math.core;

import java.util.List;

import net.turtle.math.exception.CalculationException;

public interface BigVector< T extends FieldElement< T > > {
	
	
	BigVector< T > add( BigVector< T > augend ) throws CalculationException;

	BigVector< T > negate();

	BigVector< T > inverse();

	BigVector< T > divide( T divisor );

	BigVector< T > divide( BigVector< T > divisor );

	BigVector< T > multiply( T multiplicand );

	BigVector< T > multiply( BigVector< T > multiplicand ) throws CalculationException;

	BigVector< T > subtract( BigVector< T > subtrahend ) throws CalculationException;

	int getDimension();

	List< T > getCoordinates();
	
}
