package com.mcsm.hellapp.admin.model.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "discount_stats")
public class CampaignStat implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "discount_id")
    private Campaign campaign;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private CustomerUser user;

    private Date whenApllied;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Campaign getCampaign() {
        return campaign;
    }

    public void setCampaign(Campaign campaign) {
        this.campaign = campaign;
    }

    public CustomerUser getUser() {
        return user;
    }

    public void setUser(CustomerUser user) {
        this.user = user;
    }

    public Date getWhenApllied() {
        return whenApllied;
    }

    public void setWhenApllied(Date whenApllied) {
        this.whenApllied = whenApllied;
    }
}
