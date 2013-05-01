package kz.helloapp.view.partner;


import kz.helloapp.model.domain.PartnerUser;
import kz.helloapp.model.service.PartnerService;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthServlet extends PartnerServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String email = req.getParameter("e");
        String pass = req.getParameter("p");

        String ctx = req.getContextPath();
        if (!ctx.endsWith("/")) ctx = ctx + "/";

        if (email != null && pass != null && !"".equals(email.trim()) && !"".equals(pass.trim())) {
            PartnerUser user = service.getPartnerUser(email);

            if (user != null && pass.equals(user.getPass())) {

                req.getSession().setAttribute("user", user);

                resp.sendRedirect(ctx + "partner/view/campaigns");
                return;
            }
        }

        resp.sendRedirect(ctx + "login-partner.html");
    }
}
