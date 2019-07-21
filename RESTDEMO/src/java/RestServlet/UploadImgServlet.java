/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RestServlet;

import com.sun.org.apache.xml.internal.security.utils.Base64;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author vishal
 */
public class UploadImgServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String image= request.getParameter("image");
        String email = request.getParameter("email");
        String mobno = request.getParameter("mobno");
        String address = request.getParameter("address");
        String prescomp = request.getParameter("prescomp");
        String prespos = request.getParameter("prespos");               
        String path = null;
            PrintWriter out1 = response.getWriter();
            JSONObject js= new JSONObject();
            JSONArray ja = new JSONArray();
            
            //System.out.println(image);
            
            try {
                
           
            if(image!=null){
                byte b[]=image.getBytes();
                byte bb[]=Base64.decode(b);
                
                
                FileOutputStream fo=new FileOutputStream("F:\\hhha\\"+email);
                
                fo.write(b);
                fo.close();
                path = "F:\\hhha\\"+email;
                js.put("Image","Yes");    
            while(path == null);
            Class.forName("com.mysql.jdbc.Driver");
            
            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/studentregsystem","root","rat");
            
            String sql = "UPDATE alumdata SET mobno = ?,imageurl = ? ,address = ?,prescomp = ?,prespos = ? WHERE email = ? ";
            PreparedStatement ps= con.prepareStatement(sql);
           
           
           
            ps.setString(1, mobno);
            ps.setString(2, path);
            ps.setString(3, address);
            ps.setString(4, prescomp);
            ps.setString(5, prespos);
            ps.setString(6, email);
           
            int i = ps.executeUpdate();
            js.put("Query", ""+i);
           
            out1.write(js.toString());
           System.out.print(""+ js.toString());
            }
            } catch (Exception e) {
                    out1.write(e.getLocalizedMessage());
            /*try {
                js.put("response","No");
                        
            } catch (JSONException ex) {
                ex.printStackTrace();
                //Logger.getLogger(UploadImgServlet.class.getName()).log(Level.SEVERE, null, ex);
            }*/
            System.out.println("Exception");
                    e.printStackTrace();
                    
                    //out.write(e.getLocalizedMessage());
                    
                    
                }
    }
   
}
