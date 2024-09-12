package com.bootcamp_2024_1.emazon.application.handler;

import com.bootcamp_2024_1.emazon.application.dto.CategoryRequestDTO;
import com.bootcamp_2024_1.emazon.application.dto.CategoryResponseDTO;
import com.bootcamp_2024_1.emazon.application.dto.PagedResponseDTO;
import com.bootcamp_2024_1.emazon.application.mapper.CategoryRequestMapper;
import com.bootcamp_2024_1.emazon.application.mapper.CategoryResponseMapper;
import com.bootcamp_2024_1.emazon.domain.api.CategoryServicePort;
import com.bootcamp_2024_1.emazon.domain.model.DomainCategory;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Setter
@Getter
public class CategoryHandlerImpl implements CategoryHandler {

  private final CategoryServicePort categoryServicePort;
  private final CategoryRequestMapper categoryRequestMapper;
  private final CategoryResponseMapper categoryResponseMapper;


  @Override
  public CategoryResponseDTO saveCategory(
      CategoryRequestDTO categoryRequestDTO) {
    DomainCategory domainCategory = categoryRequestMapper.toModel(categoryRequestDTO);
    categoryServicePort.saveCategory(domainCategory);
    return categoryResponseMapper.toDto(domainCategory);
  }

  @Override
  public PagedResponseDTO<CategoryResponseDTO> getAllCategories(Pageable pageable) {

    Page<DomainCategory> domainCategories = categoryServicePort.getAll(pageable);

    List<CategoryResponseDTO> categoryDTOs = domainCategories
        .getContent()
        .stream()
        .map(categoryResponseMapper::toDto)
        .collect(Collectors.toList());

    return new PagedResponseDTO<>(
        categoryDTOs,
        domainCategories.getTotalElements(),
        domainCategories.getTotalPages(),
        domainCategories.getSize()
    );
  }
}
