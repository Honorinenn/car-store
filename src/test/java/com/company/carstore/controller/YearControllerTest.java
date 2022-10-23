package com.company.carstore.controller;

import com.company.carstore.model.Year;
import com.company.carstore.service.ServiceLayer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@WebMvcTest(YearController.class)
public class YearControllerTest {

    @MockBean
    ServiceLayer serviceLayer;

    private Year inputYear;
    private Year outputYear;
    private String inputYearString;
    private String outputYearString;

    private List<Year> allYears;
    private String allYearsString;
    private int yearId = 14;
    private int nonExistentYearId = 511;

    private ObjectMapper mapper = new ObjectMapper();

    


}
