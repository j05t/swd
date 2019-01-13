package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

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
@WebServlet("/app/EditTermin")
public class EditTermin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditTermin() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String createNew = request.getParameter("new");
		String pid = request.getParameter("pid");
		String tid = request.getParameter("tid");

		Termin t;
		String title;

		if (createNew != null) {
			title = "Termin anlegen";
			t = new Termin();
			t.setDate(LocalDate.now());
			t.setName("");
		} else {
			title = "Termin bearbeiten";
			t = (new PatientService()).findTerminById(Integer.parseInt(tid));
		}

		if (createNew != null)
			createNew = "true";
		else
			createNew = "false";

		response.getWriter().append(
				"<span onclick=\"document.getElementById('myModal').style.display='none'\" class=\"close\">&times;</span>")
				.append("<h3>" + title + "</h3><form><input type='hidden' id='pid' value='" + pid
						+ "' type='text' /><br />" + "<input type='hidden' id='tid' value='" + t.getId()
						+ "' type='text' /><br />" + "<input type='hidden' id='create' value='" + createNew + "' />"
						+ "<label>Datum:</label>  <input id='datum' value='" + t.getDate() + "' type='text' /><br />"
						+ "<label>Zeit:</label>  <input id='zeit' value='"
						+ (t.getTime() != null ? t.getTime() : "00:00") + "' type='text' /><br />"
						+ "<button class='editSubmit' onclick='sendEditTerminForm()'>Absenden</button></form>");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pid = request.getParameter("pid");
		String tid = request.getParameter("tid");
		String createNew = request.getParameter("create");

		PrintWriter out = response.getWriter();

		Termin t;

		Patient p = (new PatientService()).findById(Integer.parseInt(pid));

		if (createNew != null && createNew.equals("true")) {
			t = new Termin();
		} else {
			t = (new PatientService()).findTerminById(Integer.parseInt(tid));
		}

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

		try {
			t.setTime(LocalTime.parse(request.getParameter("zeit"), timeFormatter));
			t.setDate(LocalDate.parse(request.getParameter("datum"), formatter));
		} catch (Exception e) {
			System.out.println("Error converting date or time.");
			response.sendError(500);
			return;
		}
		
		EntityTransaction tx = JPAService.getEntityManager().getTransaction();
		tx.begin();

		try {
			if (createNew == null) {
				System.out.println("merging termin " + t);
				JPAService.getEntityManager().merge(t);
			} else {
				System.out.println("persisting new termin # " + t.getId());
				JPAService.getEntityManager().persist(t);
				t.getPatienten().add(p);
				p.getTermine().add(t);
			}
			
			JPAService.getEntityManager().merge(p);
			JPAService.getEntityManager().flush();
			JPAService.getEntityManager().refresh(p);
	
			tx.commit();
			
			response.setContentType("text/html");
			out.append("comitted changes on termin #" + tid + " to database..");
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			tx.rollback();
			response.sendError(500);
		}

	}

}
