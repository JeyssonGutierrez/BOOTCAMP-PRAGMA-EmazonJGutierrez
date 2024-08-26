package com.bootcamp_2024_1.emazon.infrastructure.adapter.inbound;

import com.bootcamp_2024_1.emazon.application.dto.CategoryRequestDTO;
import com.bootcamp_2024_1.emazon.application.dto.CategoryResponseDTO;
import com.bootcamp_2024_1.emazon.application.handler.CategoryHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/categories")
public class CategoryController {

  private final CategoryHandler categoryHandler;

  public CategoryController(CategoryHandler categoryHandler) {
    this.categoryHandler = categoryHandler;
  }

  @PostMapping(path = "create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<CategoryResponseDTO> createCategory(
      @RequestBody CategoryRequestDTO categoryRequestDTO) {
    try {
      var response = categoryHandler.saveCategory(categoryRequestDTO);
      return new ResponseEntity<>(response, HttpStatus.CREATED);
    } catch (IllegalAccessException e) {
      // Manejar la excepción y devolver una respuesta adecuada
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
  }

  @GetMapping(path = "all", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<CategoryResponseDTO>> getAllCategories() {
    List<CategoryResponseDTO> categories = categoryHandler.getAllCategories();
    return ResponseEntity.ok(categories);
  }

}
