package com.bootcamp_2024_1.emazon.application.service;

import com.bootcamp_2024_1.emazon.domain.spi.BrandPersistencePort;
import com.bootcamp_2024_1.emazon.infrastructure.mapper.BrandEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BrandService {

  private final BrandPersistencePort brandPersistencePort;
  private final BrandEntityMapper brandEntityMapper;


}
