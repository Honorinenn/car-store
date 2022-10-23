package com.company.carstore.controller;

import com.company.carstore.model.CarType;
import com.company.carstore.service.ServiceLayer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

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

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        inputCarType = new CarType(8, "audi", 4);
        outputCarType = new CarType(carTypeId, "Jackson 5", "@jackson5Insta", "@jackson5Twitter");
        inputCarTypeString = mapper.writeValueAsString(inputCarType);
        outputCarTypeString = mapper.writeValueAsString(outputCarType);
        allCarTypes = Arrays.asList(outputCarType);
        allCarTypesString = mapper.writeValueAsString(allCarTypes);

        when(serviceLayer.saveCarType(inputCarType)).thenReturn(outputCarType);
        when(serviceLayer.findAllCarTypes()).thenReturn(allCarTypes);
        when(serviceLayer.findCarType(carTypeId)).thenReturn(outputCarType);

    }


}
