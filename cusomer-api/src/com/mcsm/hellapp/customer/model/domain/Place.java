package com.mcsm.hellapp.customer.model.domain;

import javax.persistence.*;
import java.io.Serializable;

@Deprecated
//@Entity
@Table(name = "places")
public class Place implements Serializable {

    @Id
    private String name;
    private String title;
    private String category;
    private String city;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
