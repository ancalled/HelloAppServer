package kz.helloapp.view.customer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import kz.helloapp.model.domain.Campaign;
import kz.helloapp.model.domain.CampaignStat;
import kz.helloapp.model.domain.CustomerUser;
import kz.helloapp.model.domain.PartnerConfirmer;
import kz.helloapp.model.service.CustomerService;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ApplyCampaignServlet extends HttpServlet {

    private CustomerService customerService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        try {
            InitialContext context = new InitialContext();
            customerService = (CustomerService) context.lookup("java:app/helloapp.jar/customer-service");

        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        System.out.println("ApplyCampaignServlet.doPost");

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();

        String uidStr = req.getParameter("userId");
        String cmpIdStr = req.getParameter("campaignId");
        String confirmerCode = req.getParameter("confirmerCode");

        long userId = Long.parseLong(uidStr);
        long campId = Long.parseLong(cmpIdStr);

        System.out.println("\tuserId = " + userId);
        System.out.println("\tdiscountId = " + campId);
        System.out.println("\tconfirmerCode = " + confirmerCode);

        PartnerConfirmer confirmer = customerService.getConfirmer(confirmerCode);
        CustomerUser user = customerService.getUser(userId);
        Campaign campaign = customerService.getCampaign(campId);


        Status status;
        Long appliedId = null;

        if (confirmer == null) {
            status =  Status.NO_CONFIRMER_FOUND;
        } else if (user == null) {
            status =  Status.NO_USER_FOUND;
        } else if (campaign == null) {
            status =  Status.NO_DISCOUNT_FOUND;
        } else {

            CampaignStat stat = new CampaignStat();
            stat.setCampaign(campaign);
            stat.setUser(user);
            stat.setConfirmer(confirmer);

            stat = customerService.saveCampaignStat(stat);

            if (stat != null) {
                status = Status.OK;
                appliedId = stat.getId();
            } else {
                status = Status.COULD_NOT_APPLY;
            }
        }

        out.print(gson.toJson(new Result(status, appliedId)));
        out.close();
    }


    public static enum Status {
        OK, NO_USER_FOUND, NO_DISCOUNT_FOUND, NO_CONFIRMER_FOUND, COULD_NOT_APPLY
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
