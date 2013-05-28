package kz.helloapp.view.customer.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import kz.helloapp.model.domain.Campaign;
import kz.helloapp.model.domain.CampaignStat;
import kz.helloapp.model.domain.CustomerUser;
import kz.helloapp.model.domain.PartnerConfirmer;
import kz.helloapp.view.customer.CustomerServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ApplyDiscountServlet extends CustomerServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        System.out.println("ApplyDiscountServlet.doPost");

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();

        String uidStr = req.getParameter("uid");
        String cmpIdStr = req.getParameter("campaignId");
        String confirmerCode = req.getParameter("confirmerCode");

        long userId = Long.parseLong(uidStr);
        long campId = Long.parseLong(cmpIdStr);

        System.out.println("\tuserId = " + userId);
        System.out.println("\tdiscountId = " + campId);
        System.out.println("\tconfirmerCode = " + confirmerCode);

        CustomerUser user = service.getUser(userId);
        Campaign campaign = service.getCampaign(campId);


        if (campaign == null) {
            error(out, Status.NO_DISCOUNT_FOUND);
            return;
        }

        if (user == null) {
            error(out, Status.NO_USER_FOUND);
            return;
        }

        PartnerConfirmer confirmer = null;
        if (campaign.isNeedConfirm()) {
            confirmer = service.getConfirmer(confirmerCode);
            if (confirmer == null) {
                error(out, Status.NO_CONFIRMER_FOUND);
                return;
            }

            if (confirmer.getCompany() != null && campaign.getCompany() != null &&
                    !confirmer.getCompany().getId().equals(campaign.getCompany().getId())) {
                error(out, Status.CONFIRMER_IS_NOT_OF_THIS_COMPANY);
                return;
            }
        }

        CampaignStat stat = new CampaignStat();
        stat.setCampaign(campaign);
        stat.setUser(user);
        if (campaign.isNeedConfirm()) {
            stat.setConfirmer(confirmer);
        }

        stat = service.saveCampaignStat(stat);
        if (stat == null) {
            error(out, Status.COULD_NOT_APPLY);
            return;
        }

        out.print(gson.toJson(new Result(Status.OK, stat.getId())));
    }


    private void error(PrintWriter out, Status status) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        out.print(gson.toJson(new Result(status, null)));
    }


    public static enum Status {
        OK, NO_USER_FOUND,
        NO_DISCOUNT_FOUND,
        NO_CONFIRMER_FOUND,
        CONFIRMER_IS_NOT_OF_THIS_COMPANY,
        COULD_NOT_APPLY
    }

    public static class Result {
        private Status status;
        private Long appliedId;

        public Result(Status status, Long appliedId) {
            this.status = status;
            this.appliedId = appliedId;
        }

        public Status getStatus() {
            return status;
        }

        public Long getAppliedId() {
            return appliedId;
        }
    }

}
