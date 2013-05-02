package kz.helloapp.model.service;


import kz.helloapp.model.domain.*;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.Date;
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
    public CustomerUser getUser(String login) {
        try {
            return (CustomerUser) em.createQuery("select c from CustomerUser c where c.name = :login")
                    .setParameter("login", login)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public CustomerUser setAuthToken(CustomerUser user, String token) {
        AuthToken t = new AuthToken();
        t.setToken(token);
        t.setWhenGenerated(new Date());
        user.setAuthToken(t);

        return em.merge(user);
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
