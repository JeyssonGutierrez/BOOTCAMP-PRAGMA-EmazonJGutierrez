package com.bootcamp_2024_1.emazon.application.handler;

import com.bootcamp_2024_1.emazon.application.dto.CategoryRequestDTO;
import com.bootcamp_2024_1.emazon.application.dto.CategoryResponseDTO;
import com.bootcamp_2024_1.emazon.application.mapper.CategoryRequestMapper;
import com.bootcamp_2024_1.emazon.application.mapper.CategoryResponseMapper;
import com.bootcamp_2024_1.emazon.domain.api.CategoryServicePort;
import com.bootcamp_2024_1.emazon.domain.model.DomainCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryHandlerImpl implements CategoryHandler {

  private final CategoryServicePort categoryServicePort;
  private final CategoryRequestMapper categoryRequestMapper;
  private final CategoryResponseMapper categoryResponseMapper;


  @Override
  public CategoryResponseDTO saveCategory(CategoryRequestDTO categoryRequestDTO) throws IllegalAccessException {  // Añadimos la excepción aquí
    DomainCategory domainCategory = categoryRequestMapper.toModel(categoryRequestDTO);
    categoryServicePort.saveCategory(domainCategory);  // Aquí se lanza la excepción
    return categoryResponseMapper.toDto(domainCategory);
  }

  @Override
  public List<CategoryResponseDTO> getAllCategories() {
    List<DomainCategory> categories = categoryServicePort.findAll(); // Usa el método correcto
    return categoryResponseMapper.toDtoList(categories);
  }

  @Override
  public void saveCategoryInCategory(CategoryRequestDTO categoryRequestDTO) {
    // Implementación si es necesario
  }

  @Override
  public List<CategoryResponseDTO> getAllCategoryFromCategory() {
    return List.of(); // Implementación si es necesario
  }
}
