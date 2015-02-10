package net.turtle.math;

public class BigComplexUtil {

	public static BigComplex reduce(BigComplex bc) {
		final BigRational newA = BigRationalUtil.reduce(bc.getA());
		final BigRational newB = BigRationalUtil.reduce(bc.getB());

		final BigComplex result;
		if(bc.getA() == newA && bc.getB() == newB) {
			result = bc;
		} else {
			result = new BigComplex(newA, newB);
		}
		return result;
	}

	public static BigComplex reduce(BigComplex bc, int primesCount) {
		final BigRational newA = BigRationalUtil.reduce(bc.getA(), primesCount);
		final BigRational newB = BigRationalUtil.reduce(bc.getB(), primesCount);

		final BigComplex result;
		if(bc.getA() == newA && bc.getB() == newB) {
			result = bc;
		} else {
			result = new BigComplex(newA, newB);
		}
		return result;
	}

	public static String toStringNormalized(BigComplex complex) {
		final StringBuilder result = new StringBuilder();
		if(!complex.getA().equals(BigRational.ZERO)) {
			if(!complex.getB().equals(BigRational.ZERO)) {
				result.append(BigRationalUtil.toStringNormalized(complex.getA()));
				final BigRational bNormalized = complex.getB().normalizeSignum();
				if(bNormalized.signum() >= 0) {
					result.append("+");
				}
				result.append(BigRationalUtil.toStringNormalized(bNormalized)).append("i");
			}
		} else {
			if(!complex.getB().equals(BigRational.ZERO)) {
				result.append(BigRationalUtil.toStringNormalized(complex.getB().normalizeSignum())).append("i");
			} else {
				result.append("0");
			}
		}
		return result.toString();
	}

}
