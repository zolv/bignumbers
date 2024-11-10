package net.turtle.math.context;

public class PooledBigMathContextThread extends Thread {

  private final BigMathContext bigMathContext;

  public PooledBigMathContextThread(BigMathContext bigMathContext) {
    this.bigMathContext = bigMathContext;
  }

  @Override
  public synchronized void run() {
    BigMathContext.set(this.bigMathContext);
    super.run();
    BigMathContext.remove();
  }

  @Override
  public void interrupt() {
    BigMathContext.remove();
    super.interrupt();
  }
}
