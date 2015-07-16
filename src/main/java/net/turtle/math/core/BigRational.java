package net.turtle.math.core;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import net.turtle.math.context.BigMathContext;
import net.turtle.math.util.BigRationalUtil;

public class BigRational implements Comparable<BigRational> {

	public static final BigRational ZERO = new BigRational(BigInteger.ZERO);

	public static final BigRational ONE = new BigRational(BigInteger.ONE);

	private final BigInteger numerator;

	private final BigInteger denominator;

	public BigRational() {
		this(BigInteger.ZERO, BigInteger.ONE);
	}

	public BigRational(BigDecimal bigDecimalValue) {
		this(
				bigDecimalValue.scale() >= 0 ? bigDecimalValue.unscaledValue()
						: bigDecimalValue.unscaledValue().multiply(BigRationalUtil.bigTenToThe(-bigDecimalValue.scale())),
				bigDecimalValue.scale() < 0 ? BigInteger.ONE : BigRationalUtil.bigTenToThe(bigDecimalValue.scale()));
	}

	public BigRational(BigInteger bigIntegerValue) throws ArithmeticException, NullPointerException {
		this(bigIntegerValue, BigInteger.ONE);
	}

	public BigRational(String value) throws ArithmeticException, NullPointerException {
		this(BigRationalUtil.getNumerator( value ), BigRationalUtil.getDenominator( value ));
	}

	public BigRational(String numerator, String denominator) throws ArithmeticException, NullPointerException {
		this(new BigInteger(numerator), new BigInteger(denominator));
	}

	public BigRational(BigInteger numerator, BigInteger denominator) throws ArithmeticException, NullPointerException {
		this(numerator, denominator, BigMathContext.get().getNormalizeResult());
	}

	public BigRational(BigInteger numerator, BigInteger denominator, boolean normalize) throws ArithmeticException, NullPointerException {
		if(numerator != null) {
			if(denominator != null) {
				if(!denominator.equals(BigInteger.ZERO)) {
					if(normalize) {
						final BigRational toNormalize = new BigRational( numerator , denominator , false ).normalize();
						this.numerator = toNormalize.getNumerator();
						this.denominator = toNormalize.getDenominator();
					} else {
						this.numerator = numerator;
						this.denominator = denominator;
					}
				} else {
					throw new ArithmeticException("Division by zero");
				}
			} else {
				throw new NullPointerException("Denominator cannot be null.");
			}
		} else {
			throw new NullPointerException("Numerator cannot be null.");
		}
	}

	public BigInteger getNumerator() {
		return this.numerator;
	}

	public BigInteger getDividend() {
		return this.numerator;
	}

	public BigInteger getDenominator() {
		return this.denominator;
	}

	public BigInteger getDivisor() {
		return this.denominator;
	}

	/**
	 * Normalizes fraction by normalizing signum leaving sign only in numerator
	 * (denominator is positive) e.g.: 2/-3 becomes -2/3 -2/-3 becomes 2/3 Then
	 * fraction is cancelled e.g.: -4/6 becomes -2/3 11/11 becomes 1/1 0/123
	 * (any representation of 0) becomes 0/1
	 *
	 * @return Normalized fraction e.g. 4/-6 becomes -2/3
	 */
	public BigRational normalize() {
		return this.cancel().normalizeSignum();
	}

	public BigRational normalizeSignum() {
		return this.denominator.signum() >= 0 ? this : new BigRational(this.numerator.negate(), this.denominator.negate());
	}

	public BigRational cancel() {
		final BigRational result;
		if ( !this.denominator.equals( BigInteger.ONE ) ) {
			final BigInteger gcd = this.numerator.gcd( this.denominator );
			if ( !gcd.equals( BigInteger.ONE ) ) {
				result = new BigRational( this.numerator.divide( gcd ) , this.denominator.divide( gcd ) );
			} else {
				result = this;
			}
		} else {
			result = this;
		}
		return result;
	}

	public BigRational add(BigRational augend) {
		return prepareResult(new BigRational(this.numerator.multiply(augend.denominator).add(augend.numerator.multiply(this.denominator)),
				this.denominator.multiply(augend.denominator)));
	}

