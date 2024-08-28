package com.bootcamp_2024_1.emazon.infrastructure.adapter.inbound;


import static com.bootcamp_2024_1.emazon.TestDataFactory.builCategoryRequestDTO;
import static com.bootcamp_2024_1.emazon.TestDataFactory.builCategoryResponseDTO;

import com.bootcamp_2024_1.emazon.application.dto.CategoryRequestDTO;
import com.bootcamp_2024_1.emazon.application.dto.CategoryResponseDTO;
import com.bootcamp_2024_1.emazon.application.handler.CategoryHandler;
import com.bootcamp_2024_1.emazon.TestDataFactory;
import java.util.List;
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
    Mockito.when(categoryHandler.saveCategory(builCategoryRequestDTO()))
        .thenReturn(responseDTO);

    ResponseEntity<CategoryResponseDTO> result = controller.createCategory(
        builCategoryRequestDTO());

    Assertions.assertNotNull(result);
    Assertions.assertEquals(HttpStatus.CREATED, result.getStatusCode());
    Assertions.assertEquals(builCategoryResponseDTO(), result.getBody());
  }

  @Test
  void createCategoryThrowsExceptionTest() throws IllegalAccessException {

    Mockito.when(categoryHandler.saveCategory(Mockito.any(CategoryRequestDTO.class)))
        .thenThrow(new IllegalAccessException());

    ResponseEntity<CategoryResponseDTO> result = controller.createCategory(builCategoryRequestDTO());

    Assertions.assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    Assertions.assertNull(result.getBody());
  }


  @Test
  void getAllCategoriesTest() {
    List<CategoryResponseDTO> categories = TestDataFactory.buildCategoryResponseDTOList();

    Mockito.when(categoryHandler.getAllCategories()).thenReturn(categories);

    ResponseEntity<List<CategoryResponseDTO>> result = controller.getAllCategories();

    Assertions.assertNotNull(result);
    Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
    Assertions.assertEquals(categories, result.getBody());
  }

}
