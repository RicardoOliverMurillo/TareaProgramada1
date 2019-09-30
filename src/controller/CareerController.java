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

import careerLogic.Career;
import careerLogic.CareerInterface;
import careerLogic.Plan;
import dao.DaoCareer;
import dao.DaoCareerInterface;

/**
 * Servlet implementation class CareerController
 */
@WebServlet("/CareerController")
public class CareerController extends HttpServlet implements CareerInterface,DaoCareerInterface{
	private static final long serialVersionUID = 1L;
	DaoCareer dbCareer = new DaoCareer();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CareerController() {
        super();
        // TODO Auto-generated constructor stub
    }
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	/*protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

	}*/

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("allCareers") != null) {
			ArrayList<Career> careerList = new ArrayList<Career>();
			try {
				careerList = getAllCareer();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			request.setAttribute("list", careerList);
			RequestDispatcher rd = request.getRequestDispatcher("RegisterCareerView.jsp");
			rd.forward(request, response);
			careerList.clear();
		}
		else if (request.getParameter("add") != null) {
			String id = request.getParameter("id");
			String name = request.getParameter("name");
			Career newCareer = new Career(id,name);
			try {
				addCareer(newCareer);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			response.sendRedirect("RegisterCareerView.jsp");
		}
	}
	
	private void addCareer(Career career) throws SQLException {
		dbCareer.manipulationQueryCareer("INSERT INTO CAREERS(IDCAREER,NAME) VALUES "
				+ "('"+career.getId()+"','"+career.getName()+"')");
	}
	
	private ArrayList<Career> getAllCareer() throws SQLException {
		ArrayList<Career> result = new ArrayList<Career>();
		result = dbCareer.selectQueryCareer("SELECT * FROM CAREERS");
		return result;
	}

	@Override
	public void addInfo(String pType, String pDescription) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addPlan(Plan plan) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setId(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Career> selectQueryCareer(String query) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void manipulationQueryCareer(String query) throws SQLException {
		// TODO Auto-generated method stub
		
	}

}
