package com.bootcamp_2024_1.emazon.application.handler;

import com.bootcamp_2024_1.emazon.application.dto.CategoryRequestDTO;
import com.bootcamp_2024_1.emazon.application.dto.CategoryResponseDTO;
import com.bootcamp_2024_1.emazon.application.mapper.CategoryRequestMapper;
import com.bootcamp_2024_1.emazon.application.mapper.CategoryResponseMapper;
import com.bootcamp_2024_1.emazon.domain.api.CategoryServicePort;
import com.bootcamp_2024_1.emazon.domain.exceptions.GlobalException;
import com.bootcamp_2024_1.emazon.domain.model.DomainCategory;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Setter
@Getter
public class CategoryHandlerImpl implements CategoryHandler {

  private final CategoryServicePort categoryServicePort;
  private final CategoryRequestMapper categoryRequestMapper;
  private final CategoryResponseMapper categoryResponseMapper;


  @Override
  public CategoryResponseDTO saveCategory(CategoryRequestDTO categoryRequestDTO)
      throws IllegalAccessException {  // Añadimos la excepción aquí
    DomainCategory domainCategory = categoryRequestMapper.toModel(categoryRequestDTO);
    categoryServicePort.saveCategory(domainCategory);  // Aquí se lanza la excepción
    return categoryResponseMapper.toDto(domainCategory);
  }

  @Override
  public List<CategoryResponseDTO> findAllCategories() throws GlobalException {
    return List.of();
  }

  @Override
  public void saveCategoryInCategory(CategoryRequestDTO categoryRequestDTO) {
  }

}
