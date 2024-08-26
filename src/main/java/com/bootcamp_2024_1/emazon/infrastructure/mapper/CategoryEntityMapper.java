package com.bootcamp_2024_1.emazon.infrastructure.mapper;

import com.bootcamp_2024_1.emazon.infrastructure.entity.CategoryEntity;
import com.bootcamp_2024_1.emazon.domain.model.DomainCategory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoryEntityMapper {

  // Convertir de modelo de dominio a entidad de infraestructura

  CategoryEntity toEntity(DomainCategory domainCategory);

  // Convertir de entidad de infraestructura a modelo de dominio
  DomainCategory toDomain(CategoryEntity categoryEntity);
}
