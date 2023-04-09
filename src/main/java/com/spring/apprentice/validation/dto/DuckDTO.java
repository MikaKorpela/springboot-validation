package com.spring.apprentice.validation.dto;

import com.spring.apprentice.validation.validation.DuckConstraint;
import com.spring.apprentice.validation.validation.FirstValidationGroup;
import com.spring.apprentice.validation.validation.SecondValidationGroup;
import jakarta.validation.GroupSequence;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Builder
@Data
@FieldDefaults(makeFinal = true)
@GroupSequence({FirstValidationGroup.class, SecondValidationGroup.class, DuckDTO.class})
@DuckConstraint(groups = SecondValidationGroup.class)
public class DuckDTO {
  @NotEmpty(message = "UID cannot be empty", groups = FirstValidationGroup.class)
  String uid;
  String name;
  @NotNull(message = "Birthday cannot be null", groups = FirstValidationGroup.class)
  LocalDate birthday;
  @Email(message = "Email must be in correct format", groups = FirstValidationGroup.class)
  String email;
  @Positive(message = "Height must be positive", groups = FirstValidationGroup.class)
  Integer height;
}
