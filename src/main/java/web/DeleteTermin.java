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
import main.java.data.Termin;
import main.java.service.JPAService;
import main.java.service.PatientService;

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
		// TODO Auto-generated constructor stub
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
		tx.begin();
		
		Termin t = (new PatientService()).findTerminById(Integer.parseInt(tid));
		Patient p = (new PatientService()).findById(Integer.parseInt(pid));

		p.getTermine().remove(t);
		JPAService.getEntityManager().merge(p);
		
		JPAService.getEntityManager().flush();
		JPAService.getEntityManager().refresh(p);

		tx.commit();
		
		response.getWriter().append("Termin entfernt");
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
