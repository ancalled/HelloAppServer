package kz.helloapp.view.partner;


import kz.helloapp.model.domain.Campaign;
import kz.helloapp.model.domain.CampaignStat;
import kz.helloapp.model.domain.PartnerUser;
import kz.helloapp.model.service.PartnerService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class DispatcherServlet extends PartnerServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Action action = ActionFactory.getAction(req, service);
        if (action != null) {
            String view = action.execute(req, resp);

            req.getRequestDispatcher("/WEB-INF/views/partner/" + view + ".jsp").forward(req, resp);

        } else {
            resp.sendRedirect("/404.html");
        }
    }


    // ------------------------------------------------------------------------------------------------


    public static class ActionFactory {

        public static Action getAction(HttpServletRequest req, PartnerService service) {
            String pth = req.getPathInfo();

            switch (pth) {
                case "/reports":
                    return new Action(service) {

                        @Override
                        public String execute(HttpServletRequest req, HttpServletResponse resp) {
                            PartnerUser user = (PartnerUser) req.getSession().getAttribute("user");

                            if (user != null && user.getCompany() != null) {
                                List<CampaignStat> stats = service.getStatsByCompany(user.getCompany().getId());

                                req.setAttribute("stats", stats);
                            }

                            return "reports";
                        }
                    };

                case "/campaigns":
                    return new Action(service) {

                        @Override
                        public String execute(HttpServletRequest req, HttpServletResponse resp) {

                            PartnerUser user = (PartnerUser) req.getSession().getAttribute("user");

                            if (user != null && user.getCompany() != null) {
                                List<Campaign> campaigns = service.getCampaignsByCompany(user.getCompany().getId());

                                req.setAttribute("campaigns", campaigns);
                            }

                            return "campaigns";
                        }
                    };

                case "/new-campaign":
                    return new Action(service) {

                        @Override
                        public String execute(HttpServletRequest req, HttpServletResponse resp) {
                            return "new-campaign";
                        }
                    };
            }

            return null;
        }
    }

    public abstract static class Action {

        protected final PartnerService service;


        protected Action(PartnerService service) {
            this.service = service;
        }

        public abstract String execute(HttpServletRequest req, HttpServletResponse resp);

    }
}
