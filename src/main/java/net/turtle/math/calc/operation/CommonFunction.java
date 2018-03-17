package net.turtle.math.calc.operation;

public abstract class CommonFunction< OUT, IN > implements BigMathSingleValueFunction< OUT, IN >{
	
	public CommonFunction() {
		super();
	}

	protected OUT getDefaultValue() {
		return null;
	}
	
}
