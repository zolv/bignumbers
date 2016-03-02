package net.turtle.math.util;

import java.util.ArrayList;
import java.util.List;

import net.turtle.math.core.BigComplex;
import net.turtle.math.core.BigRational;
import net.turtle.math.exception.ParsingException;

public class BigVectorUtil {
	
	
	private BigVectorUtil() {
		
	}
	
	public static List< BigRational > parseBigRationalVector( String vector ) {
		final List< BigRational > coordinates;
		if ( vector.startsWith( "[" ) && vector.endsWith( "]" ) ) {
			final String vectorValues = vector.substring( 1, vector.length() - 1 ).trim();
			
			if ( !vectorValues.isEmpty() ) {
				final String[] values = vectorValues.split( "," );
				final List< BigRational > coordinatesTemp = new ArrayList<>( values.length );
				for ( final String value : values ) {
					final BigRational valueNumber = new BigRational( value );
					coordinatesTemp.add( valueNumber );
				}
				coordinates = coordinatesTemp;
			} else {
				coordinates = new ArrayList<>( 0 );
			}
			
		} else {
			throw new ParsingException( "Vector string does not start with '[' or does not ends with ']'" );
		}
		return coordinates;
	}
	
	public static List< BigComplex > parseBigComplexVector( String vector ) {
		final List< BigComplex > coordinates;
		if ( vector.startsWith( "[" ) && vector.endsWith( "]" ) ) {
			final String vectorValues = vector.substring( 1, vector.length() - 1 ).trim();
			
			if ( !vectorValues.isEmpty() ) {
				final String[] values = vectorValues.split( "," );
				final List< BigComplex > coordinatesTemp = new ArrayList<>( values.length );
				for ( final String value : values ) {
					final BigComplex valueNumber = new BigComplex( value );
					coordinatesTemp.add( valueNumber );
				}
				coordinates = coordinatesTemp;
			} else {
				coordinates = new ArrayList<>( 0 );
			}
			
		} else {
			throw new ParsingException( "Vector string does not start with '[' or does not ends with ']'" );
		}
		return coordinates;
	}
	
}
