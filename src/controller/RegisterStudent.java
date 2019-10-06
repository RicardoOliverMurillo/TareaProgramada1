package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoStudent;
import userLogic.Session;
import userLogic.Student;

/**
 * Servlet implementation class RegisterStudent
 */
@WebServlet("/RegisterStudent")
public class RegisterStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DaoStudent db = new DaoStudent();
       
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
			registerStudent(newStudent);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		response.sendRedirect("loginView.jsp");
	}
	
	private void registerStudent(Student student) throws Exception {
		db.manipulationQuery("INSERT INTO USERS (IDUSER, NAME, LASTNAME, EMAIL, PASSWORD, ROLE) VALUES ('"+student.getId()+"','"+
				student.getName()+"','"+student.getLastName()+"','"+student.getEmail()+"','"+student.getPassword()+"','"+student.getRole()+"')");
	}
}

