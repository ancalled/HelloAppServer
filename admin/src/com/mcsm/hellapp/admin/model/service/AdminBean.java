package com.mcsm.hellapp.admin.model.service;

import com.mcsm.hellapp.admin.model.domain.AdminUser;
import com.mcsm.hellapp.admin.model.domain.BusinessCompany;
import com.mcsm.hellapp.admin.model.domain.BusinessUser;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless(name = "admin-service")
@Local(AdminService.class)
public class AdminBean implements AdminService {

    @PersistenceContext(unitName = "HelloAppAdminPU")
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
    public BusinessCompany createPartner(BusinessCompany c) {
        em.persist(c);
        return c;
    }

    @Override
    public BusinessUser createPartnerUser(BusinessUser u) {
        em.persist(u);
        return u;
    }

    @Override
    public List<BusinessCompany> getBusinessCompanies() {
        //noinspection unchecked
        return em.createQuery("select c from BusinessCompany c")
                .getResultList();
    }

    @Override
    public List<BusinessUser> getBusinessUsers() {
        //noinspection unchecked
        return em.createQuery("select u from BusinessUser u")
                .getResultList();
    }

    @Override
    public List<BusinessUser> getCampignStats() {
        //noinspection unchecked
        return em.createQuery("select s from CampaignStat s")
                .getResultList();
    }
}
