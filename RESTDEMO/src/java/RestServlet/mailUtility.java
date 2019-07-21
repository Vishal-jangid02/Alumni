/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RestServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author vishal
 */
public class mailUtility extends HttpServlet {
    
    public static void sendEmail(String host,String port,final String username,final String password,String toAddress,String Subject,String message) throws AddressException,MessagingException{
     
        Properties properties=new Properties();
        properties.put("mail.smtp.host",host);
        properties.put("mail.smtp.port",port);
        properties.put("mail.smtp.auth",true);
        properties.put("mail.smtp.starttls", true);
        
        Authenticator authenticator=new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(username, password);
            }
};
    
        Session session=Session.getInstance(properties,authenticator);
        
        Message msg=new MimeMessage(session);
       
        msg.setFrom(new InternetAddress(username));
      
        InternetAddress[] toAddresses={new InternetAddress(toAddress) };
        msg.setRecipients(Message.RecipientType.TO, toAddresses);
        
        msg.setSubject(Subject);
        msg.setSentDate(new Date());
        msg.setText(message);
        
        Transport.send(msg);
        
    }
}
