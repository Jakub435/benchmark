package com.benchmark.MySQL.domain;

import javax.persistence.*;

@Entity
@Table(name = "shapename")
public class ShapeName {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

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

    public ShapeName(String name) {
        this.name = name;
    }

    public ShapeName(){}
}
