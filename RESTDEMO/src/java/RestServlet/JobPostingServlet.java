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
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author MAYANK-PC
 */
public class JobPostingServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        ArrayList<String> errorlist = new ArrayList<>();
        String postedby = req.getParameter("postedby");
        String jobType = req.getParameter("jobtype");
        String experience = req.getParameter("experience");
        String location = req.getParameter("location");
        String skills = req.getParameter("skills");
        String ctc = req.getParameter("ctc");
        String dateofpost = req.getParameter("dateofpost");
        String jd = req.getParameter("jd");
        String industrytype = req.getParameter("industrytype");
        String role = req.getParameter("role");
        String emptype = req.getParameter("emptype");
        String candidateprofile = req.getParameter("candidateprofile");
        String companyname = req.getParameter("companyname");
        String companywebsite = req.getParameter("companywebsite");
        System.out.println("Posted bby :: " + postedby);
        
        
          PrintWriter out = resp.getWriter();
            JSONObject js= new JSONObject();
            JSONArray ja = new JSONArray();
            
            
  
            try {
                    
                Class.forName("com.mysql.jdbc.Driver");
                    
                 Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/studentregsystem", "root", "rat");
                    
                    PreparedStatement ps = con.prepareStatement("insert into jobtable values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                    ps.setString(1, postedby);
                    ps.setString(2, jobType);
                    ps.setString(3, location);
                    ps.setString(4, skills);
                    ps.setString(5, ctc);
                    ps.setString(6, dateofpost);
                    ps.setString(7, jd);
                    ps.setString(8, industrytype);
                    ps.setString(9, role);
                    ps.setString(10, emptype);
                    ps.setString(11, candidateprofile);
                    ps.setString(12, companyname);
                    ps.setString(13, companywebsite);
                    ps.setString(14, experience);
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

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
