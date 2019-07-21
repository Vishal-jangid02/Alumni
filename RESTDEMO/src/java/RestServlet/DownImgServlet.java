/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RestServlet;

import com.sun.org.apache.xml.internal.security.utils.Base64;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;
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
public class DownImgServlet extends HttpServlet {

     protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
       
        String email = request.getParameter("email");
          PrintWriter out = response.getWriter();
           
        String path = null;
            PrintWriter out1 = response.getWriter();
            JSONObject js= new JSONObject();
            JSONArray ja = new JSONArray();
            String img = "";
            
            
            
            //Scanner sc = new Scanner("F:\\hhha\\"+email);
            
          // img = sc.next();
           
              try {
                    Class.forName("com.mysql.jdbc.Driver");
                    
                   Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/studentregsystem", "root", "rat");
                    
                    PreparedStatement ps = con.prepareStatement("select imageurl from alumdata where email=?");
                    ps.setString(1, email);
                   
                    ResultSet rs = ps.executeQuery();
                  
                    System.out.println(rs);
                    if (rs.next()) {
                        File file = new File(rs.getString(1));
                        byte [] b = new byte[(int)(file.length())];
                          FileInputStream fileReader = new FileInputStream(new File(rs.getString(1)));
                          fileReader.read(b, 0, (int)(file.length()));
                          System.out.println("file size" +file.length() + "bytesize : " + b.length);
                          
                          js.put("response", b);
                          
                       
                        }
                    else
                    {
                        js.put("response", "No");
                        //js.put("reson", );
                    }
                    
                }   catch (Exception e) {
                    System.out.println(e.getLocalizedMessage());
                    System.out.println("Exception");
                    out.write(e.getLocalizedMessage());
                }
             
              System.out.print("JSON object :: \n\n" + js.toString());
            out.write(js.toString());
            
     }
   
}
