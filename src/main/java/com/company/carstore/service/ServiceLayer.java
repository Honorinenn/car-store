package com.company.carstore.service;

import com.company.carstore.model.Brand;
import com.company.carstore.model.Year;
import com.company.carstore.repository.BrandRepository;
import com.company.carstore.repository.CarTypeRepository;
import com.company.carstore.repository.DesignRepository;
import com.company.carstore.repository.YearRepository;
import com.company.carstore.viewmodel.BrandViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class ServiceLayer {

    private BrandRepository brandRepository;
    private CarTypeRepository carTypeRepository;
    private DesignRepository designRepository;
    private YearRepository yearRepository;

    @Autowired
    public ServiceLayer(BrandRepository brandRepository, CarTypeRepository carTypeRepository, DesignRepository designRepository, YearRepository yearRepository) {
        this.brandRepository = brandRepository;
        this.carTypeRepository = carTypeRepository;
        this.designRepository = designRepository;
        this.yearRepository = yearRepository;
    }

    @Transactional
    public BrandViewModel saveBrand(BrandViewModel viewModel) {

        // Persist Album
        Brand b = new Brand();
        b.setTitle(viewModel.getTitle());
        b.setReleaseDate(viewModel.getReleaseDate());
        b.setListPrice(viewModel.getListPrice());
        b.setCarTypeId(viewModel.getCarType().getId());
        b.setYearId(viewModel.getYear().getId());
        b = brandRepository.save(b);
        viewModel.setId(b.getId());

        // Add Brand Id to Track and Persist Tracks
        List<Year> years = viewModel.getYears();

        years.stream()
                .forEach(t ->
                {
                    t.setBrandId(viewModel.getId());
                    yearRepository.save(t);
                });

        years = yearRepository.findAllYearsByBrandId(viewModel.getId());
        viewModel.setYears(years);

        return viewModel
    }



}
