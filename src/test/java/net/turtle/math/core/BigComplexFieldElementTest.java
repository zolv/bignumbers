package net.turtle.math.core;

import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class BigComplexFieldElementTest {

  /** a + ( b + c ) = (a + b ) + c */
  @Test
  void testAPlusBPlusC() {
    // given
    final var br1 = new BigComplex(new BigRational("1"), new BigRational("2"));
    final var br2 = new BigComplex(new BigRational("2"), new BigRational("4"));
    final var br3 = new BigComplex(new BigRational("3"), new BigRational("6"));
    final var expected = new BigComplex(new BigRational("6"), new BigRational("12"));

    // when
    final var gotResult1 = br1.add(br2.add(br3));
    final var gotResult2 = br1.add(br2).add(br3);

    // then
    Assertions.assertEquals(expected, gotResult1);
    Assertions.assertEquals(expected, gotResult2);
  }

  static Stream<Arguments> testAPlus0() {
    return Stream.of(
        Arguments.of(new BigComplex(new BigRational("1"), new BigRational("2"))),
        Arguments.of(new BigComplex(new BigRational("0"), new BigRational("0"))));
  }

  /** a + 0 = a */
  @ParameterizedTest
  @MethodSource
  void testAPlus0(BigComplex given) {
    // when
    final var got1 = given.add(BigComplexValues.ZERO);
    final var got2 = BigComplexValues.ZERO.add(given);

    // then
    Assertions.assertEquals(given, got1);
    Assertions.assertEquals(given, got2);
  }

  /** a + (-a) = 0 */
  @Test
  void testAPlusMinusA() {
    // given
    final var br1 = new BigComplex(new BigRational("1"), new BigRational("2"));
    final var br2 = new BigComplex(new BigRational("-1"), new BigRational("-2"));

    // when
    final var got = br1.add(br2);

    // then
    Assertions.assertEquals(BigComplexValues.ZERO, got);
  }

  /** a + 0 = a */
  @Test
  void testAPlusBBPlusA() {
    // given
    final var br1 = new BigComplex(new BigRational("1"), new BigRational("2"));
    final var br2 = new BigComplex(new BigRational("2"), new BigRational("4"));
    final var r1 = new BigComplex(new BigRational("3"), new BigRational("6"));

    // then
    Assertions.assertEquals(r1, br1.add(br2));
    Assertions.assertEquals(r1, br2.add(br1));
  }

  /** a * ( b * c ) = (a * b ) * c */
  @Test
  void testATimesBTimesC() {
    // given
    final var given1 = new BigComplex(new BigRational("1"), new BigRational("2"));
    final var given2 = new BigComplex(new BigRational("2"), new BigRational("4"));
    final var given3 = new BigComplex(new BigRational("3"), new BigRational("6"));
    final var expected = new BigComplex(new BigRational("-66"), new BigRational("-12"));

    // when
    final var got1 = given1.multiply(given2.multiply(given3));
    final var got2 = given1.multiply(given2).multiply(given3);

    // then
    Assertions.assertEquals(expected, got1);
    Assertions.assertEquals(expected, got2);
  }

  /** a * 1 = a */
  @Test
  void testATimes1() {
    // given
    final var given = new BigComplex(new BigRational("1"), new BigRational("2"));

    // when
    final var got1 = given.multiply(BigComplexValues.ONE);
    final var got2 = BigComplexValues.ONE.multiply(given);

    // then
    Assertions.assertEquals(given, got1);
    Assertions.assertEquals(given, got2);
  }

  /** a * (1/a) = 1 */
  @Test
  void testATimesInversedA() {
    // given
    final var br1 = new BigComplex(new BigRational("1"), new BigRational("2"));

    // when
    final var got = br1.multiply(br1.inverse());

    // then
    Assertions.assertEquals(BigComplexValues.ONE, got);
  }

  /** a * b = b * a */
  @Test
  void testATimesBBTimesA() {
    // given
    final var br1 = new BigComplex(new BigRational("1"), new BigRational("2"));
    final var br2 = new BigComplex(new BigRational("2"), new BigRational("4"));
    final var r1 = new BigComplex(new BigRational("-6"), new BigRational("8"));

    // when
    final var got1 = br1.multiply(br2);
    final var got2 = br2.multiply(br1);

    // then
    Assertions.assertEquals(r1, got1);
    Assertions.assertEquals(r1, got2);
  }

  /** a * ( b + c ) = (a * b ) + ( a * c ) */
  @Test
  void testATimesBPlusC() {
    // given
    final var br1 = new BigComplex(new BigRational("1"), new BigRational("2"));
    final var br2 = new BigComplex(new BigRational("2"), new BigRational("4"));
    final var br3 = new BigComplex(new BigRational("3"), new BigRational("6"));
    final var r1 = new BigComplex(new BigRational("-15"), new BigRational("20"));

    // when
    final var got1 = br1.multiply(br2.add(br3));
    final var got2 = br1.multiply(br2).add(br1.multiply(br3));

    // then
    Assertions.assertEquals(r1, got1);
    Assertions.assertEquals(r1, got2);
  }
}
