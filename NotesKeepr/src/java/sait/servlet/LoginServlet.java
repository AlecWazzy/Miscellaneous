/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sait.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sait.businesslogic.AccountService;
import sait.domainmodel.User;

/**
 * @author awassill
 */
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        AccountService as = new AccountService();
        
        String message = as.login(request, username, password);
        
        HttpSession session = request.getSession();
        
        User user = (User) session.getAttribute("currentUser");
        
        if (message.equals("Valid user.")) {
            if(!user.getRoleList().isEmpty())
            {
                response.sendRedirect("admin");
                return;
            }
            else{
                response.sendRedirect("notes");
                return;
            }
        } else {
            request.setAttribute("message", message);
        }
        
        getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
    }
}
