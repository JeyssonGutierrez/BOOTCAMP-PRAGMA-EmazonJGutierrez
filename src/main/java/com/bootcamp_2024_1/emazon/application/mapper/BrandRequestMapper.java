package com.bootcamp_2024_1.emazon.application.mapper;

import com.bootcamp_2024_1.emazon.application.dto.BrandRequestDTO;
import com.bootcamp_2024_1.emazon.application.dto.BrandResponseDTO;
import com.bootcamp_2024_1.emazon.domain.model.DomainBrand;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BrandRequestMapper {

  DomainBrand toModel(BrandRequestDTO brandRequestDTO);

  BrandResponseDTO toDto(DomainBrand entity);
}
