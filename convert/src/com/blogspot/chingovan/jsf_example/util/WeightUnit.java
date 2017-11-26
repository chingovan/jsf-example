package com.blogspot.chingovan.jsf_example.util;

/**
 * Created by ChiNV on 11/26/2017.
 */
public enum WeightUnit {
    KILOGRAMS(0), POUNDS(1);

    public int unit;

    private WeightUnit(int unit) {
        this.unit = unit;
    }
}
