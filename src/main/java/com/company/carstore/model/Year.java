package com.company.carstore.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "year")

public class Year implements Serializable {

    @Id
    @Column(name = "year_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "yearId")
    private Set<Brand> brands = new HashSet<>();

    private String name;

    public Year(int id, Set<Brand> brands, String name) {
        this.id = id;
        this.brands = brands;
        this.name = name;
    }

    public Year() {
    }

    public Year(Set<Brand> brands, String name) {
        this.brands = brands;
        this.name = name;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Year year = (Year) o;
        return id == year.id && Objects.equals(brands, year.brands) && Objects.equals(name, year.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, brands, name);
    }

    @Override
    public String toString() {
        return "Year{" +
                "id=" + id +
                ", brands=" + brands +
                ", name='" + name + '\'' +
                '}';
    }
}
