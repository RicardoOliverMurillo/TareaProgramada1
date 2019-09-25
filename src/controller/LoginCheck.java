package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoStudent;
import dao.Db2Connection;
import userLogic.Student;

/**
 * Servlet implementation class LoginCheck
 */
@WebServlet("/LoginCheck")
public class LoginCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DaoStudent db = new DaoStudent();
       
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
		
		try {
			if(validateUser(username, password, role)) {
				if(role.equals("admin")) {
					response.sendRedirect("AdminView.jsp");
				}else if(role.equals("student")){
					response.sendRedirect("StudentView.jsp");
				}	
			}else {
				response.sendRedirect("loginView.jsp");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private boolean validateUser(String username, String password, String role) throws SQLException {
		ArrayList<Student> result = db.selectQuery("SELECT * FROM CXF11927.STUDENT WHERE ID = '"+ username+"'");
		System.out.println(result.size());
		for(int i = 0; i < result.size(); i++) {
			if(result.get(i).getId().equals(username) && result.get(i).getPassword().equals(password)) {
				return true;
			}
		}
		return false;
	}
}
