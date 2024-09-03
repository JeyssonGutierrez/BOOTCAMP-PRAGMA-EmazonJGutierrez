package com.bootcamp_2024_1.emazon.infrastructure.adapter.inbound;


import static com.bootcamp_2024_1.emazon.TestDataFactory.buildCategoryRequestDTO;
import static com.bootcamp_2024_1.emazon.TestDataFactory.builCategoryResponseDTO;

import com.bootcamp_2024_1.emazon.application.dto.CategoryRequestDTO;
import com.bootcamp_2024_1.emazon.application.dto.CategoryResponseDTO;
import com.bootcamp_2024_1.emazon.application.handler.CategoryHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class CategoryControllerTest {

  @Mock
  CategoryHandler categoryHandler;

  @InjectMocks
  CategoryController controller;

  @Test
  void createCategoryTest() throws IllegalAccessException {

    CategoryResponseDTO responseDTO = builCategoryResponseDTO();

    // Cambiamos aquí para asegurar que cualquier CategoryRequestDTO sea "stubbed"
    Mockito.when(categoryHandler.saveCategory(Mockito.any(CategoryRequestDTO.class)))
        .thenReturn(responseDTO);

    // Ejecutamos el método del controlador
    ResponseEntity<CategoryResponseDTO> result = controller.createCategory(
        buildCategoryRequestDTO());

    // Verificaciones
    Assertions.assertNotNull(result);
    Assertions.assertEquals(HttpStatus.CREATED, result.getStatusCode());
    Assertions.assertEquals(builCategoryResponseDTO(), result.getBody());
  }

  @Test
  void createCategoryThrowsExceptionTest() throws IllegalAccessException {

    Mockito.when(categoryHandler.saveCategory(Mockito.any(CategoryRequestDTO.class)))
        .thenThrow(new IllegalAccessException());

    ResponseEntity<CategoryResponseDTO> result = controller.createCategory(buildCategoryRequestDTO());

    Assertions.assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    Assertions.assertNull(result.getBody());
  }



}
