package com.bootcamp_2024_1.emazon.domain.exceptions;


public class GlobalException extends RuntimeException {

  private final int status;
  private final String message;


  public GlobalException(int status, String message) {
    super(message);
    this.status = status;
    this.message = message;
  }

  public int getStatus(){
    return this.status;
  }

  public String getMessage(){
    return this.message;
  }
}

