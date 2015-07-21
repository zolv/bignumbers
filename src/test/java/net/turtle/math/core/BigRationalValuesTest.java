package net.turtle.math.core;

import org.junit.Assert;
import org.junit.Test;


public class BigRationalValuesTest {

	@Test
	public void initialize() throws Exception {
		Assert.assertEquals(BigRationalValues.ZERO, BigRationalValues.ZERO.multiply(BigRationalValues.ONE));
	}

}
