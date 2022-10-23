package com.company.carstore.controller;

import com.company.carstore.model.Year;
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

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        inputYear = new Year(14, "audi");
        outputYear = new Year(yearId,  "audi");
        inputYearString = mapper.writeValueAsString(inputYear);
        outputYearString = mapper.writeValueAsString(outputYear);
        allYears = Arrays.asList(outputYear);
        allYearsString = mapper.writeValueAsString(allYears);

        when(serviceLayer.saveYear(inputYear)).thenReturn(outputYear);
        when(serviceLayer.findAllYears()).thenReturn(allYears);
        when(serviceLayer.findYear(yearId)).thenReturn(outputYear);


    }


}
