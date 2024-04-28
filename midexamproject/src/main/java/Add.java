

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.sql.*;



/**
 * Servlet implementation class Add
 */
@WebServlet("/Add")
public class Add extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Add() {
        super();
        // TODO Auto-generated constructor stub
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
		Connection con= ConnectDB.connect();
		PrintWriter pw = response.getWriter();
		
		String sname , enrollment , gender , phone , status;
		sname = request.getParameter("sname");
		enrollment = request.getParameter("enroll");
		String dob = request.getParameter("dob");
		gender = request.getParameter("gender");
		phone = request.getParameter("phone");
		status = request.getParameter("status");
		String g=request.getParameter("OperationType");
		RequestDispatcher dispatcher = null;
		
		try {
			PreparedStatement pst = con.prepareStatement("insert into student(sname , dob , gender , phone) values(?,?,?,?)");
			pst.setString(1, sname);
			
			pst.setString(2,dob);
			pst.setString(3, gender);
			pst.setString(4, phone);
		
		
			int rowCount = pst.executeUpdate();
			
			if (rowCount > 0) {
				pw.print("<script>alert('student inserted..')</script>");
		    	RequestDispatcher rq = request.getRequestDispatcher("Profile");
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
