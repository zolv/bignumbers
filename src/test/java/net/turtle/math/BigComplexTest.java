package net.turtle.math;

import org.junit.Assert;
import org.junit.Test;


public class BigComplexTest {

	@Test
	public void testBigComplexBigRational() {
		Assert.assertEquals(new BigRational("2"), new BigComplex(new BigRational("2")).getA());
		Assert.assertEquals(BigRational.ZERO, new BigComplex(new BigRational("2")).getB());
	}

	@Test
	public void testBigComplexBigRationalBigRational() {
		{
			final BigComplex c1 = new BigComplex(new BigRational("2"), new BigRational("3"));
			Assert.assertEquals(new BigRational("2"), c1.getA());
			Assert.assertEquals(new BigRational("3"), c1.getB());
		}
	}

//	@Test
//	public void testBigComplexString() {
//		{
//			final BigComplex c1 = new BigComplex("2");
//			Assert.assertEquals(new BigRational("2"), c1.getA());
//			Assert.assertEquals(BigRational.ZERO, c1.getB());
//		}
//		{
//			final BigComplex c1 = new BigComplex("3i");
//			Assert.assertEquals(BigRational.ZERO, c1.getA());
//			Assert.assertEquals(new BigRational("3"), c1.getB());
//		}
//		{
//			final BigComplex c1 = new BigComplex("2");
//			Assert.assertEquals(new BigRational("2"), c1.getA());
//			Assert.assertEquals(BigRational.ZERO, c1.getB());
//		}
//		{
//			final BigComplex c1 = new BigComplex("2+3i");
//			Assert.assertEquals(new BigRational("2"), c1.getA());
//			Assert.assertEquals(new BigRational("3"), c1.getB());
//		}
//		{
//			final BigComplex c1 = new BigComplex("-2-3i");
//			Assert.assertEquals(new BigRational("-2"), c1.getA());
//			Assert.assertEquals(new BigRational("-3"), c1.getB());
//		}
//		{
//			final BigComplex c1 = new BigComplex("-2/3-5/7i");
//			Assert.assertEquals(new BigRational("-2/3"), c1.getA());
//			Assert.assertEquals(new BigRational("-5/7"), c1.getB());
//		}
//	}

	@Test(expected=NullPointerException.class)
	public void testBigComplexBigRationalBigRational_nullA() {
			new BigComplex(null, new BigRational("3"));
			Assert.fail();
	}

	@Test(expected=NullPointerException.class)
	public void testBigComplexBigRationalBigRational_nullB() {
			new BigComplex(new BigRational("3"), null);
			Assert.fail();
	}

	@Test
	public void testGetA() {
		Assert.assertEquals(new BigRational("2"), new BigComplex(new BigRational("2"), new BigRational("3")).getA());
	}

	@Test(expected=NullPointerException.class)
	public void testGetA_null() {
		new BigComplex(null, new BigRational("3")).getA();
		Assert.fail();
	}

	@Test
	public void testGetB() {
		Assert.assertEquals(new BigRational("3"), new BigComplex(new BigRational("2"), new BigRational("3")).getB());
	}

	@Test(expected=NullPointerException.class)
	public void testGetB_null() {
		new BigComplex(new BigRational("2"), null).getB();
		Assert.fail();
	}

	@Test
	public void testReduce() {
		final long tripledPrimeDivisors = 2 * 2 * 2 * 3 * 3 * 3 * 5 * 5 * 5 * 7 * 7 * 7;
		final Long tripledPrimeDivisorsLong = Long.valueOf(tripledPrimeDivisors);
		final String tripledPrimeDivisorsString = tripledPrimeDivisorsLong.toString();
		final String oneAsTriplePrimeDivisorsString = tripledPrimeDivisorsString + "/" + tripledPrimeDivisorsString;
		{
			final BigRational a = new BigRational(oneAsTriplePrimeDivisorsString);
			final BigRational b = new BigRational(oneAsTriplePrimeDivisorsString);
			final BigComplex br = new BigComplex(a, b);
			final BigComplex one = new BigComplex(BigRational.ONE, BigRational.ONE);
			Assert.assertEquals(one, br.reduce());
			Assert.assertEquals(BigRational.ONE, br.reduce().getA());
			Assert.assertEquals(BigRational.ONE, br.reduce().getB());
		}
	}

	@Test
	public void testAdd() {
		{
			final BigComplex c1 = new BigComplex(BigRational.ZERO, BigRational.ZERO);
			final BigComplex c2 = new BigComplex(BigRational.ZERO, BigRational.ZERO);
			final BigComplex cr = new BigComplex(BigRational.ZERO, BigRational.ZERO);
			Assert.assertEquals(cr, c1.add(c2));
		}
		{
			final BigRational a1 = new BigRational("2");
			final BigRational b1 = new BigRational("3");
			final BigComplex c1 = new BigComplex(a1,b1);

			final BigRational a2 = new BigRational("4");
			final BigRational b2 = new BigRational("5");
			final BigComplex c2 = new BigComplex(a2,b2);

			final BigRational ar = new BigRational("6");
			final BigRational br = new BigRational("8");
			final BigComplex cr = new BigComplex(ar,br);

			Assert.assertEquals(cr, c1.add(c2));
		}
	}

