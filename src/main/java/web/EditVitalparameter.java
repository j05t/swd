package main.java.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Enumeration;

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
@WebServlet("/app/EditVitalparameter")
public class EditVitalparameter extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditVitalparameter() {
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
		String id = request.getParameter("id");
		String vid = request.getParameter("vid");

		Vitalparameter v;
		
		String title;
		
		if(createNew != null) {
			title = "Vitalparameter anlegen";
			v = new Vitalparameter();
			v.setBelastungsSchmerz("");
			v.setBlutdruckDiastolisch("");
			v.setBlutdruckSystolisch("");
			v.setDiagnosisDate(LocalDate.now());
			v.setMaximalSchmerz("");
			v.setPuls("");
			v.setRuheSchmerz("");
			v.setTemperatur("");
		} else {
			title = "Vitalparameter bearbeiten";
			v = (new PatientService()).findVitalparameterById(Integer.parseInt(vid));
		}
		
		if(createNew != null) createNew = "true"; else createNew = "false";
		
		response.getWriter().append(
				"<span onclick=\"document.getElementById('myModal').style.display='none'\" class=\"close\">&times;</span>")
				.append("<h3>" + title + "</h3><form><input type='hidden' id='pid' value='" + id + "' type='text' /><br />"
						+ "<input type='hidden' id='vid' value='" + v.getId() + "' type='text' /><br />"
						+ "<input type='hidden' id='create' value='" + createNew + "' />"
						+ "<label>Datum:</label>  <input id='DiagnosisDate' value='" + v.getDiagnosisDate() + "' type='text' /><br />"
						+ "<label>BD Diastolisch:</label>  <input id='BlutdruckDiastolisch' value='" + v.getBlutdruckDiastolisch() + "' type='text' /><br />"
						+ "<label>BD Systolisch:</label>  <input id='BlutdruckSystolisch' value='" + v.getBlutdruckSystolisch() + "' type='text' /><br />"
						+ "<label>Puls:</label>  <input id='Puls' value='" + v.getPuls() +"' type='text' /><br />" 
						+ "<label>Temperatur:</label> <input id='Temperatur' value='" + v.getTemperatur() + "' type='text' /><br />" 
						+ "<label>Belastungsschmerz:</label> <input id='BelastungsSchmerz' value='" + v.getBelastungsSchmerz() + "' type='text' /><br />" 
						+ "<label>Maximalschmerz:</label> <input id='MaximalSchmerz' value='" + v.getMaximalSchmerz() + "' type='text' /><br />" 
						+ "<label>Ruheschmerz:</label> <input id='RuheSchmerz' value='" + v.getRuheSchmerz() + "' type='text' /><br />" 
						+ "<button class='editSubmit' onclick='sendVitalparameterForm()'>Absenden</button></form>");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		String vid = request.getParameter("vid");
		String createNew = request.getParameter("create");
		
		Vitalparameter v;

		Enumeration<String> params = request.getParameterNames(); 
		while(params.hasMoreElements()){
		 String paramName = params.nextElement();
		 System.out.println("Parameter Name - "+paramName+", Value - "+request.getParameter(paramName));
		}
		
		Patient p = (new PatientService()).findById(Integer.parseInt(id));
		
		System.out.println(p);
		
		EntityTransaction tx = JPAService.getEntityManager().getTransaction();
		tx.begin();
		
		if(createNew != null && createNew.equals("true")) {
			v = new Vitalparameter();
			p.getVitalParameter().add(v);
		} else {
			v = (new PatientService()).findVitalparameterById(Integer.parseInt(vid));
		}
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		v.setBelastungsSchmerz(request.getParameter("BelastungsSchmerz"));
		v.setBlutdruckDiastolisch(request.getParameter("BlutdruckDiastolisch"));
		v.setBlutdruckSystolisch(request.getParameter("BlutdruckSystolisch"));
		v.setDiagnosisDate(LocalDate.parse(request.getParameter("DiagnosisDate"), formatter) );
		v.setMaximalSchmerz(request.getParameter("MaximalSchmerz"));
		v.setPuls(request.getParameter("Puls"));
		v.setRuheSchmerz(request.getParameter("RuheSchmerz"));
		v.setTemperatur(request.getParameter("Temperatur"));
		
		JPAService.getEntityManager().merge(p);
		
		if(createNew == null) {
			System.out.println("merging vitalparameter " + v);
			JPAService.getEntityManager().merge(v);
		} else {
			System.out.println("persisting new vitalparameter " + v);
			JPAService.getEntityManager().persist(v);
		}
				
		JPAService.getEntityManager().flush();
		JPAService.getEntityManager().refresh(p);

		tx.commit();

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.append("comitted changes on person #" + id + " to database..");

		//reload tables
		//String loadDoc = " loadDoc('PatientDetail?id=" + p.getId() + "', 'detail')";
		//out.append("<script>loadDoc('Patienten');" + loadDoc + "</script>");
	}

}
