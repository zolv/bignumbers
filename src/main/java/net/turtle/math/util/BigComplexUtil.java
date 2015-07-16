package net.turtle.math.util;

import net.turtle.math.core.BigComplex;
import net.turtle.math.core.BigRational;

public class BigComplexUtil {

	/**
	 * z = 0 = 0 + 0i
	 */
	public static final BigComplex ZERO = BigComplex.ZERO;

	/**
	 * z = 1 = 1 + 0i
	 */
	public static final BigComplex ONE = BigComplex.ONE;

	/**
	 * z = i = 0 + i
	 */
	public static final BigComplex I = BigComplex.I;

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

}
