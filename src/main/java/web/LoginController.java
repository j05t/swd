package main.java.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.java.data.User;
import main.java.service.UserService;


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
		userService = new UserService();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		User user = userService.find(username, password);
		System.out.println("Found " + user);
		
		if (user != null) {
			// reuse existing session if exist or create one
			HttpSession session = request.getSession(true); 

			session.setAttribute("role", user.getRole());
			session.setMaxInactiveInterval(600); // 10 minutes
			response.sendRedirect("app/Dashboard.jsp?#");
		} else {            
			response.sendRedirect(request.getContextPath() + "/index.html#badLogin");
		}
	}

}