	@Test
	public void testSubtract() {
		{
			final BigComplex c1 = new BigComplex(BigRational.ZERO, BigRational.ZERO);
			final BigComplex c2 = new BigComplex(BigRational.ZERO, BigRational.ZERO);
			final BigComplex cr = new BigComplex(BigRational.ZERO, BigRational.ZERO);
			Assert.assertEquals(cr, c1.subtract(c2));
		}
		{
			final BigRational a1 = new BigRational("2");
			final BigRational b1 = new BigRational("3");
			final BigComplex c1 = new BigComplex(a1,b1);

			final BigRational a2 = new BigRational("4");
			final BigRational b2 = new BigRational("6");
			final BigComplex c2 = new BigComplex(a2,b2);

			final BigRational ar = new BigRational("-2");
			final BigRational br = new BigRational("-3");
			final BigComplex cr = new BigComplex(ar,br);

			Assert.assertEquals(cr, c1.subtract(c2));
		}
	}

	@Test
	public void testMultiply() {
		{
			final BigComplex c1 = new BigComplex(BigRational.ZERO, BigRational.ZERO);
			final BigComplex c2 = new BigComplex(BigRational.ZERO, BigRational.ZERO);
			final BigComplex cr = new BigComplex(BigRational.ZERO, BigRational.ZERO);
			Assert.assertEquals(cr, c1.multiply(c2));
		}
		{
			final BigRational a1 = new BigRational("2");
			final BigRational b1 = new BigRational("3");
			final BigComplex c1 = new BigComplex(a1,b1);

			final BigRational a2 = new BigRational("5");
			final BigRational b2 = new BigRational("7");
			final BigComplex c2 = new BigComplex(a2,b2);

			final BigRational ar = new BigRational("-11");
			final BigRational br = new BigRational("29");
			final BigComplex cr = new BigComplex(ar,br);

			Assert.assertEquals(cr, c1.multiply(c2));
		}
	}

	@Test
	public void testDivide() {
		{
			final BigComplex c1 = new BigComplex(BigRational.ZERO, BigRational.ZERO);
			final BigComplex c2 = new BigComplex(BigRational.ZERO, BigRational.ONE);
			final BigComplex cr = new BigComplex(BigRational.ZERO, BigRational.ZERO);
			Assert.assertEquals(cr, c1.divide(c2));
		}
		{
			final BigRational a1 = new BigRational("2");
			final BigRational b1 = new BigRational("3");
			final BigComplex c1 = new BigComplex(a1,b1);

			final BigRational a2 = new BigRational("5");
			final BigRational b2 = new BigRational("7");
			final BigComplex c2 = new BigComplex(a2,b2);

			final BigRational ar = new BigRational("31/74");
			final BigRational br = new BigRational("1/74");
			final BigComplex cr = new BigComplex(ar,br);

			Assert.assertEquals(cr, c1.divide(c2));
		}
	}

	@Test
	public void testModuleSquared() {
		{
			final BigComplex c1 = new BigComplex(BigRational.ZERO, BigRational.ZERO);
			final BigRational r = BigRational.ZERO;
			Assert.assertEquals(r, c1.absSquared());
		}
		{
			final BigComplex c1 = new BigComplex(new BigRational("-2"), new BigRational("-3"));
			final BigRational r = new BigRational("13");
			Assert.assertEquals(r, c1.absSquared());
		}
	}

	@Test
	public void testNegate() {
		{
			final BigComplex c1 = new BigComplex(BigRational.ZERO, BigRational.ZERO);
			final BigComplex cr = new BigComplex(BigRational.ZERO, BigRational.ZERO);
			Assert.assertEquals(cr, c1.negate());
		}
		{
			final BigRational a1 = new BigRational("2");
			final BigRational b1 = new BigRational("3");
			final BigComplex c1 = new BigComplex(a1,b1);

			final BigRational ar = new BigRational("-2");
			final BigRational br = new BigRational("-3");
			final BigComplex cr = new BigComplex(ar,br);

			Assert.assertEquals(cr, c1.negate());
		}
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
			final BigRational a1 = new BigRational("2/3");
			final BigRational b1 = new BigRational("4/5");
			final BigComplex c1 = new BigComplex(a1,b1);
			final BigRational a2 = new BigRational("2/3");
			final BigRational b2 = new BigRational("4/5");
			final BigComplex c2 = new BigComplex(a2,b2);
			Assert.assertTrue(c1.hashCode() == c2.hashCode());
		}
		{
			final BigRational a1 = new BigRational("2/3");
			final BigRational b1 = new BigRational("4/5");
			final BigComplex c1 = new BigComplex(a1,b1);
			final BigRational a2 = new BigRational("4/6");
			final BigRational b2 = new BigRational("8/10");
			final BigComplex c2 = new BigComplex(a2,b2);
			Assert.assertFalse(c1.hashCode() == c2.hashCode());
		}
	}

	@Test
	public void testToString() {
		Assert.assertEquals("2/1+3/1i", new BigComplex(new BigRational("2"), new BigRational("3")).toString());
		Assert.assertEquals("-2/1+3/1i", new BigComplex(new BigRational("-2"), new BigRational("3")).toString());
		Assert.assertEquals("2/1-3/1i", new BigComplex(new BigRational("2"), new BigRational("-3")).toString());
		Assert.assertEquals("-2/1-3/1i", new BigComplex(new BigRational("-2"), new BigRational("-3")).toString());
		Assert.assertEquals("0/1+3/1i", new BigComplex(new BigRational("0"), new BigRational("3")).toString());
		Assert.assertEquals("0/1+0/1i", new BigComplex(new BigRational("0"), new BigRational("0")).toString());
	}

}
