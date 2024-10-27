package net.turtle.math.numbers;

import java.math.BigInteger;
import net.turtle.math.core.BigRational;

public class BigRationalOne extends BigRational {

  public BigRationalOne() {
    super(BigInteger.ONE);
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
        subtrahend.getDenominator().subtract(subtrahend.getNumerator()),
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
