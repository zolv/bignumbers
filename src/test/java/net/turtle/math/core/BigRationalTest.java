package net.turtle.math.core;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.Random;
import java.util.stream.Stream;
import net.turtle.math.exception.CalculationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class BigRationalTest {

  @Test
  void testBigRational() {
    // given
    final BigRational br = new BigRational();

    // when
    final BigInteger gotDenominator = br.getDenominator();
    final BigInteger gotNumerator = br.getNumerator();

    // then
    Assertions.assertEquals(new BigInteger("0"), gotNumerator);
    Assertions.assertEquals(new BigInteger("1"), gotDenominator);
  }

  static Stream<Arguments> testBigRational_String() {
    return Stream.of(
        Arguments.of("", new BigInteger("0"), new BigInteger("1")),
        Arguments.of("-123/456", new BigInteger("-123"), new BigInteger("456")),
        Arguments.of("-123/-456", new BigInteger("-123"), new BigInteger("-456")),
        Arguments.of("-123.456", new BigInteger("-123456"), new BigInteger("1000")),
        Arguments.of("-123456", new BigInteger("-123456"), new BigInteger("1")));
  }

  @ParameterizedTest
  @MethodSource
  void testBigRational_String(
      String given, BigInteger expectedNumerator, BigInteger expectedDenominator) {
    // when
    final BigRational got = new BigRational(given);

    // then
    Assertions.assertEquals(expectedNumerator, got.getNumerator());
    Assertions.assertEquals(expectedDenominator, got.getDenominator());
  }

  static Stream<Arguments> testBigRational_BigDecimal() {
    return Stream.of(
        Arguments.of(
            new BigDecimal(new BigInteger("2"), 6), new BigInteger("2"), new BigInteger("1000000")),
        Arguments.of(
            new BigDecimal(new BigInteger("2"), -6),
            new BigInteger("2000000"),
            new BigInteger("1")),
        Arguments.of(
            new BigDecimal(new BigInteger("2"), 0), new BigInteger("2"), new BigInteger("1")),
        Arguments.of(
            new BigDecimal("-123.456"), new BigInteger("-123456"), new BigInteger("1000")));
  }

  @ParameterizedTest
  @MethodSource
  void testBigRational_BigDecimal(
      BigDecimal given, BigInteger expectedNumerator, BigInteger expectedDenominator) {

    // when
    final BigRational got = new BigRational(given);

    // then
    Assertions.assertEquals(expectedNumerator, got.getNumerator());
    Assertions.assertEquals(expectedDenominator, got.getDenominator());
  }

  static Stream<Arguments> testBigRational_BigInteger() {
    return Stream.of(
        Arguments.of(new BigInteger("0")),
        Arguments.of(new BigInteger("1")),
        Arguments.of(new BigInteger("-1")));
  }

  @ParameterizedTest
  @MethodSource
  void testBigRational_BigInteger(BigInteger given) {
    // when
    final BigRational got = new BigRational(given);

    // then
    Assertions.assertSame(given, got.getNumerator());
    Assertions.assertEquals(new BigInteger("1"), got.getDenominator());
  }

  static Stream<Arguments> testBigRational_BigInteger_BigInteger() {
    return Stream.of(
        Arguments.of(new BigInteger("2"), new BigInteger("3")),
        Arguments.of(new BigInteger("2"), new BigInteger("-3")),
        Arguments.of(new BigInteger("-2"), new BigInteger("3")),
        Arguments.of(new BigInteger("-2"), new BigInteger("-3")));
  }

  @ParameterizedTest
  @MethodSource
  void testBigRational_BigInteger_BigInteger(
      BigInteger givenNumertor, BigInteger givenDenominator) {
    // when
    final BigRational got = new BigRational(givenNumertor, givenDenominator);

    // then
    Assertions.assertSame(givenNumertor, got.getNumerator());
    Assertions.assertSame(givenDenominator, got.getDenominator());
  }

  @Test
  void testBigRational_BigInteger_BigInteger_boolean() {
    {
      final BigRational br = new BigRational(new BigInteger("4"), new BigInteger("2"), true);
      Assertions.assertEquals(new BigInteger("2"), br.getNumerator());
      Assertions.assertEquals(new BigInteger("1"), br.getDenominator());
    }
  }

  @Test
  void testBigRational_BigInteger_BigInteger_DivisionByZeroException() {
    Assertions.assertThrows(
        ArithmeticException.class,
        () -> {
          final Random r = new Random(new Date().getTime());
          final String randomNumerator = Long.valueOf(r.nextLong()).toString();
          new BigRational(new BigInteger(randomNumerator), new BigInteger("0"));
        });
  }

  static Stream<Arguments> testBigRational_BigInteger_BigInteger_null() {
    return Stream.of(
        Arguments.of((BigInteger) null, null),
        Arguments.of((BigInteger) null, BigInteger.valueOf(2)),
        Arguments.of(BigInteger.valueOf(2), (BigInteger) null));
  }

  @ParameterizedTest
  @MethodSource
  void testBigRational_BigInteger_BigInteger_null(
      BigInteger givenNumertor, BigInteger givenDenominator) {
    // when, then
    Assertions.assertThrows(
        NullPointerException.class, () -> new BigRational(givenNumertor, givenDenominator));
  }

  @Test
  void testGetNumerator() {
    Assertions.assertEquals(
        new BigInteger("5"), new BigRational(new BigInteger("5")).getNumerator());
    Assertions.assertEquals(
        new BigInteger("5"), new BigRational(new BigInteger("5")).getDividend());
  }

  @Test
  void testGetDenominator() {
    Assertions.assertEquals(
        new BigInteger("5"),
        new BigRational(new BigInteger("1"), new BigInteger("5")).getDenominator());
    Assertions.assertEquals(
        new BigInteger("5"),
        new BigRational(new BigInteger("1"), new BigInteger("5")).getDivisor());
  }

  static Stream<Arguments> testNormalize_needed() {
    return Stream.of(
        Arguments.of(new BigRational("4", "6"), new BigRational("2", "3")),
        Arguments.of(new BigRational("4", "-6"), new BigRational("-2", "3")),
        Arguments.of(new BigRational("-4", "6"), new BigRational("-2", "3")),
        Arguments.of(new BigRational("-4", "-6"), new BigRational("2", "3")),
        Arguments.of(new BigRational("0", "3"), new BigRational("0", "1")));
  }

  @ParameterizedTest
  @MethodSource
  void testNormalize_needed(BigRational given, BigRational expected) {
    // when
    final BigRational got = given.normalize();

    // then
    Assertions.assertEquals(expected, got);
  }

  static Stream<Arguments> testNormalize_notNeeded() {
    return Stream.of(
        Arguments.of(new BigRational("2", "3")), Arguments.of(new BigRational("-2", "3")));
  }

  @ParameterizedTest
  @MethodSource
  void testNormalize_notNeeded(BigRational given) {
    // when
    final BigRational got = given.normalize();

    // then
    Assertions.assertSame(given, got);
  }

  static Stream<Arguments> testNormalizeSignum() {
    return Stream.of(
        Arguments.of(new BigRational("2", "3"), new BigRational("2", "3")),
        Arguments.of(new BigRational("2", "-3"), new BigRational("-2", "3")),
        Arguments.of(new BigRational("-2", "3"), new BigRational("-2", "3")),
        Arguments.of(new BigRational("-2", "-3"), new BigRational("2", "3")),
        Arguments.of(new BigRational("0", "-3"), new BigRational("0", "1")));
  }

  @ParameterizedTest
  @MethodSource
  void testNormalizeSignum(BigRational given, BigRational expected) {
    // when
    final BigRational got = given.normalize();

    // then
    Assertions.assertEquals(expected, got);
  }

  @Test
  void testCancel_zero() {
    // given
    final BigRational given = new BigRational("0/256");

    // when
    final BigRational got = given.cancel();

    // then
    Assertions.assertEquals(BigInteger.ZERO, got.getNumerator());
    Assertions.assertEquals(BigInteger.ONE, got.getDenominator());
  }

  @Test
  void testCancel_gcdCheck() {
    // given
    final var productOfFirst100PrimeNumbers =
        Stream.of(
                2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79,
                83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167,
                173, 179, 181, 191, 193, 197, 199, 211, 223, 227, 229, 233, 239, 241, 251, 257, 263,
                269, 271, 277, 281, 283, 293, 307, 311, 313, 317, 331, 337, 347, 349, 353, 359, 367,
                373, 379, 383, 389, 397, 401, 409, 419, 421, 431, 433, 439, 443, 449, 457, 461, 463,
                467, 479, 487, 491, 499, 503, 509, 521, 523, 541)
            .map(i -> new BigInteger("" + i))
            .reduce(BigInteger.ONE, (a, b) -> a.multiply(b));

    final BigInteger givenPrimeNumber101 = new BigInteger("547");
    final BigInteger givenPrimeNumber102 = new BigInteger("557");
    final BigRational given =
        new BigRational(
            productOfFirst100PrimeNumbers.multiply(givenPrimeNumber101)
                + "/"
                + productOfFirst100PrimeNumbers.multiply(givenPrimeNumber102));

    // when
    final BigRational got = given.cancel();

    // then
    Assertions.assertEquals(givenPrimeNumber101, got.getNumerator());
    Assertions.assertEquals(givenPrimeNumber102, got.getDenominator());
  }

  @Test
  void testCancel_notNeeded() {
    // given
    final var given = new BigRational("2", "3");

    // when
    final var got = given.cancel();

    // then
    Assertions.assertSame(given, got);
  }

  static Stream<Arguments> testAdd() {
    return Stream.of(
        Arguments.of(
            new BigRational("2", "3"), new BigRational("4", "5"), new BigRational("22", "15")),
        Arguments.of(
            new BigRational("2", "3"), new BigRational("-4", "5"), new BigRational("-2", "15")),
        Arguments.of(
            new BigRational("0", "3"), new BigRational("0", "5"), new BigRational("0", "15")),
        Arguments.of(
            new BigRational("2", "1"), new BigRational("3", "1"), new BigRational("5", "1")));
  }

  @ParameterizedTest
  @MethodSource
  void testAdd(BigRational given1, BigRational given2, BigRational expected) {
    // when
    final BigRational got1 = given1.add(given2);
    final BigRational got2 = given2.add(given1);

    // then
    Assertions.assertEquals(expected, got1);
    Assertions.assertEquals(expected, got2);
  }

  @Test
  void testAdd_Null() {
    // given
    final BigRational given = new BigRational("2", "3");

    // when, then
    Assertions.assertThrows(NullPointerException.class, () -> given.add((BigRational) null));
  }

  static Stream<Arguments> testSubtract() {
    return Stream.of(
        Arguments.of(
            new BigRational("2", "3"), new BigRational("4", "5"), new BigRational("-2", "15")),
        Arguments.of(
            new BigRational("2", "3"), new BigRational("-4", "5"), new BigRational("22", "15")),
        Arguments.of(
            new BigRational("0", "3"), new BigRational("0", "5"), new BigRational("0", "15")),
        Arguments.of(
            new BigRational("2", "1"), new BigRational("3", "1"), new BigRational("-1", "1")));
  }

  @ParameterizedTest
  @MethodSource
  void testSubtract(BigRational given1, BigRational given2, BigRational expected) {
    // when
    final BigRational got1 = given1.subtract(given2);
    final BigRational got2 = given2.subtract(given1).negate();

    // then
    Assertions.assertEquals(expected, got1);
    Assertions.assertEquals(expected, got2);
  }

  @Test
  void testSubstract_Null() {
    // given
    final BigRational given = new BigRational("2", "3");

    // when, then
    Assertions.assertThrows(NullPointerException.class, () -> given.subtract((BigRational) null));
  }

  static Stream<Arguments> testMultiply() {
    return Stream.of(
        Arguments.of(
            new BigRational("2", "3"), new BigRational("4", "5"), new BigRational("8", "15")),
        Arguments.of(
            new BigRational("2", "3"), new BigRational("-4", "5"), new BigRational("-8", "15")),
        Arguments.of(
            new BigRational("-2", "3"), new BigRational("4", "5"), new BigRational("-8", "15")),
        Arguments.of(
            new BigRational("-2", "3"), new BigRational("-4", "5"), new BigRational("8", "15")));
  }

  @ParameterizedTest
  @MethodSource
  void testMultiply(BigRational given1, BigRational given2, BigRational expected) {
    // when
    final BigRational got1 = given1.multiply(given2);
    final BigRational got2 = given2.multiply(given1);

    // then
    Assertions.assertEquals(expected, got1);
    Assertions.assertEquals(expected, got2);
  }

  static Stream<Arguments> testMultiply_byZero() {
    return Stream.of(
        Arguments.of(new BigRational("0", "1")),
        Arguments.of(new BigRational("2", "3")),
        Arguments.of(new BigRational("2", "-3")),
        Arguments.of(new BigRational("-2", "3")),
        Arguments.of(new BigRational("-2", "-3")));
  }

  @ParameterizedTest
  @MethodSource
  void testMultiply_byZero(BigRational given) {
    final BigRational zero1 = new BigRational("0/1");
    final BigRational zero2 = new BigRational("0/11");

    // when
    final BigRational got1 = given.multiply(zero1);
    final BigRational got2 = zero1.multiply(given);
    final BigRational got3 = given.multiply(zero2);
    final BigRational got4 = zero2.multiply(given);

    // then
    Assertions.assertSame(BigRational.ZERO, got1);
    Assertions.assertSame(BigRational.ZERO, got2);
    Assertions.assertSame(BigRational.ZERO, got3);
    Assertions.assertSame(BigRational.ZERO, got4);
  }

  @Test
  void testMultiply_byNull() {
    // given
    final BigRational given = new BigRational("2", "3");

    // when, then
    Assertions.assertThrows(NullPointerException.class, () -> given.multiply((BigRational) null));
  }

  static Stream<Arguments> testDivide() {
    return Stream.of(
        Arguments.of(
            new BigRational("2", "3"), new BigRational("4", "5"), new BigRational("10", "12")),
        Arguments.of(
            new BigRational("2", "3"), new BigRational("-4", "5"), new BigRational("10", "-12")),
        Arguments.of(
            new BigRational("-2", "3"), new BigRational("4", "5"), new BigRational("-10", "12")),
        Arguments.of(
            new BigRational("-2", "3"), new BigRational("-4", "5"), new BigRational("-10", "-12")));
  }

  @ParameterizedTest
  @MethodSource
  void testDivide(BigRational given1, BigRational given2, BigRational expected) {
    // when
    final BigRational got = given1.divide(given2);

    // then
    Assertions.assertEquals(expected, got);
  }

  @Test
  void testDivide_byZero() throws CalculationException {
    // given
    final BigRational given1 = new BigRational("2", "3");
    final BigRational given2 = new BigRational("0", "5");

    // when
    Assertions.assertThrows(ArithmeticException.class, () -> given1.divide(given2));
  }

  @Test
  void testDivide_byNull() throws ArithmeticException, NullPointerException {
    // given
    final BigRational given = new BigRational("2", "3");

    // when
    Assertions.assertThrows(NullPointerException.class, () -> given.divide((BigRational) null));
  }

  static Stream<Arguments> testPowBigRational() {
    return Stream.of(
        Arguments.of(new BigRational("2/3"), new BigRational("8"), new BigRational("256/6561")),
        Arguments.of(new BigRational("2/3"), new BigRational("16/2"), new BigRational("256/6561")),
        Arguments.of(new BigRational("2/3"), new BigRational("-8"), new BigRational("6561/256")),
        Arguments.of(new BigRational("2/3"), new BigRational("-16/2"), new BigRational("6561/256")),
        Arguments.of(new BigRational("2/3"), new BigRational("0"), new BigRational("1")),
        Arguments.of(new BigRational("0"), new BigRational("0"), new BigRational("1")));
  }

  @ParameterizedTest
  @MethodSource
  void testPowBigRational(BigRational givenNumber, BigRational givenPower, BigRational expected) {
    // when
    final BigRational got = givenNumber.pow(givenPower);

    // then
    Assertions.assertEquals(expected, got);
  }

  @Test
  void testPowBigRational_toNull() throws NullPointerException, ArithmeticException {
    // given
    final BigRational given = new BigRational("2", "3");

    // when, then
    Assertions.assertThrows(NullPointerException.class, () -> given.pow((BigRational) null));
  }

  static Stream<Arguments> testPowBigInteger() {
    return Stream.of(
        Arguments.of(new BigRational("2/3"), new BigInteger("8"), new BigRational("256/6561")),
        Arguments.of(new BigRational("2/3"), new BigInteger("-8"), new BigRational("6561/256")),
        Arguments.of(new BigRational("2/3"), new BigInteger("0"), new BigRational("1")),
        Arguments.of(new BigRational("0"), new BigInteger("0"), new BigRational("1")));
  }

  @ParameterizedTest
  @MethodSource
  void testPowBigInteger(BigRational givenNumber, BigInteger givenPower, BigRational expected) {
    // when
    final BigRational got = givenNumber.pow(givenPower);

    // then
    Assertions.assertEquals(expected, got);
  }

  @Test
  void testPowBigInteger_ZeroMinusPow() throws NullPointerException, ArithmeticException {
    // given
    final BigRational given = new BigRational("0");

    // when, then
    Assertions.assertThrows(ArithmeticException.class, () -> given.pow(BigInteger.valueOf(-2)));
  }

  @Test
  void testPowBigInteger_Null() throws NullPointerException, ArithmeticException {
    // given
    final BigRational given = new BigRational("2", "3");

    // when, then
    Assertions.assertThrows(NullPointerException.class, () -> given.pow((BigInteger) null));
  }

  static Stream<Arguments> testAbs() {
    final var expected = new BigRational("2", "3");
    return Stream.of(
        Arguments.of(new BigRational("2", "3"), expected),
        Arguments.of(new BigRational("2", "-3"), expected),
        Arguments.of(new BigRational("-2", "3"), expected),
        Arguments.of(new BigRational("-2", "-3"), expected));
  }

  @ParameterizedTest
  @MethodSource
  void testAbs(BigRational given, BigRational expected) {
    // when
    final var got = given.abs();

    // then
    Assertions.assertEquals(expected, got);
  }

  static Stream<Arguments> testNegate() {
    return Stream.of(
        Arguments.of(new BigRational("2", "3"), new BigRational("-2", "3")),
        Arguments.of(new BigRational("2", "-3"), new BigRational("-2", "-3")),
        Arguments.of(new BigRational("-2", "3"), new BigRational("2", "3")),
        Arguments.of(new BigRational("-2", "-3"), new BigRational("2", "-3")),
        Arguments.of(new BigRational("0", "3"), new BigRational("0", "3")),
        Arguments.of(new BigRational("0", "-3"), new BigRational("0", "-3")));
  }

  @ParameterizedTest
  @MethodSource
  void testNegate(BigRational given, BigRational expected) {
    // when
    final var got1 = given.negate();
    final var got2 = got1.negate();

    // then
    Assertions.assertEquals(expected, got1);
    Assertions.assertEquals(given, got2);
  }

  static Stream<Arguments> testInverse() {
    return Stream.of(
        Arguments.of(new BigRational("2", "3"), new BigRational("3", "2")),
        Arguments.of(new BigRational("2", "-3"), new BigRational("-3", "2")),
        Arguments.of(new BigRational("-2", "3"), new BigRational("3", "-2")),
        Arguments.of(new BigRational("-2", "-3"), new BigRational("-3", "-2")),
        Arguments.of(new BigRational("1", "1"), new BigRational("1", "1")));
  }

  @ParameterizedTest
  @MethodSource
  void testInverse(BigRational given, BigRational expected) {
    // when
    final var got1 = given.inverse();
    final var got2 = got1.inverse();

    // then
    Assertions.assertEquals(expected, got1);
    Assertions.assertEquals(given, got2);
  }

  @Test
  void testInverse_Zero() {
    // given
    final var given = new BigRational("0", "3");

    // when, then
    Assertions.assertThrows(ArithmeticException.class, () -> given.inverse());
  }

  static Stream<Arguments> testSignum() {
    return Stream.of(
        Arguments.of(new BigRational("2", "3"), 1),
        Arguments.of(new BigRational("2", "-3"), -1),
        Arguments.of(new BigRational("-2", "3"), -1),
        Arguments.of(new BigRational("-2", "-3"), 1),
        Arguments.of(new BigRational("0", "3"), 0),
        Arguments.of(new BigRational("0", "-5"), 0));
  }

  @ParameterizedTest
  @MethodSource
  void testSignum(BigRational given, int expected) {
    // when
    final var got = given.signum();

    // then
    Assertions.assertEquals(expected, got);
  }

  static Stream<Arguments> testMin() {
    final BigRational given1 = new BigRational("2", "3");
    final BigRational given2 = new BigRational("-2", "3");
    final BigRational given3 = new BigRational("5", "7");
    final BigRational given4 = new BigRational("-5", "7");
    final BigRational given11 = new BigRational("4", "6");
    final BigRational givenZero1 = new BigRational("0", "1");
    final BigRational givenZero2 = new BigRational("0", "2");

    return Stream.of(
        Arguments.of(given1, given2, given2),
        Arguments.of(given1, given3, given1),
        Arguments.of(given1, given4, given4),
        Arguments.of(given1, given11, given1),
        Arguments.of(given2, given3, given2),
        Arguments.of(given2, given4, given4),
        Arguments.of(given3, given4, given4),
        Arguments.of(givenZero1, givenZero2, givenZero1),
        // Swapped:
        Arguments.of(given2, given1, given2),
        Arguments.of(given3, given1, given1),
        Arguments.of(given4, given1, given4),
        Arguments.of(given11, given1, given1), // lower denominator
        Arguments.of(given3, given2, given2),
        Arguments.of(given4, given2, given4),
        Arguments.of(given4, given3, given4),
        Arguments.of(givenZero2, givenZero1, givenZero1) // lower denominator
        );
  }

  @ParameterizedTest
  @MethodSource
  void testMin(BigRational given1, BigRational given2, BigRational expected) {
    // when
    final var got = given1.min(given2);

    // then
    Assertions.assertSame(expected, got);
  }

  @Test
  void testMin_Null() {
    Assertions.assertThrows(
        NullPointerException.class,
        () -> {
          final BigRational br1 = new BigRational("-2", "3");
          br1.min(null);
        });
  }

  static Stream<Arguments> testMax() {
    final BigRational given1 = new BigRational("2", "3");
    final BigRational given2 = new BigRational("-2", "3");
    final BigRational given3 = new BigRational("5", "7");
    final BigRational given4 = new BigRational("-5", "7");
    final BigRational given11 = new BigRational("4", "6");
    final BigRational givenZero1 = new BigRational("0", "1");
    final BigRational givenZero2 = new BigRational("0", "2");

    return Stream.of(
        Arguments.of(given1, given2, given1),
        Arguments.of(given1, given3, given3),
        Arguments.of(given1, given4, given1),
        Arguments.of(given1, given11, given1),
        Arguments.of(given2, given3, given3),
        Arguments.of(given2, given4, given2),
        Arguments.of(given3, given4, given3),
        Arguments.of(givenZero1, givenZero2, givenZero1),
        // Swapped:
        Arguments.of(given2, given1, given1),
        Arguments.of(given3, given1, given3),
        Arguments.of(given4, given1, given1),
        Arguments.of(given11, given1, given1), // lower denominator
        Arguments.of(given3, given2, given3),
        Arguments.of(given4, given2, given2),
        Arguments.of(given4, given3, given3),
        Arguments.of(givenZero2, givenZero1, givenZero1) // lower denominator
        );
  }

  @ParameterizedTest
  @MethodSource
  void testMax(BigRational given1, BigRational given2, BigRational expected) {
    // when
    final var got = given1.max(given2);

    // then
    Assertions.assertSame(expected, got);
  }

  @Test
  void testMax_Null() {
    // given
    final BigRational given = new BigRational("-2", "3");

    // when, then
    Assertions.assertThrows(NullPointerException.class, () -> given.max(null));
  }

  static Stream<Arguments> testSquare() {
    return Stream.of(
        Arguments.of(new BigRational("2", "3"), new BigRational("4", "9")),
        Arguments.of(new BigRational("2", "-3"), new BigRational("4", "9")),
        Arguments.of(new BigRational("-2", "3"), new BigRational("4", "9")),
        Arguments.of(new BigRational("-2", "-3"), new BigRational("4", "9")),
        Arguments.of(new BigRational("1", "1"), new BigRational("1", "1")),
        Arguments.of(new BigRational("0", "2"), new BigRational("0", "1")));
  }

  @ParameterizedTest
  @MethodSource
  void testSquare(BigRational given, BigRational expected) {
    // when
    final var got = given.square();

    // then
    Assertions.assertEquals(expected, got);
  }

  static Stream<Arguments> testCube() {
    return Stream.of(
        Arguments.of(new BigRational("2", "3"), new BigRational("8", "27")),
        Arguments.of(new BigRational("2", "-3"), new BigRational("8", "-27")),
        Arguments.of(new BigRational("-2", "3"), new BigRational("-8", "27")),
        Arguments.of(new BigRational("-2", "-3"), new BigRational("-8", "-27")),
        Arguments.of(new BigRational("1", "1"), new BigRational("1", "1")),
        Arguments.of(new BigRational("0", "2"), new BigRational("0", "1")));
  }

  @ParameterizedTest
  @MethodSource
  void testCube(BigRational given, BigRational expected) {
    // when
    final var got = given.cube();

    // then
    Assertions.assertEquals(expected, got);
  }

  static Stream<Arguments> testCompareTo() {
    final BigRational given1 = new BigRational("2", "3");
    final BigRational given2 = new BigRational("-2", "3");
    final BigRational given3 = new BigRational("5", "7");
    final BigRational given4 = new BigRational("-5", "7");
    final BigRational given11 = new BigRational("4", "6");
    final BigRational givenZero1 = new BigRational("0", "1");
    final BigRational givenZero2 = new BigRational("0", "2");

    return Stream.of(
        Arguments.of(given1, given2, 1),
        Arguments.of(given1, given3, -1),
        Arguments.of(given1, given4, 1),
        Arguments.of(given1, given11, 0),
        Arguments.of(given2, given3, -1),
        Arguments.of(given2, given4, 1),
        Arguments.of(given3, given4, 1),
        Arguments.of(givenZero1, givenZero2, 0),
        // Swapped:
        Arguments.of(given2, given1, -1),
        Arguments.of(given3, given1, 1),
        Arguments.of(given4, given1, -1),
        Arguments.of(given11, given1, 0),
        Arguments.of(given3, given2, 1),
        Arguments.of(given4, given2, -1),
        Arguments.of(given4, given3, -1),
        Arguments.of(givenZero2, givenZero1, 0));
  }

  @ParameterizedTest
  @MethodSource
  void testCompareTo(BigRational given1, BigRational given2, int expected) {
    // when
    final var got = given1.compareTo(given2);

    // then
    Assertions.assertSame(expected, got);
  }

  static Stream<Arguments> testEqualsByValue() {
    final BigRational given1 = new BigRational("2", "3");
    final BigRational given2 = new BigRational("-2", "3");
    final BigRational given3 = new BigRational("-4", "-6");
    final BigRational givenZero1 = new BigRational("0", "1");
    final BigRational givenZero2 = new BigRational("0", "2");

    return Stream.of(
        Arguments.of(given1, given2, false),
        Arguments.of(given1, given3, true),
        Arguments.of(givenZero1, givenZero2, true),
        // Swapped:
        Arguments.of(given2, given1, false),
        Arguments.of(given3, given1, true),
        Arguments.of(givenZero2, givenZero1, true),
        // Other:
        Arguments.of(given1, given1, true));
  }

  @ParameterizedTest
  @MethodSource
  void testEqualsByValue(BigRational given1, BigRational given2, boolean expected) {

    // when
    final var got = given1.equalsValue(given2);

    // then
    Assertions.assertEquals(expected, got);
  }

  static Stream<Arguments> testEqualsStrict() {
    final BigRational given1 = new BigRational("2", "3");
    final BigRational given2 = new BigRational("-2", "3");
    final BigRational given3 = new BigRational("-4", "-6");
    final BigRational given4 = new BigRational("-4", "-6");
    final BigRational givenZero1 = new BigRational("0", "1");
    final BigRational givenZero2 = new BigRational("0", "2");

    return Stream.of(
        Arguments.of(given1, given2, false),
        Arguments.of(given1, given3, false),
        Arguments.of(givenZero1, givenZero2, false),
        // Swapped:
        Arguments.of(given2, given1, false),
        Arguments.of(given3, given1, false),
        Arguments.of(givenZero2, givenZero1, false),
        // Other:
        Arguments.of(given3, given4, true),
        Arguments.of(given1, given1, true));
  }

  @ParameterizedTest
  @MethodSource
  void testEqualsStrict(BigRational given1, BigRational given2, boolean expected) {

    // when
    final var got = given1.equalsStrict(given2);

    // then
    Assertions.assertEquals(expected, got);
  }

  static Stream<Arguments> hashCodeByValue() {
    return Stream.of(
        Arguments.of(new BigRational("2/3"), new BigRational("2/3")),
        Arguments.of(new BigRational("2/3"), new BigRational("4/6")));
  }

  @ParameterizedTest
  @MethodSource
  void hashCodeByValue(BigRational given1, BigRational given2) {
    // when
    final var got1 = given1.hashCode();
    final var got2 = given2.hashCode();

    // then
    Assertions.assertTrue(got1 == got2);
  }

  @Test
  /**
   * Testing for inequality of hashcodes of 2/3 and 4/6 is not valid from the contract point of
   * view.
   */
  void hashCodeStrict() {
    // given
    final var given1 = new BigRational("2/3");
    final var given2 = new BigRational("2/3");

    // when
    final var got1 = given1.hashCodeStrict();
    final var got2 = given2.hashCodeStrict();

    // then
    Assertions.assertTrue(got1 == got2);
  }

  static Stream<Arguments> testToString() {
    return Stream.of(
        Arguments.of(new BigRational("2"), "2/1"),
        Arguments.of(new BigRational("2/3"), "2/3"),
        Arguments.of(new BigRational("-2/3"), "-2/3"),
        Arguments.of(new BigRational("2/-3"), "2/-3"),
        Arguments.of(new BigRational("-2/-3"), "-2/-3"));
  }

  @ParameterizedTest
  @MethodSource
  void testToString(BigRational given, String expected) {
    // when
    final var got = given.toString();

    // then
    Assertions.assertEquals(expected, got);
  }

  static Stream<Arguments> testToStringPretty() {
    return Stream.of(
        Arguments.of(new BigRational("2"), "2"),
        Arguments.of(new BigRational("0/2"), "0"),
        Arguments.of(new BigRational("4/6"), "2/3"),
        Arguments.of(new BigRational("-4/6"), "-2/3"),
        Arguments.of(new BigRational("4/-6"), "-2/3"),
        Arguments.of(new BigRational("-4/-6"), "2/3"));
  }

  @ParameterizedTest
  @MethodSource
  void testToStringPretty(BigRational given, String expected) {
    // when
    final var got = given.toStringPretty();

    // then
    Assertions.assertEquals(expected, got);
  }
}
