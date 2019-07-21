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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Mayank420
 */
public class RegisterServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
            String fullname = req.getParameter("fullname");
            String email = req.getParameter("email");
            String mobno = req.getParameter("mobno");
            String course = req.getParameter("course");
            String branch = req.getParameter("branch");
            String startyear = req.getParameter("startyear");
            String endyear = req.getParameter("endyear");
            String prescomp = req.getParameter("prescomp");
            String prespos = req.getParameter("prespos");
            String address = req.getParameter("address");
            String password = req.getParameter("password");
            String clgid = req.getParameter("clgid");
            
            PrintWriter out = resp.getWriter();
            JSONObject js= new JSONObject();
            JSONArray ja = new JSONArray();
            
            try {
                    Class.forName("com.mysql.jdbc.Driver");
                    
                   Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/studentregsystem", "root", "rat");
                    
                   PreparedStatement ps = con.prepareStatement("insert into alumdata (fullname, email, mobno, course, branch, startyear, endyear, prescomp, prespos, address, password, imageurl, emailVerified) values (?,?,?,?,?,?,?,?,?,?,?,?,?)");
                    ps.setString(1, fullname);
                    ps.setString(2, email);
                    ps.setString(3, mobno);
                    ps.setString(4, course);
                    ps.setString(5, branch);
                    ps.setString(6, startyear);
                    ps.setString(7, endyear);
                    ps.setString(8, prescomp);
                    ps.setString(9, prespos);
                    ps.setString(10,address);
                    ps.setString(11,password);
                    ps.setString(12, "F://hhha//default");
                    ps.setString(13, "NO");
                    //ps.setString(12, clgid);
                    
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
