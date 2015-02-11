package net.turtle.math;

import org.junit.Assert;
import org.junit.Test;

public class BigRationalUtilTest {

	@Test
	public void testToStringNormalized() {
		Assert.assertEquals("2", BigRationalUtil.toStringNormalized(new BigRational("2/1")));
		Assert.assertEquals("-2", BigRationalUtil.toStringNormalized(new BigRational("2/-1")));
		Assert.assertEquals("2/3", BigRationalUtil.toStringNormalized(new BigRational("2", "3")));
		Assert.assertEquals("-2/3", BigRationalUtil.toStringNormalized(new BigRational("-2", "3")));
		Assert.assertEquals("-2/3", BigRationalUtil.toStringNormalized(new BigRational("2", "-3")));
		Assert.assertEquals("2/3", BigRationalUtil.toStringNormalized(new BigRational("-2", "-3")));
		Assert.assertEquals("-2/3", BigRationalUtil.toStringNormalized(new BigRational("+2", "-3")));
	}

}


