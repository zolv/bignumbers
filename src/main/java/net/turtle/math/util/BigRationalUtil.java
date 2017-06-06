package net.turtle.math.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

import net.turtle.math.core.BigRational;
import net.turtle.math.core.BigRationalValues;
import net.turtle.math.exception.CalculationException;

public class BigRationalUtil {
	
	private static final String UNIT_DENOMINATOR_STRING = "1";
	private static final String ZERO_NUMERATOR_STRING = "0";
	private static final char ZERO_CHAR = '0';
	private static final char ONE_CHAR = '1';
	
	private BigRationalUtil() {
		super();
	}
	
	public static BigDecimal asBigDecimal( BigRational rational, int scale ) {
		return new BigDecimal( rational.getNumerator() ).divide( new BigDecimal( rational.getDenominator() ), scale, RoundingMode.HALF_UP );
	}
	
	public static BigInteger asBigInteger( BigRational rational ) {
		return rational.getNumerator().divide( rational.getDenominator() );
	}
	
	public static String toStringNormalized( BigRational number ) {
		final BigRational signumNormalized = number.normalizeSignum();
		final StringBuilder result = new StringBuilder();
		result.append( signumNormalized.getNumerator() );
		if ( !signumNormalized.getDenominator().equals( BigInteger.ONE ) ) {
			result.append( "/" ).append( signumNormalized.getDenominator().toString() );
		}
		return result.toString();
	}
	
	public static String getNumerator( String value ) {
		final String n;
		final int slashIndex = value.indexOf( "/" );
		if ( slashIndex > 0 ) {
			/*
			 * 123/456
			 */
			n = value.substring( 0, slashIndex );
		} else {
			/*
			 * 123.456
			 */
			final int dotIndex = value.indexOf( "." );
			if ( dotIndex > 0 ) {
				final String dString = value.substring( dotIndex + 1 );
				n = value.substring( 0, dotIndex ) + dString;
			} else {
				/*
				 * 123456
				 */
				n = value.isEmpty() ? ZERO_NUMERATOR_STRING : value;
			}
		}
		return n;
	}
	
	public static String getDenominator( String value ) {
		final String d;
		final int slashIndex = value.indexOf( "/" );
		if ( slashIndex > 0 ) {
			/*
			 * 123/456
			 */
			d = value.substring( slashIndex + 1 );
		} else {
			/*
			 * 123.456
			 */
			final int dotIndex = value.indexOf( "." );
			if ( dotIndex > 0 ) {
				final String dString = value.substring( dotIndex + 1 );
				d = bigTenToTheString( dString.length() );
			} else {
				/*
				 * 123456
				 */
				d = UNIT_DENOMINATOR_STRING;
			}
		}
		return d;
	}
	
	public static BigInteger bigTenToThe( int n ) {
		final BigInteger result;
		if ( n < 0 ) {
			result = BigInteger.ZERO;
		} else {
			result = new BigInteger( BigRationalUtil.bigTenToTheString( n ) );
		}
		return result;
	}
	
	public static String bigTenToTheString( int n ) {
		final char tenpow[] = new char[ n + 1 ];
		tenpow[ 0 ] = ONE_CHAR;
		for ( int i = 1 ; i <= n ; i++ ) {
			tenpow[ i ] = ZERO_CHAR;
		}
		return new String( tenpow );
	}
	
	public static BigRational factorial( BigRational n ) {
		final BigRational result;
		if ( n.compareTo( BigRationalValues.ZERO ) >= 0 ) {
			BigRational tempResult = BigRationalValues.ONE;
			for ( BigRational i = n ; i.compareTo( BigRationalValues.ONE ) >= 0 ; i = i.subtract( BigRationalValues.ONE ) ) {
				tempResult = tempResult.multiply( i );
			}
			result = tempResult;
		} else {
			throw new CalculationException( "Factorial parameter is less than 0" );
		}
		return result;
	}
}
