package com.bootcamp_2024_1.emazon.domain.useCase;


import com.bootcamp_2024_1.emazon.domain.api.CategoryServicePort;
import com.bootcamp_2024_1.emazon.domain.exceptions.Util;
import com.bootcamp_2024_1.emazon.domain.model.DomainCategory;
import com.bootcamp_2024_1.emazon.domain.spi.CategoryPersistencePort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class CategoryUseCase implements CategoryServicePort {

  private final CategoryPersistencePort categoryPersistencePort;

  public CategoryUseCase(CategoryPersistencePort categoryPersistencePort) {
    this.categoryPersistencePort = categoryPersistencePort;
  }

  @Override
  public DomainCategory saveCategory(DomainCategory domainCategory) throws IllegalAccessException {
    var badRequestCode = HttpStatus.BAD_REQUEST.value();
    // Validaciones
    DomainCategory existingCategories = categoryPersistencePort.findByName(
        domainCategory.getName());

    if (existingCategories != null) {
      Util.throwException(badRequestCode, String.format("El nombre %s, ya existe.",existingCategories.getName()));
    }
    if (domainCategory.getName() == null || domainCategory.getName().trim().isEmpty()) {
      Util.throwException(badRequestCode, "El nombre de la categoría es obligatorio.");
    }
    if (domainCategory.getDescription() == null || domainCategory.getDescription().trim()
        .isEmpty()) {
      Util.throwException(badRequestCode, "La descripción de la Categoría es obligatoria.");
    }

    if (domainCategory.getName().length() > 50) {
      Util.throwException(badRequestCode, "El campo Nombre no puede superar 50 caracteres.");
    }

    if (domainCategory.getDescription().length() > 90) {
      Util.throwException(badRequestCode, "El campo descripción no puede superar 90 caracteres.");
    }

    return categoryPersistencePort.saveCategory(domainCategory);
  }

  @Override
  public DomainCategory findByName(String name) {
    return null;
  }

  @Override
  public void saveCategoryInCategory(DomainCategory domainCategory) {
    // Implementación si es necesaria
  }

}
