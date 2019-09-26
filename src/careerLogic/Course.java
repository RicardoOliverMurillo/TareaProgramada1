package careerLogic;

import java.util.ArrayList;

public class Course {
	private String id;
	private String name;
	private int sumCredits;
	private int semester;
	private String knowledgeArea;
	private String type;
	//private Plan plan;
	private ArrayList<Course> corequisites;
	private ArrayList<Course> requisites;
	private ArrayList<Course> equivalences;
	
	public Course (String id, String name, int sumCredits, int semester, String knowledgeArea, String type) {
		this.id = id;
		this.name = name;
		this.sumCredits = sumCredits;
		this.semester = semester;
		this.knowledgeArea = knowledgeArea;
		this.type = type;
		//insert plan line here
		this.corequisites = new ArrayList<Course>();
		this.requisites = new ArrayList<Course>();
		this.equivalences = new ArrayList<Course>();
	}
	
	public Course() {
		
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSumCredits() {
		return sumCredits;
	}

	public void setSumCredits(int sumCredits) {
		this.sumCredits = sumCredits;
	}

	public int getSemester() {
		return semester;
	}

	public void setSemester(int semester) {
		this.semester = semester;
	}

	public String getKnowledgeArea() {
		return knowledgeArea;
	}

	public void setKnowledgeArea(String knowledgeArea) {
		this.knowledgeArea = knowledgeArea;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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
