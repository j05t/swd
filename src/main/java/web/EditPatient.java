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

import main.java.data.Person;
import main.java.service.JPAService;
import main.java.service.PatientService;

/**
 * Servlet implementation class EditPatient
 */
@WebServlet("/app/EditPatient")
public class EditPatient extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditPatient() {
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
		Person p = (new PatientService()).findById(Integer.parseInt(id));

		response.getWriter().append(
				"<span onclick=\"document.getElementById('myModal').style.display='none'\" class=\"close\">&times;</span>")
				.append("<form>" + "<input type='hidden' id='id' value='" + p.getId() + "' type='text' /></p>"
						+ "<label>Vorname:</label>  <input id='first_name' value='" + p.getFirstName() + "' type='text' /><br />"
						+ "<label>Nachname:</label>  <input id='last_name' value='" + p.getLastName() + "' type='text' /><br />"
						+ "<label>Alter:</label>  <input id='age' value='" + p.getAge() + "' type='text' /><br />"
						+ "<label>Aufnahmedatum:</label>  <input id='admission_date' value='" + p.getAdmissionDate()
						+ "' type='text' /><br />" + "<label>Warnhinweis:</label> <input id='comment' value='" + p.getComment()
						+ "' type='text' /><br />" + "<button id='editSubmit' onclick='sendPatientForm()'>Absenden</button>" + "</form>");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		String firstName = request.getParameter("first_name");
		String lastName = request.getParameter("last_name");
		String age = request.getParameter("age");
		String admission_date = request.getParameter("admission_date");
		String comment = request.getParameter("comment");

		System.out.println(comment);
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate dateTime = LocalDate.parse(admission_date, formatter);

		EntityTransaction tx = JPAService.getEntityManager().getTransaction();
		tx.begin();
		Person p = (new PatientService()).findById(Integer.parseInt(id));
		p.setFirstName(firstName);
		p.setLastName(lastName);
		p.setAge(Integer.parseInt(age));
		p.setAdmissionDate(dateTime);
		p.setComment(comment);
		JPAService.getEntityManager().merge(p);
		JPAService.getEntityManager().flush();
		JPAService.getEntityManager().refresh(p);
		tx.commit();

		System.out.println("merged entity " + p);

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.append("comitted changes on person #" + id + " to database..");

		//reload table
		out.append("<script>loadDoc('Patienten')</script>");
	}

}
