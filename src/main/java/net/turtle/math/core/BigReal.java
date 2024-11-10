package net.turtle.math.core;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.concurrent.ExecutionException;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.NotImplementedException;

import net.turtle.math.context.BigMathContext;
import net.turtle.math.exception.CalculationException;
import net.turtle.math.numbers.BigRationalOne;
import net.turtle.math.numbers.BigRationalZero;
import net.turtle.math.util.BigRationalUtil;
import net.turtle.math.validation.NotZero;

public class BigReal implements BigFieldElement<BigReal>, Comparable<BigReal>, BigValue {

  public static final BigReal ZERO = new BigRationalZero();

  public static final BigReal ONE = new BigRationalOne();

  /** Creates new BigDecimal with value ZERO. */
  public BigReal() {}

  public BigReal(@NotNull BigDecimal bigDecimalValue) {
    this(
        bigDecimalValue.scale() >= 0
            ? bigDecimalValue.unscaledValue()
            : bigDecimalValue
                .unscaledValue()
                .multiply(BigRationalUtil.bigTenToThe(-bigDecimalValue.scale())),
        bigDecimalValue.scale() < 0
            ? BigInteger.ONE
            : BigRationalUtil.bigTenToThe(bigDecimalValue.scale()));
  }

  public BigReal(@NotNull BigInteger bigIntegerValue)
      throws ArithmeticException, NullPointerException {
    this(bigIntegerValue, BigInteger.ONE);
  }

  public BigReal(@NotNull String value) throws ArithmeticException, NullPointerException {
    this(BigRationalUtil.getNumerator(value), BigRationalUtil.getDenominator(value));
  }

  public BigReal(@NotNull String numerator, @NotNull @NotZero String denominator)
      throws ArithmeticException, NullPointerException {
    this(new BigInteger(numerator), new BigInteger(denominator));
  }

  public BigReal(@NotNull BigInteger numerator, @NotNull @NotZero BigInteger denominator)
      throws ArithmeticException, NullPointerException {
    this(numerator, denominator, BigMathContext.get().getNormalizeResult());
  }

  public BigReal(
      @NotNull BigInteger numerator, @NotNull @NotZero BigInteger denominator, boolean normalize)
      throws ArithmeticException, NullPointerException {
    if (numerator != null) {
      if (denominator != null) {
        if (!denominator.equals(BigInteger.ZERO)) {
          if (normalize) {
            /*
             * If numerator and denominator are already normalized, the only loss of memory
             * is just the BigRational instance (2 references to numerator and denominator)
             * because after normalization same instances of numerator and denominator are
             * used.
             */
            final var normalized = new BigReal(numerator, denominator, false).normalize();
            this.numerator = normalized.getNumerator();
            this.denominator = normalized.getDenominator();
          } else {
            this.numerator = numerator;
            this.denominator = denominator;
          }
        } else {
          throw new ArithmeticException("Division by zero");
        }
      } else {
        throw new NullPointerException("Denominator cannot be null.");
      }
    } else {
      throw new NullPointerException("Numerator cannot be null.");
    }
  }

  public BigInteger getNumerator() {
    return this.numerator;
  }

  public BigInteger getDividend() {
    return this.numerator;
  }

  public BigInteger getDenominator() {
    return this.denominator;
  }

  public BigInteger getDivisor() {
    return this.denominator;
  }

  /**
   * Normalizes fraction by normalizing signum leaving sign only in numerator (denominator is
   * positive) e.g.: 2/-3 becomes -2/3 -2/-3 becomes 2/3 Then fraction is cancelled e.g.: -4/6
   * becomes -2/3 11/11 becomes 1/1 0/123 (any representation of 0) becomes 0/1
   *
   * @return Normalized fraction e.g. 4/-6 becomes -2/3
   */
  public BigReal normalize() {
    return this.cancel().normalizeSignum();
  }

  public BigReal normalizeSignum() {
    return this.denominator.signum() > 0
        ? this
        : new BigReal(this.numerator.negate(), this.denominator.negate());
  }

