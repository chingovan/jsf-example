package com.blogspot.chingovan.jsf_example.util;

/**
 * Created by ChiNV on 11/22/2017.
 */
public enum ServiceLevel {

    MEDIUM("Medium", 1), BASIC("Basic", 2), PREMIUM("Premium", 3);

    public String name;
    public int value;

    private ServiceLevel(String name, int value) {
        this.name = name;
        this.value = value;
    }
}
