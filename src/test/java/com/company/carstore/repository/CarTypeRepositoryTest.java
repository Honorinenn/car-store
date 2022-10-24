package com.company.carstore.repository;

import com.company.carstore.model.CarType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CarTypeRepositoryTest {

    @Autowired
    CarTypeRepository carTypeRepository;

    @Before
    public void setUp() throws Exception {
        carTypeRepository.deleteAll();
    }

    @Test
    public void addGetDeleteArtist() {

        CarType carType = new CarType();
        carType.setName("Rock Star");
        carType.setSeat(4);
        carType = carTypeRepository.save(carType);

        Optional<CarType> carType1 = carTypeRepository.findById(carType.getId());

        assertEquals(carType1.get(), carType);

        carTypeRepository.deleteById(carType.getId());

        carType1 = carTypeRepository.findById(carType.getId());

        assertFalse(carType1.isPresent());
    }

    @Test
    public void updateCarType() {

        CarType carType = new CarType();
        carType.setName("Rock Star");
        carType.setSeat(4);
        carType = carTypeRepository.save(carType);

        carType.setName("Pop Star");
        carType.setSeat(4);

        carTypeRepository.save(carType);

        Optional<CarType> carType1 = carTypeRepository.findById(carType.getId());
        assertEquals(carType1.get(), carType);
    }



}
