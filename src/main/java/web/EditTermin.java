package main.java.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Enumeration;

import javax.persistence.EntityTransaction;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.data.Patient;
import main.java.data.Termin;
import main.java.data.Vitalparameter;
import main.java.service.JPAService;
import main.java.service.PatientService;

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
		// TODO Auto-generated constructor stub
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
		
		Enumeration<String> params = request.getParameterNames(); 
		while(params.hasMoreElements()){
		 String paramName = params.nextElement();
		 System.out.println("Parameter Name - "+paramName+", Value - "+request.getParameter(paramName));
		}
		
		System.out.println("createNew is " + createNew);
		
		if(createNew != null) {
			title = "Termin anlegen";
			t = new Termin();
			t.setDate(LocalDate.now());
			t.setName("");
		} else {
			title = "Termin bearbeiten";
			t = (new PatientService()).findTerminById(Integer.parseInt(tid));
		}
		
		if(createNew != null) createNew = "true"; else createNew = "false";
		
		response.getWriter().append(
				"<span onclick=\"document.getElementById('myModal').style.display='none'\" class=\"close\">&times;</span>")
				.append("<h3>" + title + "</h3><form><input type='hidden' id='pid' value='" + pid + "' type='text' /><br />"
						+ "<input type='hidden' id='tid' value='" + t.getId() + "' type='text' /><br />"
						+ "<input type='hidden' id='create' value='" + createNew + "' />"
						+ "<label>Datum:</label>  <input id='datum' value='" + t.getDate() + "' type='text' /><br />"
						+ "<label>Zeit:</label>  <input id='zeit' value='" + (t.getTime() != null ? t.getTime() : "00:00") + "' type='text' /><br />"
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
		
		Termin t;

		Patient p = (new PatientService()).findById(Integer.parseInt(pid));
				
		EntityTransaction tx = JPAService.getEntityManager().getTransaction();
		tx.begin();
		
		if(createNew != null && createNew.equals("true")) {
			t = new Termin();
			p.getTermine().add(t);
		} else {
			t = (new PatientService()).findTerminById(Integer.parseInt(tid));
		}
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

		t.setTime(LocalTime.parse(request.getParameter("zeit"), timeFormatter) );
		t.setDate(LocalDate.parse(request.getParameter("datum"), formatter) );
		
		if(createNew == null) {
			System.out.println("merging termin " + t);
			JPAService.getEntityManager().merge(t);
		} else {
			System.out.println("persisting new termin " + t);
			JPAService.getEntityManager().persist(t);
		}
		JPAService.getEntityManager().merge(p);

		JPAService.getEntityManager().flush();
		JPAService.getEntityManager().refresh(p);

		tx.commit();

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.append("comitted changes on termin #" + tid + " to database..");
	}

}
