package main.java.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.data.Patient;
import main.java.data.Termin;
import main.java.service.TerminService;

/**
 * Servlet implementation class Termine
 */
@WebServlet("/app/Termine")
public class Termine extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Termine() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("<table>\n" + 
				"  <tr>\n" + 
				"    <th>Datum</th>\n" + 
				"    <th>Uhrzeit</th>\n" + 
				"    <th>Patienten</th>\n" + 
				"  </tr>");
		
		for(Termin t: (new TerminService()).findAll()) {
			
			StringBuilder sb = new StringBuilder();
			sb.append("<ul>");
			for (Patient p: t.getPatienten()) {
				sb.append("<li>").append(p.getFirstName()).append(" ").append(p.getLastName()).append("</li>");
			}
			sb.append("</ul>");
				
			response.getWriter().append(
			"  <tr onclick=\"loadDoc('TerminDetail?id=" + t.getId() + "', 'detail')\">\n" + 
			"    <td>" + t.getDate() + "</td>\n" + 
			"    <td>" + t.getTime() + "</td>\n" + 
			"    <td>" + sb.toString() + "</td>\n" + 
			"  </tr>");
		}
		
		response.getWriter().append("</table>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
