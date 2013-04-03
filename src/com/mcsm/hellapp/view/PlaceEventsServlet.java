package com.mcsm.hellapp.view;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mcsm.hellapp.model.domain.Event;
import com.mcsm.hellapp.model.domain.Place;
import com.mcsm.hellapp.model.service.HelloAppCatalog;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

public class PlaceEventsServlet extends HttpServlet {

    private HelloAppCatalog catalog;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        try {
            InitialContext context = new InitialContext();
            catalog = (HelloAppCatalog) context.lookup("java:app/HelloAppServer.jar/helloApp");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        System.out.println("CatPlacesServlet.doGet");

        String place = req.getPathInfo().substring(1);

        System.out.println("place = " + place);

        Date from = new Date();


        List<Event> events = catalog.getPlaceEvents(place, from);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        PrintWriter out = resp.getWriter();
        out.print(gson.toJson(events));

        out.close();
    }


}
