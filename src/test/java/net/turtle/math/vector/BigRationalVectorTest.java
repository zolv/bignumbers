package net.turtle.math.vector;

import java.math.BigInteger;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import net.turtle.math.core.BigRational;
import net.turtle.math.exception.CalculationException;
import net.turtle.math.exception.DifferentDimensionsException;
import net.turtle.math.exception.ParsingException;
import net.turtle.math.vector.BigRationalVector;

public class BigRationalVectorTest {

  @Test
  public void testBigVector() {
    {
      Assertions.assertEquals(0, new BigRationalVector().getDimension());
    }
  }

  @Test
  public void testBigVector_String() {
    {
      final BigRationalVector input = new BigRationalVector("[]");
      Assertions.assertEquals(0, input.getDimension());
    }
    {
      final BigRationalVector input = new BigRationalVector("[111]");
      Assertions.assertEquals(1, input.getDimension());
      Assertions.assertEquals(new BigRational("111"), input.getCoordinates().get(0));
    }
    {
      final BigRationalVector input = new BigRationalVector("[2,3]");
      Assertions.assertEquals(2, input.getDimension());
      Assertions.assertEquals(new BigRational("2"), input.getCoordinates().get(0));
      Assertions.assertEquals(new BigRational("3"), input.getCoordinates().get(1));
    }
    {
      final BigRationalVector input = new BigRationalVector("[1.2,3.4,5/6,-7/8,9.10,0]");
      Assertions.assertEquals(6, input.getDimension());
      Assertions.assertEquals(new BigRational("1.2"), input.getCoordinates().get(0));
      Assertions.assertEquals(new BigRational("34/10"), input.getCoordinates().get(1));
      Assertions.assertEquals(new BigRational("5/6"), input.getCoordinates().get(2));
      Assertions.assertEquals(new BigRational("-7/8"), input.getCoordinates().get(3));
      Assertions.assertEquals(new BigRational("91/10"), input.getCoordinates().get(4));
      Assertions.assertEquals(new BigRational("0/1"), input.getCoordinates().get(5));
    }
  }

  @Test
  public void testBigVector_String_null() {
    Assertions.assertThrows(
        ParsingException.class,
        () -> {
          new BigRationalVector("test");
        });
  }

  @Test
  public void testAdd() {
    {
      final BigRationalVector bv1 =
          new BigRationalVector(new BigRational("1"), new BigRational("2"), new BigRational("3"));
      final BigRationalVector bv2 =
          new BigRationalVector(new BigRational("2"), new BigRational("4"), new BigRational("8"));
      final BigRationalVector r1 =
          new BigRationalVector(new BigRational("3"), new BigRational("6"), new BigRational("11"));
      Assertions.assertArrayEquals(
          r1.getCoordinates().toArray(), bv1.add(bv2).getCoordinates().toArray());
    }
  }

  @Test
  public void testAdd_Dimensions1() {
    Assertions.assertThrows(
        DifferentDimensionsException.class,
        () -> {
          final BigRationalVector bv1 =
              new BigRationalVector(
                  new BigRational("1"), new BigRational("2"), new BigRational("3"));
          final BigRationalVector bv2 =
              new BigRationalVector(new BigRational("2"), new BigRational("4"));
          bv1.add(bv2).getCoordinates().toArray();
        });
  }

  @Test
  public void testAdd_Dimensions2() {
    Assertions.assertThrows(
        DifferentDimensionsException.class,
        () -> {
          final BigRationalVector bv1 =
              new BigRationalVector(
                  new BigRational("1"), new BigRational("2"), new BigRational("3"));
          final BigRationalVector bv2 =
              new BigRationalVector(new BigRational("2"), new BigRational("4"));
          bv2.add(bv1).getCoordinates().toArray();
        });
  }

  @Test
  public void testAdd_Dimensions3() {
    {
      final BigRationalVector bv1 = new BigRationalVector();
      final BigRationalVector bv2 = new BigRationalVector();
      final BigRationalVector r1 = new BigRationalVector();
      Assertions.assertArrayEquals(
          r1.getCoordinates().toArray(), bv1.add(bv2).getCoordinates().toArray());
    }
  }

