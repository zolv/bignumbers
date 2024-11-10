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
public class BigComplex implements BigFieldElement<BigComplex>, Comparable<BigComplex> {

  /** z = 0 = 0 + 0i */
  @Valid
  public static final BigComplex<BigReal> ZERO =
      new BigComplex<>(BigRational.ZERO, BigRational.ZERO);

  /** z = 1 = 1 + 0i */
  @Valid
  public static final BigComplex<BigRational> ONE =
      new BigComplex<>(BigRational.ONE, BigRational.ZERO);

  /** z = i = 0 + i */
  @Valid
  public static final BigComplex<BigRational> I =
      new BigComplex<>(BigRational.ZERO, BigRational.ONE);

  @Valid private final T a;

  @Valid private final T b;

  public BigComplex() {
    this((T) BigRational.ZERO, (T) BigRational.ZERO);
  }

  public BigComplex(@NotNull T real) {
    this(real, (T) BigRational.ZERO);
  }

  public BigComplex(@NotNull T a, @NotNull T b) {
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
   * <p>General format is:<br>
   * &lt;BigRational&gt;&ltBigRational with sign;&gti e.g.:<br>
   * "2", "2.3", "2/3", "-2/3", ...<br>
   * "2+3i", "-2.3+4.5i", "-2/3-4/5i", ...<br>
   * "2i", "-2.3i", "-2/3i", ...<br>
   * And special cases:<br>
   * "i", "-i"
   *
   * <p>Note: Parser is probably not 100% error prone. But as long as You stick to the supported
   * format, You should be fine ;)
   *
   * @param text
   */
  public BigComplex(@NotNull @NotEmpty String text) {
    this((T) BigComplexUtil.getReal(text), (T) BigComplexUtil.getImaginary(text));
  }

  public T getA() {
    return this.a;
  }

  public T getReal() {
    return this.a;
  }

  public T getB() {
    return this.b;
  }

  public T getImaginary() {
    return this.b;
  }

  @Override
  public BigComplex<T> add(@NotNull BigComplex<T> augend) {
    return new BigComplex<>(this.a.add(augend.a), this.b.add(augend.b));
  }

  @Override
  public BigComplex<T> subtract(@NotNull BigComplex<T> subtrahend) {
    return new BigComplex<>(this.a.subtract(subtrahend.a), this.b.subtract(subtrahend.b));
  }

  @Override
  public BigComplex<T> multiply(@NotNull BigComplex<T> multiplicand) throws ArithmeticException {
    return new BigComplex<>(
        this.a.multiply(multiplicand.a).subtract(this.b.multiply(multiplicand.b)),
        this.b.multiply(multiplicand.a).add(this.a.multiply(multiplicand.b)));
  }

  @Override
  public BigComplex<T> divide(@NotNull BigComplex<T> divisor) throws CalculationException {

    final var denominator = divisor.a.multiply(divisor.a).add(divisor.b.multiply(divisor.b));
    return new BigComplex<>(
        this.a.multiply(divisor.a).add(this.b.multiply(divisor.b)).divide(denominator),
        this.b.multiply(divisor.a).subtract(this.a.multiply(divisor.b)).divide(denominator));
  }

  public T absSquared() throws ArithmeticException {
    return this.a.multiply(this.a).add(this.b.multiply(this.b));
  }

  @Override
  public BigComplex<T> negate() {
    return new BigComplex<>(this.a.negate(), this.b.negate());
  }

  @Override
  public BigComplex<T> inverse() throws ArithmeticException, CalculationException {
    final var abs = this.absSquared();
    return new BigComplex<>(this.a.divide(abs), this.b.divide(abs).negate());
  }

  public BigComplex<T> conjugate() throws ArithmeticException, CalculationException {
    return new BigComplex<>(this.a, this.b.negate());
  }

  public BigComplex<T> reuse(
      @NotNull final T aNormalizedSignum, @NotNull final T bNormalizedSignum) {
    final BigComplex<T> result;
    if ((this.a == aNormalizedSignum) && (this.b == bNormalizedSignum)) {
      result = this;
    } else {
      result = new BigComplex<>(aNormalizedSignum, bNormalizedSignum);
    }
    return result;
  }

  /**
   * Note that:
   *
   * <p>"Because complex numbers are naturally thought of as existing on a two-dimensional plane,
   * there is no natural linear ordering on the set of complex numbers.
   *
   * <p>There is no linear ordering on the complex numbers that is compatible with addition and
   * multiplication. Formally, we say that the complex numbers cannot have the structure of an
   * ordered field. This is because any square in an ordered field is at least 0, but i2 = -1."
   *
   * <p>Current implementation of {@link #compareTo(BigComplex)} method uses {@link #absSquared()}
   * method to compare.
   */
  @Override
  public int compareTo(BigComplex<T> val) {
    return this.absSquared().compareTo(val.absSquared());
  }

  @Override
  public boolean equals(Object obj) {
    final boolean result;
    if (this == obj) {
      result = true;
    } else {
      if (obj instanceof BigComplex) {
        if (BigMathContext.get().getStrictEqualsAndHashContract()) {
          result = this.equalsStrict((BigComplex) obj);
        } else {
          result = this.equalsValue((BigComplex) obj);
        }
      } else {
        result = false;
      }
    }
    return result;
  }

  @Override
  public boolean equalsValue(@NotNull BigComplex<T> obj) {
    return obj != null ? this.a.equalsValue(obj.a) && this.b.equalsValue(obj.b) : false;
  }

  @Override
  public boolean equalsStrict(BigComplex<T> obj) {
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
}
