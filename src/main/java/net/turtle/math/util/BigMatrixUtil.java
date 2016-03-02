package net.turtle.math.util;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.turtle.math.core.BigComplexVector;
import net.turtle.math.core.BigRationalVector;
import net.turtle.math.exception.DifferentDimensionsException;
import net.turtle.math.exception.ParsingException;

public class BigMatrixUtil {
	
	private BigMatrixUtil() {
		
	}
	
	public static List< BigRationalVector > parseBigRationalMatrix( String matrix ) {
		final String matrixPatternString = "^\\[((\\[.*\\])+|)\\]$";
		
		final Pattern matrixPattern = Pattern.compile( matrixPatternString );
		final Matcher matrixMatcher = matrixPattern.matcher( matrix );
		final List< BigRationalVector > entries;
		if ( matrixMatcher.find() ) {
			final String matrixContent = matrixMatcher.group( 1 );
			
			final String vectorPatternString = "(\\[[^\\[\\]]*\\])";
			final Pattern vectorPattern = Pattern.compile( vectorPatternString );
			final Matcher vectorMatcher = vectorPattern.matcher( matrixContent );
			
			int dimention = -1;
			final LinkedList< BigRationalVector > vectorsTemp = new LinkedList<>();
			while ( vectorMatcher.find() ) {
				final String vectorString = vectorMatcher.group( 1 );
				final BigRationalVector vector = new BigRationalVector( vectorString );
				if ( dimention >= 0 ) {
					if ( vector.getDimension() != dimention ) {
						throw new DifferentDimensionsException( "Row " + vectorsTemp.size() + " has column count " + vector.getDimension() + " but expected " + dimention );
					}
				} else {
					dimention = vector.getDimension();
				}
				vectorsTemp.add( vector );
			}
			entries = new ArrayList<>( vectorsTemp );
		} else {
			throw new ParsingException();
		}
		return entries;
	}

	public static List< BigComplexVector > parseBigComplexMatrix( String matrix ) {
		final String matrixPatternString = "^\\[((\\[.*\\])+|)\\]$";
		
		final Pattern matrixPattern = Pattern.compile( matrixPatternString );
		final Matcher matrixMatcher = matrixPattern.matcher( matrix );
		final List< BigComplexVector > entries;
		if ( matrixMatcher.find() ) {
			final String matrixContent = matrixMatcher.group( 1 );
			
			final String vectorPatternString = "(\\[[^\\[\\]]*\\])";
			final Pattern vectorPattern = Pattern.compile( vectorPatternString );
			final Matcher vectorMatcher = vectorPattern.matcher( matrixContent );
			
			int dimention = -1;
			final LinkedList< BigComplexVector > vectorsTemp = new LinkedList<>();
			while ( vectorMatcher.find() ) {
				final String vectorString = vectorMatcher.group( 1 );
				final BigComplexVector vector = new BigComplexVector( vectorString );
				if ( dimention >= 0 ) {
					if ( vector.getDimension() != dimention ) {
						throw new DifferentDimensionsException( "Row " + vectorsTemp.size() + " has column count " + vector.getDimension() + " but expected " + dimention );
					}
				} else {
					dimention = vector.getDimension();
				}
				vectorsTemp.add( vector );
			}
			entries = new ArrayList<>( vectorsTemp );
		} else {
			throw new ParsingException();
		}
		return entries;
	}
}
