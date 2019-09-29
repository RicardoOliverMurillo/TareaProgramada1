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
 * Servlet implementation class CourseController
 */
@WebServlet("/CourseController")
public class CourseController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Course course;
	private ArrayList<Course> coursesList;
	private DaoCourse db = new DaoCourse();
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
			try {
				String[][] matriz = new String[10][11];
				matriz = getCourses();
				request.setAttribute("result", matriz);
				request.setAttribute("pass", getPassCourses(Session.getUser()));
				request.setAttribute("passCredits", getPassCredits(getPassCourses(Session.getUser())));
				request.setAttribute("totalCredits", getTotalCredits());
				RequestDispatcher rd = request.getRequestDispatcher("PlanView.jsp");
				rd.forward(request, response);
				matriz = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else if(request.getParameter("course") != null) {
			String id = request.getParameter("course");
			try {
				Course course = getCourse(id);
				request.setAttribute("data", course);
				request.setAttribute("requirements", getRequirements(id));
				request.setAttribute("corequirements", getCorequirements(id));
				RequestDispatcher rd = request.getRequestDispatcher("CourseInfoView.jsp");
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
			try {
				registerRequirements(idCourse, idRequirement);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else if (request.getParameter("addCorequirements") != null) {
			String idCourse = request.getParameter("id");
			String idCoequirement = request.getParameter("corequirements");
			try {
				registerCorequirements(idCourse, idCoequirement);
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		}else if (request.getParameter("updateState") != null) {
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
		}else {
			String id = request.getParameter("id");
			String name = request.getParameter("name");
			int sumCredits = Integer.parseInt(request.getParameter("sumCredits"));
			int semester = Integer.parseInt(request.getParameter("semester"));
			String knowledgeArea = request.getParameter("knowledgeArea");
		
			Course newCourse = new Course(id, name, sumCredits, semester, knowledgeArea);
			try {
				registerCourse(newCourse);
			} catch (Exception e) {
				e.printStackTrace();
			}
			response.sendRedirect("AdminView.jsp");
		}
	}
	
	private Course getCourse(String idCourse) throws SQLException {
		Course course = db.getCourse("SELECT * FROM CXF11927.COURSE WHERE ID = '"+idCourse+"'");
		return course;
	}
	
	private ArrayList<String> getPassCourses(String idStudent) throws SQLException {
		ArrayList<String> result = db.getPassCourses("SELECT IDCOURSE FROM CXF11927.STUDENTCOURSE WHERE IDSTUDENT = '"+idStudent+"'");
		return result;
	}
	
	private int getPassCredits(ArrayList<String> courses) throws SQLException {
		int total = 0;
		for(int i = 0; i < courses.size(); i++) {
			Course course = getCourse(courses.get(i));
			total = total + course.getSumCredits();
		}
		return total;
	}
	
	private int getTotalCredits() throws SQLException {
		ArrayList<Course> data = db.selectQuery("SELECT * FROM CXF11927.COURSE");
		int total = 0;
		for(int i = 0; i < data.size(); i++) {
			total= total + data.get(i).getSumCredits();
		}
		data.clear();
		return total;
	}
	
	private void registerCourse(Course course) throws Exception {
		db.manipulationQuery("INSERT INTO CXF11927.COURSE (ID,NAME,CREDITS, SEMESTER, AREA) VALUES ('"+course.getId()+"','"+
				course.getName()+"','"+course.getSumCredits()+"','"+course.getSemester()+"','"+course.getKnowledgeArea()+"')");
	}
	
	private ArrayList<String> getRequirements(String idCourse) throws SQLException{
		ArrayList<String> result = db.getDependentCourses("SELECT IDREQUIREMENT FROM CXF11927.REQUIREMENTS WHERE IDCOURSE = '"+idCourse+"'");
		return result;
	}
	
	private ArrayList<String> getCorequirements(String idCourse) throws SQLException{
		ArrayList<String> result = db.getDependentCourses("SELECT IDCOREQUIREMENTS FROM CXF11927.COREQUIREMENTS WHERE IDCOURSE = '"+idCourse+"'");
		return result;
	}
	
	private void registerRequirements(String idCourse, String idRequirement) throws Exception {
		db.manipulationQuery("INSERT INTO CXF11927.REQUIREMENTS (IDCOURSE,IDREQUIREMENT) VALUES ('"+idCourse+"','"+idRequirement+"')");
	}
	
	private void registerCorequirements(String idCourse, String idCorequirement) throws Exception {
		db.manipulationQuery("INSERT INTO CXF11927.COREQUIREMENTS (IDCOURSE,IDCOREQUIREMENTS) VALUES ('"+idCourse+"','"+idCorequirement+"')");
	}
	
	private void insertStudentCourse(String idStudent, String idCourse) throws Exception {
		db.manipulationQuery("INSERT INTO CXF11927.STUDENTCOURSE (IDSTUDENT, IDCOURSE) VALUES ('"+idStudent+"','"+idCourse+"')");
	}
	
	private void deleteStudentCourse(String idStudent, String idCourse) throws Exception {
		db.manipulationQuery("DELETE FROM CXF11927.STUDENTCOURSE WHERE IDCOURSE = '"+idCourse+"' AND IDSTUDENT = '"+idStudent+"'");
	}
	
	private String[][] getCourses() throws SQLException{
		ArrayList<Course> data = db.selectQuery("SELECT * FROM CXF11927.COURSE");
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
