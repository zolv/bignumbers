package net.turtle.math;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

public class BigRationalTest {

	@Test
	public void testBigRational_String() {
		{
			final BigRational br = new BigRational("-123/456");
			Assert.assertEquals(new BigInteger("-123"), br.getNumerator());
			Assert.assertEquals(new BigInteger("456"), br.getDenominator());
		}
		{
			final BigRational br = new BigRational("-123/-456");
			Assert.assertEquals(new BigInteger("-123"), br.getNumerator());
			Assert.assertEquals(new BigInteger("-456"), br.getDenominator());
		}
		{
			final BigRational br = new BigRational("-123.456");
			Assert.assertEquals(new BigInteger("-123456"), br.getNumerator());
			Assert.assertEquals(new BigInteger("1000"), br.getDenominator());
		}
		{
			final BigRational br = new BigRational("-123456");
			Assert.assertEquals(new BigInteger("-123456"), br.getNumerator());
			Assert.assertEquals(new BigInteger("1"), br.getDenominator());
		}
	}

	@Test
	public void testBigRational_BigDecimal() {
		{
			final BigRational br = new BigRational(new BigDecimal(new BigInteger("2"), 6));
			Assert.assertEquals(new BigInteger("2"), br.getNumerator());
			Assert.assertEquals(new BigInteger("1000000"), br.getDenominator());
		}
		{
			final BigRational br = new BigRational(new BigDecimal(new BigInteger("2"), -6));
			Assert.assertEquals(new BigInteger("2000000"), br.getNumerator());
			Assert.assertEquals(new BigInteger("1"), br.getDenominator());
		}
		{
			final BigRational br = new BigRational(new BigDecimal(new BigInteger("2"), 0));
			Assert.assertEquals(new BigInteger("2"), br.getNumerator());
			Assert.assertEquals(new BigInteger("1"), br.getDenominator());
		}
		{
			final BigRational br = new BigRational(new BigDecimal("-123.456"));
			Assert.assertEquals(new BigInteger("-123456"), br.getNumerator());
			Assert.assertEquals(new BigInteger("1000"), br.getDenominator());
		}
	}

	@Test
	public void testBigRational_BigInteger() {
		{
			final BigRational br = new BigRational(new BigInteger("0"));
			Assert.assertEquals(new BigInteger("0"), br.getNumerator());
			Assert.assertEquals(new BigInteger("1"), br.getDenominator());
		}
		{
			final BigRational br = new BigRational(new BigInteger("1"));
			Assert.assertEquals(new BigInteger("1"), br.getNumerator());
			Assert.assertEquals(new BigInteger("1"), br.getDenominator());
		}
		{
			final BigRational br = new BigRational(new BigInteger("-1"));
			Assert.assertEquals(new BigInteger("-1"), br.getNumerator());
			Assert.assertEquals(new BigInteger("1"), br.getDenominator());
		}
		{
			final Random r = new Random(new Date().getTime());
			final String randomNumerator = Long.valueOf(r.nextLong()).toString();
			final BigRational br = new BigRational(new BigInteger(randomNumerator));
			Assert.assertEquals(new BigInteger(randomNumerator), br.getNumerator());
			Assert.assertEquals(new BigInteger("1"), br.getDenominator());
		}
	}

	@Test
	public void testBigRational_BigInteger_BigInteger() {
		{
			final Random r = new Random(new Date().getTime());
			final String randomNumerator = Long.valueOf(r.nextLong()).toString();
			final String randomDenominator = Long.valueOf(r.nextLong()).toString();
			final BigRational br = new BigRational(new BigInteger(randomNumerator), new BigInteger(randomDenominator));
			Assert.assertEquals(new BigInteger(randomNumerator), br.getNumerator());
			Assert.assertEquals(new BigInteger(randomDenominator), br.getDenominator());
		}
	}

	@Test(expected = ArithmeticException.class)
	public void testBigRational_BigInteger_BigInteger_DivisionByZeroException() {
		final Random r = new Random(new Date().getTime());
		final String randomNumerator = Long.valueOf(r.nextLong()).toString();
		new BigRational(new BigInteger(randomNumerator), new BigInteger("0"));
	}

