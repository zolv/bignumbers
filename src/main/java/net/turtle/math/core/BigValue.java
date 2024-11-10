package net.turtle.math.core;

import java.math.BigInteger;
import java.math.RoundingMode;

import net.turtle.math.context.BigMathContext;

public interface BigValue {

  default BigRationable toBigRational() {
    final var context = BigMathContext.get();
    return this.toBigRational(
        context.getDenominatorRoundingValue(), context.getNumeratorRoundingMode());
  }

  BigRationable toBigRational(BigInteger denominator, RoundingMode numeratorRoundingMode);

  BigRealable toBigReal();

  default BigComplexable toBigComplex() {
    final var context = BigMathContext.get();
    return this.toBigComplex(
        context.getDenominatorRoundingValue(), context.getNumeratorRoundingMode());
  }

  default BigComplexable toBigComplex(
      BigInteger realDenominatorRoundingValue, RoundingMode realNumeratorRoundingMode) {
    final var context = BigMathContext.get();
    return this.toBigComplex(
        context.getDenominatorRoundingValue(),
        context.getNumeratorRoundingMode(),
        context.getDenominatorRoundingValue(),
        context.getNumeratorRoundingMode());
  }

  BigComplexable toBigComplex(
      BigInteger realDenominatorRoundingValue,
      RoundingMode realNumeratorRoundingMode,
      BigInteger imaginaryDenominatorRoundingValue,
      RoundingMode imaginaryNumeratorRoundingMode);
}
