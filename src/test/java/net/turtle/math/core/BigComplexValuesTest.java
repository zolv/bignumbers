package net.turtle.math.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BigComplexValuesTest {

  @Test
  void initialize() throws Exception {
    Assertions.assertEquals(
        BigComplexValues.ZERO, BigComplexValues.ZERO.multiply(BigComplexValues.I));
  }
}
