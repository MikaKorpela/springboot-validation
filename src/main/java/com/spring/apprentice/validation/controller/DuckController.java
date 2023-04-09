package com.spring.apprentice.validation.controller;

import com.spring.apprentice.validation.dto.DuckDTO;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ducks")
public class DuckController {
  @PostMapping
  public DuckDTO postDuck(
    @RequestBody @Valid DuckDTO duckDTO
  ) {
    return duckDTO;
  }
}
