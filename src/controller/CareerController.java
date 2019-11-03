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

import behaviorLogic.Action;
import behaviorLogic.CSV;
import behaviorLogic.Record;
import behaviorLogic.TXT;
import behaviorLogic.XML;
import businessLogic.career.Career;
import businessLogic.career.CareerInterface;
import businessLogic.career.Plan;
import dao.DaoCareer;

/**
 * Servlet implementation class CareerController
 */
@WebServlet("/CareerController")
public class CareerController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private Career career = new Career();
	private Record xml, csv, txt;
	private Action action;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CareerController() {
        super();
        action = new Action();
        xml = new XML(action);
		csv = new CSV(action);
		txt = new TXT(action);
    }
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("allCareers") != null) {
			ArrayList<Career> careerList = new ArrayList<Career>();
			try {
				careerList = career.getAllCareer();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			request.setAttribute("list", careerList);
			RequestDispatcher rd = request.getRequestDispatcher("RegisterCareerView.jsp");
			rd.forward(request, response);
			careerList.clear();
			action.setAction("view all careers");
		}
		else if (request.getParameter("add") != null) {
			String id = request.getParameter("id");
			String name = request.getParameter("name");
			Career newCareer = new Career(id,name);
			try {
				career.addCareer(newCareer);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			response.sendRedirect("RegisterCareerView.jsp");
			action.setAction("register career");
		}
	}
}
