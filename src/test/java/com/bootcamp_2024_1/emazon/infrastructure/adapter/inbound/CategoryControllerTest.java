package com.bootcamp_2024_1.emazon.infrastructure.adapter.inbound;


import static com.bootcamp_2024_1.emazon.TestDataFactory.buildCategoryRequestDTO;
import static com.bootcamp_2024_1.emazon.TestDataFactory.buildCategoryResponseDTO;


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
  void createCategoryTest() {

    CategoryResponseDTO responseDTO = buildCategoryResponseDTO();

    // Simulación del comportamiento del handler con Mockito
    Mockito.when(categoryHandler.saveCategory(Mockito.any(CategoryRequestDTO.class)))
        .thenReturn(responseDTO);

    // Ejecutamos el método del controlador
    ResponseEntity<CategoryResponseDTO> result = controller.createCategory(
        buildCategoryRequestDTO());

    // Verificaciones
    Assertions.assertNotNull(result);
    Assertions.assertEquals(HttpStatus.CREATED, result.getStatusCode());

    // Verificamos que el cuerpo de la respuesta es igual al objeto esperado
    Assertions.assertEquals(responseDTO, result.getBody());
  }


}
