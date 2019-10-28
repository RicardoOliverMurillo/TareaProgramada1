package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import businessLogic.Session;
import observerLogic.Action;
import observerLogic.CSV;
import observerLogic.Record;
import observerLogic.TXT;
import observerLogic.XML;

/**
 * Servlet implementation class LoginCheck
 */
@WebServlet("/LoginCheck")
public class LoginCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Record xml, csv, txt;
	private Action action;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginCheck() {
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
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String role = request.getParameter("role");
		Session session = new Session(username);
		try {
			if(session.login(username, password, role)) {
				
				if(role.equals("Admin")) {
					response.sendRedirect("AdminView.jsp");
				}else if(role.equals("Student")){
					action.setAction("user login");
					response.sendRedirect("StudentView.jsp");
				}	
			}else {
				response.sendRedirect("loginView.jsp");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
