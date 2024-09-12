package com.bootcamp_2024_1.emazon.application.mapper;

import com.bootcamp_2024_1.emazon.application.dto.CategoryRequestDTO;
import com.bootcamp_2024_1.emazon.application.dto.CategoryResponseDTO;
import com.bootcamp_2024_1.emazon.domain.model.DomainCategory;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface CategoryRequestMapper {

  DomainCategory toModel(CategoryRequestDTO categoryRequestDTO);

  CategoryResponseDTO toDto(DomainCategory entity);


}
