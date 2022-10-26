package com.company.carstore.repository;

import com.company.carstore.model.Brand;
import com.company.carstore.model.CarType;
import com.company.carstore.model.Design;
import com.company.carstore.model.Year;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class DesignRepositoryTest {
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
    public void addGetDeleteDesign() {

        // Need to create a Year, CarType, and Brand first
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

        Design design = new Design();
        design.setName("The Big Hit");
        design.setSeries(180);
        design.setBrandId(brand.getId());
        design = designRepository.save(design);

        Optional<Design> design1 = designRepository.findById(design.getId());

        assertEquals(design1.get(), design);

        designRepository.deleteById(design.getId());

        design1 = designRepository.findById(design.getId());

        assertFalse(design1.isPresent());
    }

    @Test
    public void updateDesign() {

        // Need to create a Year, CarType, and Brand first
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

        Design design = new Design();
        design.setName("audi");
        design.setSeries(180);
        design.setBrandId(brand.getId());
        design = designRepository.save(design);

        design.setName("New Name");
        design.setSeries(12);

        designRepository.save(design);

        Optional<Design> design1 = designRepository.findById(design.getId());

        assertEquals(design1.get(), design);

    }

    @Test
    public void getAllDesigns() {

        // Need to create a Year, CarType, and Brand first
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

        Brand brand1 = new Brand();
        brand1.setTitle("Lesser Hits");
        brand1.setCarTypeId(carType.getId());
        brand1.setYearId(year.getId());
        brand1.setReleaseDate(LocalDate.of(2012, 6, 25));
        brand1.setListPrice(new BigDecimal("9.95"));

        brand1 = brandRepository.save(brand1);


        Design design = new Design();
        design.setName("The Big Hit");
        design.setSeries(180);
        design.setBrandId(brand.getId());
        design = designRepository.save(design);

        design = new Design();
        design.setName("Just A Song");
        design.setSeries(120);
        design.setBrandId(brand1.getId());
        design = designRepository.save(design);

        List<Design> dList = designRepository.findAll();

        assertEquals(dList.size(), 2);

    }

    @Test
    public void getDesignsByBrand() {

        // Need to create a Year, CarType, and Brand first
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

        Brand brand1 = new Brand();
        brand1.setTitle("Lesser Hits");
        brand1.setCarTypeId(carType.getId());
        brand1.setYearId(year.getId());
        brand1.setReleaseDate(LocalDate.of(2012, 6, 25));
        brand1.setListPrice(new BigDecimal("9.95"));

        brand1 = brandRepository.save(brand1);


        Design design = new Design();
        design.setName("The Big Hit");
        design.setSeries(180);
        design.setBrandId(brand.getId());
        design = designRepository.save(design);

        design = new Design();
        design.setName("Just A Song");
        design.setSeries(120);
        design.setBrandId(brand1.getId());
        design = designRepository.save(design);

        design = new Design();
        design.setName("A Little Tune");
        design.setSeries(100);
        design.setBrandId(brand1.getId());
        design = designRepository.save(design);

        List<Design> dList = designRepository.findAllDesignsByBrandId(brand.getId());
        assertEquals(dList.size(), 1);

        dList = designRepository.findAllDesignsByBrandId(brand1.getId());
        assertEquals(dList.size(), 2);

    }
}
