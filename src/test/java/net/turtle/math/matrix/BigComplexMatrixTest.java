package net.turtle.math.matrix;

import java.math.BigInteger;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import net.turtle.math.core.BigComplex;
import net.turtle.math.core.BigComplexValues;
import net.turtle.math.exception.CalculationException;
import net.turtle.math.exception.DifferentDimensionsException;
import net.turtle.math.exception.ParsingException;
import net.turtle.math.matrix.BigComplexMatrix;
import net.turtle.math.vector.BigComplexVector;

public class BigComplexMatrixTest {

  @Test
  public void testBigComplexMatrix() {
    {
      final BigComplexMatrix emptyMatrix = new BigComplexMatrix();
      Assertions.assertEquals(0, emptyMatrix.getRowsCount());
      Assertions.assertEquals(0, emptyMatrix.getColumnsCount());
    }
  }

  @Test
  public void testBigComplexMatrix_String() {
    {
      final BigComplexMatrix emptyMatrix = new BigComplexMatrix("[]");
      Assertions.assertEquals(0, emptyMatrix.getRowsCount());
      Assertions.assertEquals(0, emptyMatrix.getColumnsCount());
    }
    {
      final BigComplexMatrix input = new BigComplexMatrix("[[1,2,3],[4,5,6],[7,8,9]]");
      Assertions.assertEquals(3, input.getRowsCount());
      Assertions.assertEquals(3, input.getColumnsCount());
    }
    {
      final BigComplexMatrix input = new BigComplexMatrix("[[2,3]]");
      Assertions.assertEquals(1, input.getRowsCount());
      Assertions.assertEquals(2, input.getColumnsCount());
    }
  }

  @Test
  public void testBigComplexMatrix_String_Dimentions() {
    Assertions.assertThrows(
        DifferentDimensionsException.class,
        () -> {
          new BigComplexMatrix("[[1+i,2+i,3+i],[4+i,5+i],[6+i,7+i,8+i]]");
        });
  }

  @Test
  public void testBigComplexMatrix_String_MatrixParsing() {
    Assertions.assertThrows(
        ParsingException.class,
        () -> {
          new BigComplexMatrix("[1,2,3]");
        });
  }

  @Test
  public void testBigComplexMatrix_String_MatrixParsing2() {
    Assertions.assertThrows(
        ParsingException.class,
        () -> {
          new BigComplexMatrix("[[][]");
        });
  }

  @Test
  public void testTranspose() {
    {
      final BigComplexMatrix input = new BigComplexMatrix("[]");
      final BigComplexMatrix output = input.transpose();
      Assertions.assertEquals(0, output.getRowsCount());
      Assertions.assertEquals(0, output.getColumnsCount());
    }
    {
      final BigComplexMatrix input = new BigComplexMatrix("[[1,2,3],[4,5,6]]");
      final BigComplexMatrix output = input.transpose();
      Assertions.assertEquals(3, output.getRowsCount());
      Assertions.assertEquals(2, output.getColumnsCount());

      final BigComplexVector rowVector0 = output.getRowVector(0);
      final List<BigComplex> coordinates0 = rowVector0.getCoordinates();
      Assertions.assertEquals(new BigComplex("1"), coordinates0.get(0));
      Assertions.assertEquals(new BigComplex("4"), coordinates0.get(1));

      final BigComplexVector rowVector1 = output.getRowVector(1);
      final List<BigComplex> coordinates1 = rowVector1.getCoordinates();
      Assertions.assertEquals(new BigComplex("2"), coordinates1.get(0));
      Assertions.assertEquals(new BigComplex("5"), coordinates1.get(1));

      final BigComplexVector rowVector2 = output.getRowVector(2);
      final List<BigComplex> coordinates2 = rowVector2.getCoordinates();
      Assertions.assertEquals(new BigComplex("3"), coordinates2.get(0));
      Assertions.assertEquals(new BigComplex("6"), coordinates2.get(1));
    }
  }

  @Test
  public void testAddMatrix() {
    {
      final BigComplexMatrix input = new BigComplexMatrix("[]");
      final BigComplexMatrix output = input.add(input);
      Assertions.assertEquals(0, output.getRowsCount());
      Assertions.assertEquals(0, output.getColumnsCount());
    }
    {
      final BigComplexMatrix input = new BigComplexMatrix("[[1,2,3],[4,5,6]]");
      final BigComplexMatrix input2 = new BigComplexMatrix("[[7,8,9],[10,11,12]]");
      final BigComplexMatrix output = input.add(input2);
      Assertions.assertEquals(2, output.getRowsCount());
      Assertions.assertEquals(3, output.getColumnsCount());

      final BigComplexVector rowVector0 = output.getRowVector(0);
      final List<BigComplex> coordinates0 = rowVector0.getCoordinates();
      Assertions.assertEquals(new BigComplex("8"), coordinates0.get(0));
      Assertions.assertEquals(new BigComplex("10"), coordinates0.get(1));
      Assertions.assertEquals(new BigComplex("12"), coordinates0.get(2));

      final BigComplexVector rowVector1 = output.getRowVector(1);
      final List<BigComplex> coordinates1 = rowVector1.getCoordinates();
      Assertions.assertEquals(new BigComplex("14"), coordinates1.get(0));
      Assertions.assertEquals(new BigComplex("16"), coordinates1.get(1));
      Assertions.assertEquals(new BigComplex("18"), coordinates1.get(2));
    }
  }

