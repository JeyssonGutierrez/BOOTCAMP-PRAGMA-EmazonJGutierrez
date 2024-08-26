package com.bootcamp_2024_1.emazon.domain.exceptions;


public class Util {

  public static void throwException(int status, String message) {
    throw new GlobalException(status, message);
  }

  private Util (){

  }
}
