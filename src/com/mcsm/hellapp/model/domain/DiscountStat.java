package com.mcsm.hellapp.model.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "disc_stats")
public class DiscountStat {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long userId;
    private Long discountId;
    private Date whenApllied;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getDiscountId() {
        return discountId;
    }

    public void setDiscountId(Long discountId) {
        this.discountId = discountId;
    }

    public Date getWhenApllied() {
        return whenApllied;
    }

    public void setWhenApllied(Date whenApllied) {
        this.whenApllied = whenApllied;
    }
}
