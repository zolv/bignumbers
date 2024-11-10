package net.turtle.math.core;

import java.math.BigInteger;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class BigComplexTest {

  @Test
  void testBigComplex() {
    // when
    final var got = new BigComplex();

    // then
    Assertions.assertEquals(BigRational.ZERO, got.getA());
    Assertions.assertEquals(BigRational.ZERO, got.getB());
  }

  @Test
  void testBigComplexBigRational() {
    Assertions.assertEquals(new BigRational("2"), new BigComplex(new BigRational("2")).getA());
    Assertions.assertEquals(BigRational.ZERO, new BigComplex(new BigRational("2")).getB());
  }

  @Test
  void testBigComplexBigRationalBigRational() {
    // given
    final var givenA = new BigRational("2");
    final var givenB = new BigRational("3");

    // when
    final var c1 = new BigComplex(givenA, givenB);

    // then
    Assertions.assertEquals(givenA, c1.getA());
    Assertions.assertEquals(givenB, c1.getB());
  }

  static Stream<Arguments> testBigComplexBigRationalBigRational_null() {
    return Stream.of(
        Arguments.of(null, null),
        Arguments.of(new BigRational("2"), null),
        Arguments.of(null, new BigRational("3")));
  }

  @ParameterizedTest
  @MethodSource
  void testBigComplexBigRationalBigRational_null(BigRational givenA, BigRational givenB) {
    // when, then
    Assertions.assertThrows(
        NullPointerException.class,
        () -> {
          new BigComplex(givenA, givenB);
        });
  }

  static Stream<Arguments> testBigComplexString() {
    return Stream.of(
        Arguments.of("2", new BigRational("2"), new BigRational("0")),
        Arguments.of("-2", new BigRational("-2"), new BigRational("0")),
        Arguments.of("+2", new BigRational("2"), new BigRational("0")),
        Arguments.of("2.3", new BigRational("2.3"), new BigRational("0")),
        Arguments.of("-2.3", new BigRational("-2.3"), new BigRational("0")),
        Arguments.of("2/3", new BigRational("2/3"), new BigRational("0")),
        Arguments.of("+2/3", new BigRational("2/3"), new BigRational("0")),
        Arguments.of("-2/3", new BigRational("-2/3"), new BigRational("0")),
        Arguments.of("2+3i", new BigRational("2"), new BigRational("3")),
        Arguments.of("-2-3i", new BigRational("-2"), new BigRational("-3")),
        Arguments.of("+2+3i", new BigRational("2"), new BigRational("3")),
        Arguments.of("2.3-2.3i", new BigRational("2.3"), new BigRational("-2.3")),
        Arguments.of("-2.3-2.3i", new BigRational("-2.3"), new BigRational("-2.3")),
        Arguments.of("2/3+2/3i", new BigRational("2/3"), new BigRational("2/3")),
        Arguments.of("+2/3-2/3i", new BigRational("2/3"), new BigRational("-2/3")),
        Arguments.of("-2/3+2/3i", new BigRational("-2/3"), new BigRational("2/3")),
        Arguments.of("2i", new BigRational("0"), new BigRational("2")),
        Arguments.of("-2i", new BigRational("0"), new BigRational("-2")),
        Arguments.of("+2i", new BigRational("0"), new BigRational("2")),
        Arguments.of("2.3i", new BigRational("0"), new BigRational("2.3")),
        Arguments.of("-2.3i", new BigRational("0"), new BigRational("-2.3")),
        Arguments.of("2/3i", new BigRational("0"), new BigRational("2/3")),
        Arguments.of("+2/3i", new BigRational("0"), new BigRational("2/3")),
        Arguments.of("-2/3i", new BigRational("0"), new BigRational("-2/3")),
        Arguments.of("2+i", new BigRational("2"), new BigRational("1")),
        Arguments.of("2-i", new BigRational("2"), new BigRational("-1")),
        Arguments.of("i", new BigRational("0"), new BigRational("1")),
        Arguments.of("-i", new BigRational("0"), new BigRational("-1")));
  }

  @ParameterizedTest
  @MethodSource
  void testBigComplexString(String given, BigRational expectedA, BigRational expectedB) {
    // when
    final var got = new BigComplex<>(given);

    // then
    Assertions.assertEquals(expectedA, got.getA());
    Assertions.assertEquals(expectedB, got.getB());
  }

  @Test
  void testGetA() {
    // given
    final var expected = new BigRational("2");
    final var given = new BigComplex<>(new BigRational("2"), new BigRational("3"));

    // when
    final var gotA = given.getA();
    final var gotReal = given.getReal();

    // then
    Assertions.assertEquals(expected, gotA);
    Assertions.assertEquals(expected, gotReal);
  }

  @Test
  void testGetB() {
    // given
    final var expected = new BigRational("3");
    final var given = new BigComplex<>(new BigRational("2"), new BigRational("3"));

    // when
    final var gotB = given.getB();
    final var gotImaginary = given.getImaginary();

    // then
    Assertions.assertEquals(expected, gotB);
    Assertions.assertEquals(expected, gotImaginary);
  }

  @Test
  void testNormalize_needed() {
    // given
    final var given = new BigComplex<>(new BigRational("4/-6"), new BigRational("-12/-20"));

    // when
    final var got = given.normalize();

    // then
    Assertions.assertEquals(BigInteger.valueOf(-2), got.getA().getNumerator());
    Assertions.assertEquals(BigInteger.valueOf(3), got.getA().getDenominator());
    Assertions.assertEquals(BigInteger.valueOf(3), got.getB().getNumerator());
    Assertions.assertEquals(BigInteger.valueOf(5), got.getB().getDenominator());
    Assertions.assertNotSame(given, got);
  }

  @Test
  void testNormalize_notNeeded() {
    // given
    final var given = new BigComplex<>(new BigRational("-2", "3"), new BigRational("-3", "5"));

    // when
    final var got = given.normalize();

    // then
    Assertions.assertSame(given, got);
  }

  @Test
  void testNormalizeSignum_needed() {
    // given
    final var given = new BigComplex<>(new BigRational("2", "-3"), new BigRational("-3", "-5"));

    // when
    final var got = given.normalizeSignum();

    // then
    Assertions.assertEquals(BigInteger.valueOf(-2), got.getA().getNumerator());
    Assertions.assertEquals(BigInteger.valueOf(3), got.getA().getDenominator());
    Assertions.assertEquals(BigInteger.valueOf(3), got.getB().getNumerator());
    Assertions.assertEquals(BigInteger.valueOf(5), got.getB().getDenominator());
    Assertions.assertNotSame(given, got);
  }

  @Test
  void testNormalizeSignum_notNeeded() {
    // given
    final var given = new BigComplex(new BigRational("-2", "3"), new BigRational("-3", "5"));

    // when
    final var got = given.normalizeSignum();

    // then
    Assertions.assertSame(given, got);
  }

  @Test
  void testCancelBigComplex_needed() {
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

    final var givenPrimeNumber101 = new BigInteger("547");
    final var givenPrimeNumber102 = new BigInteger("557");
    final var givenPrimeNumber103 = new BigInteger("563");
    final var givenPrimeNumber104 = new BigInteger("569");
    final var givenARational =
        new BigRational(
            productOfFirst100PrimeNumbers.multiply(givenPrimeNumber101)
                + "/"
                + productOfFirst100PrimeNumbers.multiply(givenPrimeNumber102));
    final var givenBRational =
        new BigRational(
            productOfFirst100PrimeNumbers.multiply(givenPrimeNumber103)
                + "/"
                + productOfFirst100PrimeNumbers.multiply(givenPrimeNumber104));
    final var given = new BigComplex<>(givenARational, givenBRational);

    // when
    final var reduced = given.cancel();

    // then
    Assertions.assertEquals(givenPrimeNumber101, reduced.getA().getNumerator());
    Assertions.assertEquals(givenPrimeNumber102, reduced.getA().getDenominator());
    Assertions.assertEquals(givenPrimeNumber103, reduced.getB().getNumerator());
    Assertions.assertEquals(givenPrimeNumber104, reduced.getB().getDenominator());
  }

  @Test
  void testCancelBigComplex_notNeeded() {
    // given
    final var bc = new BigComplex(new BigRational("2", "3"), new BigRational("5", "7"));

    // when
    final var got = bc.cancel();

    // then
    Assertions.assertSame(bc, got);
  }

  static Stream<Arguments> testAdd() {
    return Stream.of(
        Arguments.of(
            new BigComplex(BigRational.ZERO, BigRational.ZERO),
            new BigComplex(BigRational.ZERO, BigRational.ZERO),
            new BigComplex(BigRational.ZERO, BigRational.ZERO)),
        Arguments.of(
            new BigComplex(new BigRational("2"), new BigRational("3")),
            new BigComplex(new BigRational("4"), new BigRational("5")),
            new BigComplex(new BigRational("6"), new BigRational("8"))));
  }

  @ParameterizedTest
  @MethodSource
  void testAdd(BigComplex given1, BigComplex given2, BigComplex expected) {
    // when
    final var got = given1.add(given2);

    // then
    Assertions.assertEquals(expected, got);
  }

  static Stream<Arguments> testSubtract() {
    return Stream.of(
        Arguments.of(
            new BigComplex(BigRational.ZERO, BigRational.ZERO),
            new BigComplex(BigRational.ZERO, BigRational.ZERO),
            new BigComplex(BigRational.ZERO, BigRational.ZERO)),
        Arguments.of(
            new BigComplex(new BigRational("2"), new BigRational("3")),
            new BigComplex(new BigRational("4"), new BigRational("6")),
            new BigComplex(new BigRational("-2"), new BigRational("-3"))));
  }

  @ParameterizedTest
  @MethodSource
  void testSubtract(BigComplex given1, BigComplex given2, BigComplex expected) {
    // when
    final var got = given1.subtract(given2);

    // then
    Assertions.assertEquals(expected, got);
  }

  static Stream<Arguments> testMultiply() {
    return Stream.of(
        Arguments.of(
            new BigComplex(BigRational.ZERO, BigRational.ZERO),
            new BigComplex(BigRational.ZERO, BigRational.ZERO),
            new BigComplex(BigRational.ZERO, BigRational.ZERO)),
        Arguments.of(
            new BigComplex(BigRational.ONE, BigRational.ONE),
            new BigComplex(BigRational.ONE, BigRational.ONE),
            new BigComplex(BigRational.ZERO, new BigRational("2"))),
        Arguments.of(
            new BigComplex(new BigRational("2"), new BigRational("3")),
            new BigComplex(new BigRational("5"), new BigRational("7")),
            new BigComplex(new BigRational("-11"), new BigRational("29"))));
  }

  @ParameterizedTest
  @MethodSource
  void testMultiply(BigComplex given1, BigComplex given2, BigComplex expected) {
    // when
    final var got = given1.multiply(given2);

    // then
    Assertions.assertEquals(expected, got);
  }

  static Stream<Arguments> testDivide() {
    return Stream.of(
        Arguments.of(
            new BigComplex(BigRational.ZERO, BigRational.ZERO),
            new BigComplex(BigRational.ZERO, BigRational.ONE),
            new BigComplex(BigRational.ZERO, BigRational.ZERO)),
        Arguments.of(
            new BigComplex("2+3i"), new BigComplex("5+7i"), new BigComplex("31/74+1/74i")));
  }

  @ParameterizedTest
  @MethodSource
  void testDivide(final BigComplex given1, final BigComplex given2, final BigComplex expected) {
    // when
    final var got = given1.divide(given2);

    // then
    Assertions.assertEquals(expected, got);
  }

  static Stream<Arguments> testModuleSquared() {
    return Stream.of(
        Arguments.of(new BigComplex(BigRational.ZERO, BigRational.ZERO), BigRational.ZERO),
        Arguments.of(
            new BigComplex(new BigRational("-2"), new BigRational("-3")), new BigRational("13")));
  }

  @ParameterizedTest
  @MethodSource
  void testModuleSquared(BigComplex c1, BigRational r) {
    // when
    final var got = c1.absSquared();

    // then
    Assertions.assertEquals(r, got);
  }

  static Stream<Arguments> testNegate() {
    return Stream.of(
        Arguments.of(
            new BigComplex(BigRational.ZERO, BigRational.ZERO),
            new BigComplex(BigRational.ZERO, BigRational.ZERO)),
        Arguments.of(
            new BigComplex(new BigRational("2"), new BigRational("-3")),
            new BigComplex(new BigRational("-2"), new BigRational("3"))));
  }

  @ParameterizedTest
  @MethodSource
  void testNegate(BigComplex given, BigComplex expected) {
    // when
    final var got = given.negate();

    // then
    Assertions.assertEquals(expected, got);
  }

  @Test
  void testInverse() {
    // given
    final var given = new BigComplex(new BigRational("2"), new BigRational("3"));
    final var expected = new BigComplex(new BigRational("2/13"), new BigRational("-3/13"));

    // when
    final var got1 = given.inverse();
    final var got2 = got1.inverse();
    final var got3 = got2.inverse();
    final var gotMultiplyContract = got1.multiply(given);

    // then
    Assertions.assertEquals(expected, got1);
    Assertions.assertEquals(given, got2);
    Assertions.assertEquals(expected, got3);

    Assertions.assertEquals(BigComplex.ONE, gotMultiplyContract);
  }

  @Test
  void testInverse_ZeroDivision() {
    // given
    final var given = new BigComplex(new BigRational("0"), new BigRational("0"));

    // when, then
    Assertions.assertThrows(ArithmeticException.class, () -> given.inverse());
  }

  @Test
  void testConjugate() {
    // given
    final var c1 = new BigComplex(new BigRational("2"), new BigRational("-3"));
    final var c2 = new BigComplex(new BigRational("2"), new BigRational("3"));

    // when
    final var got1 = c1.conjugate();
    final var got2 = c2.conjugate();

    // then
    Assertions.assertEquals(c2, got1);
    Assertions.assertEquals(c1, got2);
  }

  static Stream<Arguments> testCompareTo() {
    return Stream.of(
        Arguments.of(new BigComplex("6/2+6/3i"), new BigComplex("6/3+10/5i"), 1),
        Arguments.of(new BigComplex("6/3+10/5i"), new BigComplex("6/2+6/3i"), -1),
        Arguments.of(new BigComplex("6/3+10/5i"), new BigComplex("6/3+10/5i"), 0));
  }

  @ParameterizedTest
  @MethodSource
  void testCompareTo(BigComplex given1, BigComplex given2, int expected) {
    // when
    final var got = given1.compareTo(given2);

    // then
    Assertions.assertEquals(expected, got);
  }

  static Stream<Arguments> equalsByValue_true() {
    final var given1 = new BigComplex("2/3+3/5i");
    return Stream.of(
        Arguments.of(given1, given1),
        Arguments.of(new BigComplex("2/3+3/5i"), new BigComplex("2/3+3/5i")),
        Arguments.of(new BigComplex("2/3+3/5i"), new BigComplex("4/6+9/15i")));
  }

  @ParameterizedTest
  @MethodSource
  void equalsByValue_true(BigComplex given1, BigComplex given2) {
    // when
    final var got1 = given1.equals(given2);
    final var got2 = given2.equals(given1);

    // then
    Assertions.assertTrue(got1);
    Assertions.assertTrue(got2);
  }

  static Stream<Arguments> equalsByValue_false() {
    final var given1 = new BigComplex("2/3+3/5i");
    return Stream.of(
        Arguments.of(given1, null),
        Arguments.of(new BigComplex("2/3+3/5i"), new BigComplex("4/6+10/15i")),
        Arguments.of(new BigComplex("2/3+3/5i"), new Object()));
  }

  @ParameterizedTest
  @MethodSource
  void equalsByValue_false(BigComplex given1, Object given2) {
    // when
    final var got = given1.equals(given2);

    // then
    Assertions.assertFalse(got);
  }

  static Stream<Arguments> testEqualsStrict_true() {
    final var given1 = new BigComplex("2/3+3/5i");
    return Stream.of(
        Arguments.of(given1, given1),
        Arguments.of(new BigComplex("2/3+3/5i"), new BigComplex("2/3+3/5i")));
  }

  @ParameterizedTest
  @MethodSource
  void testEqualsStrict_true(BigComplex given1, BigComplex given2) {
    // when
    final var got1 = given1.equalsStrict(given2);
    final var got2 = given2.equalsStrict(given1);

    // then
    Assertions.assertTrue(got1);
    Assertions.assertTrue(got2);
  }

  static Stream<Arguments> testEqualsStrict_false() {
    final var given1 = new BigComplex(new BigRational("2", "3"), new BigRational("3", "5"));
    return Stream.of(
        Arguments.of(given1, null),
        Arguments.of(new BigComplex("2/3+3/5i"), new BigComplex("4/6+10/15i")),
        Arguments.of(new BigComplex("2/3+3/5i"), new BigComplex("4/6+9/15i")));
  }

  @ParameterizedTest
  @MethodSource
  void testEqualsStrict_false(BigComplex given1, BigComplex given2) {
    // when
    final var got = given1.equalsStrict(given2);

    // then
    Assertions.assertFalse(got);
  }

  static Stream<Arguments> testHashCode() {
    return Stream.of(
        Arguments.of(new BigComplex("2/3+4/5i"), new BigComplex("2/3+4/5i")),
        Arguments.of(new BigComplex("2/3+4/5i"), new BigComplex("4/6+8/10i")));
  }

  @ParameterizedTest
  @MethodSource
  void testHashCode(BigComplex c1, BigComplex c2) {
    // when
    final var got1 = c1.hashCode();
    final var got2 = c2.hashCode();

    // then
    Assertions.assertNotEquals(0, got1);
    Assertions.assertNotEquals(0, got2);
    Assertions.assertTrue(got1 == got2);
  }

  static Stream<Arguments> testToString() {
    return Stream.of(
        Arguments.of(new BigComplex("2+3i").toString(), "2/1+3/1i"),
        Arguments.of(new BigComplex("-2+3i").toString(), "-2/1+3/1i"),
        Arguments.of(new BigComplex("2-3i").toString(), "2/1-3/1i"),
        Arguments.of(new BigComplex("-2-3i").toString(), "-2/1-3/1i"),
        Arguments.of(new BigComplex("3i").toString(), "0/1+3/1i"),
        Arguments.of(new BigComplex().toString(), "0/1+0/1i"));
  }

  @ParameterizedTest
  @MethodSource
  void testToString(BigComplex given, String expected) {
    // when
    final var got = given.toString();

    // then
    Assertions.assertEquals(expected, got);
  }
}
