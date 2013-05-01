package kz.helloapp.model.service;


import kz.helloapp.model.domain.*;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;

@Stateless(name = "business-service")
@Local(PartnerService.class)
public class PartnerBean implements PartnerService {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Campaign addCampaign(Campaign campaign) {
        em.persist(campaign);
        return campaign;
    }

    @Override
    public List<Campaign> getCampaignsByCompany(long companyId) {
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
    public List<CampaignStat> getStatsByCampaignId(long campaignId) {
        //noinspection unchecked
        return em.createQuery("select s from CampaignStat s where s.campaign.id=:did")
                .setParameter("did", campaignId)
                .getResultList();
    }

    @Override
    public List<CampaignStat> getStatsByCampaignId(long campaignId, Date from, Date to) {
        //noinspection unchecked
        return em.createQuery("select s from CampaignStat s where s.campaign.id=:did " +
                "and s.whenApllied between :from and :to")
                .setParameter("did", campaignId)
                .setParameter("from", from)
                .setParameter("to", to)
                .getResultList();
    }

    @Override
    public PartnerUser getPartnerUser(String email) {
        try {
            return (PartnerUser) em.createQuery("select u from PartnerUser u where u.email = :email")
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public PartnerCompany getPartnerCompany(long id) {
        return em.find(PartnerCompany.class, id);
    }


    @Override
    public PartnerConfirmer addConfirmer(PartnerConfirmer confirmer) {
        em.persist(confirmer);
        return confirmer;
    }

    @Override
    public PartnerConfirmer getConfirmer(long id) {
        try {
            em.find(PartnerConfirmer.class, id);
        } catch (NoResultException e) {
            return null;
        }
        return null;
    }



}