	@Test(expected = NullPointerException.class)
	public void testBigRational_BigInteger_BigInteger_Null_1() {
		new BigRational(null, BigInteger.valueOf( 2 ));
		Assert.fail();
	}

	@Test(expected = NullPointerException.class)
	public void testBigRational_BigInteger_BigInteger_Null_2() {
		new BigRational(BigInteger.valueOf( 2 ), null);
		Assert.fail();
	}

	@Test
	public void testGetNumerator() {
		Assert.assertEquals(new BigInteger("5"), new BigRational(new BigInteger("5")).getNumerator());
	}

	@Test
	public void testGetDenominator() {
		Assert.assertEquals(new BigInteger("5"), new BigRational(new BigInteger("1"), new BigInteger("5")).getDenominator());
	}

	@Test
	public void testReduce() {
		final long tripledPrimeDivisors = 2 * 2 * 2 * 3 * 3 * 3 * 5 * 5 * 5 * 7 * 7 * 7;
		final BigInteger tripledPrimeDivisors2 = new BigInteger(Long.valueOf(tripledPrimeDivisors).toString());
		final Random r = new Random(new Date().getTime());
		{
			final BigInteger zero = BigInteger.ZERO;
			final BigInteger doubledPrimeDivisors2 = new BigInteger(Long.valueOf(tripledPrimeDivisors).toString());
			final BigRational br = new BigRational(zero, doubledPrimeDivisors2).reduce();
			Assert.assertEquals(zero, br.getNumerator());
			Assert.assertEquals(BigInteger.ONE, br.getDenominator());
		}
		{
			final BigInteger randomMultiplicant = new BigInteger(Long.valueOf(r.nextLong()).toString());
			final BigRational br = new BigRational(randomMultiplicant.multiply(tripledPrimeDivisors2), tripledPrimeDivisors2).reduce();
			Assert.assertEquals(randomMultiplicant, br.getNumerator());
			Assert.assertEquals(BigInteger.ONE, br.getDenominator());
		}
		{
			final BigInteger randomMultiplicant = new BigInteger(Long.valueOf(r.nextLong()).toString());
			final BigRational br = new BigRational(randomMultiplicant.multiply(tripledPrimeDivisors2), tripledPrimeDivisors2).reduce();
			Assert.assertEquals(randomMultiplicant, br.getNumerator());
			Assert.assertEquals(BigInteger.ONE, br.getDenominator());
		}
		{
			final BigRational br = new BigRational(new BigInteger(Long.valueOf(( 2 * 3 * 5 * 7 ) + 1).toString()));
			/*
			 * No new BigRational object is created.
			 */
			Assert.assertSame(br, br.reduce());
		}
	}

	@Test
	public void testReduceArrayBigRational() {
		final BigInteger[] divisors = new BigInteger[] {
			BigInteger.valueOf( 2 ),
			BigInteger.valueOf( 3 ),
			BigInteger.valueOf( 5 ),
			BigInteger.valueOf( 7 ),
		};
		final long tripledPrimeDivisors = 2 * 2 * 2 * 3 * 3 * 3 * 5 * 5 * 5 * 7 * 7 * 7;
		final BigInteger tripledPrimeDivisors2 = new BigInteger(Long.valueOf(tripledPrimeDivisors).toString());
		final Random r = new Random(new Date().getTime());
		{
			final BigInteger doubledPrimeDivisors2 = new BigInteger(Long.valueOf(tripledPrimeDivisors).toString());
			final BigRational br = new BigRational(BigInteger.ZERO, doubledPrimeDivisors2).reduce( divisors );
			Assert.assertEquals(BigInteger.ZERO, br.getNumerator());
			Assert.assertEquals(BigInteger.ONE, br.getDenominator());
		}
		{
			final BigInteger randomMultiplicant = new BigInteger(Long.valueOf(r.nextLong()).toString());
			final BigRational br = new BigRational(randomMultiplicant.multiply(tripledPrimeDivisors2), tripledPrimeDivisors2).reduce( divisors );
			Assert.assertEquals(randomMultiplicant, br.getNumerator());
			Assert.assertEquals(BigInteger.ONE, br.getDenominator());
		}
		{
			final BigInteger randomMultiplicant = new BigInteger(Long.valueOf(r.nextLong()).toString());
			final BigRational br = new BigRational(randomMultiplicant.multiply(tripledPrimeDivisors2), tripledPrimeDivisors2).reduce( divisors );
			Assert.assertEquals(randomMultiplicant, br.getNumerator());
			Assert.assertEquals(BigInteger.ONE, br.getDenominator());
		}
		{
			final BigRational br = new BigRational(new BigInteger(Long.valueOf(( 2 * 3 * 5 * 7 ) + 1).toString()));
			/*
			 * No new BigRational object is created.
			 */
			Assert.assertSame(br, br.reduce());
		}
	}

