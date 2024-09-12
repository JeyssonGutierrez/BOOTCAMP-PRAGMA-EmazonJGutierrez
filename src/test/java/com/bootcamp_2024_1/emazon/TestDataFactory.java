package com.bootcamp_2024_1.emazon;

import com.bootcamp_2024_1.emazon.application.dto.CategoryRequestDTO;
import com.bootcamp_2024_1.emazon.application.dto.CategoryResponseDTO;
import com.bootcamp_2024_1.emazon.domain.model.DomainCategory;
import com.bootcamp_2024_1.emazon.infrastructure.entity.CategoryEntity;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

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

  public static CategoryRequestDTO buildCategoryRequestDTO() {
    var category = new CategoryRequestDTO();
    category.setName("pintura");
    category.setDescription("test pintura");

    return category;
  }

  public static CategoryResponseDTO buildCategoryResponseDTO() {

    var category = new CategoryResponseDTO();
    category.setName("pintura");
    category.setDescription("test pintura");

    return category;
  }

  public static Page<CategoryEntity> buildCategoryEntityPage(int page, int size,
      String sortDirection) {
    Sort sort = sortDirection.equalsIgnoreCase("DESC") ? Sort.by("name").descending()
        : Sort.by("name").ascending();
    Pageable pageable = PageRequest.of(page, size, sort);
    List<CategoryEntity> categoryEntities = buildCategoryEntityList();
    return new PageImpl<>(categoryEntities, pageable, categoryEntities.size());
  }

}



