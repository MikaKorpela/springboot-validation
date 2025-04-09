package com.pikecape.springboot.validation.service;

import com.pikecape.springboot.validation.model.Duck;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
public class DuckService {
  public Duck createDuck(Duck duck) {
    return duck;
  }
}
