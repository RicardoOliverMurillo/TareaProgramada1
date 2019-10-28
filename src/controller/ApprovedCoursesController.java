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

import businessLogic.Course;
import businessLogic.Session;
import observerLogic.Action;
import observerLogic.CSV;
import observerLogic.Record;
import observerLogic.TXT;
import observerLogic.XML;

/**
 * Servlet implementation class ApprovedCoursesController
 */
@WebServlet("/ApprovedCoursesController")
public class ApprovedCoursesController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Course course = new Course();
	private Record xml, csv, txt;
	private Action action;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApprovedCoursesController() {
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
		if (request.getParameter("updateState") != null) {
			String studentId = Session.getUser();
			String idCourse = (String) request.getParameter("updateState");
			if(request.getParameter("state").equals("yes")) {
				try {
					course.insertStudentCourse(studentId, idCourse);
					response.sendRedirect("StudentView.jsp");
					action.setAction("insert an approved course");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else {
				try {
					course.deleteStudentCourse(studentId, idCourse);
					response.sendRedirect("StudentView.jsp");
					action.setAction("delete an approved course");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		else if(request.getParameter("addPlan")!= null) {
			try {
				ArrayList<Course> data = course.getCoursesPlan(request.getParameter("addPlan"));
				course.insertApprovedFilter(data);
				response.sendRedirect("StudentView.jsp");
				action.setAction("insert an approved plan");
				data.clear();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(request.getParameter("removePlan")!= null) {
			try {
				ArrayList<Course> data = course.getCoursesPlan(request.getParameter("removePlan"));
				course.deleteApprovedFilter(data);
				response.sendRedirect("StudentView.jsp");
				action.setAction("delete an approved plan");
				data.clear();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(request.getParameter("addSem")!= null) {
			try {
				ArrayList<Course> data = course.getCoursesSemester(request.getParameter("ApprovedSem"));
				course.insertApprovedFilter(data);
				response.sendRedirect("StudentView.jsp");
				action.setAction("insert an approved semester");
				data.clear();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(request.getParameter("removeSem")!= null) {
			try {
				ArrayList<Course> data = course.getCoursesSemester(request.getParameter("ApprovedSem"));
				course.deleteApprovedFilter(data);
				response.sendRedirect("StudentView.jsp");
				action.setAction("delete an approved semester");
				data.clear();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(request.getParameter("addArea")!= null) {
			try {
				System.out.println(request.getParameter("ApprovedSem"));
				ArrayList<Course> data = course.getCoursesArea(request.getParameter("ApprovedArea"));
				course.insertApprovedFilter(data);
				response.sendRedirect("StudentView.jsp");
				action.setAction("insert an approved knowledge area");
				data.clear();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(request.getParameter("removeArea")!= null) {
			try {
				System.out.println(request.getParameter("ApprovedSem"));
				ArrayList<Course> data = course.getCoursesArea(request.getParameter("ApprovedArea"));
				course.deleteApprovedFilter(data);
				response.sendRedirect("StudentView.jsp");
				action.setAction("delete an approved knowledge area");
				data.clear();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
