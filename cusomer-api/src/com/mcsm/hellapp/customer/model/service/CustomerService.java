package com.mcsm.hellapp.customer.model.service;

import com.mcsm.hellapp.customer.model.domain.Category;
import com.mcsm.hellapp.customer.model.domain.Discount;
import com.mcsm.hellapp.customer.model.domain.Event;
import com.mcsm.hellapp.customer.model.domain.Place;

import java.util.Date;
import java.util.List;

public interface CustomerService {


    List<Discount> getAllDiscounts();

    Long applyDiscount(long userId, long discountId);



//    ---------------------------------


//    List<Category> getAllCategories();
//
//    List<Place> getCatPlaces(String cat);
//
//    List<Event> getPlaceEvents(String place, Date from);
//
//    List<Event> getPlaceEvents(String place, Date from, Date to);
//
//    List<Place> getAllPlaces();
//
//    List<Place> getAllEvents();

}
