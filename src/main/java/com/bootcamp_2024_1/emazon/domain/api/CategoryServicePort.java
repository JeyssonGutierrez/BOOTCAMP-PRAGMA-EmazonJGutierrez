package com.bootcamp_2024_1.emazon.domain.api;

import com.bootcamp_2024_1.emazon.domain.model.DomainCategory;
import com.bootcamp_2024_1.emazon.infrastructure.entity.CategoryEntity;
import java.util.List;

/*
 * @utor Jeysson Gutiérrez
 * Esta interface define los contratos que los casos de uso deben cumplir,
 * describe que operaciones son posibles en el domain
 */
public interface CategoryServicePort {

    //Operacion guardar category
  DomainCategory saveCategory(DomainCategory domainCategory) throws IllegalAccessException;

  DomainCategory findByName(String name);


  void saveCategoryInCategory(DomainCategory domainCategory);


}
