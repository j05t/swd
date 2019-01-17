package web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.Patient;
import data.Termin;
import service.TerminService;

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
		response.setContentType("text/html;charset=UTF-8");

		response.getWriter().append("<table id='mainTable'>\n" + 
				"  <tr>\n" + 
				"    <th>Datum</th>\n" + 
				"    <th>Uhrzeit</th>\n" + 
				"    <th>Patienten</th>\n" + 
				"  </tr>");
		
		for(Termin t: (new TerminService()).findAll()) {
			
			StringBuilder sb = new StringBuilder();

			for (Patient p: t.getPatienten()) {
				sb.append("<p>").append(p.getFirstName()).append(" ").append(p.getLastName()).append("</p>");
			}

				
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
