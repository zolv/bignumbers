package net.turtle.math;

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

}
