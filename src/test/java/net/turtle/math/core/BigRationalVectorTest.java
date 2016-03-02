package net.turtle.math.core;

import org.junit.Assert;
import org.junit.Test;

import net.turtle.math.core.BigRational;
import net.turtle.math.exception.CalculationException;
import net.turtle.math.exception.ParsingException;

public class BigRationalVectorTest {
	
	@Test
	public void testBigVector() {
		{
			Assert.assertEquals( 0, new BigRationalVector().getDimension() );
		}
	}
	
	@Test
	public void testBigVector_String() {
		{
			final BigRationalVector input = new BigRationalVector( "[]" );
			Assert.assertEquals( 0, input.getDimension() );
		}
		{
			final BigRationalVector input = new BigRationalVector( "[111]" );
			Assert.assertEquals( 1, input.getDimension() );
			Assert.assertEquals( new BigRational( "111" ), input.getCoordinates().get( 0 ) );
		}
		{
			final BigRationalVector input = new BigRationalVector( "[2,3]" );
			Assert.assertEquals( 2, input.getDimension() );
			Assert.assertEquals( new BigRational( "2" ), input.getCoordinates().get( 0 ) );
			Assert.assertEquals( new BigRational( "3" ), input.getCoordinates().get( 1 ) );
		}
		{
			final BigRationalVector input = new BigRationalVector( "[1.2,3.4,5/6,-7/8,9.10,0]" );
			Assert.assertEquals( 6, input.getDimension() );
			Assert.assertEquals( new BigRational( "1.2" ), input.getCoordinates().get( 0 ) );
			Assert.assertEquals( new BigRational( "34/10" ), input.getCoordinates().get( 1 ) );
			Assert.assertEquals( new BigRational( "5/6" ), input.getCoordinates().get( 2 ) );
			Assert.assertEquals( new BigRational( "-7/8" ), input.getCoordinates().get( 3 ) );
			Assert.assertEquals( new BigRational( "91/10" ), input.getCoordinates().get( 4 ) );
			Assert.assertEquals( new BigRational( "0/1" ), input.getCoordinates().get( 5 ) );
		}
	}
	
	@Test ( expected = ParsingException.class )
	public void testBigVector_String_null() {
		{
			new BigRationalVector( "test" );
			Assert.fail();
		}
	}
	
	@Test
	public void testAdd() {
		{
			final BigRationalVector bv1 = new BigRationalVector( new BigRational( "1" ), new BigRational( "2" ), new BigRational( "3" ) );
			final BigRationalVector bv2 = new BigRationalVector( new BigRational( "2" ), new BigRational( "4" ), new BigRational( "8" ) );
			final BigRationalVector r1 = new BigRationalVector( new BigRational( "3" ), new BigRational( "6" ), new BigRational( "11" ) );
			Assert.assertArrayEquals( r1.getCoordinates().toArray(), bv1.add( bv2 ).getCoordinates().toArray() );
		}
	}
	
	@Test ( expected = CalculationException.class )
	public void testAdd_Dimensions1() {
		{
			final BigRationalVector bv1 = new BigRationalVector( new BigRational( "1" ), new BigRational( "2" ), new BigRational( "3" ) );
			final BigRationalVector bv2 = new BigRationalVector( new BigRational( "2" ), new BigRational( "4" ) );
			bv1.add( bv2 ).getCoordinates().toArray();
			Assert.fail();
		}
	}
	
	@Test ( expected = CalculationException.class )
	public void testAdd_Dimensions2() {
		{
			final BigRationalVector bv1 = new BigRationalVector( new BigRational( "1" ), new BigRational( "2" ), new BigRational( "3" ) );
			final BigRationalVector bv2 = new BigRationalVector( new BigRational( "2" ), new BigRational( "4" ) );
			bv2.add( bv1 ).getCoordinates().toArray();
			Assert.fail();
		}
	}
	
	@Test
	public void testAdd_Dimensions3() {
		{
			final BigRationalVector bv1 = new BigRationalVector();
			final BigRationalVector bv2 = new BigRationalVector();
			final BigRationalVector r1 = new BigRationalVector();
			Assert.assertArrayEquals( r1.getCoordinates().toArray(), bv1.add( bv2 ).getCoordinates().toArray() );
		}
	}
	
	@Test
	public void testSubstract() {
		{
			final BigRationalVector bv1 = new BigRationalVector( new BigRational( "3" ), new BigRational( "2" ), new BigRational( "1" ) );
			final BigRationalVector bv2 = new BigRationalVector( new BigRational( "2" ), new BigRational( "4" ), new BigRational( "6" ) );
			final BigRationalVector r1 = new BigRationalVector( new BigRational( "1" ), new BigRational( "-2" ), new BigRational( "-5" ) );
			Assert.assertArrayEquals( r1.getCoordinates().toArray(), bv1.subtract( bv2 ).getCoordinates().toArray() );
		}
	}
	
	@Test
	public void testMultiply_BigRational() {
		{
			final BigRationalVector bv1 = new BigRationalVector( new BigRational( "1" ), new BigRational( "2" ), new BigRational( "-3" ) );
			final BigRationalVector r1 = new BigRationalVector( new BigRational( "3" ), new BigRational( "6" ), new BigRational( "-9" ) );
			Assert.assertArrayEquals( r1.getCoordinates().toArray(), bv1.multiply( new BigRational( "3" ) ).getCoordinates().toArray() );
		}
	}
	
	@Test
	public void testDivide_BigRational() {
		{
			final BigRationalVector bv1 = new BigRationalVector( new BigRational( "1" ), new BigRational( "2" ), new BigRational( "-3" ) );
			final BigRationalVector r1 = new BigRationalVector( new BigRational( "1/3" ), new BigRational( "2/3" ), new BigRational( "-1" ) );
			Assert.assertArrayEquals( r1.getCoordinates().toArray(), bv1.divide( new BigRational( "3" ) ).getCoordinates().toArray() );
		}
	}
	
	@Test
	public void testInverse() {
		{
			final BigRationalVector bv1 = new BigRationalVector( new BigRational( "3" ), new BigRational( "2" ), new BigRational( "1" ) );
			final BigRationalVector r1 = new BigRationalVector( new BigRational( "1/3" ), new BigRational( "1/2" ), new BigRational( "1" ) );
			Assert.assertArrayEquals( r1.getCoordinates().toArray(), bv1.inverse().getCoordinates().toArray() );
		}
	}
	
	@Test
	public void testNegate() {
		{
			final BigRationalVector bv1 = new BigRationalVector( new BigRational( "3" ), new BigRational( "2" ), new BigRational( "1" ) );
			final BigRationalVector r1 = new BigRationalVector( new BigRational( "-3" ), new BigRational( "-2" ), new BigRational( "-1" ) );
			Assert.assertArrayEquals( r1.getCoordinates().toArray(), bv1.negate().getCoordinates().toArray() );
		}
	}
}
