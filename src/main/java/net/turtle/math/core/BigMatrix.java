package net.turtle.math.core;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.turtle.math.exception.DifferentDimensionsException;
import net.turtle.math.exception.ParsingException;

public abstract class BigMatrix<T extends FieldElement<T>> {

	protected final List<BigVector<T>> entries;

	public BigMatrix() {
		this.entries = new ArrayList<>(0);
	}

	public BigMatrix(String matrix) {

		final String matrixPatternString = "^\\[((\\[.*\\])+|)\\]$";

		final Pattern matrixPattern = Pattern.compile(matrixPatternString);
		final Matcher matrixMatcher = matrixPattern.matcher(matrix);
		if(matrixMatcher.find()) {
			final String matrixContent = matrixMatcher.group(1);

			final String vectorPatternString = "(\\[[^\\[\\]]*\\])";
			final Pattern vectorPattern = Pattern.compile(vectorPatternString);
			final Matcher vectorMatcher = vectorPattern.matcher(matrixContent);

			int dimention = -1;
			final LinkedList<BigVector<T>> vectorsTemp = new LinkedList<>();
			while(vectorMatcher.find()) {
				final String vectorString = vectorMatcher.group(1);
				System.out.println("found: " + vectorString);
				final BigVector<T> vector = this.createRowElement(vectorString);
				if(dimention >= 0) {
					if(vector.getDimension() != dimention) {
						throw new DifferentDimensionsException(
								"Row " + vectorsTemp.size() + " has column count " + vector.getDimension() + " but expected " + dimention);
					}
				} else {
					dimention = vector.getDimension();
				}
				vectorsTemp.add(vector);
			}
			this.entries = new ArrayList<>(vectorsTemp);

		} else {
			throw new ParsingException();
		}

	}

	protected abstract BigVector<T> createRowElement(final String vectorString);

	protected abstract BigVector<T> createRowElement(List<T> input);

	public int getRowsCount() {
		return this.entries.size();
	}

	public int getColumnsCount() {
		final int result;
		if(!this.entries.isEmpty()) {
			final BigVector<T> firstRow = this.entries.iterator().next();
			result = firstRow.getDimension();
		} else {
			result = 0;
		}
		return result;
	}

	public BigVector<T> getRowVector(int rowIndex) {
		return this.entries.get(rowIndex);
	}

	public BigVector<T> getColumnVector(int columnIndex) {
		final List<T> columnEntries = new ArrayList<>(this.getColumnsCount());
		for (final BigVector<T> element : this.entries) {
			columnEntries.add(element.getCoordinates().get(columnIndex));
		}
		return createRowElement(columnEntries);
	}

}
