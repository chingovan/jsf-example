package com.blogspot.chingovan.jsf_example.convert;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

/**
 * Created by ChiNV on 11/26/2017.
 */
@ManagedBean(name = "convertController")
public class ConvertController {
    private String name;
    private double weight;

    public ConvertController() {
        name = "Test";
        weight = 60D;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void save() {
        System.out.println("save: " + weight);
    }
}
