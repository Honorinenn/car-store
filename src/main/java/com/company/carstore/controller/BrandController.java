package com.company.carstore.controller;

import com.company.carstore.service.ServiceLayer;
import com.company.carstore.viewmodel.BrandViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BrandController {

    @Autowired
    private ServiceLayer serviceLayer;

    @RequestMapping(value="/car", method= RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<BrandViewModel> getAllBrands() {
        return serviceLayer.findAllBrands();
    }

    @RequestMapping(value="/car", method=RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public BrandViewModel createBrand(@RequestBody BrandViewModel brandViewModel) {
        return serviceLayer.saveBrand(brandViewModel);
    }

}
