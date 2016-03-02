package net.turtle.math.core;

import java.util.ArrayList;
import java.util.List;

import net.turtle.math.util.BigMatrixUtil;

public class BigComplexMatrix extends BigFieldElementMatrix< BigComplex, BigComplexVector, BigComplexMatrix > {
	
	
	public BigComplexMatrix() {
		this( new ArrayList< BigComplexVector >( 0 ), false );
	}
	
	public BigComplexMatrix( String matrix ) {
		this( BigMatrixUtil.parseBigComplexMatrix( matrix ), false );
	}
	
	protected BigComplexMatrix( List< BigComplexVector > input ) {
		super( input, false );
	}
	
	protected BigComplexMatrix( List< BigComplexVector > input, boolean trusted ) {
		super( input, trusted );
	}
	
	public BigComplexMatrix conjugate() {
		final int rowCount = this.getRowsCount();
		final List< BigComplexVector > resultEntries = new ArrayList<>( rowCount );
		for ( final BigComplexVector vector : this.entries ) {
			final BigComplexVector resultVector = vector.conjugate();
			resultEntries.add( resultVector );
		}
		final BigComplexMatrix result = this.createInstance( resultEntries );
		return result;
	}
	
	@Override
	protected BigComplexVector createRow( List< BigComplex > input ) {
		return new BigComplexVector( input, true );
	}
	
	@Override
	protected BigComplexMatrix createInstance( List< BigComplexVector > input ) {
		return new BigComplexMatrix( input, true );
	}
	
}
