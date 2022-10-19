package com.company.carstore.repository;

import com.company.carstore.model.Design;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DesignRepository extends JpaRepository<Design, Integer> {
    List<Design> findAllDesignsByBrandId(int brandId);
}



