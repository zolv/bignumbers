package net.turtle.math.vector;

import net.turtle.math.core.BigComplex;
import net.turtle.math.exception.CalculationException;
import net.turtle.math.exception.ParsingException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BigComplexVectorTest {

  @Test
  void testBigVector() {
    {
      Assertions.assertEquals(0, new BigComplexVector().getDimension());
    }
  }

  @Test
  void testBigVector_String() {
    {
      final var input = new BigComplexVector("[]");
      Assertions.assertEquals(0, input.getDimension());
    }
    {
      final var input = new BigComplexVector("[111]");
      Assertions.assertEquals(1, input.getDimension());
      Assertions.assertEquals(new BigComplex("111"), input.getCoordinates().get(0));
    }
    {
      final var input = new BigComplexVector("[2,3]");
      Assertions.assertEquals(2, input.getDimension());
      Assertions.assertEquals(new BigComplex("2"), input.getCoordinates().get(0));
      Assertions.assertEquals(new BigComplex("3"), input.getCoordinates().get(1));
    }
    {
      final var input = new BigComplexVector("[1.2,3.4,5/6,-7/8,9.10,0]");
      Assertions.assertEquals(6, input.getDimension());
      Assertions.assertEquals(new BigComplex("1.2"), input.getCoordinates().get(0));
      Assertions.assertEquals(new BigComplex("34/10"), input.getCoordinates().get(1));
      Assertions.assertEquals(new BigComplex("5/6"), input.getCoordinates().get(2));
      Assertions.assertEquals(new BigComplex("-7/8"), input.getCoordinates().get(3));
      Assertions.assertEquals(new BigComplex("91/10"), input.getCoordinates().get(4));
      Assertions.assertEquals(new BigComplex("0/1"), input.getCoordinates().get(5));
    }
  }

  @Test
  void testBigVector_String_null() {
    Assertions.assertThrows(
        ParsingException.class,
        () -> {
          new BigComplexVector("test");
        });
  }

  @Test
  void testAdd() {
    {
      final var bv1 =
          new BigComplexVector(new BigComplex("1"), new BigComplex("2"), new BigComplex("3"));
      final var bv2 =
          new BigComplexVector(new BigComplex("2"), new BigComplex("4"), new BigComplex("8"));
      final var r1 =
          new BigComplexVector(new BigComplex("3"), new BigComplex("6"), new BigComplex("11"));
      Assertions.assertArrayEquals(
          r1.getCoordinates().toArray(), bv1.add(bv2).getCoordinates().toArray());
    }
  }

  @Test
  void testAdd_Dimensions1() {
    Assertions.assertThrows(
        CalculationException.class,
        () -> {
          final var bv1 =
              new BigComplexVector(new BigComplex("1"), new BigComplex("2"), new BigComplex("3"));
          final var bv2 = new BigComplexVector(new BigComplex("2"), new BigComplex("4"));
          bv1.add(bv2).getCoordinates().toArray();
        });
  }

  @Test
  void testAdd_Dimensions2() {
    Assertions.assertThrows(
        CalculationException.class,
        () -> {
          final var bv1 =
              new BigComplexVector(new BigComplex("1"), new BigComplex("2"), new BigComplex("3"));
          final var bv2 = new BigComplexVector(new BigComplex("2"), new BigComplex("4"));
          bv2.add(bv1).getCoordinates().toArray();
        });
  }

  @Test
  void testAdd_Dimensions3() {
    {
      final var bv1 = new BigComplexVector();
      final var bv2 = new BigComplexVector();
      final var r1 = new BigComplexVector();
      Assertions.assertArrayEquals(
          r1.getCoordinates().toArray(), bv1.add(bv2).getCoordinates().toArray());
    }
  }

  @Test
  void testSubstract() {
    {
      final var bv1 =
          new BigComplexVector(new BigComplex("3"), new BigComplex("2"), new BigComplex("1"));
      final var bv2 =
          new BigComplexVector(new BigComplex("2"), new BigComplex("4"), new BigComplex("6"));
      final var r1 =
          new BigComplexVector(new BigComplex("1"), new BigComplex("-2"), new BigComplex("-5"));
      Assertions.assertArrayEquals(
          r1.getCoordinates().toArray(), bv1.subtract(bv2).getCoordinates().toArray());
    }
  }

  @Test
  void testMultiply_BigComplex() {
    {
      final var bv1 =
          new BigComplexVector(new BigComplex("1"), new BigComplex("2"), new BigComplex("-3"));
      final var r1 =
          new BigComplexVector(new BigComplex("3"), new BigComplex("6"), new BigComplex("-9"));
      Assertions.assertArrayEquals(
          r1.getCoordinates().toArray(),
          bv1.multiply(new BigComplex("3")).getCoordinates().toArray());
    }
  }

  @Test
  void testDivide_BigComplex() {
    {
      final var bv1 =
          new BigComplexVector(new BigComplex("1"), new BigComplex("2"), new BigComplex("-3"));
      final var r1 =
          new BigComplexVector(new BigComplex("1/3"), new BigComplex("2/3"), new BigComplex("-1"));
      Assertions.assertArrayEquals(
          r1.getCoordinates().toArray(),
          bv1.divide(new BigComplex("3")).getCoordinates().toArray());
    }
  }

  @Test
  void testInverse() {
    {
      final var bv1 =
          new BigComplexVector(new BigComplex("3"), new BigComplex("2"), new BigComplex("1"));
      final var r1 =
          new BigComplexVector(new BigComplex("1/3"), new BigComplex("1/2"), new BigComplex("1"));
      Assertions.assertArrayEquals(
          r1.getCoordinates().toArray(), bv1.inverse().getCoordinates().toArray());
    }
  }

  @Test
  void testNegate() {
    {
      final var bv1 =
          new BigComplexVector(new BigComplex("3"), new BigComplex("2"), new BigComplex("1"));
      final var r1 =
          new BigComplexVector(new BigComplex("-3"), new BigComplex("-2"), new BigComplex("-1"));
      Assertions.assertArrayEquals(
          r1.getCoordinates().toArray(), bv1.negate().getCoordinates().toArray());
    }
  }

  @Test
  void testConjugate() {
    {
      final var input = new BigComplexVector("[]");
      Assertions.assertEquals(0, input.conjugate().getDimension());
    }
    {
      final var input = new BigComplexVector("[1.2+3.4i,5/6-7/8i,9.10+0i]");
      final var result = input.conjugate();
      Assertions.assertEquals(3, result.getDimension());
      Assertions.assertEquals(new BigComplex("1.2-3.4i"), result.getCoordinates().get(0));
      Assertions.assertEquals(new BigComplex("5/6+7/8i"), result.getCoordinates().get(1));
      Assertions.assertEquals(new BigComplex("9.10-0i"), result.getCoordinates().get(2));
    }
  }
}
