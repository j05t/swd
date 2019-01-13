package web;

import java.io.IOException;

import javax.persistence.EntityTransaction;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.Patient;
import service.JPAService;
import service.PatientService;

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
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		Patient p;

		try {
			p = (new PatientService()).findById(Integer.parseInt(id));
		} catch (Exception e) {
			System.out.println("Error finding patient.");
			response.sendError(500);
			return;
		}

		EntityTransaction tx = JPAService.getEntityManager().getTransaction();
		tx.begin();

		try {
			JPAService.getEntityManager().remove(p);
			JPAService.getEntityManager().flush();
			tx.commit();
			response.getWriter().append("Patient entfernt");
		} catch (Exception e) {
			response.getWriter().append("<div class='error'>Fehler bei Entfernen!</div>");
			tx.rollback();
			response.sendError(500);
		}
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
