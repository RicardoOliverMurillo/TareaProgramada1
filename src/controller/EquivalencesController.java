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

import behaviorLogic.Action;
import behaviorLogic.CSV;
import behaviorLogic.Record;
import behaviorLogic.TXT;
import behaviorLogic.XML;
import businessLogic.career.Course;
import dao.DaoCourse;

/**
 * Servlet implementation class EquivalencesController
 */
@WebServlet("/EquivalencesController")
public class EquivalencesController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Course courseCtrl = new Course();
	private String selectedPlan1,selectedPlan2,selectedCourse1,selectedCourse2;
	private Record xml, csv, txt;
	private Action action;

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EquivalencesController() {
        super();
        xml = new XML(action);
		csv = new CSV(action);
		txt = new TXT(action);
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		if(request.getParameter("choosePlan1") != null) {
			String idPlan1 = request.getParameter("idPlan1");
			ArrayList<Course> courseList1 = new ArrayList<Course>();
			try {
				courseList1 = courseCtrl.getCoursesPlan(idPlan1);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			request.setAttribute("list1Courses", courseList1);
			RequestDispatcher rd = request.getRequestDispatcher("RegisterEquivalencesView.jsp");
			rd.forward(request, response);
			selectedPlan1 = idPlan1;
			courseList1.clear();
			action.setAction("get all plans");
		}
		else if(request.getParameter("chooseCourse1") != null) {
			String idCourse1 = request.getParameter("idCourse1");
			selectedCourse1 = idCourse1;
			response.sendRedirect("RegisterEquivalencesView.jsp");
			action.setAction("get all courses");
		}
		else if(request.getParameter("choosePlan2") != null) {
			String idPlan2 = request.getParameter("idPlan2");
			ArrayList<Course> courseList2 = new ArrayList<Course>();
			try {
				courseList2 = courseCtrl.getCoursesPlan(idPlan2);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			request.setAttribute("list2Courses", courseList2);
			RequestDispatcher rd = request.getRequestDispatcher("RegisterEquivalencesView.jsp");
			rd.forward(request, response);
			selectedPlan2 = idPlan2;
			courseList2.clear();
			action.setAction("get all plans");
		}
		else if(request.getParameter("chooseCourse2") != null) {
			String idCourse2 = request.getParameter("idCourse2");
			selectedCourse2 = idCourse2;
			response.sendRedirect("RegisterEquivalencesView.jsp");
			action.setAction("get all courses");
		}
		else if (request.getParameter("addEquivalence") != null) {
			Course course1 = new Course();
			Course course2 = new Course();
			try {
				course1 = courseCtrl.getCourse(selectedCourse1,selectedPlan1);
				course2 = courseCtrl.getCourse(selectedCourse2,selectedPlan2);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			course1.setEquivalences(course2);
			course2.setEquivalences(course1);
			try {
				courseCtrl.addEquivalence(selectedCourse1,selectedPlan1,selectedCourse2,selectedPlan2);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			selectedPlan1 = "";
			selectedPlan2 = "";
			selectedCourse1 = "";
			selectedCourse2 = "";
			response.sendRedirect("RegisterEquivalencesView.jsp");
			action.setAction("register equivalence");
		}
	}
}
