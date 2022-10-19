package com.company.carstore.repository;

import com.company.carstore.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarTypeRepository extends JpaRepository<Brand, Integer> {
}


