package com.example.juanc.bitcoin.models;

/**
 * Created by RicardoAndrés on 06/08/2017.
 */

public class Category {

    long id;
    String name;
    float color;

    public Category() {
    }

    public Category(long id, String name, float color) {
        this.id = id;
        this.name = name;
        this.color = color;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getColor() {
        return color;
    }

    public void setColor(float color) {
        this.color = color;
    }
}
