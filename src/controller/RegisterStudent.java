package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import businessLogic.user.Session;
import businessLogic.user.Student;
import dao.DaoStudent;

/**
 * Servlet implementation class RegisterStudent
 */
@WebServlet("/RegisterStudent")
public class RegisterStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Student student = new Student();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterStudent() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String password = Session.encrypt(request.getParameter("password"));
		
		Student newStudent = new Student(id, name, lastName, email, password);
		try {
			student.registerStudent(newStudent);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		response.sendRedirect("loginView.jsp");
	}
}

