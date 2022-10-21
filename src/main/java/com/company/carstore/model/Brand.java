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
@Table(name = "brand")
public class Brand implements Serializable {

    @Id
    @Column(name = "brand_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "brandId")
    private Set<Design> designs = new HashSet<>();

    private String title;
    private int carTypeId;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate releaseDate;
    private int yearId;
    private BigDecimal listPrice;

    public Brand(int id, Set<Design> designs, String title, int carTypeId, LocalDate releaseDate, int yearId, BigDecimal listPrice) {
        this.id = id;
        this.designs = designs;
        this.title = title;
        this.carTypeId = carTypeId;
        this.releaseDate = releaseDate;
        this.yearId = yearId;
        this.listPrice = listPrice;
    }

    public Brand(Set<Design> designs, String title, int carTypeId, LocalDate releaseDate, int yearId, BigDecimal listPrice) {
        this.designs = designs;
        this.title = title;
        this.carTypeId = carTypeId;
        this.releaseDate = releaseDate;
        this.yearId = yearId;
        this.listPrice = listPrice;
    }

    public Brand() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<Design> getDesigns() {
        return designs;
    }

    public void setDesigns(Set<Design> designs) {
        this.designs = designs;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Brand brand = (Brand) o;
        return id == brand.id && carTypeId == brand.carTypeId && yearId == brand.yearId && Objects.equals(designs, brand.designs) && Objects.equals(title, brand.title) && Objects.equals(releaseDate, brand.releaseDate) && Objects.equals(listPrice, brand.listPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, designs, title, carTypeId, releaseDate, yearId, listPrice);
    }

    @Override
    public String toString() {
        return "Brand{" +
                "id=" + id +
                ", designs=" + designs +
                ", title='" + title + '\'' +
                ", carTypeId=" + carTypeId +
                ", releaseDate=" + releaseDate +
                ", yearId=" + yearId +
                ", listPrice=" + listPrice +
                '}';
    }
}

