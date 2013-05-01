package kz.helloapp.view.partner;

import kz.helloapp.model.service.PartnerService;
import kz.helloapp.view.Consts;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;


public  abstract class PartnerServlet extends HttpServlet{

    protected PartnerService service;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        try {
            InitialContext context = new InitialContext();
            service = (PartnerService) context.lookup(Consts.PARTNER_SERVICE_NAME);
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
}
