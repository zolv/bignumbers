package net.turtle.math.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BigRationalFieldElementTest {

  /** a + ( b + c ) = (a + b ) + c */
  @Test
  public void testAPlusBPlusC() {
    {
      final BigRational br1 = new BigRational("1", "2");
      final BigRational br2 = new BigRational("2", "4");
      final BigRational br3 = new BigRational("3", "6");
      final BigRational r1 = new BigRational("3", "2");
      Assertions.assertEquals(r1, br1.add(br2.add(br3)));
      Assertions.assertEquals(r1, br1.add(br2).add(br3));
    }
  }

  /** a + 0 = a */
  @Test
  public void testAPlus0() {
    {
      final BigRational br1 = new BigRational("1", "2");
      final BigRational br2 = new BigRational("0", "4");
      Assertions.assertEquals(br1, br1.add(br2));
      Assertions.assertEquals(br1, BigRationalValues.ZERO.add(br1));
    }
  }

  /** a + (-a) = 0 */
  @Test
  public void testAPlusMinusA() {
    {
      final BigRational br1 = new BigRational("1", "2");
      Assertions.assertEquals(BigRationalValues.ZERO, br1.add(br1.negate()));
    }
  }

  /** a + 0 = a */
  @Test
  public void testAPlusBBPlusA() {
    {
      final BigRational br1 = new BigRational("1", "2");
      final BigRational br2 = new BigRational("2", "4");
      Assertions.assertEquals(BigRationalValues.ONE, br1.add(br2));
      Assertions.assertEquals(BigRationalValues.ONE, br2.add(br1));
    }
  }

  /** a * ( b * c ) = (a * b ) * c */
  @Test
  public void testATimesBTimesC() {
    {
      final BigRational br1 = new BigRational("1", "2");
      final BigRational br2 = new BigRational("2", "4");
      final BigRational br3 = new BigRational("3", "6");
      final BigRational r1 = new BigRational("1", "8");
      Assertions.assertEquals(r1, br1.multiply(br2.multiply(br3)));
      Assertions.assertEquals(r1, br1.multiply(br2).multiply(br3));
    }
  }

  /** a + 0 = a */
  @Test
  public void testATimes1() {
    {
      final BigRational br1 = new BigRational("1", "2");
      final BigRational br2 = new BigRational("3", "3");
      Assertions.assertEquals(br1, br1.multiply(br2));
      Assertions.assertEquals(br1, BigRationalValues.ONE.multiply(br1));
    }
  }

  /** a * (1/a) = 1 */
  @Test
  public void testATimesInversedA() {
    {
      final BigRational br1 = new BigRational("1", "2");
      Assertions.assertEquals(BigRationalValues.ONE, br1.multiply(br1.inverse()));
    }
  }

  /** a * b = b * a */
  @Test
  public void testATimesBBTimesA() {
    {
      final BigRational br1 = new BigRational("1", "2");
      final BigRational br2 = new BigRational("2", "4");
      final BigRational r1 = new BigRational("1", "4");
      Assertions.assertEquals(r1, br1.multiply(br2));
      Assertions.assertEquals(r1, br2.multiply(br1));
    }
  }

  /** a * ( b + c ) = (a * b ) + ( a * c ) */
  @Test
  public void testATimesBPlusC() {
    {
      final BigRational br1 = new BigRational("1", "2");
      final BigRational br2 = new BigRational("2", "4");
      final BigRational br3 = new BigRational("3", "6");
      final BigRational r1 = new BigRational("1", "2");
      Assertions.assertEquals(r1, br1.multiply(br2.add(br3)));
      Assertions.assertEquals(r1, br1.multiply(br2).add(br1.multiply(br3)));
    }
  }
}
