package kz.helloapp.view.partner;

import kz.helloapp.model.domain.PartnerUser;
import kz.helloapp.model.domain.Campaign;
import kz.helloapp.model.service.PartnerService;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class AddCampaignServlet extends PartnerServlet {

    public static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String title = req.getParameter("title");
        String description = req.getParameter("descr");
        String rateStr = req.getParameter("rate");
        String startFromStr = req.getParameter("from");
        String goodThroughStr = req.getParameter("to");

        String ctx = req.getContextPath();
        if (!ctx.endsWith("/")) ctx = ctx + "/";


        if (title != null && description != null && rateStr != null && startFromStr != null && goodThroughStr != null) {

            PartnerUser user = (PartnerUser) req.getSession().getAttribute("user");

            if (user != null && user.getCompany() != null) {

                Campaign c = new Campaign();
                c.setTitle(title);
                c.setDescription(description);
                c.setRate(Integer.parseInt(rateStr));
                c.setCompany(user.getCompany());

                try {
                    c.setStartFrom(DATE_FORMAT.parse(startFromStr));
                    c.setGoodThrough(DATE_FORMAT.parse(goodThroughStr));
                } catch (ParseException e) {
                    e.printStackTrace();
                    return;
                }

                service.addCampaign(c);

                resp.sendRedirect(ctx + "cabinet/campaigns");
            }

        }


    }
}
