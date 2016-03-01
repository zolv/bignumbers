package net.turtle.math.core;

public interface BigMatrix< F extends FieldElement< F > , V extends BigVector< F > > {
	
	BigMatrix< F , V > transpose();
	
	BigMatrix< F , V > add( BigMatrix< F , V > augend );
	
	BigMatrix< F , V > substract( BigMatrix< F , V > subtrahend );

	BigMatrix< F , V > multiply( BigMatrix< F , V > multiplicand );
	
	int getRowsCount();
	
	int getColumnsCount();
	
	V getRowVector( int rowIndex );
	
	V getColumnVector( int columnIndex );
	
}
