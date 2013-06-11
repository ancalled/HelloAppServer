package kz.helloapp.model.service;

import kz.helloapp.model.domain.*;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless(name = "admin-service")
@Local(AdminService.class)
public class AdminBean implements AdminService {

    @PersistenceContext
    private EntityManager em;


    @Override
    public AdminUser getAdminUser(String email) {
        try {
            return (AdminUser) em.createQuery("select u from AdminUser u where u.email = :email")
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public PartnerCompany createPartner(PartnerCompany c) {
        em.persist(c);
        return c;
    }

    @Override
    public PartnerUser createPartnerUser(PartnerUser u) {
        em.persist(u);
        return u;
    }

    @Override
    public List<PartnerCompany> getPartnerCompanies() {
        //noinspection unchecked
        return em.createQuery("select c from PartnerCompany c")
                .getResultList();
    }

    @Override
    public List<PartnerUser> getPartnerUsers() {
        //noinspection unchecked
        return em.createQuery("select u from PartnerUser u")
                .getResultList();
    }

    @Override
    public List<PartnerUser> getPartnerUsers(long partnerId) {
        //noinspection unchecked
        return em.createQuery("select u from PartnerUser u where u.company.id =:id")
                .setParameter("id", partnerId)
                .getResultList();
    }

    @Override
    public List<PartnerUser> getCampaignStats() {
        //noinspection unchecked
        return em.createQuery("select s from CampaignStat s")
                .getResultList();
    }

    @Override
    public PartnerCompany removePartner(long partnerId) {
        try {
            PartnerCompany pc = (PartnerCompany) em.
                    createQuery("select p from PartnerCompany p where p.id = :id")
                    .setParameter("id", partnerId)
                    .getSingleResult();

            em.createQuery("delete from PartnerCompany p where p.id = :id")
                    .setParameter("id", partnerId)
                    .executeUpdate();

            return pc;
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<Campaign> getCampaigns() {
        //noinspection unchecked
        return em.createQuery("select c from Campaign c")
                .getResultList();
    }

    @Override
    public List<PartnerConfirmer> getConfirmers(long partnerId) {
        //noinspection unchecked
        return em.createQuery("select c from PartnerConfirmer c where c.company.id =:id")
                .setParameter("id", partnerId)
                .getResultList();
    }

    @Override
    public PartnerConfirmer getConfirmer(long cinfId) {
        try {
            //noinspection unchecked
            return (PartnerConfirmer) em.createQuery("select c from PartnerConfirmer c where c.id =:id")
                    .setParameter("id", cinfId)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
