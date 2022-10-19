package com.company.carstore.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "carType")

public class CarType implements Serializable {

    @Id
    @Column(name = "artist_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "carTypeId")
    private Set<Brand> brands = new HashSet<>();

    private String name;
    private Integer seat;

    public CarType(int id, Set<Brand> brands, String name, Integer seat) {
        this.id = id;
        this.brands = brands;
        this.name = name;
        this.seat = seat;
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

    public Integer getSeat() {
        return seat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarType carType = (CarType) o;
        return id == carType.id && Objects.equals(brands, carType.brands) && Objects.equals(name, carType.name) && Objects.equals(seat, carType.seat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, brands, name, seat);
    }

    public void setSeat(Integer seat) {
        this.seat = seat;
    }

    @Override
    public String toString() {
        return "CarType{" +
                "id=" + id +
                ", brands=" + brands +
                ", name='" + name + '\'' +
                ", seat=" + seat +
                '}';
    }
}
