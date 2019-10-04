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

import careerLogic.Course;
import careerLogic.Plan;
import dao.DaoCourse;
import dao.DaoEquivalences;
import userLogic.Session;

/**
 * Servlet implementation class CourseController
 */
@WebServlet("/CourseController")
public class CourseController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DaoCourse dbCourse = new DaoCourse();
	private DaoEquivalences dbEquivalences = new DaoEquivalences();
	private static String plan = "";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CourseController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("2050") != null) {
			plan = "2050";
			try {
				String[][] matriz = new String[10][11];
				matriz = getCourses(plan);
				request.setAttribute("result", matriz);
				request.setAttribute("pass", getPassCourses(Session.getUser()));
				request.setAttribute("passCredits", getPassCredits(getPassCourses(Session.getUser()),plan));
				request.setAttribute("totalCredits", getTotalCredits(plan));
				request.setAttribute("planId", plan);
				RequestDispatcher rd = request.getRequestDispatcher("PlanView.jsp");
				rd.forward(request, response);
				matriz = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(request.getParameter("2051") != null) {
			plan = "2051";
			try {
				String[][] matriz = new String[10][11];
				matriz = getCourses(plan);
				request.setAttribute("result", matriz);
				request.setAttribute("pass", getPassCoursesEquivalences(Session.getUser()));
				request.setAttribute("passCredits", getPassCredits(getPassCoursesEquivalences(Session.getUser()),plan));
				request.setAttribute("totalCredits", getTotalCredits(plan));
				request.setAttribute("planId", plan);
				RequestDispatcher rd = request.getRequestDispatcher("EquivalencesPlanView.jsp");
				rd.forward(request, response);
				matriz = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		else if(request.getParameter("course") != null) {
			String id = request.getParameter("course");
			try {
				Course course = getCourse(id, plan);
				request.setAttribute("data", course);
				request.setAttribute("requirements", getRequirements(id,plan));
				request.setAttribute("corequirements", getCorequirements(id,plan));
				RequestDispatcher rd = request.getRequestDispatcher("CourseInfoView.jsp");
				rd.forward(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		else if(request.getParameter("courseEquivalence") != null) {
			String id = request.getParameter("courseEquivalence");
			try {
				Course course = getCourse(id, plan);
				request.setAttribute("data", course);
				request.setAttribute("requirements", getRequirements(id,plan));
				request.setAttribute("corequirements", getCorequirements(id,plan));
				RequestDispatcher rd = request.getRequestDispatcher("CourseInfoEquivalenceView.jsp");
				rd.forward(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		else if(request.getParameter("add2050Course") != null) {
			plan = "2050";
			try {
				ArrayList<Course> data = dbCourse.selectQuery("SELECT * FROM COURSES WHERE IDPLAN = " +plan);
				request.setAttribute("data", data);
				request.setAttribute("planId", plan);
				RequestDispatcher rd = request.getRequestDispatcher("RegisterCourse.jsp");
				rd.forward(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		else if(request.getParameter("add2051Course") != null) {
			plan = "2051";
			try {
				ArrayList<Course> data = dbCourse.selectQuery("SELECT * FROM COURSES WHERE IDPLAN = " +plan);
				request.setAttribute("data", data);
				request.setAttribute("planId", plan);
				RequestDispatcher rd = request.getRequestDispatcher("RegisterCourse.jsp");
				rd.forward(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("addRequirements") != null) {
			String idCourse = request.getParameter("id");
			String idRequirement = request.getParameter("requirements");
			String plan = request.getParameter("plan");
			try {
				registerRequirements(idCourse, idRequirement,plan);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else if (request.getParameter("addCorequirements") != null) {
			String idCourse = request.getParameter("id");
			String idCoequirement = request.getParameter("corequirements");
			String plan = request.getParameter("plan");
			System.out.println(plan);
			try {
				registerCorequirements(idCourse, idCoequirement,plan);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else {
			String id = request.getParameter("id");
			String name = request.getParameter("name");
			int sumCredits = Integer.parseInt(request.getParameter("sumCredits"));
			int semester = Integer.parseInt(request.getParameter("semester"));
			String knowledgeArea = request.getParameter("knowledgeArea");
			Plan plan = new Plan(request.getParameter("plan"));
		
			Course newCourse = new Course(id, name, sumCredits, semester, knowledgeArea, plan);
			try {
				registerCourse(newCourse);
			} catch (Exception e) {
				e.printStackTrace();
			}
			response.sendRedirect("AdminView.jsp");
		}
	}
	
	private Course getCourse(String idCourse, String idPlan) throws SQLException {
		ArrayList<Course> data = dbCourse.selectQuery("SELECT * FROM COURSES WHERE IDCOURSE = '"+idCourse+"' AND IDPLAN = '"+idPlan+"'");
		Course course = null;
		for(int i = 0; i < data.size(); i++) {
			course = data.get(i);
		}
		return course;
	}
	
	private ArrayList<String> getPassCourses(String idStudent) throws SQLException {
		ArrayList<String> result = dbEquivalences.selectQuery("SELECT IDCOURSE FROM STUDENTCOURSE WHERE IDSTUDENT = '"+idStudent+"'");
		return result;
	}
	
	private ArrayList<String> getPassCoursesEquivalences(String idStudent) throws SQLException{
		ArrayList<String> data = getPassCourses(idStudent);
		ArrayList<String> result = new ArrayList<String>();
		for(int i = 0; i < data.size(); i++) {
			String equivalence = getEquivalences(data.get(i));
			result.add(equivalence);
		}
		return result;
	}
	
	private String getEquivalences(String idCourse) throws SQLException{
			ArrayList<String> result = dbEquivalences.selectQuery("SELECT IDCOURSE2 FROM EQUIVALENCES WHERE IDCOURSE1 = '"+idCourse+"'");
			if(result.size()>0) {
				return result.get(0);
			}
			return null;
	}
	
	private String getPassCredits(ArrayList<String> courses, String idPlan) throws SQLException {
		int total = 0;
		for(int i = 0; i < courses.size(); i++) {
			Course course = getCourse(courses.get(i), idPlan);
			total = total + course.getSumCredits();
		}
		return String.valueOf(total);
	}
	
	private String getTotalCredits(String plan) throws SQLException {
		ArrayList<Course> data = dbCourse.selectQuery("SELECT * FROM COURSES WHERE IDPLAN = " + plan);
		int total = 0;
		for(int i = 0; i < data.size(); i++) {
			total= total + data.get(i).getSumCredits();
		}
		data.clear();
		return String.valueOf(total);
	}
	
	private ArrayList<String> getRequirements(String idCourse, String idPlan) throws SQLException{
		ArrayList<String> result = dbEquivalences.selectQuery("SELECT IDREQUIREMENT FROM REQUIREMENTS WHERE IDCOURSE = '"+idCourse+"' AND IDPLAN = '"+idPlan+"'");
		return result;
	}
	
	private ArrayList<String> getCorequirements(String idCourse, String idPlan) throws SQLException{
		ArrayList<String> result = dbEquivalences.selectQuery("SELECT IDCOREQUIREMENT FROM COREQUIREMENTS WHERE IDCOURSE = '"+idCourse+"' AND IDPLAN = '"+idPlan+"'");
		return result;
	}
	
	private void registerCourse(Course course) throws Exception {
		dbCourse.manipulationQuery("INSERT INTO COURSES (IDCOURSE,NAME,SUMCREDITS, SEMESTER, KNOWLEDGEAREA, IDPLAN) VALUES ('"+course.getId()+"','"+
				course.getName()+"','"+course.getSumCredits()+"','"+course.getSemester()+"','"+course.getKnowledgeArea()+"','"+course.getPlan().getId()+"')");
	}
	
	private void registerRequirements(String idCourse, String idRequirement, String idPlan) throws Exception {
		dbEquivalences.manipulationQuery("INSERT INTO REQUIREMENTS (IDCOURSE,IDREQUIREMENT,IDPLAN) VALUES ('"+idCourse+"','"+idRequirement+"','"+idPlan+"')");
	}
	
	private void registerCorequirements(String idCourse, String idCorequirement,String idPlan) throws Exception {
		dbEquivalences.manipulationQuery("INSERT INTO COREQUIREMENTS (IDCOURSE,IDCOREQUIREMENT,IDPLAN) VALUES ('"+idCourse+"','"+idCorequirement+"','"+idPlan+"')");
	}
	
	private String[][] getCourses(String plan) throws SQLException{
		ArrayList<Course> data = dbCourse.selectQuery("SELECT * FROM COURSES WHERE IDPLAN = "+plan);
		String[][] result = new String[10][11];
		System.out.println(data.size());
		for(int i = 0; i < data.size(); i++) {
			int sem = data.get(i).getSemester();
			for(int row = 0; row < 10; row++) {
				if(result[row][sem] == null) {
					Course newCourse = data.get(i);
					result[row][sem] = newCourse.getId();
					break;
				}
			}
		}
		data.clear();
		return result;
	}
}