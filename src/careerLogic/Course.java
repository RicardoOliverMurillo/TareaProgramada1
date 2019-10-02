package careerLogic;

import java.util.ArrayList;

public class Course {
	private String idCourse;
	private String name;
	private int sumCredits;
	private int semester;
	private String knowledgeArea;
	private String idPlan;
	//private Plan plan;
	private ArrayList<Course> corequisites;
	private ArrayList<Course> requisites;
	private ArrayList<Course> equivalences;
	
	public Course (String idCourse, String name, int sumCredits, int semester, String knowledgeArea, String idPlan) {
		this.idCourse = idCourse;
		this.name = name;
		this.sumCredits = sumCredits;
		this.semester = semester;
		this.knowledgeArea = knowledgeArea;
		this.idPlan = idPlan;
		//insert plan line here
		this.corequisites = new ArrayList<Course>();
		this.requisites = new ArrayList<Course>();
		this.equivalences = new ArrayList<Course>();
	}
	
	public Course() {
		this.equivalences = new ArrayList<Course>();
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

	/**
	 * @return the idPlan
	 */
	public String getIdPlan() {
		return idPlan;
	}

	/**
	 * @param idPlan the idPlan to set
	 */
	public void setIdPlan(String idPlan) {
		this.idPlan = idPlan;
	}

	public ArrayList<Course> getCorequisites() {
		return corequisites;
	}

	public ArrayList<Course> getRequisites() {
		return requisites;
	}

	public ArrayList<Course> getEquivalences() {
		return equivalences;
	}

	public void addEquivalences(Course course) {
		this.equivalences.add(course);
	}
	
	public void addRequisites(Course course) {
		this.requisites.add(course);
	}
	
	public void addCorequisites(Course course) {
		this.corequisites.add(course);
	}
	
}
