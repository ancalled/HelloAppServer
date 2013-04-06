package com.mcsm.hellapp.business.model.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "campaign_stats")
public class CampaignStat implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @ManyToOne
    private Campaign campaign;

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

    public Date getWhenApllied() {
        return whenApllied;
    }

    public void setWhenApllied(Date whenApllied) {
        this.whenApllied = whenApllied;
    }
}
