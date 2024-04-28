package loginregistration;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class User_registration
 */
@WebServlet("/register")
public class User_registration extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public User_registration() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String uname = request.getParameter("name");
		String uemail = request.getParameter("email");
		String upwd = request.getParameter("pass");
		String urole = request.getParameter("role");
		RequestDispatcher dispatcher = null;
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/midexam","root","");
			PreparedStatement pst = con.prepareStatement("insert into users(email,password,role) values(?,?,?)");
			
			pst.setString(1, uemail);
			pst.setString(2, upwd);
			pst.setString(3, urole);
		
			int rowCount = pst.executeUpdate();
			PrintWriter pw = response.getWriter();
			if (rowCount > 0) {
				pw.print("<script>alert('student inserted..')</script>");
		    	RequestDispatcher rq = request.getRequestDispatcher("createAcc.jsp");
		    	rq.include(request, response);	
			}else {
				pw.print("<script>alert('student not inserted try Again..')</script>");
			}
			
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				con.close();	
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}

	}

	}


