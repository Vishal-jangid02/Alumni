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
public class JobAvailableServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
       PrintWriter out = response.getWriter();
        JSONObject js;
        JSONArray ja = new JSONArray();
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            
            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/studentregsystem","root","rat");
            
            Statement stmt = con.createStatement();
            
            String query = "select * from jobtable;";
            
            ResultSet rs = stmt.executeQuery(query);
            
            int i=0;
            
            while(rs.next()){
                
                js = new JSONObject();
        
                js.put("postedby",rs.getString("postedby"));
                js.put("jobtype",rs.getString("jobtype"));
                js.put("experience",rs.getString("experience"));
                js.put("location",rs.getString("location"));
                js.put("skills",rs.getString("skills"));
                js.put("ctc",rs.getString("ctc"));
                js.put("dateofpost",rs.getString("dateofpost"));
                js.put("jd",rs.getString("jd"));
                js.put("industrytype",rs.getString("industrytype"));
                js.put("role",rs.getString("role"));
                js.put("emptype",rs.getString("emptype"));
                js.put("candidateprofile",rs.getString("candidateprofile"));
                js.put("companyname",rs.getString("companyname"));
                js.put("companywebsite",rs.getString("companywebsite"));
                
                ja.put(i,js);
                i++;
            }
            out.write(ja.toString());
            rs.close();
            
        }catch(Exception e){
            System.out.println(e.getLocalizedMessage());
            System.out.println("Exception");
           out.write(e.getLocalizedMessage());
        }
      
    }

}
