package main.java.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.data.Patient;
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
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        
		Patient p = (new PatientService()).findById(Integer.parseInt(id));
		
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
			String onDelBtnClick = " onclick=\"loadDoc('DeleteVitalparameter?id=" + p.getId()  + "&vid=" + v.getId() + "', 'editContent'); document.getElementById('myModal').style.display='block';\"";
		
			response.getWriter().append(
			"  <tr>\n" + 
			"    <td>" + v.getDiagnosisDate() + "</td>\n" + 
			"    <td>" + v.getBlutdruckDiastolisch() + "</td>\n" + 
			"    <td>" + v.getBlutdruckSystolisch() + "</td>\n" + 
			"    <td>" + v.getPuls() + "</td>\n" + 
			"    <td>" + v.getTemperatur() + "</td>\n" + 
			"    <td>" + v.getBelastungsSchmerz() + "</td>\n" + 
			"    <td>" + v.getMaximalSchmerz() + "</td>\n" + 
			"    <td>" + v.getRuheSchmerz() + "</td>\n" + 
			"    <td><button class='editButton'" + onEditBtnClick + ">Edit</button> <button class='deleteButton'" + onDelBtnClick + ">X</button>\n" + 
			"  </tr>");
		}
		
		response.getWriter().append("</table>");
		
		String onNewBtnClick = " onclick=\"loadDoc('EditVitalparameter?new=1&id=" + p.getId() + " ', 'editContent'); document.getElementById('myModal').style.display='block';\"";
		response.getWriter().append("<button class='newButton' " + onNewBtnClick + ">+</button>");
		
		response.getWriter().append("</div>");
		
		
		
		response.getWriter().append("<div id=\"medikation\" class=\"tab\" style=\"display:none\">");
		response.getWriter().append("<p>load medikation here</p>");
		response.getWriter().append("</div>\n");
		
		
		
		
		response.getWriter().append("<div id=\"termine\" class=\"tab\" style=\"display:none\">");
		response.getWriter().append("<p>load termine here</p>");
		response.getWriter().append("</div>\n");
				
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
