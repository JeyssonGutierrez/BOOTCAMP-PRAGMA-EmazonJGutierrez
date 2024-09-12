package com.bootcamp_2024_1.emazon.application.handler;

import com.bootcamp_2024_1.emazon.application.dto.CategoryRequestDTO;
import com.bootcamp_2024_1.emazon.application.dto.CategoryResponseDTO;
import com.bootcamp_2024_1.emazon.application.dto.PagedResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryHandler {

  CategoryResponseDTO saveCategory(CategoryRequestDTO categoryRequestDTO);

  PagedResponseDTO<CategoryResponseDTO> getAllCategories(Pageable pageable);
  }