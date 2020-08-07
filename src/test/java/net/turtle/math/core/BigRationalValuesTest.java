package net.turtle.math.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BigRationalValuesTest {

  @Test
  public void initialize() throws Exception {
    Assertions.assertEquals(
        BigRationalValues.ZERO, BigRationalValues.ZERO.multiply(BigRationalValues.ONE));
  }
}
