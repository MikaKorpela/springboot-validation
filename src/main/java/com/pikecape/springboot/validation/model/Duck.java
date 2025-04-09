package com.pikecape.springboot.validation.model;

import com.pikecape.springboot.validation.validator.DuckConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
@DuckConstraint
public class Duck {
  @NotBlank(message = "UID cannot be empty")
  String uid;
  @NotBlank
  @Min(value = 5, message = "Name must be at least 5 characters long")
  String name;
  @NotNull(message = "Birthday cannot be null")
  LocalDate birthday;
  @Email(message = "Email must be in correct format")
  String email;
  @Positive(message = "Height must be positive")
  Integer height;
}
