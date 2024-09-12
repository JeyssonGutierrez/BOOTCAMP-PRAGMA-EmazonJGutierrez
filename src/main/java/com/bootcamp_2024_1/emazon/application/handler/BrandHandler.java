package com.bootcamp_2024_1.emazon.application.handler;

import com.bootcamp_2024_1.emazon.application.dto.BrandRequestDTO;
import com.bootcamp_2024_1.emazon.application.dto.BrandResponseDTO;

public interface BrandHandler {

  BrandResponseDTO saveBrand(BrandRequestDTO brandRequestDTO);

}
