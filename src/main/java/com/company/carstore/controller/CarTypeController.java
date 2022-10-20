package com.company.carstore.controller;


import com.company.carstore.exception.InvalidRequestException;
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

    @RequestMapping(value="/carType/{id}", method=RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCarType(@PathVariable int id, @RequestBody CarType carType) {
        if (carType.getId() == 0) {
            carType.setId(id);
        }
        if (carType.getId() != id) {
            throw new InvalidRequestException("id in request body must match id in path");
        }
        serviceLayer.updateCarType(carType);
    }

    @RequestMapping(value="/carType/{id}", method=RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCarType(@PathVariable int id) {
        serviceLayer.removeCarType(id);
    }



}
