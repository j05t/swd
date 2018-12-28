package main.java.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.data.Person;
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
		
		for(Person p: (new PatientService()).findAll()) {
			String onClick = " onclick=\"loadDoc('PersonDetail?id=" + p.getId() + "', 'detail')\" ";
			String onBtnClick = " onclick=\"loadDoc('EditPatient?id=" + p.getId() + "', 'editContent'); document.getElementById('myModal').style.display='block';\"";
			response.getWriter().append(
			"  <tr title=\"" + p.getAddress().toString() + "\">\n" +
			"    <td" + onClick + ">" + p.getFirstName() + "</td>\n" + 
			"    <td>" + p.getLastName() + "</td>\n" + 
			"    <td>" + p.getAge() + "</td>\n" + 
			"    <td>" + p.getAdmissionDate() + "</td>\n" + 
			"    <td>" + p.getComment() + "</td>\n" + 
			"    <td><button" + onBtnClick + ">Edit</button>\n" + 
			"</td>\n" + 
			"  </tr>");
		}
		
		response.getWriter().append("</table>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
