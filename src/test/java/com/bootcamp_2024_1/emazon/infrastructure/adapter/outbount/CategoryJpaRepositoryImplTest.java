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
    private CategoryJpaRepository categoryJpaRepository;

    @Mock
    private CategoryEntityMapper categoryEntityMapper;

    @InjectMocks
    CategoryRepositoryImpl categoryRepository;


    @Test
    void findByNameTest() {
      when(categoryEntityMapper.toDomain(any(CategoryEntity.class)))
          .thenReturn(buildDomainCategory());

      when(categoryJpaRepository.findByName(Mockito.anyString())).thenReturn(buildCategoryEntity());

      var result = categoryRepository.findByName("prueba");
      Assertions.assertNotNull(result);
      assertEquals(1L, result.getId());


    }

    @Test
    void saveCategoryTest() {
      when(categoryEntityMapper.toEntity(any(DomainCategory.class)))
          .thenReturn(buildCategoryEntity());

      when(categoryJpaRepository.save(any(CategoryEntity.class)))
          .thenReturn(buildCategoryEntity());

      when(categoryEntityMapper.toDomain(any(CategoryEntity.class)))
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

      // Utiliza la clase TestDataFactory para crear el Page<CategoryEntity>
      Page<CategoryEntity> categoryEntityPage = TestDataFactory.buildCategoryEntityPage(page, size, direction);

      when(categoryJpaRepository.findAll(any(Pageable.class))).thenReturn(categoryEntityPage);
      when(categoryEntityMapper.toDomain(any(CategoryEntity.class)))
          .thenAnswer(invocation -> TestDataFactory.buildDomainCategory());

      // Act
      Page<DomainCategory> result = categoryRepository.getCategoriesList(page, size, direction);

      // Assert
      assertEquals(categoryEntityPage.getContent().size(), result.getContent().size());
      verify(categoryJpaRepository).findAll(any(Pageable.class));
      verify(categoryEntityMapper, times(categoryEntityPage.getContent().size())).toDomain(any(CategoryEntity.class));
    }
  }


