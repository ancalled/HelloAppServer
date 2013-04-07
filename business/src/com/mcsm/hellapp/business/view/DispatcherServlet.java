package com.mcsm.hellapp.business.view;


import com.mcsm.hellapp.business.model.domain.BusinessUser;
import com.mcsm.hellapp.business.model.domain.Campaign;
import com.mcsm.hellapp.business.model.domain.CampaignStat;
import com.mcsm.hellapp.business.model.service.BusinessService;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class DispatcherServlet extends HttpServlet {


    private BusinessService service;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        try {
            InitialContext context = new InitialContext();
            service = (BusinessService) context.lookup("java:app/business.jar/business-service");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Action action = ActionFactory.getAction(req, service);
        if (action != null) {
            String view = action.execute(req, resp);

            req.getRequestDispatcher("/WEB-INF/" + view + ".jsp").forward(req, resp);

        } else {
            resp.sendRedirect("/404.html");
        }
    }


 // ------------------------------------------------------------------------------------------------


    public static class ActionFactory {

        public static Action getAction(HttpServletRequest req, BusinessService service) {
            String pth = req.getPathInfo();

            if ("/reports".equals(pth)) {
                return new Action(service) {

                    @Override
                    public String execute(HttpServletRequest req, HttpServletResponse resp) {
                        BusinessUser user = (BusinessUser) req.getSession().getAttribute("user");

                        if (user != null && user.getCompany() != null) {
                            List<CampaignStat> stats = service.getStatsByCompany(user.getCompany().getId());

                            req.setAttribute("stats", stats);
                        }

                        return "reports";
                    }
                };

            } else if ("/campaigns".equals(pth)) {
                return new Action(service) {

                    @Override
                    public String execute(HttpServletRequest req, HttpServletResponse resp) {

                        BusinessUser user = (BusinessUser) req.getSession().getAttribute("user");

                        if (user != null && user.getCompany() != null) {
                            List<Campaign> campaigns = service.getDiscountsByCompany(user.getCompany().getId());

                            req.setAttribute("campaigns", campaigns);
                        }

                        return "campaigns";
                    }
                };

            } else if ("/add-campaign".equals(pth)) {
                return new Action(service) {

                    @Override
                    public String execute(HttpServletRequest req, HttpServletResponse resp) {

//        BusinessUser user = (BusinessUser) req.getSession().getAttribute("user");
//
//        if (user != null && user.getCompany() != null) {
//            List<Campaign> campaigns = service.getDiscountsByCompany(user.getCompany().getId());
//
//            req.setAttribute("campaigns", campaigns);
//        }

                        return "add-campaign";
                    }
                };
            }

            return null;
        }
    }

    public abstract static class Action {

        protected final BusinessService service;


        protected Action(BusinessService service) {
            this.service = service;
        }

        public abstract String execute(HttpServletRequest req, HttpServletResponse resp);

    }
}
