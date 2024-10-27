package net.turtle.math.numbers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigInteger;
import java.util.stream.Stream;
import net.turtle.math.core.BigRational;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class BigRationalZeroTest {

  private final BigRationalZero sut = new BigRationalZero();

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
        Arguments.of(new BigRational("-2")),
        Arguments.of(new BigRational("-1")),
        Arguments.of(new BigRational("0")),
        Arguments.of(new BigRational("1")),
        Arguments.of(new BigRational("2")));
  }

  @ParameterizedTest
  @MethodSource
  void testAdd(BigRational givenAugend) {
    // when
    final var got = this.sut.add(givenAugend);

    // then
    assertSame(givenAugend, got);
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
    assertEquals(this.sut, got);
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
    assertThrows(ArithmeticException.class, () -> this.sut.inverse());
  }

  @Test
  void testSignum() {
    // when
    final var got = this.sut.signum();

    // then
    assertEquals(0, got);
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
    assertSame(BigInteger.ZERO, this.sut.getNumerator());
    assertSame(BigInteger.ONE, this.sut.getDenominator());
  }
}
