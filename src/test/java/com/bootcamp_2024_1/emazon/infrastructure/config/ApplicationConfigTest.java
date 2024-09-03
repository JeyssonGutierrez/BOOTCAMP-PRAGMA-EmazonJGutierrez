package com.bootcamp_2024_1.emazon.infrastructure.config;

import com.bootcamp_2024_1.emazon.application.handler.CategoryHandler;
import com.bootcamp_2024_1.emazon.application.handler.CategoryHandlerImpl;
import com.bootcamp_2024_1.emazon.application.mapper.CategoryRequestMapper;
import com.bootcamp_2024_1.emazon.application.mapper.CategoryResponseMapper;
import com.bootcamp_2024_1.emazon.domain.api.CategoryServicePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ApplicationConfigTest {

  @Mock
  private CategoryServicePort categoryServicePort;

  @Mock
  private CategoryRequestMapper categoryRequestMapper;

  @Mock
  private CategoryResponseMapper categoryResponseMapper;

  @InjectMocks
  private ApplicationConfig applicationConfig;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void testCategoryHandlerBean() {
    CategoryHandler categoryHandler = applicationConfig.categoryHandler(categoryServicePort, categoryRequestMapper, categoryResponseMapper);
    assertNotNull(categoryHandler);
    assertTrue(categoryHandler instanceof CategoryHandlerImpl);
  }
}
