package kz.helloapp.view.partner;


import kz.helloapp.model.domain.Campaign;
import kz.helloapp.model.domain.CampaignStat;
import kz.helloapp.model.domain.PartnerConfirmer;
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

            final PartnerUser user = (PartnerUser) req.getSession().getAttribute("user");
            if (user == null || user.getCompany() == null) {
                return null;
            }


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

                case "/confirmers":
                    return new Action(service) {

                        @Override
                        public String execute(HttpServletRequest req, HttpServletResponse resp) {

                            PartnerUser user = (PartnerUser) req.getSession().getAttribute("user");

                            if (user != null && user.getCompany() != null) {
                                List<PartnerConfirmer> confirmers = service.getConfirmersByCompany(user.getCompany().getId());

                                req.setAttribute("confirmers", confirmers);
                            }

                            return "confirmers";
                        }
                    };

                case "/qrcode":
                    return new Action(service) {

                        @Override
                        public String execute(HttpServletRequest req, HttpServletResponse resp) {

//                            Long confId = Long.parseLong(req.getParameter("cid"));
//                            PartnerConfirmer confirmer = service.getConfirmer(confId);
//
//                            if (confirmer != null) {
//                                req.setAttribute("confirmers", confirmers);
//
//                            }
                            return "confirmers";
                        }
                    };

                case "/new-campaign":
                    return new Action(service) {

                        @Override
                        public String execute(HttpServletRequest req, HttpServletResponse resp) {
                            return "new-campaign";
                        }
                    };

                case "/check-sign":
                    return new Action(service) {

                        @Override
                        public String execute(HttpServletRequest req, HttpServletResponse resp) {

                            List<Campaign> campaigns = service.getCampaignsByCompany(user.getCompany().getId());
                            req.setAttribute("campaigns", campaigns);

                            return "check-sign";
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
