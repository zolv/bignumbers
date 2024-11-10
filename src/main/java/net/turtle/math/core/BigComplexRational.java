/* (C)2024 */
package net.turtle.math.core;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import net.turtle.math.context.BigMathContext;
import net.turtle.math.exception.CalculationException;
import net.turtle.math.util.BigComplexUtil;

/**
 * Class representing complex number in the form: Z = a + b * i
 *
 * @see
 * @author Radosław Adamiak
 */
public class BigComplexRational extends BigComplex<BigRational> {

  /** z = 0 = 0 + 0i */
  @Valid
  public static final BigComplexRational ZERO =
      new BigComplexRational(BigRational.ZERO, BigRational.ZERO);

  /** z = 1 = 1 + 0i */
  @Valid
  public static final BigComplexRational ONE =
      new BigComplexRational(BigRational.ONE, BigRational.ZERO);

  /** z = i = 0 + i */
  @Valid
  public static final BigComplexRational I =
      new BigComplexRational(BigRational.ZERO, BigRational.ONE);

  @Valid private final BigRational a;

  @Valid private final BigRational b;

  public BigComplexRational() {
    this(BigRational.ZERO, BigRational.ZERO);
  }

  public BigComplexRational(@NotNull BigRational real) {
    this(real, BigRational.ZERO);
  }

  public BigComplexRational(@NotNull BigRational a, @NotNull BigRational b) {
    if (a != null) {
      this.a = a;
    } else {
      throw new NullPointerException("a cannot be null");
    }
    if (b != null) {
      this.b = b;
    } else {
      throw new NullPointerException("b cannot be null");
    }
  }

  /**
   * Parses complex number provided as string.
   *
   * <p>
   * General format is:<br>
   * &lt;BigRational&gt;&ltBigRational with sign;&gti e.g.:<br>
   * "2", "2.3", "2/3", "-2/3", ...<br>
   * "2+3i", "-2.3+4.5i", "-2/3-4/5i", ...<br>
   * "2i", "-2.3i", "-2/3i", ...<br>
   * And special cases:<br>
   * "i", "-i"
   *
   * <p>
   * Note: Parser is probably not 100% error prone. But as long as You stick to
   * the supported format, You should be fine ;)
   *
   * @param text
   */
  public BigComplexRational(@NotNull @NotEmpty String text) {
    this(BigComplexUtil.getReal(text), BigComplexUtil.getImaginary(text));
  }

  @Override
  public BigRational getA() {
    return this.a;
  }

  @Override
  public BigRational getReal() {
    return this.a;
  }

  @Override
  public BigRational getB() {
    return this.b;
  }

  @Override
  public BigRational getImaginary() {
    return this.b;
  }

  public BigComplexRational normalize() {
    return this.normalizeSignum().cancel();
  }

  public BigComplexRational normalizeSignum() {
    final var aNormalizedSignum = this.a.normalizeSignum();
    final var bNormalizedSignum = this.b.normalizeSignum();
    final var result = this.reuse(aNormalizedSignum, bNormalizedSignum);
    return result;
  }

  public BigComplexRational cancel() {
    return this.reuse(this.a.cancel(), this.b.cancel());
  }

  @Override
  public BigComplexRational add(@NotNull BigComplexRational augend) {
    return new BigComplexRational(this.a.add(augend.a), this.b.add(augend.b));
  }

  @Override
  public BigComplexRational subtract(@NotNull BigComplexRational subtrahend) {
    return new BigComplexRational(this.a.subtract(subtrahend.a), this.b.subtract(subtrahend.b));
  }

  @Override
  public BigComplexRational multiply(@NotNull BigComplexRational multiplicand)
      throws ArithmeticException {
    return new BigComplexRational(
        this.a.multiply(multiplicand.a).subtract(this.b.multiply(multiplicand.b)),
        this.b.multiply(multiplicand.a).add(this.a.multiply(multiplicand.b)));
  }

  @Override
  public BigComplexRational divide(@NotNull BigComplexRational divisor)
      throws CalculationException {

    final var denominator = divisor.a.multiply(divisor.a).add(divisor.b.multiply(divisor.b));
    return new BigComplexRational(
        this.a.multiply(divisor.a).add(this.b.multiply(divisor.b)).divide(denominator),
        this.b.multiply(divisor.a).subtract(this.a.multiply(divisor.b)).divide(denominator));
  }

  @Override
  public BigRational absSquared() throws ArithmeticException {
    return this.a.multiply(this.a).add(this.b.multiply(this.b));
  }

  @Override
  public BigComplexRational negate() {
    return new BigComplexRational(this.a.negate(), this.b.negate());
  }

  @Override
  public BigComplexRational inverse() throws ArithmeticException, CalculationException {
    final var abs = this.absSquared();
    return new BigComplexRational(this.a.divide(abs), this.b.divide(abs).negate());
  }

  @Override
  public BigComplexRational conjugate() throws ArithmeticException, CalculationException {
    return new BigComplexRational(this.a, this.b.negate());
  }

  @Override
  public BigComplexRational reuse(
      @NotNull final BigRational aNormalizedSignum, @NotNull final BigRational bNormalizedSignum) {
    final BigComplexRational result;
    if ((this.a == aNormalizedSignum) && (this.b == bNormalizedSignum)) {
      result = this;
    } else {
      result = new BigComplexRational(aNormalizedSignum, bNormalizedSignum);
    }
    return result;
  }

  /**
   * Note that:
   *
   * <p>
   * "Because complex numbers are naturally thought of as existing on a
   * two-dimensional plane, there is no natural linear ordering on the set of
   * complex numbers.
   *
   * <p>
   * There is no linear ordering on the complex numbers that is compatible with
   * addition and multiplication. Formally, we say that the complex numbers cannot
   * have the structure of an ordered field. This is because any square in an
   * ordered field is at least 0, but i2 = -1."
   *
   * <p>
   * Current implementation of {@link #compareTo(BigComplexRational)} method uses
   * {@link #absSquared()} method to compare.
   */
  @Override
  public int compareTo(BigComplexRational val) {
    return this.absSquared().compareTo(val.absSquared());
  }

  @Override
  public boolean equals(Object obj) {
    final boolean result;
    if (this == obj) {
      result = true;
    } else {
      if (obj instanceof BigComplexRational) {
        if (BigMathContext.get().getStrictEqualsAndHashContract()) {
          result = this.equalsStrict((BigComplexRational) obj);
        } else {
          result = this.equalsValue((BigComplexRational) obj);
        }
      } else {
        result = false;
      }
    }
    return result;
  }

  @Override
  public boolean equalsValue(@NotNull BigComplexRational obj) {
    return obj != null ? this.a.equalsValue(obj.a) && this.b.equalsValue(obj.b) : false;
  }

  @Override
  public boolean equalsStrict(BigComplexRational obj) {
    return obj != null ? this.a.equalsStrict(obj.a) && this.b.equalsStrict(obj.b) : false;
  }

  @Override
  public int hashCode() {
    final var prime = 31;
    var result = 1;
    result = (prime * result) + this.b.hashCode();
    result = (prime * result) + this.a.hashCode();
    return result;
  }

  @Override
  public String toString() {
    return new StringBuilder()
        .append(this.a.toString())
        .append(this.b.signum() >= 0 ? "+" : "")
        .append(this.b)
        .append("i")
        .toString();
  }

  @Override
  public int signum() {
    // TODO Auto-generated method stub
    return 0;
  }
}