  @Test
  public void testSubstract() {
    {
      final BigRationalVector bv1 =
          new BigRationalVector(new BigRational("3"), new BigRational("2"), new BigRational("1"));
      final BigRationalVector bv2 =
          new BigRationalVector(new BigRational("2"), new BigRational("4"), new BigRational("6"));
      final BigRationalVector r1 =
          new BigRationalVector(new BigRational("1"), new BigRational("-2"), new BigRational("-5"));
      Assertions.assertArrayEquals(
          r1.getCoordinates().toArray(), bv1.subtract(bv2).getCoordinates().toArray());
    }
  }

  @Test
  public void testMultiply_BigRational() {
    {
      final BigRationalVector bv1 =
          new BigRationalVector(new BigRational("1"), new BigRational("2"), new BigRational("-3"));
      final BigRationalVector r1 =
          new BigRationalVector(new BigRational("3"), new BigRational("6"), new BigRational("-9"));
      Assertions.assertArrayEquals(
          r1.getCoordinates().toArray(),
          bv1.multiply(new BigRational("3")).getCoordinates().toArray());
    }
  }

  @Test
  public void testDivide_BigRational() {
    {
      final BigRationalVector bv1 =
          new BigRationalVector(new BigRational("1"), new BigRational("2"), new BigRational("-3"));
      final BigRationalVector r1 =
          new BigRationalVector(
              new BigRational("1/3"), new BigRational("2/3"), new BigRational("-1"));
      Assertions.assertArrayEquals(
          r1.getCoordinates().toArray(),
          bv1.divide(new BigRational("3")).getCoordinates().toArray());
    }
  }

  @Test
  public void testInverse() {
    {
      final BigRationalVector bv1 =
          new BigRationalVector(new BigRational("3"), new BigRational("2"), new BigRational("1"));
      final BigRationalVector r1 =
          new BigRationalVector(
              new BigRational("1/3"), new BigRational("1/2"), new BigRational("1"));
      Assertions.assertArrayEquals(
          r1.getCoordinates().toArray(), bv1.inverse().getCoordinates().toArray());
    }
  }

  @Test
  public void testNegate() {
    {
      final BigRationalVector bv1 =
          new BigRationalVector(new BigRational("3"), new BigRational("2"), new BigRational("1"));
      final BigRationalVector r1 =
          new BigRationalVector(
              new BigRational("-3"), new BigRational("-2"), new BigRational("-1"));
      Assertions.assertArrayEquals(
          r1.getCoordinates().toArray(), bv1.negate().getCoordinates().toArray());
    }
  }

  @Test
  public void equalsHashContract() {
    {
      final BigRationalVector input1 = new BigRationalVector("[]");
      final BigRationalVector input2 = new BigRationalVector("[]");
      Assertions.assertTrue(input1.equals(input1));
      Assertions.assertTrue(input1.equals(input2));
    }
    {
      final BigRationalVector input1 = new BigRationalVector("[2]");
      final BigRationalVector input2 = new BigRationalVector("[4/2]");
      Assertions.assertTrue(input1.equals(input2));
      Assertions.assertTrue(input1.hashCode() == input2.hashCode());
    }
    {
      final BigRationalVector input1 = new BigRationalVector("[2,3,5,7]");
      final BigRationalVector input2 = new BigRationalVector("[2,3,5,7]");
      Assertions.assertTrue(input1.equals(input2));
      Assertions.assertTrue(input1.hashCode() == input2.hashCode());
    }
    {
      final BigRationalVector input1 = new BigRationalVector("[2,3,5,7]");
      final BigRationalVector input2 = new BigRationalVector("[2,3,5,8]");
      Assertions.assertFalse(input1.equals(input2));
    }
    {
      final BigRationalVector input1 = new BigRationalVector("[2,3,5,7]");
      final BigRationalVector input2 = new BigRationalVector("[2,3,5,7,11]");
      Assertions.assertFalse(input1.equals(input2));
    }
    {
      final BigRationalVector input1 = new BigRationalVector("[2,3,5,7]");
      Assertions.assertFalse(input1.equals(BigInteger.ONE));
    }
    {
      final BigRationalVector input1 = new BigRationalVector("[2,3,5,7]");
      Assertions.assertFalse(input1.equals(null));
    }
  }
}
