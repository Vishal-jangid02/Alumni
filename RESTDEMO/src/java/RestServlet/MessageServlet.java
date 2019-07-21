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
import org.json.JSONArray;
import org.json.JSONObject;

public class MessageServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        PrintWriter out = resp.getWriter();
            JSONObject js= new JSONObject();
            JSONArray ja = new JSONArray();
            
            try {
                    Class.forName("com.mysql.jdbc.Driver");
                    
                   Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/studentregsystem", "root", "rat");
                    
                    PreparedStatement ps = con.prepareStatement("select * from messages where sender=? or receiver = ? order by timestamp");
                    
                    ps.setString(1, email);
                    ps.setString(2, email);
                    ResultSet rs = ps.executeQuery();
                    
                    PreparedStatement ps1 = con.prepareStatement("UPDATE messages SET status = 1 where receiver = ?");
                    ps1.setString(1, email);
                    ps1.executeUpdate();
                  
                    System.out.println(rs);
                    while(rs.next()) {
                             js = new JSONObject();
                             js.put("sender", rs.getString(1));
                             js.put("receiver", rs.getString(2));
                             js.put("timestamp", rs.getString(3));
                             js.put("status", rs.getString(4));
                             js.put("message",rs.getString(5));
                             ja.put(js);
                        }
                    
                    
                
                    out.write(ja.toString());
                
                } catch (Exception e) {
                    System.out.println(e.getLocalizedMessage());
                    System.out.println("Exception");
                    out.write(e.getLocalizedMessage());
                }
    }
    
}
