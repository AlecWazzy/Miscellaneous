package sait.businesslogic;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Properties;
import java.util.Scanner;
import javax.mail.*;
import javax.mail.internet.*;
import javax.naming.*;

/**
 *
 * @author awassill
 */
public class WebMailService {
    public static void send(String template, HashMap<String, String> contents, String to, String subject) throws MessagingException, NamingException, FileNotFoundException {
        String body;
        
        // read in template
        body = new Scanner(new File(template)).useDelimiter("\\z").next();
        
        // replace all template parameters with what is in contents
        for(String key : contents.keySet()) {
            body = body.replace("{{" + key + "}}", contents.get(key));
        }
        
        sendMail(to, subject, body, true);
    }
    
    
    private static void sendMail(String to, String subject, String body, boolean bodyIsHTML) throws MessagingException, NamingException {
        Context env = (Context)new InitialContext().lookup("java:comp/env");
        String username = (String)env.lookup("webmail-username");
        String password = (String)env.lookup("webmail-password");
        
        Properties props = new Properties();
        props.put("mail.transport.protocol", "smtps");
        props.put("mail.smtps.host", "smtp.gmail.com");
        props.put("mail.smtps.port", 465);
        props.put("mail.smtps.auth", "true");
        props.put("mail.smtps.quitwait", "false");
        Session session = Session.getDefaultInstance(props);
        session.setDebug(true);
        
        // create a message
        Message message = new MimeMessage(session);
        message.setSubject(subject);
        if (bodyIsHTML) {
            message.setContent(body, "text/html");
        } else {
            message.setText(body);
        }
        
        // address the message
        Address fromAddress = new InternetAddress("cprg352@gmail.com");
        Address toAddress = new InternetAddress(to);
        message.setFrom(fromAddress);
        message.setRecipient(Message.RecipientType.TO, toAddress);
        
        // send the message
        Transport transport = session.getTransport();
        transport.connect(username, password);
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
    }
}
