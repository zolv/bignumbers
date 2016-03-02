package net.turtle.math.core;

import org.junit.Assert;
import org.junit.Test;

public class BigComplexValuesTest {
	
	
	@Test
	public void initialize() throws Exception {
		Assert.assertEquals( BigComplexValues.ZERO, BigComplexValues.ZERO.multiply( BigComplexValues.I ) );
	}
	
}
