package kz.helloapp.view.admin;

import kz.helloapp.model.service.AdminService;
import kz.helloapp.view.Consts;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class AdminServlet extends HttpServlet {

    protected AdminService service;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        try {
            InitialContext context = new InitialContext();
            service = (AdminService) context.lookup(Consts.ADMIN_SERVICE_NAME);
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
}
