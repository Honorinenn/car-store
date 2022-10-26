package com.company.carstore.repository;

import com.company.carstore.model.Year;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class YearRepositoryTest {

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
    public void addGetDeleteYear() {

        Year year = new Year();
        year.setName("Intesrcope");
        yearRepository.save(year);

        Optional<Year> year1 = yearRepository.findById(year.getId());

        assertEquals(year1.get(), year);

        yearRepository.deleteById(year.getId());

        year1 = yearRepository.findById(year.getId());

        assertFalse(year1.isPresent());

    }

    @Test
    public void getAllYears() {

        Year year = new Year();
        year.setName("Intesrcope");
        yearRepository.save(year);

        year = new Year();
        year.setName("Island");

        yearRepository.save(year);

        List<Year> yList = yearRepository.findAll();

        assertEquals(yList.size(), 2);

    }

    @Test
    public void updateYear() {

        Year year = new Year();
        year.setName("Intesrcope");

        yearRepository.save(year);

        year.setName("NEW NAME");
        yearRepository.save(year);

        Optional<Year> year1 = yearRepository.findById(year.getId());

        assertEquals(year1.get(), year);
    }
}
