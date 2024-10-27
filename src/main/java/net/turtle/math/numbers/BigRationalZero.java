package net.turtle.math.numbers;

import net.turtle.math.core.BigRational;

public class BigRationalZero extends BigRational {

  public BigRationalZero() {}

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
    return augend;
  }

  @Override
  public BigRational subtract(BigRational subtrahend) throws NullPointerException {
    return subtrahend.negate();
  }

  @Override
  public BigRational multiply(BigRational multiplicand) {
    return this;
  }

  @Override
  public BigRational abs() {
    return this;
  }

  @Override
  public BigRational negate() {
    return this;
  }

  @Override
  public BigRational inverse() {
    throw new ArithmeticException("Division by zero");
  }

  @Override
  public int signum() throws ArithmeticException {
    return 0;
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
