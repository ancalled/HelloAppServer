package com.mcsm.hellapp.customer.view;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mcsm.hellapp.customer.model.service.CustomerService;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ApplyDiscountServlet extends HttpServlet {

    private CustomerService service;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        try {
            InitialContext context = new InitialContext();
            service = (CustomerService) context.lookup("java:app/customer-api.jar/customer-service");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        System.out.println("ApplyDiscountServlet.doPost");

        String uidStr = req.getParameter("userId");
        String didStr = req.getParameter("discountId");

        long userId = Long.parseLong(uidStr);
        long discountId = Long.parseLong(didStr);

        System.out.println("\tuserId = " + userId);
        System.out.println("\tdiscountId = " + discountId);


        Long appliedId = service.applyDiscount(userId, discountId);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();

        if (appliedId != null) {
            out.print(gson.toJson(new Result(Status.OK, appliedId)));
        } else {
            out.print(gson.toJson(new Result(Status.ERRORS, null)));
        }

        out.close();
    }


    public static enum Status {
        OK, ERRORS
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
