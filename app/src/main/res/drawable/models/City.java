package com.juansoft.aceptobitcoin.models;

/**
 * Created by RicardoAndrés on 06/08/2017.
 */

public class City {

    long id;
    long country_id;
    String name;

    public City() {
    }

    public City(long id, long country_id, String name) {
        this.id = id;
        this.country_id = country_id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCountry_id() {
        return country_id;
    }

    public void setCountry_id(long country_id) {
        this.country_id = country_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
