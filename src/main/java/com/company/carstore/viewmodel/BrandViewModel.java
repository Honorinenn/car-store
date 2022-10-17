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
import java.util.Objects;

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

    public BrandViewModel(String title, int carTypeId, LocalDate releaseDate, int yearId, BigDecimal listPrice, List<Design> designs) {
        this.title = title;
        this.carTypeId = carTypeId;
        this.releaseDate = releaseDate;
        this.yearId = yearId;
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

    public int getCarTypeId() {
        return carTypeId;
    }

    public void setCarTypeId(int carTypeId) {
        this.carTypeId = carTypeId;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getYearId() {
        return yearId;
    }

    public void setYearId(int yearId) {
        this.yearId = yearId;
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
        return id == that.id && carTypeId == that.carTypeId && yearId == that.yearId && Objects.equals(title, that.title) && Objects.equals(releaseDate, that.releaseDate) && Objects.equals(listPrice, that.listPrice) && Objects.equals(designs, that.designs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, carTypeId, releaseDate, yearId, listPrice, designs);
    }

    @Override
    public String toString() {
        return "BrandViewModel{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", carTypeId=" + carTypeId +
                ", releaseDate=" + releaseDate +
                ", yearId=" + yearId +
                ", listPrice=" + listPrice +
                ", designs=" + designs +
                '}';
    }
}
