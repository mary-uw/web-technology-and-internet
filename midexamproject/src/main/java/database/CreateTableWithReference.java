package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTableWithReference {
	  
    // JDBC variables for opening, closing and managing connection
    private static Connection connection;
    private static Statement statement;
    
    private static final String URL = "jdbc:mysql://localhost:3306/midexam?useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    
    public static void main(String[] args) {
    	

        try {
            // Step 1: Open a connection
            System.out.println("Connecting to database...");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
           
            // Step 2: Create a statement
            System.out.println("Creating tables...");
            statement = connection.createStatement();
            
            String createusersTable = "CREATE TABLE IF NOT EXISTS users ("
                    + "id INT AUTO_INCREMENT PRIMARY KEY,"
                    + "email VARCHAR(255),"
                    + "password VARCHAR(255),"
                    + "role VARCHAR(255))";

            // Step 3: Execute SQL queries to create tables
            String createsemesterTable = "CREATE TABLE IF NOT EXISTS semester ("
                    + "semester_id INT AUTO_INCREMENT PRIMARY KEY,"
                    + "semester_name VARCHAR(255),"
                    + "stating_date VARCHAR(50),"
                    + "end_date VARCHAR(50))";
            
            String createstudentTable = "CREATE TABLE IF NOT EXISTS student ("
                    + "id INT AUTO_INCREMENT PRIMARY KEY,"
                    + "sname VARCHAR(255),"
                    
                    + "dob VARCHAR(50),"
                    + "gender VARCHAR(50),"
                    + "phone VARCHAR(50))";
                    		
            String createdepartmentTable = "CREATE TABLE IF NOT EXISTS department ("
                    + "department_id INT AUTO_INCREMENT PRIMARY KEY,"
                    + "department_name VARCHAR(255) )";

            String createcourseTable = "CREATE TABLE IF NOT EXISTS course ("
                    + "course_id INT AUTO_INCREMENT PRIMARY KEY,"
                    + "course_code VARCHAR(255),"
                    + "course_name VARCHAR(255),"
                    + "semester_id INT,"
                    + "department_id INT,"
                    + "FOREIGN KEY (semester_id) REFERENCES semester(semester_id),"
                    + "FOREIGN KEY (department_id) REFERENCES department(department_id))";
            
            String createcoursedefinitionTable = "CREATE TABLE IF NOT EXISTS course_definition ("
                    + "course_definition_id INT AUTO_INCREMENT PRIMARY KEY,"
                    + "course_definition_code VARCHAR(50),"
                    + "course_definition_description VARCHAR(50),"
                    + "course_id INT,"
                    + "FOREIGN KEY (course_id) REFERENCES course(course_id))";
            
            String createacademicunitTable = "CREATE TABLE IF NOT EXISTS academic_unit ("
                    + "academic_id INT AUTO_INCREMENT PRIMARY KEY,"
                    + "academic_code VARCHAR(50),"
                    + "academic_name VARCHAR(50),"
                    + "type VARCHAR(50))";
            
            

            String createteacherTable = "CREATE TABLE IF NOT EXISTS teacher ("
                    + "teacher_id INT AUTO_INCREMENT PRIMARY KEY,"
                    + "frist_name VARCHAR(255),"
                    + "last_name VARCHAR(255),"
                    + "qualification VARCHAR(255),"
                    + "course_id INT,"
                    + "FOREIGN KEY (course_id) REFERENCES course(course_id))";
            
            
            String createstudentregistrationTable = "CREATE TABLE IF NOT EXISTS studentregistration ("
                    + "registration_id INT AUTO_INCREMENT PRIMARY KEY,"
                    + "registration_code VARCHAR(255),"
                    + "registration_date VARCHAR(255),"
                    + "id INT,"
                    + "semester_id INT,"
                    + "department_id INT,"
                    + "FOREIGN KEY (id) REFERENCES student(id),"
                    + "FOREIGN KEY (semester_id) REFERENCES semester(semester_id),"
                    + "FOREIGN KEY (department_id) REFERENCES department(department_id))";
            
            
            
            
            statement.executeUpdate(createusersTable);
            statement.executeUpdate(createsemesterTable);
            statement.executeUpdate(createdepartmentTable);
            statement.executeUpdate(createstudentTable);
            statement.executeUpdate(createacademicunitTable);
            statement.executeUpdate(createcourseTable);
            statement.executeUpdate(createcoursedefinitionTable);
            statement.executeUpdate(createteacherTable);
            statement.executeUpdate(createstudentregistrationTable);
          
            System.out.println("Tables created successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                // Step 4: Close the statement and connection
                if (statement != null) statement.close();
                if (connection != null) connection.close();
                System.out.println("Resources closed.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
