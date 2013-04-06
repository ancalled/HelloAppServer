package com.mcsm.hellapp.business.view.mvc;


import com.mcsm.hellapp.business.model.domain.BusinessUser;
import com.mcsm.hellapp.business.model.domain.Campaign;
import com.mcsm.hellapp.business.model.service.BusinessService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class CampaignsAction extends Action {

    public CampaignsAction(BusinessService service) {
        super(service);
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        BusinessUser user = (BusinessUser) req.getSession().getAttribute("user");

        if (user != null && user.getCompany() != null) {
            List<Campaign> campaigns = service.getDiscountsByCompany(user.getCompany().getId());

            req.setAttribute("campaigns", campaigns);
        }

        return "campaigns";
    }

}
