package com.mcsm.hellapp.model.service;

import com.mcsm.hellapp.model.domain.*;

import java.util.Date;
import java.util.List;

public interface HelloAppCatalog {


    List<Discount> getAllDiscounts();

    Long applyDiscount(long userId, long discountId);


//    ---------------------------------


    List<DiscountStat> getDiscounts(long discountId, Date from, Date to);

//    ---------------------------------


    List<Category> getAllCategories();

    List<Place> getCatPlaces(String cat);

    List<Event> getPlaceEvents(String place, Date from);

    List<Event> getPlaceEvents(String place, Date from, Date to);

    List<Place> getAllPlaces();

    List<Place> getAllEvents();

}