	@Test(expected=ArithmeticException.class)
	public void testReduceArrayBigInteger_Zero() {
		final BigRational br = new BigRational( new BigInteger( Long.valueOf( ( 2 * 3 * 5 * 7 ) + 1 ).toString() ) );
		br.reduce( new BigInteger[] { BigInteger.ZERO } );
		Assert.fail();
	}

	@Test(expected=NullPointerException.class)
	public void testReduceArrayBigInteger_Null() {
		final BigRational br = new BigRational( new BigInteger( Long.valueOf( ( 2 * 3 * 5 * 7 ) + 1 ).toString() ) );
		br.reduce((BigInteger[])null );
		Assert.fail();
	}

	@Test(expected=NullPointerException.class)
	public void testReduceArrayBigInteger_NullValue() {
		final BigRational br = new BigRational( new BigInteger( Long.valueOf( ( 2 * 3 * 5 * 7 ) + 1 ).toString() ) );
		br.reduce( new BigInteger[] { null } );
		Assert.fail();
	}

	@Test(timeout=1000)
	public void testReduceBigInteger() {
		{
			/*
			 * Infinite loop test.
			 */
			final BigRational br = new BigRational(new BigInteger(Long.valueOf(( 2 * 3 * 5 * 7 ) + 1).toString()));
			Assert.assertSame(br, br.reduce(BigInteger.ONE));
			Assert.assertSame(br, br.reduce(BigInteger.valueOf( -1 )));
		}
		{
			/*
			 * Negative numbers.
			 */
			final BigRational br = new BigRational("4/6").reduce(BigInteger.valueOf( -2 ));

			Assert.assertEquals(BigInteger.valueOf( -2 ), br.getNumerator());
			Assert.assertEquals(BigInteger.valueOf( -3 ), br.getDenominator());
			Assert.assertEquals(1, br.signum());
		}
	}

	@Test(expected=ArithmeticException.class)
	public void testReduceBigInteger_Zero() {
		final BigRational br = new BigRational( new BigInteger( Long.valueOf( ( 2 * 3 * 5 * 7 ) + 1 ).toString() ) );
		br.reduce( BigInteger.ZERO );
		Assert.fail();
	}

	@Test(expected=NullPointerException.class)
	public void testReduceBigInteger_Null() {
		final BigRational br = new BigRational( new BigInteger( Long.valueOf( ( 2 * 3 * 5 * 7 ) + 1 ).toString() ) );
		br.reduce((BigInteger)null );
		Assert.fail();
	}

