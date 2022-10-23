package com.company.carstore.repository;

import com.company.carstore.model.Brand;
import com.company.carstore.model.CarType;
import com.company.carstore.model.Year;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class BrandRepositoryTest {

    @Autowired
    DesignRepository designRepository;
    @Autowired
    BrandRepository brandRepository;
    @Autowired
    CarTypeRepository carTypeRepository;
    @Autowired
    YearRepository yearRepository;

    @Before
    public void setUp() throws Exception {

        designRepository.deleteAll();
        brandRepository.deleteAll();
        carTypeRepository.deleteAll();
        yearRepository.deleteAll();
    }

    @Test
    public void addGetDeleteBrand() {

        // Need to create a year and a CarType first
        Year year = new Year();
        year.setName("A&M");
        year = yearRepository.save(year);

        CarType carType = new CarType();
        carType.setName("audi");
        carType.setSeat(4);
        carType = carTypeRepository.save(carType);

        Brand brand = new Brand();
        brand.setTitle("Greatest Hits");
        brand.setCarTypeId(carType.getId());
        brand.setYearId(year.getId());
        brand.setReleaseDate(LocalDate.of(2010, 1, 5));
        brand.setListPrice(new BigDecimal("21.95"));

        brand = brandRepository.save(brand);

        Optional<Brand> brand1 = brandRepository.findById(brand.getId());

        assertEquals(brand1.get(), brand);

        brandRepository.deleteById(brand.getId());

        brand1 = brandRepository.findById(brand.getId());

        assertFalse(brand1.isPresent());

    }

    @Test(expected  = DataIntegrityViolationException.class)
    public void addWithRefIntegrityException() {

        Brand brand = new Brand();
        brand.setTitle("Greatest Hits");
        brand.setCarTypeId(54);
        brand.setYearId(91);
        brand.setReleaseDate(LocalDate.of(2010, 1, 5));
        brand.setListPrice(new BigDecimal("21.95"));

        brand = brandRepository.save(brand);

    }

    @Test
    public void getAllBrands() {

        // Need to create a Year and a CarType first
        Year year = new Year();
        year.setName("A&M");
        year = yearRepository.save(year);

        CarType carType = new CarType();
        carType.setName("Rock Start");
        carType.setSeat(4);
        carType = carTypeRepository.save(carType);

        Brand brand = new Brand();
        brand.setTitle("Greatest Hits");
        brand.setCarTypeId(carType.getId());
        brand.setYearId(year.getId());
        brand.setReleaseDate(LocalDate.of(2010, 1, 5));
        brand.setListPrice(new BigDecimal("21.95"));

        brand = brandRepository.save(brand);

        brand = new Brand();
        brand.setTitle("Leftovers");
        brand.setCarTypeId(carType.getId());
        brand.setYearId(year.getId());
        brand.setReleaseDate(LocalDate.of(2012, 4, 5));
        brand.setListPrice(new BigDecimal("18.95"));

        brand = brandRepository.save(brand);

        List<Brand> aList = brandRepository.findAll();

        assertEquals(aList.size(), 2);

    }

    @Test
    public void updateBrand() {

        Year year = new Year();
        year.setName("A&M");
        year = yearRepository.save(year);

        CarType carType = new CarType();
        carType.setName("Rock Start");
        carType.setSeat(4);

        carType = carTypeRepository.save(carType);

        Brand brand = new Brand();
        brand.setTitle("Greatest Hits");
        brand.setCarTypeId(carType.getId());
        brand.setYearId(year.getId());
        brand.setReleaseDate(LocalDate.of(2010, 1, 1));
        brand.setListPrice(new BigDecimal("21.95"));

        brand = brandRepository.save(brand);

        brand.setTitle("NEW TITLE");
        brand.setReleaseDate(LocalDate.of(2000, 7, 7));
        brand.setListPrice(new BigDecimal("15.68"));

        brandRepository.save(brand);

        Optional<Brand> brand1 = brandRepository.findById(brand.getId());
        assertEquals(brand1.get(), brand);

    }


}
