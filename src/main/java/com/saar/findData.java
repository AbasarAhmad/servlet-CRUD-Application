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

import java.sql.ResultSet;

public class findData extends HttpServlet {
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String roll=req.getParameter("rollNumber");
		int rollNumber=Integer.parseInt(roll);
		String sql="select*from student where rollNumber=?";
		PrintWriter out=resp.getWriter();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			// We make connection
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/ducat","root","Riyaz@31200");
			PreparedStatement ps=conn.prepareStatement(sql);
				ps.setInt(1, rollNumber);
				ResultSet resultSet=ps.executeQuery();
				
				resp.setContentType("text/html");
				out.println("<html>");
				out.println("<body>");
				out.println("<table border='1'>");
				while(resultSet.next())
				{
				
						out.println("<tr broder=5px >");
							out.println("<td>" +resultSet.getInt(1)+"</td>");
							
							out.println("<td>"+resultSet.getString(2)+"</td>");
							
							out.println("<td>"+resultSet.getString(3)+"</td>");
						out.println("</tr>");
					
				}
				out.println("</table");
				out.println("</body>");
			out.println("</html>");
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
		out.println("<h1>data fetched!!!</h1>");
		
		
	}

}
