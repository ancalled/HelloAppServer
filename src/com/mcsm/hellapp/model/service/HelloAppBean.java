package com.mcsm.hellapp.model.service;

import com.mcsm.hellapp.model.domain.*;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;

@Stateless(name = "helloApp")
@Local(HelloAppCatalog.class)
public class HelloAppBean implements HelloAppCatalog {

    @PersistenceContext
    private EntityManager em;


    @Override
    public List<Discount> getAllDiscounts() {
        //noinspection unchecked
        return em.createQuery("select c from Discount c")
                .getResultList();
    }

    @Override
    public Long applyDiscount(long userId, long discountId) {
        User u = em.find(User.class, userId);
        Discount d = em.find(Discount.class, discountId);

        if (u != null && d != null) {
            //todo check user can apply this discount

            DiscountStat stat = new DiscountStat();
            stat.setUserId(userId);
            stat.setDiscountId(discountId);
            stat.setWhenApllied(new Date());
            em.persist(stat);

            return stat.getId();
        }

        return null;
    }

    @Override
    public List<DiscountStat> getDiscounts(long discountId, Date from, Date to) {
        //noinspection unchecked
        return em.createQuery("select s from DiscountStat s where s.discountId=:did " +
                "and s.whenApllied between :from and :to")
                .setParameter("did", discountId)
                .setParameter("from", from)
                .setParameter("to", to)
                .getResultList();
    }


    //    -------------------------

    @Override
    public List<Category> getAllCategories() {
        //noinspection unchecked
        return em.createQuery("select c from Category c")
                .getResultList();
    }

    @Override
    public List<Place> getCatPlaces(String cat) {
        //noinspection unchecked
        return em.createQuery("select c from Place c where c.category = :cat")
                .setParameter("cat", cat)
                .getResultList();
    }


    @Override
    public List<Event> getPlaceEvents(String place, Date from) {
        //noinspection unchecked
        return em.createQuery("select e from Event e where e.place = :place and e.eventDate > :from")
                .setParameter("place", place)
                .setParameter("from", from)
                .getResultList();
    }

    @Override
    public List<Event> getPlaceEvents(String place, Date from, Date to) {
        //noinspection unchecked
        return em.createQuery("select e from Event e where e.place = :place and e.eventDate between :from and :to")
                .setParameter("place", place)
                .setParameter("from", from)
                .setParameter("to", to)
                .getResultList();
    }

    @Override
    public List<Place> getAllPlaces() {
        //noinspection unchecked
        return em.createQuery("select c from Place c")
                .getResultList();
    }

    @Override
    public List<Place> getAllEvents() {
        //noinspection unchecked
        return em.createQuery("select c from Event c")
                .getResultList();
    }
}
