package net.turtle.math;

import java.math.BigDecimal;
import java.math.BigInteger;

public class BigRational implements Comparable<BigRational> {

	public static final BigRational ZERO = new BigRational(BigInteger.ZERO);

	public static final BigRational ONE = new BigRational(BigInteger.ONE);

	private static final BigInteger BIGINTEGER_MINUS_ONE = BigInteger.valueOf( -1 ) ;

	private final BigInteger numerator;

	private final BigInteger denominator;

	public BigRational(BigDecimal bigDecimalValue) {
		this(bigDecimalValue.scale() >= 0 ? bigDecimalValue.unscaledValue() : bigDecimalValue.unscaledValue().multiply(bigTenToThe(-bigDecimalValue.scale())),
				 bigDecimalValue.scale() < 0 ? BigInteger.ONE : bigTenToThe(bigDecimalValue.scale()) ) ;
	}

	private static BigInteger bigTenToThe(int n) {
		if(n < 0) {
			return BigInteger.ZERO;
		} else {
			final char tenpow[] = new char[n + 1];
			tenpow[0] = '1';
			for (int i = 1; i <= n; i++) {
				tenpow[i] = '0';
			}
			return new BigInteger(new String(tenpow));
		}
	}

	public BigRational(BigInteger bigIntegerValue) {
		this(bigIntegerValue, BigInteger.ONE);
	}

	public BigRational(String value) {
		final BigInteger n;
		final BigInteger d;
		final int slashIndex = value.indexOf("/");
		if(slashIndex > 0) {
			/*
			 * 123/456
			 */
			final String nString = value.substring(0, slashIndex);
			final String dString = value.substring(slashIndex+1);
			n = new BigInteger(nString);
			d = new BigInteger(dString);
		} else {
			/*
			 * 123.456
			 */
			final int dotIndex = value.indexOf(".");
			if(dotIndex > 0) {
				final String nString = value.substring(0, dotIndex);
				final String dString = value.substring(dotIndex +1);
				n = new BigInteger(nString + dString);
				d = bigTenToThe(dString.length());
			} else {
				/*
				 * 123456
				 */
				n = new BigInteger(value);
				d = BigInteger.ONE;
			}
		}

		if(!d.equals(BigInteger.ZERO)) {
			this.numerator = n;
			this.denominator = d;
		} else {
			throw new ArithmeticException("Division by zero");
		}
	}

	public BigRational(String numerator, String denominator) {
		this(new BigInteger(numerator), new BigInteger(denominator));
	}

	public BigRational( BigInteger numerator , BigInteger denominator ) {
		if ( numerator != null ) {
			this.numerator = numerator;
		} else {
			throw new NullPointerException( "Numerator cannot be null." );
		}
		if ( denominator != null ) {
			if ( !denominator.equals( BigInteger.ZERO ) ) {
				this.denominator = denominator;
			} else {
				throw new ArithmeticException( "Division by zero" );
			}
		} else {
			throw new NullPointerException( "Denominator cannot be null." );
		}
	}

	public BigInteger getNumerator() {
		return this.numerator;
	}

	public BigInteger getDenominator() {
		return this.denominator;
	}

	public BigRational normalize() {
		return this.normalizeSignum().reduce();
	}

	public BigRational normalizeSignum() {
		return this.denominator.signum() > 0 ? this : new BigRational(
			this.numerator.negate() ,
			this.denominator.negate() );
	}

	public BigRational reduce() {
		return this.reduce(new BigInteger[] {
				BigInteger.valueOf(7),
				BigInteger.valueOf(5),
				BigInteger.valueOf(3),
				BigInteger.valueOf(2)
			});
	}

	public BigRational reduce(BigInteger[] divisors) {
		BigRational result = this;
		for( int i = divisors.length - 1 ; i >= 0 ; i--) {
			result = result.reduce(divisors[ i ]);
		}
		return result;
	}

