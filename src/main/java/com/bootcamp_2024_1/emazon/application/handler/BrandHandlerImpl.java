package com.bootcamp_2024_1.emazon.application.handler;


import com.bootcamp_2024_1.emazon.application.dto.BrandRequestDTO;
import com.bootcamp_2024_1.emazon.application.dto.BrandResponseDTO;
import com.bootcamp_2024_1.emazon.application.mapper.BrandRequestMapper;
import com.bootcamp_2024_1.emazon.application.mapper.BrandResponseMapper;
import com.bootcamp_2024_1.emazon.domain.api.BrandServicePort;
import com.bootcamp_2024_1.emazon.domain.model.DomainBrand;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Getter
@Setter
public class BrandHandlerImpl implements BrandHandler {


  private final BrandServicePort brandServicePort;
  private final BrandRequestMapper brandRequestMapper;
  private final BrandResponseMapper brandResponseMapper;

  @Override
  public BrandResponseDTO saveBrand(BrandRequestDTO brandRequestDTO) {
    DomainBrand domainBrand = brandRequestMapper.toModel(brandRequestDTO);
    brandServicePort.saveBrand(domainBrand);
    return brandResponseMapper.toDto(domainBrand);
  }
}
