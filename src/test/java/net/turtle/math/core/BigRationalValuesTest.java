package net.turtle.math.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BigRationalValuesTest {

  @Test
  void initialize() throws Exception {
    Assertions.assertEquals(
        BigRationalValues.ZERO, BigRationalValues.ZERO.multiply(BigRationalValues.ONE));
  }
}
