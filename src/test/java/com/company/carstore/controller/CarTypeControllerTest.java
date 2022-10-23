package com.company.carstore.controller;

import com.company.carstore.model.CarType;
import com.company.carstore.service.ServiceLayer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@WebMvcTest(CarTypeController.class)
public class CarTypeControllerTest {

    @MockBean
    ServiceLayer serviceLayer;

    private CarType inputCarType;
    private CarType outputCarType;
    private String inputCarTypeString;
    private String outputCarTypeString;

    private List<CarType> allCarTypes;
    private String allCarTypesString;
    private int carTypeId = 55;
    private int nonExistentCarTypeId = 999;

    private ObjectMapper mapper = new ObjectMapper();
}
