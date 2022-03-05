package net.turtle.math.vector;

import java.util.Arrays;
import java.util.List;

import net.turtle.math.core.BigRational;
import net.turtle.math.util.BigVectorUtil;

public class BigRationalVector extends BigFieldElementVector<BigRational, BigRationalVector> {

  public BigRationalVector() {}

  public BigRationalVector(String vector) {
    super(BigVectorUtil.parseBigRationalVector(vector));
  }

  public BigRationalVector(BigRational... coordinatesToUse) {
    super(Arrays.asList(coordinatesToUse));
  }

  public BigRationalVector(List<BigRational> input) {
    super(input);
  }

  public BigRationalVector(List<BigRational> input, boolean trusted) {
    super(input, trusted);
  }

  @Override
  protected BigRational createZeroFieldElement() {
    return BigRational.ZERO;
  }

  @Override
  protected BigRationalVector createInstance(List<BigRational> coordinatesSum) {
    return new BigRationalVector(coordinatesSum, true);
  }
}
