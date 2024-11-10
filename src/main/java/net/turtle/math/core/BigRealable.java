package net.turtle.math.core;

import net.turtle.math.exception.CalculationException;

public interface BigRealable extends BigComplexable {

  BigRealable add(BigRealable augend) throws CalculationException;

  BigRealable subtract(BigRealable subtrahend) throws CalculationException;

  BigRealable multiply(BigRealable multiplicand) throws CalculationException;

  BigRealable divide(BigRealable divisor) throws CalculationException;

  BigRealable inverse() throws ArithmeticException;

  BigRealable negate();

  boolean equalsValue(BigRealable a);

  boolean equalsStrict(BigRealable a);

  @Override
  default BigRealable toBigReal() {
    return this;
  }
}
