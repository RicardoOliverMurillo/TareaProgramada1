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

import dao.DaoComment;
import services.SentimentAnalyzer;
import services.SentimentAnalyzerInterface;
import userLogic.Comment;
import userLogic.Session;
import userLogic.Student;


/**
 * Servlet implementation class AddComment
 */
@WebServlet("/AddComment")
public class AddComment extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Student student = new Student();
	SentimentAnalyzerInterface analyzer;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddComment() {
        super();
        analyzer = new SentimentAnalyzer();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Comment> commentList = new ArrayList<Comment>();
		String option = request.getParameter("option");
		try {
			commentList = student.getComment(option);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("list", commentList);
		RequestDispatcher rd = request.getRequestDispatcher("CommentAnalysis.jsp");
		rd.forward(request, response);
		commentList.clear();

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("historial") != null) {
			ArrayList<Comment> commentList = new ArrayList<Comment>();
			try {
				commentList = student.getCommentStudent(Session.getUser());
			} catch (SQLException e) {
				e.printStackTrace();
			}
			request.setAttribute("list", commentList);
			RequestDispatcher rd = request.getRequestDispatcher("AddComment.jsp");
			rd.forward(request, response);
			commentList.clear();
		}
		else if (request.getParameter("add") != null) {
			String text = request.getParameter("comment");
			Student student = new Student(Session.getUser());
			student.setComment(text);
			try {
				for(int i = 0; i < student.getComments().size(); i++) {
					ArrayList<Comment> result = analyzer.analyzeComment(student.getComments().get(i));
					student.addComment(result);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			response.sendRedirect("StudentView.jsp");
		}
	}
}