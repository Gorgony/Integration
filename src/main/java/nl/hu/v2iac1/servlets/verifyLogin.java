package nl.hu.v2iac1.servlets;

import nl.hu.v2iac1.Configuration;
import nl.hu.v2iac1.domain.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Nathan on 12/02/2015.
 */
public class verifyLogin extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd;
        Configuration configuration = new Configuration();
        boolean correct = false;
        String path = "/index.do";
        if(request.getSession().getAttribute("path") != null){
            path = request.getSession().getAttribute("path").toString();
        }

        User user = (User)request.getSession().getAttribute("user");
        if(user == null){
            user = new User(0);
        }
        if(request.getParameter("account") != null && request.getParameter("password") != null) {
            String account = request.getParameter("account");
            String password = request.getParameter("password");
            if (account.equals(configuration.getValue(Configuration.Key.ACCOUNT)) && password.equals(configuration.getValue(Configuration.Key.PASSWORD)) && user.getLevel() < 1) {
                user.setLevel(1);
                correct = true;
            }
        }
        if(request.getParameter("hiddenInput") != null) {
            String userID = request.getParameter("hiddenInput");
            if (userID.equals(configuration.getValue(Configuration.Key.USERID)) && user.getLevel() < 2) {
                user.setLevel(2);
                correct = true;
            }
        }
        if(correct){
            request.getSession().setAttribute("user",user);
            request.setAttribute("succes","U bent succesvol ingelogd");
            rd = request.getRequestDispatcher(path);
            rd.forward(request,response);
        } else {
            request.setAttribute("error","De login was incorrect");
            rd = request.getRequestDispatcher("index.do");
            rd.forward(request,response);
        }
    }
}
