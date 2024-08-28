package com.bootcamp_2024_1.emazon.infrastructure.adapter.outbount;

import static com.bootcamp_2024_1.emazon.TestDataFactory.buildCategoryEntity;
import static com.bootcamp_2024_1.emazon.TestDataFactory.buildCategoryEntityList;
import static com.bootcamp_2024_1.emazon.TestDataFactory.buildDomainCategory;

import com.bootcamp_2024_1.emazon.domain.model.DomainCategory;
import com.bootcamp_2024_1.emazon.infrastructure.adapter.outbound.CategoryRepositoryImpl;
import com.bootcamp_2024_1.emazon.infrastructure.entity.CategoryEntity;
import com.bootcamp_2024_1.emazon.infrastructure.mapper.CategoryEntityMapper;
import com.bootcamp_2024_1.emazon.infrastructure.repository.CategoryJpaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
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

    Mockito.when(jpaRepository.findAll()).thenReturn(buildCategoryEntityList());

    Mockito.when(mapper.toDomain(Mockito.any(CategoryEntity.class)))
        .thenAnswer(invocation -> {
          CategoryEntity entity = invocation.getArgument(0);
          return new DomainCategory(entity.getId(), entity.getName(), entity.getDescription());
        });

    var result = categoryRepository.findAll();

    Assertions.assertNotNull(result, "La lista resultante no debe ser nula");
    Assertions.assertEquals(2, result.size(), "El tamaño de la lista debe ser 2");
    Assertions.assertEquals("Pintura", result.get(0).getName(), "El nombre de la primera categoría debe ser 'Pintura'");
    Assertions.assertEquals("accesorios", result.get(1).getName(), "El nombre de la segunda categoría debe ser 'accesorios'");

    Assertions.assertEquals(1L, result.get(0).getId(), "El ID de la primera categoría debe ser 1L");
    Assertions.assertEquals("Pintura", result.get(0).getName(), "El nombre de la primera categoría debe ser 'Pintura'");
    Assertions.assertEquals("test Pintura", result.get(0).getDescription(), "La descripción de la primera categoría debe ser 'test Pintura'");

    Assertions.assertEquals(2L, result.get(1).getId(), "El ID de la segunda categoría debe ser 2L");
    Assertions.assertEquals("accesorios", result.get(1).getName(), "El nombre de la segunda categoría debe ser 'accesorios'");
    Assertions.assertEquals("test accesorios", result.get(1).getDescription(), "La descripción de la segunda categoría debe ser 'test accesorios'");

    Mockito.verify(mapper, Mockito.times(2)).toDomain(Mockito.any(CategoryEntity.class));
  }

}


