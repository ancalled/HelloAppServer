package kz.helloapp.view.customer.api;

import com.google.gson.Gson;
import kz.helloapp.model.domain.AuthToken;
import kz.helloapp.model.domain.CustomerUser;
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

public class RegisterAndAuthServlet extends HttpServlet {

    private CustomerService service;

    @Override
    public void init(ServletConfig config) throws ServletException {
        try {
            InitialContext context = new InitialContext();
            service = (CustomerService) context.lookup("java:app/helloapp.jar/customer-service");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String login = req.getParameter("l");
        String pass = req.getParameter("p");

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();

        CustomerUser user = service.getUser(login);

        Result res;
        if (user == null) {
            user = new CustomerUser();
            user.setName(login);
            user.setPass(pass);
            user = service.saveUser(user);

            if (user != null) {

                AuthToken authToken = user.getAuthToken();
                if (authToken != null) {
                    res = new Result(Status.OK, login, user.getId(),
                            authToken.getToken());
                } else {
                    res = new Result(Status.FAIL);

                }
            } else {
                res = new Result(Status.FAIL);

            }

        } else {
            res = new Result(Status.USER_ALREADY_EXISTS);
        }

        Gson gson = new Gson();
        out.print(gson.toJson(res));

        out.close();
    }


    public static enum Status {NONE, USER_ALREADY_EXISTS, FAIL, OK}

    public static class Result {

        private final Status status;
        private final String login;
        private final Long userId;
        private final String token;

        public Result(Status status) {
            this.status = status;
            login = null;
            userId = null;
            token = null;
        }

        public Result(Status status, String login, long userId, String token) {
            this.status = status;
            this.login = login;
            this.userId = userId;
            this.token = token;
        }

        public Status getStatus() {
            return status;
        }

        public String getLogin() {
            return login;
        }

        public Long getUserId() {
            return userId;
        }

        public String getToken() {
            return token;
        }
    }
}
