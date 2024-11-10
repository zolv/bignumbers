package net.turtle.math.core;

import java.math.BigInteger;
import java.math.RoundingMode;
import net.turtle.math.context.BigMathContext;
import net.turtle.math.exception.CalculationException;

public interface BigComplexable extends BigValue {

  BigComplexable add(BigComplexable augend) throws CalculationException;

  BigComplexable subtract(BigComplexable subtrahend) throws CalculationException;

  BigComplexable multiply(BigComplexable multiplicand) throws CalculationException;

  BigComplexable divide(BigComplexable divisor) throws CalculationException;

  BigComplexable inverse() throws ArithmeticException;

  BigComplexable negate();

  boolean equalsValue(BigComplexable a);

  boolean equalsStrict(BigComplexable a);

  @Override
  default BigComplexable toBigComplex() {
    return this;
  }
}
