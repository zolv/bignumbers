package net.turtle.math.core;

import org.junit.Assert;
import org.junit.Test;

public class BigRationalMatrixTest {

	@Test
	public void testBigMatrix() {
		{
			final BigRationalMatrix emptyMatrix = new BigRationalMatrix();
			Assert.assertEquals( 0 , emptyMatrix.getRowsCount() );
			Assert.assertEquals( 0 , emptyMatrix.getColumnsCount() );
		}
	}

	@Test
	public void testBigMatrix_String() {
		{
			final BigRationalMatrix emptyMatrix = new BigRationalMatrix( "[]" );
			Assert.assertEquals( 0 , emptyMatrix.getRowsCount() );
			Assert.assertEquals( 0 , emptyMatrix.getColumnsCount() );
		}
		{
			final BigRationalMatrix input = new BigRationalMatrix( "[[1,2,3],[4,5,6],[7,8,9]]" );
			Assert.assertEquals( 3 , input.getRowsCount() );
			Assert.assertEquals( 3 , input.getColumnsCount() );
		}
		{
			final BigRationalMatrix input = new BigRationalMatrix( "[[2,3]]" );
			Assert.assertEquals( 1 , input.getRowsCount() );
			Assert.assertEquals( 2 , input.getColumnsCount() );
		}
	}

}
