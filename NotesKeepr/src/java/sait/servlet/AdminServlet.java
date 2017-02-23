package sait.servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sait.businesslogic.AccountService;
import sait.businesslogic.AdminService;
import sait.domainmodel.User;

/**
 * @author awassill
 */
public class AdminServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        
        String action = request.getParameter("action");
        
        if(action != null && action.equals("logout"))
        {
            AccountService as = new AccountService();
            as.logout(request);
            request.setAttribute("message", "Logged out");
            response.sendRedirect("login");
            return;
        }
        
        AdminService us = new AdminService();
        
        List userList = null;
        try {
            userList = us.getAll();
        } catch (Exception ex) {
            request.setAttribute("message", ex.toString());
        }
        
        request.setAttribute("logoutlink","<a href=\"admin?action=logout\">Logout</a>");
        request.setAttribute("users", userList);
        request.getRequestDispatcher("/WEB-INF/admin/users.jsp").forward(request, response);
    }
    
    @Override            
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        
        User user = (User) session.getAttribute("currentUser");
        
        String action = request.getParameter("action");

        String username = request.getParameter("username");
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        AdminService ads = new AdminService();
        AccountService acs = new AccountService();
        
        if (action == null)
        {
            action = "";
        }
        switch (action) {
            case "view":
                try {
                    User selectedUser = ads.get(username);
                    request.setAttribute("selectedUser", selectedUser);
                } catch (Exception ex) {
                }
                break;
            case "add":
                try {
                    ads.insert(username, firstname, lastname, email, password);
                    request.setAttribute("message", "User added.");
                } catch (Exception ex) {
                    request.setAttribute("message", "Error: User could not be added!"); 
                }
                break;
            case "delete":
                try {
                    if(!username.equals(user.getUserName()))
                    {
                        ads.delete(username, user);
                        request.setAttribute("message", "User deleted.");
                    }
                } catch (Exception ex) {
                    request.setAttribute("message", "Error: User could not be deleted!");
                }
                break;
            case "edit":
                try {
                    ads.update(username, firstname, lastname, email, password);
                    request.setAttribute("message", "User updated.");
                } catch (Exception ex) {
                    request.setAttribute("message", "Error: User could not be updated!");
                }   
                break;
            case "impersonate":
                try {
                    acs.impersonate(request, username, user);
                    response.sendRedirect("notes");
                    return;
                }
                catch (Exception ex) {
                    request.setAttribute("message", "Error: User could not be impersonated.");
                }   break;
            case "promote":
                try {
                    ads.promote(username, user);
                    request.setAttribute("message", "User promoted to administrator.");
                }
                catch (Exception ex) {
                    request.setAttribute("message", "Error: User could not be promoted.");
                }
                break;
            case "demote":
                try {
                    ads.demote(username, user);
                    request.setAttribute("message", "Adminstrator demoted to user.");
                }
                catch (Exception ex) {
                    request.setAttribute("message", "Error: Administrator could not be demoted.");
                }
                break;
        }
        
        try {
            List<User> users = ads.getAll();
            request.setAttribute("users", users);
        } catch (Exception ex) {
            
        }
        
        request.setAttribute("logoutlink","<a href=\"admin?action=logout\">Logout</a>");
        request.getRequestDispatcher("/WEB-INF/admin/users.jsp").forward(request, response);
    }
}
