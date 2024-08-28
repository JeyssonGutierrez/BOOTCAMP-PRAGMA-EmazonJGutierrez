package com.bootcamp_2024_1.emazon.domain.exceptions;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HandlerExceptionTest {

  @Test
  void globalExceptionMethodTest() {
    // Preparación
    GlobalException ex = Mockito.mock(GlobalException.class);
    Mockito.when(ex.getStatus()).thenReturn(HttpStatus.BAD_REQUEST.value());
    Mockito.when(ex.getMessage()).thenReturn("Test error message");

    HandlerException handlerException = new HandlerException();

    // Ejecución
    ResponseEntity<ResponseErrorDto> response = handlerException.globalExceptionMethod(ex);

    // Verificación
    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    assertEquals("Test error message", response.getBody().getMessage());
    assertEquals(String.valueOf(HttpStatus.BAD_REQUEST.value()), response.getBody().getStatusCode());
    assertEquals(HttpStatus.BAD_REQUEST.name(), response.getBody().getStatusName());
  }
}
