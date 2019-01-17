package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityTransaction;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.Diagnose;
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
		response.setContentType("text/html;charset=UTF-8");

		String createNew = request.getParameter("new");
		String pid = request.getParameter("pid");
		String tid = request.getParameter("tid");
		PatientService ps = new PatientService();
		
		Termin t;
		String title;
		StringBuilder sb = new StringBuilder();

		if (createNew != null) {
			createNew = "true";
			title = "Termin anlegen";
			t = new Termin();
			t.setDate(LocalDate.now());
			t.setName("");
			
			for(Diagnose d: ps.findAllPossibleDiagnosis()) {
				sb.append("<br><label>" + d.getBezeichnung() + "</label><input type=\"checkbox\" class='diagCheckBox' id=\"" + d.getId() + "\" />");
			}
			
		} else {
			createNew = "false";
			title = "Termin bearbeiten";
			t = ps.findTerminById(Integer.parseInt(tid));
			
			for(Diagnose d: ps.findAllPossibleDiagnosis()) {
				String checked = "";
				if(t.getDiagnosen().contains(d)) checked= " checked='checked' ";
				
				sb.append("<br><label>" + d.getBezeichnung() + "</label><input type=\"checkbox\"" + checked + "class='diagCheckBox' id=\"" + d.getId() + "\" />");
			}
		}
		
		response.getWriter().append(
				"<span onclick=\"document.getElementById('myModal').style.display='none'\" class=\"close\">&times;</span>")
				.append("<h3>" + title + "</h3><form style='height: 264px' action=\"javascript:void(0);\"><input type='hidden' id='pid' value='" + pid
						+ "' />" + "<input type='hidden' id='tid' value='" + t.getId()
						+ "' />" + "<input type='hidden' id='create' value='" + createNew + "' />"
						+ "<label>Datum:</label>  <input id='datum' value='" + t.getDate() + "' type='text' /><br />"
						+ "<label>Zeit:</label>  <input id='zeit' value='"
						+ (t.getTime() != null ? t.getTime() : "00:00") + "' type='text' />"
						+ sb.toString()
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
		String diags = request.getParameter("diag");
		String createNew = request.getParameter("create");
		
		PatientService ps = new PatientService();
		
		// print all POST parameters
		Enumeration<String> params = request.getParameterNames(); 
		while(params.hasMoreElements()){
		 String paramName = params.nextElement();
		 System.out.println("Parameter Name - "+paramName+", Value - "+request.getParameter(paramName));
		}

		PrintWriter out = response.getWriter();
		Termin t;

		Patient p = ps.findById(Integer.parseInt(pid));
		
		if (createNew != null && createNew.equals("true")) {
			System.out.println("creating new appointment");
			t = new Termin();
		} else {
			System.out.println("edit appointment #" + tid);
			t = ps.findTerminById(Integer.parseInt(tid));
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
			List<Integer> ids = new ArrayList<Integer>();
			for(String s: diags.split(",")) {
				ids.add(Integer.parseInt(s));
			}
			
			Set<Diagnose> diagnosen = ps.findDiagnosisByIds(ids);
			t.setDiagnosen(diagnosen);

			if (createNew != null && createNew.equals("true")) {
				System.out.println("persisting new termin # " + t.getId());
				JPAService.getEntityManager().persist(t);
				t.getPatienten().add(p);
				p.getTermine().add(t);
			} else {
				System.out.println("merging termin " + t);
				JPAService.getEntityManager().merge(t);
			}

			JPAService.getEntityManager().merge(p);
			JPAService.getEntityManager().flush();
			JPAService.getEntityManager().refresh(p);
	
			tx.commit();
			
			response.setContentType("text/html");
			out.append("comitted changes on appointment #" + tid + " to database..");
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			tx.rollback();
			response.sendError(500);
		}

	}

}
