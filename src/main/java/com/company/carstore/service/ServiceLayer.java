package com.company.carstore.service;

import com.company.carstore.repository.BrandRepository;
import com.company.carstore.repository.CarTypeRepository;
import com.company.carstore.repository.ModelRepository;
import com.company.carstore.repository.YearRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServiceLayer {

    private BrandRepository brandRepository;
    private CarTypeRepository carTypeRepository;
    private ModelRepository modelRepository;
    private YearRepository yearRepository;

    @Autowired
    public ServiceLayer(BrandRepository brandRepository, CarTypeRepository carTypeRepository, ModelRepository modelRepository, YearRepository yearRepository) {
        this.brandRepository = brandRepository;
        this.carTypeRepository = carTypeRepository;
        this.modelRepository = modelRepository;
        this.yearRepository = yearRepository;
    }



}