  @Test
  public void testSubtractMatrix() {
    {
      final BigComplexMatrix input = new BigComplexMatrix("[]");
      final BigComplexMatrix output = input.substract(input);
      Assertions.assertEquals(0, output.getRowsCount());
      Assertions.assertEquals(0, output.getColumnsCount());
    }
    {
      final BigComplexMatrix input = new BigComplexMatrix("[[1,6,5],[12,7,11]]");
      final BigComplexMatrix input2 = new BigComplexMatrix("[[4,3,2],[9,10,8]]");
      final BigComplexMatrix output = input.substract(input2);
      Assertions.assertEquals(2, output.getRowsCount());
      Assertions.assertEquals(3, output.getColumnsCount());

      final BigComplexVector rowVector0 = output.getRowVector(0);
      final List<BigComplex> coordinates0 = rowVector0.getCoordinates();
      Assertions.assertEquals(new BigComplex("-3"), coordinates0.get(0));
      Assertions.assertEquals(new BigComplex("3"), coordinates0.get(1));
      Assertions.assertEquals(new BigComplex("3"), coordinates0.get(2));

      final BigComplexVector rowVector1 = output.getRowVector(1);
      final List<BigComplex> coordinates1 = rowVector1.getCoordinates();
      Assertions.assertEquals(new BigComplex("3"), coordinates1.get(0));
      Assertions.assertEquals(new BigComplex("-3"), coordinates1.get(1));
      Assertions.assertEquals(new BigComplex("3"), coordinates1.get(2));
    }
  }

  @Test
  public void multiplyByMatrix() {
    {
      final BigComplexMatrix input = new BigComplexMatrix("[]");
      final BigComplexMatrix output = input.multiply(input);
      Assertions.assertEquals(0, output.getRowsCount());
      Assertions.assertEquals(0, output.getColumnsCount());
    }
    {
      final BigComplexMatrix input = new BigComplexMatrix("[[1,0,2],[-1,3,1]]");
      final BigComplexMatrix input2 = new BigComplexMatrix("[[3,1],[2,1],[1,0]]");
      final BigComplexMatrix output = input.multiply(input2);
      Assertions.assertEquals(2, output.getRowsCount());
      Assertions.assertEquals(2, output.getColumnsCount());

      final BigComplexVector rowVector0 = output.getRowVector(0);
      final List<BigComplex> coordinates0 = rowVector0.getCoordinates();
      Assertions.assertEquals(new BigComplex("5"), coordinates0.get(0));
      Assertions.assertEquals(new BigComplex("1"), coordinates0.get(1));

      final BigComplexVector rowVector1 = output.getRowVector(1);
      final List<BigComplex> coordinates1 = rowVector1.getCoordinates();
      Assertions.assertEquals(new BigComplex("4"), coordinates1.get(0));
      Assertions.assertEquals(new BigComplex("2"), coordinates1.get(1));
    }

    {
      final BigComplexMatrix input = new BigComplexMatrix("[[2,3,5,7,11]]");
      final BigComplexMatrix input2 = new BigComplexMatrix("[[13],[17],[19],[23],[29]]");
      final BigComplexMatrix output = input.multiply(input2);
      Assertions.assertEquals(1, output.getRowsCount());
      Assertions.assertEquals(1, output.getColumnsCount());

      final BigComplexVector rowVector0 = output.getRowVector(0);
      final List<BigComplex> coordinates0 = rowVector0.getCoordinates();
      Assertions.assertEquals(new BigComplex("652"), coordinates0.get(0));
    }
  }

  @Test
  public void multiplyByWrongMatrix() {
    Assertions.assertThrows(
        CalculationException.class,
        () -> {
          final BigComplexMatrix input = new BigComplexMatrix("[[1,0,2],[-1,3,1]]");
          final BigComplexMatrix input2 = new BigComplexMatrix("[[3,1],[2,1],[1,0],[1,0]]");
          input.multiply(input2);
        });
  }

