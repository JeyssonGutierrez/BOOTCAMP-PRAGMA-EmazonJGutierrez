package com.bootcamp_2024_1.emazon.domain.spi;

import com.bootcamp_2024_1.emazon.domain.model.DomainBrand;

public interface BrandPersistencePort {
  DomainBrand saveBrand(DomainBrand domainBrand);

  DomainBrand findByName(String name);
}
