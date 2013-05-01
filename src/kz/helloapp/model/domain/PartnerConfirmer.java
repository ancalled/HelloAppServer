package kz.helloapp.model.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "partner_confirmers")
public class PartnerConfirmer {

    @Id
    private Long id;

    @ManyToOne
    private PartnerCompany company;

    private String name;

    private String code;

    private Date codeGenTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PartnerCompany getCompany() {
        return company;
    }

    public void setCompany(PartnerCompany company) {
        this.company = company;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getCodeGenTime() {
        return codeGenTime;
    }

    public void setCodeGenTime(Date codeGenTime) {
        this.codeGenTime = codeGenTime;
    }
}
