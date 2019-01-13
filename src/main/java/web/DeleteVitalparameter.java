package main.java.web;

import java.io.IOException;

import javax.persistence.EntityTransaction;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.data.Patient;
import main.java.data.Vitalparameter;
import main.java.service.JPAService;
import main.java.service.PatientService;

/**
 * Servlet implementation class EditPatient
 */
@WebServlet("/app/DeleteVitalparameter")
public class DeleteVitalparameter extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteVitalparameter() {
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
		String vid = request.getParameter("vid");

		EntityTransaction tx = JPAService.getEntityManager().getTransaction();
		try {
			tx.begin();
			Patient p = (new PatientService()).findById(Integer.parseInt(id));
			Vitalparameter vp = (new PatientService()).findVitalparameterById(Integer.parseInt(vid));
			p.getVitalParameter().remove(vp);
			JPAService.getEntityManager().merge(p);
			JPAService.getEntityManager().flush();
			tx.commit();

			response.getWriter().append("Vitalparameter entfernt");
		} catch (Exception e) {
			response.getWriter().append("<div class='error'>Fehler bei Entfernen!</div>");
			tx.rollback();
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
