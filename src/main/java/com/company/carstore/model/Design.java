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

    
}
