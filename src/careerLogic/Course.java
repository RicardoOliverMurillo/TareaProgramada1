package careerLogic;

import java.util.ArrayList;

public class Course implements CourseInterface{
	
	private String idCourse;
	private String name;
	private int sumCredits;
	private int semester;
	private String knowledgeArea;
	private Plan plan;
	private ArrayList<String> corequisites;
	private ArrayList<String> requisites;
	private ArrayList<Course> equivalences;
	
	public Course (String idCourse, String name, int sumCredits, int semester, String knowledgeArea, Plan plan) {
		this.idCourse = idCourse;
		this.name = name;
		this.sumCredits = sumCredits;
		this.semester = semester;
		this.knowledgeArea = knowledgeArea;
		this.setPlan(plan);
		this.corequisites = new ArrayList<String>();
		this.requisites = new ArrayList<String>();
		this.equivalences = new ArrayList<Course>();
	}
	
	public Course() {
		this.equivalences = new ArrayList<Course>();
	}
	
	public boolean isAprove(ArrayList<String> courses, String id) {
		for(int i = 0; i < courses.size(); i++) {
			if(id.equals(courses.get(i))) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @return the idCourse
	 */
	public String getId() {
		return idCourse;
	}

	/**
	 * @param idCourse the idCourse to set
	 */
	public void setId(String idCourse) {
		this.idCourse = idCourse;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the sumCredits
	 */
	public int getSumCredits() {
		return sumCredits;
	}

	/**
	 * @param sumCredits the sumCredits to set
	 */
	public void setSumCredits(int sumCredits) {
		this.sumCredits = sumCredits;
	}

	/**
	 * @return the semester
	 */
	public int getSemester() {
		return semester;
	}

	/**
	 * @param semester the semester to set
	 */
	public void setSemester(int semester) {
		this.semester = semester;
	}

	/**
	 * @return the knowledgeArea
	 */
	public String getKnowledgeArea() {
		return knowledgeArea;
	}

	/**
	 * @param knowledgeArea the knowledgeArea to set
	 */
	public void setKnowledgeArea(String knowledgeArea) {
		this.knowledgeArea = knowledgeArea;
	}

	public ArrayList<String> getCorequisites() {
		return corequisites;
	}

	public ArrayList<String> getRequisites() {
		return requisites;
	}

	public ArrayList<Course> getEquivalences() {
		return equivalences;
	}

	public void addEquivalences(Course course) {
		this.equivalences.add(course);
	}
	
	public void addRequisites(String course) {
		this.requisites.add(course);
	}
	
	public void addCorequisites(String course) {
		this.corequisites.add(course);
	}

	public Plan getPlan() {
		return plan;
	}

	public void setPlan(Plan plan) {
		this.plan = plan;
	}
	
}
