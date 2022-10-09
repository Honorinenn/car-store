package com.company.carstore.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "car")
public class Car implements Serializable {

    @Id
    @Column(name = "car_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "carId")
    private Set<Model> models = new HashSet<>();

    private String title;
    private int brandId;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate releaseDate;
    private int yearId;
    private BigDecimal listPrice;

    public Car(int id, Set<Model> models, String title, int brandId, LocalDate releaseDate, int yearId, BigDecimal listPrice) {
        this.id = id;
        this.models = models;
        this.title = title;
        this.brandId = brandId;
        this.releaseDate = releaseDate;
        this.yearId = yearId;
        this.listPrice = listPrice;
    }

    public Car(Set<Model> models, String title, int brandId, LocalDate releaseDate, int yearId, BigDecimal listPrice) {
        this.models = models;
        this.title = title;
        this.brandId = brandId;
        this.releaseDate = releaseDate;
        this.yearId = yearId;
        this.listPrice = listPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<Model> getModels() {
        return models;
    }

    public void setModels(Set<Model> models) {
        this.models = models;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return id == car.id && brandId == car.brandId && yearId == car.yearId && Objects.equals(models, car.models) && Objects.equals(title, car.title) && Objects.equals(releaseDate, car.releaseDate) && Objects.equals(listPrice, car.listPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, models, title, brandId, releaseDate, yearId, listPrice);
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", models=" + models +
                ", title='" + title + '\'' +
                ", brandId=" + brandId +
                ", releaseDate=" + releaseDate +
                ", yearId=" + yearId +
                ", listPrice=" + listPrice +
                '}';
    }
}

