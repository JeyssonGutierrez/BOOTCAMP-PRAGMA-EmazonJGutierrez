package com.bootcamp_2024_1.emazon.domain.generic;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GenericServicePort<T, R> {
  Page<R> getAll(Pageable pageable);
}
