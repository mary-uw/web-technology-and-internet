

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
 * Servlet implementation class OperationForm
 */
@WebServlet("/OperationForm")
public class OperationForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OperationForm() {
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
		    PreparedStatement pst = con.prepareStatement(fetch_students);
		    ResultSet rs = pst.executeQuery(fetch_students);
		    
		    
		    
		    pw.print("<html>"
		    		+ "<head><title> Operation form</title>"
		    		
                 + "<style>"
                 +"td,th{padding:14px 30px;}"
			     + "body {font-family:arial;}"
			     + "a {text-decoration:none; border:1px solid black; padding:10px 10px;}"
			     + "a:hover{color:red;}"
                    
                 + "</style>"
                 + "<link href='signup.css' rel='stylesheet'>"
                 + "</head>"
               
		    		);
		    if( session.getAttribute("role") == null){
				response.sendRedirect("login.jsp");
			}

		    
		    pw.print("<body>"
		    		+ "<center>"
		    		+ "<section class='home'>"
					 + "    <div class='open-overlay'>"
					 + "<h2>Add new student</h2>"
					 + "<br>"
					
					 +"<div style='clear:both;'></div><div style='float:center; background-color: white;'><a style='background-color:white;' href='Profile'>view Student</a>"
					 
					 +"<a style='background-color: white;' href='Logout' style='margin-left:10px;'>Log Out</a></div>"
					 
					 +"<br><br><br>"
					 
					+"<br><br><br>"
		    		);
		    
		    String Id = request.getParameter("Id");
		    String Sname, enrollment,gender, phone;
		    ResultSet rs4 =null;
		    
		    if(Id.equals("Add")) {
		    	pw.print("<h1> </h1>");
		    	
		    	pw.print("<form action='Add' method='post'>"
		    			+ "<table>"
		    			   + "<tr>"
		    			     + "<td> student Name</td>"
		    			     + "<td><input type='text' name='sname' placeholder='Student name'></td>"
		    			    + "</tr>"
		    			    
		    			 
		    			    
		    			   + "<tr>"
		    			     + "<td> Data of birth</td>"
		    			     + "<td><input type='date' name='bod'></td>"
		    			    + "</tr>"
		    			   
		    			   + "<tr>"
		    			     + "<td> Gender</td>"
		    			     + "<td><input type='radio' name='gender' value='Male'>Male"
		    			     + " <input type='radio' name='gender' value='Female'>Female </td>"
		    			    + "</tr>"
		    			   
                            + "<tr>"
                               + "<td> Phone</td>"
                                + "<td><input type='text' name='phone' placeholder='Phone no'></td>"
                             + "</tr>"
                             
                           
                           
                         + "<tr>"
                           + "<input type='hidden' value='Add' name='OperationType'>"
                           + "<td><input type='submit' value='Add' class='btn'></td>"
                         + "</tr>"
                         + "</table></form></center>" 
                           
		    			);
		    }
		    else {
		    
		    	
		    	String q = "select * from student where id ="+Integer.parseInt(Id)+"";
		    	PreparedStatement pst1= con.prepareStatement(q);
		    	rs4 = pst1.executeQuery();
		    	rs4.next();
		    	
		    	pw.print("<form action='UpdateServlet' method='post'>"
		    			+ "<table>"
		    			   + "<tr>"
		    			     + "<td> student Name</td>"
		    			     + "<td><input type='text' name='sname' placeholder='Student name' value='"+rs4.getString(2) +"'></td>"
		    			    + "</tr>"
		    			    
		    			  
		    			    
		    			   + "<tr>"
		    			     + "<td> Data of birth</td>"
		    			     + "<td><input type='date' name='bod' value='"+rs4.getString(3) +"'></td>"
		    			    + "</tr>"
		    	
		    			   + "<tr>"
		    			     + "<td> Gender</td>"
		    			   );
		    	 if(rs4.getString(4).equals("Male")) {
 			    	pw.print("<td><input type='radio' name='gender' value='Male' checked>Male"
 			    			+ "<input type='radio' name='gender' value='Female'>Female </td>");
 			    }
		    	 else {
		    		 pw.print("<td><input type='radio' name='gender' value='Male' >Male"
	 			    			+ "<input type='radio' name='gender' value='Female' checked>Female </td>");
	 			  
		    	 }
		    	 pw.print("</tr>"
		    			 
                         + "<tr>"
                         + "<td> Phone</td>"
                          + "<td><input type='text' name='phone' placeholder='Phone no' value='"+rs4.getString(5) +"'></td>"
                       + "</tr>"
		    			 );
		    	 
		    	 pw.print("<tr>"
                           + "<input type='hidden' value='Update' name='OperationType'>"
                           + "<input type='hidden' value='"+rs4.getString("Id")+"' name='Id'>"
                           + "<td><input type='submit' value='Update' class='btn'> </td>"
                         + "</tr>"
                         + "</table></form>"
                         + "</section></div>" 
                           
		    			);
		    	 
		    }
		    pw.print("</center></body></html>");
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
