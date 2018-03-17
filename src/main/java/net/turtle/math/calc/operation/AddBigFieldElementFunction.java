package net.turtle.math.calc.operation;

import net.turtle.math.core.BigFieldElement;

public class AddBigFieldElementFunction< T extends BigFieldElement< T > > extends BigIterativeFunction< T > {
	
	@Override
	protected T evaluate( T a, T b ) {
		return a.add( b );
	}
	
}
