package careerLogic;

import java.util.ArrayList;

public class Plan implements PlanInterface{
	private String id;
	private Career career;
	private ArrayList<String> knowledgeAreaDesciption;
	private ArrayList<Course> courses;
	
	public Plan(String pId) {
		id = pId;
		this.knowledgeAreaDesciption = new ArrayList<String>();
		courses = new ArrayList<Course>();
	}
	public Plan(String pId, Career pCareer) {
		this(pId);
		career = pCareer;
	}
	
	public Plan() {
		// TODO Auto-generated constructor stub
	}
	public void addCourse(Course course) {
		courses.add(course);
	}
	
	public void generateNetView() {
		
	}

	public void addKnowledgeArea(String description) {
		this.knowledgeAreaDesciption.add(description);
	}
	
	/**
	 * @return the knowledgeAreaDesciption
	 */
	public ArrayList<String> getKnowledgeAreaDesciption() {
		return knowledgeAreaDesciption;
	}
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the career
	 */
	public Career getCareer() {
		return career;
	}

	/**
	 * @param career the career to set
	 */
	public void setCareer(Career career) {
		this.career = career;
	}
	
}
