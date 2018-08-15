package net.turtle.math.calc.operation;

import java.util.Iterator;
import java.util.List;

import net.turtle.math.core.BigFieldElement;

public abstract class BigIterativeFunction< T extends BigFieldElement< T > > extends CommonFunction< T, T > {
	
	public BigIterativeFunction() {
		super();
	}

	@Override
	public T apply( List< T > parameters ) {
		T result;
		if(!parameters.isEmpty()) {
			final Iterator<T> parametersIt = parameters.iterator();
			result = parametersIt.next();
			while(parametersIt.hasNext()) {
				T a = parametersIt.next();
				result = evaluate( result, a );
			}
		} else {
			result = getDefaultValue();
		}
		return result;
	}

	protected abstract T evaluate( T a, T b );
	
}
