

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;



/**
 * Servlet implementation class profile
 */
@WebServlet("/Profile")
public class Profile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
    public Profile() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}


	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		

		response.setContentType("text/html");
		
		PrintWriter pw = response.getWriter();
		
		HttpSession session = request.getSession(false);
		
		if(session == null) {
			response.sendRedirect("login.jsp");	
		}
		try {
			
			Connection con = ConnectDB.connect();
			String fetch_students = "select * from student";
			
			PreparedStatement pst = con.prepareStatement(fetch_students);
			ResultSet rs = pst.executeQuery();
			
			
			
			pw.print(  "<html>"
					+ "<head><title> Profile </title>"
					+ "<style>"
					     +"td,th{padding:14px 30px;}"
					     + "body {font-family:arial;}"
					     + "a {text-decoration:none; border:1px solid black; padding:10px 10px;}"
					     + "a:hover{color:red;}"
					 + "</style>"
					 + "<link href='signup.css' rel='stylesheet'>"
					 + "</head>"
					 
					 + "<body>"
					 + "<center>"
					 + "<section class='home'>"
					 + "    <div class='open-overlay'>"
					 
					 + "<h2>Student list</h2>"
					 + "<br>"
					
					 +"<div style='float:center;'><a style='background-color:white;' href='OperationForm?Id=Add'>Add Student</a>"
					 
					 +"<a href='Logout' style='margin-left:10px;background-color:white;'>Log Out</a></div>"
					 
					 +"<br><br><br>"
					 
					
					
					);
			if( session.getAttribute("role") == null){
				response.sendRedirect("login.jsp");
			}

			pw.print("<table margin-top:-80px; background-color: white;><tr><th>Id</th>"
					+ "<th>student</th>"
					
					+ "<th>BOD</th>"
					+ "<th>Gender</th>"
					+ "<th>phone</th>"
					
					+ "<th>update</th>"
					+ "<th>delete</th></tr>");
			while(rs.next()) {
				pw.print("<tr><td>"+ rs.getInt(1)+"</td>"
						+ "<td>"+ rs.getString(2) +"</td>"
						+ "<td>"+ rs.getString(3) +"</td>"
						+ "<td>"+ rs.getString(4) +"</td>"
						+ "<td>"+ rs.getString(5) +"</td>"
					
						
                        + "<td> <a href='OperationForm?Id="+ rs.getInt(1) +"'>Update</a></td>"
                        
                        + "<td> <a href='OperationWithDatabase?OperationType=Delete&Id="+ rs.getInt(1) +"'>Delete</a></td>"
                        +"</tr> <br><br>"
						);
				
			}
			pw.print("</table> </div>"
					+ "</section></div></body></html>");
			con.close();
				
					 
		} catch (Exception ex) {
			pw.print(ex);
		}
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
