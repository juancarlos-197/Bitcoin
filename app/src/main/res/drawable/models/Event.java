package com.juansoft.aceptobitcoin.models;

import java.util.Date;

/**
 * Created by RicardoAndrés on 06/08/2017.
 */

public class Event {

    long id;
    long city_id;
    String name;
    String description;
    Date date;
    double latitude;
    double longitude;

    public Event() {
    }

    public Event(long id, long city_id, String name, String description, Date date, double latitude, double longitude) {
        this.id = id;
        this.city_id = city_id;
        this.name = name;
        this.description = description;
        this.date = date;
        this.latitude = latitude;
        this.longitude = longitude;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
