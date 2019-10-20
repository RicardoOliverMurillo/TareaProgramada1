package careerLogic;

import java.sql.SQLException;
import java.util.ArrayList;

import dao.DaoCareer;
import dao.DaoInterface;
import dao.DaoPlan;

/**
 * 
 * @author Marco Gómez, Ricardo Oliver, Anjelica Tristani 
 *
 * Class Plan for the insertion and manipulation of the plan information. 
 */
public class Plan implements PlanInterface{
	private String id;
	private Career career;
	private ArrayList<String> knowledgeAreaDesciption;
	private ArrayList<Course> courses;
	private DaoInterface dbPlan, dbCareer;
	
	/**
	 *  Constructor of the class Plan
	 */
	public Plan() {
		dbPlan = new DaoPlan();
		dbCareer = new DaoCareer();
	}
	
	/**
	 * Constructor of the class Plan with the id
	 * @param pId id of the plan
	 */
	public Plan(String id) {
		this();
		this.id = id;
		knowledgeAreaDesciption = new ArrayList<String>();
		courses = new ArrayList<Course>();
	}
	/**
	 * Constructor of the class Plan with the id and the career
	 * @param pId id of the plan
	 * @param pCareer career where the plan belongs
	 */
	public Plan(String id, Career career) {
		this(id);
		this.career = career;
	}
	
	/**
	 * the method add a new course to the plan array list
	 * @param course the course that is going to be added
	 */
	public void addCourse(Course course) {
		courses.add(course);
	}
	
	/**
	 * the method add the description of the knowledge of area that the plan belongs
	 * @param description with the knowledge area that the plan belongs 
	 */
	public void addKnowledgeArea(String description) {
		knowledgeAreaDesciption.add(description);
	}
	
	/**
	 * the method add the plan information to the database
	 * @param plan the plan that is going to be added
	 * @throws SQLException
	 */
	public void addPlan(Plan plan) throws SQLException {
		dbPlan.manipulationQuery("INSERT INTO PLANS(IDPLAN,IDCAREER) VALUES "
				+ "('"+plan.getId()+"','"+plan.getCareer().getId()+"')");
	}
	
	/**
	 * the method retrieves the careers stored in the database
	 * @param idCareer 
	 * @return result ArrayList with the careers requested
	 * @throws SQLException
	 */
	public ArrayList<Career> getCareer(String idCareer) throws SQLException {
		ArrayList<Career> result = new ArrayList<Career>();
		result = (ArrayList<Career>) dbCareer.selectQuery("SELECT * FROM CAREERS WHERE IDCAREER = '"+idCareer+"'");
		return result;
	}
	
	/**
	 * The method gets all the plans of an specific career 
	 * @param idCareer the id of the career that has the plans  
	 * @return result Array List with the plans requested 
	 * @throws SQLException
	 */
	public ArrayList<Plan> getPlans(String idCareer) throws SQLException {
		ArrayList<Plan> result = new ArrayList<Plan>();
		result = (ArrayList<Plan>) dbPlan.selectQuery("SELECT * FROM PLANS WHERE IDCAREER = '"+idCareer+"'");
		return result;
	}
	
	/**
	 * The method gets all the plans   
	 * @return result Array List with the plans requested 
	 * @throws SQLException
	 */
	public ArrayList<Plan> getAllPlans() throws SQLException {
		ArrayList<Plan> result = new ArrayList<Plan>();
		result = (ArrayList<Plan>) dbPlan.selectQuery("SELECT * FROM PLANS");
		return result;
	}
	
	/**
	 * the method gets the plan id 
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * the method sets the id of the plan
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * the method get the career where the plan belongs
	 * @return the career
	 */
	public Career getCareer() {
		return career;
	}

	/**
	 * the method sets the plan's career where it belongs
	 * @param career the career to set
	 */
	public void setCareer(Career career) {
		this.career = career;
	}
	
}
