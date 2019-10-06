package careerLogic;

import java.util.ArrayList;

public class Plan implements PlanInterface{
	private String id;
	private Career career;
	private ArrayList<String> knowledgeAreaDesciption;
	private ArrayList<Course> courses;
	
	public Plan(String pId) {
		id = pId;
		knowledgeAreaDesciption = new ArrayList<String>();
		courses = new ArrayList<Course>();
	}
	public Plan(String pId, Career pCareer) {
		this(pId);
		career = pCareer;
	}
	
	public Plan() {}
	
	public void addCourse(Course course) {
		courses.add(course);
	}
	
	public void addKnowledgeArea(String description) {
		knowledgeAreaDesciption.add(description);
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
