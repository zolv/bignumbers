package net.turtle.math.core;

import java.math.BigInteger;

import org.junit.Test;

import net.turtle.math.util.BigRationalUtil;

public class BigRationalBenchmarkTest {
	
	
	/**
	 * Note: Converges very slowly.
	 */
	@Test
	public void testPiCalculation() {
		final int iterations = 10000;
		
		BigRational step = BigRationalValues.ONE;
		BigRational an = BigRationalValues.ZERO;
		for ( int n = 1 ; n <= iterations ; n++ ) {
			if ( ( n % 2 ) == 1 ) {
				an = an.add( step.inverse() );
			} else {
				an = an.subtract( step.inverse() );
			}
			step = step.add( BigRationalValues.TWO );
		}
		System.out.println( "pi ~ " + BigRationalUtil.asBigDecimal( an.multiply( BigRationalValues.FOUR ).normalize(), 1000 ) );
		
	}
	
	@Test
	public void testPiCalculationSpigot() {
		final BigInteger iterations = new BigInteger( "100" );
		
		BigRational an = BigRationalValues.ZERO;
		final BigRational _16 = new BigRational( "16" );
		final BigInteger _8 = new BigInteger( "8" );
		final BigInteger _6 = new BigInteger( "6" );
		final BigInteger _5 = new BigInteger( "5" );
		final BigInteger _4 = new BigInteger( "4" );
		final BigInteger _2 = new BigInteger( "2" );
		for ( BigInteger k = BigInteger.ZERO ; k.compareTo( iterations ) < 0 ; k = k.add( BigInteger.ONE ) ) {
			final BigRational _16Power = _16.pow( k );
			final BigRational bracket1 = new BigRational( _4, _8.multiply( k ).add( BigInteger.ONE ) );
			final BigRational bracket2 = new BigRational( _2, _8.multiply( k ).add( _4 ) );
			final BigRational bracket3 = new BigRational( BigInteger.ONE, _8.multiply( k ).add( _5 ) );
			final BigRational bracket4 = new BigRational( BigInteger.ONE, _8.multiply( k ).add( _6 ) );
			final BigRational bracket = bracket1.subtract( bracket2 ).subtract( bracket3 ).subtract( bracket4 );
			an = an.add( ( _16Power ).inverse().multiply( bracket ) );
		}
		System.out.println( "pi ~ " + BigRationalUtil.asBigDecimal( an.normalize(), 1000 ) );
	}
	
	@Test
	public void testSquareRoot2Calculation() {
		final int iterations = 10;
		
		BigRational an = BigRationalValues.ONE;
		for ( int n = 1 ; n <= iterations ; n++ ) {
			an = an.divide( BigRationalValues.TWO ).add( an.inverse() );
		}
		System.out.println( "sqrt(2) ~ " + BigRationalUtil.asBigDecimal( an, 1000 ) );
		
	}
	
}
