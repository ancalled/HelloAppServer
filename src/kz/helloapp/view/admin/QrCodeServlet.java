package kz.helloapp.view.admin;

import kz.helloapp.model.domain.PartnerConfirmer;
import kz.helloapp.model.domain.PartnerUser;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

public class QRCodeServlet extends AdminServlet{


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {


        PartnerUser user = (PartnerUser) req.getSession().getAttribute("user");
//
        if (user != null && user.getCompany() != null) {

            System.out.println("QRCode servlet");

            Long confId = Long.parseLong(req.getParameter("cid"));
            PartnerConfirmer confirmer = service.getConfirmer(confId);

            if (confirmer != null &&
                    confirmer.getCompany() != null &&
                    confirmer.getCompany().getId().equals(user.getCompany().getId())) {

                byte[] data = QRCode.from(confirmer.getCode())
                        .to(ImageType.PNG).withSize(512, 512)
                        .stream().toByteArray();

                resp.setContentType("image/png");
                resp.setContentLength(data.length);
                OutputStream out = resp.getOutputStream();
                out.write(data);
                out.flush();
                out.close();
            }
        }
    }
}