	public BigRational reduce(BigInteger divisor) {
		final BigRational result;
		if( !divisor.equals( BigInteger.ONE ) && !divisor.equals( BIGINTEGER_MINUS_ONE)) {
			BigRational iterativeResult = this;
			while(iterativeResult.numerator.remainder(divisor).equals(BigInteger.ZERO) && iterativeResult.denominator.remainder(divisor).equals(BigInteger.ZERO)) {
				iterativeResult = new BigRational(iterativeResult.numerator.divide(divisor), iterativeResult.denominator.divide(divisor));
			}
			result = iterativeResult;
		} else {
			result = this;
		}
		return result;
	}

	public BigRational add(BigRational augend) {
		return new BigRational(
			this.numerator.multiply( augend.denominator ).add(augend.numerator.multiply( this.denominator ) ) ,
			this.denominator.multiply( augend.denominator ) );
	}

	public BigRational subtract(BigRational subtrahend) {
		return new BigRational(
			this.numerator.multiply( subtrahend.denominator ).subtract(subtrahend.numerator.multiply( this.denominator ) ) ,
			this.denominator.multiply( subtrahend.denominator ) );
	}

	public BigRational multiply(BigRational multiplicand) {
		return new BigRational(
			this.numerator.multiply( multiplicand.numerator ) ,
			this.denominator.multiply( multiplicand.denominator ) );
	}

	public BigRational divide(BigRational divisor) {
		return new BigRational(
			this.numerator.multiply( divisor.denominator ) ,
			this.denominator.multiply( divisor.numerator ) );
	}
	
	public BigRational pow(BigInteger power) {
		final BigRational result;
		final BigInteger absMultiplicand = power.abs();
		if( !absMultiplicand.equals( BigInteger.ZERO )) {
			if( !absMultiplicand.equals( BigInteger.ONE )) {
				BigRational multiplicant;
				if( power.signum() < 0 ) {
					multiplicant = BigRational.ONE.divide( this );
				} else {
					multiplicant = this;
				}
				BigRational partResult = multiplicant;
				for( BigInteger i = BigInteger.ONE ; i.compareTo( absMultiplicand ) < 0 ; i = i.add( BigInteger.ONE )) {
					partResult = partResult.multiply( multiplicant );
				}
				result = partResult;
			} else {
				result = this;
			}
		} else {
			result = BigRational.ONE;
		}
		return result;
	}

	public BigRational abs() {
		return this.signum() < 0 ? this.negate() : this;
	}

	public BigRational negate() {
		return new BigRational( this.numerator.negate() , this.denominator );
	}

	public int signum() {
		final int result;
		if ( this.numerator.signum() < 0 ) {
			if ( this.denominator.signum() < 0 ) {
				result = 1;
			} else {
				if ( this.denominator.signum() > 0 ) {
					result = -1;
				} else {
					throw new ArithmeticException("Division by zero");
				}
			}
		} else {
			if ( this.numerator.signum() > 0 ) {
				if ( this.denominator.signum() < 0 ) {
					result = -1;
				} else {
					if ( this.denominator.signum() > 0 ) {
						result = 1;
					} else {
						throw new ArithmeticException("Division by zero");
					}
				}
			} else {
				if ( this.denominator.signum() == 0 ) {
					throw new ArithmeticException("Division by zero");
				} else {
					result = 0;
				}
			}
		}
		return result;
	}

	@Override
	public int compareTo(BigRational val) {
		return this.numerator.multiply( val.denominator ).compareTo(
			val.numerator.multiply( this.denominator ) );
	}

	@Override
	public boolean equals(Object obj) {
		final boolean result;
		if(this == obj) {
			result = true;
		} else {
			if(obj == null) {
				result = false;
			} else {
				if(this.getClass() != obj.getClass()) {
					result = false;
				} else {
					result = this.numerator.multiply( ( (BigRational)obj ).denominator ).equals(
						( (BigRational)obj ).numerator.multiply( this.denominator ) );
				}
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = ( prime * result ) + ( ( this.denominator == null ) ? 0 : this.denominator.hashCode() );
		result = ( prime * result ) + ( ( this.numerator == null ) ? 0 : this.numerator.hashCode() );
		return result;
	}

	@Override
	public String toString() {
		return new StringBuilder().append(this.numerator.toString()).append("/").append(this.denominator.toString()).toString();
	}


}
