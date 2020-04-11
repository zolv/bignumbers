package net.turtle.math.core;

import java.math.BigInteger;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import net.turtle.math.exception.CalculationException;
import net.turtle.math.exception.DifferentDimensionsException;
import net.turtle.math.exception.ParsingException;

public class BigRationalMatrixTest {

  @Test
  public void testBigMatrix() {
    {
      final BigRationalMatrix emptyMatrix = new BigRationalMatrix();
      Assert.assertEquals(0, emptyMatrix.getRowsCount());
      Assert.assertEquals(0, emptyMatrix.getColumnsCount());
    }
  }

  @Test
  public void testBigMatrix_String() {
    {
      final BigRationalMatrix emptyMatrix = new BigRationalMatrix("[]");
      Assert.assertEquals(0, emptyMatrix.getRowsCount());
      Assert.assertEquals(0, emptyMatrix.getColumnsCount());
    }
    {
      final BigRationalMatrix input = new BigRationalMatrix("[[1,2,3],[4,5,6],[7,8,9]]");
      Assert.assertEquals(3, input.getRowsCount());
      Assert.assertEquals(3, input.getColumnsCount());
    }
    {
      final BigRationalMatrix input = new BigRationalMatrix("[[2,3]]");
      Assert.assertEquals(1, input.getRowsCount());
      Assert.assertEquals(2, input.getColumnsCount());
    }
  }

  @Test(expected = DifferentDimensionsException.class)
  public void testBigMatrix_String_Dimentions() {
    {
      new BigRationalMatrix("[[1,2,3],[4,5],[6,7,8]]");
      Assert.fail();
    }
  }

  @Test(expected = ParsingException.class)
  public void testBigMatrix_String_MatrixParsing() {
    {
      new BigRationalMatrix("[1,2,3]");
      Assert.fail();
    }
  }

  @Test(expected = ParsingException.class)
  public void testBigMatrix_String_MatrixParsing2() {
    {
      new BigRationalMatrix("[[][]");
      Assert.fail();
    }
  }

  @Test
  public void testTranspose() {
    {
      final BigRationalMatrix input = new BigRationalMatrix("[]");
      final BigRationalMatrix output = input.transpose();
      Assert.assertEquals(0, output.getRowsCount());
      Assert.assertEquals(0, output.getColumnsCount());
    }
    {
      final BigRationalMatrix input = new BigRationalMatrix("[[1,2,3],[4,5,6]]");
      final BigRationalMatrix output = input.transpose();
      Assert.assertEquals(3, output.getRowsCount());
      Assert.assertEquals(2, output.getColumnsCount());

      final BigRationalVector rowVector0 = output.getRowVector(0);
      final List<BigRational> coordinates0 = rowVector0.getCoordinates();
      Assert.assertEquals(new BigRational("1"), coordinates0.get(0));
      Assert.assertEquals(new BigRational("4"), coordinates0.get(1));

      final BigRationalVector rowVector1 = output.getRowVector(1);
      final List<BigRational> coordinates1 = rowVector1.getCoordinates();
      Assert.assertEquals(new BigRational("2"), coordinates1.get(0));
      Assert.assertEquals(new BigRational("5"), coordinates1.get(1));

      final BigRationalVector rowVector2 = output.getRowVector(2);
      final List<BigRational> coordinates2 = rowVector2.getCoordinates();
      Assert.assertEquals(new BigRational("3"), coordinates2.get(0));
      Assert.assertEquals(new BigRational("6"), coordinates2.get(1));
    }
  }

  @Test
  public void testAddMatrix() {
    {
      final BigRationalMatrix input = new BigRationalMatrix("[]");
      final BigRationalMatrix output = input.add(input);
      Assert.assertEquals(0, output.getRowsCount());
      Assert.assertEquals(0, output.getColumnsCount());
    }
    {
      final BigRationalMatrix input = new BigRationalMatrix("[[1,2,3],[4,5,6]]");
      final BigRationalMatrix input2 = new BigRationalMatrix("[[7,8,9],[10,11,12]]");
      final BigRationalMatrix output = input.add(input2);
      Assert.assertEquals(2, output.getRowsCount());
      Assert.assertEquals(3, output.getColumnsCount());

      final BigRationalVector rowVector0 = output.getRowVector(0);
      final List<BigRational> coordinates0 = rowVector0.getCoordinates();
      Assert.assertEquals(new BigRational("8"), coordinates0.get(0));
      Assert.assertEquals(new BigRational("10"), coordinates0.get(1));
      Assert.assertEquals(new BigRational("12"), coordinates0.get(2));

      final BigRationalVector rowVector1 = output.getRowVector(1);
      final List<BigRational> coordinates1 = rowVector1.getCoordinates();
      Assert.assertEquals(new BigRational("14"), coordinates1.get(0));
      Assert.assertEquals(new BigRational("16"), coordinates1.get(1));
      Assert.assertEquals(new BigRational("18"), coordinates1.get(2));
    }
  }

