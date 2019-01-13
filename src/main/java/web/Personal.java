package main.java.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.data.Arzt;
import main.java.service.PersonalService;

/**
 * Servlet implementation class Personal
 */
@WebServlet("/app/Personal")
public class Personal extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Personal() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("<table>\n" + 
				"  <tr>\n" + 
				"    <th>Funktion</th>\n" + 
				"    <th>Name</th>\n" + 
				"    <th>Fachgebiet</th>\n" + 
				"    <th>Durchwahl</th>\n" + 
				"  </tr>");
		
		for(Arzt p: (new PersonalService()).findAll()) {
			response.getWriter().append(
			"  <tr onclick=\"loadDoc('PersonalDetail?id=" + p.getId() + "', 'detail')\">\n" + 
			"    <td>" + p.getRole() + "</td>\n" + 
			"    <td>" + p.getName() + "</td>\n" + 
			"    <td>" + p.getFachgebiet() + "</td>\n" + 
			"    <td>" + p.getDurchwahl() + "</td>\n" + 
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