  @Test
  public void multiplyByBigComplex() {
    {
      final BigComplexMatrix input = new BigComplexMatrix("[]");
      final BigComplexMatrix result = new BigComplexMatrix("[]");
      final BigComplexMatrix output = input.multiply((BigComplex) null);
      Assertions.assertEquals(result, output);
    }
    {
      final BigComplexMatrix input = new BigComplexMatrix("[]");
      final BigComplexMatrix result = new BigComplexMatrix("[]");
      final BigComplexMatrix output = input.multiply(new BigComplex("2"));
      Assertions.assertEquals(result, output);
    }
    {
      final BigComplexMatrix input = new BigComplexMatrix("[[1,0,2],[-1,3,1]]");
      final BigComplexMatrix result = new BigComplexMatrix("[[2,0,4],[-2,6,2]]");
      final BigComplexMatrix output = input.multiply(new BigComplex("2"));
      Assertions.assertEquals(result, output);
    }
  }

  @Test
  public void multiplyByNullBigComplex() {
    Assertions.assertThrows(
        NullPointerException.class,
        () -> {
          final BigComplexMatrix input = new BigComplexMatrix("[[2,3],[4,6]]");
          input.multiply((BigComplex) null);
        });
  }

  @Test
  public void isSquare() {
    {
      final BigComplexMatrix input = new BigComplexMatrix("[]");
      Assertions.assertTrue(input.isSquare());
    }
    {
      final BigComplexMatrix input = new BigComplexMatrix("[[1]]");
      Assertions.assertTrue(input.isSquare());
    }
    {
      final BigComplexMatrix input = new BigComplexMatrix("[[1,0,2],[-1,3,1],[-1,3,1]]");
      Assertions.assertTrue(input.isSquare());
    }
    {
      final BigComplexMatrix input = new BigComplexMatrix("[[1,0,2],[-1,3,1]]");
      Assertions.assertFalse(input.isSquare());
    }
  }

  @Test
  public void isSimetric() {
    {
      final BigComplexMatrix input = new BigComplexMatrix("[]");
      Assertions.assertTrue(input.isSimetric());
    }
    {
      final BigComplexMatrix input = new BigComplexMatrix("[[1]]");
      Assertions.assertTrue(input.isSimetric());
    }
    {
      final BigComplexMatrix input = new BigComplexMatrix("[[2,3,5],[3,7,11],[5,11,13]]");
      Assertions.assertTrue(input.isSimetric());
    }
    {
      final BigComplexMatrix input = new BigComplexMatrix("[[0,0],[0,0]]");
      Assertions.assertTrue(input.isSimetric());
    }
    {
      final BigComplexMatrix input = new BigComplexMatrix("[[2,3],[5,7]]");
      Assertions.assertFalse(input.isSimetric());
    }
  }

  @Test
  public void detCalculation() {
    {
      final BigComplexMatrix input = new BigComplexMatrix("[]");
      final BigComplex output = input.det();
      Assertions.assertEquals(BigComplexValues.ZERO, output);
    }
    {
      final BigComplexMatrix input = new BigComplexMatrix("[[2]]");
      final BigComplex output = input.det();
      Assertions.assertEquals(new BigComplex("2"), output);
    }
    {
      final BigComplexMatrix input = new BigComplexMatrix("[[2,3],[5,7]]");
      final BigComplex output = input.det();
      Assertions.assertEquals(new BigComplex("-1"), output);
    }
    {
      final BigComplexMatrix input = new BigComplexMatrix("[[-2,2,-3],[-1,1,3],[2,0,-1]]");
      final BigComplex output = input.det();
      Assertions.assertEquals(new BigComplex("18"), output);
    }
    {
      final BigComplexMatrix input =
          new BigComplexMatrix("[[1,3,0,-1],[0,2,1,3],[3,1,2,1],[-1,2,0,3]]");
      final BigComplex output = input.det();
      Assertions.assertEquals(new BigComplex("14"), output);
    }
    {
      final BigComplexMatrix input =
          new BigComplexMatrix("[[0,1,2,7],[1,2,3,4],[5,6,7,8],[-1,1,-1,1]]");
      final BigComplex output = input.det();
      Assertions.assertEquals(new BigComplex("-64"), output);
    }
    {
      final BigComplexMatrix input =
          new BigComplexMatrix(
              "[[2, 3, 5, 7, 11],[13, 17, 19, 23, 29],[31, 37, 41, 43, 47],[53, 59, 61, 67, 71],[73, 79, 83, 89, 97]]");
      final BigComplex output = input.det();
      Assertions.assertEquals(new BigComplex("-4656"), output);
    }
    {
      final BigComplexMatrix input =
          new BigComplexMatrix(
              "[[0,1,2,3,4,5,6,7,8,9,10],[0,1,2,3,4,5,6,7,8,9,10],[0,1,2,3,4,5,6,7,8,9,10],[0,1,2,3,4,5,6,7,8,9,10],[0,1,2,3,4,5,6,7,8,9,10],[0,1,2,3,4,5,6,7,8,9,10],[0,1,2,3,4,5,6,7,8,9,10],[0,1,2,3,4,5,6,7,8,9,10],[0,1,2,3,4,5,6,7,8,9,10],[0,1,2,3,4,5,6,7,8,9,10],[0,1,2,3,4,5,6,7,8,9,10]]]");
      final BigComplex output = input.det();
      Assertions.assertEquals(new BigComplex("-0"), output);
    }
    {
      final BigComplexMatrix input =
          new BigComplexMatrix(
              "[[0,1,2,3,4,5,6,7,8,9,10],[0,1,2,3,4,5,6,7,8,9,10],[0,1,2,3,4,5,6,7,8,9,10],[0,1,2,3,4,5,6,7,8,9,10],[0,1,2,3,4,5,6,7,8,9,10],[0,1,2,3,4,5,6,7,8,9,10],[0,1,2,3,4,5,6,7,8,9,10],[0,1,2,3,4,5,6,7,8,9,10],[0,1,2,3,4,5,6,7,8,9,10],[0,1,2,3,4,5,6,7,8,9,10],[0,1,2,3,4,5,6,7,8,9,10]]]");
      final BigComplex output = input.det();
      Assertions.assertEquals(new BigComplex("-0"), output);
    }
  }

