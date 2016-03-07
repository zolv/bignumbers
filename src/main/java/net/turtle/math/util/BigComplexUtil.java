package net.turtle.math.util;

import java.math.BigInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.turtle.math.core.BigComplex;
import net.turtle.math.core.BigRational;

public class BigComplexUtil {
	
	private BigComplexUtil() {
		super();
	}

	public static final BigRational MINUS_ONE = BigRational.ONE.negate();
	
	public static String toStringShort( BigComplex complex ) {
		final StringBuilder result = new StringBuilder();
		appendNonZeroA( result, complex.getA() );
		appendNonZeroB( result, complex.getB() );
		if(result.length() <= 0) {
			result.append( "0" );
		}
		return result.toString();
	}
	
	private static void appendNonZeroA( final StringBuilder result, final BigRational a ) {
		if ( !a.equals( BigRational.ZERO ) ) {
			result.append( BigRationalUtil.toStringNormalized( a.normalizeSignum() ) );
		}
	}
	
	private static void appendNonZeroB( final StringBuilder result, final BigRational b ) {
		if ( !b.equals( BigRational.ZERO ) ) {
			if ( !b.equals( BigRational.ONE ) ) {
				if ( !b.equals( MINUS_ONE ) ) {
					if(result.length() > 0 && b.signum() > 0) {
						result.append( "+" );
					}
					result.append( BigRationalUtil.toStringNormalized( b.normalizeSignum() ) ).append( "i" );
				} else {
					result.append( "-i" );
				}
			} else {
				result.append( "i" );
			}
		}
	}
	
	public static BigRational getReal( String text ) {
		final String realString;
		if ( text.endsWith( "i" ) ) {
			
			final String realPattern = "^([-+]?([0-9]+|[0-9]+\\.[0-9]+|[0-9]+\\/[0-9]+))[+-]";
			
			final Pattern matrixPattern = Pattern.compile( realPattern );
			final Matcher matrixMatcher = matrixPattern.matcher( text );
			if ( matrixMatcher.find() ) {
			realString = matrixMatcher.group( 1 );
			} else {
			realString = "0";
			}
		} else {
			realString = text;
		}
		final BigRational real = new BigRational( realString );
		return real;
	}
	
	public static BigRational getImaginary( String text ) {
		
		final String imaginaryString;
		if ( text.endsWith( "i" ) ) {
			if ( !text.equals( "i" ) ) {
			if ( !text.equals( "-i" ) ) {
				
				final String realPattern = "([-+]?([0-9]+|[0-9]+\\.[0-9]+|[0-9]+\\/[0-9]+|))i";
				// final String realPattern = "([-+]?[0-9\\.\\/]+)i$";
				
				final Pattern matrixPattern = Pattern.compile( realPattern );
				final Matcher matrixMatcher = matrixPattern.matcher( text );
				if ( matrixMatcher.find() ) {
					imaginaryString = matrixMatcher.group( 1 );
				} else {
					imaginaryString = "0";
				}
			} else {
				imaginaryString = "-1";
			}
			} else {
			imaginaryString = "1";
			}
		} else {
			imaginaryString = "0";
		}
		final BigRational real = new BigRational( imaginaryString );
		return real;
		
	}
	
}
