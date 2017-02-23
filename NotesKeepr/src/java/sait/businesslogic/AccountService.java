package sait.businesslogic;

import com.sun.media.jfxmedia.logging.Logger;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.UUID;
import java.util.logging.Level;
import javax.mail.MessagingException;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import sait.dataaccess.UserRepository;
import sait.domainmodel.User;

/**
 * @author awassill
 */
public class AccountService {
    public String login(HttpServletRequest request, String userName, String password)
    {
        HttpSession session = request.getSession();
        
        String message;
        
        if(userName == null || password == null || userName.equals("") || password.equals(""))
        {
            message = "Both values are required.";
            request.setAttribute("username", userName);
            session.invalidate();
            return message;
        }
        
        UserRepository ur = new UserRepository();
        User user = ur.getUser(userName);
        
        if(user == null)
        {
            message = "Unvalid username or password.";
            request.setAttribute("username", userName);
            session.invalidate();
            return message;
        }
        
        if(!user.getUserPassword().equals(password))
        {
            message = "Unvalid username or password.";
            request.setAttribute("username", userName);
            session.invalidate();
            return message;
        }
        
        if(!user.getRoleList().isEmpty())
        {
            session.setAttribute("isAdmin", true);
        }
        
        session.setAttribute("currentUser", user);
        message = "Valid user.";
        return message;
    }
    
    public void logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
    }
    
    public int delete(String userName, User currentUser) throws Exception {
        if(currentUser.getUserName().equals(userName))
        {
            UserRepository ur = new UserRepository();
            User user = ur.getUser(userName);
            return ur.delete(user);
        }
        return -1;
    }
    
    public void impersonate(HttpServletRequest request, String username, User currentUser)
    {
        if(!currentUser.getUserName().equals(username))
        {
            logout(request);
            UserRepository ur = new UserRepository();
            User user = ur.getUser(username);
            login(request, user.getUserName(), user.getUserPassword());
            String template = request.getServletContext().getRealPath("/WEB-INF/emailtemplates/support.html");
            HashMap<String, String> contents = new HashMap<>();
            contents.put("firstname", user.getFirstName());
            contents.put("username", user.getUserName());

            try {
                WebMailService.send(template, contents, user.getEmail(), "Notes Keepr Support");
            } catch (MessagingException | NamingException | FileNotFoundException ex) {
                Logger.logMsg(Level.SEVERE.intValue(), ex.getMessage());
            }
        }
    }
    
    public boolean sendPasswordResetEmail(HttpServletRequest request, String email)
    {
        UserRepository us = new UserRepository();
        User user = us.getUserByEmail(email);
        
        String template = request.getServletContext().getRealPath("/WEB-INF/emailtemplates/resetpassword.html");
        String uuid = UUID.randomUUID().toString();
        HashMap<String, String> contents = new HashMap<>();
        contents.put("firstname", user.getFirstName());
        contents.put("lastname", user.getLastName());
        contents.put("username", user.getUserName());
        contents.put("link", request.getRequestURL().toString() + "?uuid=" + uuid);
        
        try {
            WebMailService.send(template, contents, user.getEmail(), "Password Reset");
            user.setResetPasswordUUID(uuid);
            UserRepository ur = new UserRepository();
            ur.update(user);
            return true;
        } catch (MessagingException | NamingException | FileNotFoundException ex) {
            java.util.logging.Logger.getLogger(AccountService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean changePassword(String uuid, String password)
    {
        UserRepository ur = new UserRepository();
        
        try
        {
            User user = ur.getUserByUUID(uuid);
            user.setUserPassword(password);
            user.setResetPasswordUUID(null);
            ur.update(user);
            return true;
        }
        catch(Exception ex)
        {
            return false;
        }
    }
}
