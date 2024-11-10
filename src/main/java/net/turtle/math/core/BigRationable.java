package net.turtle.math.core;

import java.math.BigInteger;
import java.math.RoundingMode;
import net.turtle.math.context.BigMathContext;
import net.turtle.math.exception.CalculationException;

public interface BigRationable extends BigRealable {

  BigRationable add(BigRationable augend) throws CalculationException;

  BigRationable subtract(BigRationable subtrahend) throws CalculationException;

  BigRationable multiply(BigRationable multiplicand) throws CalculationException;

  BigRationable divide(BigRationable divisor) throws CalculationException;

  BigRationable inverse() throws ArithmeticException;

  BigRationable negate();

  boolean equalsValue(BigRationable a);

  boolean equalsStrict(BigRationable a);

  @Override
  default BigRationable toBigRational() {
    return this;
  }
}
