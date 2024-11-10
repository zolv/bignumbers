package net.turtle.math.function;

import java.util.function.BiFunction;
import net.turtle.math.core.BigRational;

public class BigRationalAddBigRational
    implements BiFunction<BigRational, BigRational, BigRational> {

  @Override
  public BigRational apply(BigRational a, BigRational b) {
    return a.add(b);
  }
}
