package com.company.carstore.viewmodel;


import com.company.carstore.model.Design;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BrandViewModel {
    private int id;
    private String title;
    private int carTypeId;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate releaseDate;
    private int yearId;
    private BigDecimal listPrice;
    private List<Design> designs = new ArrayList<>();

    public BrandViewModel(int id, String title, int carTypeId, LocalDate releaseDate, int yearId, BigDecimal listPrice, List<Design> designs) {
        this.id = id;
        this.title = title;
        this.carTypeId = carTypeId;
        this.releaseDate = releaseDate;
        this.yearId = yearId;
        this.listPrice = listPrice;
        this.designs = designs;
    }
}
