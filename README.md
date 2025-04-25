# springboot-validation #

This project contains examples how to perform validation in Spring Boot.

Validations are defined in the DTO classes, and they are triggered by the Spring Boot framework:
* Using `@Valid` annotation in the `@RestController` class.
* Using `Validator` class directly.

## DTO Class ##

The `Duck` class uses the custom validation annotation `@DuckConstraint` and pre-defined validation annotations.

```java
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
```

## Rest Controller ##

The `DuckController` class triggers validation with `@Valid` annotation.

```java
@PostMapping
public Duck postDuck(@RequestBody @Valid Duck duck) {
  return duck;
}
```

## Custom Validation ##

The `DuckConstraint` class is a custom validation that checks if the duck's birthday is in the future.
