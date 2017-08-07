package com.example.juanc.bitcoin.utils;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by RicardoAndrés on 06/08/2017.
 */

public class Test {

    public static void dataTest(){
        L.categoryStaticList = new ArrayList<>();
        L.categoryStaticList.add(new com.example.juanc.bitcoin.models.Category(1, "Ropa", 0.0F));
        L.categoryStaticList.add(new com.example.juanc.bitcoin.models.Category(2, "Hotel", 120.0F));
        L.categoryStaticList.add(new com.example.juanc.bitcoin.models.Category(3, "Bar", 240.0F));

        L.countryStaticList = new ArrayList<>();
        L.countryStaticList.add(new com.example.juanc.bitcoin.models.Country(1, "Colombia"));
        L.countryStaticList.add(new com.example.juanc.bitcoin.models.Country(2, "Canada"));
        L.countryStaticList.add(new com.example.juanc.bitcoin.models.Country(3, "España"));

        L.cityStaticList = new ArrayList<>();
        L.cityStaticList.add(new com.example.juanc.bitcoin.models.City(1, 1, "Popayan"));
        L.cityStaticList.add(new com.example.juanc.bitcoin.models.City(2, 1, "Cartagena"));
        L.cityStaticList.add(new com.example.juanc.bitcoin.models.City(3, 2, "Toronto"));
        L.cityStaticList.add(new com.example.juanc.bitcoin.models.City(4, 2, "Vancouver"));

        L.placeStaticList = new ArrayList<>();
        L.placeStaticList.add(new com.example.juanc.bitcoin.models.Place(1, 1, 1, "Arturo Calle", 2.459314, -76.594656));
        L.placeStaticList.add(new com.example.juanc.bitcoin.models.Place(2, 2, 2, "Hotel Palma real", 10.391193, -75.522445));
        L.placeStaticList.add(new com.example.juanc.bitcoin.models.Place(3, 3, 3, "The Loose Moose", 43.645169, -79.383901));
        L.placeStaticList.add(new com.example.juanc.bitcoin.models.Place(4, 2, 4, "Chill Vacation Rental", 49.341300, -123.189672));
        L.placeStaticList.add(new com.example.juanc.bitcoin.models.Place(5, 3, 1, "Barriles", 2.457234, -76.595456));

        Calendar c = Calendar.getInstance();
        L.eventStaticList = new ArrayList<>();
        c.set(2017, 8, 7, 15, 30);
        L.eventStaticList.add(new com.example.juanc.bitcoin.models.Event(1,1, "Conferencia Ropa", "Conferencia", c.getTime(), 2.442969, -76.606168));
        c.set(2017, 8, 9, 8, 0);
        L.eventStaticList.add(new com.example.juanc.bitcoin.models.Event(2,1, "Conferencia Bar", "Conferencia", c.getTime(), 2.443922, -76.605873));
        c.set(2017, 4, 5, 15, 30);
        L.eventStaticList.add(new com.example.juanc.bitcoin.models.Event(3,1, "Conferencia hotel", "Conferencia", c.getTime(), 2.442714, -76.607165));

        L.personStaticList = new ArrayList<>();
        L.personStaticList.add(new com.example.juanc.bitcoin.models.Person(1, 1, "Juan Carlos", "312 312 3213", 1));
        L.personStaticList.add(new com.example.juanc.bitcoin.models.Person(2, 1, "Ricardo", "312 312 3213", 1));
        L.personStaticList.add(new com.example.juanc.bitcoin.models.Person(3, 1, "Maria", "312 312 3213", 2));
        L.personStaticList.add(new com.example.juanc.bitcoin.models.Person(4, 1, "Laura", "312 312 3213", 2));

    }

}
