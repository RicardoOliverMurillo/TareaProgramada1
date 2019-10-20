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
import services.AudioManipulation;
import services.TextToSpeechClass;
import services.TranslateText;
import userLogic.Session;

/**
 * Servlet implementation class CourseController
 */
@WebServlet("/CourseController")
public class CourseController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Course courseCtrl = new Course();
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
				matriz = courseCtrl.getCourses(plan);
				request.setAttribute("result", matriz);
				request.setAttribute("pass", courseCtrl.getPassCourses(Session.getUser()));
				request.setAttribute("passCredits", courseCtrl.getPassCredits(courseCtrl.getPassCourses(Session.getUser()),plan));
				request.setAttribute("totalCredits", courseCtrl.getTotalCredits(plan));
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
				matriz = courseCtrl.getCourses(plan);
				request.setAttribute("result", matriz);
				request.setAttribute("pass", courseCtrl.getPassCoursesEquivalences(Session.getUser()));
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
				Course newCourse = courseCtrl.getCourse(id, plan);
				request.setAttribute("data", newCourse);
				request.setAttribute("requirements", courseCtrl.getRequirements(id,plan));
				request.setAttribute("corequirements", courseCtrl.getCorequirements(id,plan));
				RequestDispatcher rd = request.getRequestDispatcher("CourseInfoView.jsp");
				rd.forward(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		else if(request.getParameter("courseEquivalence") != null) {
			String id = request.getParameter("courseEquivalence");
			try {
				Course newCourse = courseCtrl.getCourse(id, plan);
				request.setAttribute("data", newCourse);
				request.setAttribute("requirements", courseCtrl.getRequirements(id,plan));
				request.setAttribute("corequirements", courseCtrl.getCorequirements(id,plan));
				RequestDispatcher rd = request.getRequestDispatcher("CourseInfoEquivalenceView.jsp");
				rd.forward(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		else if(request.getParameter("add2050Course") != null) {
			plan = "2050";
			try {
				ArrayList<Course> data = courseCtrl.getCoursesPlan(plan);
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
				ArrayList<Course> data = courseCtrl.getCoursesPlan(plan);
				request.setAttribute("data", data);
				request.setAttribute("planId", plan);
				RequestDispatcher rd = request.getRequestDispatcher("RegisterCourse.jsp");
				rd.forward(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		else if(request.getParameter("planReports") != null) {
			plan = request.getParameter("planSelected");;
			System.out.println(plan);
			try {
				if (plan.equals("2050")) {
					request.setAttribute("passCourses", String.valueOf(courseCtrl.getPassCourses(Session.getUser()).size()));
					request.setAttribute("passCredits", courseCtrl.getPassCredits(courseCtrl.getPassCourses(Session.getUser()),plan));
					request.setAttribute("totalCredits", courseCtrl.getTotalCredits(plan));
					request.setAttribute("pendingCourses", String.valueOf(courseCtrl.getCoursesLength(plan)- courseCtrl.getPassCourses(Session.getUser()).size()));
					RequestDispatcher rd = request.getRequestDispatcher("ReportsView.jsp");
					rd.forward(request, response);
				}else if (plan.equals("2051")) {
					request.setAttribute("passCourses", String.valueOf(courseCtrl.getPassCoursesEquivalencesReport(Session.getUser()).size()));
					request.setAttribute("passCredits", courseCtrl.getPassCreditsEquivalences(courseCtrl.getPassCoursesEquivalencesReport(Session.getUser()),plan));
					request.setAttribute("totalCredits", courseCtrl.getTotalCredits(plan));
					request.setAttribute("pendingCourses", String.valueOf(courseCtrl.getCoursesLength(plan)- courseCtrl.getPassCoursesEquivalencesReport(Session.getUser()).size()));
					RequestDispatcher rd = request.getRequestDispatcher("ReportsView.jsp");
					rd.forward(request, response);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		else if(request.getParameter("translateCourse") != null){
			try {String courseName = request.getParameter("translateCourse");
				TranslateText text = new TranslateText();
				TextToSpeechClass txt = new TextToSpeechClass();
				txt.create_Audio(text.translate_text(courseName));
				AudioManipulation audio = new AudioManipulation();
				audio.playAudio();
			}catch(Exception e) {
				e.printStackTrace();
			}
			RequestDispatcher rd = request.getRequestDispatcher("ViewInformation.jsp");
			rd.forward(request, response);
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
				courseCtrl.registerRequirements(idCourse, idRequirement,plan);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else if (request.getParameter("addCorequirements") != null) {
			String idCourse = request.getParameter("id");
			String idCoequirement = request.getParameter("corequirements");
			String plan = request.getParameter("plan");
			System.out.println(plan);
			try {
				courseCtrl.registerCorequirements(idCourse, idCoequirement,plan);
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
				courseCtrl.registerCourse(newCourse);
			} catch (Exception e) {
				e.printStackTrace();
			}
			response.sendRedirect("AdminView.jsp");
		}
	}
}