package com.company.carstore.controller;

import com.company.carstore.service.ServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BrandController {

    @Autowired
    private ServiceLayer serviceLayer;

}
