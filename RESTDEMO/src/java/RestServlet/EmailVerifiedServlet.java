/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RestServlet;

import com.sun.org.apache.xml.internal.security.utils.Base64;
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

/**
 *
 * @author vishal
 */
public class EmailVerifiedServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        try{
        String OrgEmail = req.getParameter("check");
        System.out.println(OrgEmail);
        
                           Class.forName("com.mysql.jdbc.Driver");
                    
                   Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/studentregsystem", "root", "rat");
                    
                    PreparedStatement ps = con.prepareStatement("UPDATE alumdata SET emailVerified= 'YES' where email=?");
                    ps.setString(1, OrgEmail);
                    int rs = ps.executeUpdate();
                    
                    if (rs>0) 
                    {
                            System.out.println("Success");
                    }
                    else
                    {
                        System.out.println("Failed");
                    }
 
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    
    
}
