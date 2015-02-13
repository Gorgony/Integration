package nl.hu.v2iac1.servlets;

import nl.hu.v2iac1.domain.User;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;

public class SecurityFilter implements Filter {
    public void init(FilterConfig arg0) throws ServletException {}

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        RequestDispatcher rd;
        String[] parts = req.getRequestURI().split("/");
        String requestUrl = parts[parts.length-1];
        if(requestUrl.equals("")){
            requestUrl = parts[parts.length-2];
        }
        System.out.println(requestUrl);
        boolean correctLogin = false;
        String message = "";
        if(requestUrl.equals("public") || requestUrl.equals("javascript.js") || requestUrl.equals("style.css")){
            correctLogin = true;
        } else {
            if (req.getSession().getAttribute("user") == null) {
                message = "Je moet ingelogd zijn";
            } else {
                User user = (User) req.getSession().getAttribute("user");
                if(requestUrl.equals("secret")){
                    if (user.getLevel() > 0) {
                        correctLogin = true;
                    } else {
                        message = "Voor deze rest service moet je minstens ingelogd zijn met een account/password combinatie.";
                    }
                } else if(requestUrl.equals("verysecret")){
                    if (user.getLevel() > 1) {
                        correctLogin = true;
                    } else {
                        message = "Voor deze rest service moet je minstens ingelogd zijn met facebook.";
                    }
                } else if(requestUrl.equals("topsecret")){
                    if (false) {
                        correctLogin = true;
                    } else {
                        message = "Voor deze restservice hebben we nog geen beveiliging kunnen implementeren.";
                    }
                }
            }
        }
        if(correctLogin) {
            chain.doFilter(request, response);
        } else {
            System.out.println(message);
            request.setAttribute("error",message);
            rd = request.getRequestDispatcher("/index.do");
            rd.forward(request,response);
        }
    }

    public void destroy() {}
}