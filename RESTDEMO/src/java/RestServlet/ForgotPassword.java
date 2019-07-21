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
 * @author vishal
 */
public class ForgotPassword extends HttpServlet {


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         String email = req.getParameter("email");
           
            
            PrintWriter out = resp.getWriter();
            JSONObject js= new JSONObject();
            //JSONArray ja = new JSONArray();
            
            
            try {
                    Class.forName("com.mysql.jdbc.Driver");
                    
                   Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/studentregsystem", "root", "rat");
                    
                    PreparedStatement ps = con.prepareStatement("select * from alumdata where email=?");
                    ps.setString(1, email);
                  
                    
                    ResultSet rs = ps.executeQuery();
                  
                    System.out.println(rs+"1222");
                    if (rs.next()) {
                        
                           
                             js.put("email", rs.getString(2));
                         
                             js.put("emailVerified", rs.getString(13));
                             
                        }
                    else
                    {
                        js.put("response", "No");
                        //js.put("reson", );
                    }
                    
                   
                    out.write(js.toString());
                } catch (Exception e) {
                    System.out.println(e.getLocalizedMessage());
                    System.out.println("Exception");
                    out.write(e.getLocalizedMessage());
                }
 
    }

   

}
