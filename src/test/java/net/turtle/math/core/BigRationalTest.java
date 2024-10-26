package net.turtle.math.core;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import net.turtle.math.context.BigMathContext;
import net.turtle.math.exception.CalculationException;

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
  void testCancel_needed() {
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
    Assertions.assertThrows(
        NullPointerException.class,
        () -> {
          final BigRational br1 = new BigRational("2", "3");
          br1.subtract((BigRational) null);
        });
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

  @Test
  void testDivide() throws ArithmeticException {
    {
      /*
       * + and +
       */
      final BigRational br1 = new BigRational("2", "3");
      final BigRational br2 = new BigRational("4", "5");
      final BigRational r1 = new BigRational("10", "12");
      Assertions.assertEquals(r1, br1.divide(br2));
      final BigRational r2 = new BigRational("12", "10");
      Assertions.assertEquals(r2, br2.divide(br1));
    }
    {
      /*
       * + and -
       */
      final BigRational br1 = new BigRational("2", "3");
      final BigRational br2 = new BigRational("-4", "5");
      final BigRational r1 = new BigRational("-10", "12");
      Assertions.assertEquals(r1, br1.divide(br2));
      final BigRational r2 = new BigRational("-12", "10");
      Assertions.assertEquals(r2, br2.divide(br1));
    }
    {
      /*
       * - and -
       */
      final BigRational br1 = new BigRational("-2", "3");
      final BigRational br2 = new BigRational("-4", "5");
      final BigRational r1 = new BigRational("10", "12");
      Assertions.assertEquals(r1, br1.divide(br2));
      final BigRational r2 = new BigRational("12", "10");
      Assertions.assertEquals(r2, br2.divide(br1));
    }
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

  @Test
  void testPowBigRational() {
    {
      final BigRational br1 = new BigRational("2/3");
      final BigRational r1 = new BigRational("256/6561");
      Assertions.assertEquals(r1, br1.pow(new BigRational("8")));
      Assertions.assertEquals(r1, br1.pow(new BigRational("16/2").normalize()));
    }
    {
      final BigRational br1 = new BigRational("2/3");
      final BigRational r1 = new BigRational("6561/256");
      Assertions.assertEquals(r1, br1.pow(new BigRational("-8")));
      Assertions.assertEquals(r1, br1.pow(new BigRational("-24/3")));
    }
    {
      final BigRational br1 = new BigRational("10");
      final BigRational r1 = new BigRational("0.1");
      Assertions.assertEquals(r1, br1.pow(new BigRational("-1")));
    }
    {
      final BigRational br1 = new BigRational("0");
      final BigRational r1 = new BigRational("1");
      Assertions.assertEquals(r1, br1.pow(BigRational.ZERO));
    }

    {
      final BigRational br1 = new BigRational("123");
      final BigRational r1 = new BigRational("6443858614676334363");
      Assertions.assertEquals(r1, br1.pow(BigInteger.valueOf(9)));
    }

    {
      final BigRational br1 = new BigRational("5");
      final BigRational r1 = new BigRational("5");
      final BigRational result = br1.pow(new BigRational("1"));
      Assertions.assertEquals(r1, result);
      Assertions.assertSame(br1, result);
    }
    {
      final BigRational br1 = new BigRational("5");
      final BigRational r1 = new BigRational("5");
      final BigRational result = br1.pow(new BigRational("7/7"));
      Assertions.assertEquals(r1, result);
      Assertions.assertSame(br1, result);
    }
  }

  @Test
  void testPowBigRational_Null() throws NullPointerException, ArithmeticException {
    Assertions.assertThrows(
        NullPointerException.class,
        () -> {
          final BigRational br1 = new BigRational("2", "3");
          br1.pow((BigRational) null);
        });
  }

  @Test
  void testPowBigInteger() {
    {
      final BigRational br1 = new BigRational("2/3");
      final BigRational r1 = new BigRational("256/6561");
      Assertions.assertEquals(r1, br1.pow(BigInteger.valueOf(8)));
    }
    {
      final BigRational br1 = new BigRational("2/3");
      final BigRational r1 = new BigRational("6561/256");
      Assertions.assertEquals(r1, br1.pow(BigInteger.valueOf(-8)));
    }
    {
      final BigRational br1 = new BigRational("10");
      final BigRational r1 = new BigRational("0.1");
      Assertions.assertEquals(r1, br1.pow(BigInteger.valueOf(-1)));
    }
    {
      final BigRational br1 = new BigRational("0");
      final BigRational r1 = new BigRational("1");
      Assertions.assertEquals(r1, br1.pow(BigInteger.valueOf(0)));
    }

    {
      final BigRational br1 = new BigRational("123");
      final BigRational r1 = new BigRational("6443858614676334363");
      Assertions.assertEquals(r1, br1.pow(BigInteger.valueOf(9)));
    }

    {
      final BigRational br1 = new BigRational("5");
      final BigRational r1 = new BigRational("5");
      final BigRational result = br1.pow(BigInteger.valueOf(1));
      Assertions.assertEquals(r1, result);
      Assertions.assertSame(br1, result);
    }
  }

  @Test
  void testPowBigInteger_ZeroMinusPow() throws NullPointerException, ArithmeticException {
    Assertions.assertThrows(
        ArithmeticException.class,
        () -> {
          final BigRational br1 = new BigRational("0");
          br1.pow(BigInteger.valueOf(-2));
        });
  }

  @Test
  void testPowBigInteger_Null() throws NullPointerException, ArithmeticException {
    Assertions.assertThrows(
        NullPointerException.class,
        () -> {
          final BigRational br1 = new BigRational("2", "3");
          br1.pow((BigInteger) null);
        });
  }

  @Test
  void testAbs() {
    final BigRational r1 = new BigRational("2", "3");

    final BigRational br1 = new BigRational("2", "3");
    Assertions.assertEquals(r1, br1.abs());
    final BigRational br2 = new BigRational("2", "-3");
    Assertions.assertEquals(r1, br2.abs());
    final BigRational br3 = new BigRational("-2", "3");
    Assertions.assertEquals(r1, br3.abs());
    final BigRational br4 = new BigRational("-2", "-3");
    Assertions.assertEquals(r1, br4.abs());
  }

  @Test
  void testNegate() {
    {
      final BigRational br1 = new BigRational("-2", "3");
      final BigRational r1 = new BigRational("2", "3");
      Assertions.assertEquals(r1, br1.negate());
    }
    {
      final BigRational br1 = new BigRational("2", "-3");
      final BigRational r1 = new BigRational("-2", "-3");
      Assertions.assertEquals(r1, br1.negate());
    }
    {
      final BigRational br1 = new BigRational("2", "3");
      final BigRational r1 = new BigRational("-2", "3");
      Assertions.assertEquals(r1, br1.negate());
    }
    {
      final BigRational br1 = new BigRational("-2", "-3");
      final BigRational r1 = new BigRational("2", "-3");
      Assertions.assertEquals(r1, br1.negate());
    }
    {
      final BigRational br1 = new BigRational("0", "3");
      final BigRational r1 = new BigRational("0", "3");
      Assertions.assertEquals(r1, br1.negate());
      Assertions.assertEquals(br1, br1.negate().negate());
      Assertions.assertEquals(r1, br1.negate().negate().negate());
    }
  }

  @Test
  void testInverse() {
    {
      final BigRational br1 = new BigRational("-2", "3");
      final BigRational r1 = new BigRational("3", "-2");
      Assertions.assertEquals(r1, br1.inverse());
      Assertions.assertEquals(br1, br1.inverse().inverse());
      Assertions.assertEquals(r1, br1.inverse().inverse().inverse());
    }
    {
      final BigRational br1 = new BigRational("1", "1");
      final BigRational r1 = new BigRational("1", "1");
      Assertions.assertEquals(r1, br1.inverse());
    }
  }

  @Test
  void testInverse_Zero() {
    Assertions.assertThrows(
        ArithmeticException.class,
        () -> {
          new BigRational("0", "3").inverse();
        });
  }

  @Test
  void testSignum() {
    {
      final BigRational br1 = new BigRational("-2", "3");
      Assertions.assertEquals(-1, br1.signum());
    }
    {
      final BigRational br1 = new BigRational("2", "-3");
      Assertions.assertEquals(-1, br1.signum());
    }
    {
      final BigRational br1 = new BigRational("2", "3");
      Assertions.assertEquals(1, br1.signum());
    }
    {
      final BigRational br1 = new BigRational("-2", "-3");
      Assertions.assertEquals(1, br1.signum());
    }
    {
      final BigRational br1 = new BigRational("0", "3");
      Assertions.assertEquals(0, br1.signum());
    }
  }

  @Test
  void testMin() {
    {
      final BigRational br1 = new BigRational("-2", "3");
      final BigRational br2 = new BigRational("2", "5");
      Assertions.assertSame(br1, br1.min(br2));
      Assertions.assertSame(br1, br2.min(br1));
    }
    {
      final BigRational br1 = new BigRational("2", "3");
      final BigRational br2 = new BigRational("4", "6");
      Assertions.assertSame(br2, br1.min(br2));
      Assertions.assertSame(br1, br2.min(br1));
    }
    {
      final BigRational br1 = new BigRational("0", "3");
      final BigRational br2 = new BigRational("0", "5");
      Assertions.assertSame(br2, br1.min(br2));
      Assertions.assertSame(br1, br2.min(br1));
    }
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

  @Test
  void testMax() {
    {
      final BigRational br1 = new BigRational("-2", "3");
      final BigRational br2 = new BigRational("2", "5");
      Assertions.assertSame(br2, br1.max(br2));
      Assertions.assertSame(br2, br2.max(br1));
    }
    {
      final BigRational br1 = new BigRational("2", "3");
      final BigRational br2 = new BigRational("4", "6");
      Assertions.assertSame(br2, br1.max(br2));
      Assertions.assertSame(br1, br2.max(br1));
    }
    {
      final BigRational br1 = new BigRational("0", "3");
      final BigRational br2 = new BigRational("0", "5");
      Assertions.assertSame(br2, br1.max(br2));
      Assertions.assertSame(br1, br2.max(br1));
    }
  }

  @Test
  void testMax_Null() {
    Assertions.assertThrows(
        NullPointerException.class,
        () -> {
          final BigRational br1 = new BigRational("-2", "3");
          br1.max(null);
        });
  }

  @Test
  void testSquare() {
    {
      final BigRational br1 = new BigRational("2", "3");
      final BigRational r1 = new BigRational("4", "9");
      Assertions.assertEquals(r1, br1.square());
    }
    {
      final BigRational br1 = new BigRational("2", "-3");
      final BigRational r1 = new BigRational("4", "9");
      Assertions.assertEquals(r1, br1.square());
    }
  }

  @Test
  void testCube() {
    {
      final BigRational br1 = new BigRational("2", "3");
      final BigRational r1 = new BigRational("8", "27");
      Assertions.assertEquals(r1, br1.cube());
    }
    {
      final BigRational br1 = new BigRational("2", "-3");
      final BigRational r1 = new BigRational("8", "-27");
      Assertions.assertEquals(r1, br1.cube());
    }
  }

  @Test
  void testCompareTo() {
    {
      final BigRational br1 = new BigRational("2", "3");
      final BigRational br2 = new BigRational("4", "5");
      Assertions.assertEquals(-1, br1.compareTo(br2));
      Assertions.assertEquals(1, br2.compareTo(br1));
    }
    {
      final BigRational br1 = new BigRational("-2", "3");
      final BigRational br2 = new BigRational("4", "5");
      Assertions.assertEquals(-1, br1.compareTo(br2));
      Assertions.assertEquals(1, br2.compareTo(br1));
    }
    {
      final BigRational br1 = new BigRational("-2", "3");
      final BigRational br2 = new BigRational("-4", "5");
      Assertions.assertEquals(1, br1.compareTo(br2));
      Assertions.assertEquals(-1, br2.compareTo(br1));
    }

    {
      final BigRational br1 = new BigRational("2", "3");
      final BigRational br2 = new BigRational("4", "6");
      Assertions.assertEquals(0, br1.compareTo(br2));
      Assertions.assertEquals(0, br2.compareTo(br1));
    }
    {
      final BigRational br1 = new BigRational("-2", "3");
      final BigRational br2 = new BigRational("-4", "6");
      Assertions.assertEquals(0, br1.compareTo(br2));
      Assertions.assertEquals(0, br2.compareTo(br1));
    }
    {
      final BigRational br1 = new BigRational("-2", "3");
      final BigRational br2 = new BigRational("4", "-5");
      Assertions.assertEquals(1, br1.compareTo(br2));
      Assertions.assertEquals(-1, br2.compareTo(br1));
    }

    {
      final BigRational br1 = new BigRational("0", "3");
      final BigRational br2 = new BigRational("0", "5");
      Assertions.assertEquals(0, br1.compareTo(br2));
      Assertions.assertEquals(0, br2.compareTo(br1));
    }
  }

  @Test
  void equalsByValue() {
    {
      /*
       * Equals null
       */
      final BigRational br1 = new BigRational("2", "3");
      Assertions.assertFalse(br1.equals(null));
    }
    {
      /*
       * Same instance
       */
      final BigRational br1 = new BigRational("2", "3");
      Assertions.assertTrue(br1.equals(br1));
    }

    {
      final BigRational br1 = new BigRational("2", "3");
      final BigRational br2 = new BigRational("4", "5");
      Assertions.assertFalse(br1.equals(br2));
      Assertions.assertFalse(br2.equals(br1));
    }

    {
      /*
       * Equal by value
       */
      final BigRational br1 = new BigRational("2", "3");
      final BigRational br2 = new BigRational("4", "6");
      Assertions.assertTrue(br1.equals(br2));
      Assertions.assertTrue(br2.equals(br1));
    }
    {
      /*
       * Equal by value
       */
      final BigRational br1 = new BigRational("-2", "3");
      final BigRational br2 = new BigRational("-4", "6");
      Assertions.assertTrue(br1.equals(br2));
      Assertions.assertTrue(br2.equals(br1));
    }

    {
      /*
       * Zero
       */
      final BigRational br1 = new BigRational("0", "3");
      final BigRational br2 = new BigRational("0", "-5");
      Assertions.assertTrue(br1.equals(br2));
      Assertions.assertTrue(br2.equals(br1));
    }
  }

  @Test
  void equalsStrict() {
    BigMathContext.get().setStrictEqualsAndHashContract(true);
    {
      /*
       * Equals null
       */
      final BigRational br1 = new BigRational("2", "3");
      Assertions.assertFalse(br1.equals(null));
    }
    {
      /*
       * Same instance
       */
      final BigRational br1 = new BigRational("2", "3");
      Assertions.assertTrue(br1.equals(br1));
    }

    {
      final BigRational br1 = new BigRational("2", "3");
      final BigRational br2 = new BigRational("4", "5");
      Assertions.assertFalse(br1.equals(br2));
      Assertions.assertFalse(br2.equals(br1));
    }

    {
      /*
       * Equal by value
       */
      final BigRational br1 = new BigRational("2", "3");
      final BigRational br2 = new BigRational("4", "6");
      Assertions.assertFalse(br1.equals(br2));
      Assertions.assertFalse(br2.equals(br1));
    }
    {
      /*
       * Equal by value
       */
      final BigRational br1 = new BigRational("-2", "3");
      final BigRational br2 = new BigRational("-4", "6");
      Assertions.assertFalse(br1.equals(br2));
      Assertions.assertFalse(br2.equals(br1));
    }

    {
      /*
       * Zero
       */
      final BigRational br1 = new BigRational("0", "3");
      final BigRational br2 = new BigRational("0", "-5");
      Assertions.assertFalse(br1.equals(br2));
      Assertions.assertFalse(br2.equals(br1));
    }
    BigMathContext.get().setStrictEqualsAndHashContract(false);
  }

  @Test
  void hashCodeByValue() {
    {
      final BigRational br1 = new BigRational(new BigInteger("2"), new BigInteger("3"));
      final BigRational br2 = new BigRational(new BigInteger("2"), new BigInteger("3"));
      Assertions.assertTrue(br1.hashCode() == br2.hashCode());
    }
    {
      final BigRational br1 = new BigRational(new BigInteger("2"), new BigInteger("3"));
      final BigRational br2 = new BigRational(new BigInteger("4"), new BigInteger("6"));
      Assertions.assertTrue(br1.hashCode() == br2.hashCode());
    }
  }

  @Test
  void hashCodeStrict() {
    BigMathContext.get().setStrictEqualsAndHashContract(true);
    {
      final BigRational br1 = new BigRational(new BigInteger("2"), new BigInteger("3"));
      final BigRational br2 = new BigRational(new BigInteger("2"), new BigInteger("3"));
      Assertions.assertTrue(br1.hashCode() == br2.hashCode());
    }
    {
      final BigRational br1 = new BigRational(new BigInteger("2"), new BigInteger("3"));
      final BigRational br2 = new BigRational(new BigInteger("4"), new BigInteger("6"));
      Assertions.assertFalse(br1.hashCode() == br2.hashCode());
    }
    BigMathContext.get().setStrictEqualsAndHashContract(false);
  }

  @Test
  void equalsHashCodeContractByValue() {
    {
      final Set<BigRational> set = new HashSet<>();
      set.add(new BigRational(new BigInteger("2"), new BigInteger("3")));
      final BigRational br2 = new BigRational(new BigInteger("2"), new BigInteger("3"));
      Assertions.assertTrue(set.contains(br2));
      Assertions.assertTrue(set.contains(new BigRational("4/6")));
    }
    {
      final Set<BigRational> set = new HashSet<>();
      set.add(new BigRational(new BigInteger("3"), new BigInteger("6")).normalize());
      final BigRational br2 = new BigRational(new BigInteger("5"), new BigInteger("10"));
      Assertions.assertTrue(set.contains(br2.normalize()));
      Assertions.assertTrue(set.contains(br2));
    }
  }

  @Test
  void equalsHashCodeContractStrict() {
    BigMathContext.get().setStrictEqualsAndHashContract(true);
    {
      final Set<BigRational> set = new HashSet<>();
      set.add(new BigRational(new BigInteger("2"), new BigInteger("3")));
      final BigRational br2 = new BigRational(new BigInteger("2"), new BigInteger("3"));
      Assertions.assertTrue(set.contains(br2));
      Assertions.assertFalse(set.contains(new BigRational("4/6")));
    }
    {
      final Set<BigRational> set = new HashSet<>();
      set.add(new BigRational(new BigInteger("3"), new BigInteger("6")).normalize());
      final BigRational br2 = new BigRational(new BigInteger("5"), new BigInteger("10"));
      Assertions.assertTrue(set.contains(br2.normalize()));
      Assertions.assertFalse(set.contains(br2));
    }
    BigMathContext.get().setStrictEqualsAndHashContract(false);
  }

  @Test
  void testToString() {
    Assertions.assertEquals("2/1", new BigRational("2/1").toString());
    Assertions.assertEquals("2/3", new BigRational("2", "3").toString());
    Assertions.assertEquals("-2/3", new BigRational("-2", "3").toString());
    Assertions.assertEquals("2/-3", new BigRational("2", "-3").toString());
    Assertions.assertEquals("-2/-3", new BigRational("-2", "-3").toString());
  }

  @Test
  void testChain() throws ArithmeticException, NullPointerException {
    {
      final BigRational br1 = new BigRational("1");
      final BigRational br2 = new BigRational("3");
      final BigRational r1 = new BigRational("6");
      Assertions.assertEquals(
          r1,
          br1.divide(br2).multiply(br2).subtract(new BigRational("-2")).add(new BigRational("3")));
    }
  }
}
