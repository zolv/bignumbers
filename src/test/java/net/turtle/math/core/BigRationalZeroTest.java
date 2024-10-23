package net.turtle.math.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.math.BigInteger;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class BigRationalZeroTest {

  private final BigRationalZero sut = new BigRationalZero();

  @Test
  void testNormalize() {
    // when
    var got = sut.normalize();

    // then
    assertSame(sut, got);
  }

  @Test
  void testNormalizeSignum() {
    // when
    var got = sut.normalizeSignum();

    // then
    assertSame(sut, got);
  }

  @Test
  void testCancel() {
    // when
    var got = sut.cancel();

    // then
    assertSame(sut, got);
  }

  static Stream<Arguments> testAdd() {
    return Stream.of(
        Arguments.of(new BigRational("-2"), new BigRational("-2")),
        Arguments.of(new BigRational("-1"), new BigRational("-1")),
        Arguments.of(new BigRational("0"), new BigRational("0")),
        Arguments.of(new BigRational("1"), new BigRational("1")),
        Arguments.of(new BigRational("2"), new BigRational("2")));
  }

  @ParameterizedTest
  @MethodSource
  void testAdd(BigRational givenAugend, BigRational expected) {
    // when
    var got = sut.add(givenAugend);

    // then
    assertEquals(expected, got);
  }

  static Stream<Arguments> testSubtract() {
    return Stream.of(
        Arguments.of(new BigRational("-2"), new BigRational("2")),
        Arguments.of(new BigRational("-1"), new BigRational("1")),
        Arguments.of(new BigRational("0"), new BigRational("0")),
        Arguments.of(new BigRational("1"), new BigRational("-1")),
        Arguments.of(new BigRational("2"), new BigRational("-2")));
  }

  @ParameterizedTest
  @MethodSource
  void testSubtract(BigRational givenSubtrahend, BigRational expected) {
    // when
    var got = sut.subtract(givenSubtrahend);

    // then
    assertEquals(expected, got);
  }

  static Stream<Arguments> testMultiply() {
    return Stream.of(
        Arguments.of(new BigRational("-2")),
        Arguments.of(new BigRational("-1")),
        Arguments.of(new BigRational("0")),
        Arguments.of(new BigRational("1")),
        Arguments.of(new BigRational("2")));
  }

  @ParameterizedTest
  @MethodSource
  void testMultiply(BigRational givenMultiplier) {
    // when
    var got = sut.multiply(givenMultiplier);

    // then
    assertEquals(BigRationalValues.ZERO, got);
  }

  @Test
  void testAbs() {
    // when
    var got = sut.abs();

    // then
    assertSame(sut, got);
  }

  @Test
  void testInverse() {
    assertThrows(ArithmeticException.class, () -> sut.inverse());
  }

  @Test
  void testSignum() {
    // when
    var got = sut.signum();

    // then
    assertEquals(0, got);
  }

  @Test
  void testSquare() {
    // when
    var got = sut.square();

    // then
    assertSame(sut, got);
  }

  @Test
  void testCube() {
    // when
    var got = sut.cube();

    // then
    assertSame(sut, got);
  }

  @Test
  void testBigRationalZero() {
    // then
    assertEquals(BigInteger.ZERO, sut.getNumerator());
    assertEquals(BigInteger.ONE, sut.getDenominator());
  }
}
