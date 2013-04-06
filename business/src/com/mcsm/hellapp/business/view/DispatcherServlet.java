package com.mcsm.hellapp.business.view;


import com.mcsm.hellapp.business.model.service.BusinessService;
import com.mcsm.hellapp.business.view.mvc.Action;
import com.mcsm.hellapp.business.view.mvc.AddCampaignAction;
import com.mcsm.hellapp.business.view.mvc.CampaignsAction;
import com.mcsm.hellapp.business.view.mvc.ReportAction;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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

//        if (view.equals(req.getPathInfo().substring(1))) {
            req.getRequestDispatcher("/WEB-INF/" + view + ".jsp").forward(req, resp);
//        } else {
//            resp.sendRedirect(view);
//        }

//        long discId = 1L;
////        Date fromDate = new Date();
////        Date toDate = new Date();
//        List<CampaignStat> stats = service.getStatsByDiscountId(discId);
//
//        req.setAttribute("stats", stats);
//        req.getRequestDispatcher("/WEB-INF/admin/report.jsp").forward(req, resp); // Forward to JSP page to display them in a HTML table.

        } else {
            resp.sendRedirect("/404.html");
        }
    }



    public static class ActionFactory {

        public static Action getAction(HttpServletRequest req, BusinessService service) {
            if ("/reports".equals(req.getPathInfo())) {
                return new ReportAction(service);

            } else if ("/campaigns".equals(req.getPathInfo())) {
                return new CampaignsAction(service);

            } else if ("/add-campaign".equals(req.getPathInfo())) {
                return new AddCampaignAction(service);
            }

            return null;
        }
    }
}
