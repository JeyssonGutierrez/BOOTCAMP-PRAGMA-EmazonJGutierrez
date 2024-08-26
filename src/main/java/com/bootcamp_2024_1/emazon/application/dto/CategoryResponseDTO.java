package com.bootcamp_2024_1.emazon.application.dto;

/*
 *@utor Jeysson Gutiérrez
 * Este dto se usa para la consulta, lo que le vamos a retornar a nuestro usuario o cliente
 * proporcionando unicamente la información necesaria y omitiendo detalles que no son relevantes
 * para el usuario. encapsula los datos que el back-end devuelve al front-end.
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//Anotaciones de lombok para generar Contructor, Getters y Setters
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryResponseDTO {
  private String name;
  private String description;
}
