package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import behaviorLogic.Action;
import behaviorLogic.CSV;
import behaviorLogic.Record;
import behaviorLogic.TXT;
import behaviorLogic.XML;
import creationalLogic.SimpleFactoryRecord;

/**
 * Servlet implementation class RecordController
 */
@WebServlet("/RecordController")
public class RecordController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Record xml, csv, txt;
	private Action action;
	private SimpleFactoryRecord factory;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecordController() {
    	super();
    	action = new Action();
    	factory = new SimpleFactoryRecord();
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
		String format = request.getParameter("format");
		Record record;
		try {
			record = factory.createRecord(format);
			String[] data = record.read();
			request.setAttribute("data", data);
			RequestDispatcher rd = request.getRequestDispatcher("RecordView.jsp");
			rd.forward(request, response);
			action.setAction("get record");
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
