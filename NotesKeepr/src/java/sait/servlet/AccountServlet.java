package sait.servlet;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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
@WebServlet(name = "AccountServlet", urlPatterns = {"/account"})
public class AccountServlet extends HttpServlet {
    
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
        
        AdminService as = new AdminService();
        HttpSession session = request.getSession();
        User sessionUser = (User) session.getAttribute("currentUser");
        User user;
        try {
            user = as.get(sessionUser.getUserName());
            request.setAttribute("currentUser", user);
        } catch (Exception ex) {
            Logger.getLogger(AccountServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        request.setAttribute("logoutlink","<a href=\"account?action=logout\">Logout</a>");
        getServletContext().getRequestDispatcher("/WEB-INF/account/account.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
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
        
        if(action != null && action.equals("edit"))
        {
            try {
                    ads.update(username, firstname, lastname, email, password);
                    session.setAttribute("currentUser", ads.get(username));
                    request.setAttribute("editfeedback", "Account updated.");
                } catch (Exception ex) {
                    request.setAttribute("editfeedback", "Error: Account could not be updated!");
                }
        }
        else if(action != null && action.equals("delete"))
        {
            try {
                    acs.delete(username, user);
                    acs.logout(request);
                    response.sendRedirect("login");
                    return;
                } catch (Exception ex) {
                    request.setAttribute("deletefeedback", "Error: Account could not be deleted!");
                }
        }
        
        request.setAttribute("logoutlink","<a href=\"account?action=logout\">Logout</a>");
        getServletContext().getRequestDispatcher("/WEB-INF/account/account.jsp").forward(request, response);
    }
}
