package kz.helloapp.view.admin;


import kz.helloapp.model.domain.AdminUser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthServlet extends AdminServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String email = req.getParameter("e");
        String pass = req.getParameter("p");

        String ctx = req.getContextPath();
        if (!ctx.endsWith("/")) ctx = ctx + "/";

        if (email != null && pass != null && !"".equals(email.trim()) && !"".equals(pass.trim())) {
            AdminUser user = service.getAdminUser(email);

            if (user != null && pass.equals(user.getPass())) {

                req.getSession().setAttribute("user", user);

                resp.sendRedirect(ctx + "/view/admin/partners");
                return;
            }
        }

        resp.sendRedirect(ctx + "login-admin.html");
    }
}
