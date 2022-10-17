package com.company.carstore.viewmodel;


import com.company.carstore.model.CarType;
import com.company.carstore.model.Design;
import com.company.carstore.model.Year;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BrandViewModel {
    private int id;
    private String title;
    private CarType carType;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate releaseDate;
    private Year year;
    private BigDecimal listPrice;
    private List<Design> designs = new ArrayList<>();

    public BrandViewModel(int id, String title, CarType carType, LocalDate releaseDate, Year year, BigDecimal listPrice, List<Design> designs) {
        this.id = id;
        this.title = title;
        this.carType = carType;
        this.releaseDate = releaseDate;
        this.year = year;
        this.listPrice = listPrice;
        this.designs = designs;
    }

    public BrandViewModel(String title, CarType carType, LocalDate releaseDate, Year year, BigDecimal listPrice, List<Design> designs) {
        this.title = title;
        this.carType = carType;
        this.releaseDate = releaseDate;
        this.year = year;
        this.listPrice = listPrice;
        this.designs = designs;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public CarType getCarType() {
        return carType;
    }

    public void setCarType(CarType carType) {
        this.carType = carType;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Year getYear() {
        return year;
    }

    public void setYear(Year year) {
        this.year = year;
    }

    public BigDecimal getListPrice() {
        return listPrice;
    }

    public void setListPrice(BigDecimal listPrice) {
        this.listPrice = listPrice;
    }

    public List<Design> getDesigns() {
        return designs;
    }

    public void setDesigns(List<Design> designs) {
        this.designs = designs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BrandViewModel that = (BrandViewModel) o;
        return id == that.id && Objects.equals(title, that.title) && Objects.equals(carType, that.carType) && Objects.equals(releaseDate, that.releaseDate) && Objects.equals(year, that.year) && Objects.equals(listPrice, that.listPrice) && Objects.equals(designs, that.designs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, carType, releaseDate, year, listPrice, designs);
    }

    @Override
    public String toString() {
        return "BrandViewModel{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", carType=" + carType +
                ", releaseDate=" + releaseDate +
                ", year=" + year +
                ", listPrice=" + listPrice +
                ", designs=" + designs +
                '}';
    }
}
