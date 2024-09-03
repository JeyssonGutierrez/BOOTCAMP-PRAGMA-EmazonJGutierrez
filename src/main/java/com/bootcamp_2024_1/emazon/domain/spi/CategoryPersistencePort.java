package com.bootcamp_2024_1.emazon.domain.spi;

import com.bootcamp_2024_1.emazon.domain.model.DomainCategory;
import org.springframework.data.domain.Page;

public interface CategoryPersistencePort {

  // Guarda una categoría
  DomainCategory saveCategory(DomainCategory domainCategory);

  DomainCategory findByName (String name);

  Page<DomainCategory> getCategoriesList (int page, int size, String direction);
}
