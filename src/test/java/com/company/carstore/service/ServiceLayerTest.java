package com.company.carstore.service;

import com.company.carstore.model.Brand;
import com.company.carstore.model.CarType;
import com.company.carstore.model.Design;
import com.company.carstore.model.Year;
import com.company.carstore.repository.BrandRepository;
import com.company.carstore.repository.CarTypeRepository;
import com.company.carstore.repository.DesignRepository;
import com.company.carstore.repository.YearRepository;
import com.company.carstore.viewmodel.BrandViewModel;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class ServiceLayerTest {

    ServiceLayer service;
    BrandRepository brandRepository;
    CarTypeRepository carTypeRepository;
    YearRepository yearRepository;
    DesignRepository designRepository;

    @Before
    public void setUp() throws Exception {
        setUpBrandRepositoryMock();
        setUpCarTypeRepositoryMock();
        setUpYearRepositoryMock();
        setUpDesignRepositoryMock();

        service = new ServiceLayer(brandRepository, carTypeRepository, designRepository, yearRepository);

    }

    // Helper methods
    private void setUpBrandRepositoryMock() {
        brandRepository = mock(BrandRepository.class);
        Brand brand = new Brand();
        brand.setId(1);
        brand.setCarTypeId(45);
        brand.setYearId(10);
        brand.setTitle("Greatest Hits");
        brand.setListPrice(new BigDecimal("14.99"));
        brand.setReleaseDate(LocalDate.of(1999, 05, 15));

        Brand brand2 = new Brand();
        brand2.setCarTypeId(45);
        brand2.setYearId(10);
        brand2.setTitle("Greatest Hits");
        brand2.setListPrice(new BigDecimal("14.99"));
        brand2.setReleaseDate(LocalDate.of(1999, 05, 15));

        List<Brand> bList = new ArrayList<>();
        bList.add(brand);

        doReturn(brand).when(brandRepository).save(brand2);
        doReturn(Optional.of(brand)).when(brandRepository).findById(1);
        doReturn(bList).when(brandRepository).findAll();
    }

    private void setUpCarTypeRepositoryMock() {
        carTypeRepository = mock(CarTypeRepository.class);
        CarType carType = new CarType();
        carType.setId(45);
        carType.setSeat(6);
        carType.setName("The GOAT");

        CarType carType2 = new CarType();
        carType2.setSeat(4);
        carType2.setName("The GOAT");

        List cList = new ArrayList();
        cList.add(carType);

        doReturn(carType).when(carTypeRepository).save(carType2);
        doReturn(Optional.of(carType)).when(carTypeRepository).findById(45);
        doReturn(cList).when(carTypeRepository).findAll();
    }

    private void setUpYearRepositoryMock() {
        yearRepository = mock(YearRepository.class);
        Year year = new Year();
        year.setId(10);
        year.setName("Blue Note");

        Year year2 = new Year();
        year2.setName("Blue Note");


        List yList = new ArrayList<>();
        yList.add(year);

        doReturn(year).when(yearRepository).save(year2);
        doReturn(Optional.of(year)).when(yearRepository).findById(10);
        doReturn(yList).when(yearRepository).findAll();
    }

    private void setUpDesignRepositoryMock() {
        designRepository = mock(DesignRepository.class);
        Design design = new Design();
        design.setId(1);
        design.setBrandId(1);
        design.setSeries(180);

        Design design2 = new Design();
        design2.setBrandId(1);
        design2.setSeries(180);

        List dList = new ArrayList<>();
        dList.add(design);

        doReturn(design).when(designRepository).save(design2);
        doReturn(Optional.of(design)).when(designRepository).findById(1);
        doReturn(dList).when(designRepository).findAll();
        doReturn(dList).when(designRepository).findAllDesignsByBrandId(1);
    }

    @Test
    public void findAllBrands() {
        List<BrandViewModel> fromService = service.findAllBrands();

        assertEquals(1, fromService.size());
    }

    @Test
    public void shouldSaveCarType() {
        CarType carTypeToSave = new CarType();
        carTypeToSave.setSeat(4);
        carTypeToSave.setName("The GOAT");

        CarType expectedCarType = new CarType();
        expectedCarType.setSeat(4);
        expectedCarType.setName("The GOAT");
        expectedCarType.setId(45);

        CarType actualResult = service.saveCarType(carTypeToSave);

        assertEquals(expectedCarType, actualResult);
    }

    @Test
    public void shouldFindBrandAndBuildViewModelCorrectly() {
        BrandViewModel whatIExpect = new BrandViewModel();

        whatIExpect.setListPrice(new BigDecimal("14.99"));
        whatIExpect.setReleaseDate(LocalDate.of(1999, 05, 15));
        whatIExpect.setTitle("Greatest Hits");
        whatIExpect.setId(1);

        CarType carType = new CarType();
        carType.setSeat(4);
        carType.setName("The GOAT");
        carType.setId(45);
        whatIExpect.setCarType(carType);

        Year year = new Year();
        year.setName("Blue Note");
        year.setId(10);

        whatIExpect.setYear(year);

        Design design = new Design();
        design.setSeries(180);
        design.setName("Number 1 Hit!");
        design.setBrandId(1);
        design.setId(1);
        List<Design> dList = new ArrayList<>();
        dList.add(design);

        whatIExpect.setDesigns(dList);

        // Act
        BrandViewModel fromService = service.findBrand(1);

        // Assert
        assertEquals(whatIExpect, fromService);

    }

    @Test
    public void shouldFindYear() {
        Year expectedYear = new Year();
        expectedYear.setId(10);
        expectedYear.setName("Blue Note");

        Year actualResult = service.findYear(10);

        assertEquals(expectedYear, actualResult);
    }

    @Test
    public void shouldReturnNullIfYearNotFound() {
        Year actualResult = service.findYear(54321);

        assertNull(actualResult);
    }

    @Test
    public void shouldSaveYear() {
        // Arrange
        Year expectedOutput = new Year();
        expectedOutput.setId(10);
        expectedOutput.setName("Blue Note");

        Year year = new Year();
        year.setName("Blue Note");

        // Act
        Year actualOutput = service.saveYear(year);

        // Assert
        assertEquals(expectedOutput, actualOutput);
    }

}
