package net.turtle.math.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BigRationalFieldElementTest {

  /** a + ( b + c ) = (a + b ) + c */
  @Test
  void testAPlusBPlusC() {
    {
      final var br1 = new BigRational("1", "2");
      final var br2 = new BigRational("2", "4");
      final var br3 = new BigRational("3", "6");
      final var r1 = new BigRational("3", "2");
      Assertions.assertEquals(r1, br1.add(br2.add(br3)));
      Assertions.assertEquals(r1, br1.add(br2).add(br3));
    }
  }

  /** a + 0 = a */
  @Test
  void testAPlus0() {
    {
      final var br1 = new BigRational("1", "2");
      final var br2 = new BigRational("0", "4");
      Assertions.assertEquals(br1, br1.add(br2));
      Assertions.assertEquals(br1, BigRationalValues.ZERO.add(br1));
    }
  }

  /** a + (-a) = 0 */
  @Test
  void testAPlusMinusA() {
    {
      final var br1 = new BigRational("1", "2");
      Assertions.assertEquals(BigRationalValues.ZERO, br1.add(br1.negate()));
    }
  }

  /** a + 0 = a */
  @Test
  void testAPlusBBPlusA() {
    {
      final var br1 = new BigRational("1", "2");
      final var br2 = new BigRational("2", "4");
      Assertions.assertEquals(BigRationalValues.ONE, br1.add(br2));
      Assertions.assertEquals(BigRationalValues.ONE, br2.add(br1));
    }
  }

  /** a * ( b * c ) = (a * b ) * c */
  @Test
  void testATimesBTimesC() {
    {
      final var br1 = new BigRational("1", "2");
      final var br2 = new BigRational("2", "4");
      final var br3 = new BigRational("3", "6");
      final var r1 = new BigRational("1", "8");
      Assertions.assertEquals(r1, br1.multiply(br2.multiply(br3)));
      Assertions.assertEquals(r1, br1.multiply(br2).multiply(br3));
    }
  }

  /** a + 0 = a */
  @Test
  void testATimes1() {
    {
      final var br1 = new BigRational("1", "2");
      final var br2 = new BigRational("3", "3");
      Assertions.assertEquals(br1, br1.multiply(br2));
      Assertions.assertEquals(br1, BigRationalValues.ONE.multiply(br1));
    }
  }

  /** a * (1/a) = 1 */
  @Test
  void testATimesInversedA() {
    {
      final var br1 = new BigRational("1", "2");
      Assertions.assertEquals(BigRationalValues.ONE, br1.multiply(br1.inverse()));
    }
  }

  /** a * b = b * a */
  @Test
  void testATimesBBTimesA() {
    {
      final var br1 = new BigRational("1", "2");
      final var br2 = new BigRational("2", "4");
      final var r1 = new BigRational("1", "4");
      Assertions.assertEquals(r1, br1.multiply(br2));
      Assertions.assertEquals(r1, br2.multiply(br1));
    }
  }

  /** a * ( b + c ) = (a * b ) + ( a * c ) */
  @Test
  void testATimesBPlusC() {
    {
      final var br1 = new BigRational("1", "2");
      final var br2 = new BigRational("2", "4");
      final var br3 = new BigRational("3", "6");
      final var r1 = new BigRational("1", "2");
      Assertions.assertEquals(r1, br1.multiply(br2.add(br3)));
      Assertions.assertEquals(r1, br1.multiply(br2).add(br1.multiply(br3)));
    }
  }
}
