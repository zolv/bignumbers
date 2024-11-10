package net.turtle.math.matrix;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.turtle.math.core.BigFieldElement;
import net.turtle.math.exception.CalculationException;
import net.turtle.math.vector.BigFieldElementVector;

public abstract class BigFieldElementMatrix<
        F extends BigFieldElement<F>,
        V extends BigFieldElementVector<F, V>,
        M extends BigFieldElementMatrix<F, V, M>>
    implements BigMatrix<F, V, M> {

  protected final List<V> entries;

  protected BigFieldElementMatrix(List<V> entries, boolean trusted) {
    if (trusted) {
      this.entries = entries;
    } else {
      this.entries = new ArrayList<>(entries);
    }
  }

  protected abstract F createZeroFieldElement();

  protected abstract V createRow(List<F> input);

  protected abstract M createInstance(List<V> vectors);

  /*
   * (non-Javadoc)
   *
   * @see net.turtle.math.core.BigMatrix#transpose()
   */
  @Override
  public M transpose() {
    final var columnsCount = this.getColumnsCount();
    final List<V> transposedEntries = new ArrayList<>(columnsCount);
    for (var i = 0; i < columnsCount; i++) {
      final var columnVector = this.getColumnVector(i);
      transposedEntries.add(columnVector);
    }
    final var result = this.createInstance(transposedEntries);
    return result;
  }

  /*
   * (non-Javadoc)
   *
   * @see net.turtle.math.core.BigMatrix#add(net.turtle.math.core.BigMatrix)
   */
  @Override
  public M add(M augend) {
    final var rowCount = this.getRowsCount();
    final List<V> transposedEntries = new ArrayList<>(rowCount);
    for (var i = 0; i < rowCount; i++) {
      final var rowVector = this.getRowVector(i);
      final var rowVectorAugend = augend.getRowVector(i);
      transposedEntries.add(rowVector.add(rowVectorAugend));
    }
    final var result = this.createInstance(transposedEntries);
    return result;
  }

  /*
   * (non-Javadoc)
   *
   * @see
   * net.turtle.math.core.BigMatrix#substract(net.turtle.math.core.BigMatrix)
   */
  @Override
  public M substract(M subtrahend) {
    final var rowCount = this.getRowsCount();
    final List<V> transposedEntries = new ArrayList<>(rowCount);
    for (var i = 0; i < rowCount; i++) {
      final var rowVector = this.getRowVector(i);
      final var rowVectorAugend = subtrahend.getRowVector(i);
      transposedEntries.add(rowVector.subtract(rowVectorAugend));
    }
    final var result = this.createInstance(transposedEntries);
    return result;
  }

  @Override
  public M multiply(M multiplicand) {
    final var thisColumnsCount = this.getColumnsCount();
    final var thatRowsCount = multiplicand.getRowsCount();
    final M result;
    if (thisColumnsCount == thatRowsCount) {
      final var thatColumnsCount = multiplicand.getColumnsCount();

      /*
       * Creating column vector cache.
       */
      final Map<Integer, V> columnVectorCache = new HashMap<>(this.getColumnsCount(), 1.01F);
      for (var p = 0; p < thatColumnsCount; p++) {
        final var thatColumnVector = multiplicand.getColumnVector(p);
        columnVectorCache.put(Integer.valueOf(p), thatColumnVector);
      }

      final var thisRowsCount = this.getRowsCount();
      final List<V> resultEntries = new ArrayList<>(thisRowsCount);

      for (var n = 0; n < thisRowsCount; n++) {
        final var thisRowVector = this.getRowVector(n);
        final List<F> resultCoordinates = new ArrayList<>(thatColumnsCount);

        for (var p = 0; p < thatColumnsCount; p++) {
          final var thatColumnVector = columnVectorCache.get(Integer.valueOf(p));
          final var dotProduct = thisRowVector.dotProduct(thatColumnVector);
          resultCoordinates.add(dotProduct);
        }
        resultEntries.add(this.createRow(resultCoordinates));
      }

      result = this.createInstance(resultEntries);
    } else {
      throw new CalculationException(
          "Number of columns of this matrix ("
              + thisColumnsCount
              + ") and number of rows in multiplicand matrix ("
              + thatRowsCount
              + ") are not equal.");
    }
    return result;
  }

  @Override
  public M multiply(V multiplicand) {
    final List<V> vectors = new ArrayList<>(1);
    vectors.add(multiplicand);
    final var converted = this.createInstance(vectors).transpose();
    return this.multiply(converted);
  }

  @Override
  public M multiply(F multiplicand) {
    final var rowCount = this.getRowsCount();
    final List<V> resultEntries = new ArrayList<>(rowCount);
    for (final V vector : this.entries) {
      final var resultVector = vector.multiply(multiplicand);
      resultEntries.add(resultVector);
    }
    final var result = this.createInstance(resultEntries);
    return result;
  }

  public F det() {
    final F result;
    if (this.isSquare()) {
      if (this.getRowsCount() == 0) {
        result = this.createZeroFieldElement();
      } else {
        if (this.getRowsCount() == 1) {
          result = this.getRowVector(0).getCoordinates().get(0);
        } else {
          if (this.getRowsCount() == 2) {
            result = this.det2x2();
          } else {
            if (this.getRowsCount() == 3) {
              result = this.det3x3();
            } else {
              result = this.detLaplace();
            }
          }
        }
      }
    } else {
      throw new CalculationException(
          "Matrix is not square matrix: " + this.getRowsCount() + " x " + this.getColumnsCount());
    }
    return result;
  }

  protected F det2x2() {
    final var row0Coordinates = this.getRowVector(0).getCoordinates();
    final var row1Coordinates = this.getRowVector(1).getCoordinates();
    final var result =
        row0Coordinates
            .get(0)
            .multiply(row1Coordinates.get(1))
            .subtract(row0Coordinates.get(1).multiply(row1Coordinates.get(0)));
    return result;
  }

  protected F det3x3() {
    final F result;
    final var row0Coordinates = this.getRowVector(0).getCoordinates();
    final var row1Coordinates = this.getRowVector(1).getCoordinates();
    final var row2Coordinates = this.getRowVector(2).getCoordinates();
    final var a = row0Coordinates.get(0);
    final var b = row0Coordinates.get(1);
    final var c = row0Coordinates.get(2);
    final var d = row1Coordinates.get(0);
    final var e = row1Coordinates.get(1);
    final var f = row1Coordinates.get(2);
    final var g = row2Coordinates.get(0);
    final var h = row2Coordinates.get(1);
    final var i = row2Coordinates.get(2);

    /*
     * Alternative: result = a.multiply( e.multiply( i ).subtract( f.multiply( h
     * ) ) ).subtract( b.multiply( d.multiply( i ).subtract( f.multiply( g ) ) )
     * ) .add( c.multiply( d.multiply( h ).subtract( e.multiply( g ) ) ) );
     */
    result =
        a.multiply(e)
            .multiply(i)
            .add(b.multiply(f).multiply(g))
            .add(c.multiply(d).multiply(h))
            .subtract(c.multiply(e).multiply(g))
            .subtract(b.multiply(d).multiply(i))
            .subtract(a.multiply(f).multiply(h));
    return result;
  }

  protected F detLaplace() {
    final F result;

    final var rowsCount = this.getRowsCount();
    var determinant = this.createZeroFieldElement();
    for (var i = 0; i < rowsCount; i++) {
      final var rowVector = this.getRowVector(i);
      final var cellValue = rowVector.getCoordinate(0);
      /*
       * Zero check to speed up.
       */
      if (!this.createZeroFieldElement().equals(cellValue)) {
        final var subDet2 = this.createMatrixWithoutRowAndColumn(i, 0).det();
        final var subDet = cellValue.multiply(subDet2);
        if ((i % 2) != 0) {
          determinant = determinant.subtract(subDet);
        } else {
          determinant = determinant.add(subDet);
        }
      }
    }
    result = determinant;
    return result;
  }

  private M createMatrixWithoutRowAndColumn(int rowIndex, int columnIndex) {
    final var rowsCount = this.getRowsCount();

    final List<V> rows = new ArrayList<>(Math.max(rowsCount - 1, 0));

    final var columnsCount = this.getColumnsCount();

    for (var i = 0; i < rowsCount; i++) {
      if (i != rowIndex) {
        final var row = this.getRowVector(i);
        final List<F> coordinates = new ArrayList<>(Math.max(columnsCount - 1, 0));
        for (var j = 0; j < columnsCount; j++) {
          if (j != columnIndex) {
            coordinates.add(row.getCoordinate(j));
          }
        }
        rows.add(this.createRow(coordinates));
      }
    }
    return this.createInstance(rows);
  }

  @Override
  public boolean isSquare() {
    return this.getRowsCount() == this.getColumnsCount();
  }

  @Override
  public boolean isSimetric() {
    return this.equals(this.transpose());
  }

  /*
   * (non-Javadoc)
   *
   * @see net.turtle.math.core.BigMatrix#getRowsCount()
   */
  @Override
  public int getRowsCount() {
    return this.entries.size();
  }

  /*
   * (non-Javadoc)
   *
   * @see net.turtle.math.core.BigMatrix#getColumnsCount()
   */
  @Override
  public int getColumnsCount() {
    final int result;
    if (!this.entries.isEmpty()) {
      final var firstRow = this.entries.iterator().next();
      result = firstRow.getDimension();
    } else {
      result = 0;
    }
    return result;
  }

  /*
   * (non-Javadoc)
   *
   * @see net.turtle.math.core.BigMatrix#getRowVector(int)
   */
  @Override
  public V getRowVector(int rowIndex) {
    return this.entries.get(rowIndex);
  }

  /*
   * (non-Javadoc)
   *
   * @see net.turtle.math.core.BigMatrix#getColumnVector(int)
   */
  @Override
  public V getColumnVector(int columnIndex) {
    final List<F> columnEntries = new ArrayList<>(this.getColumnsCount());
    for (final V element : this.entries) {
      columnEntries.add(element.getCoordinates().get(columnIndex));
    }
    return this.createRow(columnEntries);
  }

  @Override
  public String toString() {
    final var builder = new StringBuilder();
    builder.append(this.entries);
    return builder.toString();
  }

  @Override
  public int hashCode() {
    final var prime = 31;
    var result = 1;
    result = (prime * result) + this.entries.hashCode();
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    final boolean result;
    if (this == obj) {
      result = true;
    } else {
      if (obj == null) {
        result = false;
      } else {
        if (obj instanceof BigFieldElementMatrix) {
          final var other = (BigFieldElementMatrix<F, V, M>) obj;
          result = this.entries.equals(other.entries);
        } else {
          result = false;
        }
      }
    }
    return result;
  }
}
