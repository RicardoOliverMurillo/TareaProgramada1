package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import careerLogic.Career;
import careerLogic.RelevantInfo;
import dao.DaoRelevantInfo;

/**
 * Servlet implementation class InformationController
 */
@WebServlet("/InformationController")
public class InformationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DaoRelevantInfo db = new DaoRelevantInfo();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InformationController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("add") != null) {
			Career career = new Career(request.getParameter("career"));
			String type = request.getParameter("type");
			String information = request.getParameter("information");
			career.addInfo(type, information);
			
			try {
				insertInformation(career);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			response.sendRedirect("RelevantInformation.jsp");
		}
		else if(request.getParameter("search") != null){
			String type = request.getParameter("type");
			String career = request.getParameter("career");
			request.setAttribute("type", type);
			request.setAttribute("career",career);
			try {
				String text = getInformation(type, career);
				request.setAttribute("text",text);
			} catch (SQLException e) {
				e.printStackTrace();
			}

			RequestDispatcher rd = request.getRequestDispatcher("UpdateInformation.jsp");
			rd.forward(request, response);
		}
		else if(request.getParameter("update") != null){
			Career career = new Career(request.getParameter("career"));
			String type = request.getParameter("type");
			String information = request.getParameter("information");
			career.addInfo(type, information);
			
			try {
				updateInformation(career);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			response.sendRedirect("RelevantInformation.jsp");
		}
		else if(request.getParameter("searchInfo") != null){
			String career = request.getParameter("career");
			try {
				request.setAttribute("result", getInformation(career));
			} catch (SQLException e) {
				e.printStackTrace();
			}
			RequestDispatcher rd = request.getRequestDispatcher("ViewInformation.jsp");
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("addEA") != null) {
			request.setAttribute("type", "EmploymentArea");
			RequestDispatcher rd = request.getRequestDispatcher("AddInformation.jsp");
			rd.forward(request, response);
		}
		else if (request.getParameter("updateEA") != null) {
			request.setAttribute("type", "EmploymentArea");
			RequestDispatcher rd = request.getRequestDispatcher("UpdateInformation.jsp");
			rd.forward(request, response);
		}
		else if (request.getParameter("addSP") != null) {
			request.setAttribute("type", "StudentProfile");
			RequestDispatcher rd = request.getRequestDispatcher("AddInformation.jsp");
			rd.forward(request, response);
		}
		else if (request.getParameter("updateSP") != null) {
			request.setAttribute("type", "StudentProfile");
			RequestDispatcher rd = request.getRequestDispatcher("UpdateInformation.jsp");
			rd.forward(request, response);
		}
		else if (request.getParameter("addMC") != null) {
			request.setAttribute("type", "MajorCharacteristics");
			RequestDispatcher rd = request.getRequestDispatcher("AddInformation.jsp");
			rd.forward(request, response);
		}
		else if (request.getParameter("updateMC") != null) {
			request.setAttribute("type", "MajorCharacteristics");
			RequestDispatcher rd = request.getRequestDispatcher("UpdateInformation.jsp");
			rd.forward(request, response);
		}
	}
	
	private void insertInformation(Career info) throws SQLException {
		ArrayList<RelevantInfo> data = info.getInfos(); 
		for(int i = 0; i < data.size(); i++) {
			db.manipulationQuery("INSERT INTO INFORMATIONS(TYPE,DESCRIPTION,IDCAREER) VALUES "
					+ "('"+data.get(i).getType()+"','"+data.get(i).getDescription()+"','"+info.getId()+"')");
		}
	}
	
	private String getInformation(String type, String career) throws SQLException {
		String text = new String();
		ArrayList<RelevantInfo> data = db.selectQuery("SELECT * FROM INFORMATIONS WHERE TYPE = '"+type+
				"' AND IDCAREER = '"+ career+"'");
		for(int i = 0; i < data.size(); i++) {
			text = data.get(i).getDescription();
		}
		return text;
	}
	
	private void updateInformation(Career info) throws SQLException {
		ArrayList<RelevantInfo> data = info.getInfos(); 
		for(int i = 0; i < data.size(); i++) {
			db.manipulationQuery("UPDATE INFORMATIONS SET TYPE = '"+data.get(i).getType()+"', "
				+ "DESCRIPTION = '"+data.get(i).getDescription()+"', "
				+ "IDCAREER = '"+info.getId()+"' WHERE "
				+ "TYPE = '"+data.get(i).getType()+"' AND "
				+ "IDCAREER = '"+info.getId()+"'");
		}
	}
	
	private ArrayList<RelevantInfo> getInformation(String career) throws SQLException{
		ArrayList<RelevantInfo> result = new ArrayList<RelevantInfo>();
		result = db.selectQuery("SELECT * FROM INFORMATIONS WHERE IDCAREER = '"+ career+"'");
		return result;
	}
}
