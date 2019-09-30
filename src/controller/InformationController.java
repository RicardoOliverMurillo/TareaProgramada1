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
			String type = request.getParameter("type");
			String information = request.getParameter("information");
			String career = request.getParameter("career");
			RelevantInfo info = new RelevantInfo(type, information,career);
			
			try {
				insertInformation(info);
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
			String type = request.getParameter("type");
			String information = request.getParameter("information");
			String career = request.getParameter("career");
			RelevantInfo info = new RelevantInfo(type, information,career);
			
			try {
				updateInformation(info);
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
	
	private void insertInformation(RelevantInfo info) throws SQLException {
		db.manipulationQuery("INSERT INTO INFORMATIONS(TYPE,DESCRIPTION, IDCAREER) VALUES "
			+ "('"+info.getType()+"','"+info.getDescription()+/*"','"+info.getCareer()+*/"')");
	}
	
	private String getInformation(String type, String idCareer) throws SQLException {
		String text = db.getInformation("SELECT * FROM INFORMATIONS WHERE TYPE = '"+type+
				"' AND IDCAREER = '"+ idCareer+"'");
		return text;
	}
	
	private void updateInformation(RelevantInfo info) throws SQLException {
		db.manipulationQuery("UPDATE INFORMATIONS SET TYPE = '"+info.getType()+"', "
				+ "DESCRIPTION = '"+info.getDescription()+"', "
				+ "CAREER = '"+info.getCareer()+"' WHERE "
				+ "TYPE = '"+info.getType()+"' AND "
				+ "CAREER = '"+info.getCareer()+"'");
	}
	
	private ArrayList<RelevantInfo> getInformation(String idCareer) throws SQLException{
		ArrayList<RelevantInfo> result = new ArrayList<RelevantInfo>();
		result = db.selectQuery("SELECT * FROM INFORMATIONS WHERE CAREER = '"+ idCareer+"'");
		return result;
	}
}
