package kz.helloapp.view.customer.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import kz.helloapp.model.domain.Campaign;
import kz.helloapp.view.customer.CustomerServlet;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class CampaignsServlet extends CustomerServlet {

    private static final Logger log = Logger.getLogger(CampaignsServlet.class);


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        log.info("Requesting list of campaigns...");

        List<Campaign> campaigns = service.getAllCampaigns();

        Gson gson = new GsonBuilder().setPrettyPrinting()
                .setDateFormat("yyyy-MM-dd")
                .create();

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        PrintWriter out = resp.getWriter();
        out.print(gson.toJson(campaigns));

        out.close();
    }


}