	@Test
	public void testAdd() {
		{
			final BigRational br1 = new BigRational("2", "3");
			final BigRational br2 = new BigRational("4", "5");
			final BigRational r1 = new BigRational("22", "15");
			Assert.assertEquals(r1, br1.add(br2));
			Assert.assertEquals(r1, br2.add(br1));
		}
		{
			final BigRational br1 = new BigRational("2", "3");
			final BigRational br2 = new BigRational("-4", "5");
			final BigRational r1 = new BigRational("-2", "15");
			Assert.assertEquals(r1, br1.add(br2));
			Assert.assertEquals(r1, br2.add(br1));
		}
		{
			final BigRational br1 = new BigRational("0", "3");
			final BigRational br2 = new BigRational("0", "5");
			final BigRational r1 = new BigRational("0", "15");
			Assert.assertEquals(r1, br1.add(br2));
			Assert.assertEquals(r1, br2.add(br1));
		}
		{
			final BigRational br1 = new BigRational("2", "1");
			final BigRational br2 = new BigRational("3", "1");
			final BigRational r1 = new BigRational("5", "1");
			Assert.assertEquals(r1, br1.add(br2));
			Assert.assertEquals(r1, br2.add(br1));
		}
	}

	@Test(expected = NullPointerException.class)
	public void testAdd_Null() {
		final BigRational br1 = new BigRational("2", "3");
		br1.add(null);
		Assert.fail();
	}

	@Test
	public void testSubtract() {
		{
			final BigRational br1 = new BigRational("2", "3");
			final BigRational br2 = new BigRational("4", "5");
			final BigRational r1 = new BigRational("-2", "15");
			Assert.assertEquals(r1, br1.subtract(br2));
			final BigRational r2 = new BigRational("2", "15");
			Assert.assertEquals(r2, br2.subtract(br1));
		}
		{
			final BigRational br1 = new BigRational("2", "3");
			final BigRational br2 = new BigRational("-4", "5");
			final BigRational r1 = new BigRational("22", "15");
			Assert.assertEquals(r1, br1.subtract(br2));
			final BigRational r2 = new BigRational("-22", "15");
			Assert.assertEquals(r2, br2.subtract(br1));
		}
		{
			final BigRational br1 = new BigRational("0", "3");
			final BigRational br2 = new BigRational("0", "5");
			final BigRational r1 = new BigRational("0", "15");
			Assert.assertEquals(r1, br1.subtract(br2));
			final BigRational r2 = new BigRational("0", "15");
			Assert.assertEquals(r2, br2.subtract(br1));
		}
		{
			final BigRational br1 = new BigRational("2", "1");
			final BigRational br2 = new BigRational("3", "1");
			final BigRational r1 = new BigRational("-1", "1");
			Assert.assertEquals(r1, br1.subtract(br2));
			final BigRational r2 = new BigRational("1", "1");
			Assert.assertEquals(r2, br2.subtract(br1));
		}
	}

	@Test(expected = NullPointerException.class)
	public void testSubstract_Null() {
		final BigRational br1 = new BigRational("2", "3");
		br1.subtract(null);
		Assert.fail();
	}

	@Test
	public void testMultiply() {
		{
			/*
			 * + and +
			 */
			final BigRational br1 = new BigRational("2", "3");
			final BigRational br2 = new BigRational("4", "5");
			final BigRational r1 = new BigRational("8", "15");
			Assert.assertEquals(r1, br1.multiply(br2));
			Assert.assertEquals(r1, br2.multiply(br1));
		}
		{
			/*
			 * + and -
			 */
			final BigRational br1 = new BigRational("2", "3");
			final BigRational br2 = new BigRational("-4", "5");
			final BigRational r1 = new BigRational("-8", "15");
			Assert.assertEquals(r1, br1.multiply(br2));
			Assert.assertEquals(r1, br2.multiply(br1));
		}
		{
			/*
			 * - and -
			 */
			final BigRational br1 = new BigRational("-2", "3");
			final BigRational br2 = new BigRational("-4", "5");
			final BigRational r1 = new BigRational("8", "15");
			Assert.assertEquals(r1, br1.multiply(br2));
			Assert.assertEquals(r1, br2.multiply(br1));
		}
	}

	@Test(expected = NullPointerException.class)
	public void testMultiply_Null() {
		final BigRational br1 = new BigRational("2", "3");
		br1.multiply(null);
		Assert.fail();
	}

