package com.bootcamp_2024_1.emazon.infrastructure.adapter.outbount;

import static com.bootcamp_2024_1.emazon.TestDataFactory.buildCategoryEntity;
import static com.bootcamp_2024_1.emazon.TestDataFactory.buildDomainCategory;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.bootcamp_2024_1.emazon.TestDataFactory;
import com.bootcamp_2024_1.emazon.domain.model.DomainCategory;
import com.bootcamp_2024_1.emazon.infrastructure.adapter.outbound.CategoryRepositoryImpl;
import com.bootcamp_2024_1.emazon.infrastructure.entity.CategoryEntity;
import com.bootcamp_2024_1.emazon.infrastructure.mapper.CategoryEntityMapper;
import com.bootcamp_2024_1.emazon.infrastructure.repository.CategoryJpaRepository;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@ExtendWith(MockitoExtension.class)
class CategoryJpaRepositoryImplTest {

  @Mock
  CategoryJpaRepository jpaRepository;
  @Mock
  CategoryEntityMapper mapper;

  @Mock
  private CategoryJpaRepository categoryJpaRepository;

  @Mock
  private CategoryEntityMapper categoryEntityMapper;

  @InjectMocks
  CategoryRepositoryImpl categoryRepository;


  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }


  @Test
  void findByNameTest() {
    when(mapper.toDomain(any(CategoryEntity.class)))
        .thenReturn(buildDomainCategory());

    when(jpaRepository.findByName(Mockito.anyString())).thenReturn(buildCategoryEntity());

    var result = categoryRepository.findByName("prueba");
    Assertions.assertNotNull(result);
    assertEquals(1L, result.getId());


  }

  @Test
  void saveCategoryTest() {
    when(mapper.toEntity(any(DomainCategory.class)))
        .thenReturn(buildCategoryEntity());

    when(jpaRepository.save(any(CategoryEntity.class)))
        .thenReturn(buildCategoryEntity());

    when(mapper.toDomain(any(CategoryEntity.class)))
        .thenReturn(buildDomainCategory());

    var result = categoryRepository.saveCategory(buildDomainCategory());
    Assertions.assertNotNull(result);
    assertEquals(1L, result.getId());
    assertEquals("Pintura", result.getName());
    assertEquals("test Pintura", result.getDescription());

  }

  @Test
  void getCategoriesList_ShouldReturnMappedDomainCategories() {

    int page = 0;
    int size = 10;
    String direction = "ASC";

    Pageable pageable = PageRequest.of(page, size, Sort.by("name").ascending());

    List<CategoryEntity> categoryEntities = TestDataFactory.buildCategoryEntityList();
    Page<CategoryEntity> categoryEntityPage = new PageImpl<>(categoryEntities, pageable, categoryEntities.size());

    when(categoryJpaRepository.findAll(pageable)).thenReturn(categoryEntityPage);
    when(categoryEntityMapper.toDomain(any(CategoryEntity.class)))
        .thenAnswer(invocation -> {
          CategoryEntity entity = invocation.getArgument(0);
          return TestDataFactory.buildDomainCategory();
        });

    // Act
    Page<DomainCategory> result = categoryRepository.getCategoriesList(page, size, direction);

    // Assert
    assertEquals(categoryEntities.size(), result.getContent().size());
    verify(categoryJpaRepository).findAll(pageable);
    verify(categoryEntityMapper, times(categoryEntities.size())).toDomain(any(CategoryEntity.class));
  }

}


