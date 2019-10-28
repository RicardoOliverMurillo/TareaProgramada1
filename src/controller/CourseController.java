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
import businessLogic.Plan;
import businessLogic.Session;
import businessLogic.Student;
import observerLogic.Action;
import observerLogic.CSV;
import observerLogic.Record;
import observerLogic.TXT;
import observerLogic.XML;
import services.AudioManipulation;
import services.Email;
import services.TextToSpeechClass;
import services.TranslateText;

/**
 * Servlet implementation class CourseController
 */
@WebServlet("/CourseController")
public class CourseController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Course courseCtrl = new Course();
	TranslateText txtTranslate = new TranslateText();
	private static String plan = "";
	private Record xml, csv, txt;
	private Action action;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CourseController() {
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
				action.setAction("view 2050 plan");
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
				action.setAction("view 2051 plan");
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
				action.setAction("view course specific information");
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
				action.setAction("view course specific equivalence information");
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
					action.setAction("view 2050 plan report");
				}else if (plan.equals("2051")) {
					request.setAttribute("passCourses", String.valueOf(courseCtrl.getPassCoursesEquivalencesReport(Session.getUser()).size()));
					request.setAttribute("passCredits", courseCtrl.getPassCreditsEquivalences(courseCtrl.getPassCoursesEquivalencesReport(Session.getUser()),plan));
					request.setAttribute("totalCredits", courseCtrl.getTotalCredits(plan));
					request.setAttribute("pendingCourses", String.valueOf(courseCtrl.getCoursesLength(plan)- courseCtrl.getPassCoursesEquivalencesReport(Session.getUser()).size()));
					RequestDispatcher rd = request.getRequestDispatcher("ReportsView.jsp");
					rd.forward(request, response);
					action.setAction("view 2051 plan report");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		else if(request.getParameter("emailReport") != null) {
			String language = request.getParameter("languageSelected");
			Student tempStudent = new Student();
			String userEmail = tempStudent.getStudentEmail(Session.getUser());
			String msg = tempStudent.reportsToString(courseCtrl, plan, Session.getUser());
			String subject = "Plan Report" + plan;
			try {
				if (language.equals("english")) {
					Email.sendEmail(userEmail, subject, msg);
					RequestDispatcher rd = request.getRequestDispatcher("ReportsView.jsp");
					rd.forward(request, response);
					action.setAction("send plan report by email");
					
				}else if(language.equals("both")) {
					String msgTranslated= txtTranslate.translate_text(msg, txtTranslate.getLanguage(msg), "es");
					subject += " English and Spanish";
					String finalMessage = "English \t";
					finalMessage += msg + "\t";
					finalMessage += "Español"+"\t";
					finalMessage += msgTranslated + "\t";
					
					Email.sendEmail(userEmail, subject, finalMessage);
					
					RequestDispatcher rd = request.getRequestDispatcher("ReportsView.jsp");
					rd.forward(request, response);
					action.setAction("send plan report by email");
				}
				else {
					String msgTranslated= txtTranslate.translate_text(msg, txtTranslate.getLanguage(msg), "es");
					String subjectTranslated = txtTranslate.translate_text(subject, txtTranslate.getLanguage(subject), "es");
					
					Email.sendEmail(userEmail, subjectTranslated, msgTranslated);
					
					RequestDispatcher rd = request.getRequestDispatcher("ReportsView.jsp");
					rd.forward(request, response);
					action.setAction("send plan report by email");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(request.getParameter("translateCourse") != null){
			try {String courseName = request.getParameter("translateCourse");
			
				TextToSpeechClass text = new TextToSpeechClass();
				text.create_Audio(txtTranslate.translate_text(courseName,txtTranslate.getLanguage(courseName),"en"));
				AudioManipulation audio = new AudioManipulation();
				audio.playAudio();
			}catch(Exception e) {
				e.printStackTrace();
			}
			RequestDispatcher rd = request.getRequestDispatcher("ViewInformation.jsp");
			rd.forward(request, response);
			action.setAction("listen to course name");
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