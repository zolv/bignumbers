package net.turtle.math.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import net.turtle.math.core.BigRational;

public class NotZeroBigRationalValidator implements ConstraintValidator<NotZero, BigRational> {

  @Override
  public void initialize(NotZero constraintAnnotation) {
    ConstraintValidator.super.initialize(constraintAnnotation);
  }

  @Override
  public boolean isValid(BigRational value, ConstraintValidatorContext context) {
    return !BigRational.ZERO.equals(value);
  }
}
