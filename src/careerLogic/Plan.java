package careerLogic;

import java.util.ArrayList;

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
	/**
	 * Constructor of the class Plan with the id
	 * @param pId id of the plan
	 */
	public Plan(String pId) {
		id = pId;
		knowledgeAreaDesciption = new ArrayList<String>();
		courses = new ArrayList<Course>();
	}
	/**
	 * Constructor of the class Plan with the id and the career
	 * @param pId id of the plan
	 * @param pCareer career where the plan belongs
	 */
	public Plan(String pId, Career pCareer) {
		this(pId);
		career = pCareer;
	}
	
	public Plan() {}
	
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
