package net.turtle.math.context;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class BigMathContextSingleThreaded extends BigMathContext {
	
	private final Executor executor;
	
	public BigMathContextSingleThreaded() {
		super();
		this.executor = Executors.newSingleThreadScheduledExecutor();
	}
	
	@Override
	protected Executor getExecutor() {
		return this.executor;
	}
	
}
