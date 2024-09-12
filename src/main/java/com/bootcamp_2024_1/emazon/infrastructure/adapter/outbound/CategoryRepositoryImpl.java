  package com.bootcamp_2024_1.emazon.infrastructure.adapter.outbound;

  import com.bootcamp_2024_1.emazon.domain.model.DomainCategory;
  import com.bootcamp_2024_1.emazon.infrastructure.entity.CategoryEntity;
  import com.bootcamp_2024_1.emazon.domain.spi.CategoryPersistencePort;
  import com.bootcamp_2024_1.emazon.infrastructure.repository.CategoryJpaRepository;
  import com.bootcamp_2024_1.emazon.infrastructure.mapper.CategoryEntityMapper;

  import lombok.AllArgsConstructor;
  import org.springframework.data.domain.Page;
  import org.springframework.data.domain.PageRequest;
  import org.springframework.data.domain.Pageable;
  import org.springframework.stereotype.Repository;
  import org.springframework.data.domain.Sort;

  @AllArgsConstructor
  @Repository
  public class CategoryRepositoryImpl implements CategoryPersistencePort {

    private final CategoryJpaRepository categoryJpaRepository;
    private final CategoryEntityMapper categoryEntityMapper;


    @Override
    public DomainCategory saveCategory(
        DomainCategory domainCategory) {
      // Convierte el modelo de dominio a entidad de infraestructura
      CategoryEntity entity = categoryEntityMapper.toEntity(domainCategory);
      CategoryEntity savedEntity = categoryJpaRepository.save(entity);
      // Convierte la entidad de infraestructura a modelo de dominio
      return categoryEntityMapper.toDomain(savedEntity);
    }

    @Override
    public DomainCategory findByName(String name) {
      return categoryEntityMapper.toDomain(categoryJpaRepository.findByName(name));
    }

    @Override
    public Page<DomainCategory> getCategoriesList(int page, int size, String direction) {
      Sort sort;
      if (direction.equalsIgnoreCase("DESC")) {
        sort = Sort.by("name").descending();
      } else {
        sort = Sort.by("name").ascending();
      }
      Pageable pageable = PageRequest.of(page, size, sort);
      Page<CategoryEntity> categoriesPage = categoryJpaRepository.findAll(pageable);
      return categoriesPage.map(categoryEntityMapper::toDomain);
    }


  }
