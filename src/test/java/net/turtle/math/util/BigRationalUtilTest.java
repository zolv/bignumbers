package net.turtle.math.util;

import java.math.BigInteger;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import net.turtle.math.core.BigRational;
import net.turtle.math.exception.CalculationException;
import net.turtle.math.exception.ParsingException;

public class BigRationalUtilTest {

  @Test
  public void testToStringNormalized() {
    Assertions.assertEquals("2", BigRationalUtil.toStringNormalized(new BigRational("2/1")));
    Assertions.assertEquals("-2", BigRationalUtil.toStringNormalized(new BigRational("2/-1")));
    Assertions.assertEquals("2/3", BigRationalUtil.toStringNormalized(new BigRational("2", "3")));
    Assertions.assertEquals("-2/3", BigRationalUtil.toStringNormalized(new BigRational("-2", "3")));
    Assertions.assertEquals("-2/3", BigRationalUtil.toStringNormalized(new BigRational("2", "-3")));
    Assertions.assertEquals("2/3", BigRationalUtil.toStringNormalized(new BigRational("-2", "-3")));
    Assertions.assertEquals(
        "-2/3", BigRationalUtil.toStringNormalized(new BigRational("+2", "-3")));
  }

  @Test
  public void testFactorial() {
    {
      final BigRational result = new BigRational("1");
      Assertions.assertEquals(result, BigRationalUtil.factorial(new BigRational("0")));
      Assertions.assertEquals(result, BigRationalUtil.factorial(new BigRational("1")));
    }
    {
      final BigRational result = new BigRational("120");
      Assertions.assertEquals(result, BigRationalUtil.factorial(new BigRational("5")));
    }
    {
      final BigRational result = new BigRational("2432902008176640000");
      Assertions.assertEquals(result, BigRationalUtil.factorial(new BigRational("20")));
    }
  }

  @Test
  public void testFactorial_negative() {
    Assertions.assertThrows(
        CalculationException.class,
        () -> {
          BigRationalUtil.factorial(new BigRational("-1"));
        });
  }

  @Test
  public void testFactorialBigInteger_null() {
    Assertions.assertThrows(
        NullPointerException.class,
        () -> {
          BigRationalUtil.factorial((BigInteger) null);
        });
  }

  @Test
  public void testFactorialBigRational_null() {
    Assertions.assertThrows(
        NullPointerException.class,
        () -> {
          BigRationalUtil.factorial((BigRational) null);
        });
  }

  @Test
  public void testAsBigInteger() {
    Assertions.assertEquals(BigInteger.ONE, BigRationalUtil.asBigInteger(new BigRational("3/3")));
  }

  @Test
  public void testBigTenToThe() {
    {
      final BigInteger result = new BigInteger("0");
      Assertions.assertEquals(result, BigRationalUtil.bigTenToThe(-1));
    }
    {
      final BigInteger result = new BigInteger("1");
      Assertions.assertEquals(result, BigRationalUtil.bigTenToThe(0));
    }
    {
      final BigInteger result = new BigInteger("1000");
      Assertions.assertEquals(result, BigRationalUtil.bigTenToThe(3));
    }
  }
}
