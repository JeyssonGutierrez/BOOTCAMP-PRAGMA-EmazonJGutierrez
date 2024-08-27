package com.bootcamp_2024_1.emazon.infrastructure.adapter.outbount;

import com.bootcamp_2024_1.emazon.domain.model.DomainCategory;
import com.bootcamp_2024_1.emazon.infrastructure.adapter.outbound.CategoryRepositoryImpl;
import com.bootcamp_2024_1.emazon.infrastructure.entity.CategoryEntity;
import com.bootcamp_2024_1.emazon.infrastructure.mapper.CategoryEntityMapper;
import com.bootcamp_2024_1.emazon.infrastructure.repository.CategoryJpaRepository;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CategoryRepositoryImplTest {

  @Mock
  CategoryJpaRepository jpaRepository;
  @Mock
  CategoryEntityMapper mapper;

  @InjectMocks
  CategoryRepositoryImpl categoryRepository;


  @Test
  void findByNameTest() {
    Mockito.when(mapper.toDomain(Mockito.any(CategoryEntity.class)))
        .thenReturn(buildDomainCategory());

    Mockito.when(jpaRepository.findByName(Mockito.anyString())).thenReturn(buildCategoryEntity());

    var result = categoryRepository.findByName("prueba");
    Assertions.assertNotNull(result);
    Assertions.assertEquals(1L, result.getId());


  }

  @Test
  void saveTest() {
    Mockito.when(mapper.toEntity(Mockito.any(DomainCategory.class)))
        .thenReturn(buildCategoryEntity());

    Mockito.when(jpaRepository.save(Mockito.any(CategoryEntity.class)))
        .thenReturn(buildCategoryEntity());

    Mockito.when(mapper.toDomain(Mockito.any(CategoryEntity.class)))
        .thenReturn(buildDomainCategory());

    var result = categoryRepository.save(buildDomainCategory());
    Assertions.assertNotNull(result);
    Assertions.assertEquals(1L, result.getId());
    Assertions.assertEquals("Pintura", result.getName());
    Assertions.assertEquals("test Pintura", result.getDescription());

  }

  @Test
  void findAllTest() {

    var result = categoryRepository.findAll();
  }

  private CategoryEntity buildCategoryEntity() {
    var category = new CategoryEntity();
    category.setId(1L);
    category.setName("Pintura");
    category.setDescription("test Pintura");

    return category;
  }

  private DomainCategory buildDomainCategory() {
    var category = new DomainCategory();
    category.setId(1L);
    category.setName("Pintura");
    category.setDescription("test Pintura");

    return category;
  }
}


