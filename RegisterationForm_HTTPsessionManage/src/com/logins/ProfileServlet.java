package com.logins;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ProfileServlet")
public class ProfileServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		HttpSession session = request.getSession(false);

		if (session != null) {
			String name = (String) session.getAttribute("name");

			out.print("<h3  style=\"text-align:center;margin-top:30px;color:#004de6; \">Hello, " + name + " Welcome to Profile</h3>");
		}

		else {
			out.print("<h3 style=\"text-align:center;margin-top:30px;color:#cc2900; \">Please login first</h5>");
			request.getRequestDispatcher("login.html").include(request, response);
		}
		out.close();
	}
}
