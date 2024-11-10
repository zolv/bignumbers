package net.turtle.math.vector;

import java.math.BigInteger;
import net.turtle.math.core.BigRational;
import net.turtle.math.exception.DifferentDimensionsException;
import net.turtle.math.exception.ParsingException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BigRationalVectorTest {

  @Test
  void testBigVector() {
    {
      Assertions.assertEquals(0, new BigRationalVector().getDimension());
    }
  }

  @Test
  void testBigVector_String() {
    {
      final var input = new BigRationalVector("[]");
      Assertions.assertEquals(0, input.getDimension());
    }
    {
      final var input = new BigRationalVector("[111]");
      Assertions.assertEquals(1, input.getDimension());
      Assertions.assertEquals(new BigRational("111"), input.getCoordinates().get(0));
    }
    {
      final var input = new BigRationalVector("[2,3]");
      Assertions.assertEquals(2, input.getDimension());
      Assertions.assertEquals(new BigRational("2"), input.getCoordinates().get(0));
      Assertions.assertEquals(new BigRational("3"), input.getCoordinates().get(1));
    }
    {
      final var input = new BigRationalVector("[1.2,3.4,5/6,-7/8,9.10,0]");
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
  void testBigVector_String_null() {
    Assertions.assertThrows(
        ParsingException.class,
        () -> {
          new BigRationalVector("test");
        });
  }

  @Test
  void testAdd() {
    {
      final var bv1 =
          new BigRationalVector(new BigRational("1"), new BigRational("2"), new BigRational("3"));
      final var bv2 =
          new BigRationalVector(new BigRational("2"), new BigRational("4"), new BigRational("8"));
      final var r1 =
          new BigRationalVector(new BigRational("3"), new BigRational("6"), new BigRational("11"));
      Assertions.assertArrayEquals(
          r1.getCoordinates().toArray(), bv1.add(bv2).getCoordinates().toArray());
    }
  }

  @Test
  void testAdd_Dimensions1() {
    Assertions.assertThrows(
        DifferentDimensionsException.class,
        () -> {
          final var bv1 =
              new BigRationalVector(
                  new BigRational("1"), new BigRational("2"), new BigRational("3"));
          final var bv2 = new BigRationalVector(new BigRational("2"), new BigRational("4"));
          bv1.add(bv2).getCoordinates().toArray();
        });
  }

  @Test
  void testAdd_Dimensions2() {
    Assertions.assertThrows(
        DifferentDimensionsException.class,
        () -> {
          final var bv1 =
              new BigRationalVector(
                  new BigRational("1"), new BigRational("2"), new BigRational("3"));
          final var bv2 = new BigRationalVector(new BigRational("2"), new BigRational("4"));
          bv2.add(bv1).getCoordinates().toArray();
        });
  }

  @Test
  void testAdd_Dimensions3() {
    {
      final var bv1 = new BigRationalVector();
      final var bv2 = new BigRationalVector();
      final var r1 = new BigRationalVector();
      Assertions.assertArrayEquals(
          r1.getCoordinates().toArray(), bv1.add(bv2).getCoordinates().toArray());
    }
  }

  @Test
  void testSubstract() {
    {
      final var bv1 =
          new BigRationalVector(new BigRational("3"), new BigRational("2"), new BigRational("1"));
      final var bv2 =
          new BigRationalVector(new BigRational("2"), new BigRational("4"), new BigRational("6"));
      final var r1 =
          new BigRationalVector(new BigRational("1"), new BigRational("-2"), new BigRational("-5"));
      Assertions.assertArrayEquals(
          r1.getCoordinates().toArray(), bv1.subtract(bv2).getCoordinates().toArray());
    }
  }

  @Test
  void testMultiply_BigRational() {
    {
      final var bv1 =
          new BigRationalVector(new BigRational("1"), new BigRational("2"), new BigRational("-3"));
      final var r1 =
          new BigRationalVector(new BigRational("3"), new BigRational("6"), new BigRational("-9"));
      Assertions.assertArrayEquals(
          r1.getCoordinates().toArray(),
          bv1.multiply(new BigRational("3")).getCoordinates().toArray());
    }
  }

  @Test
  void testDivide_BigRational() {
    {
      final var bv1 =
          new BigRationalVector(new BigRational("1"), new BigRational("2"), new BigRational("-3"));
      final var r1 =
          new BigRationalVector(
              new BigRational("1/3"), new BigRational("2/3"), new BigRational("-1"));
      Assertions.assertArrayEquals(
          r1.getCoordinates().toArray(),
          bv1.divide(new BigRational("3")).getCoordinates().toArray());
    }
  }

  @Test
  void testInverse() {
    {
      final var bv1 =
          new BigRationalVector(new BigRational("3"), new BigRational("2"), new BigRational("1"));
      final var r1 =
          new BigRationalVector(
              new BigRational("1/3"), new BigRational("1/2"), new BigRational("1"));
      Assertions.assertArrayEquals(
          r1.getCoordinates().toArray(), bv1.inverse().getCoordinates().toArray());
    }
  }

  @Test
  void testNegate() {
    {
      final var bv1 =
          new BigRationalVector(new BigRational("3"), new BigRational("2"), new BigRational("1"));
      final var r1 =
          new BigRationalVector(
              new BigRational("-3"), new BigRational("-2"), new BigRational("-1"));
      Assertions.assertArrayEquals(
          r1.getCoordinates().toArray(), bv1.negate().getCoordinates().toArray());
    }
  }

  @Test
  void equalsHashContract() {
    {
      final var input1 = new BigRationalVector("[]");
      final var input2 = new BigRationalVector("[]");
      Assertions.assertTrue(input1.equals(input1));
      Assertions.assertTrue(input1.equals(input2));
    }
    {
      final var input1 = new BigRationalVector("[2]");
      final var input2 = new BigRationalVector("[4/2]");
      Assertions.assertTrue(input1.equals(input2));
      Assertions.assertTrue(input1.hashCode() == input2.hashCode());
    }
    {
      final var input1 = new BigRationalVector("[2,3,5,7]");
      final var input2 = new BigRationalVector("[2,3,5,7]");
      Assertions.assertTrue(input1.equals(input2));
      Assertions.assertTrue(input1.hashCode() == input2.hashCode());
    }
    {
      final var input1 = new BigRationalVector("[2,3,5,7]");
      final var input2 = new BigRationalVector("[2,3,5,8]");
      Assertions.assertFalse(input1.equals(input2));
    }
    {
      final var input1 = new BigRationalVector("[2,3,5,7]");
      final var input2 = new BigRationalVector("[2,3,5,7,11]");
      Assertions.assertFalse(input1.equals(input2));
    }
    {
      final var input1 = new BigRationalVector("[2,3,5,7]");
      Assertions.assertFalse(input1.equals(BigInteger.ONE));
    }
    {
      final var input1 = new BigRationalVector("[2,3,5,7]");
      Assertions.assertFalse(input1.equals(null));
    }
  }
}
