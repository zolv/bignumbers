package net.turtle.math.context;

import java.util.concurrent.Executor;
import java.util.concurrent.FutureTask;

public abstract class BigMathContext {

  private static final ThreadLocal<BigMathContext> contextThreadLocal =
      new ThreadLocal<BigMathContext>() {

        @Override
        protected BigMathContext initialValue() {
          return new BigMathContextDefault();
        }
      };

  private boolean normalizeResult = false;

  private boolean strictEqualsAndHashContract = false;

  protected static ThreadLocal<BigMathContext> getContextThreadLocal() {
    return contextThreadLocal;
  }

  public static void set(BigMathContext context) {
    contextThreadLocal.set(context);
  }

  public static BigMathContext get() {
    return contextThreadLocal.get();
  }

  public BigMathContext() {
    super();
  }

  protected abstract Executor getExecutor();

  public void submit(FutureTask<?> task) {
    this.getExecutor().execute(task);
  }

  public boolean getNormalizeResult() {
    return this.normalizeResult;
  }

  public void setNormalizeResult(boolean normalizeResult) {
    this.normalizeResult = normalizeResult;
  }

  public boolean getStrictEqualsAndHashContract() {
    return this.strictEqualsAndHashContract;
  }

  public void setStrictEqualsAndHashContract(boolean strictEqualsAndHashContract) {
    this.strictEqualsAndHashContract = strictEqualsAndHashContract;
  }
}
