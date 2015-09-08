package net.turtle.math.core;

import org.junit.Assert;
import org.junit.Test;

import net.turtle.math.core.BigRational;

public class BigVectorTest {

	@Test
	public void testBigVector() {
		{
			Assert.assertEquals( 0 , new BigVector().getDimension() );
		}
	}

	@Test
	public void testAdd() {
		{
			final BigVector bv1 = new BigVector( new BigRational("1"), new BigRational("2"), new BigRational("3") );
			final BigVector bv2 = new BigVector( new BigRational("2"), new BigRational("4"), new BigRational("8") );
			final BigVector r1 = new BigVector( new BigRational("3"), new BigRational("6"), new BigRational("11") );
			Assert.assertArrayEquals( r1.getCoordinates().toArray() , bv1.add( bv2 ).getCoordinates().toArray() );
		}
	}

	@Test
	public void testSubstract() {
		{
			final BigVector bv1 = new BigVector( new BigRational("3"), new BigRational("2"), new BigRational("1") );
			final BigVector bv2 = new BigVector( new BigRational("2"), new BigRational("4"), new BigRational("6") );
			final BigVector r1 = new BigVector( new BigRational("1"), new BigRational("-2"), new BigRational("-5") );
			Assert.assertArrayEquals( r1.getCoordinates().toArray() , bv1.subtract( bv2 ).getCoordinates().toArray() );
		}
	}

	@Test
	public void testMultiply_BigRational() {
		{
			final BigVector bv1 = new BigVector( new BigRational("1"), new BigRational("2"), new BigRational("-3") );
			final BigVector r1 = new BigVector( new BigRational("3"), new BigRational("6"), new BigRational("-9") );
			Assert.assertArrayEquals( r1.getCoordinates().toArray() , bv1.multiply( new BigRational("3") ).getCoordinates().toArray() );
		}
	}

	@Test
	public void testDivide_BigRational() {
		{
			final BigVector bv1 = new BigVector( new BigRational("1"), new BigRational("2"), new BigRational("-3") );
			final BigVector r1 = new BigVector( new BigRational("1/3"), new BigRational("2/3"), new BigRational("-1") );
			Assert.assertArrayEquals( r1.getCoordinates().toArray() , bv1.divide( new BigRational("3") ).getCoordinates().toArray() );
		}
	}
}
