package com.bootcamp_2024_1.emazon.infrastructure.mapper;

import com.bootcamp_2024_1.emazon.application.dto.BrandResponseDTO;
import com.bootcamp_2024_1.emazon.domain.model.DomainBrand;
import com.bootcamp_2024_1.emazon.infrastructure.entity.BrandEntity;
import lombok.AllArgsConstructor;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BrandEntityMapper {

  BrandEntity toEntity(DomainBrand domainBrand);

  DomainBrand toDomain(BrandEntity brandEntity);

  BrandResponseDTO toResponseDTO(BrandEntity brandEntity);

  BrandResponseDTO toResponseDTO(DomainBrand domainBrand);



}
