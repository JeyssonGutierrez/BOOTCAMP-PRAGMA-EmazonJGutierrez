package com.bootcamp_2024_1.emazon;

import com.bootcamp_2024_1.emazon.application.dto.CategoryRequestDTO;
import com.bootcamp_2024_1.emazon.application.dto.CategoryResponseDTO;
import com.bootcamp_2024_1.emazon.domain.model.DomainCategory;
import com.bootcamp_2024_1.emazon.infrastructure.entity.CategoryEntity;
import java.util.List;

public class TestDataFactory {

  public static CategoryEntity buildCategoryEntity() {
    var category = new CategoryEntity();
    category.setId(1L);
    category.setName("Pintura");
    category.setDescription("test Pintura");

    return category;
  }

  public static DomainCategory buildDomainCategory() {
    var category = new DomainCategory();
    category.setId(1L);
    category.setName("Pintura");
    category.setDescription("test Pintura");

    return category;
  }

  public static List<CategoryEntity> buildCategoryEntityList() {
    return List.of(
        new CategoryEntity(1L, "Pintura", "test Pintura"),
        new CategoryEntity(2L, "accesorios", "test accesorios")
    );
  }

  public static CategoryRequestDTO builCategoryRequestDTO() {
    var category = new CategoryRequestDTO();
    category.setName("pintura");
    category.setDescription("test pintura");

    return category;
  }

  public static CategoryResponseDTO builCategoryResponseDTO() {

    var category = new CategoryResponseDTO();
    category.setName("pintura");
    category.setDescription("test pintura");

    return category;
  }


  public static List<CategoryResponseDTO> buildCategoryResponseDTOList() {
    return List.of(
        new CategoryResponseDTO("pintura", "test pintura"),
        new CategoryResponseDTO("accesorios", "test accesorios")
    );
  }


}
