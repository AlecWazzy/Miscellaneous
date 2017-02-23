package sait.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sait.businesslogic.AccountService;

/**
 * @author awassill
 */
public class ResetPasswordServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String uuid = request.getParameter("uuid");
        
        if(uuid != null)
        {
            request.setAttribute("uuid", uuid);
            getServletContext().getRequestDispatcher("/WEB-INF/resetNewPassword.jsp").forward(request, response);
            return;
        }
        
        getServletContext().getRequestDispatcher("/WEB-INF/reset.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        AccountService as = new AccountService();
        
        if(action == null)
            action = "";
        
        switch (action) {
            case "sendemail":
                String email = request.getParameter("email");
                
                try {
                     if(as.sendPasswordResetEmail(request, email))
                    {
                    request.setAttribute("message", "Email successfully sent.");
                    }
                    else
                    {
                        request.setAttribute("message", "Error! Email not sent.");
                    }
                }
                catch (Exception ex) {
                    request.setAttribute("message", "Error! Email not sent.");
                }
               
                getServletContext().getRequestDispatcher("/WEB-INF/reset.jsp").forward(request, response);
                break;
            case "resetpassword":
                String uuid = request.getParameter("uuid");
                String password = request.getParameter("password");
                if(as.changePassword(uuid, password))
                {
                    request.setAttribute("message", "Password change successful.");
                }
                else
                {
                    request.setAttribute("message", "Password change unsuccessful.");
                }
                getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
                break;
        }
    }
}
