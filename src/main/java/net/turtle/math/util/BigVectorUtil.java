package net.turtle.math.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.turtle.math.core.BigComplex;
import net.turtle.math.core.BigRational;
import net.turtle.math.core.BigRationalValues;
import net.turtle.math.core.BigRationalVector;
import net.turtle.math.exception.ParsingException;

public class BigVectorUtil {
	
	private BigVectorUtil() {
		
	}
	
	public static List< BigRational > parseBigRationalVector( String vectorInput ) {
		final List< BigRational > coordinates;
		final String vector = vectorInput.trim();
		if ( vector.startsWith( "[" ) && vector.endsWith( "]" ) ) {
			final String vectorValues = vector.substring( 1, vector.length() - 1 );
			coordinates = parseComaSeparatedBigRationalValues( vectorValues );
		} else {
			throw new ParsingException( "Vector string does not start with '[' or does not ends with ']'" );
		}
		return coordinates;
	}
	
	public static List< BigRational > parseComaSeparatedBigRationalValues( final String vectorValues ) {
		final String valuesTrimmed = vectorValues.trim();
		final List< BigRational > coordinates;
		if ( !valuesTrimmed.isEmpty() ) {
			final String[] values = valuesTrimmed.split( "," );
			coordinates = parseArrayBigRationalValues( values );
		} else {
			coordinates = new ArrayList<>( 0 );
		}
		return coordinates;
	}
	
	public static List< BigRational > parseArrayBigRationalValues( final String[] values ) {
		final List< BigRational > coordinatesTemp = new ArrayList<>( values.length );
		for ( final String value : values ) {
			final BigRational valueNumber = new BigRational( value.trim() );
			coordinatesTemp.add( valueNumber );
		}
		return coordinatesTemp;
	}
	
	public static List< BigComplex > parseBigComplexVector( String vectorInput ) {
		final List< BigComplex > coordinates;
		final String vector = vectorInput.trim();
		if ( vector.startsWith( "[" ) && vector.endsWith( "]" ) ) {
			final String vectorValues = vector.substring( 1, vector.length() - 1 );
			coordinates = parseComaSeparatedBigComplexValues( vectorValues );
		} else {
			throw new ParsingException( "Vector string does not start with '[' or does not ends with ']'" );
		}
		return coordinates;
	}
	
	public static List< BigComplex > parseComaSeparatedBigComplexValues( final String vectorValues ) {
		final List< BigComplex > coordinates;
		final String valuesTrimmed = vectorValues.trim();
		if ( !valuesTrimmed.isEmpty() ) {
			final String[] values = valuesTrimmed.split( "," );
			coordinates = parseArrayBigComplexValues( values );
		} else {
			coordinates = new ArrayList<>( 0 );
		}
		return coordinates;
	}
	
	public static List< BigComplex > parseArrayBigComplexValues( final String[] values ) {
		final List< BigComplex > coordinatesTemp = new ArrayList<>( values.length );
		for ( final String value : values ) {
			final BigComplex valueNumber = new BigComplex( value );
			coordinatesTemp.add( valueNumber );
		}
		return coordinatesTemp;
	}
	
	public static BigRationalVector createZeroVector( int dimention ) {
		return create( dimention, BigRationalValues.ZERO );
	}
	
	public static BigRationalVector create( int dimention, BigRational coordinate ) {
		final List< BigRational > coordinates = new ArrayList<>( dimention );
		Collections.fill( coordinates, coordinate );
		return new BigRationalVector( coordinates );
	}
	
}
