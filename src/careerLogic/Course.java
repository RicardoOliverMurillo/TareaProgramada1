package careerLogic;

import java.util.ArrayList;

/**
 * 
 * @author Marco Gómez, Ricardo Oliver, Anjelica Tristani 
 *
 * Class Course for the insertion and manipulation of the courses information. 
 */
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
	/**
	 * Constructor of the class Course
	 * 
	 * @param idCourse id of the new course 
	 * @param name name of the course
	 * @param sumCredits the number of credits of the course
	 * @param semester number of the semester that is located the course
	 * @param knowledgeArea the focus area of the course
	 * @param plan the plan where belongs the course
	 */
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
	
	/**
	 * the method check if the course is approved by the student
	 * @param courses of the student
	 * @param id of the student 
	 * @return boolean true if the course is approved or false if is not approved
	 */
	public boolean isAprove(ArrayList<String> courses, String id) {
		for(int i = 0; i < courses.size(); i++) {
			if(id.equals(courses.get(i))) {
				return true;
			}
		}
		return false;
	}

	/**
	 * the method get the identifier of the course
	 * @return the idCourse
	 */
	public String getId() {
		return idCourse;
	}

	/**
	 * the method set the value of the course id
	 * @param idCourse the idCourse to set
	 */
	public void setId(String idCourse) {
		this.idCourse = idCourse;
	}

	/**
	 * the method get the course name 
	 * @return name of the course
	 */
	public String getName() {
		return name;
	}

	/**
	 * the method sets the name of the course
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * the method get the number of credits of the course
	 * @return the sumCredits 
	 */
	public int getSumCredits() {
		return sumCredits;
	}

	/**
	 * the method sets the number of credits of the course
	 * @param sumCredits the sumCredits to set
	 */
	public void setSumCredits(int sumCredits) {
		this.sumCredits = sumCredits;
	}

	/**
	 * the method get the semester that the course is located
	 * @return the semester
	 */
	public int getSemester() {
		return semester;
	}

	/**
	 * the method sets the semester where the course is located
	 * @param semester the semester to set
	 */
	public void setSemester(int semester) {
		this.semester = semester;
	}

	/**
	 * the method get the name of the knowledge area that the course belongs
	 * @return the knowledgeArea
	 */
	public String getKnowledgeArea() {
		return knowledgeArea;
	}

	/**
	 * the method sets the knowledge area that the course belongs
	 * @param knowledgeArea the knowledgeArea to set
	 */
	public void setKnowledgeArea(String knowledgeArea) {
		this.knowledgeArea = knowledgeArea;
	}
	
	/**
	 * the method return an array list with the course Corequisites 
	 * @return ArrayList with the corequisites of the course
	 */
	public ArrayList<String> getCorequisites() {
		return corequisites;
	}
	/**
	 * the method return an array list with the course requisites 
	 * @return ArrayList with the requisites of the course
	 */
	public ArrayList<String> getRequisites() {
		return requisites;
	}
	
	/**
	 * the method return an array list with the course equivalences 
	 * @return ArrayList with the equivalences of the course
	 */
	public ArrayList<Course> getEquivalences() {
		return equivalences;
	}

	/**
	 * the method add to the list of equivalences of the course 
	 * @param course
	 */
	public void addEquivalences(Course course) {
		this.equivalences.add(course);
	}
	
	/**
	 * the method add to the list of requisites of the course 
	 * @param course
	 */
	public void addRequisites(String course) {
		this.requisites.add(course);
	}
	
	/**
	 * the method add to the list of corequisites of the course 
	 * @param course
	 */
	public void addCorequisites(String course) {
		this.corequisites.add(course);
	}

	/**
	 * the method get the plan where the course belongs
	 * @return plan  
	 */
	public Plan getPlan() {
		return plan;
	}

	/**
	 * the method sets the plan where the course belongs
	 * @param plan that is going to be assigned 
	 */
	public void setPlan(Plan plan) {
		this.plan = plan;
	}
	
}
