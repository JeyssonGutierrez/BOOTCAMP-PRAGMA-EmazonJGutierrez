package com.bootcamp_2024_1.emazon.domain.useCase;

import com.bootcamp_2024_1.emazon.domain.model.DomainCategory;
import com.bootcamp_2024_1.emazon.domain.spi.CategoryPersistencePort;
import com.bootcamp_2024_1.emazon.TestDataFactory;
import com.bootcamp_2024_1.emazon.domain.exceptions.GlobalException;
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
  CategoryPersistencePort categoryPersistencePort;

  @InjectMocks
  CategoryUseCase useCase;

  @Test
  void saveCategoryTest() throws IllegalAccessException {
    DomainCategory category = TestDataFactory.buildDomainCategory();

    // Configura el mock para que devuelva la categoría cuando se guarde
    Mockito.when(categoryPersistencePort.save(Mockito.any(DomainCategory.class)))
        .thenReturn(category);

    DomainCategory result = useCase.saveCategory(category);

    // Verifica que la categoría se haya guardado correctamente
    Assertions.assertNotNull(result);
    Assertions.assertEquals("Pintura", result.getName());
    Assertions.assertEquals("test Pintura", result.getDescription());
  }

  @Test
  void saveCategoryThrowsExceptionTest() {
    DomainCategory category = TestDataFactory.buildDomainCategory();

    // Simula que la categoría ya existe
    Mockito.when(categoryPersistencePort.findByName("Pintura"))
        .thenReturn(category);

    // Verifica que se lance una excepción al intentar guardar una categoría existente
    Assertions.assertThrows(GlobalException.class, () -> {
      useCase.saveCategory(category);
    });
  }

  @Test
  void findAllCategoriesTest() {
    List<DomainCategory> categories = TestDataFactory.buildCategoryEntityList().stream()
        .map(entity -> TestDataFactory.buildDomainCategory())
        .toList();

    // Simula la devolución de la lista de categorías
    Mockito.when(categoryPersistencePort.findAll())
        .thenReturn(categories);

    List<DomainCategory> result = useCase.findAll();

    // Verifica que se devuelvan todas las categorías
    Assertions.assertNotNull(result);
    Assertions.assertEquals(2, result.size());
  }

  @Test
  void getAllCategoriesTest() {
    List<DomainCategory> categories = TestDataFactory.buildCategoryEntityList().stream()
        .map(entity -> TestDataFactory.buildDomainCategory())
        .toList();

    // Simula la devolución de la lista de categorías
    Mockito.when(categoryPersistencePort.findAll())
        .thenReturn(categories);

    List<DomainCategory> result = useCase.getAllCategory();

    // Verifica que se devuelvan todas las categorías
    Assertions.assertNotNull(result);
    Assertions.assertEquals(2, result.size());
  }

  @Test
  void shouldThrowExceptionWhenCategoryNameExists() {
    // Arrange
    DomainCategory category = TestDataFactory.buildDomainCategory();
    Mockito.when(categoryPersistencePort.findByName(Mockito.anyString()))
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
    Mockito.when(categoryPersistencePort.findByName(Mockito.anyString()))
        .thenReturn(null);
    Mockito.when(categoryPersistencePort.save(Mockito.any(DomainCategory.class)))
        .thenReturn(category);

    DomainCategory result = useCase.saveCategory(category);

    Assertions.assertNotNull(result);
    Assertions.assertEquals(category.getName(), result.getName());
    Assertions.assertEquals(category.getDescription(), result.getDescription());
  }
}