  public BigReal cancel() {
    final BigReal result;
    if (!this.denominator.equals(BigInteger.ONE)) {
      if (!this.numerator.equals(BigInteger.ZERO)) {
        final var gcd = this.numerator.gcd(this.denominator);
        if (!gcd.equals(BigInteger.ONE)) {
          result = new BigReal(this.numerator.divide(gcd), this.denominator.divide(gcd));
        } else {
          result = this;
        }
      } else {
        result = BigReal.ZERO;
      }
    } else {
      result = this;
    }
    return result;
  }

  @Override
  public BigReal add(BigReal augend) {
    final BigReal result;
    if (!this.denominator.equals(augend.denominator)) {
      result =
          new BigReal(
              this.numerator
                  .multiply(augend.denominator)
                  .add(augend.numerator.multiply(this.denominator)),
              this.denominator.multiply(augend.denominator));
    } else {
      result = new BigReal(this.numerator.add(augend.numerator), this.denominator);
    }
    return result;
  }

  @Override
  public BigReal subtract(BigReal subtrahend) throws NullPointerException {
    final BigReal result;
    if (!this.denominator.equals(subtrahend.denominator)) {
      result =
          new BigReal(
              this.numerator
                  .multiply(subtrahend.denominator)
                  .subtract(subtrahend.numerator.multiply(this.denominator)),
              this.denominator.multiply(subtrahend.denominator));
    } else {
      result = new BigReal(this.numerator.subtract(subtrahend.numerator), this.denominator);
    }
    return result;
  }

  @Override
  public BigReal multiply(final BigReal multiplicand) {
    final BigReal result;
    if (!multiplicand.equals(BigReal.ONE)) {
      if (!this.equals(BigReal.ONE)) {
        if (!multiplicand.equals(BigReal.ZERO) && !this.equals(BigReal.ZERO)) {
          result = this.doMultiply(multiplicand);
        } else {
          result = BigReal.ZERO;
        }
      } else {
        result = multiplicand;
      }
    } else {
      result = this;
    }
    return result;
  }

  private BigReal doMultiply(final BigReal multiplicand) {
    final var numeratorComputation =
        BigMathContext.get()
            .submit(() -> BigReal.this.multiply(BigReal.this.numerator, multiplicand.numerator));

    /*
     * Reuse current thread for calculation.
     */
    final var denominatorComputed = this.multiply(this.denominator, multiplicand.denominator);

    try {
      return new BigReal(numeratorComputation.get(), denominatorComputed);
    } catch (ArithmeticException
        | NullPointerException
        | InterruptedException
        | ExecutionException e) {
      throw new RuntimeException(e);
    }
  }

  private BigInteger multiply(@NotNull BigInteger multiplicandA, BigInteger multiplicandB) {
    final BigInteger result;
    if (multiplicandB.equals(BigInteger.ONE)) {
      result = multiplicandA;
    } else {
      if (multiplicandA.equals(BigInteger.ONE)) {
        result = multiplicandB;
      } else {
        result = multiplicandA.multiply(multiplicandB);
      }
    }
    return result;
  }

  @Override
  public BigReal divide(@NotNull @NotZero final BigReal divisor) throws CalculationException {
    final var numeratorComputation =
        BigMathContext.get().submit(() -> BigReal.this.numerator.multiply(divisor.denominator));
    final var denominatorComputation = this.denominator.multiply(divisor.numerator);
    try {
      return new BigReal(numeratorComputation.get(), denominatorComputation);
    } catch (InterruptedException | ExecutionException e) {
      throw new CalculationException(e);
    }
  }

  public BigReal pow(@NotNull BigReal power)
      throws NullPointerException, ArithmeticException, CalculationException {
    final BigReal result;
    if (power.equals(BigReal.ONE)) {
      result = this;
    } else {
      final var normalizedPower = power.normalize();
      if (normalizedPower.denominator.equals(BigInteger.ONE)) {
        result = this.pow(normalizedPower.numerator);
      } else {
        throw new NotImplementedException("Power operation is available only for integers");
      }
    }
    return result;
  }

