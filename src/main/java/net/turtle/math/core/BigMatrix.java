package net.turtle.math.core;

import java.util.ArrayList;
import java.util.List;

public abstract class BigMatrix< F extends FieldElement< F > , V extends BigVector< F > > {

	protected final List< V > entries;

	protected BigMatrix( List< V > entries , boolean trusted ) {
		if ( trusted ) {
			this.entries = entries;
		} else {
			this.entries = new ArrayList< >( entries );
		}
	}

	protected abstract V createRow( List< F > input );

	protected abstract BigMatrix< F , V > createInstance( List< V > vectors );

	public BigMatrix< F , V > transpose() {
		final int columnsCount = this.getColumnsCount();
		final List< V > transposedEntries = new ArrayList< >( columnsCount );
		for ( int i = 0 ; i < columnsCount ; i++ ) {
			final V columnVector = this.getColumnVector( i );
			transposedEntries.add( columnVector );
		}
		final BigMatrix< F , V > result = this.createInstance( transposedEntries );
		return result;
	}

	public BigMatrix< F , V > add( BigMatrix< F , V > augend ) {
		final int rowCount = this.getRowsCount();
		final List< V > transposedEntries = new ArrayList< >( rowCount );
		for ( int i = 0 ; i < rowCount ; i++ ) {
			final V rowVector = this.getRowVector( i );
			final V rowVectorAugend = augend.getRowVector( i );
			transposedEntries.add( (V)rowVector.add( rowVectorAugend ) );
		}
		final BigMatrix< F , V > result = this.createInstance( transposedEntries );
		return result;
	}

	public BigMatrix< F , V > substract( BigMatrix< F , V > subtrahend ) {
		final int rowCount = this.getRowsCount();
		final List< V > transposedEntries = new ArrayList< >( rowCount );
		for ( int i = 0 ; i < rowCount ; i++ ) {
			final V rowVector = this.getRowVector( i );
			final V rowVectorAugend = subtrahend.getRowVector( i );
			transposedEntries.add( (V)rowVector.subtract( rowVectorAugend ) );
		}
		final BigMatrix< F , V > result = this.createInstance( transposedEntries );
		return result;
	}

	public int getRowsCount() {
		return this.entries.size();
	}

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

	public V getRowVector( int rowIndex ) {
		return this.entries.get( rowIndex );
	}

	public V getColumnVector( int columnIndex ) {
		final List< F > columnEntries = new ArrayList< >( this.getColumnsCount() );
		for ( final V element : this.entries ) {
			columnEntries.add( element.getCoordinates().get( columnIndex ) );
		}
		return this.createRow( columnEntries );
	}

}
