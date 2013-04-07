package com.mcsm.hellapp.admin.model.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "business_users")
public class BusinessUser implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String email;
    private String pass;

    @ManyToOne
    private BusinessCompany company;

    public String getEmail() {
        return email;
    }

    public void setEmail(String name) {
        this.email = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String passs) {
        this.pass = passs;
    }

    public BusinessCompany getCompany() {
        return company;
    }

    public void setCompany(BusinessCompany company) {
        this.company = company;
    }
}