	public BigRational subtract(BigRational subtrahend) throws NullPointerException {
		return prepareResult(
				new BigRational(this.numerator.multiply(subtrahend.denominator).subtract(subtrahend.numerator.multiply(this.denominator)),
						this.denominator.multiply(subtrahend.denominator)));
	}

	public BigRational multiply(final BigRational multiplicand) {
		if(multiplicand == null) {
			throw new NullPointerException("Multiplicand cannot be null");
		}

		final FutureTask<BigInteger> numeratorComputation = new FutureTask<BigInteger>(new Callable<BigInteger>() {

			@Override
			public BigInteger call() throws Exception {
				return BigRational.this.numerator.multiply(multiplicand.numerator);
			}
		});

		BigMathContext.get().submit(numeratorComputation);

		/*
		 * Reuse current thread for calculation.
		 */
		final BigInteger denominatorComputed = this.denominator.multiply(multiplicand.denominator);

		try {

			return prepareResult(new BigRational(numeratorComputation.get(), denominatorComputed));

		} catch(ArithmeticException | NullPointerException | InterruptedException | ExecutionException e) {
			throw new RuntimeException(e);
		}
	}

	public BigRational divide(final BigRational divisor)
			throws ArithmeticException, NullPointerException, InterruptedException, ExecutionException {
		final FutureTask<BigInteger> numeratorComputation = new FutureTask<BigInteger>(new Callable<BigInteger>() {

			@Override
			public BigInteger call() throws Exception {
				return BigRational.this.numerator.multiply(divisor.denominator);
			}
		});

		BigMathContext.get().submit(numeratorComputation);
		final BigInteger denominatorComputation = this.denominator.multiply(divisor.numerator);
		return prepareResult(new BigRational(numeratorComputation.get(), denominatorComputation));
	}

	public BigRational pow(BigInteger power) throws NullPointerException, ArithmeticException, InterruptedException, ExecutionException {
		final BigRational result;
		if(!power.equals(BigInteger.ZERO)) {
			final BigInteger powerAbs = power.abs();
			if(!powerAbs.equals(BigInteger.ONE)) {
				final BigInteger newNumerator;
				final BigInteger newDenominator;
				if(power.signum() >= 0) {
					newNumerator = this.numerator;
					newDenominator = this.denominator;
				} else {
					newNumerator = this.denominator;
					newDenominator = this.numerator;
				}
				final FutureTask<BigInteger> numeratorComputation = new FutureTask<BigInteger>(new Callable<BigInteger>() {

					@Override
					public BigInteger call() throws Exception {
						return BigRational.this.pow(newNumerator, powerAbs);
					}
				});

				BigMathContext.get().submit(numeratorComputation);
				final BigInteger denominatorComputation = this.pow(newDenominator, powerAbs);

				result = new BigRational(numeratorComputation.get(), denominatorComputation);
			} else {
				if(power.equals(BigInteger.ONE)) {
					result = this;
				} else {
					/*
					 * power == -1
					 */
					result = this.inverse();
				}
			}
		} else {
			result = BigRational.ONE;
		}
		return prepareResult(result);
	}

	private BigInteger pow(BigInteger value, BigInteger power) {
		BigInteger partialResult = BigInteger.ONE;
		final BigInteger two = BigInteger.valueOf(2L);
		BigInteger squaredValue = value;
		BigInteger powerIteration = power;
		while(powerIteration.compareTo(BigInteger.ONE) >= 0) {
			if(powerIteration.testBit(0)) {
				partialResult = partialResult.multiply(squaredValue);
			}
			powerIteration = powerIteration.divide(two);
			if(powerIteration.compareTo(BigInteger.ONE) >= 0) {
				squaredValue = squaredValue.multiply(squaredValue);
			}
		}

		return partialResult;
	}

	public BigRational abs() {
		return prepareResult(this.abs(this.signum()));
	}

	private BigRational abs(int signum) {
		return signum < 0 ? this.negate() : this;
	}

