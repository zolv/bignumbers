package net.turtle.math.core;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import net.turtle.math.exception.CalculationException;
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
	
	@Test
	public void testMultiplyMatrix() {
		{
			final BigRationalMatrix input = new BigRationalMatrix( "[]" );
			final BigRationalMatrix output = input.multiply( input );
			Assert.assertEquals( 0, output.getRowsCount() );
			Assert.assertEquals( 0, output.getColumnsCount() );
		}
		{
			final BigRationalMatrix input = new BigRationalMatrix( "[[1,0,2],[-1,3,1]]" );
			final BigRationalMatrix input2 = new BigRationalMatrix( "[[3,1],[2,1],[1,0]]" );
			final BigRationalMatrix output = input.multiply( input2 );
			Assert.assertEquals( 2, output.getRowsCount() );
			Assert.assertEquals( 2, output.getColumnsCount() );
			
			final BigRationalVector rowVector0 = output.getRowVector( 0 );
			final List< BigRational > coordinates0 = rowVector0.getCoordinates();
			Assert.assertEquals( new BigRational( "5" ), coordinates0.get( 0 ) );
			Assert.assertEquals( new BigRational( "1" ), coordinates0.get( 1 ) );
			
			final BigRationalVector rowVector1 = output.getRowVector( 1 );
			final List< BigRational > coordinates1 = rowVector1.getCoordinates();
			Assert.assertEquals( new BigRational( "4" ), coordinates1.get( 0 ) );
			Assert.assertEquals( new BigRational( "2" ), coordinates1.get( 1 ) );
		}
		
		{
			final BigRationalMatrix input = new BigRationalMatrix( "[[2,3,5,7,11]]" );
			final BigRationalMatrix input2 = new BigRationalMatrix( "[[13],[17],[19],[23],[29]]" );
			final BigRationalMatrix output = input.multiply( input2 );
			Assert.assertEquals( 1, output.getRowsCount() );
			Assert.assertEquals( 1, output.getColumnsCount() );
			
			final BigRationalVector rowVector0 = output.getRowVector( 0 );
			final List< BigRational > coordinates0 = rowVector0.getCoordinates();
			Assert.assertEquals( new BigRational( "652" ), coordinates0.get( 0 ) );
		}
		
	}
	
	@Test
	public void testDet() {
		{
			final BigRationalMatrix input = new BigRationalMatrix( "[]" );
			final BigRational output = input.det();
			Assert.assertEquals( BigRationalValues.ZERO, output );
		}
		{
			final BigRationalMatrix input = new BigRationalMatrix( "[[2]]" );
			final BigRational output = input.det();
			Assert.assertEquals( BigRationalValues.TWO, output );
		}
		{
			final BigRationalMatrix input = new BigRationalMatrix( "[[2,3],[5,7]]" );
			final BigRational output = input.det();
			Assert.assertEquals( new BigRational( "-1" ), output );
		}
		{
			final BigRationalMatrix input = new BigRationalMatrix( "[[-2,2,-3],[-1,1,3],[2,0,-1]]" );
			final BigRational output = input.det();
			Assert.assertEquals( new BigRational( "18" ), output );
		}
		{
			final BigRationalMatrix input = new BigRationalMatrix( "[[1,3,0,-1],[0,2,1,3],[3,1,2,1],[-1,2,0,3]]" );
			final BigRational output = input.det();
			Assert.assertEquals( new BigRational( "14" ), output );
		}
		{
			final BigRationalMatrix input = new BigRationalMatrix( "[[0,1,2,7],[1,2,3,4],[5,6,7,8],[-1,1,-1,1]]" );
			final BigRational output = input.det();
			Assert.assertEquals( new BigRational( "-64" ), output );
		}
		
	}
	
	@Test ( expected = CalculationException.class )
	public void testDet_NotSquare() {
		{
			{
				final BigRationalMatrix input = new BigRationalMatrix( "[[0,1,2,7],[1,2,3,4],[5,6,7,8],[-1,1,-1,1],[-1,1,-1,1],[-1,1,-1,1]]" );
				input.det();
				Assert.fail();
			}
		}
	}
}
