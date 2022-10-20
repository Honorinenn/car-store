package com.company.carstore.controller;


import com.company.carstore.model.CarType;
import com.company.carstore.service.ServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CarTypeController {
    @Autowired
    private ServiceLayer serviceLayer;

    @RequestMapping(value="/carType", method= RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<CarType> getAllCarTypes() {
        return serviceLayer.findAllCarTypes();
    }


}
