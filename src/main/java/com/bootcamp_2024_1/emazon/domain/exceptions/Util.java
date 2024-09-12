package com.bootcamp_2024_1.emazon.domain.exceptions;


import org.springframework.http.HttpStatus;

public class Util {

  public static void throwException( String message) {
    throw new GlobalException(HttpStatus.BAD_REQUEST.value(), message);
  }
  private  Util (){

  }
}
