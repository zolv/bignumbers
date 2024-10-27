package net.turtle.math.numbers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.math.BigInteger;
import java.util.stream.Stream;
import net.turtle.math.core.BigRational;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class BigRationalOneTest {

  private final BigRationalOne sut = new BigRationalOne();

  @Test
  void testNormalize() {
    // when
    final var got = this.sut.normalize();

    // then
    assertSame(this.sut, got);
  }

  @Test
  void testNormalizeSignum() {
    // when
    final var got = this.sut.normalizeSignum();

    // then
    assertSame(this.sut, got);
  }

  @Test
  void testCancel() {
    // when
    final var got = this.sut.cancel();

    // then
    assertSame(this.sut, got);
  }

  static Stream<Arguments> testAdd() {
    return Stream.of(
        Arguments.of(new BigRational("-2/3"), new BigRational("1/3")),
        Arguments.of(new BigRational("-1/3"), new BigRational("2/3")),
        Arguments.of(new BigRational("0/3"), new BigRational("3/3")),
        Arguments.of(new BigRational("1/3"), new BigRational("4/3")),
        Arguments.of(new BigRational("2/3"), new BigRational("5/3")));
  }

  @ParameterizedTest
  @MethodSource
  void testAdd(BigRational givenAugend, BigRational expected) {
    // when
    final var got = this.sut.add(givenAugend);

    // then
    assertEquals(expected, got);
  }

  static Stream<Arguments> testSubtract() {
    return Stream.of(
        Arguments.of(new BigRational("-2/3"), new BigRational("5/3")),
        Arguments.of(new BigRational("-1/3"), new BigRational("4/3")),
        Arguments.of(new BigRational("0/3"), new BigRational("3/3")),
        Arguments.of(new BigRational("1/3"), new BigRational("2/3")),
        Arguments.of(new BigRational("2/3"), new BigRational("1/3")));
  }

  @ParameterizedTest
  @MethodSource
  void testSubtract(BigRational givenSubtrahend, BigRational expected) {
    // when
    final var got = this.sut.subtract(givenSubtrahend);

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
    final var got = this.sut.multiply(givenMultiplier);

    // then
    assertEquals(givenMultiplier, got);
  }

  @Test
  void testAbs() {
    // when
    final var got = this.sut.abs();

    // then
    assertSame(this.sut, got);
  }

  @Test
  void testInverse() {
    // when
    final var got = this.sut.inverse();

    // then
    assertSame(this.sut, got);
  }

  @Test
  void testSignum() {
    // when
    final var got = this.sut.signum();

    // then
    assertEquals(1, got);
  }

  @Test
  void testSquare() {
    // when
    final var got = this.sut.square();

    // then
    assertSame(this.sut, got);
  }

  @Test
  void testCube() {
    // when
    final var got = this.sut.cube();

    // then
    assertSame(this.sut, got);
  }

  @Test
  void testBigRationalZero() {
    // then
    assertSame(BigInteger.ONE, this.sut.getNumerator());
    assertSame(BigInteger.ONE, this.sut.getDenominator());
  }
}
