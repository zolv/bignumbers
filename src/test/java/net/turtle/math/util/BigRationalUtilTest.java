package net.turtle.math.util;

import org.junit.Assert;
import org.junit.Test;

import net.turtle.math.core.BigRational;
import net.turtle.math.exception.CalculationException;
import net.turtle.math.util.BigRationalUtil;

public class BigRationalUtilTest {

	@Test
	public void testToStringNormalized() {
		Assert.assertEquals( "2" , BigRationalUtil.toStringNormalized( new BigRational( "2/1" ) ) );
		Assert.assertEquals( "-2" , BigRationalUtil.toStringNormalized( new BigRational( "2/-1" ) ) );
		Assert.assertEquals( "2/3" , BigRationalUtil.toStringNormalized( new BigRational( "2" , "3" ) ) );
		Assert.assertEquals( "-2/3" , BigRationalUtil.toStringNormalized( new BigRational( "-2" , "3" ) ) );
		Assert.assertEquals( "-2/3" , BigRationalUtil.toStringNormalized( new BigRational( "2" , "-3" ) ) );
		Assert.assertEquals( "2/3" , BigRationalUtil.toStringNormalized( new BigRational( "-2" , "-3" ) ) );
		Assert.assertEquals( "-2/3" , BigRationalUtil.toStringNormalized( new BigRational( "+2" , "-3" ) ) );
	}

	@Test
	public void testFactorial() {
		{
			final BigRational result = new BigRational( "1" );
			Assert.assertEquals( result , BigRationalUtil.factorial( new BigRational( "0" ) ) );
			Assert.assertEquals( result , BigRationalUtil.factorial( new BigRational( "1" ) ) );
		}
		{
			final BigRational result = new BigRational( "120" );
			Assert.assertEquals( result , BigRationalUtil.factorial( new BigRational( "5" ) ) );
		}
		{
			final BigRational result = new BigRational( "2432902008176640000" );
			Assert.assertEquals( result , BigRationalUtil.factorial( new BigRational( "20" ) ) );
		}
	}

	@Test ( expected = CalculationException.class )
	public void testFactorial_negative() {
		BigRationalUtil.factorial( new BigRational( "-1" ) );
		Assert.fail();
	}

	@Test ( expected = NullPointerException.class )
	public void testFactorial_null() {
		BigRationalUtil.factorial( null );
		Assert.fail();
	}

}
