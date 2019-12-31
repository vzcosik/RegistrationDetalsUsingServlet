package com.details;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class CustDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;

 
    public CustDetails() { 
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		
		//get request Details
		String firstName = request.getParameter("FirstName");
		String lastName = request.getParameter("LastName");
		String eMail = request.getParameter("E-Mail");
		String mobile = request.getParameter("Mobile");
		String[] ss = request.getParameterValues("t1");
		StringBuilder sb = new StringBuilder();
		for(String str:ss)
		{
			sb.append(str+",");
		}
		String addr = sb.toString();
		
		try {
			//Load Driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//Create Conection
			Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","meena");
			//Create Statement Object
			//String ss1 = "create ";
			PreparedStatement preparedStatement = connection.prepareStatement("insert into CustomerDetails values (?,?,?,?,?)");
			preparedStatement.setString(1, firstName);
			preparedStatement.setString(2, lastName);
			preparedStatement.setString(3, eMail);
			preparedStatement.setString(4, mobile);
			preparedStatement.setString(5, addr);
			int x = preparedStatement.executeUpdate();
			if(x == 1)
				writer.println("Values are inserted successfully...");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
	}

}
