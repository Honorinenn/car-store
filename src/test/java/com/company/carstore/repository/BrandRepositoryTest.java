package com.company.carstore.repository;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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

}
