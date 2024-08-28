package com.bootcamp_2024_1.emazon.application.handler;

import com.bootcamp_2024_1.emazon.TestDataFactory;
import com.bootcamp_2024_1.emazon.application.dto.CategoryRequestDTO;
import com.bootcamp_2024_1.emazon.application.dto.CategoryResponseDTO;
import com.bootcamp_2024_1.emazon.application.mapper.CategoryRequestMapper;
import com.bootcamp_2024_1.emazon.application.mapper.CategoryResponseMapper;
import com.bootcamp_2024_1.emazon.domain.api.CategoryServicePort;
import com.bootcamp_2024_1.emazon.domain.model.DomainCategory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class CategoryHandlerImplTest {

  @Mock
  private CategoryServicePort categoryServicePort;

  @Mock
  private CategoryRequestMapper categoryRequestMapper;

  @Mock
  private CategoryResponseMapper categoryResponseMapper;

  @InjectMocks
  private CategoryHandlerImpl categoryHandler;

  @Test
  void saveCategoryTest() throws IllegalAccessException {
    // Arrange
    CategoryRequestDTO requestDTO = TestDataFactory.builCategoryRequestDTO();
    DomainCategory domainCategory = TestDataFactory.buildDomainCategory();
    CategoryResponseDTO responseDTO = TestDataFactory.builCategoryResponseDTO();

    Mockito.when(categoryRequestMapper.toModel(requestDTO)).thenReturn(domainCategory);
    Mockito.when(categoryResponseMapper.toDto(domainCategory)).thenReturn(responseDTO);

    // Act
    CategoryResponseDTO result = categoryHandler.saveCategory(requestDTO);

    // Assert
    assertNotNull(result);
    assertEquals(responseDTO, result);
    Mockito.verify(categoryServicePort).saveCategory(domainCategory);
  }

  @Test
  void getAllCategoriesTest() {
    // Arrange
    List<DomainCategory> domainCategories = List.of(TestDataFactory.buildDomainCategory());
    List<CategoryResponseDTO> responseDTOs = TestDataFactory.buildCategoryResponseDTOList();

    Mockito.when(categoryServicePort.findAll()).thenReturn(domainCategories);
    Mockito.when(categoryResponseMapper.toDtoList(domainCategories)).thenReturn(responseDTOs);

    // Act
    List<CategoryResponseDTO> result = categoryHandler.getAllCategories();

    // Assert
    assertNotNull(result);
    assertEquals(responseDTOs, result);
    Mockito.verify(categoryServicePort).findAll();
  }

  @Test
  void saveCategoryInCategoryTest() {
    // This method is not implemented in the actual class, so you can either implement it or skip testing it for now.
  }

  @Test
  void getAllCategoryFromCategoryTest() {
    // Act
    List<CategoryResponseDTO> result = categoryHandler.getAllCategoryFromCategory();

    // Assert
    assertNotNull(result);
    assertTrue(result.isEmpty());
  }
}
