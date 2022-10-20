package com.company.carstore.controller;


import com.company.carstore.exception.NoRecordFoundException;
import com.company.carstore.model.Year;
import com.company.carstore.service.ServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class YearController {

    @Autowired
    private ServiceLayer serviceLayer;

    @RequestMapping(value="/year", method= RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Year> getAllYears() {
        return serviceLayer.findAllYears();
    }

    @RequestMapping(value="/year", method=RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Year createYear(@RequestBody Year year) {
        return serviceLayer.saveYear(year);
    }

    @RequestMapping(value="/year/{id}", method=RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Year getYearById(@PathVariable int id) {
        Year year = serviceLayer.findYear(id);
        if (year == null) {
            throw new NoRecordFoundException("Year with id " + id + " does not exist.");
        }
        return year;
    }
}
