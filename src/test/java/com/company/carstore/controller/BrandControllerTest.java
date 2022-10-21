package com.company.carstore.controller;

import com.company.carstore.service.ServiceLayer;
import com.company.carstore.viewmodel.BrandViewModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

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

}
