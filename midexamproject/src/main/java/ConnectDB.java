

import java.sql.Connection;
import java.sql.DriverManager;

import org.apache.catalina.webresources.Cache;

public class ConnectDB {
	public static Connection connect()
	{	
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/midexam","root","");
		return con;
		
	} 
	catch(Exception ex)
	{
		
	}
	return null;
	}

public static void main(String[] args) {
	
}
}
