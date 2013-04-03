package com.mcsm.hellapp.model.domain;

import javax.persistence.*;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    private String name;

    private String title;

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
}
