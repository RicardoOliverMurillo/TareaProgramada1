package controller;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import userLogic.Session;

/**
 * Servlet implementation class LoginCheck
 */
@WebServlet("/LoginCheck")
public class LoginCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginCheck() {
        super();
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
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String role = request.getParameter("role");
		Session session = new Session(username);
		System.out.println("USER:"+username);
		System.out.println("Pass:"+password);
		System.out.println("Role:"+role);
		
		try {
			if(session.login(username, password, role)) {
				System.out.println("Entre");
				if(role.equals("Admin")) {
					response.sendRedirect("AdminView.jsp");
				}else if(role.equals("Student")){
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
