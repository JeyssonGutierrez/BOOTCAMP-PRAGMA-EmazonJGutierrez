package com.bootcamp_2024_1.emazon.infrastructure.repository;

import com.bootcamp_2024_1.emazon.infrastructure.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryJpaRepository extends JpaRepository<CategoryEntity, Long> {

  CategoryEntity findByName (String name);



}
