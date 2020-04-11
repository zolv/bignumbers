/*
 * Bean Validation API
 *
 * License: Apache License, Version 2.0
 * See the license.txt file in the root directory or <http://www.apache.org/licenses/LICENSE-2.0>.
 */
package net.turtle.math.validation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import net.turtle.math.validation.NotZero.List;

/** The annotated element must not equal {@code 0}. Accepts any type. */
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Repeatable(List.class)
@Constraint(validatedBy = {NotZeroBigIntegerValidator.class, NotZeroBigRationalValidator.class})
public @interface NotZero {

  String message() default "Cannot equal zero";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

  /**
   * Defines several {@link NotZero} annotations on the same element.
   *
   * @see javax.validation.constraints.NotZero
   */
  @Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
  @Retention(RUNTIME)
  @interface List {

    NotZero[] value();
  }
}
