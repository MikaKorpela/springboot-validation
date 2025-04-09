package com.pikecape.springboot.validation.controller;

import com.pikecape.springboot.validation.model.Duck;
import com.pikecape.springboot.validation.service.DuckService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ducks")
@RequiredArgsConstructor
public class DuckController {
  private final DuckService duckService;

  @PostMapping
  public Duck postDuck(@RequestBody @Valid Duck duck) {
    return duck;
  }
}
