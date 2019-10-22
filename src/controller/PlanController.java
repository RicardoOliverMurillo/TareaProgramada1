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

import businessLogic.Career;
import businessLogic.Course;
import businessLogic.Plan;
import businessLogic.PlanInterface;
import dao.DaoPlan;
import dao.DaoCareer;

/**
 * Servlet implementation class PlanController
 */
@WebServlet("/PlanController")
public class PlanController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private Plan plan = new Plan();
       
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
				planList = plan.getPlans(id);
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
				careerList = plan.getCareer(idCareer);
				System.out.println(careerList);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			Career career = careerList.get(0);
			Plan newPlan = new Plan(id, career);
			try {
				plan.addPlan(newPlan);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			response.sendRedirect("RegisterPlanView.jsp");
		}
	}
}
