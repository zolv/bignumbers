package net.turtle.math.core;

public abstract class BigNumber implements BigFieldElement<BigNumber>, Comparable<BigNumber> {

  abstract BigRational toRational();

  abstract BigComplex toReal();

  abstract BigComplex toComplex();
}
