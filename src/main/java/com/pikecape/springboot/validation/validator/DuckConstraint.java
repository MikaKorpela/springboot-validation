package com.pikecape.springboot.validation.validator;

import com.pikecape.springboot.validation.model.Duck;
import com.pikecape.springboot.validation.validator.DuckConstraint.DuckValidator;
import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Documented
@Constraint(validatedBy = DuckValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface DuckConstraint {

  String message() default "Validation for duck failed";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

  class DuckValidator implements ConstraintValidator<DuckConstraint, Duck> {

    @Override
    public boolean isValid(Duck duck, ConstraintValidatorContext context) {
      List<String> errors = new ArrayList<>();

      if (duck.getBirthday().isAfter(LocalDate.now())) {
        errors.add("Duck cannot exists; birthday is in the future");
      }

      if (!errors.isEmpty()) {
        context.buildConstraintViolationWithTemplate(String.join(", ", errors))
          .addConstraintViolation();

        return false;
      }

      return true;
    }
  }
}