	/**
	 * Negates value. Includes silent signum normalization.
	 *
	 * @return
	 */
	public BigRational negate() {
		final BigRational result;
		/**
		 * "Silent" signum normalization.
		 */
		if(this.denominator.signum() < 0) {
			result = new BigRational(this.numerator, this.denominator.negate());
		} else {
			result = new BigRational(this.numerator.negate(), this.denominator);
		}
		return prepareResult(result);
	}

	/**
	 * Returns result of 1 / this = this.denominator / this.numerator. Always
	 * return new BigRational instance. Checking for this.numerator.equals(
	 * this.denominator ) is not worth from performance point of view.
	 *
	 * @return Inversed
	 */
	public BigRational inverse() {
		return prepareResult(new BigRational(this.denominator, this.numerator));
	}

	/**
	 *
	 * @return 1 = positive, -1 = negative, 0 = zero
	 * @throws ArithmeticException
	 */
	public int signum() throws ArithmeticException {
		final int result;
		if(this.numerator.signum() > 0) {
			if(this.denominator.signum() > 0) {
				result = 1;
			} else {
				if(this.denominator.signum() < 0) {
					result = -1;
				} else {
					throw new ArithmeticException("Division by zero");
				}
			}
		} else {
			if(this.numerator.signum() < 0) {
				if(this.denominator.signum() < 0) {
					result = 1;
				} else {
					if(this.denominator.signum() > 0) {
						result = -1;
					} else {
						throw new ArithmeticException("Division by zero");
					}
				}
			} else {
				if(this.denominator.signum() != 0) {
					result = 0;
				} else {
					throw new ArithmeticException("Division by zero");
				}
			}
		}
		return result;
	}

	private static BigRational prepareResult(BigRational rawResult) {
		if(BigMathContext.get().getNormalizeResult()) {
			return rawResult.normalize();
		} else {
			return rawResult;
		}
	}

	@Override
	public int compareTo(BigRational that) {
		final int result;
		final int thisSignum = this.signum();
		final int thatSignum = that.signum();
		if(thisSignum == thatSignum) {
			if(thisSignum != 0) {
				final BigRational thisNormalized = this.normalize();
				final BigRational thatNormalized = that.normalize();
				final int denominatorComparison = thisNormalized.denominator.compareTo(thatNormalized.denominator);
				if(denominatorComparison == 0) {
					result = thisNormalized.numerator.compareTo(thatNormalized.numerator);
				} else {
					final int numeratorComparison = thisNormalized.numerator.compareTo(thatNormalized.numerator);
					if((denominatorComparison > 0) && (numeratorComparison <= 0)) {
						/*
						 * E.g. 2/5 vs 3/4 => -1 2/5 vs 2/3 => -1
						 */
						result = -1;
					} else {
						if((denominatorComparison < 0) && (numeratorComparison >= 0)) {
							result = 1;
						} else {
							/*
							 * abs() of multiplication results are not needed
							 * due to normalization.
							 */
							final int absCompare = thisNormalized.numerator.multiply(thatNormalized.denominator)
									.compareTo(thatNormalized.numerator.multiply(thisNormalized.denominator));
							if(thisSignum > 0) {
								result = absCompare;
							} else {
								result = -absCompare;
							}
						}
					}
				}
			} else {
				result = 0;
			}
		} else {
			if(thisSignum < thatSignum) {
				result = -1;
			} else {
				result = 1;
			}
		}
		return result;
	}

	public BigRational min(BigRational val) {
		return (this.compareTo(val) <= 0 ? this : val);
	}

	public BigRational max(BigRational val) {
		return (this.compareTo(val) > 0 ? this : val);
	}

	@Override
	public boolean equals(Object obj) {
		final boolean result;
		if(this == obj) {
			result = true;
		} else {
			if(obj instanceof BigRational) {
				result = this.numerator.multiply(((BigRational) obj).denominator).equals(((BigRational) obj).numerator.multiply(this.denominator));
			} else {
				result = false;
			}
		}
		return result;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = prime + this.denominator.intValue();
		result = (prime * result) + this.numerator.intValue();
		return result;
	}

	@Override
	public String toString() {
		return new StringBuilder().append(this.numerator.toString()).append("/").append(this.denominator.toString()).toString();
	}

}
