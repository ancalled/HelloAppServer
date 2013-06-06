package kz.helloapp.model.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "partner_companies")
public class PartnerCompany implements Serializable {

    @Id
    @GeneratedValue
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
