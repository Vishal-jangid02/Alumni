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
import javax.servlet.*;
import javax.servlet.http.*;
/**
 *
 * @author vishal
 */
public class ChangedPasswordServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        /*
        String email = request.getParameter("email");
        String oldpassword = request.getParameter("oldpassword");
        String newpassword = request.getParameter("newpassword");
        */
        PrintWriter out = response.getWriter();
        String image= request.getParameter("image");
        String email = request.getParameter("email");
        String mobno = request.getParameter("mobno");
        String address = request.getParameter("address");
        String prescomp = request.getParameter("prescomp");
        String prespos = request.getParameter("prespos");
        try{
            
               Class.forName("com.mysql.jdbc.Driver");
                    
                   Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/studentregsystem", "root", "rat");
             /*       
                    PreparedStatement ps = con.prepareStatement("select * from alumdata where email=? and password=?");
                    ps.setString(1, email);
                    ps.setString(2, oldpassword);
                    
                    ResultSet rs = ps.executeQuery();
                    if(rs.next()){
                         out.write(rs.toString());
                                         
                    PreparedStatement pss = con.prepareStatement("update alumdata set password=? where email=?");
                    pss.setString(1, newpassword);
                    pss.setString(2, email);
                    
                    
                    
                    int i = pss.executeUpdate();
                    out.write(i);
                    }else{
                        out.write(-2);
                    }
           */
            mobno="4556";
            address="jaipurjj";
            prescomp="sopra";
            prespos="devvv";
            email="kaushal@gmail.com";
            
                        String sql = "UPDATE alumdata SET mobno = ?,address = ?,prescomp = ?,prespos = ? ,imageurl = ? WHERE email = ? ";
           PreparedStatement ps= con.prepareStatement(sql);

           ps.setString(1, mobno);
           ps.setString(2, address);
           ps.setString(3, prescomp);
           ps.setString(4, prespos);
           ps.setString(5, "abc");
           ps.setString(6, email);
           int i=ps.executeUpdate();
           out.write(i);
         
        }catch(Exception e){
            
        }
        
    }

}
