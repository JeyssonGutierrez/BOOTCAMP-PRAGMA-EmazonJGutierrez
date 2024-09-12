package com.bootcamp_2024_1.emazon.infrastructure.adapter.inbound;

import com.bootcamp_2024_1.emazon.application.dto.BrandRequestDTO;
import com.bootcamp_2024_1.emazon.application.dto.BrandResponseDTO;
import com.bootcamp_2024_1.emazon.application.handler.BrandHandler;
import com.bootcamp_2024_1.emazon.application.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/brands")
@RequiredArgsConstructor
public class BrandController {

  private final BrandHandler brandHandler;
  private final BrandService brandService;


@PostMapping(path = "create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<BrandResponseDTO> createBrand(
    @RequestBody BrandRequestDTO brandRequestDTO) {
  var response = brandHandler.saveBrand(brandRequestDTO);
  return new ResponseEntity<>(response, HttpStatus.CREATED);
}

}