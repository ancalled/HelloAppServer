package com.mcsm.hellapp.business.model.service;

import com.mcsm.hellapp.business.model.domain.BusinessCompany;
import com.mcsm.hellapp.business.model.domain.BusinessUser;
import com.mcsm.hellapp.business.model.domain.Campaign;
import com.mcsm.hellapp.business.model.domain.CampaignStat;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;

@Stateless(name = "business-service")
@Local(BusinessService.class)
public class BusinessBean implements BusinessService {

    @PersistenceContext(unitName = "HelloAppBusinessPU")
    private EntityManager em;

    @Override
    public Campaign addCampaign(Campaign campaign) {
        em.persist(campaign);
        return campaign;
    }

    @Override
    public List<Campaign> getDiscountsByCompany(long companyId) {
        //noinspection unchecked
        return em.createQuery("select s from Campaign s where s.company.id=:cid")
                .setParameter("cid", companyId)
                .getResultList();
    }

    @Override
    public List<CampaignStat> getStatsByCompany(long companyId) {
        //noinspection unchecked
        return em.createQuery("select s from CampaignStat s where s.campaign.company.id=:cid")
                .setParameter("cid", companyId)
                .getResultList();
    }

    @Override
    public List<CampaignStat> getStatsByDiscountId(long discountId) {
        //noinspection unchecked
        return em.createQuery("select s from CampaignStat s where s.campaign.id=:did")
                .setParameter("did", discountId)
                .getResultList();
    }

    @Override
    public List<CampaignStat> getStatsByDiscountId(long discountId, Date from, Date to) {
        //noinspection unchecked
        return em.createQuery("select s from CampaignStat s where s.campaign.id=:did " +
                "and s.whenApllied between :from and :to")
                .setParameter("did", discountId)
                .setParameter("from", from)
                .setParameter("to", to)
                .getResultList();
    }

    @Override
    public BusinessUser getBusinessUser(String email) {
        try {
            return (BusinessUser) em.createQuery("select u from BusinessUser u where u.email = :email")
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public BusinessCompany getBusinessCompany(long id) {
        return em.find(BusinessCompany.class, id);
    }

}
