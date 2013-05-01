package kz.helloapp.view.customer;

import kz.helloapp.model.service.CustomerService;
import kz.helloapp.view.Consts;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;


public abstract class CustomerServlet extends HttpServlet {

    protected CustomerService service;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        try {
            InitialContext context = new InitialContext();
            service = (CustomerService) context.lookup(Consts.CUSTOMER_SERVICE_NAME);
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
}
