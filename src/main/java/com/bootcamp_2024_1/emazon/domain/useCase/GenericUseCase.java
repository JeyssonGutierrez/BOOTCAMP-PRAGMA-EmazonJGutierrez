package com.bootcamp_2024_1.emazon.domain.useCase;

import com.bootcamp_2024_1.emazon.domain.generic.GenericPersistencePort;
import com.bootcamp_2024_1.emazon.domain.generic.GenericServicePort;
import java.util.function.Function;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class GenericUseCase<T, R> implements GenericServicePort<T, R> {

  private final GenericPersistencePort<T> genericPersistencePort;
  private final Function<T, R> mapper;  // Función para el mapeo específico

  public GenericUseCase(GenericPersistencePort<T> genericPersistencePort, Function<T, R> mapper) {
    this.genericPersistencePort = genericPersistencePort;
    this.mapper = mapper;
  }

  @Override
  public Page<R> getAll(Pageable pageable) {
    Page<T> entitiesPage = genericPersistencePort.getAll(pageable);
    return entitiesPage.map(mapper);  // Mapea las entidades a DTOs
  }
}