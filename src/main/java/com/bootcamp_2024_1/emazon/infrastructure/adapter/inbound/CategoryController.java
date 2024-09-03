package com.bootcamp_2024_1.emazon.infrastructure.adapter.inbound;

import com.bootcamp_2024_1.emazon.application.dto.CategoryRequestDTO;
import com.bootcamp_2024_1.emazon.application.dto.CategoryResponseDTO;
import com.bootcamp_2024_1.emazon.application.handler.CategoryHandler;
import com.bootcamp_2024_1.emazon.application.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/categories")
@RequiredArgsConstructor
public class CategoryController {

  private final CategoryHandler categoryHandler;
  private final CategoryService categoryService;


  @PostMapping(path = "create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<CategoryResponseDTO> createCategory(
      @RequestBody CategoryRequestDTO categoryRequestDTO) {
    try {
      var response = categoryHandler.saveCategory(categoryRequestDTO);
      return new ResponseEntity<>(response, HttpStatus.CREATED);
    } catch (IllegalAccessException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
  }


  @GetMapping(path = "all", produces = MediaType.APPLICATION_JSON_VALUE)
  public Page<CategoryResponseDTO> getCategories(
      @RequestParam(name = "page", required = true) int page,
      @RequestParam(name = "size", required = true) int size,
      @RequestParam(name = "direction", required = false, defaultValue = "ASC") String direction
  ) {
    return categoryService.getCategoriesList(page, size, direction);
  }
}
