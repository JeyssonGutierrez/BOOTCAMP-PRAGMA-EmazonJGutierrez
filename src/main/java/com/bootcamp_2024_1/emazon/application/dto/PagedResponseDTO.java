package com.bootcamp_2024_1.emazon.application.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class PagedResponseDTO<T> {

  private List<T> content;
  private long totalElements;
  private int totalPages;
  private int size;
}
