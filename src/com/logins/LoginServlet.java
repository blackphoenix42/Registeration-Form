package com.logins;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		request.getRequestDispatcher("index.html").include(request, response);

		String name = request.getParameter("name");
		String password = request.getParameter("password");

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/register", "root", "anshu123");
			String query = "SELECT * FROM persons WHERE username=? and pass=?";
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, name);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();
			boolean status = rs.next();
			if (status) {
				out.print("<h3 style=\"text-align:center;margin-top:30px;color:#004de6; \">Welcome: " + name + "</h3>");
				HttpSession session = request.getSession();
				session.setAttribute("name", name);
			} else {
				out.print("<h5 style=\"text-align:center;margin-top:30px;color:#cc2900; \">Sorry, username or password error!</h5>");
//				request.getRequestDispatcher("login.html").include(request, response);
			}
			out.close();
			System.out.println("QUERY2 EXECUTED SUCCESSFULLY");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
