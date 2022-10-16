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

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "labelId")
    private Set<Brand> brands = new HashSet<>();

    private String name;
    private String series;

    public Design(int id, Set<Brand> brands, String name, String series) {
        this.id = id;
        this.brands = brands;
        this.name = name;
        this.series = series;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<Brand> getBrands() {
        return brands;
    }

    public void setBrands(Set<Brand> brands) {
        this.brands = brands;
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
        Design design = (Design) o;
        return id == design.id && Objects.equals(brands, design.brands) && Objects.equals(name, design.name) && Objects.equals(series, design.series);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, brands, name, series);
    }

    @Override
    public String toString() {
        return "Model{" +
                "id=" + id +
                ", brands=" + brands +
                ", name='" + name + '\'' +
                ", series='" + series + '\'' +
                '}';
    }
}
