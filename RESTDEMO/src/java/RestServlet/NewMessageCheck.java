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
import org.json.JSONObject;

public class NewMessageCheck extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        email = "vishaljangid@gmail.com";
        PrintWriter out = resp.getWriter();
            JSONObject js= new JSONObject();
            //JSONArray ja = new JSONArray();
            
            try {
                    Class.forName("com.mysql.jdbc.Driver");
                    
                   Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/studentregsystem", "root", "rat");
                    
                    PreparedStatement ps = con.prepareStatement("select count(*) from messages where receiver = ? and status = ?");
                    
                    ps.setString(1, email);
                    ps.setInt(2, 0);
                    
                    
                    ResultSet rs = ps.executeQuery();
                  
                    System.out.println(rs);
                    if (rs.next()) {
                            
                             js.put("response", rs.getString(1));
                             
                        }
                    else
                    {
                        js.put("response", "No");
                        //js.put("reson", );
                    }
                    System.out.println(rs);   
                    out.write(js.toString());
                } catch (Exception e) {
                    System.out.println(e.getLocalizedMessage());
                    System.out.println("Exception");
                    out.write(e.getLocalizedMessage());
                }
    }

   
}
