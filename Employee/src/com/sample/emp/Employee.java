package com.sample.emp;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet("/Employee")
public class Employee extends HttpServlet{

	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		/*
		 * Fetch the data from the html form
		 */
		String temp = request.getParameter("id");
		/*
		 * Converting String into integer by using parseInt method
		 */
		int employeId= Integer.parseInt(temp);
		String employeeName = request.getParameter("name");
		String temp1 = request.getParameter("sal");
		/*
		 * Converting String into integer by using parseDouble method
		 */
		double employeeSalary = Double.parseDouble(temp1);
		String temp2 = request.getParameter("dept");
		/*
		 * Converting String into integer by using parseInt method
		 */
		int employeeDept = Integer.parseInt(temp2);
		
		
		/*
		 * Store the data from the database by using url 
		 * JDBC code
		 */
		String url = "jdbc:mysql://localhost:3306?user=root&password=12345";
		
		/*
		 * Url from the database 
		 * jdbc:mysql: ->It is a Protocal
		 * localhost: -> host Info
		 * 3306 :-> Port number
		 * user name & password : -> User Info
		 */
		
		/*
		 * insert the data in database by using syntax
		 * It is a run time value are passed through the database
		 */
		String query = "insert into test.employee values(?,?,?,?)";
		
		try {
			//local mysql driver
			/*
			 * fromName() -class-static method argument - fully qualified class name of Driver
			 * Driver is a class -com.mysql.jdbc package com.mysql.jdbe.Driver
			 *
			 */
			getClass().forName("com.mysql.jdbc.Driver");//ClassNotFoundException
			/*
			 * Establish the connection
			 * Connection -> is a interface present in java.sql package
			 * connect -> is a reference variable
			 * DriverManager -> it is a helper class in java.sql package
			 * getConnection -> it is a static and overloaded method for establishing connection
			 */
			Connection connect = DriverManager.getConnection(url);//SQLException
			PreparedStatement ps = connect.prepareStatement(query);
			//Assigning the values
			ps.setInt(1, employeId);
			ps.setString(2, employeeName);
			ps.setDouble(3, employeeSalary);
			ps.setInt(4, employeeDept);
			
			//Execute update is a query updated in the database
			ps.executeUpdate();
			
			//Print output on browser
			PrintWriter writer = response.getWriter();
			/*
			 * Print the output in the Browser
			 */
			writer.println("<h1 style='color:Green;'>Registration Successfull...</h1>");
			connect.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
