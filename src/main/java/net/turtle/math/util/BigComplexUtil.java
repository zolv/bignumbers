package net.turtle.math.util;

import java.util.regex.Pattern;
import net.turtle.math.core.BigComplex;
import net.turtle.math.core.BigFieldElement;
import net.turtle.math.core.BigRational;

public class BigComplexUtil {

  private BigComplexUtil() {}

  public static final BigRational MINUS_ONE = BigRational.ONE.negate();

  private static final String REAL_PATTERN_STRING =
      "^([-+]?([0-9]+|[0-9]+\\.[0-9]+|[0-9]+\\/[0-9]+))[+-]";
  private static final Pattern REAL_PATTERN = Pattern.compile(REAL_PATTERN_STRING);
  private static final String IMAGINARY_PATTERN_STRING =
      "([-+]?([0-9]+|[0-9]+\\.[0-9]+|[0-9]+\\/[0-9]+|))i";
  private static final Pattern IMAGINARY_PATTERN = Pattern.compile(IMAGINARY_PATTERN_STRING);

  public static String toStringShort(BigComplex complex) {
    final var result = new StringBuilder();
    appendNonZeroA(result, complex.getA());
    appendNonZeroB(result, complex.getB());
    if (result.length() <= 0) {
      result.append("0");
    }
    return result.toString();
  }

  private static void appendNonZeroA(final StringBuilder result, final BigFieldElement<?> a) {
    if (!a.equals(BigRational.ZERO)) {
      result.append(BigRationalUtil.toStringNormalized(a.normalizeSignum()));
    }
  }

  private static void appendNonZeroB(final StringBuilder result, final BigRational b) {
    if (!b.equals(BigRational.ZERO)) {
      if (!b.equals(BigRational.ONE)) {
        if (!b.equals(MINUS_ONE)) {
          if ((result.length() > 0) && (b.signum() > 0)) {
            result.append("+");
          }
          result.append(BigRationalUtil.toStringNormalized(b.normalizeSignum())).append("i");
        } else {
          result.append("-i");
        }
      } else {
        result.append("i");
      }
    }
  }

  public static BigRational getReal(String text2) {
    final var text = text2.trim();
    final String realString;
    if (text.endsWith("i")) {
      final var realMatcher = REAL_PATTERN.matcher(text);
      if (realMatcher.find()) {
        realString = realMatcher.group(1);
      } else {
        realString = "0";
      }
    } else {
      realString = text;
    }
    final var real = new BigRational(realString);
    return real;
  }

  public static BigRational getImaginary(String text2) {
    final var text = text2.trim();
    final String imaginaryString;
    if (text.endsWith("i")) {
      if (!text.equals("i") && !text.endsWith("+i")) {
        if (!text.equals("-i") && !text.endsWith("-i")) {
          final var imaginaryMatcher = IMAGINARY_PATTERN.matcher(text);
          if (imaginaryMatcher.find()) {
            imaginaryString = imaginaryMatcher.group(1);
          } else {
            imaginaryString = "0";
          }
        } else {
          imaginaryString = "-1";
        }
      } else {
        imaginaryString = "1";
      }
    } else {
      imaginaryString = "0";
    }
    final var real = new BigRational(imaginaryString);
    return real;
  }
}
