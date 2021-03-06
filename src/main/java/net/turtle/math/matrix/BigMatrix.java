package net.turtle.math.matrix;

import net.turtle.math.core.BigFieldElement;
import net.turtle.math.vector.BigVector;

public interface BigMatrix<
    F extends BigFieldElement<F>, V extends BigVector<F, V>, M extends BigMatrix<F, V, M>> {

  M transpose();

  M add(M augend);

  M substract(M subtrahend);

  M multiply(M multiplicand);

  M multiply(V multiplicand);

  M multiply(F multiplicand);

  int getRowsCount();

  int getColumnsCount();

  V getRowVector(int rowIndex);

  V getColumnVector(int columnIndex);

  /** @return true if matrix is square. */
  boolean isSquare();

  boolean isSimetric();
}