  @Test
  public void testSubtractMatrix() {
    {
      final BigRationalMatrix input = new BigRationalMatrix("[]");
      final BigRationalMatrix output = input.substract(input);
      Assert.assertEquals(0, output.getRowsCount());
      Assert.assertEquals(0, output.getColumnsCount());
    }
    {
      final BigRationalMatrix input = new BigRationalMatrix("[[1,6,5],[12,7,11]]");
      final BigRationalMatrix input2 = new BigRationalMatrix("[[4,3,2],[9,10,8]]");
      final BigRationalMatrix output = input.substract(input2);
      Assert.assertEquals(2, output.getRowsCount());
      Assert.assertEquals(3, output.getColumnsCount());

      final BigRationalVector rowVector0 = output.getRowVector(0);
      final List<BigRational> coordinates0 = rowVector0.getCoordinates();
      Assert.assertEquals(new BigRational("-3"), coordinates0.get(0));
      Assert.assertEquals(new BigRational("3"), coordinates0.get(1));
      Assert.assertEquals(new BigRational("3"), coordinates0.get(2));

      final BigRationalVector rowVector1 = output.getRowVector(1);
      final List<BigRational> coordinates1 = rowVector1.getCoordinates();
      Assert.assertEquals(new BigRational("3"), coordinates1.get(0));
      Assert.assertEquals(new BigRational("-3"), coordinates1.get(1));
      Assert.assertEquals(new BigRational("3"), coordinates1.get(2));
    }
  }

  @Test
  public void multiplyByMatrix() {
    {
      final BigRationalMatrix input = new BigRationalMatrix("[]");
      final BigRationalMatrix output = input.multiply(input);
      Assert.assertEquals(0, output.getRowsCount());
      Assert.assertEquals(0, output.getColumnsCount());
    }
    {
      final BigRationalMatrix input = new BigRationalMatrix("[[1,0,2],[-1,3,1]]");
      final BigRationalMatrix input2 = new BigRationalMatrix("[[3,1],[2,1],[1,0]]");
      final BigRationalMatrix output = input.multiply(input2);
      Assert.assertEquals(2, output.getRowsCount());
      Assert.assertEquals(2, output.getColumnsCount());

      final BigRationalVector rowVector0 = output.getRowVector(0);
      final List<BigRational> coordinates0 = rowVector0.getCoordinates();
      Assert.assertEquals(new BigRational("5"), coordinates0.get(0));
      Assert.assertEquals(new BigRational("1"), coordinates0.get(1));

      final BigRationalVector rowVector1 = output.getRowVector(1);
      final List<BigRational> coordinates1 = rowVector1.getCoordinates();
      Assert.assertEquals(new BigRational("4"), coordinates1.get(0));
      Assert.assertEquals(new BigRational("2"), coordinates1.get(1));
    }

    {
      final BigRationalMatrix input = new BigRationalMatrix("[[2,3,5,7,11]]");
      final BigRationalMatrix input2 = new BigRationalMatrix("[[13],[17],[19],[23],[29]]");
      final BigRationalMatrix output = input.multiply(input2);
      Assert.assertEquals(1, output.getRowsCount());
      Assert.assertEquals(1, output.getColumnsCount());

      final BigRationalVector rowVector0 = output.getRowVector(0);
      final List<BigRational> coordinates0 = rowVector0.getCoordinates();
      Assert.assertEquals(new BigRational("652"), coordinates0.get(0));
    }
  }

  @Test(expected = CalculationException.class)
  public void multiplyByWrongMatrix() {
    {
      final BigRationalMatrix input = new BigRationalMatrix("[[1,0,2],[-1,3,1]]");
      final BigRationalMatrix input2 = new BigRationalMatrix("[[3,1],[2,1],[1,0],[1,0]]");
      input.multiply(input2);
      Assert.fail();
    }
  }

