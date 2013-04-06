package com.mcsm.hellapp.business.view.mvc;


import com.mcsm.hellapp.business.model.service.BusinessService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class Action {

    protected final BusinessService service;


    protected Action(BusinessService service) {
        this.service = service;
    }

    public abstract String execute(HttpServletRequest req, HttpServletResponse resp);

}
