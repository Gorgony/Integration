package nl.hu.v2iac1.servlets;

import nl.hu.v2iac1.domain.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Nathan on 13/02/2015.
 */
public class indexServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        User user = (User)request.getSession().getAttribute("user");
        if(user == null){
            user = new User(0);
        }
        out.println("<html><head><script src=\"/int/javascript.js\"></script><link rel=\"stylesheet\" type=\"text/css\" href=\"/int/style.css\"></head><body>");
        if(request.getAttribute("error") != null){
            out.println("<div class=\"isa_error\">\n" +
                    "   <i class=\"fa fa-times-circle\"></i>\n" +
                     request.getAttribute("error") + "\n" +
                    "</div>");
        }
        if(request.getAttribute("succes") != null){
            out.println("<div class=\"isa_success\">\n" +
                    "   <i class=\"fa fa-check\"></i>\n" +
                    request.getAttribute("succes") + "\n" +
                    "</div>");
        }
        out.println("<form id=\"form\" method=\"post\" action=\"/int/verifyLogin.do\">");
        out.println("<input id=\"hidden\" name=\"hiddenInput\" type=\"hidden\" value=\"\" />");
        if(user.getLevel() < 1){
            out.println("<input type=\"text\" name=\"account\"><br/>");
            out.println("<input type=\"password\" name=\"password\"><br/>");
            out.println("<input type=\"submit\" value=\"Submit\">");
        }
        out.println("</form>");
        if(user.getLevel() < 2) {
            out.println("<fb:login-button scope=\"public_profile\" onlogin=\"checkLoginState();\">\n</fb:login-button>");
        }
        if(user.getLevel() > 0){
            out.println("<button onclick=\"location.href='/int/logout.do'\">Logout</button>");
        }
        out.println("<h2>Muhaha!</h2>\n" +
                "<ul>\n" +
                "    <li>Deze <a href=\"rest/public/\">link</a> mag publiek zijn.</li>\n" +
                "    <li>Deze <a href=\"rest/secret/\">link</a> moet met een username/password beveiligd worden. (+3 pt)</li>\n" +
                "    <li>Deze <a href=\"rest/verysecret\">link</a> moet via een externe identity provider beveiligd worden. (+3 pt)</li>\n" +
                "    <li>Deze <a href=\"rest/topsecret/\">link</a> moet met two-factor authenticatie beveiligd worden. (+3 pt)</li>\n" +
                "</ul>\n" +
                "</body>\n" +
                "</html>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
