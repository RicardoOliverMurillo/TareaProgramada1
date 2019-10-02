package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import careerLogic.Course;
import dao.DaoCourse;

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
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		int sumCredits = Integer.parseInt(request.getParameter("sumCredits"));
		int semester = Integer.parseInt(request.getParameter("semester"));
		String knowledgeArea = request.getParameter("knowledgeArea");
		String type = request.getParameter("type");
		
		Course newCourse = new Course(id, name, sumCredits, semester, knowledgeArea, type);
		System.out.println(newCourse.getName());
		try {
			registerCourse(newCourse);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect("StudentView.jsp");
	}
	
	private void registerCourse(Course course) throws Exception {
		System.out.println("ejecutando query");
		db.manipulationQuery("INSERT INTO COURSES(IDCOURSE,NAME, SUMCREDITS, SEMESTER, KNOWLEDGEAREA, IDPLAN) VALUES ('"+course.getId()+"','"+
				course.getName()+"','"+course.getSumCredits()+"','"+course.getSemester()+"','"+course.getKnowledgeArea()+"','"+course.getIdPlan()+"')");
	}

}
