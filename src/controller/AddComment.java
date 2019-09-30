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
import servicios.SentimentAnalyzer;
import userLogic.Comment;
import userLogic.Session;


/**
 * Servlet implementation class AddComment
 */
@WebServlet("/AddComment")
public class AddComment extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DaoComment db = new DaoComment();
	SentimentAnalyzer analyzer = new SentimentAnalyzer();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddComment() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Comment> commentList = new ArrayList<Comment>();
		String option = request.getParameter("option");
		try {
			commentList = getComment(option);
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
				commentList = getCommentStudent(Session.getUser());
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
			Comment newComment = new Comment(text);
			System.out.println(newComment.toString());
			ArrayList<Comment> result = analyzer.analyzeComment(newComment);
			try {
				addComment(result);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			response.sendRedirect("StudentView.jsp");
		}
	}
	
	private void addComment(ArrayList<Comment> result) throws SQLException {
		for(int i = 0; i < result.size(); i++) {
			Comment comment = result.get(i);
			db.manipulationQuery("INSERT INTO COMMENTS (IDOWNER, DESCRIPTION, TONENAME, SCORE) VALUES "
					+ "('"+Session.getUser()+"','"+comment.getDescription()+"','"+comment.getToneName()
					+ "',"+comment.getScore()+")");
		}
	}
	
	private ArrayList<Comment> getComment(String option) throws SQLException {
		ArrayList<Comment> result = new ArrayList<Comment>();
		result = db.selectQuery("SELECT * FROM COMMENTS WHERE TONENAME = '"+option+"'");
		return result;
	}
	
	private ArrayList<Comment> getCommentStudent(String id) throws SQLException {
		ArrayList<Comment> result = new ArrayList<Comment>();
		result = db.selectQuery("SELECT * FROM COMMENTS WHERE IDOWNER = '"+id+"'");
		return result;
	}
	

}