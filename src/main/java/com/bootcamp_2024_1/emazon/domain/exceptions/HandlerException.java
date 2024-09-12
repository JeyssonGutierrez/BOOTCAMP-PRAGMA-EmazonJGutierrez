package com.bootcamp_2024_1.emazon.domain.exceptions;

import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class HandlerException {
  //Cambiar var ResponseErrorDto
  @ExceptionHandler({GlobalException.class})
  public ResponseEntity<ResponseErrorDto> globalExceptionMethod(GlobalException ex) {
    log.error("{}", ex.getMessage(), ex);
    var error = ResponseErrorDto.builder()
        .uuid(UUID.randomUUID().toString())
        .statusCode(String.valueOf(ex.getStatus()))
        .statusName(buildStatus(ex.getStatus()).name())
        .message(ex.getMessage()).build();
    return new ResponseEntity<>(error,buildStatus(ex.getStatus()));
  }
  private HttpStatus buildStatus(int status){
    return HttpStatus.valueOf(status);
  }
}