  public BigReal pow(BigInteger power) throws ArithmeticException, CalculationException {
    final BigReal result;
    if (!power.equals(BigInteger.ZERO)) {
      final var powerAbs = power.abs();
      if (!powerAbs.equals(BigInteger.ONE)) {
        final BigInteger newNumerator;
        final BigInteger newDenominator;
        if (power.signum() >= 0) {
          newNumerator = this.numerator;
          newDenominator = this.denominator;
        } else {
          newNumerator = this.denominator;
          newDenominator = this.numerator;
        }
        final var numeratorComputation =
            BigMathContext.get().submit(() -> BigReal.this.pow(newNumerator, powerAbs));
        final var denominatorComputation = this.pow(newDenominator, powerAbs);

        try {
          result = new BigReal(numeratorComputation.get(), denominatorComputation);
        } catch (InterruptedException | ExecutionException e) {
          throw new CalculationException(e);
        }
      } else {
        if (power.equals(BigInteger.ONE)) {
          result = this;
        } else {
          /*
           * power == -1
           */
          result = this.inverse();
        }
      }
    } else {
      result = BigReal.ONE;
    }
    return result;
  }

  protected BigInteger pow(BigInteger value, BigInteger power) {
    var partialResult = BigInteger.ONE;
    final var two = BigInteger.valueOf(2L);
    var squaredValue = value;
    var powerIteration = power;
    while (powerIteration.compareTo(BigInteger.ONE) >= 0) {
      if (powerIteration.testBit(0)) {
        partialResult = partialResult.multiply(squaredValue);
      }
      powerIteration = powerIteration.divide(two);
      if (powerIteration.compareTo(BigInteger.ONE) >= 0) {
        squaredValue = squaredValue.multiply(squaredValue);
      }
    }

    return partialResult;
  }

  public BigReal abs() {
    return this.signum() >= 0 ? this : this.negate();
  }

  /**
   * Negates value.
   *
   * @return
   */
  @Override
  public BigReal negate() {
    return new BigReal(this.numerator.negate(), this.denominator);
  }

  /**
   * Returns result of 1 / this = this.denominator / this.numerator. Always return new BigRational
   * instance. Checking for this.numerator.equals( this.denominator ) is not worth from performance
   * point of view.
   *
   * @return Inversed
   */
  @Override
  public BigReal inverse() {
    return new BigReal(this.denominator, this.numerator);
  }

  /**
   * @return 1 = positive, -1 = negative, 0 = zero
   * @throws ArithmeticException
   */
  public int signum() throws ArithmeticException {
    final int result;
    if (this.numerator.signum() > 0) {
      if (this.denominator.signum() > 0) {
        result = 1;
      } else {
        if (this.denominator.signum() < 0) {
          result = -1;
        } else {
          /*
           * Impossible
           */
          throw new ArithmeticException("Division by zero");
        }
      }
    } else {
      if (this.numerator.signum() < 0) {
        if (this.denominator.signum() < 0) {
          result = 1;
        } else {
          if (this.denominator.signum() > 0) {
            result = -1;
          } else {
            /*
             * Impossible
             */
            throw new ArithmeticException("Division by zero");
          }
        }
      } else {
        if (this.denominator.signum() != 0) {
          result = 0;
        } else {
          /*
           * Impossible
           */
          throw new ArithmeticException("Division by zero");
        }
      }
    }
    return result;
  }

  public BigReal square() {
    return this.multiply(this);
  }

  public BigReal cube() {
    return this.multiply(this).multiply(this);
  }

  @Override
  public int compareTo(BigReal that) {
    final int result;
    final var thisSignum = this.signum();
    final var thatSignum = that.signum();
    if (thisSignum == thatSignum) {
      if (thisSignum != 0) {
        final var thisNormalized = this.normalize();
        final var thatNormalized = that.normalize();
        final var denominatorComparison =
            thisNormalized.denominator.compareTo(thatNormalized.denominator);
        if (denominatorComparison == 0) {
          result = thisNormalized.numerator.compareTo(thatNormalized.numerator);
        } else {
          final var numeratorComparison =
              thisNormalized.numerator.compareTo(thatNormalized.numerator);
          if ((denominatorComparison > 0) && (numeratorComparison <= 0)) {
            /*
             * E.g. 2/5 vs 3/4 => -1 2/5 vs 2/3 => -1
             */
            result = -1;
          } else {
            if ((denominatorComparison < 0) && (numeratorComparison >= 0)) {
              result = 1;
            } else {
              /*
               * abs() of multiplication results are not needed due to normalization.
               */
              final var absCompare =
                  thisNormalized
                      .numerator
                      .multiply(thatNormalized.denominator)
                      .compareTo(thatNormalized.numerator.multiply(thisNormalized.denominator));
              if (thisSignum > 0) {
                result = absCompare;
              } else {
                result = -absCompare;
              }
            }
          }
        }
      } else {
        result = 0;
      }
    } else {
      if (thisSignum < thatSignum) {
        result = -1;
      } else {
        result = 1;
      }
    }
    return result;
  }

