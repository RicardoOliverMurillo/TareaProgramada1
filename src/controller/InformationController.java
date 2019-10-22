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

import businessLogic.Career;
import businessLogic.RelevantInfo;
import dao.DaoRelevantInfo;
import services.AudioManipulation;
import services.TextToSpeechClass;

/**
 * Servlet implementation class InformationController
 */
@WebServlet("/InformationController")
public class InformationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Career career = new Career();
	
       
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
				career.insertInformation(career);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			response.sendRedirect("RelevantInformation.jsp");
		}
		else if(request.getParameter("search") != null){
			String type = request.getParameter("type");
			String careerName = request.getParameter("career");
			request.setAttribute("type", type);
			request.setAttribute("career",careerName);
			try {
				String text = career.getInformation(type, careerName);
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
				career.updateInformation(career);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			response.sendRedirect("RelevantInformation.jsp");
		}
		else if(request.getParameter("searchInfo") != null){
			String careerName = request.getParameter("career");
			try {
				request.setAttribute("result", career.getInformation(careerName));
			} catch (SQLException e) {
				e.printStackTrace();
			}
			RequestDispatcher rd = request.getRequestDispatcher("ViewInformation.jsp");
			rd.forward(request, response);
		}
		else if(request.getParameter("makeSound") != null){
			try {String description = request.getParameter("makeSound");
				TextToSpeechClass txt = new TextToSpeechClass();
				txt.create_Audio(description);
				AudioManipulation audio = new AudioManipulation();
				audio.playAudio();
			}catch(Exception e) {
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
}
