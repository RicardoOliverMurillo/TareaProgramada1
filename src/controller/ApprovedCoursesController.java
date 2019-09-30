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
	}

	private void insertStudentCourse(String idStudent, String idCourse) throws Exception {
		db.manipulationQuery("INSERT INTO STUDENTCOURSE (IDSTUDENT, IDCOURSE) VALUES ('"+idStudent+"','"+idCourse+"')");
	}
	
	private void deleteStudentCourse(String idStudent, String idCourse) throws Exception {
		db.manipulationQuery("DELETE FROM STUDENTCOURSE WHERE IDCOURSE = '"+idCourse+"' AND IDSTUDENT = '"+idStudent+"'");
	}
}
