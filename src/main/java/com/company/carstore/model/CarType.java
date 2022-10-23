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

    public CarType(int id, String name, Integer seat) {
        this.id = id;
        this.name = name;
        this.seat = seat;
    }

    public CarType(String name, Integer seat) {
        this.name = name;
        this.seat = seat;
    }

    public CarType() {
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public void setSeat(Integer seat) {
        this.seat = seat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarType carType = (CarType) o;
        return id == carType.id && Objects.equals(name, carType.name) && Objects.equals(seat, carType.seat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, seat);
    }

    @Override
    public String toString() {
        return "CarType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", seat=" + seat +
                '}';
    }
}
