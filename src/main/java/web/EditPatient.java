package web;

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

import data.Patient;
import service.JPAService;
import service.PatientService;

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
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");

		String createNew = request.getParameter("new");
		Patient p;
		String title;

		if (createNew != null) {
			title = "Patient Anlegen";
			p = new Patient();
			p.setAdmissionDate(LocalDate.now());
			p.setFirstName("");
			p.setLastName("");
			p.setComment("");
		} else {
			title = "Patient bearbeiten";
			String id = request.getParameter("id");
			p = (new PatientService()).findById(Integer.parseInt(id));
		}

		response.getWriter().append(
				"<span onclick=\"document.getElementById('myModal').style.display='none'\" class=\"close\">&times;</span>")
				.append("<h3>" + title + "</h3><form><input type='hidden' id='id' value='" + p.getId() + "' />" 
						+ "<input type='hidden' id='create' value='"
						+ ((createNew) != null ? "true" : "false") + "' />"
						+ "<label>Vorname:</label>  <input id='first_name' value='" + p.getFirstName()
						+ "' type='text' /><br />" + "<label>Nachname:</label>  <input id='last_name' value='"
						+ p.getLastName() + "' type='text' /><br />" + "<label>Alter:</label>  <input id='age' value='"
						+ p.getAge() + "' type='text' /><br />"
						+ "<label>Aufnahmedatum:</label>  <input id='admission_date' value='" + p.getAdmissionDate()
						+ "' type='text' /><br />" + "<label>Warnhinweis:</label> <input id='comment' value='"
						+ p.getComment() + "' type='text' /><br />"
						+ "<label>Adresse:</label> <input id='street' value='" + p.getStreet()
						+ "' type='text' /><br />" + "<label>City:</label> <input id='city' value='" + p.getCity()
						+ "' type='text' /><br />" + "<label>PLZ:</label> <input id='zip' value='" + p.getZip()
						+ "' type='text' /><br />" + "<label>SV-Nummer:</label> <input id='ssn' value='" + p.getSsn()
						+ "' type='text' /><br />"
						+ "<button class='editSubmit' onclick='sendPatientForm()'>Absenden</button></form>");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		String createNew = request.getParameter("create");
		String firstName = request.getParameter("first_name");
		String lastName = request.getParameter("last_name");
		String age = request.getParameter("age");
		String admission_date = request.getParameter("admission_date");
		String comment = request.getParameter("comment");
		String street = request.getParameter("street");
		String city = request.getParameter("city");
		String zip = request.getParameter("zip");
		String ssn = request.getParameter("ssn");

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate dateTime = LocalDate.parse(admission_date, formatter);
		Patient p;

		EntityTransaction tx = JPAService.getEntityManager().getTransaction();
		try {
			tx.begin();

			if (createNew.equals("true")) {
				p = new Patient();
			} else {
				p = (new PatientService()).findById(Integer.parseInt(id));
			}

			p.setFirstName(firstName);
			p.setLastName(lastName);
			p.setAge(Integer.parseInt(age));
			p.setAdmissionDate(dateTime);
			p.setComment(comment);
			p.setCity(city);
			p.setStreet(street);
			p.setZip(zip);
			p.setSsn(ssn);

			JPAService.getEntityManager().persist(p);
			JPAService.getEntityManager().flush();
			JPAService.getEntityManager().refresh(p);
			
			tx.commit();

			System.out.println("merged entity " + p);

			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.append("comitted changes on person #" + id + " to database..");

		} catch (Exception e) {
			System.out.println(e.getMessage());
			tx.rollback();
			response.sendError(500);
		}
	}

}
