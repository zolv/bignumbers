package net.turtle.math.core;

import org.junit.Assert;
import org.junit.Test;

import net.turtle.math.exception.CalculationException;
import net.turtle.math.exception.ParsingException;

public class BigComplexVectorTest {

  @Test
  public void testBigVector() {
    {
      Assert.assertEquals(0, new BigComplexVector().getDimension());
    }
  }

  @Test
  public void testBigVector_String() {
    {
      final BigComplexVector input = new BigComplexVector("[]");
      Assert.assertEquals(0, input.getDimension());
    }
    {
      final BigComplexVector input = new BigComplexVector("[111]");
      Assert.assertEquals(1, input.getDimension());
      Assert.assertEquals(new BigComplex("111"), input.getCoordinates().get(0));
    }
    {
      final BigComplexVector input = new BigComplexVector("[2,3]");
      Assert.assertEquals(2, input.getDimension());
      Assert.assertEquals(new BigComplex("2"), input.getCoordinates().get(0));
      Assert.assertEquals(new BigComplex("3"), input.getCoordinates().get(1));
    }
    {
      final BigComplexVector input = new BigComplexVector("[1.2,3.4,5/6,-7/8,9.10,0]");
      Assert.assertEquals(6, input.getDimension());
      Assert.assertEquals(new BigComplex("1.2"), input.getCoordinates().get(0));
      Assert.assertEquals(new BigComplex("34/10"), input.getCoordinates().get(1));
      Assert.assertEquals(new BigComplex("5/6"), input.getCoordinates().get(2));
      Assert.assertEquals(new BigComplex("-7/8"), input.getCoordinates().get(3));
      Assert.assertEquals(new BigComplex("91/10"), input.getCoordinates().get(4));
      Assert.assertEquals(new BigComplex("0/1"), input.getCoordinates().get(5));
    }
  }

  @Test(expected = ParsingException.class)
  public void testBigVector_String_null() {
    {
      new BigComplexVector("test");
      Assert.fail();
    }
  }

  @Test
  public void testAdd() {
    {
      final BigComplexVector bv1 =
          new BigComplexVector(new BigComplex("1"), new BigComplex("2"), new BigComplex("3"));
      final BigComplexVector bv2 =
          new BigComplexVector(new BigComplex("2"), new BigComplex("4"), new BigComplex("8"));
      final BigComplexVector r1 =
          new BigComplexVector(new BigComplex("3"), new BigComplex("6"), new BigComplex("11"));
      Assert.assertArrayEquals(
          r1.getCoordinates().toArray(), bv1.add(bv2).getCoordinates().toArray());
    }
  }

  @Test(expected = CalculationException.class)
  public void testAdd_Dimensions1() {
    {
      final BigComplexVector bv1 =
          new BigComplexVector(new BigComplex("1"), new BigComplex("2"), new BigComplex("3"));
      final BigComplexVector bv2 = new BigComplexVector(new BigComplex("2"), new BigComplex("4"));
      bv1.add(bv2).getCoordinates().toArray();
      Assert.fail();
    }
  }

  @Test(expected = CalculationException.class)
  public void testAdd_Dimensions2() {
    {
      final BigComplexVector bv1 =
          new BigComplexVector(new BigComplex("1"), new BigComplex("2"), new BigComplex("3"));
      final BigComplexVector bv2 = new BigComplexVector(new BigComplex("2"), new BigComplex("4"));
      bv2.add(bv1).getCoordinates().toArray();
      Assert.fail();
    }
  }

  @Test
  public void testAdd_Dimensions3() {
    {
      final BigComplexVector bv1 = new BigComplexVector();
      final BigComplexVector bv2 = new BigComplexVector();
      final BigComplexVector r1 = new BigComplexVector();
      Assert.assertArrayEquals(
          r1.getCoordinates().toArray(), bv1.add(bv2).getCoordinates().toArray());
    }
  }

  @Test
  public void testSubstract() {
    {
      final BigComplexVector bv1 =
          new BigComplexVector(new BigComplex("3"), new BigComplex("2"), new BigComplex("1"));
      final BigComplexVector bv2 =
          new BigComplexVector(new BigComplex("2"), new BigComplex("4"), new BigComplex("6"));
      final BigComplexVector r1 =
          new BigComplexVector(new BigComplex("1"), new BigComplex("-2"), new BigComplex("-5"));
      Assert.assertArrayEquals(
          r1.getCoordinates().toArray(), bv1.subtract(bv2).getCoordinates().toArray());
    }
  }

  @Test
  public void testMultiply_BigComplex() {
    {
      final BigComplexVector bv1 =
          new BigComplexVector(new BigComplex("1"), new BigComplex("2"), new BigComplex("-3"));
      final BigComplexVector r1 =
          new BigComplexVector(new BigComplex("3"), new BigComplex("6"), new BigComplex("-9"));
      Assert.assertArrayEquals(
          r1.getCoordinates().toArray(),
          bv1.multiply(new BigComplex("3")).getCoordinates().toArray());
    }
  }

  @Test
  public void testDivide_BigComplex() {
    {
      final BigComplexVector bv1 =
          new BigComplexVector(new BigComplex("1"), new BigComplex("2"), new BigComplex("-3"));
      final BigComplexVector r1 =
          new BigComplexVector(new BigComplex("1/3"), new BigComplex("2/3"), new BigComplex("-1"));
      Assert.assertArrayEquals(
          r1.getCoordinates().toArray(),
          bv1.divide(new BigComplex("3")).getCoordinates().toArray());
    }
  }

  @Test
  public void testInverse() {
    {
      final BigComplexVector bv1 =
          new BigComplexVector(new BigComplex("3"), new BigComplex("2"), new BigComplex("1"));
      final BigComplexVector r1 =
          new BigComplexVector(new BigComplex("1/3"), new BigComplex("1/2"), new BigComplex("1"));
      Assert.assertArrayEquals(
          r1.getCoordinates().toArray(), bv1.inverse().getCoordinates().toArray());
    }
  }

  @Test
  public void testNegate() {
    {
      final BigComplexVector bv1 =
          new BigComplexVector(new BigComplex("3"), new BigComplex("2"), new BigComplex("1"));
      final BigComplexVector r1 =
          new BigComplexVector(new BigComplex("-3"), new BigComplex("-2"), new BigComplex("-1"));
      Assert.assertArrayEquals(
          r1.getCoordinates().toArray(), bv1.negate().getCoordinates().toArray());
    }
  }

  @Test
  public void testConjugate() {
    {
      final BigComplexVector input = new BigComplexVector("[]");
      Assert.assertEquals(0, input.conjugate().getDimension());
    }
    {
      final BigComplexVector input = new BigComplexVector("[1.2+3.4i,5/6-7/8i,9.10+0i]");
      final BigComplexVector result = input.conjugate();
      Assert.assertEquals(3, result.getDimension());
      Assert.assertEquals(new BigComplex("1.2-3.4i"), result.getCoordinates().get(0));
      Assert.assertEquals(new BigComplex("5/6+7/8i"), result.getCoordinates().get(1));
      Assert.assertEquals(new BigComplex("9.10-0i"), result.getCoordinates().get(2));
    }
  }
}
