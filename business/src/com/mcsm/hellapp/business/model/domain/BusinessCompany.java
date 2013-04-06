package com.mcsm.hellapp.business.model.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "business_companies")
public class BusinessCompany implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
