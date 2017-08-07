package com.example.juanc.bitcoin.models;

/**
 * Created by RicardoAndrés on 06/08/2017.
 */

public class CategorySelect {

    String name;
    boolean selected;

    public CategorySelect(String name, boolean selected) {
        this.name = name;
        this.selected = selected;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
