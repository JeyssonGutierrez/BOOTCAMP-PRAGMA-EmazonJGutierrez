package com.bootcamp_2024_1.emazon.application.handler;

import com.bootcamp_2024_1.emazon.application.dto.CategoryRequestDTO;
import com.bootcamp_2024_1.emazon.application.dto.CategoryResponseDTO;
import com.bootcamp_2024_1.emazon.domain.exceptions.GlobalException;
import java.util.List;

public interface CategoryHandler {

    CategoryResponseDTO saveCategory(CategoryRequestDTO categoryRequestDTO) throws IllegalAccessException;

    List<CategoryResponseDTO> findAllCategories() throws GlobalException;

    void saveCategoryInCategory(CategoryRequestDTO categoryRequestDTO);

}
