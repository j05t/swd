package main.java.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.data.Patient;
import main.java.service.PatientService;

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
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
			String onClick = " onclick=\"loadDoc('PatientDetail?id=" + p.getId() + "', 'detail')\" ";
			String onBtnClick = " onclick=\"loadDoc('EditPatient?id=" + p.getId() + "', 'editContent'); document.getElementById('myModal').style.display='block';\"";
			String onDelBtnClick = " onclick=\"loadDoc('DeletePatient?id=" + p.getId() + "', 'editContent'); document.getElementById('myModal').style.display='block';\"";
			response.getWriter().append(
			"  <tr title=\"" + p.getAddress() + "\">\n" +
			"    <td " + onClick + ">" + p.getFirstName() + "</td>\n" + 
			"    <td " + onClick + ">" + p.getLastName() + "</td>\n" + 
			"    <td " + onClick + ">" + p.getAge() + "</td>\n" + 
			"    <td " + onClick + ">" + p.getAdmissionDate() + "</td>\n" + 
			"    <td " + onClick + ">" + p.getComment() + "</td>\n" + 
			"    <td><button id='editButton'" + onBtnClick + ">Edit</button> <button id='deleteButton'" + onDelBtnClick + ">X</button>\n" + 
			"</td>\n" + 
			"  </tr>");
		}
		
		response.getWriter().append("</table>");
		
		String onNewBtnClick = " onclick=\"loadDoc('EditPatient?new=1', 'editContent'); document.getElementById('myModal').style.display='block';\"";
		response.getWriter().append("<button id='newButton' " + onNewBtnClick + ">+</button>");
		
		response.getWriter().append("<div id=\"detail\"></div>\n");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
