package net.turtle.math.context;

import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.FutureTask;

public abstract class BigMathContext {

  private static final ThreadLocal<BigMathContext> contextThreadLocal =
      ThreadLocal.withInitial(() -> new BigMathContextDefault());

  private boolean normalizeResult = false;

  private boolean strictEqualsAndHashContract = false;

  private BigInteger denominatorRoundingValue = BigInteger.valueOf(1_000_000_000_000L);

  private RoundingMode numeratorRoundingMode = RoundingMode.HALF_UP;

  public static void set(BigMathContext context) {
    contextThreadLocal.set(context);
  }

  public static void remove() {
    contextThreadLocal.remove();
  }

  public static BigMathContext get() {
    return contextThreadLocal.get();
  }

  protected abstract Executor getExecutor();

  public <T> FutureTask<T> submit(Callable<T> task) {
    final var futureTask = new FutureTask<>(task);
    this.getExecutor().execute(futureTask);
    return futureTask;
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

  public BigInteger getDenominatorRoundingValue() {
    return this.denominatorRoundingValue;
  }

  public void setDenominatorRoundingValue(BigInteger denominatorRoundingValue) {
    this.denominatorRoundingValue = denominatorRoundingValue;
  }

  public RoundingMode getNumeratorRoundingMode() {
    return this.numeratorRoundingMode;
  }

  public void setNumeratorRoundingMode(RoundingMode numeratorRoundingMode) {
    this.numeratorRoundingMode = numeratorRoundingMode;
  }
}
