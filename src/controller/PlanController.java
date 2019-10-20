//package controller;
/*
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/PlanController")
public class PlanController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public PlanController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}*/

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
import careerLogic.Course;
import careerLogic.Plan;
import careerLogic.PlanInterface;
import dao.DaoPlan;
import dao.DaoCareer;

/**
 * Servlet implementation class PlanController
 */
@WebServlet("/PlanController")
public class PlanController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	DaoPlan dbPlan = new DaoPlan();
	DaoCareer dbCareer = new DaoCareer();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PlanController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		if(request.getParameter("allPlans") != null) {
			String id = request.getParameter("careerOption");
			ArrayList<Plan> planList = new ArrayList<Plan>();
			try {
				planList = getPlans(id);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			request.setAttribute("listPlans", planList);
			RequestDispatcher rd = request.getRequestDispatcher("RegisterPlanView.jsp");
			rd.forward(request, response);
			planList.clear();
		}
		else if (request.getParameter("addPlan") != null) {
			String id = request.getParameter("idPlan");
			System.out.println(id);
			String idCareer = request.getParameter("idCareer");
			System.out.println(idCareer);
			ArrayList<Career> careerList = new ArrayList<Career>();
			try {
				careerList = getCareer(idCareer);
				System.out.println(careerList);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			Career career = careerList.get(0);
			Plan newPlan = new Plan(id, career);
			try {
				addPlan(newPlan);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			response.sendRedirect("RegisterPlanView.jsp");
		}
	}
	
	/**
	 * the method add the plan information to the database
	 * @param plan the plan that is going to be added
	 * @throws SQLException
	 */
	private void addPlan(Plan plan) throws SQLException {
		dbPlan.manipulationQuery("INSERT INTO PLANS(IDPLAN,IDCAREER) VALUES "
				+ "('"+plan.getId()+"','"+plan.getCareer().getId()+"')");
	}
	
	/**
	 * the method retrieves the careers stored in the database
	 * @param idCareer 
	 * @return result ArrayList with the careers requested
	 * @throws SQLException
	 */
	private ArrayList<Career> getCareer(String idCareer) throws SQLException {
		ArrayList<Career> result = new ArrayList<Career>();
		result = dbCareer.selectQuery("SELECT * FROM CAREERS WHERE IDCAREER = '"+idCareer+"'");
		return result;
	}
	
	/**
	 * The method gets all the plans of an specific career 
	 * @param idCareer the id of the career that has the plans  
	 * @return result Array List with the plans requested 
	 * @throws SQLException
	 */
	private ArrayList<Plan> getPlans(String idCareer) throws SQLException {
		System.out.println(idCareer+" hola");
		ArrayList<Plan> result = new ArrayList<Plan>();
		result = dbPlan.selectQuery("SELECT * FROM PLANS WHERE IDCAREER = '"+idCareer+"'");
		System.out.println("entró");
		return result;
	}

}
