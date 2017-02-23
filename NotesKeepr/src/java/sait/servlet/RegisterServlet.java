package sait.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sait.businesslogic.AdminService;

/**
 * @author awassill
 */
public class RegisterServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        
        String action = request.getParameter("action");

        String username = request.getParameter("username");
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        AdminService us = new AdminService();
        
        if(action.equals("register"))
        {
            try {
                    us.insert(username, firstname, lastname, email, password);
                    session.setAttribute("currentUser", us.get(username));
                    response.sendRedirect("notes");
                    return;
                } catch (Exception ex) {
                    request.setAttribute("message", "Whoops! Please recheck your fields.");
                    request.setAttribute("username", username);
                    request.setAttribute("firstname", firstname);
                    request.setAttribute("lastname", lastname);
                    request.setAttribute("email", email);
                }
        }
        
        request.getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
    }
}
