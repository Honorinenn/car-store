package com.company.carstore.controller;

import com.company.carstore.model.CarType;
import com.company.carstore.service.ServiceLayer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
        outputCarType = new CarType(carTypeId,  "@jackson5Insta", 4);
        inputCarTypeString = mapper.writeValueAsString(inputCarType);
        outputCarTypeString = mapper.writeValueAsString(outputCarType);
        allCarTypes = Arrays.asList(outputCarType);
        allCarTypesString = mapper.writeValueAsString(allCarTypes);

        when(serviceLayer.saveCarType(inputCarType)).thenReturn(outputCarType);
        when(serviceLayer.findAllCarTypes()).thenReturn(allCarTypes);
        when(serviceLayer.findCarType(carTypeId)).thenReturn(outputCarType);

    }

    @Test
    public void shouldCreateCarType() throws Exception {
        mockMvc.perform(post("/carType")
                        .content(inputCarTypeString)
                        .contentType(MediaType.APPLICATION_JSON)
                ).andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(outputCarTypeString));
    }

    @Test
    public void shouldGetAllCarTypes() throws Exception {
        mockMvc.perform(get("/carType"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(allCarTypesString));
    }

    @Test
    public void shouldGetCarTypeById() throws Exception {
        mockMvc.perform(get("/carType/" + carTypeId))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputCarTypeString));
    }


    @Test
    public void shouldReport404WhenFindCarTypeByInvalidId() throws Exception {
        mockMvc.perform(get("/carType/" + nonExistentCarTypeId))
                .andDo(print())
                .andExpect(status().isNotFound());
    }


    @Test
    public void shouldUpdateCarType() throws Exception {
        mockMvc.perform(put("/carType/" + carTypeId)
                        .content(outputCarTypeString)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldBeUnprocessableEntityWhenPutRequestContainsNonMatchingIds() throws Exception {
        mockMvc.perform(put("/carType/" + nonExistentCarTypeId)
                        .content(outputCarTypeString)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void shouldDeleteCarType() throws Exception {
        mockMvc.perform(delete("/carType/" + carTypeId))
                .andDo(print())
                .andExpect(status().isNoContent());
    }
}
