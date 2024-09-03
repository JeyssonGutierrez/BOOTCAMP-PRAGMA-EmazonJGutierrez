package com.bootcamp_2024_1.emazon.domain.useCase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.bootcamp_2024_1.emazon.application.dto.CategoryRequestDTO;
import com.bootcamp_2024_1.emazon.application.dto.CategoryResponseDTO;
import com.bootcamp_2024_1.emazon.application.handler.CategoryHandlerImpl;
import com.bootcamp_2024_1.emazon.application.mapper.CategoryResponseMapper;
import com.bootcamp_2024_1.emazon.domain.api.CategoryServicePort;
import com.bootcamp_2024_1.emazon.domain.model.DomainCategory;
import com.bootcamp_2024_1.emazon.domain.spi.CategoryPersistencePort;
import com.bootcamp_2024_1.emazon.TestDataFactory;
import com.bootcamp_2024_1.emazon.domain.exceptions.GlobalException;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class CategoryUseCaseTest {

  @Mock
  private CategoryServicePort categoryServicePort;

  @Mock
  CategoryPersistencePort categoryPersistencePort;

  @Mock
  private CategoryResponseMapper categoryResponseMapper;

  @InjectMocks
  CategoryUseCase useCase;

  @InjectMocks
  private CategoryHandlerImpl categoryHandler;

  @Test
  void saveCategoryTest() throws IllegalAccessException {
    DomainCategory category = TestDataFactory.buildDomainCategory();

    // Configura el mock para que devuelva la categoría cuando se guarde
    when(categoryPersistencePort.saveCategory(any(DomainCategory.class)))
        .thenReturn(category);

    DomainCategory result = useCase.saveCategory(category);

    // Verifica que la categoría se haya guardado correctamente
    assertNotNull(result);
    assertEquals("Pintura", result.getName());
    assertEquals("test Pintura", result.getDescription());
  }

  @Test
  void saveCategoryThrowsExceptionTest() {
    DomainCategory category = TestDataFactory.buildDomainCategory();

    // Simula que la categoría ya existe
    when(categoryPersistencePort.findByName("Pintura"))
        .thenReturn(category);

    // Verifica que se lance una excepción al intentar guardar una categoría existente
    Assertions.assertThrows(GlobalException.class, () -> {
      useCase.saveCategory(category);
    });
  }


  @Test
  void shouldThrowExceptionWhenCategoryNameExists() {
    // Arrange
    DomainCategory category = TestDataFactory.buildDomainCategory();
    when(categoryPersistencePort.findByName(Mockito.anyString()))
        .thenReturn(category);

    // Act & Assert
    Assertions.assertThrows(GlobalException.class, () -> {
      useCase.saveCategory(category);
    });
  }

  @Test
  void shouldThrowExceptionWhenCategoryDescriptionIsNullOrEmpty() {
    DomainCategory category = TestDataFactory.buildDomainCategory();
    category.setDescription(null); // O probar con ""

    Assertions.assertThrows(GlobalException.class, () -> {
      useCase.saveCategory(category);
    });
  }

  @Test
  void shouldThrowExceptionWhenCategoryNameExceedsMaxLength() {
    DomainCategory category = TestDataFactory.buildDomainCategory();
    category.setName("A".repeat(51)); // Supera los 50 caracteres

    Assertions.assertThrows(GlobalException.class, () -> {
      useCase.saveCategory(category);
    });
  }

  @Test
  void shouldThrowExceptionWhenCategoryDescriptionExceedsMaxLength() {
    DomainCategory category = TestDataFactory.buildDomainCategory();
    category.setDescription("A".repeat(91)); // Supera los 90 caracteres

    Assertions.assertThrows(GlobalException.class, () -> {
      useCase.saveCategory(category);
    });
  }

  @Test
  void shouldSaveCategorySuccessfully() throws IllegalAccessException {
    DomainCategory category = TestDataFactory.buildDomainCategory();
    when(categoryPersistencePort.findByName(Mockito.anyString()))
        .thenReturn(null);
    when(categoryPersistencePort.saveCategory(any(DomainCategory.class)))
        .thenReturn(category);

    DomainCategory result = useCase.saveCategory(category);

    assertNotNull(result);
    assertEquals(category.getName(), result.getName());
    assertEquals(category.getDescription(), result.getDescription());
  }

}


