package net.turtle.math.util;

import java.math.BigInteger;

import net.turtle.math.core.BigRational;

public class BigRationalUtil {

	private static final String UNIT_DENOMINATOR_STRING = "1";

	private BigRationalUtil() {
		super();
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

	public static String getNumerator(String value) {
		final String n;
		final int slashIndex = value.indexOf("/");
		if(slashIndex > 0) {
			/*
			 * 123/456
			 */
			n = value.substring(0, slashIndex);
		} else {
			/*
			 * 123.456
			 */
			final int dotIndex = value.indexOf(".");
			if(dotIndex > 0) {
				final String dString = value.substring(dotIndex + 1);
				n = value.substring(0, dotIndex) + dString;
			} else {
				/*
				 * 123456
				 */
				n = value;
			}
		}
		return n;
	}

	public static String getDenominator(String value) {
		final String d;
		final int slashIndex = value.indexOf("/");
		if(slashIndex > 0) {
			/*
			 * 123/456
			 */
			d = value.substring(slashIndex + 1);
		} else {
			/*
			 * 123.456
			 */
			final int dotIndex = value.indexOf(".");
			if(dotIndex > 0) {
				final String dString = value.substring(dotIndex + 1);
				d = bigTenToTheString(dString.length());
			} else {
				/*
				 * 123456
				 */
				d = UNIT_DENOMINATOR_STRING;
			}
		}
		return d;
	}

	public static BigInteger bigTenToThe(int n) {
		final BigInteger result;
		if(n < 0) {
			result = BigInteger.ZERO;
		} else {
			result = new BigInteger(BigRationalUtil.bigTenToTheString(n));
		}
		return result;
	}

	public static String bigTenToTheString(int n) {
		final char tenpow[] = new char[n + 1];
		tenpow[0] = '1';
		for (int i = 1; i <= n; i++) {
			tenpow[i] = '0';
		}
		return new String(tenpow);
	}
}
