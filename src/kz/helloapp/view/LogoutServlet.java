package kz.helloapp.view;


import kz.helloapp.model.domain.AdminUser;
import kz.helloapp.model.domain.PartnerUser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession ses = req.getSession();

        Object user = ses.getAttribute("user");

        if (user != null) {
            ses.setAttribute("user", null);
        }

        String role = "partner";

        if (user instanceof AdminUser) {
            role = "admin";
        } else if (user instanceof PartnerUser) {
            role = "partner";
        }

        resp.sendRedirect("/helloapp/login-" + role + ".html");
    }
}
