package com.mcsm.hellapp.admin.view;

import com.mcsm.hellapp.admin.model.domain.AdminUser;
import com.mcsm.hellapp.admin.model.domain.BusinessUser;
import com.mcsm.hellapp.admin.model.service.AdminService;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthServlet extends HttpServlet {

    private AdminService service;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        try {
            InitialContext context = new InitialContext();
            service = (AdminService) context.lookup("java:app/admin.jar/admin-service");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

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

                resp.sendRedirect(ctx + "cabinet/partners");
                return;
            }
        }

        resp.sendRedirect(ctx + "login.html");
    }
}
