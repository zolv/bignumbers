package net.turtle.math.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.turtle.math.core.BigComplex;
import net.turtle.math.core.BigRational;

public class BigComplexUtil {
	
	
	private BigComplexUtil() {
		super();
	}
	
	public static String toStringNormalized( BigComplex complex ) {
		final StringBuilder result = new StringBuilder();
		if ( !complex.getA().equals( BigRational.ZERO ) ) {
			if ( !complex.getB().equals( BigRational.ZERO ) ) {
				result.append( BigRationalUtil.toStringNormalized( complex.getA() ) );
				final BigRational bNormalized = complex.getB().normalizeSignum();
				if ( bNormalized.signum() >= 0 ) {
					result.append( "+" );
				}
				result.append( BigRationalUtil.toStringNormalized( bNormalized ) ).append( "i" );
			}
		} else {
			if ( !complex.getB().equals( BigRational.ZERO ) ) {
				result.append( BigRationalUtil.toStringNormalized( complex.getB().normalizeSignum() ) ).append( "i" );
			} else {
				result.append( "0" );
			}
		}
		return result.toString();
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
