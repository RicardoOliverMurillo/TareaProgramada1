package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import observerLogic.Action;
import observerLogic.CSV;
import observerLogic.Record;
import observerLogic.TXT;
import observerLogic.XML;

/**
 * Servlet implementation class RecordController
 */
@WebServlet("/RecordController")
public class RecordController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Record xml, csv, txt;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecordController() {
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
		
	}

}
