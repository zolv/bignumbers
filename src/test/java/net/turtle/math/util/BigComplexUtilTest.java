package net.turtle.math.util;

import org.junit.Assert;
import org.junit.Test;

import net.turtle.math.core.BigComplex;

public class BigComplexUtilTest {
	
	@Test
	public void testToStringShort() {
		{
			Assert.assertEquals( "0", BigComplexUtil.toStringShort( new BigComplex( "0" ) ) );
			Assert.assertEquals( "i", BigComplexUtil.toStringShort( new BigComplex( "i" ) ) );
			Assert.assertEquals( "-i", BigComplexUtil.toStringShort( new BigComplex( "-i" ) ) );
			Assert.assertEquals( "0", BigComplexUtil.toStringShort( new BigComplex( "-0-0i" ) ) );
			Assert.assertEquals( "2+3i", BigComplexUtil.toStringShort( new BigComplex( "2+3i" ) ) );
			Assert.assertEquals( "23/10+34/10i", BigComplexUtil.toStringShort( new BigComplex( "2.3+3.4i" ) ) );
			Assert.assertEquals( "-2/3-3/4i", BigComplexUtil.toStringShort( new BigComplex( "-2/3-3/4i" ) ) );
		}
	}
	
}