	@Test
	public void testDivide() {
		{
			/*
			 * + and +
			 */
			final BigRational br1 = new BigRational("2", "3");
			final BigRational br2 = new BigRational("4", "5");
			final BigRational r1 = new BigRational("10", "12");
			Assert.assertEquals(r1, br1.divide(br2));
			final BigRational r2 = new BigRational("12", "10");
			Assert.assertEquals(r2, br2.divide(br1));
		}
		{
			/*
			 * + and -
			 */
			final BigRational br1 = new BigRational("2", "3");
			final BigRational br2 = new BigRational("-4", "5");
			final BigRational r1 = new BigRational("-10", "12");
			Assert.assertEquals(r1, br1.divide(br2));
			final BigRational r2 = new BigRational("-12", "10");
			Assert.assertEquals(r2, br2.divide(br1));
		}
		{
			/*
			 * - and -
			 */
			final BigRational br1 = new BigRational("-2", "3");
			final BigRational br2 = new BigRational("-4", "5");
			final BigRational r1 = new BigRational("10", "12");
			Assert.assertEquals(r1, br1.divide(br2));
			final BigRational r2 = new BigRational("12", "10");
			Assert.assertEquals(r2, br2.divide(br1));
		}
	}

	@Test(expected = ArithmeticException.class)
	public void testDivide_Zero() {
		final BigRational br1 = new BigRational("2", "3");
		final BigRational br2 = new BigRational("0", "5");
		br1.divide(br2);
		Assert.fail();
	}

	@Test(expected = NullPointerException.class)
	public void testDivide_Null() {
		final BigRational br1 = new BigRational("2", "3");
		br1.divide(null);
		Assert.fail();
	}
	
	@Test
	public void testPowBigInteger() {
		{
			final BigRational br1 = new BigRational("2/3");
			final BigRational r1 = new BigRational("256/6561");
			Assert.assertEquals(r1, br1.pow(BigInteger.valueOf(8)));
		}
		{
			final BigRational br1 = new BigRational("2/3");
			final BigRational r1 = new BigRational("6561/256");
			Assert.assertEquals(r1, br1.pow(BigInteger.valueOf(-8)));
		}
		{
			final BigRational br1 = new BigRational("2");
			final BigRational r1 = new BigRational("1");
			Assert.assertEquals(r1, br1.pow(BigInteger.valueOf(0)));
		}
		{
			final BigRational br1 = new BigRational("0");
			final BigRational r1 = new BigRational("1");
			Assert.assertEquals(r1, br1.pow(BigInteger.valueOf(0)));
		}
	}
	
	@Test(expected=ArithmeticException.class)
	public void testPowBigInteger_ZeroMinusPow() {
		final BigRational br1 = new BigRational("0");
		br1.pow(BigInteger.valueOf(-2));
		Assert.fail();
	}
	
	@Test(expected = NullPointerException.class)
	public void testPowBigInteger_Null() {
		final BigRational br1 = new BigRational("2", "3");
		br1.pow(null);
		Assert.fail();
	}

	@Test
	public void testAbs() {
		final BigRational br1 = new BigRational("-2", "3");
		final BigRational br2 = new BigRational("2", "3");
		final BigRational r1 = new BigRational("2", "3");
		Assert.assertEquals(r1, br1.abs());
		Assert.assertEquals(r1, br2.abs());
	}

	@Test
	public void testNegate() {
		{
			final BigRational br1 = new BigRational("-2", "3");
			final BigRational r1 = new BigRational("2", "3");
			Assert.assertEquals(r1, br1.negate());
		}
		{
			final BigRational br1 = new BigRational("2", "3");
			final BigRational r1 = new BigRational("-2", "3");
			Assert.assertEquals(r1, br1.negate());
		}
		{
			final BigRational br1 = new BigRational("-2", "-3");
			final BigRational r1 = new BigRational("2", "-3");
			Assert.assertEquals(r1, br1.negate());
		}
		{
			final BigRational br1 = new BigRational("0", "3");
			final BigRational r1 = new BigRational("0", "3");
			Assert.assertEquals(r1, br1.negate());
		}
	}

