package kz.helloapp.view.customer.api;

import kz.helloapp.model.domain.CustomerUser;
import kz.helloapp.model.service.CustomerService;
import kz.helloapp.view.Consts;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.List;


public class VerifyFilter implements Filter {

    private CustomerService service;
    private MessageDigest md;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        try {
            InitialContext context = new InitialContext();
            service = (CustomerService) context.lookup(Consts.CUSTOMER_SERVICE_NAME);
        } catch (NamingException e) {
            e.printStackTrace();
        }

        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;


        String uidStr = req.getParameter("uid");
        String hash = req.getParameter("h");

        //todo test stuff!
        String ingoreVerify = req.getParameter("ignore");
        if ("ok".equals(ingoreVerify)) {
            chain.doFilter(request, response);
            return;
        }

        if (uidStr != null) {
            long uid = Long.parseLong(uidStr);
            CustomerUser user = service.getUser(uid);
            if (user != null && user.getAuthToken() != null) {
                String token = user.getAuthToken().getToken();

                String expected = buildParamsHash(req, token);
                if (hash.equals(expected)) {
                    chain.doFilter(request, response);
                    return;
                } else {
                    System.err.println("Wrong hash code! Expected: '" + expected + "', but got: '" + hash + "'");
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


    private String buildParamsHash(HttpServletRequest req, String token) {
        //noinspection unchecked
        List<String> params = Collections.list(req.getParameterNames());
        params.remove("h");

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

    private String calcHash(String data) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        byte[] bytes = data.getBytes("UTF-8");
        byte[] digest = md.digest(bytes);
        return toHex(digest);
//        return DatatypeConverter.printBase64Binary(digest);
    }

    public String toHex(byte[] bytes) {
        return String.format("%040x", new BigInteger(bytes));
    }

    private String decode(String text) {
        try {
            return URLDecoder.decode(text, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