  @Test
  public void multiplyByBigRational() {
    {
      final BigRationalMatrix input = new BigRationalMatrix("[]");
      final BigRationalMatrix result = new BigRationalMatrix("[]");
      final BigRationalMatrix output = input.multiply((BigRational) null);
      Assert.assertEquals(result, output);
    }
    {
      final BigRationalMatrix input = new BigRationalMatrix("[]");
      final BigRationalMatrix result = new BigRationalMatrix("[]");
      final BigRationalMatrix output = input.multiply(new BigRational("2"));
      Assert.assertEquals(result, output);
    }
    {
      final BigRationalMatrix input = new BigRationalMatrix("[[1,0,2],[-1,3,1]]");
      final BigRationalMatrix result = new BigRationalMatrix("[[2,0,4],[-2,6,2]]");
      final BigRationalMatrix output = input.multiply(new BigRational("2"));
      Assert.assertEquals(result, output);
    }
  }

  @Test(expected = NullPointerException.class)
  public void multiplyByNullBigRational() {
    final BigRationalMatrix input = new BigRationalMatrix("[[2,3],[4,6]]");
    input.multiply((BigRational) null);
    Assert.fail();
  }

  @Test
  public void isSquare() {
    {
      final BigRationalMatrix input = new BigRationalMatrix("[]");
      Assert.assertTrue(input.isSquare());
    }
    {
      final BigRationalMatrix input = new BigRationalMatrix("[[1]]");
      Assert.assertTrue(input.isSquare());
    }
    {
      final BigRationalMatrix input = new BigRationalMatrix("[[1,0,2],[-1,3,1],[-1,3,1]]");
      Assert.assertTrue(input.isSquare());
    }
    {
      final BigRationalMatrix input = new BigRationalMatrix("[[1,0,2],[-1,3,1]]");
      Assert.assertFalse(input.isSquare());
    }
  }

  @Test
  public void isSimetric() {
    {
      final BigRationalMatrix input = new BigRationalMatrix("[]");
      Assert.assertTrue(input.isSimetric());
    }
    {
      final BigRationalMatrix input = new BigRationalMatrix("[[1]]");
      Assert.assertTrue(input.isSimetric());
    }
    {
      final BigRationalMatrix input = new BigRationalMatrix("[[2,3,5],[3,7,11],[5,11,13]]");
      Assert.assertTrue(input.isSimetric());
    }
    {
      final BigRationalMatrix input = new BigRationalMatrix("[[0,0],[0,0]]");
      Assert.assertTrue(input.isSimetric());
    }
    {
      final BigRationalMatrix input = new BigRationalMatrix("[[2,3],[5,7]]");
      Assert.assertFalse(input.isSimetric());
    }
  }

  @Test
  public void detCalculation() {
    {
      final BigRationalMatrix input = new BigRationalMatrix("[]");
      final BigRational output = input.det();
      Assert.assertEquals(BigRationalValues.ZERO, output);
    }
    {
      final BigRationalMatrix input = new BigRationalMatrix("[[2]]");
      final BigRational output = input.det();
      Assert.assertEquals(BigRationalValues.TWO, output);
    }
    {
      final BigRationalMatrix input = new BigRationalMatrix("[[2,3],[5,7]]");
      final BigRational output = input.det();
      Assert.assertEquals(new BigRational("-1"), output);
    }
    {
      final BigRationalMatrix input = new BigRationalMatrix("[[-2,2,-3],[-1,1,3],[2,0,-1]]");
      final BigRational output = input.det();
      Assert.assertEquals(new BigRational("18"), output);
    }
    {
      final BigRationalMatrix input =
          new BigRationalMatrix("[[1,3,0,-1],[0,2,1,3],[3,1,2,1],[-1,2,0,3]]");
      final BigRational output = input.det();
      Assert.assertEquals(new BigRational("14"), output);
    }
    {
      final BigRationalMatrix input =
          new BigRationalMatrix("[[0,1,2,7],[1,2,3,4],[5,6,7,8],[-1,1,-1,1]]");
      final BigRational output = input.det();
      Assert.assertEquals(new BigRational("-64"), output);
    }
    {
      final BigRationalMatrix input =
          new BigRationalMatrix(
              "[[2, 3, 5, 7, 11],[13, 17, 19, 23, 29],[31, 37, 41, 43, 47],[53, 59, 61, 67, 71],[73, 79, 83, 89, 97]]");
      final BigRational output = input.det();
      Assert.assertEquals(new BigRational("-4656"), output);
    }
    {
      final BigRationalMatrix input =
          new BigRationalMatrix(
              "[[0,1,2,3,4,5,6,7,8,9,10],[0,1,2,3,4,5,6,7,8,9,10],[0,1,2,3,4,5,6,7,8,9,10],[0,1,2,3,4,5,6,7,8,9,10],[0,1,2,3,4,5,6,7,8,9,10],[0,1,2,3,4,5,6,7,8,9,10],[0,1,2,3,4,5,6,7,8,9,10],[0,1,2,3,4,5,6,7,8,9,10],[0,1,2,3,4,5,6,7,8,9,10],[0,1,2,3,4,5,6,7,8,9,10],[0,1,2,3,4,5,6,7,8,9,10]]]");
      final BigRational output = input.det();
      Assert.assertEquals(new BigRational("-0"), output);
    }
    {
      final BigRationalMatrix input =
          new BigRationalMatrix(
              "[[0,1,2,3,4,5,6,7,8,9,10],[0,1,2,3,4,5,6,7,8,9,10],[0,1,2,3,4,5,6,7,8,9,10],[0,1,2,3,4,5,6,7,8,9,10],[0,1,2,3,4,5,6,7,8,9,10],[0,1,2,3,4,5,6,7,8,9,10],[0,1,2,3,4,5,6,7,8,9,10],[0,1,2,3,4,5,6,7,8,9,10],[0,1,2,3,4,5,6,7,8,9,10],[0,1,2,3,4,5,6,7,8,9,10],[0,1,2,3,4,5,6,7,8,9,10]]]");
      final BigRational output = input.det();
      Assert.assertEquals(new BigRational("-0"), output);
    }
  }

