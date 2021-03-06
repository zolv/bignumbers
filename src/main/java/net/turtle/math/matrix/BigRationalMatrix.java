package net.turtle.math.matrix;

import java.util.ArrayList;
import java.util.List;

import net.turtle.math.core.BigRational;
import net.turtle.math.core.BigRationalValues;
import net.turtle.math.util.BigMatrixUtil;
import net.turtle.math.vector.BigRationalVector;

public class BigRationalMatrix
    extends BigFieldElementMatrix<BigRational, BigRationalVector, BigRationalMatrix> {

  public BigRationalMatrix() {
    this(new ArrayList<BigRationalVector>(0), false);
  }

  public BigRationalMatrix(String matrix) {
    this(BigMatrixUtil.parseBigRationalMatrix(matrix), false);
  }

  protected BigRationalMatrix(List<BigRationalVector> input) {
    super(input, false);
  }

  protected BigRationalMatrix(List<BigRationalVector> input, boolean trusted) {
    super(input, trusted);
  }

  @Override
  protected BigRational createZeroFieldElement() {
    return BigRationalValues.ZERO;
  }

  @Override
  protected BigRationalVector createRow(List<BigRational> input) {
    return new BigRationalVector(input, true);
  }

  @Override
  protected BigRationalMatrix createInstance(List<BigRationalVector> input) {
    return new BigRationalMatrix(input, true);
  }
}
