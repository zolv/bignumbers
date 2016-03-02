package net.turtle.math.context;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

final class DefaultThreadFactory implements ThreadFactory {
	
	
	private static final AtomicInteger poolNumber = new AtomicInteger( 1 );
	
	private final ThreadGroup group;
	
	private final AtomicInteger threadNumber = new AtomicInteger( 1 );
	
	private final String namePrefix;
	
	private final BigMathContext bigMathContext;
	
	public DefaultThreadFactory( BigMathContext bigMathContext ) {
		this.bigMathContext = bigMathContext;
		final SecurityManager s = System.getSecurityManager();
		this.group = ( s != null ) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
		this.namePrefix = "pool-" + poolNumber.getAndIncrement() + "-thread-";
	}
	
	public Thread newThread( Runnable r ) {
		final Thread t = new PooledBigMathContextThread( this.bigMathContext, this.group, r, this.namePrefix + this.threadNumber.getAndIncrement(), 0 );
		if ( t.isDaemon() ) {
			t.setDaemon( false );
		}
		if ( t.getPriority() != Thread.NORM_PRIORITY ) {
			t.setPriority( Thread.NORM_PRIORITY );
		}
		return t;
	}
}
