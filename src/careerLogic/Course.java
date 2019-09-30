package careerLogic;

import java.util.ArrayList;

public class Course {
	private String id;
	private String name;
	private int sumCredits;
	private int semester;
	private String knowledgeArea;
	private Plan plan;
	private ArrayList<String> corequisites;
	private ArrayList<String> requisites;
	private ArrayList<Course> equivalences;
	
	public Course (String id, String name, int sumCredits, int semester, String knowledgeArea, Plan plan) {
		this.id = id;
		this.name = name;
		this.sumCredits = sumCredits;
		this.semester = semester;
		this.knowledgeArea = knowledgeArea;
		this.plan = plan;
		this.corequisites = new ArrayList<String>();
		this.requisites = new ArrayList<String>();
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
