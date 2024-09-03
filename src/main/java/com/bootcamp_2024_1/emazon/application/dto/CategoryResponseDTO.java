package com.bootcamp_2024_1.emazon.application.dto;

/*
 *@utor Jeysson Gutiérrez
 */

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//Anotaciones de lombok para generar Contructor, Getters y Setters
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class CategoryResponseDTO {
  private Long id;
  private String name;
  private String description;
}
