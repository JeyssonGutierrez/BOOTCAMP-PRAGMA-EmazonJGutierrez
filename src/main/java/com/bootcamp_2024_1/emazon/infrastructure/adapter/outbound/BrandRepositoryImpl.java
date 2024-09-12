package com.bootcamp_2024_1.emazon.infrastructure.adapter.outbound;

import com.bootcamp_2024_1.emazon.domain.model.DomainBrand;
import com.bootcamp_2024_1.emazon.domain.spi.BrandPersistencePort;
import com.bootcamp_2024_1.emazon.infrastructure.entity.BrandEntity;
import com.bootcamp_2024_1.emazon.infrastructure.mapper.BrandEntityMapper;
import com.bootcamp_2024_1.emazon.infrastructure.repository.BrandJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@AllArgsConstructor
@Repository
public class BrandRepositoryImpl implements BrandPersistencePort {

  private final BrandEntityMapper brandEntityMapper;
  private final BrandJpaRepository brandJpaRepository;
  @Override
  public DomainBrand saveBrand(DomainBrand domainBrand) {
    BrandEntity entity = brandEntityMapper.toEntity(domainBrand);
    BrandEntity savedEntity= brandJpaRepository.save(entity);
    return brandEntityMapper.toDomain(savedEntity);
  }

  @Override
  public DomainBrand findByName(String name) {
    return null;
  }
}
