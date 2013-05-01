package kz.helloapp.model.service;


import kz.helloapp.model.domain.Campaign;
import kz.helloapp.model.domain.CampaignStat;
import kz.helloapp.model.domain.PartnerConfirmer;
import kz.helloapp.model.domain.CustomerUser;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless(name = "customer-service")
@Local(CustomerService.class)
public class CustomerBean implements CustomerService {

    @PersistenceContext
    private EntityManager em;


    @Override
    public List<Campaign> getAllCampaigns() {
        //noinspection unchecked
        return em.createQuery("select c from Campaign c")
                .getResultList();
    }


    @Override
    public PartnerConfirmer getConfirmer(String code) {
        try {
            return (PartnerConfirmer) em.createQuery("select c from PartnerConfirmer c where c.code = :code")
                    .setParameter("code", code)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }


    @Override
    public CustomerUser getUser(long id) {
        try {
            return em.find(CustomerUser.class, id);
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public Campaign getCampaign(long id) {
        try {
            return em.find(Campaign.class, id);
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public CampaignStat saveCampaignStat(CampaignStat stat) {
        em.persist(stat);
        return stat;
    }


}
