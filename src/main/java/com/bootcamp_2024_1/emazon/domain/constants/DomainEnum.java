package com.bootcamp_2024_1.emazon.domain.constants;


public enum DomainEnum {

  //MESSAGE
  CREATE_SUCCESS("Create Successfuly"),
  QUERY_SUCCESS("Successful query"),
  QUERY_PAGEABLE_SUCCESS("Successful query pageable"),

  //Category
  SIZE_NAME("50"),
  SIZE_DESCRIPTION_CATEGORY("90"),
  SIZE_DESCRIPTION_BRANDS("120"),
  NAME_EXIST("The name %s, already exists."),
  NAME_LIMIT("The Name field cannot exceed %s characters."),
  DESCRIPTION_LIMIT("The description field cannot exceed %s characters."),
  NAME_MANDATORY("The name field is required."),
  DESCRIPTION_MANDATORY("Category description is required.");


  DomainEnum(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

  private String message;

}
