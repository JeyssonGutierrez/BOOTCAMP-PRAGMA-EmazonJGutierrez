package com.bootcamp_2024_1.emazon.application.handler;

import com.bootcamp_2024_1.emazon.application.dto.CategoryRequestDTO;
import com.bootcamp_2024_1.emazon.application.dto.CategoryResponseDTO;
import java.util.List;

public interface CategoryHandler {

    List<CategoryResponseDTO> getAllCategories();

    CategoryResponseDTO saveCategory(CategoryRequestDTO categoryRequestDTO) throws IllegalAccessException;  // Se añade la excepción aquí

    void saveCategoryInCategory(CategoryRequestDTO categoryRequestDTO);

    List<CategoryResponseDTO> getAllCategoryFromCategory();
}
