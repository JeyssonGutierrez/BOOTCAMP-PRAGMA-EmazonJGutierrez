package com.bootcamp_2024_1.emazon.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
 * @utor Jeysson Gutiérrez
 * Modelo de la tabla "categorias" en la base de datos db_emazon
 */

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class DomainCategory {

  private Long id;
  private String name;
  private String description;
}
