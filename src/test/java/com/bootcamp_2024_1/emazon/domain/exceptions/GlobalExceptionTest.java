package com.bootcamp_2024_1.emazon.domain.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GlobalExceptionTest {

  @Test
  void testGetStatus() {
    GlobalException exception = new GlobalException(400, "Test error message");
    assertEquals(400, exception.getStatus());
  }

  @Test
  void testGetMessage() {
    GlobalException exception = new GlobalException(400, "Test error message");
    assertEquals("Test error message", exception.getMessage());
  }
}