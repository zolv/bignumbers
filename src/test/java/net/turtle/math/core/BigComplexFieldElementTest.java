package net.turtle.math.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BigComplexFieldElementTest {

  /** a + ( b + c ) = (a + b ) + c */
  @Test
  void testAPlusBPlusC() {
    {
      final BigComplex br1 = new BigComplex(new BigRational("1"), new BigRational("2"));
      final BigComplex br2 = new BigComplex(new BigRational("2"), new BigRational("4"));
      final BigComplex br3 = new BigComplex(new BigRational("3"), new BigRational("6"));
      final BigComplex r1 = new BigComplex(new BigRational("6"), new BigRational("12"));
      Assertions.assertEquals(r1, br1.add(br2.add(br3)));
      Assertions.assertEquals(r1, br1.add(br2).add(br3));
    }
  }

  /** a + 0 = a */
  @Test
  void testAPlus0() {
    {
      final BigComplex br1 = new BigComplex(new BigRational("1"), new BigRational("2"));
      final BigComplex br2 = new BigComplex(new BigRational("0"), new BigRational("0"));
      Assertions.assertEquals(br1, br1.add(br2));
      Assertions.assertEquals(br1, BigComplexValues.ZERO.add(br1));
    }
  }

  /** a + (-a) = 0 */
  @Test
  void testAPlusMinusA() {
    {
      final BigComplex br1 = new BigComplex(new BigRational("1"), new BigRational("2"));
      Assertions.assertEquals(BigComplexValues.ZERO, br1.add(br1.negate()));
    }
  }

  /** a + 0 = a */
  @Test
  void testAPlusBBPlusA() {
    {
      final BigComplex br1 = new BigComplex(new BigRational("1"), new BigRational("2"));
      final BigComplex br2 = new BigComplex(new BigRational("2"), new BigRational("4"));
      final BigComplex r1 = new BigComplex(new BigRational("3"), new BigRational("6"));
      Assertions.assertEquals(r1, br1.add(br2));
      Assertions.assertEquals(r1, br2.add(br1));
    }
  }

  /** a * ( b * c ) = (a * b ) * c */
  @Test
  void testATimesBTimesC() {
    {
      final BigComplex br1 = new BigComplex(new BigRational("1"), new BigRational("2"));
      final BigComplex br2 = new BigComplex(new BigRational("2"), new BigRational("4"));
      final BigComplex br3 = new BigComplex(new BigRational("3"), new BigRational("6"));
      final BigComplex r1 = new BigComplex(new BigRational("-66"), new BigRational("-12"));
      Assertions.assertEquals(r1, br1.multiply(br2.multiply(br3)));
      Assertions.assertEquals(r1, br1.multiply(br2).multiply(br3));
    }
  }

  /** a * 1 = a */
  @Test
  void testATimes1() {
    {
      final BigComplex br1 = new BigComplex(new BigRational("1"), new BigRational("2"));
      final BigComplex br2 = new BigComplex(new BigRational("1"), new BigRational("0"));
      Assertions.assertEquals(br1, br1.multiply(br2));
      Assertions.assertEquals(br1, BigComplexValues.ONE.multiply(br1));
    }
  }

  /** a * (1/a) = 1 */
  @Test
  void testATimesInversedA() {
    {
      final BigComplex br1 = new BigComplex(new BigRational("1"), new BigRational("2"));
      Assertions.assertEquals(BigComplexValues.ONE, br1.multiply(br1.inverse()));
    }
  }

  /** a * b = b * a */
  @Test
  void testATimesBBTimesA() {
    {
      final BigComplex br1 = new BigComplex(new BigRational("1"), new BigRational("2"));
      final BigComplex br2 = new BigComplex(new BigRational("2"), new BigRational("4"));
      final BigComplex r1 = new BigComplex(new BigRational("-6"), new BigRational("8"));
      Assertions.assertEquals(r1, br1.multiply(br2));
      Assertions.assertEquals(r1, br2.multiply(br1));
    }
  }

  /** a * ( b + c ) = (a * b ) + ( a * c ) */
  @Test
  void testATimesBPlusC() {
    {
      final BigComplex br1 = new BigComplex(new BigRational("1"), new BigRational("2"));
      final BigComplex br2 = new BigComplex(new BigRational("2"), new BigRational("4"));
      final BigComplex br3 = new BigComplex(new BigRational("3"), new BigRational("6"));
      final BigComplex r1 = new BigComplex(new BigRational("-15"), new BigRational("20"));
      Assertions.assertEquals(r1, br1.multiply(br2.add(br3)));
      Assertions.assertEquals(r1, br1.multiply(br2).add(br1.multiply(br3)));
    }
  }
}
