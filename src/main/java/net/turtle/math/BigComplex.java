package net.turtle.math;

import java.math.BigInteger;


/**
 * Class representing complex number in the form:
 * Z = a + b * i
 * @see
 * @author Radosław Adamiak
 *
 */
public class BigComplex implements Comparable<BigComplex> {

	/**
	 * z = 0 = 0 + 0i
	 */
	public static final BigComplex ZERO = new BigComplex(BigRational.ZERO, BigRational.ZERO);

	/**
	 * z = 1 = 1 + 0i
	 */
	public static final BigComplex ONE = new BigComplex(BigRational.ONE, BigRational.ZERO);

	/**
	 * z = i = 0 + i
	 */
	public static final BigComplex I = new BigComplex(BigRational.ZERO, BigRational.ONE);

	private final BigRational a;

	private final BigRational b;

	public BigComplex(BigRational a) {
		this(a, BigRational.ZERO);
	}

	public BigComplex(BigRational a, BigRational b) {
		if(a!= null) {
			this.a = a;
		} else {
			throw new NullPointerException("a cannot be null");
		}
		if(b!= null) {
			this.b = b;
		} else {
			throw new NullPointerException("b cannot be null");
		}
	}

	public BigRational getA() {
		return this.a;
	}

	public BigRational getB() {
		return this.b;
	}

	public BigComplex reduce() {
		final BigComplex result;
		final BigRational aReduced = this.a.reduce();
		final BigRational bReduced = this.b.reduce();
		if( ( this.a == aReduced ) && ( this.b == bReduced )) {
			result = this;
		} else {
			result = new BigComplex(aReduced, bReduced);
		}
		return result;
	}

	public BigComplex reduce(BigInteger[] divisors) {
		BigComplex result = this;
		for( int i = divisors.length - 1 ; i >= 0 ; i--) {
			result = result.reduce(divisors[ i ]);
		}
		return result;
	}

	private BigComplex reduce(BigInteger divisor) {
		BigComplex result = this;
		BigRational aReduced;
		BigRational bReduced;
		while( ((aReduced = result.a.reduce(divisor)) != this.a) | ( (bReduced = result.b.reduce(divisor)) != this.b ) ) {
			result = new BigComplex(aReduced, bReduced);
		}
		return result;
	}

	public BigComplex add(BigComplex augend) {
		return new BigComplex( this.a.add( augend.a ) , this.b.add( augend.b ) );
	}

	public BigComplex subtract(BigComplex subtrahend) {
		return new BigComplex( this.a.subtract( subtrahend.a ) , this.b.subtract( subtrahend.b ) );
	}

	public BigComplex multiply(BigComplex multiplicand) {
		return new BigComplex( this.a.multiply( multiplicand.a ).subtract(
			this.b.multiply( multiplicand.b ) ) , this.b.multiply( multiplicand.a ).add(
			this.a.multiply( multiplicand.b ) ) );
	}

	public BigComplex divide(BigComplex divisor) {
		final BigRational denominator = divisor.a.multiply(divisor.a).add(divisor.b.multiply(divisor.b));
		return new BigComplex(
			this.a.multiply( divisor.a ).add( this.b.multiply( divisor.b ) ).divide( denominator ) ,
			this.b.multiply( divisor.a ).subtract( this.a.multiply( divisor.b ) ).divide( denominator ) );
	}

	public BigRational absSquared() {
		return this.a.multiply( this.a ).add( this.b.multiply( this.b ) );
	}

	public BigComplex negate() {
		return new BigComplex( this.a.negate() , this.b.negate() );
	}

	@Override
	public int compareTo(BigComplex val) {
		return this.a.multiply( val.b ).compareTo( val.a.multiply( this.b ) );
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
					result = this.a.equals( ( (BigComplex)obj ).a ) && this.b.equals( ( (BigComplex)obj ).b );
				}
			}
		}
		return result;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = ( prime * result ) + ( ( this.b == null ) ? 0 : this.b.hashCode() );
		result = ( prime * result ) + ( ( this.a == null ) ? 0 : this.a.hashCode() );
		return result;
	}

	@Override
	public String toString() {
		return new StringBuilder().append(this.a.toString()).append(this.b.signum() >= 0 ? "+" : "").append(this.b).append("i").toString();
	}

}
