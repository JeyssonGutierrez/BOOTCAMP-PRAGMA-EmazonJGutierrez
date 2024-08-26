package com.bootcamp_2024_1.emazon.application.mapper;

import com.bootcamp_2024_1.emazon.application.dto.CategoryRequestDTO;
import com.bootcamp_2024_1.emazon.application.dto.CategoryResponseDTO;
import com.bootcamp_2024_1.emazon.domain.model.DomainCategory;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/*
 * @utor Jeysson Gutiérrez
 * Mapeador para el categoryRequestDTO, pasamos información de un objeto a otro con MapStruct.
 * Inyectamos el mapper como un bean de Spring (dependencia), lo que evita que debamos generar una INSTANCE del mappeador.
 */
@Mapper(componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface CategoryRequestMapper {

  // Mapea CategoryRequestDTO a CategoryModel
  DomainCategory toModel(CategoryRequestDTO categoryRequestDTO);

  // Mapea entidad Category a un CategoryRequestDTO
  CategoryResponseDTO toDto(DomainCategory entity);


}
