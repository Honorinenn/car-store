package com.company.carstore.repository;

import com.company.carstore.model.CarType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface BrandRepository extends JpaRepository<CarType, Integer> {
}

