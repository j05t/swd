package main.java.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.java.data.User;
import main.java.data.UserService;


/**
 * Servlet implementation class LoginController
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static UserService userService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginController() {
		super();
		userService = UserService.getInstance();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		User user = userService.find(username, password);
		System.out.println("Found " + user);
		
		if (user != null) {
			out.print("Welcome, " + username); // place XSS here..
			HttpSession session = request.getSession(true); // reuse existing
															// session if exist
															// or create one
			session.setAttribute("role", user.getRole());
			session.setMaxInactiveInterval(300); // 5 minutes
			response.sendRedirect("app/Dashboard.jsp");
		} else {            
			response.sendRedirect(request.getContextPath() + "/index.html#badLogin");
		}
	}

}
