package main.java.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.data.Person;
import main.java.data.PersonService;

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
				"    <th>Id</th>\n" + 
				"    <th>Vorname</th>\n" + 
				"    <th>Nachname</th>\n" + 
				"  </tr>");
		
		for(Person p: PersonService.getInstance().findAll()) {
			response.getWriter().append(
			"  <tr onclick=\"loadDoc('PersonDetail?id=" + p.getId() + "', 'personDetail')\">\n" + 
			"    <td>" + p.getId() + "</td>\n" + 
			"    <td>" + p.getFirstName() + "</td>\n" + 
			"    <td>" + p.getLastName() + "</td>\n" + 
			"  </tr>");
		}
		
		response.getWriter().append("</table><div id=\"personDetail\"></div>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}