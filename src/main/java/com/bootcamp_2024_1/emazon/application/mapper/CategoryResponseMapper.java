package com.bootcamp_2024_1.emazon.application.mapper;

import com.bootcamp_2024_1.emazon.application.dto.CategoryResponseDTO;
import com.bootcamp_2024_1.emazon.domain.model.DomainCategory;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

/*
 * @utor Jeysson Gutiérrez
 * CategoryResponseMapper se utiliza para convertir la entidad de dominio Category en un DTO
 * que será enviado de vuelta al cliente como respuesta.
 */
@Mapper(componentModel = "spring")
public interface CategoryResponseMapper {

  // Mapea una entidad Category a un CategoryResponseDTO
  CategoryResponseDTO toDto(DomainCategory entity);

  // Mapea una lista de entidades Category a una lista de CategoryResponseDTO
  List<CategoryResponseDTO> toDtoList(List<DomainCategory> entities);
}
