/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RestServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

/**
 *
 * @author vic
 */
public class MessageUploadServlet extends HttpServlet {

 @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sender = req.getParameter("sender");
        String receiver = req.getParameter("receiver");
        String message = req.getParameter("message");
        
        
            PrintWriter out = resp.getWriter();
            JSONObject js= new JSONObject();
            //JSONArray ja = new JSONArray();
            
            try {
                    Class.forName("com.mysql.jdbc.Driver");
                    
                    Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/studentregsystem", "root", "rat");
                    
                    PreparedStatement ps = con.prepareStatement("insert into messages(sender, receiver, timestamp, status, message)values(?,?,?,?,?)");
                    ps.setString(1, sender);
                    ps.setString(2, receiver);
                    ps.setString(3, System.currentTimeMillis()+"");
                    ps.setString(4, ""+0);
                    ps.setString(5, message);
                    
                    int rs = ps.executeUpdate();
                    
                    if (rs>0) {
                             js.put("response", "Yes");
                        }
                    else
                    {
                        js.put("response", "No");
                    }
                
                    out.write(js.toString());
                } catch (Exception e) {
                    System.out.println(e.getLocalizedMessage());
                    System.out.println("Exception");
                    out.write(e.getLocalizedMessage());
                }
 
    }
      
}
