package kz.helloapp.view.partner;

import kz.helloapp.model.domain.Campaign;
import kz.helloapp.model.domain.PartnerConfirmer;
import kz.helloapp.model.domain.PartnerUser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class AddConfirmerServlet extends PartnerServlet {

    public static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String name = req.getParameter("title");

        String ctx = req.getContextPath();
        if (!ctx.endsWith("/")) ctx = ctx + "/";


        if (name != null) {

            PartnerUser user = (PartnerUser) req.getSession().getAttribute("user");

            if (user != null && user.getCompany() != null) {

                PartnerConfirmer c = new PartnerConfirmer();
                c.setName(name);
                c.setCompany(user.getCompany());

                service.addConfirmer(c);

                resp.sendRedirect(ctx + "view/confirmers");
            }

        }


    }
}
