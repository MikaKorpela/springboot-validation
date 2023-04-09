package com.spring.apprentice.validation.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.apprentice.validation.dto.DuckDTO;
import java.time.LocalDate;
import java.util.UUID;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@WebMvcTest(controllers = DuckController.class)
@AutoConfigureMockMvc(addFilters = false)
class DuckControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  private final String rootUrl = "/api/ducks";

  @Test
  void testPostDuck() throws Exception {
    DuckDTO duckDTO = DuckDTO.builder()
      .uid(UUID.randomUUID().toString())
      .name("Donald")
      .birthday(LocalDate.now())
      .build();

    MvcResult result = mockMvc.perform(post(rootUrl)
        .content(objectMapper.writeValueAsString(duckDTO))
        .contentType(MediaType.APPLICATION_JSON)
      )
      .andExpect(status().isOk())
      .andReturn();

    assertEquals(
        objectMapper.writeValueAsString(duckDTO),
        result.getResponse().getContentAsString()
    );
  }

  @Test
  void testPostDuck_MissingBirthday() throws Exception {
    DuckDTO duckDTO = DuckDTO.builder()
      .uid(UUID.randomUUID().toString())
      .name("Donald")
      .build();

    MvcResult result = mockMvc.perform(post(rootUrl)
        .content(objectMapper.writeValueAsString(duckDTO))
        .contentType(MediaType.APPLICATION_JSON)
      )
      .andExpect(status().isBadRequest())
      .andReturn();

    assertTrue(result.getResolvedException().getMessage().contains("Birthday cannot be null"));
  }

  @Test
  void testPostDuck_TooOldDuck() throws Exception {
    DuckDTO duckDTO = DuckDTO.builder()
      .uid(UUID.randomUUID().toString())
      .name("Donald")
      .birthday(LocalDate.now().minusDays(1))
      .build();

    MvcResult result = mockMvc.perform(post(rootUrl)
        .content(objectMapper.writeValueAsString(duckDTO))
        .contentType(MediaType.APPLICATION_JSON)
      )
      .andExpect(status().isBadRequest())
      .andReturn();

    assertTrue(result.getResolvedException().getMessage().contains("Duck is too old"));
  }

  @Test
  void testPostDuck_TooYoungDuck() throws Exception {
    DuckDTO duckDTO = DuckDTO.builder()
      .uid(UUID.randomUUID().toString())
      .name("Donald")
      .birthday(LocalDate.now().plusDays(1))
      .build();

    MvcResult result = mockMvc.perform(post(rootUrl)
        .content(objectMapper.writeValueAsString(duckDTO))
        .contentType(MediaType.APPLICATION_JSON)
      )
      .andExpect(status().isBadRequest())
      .andReturn();

    assertTrue(result.getResolvedException().getMessage().contains("Duck is too young"));
  }
}
