package com.spring.apprentice.validation.validation;

import com.spring.apprentice.validation.dto.DuckDTO;
import com.spring.apprentice.validation.validation.DuckConstraint.DuckValidator;
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

  class DuckValidator implements ConstraintValidator<DuckConstraint, DuckDTO> {

    @Override
    public boolean isValid(DuckDTO duckDTO, ConstraintValidatorContext context) {
      List<String> errors = new ArrayList<>();

      if (duckDTO.getBirthday().isBefore(LocalDate.now())) {
        errors.add("Duck is too old");
      }

      if (duckDTO.getBirthday().isAfter(LocalDate.now())) {
        errors.add("Duck is too young");
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
