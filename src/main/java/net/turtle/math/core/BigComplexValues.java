package net.turtle.math.core;

public class BigComplexValues {

  /** z = 0 = 0 + 0i */
  public static final BigComplex ZERO = BigComplex.ZERO;

  /** z = 1 = 1 + 0i */
  public static final BigComplex ONE = BigComplex.ONE;

  /** z = -i = -1 +0i */
  public static final BigComplex MINUS_ONE = ONE.negate();

  /** z = i = 0 + 1i */
  public static final BigComplex I = BigComplex.I;

  /** z = -i = 0 - i */
  public static final BigComplex MINUS_I = I.negate();

  private BigComplexValues() {
    super();
  }
}
