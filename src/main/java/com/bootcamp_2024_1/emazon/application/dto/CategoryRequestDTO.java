package com.bootcamp_2024_1.emazon.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
 * @utor Jeysson Gutiérrez
 * Este dto encapsula las peticiones peticiones que provienen de una solicitud realizada por el
 * usuario, generalmente desde el front-end hacia el back-end.
 */

//Anotaciones de lombok para generar Contructor, Getters y Setters
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryRequestDTO {
  private String name;
  private String description;
}
