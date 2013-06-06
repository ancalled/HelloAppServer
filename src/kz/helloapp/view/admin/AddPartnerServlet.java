package kz.helloapp.view.admin;


import kz.helloapp.model.domain.PartnerCompany;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddPartnerServlet extends AdminServlet {

    @Override
    public void init() throws ServletException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String ctx = req.getContextPath();
        if (!ctx.endsWith("/")) ctx = ctx + "/";
        String title = req.getParameter("title");
        if (title != null) {
            PartnerCompany partner = new PartnerCompany();
            partner.setName(title);
            service.createPartner(partner);
        }
        resp.sendRedirect(ctx + "/admin/view/partners");
    }
}
