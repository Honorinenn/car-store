package com.company.carstore.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "design")
public class Design implements Serializable {

    @Id
    @Column(name = "design_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int brandId;
    private String name;
    private int series;

    public Design(int id, int brandId, String name, int series) {
        this.id = id;
        this.brandId = brandId;
        this.name = name;
        this.series = series;
    }

    public Design() {
    }

    public Design(int brandId, String name, int series) {
        this.brandId = brandId;
        this.name = name;
        this.series = series;
    }

    public Design(String name, int series) {
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

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Design design = (Design) o;
        return id == design.id && brandId == design.brandId && series == design.series && Objects.equals(name, design.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, brandId, name, series);
    }

    @Override
    public String toString() {
        return "Design{" +
                "id=" + id +
                ", brandId=" + brandId +
                ", name='" + name + '\'' +
                ", series=" + series +
                '}';
    }
}
