package kz.helloapp.view.admin;

import kz.helloapp.model.domain.PartnerCompany;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RemovePartnerServlet extends AdminServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String partnerIdStr = req.getParameter("partner");

        String ctx = req.getContextPath();
        if (!ctx.endsWith("/")) ctx = ctx + "/";

        long partnerId = Long.parseLong(partnerIdStr);
        PartnerCompany partner = service.removePartner(partnerId);

        if (partner != null) {
            resp.sendRedirect(ctx + "admin/view/partners?mess=partner-deleted&partner=" + partner.getName());
        }else{
            resp.sendRedirect(ctx + "admin/view/partners?mess=no-deleted");
        }

    }
}
