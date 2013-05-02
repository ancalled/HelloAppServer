package kz.helloapp.view.customer.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import kz.helloapp.model.domain.CustomerUser;
import kz.helloapp.view.customer.HashUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


public class LoginPassAuthServlet extends CampaignsServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String login = req.getParameter("login");
        String pass = req.getParameter("pass");

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");


        Status status;
        String token = null;

        CustomerUser user = service.getUser(login);
        if (user != null) {
//            String passHash = HashUtil.getMD5Hash(user.getPass());
//            if (passHash.equals(pass)) {

            if (user.getPass().equals(pass)) {

                status = Status.AUTHORIZED;
                token = HashUtil.generateToken();
                service.setAuthToken(user, token);

            } else {
                status = Status.WRONG_PASS;
            }

        } else {
            status = Status.WRONG_USER;
        }

        PrintWriter out = resp.getWriter();
        out.print(gson.toJson(new Result(status, token)));

        out.close();

    }

    public static enum Status {
        AUTHORIZED, WRONG_USER, WRONG_PASS
    }

    public static class Result {
        private Status status;
        private String token;

        public Result(Status status, String token) {
            this.status = status;
            this.token = token;
        }

        public Status getStatus() {
            return status;
        }

        public void setStatus(Status status) {
            this.status = status;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
