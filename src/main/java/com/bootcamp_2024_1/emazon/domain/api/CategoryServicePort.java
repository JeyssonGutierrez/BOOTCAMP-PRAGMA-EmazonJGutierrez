package com.bootcamp_2024_1.emazon.domain.api;

import com.bootcamp_2024_1.emazon.domain.exceptions.GlobalException;
import com.bootcamp_2024_1.emazon.domain.model.DomainCategory;
import com.bootcamp_2024_1.emazon.infrastructure.entity.CategoryEntity;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface CategoryServicePort {

    //Operacion guardar category
  DomainCategory saveCategory(DomainCategory domainCategory) throws GlobalException;

  DomainCategory findByName(String name);

  Page<DomainCategory> getAll(Pageable pageable);
}
