package com.bootcamp_2024_1.emazon.application.mapper;

import com.bootcamp_2024_1.emazon.application.dto.CategoryResponseDTO;
import com.bootcamp_2024_1.emazon.domain.model.DomainCategory;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface CategoryResponseMapper {

  CategoryResponseDTO toDto(DomainCategory entity);


}
