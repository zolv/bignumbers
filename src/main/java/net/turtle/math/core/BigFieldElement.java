package net.turtle.math.core;

import net.turtle.math.exception.CalculationException;

public interface BigFieldElement<T> extends Comparable<T> {

  T add(T augend) throws CalculationException;

  T subtract(T subtrahend) throws CalculationException;

  T multiply(T multiplicand) throws CalculationException;

  T divide(T divisor) throws CalculationException;

  T inverse() throws ArithmeticException;

  T negate();

  boolean equalsValue(T a);

  boolean equalsStrict(T a);
}
