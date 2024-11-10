package net.turtle.math.function;

import java.math.BigInteger;
import java.util.function.BiFunction;
import net.turtle.math.core.BigRational;

public class BigRationalAddBigInteger implements BiFunction<BigRational, BigInteger, BigRational> {

  @Override
  public BigRational apply(BigRational a, BigInteger b) {
    if (!b.equals(BigInteger.ZERO)) {
      return new BigRational(a.getNumerator().add(b.multiply(a.getDenominator())));
    } else {
      return a;
    }
  }
}
