package com.juansoft.aceptobitcoin.models;

/**
 * Created by RicardoAndrés on 06/08/2017.
 */

public class Person {

    long id;
    long city_id;
    String name;
    String phone;
    int state;

    public Person() {
    }

    public Person(long id, long city_id, String name, String phone, int state) {
        this.id = id;
        this.city_id = city_id;
        this.name = name;
        this.phone = phone;
        this.state = state;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCity_id() {
        return city_id;
    }

    public void setCity_id(long city_id) {
        this.city_id = city_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
