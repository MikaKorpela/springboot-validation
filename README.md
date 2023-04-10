# Spring Boot Validation

This project contains examples how to perform validation in Spring Boot.

## Spring Boot Version and Dependencies

This project shall use Spring Boot version 3.0.4, and has the following Maven dependencies:

* spring-boot-starter-web
* spring-boot-starter-test
* spring-boot-starter-validation
* lombok

See the ```pom.xml``` for more details.

The ```spring-boot-starter-validation``` enables the data validation in Spring Boot project, and it also brings the ```jakarta.validation.api``` classes.

There is also ```javax.validation.api``` that contains similar functionalities, but those two APIS should not be mixed, because they do not work together. 

## Validation Order

By default, the validation is done in that order as the validators exist in the DTO class.

The default order can be changed using ```@GroupSequence``` annotation. Empty interface classes are required for the sequence, and the validation happens in the order defined by the annotation.

In the example on below the ```@NotNull``` validation is performed first, and then the ```@DuckConstraint``` validation.

```java
@GroupSequence({FirstValidationGroup.class, SecondValidationGroup.class, DuckDTO.class})
@DuckConstraint(groups = SecondValidationGroup.class)
public class DuckDTO {
  String uid;
  String name;
  @NotNull(message = "Birthday cannot be null", groups = FirstValidationGroup.class)
  LocalDate birthday;
  String email;
  Integer height;
}
```

## Open Issues

How to perform integration testing between controller and service classes, when the validation is in service class.
