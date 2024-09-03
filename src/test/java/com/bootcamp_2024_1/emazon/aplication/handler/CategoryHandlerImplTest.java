package com.bootcamp_2024_1.emazon.aplication.handler;

import com.bootcamp_2024_1.emazon.TestDataFactory;
import com.bootcamp_2024_1.emazon.application.dto.CategoryRequestDTO;
import com.bootcamp_2024_1.emazon.application.dto.CategoryResponseDTO;
import com.bootcamp_2024_1.emazon.application.handler.CategoryHandlerImpl;
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

@ExtendWith(MockitoExtension.class)
 class CategoryHandlerImplTest {

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
    CategoryRequestDTO requestDTO = TestDataFactory.buildCategoryRequestDTO();
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

}
