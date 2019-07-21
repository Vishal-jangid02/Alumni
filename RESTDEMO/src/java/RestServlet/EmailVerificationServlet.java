/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RestServlet;

import com.sun.org.apache.xml.internal.security.utils.Base64;
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

/**
 *
 * @author vishal
 */
public class EmailVerificationServlet extends HttpServlet {
private String host = "smtp.gmail.com";
	private String port = "587";
	private String user = "vishaljangid8140@gmail.com";
	private String pass = "7976324569";

/*	public void init() {
		// reads SMTP server setting from web.xml file
		//ServletContext context = getServletContext();
		//host = context.getInitParameter("host");
		//port = context.getInitParameter("port");
		//user = context.getInitParameter("user");
		//pass = context.getInitParameter("pass");
	}
*/
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// reads form fields
		String recipient = request.getParameter("recipient");
                String type = request.getParameter("type");
                if(type.equals("reg")){
                
		String subject = "Email Verification !!";

		String resultMessage = "";

		try {
                   //String encodeLink = Base64.encode(recipient.getBytes());
            
                   
            String v = "http://192.168.43.215:8084/RESTDEMO/EmailVerified.do?check="+recipient;
            String vLink = "Click on the link to verify the account !!\n\n\n"+v;
            
			EmailUtility.sendEmail(host, port, user, pass, recipient, subject,
					vLink);
			resultMessage = "Success";
		} catch (Exception ex) {
			ex.printStackTrace();
			resultMessage = "There were an error: " + ex.getMessage();
		} finally {
			//request.setAttribute("Message", resultMessage);
                        PrintWriter out = response.getWriter();
                        out.println(resultMessage);
			//getServletContext().getRequestDispatcher("/Result.jsp").forward(
			//		request, response);
		}
        }
                else if(type.equals("forgot")){
                    
                String subject = "Password Of Alumni !!";

		String resultMessage = "";

		try {
                   //String encodeLink = Base64.encode(recipient.getBytes());
       
            try {
                    Class.forName("com.mysql.jdbc.Driver");
                    
                   Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/studentregsystem", "root", "rat");
                    
                    PreparedStatement ps = con.prepareStatement("select password from alumdata where email=?");
                    ps.setString(1, recipient);
                  
                
                    ResultSet rs = ps.executeQuery();
                  
                    System.out.println(rs);
                    if (rs.next()) {
                        
                       String rec = rs.getString(1);
                           System.out.println(rec);
                           String vLink = "Your password is  !!\n\n\n"+"Email="+recipient+"Password = "+rec;
                 
			EmailUtility.sendEmail(host, port, user, pass, recipient, subject,
					vLink);
			resultMessage = "Success";
                    }
                    else{
                        resultMessage="There were an error";
                    }
                  
                } catch (Exception e) {
                    System.out.println(e.getLocalizedMessage());
                    System.out.println("Exception");
                  
                }
 
		} catch (Exception ex) {
			ex.printStackTrace();
			resultMessage = "There were an error: " + ex.getMessage();
		} finally {
			//request.setAttribute("Message", resultMessage);
                        PrintWriter out = response.getWriter();
                        out.println(resultMessage);
			//getServletContext().getRequestDispatcher("/Result.jsp").forward(
			//		request, response);
		}
                }
                }
}
