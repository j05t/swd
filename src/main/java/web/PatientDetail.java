package main.java.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.data.Patient;
import main.java.data.Termin;
import main.java.data.Vitalparameter;
import main.java.service.PatientService;

/**
 * Servlet implementation class PersonDetail
 */
@WebServlet("/app/PatientDetail")
public class PatientDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PatientDetail() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        
        boolean allowEdit = false;
        String role = (String) request.getSession().getAttribute("role");
        
        if (role != null)
        	allowEdit = role.equals("admin");
        
		Patient p = (new PatientService()).findById(Integer.parseInt(id));

		response.getWriter().append("		<div class=\"w3-bar w3-black\">\n" + 
				"		  <button class=\"w3-bar-item w3-button\" onclick=\"loadTab('vitalparameter')\">Vitalparameter</button>\n" + 
				"		  <button class=\"w3-bar-item w3-button\" onclick=\"loadTab('medikation')\">Medikation</button>\n" + 
				"		  <button class=\"w3-bar-item w3-button\" onclick=\"loadTab('termine')\">Termine</button>\n" + 
				"		</div>");
		
		
		// tab vitalparameter
		response.getWriter().append("<div id=\"vitalparameter\" class=\"tab\">"); 
		response.getWriter().append("<h3>Vitalparameter fuer " + p.getFirstName() + " " + p.getLastName() + "</h3>");
		response.getWriter().append("<table>\n" + 
				"  <tr>\n" + 
				"    <th>Datum</th>\n" + 
				"    <th>BD Diastolisch</th>\n" + 
				"    <th>BD Systolisch</th>\n" + 
				"    <th>Puls</th>\n" + 
				"    <th>Temperatur</th>\n" + 
				"    <th>NRS Belastungsschmerz</th>\n" + 
				"    <th>NRS Maximalschmerz</th>\n" + 
				"    <th>NRS Ruheschmerz</th>\n" + 
				"    <th>Tools</th>\n" + 
				"  </tr>");

		for (Vitalparameter v: p.getVitalParameter()) {
			String onEditBtnClick = " onclick=\"loadDoc('EditVitalparameter?id=" + p.getId() + "&vid=" + v.getId() + "', 'editContent'); document.getElementById('myModal').style.display='block';\"";
			String onDelBtnClick = " onclick=\"loadDoc('DeleteVitalparameter?id=" + p.getId()  + "&vid=" + v.getId() + "', 'status'); document.getElementById('vitalparam" + v.getId() + "').style.display='none';\"";
			response.getWriter().append(
			"  <tr id='vitalparam" + v.getId() + "' >\n" + 
			"    <td>" + v.getDiagnosisDate() + "</td>\n" + 
			"    <td>" + v.getBlutdruckDiastolisch() + "</td>\n" + 
			"    <td>" + v.getBlutdruckSystolisch() + "</td>\n" + 
			"    <td>" + v.getPuls() + "</td>\n" + 
			"    <td>" + v.getTemperatur() + "</td>\n" + 
			"    <td>" + v.getBelastungsSchmerz() + "</td>\n" + 
			"    <td>" + v.getMaximalSchmerz() + "</td>\n" + 
			"    <td>" + v.getRuheSchmerz() + "</td>\n" + (allowEdit?
			"    <td><button class='editButton'" + onEditBtnClick + ">Edit</button> <button class='deleteButton'" + onDelBtnClick + ">X</button>\n" : "<td></td>") + 
			"  </tr>");
		}
	
		response.getWriter().append("</table>");
		
		if (allowEdit) {
			String onNewBtnClick = " onclick=\"loadDoc('EditVitalparameter?new=1&id=" + p.getId() + " ', 'editContent'); document.getElementById('myModal').style.display='block';\"";
			response.getWriter().append("<button class='newButton' " + onNewBtnClick + ">+</button>");
		}
		
		response.getWriter().append("</div>");
		
		
		
		// tab medikation
		response.getWriter().append("<div id=\"medikation\" class=\"tab\" style=\"display:none\">");
		response.getWriter().append("<h3>Medikation fuer " + p.getFirstName() + " " + p.getLastName() + "</h3>");
		response.getWriter().append("<table>\n" + 
				"  <tr>\n" + 
				"    <th>Datum</th>\n" + 
				"    <th>Uhrzeit</th>\n" + 
				"    <th>Diagnose</th>\n" + 
				"    <th>Medikation</th>\n" + 
				"  </tr>");
		
		for(Termin t: p.getTermine()) {	
			response.getWriter().append(
			"  <tr onclick=\"loadDoc('TerminDetail?id=" + t.getId() + "', 'detail')\">\n" + 
			"    <td>" + t.getDate() + "</td>\n" + 
			"    <td>" + t.getTime() + "</td>\n" + 
			"    <td>" + t.getDiagnosenAsString()+ "</td>\n" + 
			"    <td>" + t.getMedikationAsString() + "</td>\n" + 
			"  </tr>");
		}
		response.getWriter().append("</table>");
		response.getWriter().append("</div>\n");
		
		
		
		// tab termine
		response.getWriter().append("<div id=\"termine\" class=\"tab\" style=\"display:none\">");
		response.getWriter().append("<h3>Termine fuer " + p.getFirstName() + " " + p.getLastName() + "</h3>");
		response.getWriter().append("<table>\n" + 
				"  <tr>\n" + 
				"    <th>Datum</th>\n" + 
				"    <th>Uhrzeit</th>\n" + 
				"    <th>Adresse</th>\n" + 
				"    <th>Medikation</th>\n" + 
				"    <th>Tools</th>\n" + 
				"  </tr>");
		
		for(Termin t: p.getTermine()) {		
			String onEditBtnClick = " onclick=\"loadDoc('EditTermin?pid=" + p.getId() + "&tid=" + t.getId() + "', 'editContent'); document.getElementById('myModal').style.display='block';\"";
			String onDelBtnClick = " onclick=\"loadDoc('DeleteTermin?pid=" + p.getId()  + "&tid=" + t.getId() + "', 'status'); document.getElementById('" + "termin" + t.getId() + "').style.display='none';\"";

			response.getWriter().append(
			"  <tr id='termin" + t.getId() + "'>\n" + 
			"    <td>" + t.getDate() + "</td>\n" + 
			"    <td>" + t.getTime() + "</td>\n" + 
			"    <td>" + p.getAddress() + "</td>\n" + 
			"    <td>" + t.getMedikationAsString() + "</td>\n" + (allowEdit ?
			"    <td><button class='editButton'" + onEditBtnClick + ">Edit</button> <button class='deleteButton'" + onDelBtnClick + ">X</button>\n" : "<td></td>" ) + 
			"  </tr>");
		}
		response.getWriter().append("</table>");
		
		if (allowEdit) {
			String onNewTerminBtnClick = " onclick=\"loadDoc('EditTermin?new=1&pid=" + p.getId() + " ', 'editContent'); document.getElementById('myModal').style.display='block'\"";
			response.getWriter().append("<button class='newButton' " + onNewTerminBtnClick + ">+</button>");
		}
		
		response.getWriter().append("</div>\n");
				
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
