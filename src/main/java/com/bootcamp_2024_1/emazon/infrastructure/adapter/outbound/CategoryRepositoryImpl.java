package com.bootcamp_2024_1.emazon.infrastructure.adapter.outbound;

import com.bootcamp_2024_1.emazon.domain.model.DomainCategory;
import com.bootcamp_2024_1.emazon.infrastructure.entity.CategoryEntity;
import com.bootcamp_2024_1.emazon.domain.spi.CategoryPersistencePort;
import com.bootcamp_2024_1.emazon.infrastructure.repository.CategoryJpaRepository;
import com.bootcamp_2024_1.emazon.infrastructure.mapper.CategoryEntityMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class CategoryRepositoryImpl implements CategoryPersistencePort {

  private final CategoryJpaRepository jpaRepository;
  private final CategoryEntityMapper mapper;

  public CategoryRepositoryImpl(CategoryJpaRepository jpaRepository, CategoryEntityMapper mapper) {
    this.jpaRepository = jpaRepository;
    this.mapper = mapper;
  }

  @Override
  public DomainCategory save(
      DomainCategory domainCategory) {
    // Convierte el modelo de dominio a entidad de infraestructura
    CategoryEntity entity = mapper.toEntity(domainCategory);
    CategoryEntity savedEntity = jpaRepository.save(entity);
    // Convierte la entidad de infraestructura a modelo de dominio
    return mapper.toDomain(savedEntity);
  }

  @Override
  public List<DomainCategory> findAll() {
    return jpaRepository.findAll().stream()
        .map(mapper::toDomain) // Convierte cada entidad de infraestructura a modelo de dominio
        .collect(Collectors.toList());
  }

  @Override
  public DomainCategory findByName(String name) {
    return mapper.toDomain(jpaRepository.findByName(name));
  }
}