	@Test
	public void testSignum() {
		{
			final BigRational br1 = new BigRational("-2", "3");
			Assert.assertEquals(-1, br1.signum());
		}
		{
			final BigRational br1 = new BigRational("2", "3");
			Assert.assertEquals(1, br1.signum());
		}
		{
			final BigRational br1 = new BigRational("0", "3");
			Assert.assertEquals(0, br1.signum());
		}
	}

	@Test
	public void testMin() {
		{
			final BigRational br1 = new BigRational("-2", "3");
			final BigRational br2 = new BigRational("2", "5");
			Assert.assertSame(br1, br1.min(br2));
			Assert.assertSame(br1, br2.min(br1));
		}
		{
			final BigRational br1 = new BigRational("2", "3");
			final BigRational br2 = new BigRational("4", "6");
			Assert.assertSame(br1, br1.min(br2));
			Assert.assertSame(br2, br2.min(br1));
		}
		{
			final BigRational br1 = new BigRational("0", "3");
			final BigRational br2 = new BigRational("0", "5");
			Assert.assertSame(br1, br1.min(br2));
			Assert.assertSame(br2, br2.min(br1));
		}
	}

	@Test(expected=NullPointerException.class)
	public void testMin_Null() {
		final BigRational br1 = new BigRational("-2", "3");
		br1.min(null);
		Assert.fail();
	}

	@Test
	public void testMax() {
		{
			final BigRational br1 = new BigRational("-2", "3");
			final BigRational br2 = new BigRational("2", "5");
			Assert.assertSame(br2, br1.max(br2));
			Assert.assertSame(br2, br2.max(br1));
		}
		{
			final BigRational br1 = new BigRational("2", "3");
			final BigRational br2 = new BigRational("4", "6");
			Assert.assertSame(br2, br1.max(br2));
			Assert.assertSame(br1, br2.max(br1));
		}
		{
			final BigRational br1 = new BigRational("0", "3");
			final BigRational br2 = new BigRational("0", "5");
			Assert.assertSame(br2, br1.max(br2));
			Assert.assertSame(br1, br2.max(br1));
		}
	}

	@Test(expected=NullPointerException.class)
	public void testMax_Null() {
		final BigRational br1 = new BigRational("-2", "3");
		br1.max(null);
		Assert.fail();
	}

	@Test
	public void testCompareTo() {
		{
			final BigRational br1 = new BigRational("2", "3");
			final BigRational br2 = new BigRational("4", "5");
			Assert.assertEquals(-1, br1.compareTo(br2));
			Assert.assertEquals(1, br2.compareTo(br1));
		}
		{
			final BigRational br1 = new BigRational("-2", "3");
			final BigRational br2 = new BigRational("4", "5");
			Assert.assertEquals(-1, br1.compareTo(br2));
			Assert.assertEquals(1, br2.compareTo(br1));
		}
		{
			final BigRational br1 = new BigRational("-2", "3");
			final BigRational br2 = new BigRational("-4", "5");
			Assert.assertEquals(1, br1.compareTo(br2));
			Assert.assertEquals(-1, br2.compareTo(br1));
		}

		{
			final BigRational br1 = new BigRational("2", "3");
			final BigRational br2 = new BigRational("4", "6");
			Assert.assertEquals(0, br1.compareTo(br2));
			Assert.assertEquals(0, br2.compareTo(br1));
		}
		{
			final BigRational br1 = new BigRational("-2", "3");
			final BigRational br2 = new BigRational("-4", "6");
			Assert.assertEquals(0, br1.compareTo(br2));
			Assert.assertEquals(0, br2.compareTo(br1));
		}

		{
			final BigRational br1 = new BigRational("0", "3");
			final BigRational br2 = new BigRational("0", "5");
			Assert.assertEquals(0, br1.compareTo(br2));
			Assert.assertEquals(0, br2.compareTo(br1));
		}
	}

