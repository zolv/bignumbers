package net.turtle.math.core;

import java.lang.reflect.Constructor;
import java.math.BigInteger;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class BigRationalValidationTest {

  private static Validator validator;

  @BeforeAll
  public static void beforeClass() {
    validator = Validation.buildDefaultValidatorFactory().getValidator();
  }

  @Test
  public void validateNumeratorDenominatorConstructorParameters()
      throws NoSuchMethodException, SecurityException {
    final Constructor<BigRational> constructor =
        BigRational.class.getConstructor(
            new Class[] {BigInteger.class, BigInteger.class, boolean.class});

    final Set<ConstraintViolation<BigRational>> validations =
        validator
            .forExecutables()
            .validateConstructorParameters(
                constructor,
                new Object[] {BigInteger.valueOf(1L), BigInteger.valueOf(0), Boolean.TRUE});
    Assertions.assertEquals(1, validations.size());
  }
}
