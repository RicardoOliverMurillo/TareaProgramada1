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
import dao.DaoCourse;

/**
 * Servlet implementation class EquivalencesController
 */
@WebServlet("/EquivalencesController")
public class EquivalencesController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DaoCourse dbCourse = new DaoCourse();
	private String selectedPlan1;
	private String selectedPlan2;
	private String selectedCourse1;
	private String selectedCourse2;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EquivalencesController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		if(request.getParameter("choosePlan1") != null) {
			String idPlan1 = request.getParameter("idPlan1");
			ArrayList<Course> courseList1 = new ArrayList<Course>();
			try {
				courseList1 = getCourses(idPlan1);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			request.setAttribute("list1Courses", courseList1);
			RequestDispatcher rd = request.getRequestDispatcher("RegisterEquivalencesView.jsp");
			rd.forward(request, response);
			selectedPlan1 = idPlan1;
			courseList1.clear();
		}
		else if(request.getParameter("chooseCourse1") != null) {
			String idCourse1 = request.getParameter("idCourse1");
			selectedCourse1 = idCourse1;
			response.sendRedirect("RegisterEquivalencesView.jsp");
		}
		else if(request.getParameter("choosePlan2") != null) {
			String idPlan2 = request.getParameter("idPlan2");
			ArrayList<Course> courseList2 = new ArrayList<Course>();
			try {
				courseList2 = getCourses(idPlan2);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			request.setAttribute("list2Courses", courseList2);
			RequestDispatcher rd = request.getRequestDispatcher("RegisterEquivalencesView.jsp");
			rd.forward(request, response);
			selectedPlan2 = idPlan2;
			courseList2.clear();
		}
		else if(request.getParameter("chooseCourse2") != null) {
			String idCourse2 = request.getParameter("idCourse2");
			selectedCourse2 = idCourse2;
			response.sendRedirect("RegisterEquivalencesView.jsp");
		}
		else if (request.getParameter("addEquivalence") != null) {
			ArrayList<Course> courseList1 = new ArrayList<Course>();
			ArrayList<Course> courseList2 = new ArrayList<Course>();
			try {
				courseList1 = getCourse(selectedPlan1, selectedCourse1);
				courseList2 = getCourse(selectedPlan2, selectedCourse2);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			Course course1 = courseList1.get(0);
			Course course2 = courseList2.get(0);
			course1.addEquivalences(course2);
			course2.addEquivalences(course1);
			try {
				addEquivalence(selectedCourse1,selectedPlan1,selectedCourse2,selectedPlan2);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			selectedPlan1 = "";
			selectedPlan2 = "";
			selectedCourse1 = "";
			selectedCourse2 = "";
			response.sendRedirect("RegisterEquivalencesView.jsp");
		}
	}
	
	private void addEquivalence(String idCourse1,String idPlan1,String idCourse2, String idPlan2) throws SQLException {
		dbCourse.manipulationQuery("INSERT INTO EQUIVALENCES(IDCOURSE1,IDPLAN1,IDCOURSE2,IDPLAN2) VALUES "
				+ "('"+idCourse1+"','"+idPlan1+"','"+idCourse2+"','"+idPlan2+"')");
	}
	
	private ArrayList<Course> getCourse(String idPlan, String idCourse) throws SQLException {
		ArrayList<Course> result = new ArrayList<Course>();
		result = dbCourse.selectQuery("SELECT * FROM COURSES WHERE IDCOURSE = '"+idCourse+"'"+" AND "+"IDPLAN = '"+idPlan+"'");
		return result;
	}
	
	private ArrayList<Course> getCourses(String idPlan) throws SQLException {
		ArrayList<Course> result = new ArrayList<Course>();
		result = dbCourse.selectQuery("SELECT * FROM COURSES WHERE IDPLAN = '"+idPlan+"'");
		return result;
	}

}