	@Test
	public void testEqualsObject() {
		{
			/*
			 * Equals null
			 */
			final BigRational br1 = new BigRational("2", "3");
			Assert.assertFalse(br1.equals(null));
		}
		{
			/*
			 * Same instance
			 */
			final BigRational br1 = new BigRational("2", "3");
			Assert.assertTrue(br1.equals(br1));
		}

		{
			final BigRational br1 = new BigRational("2", "3");
			final BigRational br2 = new BigRational("4", "5");
			Assert.assertFalse(br1.equals(br2));
			Assert.assertFalse(br2.equals(br1));
		}

		{
			/*
			 * Equal by value
			 */
			final BigRational br1 = new BigRational("2", "3");
			final BigRational br2 = new BigRational("4", "6");
			Assert.assertTrue(br1.equals(br2));
			Assert.assertTrue(br2.equals(br1));
		}
		{
			/*
			 * Equal by value
			 */
			final BigRational br1 = new BigRational("-2", "3");
			final BigRational br2 = new BigRational("-4", "6");
			Assert.assertTrue(br1.equals(br2));
			Assert.assertTrue(br2.equals(br1));
		}

		{
			/*
			 * Zero
			 */
			final BigRational br1 = new BigRational("0", "3");
			final BigRational br2 = new BigRational("0", "-5");
			Assert.assertTrue(br1.equals(br2));
			Assert.assertTrue(br2.equals(br1));
		}
	}

	@Test
	public void testHashCode() {
		{
			final BigRational br1 = new BigRational(new BigInteger("2"), new BigInteger("3"));
			final BigRational br2 = new BigRational(new BigInteger("2"), new BigInteger("3"));
			Assert.assertTrue(br1.hashCode() == br2.hashCode());
		}
		{
			final BigRational br1 = new BigRational(new BigInteger("2"), new BigInteger("3"));
			final BigRational br2 = new BigRational(new BigInteger("4"), new BigInteger("6"));
			Assert.assertFalse(br1.hashCode() == br2.hashCode());
		}
	}

	@Test
	public void testToString() {
		Assert.assertEquals("2/1", new BigRational("2/1").toString());
		Assert.assertEquals("2/3", new BigRational("2", "3").toString());
		Assert.assertEquals("-2/3", new BigRational("-2", "3").toString());
		Assert.assertEquals("2/-3", new BigRational("2", "-3").toString());
		Assert.assertEquals("-2/-3", new BigRational("-2", "-3").toString());
	}

	@Test
	public void testPiE() {
		final BigRational pi1000 = new BigRational("3.1415926535897932384626433832795028841971693993751058209749445923078164062862089986280348253421170679821480865132823066470938446095505822317253594081284811174502841027019385211055596446229489549303819644288109756659334461284756482337867831652712019091456485669234603486104543266482133936072602491412737245870066063155881748815209209628292540917153643678925903600113305305488204665213841469519415116094330572703657595919530921861173819326117931051185480744623799627495673518857527248912279381830119491298336733624406566430860213949463952247371907021798609437027705392171762931767523846748184676694051320005681271452635608277857713427577896091736371787214684409012249534301465495853710507922796892589235420199561121290219608640344181598136297747713099605187072113499999983729780499510597317328160963185950244594553469083026425223082533446850352619311881710100031378387528865875332083814206171776691473035982534904287554687311595628638823537875937519577818577805321712268066130019278766111959092164201989");
		final BigRational e1000 = new BigRational("2.7182818284590452353602874713526624977572470936999595749669676277240766303535475945713821785251664274274663919320030599218174135966290435729003342952605956307381323286279434907632338298807531952510190115738341879307021540891499348841675092447614606680822648001684774118537423454424371075390777449920695517027618386062613313845830007520449338265602976067371132007093287091274437470472306969772093101416928368190255151086574637721112523897844250569536967707854499699679468644549059879316368892300987931277361782154249992295763514822082698951936680331825288693984964651058209392398294887933203625094431173012381970684161403970198376793206832823764648042953118023287825098194558153017567173613320698112509961818815930416903515988885193458072738667385894228792284998920868058257492796104841984443634632449684875602336248270419786232090021609902353043699418491463140934317381436405462531520961836908887070167683964243781405927145635490613031072085103837505101157477041718986106873969655212671546889570350354");
	}

}
