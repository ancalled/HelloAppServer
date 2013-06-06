package kz.helloapp.view.admin;

import kz.helloapp.model.domain.PartnerCompany;
import kz.helloapp.model.service.AdminService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class DispatcherServlet extends AdminServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Action action = ActionFactory.getAction(req, service);
        if (action != null) {
            String view = action.execute(req, resp);

            req.getRequestDispatcher("/WEB-INF/views/admin/" + view + ".jsp").forward(req, resp);

        } else {
            resp.sendRedirect("/404.html");
        }
    }


    public static class ActionFactory {

        public static Action getAction(HttpServletRequest req, AdminService service) {
            String pth = req.getPathInfo();

            switch (pth) {
                case "/partners":
                    return new Action(service) {

                        @Override
                        public String execute(HttpServletRequest req, HttpServletResponse resp) {
                            List<PartnerCompany> partners = service.getPartnerCompanies();

                            req.setAttribute("partners", partners);

                            return "partners";
                        }
                    };
                case "/new-partner":
                    return new Action(service) {

                        @Override
                        public String execute(HttpServletRequest req, HttpServletResponse resp) {

                            return "new-partner";
                        }
                    };
            }

            return null;
        }

    }


    public abstract static class Action {

        protected final AdminService service;


        protected Action(AdminService service) {
            this.service = service;
        }

        public abstract String execute(HttpServletRequest req, HttpServletResponse resp);

    }

}
