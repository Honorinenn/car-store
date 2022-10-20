package com.company.carstore.controller;


import com.company.carstore.exception.NoRecordFoundException;
import com.company.carstore.model.CarType;
import com.company.carstore.service.ServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value="/carType", method=RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public CarType createCarType(@RequestBody CarType carType) {
        return serviceLayer.saveCarType(carType);
    }

    @RequestMapping(value="/carType/{id}", method=RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public CarType getCarTypeById(@PathVariable int id) {
        CarType carType = serviceLayer.findCarType(id);
        if (carType == null) {
            throw new NoRecordFoundException("Artist with id " + id + " does not exist.");
        }
        return carType;
    }



}