  public BigReal min(BigReal val) {
    final BigReal result;
    final var compareValue = this.compareTo(val);
    if (compareValue < 0) {
      result = this;
    } else {
      if (compareValue > 0) {
        result = val;
      } else {
        /*
         * If equal, pick the one with lower denominator
         */
        if (this.denominator.compareTo(val.denominator) <= 0) {
          result = this;
        } else {
          result = val;
        }
      }
    }
    return result;
  }

  public BigReal max(BigReal val) {
    final BigReal result;
    final var compareValue = this.compareTo(val);
    if (compareValue > 0) {
      result = this;
    } else {
      if (compareValue < 0) {
        result = val;
      } else {
        /*
         * If equal, pick the one with lower denominator
         */
        if (this.denominator.compareTo(val.denominator) <= 0) {
          result = this;
        } else {
          result = val;
        }
      }
    }
    return result;
  }

  @Override
  public BigReal toBigRational(BigInteger denominator, RoundingMode numeratorRoundingMode) {
    return null;
  }

  @Override
  public BigComplex toBigComplex(
      BigInteger realDenominatorRoundingValue,
      RoundingMode realNumeratorRoundingMode,
      BigInteger imaginaryDenominatorRoundingValue,
      RoundingMode imaginaryNumeratorRoundingMode) {
    return new BigComplex(
        this.toBigRational(imaginaryDenominatorRoundingValue, imaginaryNumeratorRoundingMode));
  }

  @Override
  public boolean equals(Object obj) {
    final boolean result;
    if (this == obj) {
      result = true;
    } else {
      if (obj instanceof final BigReal bigRational) {
        if (BigMathContext.get().getStrictEqualsAndHashContract()) {
          result = this.equalsStrict(bigRational);
        } else {
          result = this.equalsValue(bigRational);
        }
      } else {
        result = false;
      }
    }
    return result;
  }

  public boolean equalsStrict(BigReal obj) {
    return this.numerator.equals(obj.numerator) && this.denominator.equals(obj.denominator);
  }

  public boolean equalsValue(BigReal obj) {
    final boolean result;
    final var numeratorsEqual = this.numerator.equals(obj.numerator);
    if (numeratorsEqual) {
      result = BigInteger.ZERO.equals(this.numerator) || this.denominator.equals(obj.denominator);
    } else {
      if (this.denominator.equals(obj.denominator)) {
        result = false;
      } else {
        result =
            this.numerator
                .multiply(obj.denominator)
                .equals(obj.numerator.multiply(this.denominator));
      }
    }
    return result;
  }

  @Override
  public int hashCode() {
    final int result;
    if (BigMathContext.get().getStrictEqualsAndHashContract()) {
      result = this.hashCodeStrict();
    } else {
      result = this.hashCodeNormalized();
    }
    return result;
  }

  public int hashCodeStrict() {
    final var prime = 31;
    final var result = ((prime + this.denominator.intValue()) * prime) + this.numerator.intValue();
    return result;
  }

  public int hashCodeNormalized() {
    return this.normalize().hashCodeStrict();
  }

  @Override
  public String toString() {
    return new StringBuilder()
        .append(this.numerator.toString())
        .append("/")
        .append(this.denominator.toString())
        .toString();
  }

  public String toStringPretty() {
    final String result;
    final var normalized = this.normalize();
    if (normalized.denominator.equals(BigInteger.ONE)) {
      result = normalized.numerator.toString();
    } else {
      result =
          new StringBuilder()
              .append(normalized.numerator.toString())
              .append("/")
              .append(normalized.denominator.toString())
              .toString();
    }
    return result;
  }

  @Override
  public BigRealable toBigReal() {
    return this;
  }
}
