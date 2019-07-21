/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RestServlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author vishal
 */
public class emailVer extends HttpServlet {
    
    private String host="smtp.gmail.com";
    private String port="587";
    private String email="vishaljangid8140@gmail.com";
    private String password="7976324569";

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        String recipient=req.getParameter("recipient");
        String subject="email verify";
        
        String resultMessage="";
        try{
            
            String v="http://192.168.43.215:8084/RESTDEMO/emailVerified"+recipient;
            String vlink="click on the link to verified account"+v;
            
            EmailUtility.sendEmail(host, port, email, password, recipient, subject, vlink);
            
            resultMessage="success";
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        finally{
            
            PrintWriter printWriter=resp.getWriter();
            printWriter.println(resultMessage);
        }
    }
    
    
}
