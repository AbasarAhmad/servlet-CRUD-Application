package com.saar;

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

public class deleteData extends HttpServlet {
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String roll=req.getParameter("rollNumber");
		int rollNumber=Integer.parseInt(roll);
		String sql="delete from student where rollNumber=?";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			// We make connection
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/ducat","root","Riyaz@31200");
			PreparedStatement ps=conn.prepareStatement(sql);
				ps.setInt(1, rollNumber);
				ps.executeUpdate();
				conn.close();
				ps.close();
		}
		catch(ClassNotFoundException e)
		{
			 e.printStackTrace();
	        
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		PrintWriter pw=resp.getWriter();
//		pw.append("<h1 style=\"background-color: red; color: white; text-align: center;\">Student Data has deleted </h1>");
		pw.append("data deleted");
		
		
	}

}
