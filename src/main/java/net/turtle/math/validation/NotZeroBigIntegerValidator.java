package net.turtle.math.validation;

import java.math.BigInteger;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NotZeroBigIntegerValidator implements ConstraintValidator<NotZero, BigInteger> {

  @Override
  public void initialize(NotZero constraintAnnotation) {
    ConstraintValidator.super.initialize(constraintAnnotation);
  }

  @Override
  public boolean isValid(BigInteger value, ConstraintValidatorContext context) {
    return !BigInteger.ZERO.equals(value);
  }
}
