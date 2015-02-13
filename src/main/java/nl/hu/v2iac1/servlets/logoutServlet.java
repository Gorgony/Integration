package nl.hu.v2iac1.servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Nathan on 13/02/2015.
 */
public class logoutServlet extends HttpServlet{
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd;
        request.getSession().setAttribute("user",null);
        request.setAttribute("succes","U bent succesvol uitgelogd.");
        rd = request.getRequestDispatcher("/index.do");
        rd.forward(request,response);
    }
}
