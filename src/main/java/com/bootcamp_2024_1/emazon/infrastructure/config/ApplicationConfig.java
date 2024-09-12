package com.bootcamp_2024_1.emazon.infrastructure.config;

import com.bootcamp_2024_1.emazon.application.handler.BrandHandler;
import com.bootcamp_2024_1.emazon.application.handler.BrandHandlerImpl;
import com.bootcamp_2024_1.emazon.application.handler.CategoryHandler;
import com.bootcamp_2024_1.emazon.application.handler.CategoryHandlerImpl;
import com.bootcamp_2024_1.emazon.application.mapper.BrandRequestMapper;
import com.bootcamp_2024_1.emazon.application.mapper.BrandResponseMapper;
import com.bootcamp_2024_1.emazon.application.mapper.CategoryRequestMapper;
import com.bootcamp_2024_1.emazon.application.mapper.CategoryResponseMapper;
import com.bootcamp_2024_1.emazon.domain.api.BrandServicePort;
import com.bootcamp_2024_1.emazon.domain.api.CategoryServicePort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

  @Bean
  public CategoryHandler categoryHandler(CategoryServicePort categoryServicePort,
      CategoryRequestMapper categoryRequestMapper,
      CategoryResponseMapper categoryResponseMapper) {
    return new CategoryHandlerImpl(categoryServicePort, categoryRequestMapper,
        categoryResponseMapper);
  }

  @Bean
  public BrandHandler brandHandler(BrandServicePort brandServicePort,
      BrandRequestMapper brandRequestMapper,
      BrandResponseMapper brandResponseMapper) {
    return new BrandHandlerImpl(brandServicePort, brandRequestMapper, brandResponseMapper);
  }
}