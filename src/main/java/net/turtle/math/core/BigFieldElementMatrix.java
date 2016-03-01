package net.turtle.math.core;

import java.util.ArrayList;
import java.util.List;

import net.turtle.math.exception.NotImplementedException;

public abstract class BigFieldElementMatrix< F extends FieldElement< F > , V extends BigFieldElementVector< F > > implements BigMatrix< F , V > {
	
	protected final List< V > entries;
	
	protected BigFieldElementMatrix( List< V > entries , boolean trusted ) {
		if ( trusted ) {
			this.entries = entries;
		} else {
			this.entries = new ArrayList<>( entries );
		}
	}
	
	protected abstract V createRow( List< F > input );
	
	protected abstract BigFieldElementMatrix< F , V > createInstance( List< V > vectors );
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see net.turtle.math.core.BigMatrix#transpose()
	 */
	public BigMatrix< F , V > transpose() {
		final int columnsCount = this.getColumnsCount();
		final List< V > transposedEntries = new ArrayList<>( columnsCount );
		for ( int i = 0 ; i < columnsCount ; i++ ) {
			final V columnVector = this.getColumnVector( i );
			transposedEntries.add( columnVector );
		}
		final BigMatrix< F , V > result = this.createInstance( transposedEntries );
		return result;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see net.turtle.math.core.BigMatrix#add(net.turtle.math.core.BigMatrix)
	 */
	@Override
	public BigFieldElementMatrix< F , V > add( BigMatrix< F , V > augend ) {
		final int rowCount = this.getRowsCount();
		final List< V > transposedEntries = new ArrayList<>( rowCount );
		for ( int i = 0 ; i < rowCount ; i++ ) {
			final V rowVector = this.getRowVector( i );
			final V rowVectorAugend = augend.getRowVector( i );
			transposedEntries.add( (V)rowVector.add( rowVectorAugend ) );
		}
		final BigFieldElementMatrix< F , V > result = this.createInstance( transposedEntries );
		return result;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.turtle.math.core.BigMatrix#substract(net.turtle.math.core.BigMatrix)
	 */
	public BigMatrix< F , V > substract( BigMatrix< F , V > subtrahend ) {
		final int rowCount = this.getRowsCount();
		final List< V > transposedEntries = new ArrayList<>( rowCount );
		for ( int i = 0 ; i < rowCount ; i++ ) {
			final V rowVector = this.getRowVector( i );
			final V rowVectorAugend = subtrahend.getRowVector( i );
			transposedEntries.add( (V)rowVector.subtract( rowVectorAugend ) );
		}
		final BigMatrix< F , V > result = this.createInstance( transposedEntries );
		return result;
	}
	
	@Override
	public BigMatrix< F , V > multiply( BigMatrix< F , V > multiplicand ) {
		throw new NotImplementedException();
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see net.turtle.math.core.BigMatrix#getRowsCount()
	 */
	public int getRowsCount() {
		return this.entries.size();
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see net.turtle.math.core.BigMatrix#getColumnsCount()
	 */
	public int getColumnsCount() {
		final int result;
		if ( !this.entries.isEmpty() ) {
			final V firstRow = this.entries.iterator().next();
			result = firstRow.getDimension();
		} else {
			result = 0;
		}
		return result;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see net.turtle.math.core.BigMatrix#getRowVector(int)
	 */
	public V getRowVector( int rowIndex ) {
		return this.entries.get( rowIndex );
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see net.turtle.math.core.BigMatrix#getColumnVector(int)
	 */
	public V getColumnVector( int columnIndex ) {
		final List< F > columnEntries = new ArrayList<>( this.getColumnsCount() );
		for ( final V element : this.entries ) {
			columnEntries.add( element.getCoordinates().get( columnIndex ) );
		}
		return this.createRow( columnEntries );
	}

}
