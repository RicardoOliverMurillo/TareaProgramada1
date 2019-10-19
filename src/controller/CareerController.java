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

/**
 * Servlet implementation class CareerController
 */
@WebServlet("/CareerController")
public class CareerController extends HttpServlet{
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

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
	
	/**
	 * the method inserts the information of the career in the database 
	 * @param career the career that it's going to be stored
	 * @throws SQLException
	 */
	private void addCareer(Career career) throws SQLException {
		dbCareer.manipulationQuery("INSERT INTO CAREERS(IDCAREER,NAME) VALUES "
				+ "('"+career.getId()+"','"+career.getName()+"')");
	}
	
	/**
	 * the method return all the careers stored in the database 
	 * @return result ArrayList with the careers stored in the database
	 * @throws SQLException
	 */
	private ArrayList<Career> getAllCareer() throws SQLException {
		ArrayList<Career> result = new ArrayList<Career>();
		result = dbCareer.selectQuery("SELECT * FROM CAREERS");
		return result;
	}

}
