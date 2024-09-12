package com.bootcamp_2024_1.emazon.infrastructure.repository;

import com.bootcamp_2024_1.emazon.infrastructure.entity.BrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandJpaRepository extends JpaRepository<BrandEntity, Long> {


}
