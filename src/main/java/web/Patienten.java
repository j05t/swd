package web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.Patient;
import service.PatientService;

/**
 * Servlet implementation class Patienten
 */
@WebServlet("/app/Patienten")
public class Patienten extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Patienten() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean allowEdit = false;
        String role = (String) request.getSession().getAttribute("role");
        
        if (role != null)
        	allowEdit = role.equals("admin");
                
		response.getWriter().append("<table>\n" + 
				"  <tr>\n" + 
				"    <th>Vorname</th>\n" + 
				"    <th>Nachname</th>\n" + 
				"    <th>Alter</th>\n" + 
				"    <th>Aufnahmedatum</th>\n" + 
				"    <th>Warnhinweise</th>\n" + 
				"    <th>Tools</th>\n" + 
				"  </tr>");
		
		for(Patient p: (new PatientService()).findAll()) {
			String onClick = " onclick=\"loadDoc('PatientDetail?id=" + p.getId() + "', 'patientDetail')\" ";
			String onBtnClick = " onclick=\"loadDoc('EditPatient?id=" + p.getId() + "', 'editContent'); document.getElementById('myModal').style.display='block';\"";
			String onDelBtnClick = " onclick=\"loadDoc('DeletePatient?id=" + p.getId() + "', 'status'); document.getElementById('patient" + p.getId() + "').style.display='none';\"";
			response.getWriter().append(
			"  <tr id='patient" + p.getId() + "' title=\"" + p.getAddress() + "\">\n" +
			"    <td " + onClick + ">" + p.getFirstName() + "</td>\n" + 
			"    <td " + onClick + ">" + p.getLastName() + "</td>\n" + 
			"    <td " + onClick + ">" + p.getAge() + "</td>\n" + 
			"    <td " + onClick + ">" + p.getAdmissionDate() + "</td>\n" + 
			"    <td " + onClick + ">" + p.getComment() + "</td>\n" + (allowEdit ?
			"    <td><button class='editButton'" + onBtnClick + ">Edit</button> <button class='deleteButton'" + onDelBtnClick + ">X</button>\n" : "<td></td>") + 
			"</td>\n" + 
			"  </tr>");
		}
		
		response.getWriter().append("</table>");
		
		if (allowEdit) {
			String onNewBtnClick = " onclick=\"loadDoc('EditPatient?new=1', 'editContent'); document.getElementById('myModal').style.display='block';\"";
			response.getWriter().append("<button class='newButton' " + onNewBtnClick + ">+</button>");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
