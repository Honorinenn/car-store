package com.company.carstore.controller;

import com.company.carstore.model.CarType;
import com.company.carstore.model.Design;
import com.company.carstore.model.Year;
import com.company.carstore.service.ServiceLayer;
import com.company.carstore.viewmodel.BrandViewModel;
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


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(BrandController.class)
public class BrandControllerTest {
    @MockBean
    ServiceLayer serviceLayer;

    private BrandViewModel inputBrandViewModel;
    private BrandViewModel outputBrandViewModel;
    private String inputBrandViewModelString;
    private String outputBrandViewModelString;

    private List<BrandViewModel> allBrandViewModels;
    private String allBrandViewModelsString;
    private int brandId = 18;
    private int nonExistentBrandId = 601;

    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        CarType inputCarType = new CarType(25, "@watchSZ", 4);
        Year inputYear = new Year(9,"www.goodonyasmusic.net");
        List<Design> inputDesigns = Arrays.asList(new Design("Songsville", 200));
        inputBrandViewModel = new BrandViewModel("My Album", inputCarType, LocalDate.of(2022, 05, 18), inputYear, new BigDecimal("10.95"), inputDesigns);

        List<Design> outputDesigns = Arrays.asList(new Design(831, "Songsville", 200));
        outputBrandViewModel = new BrandViewModel(brandId, "My Album", inputCarType, LocalDate.of(2022, 05, 18), inputYear, new BigDecimal("10.95"), outputDesigns);
        inputBrandViewModelString = mapper.writeValueAsString(inputBrandViewModel);
        outputBrandViewModelString = mapper.writeValueAsString(outputBrandViewModel);
        allBrandViewModels = Arrays.asList(outputBrandViewModel);
        allBrandViewModelsString = mapper.writeValueAsString(allBrandViewModels);

        when(serviceLayer.saveBrand(inputBrandViewModel)).thenReturn(outputBrandViewModel);
        when(serviceLayer.findAllBrands()).thenReturn(allBrandViewModels);
        when(serviceLayer.findBrand(brandId)).thenReturn(outputBrandViewModel);
    }

    @Test
    public void shouldCreateBrand() throws Exception {
        mockMvc.perform(post("/car")
                        .content(inputBrandViewModelString)
                        .contentType(MediaType.APPLICATION_JSON)
                ).andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(outputBrandViewModelString));
    }

    @Test
    public void shouldGetAllBrands() throws Exception {
        mockMvc.perform(get("/car"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(allBrandViewModelsString));
    }

    @Test
    public void shouldGetBrandById() throws Exception {
        mockMvc.perform(get("/car/" + brandId))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputBrandViewModelString));
    }

    @Test
    public void shouldReport404WhenFindBrandByInvalidId() throws Exception {
        mockMvc.perform(get("/car/" + nonExistentBrandId))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldUpdateBrand() throws Exception {
        mockMvc.perform(put("/car/" + brandId)
                        .content(outputBrandViewModelString)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldBeUnprocessableEntityWhenPutRequestContainsNonMatchingIds() throws Exception {
        mockMvc.perform(put("/car/" + nonExistentBrandId)
                        .content(outputBrandViewModelString)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void shouldDeleteCarType() throws Exception {
        mockMvc.perform(delete("/car/" + brandId))
                .andDo(print())
                .andExpect(status().isNoContent());
    }


}
