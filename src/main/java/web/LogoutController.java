package main.java.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
 
/**
 * Servlet implementation class LogoutController
 */
@WebServlet("/app/LogoutController")
public class LogoutController extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		//response.setContentType("text/html");
		//PrintWriter out = response.getWriter();
		//out.println("Session destroyed. Display a nice logout message here.");
		HttpSession session = request.getSession(false);
		
		if (session != null)
			session.invalidate();
		
        response.sendRedirect(request.getContextPath() + "/index.html"); // redirect to login page.

	}
}