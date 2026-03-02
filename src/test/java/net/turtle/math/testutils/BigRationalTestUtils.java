package net.turtle.math.testutils;

public class BigRationalTestUtils {

  public static String buildJavaInitialization(String numerator, String denominator) {
    var sb = new StringBuilder();
    sb.append("new BigRational(\"")
        .append(numerator)
        .append("\", \"")
        .append(denominator)
        .append("\")");
    return sb.toString();
  }

  private BigRationalTestUtils() {}
}
