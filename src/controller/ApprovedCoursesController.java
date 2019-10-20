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
import userLogic.Session;

/**
 * Servlet implementation class ApprovedCoursesController
 */
@WebServlet("/ApprovedCoursesController")
public class ApprovedCoursesController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DaoCourse db = new DaoCourse();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApprovedCoursesController() {
        super();
        // TODO Auto-generated constructor stub
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
		if (request.getParameter("updateState") != null) {
			String studentId = Session.getUser();
			String idCourse = (String) request.getParameter("updateState");
			if(request.getParameter("state").equals("yes")) {
				try {
					insertStudentCourse(studentId, idCourse);
					response.sendRedirect("StudentView.jsp");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else {
				try {
					deleteStudentCourse(studentId, idCourse);
					response.sendRedirect("StudentView.jsp");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		else if(request.getParameter("addPlan")!= null) {
			try {
				ArrayList<Course> data = getCoursesPlan(request.getParameter("addPlan"));
				insertApprovedFilter(data);
				response.sendRedirect("StudentView.jsp");
				data.clear();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(request.getParameter("removePlan")!= null) {
			try {
				ArrayList<Course> data = getCoursesPlan(request.getParameter("removePlan"));
				deleteApprovedFilter(data);
				response.sendRedirect("StudentView.jsp");
				data.clear();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(request.getParameter("addSem")!= null) {
			try {
				ArrayList<Course> data = getCoursesSemester(request.getParameter("ApprovedSem"));
				insertApprovedFilter(data);
				response.sendRedirect("StudentView.jsp");
				data.clear();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(request.getParameter("removeSem")!= null) {
			try {
				ArrayList<Course> data = getCoursesSemester(request.getParameter("ApprovedSem"));
				deleteApprovedFilter(data);
				response.sendRedirect("StudentView.jsp");
				data.clear();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(request.getParameter("addArea")!= null) {
			try {
				System.out.println(request.getParameter("ApprovedSem"));
				ArrayList<Course> data = getCoursesArea(request.getParameter("ApprovedArea"));
				insertApprovedFilter(data);
				response.sendRedirect("StudentView.jsp");
				data.clear();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(request.getParameter("removeArea")!= null) {
			try {
				System.out.println(request.getParameter("ApprovedSem"));
				ArrayList<Course> data = getCoursesArea(request.getParameter("ApprovedArea"));
				deleteApprovedFilter(data);
				response.sendRedirect("StudentView.jsp");
				data.clear();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private void insertApprovedFilter(ArrayList<Course> data) throws Exception {
		for(int i = 0; i < data.size(); i++) {
			insertStudentCourse(Session.getUser(), data.get(i).getId());
		}
	}
	
	private void deleteApprovedFilter(ArrayList<Course> data) throws Exception {
		for(int i = 0; i < data.size(); i++) {
			deleteStudentCourse(Session.getUser(), data.get(i).getId());
		}
	}
	
	private ArrayList<Course> getCoursesPlan(String plan) throws SQLException{
		System.out.println(plan);
		ArrayList<Course> data = db.selectQuery("SELECT * FROM COURSES WHERE IDPLAN = "+plan+"");
		for(int i = 0; i < data.size(); i++) {
			System.out.println(data.get(i).getName());
		}
		return data;
	}
	
	private ArrayList<Course> getCoursesSemester(String semester) throws SQLException{
		ArrayList<Course> data = db.selectQuery("SELECT * FROM COURSES WHERE SEMESTER = '"+semester+"' AND IDPLAN = 2050");
		return data;
	}
	
	private ArrayList<Course> getCoursesArea(String area) throws SQLException{
		ArrayList<Course> data = db.selectQuery("SELECT * FROM COURSES WHERE KNOWLEDGEAREA  = '"+area+"' AND IDPLAN = 2050");
		return data;
	}
	
	private void insertStudentCourse(String idStudent, String idCourse) throws Exception {
		db.manipulationQuery("INSERT INTO STUDENTCOURSE (IDSTUDENT, IDCOURSE) VALUES ('"+idStudent+"','"+idCourse+"')");
	}
	
	private void deleteStudentCourse(String idStudent, String idCourse) throws Exception {
		db.manipulationQuery("DELETE FROM STUDENTCOURSE WHERE IDCOURSE = '"+idCourse+"' AND IDSTUDENT = '"+idStudent+"'");
	}

}
