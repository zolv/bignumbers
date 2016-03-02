package net.turtle.math.core;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import net.turtle.math.exception.DifferentDimensionsException;
import net.turtle.math.exception.ParsingException;

public class BigRationalMatrixTest {
	
	@Test
	public void testBigMatrix() {
		{
			final BigRationalMatrix emptyMatrix = new BigRationalMatrix();
			Assert.assertEquals( 0, emptyMatrix.getRowsCount() );
			Assert.assertEquals( 0, emptyMatrix.getColumnsCount() );
		}
	}
	
	@Test
	public void testBigMatrix_String() {
		{
			final BigRationalMatrix emptyMatrix = new BigRationalMatrix( "[]" );
			Assert.assertEquals( 0, emptyMatrix.getRowsCount() );
			Assert.assertEquals( 0, emptyMatrix.getColumnsCount() );
		}
		{
			final BigRationalMatrix input = new BigRationalMatrix( "[[1,2,3],[4,5,6],[7,8,9]]" );
			Assert.assertEquals( 3, input.getRowsCount() );
			Assert.assertEquals( 3, input.getColumnsCount() );
		}
		{
			final BigRationalMatrix input = new BigRationalMatrix( "[[2,3]]" );
			Assert.assertEquals( 1, input.getRowsCount() );
			Assert.assertEquals( 2, input.getColumnsCount() );
		}
	}
	
	@Test ( expected = DifferentDimensionsException.class )
	public void testBigMatrix_String_Dimentions() {
		{
			new BigRationalMatrix( "[[1,2,3],[4,5],[6,7,8]]" );
			Assert.fail();
		}
	}
	
	@Test ( expected = ParsingException.class )
	public void testBigMatrix_String_MatrixParsing() {
		{
			new BigRationalMatrix( "[1,2,3]" );
			Assert.fail();
		}
	}
	
	@Test ( expected = ParsingException.class )
	public void testBigMatrix_String_MatrixParsing2() {
		{
			new BigRationalMatrix( "[[][]" );
			Assert.fail();
		}
	}
	
	@Test
	public void testTranspose() {
		{
			final BigRationalMatrix input = new BigRationalMatrix( "[]" );
			final BigRationalMatrix output = input.transpose();
			Assert.assertEquals( 0, output.getRowsCount() );
			Assert.assertEquals( 0, output.getColumnsCount() );
		}
		{
			final BigRationalMatrix input = new BigRationalMatrix( "[[1,2,3],[4,5,6]]" );
			final BigRationalMatrix output = input.transpose();
			Assert.assertEquals( 3, output.getRowsCount() );
			Assert.assertEquals( 2, output.getColumnsCount() );
			
			final BigRationalVector rowVector0 = output.getRowVector( 0 );
			final List< BigRational > coordinates0 = rowVector0.getCoordinates();
			Assert.assertEquals( new BigRational( "1" ), coordinates0.get( 0 ) );
			Assert.assertEquals( new BigRational( "4" ), coordinates0.get( 1 ) );
			
			final BigRationalVector rowVector1 = output.getRowVector( 1 );
			final List< BigRational > coordinates1 = rowVector1.getCoordinates();
			Assert.assertEquals( new BigRational( "2" ), coordinates1.get( 0 ) );
			Assert.assertEquals( new BigRational( "5" ), coordinates1.get( 1 ) );
			
			final BigRationalVector rowVector2 = output.getRowVector( 2 );
			final List< BigRational > coordinates2 = rowVector2.getCoordinates();
			Assert.assertEquals( new BigRational( "3" ), coordinates2.get( 0 ) );
			Assert.assertEquals( new BigRational( "6" ), coordinates2.get( 1 ) );
		}
		
	}
	
	@Test
	public void testAddMatrix() {
		{
			final BigRationalMatrix input = new BigRationalMatrix( "[]" );
			final BigRationalMatrix output = input.add( input );
			Assert.assertEquals( 0, output.getRowsCount() );
			Assert.assertEquals( 0, output.getColumnsCount() );
		}
		{
			final BigRationalMatrix input = new BigRationalMatrix( "[[1,2,3],[4,5,6]]" );
			final BigRationalMatrix input2 = new BigRationalMatrix( "[[7,8,9],[10,11,12]]" );
			final BigRationalMatrix output = input.add( input2 );
			Assert.assertEquals( 2, output.getRowsCount() );
			Assert.assertEquals( 3, output.getColumnsCount() );
			
			final BigRationalVector rowVector0 = output.getRowVector( 0 );
			final List< BigRational > coordinates0 = rowVector0.getCoordinates();
			Assert.assertEquals( new BigRational( "8" ), coordinates0.get( 0 ) );
			Assert.assertEquals( new BigRational( "10" ), coordinates0.get( 1 ) );
			Assert.assertEquals( new BigRational( "12" ), coordinates0.get( 2 ) );
			
			final BigRationalVector rowVector1 = output.getRowVector( 1 );
			final List< BigRational > coordinates1 = rowVector1.getCoordinates();
			Assert.assertEquals( new BigRational( "14" ), coordinates1.get( 0 ) );
			Assert.assertEquals( new BigRational( "16" ), coordinates1.get( 1 ) );
			Assert.assertEquals( new BigRational( "18" ), coordinates1.get( 2 ) );
		}
	}
	
	@Test
	public void testSubtractMatrix() {
		{
			final BigRationalMatrix input = new BigRationalMatrix( "[]" );
			final BigRationalMatrix output = input.substract( input );
			Assert.assertEquals( 0, output.getRowsCount() );
			Assert.assertEquals( 0, output.getColumnsCount() );
		}
		{
			final BigRationalMatrix input = new BigRationalMatrix( "[[1,6,5],[12,7,11]]" );
			final BigRationalMatrix input2 = new BigRationalMatrix( "[[4,3,2],[9,10,8]]" );
			final BigRationalMatrix output = input.substract( input2 );
			Assert.assertEquals( 2, output.getRowsCount() );
			Assert.assertEquals( 3, output.getColumnsCount() );
			
			final BigRationalVector rowVector0 = output.getRowVector( 0 );
			final List< BigRational > coordinates0 = rowVector0.getCoordinates();
			Assert.assertEquals( new BigRational( "-3" ), coordinates0.get( 0 ) );
			Assert.assertEquals( new BigRational( "3" ), coordinates0.get( 1 ) );
			Assert.assertEquals( new BigRational( "3" ), coordinates0.get( 2 ) );
			
			final BigRationalVector rowVector1 = output.getRowVector( 1 );
			final List< BigRational > coordinates1 = rowVector1.getCoordinates();
			Assert.assertEquals( new BigRational( "3" ), coordinates1.get( 0 ) );
			Assert.assertEquals( new BigRational( "-3" ), coordinates1.get( 1 ) );
			Assert.assertEquals( new BigRational( "3" ), coordinates1.get( 2 ) );
		}
		
	}
	
}
