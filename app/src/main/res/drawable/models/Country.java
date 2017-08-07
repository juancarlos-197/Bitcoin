package com.juansoft.aceptobitcoin.models;

/**
 * Created by RicardoAndrés on 06/08/2017.
 */

public class Country {

    long id;
    String name;

    public Country() {
    }

    public Country(long id, String name) {
        this.id = id;
        this.name = name;
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
}
