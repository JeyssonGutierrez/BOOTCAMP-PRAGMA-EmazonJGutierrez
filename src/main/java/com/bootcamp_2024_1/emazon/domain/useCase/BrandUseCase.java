package com.bootcamp_2024_1.emazon.domain.useCase;


import com.bootcamp_2024_1.emazon.domain.api.BrandServicePort;
import com.bootcamp_2024_1.emazon.domain.constants.DomainEnum;
import com.bootcamp_2024_1.emazon.domain.exceptions.GlobalException;
import com.bootcamp_2024_1.emazon.domain.exceptions.Util;
import com.bootcamp_2024_1.emazon.domain.model.DomainBrand;
import com.bootcamp_2024_1.emazon.domain.spi.BrandPersistencePort;
import org.springframework.stereotype.Service;

@Service
public class BrandUseCase implements BrandServicePort {

  private final BrandPersistencePort brandPersistencePort;

  public BrandUseCase(BrandPersistencePort brandPersistencePort) {
    this.brandPersistencePort = brandPersistencePort;
  }

  @Override
  public DomainBrand saveBrand(DomainBrand domainBrand) throws GlobalException {

    DomainBrand existingBrands = brandPersistencePort.findByName(domainBrand.getName());

    if (existingBrands != null) {
      Util.throwException(
          String.format(DomainEnum.NAME_EXIST.getMessage(), existingBrands.getName()));
    }
    validations(domainBrand);

    return brandPersistencePort.saveBrand(domainBrand);
  }

  @Override
  public DomainBrand findByName(String name) {
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

  private void validations(DomainBrand domainBrand) {
    Integer sizeName = Integer.valueOf(DomainEnum.SIZE_NAME.getMessage());
    Integer sizeDescription = Integer.valueOf(DomainEnum.SIZE_DESCRIPTION_BRANDS.getMessage());

    validField(domainBrand.getName(), DomainEnum.NAME_MANDATORY.getMessage());
    validField(domainBrand.getDescription(), DomainEnum.DESCRIPTION_MANDATORY.getMessage());
    validLimit(domainBrand.getName(), sizeName, DomainEnum.NAME_LIMIT.getMessage());
    validLimit(domainBrand.getDescription(), sizeDescription,
        DomainEnum.DESCRIPTION_LIMIT.getMessage());
  }
}
