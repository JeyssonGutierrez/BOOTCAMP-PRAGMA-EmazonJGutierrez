package com.bootcamp_2024_1.emazon.domain.exceptions;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class ResponseErrorDto {

  private String uuid;
  private String statusName;
  private String statusCode;
  private String message;

}
