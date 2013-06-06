package kz.helloapp.view.partner;


import kz.helloapp.model.domain.Campaign;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RemoveCampaignServlet extends PartnerServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String strId = req.getParameter("camp-id");
        String ctx = req.getContextPath();
        if (!ctx.endsWith("/")) ctx = ctx + "/";
        Long id = Long.parseLong(strId);

        Campaign camp = service.removeCampaign(id);
        if (camp != null) {
            resp.sendRedirect(ctx + "partner/view/campaigns?mess=camp-deleted&camp=" + camp.getTitle());
            return;
        }
        resp.sendRedirect(ctx + "partner/view/campaigns?mess=camp-not-deleted");
    }
}
