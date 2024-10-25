package net.turtle.math.core;

import java.math.BigDecimal;

public class BigRationalOne extends BigRational {

  BigRationalOne() {
    super(BigDecimal.ONE);
  }

  @Override
  public BigRational normalize() {
    return this;
  }

  @Override
  public BigRational normalizeSignum() {
    return this;
  }

  @Override
  public BigRational cancel() {
    return this;
  }

  @Override
  public BigRational add(BigRational augend) {
    return new BigRational(
        augend.getNumerator().add(augend.getDenominator()), augend.getDenominator());
  }

  @Override
  public BigRational subtract(BigRational subtrahend) throws NullPointerException {
    return new BigRational(
        subtrahend.getNumerator().subtract(subtrahend.getDenominator()).negate(),
        subtrahend.getDenominator());
  }

  @Override
  public BigRational multiply(BigRational multiplicand) {
    return multiplicand;
  }

  @Override
  public BigRational abs() {
    return this;
  }

  @Override
  public BigRational inverse() {
    return this;
  }

  @Override
  public int signum() throws ArithmeticException {
    return 1;
  }

  @Override
  public BigRational square() {
    return this;
  }

  @Override
  public BigRational cube() {
    return this;
  }
}
