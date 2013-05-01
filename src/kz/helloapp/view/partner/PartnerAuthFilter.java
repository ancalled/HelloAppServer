package kz.helloapp.view.partner;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class PartnerAuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        if (req.getSession().getAttribute("user") == null) {
            //todo store path and redirect after login
            String ctx = req.getContextPath();
            if (!ctx.endsWith("/")) ctx = ctx + "/";

            resp.sendRedirect(ctx + "login-partner.html"); // Not logged in, redirect to login page.
        } else {
            chain.doFilter(request, response); // Logged in, just continue request.
        }
    }

    @Override
    public void destroy() {
    }
}
