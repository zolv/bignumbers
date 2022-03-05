package net.turtle.math.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import net.turtle.math.core.BigComplex;

public class BigComplexUtilTest {

  @Test
  void testToStringShort() {
    {
      Assertions.assertEquals("0", BigComplexUtil.toStringShort(new BigComplex("0")));
      Assertions.assertEquals("i", BigComplexUtil.toStringShort(new BigComplex("i")));
      Assertions.assertEquals("-i", BigComplexUtil.toStringShort(new BigComplex("-i")));
      Assertions.assertEquals("0", BigComplexUtil.toStringShort(new BigComplex("-0-0i")));
      Assertions.assertEquals("2+3i", BigComplexUtil.toStringShort(new BigComplex("2+3i")));
      Assertions.assertEquals(
          "23/10+34/10i", BigComplexUtil.toStringShort(new BigComplex("2.3+3.4i")));
      Assertions.assertEquals(
          "-2/3-3/4i", BigComplexUtil.toStringShort(new BigComplex("-2/3-3/4i")));
    }
  }
}
