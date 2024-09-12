package com.bootcamp_2024_1.emazon.domain.api;

import com.bootcamp_2024_1.emazon.domain.exceptions.GlobalException;
import com.bootcamp_2024_1.emazon.domain.model.DomainBrand;
import com.bootcamp_2024_1.emazon.domain.model.DomainCategory;

public interface BrandServicePort {
  DomainBrand saveBrand(DomainBrand domainBrand) throws GlobalException;

  DomainBrand findByName(String name);

}
