package com.bootcamp_2024_1.emazon.domain.useCase;


import com.bootcamp_2024_1.emazon.domain.api.CategoryServicePort;
import com.bootcamp_2024_1.emazon.domain.constants.DomainEnum;
import com.bootcamp_2024_1.emazon.domain.exceptions.Util;
import com.bootcamp_2024_1.emazon.domain.model.DomainCategory;
import com.bootcamp_2024_1.emazon.domain.spi.CategoryPersistencePort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CategoryUseCase implements CategoryServicePort {

  private final CategoryPersistencePort categoryPersistencePort;

  public CategoryUseCase(CategoryPersistencePort categoryPersistencePort) {
    this.categoryPersistencePort = categoryPersistencePort;
  }

  @Override
  public DomainCategory saveCategory(DomainCategory domainCategory) {

    DomainCategory existingCategories = categoryPersistencePort.findByName(domainCategory.getName());

    if (existingCategories != null) {
      Util.throwException(
          String.format(DomainEnum.NAME_EXIST.getMessage(),existingCategories.getName()));
    }
    validations(domainCategory);

    return categoryPersistencePort.saveCategory(domainCategory);
  }

  @Override
  public DomainCategory findByName(String name) {
    return null;
  }

  @Override
  public Page<DomainCategory> getAll(Pageable pageable) {
    return null;
  }

  private void validField(String field, String messageMandatory) {
    if (field == null || field.trim().isEmpty()) {
      Util.throwException(messageMandatory);
    }
  }

  private void validLimit(String field, int size, String messageLimit) {
    if (field.length() > size) {
      Util.throwException(String.format(messageLimit, size));
    }
  }

  private void validations(DomainCategory domainCategory) {
    Integer sizeName = Integer.valueOf(DomainEnum.SIZE_NAME.getMessage());
    Integer sizeDescription = Integer.valueOf(DomainEnum.SIZE_DESCRIPTION_CATEGORY.getMessage());

    validField(domainCategory.getName(), DomainEnum.NAME_MANDATORY.getMessage());
    validField(domainCategory.getDescription(), DomainEnum.DESCRIPTION_MANDATORY.getMessage());
    validLimit(domainCategory.getName(), sizeName, DomainEnum.NAME_LIMIT.getMessage());
    validLimit(domainCategory.getDescription(), sizeDescription,
        DomainEnum.DESCRIPTION_LIMIT.getMessage());
  }
}
