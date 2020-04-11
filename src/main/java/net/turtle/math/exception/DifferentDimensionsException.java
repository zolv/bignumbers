package net.turtle.math.exception;

public class DifferentDimensionsException extends CalculationException {

  private static final long serialVersionUID = -6369789850367584488L;

  public DifferentDimensionsException() {}

  public DifferentDimensionsException(String message) {
    super(message);
  }

  public DifferentDimensionsException(Throwable cause) {
    super(cause);
  }

  public DifferentDimensionsException(String message, Throwable cause) {
    super(message, cause);
  }

  public DifferentDimensionsException(
      String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
