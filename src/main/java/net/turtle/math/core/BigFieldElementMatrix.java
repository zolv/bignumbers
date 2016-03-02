package net.turtle.math.core;

import java.util.ArrayList;
import java.util.List;

import net.turtle.math.exception.NotImplementedException;

public abstract class BigFieldElementMatrix< F extends FieldElement< F >, V extends BigFieldElementVector< F, V >, M extends BigFieldElementMatrix< F, V, M > > implements BigMatrix< F, V, M > {
	
	
	protected final List< V > entries;
	
	protected BigFieldElementMatrix( List< V > entries, boolean trusted ) {
		if ( trusted ) {
			this.entries = entries;
		} else {
			this.entries = new ArrayList<>( entries );
		}
	}
	
	protected abstract V createRow( List< F > input );
	
	protected abstract M createInstance( List< V > vectors );
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see net.turtle.math.core.BigMatrix#transpose()
	 */
	public M transpose() {
		final int columnsCount = this.getColumnsCount();
		final List< V > transposedEntries = new ArrayList<>( columnsCount );
		for ( int i = 0 ; i < columnsCount ; i++ ) {
			final V columnVector = this.getColumnVector( i );
			transposedEntries.add( columnVector );
		}
		final M result = this.createInstance( transposedEntries );
		return result;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see net.turtle.math.core.BigMatrix#add(net.turtle.math.core.BigMatrix)
	 */
	@Override
	public M add( M augend ) {
		final int rowCount = this.getRowsCount();
		final List< V > transposedEntries = new ArrayList<>( rowCount );
		for ( int i = 0 ; i < rowCount ; i++ ) {
			final V rowVector = this.getRowVector( i );
			final V rowVectorAugend = augend.getRowVector( i );
			transposedEntries.add( rowVector.add( rowVectorAugend ) );
		}
		final M result = this.createInstance( transposedEntries );
		return result;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.turtle.math.core.BigMatrix#substract(net.turtle.math.core.BigMatrix)
	 */
	public M substract( M subtrahend ) {
		final int rowCount = this.getRowsCount();
		final List< V > transposedEntries = new ArrayList<>( rowCount );
		for ( int i = 0 ; i < rowCount ; i++ ) {
			final V rowVector = this.getRowVector( i );
			final V rowVectorAugend = subtrahend.getRowVector( i );
			transposedEntries.add( rowVector.subtract( rowVectorAugend ) );
		}
		final M result = this.createInstance( transposedEntries );
		return result;
	}
	
	@Override
	public M multiply( M multiplicand ) {
		// int n = this.getRowsCount();
		// int m = this.getColumnsCount();
		// int p = multiplicand.getColumnsCount();
		//
		// final List< V > resultEntries = new ArrayList< >( n );
		//
		// for(int i = 0 ; i < n ; i++) {
		// final List<F> resultCoordinates = new ArrayList<>(p);
		//
		// final V aRowVector = this.getRowVector( i );
		//
		// for(int k = 0 ; k < m ; k++) {
		//
		// final V bColumnVector = multiplicand.getColumnVector( k );
		//
		//
		//
		// }
		//
		// }
		//
		//
		//
		// final M result = this.createInstance( resultEntries );
		// return result;
		throw new NotImplementedException();
	}
	
	@Override
	public M multiply( F multiplicand ) {
		final int rowCount = this.getRowsCount();
		final List< V > resultEntries = new ArrayList<>( rowCount );
		for ( final V vector : this.entries ) {
			final V resultVector = vector.multiply( multiplicand );
			resultEntries.add( resultVector );
		}
		final M result = this.createInstance( resultEntries );
		return result;
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
