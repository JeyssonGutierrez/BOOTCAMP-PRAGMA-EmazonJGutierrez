package com.bootcamp_2024_1.emazon.application.service;

import com.bootcamp_2024_1.emazon.application.dto.CategoryResponseDTO;
import com.bootcamp_2024_1.emazon.domain.model.DomainCategory;
import com.bootcamp_2024_1.emazon.domain.spi.CategoryPersistencePort;
import com.bootcamp_2024_1.emazon.infrastructure.mapper.CategoryEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {

  private final CategoryPersistencePort categoryPersistencePort;
  private final CategoryEntityMapper categoryEntityMapper;

  public Page<CategoryResponseDTO> getCategoriesList(int page, int size, String direction) {
    Page<DomainCategory> categoriesPage = categoryPersistencePort.getCategoriesList(page, size, direction);
    return categoriesPage.map(categoryEntityMapper::toResponseDTO);
  }
}