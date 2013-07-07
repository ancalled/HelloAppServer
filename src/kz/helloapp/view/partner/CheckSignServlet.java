package kz.helloapp.view.partner;


import kz.helloapp.model.domain.Campaign;
import kz.helloapp.model.domain.CustomerUser;
import kz.helloapp.model.domain.PartnerUser;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CheckSignServlet extends PartnerServlet {

    public static final int SIGN_LENGTH = 7;


    private MessageDigest md;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String campIdStr = req.getParameter("camp");
        String login = req.getParameter("user");
        String priceStr = req.getParameter("prc");
        String signCode = req.getParameter("code");

        String ctx = req.getContextPath();
        if (!ctx.endsWith("/")) ctx = ctx + "/partner/";

        if (campIdStr == null || login == null || priceStr == null || priceStr.isEmpty()) {
            resp.sendRedirect(ctx + "view/check-sign?er=wrong-params");
            return;
        }

        long campId = Long.parseLong(campIdStr);
        int price = Integer.parseInt(priceStr);

        HttpSession session = req.getSession();
        PartnerUser partnerUser = (PartnerUser) session.getAttribute("user");

        if (partnerUser == null) {
            resp.sendRedirect(ctx + "view/check-sign?er=no-partner-user");
            return;
        }

        Campaign camp = service.getCampaign(campId);
        if (camp == null) {
            resp.sendRedirect(ctx + "view/check-sign?er=wrong-campaign");
            return;
        }

        if (!camp.getCompany().getId().equals(partnerUser.getCompany().getId())) {
            resp.sendRedirect(ctx + "view/check-sign?er=wrong-campaign");
            return;
        }

        CustomerUser user = service.getCustomerUser(login);
        if (user == null) {
            resp.sendRedirect(ctx + "view/check-sign?er=wrong-user");
            return;
        }


        String data = campId + ":" + user.getId() + ":" +
                user.getAuthToken().getToken() + ":" + price;


        String actHash = null;
        try {
            actHash = calcHash(data);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        if (actHash != null && actHash.length() > SIGN_LENGTH) {
            actHash = actHash.substring(actHash.length() - SIGN_LENGTH, actHash.length());

            if (actHash.equals(signCode)) {
                //todo ok
                resp.sendRedirect(ctx + "view/check-sign?checked=yes");
            } else {
                resp.sendRedirect(ctx + "view/check-sign?er=wrong-hash");
            }

        } else {
            resp.sendRedirect(ctx + "view/check-sign?er=wrong-hash");
        }


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



}
