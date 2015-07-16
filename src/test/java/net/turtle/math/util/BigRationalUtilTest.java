package net.turtle.math.util;

import org.junit.Assert;
import org.junit.Test;

import net.turtle.math.core.BigRational;
import net.turtle.math.util.BigRationalUtil;

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


