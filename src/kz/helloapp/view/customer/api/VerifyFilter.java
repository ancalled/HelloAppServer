package kz.helloapp.view.customer.api;

import kz.helloapp.model.domain.CustomerUser;
import kz.helloapp.model.service.CustomerService;
import kz.helloapp.view.Consts;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.List;


public class VerifyFilter implements Filter {

    private CustomerService service;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        try {
            InitialContext context = new InitialContext();
            service = (CustomerService) context.lookup(Consts.CUSTOMER_SERVICE_NAME);
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;


        String uidStr = req.getParameter("uid");
        String hash = req.getParameter("hash");

        if (uidStr != null) {
            long uid = Long.parseLong(uidStr);
            CustomerUser user = service.getUser(uid);
            if (user != null && user.getAuthToken() != null) {
                String token = user.getAuthToken().getToken();


                String checkHash = buildParamsHash(req, token);

                if (hash.equals(checkHash)) {
                    chain.doFilter(request, response);
                }

            }
        }


        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().append("{\"status\": \"auth-error\"}");
    }

    @Override
    public void destroy() {
    }


    public static String buildParamsHash(HttpServletRequest req, String token) {
        //noinspection unchecked
        List<String> params = Collections.list(req.getParameterNames());
        params.remove("hash");

        Collections.sort(params);

        StringBuilder buf = new StringBuilder();
        for (String key : params) {
            buf.append(key).append("=").append(req.getParameter(key)).append("&");
        }

        buf.append("token").append("=").append(token);

        String data = buf.toString();

        try {
            return calcHash(data);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String calcHash(String data) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] bytes = data.getBytes("UTF-8");
        byte[] digest = md.digest(bytes);
        return new String(digest, "UTF-8");

    }

}
