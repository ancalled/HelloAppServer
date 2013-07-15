package kz.helloapp.model.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "campaigns")
public class Campaign implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private Integer rate;
    private Date startFrom;
    private Date goodThrough;

    private boolean needConfirm;
    private boolean needSign;

    @ManyToOne
    private PartnerCompany company;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

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

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }


    public Date getStartFrom() {
        return startFrom;
    }

    public void setStartFrom(Date startFrom) {
        this.startFrom = startFrom;
    }

    public Date getGoodThrough() {
        return goodThrough;
    }

    public void setGoodThrough(Date goodThrough) {
        this.goodThrough = goodThrough;
    }

    public boolean isNeedConfirm() {
        return needConfirm;
    }

    public void setNeedConfirm(boolean needConfirm) {
        this.needConfirm = needConfirm;
    }

    public boolean isNeedSign() {
        return needSign;
    }

    public void setNeedSign(boolean needSign) {
        this.needSign = needSign;
    }
}
