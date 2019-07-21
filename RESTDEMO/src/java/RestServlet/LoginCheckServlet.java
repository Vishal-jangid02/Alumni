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
import org.json.*;

/**
 *
 * @author Mayank420
 */
public class LoginCheckServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         String email = req.getParameter("email");
            String password = req.getParameter("password");
            PrintWriter out = resp.getWriter();
            JSONObject js= new JSONObject();
            //JSONArray ja = new JSONArray();
            
            try {
                    Class.forName("com.mysql.jdbc.Driver");
                    
                   Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/studentregsystem", "root", "rat");
                    
                    PreparedStatement ps = con.prepareStatement("select * from alumdata where email=? and password=?");
                    ps.setString(1, email);
                    ps.setString(2, password);
                    
                    ResultSet rs = ps.executeQuery();
                  
                    System.out.println(rs);
                    if (rs.next()) {
                        
                             js.put("fullname", rs.getString(1));
                             js.put("email", rs.getString(2));
                             js.put("mobno", rs.getString(3));
                             js.put("course", rs.getString(4));
                             js.put("branch", rs.getString(5));
                             js.put("startyear", rs.getString(6));
                             js.put("endyear", rs.getString(7));
                             js.put("prescomp", rs.getString(8));
                             js.put("prespos", rs.getString(9));
                             js.put("address", rs.getString(10));
                             js.put("password", rs.getString(11));
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
