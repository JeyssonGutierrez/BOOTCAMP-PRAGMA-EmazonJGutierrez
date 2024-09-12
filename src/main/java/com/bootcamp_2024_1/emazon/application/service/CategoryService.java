package com.bootcamp_2024_1.emazon.application.service;

import com.bootcamp_2024_1.emazon.application.dto.CategoryResponseDTO;
import com.bootcamp_2024_1.emazon.application.dto.PagedResponseDTO;
import com.bootcamp_2024_1.emazon.domain.model.DomainCategory;
import com.bootcamp_2024_1.emazon.domain.spi.CategoryPersistencePort;
import com.bootcamp_2024_1.emazon.infrastructure.mapper.CategoryEntityMapper;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {

  private final CategoryPersistencePort categoryPersistencePort;
  private final CategoryEntityMapper categoryEntityMapper;

  public PagedResponseDTO<CategoryResponseDTO> getCategoriesList(int page, int size,
      String direction) {
    Page<DomainCategory> categoriesPage = categoryPersistencePort.getCategoriesList(page, size,
        direction);

    List<CategoryResponseDTO> categoryDTOs = categoriesPage
        .getContent()
        .stream()
        .map(categoryEntityMapper::toResponseDTO)
        .collect(Collectors.toList());

    return new PagedResponseDTO<>(
        categoryDTOs,
        categoriesPage.getTotalElements(),
        categoriesPage.getTotalPages(),
        categoriesPage.getSize()
    );
  }
}