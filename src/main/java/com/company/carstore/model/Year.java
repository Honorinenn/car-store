package com.company.carstore.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "year")

public class Year implements Serializable {

    @Id
    @Column(name = "year_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int brandId;
    private String name;
    private String series;

    public Year(int id, int brandId, String name, String series) {
        this.id = id;
        this.brandId = brandId;
        this.name = name;
        this.series = series;
    }

    public Year(int brandId, String name, String series) {
        this.brandId = brandId;
        this.name = name;
        this.series = series;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Year year = (Year) o;
        return id == year.id && brandId == year.brandId && Objects.equals(name, year.name) && Objects.equals(series, year.series);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, brandId, name, series);
    }

    @Override
    public String toString() {
        return "Year{" +
                "id=" + id +
                ", brandId=" + brandId +
                ", name='" + name + '\'' +
                ", series='" + series + '\'' +
                '}';
    }
}
