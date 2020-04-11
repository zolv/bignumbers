package net.turtle.math.context;

public class PooledBigMathContextThread extends Thread {

  private final BigMathContext bigMathContext;

  public PooledBigMathContextThread(
      BigMathContext bigMathContext,
      ThreadGroup group,
      Runnable target,
      String name,
      long stackSize) {
    super(group, target, name, stackSize);
    this.bigMathContext = bigMathContext;
  }

  @Override
  public synchronized void run() {
    BigMathContext.getContextThreadLocal().set(this.bigMathContext);
    super.run();
    BigMathContext.getContextThreadLocal().remove();
  }

  @Override
  public void interrupt() {
    super.interrupt();
    BigMathContext.getContextThreadLocal().remove();
  }
}
