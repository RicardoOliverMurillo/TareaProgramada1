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
import dao.DaoPlanInterface;
import dao.DaoCareer;
import dao.DaoCareerInterface;

/**
 * Servlet implementation class PlanController
 */
@WebServlet("/PlanController")
public class PlanController extends HttpServlet implements PlanInterface,DaoCareerInterface,DaoPlanInterface{
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
	
	private void addPlan(Plan plan) throws SQLException {
		dbPlan.manipulationQueryPlan("INSERT INTO CXF11927.PLAN(IDPLAN,IDCAREER) VALUES "
				+ "('"+plan.getId()+"','"+plan.getCareer().getId()+"')");
	}
	
	private ArrayList<Career> getCareer(String idCareer) throws SQLException {
		ArrayList<Career> result = new ArrayList<Career>();
		result = dbCareer.selectQueryCareer("SELECT * FROM CXF11927.CAREER WHERE IDCAREER = '"+idCareer+"'");
		return result;
	}
	
	private ArrayList<Plan> getPlans(String idCareer) throws SQLException {
		System.out.println(idCareer+" hola");
		ArrayList<Plan> result = new ArrayList<Plan>();
		result = dbPlan.selectQueryPlan("SELECT * FROM CXF11927.PLAN WHERE IDCAREER = '"+idCareer+"'");
		System.out.println("entró");
		return result;
	}

	@Override
	public void addCourse(Course course) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void generateNetView() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addKnowledgeArea(String description) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setId(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Career getCareer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setCareer(Career career) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Plan> selectQueryPlan(String query) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void manipulationQueryPlan(String query) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Career> selectQueryCareer(String query) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void manipulationQueryCareer(String query) throws SQLException {
		// TODO Auto-generated method stub
		
	}

}
