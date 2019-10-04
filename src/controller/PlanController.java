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
import dao.DaoKnowledgeArea;

/**
 * Servlet implementation class PlanController
 */
@WebServlet("/PlanController")
public class PlanController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private DaoPlan dbPlan = new DaoPlan();
	private DaoCareer dbCareer = new DaoCareer();
	private DaoKnowledgeArea dbKnowledgeArea = new DaoKnowledgeArea();
	private String idPlan = "";
       
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
			//System.out.println(id);
			String idCareer = request.getParameter("idCareer");
			//System.out.println(idCareer);
			ArrayList<Career> careerList = new ArrayList<Career>();
			try {
				careerList = getCareer(idCareer);
				//System.out.println(careerList);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			Career career = careerList.get(0);
			Plan newPlan = new Plan(id, career);
			try {
				addPlan(newPlan);
				idPlan = id;
			} catch (SQLException e) {
				e.printStackTrace();
			}
			response.sendRedirect("AddKnowledgeAreaView.jsp");
			
		} else if (request.getParameter("allKnowledgeArea") != null) {
			ArrayList<String> knowledgeAreaList = new ArrayList<String>();
			try {
				knowledgeAreaList = getKnowledgeArea();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			request.setAttribute("listPlanWithKnowledgeArea", knowledgeAreaList);
			RequestDispatcher rd = request.getRequestDispatcher("AddKnowledgeAreaView.jsp");
			rd.forward(request, response);
			
		} else if (request.getParameter("addKnowledgeArea") != null) {
			String knowledgeArea = request.getParameter("knowledgeArea");
			//System.out.println("knowledgeArea: "+knowledgeArea);
			ArrayList<Plan> planList = new ArrayList<Plan>();
			try {
				setKnowledgeArea(knowledgeArea);
				planList = getPlan();
				Plan plan = (Plan) planList.get(0);
				//System.out.println("PlanKA: "+plan.getId());
				//plan.addKnowledgeArea(knowledgeArea);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			response.sendRedirect("AddKnowledgeAreaView.jsp");
			planList.clear();
		}
	}
	
	private void addPlan(Plan plan) throws SQLException {
		dbPlan.manipulationQuery("INSERT INTO PLANS(IDPLAN,IDCAREER) VALUES "
				+ "('"+plan.getId()+"','"+plan.getCareer().getId()+"')");
	}
	
	private ArrayList<Career> getCareer(String idCareer) throws SQLException {
		ArrayList<Career> result = new ArrayList<Career>();
		result = dbCareer.selectQuery("SELECT * FROM CAREERS WHERE IDCAREER = '"+idCareer+"'");
		return result;
	}
	
	private ArrayList<Plan> getPlans(String idCareer) throws SQLException {
		ArrayList<Plan> result = new ArrayList<Plan>();
		result = dbPlan.selectQuery("SELECT * FROM PLANS WHERE IDCAREER = '"+idCareer+"'");
		//System.out.println("entró");
		return result;
	}
	
	private ArrayList<Plan> getPlan() throws SQLException {
		ArrayList<Plan> result = new ArrayList<Plan>();
		result = dbPlan.selectQuery("SELECT * FROM PLANS WHERE IDPLAN = '"+idPlan+"'");
		return result;
	}
	
	private ArrayList<String> getKnowledgeArea() throws SQLException {
		ArrayList<String> result = new ArrayList<String>();
		result = dbKnowledgeArea.selectQuery("SELECT * FROM KNOWLEDGEAREAS WHERE IDPLAN = '"+idPlan+"'");
		return result;
	}
	
	private void setKnowledgeArea(String pKnowledgeArea) throws SQLException {
		dbKnowledgeArea.manipulationQuery("INSERT INTO KNOWLEDGEAREAS(IDPLAN,DESCRIPTION) VALUES "
				+ "('"+idPlan+"','"+pKnowledgeArea+"')");
	}

}