  @Test
  public void equalsHashContract() {
    {
      final BigRationalMatrix input1 = new BigRationalMatrix("[]");
      final BigRationalMatrix input2 = new BigRationalMatrix("[]");
      Assert.assertTrue(input1.equals(input1));
      Assert.assertTrue(input1.equals(input2));
      Assert.assertTrue(input1.hashCode() == input2.hashCode());
    }
    {
      final BigRationalMatrix input1 = new BigRationalMatrix("[[2]]");
      final BigRationalMatrix input2 = new BigRationalMatrix("[[4/2]]");
      Assert.assertTrue(input1.equals(input2));
      Assert.assertTrue(input1.hashCode() == input2.hashCode());
    }
    {
      final BigRationalMatrix input1 = new BigRationalMatrix("[[2,3],[5,7]]");
      final BigRationalMatrix input2 = new BigRationalMatrix("[[2,3],[5,7]]");
      Assert.assertTrue(input1.equals(input2));
    }
    {
      final BigRationalMatrix input1 = new BigRationalMatrix("[[-2,2,-3],[-1,1,3],[2,0,-1]]");
      final BigRationalMatrix input2 = new BigRationalMatrix("[[-2,2,-3],[-1,1,3],[2,0,-1]]");
      Assert.assertTrue(input1.equals(input2));
      Assert.assertTrue(input1.hashCode() == input2.hashCode());
    }
    {
      final BigRationalMatrix input1 = new BigRationalMatrix("[[-2,2,-3],[-1,1,3],[2,0,-1]]");
      final BigRationalMatrix input2 = new BigRationalMatrix("[[-2,2,-3],[-1,1,3],[2,0,1]]");
      Assert.assertFalse(input1.equals(input2));
    }
    {
      final BigRationalMatrix input1 = new BigRationalMatrix("[[-2,2,-3],[-1,1,3],[2,0,-1]]");
      Assert.assertFalse(input1.equals(BigInteger.ONE));
    }
    {
      final BigRationalMatrix input1 = new BigRationalMatrix("[[-2,2,-3],[-1,1,3],[2,0,-1]]");
      Assert.assertFalse(input1.equals(null));
    }
  }

  @Test(expected = CalculationException.class)
  public void testDet_NotSquare() {
    {
      {
        final BigRationalMatrix input =
            new BigRationalMatrix(
                "[[0,1,2,7],[1,2,3,4],[5,6,7,8],[-1,1,-1,1],[-1,1,-1,1],[-1,1,-1,1]]");
        input.det();
        Assert.fail();
      }
    }
  }

  @Test
  public void toStringResult() {
    {
      final BigRationalMatrix input = new BigRationalMatrix("[]");
      Assert.assertEquals("[]", input.toString());
    }
    {
      final BigRationalMatrix input = new BigRationalMatrix("[[1,2,3]]");
      Assert.assertEquals("[[1/1, 2/1, 3/1]]", input.toString());
    }
  }
}
