

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class SearchResult
 */
@WebServlet("/SearchResult")
public class SearchResult extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchResult() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
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
		Connection con = ConnectDB.connect();
		try {
			String fetch_students = "select * from student";
			String count_student = "select count(*) from student";
			String active_student = "select count(*) from student where status = 'Active'";
			PreparedStatement pst = con.prepareStatement(fetch_students);
			ResultSet rs = pst.executeQuery();
			
			PreparedStatement pst2 = con.prepareStatement(count_student);
			ResultSet rs2 = pst2.executeQuery(count_student);
			rs2.next();
			
			PreparedStatement pst3 = con.prepareStatement(active_student);
			ResultSet rs3 = pst3.executeQuery(active_student);
			rs3.next();
			String q;
			
			if(request.getParameter("Fetch").equals("Active")) {
				q = "select * from student where status = 'Active'";
			}
			else if(request.getParameter("Fetch").equals("Deactive")) {
				q = "select * from student where status = 'Deactive'";
			}
			else {
				q = "select * from student where sname like '%"+ request.getParameter("Fetch") +"%'";
			}
			PreparedStatement pst1 = con.prepareStatement(q);
			ResultSet rs4 = pst1.executeQuery();
			
			pw.print( "<html>"
					+ "<head><title> student Result </title>"
					+ "<style>"
				     +"td,th{padding:14px 30px;}"
				     + "body {font-family:arial;}"
				     + "table{border:1px solid black; padding:20px;}"
				     + "a {text-decoration:none; border:1px solid black; padding:10px 10px;}"
				     + "a:hover{color:red;}"
				 + "</style>"
				 + "</head>"
				 
				 + "<body>"			
					);
			pw.print( "<center>"
				 + "<h2>Hello Admin</h2>"
				 + "<br>"
				
                 + "<div style='float:left; color:orange; border:1px solid black; padding:5px 5px;'> total student<h3>"+ rs2.getInt(1) +"</h3></div>" 
 
				 +"<div style='float:left; margin-left:10px; color:blue; border:1px solid black; padding:5px 5px;'> total active student <h3><a href='SearchResult?Fetch=Active' style='border:none;'>"+ rs3.getInt(1)+"</a></h3></div>"
					
                 +"<div style='float:left; margin-left:10px; color:blue; border:1px solid black; padding:5px 5px;'> total deactive student <h3><a href='SearchResult?Fetch=Deactive' style='border:none;'>"+ (rs2.getInt(1)-rs3.getInt(1))+"</a></h3></div>"
  
				 +"<div style='clear:both;'></div><div style='float:right;'><a href='OperationForm?Id=Add'>Add Student</a>"
					 
				 +"<a href='Logout' style='margin-left:10px;'>Log Out</a></div>"
					 
				 +"<br><br><br>"
				 
                 +"<div style='flaot:left;'><form action='SearchResult'><input type='text' name='Fetch' placeholder='Search Student' required><input type='submit' value='Search'style='margin-left:10px'></form><br></div>"
	
				 +"<div style='float:right;'><form action='GeneratePDF'> <select name='status'><option>Active</option><option>Deactive</option></select> <input type='submit' value='Generate Report' style='margin-left:10px'></form></div"
				 +"<br><br>"
				 +"<h2>Result</h2>"	);
			
			pw.print("<table margin-top:-80px;><tr><th>Id</th>"
					+ "<th>student</th>"
					
					+ "<th>BOD</th>"
					+ "<th>Gender</th>"
					+ "<th>phone</th>"
					
					+ "<th>update</th>"
					+ "<th>delete</th></tr>");
			while(rs4.next()) {
				pw.print("<tr><td>"+ rs4.getInt(1)+"</td>"
						+ "<td>"+ rs4.getString(2) +"</td>"
						+ "<td>"+ rs4.getString(3) +"</td>"
						+ "<td>"+ rs4.getString(4) +"</td>"
						+ "<td>"+ rs4.getString(5) +"</td>"
						
						
                        + "<td> <a href='OperationForm?Id=" + rs4.getInt(1) + "'>Update</a></td>"
                        
                        + "<td> <a href='OperationWithDatabase?OperationType=Delete&Id=" + rs4.getInt(1) + "'>Delete</a></td>"
                        +"</tr> <br><br>"
						);
				
			}
			pw.print("</table></div></body></html>");
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
