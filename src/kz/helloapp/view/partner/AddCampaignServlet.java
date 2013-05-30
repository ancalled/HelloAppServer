package kz.helloapp.view.partner;

import kz.helloapp.model.domain.Campaign;
import kz.helloapp.model.domain.PartnerUser;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class AddCampaignServlet extends PartnerServlet {

    public static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    public static final String userDir = System.getProperty("user.dir");
    public static final String IMAGE_DIR = "F:\\Projects\\HelloAppServer\\web\\images\\camp-prev";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        List<FileItem> items = null;
        String ctx = req.getContextPath();
        if (!ctx.endsWith("/")) ctx = ctx + "/";

        try {
            items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(req);

            if (items == null || items.isEmpty()) {
                resp.sendRedirect(ctx + "view/campaigns");
                return;
            }

            String title = getParameter(req, items, "title");
            String description = getParameter(req, items, "descr");
            String rateStr = getParameter(req, items, "rate");
            String startFromStr = getParameter(req, items, "from");
            String goodThroughStr = getParameter(req, items, "to");

            if (title != null && description != null &&
                    rateStr != null &&
                    startFromStr != null &&
                    goodThroughStr != null) {

                PartnerUser user = (PartnerUser) req.getSession().getAttribute("user");

                if (user != null && user.getCompany() != null) {
                    saveIcon(req, items, user);

                    Campaign c = new Campaign();
                    c.setTitle(title);
                    c.setDescription(description);
                    c.setRate(Integer.parseInt(rateStr));
                    c.setCompany(user.getCompany());
                    c.setStartFrom(DATE_FORMAT.parse(startFromStr));
                    c.setGoodThrough(DATE_FORMAT.parse(goodThroughStr));

                    service.addCampaign(c);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        resp.sendRedirect(ctx + "view/campaigns");
    }

    private String getParameter(HttpServletRequest req, List<FileItem> items,
                                String paramName)
            throws IOException {
        for (FileItem item : items) {
            if (item.isFormField()) {
                String name = item.getFieldName();
                if (name.equals(paramName)) {
                    byte[] value = new byte[(int) item.getSize()];
                    item.getInputStream().read(value);
                    return new String(value);
                }
            }
        }

        return null;
    }

    private void saveIcon(HttpServletRequest req, List<FileItem> items,
                          PartnerUser user) throws Exception {
        for (FileItem item : items) {
            if (!item.isFormField()) {
                File file = new File(IMAGE_DIR + "\\" + user.getId());
                item.write(file);
            }
        }

    }
}
