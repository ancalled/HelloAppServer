package com.mcsm.hellapp.business.view;

import com.mcsm.hellapp.business.model.domain.BusinessUser;
import com.mcsm.hellapp.business.model.domain.Campaign;
import com.mcsm.hellapp.business.model.service.BusinessService;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class AddCampaignServlet extends HttpServlet {

    public static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    private BusinessService service;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        try {
            InitialContext context = new InitialContext();
            service = (BusinessService) context.lookup("java:app/HelloAppServer.jar/business-service");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String title = req.getParameter("title");
        String description = req.getParameter("descr");
        String rateStr = req.getParameter("rate");
        String startFromStr = req.getParameter("from");
        String goodThroughStr = req.getParameter("to");

        String ctx = req.getContextPath();
        if (!ctx.endsWith("/")) ctx = ctx + "/";


        if (title != null && description != null && rateStr != null && startFromStr != null && goodThroughStr != null) {

            BusinessUser user = (BusinessUser) req.getSession().getAttribute("user");

            if (user != null && user.getCompany() != null) {

                Campaign c = new Campaign();
                c.setTitle(title);
                c.setDescription(description);
                c.setRate(Integer.parseInt(rateStr));
                c.setCompany(user.getCompany());

                try {
                    c.setStartFrom(DATE_FORMAT.parse(startFromStr));
                    c.setGoodThrough(DATE_FORMAT.parse(goodThroughStr));
                } catch (ParseException e) {
                    e.printStackTrace();
                    return;
                }

                service.addCampaign(c);

                resp.sendRedirect(ctx + "business/campaings");

                return;
            }

        }


    }
}
