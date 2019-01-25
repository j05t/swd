package web;

import java.io.IOException;

import javax.persistence.EntityTransaction;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.Patient;
import data.Termin;
import service.JPAService;
import service.PatientService;

/**
 * Servlet implementation class EditPatient
 */
@WebServlet("/app/DeleteTermin")
public class DeleteTermin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteTermin() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String tid = request.getParameter("tid");
		String pid = request.getParameter("pid");

		EntityTransaction tx = JPAService.getEntityManager().getTransaction();
		try {
			tx.begin();

			Termin t = (new PatientService()).findTerminById(Integer.parseInt(tid));
			Patient p = (new PatientService()).findById(Integer.parseInt(pid));

			p.getAppointments().remove(t);
			JPAService.getEntityManager().merge(p);

			JPAService.getEntityManager().flush();
			JPAService.getEntityManager().refresh(p);

			tx.commit();

			response.getWriter().append("Termin entfernt");
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
