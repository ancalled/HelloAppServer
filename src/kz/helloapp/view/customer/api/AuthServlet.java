package kz.helloapp.view.customer.api;

import com.google.gson.Gson;
import kz.helloapp.model.domain.AuthToken;
import kz.helloapp.model.domain.CustomerUser;
import kz.helloapp.model.service.CustomerService;
import org.apache.log4j.Logger;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class AuthServlet extends HttpServlet {

    private static final Logger log = Logger.getLogger(AuthServlet.class);


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

        log.info("Authorizing user " + login);

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();

        CustomerUser user = service.getUser(login);

        Result res;
        if (user != null) {
            if (pass.equals(user.getPass())) {
                log.info("Authorized.");
                AuthToken authToken = user.getAuthToken();
                if (authToken != null) {
                    res = new Result(Status.OK, user.getName(), user.getId(), authToken.getToken());
                } else {
                    res = new Result(Status.FAIL);
                }
            } else {
                log.info("Wrong pass!");
                res = new Result(Status.FAIL);
            }
        } else {
            log.info("User not found!");
            res = new Result(Status.FAIL);
        }

        Gson gson = new Gson();
        out.print(gson.toJson(res));

        out.close();
    }


    public static enum Status {NONE, OK, FAIL}

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
