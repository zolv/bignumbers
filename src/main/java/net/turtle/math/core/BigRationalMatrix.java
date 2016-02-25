package net.turtle.math.core;

import java.util.List;

public class BigRationalMatrix extends BigMatrix<BigRational> {

	public BigRationalMatrix() {
		super();
	}

	public BigRationalMatrix(String matrix) {
		super(matrix);
	}

	@Override
	protected BigVector<BigRational> createRowElement(String vectorString) {
		return new BigRationalVector(vectorString);
	}

	@Override
	protected BigVector<BigRational> createRowElement(List<BigRational> input) {
		return new BigRationalVector(input, true);
	}

}
