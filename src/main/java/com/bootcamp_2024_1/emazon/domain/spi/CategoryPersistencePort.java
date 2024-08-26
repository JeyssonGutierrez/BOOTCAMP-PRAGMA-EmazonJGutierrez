package com.bootcamp_2024_1.emazon.domain.spi;

import com.bootcamp_2024_1.emazon.domain.model.DomainCategory;
import java.util.List;

public interface CategoryPersistencePort {

  // Guarda una categoría
  DomainCategory save(DomainCategory domainCategory);

  // Devuelve una lista de todas las categorías
  List<DomainCategory> findAll();

  DomainCategory findByName (String name);
}
