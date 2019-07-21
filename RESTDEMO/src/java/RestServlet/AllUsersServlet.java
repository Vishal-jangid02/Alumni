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
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author vishal
 */
public class AllUsersServlet extends HttpServlet {

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
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            //String email = req.getParameter("email");
            //String password = req.getParameter("password");
            
            PrintWriter out = resp.getWriter();
            JSONObject js ;
            JSONArray ja = new JSONArray();
            
           String fullname=req.getParameter("name");
              String course = req.getParameter("Course");
            String branch = req.getParameter("Branch");
            String endyear = req.getParameter("PassingYear");
            
           //String fullname="%";
            /*course="B.Tech";
            branch="%";
            endyear="%";*/
            
      
            try {
                    Class.forName("com.mysql.jdbc.Driver");
                    
                   Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/studentregsystem", "root", "rat");
                    
                    Statement stmt = con.createStatement();
                    
                   PreparedStatement ps= con.prepareStatement("select * from alumdata where course like ? and branch like ? and endyear like ? and fullname like ?;");
                   
                   
                   ps.setString(1, course);
                   ps.setString(2, branch);
                   ps.setString(3, endyear);
                   ps.setString(4, fullname);
 
                    ResultSet rs = ps.executeQuery();
                    
                    int i=0;
                    
                        while(rs.next())
                        {
                            //out.println(rs.getString("fullname"));
                                js= new JSONObject();
                                js.put("fullname", rs.getString("fullname"));
                                js.put("email", rs.getString("email"));
                                js.put("mobno", rs.getString("mobno"));
                                js.put("course", rs.getString("course"));
                                js.put("branch", rs.getString("branch"));
                                js.put("startyear", rs.getString("startyear"));
                                js.put("endyear", rs.getString("endyear"));
                                js.put("prescomp", rs.getString("prescomp"));
                                js.put("prespos", rs.getString("prespos"));
                                js.put("address", rs.getString("address"));
                                js.put("password", rs.getString("password"));
                             ja.put(i, js);
                            i++;    
                        }
                    out.write(ja.toString());
                    rs.close();
                } 
            catch (Exception e) {
                    System.out.println(e.getLocalizedMessage());
                    System.out.println("Exception");
                    out.write(e.getLocalizedMessage());
                }
 
    }

        
    }
