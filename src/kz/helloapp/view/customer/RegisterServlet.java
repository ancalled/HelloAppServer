package kz.helloapp.view.customer;

import kz.helloapp.model.domain.CustomerUser;
import kz.helloapp.model.service.CustomerService;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterServlet extends HttpServlet {

    private CustomerService service;

    @Override
    public void init(ServletConfig config) throws ServletException {
        try {
            InitialContext context = new InitialContext();
            service = (CustomerService) context.lookup("java:app/helloapp.jar/customer-service");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String pass = req.getParameter("pass");

        CustomerUser user = new CustomerUser();
        user.setName(name);
        user.setPass(pass);
        user.setEmail(email);

        user = service.saveUser(user);

        resp.sendRedirect("../view/register-result?id=" + user.getId());

    }
}
