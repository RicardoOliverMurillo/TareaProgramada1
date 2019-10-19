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
	
	/**
	 * Method that insert the approved course of the student in the database
	 * @param data the courses that the student approved
	 * @throws Exception
	 */
	private void insertApprovedFilter(ArrayList<Course> data) throws Exception {
		for(int i = 0; i < data.size(); i++) {
			insertStudentCourse(Session.getUser(), data.get(i).getId());
		}
	}
	
	/**
	 * the method delete the approved course of the student from the database
	 * @param data the courses that the student wants to delete 
	 * @throws Exception
	 */
	private void deleteApprovedFilter(ArrayList<Course> data) throws Exception {
		for(int i = 0; i < data.size(); i++) {
			deleteStudentCourse(Session.getUser(), data.get(i).getId());
		}
	}
	
	/**
	 * the method get the courses of an specific plan 
	 * @param plan that has the courses requested
	 * @return result with the array of courses
	 * @throws SQLException
	 */
	private ArrayList<Course> getCoursesPlan(String plan) throws SQLException{
		System.out.println(plan);
		ArrayList<Course> data = db.selectQuery("SELECT * FROM COURSES WHERE IDPLAN = "+plan+"");
		for(int i = 0; i < data.size(); i++) {
			System.out.println(data.get(i).getName());
		}
		return data;
	}
	
	/**
	 * Method that filter the courses by semester 
	 * @param semester the specific semester that retrieves the information
	 * @return data with the courses of an specific semester 
	 * @throws SQLException
	 */
	private ArrayList<Course> getCoursesSemester(String semester) throws SQLException{
		ArrayList<Course> data = db.selectQuery("SELECT * FROM COURSES WHERE SEMESTER = '"+semester+"' AND IDPLAN = 2050");
		return data;
	}
	
	/**
	 * Method that filter the courses by knowledge Area
	 * @param area the specific knowledge Area that retrieves the information
	 * @return data with the courses of an specific knowledge Area
	 * @throws SQLException
	 */
	private ArrayList<Course> getCoursesArea(String area) throws SQLException{
		ArrayList<Course> data = db.selectQuery("SELECT * FROM COURSES WHERE KNOWLEDGEAREA  = '"+area+"' AND IDPLAN = 2050");
		return data;
	}
	
	/**
	 * Method that stores the information of the courses approved by an specific student in the database
	 * @param idStudent of the student that approve the course
	 * @param idCourse the course approved by the student
	 * @throws Exception
	 */
	private void insertStudentCourse(String idStudent, String idCourse) throws Exception {
		db.manipulationQuery("INSERT INTO STUDENTCOURSE (IDSTUDENT, IDCOURSE) VALUES ('"+idStudent+"','"+idCourse+"')");
	}
	
	/**
	 * Method that deletes an specific course approved by a student
	 * @param idStudent the student that wants to delete the course
	 * @param idCourse the course that is going to be deleted
	 * @throws Exception
	 */
	private void deleteStudentCourse(String idStudent, String idCourse) throws Exception {
		db.manipulationQuery("DELETE FROM STUDENTCOURSE WHERE IDCOURSE = '"+idCourse+"' AND IDSTUDENT = '"+idStudent+"'");
	}

}
