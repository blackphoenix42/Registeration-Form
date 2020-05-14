package com.logins;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		request.getRequestDispatcher("index.html").include(request, response);
		
		String name = request.getParameter("name");
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		String password = request.getParameter("pass");
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/register", "root", "anshu123");
			String query = "INSERT INTO persons(name,username,email,Address,pass) VALUES (?,?,?,?,?);";
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1,name);
			pstmt.setString(2,username);
			pstmt.setString(3,email);
			pstmt.setString(4,address);
			pstmt.setString(5,password);
			pstmt.executeUpdate();
			con.close();
			System.out.println("QUERY EXECUTED SUCCESSFULLY");
		} catch (Exception e) {
			e.printStackTrace();
		}
		 out.print("<h1  style=\"text-align:center;margin-top:30px;color:#004de6; \">Registered Successfully</h1>");
	}

}
