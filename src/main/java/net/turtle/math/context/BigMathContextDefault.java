package net.turtle.math.context;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class BigMathContextDefault extends BigMathContext {
	
	private final Executor executor;
	
	public BigMathContextDefault() {
		super();
		this.executor = Executors.newCachedThreadPool( new DefaultThreadFactory( this ) );
	}
	
	@Override
	protected Executor getExecutor() {
		return this.executor;
	}
	
}
