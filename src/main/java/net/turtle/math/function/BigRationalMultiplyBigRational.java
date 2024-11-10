package net.turtle.math.function;

import java.util.function.BiFunction;
import net.turtle.math.core.BigRational;

public class BigRationalMultiplyBigRational
    implements BiFunction<BigRational, BigRational, BigRational> {

  @Override
  public BigRational apply(BigRational a, BigRational b) {
    return a.multiply(b);
  }
}
