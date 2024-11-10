package net.turtle.math.context;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class BigMathContextSingleThreaded extends BigMathContext {

  private final Executor executor;

  public BigMathContextSingleThreaded() {
    this.executor = Executors.newSingleThreadScheduledExecutor();
  }

  @Override
  public Executor getExecutor() {
    return this.executor;
  }
}
