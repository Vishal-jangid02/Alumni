/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RestServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author vishal
 */
public class emailVerified extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        try{
        String orgEmail=req.getParameter("check");
        
        System.out.println(orgEmail);
        
        Class.forName("com.mysql.jdbc.Driver");
        
            Connection con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/studentregsystem", "root", "rat");
            
            PreparedStatement ps=con.prepareStatement("update alumdata set emailVerified='YES' where email=?");
            ps.setString(1, orgEmail);
            
            int rs=ps.executeUpdate();
            
            if(rs>0){
                
                System.out.println("success");
            }
            else{
                
                System.out.println("failure");
            }
        
    }catch(Exception e){
        e.printStackTrace();
    }
}
}
