package main.java.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.persistence.EntityTransaction;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.data.Patient;
import main.java.service.JPAService;
import main.java.service.PatientService;

/**
 * Servlet implementation class EditPatient
 */
@WebServlet("/app/DeletePatient")
public class DeletePatient extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeletePatient() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");

		EntityTransaction tx = JPAService.getEntityManager().getTransaction();
		tx.begin();
		Patient p = (new PatientService()).findById(Integer.parseInt(id));
		JPAService.getEntityManager().remove(p);
		JPAService.getEntityManager().flush();
		tx.commit();
		
		response.getWriter().append("<span onclick=\"document.getElementById('myModal').style.display='none'; loadDoc('Patienten')\" class=\"close\">&times;</span>")
				.append("Patient entfernt");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}