  @Test
  public void equalsHashContract() {
    {
      final BigComplexMatrix input1 = new BigComplexMatrix("[]");
      final BigComplexMatrix input2 = new BigComplexMatrix("[]");
      Assertions.assertTrue(input1.equals(input1));
      Assertions.assertTrue(input1.equals(input2));
      Assertions.assertTrue(input1.hashCode() == input2.hashCode());
    }
    {
      final BigComplexMatrix input1 = new BigComplexMatrix("[[2]]");
      final BigComplexMatrix input2 = new BigComplexMatrix("[[4/2]]");
      Assertions.assertTrue(input1.equals(input2));
      Assertions.assertTrue(input1.hashCode() == input2.hashCode());
    }
    {
      final BigComplexMatrix input1 = new BigComplexMatrix("[[2,3],[5,7]]");
      final BigComplexMatrix input2 = new BigComplexMatrix("[[2,3],[5,7]]");
      Assertions.assertTrue(input1.equals(input2));
    }
    {
      final BigComplexMatrix input1 = new BigComplexMatrix("[[-2,2,-3],[-1,1,3],[2,0,-1]]");
      final BigComplexMatrix input2 = new BigComplexMatrix("[[-2,2,-3],[-1,1,3],[2,0,-1]]");
      Assertions.assertTrue(input1.equals(input2));
      Assertions.assertTrue(input1.hashCode() == input2.hashCode());
    }
    {
      final BigComplexMatrix input1 = new BigComplexMatrix("[[-2,2,-3],[-1,1,3],[2,0,-1]]");
      final BigComplexMatrix input2 = new BigComplexMatrix("[[-2,2,-3],[-1,1,3],[2,0,1]]");
      Assertions.assertFalse(input1.equals(input2));
    }
    {
      final BigComplexMatrix input1 = new BigComplexMatrix("[[-2,2,-3],[-1,1,3],[2,0,-1]]");
      Assertions.assertFalse(input1.equals(BigInteger.ONE));
    }
    {
      final BigComplexMatrix input1 = new BigComplexMatrix("[[-2,2,-3],[-1,1,3],[2,0,-1]]");
      Assertions.assertFalse(input1.equals(null));
    }
  }

  @Test
  public void testDet_NotSquare() {
    Assertions.assertThrows(
        CalculationException.class,
        () -> {
          final BigComplexMatrix input =
              new BigComplexMatrix(
                  "[[0,1,2,7],[1,2,3,4],[5,6,7,8],[-1,1,-1,1],[-1,1,-1,1],[-1,1,-1,1]]");
          input.det();
        });
  }

  @Test
  public void toStringResult() {
    {
      final BigComplexMatrix input = new BigComplexMatrix("[]");
      Assertions.assertEquals("[]", input.toString());
    }
    {
      final BigComplexMatrix input = new BigComplexMatrix("[[1,2+4i,3-7i]]");
      Assertions.assertEquals("[[1/1+0/1i, 2/1+4/1i, 3/1-7/1i]]", input.toString());
    }
  }

  @Test
  public void testConjugate() {
    {
      final BigComplexMatrix input = new BigComplexMatrix("[]");
      Assertions.assertEquals(new BigComplexMatrix("[]"), input.conjugate());
    }
    {
      final BigComplexMatrix input = new BigComplexMatrix("[[1,2+4i,3-7i]]");
      Assertions.assertEquals(
          new BigComplexMatrix("[[1/1+0/1i, 2/1-4/1i, 3/1+7/1i]]"), input.conjugate());
    }
  }
}
